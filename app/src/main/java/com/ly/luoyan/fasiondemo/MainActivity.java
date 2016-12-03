package com.ly.luoyan.fasiondemo;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;
import com.ly.luoyan.fasiondemo.adapter.JianKeAdapter;
import com.ly.luoyan.fasiondemo.model.JianKeSearchModel;
import com.ly.luoyan.fasiondemo.utils.JsonUtil;
import com.ly.luoyan.fasiondemo.view.SpacesItemDecoration;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,LoadMoreWrapper.OnLoadMoreListener{
    private RecyclerView recyclerView;
    private int page = 1;
    public static final String YLL_HOME = "https://live.myyll.com/api";
    private JianKeAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreWrapper loadMoreWrapper;

    private boolean refresh = true;

    private boolean loadAll = false;

    private boolean firstLoad = true;
    /**
     * 尖刻搜索列表
     */
    public static final String YLL_JK_SEARCH_LIST = YLL_HOME + "/tag/getArticleList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        swipeRefreshLayout.setOnRefreshListener(this);
        loadMoreWrapper.setOnLoadMoreListener(this);
    }

    private void initData() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new JianKeAdapter(this,R.layout.item_fragment_fansion_new,new ArrayList<JianKeSearchModel.DataBean>());
        loadMoreWrapper = new LoadMoreWrapper(adapter);
        SpacesItemDecoration decoration=new SpacesItemDecoration(10);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(loadMoreWrapper);
        getJianKeData();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipRefresh);
    }

    private void getJianKeData() {
        KJHttp kjHttp = new KJHttp();
        HttpParams params = new HttpParams();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userAuth", getJSONO_userAuth(this));
            jsonObject.put("p", page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        params.put("json", jsonObject.toString());
        kjHttp.post(YLL_JK_SEARCH_LIST, params, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                Log.e("searchData: ", t);
                JianKeSearchModel model = JsonUtil.jsonToEntity(t, JianKeSearchModel.class);
                if (model != null) {
                    if (model.getStatus().getSucceed() == 0) {
                        if (refresh){
                            Toast.makeText(MainActivity.this,"暂无数据",Toast.LENGTH_SHORT).show();
                        }else{
                            //由于和nice保持一致，暂时这么做代表没有数据
                            loadAll = true;
                            Toast.makeText(MainActivity.this,"已加载全部",Toast.LENGTH_SHORT).show();
                        }
                        loadMoreWrapper.setLoadMoreView(0);
                        loadMoreWrapper.notifyDataSetChanged();
                    } else {
                        if (firstLoad){
                            refresh = false;
                            firstLoad = false;
                        }
                        if (refresh){
                            refresh = false;
                            page = 1;
                            adapter.getDatas().clear();
                        }else{
                            loadMoreWrapper.setLoadMoreView(R.layout.default_loading);
                        }

                        adapter.getDatas().addAll(model.getData());
                        loadMoreWrapper.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        }
                    } else {
                    loadMoreWrapper.setLoadMoreView(0);
                    loadMoreWrapper.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this,"数据错误",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                swipeRefreshLayout.setRefreshing(false);
                loadMoreWrapper.setLoadMoreView(0);
                loadMoreWrapper.notifyDataSetChanged();
                if (errorNo == -1)
                    Toast.makeText(MainActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();

            }
        });
        kjHttp = null;
        params = null;
        jsonObject = null;
    }

    public static JSONObject getJSONO_userAuth(Context context) {

        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("userId", "9");
            jsonObj.put("token", "2c8470a697177f91cb0ba900d0b6e1cb57018bab");
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }

        return jsonObj;

    }

    @Override
    public void onRefresh() {
        Log.e("onRefresh","onRefresh");
        page = 1;
        refresh = true;
        loadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        loadMoreWrapper.notifyDataSetChanged();
        getJianKeData();
    }

    @Override
    public void onLoadMoreRequested() {
        Log.e("onLoadMoreRequested","onLoadMoreRequested" + refresh + "    "+  loadAll);
        if (!refresh&&!loadAll){
            refresh = false;
            page++;
            getJianKeData();
        }
    }
}
