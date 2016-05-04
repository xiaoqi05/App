package com.ps.app.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ps.app.R;
import com.ps.app.support.adapter.MyAssetRecentSearchRecAdapter;
import com.zjutkz.powerfulrecyclerview.animator.impl.ZoomInAnimator;
import com.zjutkz.powerfulrecyclerview.ptr.PowerfulRecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class AssetSearchActivity extends BaseActivity implements View.OnClickListener {
    public static final int DISMISS_PROGRESSBAR = 1;
    private PowerfulRecyclerView recycler;
    private MyAssetRecentSearchRecAdapter adapter;
    private List<Integer> datas;
    private int loadMoreCount = 0;
    private int positionToRestore = 0;
    private MyHandler myHandler = new MyHandler(this);

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
        initActionBar(-1, "资产查封查询");
        initRecyclerView();
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
        
     /*   LinearLayout ll_header = new LinearLayout(AssetSearchActivity.this);
        ll_header.setOrientation(LinearLayout.HORIZONTAL);
        ll_header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,DensityUtil.dip2px(AssetSearchActivity.this,48)));
        ll_header.setGravity(Gravity.CENTER_VERTICAL);
        ll_header.setPadding(DensityUtil.dip2px(AssetSearchActivity.this,16),0,DensityUtil.dip2px(AssetSearchActivity.this,16),0);
        
        TextView tv_info = new TextView(AssetSearchActivity.this);
        tv_info.setText("最近搜索");
        tv_info.setOnClickListener(AssetSearchActivity.this);
        LinearLayout.LayoutParams parmas = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        
        ll_header.addView(tv_info,parmas);*/

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
            for (int i = item-1; i >=0; i--) {
                adapter.notifyItemRemoved(i);
                datas.remove(i);
                --item;
            }
            // datas.clear();
            //adapter.notifyDataSetChanged();
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
