package com.ly.luoyan.fasiondemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.ly.luoyan.fasiondemo.R;
import com.ly.luoyan.fasiondemo.model.JianKeSearchModel;
import com.ly.luoyan.fasiondemo.utils.WindowUtil;
import com.ly.luoyan.fasiondemo.view.CustomLableView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import java.util.List;

/**
 * Created by luoyan on 2016/12/1.
 */

public class JianKeAdapter extends CommonAdapter<JianKeSearchModel.DataBean>{
    private int screenWidth;
    private Context context;

    private BitmapRequestBuilder<GlideUrl,Bitmap> requestBuilder;

    public static DisplayImageOptions baseOptions = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.image_null)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .showImageForEmptyUri(R.mipmap.image_null)
            .showImageOnFail(R.mipmap.nice_default).cacheInMemory(false)
            .cacheOnDisk(true).displayer(new FadeInBitmapDisplayer(1000))
            .build();
    public JianKeAdapter(Context context, int layoutId, List<JianKeSearchModel.DataBean> datas) {
        super(context, layoutId, datas);
        screenWidth = WindowUtil.getWindow(context).winth;
        this.context = context;

        requestBuilder = Glide.with(context).from(GlideUrl.class).asBitmap().dontAnimate().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
    }

    @Override
    protected void convert(ViewHolder holder, JianKeSearchModel.DataBean jianKeSearchModel, int position) {
        ImageView ivPic = holder.getView(R.id.item_fragment_iv);
        ivPic.setScaleType(ImageView.ScaleType.FIT_XY);
        int picWidth =  jianKeSearchModel.getAlbum().get(0).getWidth();
        int picHeight = jianKeSearchModel.getAlbum().get(0).getHeight();
        float ratio = picWidth *1.0f/((screenWidth - 10)/2);
        int showWidth;
        int showHeight;
        showHeight = (int) (picHeight *1.0f/ratio);
        showWidth = (screenWidth - 10)/2;
        if (picWidth!=0&&picHeight!=0){
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(showWidth, showHeight);
            holder.getConvertView().setLayoutParams(params);
        }

//        if (jianKeSearchModel.getAlbum().size()>0){
//            requestBuilder.load(new GlideUrl(jianKeSearchModel.getAlbum().get(0).getUrl())).
//                    skipMemoryCache(true).
//                    placeholder(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).
//                    error(R.mipmap.ic_launcher).
//                    into(ivPic);
//        }
        ImageLoader.getInstance().displayImage(jianKeSearchModel.getAlbum().get(0).getUrl(),ivPic, baseOptions);


        CustomLableView customLableView = holder.getView(R.id.item_fragment_lable);
        try {
            String word = "";
            if (jianKeSearchModel.getAlbum().get(0).getTag().get(0).getWords().length()>10){
                word = jianKeSearchModel.getAlbum().get(0).getTag().get(0).getWords().substring(0,10);
            }else{
                word = jianKeSearchModel.getAlbum().get(0).getTag().get(0).getWords();
            }
            customLableView.setText(word);
        }catch (Exception e){
            e.printStackTrace();
        }
        TextView tvZanCount = holder.getView(R.id.tv_jk_zan);
        tvZanCount.setText(""+jianKeSearchModel.getLikes());
    }
}
