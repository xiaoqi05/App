package com.ps.app.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.ps.app.R;
import com.ps.app.base.Constant;
import com.ps.app.support.Bean.AssetListBean;
import com.ps.app.support.Bean.AssetListBean.DataBean.ListBean;
import com.ps.app.support.adapter.MyAssetRecAdapter;
import com.ps.app.ui.activity.DetailActivity;
import com.ps.app.ui.widget.HistoryThemeFooterView;
import com.ps.app.ui.widget.HistoryThemeHeaderView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zjutkz.powerfulrecyclerview.animator.impl.ZoomInAnimator;
import com.zjutkz.powerfulrecyclerview.listener.OnLoadMoreListener;
import com.zjutkz.powerfulrecyclerview.listener.OnRefreshListener;
import com.zjutkz.powerfulrecyclerview.ptr.PowerfulRecyclerView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


@SuppressLint("ValidFragment")
public class AssetsSeizedFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener, View.OnClickListener {
    private static final int DISMISS_PROGRESSBAR = 5;
    private static final int DEFAULT_LIST_SIZE = 7;
    private static final int REFRESH_DATA = 0;
    private static final int INIT_LOAD = 4;
    private static final int NO_DATA = 2;
    private static final int LOAD_MORE = 1;
    private static final int ERROR_LOAD = 3;
    private String mTitle;
    private static final String TAG = "AssetsSeizedFragment";
    private PowerfulRecyclerView recycler;
    private MyAssetRecAdapter adapter;
    private List<ListBean> datas;
    private HistoryThemeFooterView footer;
    private HistoryThemeHeaderView header;
    private int positionToRestore = 0;
    private int ps = 10;
    private int pn = 1;
    private ImageView iv_nodata_view;

    private int total = 0;

    public static AssetsSeizedFragment getInstance(String title) {
        AssetsSeizedFragment sf = new AssetsSeizedFragment();
        sf.mTitle = title;
        return sf;
    }

    private MyHandler myHandler = new MyHandler(this);

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_refresh_data) {
            showShortToast(getContext(), "刷新数据");
            recycler.hideSpecialInfoView();
        }
    }

    private static class MyHandler extends Handler {
        private WeakReference<AssetsSeizedFragment> activityWeakReference;

        public MyHandler(AssetsSeizedFragment activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            AssetsSeizedFragment fragment = activityWeakReference.get();
            if (fragment != null) {
                if (msg.what == REFRESH_DATA) {
                    fragment.datas.addAll((List<ListBean>) msg.obj);
                    fragment.adapter.notifyDataSetChanged();
                    fragment.recycler.stopRefresh();
                    if (fragment.datas.size() >= DEFAULT_LIST_SIZE) {
                        fragment.footer.setVisibility(View.VISIBLE);
                        fragment.recycler.setLoadMoreEnable(true);
                    }

                } else if (msg.what == LOAD_MORE) {
                    // activity.adapter.notifyItemRangeInserted(activity.adapter.getItemCount(), 9);
                    fragment.datas.addAll((List<ListBean>) msg.obj);
                    fragment.adapter.notifyDataSetChanged();
                    fragment.recycler.stopLoadMore();
                } else if (msg.what == NO_DATA) {
                    fragment.recycler.setLoadMoreEnable(false);
                } else if (msg.what == ERROR_LOAD) {
                    fragment.recycler.stopRefresh();
                    fragment.recycler.stopLoadMore();
                    fragment.recycler.setLoadMoreEnable(false);
                    fragment.showShortToast(fragment.getContext(), (String) msg.obj);
                    // activity.recycler.hideSpecialInfoView();
                } else if (msg.what == INIT_LOAD) {
                    //初始化加载
                    fragment.datas.addAll((List<ListBean>) msg.obj);
                    if (fragment.datas.size() == 0) {
                        fragment.recycler.showNoDataView();
                    }
                    fragment.adapter.notifyDataSetChanged();
                    fragment.recycler.stopRefresh();
                }
            }
        }
    }


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
        recycler = (PowerfulRecyclerView) v.findViewById(R.id.ptr_container);
        if (datas == null) {
            datas = new ArrayList<>();
        }
        iv_nodata_view = (ImageView) v.findViewById(R.id.iv_refresh_data);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        header = (HistoryThemeHeaderView) LayoutInflater.from(getContext()).inflate(R.layout.history_header_theme, recycler, false);
        footer = (HistoryThemeFooterView) LayoutInflater.from(getContext()).inflate(R.layout.history_footer_theme, recycler, false);
        recycler.setHeaderView(header);
        recycler.setFooterView(footer);
        recycler.setNoDataViewLayout(R.layout.no_data_view);
        LayoutInflater.from(getContext()).inflate(R.layout.no_data_view, recycler, false).findViewById(R.id.iv_refresh_data).setOnClickListener(this);
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
                //数据未加载时不能点击
                if (datas.size() <= 0) {
                    return;
                }
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("listBean", datas.get(position));
                startActivity(intent);
                Log.d(TAG, "onItemClick: " + position);
            }
        });
        recycler.setItemAnimator(new ZoomInAnimator());
        recycler.setItemAnimator(new ZoomInAnimator());
        getDatas(INIT_LOAD, ps, pn);
        adapter = new MyAssetRecAdapter(getContext(), datas);
        recycler.setAdapter(adapter);
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

    private void getDatas(final int msg, int pages, int pageNum) {
        if (ps * pn > total && total != 0) {
            if ((ps * pn - total) > ps) {
                hideSpecialView("加载完成");
                return;
            }
        }
        if (!isNetworkAvailable(getContext())) {
            hideSpecialView("网络不可以用!");
            return;
        }
        final List<ListBean> listBeen = new ArrayList<>();
        String cookie = getSharePreference(getContext(), "").getString("cookie", "");
        Log.i(TAG, "cookie:" + cookie);
        OkHttpUtils.get().addParams("pn", String.valueOf(pageNum)).addParams("ps", String.valueOf(pages)).addParams("mid",getSharePreference(getContext(),"").getInt("mid", -1) + "").addHeader("cookie", cookie)
                .url(Constant.GET_ASET_URL).build().connTimeOut(10000).execute(new UserAssetCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.i(TAG, e.toString());
                hideSpecialView("加载失败，请重试");
            }

            @Override
            public void onResponse(AssetListBean response) {
                if (response.getCode() == 2000) {
                    total = response.getData().getTotal();
                    if (response.getData().getTotal() <= DEFAULT_LIST_SIZE) {
                        recycler.setLoadMoreEnable(false);
                    }
                    //无数据时返回
                    if (total == 0) {
                        hideSpecialView("无数据");
                        iv_nodata_view.setVisibility(View.VISIBLE);
                        return;
                    }
                    pn++;
                    listBeen.addAll(response.getData().getList());
                    Message message = new Message();
                    message.what = msg;
                    message.obj = listBeen;
                    myHandler.sendMessage(message);
                }
                if (response.getCode() == 2201) {
                    hideSpecialView("你的登录失效，请重新登录");
                    return;
                }
            }
        });

    }

    private void hideSpecialView(final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Message message = new Message();
                    message.what = ERROR_LOAD;
                    message.obj = msg;
                    myHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public abstract class UserAssetCallback extends Callback<AssetListBean> {
        @Override
        public AssetListBean parseNetworkResponse(Response response) throws IOException {
            String string = response.body().string();
            return new Gson().fromJson(string, AssetListBean.class);
        }
    }

    @Override
    public void onLoadMore() {
        getDatas(LOAD_MORE, ps, pn);
    }

    @Override
    public void onRefresh() {
        getDatas(REFRESH_DATA, ps, pn);
    }

}