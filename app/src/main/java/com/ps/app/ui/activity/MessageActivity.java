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
    public static final int DISMISS_PROGRESSBAR = 1;
    private static final int DEFAULT_LIST_SIZE = 8;
    private static final int REFRESH_DATA = 0;
    private static final int INIT_LOAD = 4;
    private static final int NO_DATA = 2;
    private static final String TAG = "MessageActivity";

    private PowerfulRecyclerView recycler;

    private MyMessageRecAdapter adapter;

    private List<ListBean> datas;

    private HistoryThemeFooterView footer;

    private HistoryThemeHeaderView header;

    private int ps = 0;
    private int pn = 0;


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
                    if (!activity.recycler.isLoadMoreEnable()) {
                        activity.recycler.setLoadMoreEnable(true);
                    }
                } else if (msg.what == 1) {
                    activity.getDatas(1, 1, 5);
                    // activity.adapter.notifyItemRangeInserted(activity.adapter.getItemCount(), 9);
                    activity.recycler.stopLoadMore();
                } else if (msg.what == NO_DATA) {
                    activity.recycler.setLoadMoreEnable(false);
                } else if (msg.what == 3) {
                    activity.recycler.hideSpecialInfoView();
                } else if (msg.what == INIT_LOAD) {
                    //初始化加载
                    // List<ListBean> initData = (List<ListBean>) msg.obj;
                    activity.datas.addAll((List<ListBean>) msg.obj);
                    activity.adapter.notifyDataSetChanged();
                    activity.recycler.stopRefresh();
                    if (!activity.recycler.isLoadMoreEnable()) {
                        activity.recycler.setLoadMoreEnable(true);
                    }
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
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getDatas(INIT_LOAD, 1, 5);
                adapter.notifyDataSetChanged();
                recycler.stopRefresh();
                recycler.stopLoadMore();
            }
        }, 500);*/
        recycler.setLayoutManager(new LinearLayoutManager(this));
        header = (HistoryThemeHeaderView) LayoutInflater.from(this).inflate(R.layout.history_header_theme, recycler, false);
        footer = (HistoryThemeFooterView) LayoutInflater.from(this).inflate(R.layout.history_footer_theme, recycler, false);
        if (datas.size() < DEFAULT_LIST_SIZE) {
            footer.setVisibility(View.INVISIBLE);
        } else {
            footer.setVisibility(View.VISIBLE);
        }
        recycler.setHeaderView(header);
        recycler.setFooterView(footer);
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
                showShortToast("onItemClick: " + position);
            }
        });
        recycler.setItemAnimator(new ZoomInAnimator());
        new Thread(new Runnable() {
            @Override
            public void run() {
                //初始化数据
                getDatas(INIT_LOAD, 1, 5);
            }
        }).start();
        adapter = new MyMessageRecAdapter(this, datas);
        recycler.setAdapter(adapter);
        recycler.onRefresh();
    }

    private String result = "{\"code\":2000,\"data\":{\"endRow\":2,\"firstPage\":1,\"hasNextPage\":false,\"hasPreviousPage\":false,\"isFirstPage\":true,\"isLastPage\":true,\"lastPage\":1,\"list\":[{\"createTime\":\"2016-05-05 16:15:15\",\"id\":1,\"message\":{\"content\":\"即将到期\",\"id\":1,\"type\":\"ASSET_MANAGEMENT_MSG\"},\"mid\":1,\"successed\":0,\"unread\":0},{\"createTime\":\"2016-05-05 16:15:15\",\"id\":2,\"message\":{\"content\":\"你已离开限制区域，请回去！\",\"id\":3,\"type\":\"ZONE_WARNING\"},\"mid\":1,\"successed\":0,\"unread\":0}],\"navigatePages\":8,\"navigatepageNums\":[1],\"nextPage\":0,\"orderBy\":\"\",\"pageNum\":1,\"pageSize\":5,\"pages\":1,\"prePage\":0,\"size\":2,\"startRow\":1,\"total\":2},\"error\":\"\",\"desc\":\"成功!\"}";

    private void getDatas(final int msg, int ps, int pn) {
        final List<ListBean> listBeen = new ArrayList<>();
        String cookie = getSharePreference("").getString("cookie", "");
        showLongToast("cookie:" + cookie);
        OkHttpUtils.get().addParams("pn", String.valueOf(ps)).addParams("ps", String.valueOf(pn)).addHeader("cookie", cookie)
                .url(Constant.GET_MSG_URL).build().execute(new UserMsgCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.i(TAG, e.toString());
            }

            @Override
            public void onResponse(PushMsgListBean response) {
                if (response.getData().getSize() == 0) {
                    Log.i(TAG, "没有数据");
                    recycler.showNoDataView();
                    return;
                }
                listBeen.addAll(response.getData().getList());
                Log.i(TAG, listBeen.get(0).getMessage().toString());
                Message message = new Message();
                message.what = msg;
                message.obj = listBeen;
                myHandler.sendMessage(message);
            }
        });

       /* PushMsgListBean pushMsgListBean = new Gson().fromJson(result, PushMsgListBean.class);
        // datas = pushMsgListBean.getData().getList();*/

    }

    public abstract class UserMsgCallback extends Callback<PushMsgListBean> {
        @Override
        public PushMsgListBean parseNetworkResponse(Response response) throws IOException {
            String string = response.body().string();
            PushMsgListBean pushMsgListBean = new Gson().fromJson(string, PushMsgListBean.class);
            //Log.i(TAG, pushMsgListBean.getData().toString());
            return pushMsgListBean;
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    myHandler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    myHandler.sendEmptyMessage(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    getDatas(REFRESH_DATA,1,5);
                
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
