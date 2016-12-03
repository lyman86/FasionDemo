package com.ly.luoyan.fasiondemo.utils;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.stream.HttpUrlGlideUrlLoader;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.ViewTarget;
import com.ly.luoyan.fasiondemo.R;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public class GlideConfigration implements GlideModule {
    //
    public static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/Test3";
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        ViewTarget.setTagId(R.id.glide_tag_id);
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
        int maxMemory = (int) Runtime.getRuntime().maxMemory();//获取系统分配给应用的总内存大小
        int memoryCacheSize = maxMemory / 16;//设置图片内存缓存占用八分之一
        //设置内存缓存大小
        builder.setMemoryCache(new LruResourceCache(0));
        File file = new File(PATH);
        if (!file.exists()){
            file.mkdirs();
        }
        builder.setDiskCache(new DiskLruCacheFactory(PATH,10*1024*1024));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class,new HttpUrlGlideUrlLoader.Factory());
    }
}