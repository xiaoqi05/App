package com.ps.app.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ps.app.R;
import com.ps.app.support.adapter.MyAssetRecAdapter;
import com.ps.app.ui.activity.DetailActivity;
import com.ps.app.ui.widget.HistoryThemeFooterView;
import com.ps.app.ui.widget.HistoryThemeHeaderView;
import com.zjutkz.powerfulrecyclerview.animator.impl.ZoomInAnimator;
import com.zjutkz.powerfulrecyclerview.listener.OnLoadMoreListener;
import com.zjutkz.powerfulrecyclerview.listener.OnRefreshListener;
import com.zjutkz.powerfulrecyclerview.ptr.PowerfulRecyclerView;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class AssetsSeizedFragment extends Fragment implements OnRefreshListener,OnLoadMoreListener {
    private String mTitle;
    private static final String TAG = "AssetsSeizedFragment";

    private PowerfulRecyclerView recycler;

    private MyAssetRecAdapter adapter;

    private List<Integer> datas;

    private HistoryThemeFooterView footer;

    private HistoryThemeHeaderView header;

    private int loadMoreCount = 0;

    private int positionToRestore = 0;

    public static AssetsSeizedFragment getInstance(String title) {
        AssetsSeizedFragment sf = new AssetsSeizedFragment();
        sf.mTitle = title;
        return sf;
    }

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                getDatas(1);
                adapter.notifyDataSetChanged();
                loadMoreCount = 0;
                recycler.stopRefresh();
                if(!recycler.isLoadMoreEnable()){
                    recycler.setLoadMoreEnable(true);
                }
                resetFootView();
            }else if(msg.what == 1){
                getDatas(1);
                adapter.notifyItemRangeInserted(adapter.getItemCount(), 9);
                recycler.stopLoadMore();
            }else if(msg.what == 2){
                recycler.setLoadMoreEnable(false);
            }else if(msg.what == 3){
                recycler.hideSpecialInfoView();
            }
        }
    };

    private void resetFootView() {
        footer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_asset_seized, null);
        recycler = (PowerfulRecyclerView)v.findViewById(R.id.ptr_container);

        if(datas == null){
            datas = new ArrayList<Integer>();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getDatas(1);
                adapter.notifyDataSetChanged();
                recycler.stopRefresh();
                recycler.stopLoadMore();
                if (datas.size()<9){
                    footer.setVisibility(View.INVISIBLE);
                }else {
                    footer.setVisibility(View.VISIBLE);

                }
            }
        }, 500);

        adapter = new MyAssetRecAdapter(getContext(),datas);

        recycler.setAdapter(adapter);

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        //recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        header = (HistoryThemeHeaderView) LayoutInflater.from(getContext()).inflate(R.layout.history_header_theme, recycler, false);

        footer = (HistoryThemeFooterView) LayoutInflater.from(getContext()).inflate(R.layout.history_footer_theme, recycler, false);

        recycler.setHeaderView(header);

        recycler.setFooterView(footer);

        //listHeader = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.list_header_viewpager, null);

        //  recycler.setPositionToShowBtn(4);

        // recycler.addRecyclerViewHeader(listHeader, true);

        recycler.prepareForDragAndSwipe(false, false);

        recycler.setScrollBarEnable(false);

        recycler.setOnRefreshListener(this);

        recycler.setOnLoadMoreListener(this);

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
                Toast.makeText(getContext(),"onItemClick: " + position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DetailActivity.class);
                startActivity(intent);
                Log.d(TAG, "onItemClick: " + position);
            }
        });

        recycler.setItemAnimator(new ZoomInAnimator());
        return v;
    }

    @Override
    public void onStop() {
        positionToRestore = recycler.getFirstVisiblePosition();
        Log.d(TAG, "onStop: " + recycler.getFirstVisiblePosition() + " " + recycler.getLastVisiblePosition());
        super.onStop();
    }

    @Override
    public void onResume() {
        recycler.setSelection(positionToRestore);
        super.onResume();
    }

    private void getDatas(int msg) {

        if(msg == 0){
            datas.clear();
        }

        datas.add(R.drawable.img1);
        datas.add(R.drawable.img2);
        datas.add(R.drawable.img3);
        datas.add(R.drawable.img4);
        datas.add(R.drawable.img5);
     /*   datas.add(R.drawable.img6);
        datas.add(R.drawable.img7);
        datas.add(R.drawable.img8);
        datas.add(R.drawable.img9);*/
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mHandler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onLoadMore() {
        if(++loadMoreCount <= 2){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        mHandler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }else{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        mHandler.sendEmptyMessage(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}