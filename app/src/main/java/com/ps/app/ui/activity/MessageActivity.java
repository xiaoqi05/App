package com.ps.app.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.ps.app.R;
import com.ps.app.support.Bean.PushMsgListBean;
import com.ps.app.support.Bean.PushMsgListBean.DataBean.ListBean;
import com.ps.app.support.adapter.MyMessageRecAdapter;
import com.ps.app.ui.widget.HistoryThemeFooterView;
import com.ps.app.ui.widget.HistoryThemeHeaderView;
import com.zjutkz.powerfulrecyclerview.animator.impl.ZoomInAnimator;
import com.zjutkz.powerfulrecyclerview.listener.OnLoadMoreListener;
import com.zjutkz.powerfulrecyclerview.listener.OnRefreshListener;
import com.zjutkz.powerfulrecyclerview.ptr.PowerfulRecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
    public static final int DISMISS_PROGRESSBAR = 1;

    private PowerfulRecyclerView recycler;

    private MyMessageRecAdapter adapter;

    private List<ListBean> datas;

    private HistoryThemeFooterView footer;

    private HistoryThemeHeaderView header;

    private int loadMoreCount = 0;


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
                if (msg.what == 0) {
                    activity.getDatas(0);
                    activity.adapter.notifyDataSetChanged();
                    activity.loadMoreCount = 0;
                    activity.recycler.stopRefresh();
                    if (!activity.recycler.isLoadMoreEnable()) {
                        activity.recycler.setLoadMoreEnable(true);
                    }
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
        setContentView(R.layout.activity_message);
        initActionBar(-1, "消息提醒");
        initRecylerView();
    }

    private void initRecylerView() {
        recycler = (PowerfulRecyclerView) findViewById(R.id.ptr_container);
        if (datas == null) {
            datas = new ArrayList<ListBean>();
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
        adapter = new MyMessageRecAdapter(this, datas);
        recycler.setAdapter(adapter);
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

    private String result = "{\"code\":2000,\"data\":{\"endRow\":2,\"firstPage\":1,\"hasNextPage\":false,\"hasPreviousPage\":false,\"isFirstPage\":true,\"isLastPage\":true,\"lastPage\":1,\"list\":[{\"createTime\":\"2016-05-05 16:15:15\",\"id\":1,\"message\":{\"content\":\"即将到期\",\"id\":1,\"type\":\"ASSET_MANAGEMENT_MSG\"},\"mid\":1,\"successed\":0,\"unread\":0},{\"createTime\":\"2016-05-05 16:15:15\",\"id\":2,\"message\":{\"content\":\"你已离开限制区域，请回去！\",\"id\":3,\"type\":\"ZONE_WARNING\"},\"mid\":1,\"successed\":0,\"unread\":0}],\"navigatePages\":8,\"navigatepageNums\":[1],\"nextPage\":0,\"orderBy\":\"\",\"pageNum\":1,\"pageSize\":5,\"pages\":1,\"prePage\":0,\"size\":2,\"startRow\":1,\"total\":2},\"error\":\"\",\"desc\":\"成功!\"}";

    private void getDatas(int msg) {

        if (msg == 0) {
            datas.clear();
        }
        //datas.add(R.drawable.img1);
       /* for (int i = 0; i < 10; i++) {
            ListBean listBean = new ListBean();
            ListBean.MessageBean messageBean = new ListBean.MessageBean();
            messageBean.setContent("haha" + i);
            listBean.setMessage(messageBean);
            datas.add(listBean);
        }*/
        System.out.println(datas);

        PushMsgListBean pushMsgListBean = new Gson().fromJson(result, PushMsgListBean.class);
       // datas = pushMsgListBean.getData().getList();
        datas.addAll(pushMsgListBean.getData().getList());
        System.out.println(datas.get(0).getMessage().toString());

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
        if (++loadMoreCount <= 2) {
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
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        myHandler.sendEmptyMessage(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    myHandler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
