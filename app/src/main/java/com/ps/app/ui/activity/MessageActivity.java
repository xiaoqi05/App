package com.ps.app.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.ps.app.R;
import com.ps.app.base.Constant;
import com.ps.app.support.Bean.PushMsgListBean;
import com.ps.app.support.Bean.PushMsgListBean.DataBean.ListBean;
import com.ps.app.support.adapter.MyMessageRecAdapter;
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

public class MessageActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
    private static final int DEFAULT_LIST_SIZE = 7;
    private static final int REFRESH_DATA = 0;
    private static final int INIT_LOAD = 4;
    private static final int NO_DATA = 2;
    private static final int LOAD_MORE = 1;
    private static final int ERROR_LOAD = 3;

    private static final String TAG = "MessageActivity";

    private PowerfulRecyclerView recycler;

    private MyMessageRecAdapter adapter;

    private List<ListBean> datas;

    private HistoryThemeFooterView footer;

    private HistoryThemeHeaderView header;

    private int ps = 10;
    private int pn = 1;
    private int total = 0;


    private int positionToRestore = 0;
    private MyHandler myHandler = new MyHandler(this);

    private static class MyHandler extends Handler {


        private WeakReference<MessageActivity> activityWeakReference;

        public MyHandler(MessageActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MessageActivity activity = activityWeakReference.get();
            if (activity != null) {
                if (msg.what == REFRESH_DATA) {
                    activity.datas.addAll((List<ListBean>) msg.obj);
                    activity.adapter.notifyDataSetChanged();
                    activity.recycler.stopRefresh();
                    if (activity.datas.size() >= DEFAULT_LIST_SIZE) {
                        activity.footer.setVisibility(View.VISIBLE);
                        activity.recycler.setLoadMoreEnable(true);
                    }

                } else if (msg.what == LOAD_MORE) {
                    // activity.adapter.notifyItemRangeInserted(activity.adapter.getItemCount(), 9);
                    activity.datas.addAll((List<ListBean>) msg.obj);
                    activity.adapter.notifyDataSetChanged();
                    activity.recycler.stopLoadMore();
                } else if (msg.what == NO_DATA) {
                    activity.recycler.setLoadMoreEnable(false);
                } else if (msg.what == ERROR_LOAD) {
                    activity.recycler.stopRefresh();
                    activity.recycler.stopLoadMore();
                    activity.recycler.setLoadMoreEnable(false);
                    activity.showLongToast((String) msg.obj);
                    // activity.recycler.hideSpecialInfoView();
                } else if (msg.what == INIT_LOAD) {
                    //初始化加载
                    activity.datas.addAll((List<ListBean>) msg.obj);
                    if (activity.datas.size() == 0) {
                        activity.recycler.showNoDataView();
                    }
                    activity.adapter.notifyDataSetChanged();
                    activity.recycler.stopRefresh();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initActionBar(-1, "消息提醒");
        initRecylerView();
    }


    private void initRecylerView() {
        recycler = (PowerfulRecyclerView) findViewById(R.id.ptr_container);
        if (datas == null) {
            datas = new ArrayList<>();
        }
        recycler.setLayoutManager(new LinearLayoutManager(this));
        header = (HistoryThemeHeaderView) LayoutInflater.from(this).inflate(R.layout.history_header_theme, recycler, false);
        footer = (HistoryThemeFooterView) LayoutInflater.from(this).inflate(R.layout.history_footer_theme, recycler, false);
        recycler.setHeaderView(header);
        recycler.setFooterView(footer);
        recycler.prepareForDragAndSwipe(false, false);
        recycler.setScrollBarEnable(false);
        recycler.setOnRefreshListener(this);
        recycler.setOnLoadMoreListener(this);
        recycler.setOnItemClickListener(new PowerfulRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, RecyclerView.ViewHolder holder, int position) {

                showShortToast("onItemClick: " + position);
            }
        });
        recycler.setItemAnimator(new ZoomInAnimator());
        getDatas(INIT_LOAD, ps, pn);
        adapter = new MyMessageRecAdapter(this, datas);
        recycler.setAdapter(adapter);
    }


    private void getDatas(final int msg, int pages, int pageNum) {
        if (ps * pn > total && total != 0) {
            hideSpecialView("加载完成");
            return;
        }
        final List<ListBean> listBeen = new ArrayList<>();
        String cookie = getSharePreference("").getString("cookie", "");
        Log.i(TAG, "cookie:" + cookie);
        OkHttpUtils.get().addParams("pn", String.valueOf(pageNum)).addParams("ps", String.valueOf(pages)).addHeader("cookie", cookie)
                .url(Constant.GET_MSG_URL).build().connTimeOut(10000).execute(new UserMsgCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.i(TAG, e.toString());
                hideSpecialView("加载失败，请重试");
            }

            @Override
            public void onResponse(PushMsgListBean response) {
                total = response.getData().getTotal();
                if (pn * ps > total) {
                    recycler.setLoadMoreEnable(false);
                }
                if (total == 0) {
                    recycler.showNoDataView();
                    return;
                }
                pn++;
                listBeen.addAll(response.getData().getList());
                Log.i(TAG, listBeen.get(0).getMessage().toString());
                Message message = new Message();
                message.what = msg;
                message.obj = listBeen;
                myHandler.sendMessage(message);
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

    public abstract class UserMsgCallback extends Callback<PushMsgListBean> {
        @Override
        public PushMsgListBean parseNetworkResponse(Response response) throws IOException {
            String string = response.body().string();
            return new Gson().fromJson(string, PushMsgListBean.class);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_message, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_message:
                showShortToast("清空消息");
                //带动画的删除所有
                int item_data = datas.size();
                for (int i = item_data - 1; i >= 0; i--) {
                    adapter.notifyItemRemoved(i);
                    datas.remove(i);
                    --item_data;
                }
                footer.setVisibility(View.INVISIBLE);
                return true;
            case R.id.marker_already_read:
                showShortToast("标记为已读");
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

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
