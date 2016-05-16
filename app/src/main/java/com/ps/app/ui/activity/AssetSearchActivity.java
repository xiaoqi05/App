package com.ps.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.ps.app.R;
import com.ps.app.base.Constant;
import com.ps.app.support.Bean.AssetListBean;
import com.ps.app.support.Bean.FreeManListBean;
import com.ps.app.support.adapter.MyAssetRecAdapter;
import com.ps.app.support.adapter.MyFreeManAdapter;
import com.rey.material.widget.Button;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zjutkz.powerfulrecyclerview.animator.impl.ZoomInAnimator;
import com.zjutkz.powerfulrecyclerview.ptr.PowerfulRecyclerView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

//资产搜索和保外人员搜索
public class AssetSearchActivity extends BaseActivity implements View.OnClickListener {
    public static final int DISMISS_PROGRESSBAR = 1;
    private static final String TAG = "AssetSearchActivity";
    private static final int FREEMAN = 8;
    private PowerfulRecyclerView recycler;
    private Button bt_search;
    private MyAssetRecAdapter adapter;
    private MyFreeManAdapter myFreeManAdapter;
    private List<AssetListBean.DataBean.ListBean> datas;
    private List<FreeManListBean.DataBean.ListBean> freeManListdatas;
    private EditText et_asset_search;
    private int loadMoreCount = 0;
    private int positionToRestore = 0;
    private int id;
    private MyHandler myHandler = new MyHandler(this);
    private int ps = 10;
    private int pn = 1;

    private static class MyHandler extends Handler {


        private WeakReference<AssetSearchActivity> activityWeakReference;

        public MyHandler(AssetSearchActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            AssetSearchActivity activity = activityWeakReference.get();
            if (activity != null) {

            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_search);
        id = getIntent().getIntExtra("id", 0);
        if (id == 0) {
            initActionBar(-1, "资产查封查询");
        } else {
            initActionBar(-1, "保外人员查询");
        }
        Log.i(TAG, id + "id" + id);
        findView();
        initRecyclerView();
    }

    private void findView() {
        bt_search = (Button) findViewById(R.id.bt_search);
        et_asset_search = (EditText) findViewById(R.id.et_asset_search);
        assert bt_search != null;
        bt_search.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initRecyclerView() {
        recycler = (PowerfulRecyclerView) findViewById(R.id.ptr_recent_search_container);
        if (datas == null || freeManListdatas == null) {
            datas = new ArrayList<>();
            freeManListdatas = new ArrayList<>();
        }
        if (id == 0) {
            adapter = new MyAssetRecAdapter(this, datas);
            recycler.setAdapter(adapter);
        } else {
            myFreeManAdapter = new MyFreeManAdapter(this, freeManListdatas);
            recycler.setAdapter(myFreeManAdapter);
        }
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.prepareForDragAndSwipe(false, false);
        recycler.setScrollBarEnable(false);
        RelativeLayout rl_ptr_asset_search_header = (RelativeLayout) LayoutInflater.from(AssetSearchActivity.this).inflate(R.layout.ptr_asset_search_header, recycler, false);
        ImageView iv_delete = (ImageView) rl_ptr_asset_search_header.findViewById(R.id.iv_search_delete);
        iv_delete.setOnClickListener(this);
        recycler.addRecyclerViewHeader(rl_ptr_asset_search_header);
        recycler.setOnItemClickListener(new PowerfulRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, RecyclerView.ViewHolder holder, int position) {
                showShortToast("onItemClick: " + position);
                if (id == 0) {
                    Intent intent = new Intent(AssetSearchActivity.this, DetailActivity.class);
                    intent.putExtra("listBean", datas.get(position));
                    startActivity(intent);
                }
                if (id == 1) {
                    Intent intent = new Intent(AssetSearchActivity.this, DetailActivity.class);
                    intent.putExtra("listBean", freeManListdatas.get(position));
                    intent.putExtra("source", FREEMAN);
                    startActivity(intent);
                }
            }
        });
        recycler.setItemAnimator(new ZoomInAnimator());
    }

    private void removeAllData() {
        if (id == 0) {
            int item = datas.size();
            for (int i = item - 1; i >= 0; i--) {
                adapter.notifyItemRemoved(i);
                datas.remove(i);
                --item;
            }
        }

        if (id == 1) {
            int item = freeManListdatas.size();
            for (int i = item - 1; i >= 0; i--) {
                myFreeManAdapter.notifyItemRemoved(i);
                freeManListdatas.remove(i);
                --item;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_search_delete) {
            showShortToast("删除最近搜索");
            //带动画的删除所有
            removeAllData();
        }
        if (v.getId() == R.id.bt_search) {
            String search_content = et_asset_search.getText().toString().trim();
            if (TextUtils.isEmpty(search_content)) {
                showShortToast("请填写搜索内容");
                return;
            }
            removeAllData();
            if (id == 0) {
                //资产搜索
                startAssetSearch(search_content);
            } else if (id == 1) {
                //保外人员搜索
                startFreeManSearch(search_content);
            }
        }

    }

    private void startFreeManSearch(String search_content) {
        String cookie = getSharePreference("").getString("cookie", "");
        Log.i(TAG, "cookie:" + cookie);
        OkHttpUtils.get().addParams("pn", String.valueOf(pn)).addParams("ps", String.valueOf(ps)).addParams("name", search_content).addHeader("cookie", cookie)
                .url(Constant.FREE_MAN_URL).build().connTimeOut(10000).execute(new UserFreemanDetailCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.i(TAG, e.toString());
            }

            @Override
            public void onResponse(FreeManListBean response) {
                if (response.getCode() == 2000) {
                    freeManListdatas.addAll(response.getData().getList());
                    myFreeManAdapter.notifyDataSetChanged();
                }
                if (response.getCode() == 2201) {

                    return;
                }
            }
        });
    }

    private void startAssetSearch(String search_content) {
        String cookie = getSharePreference("").getString("cookie", "");
        Log.i(TAG, "cookie:" + cookie);
        OkHttpUtils.post().addParams("pn", String.valueOf(pn)).addParams("ps", String.valueOf(ps)).addParams("name", search_content).addHeader("cookie", cookie)
                .url(Constant.ASET_SEARCH_URL).build().connTimeOut(10000).execute(new UserAssetDetailCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.i(TAG, e.toString());
            }

            @Override
            public void onResponse(AssetListBean response) {
                if (response.getCode() == 2000) {
                    datas.addAll(response.getData().getList());
                    adapter.notifyDataSetChanged();
                }
                if (response.getCode() == 2201) {

                    return;
                }
            }
        });
    }

    private abstract class UserAssetDetailCallback extends Callback<AssetListBean> {
        @Override
        public AssetListBean parseNetworkResponse(Response response) throws IOException {
            String string = response.body().string();
            return new Gson().fromJson(string, AssetListBean.class);
        }
    }

    private abstract class UserFreemanDetailCallback extends Callback<FreeManListBean> {
        @Override
        public FreeManListBean parseNetworkResponse(Response response) throws IOException {
            String string = response.body().string();
            return new Gson().fromJson(string, FreeManListBean.class);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
