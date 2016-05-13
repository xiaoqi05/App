package com.ps.app.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.TextView;

import com.ps.app.R;
import com.ps.app.support.Bean.PushMsgListBean.DataBean.ListBean;

public class MessageDetailActivity extends BaseActivity {
    private static final int FREEMAN = 8;
    private ViewStub viewStub;
    private TextView tv_show_all_info;

    private TextView tv_msg_detail_id_info;
    private TextView tv_msg_detail_content_info;
    private TextView tv_msg_detail_time_info;
    private TextView tv_msg_detail_type_info;

    private int source = 0;

    private boolean isShowAllInfo = false;
    private ListBean listBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        initActionBar(-1, "消息详情");
        findView();
        initData();
    }

    private void initData() {
        //消息详情
        listBean = (ListBean) getIntent().getSerializableExtra("listBean");
        tv_msg_detail_id_info.setText(listBean.getId() + "");
        tv_msg_detail_content_info.setText(listBean.getMessage().getContent());
        tv_msg_detail_time_info.setText(listBean.getCreateTime());
        tv_msg_detail_type_info.setText(listBean.getMessage().getType());
    }

    private void findView() {
        tv_msg_detail_id_info = (TextView) findViewById(R.id.tv_msg_detail_id_info);
        tv_msg_detail_content_info = (TextView) findViewById(R.id.tv_msg_detail_content_info);
        tv_msg_detail_time_info = (TextView) findViewById(R.id.tv_msg_detail_time_info);
        tv_msg_detail_type_info = (TextView) findViewById(R.id.tv_msg_detail_type_info);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
