package com.ps.app.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.ps.app.R;
import com.ps.app.base.Constant;
import com.ps.app.support.Bean.AssetListBean;
import com.ps.app.support.adapter.MyAssetRecentSearchRecAdapter;
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

public class AssetSearchActivity extends BaseActivity implements View.OnClickListener {
    public static final int DISMISS_PROGRESSBAR = 1;
    private static final String TAG = "AssetSearchActivity";
    private PowerfulRecyclerView recycler;
    private Button bt_search;
    private MyAssetRecentSearchRecAdapter adapter;
    private List<Integer> datas;
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
                if (msg.what == 0) {
                    activity.getDatas(0);
                    activity.adapter.notifyDataSetChanged();
                    activity.loadMoreCount = 0;

                } else if (msg.what == 1) {
                    activity.getDatas(1);
                    activity.adapter.notifyItemRangeInserted(activity.adapter.getItemCount(), 9);
                    activity.recycler.stopLoadMore();
                } else if (msg.what == 2) {
                    activity.recycler.setLoadMoreEnable(false);
                } else if (msg.what == 3) {
                    activity.recycler.hideSpecialInfoView();
                }
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
        Log.i(TAG,id+"id"+id);
        findView();
        initRecyclerView();
    }

    private void findView() {
        bt_search = (Button) findViewById(R.id.bt_search);
        assert bt_search != null;
        bt_search.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (id == 0) {
            initActionBar(-1, "资产查封查询");
        } else {
            initActionBar(-1, "保外人员查询");
        }
    }

    private void initRecyclerView() {
        recycler = (PowerfulRecyclerView) findViewById(R.id.ptr_recent_search_container);
        if (datas == null) {
            datas = new ArrayList<Integer>();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getDatas(1);
                adapter.notifyDataSetChanged();
                recycler.stopRefresh();
                recycler.stopLoadMore();
            }
        }, 500);
        adapter = new MyAssetRecentSearchRecAdapter(this, datas);
        recycler.setAdapter(adapter);
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
               /* if (position == 0) {
                    datas.add(1, R.mipmap.ic_launcher);
                    adapter.notifyItemInserted(1);
                } else if (position == 1) {
                    datas.remove(1);
                    adapter.notifyItemRemoved(1);
                }*/
                showShortToast("onItemClick: " + position);
            }
        });

        recycler.setItemAnimator(new ZoomInAnimator());
    }

    private void getDatas(int msg) {
        if (msg == 0) {
            datas.clear();
        }
        datas.add(R.drawable.img1);
        datas.add(R.drawable.img1);
        datas.add(R.drawable.img1);
        datas.add(R.drawable.img1);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_search_delete) {
            showShortToast("删除最近搜索");
            //带动画的删除所有
            int item = datas.size();
            for (int i = item - 1; i >= 0; i--) {
                adapter.notifyItemRemoved(i);
                datas.remove(i);
                --item;
            }
        }
        if (v.getId() == R.id.bt_search) {
            //// TODO: 2016-05-15 搜索
            if (id == 0) {
                //资产搜索
                startAssetSearch();
            } else if (id == 1) {
                //保外人员搜索
                startFreeManSearch();
            }
        }

    }

    private void startFreeManSearch() {

    }

    private void startAssetSearch() {
        String cookie = getSharePreference("").getString("cookie", "");
        Log.i(TAG, "cookie:" + cookie);
        OkHttpUtils.post().addParams("pn", String.valueOf(pn)).addParams("ps", String.valueOf(ps)).addParams("name","A").addHeader("cookie", cookie)
                .url(Constant.ASET_SEARCH_URL).build().connTimeOut(10000).execute(new UserAssetDetailCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.i(TAG, e.toString());
            }

            @Override
            public void onResponse(AssetListBean response) {
                if (response.getCode() == 2000) {
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
