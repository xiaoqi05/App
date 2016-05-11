package com.ps.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.ps.app.R;
import com.ps.app.support.Bean.AssetListBean;
import com.ps.app.support.Bean.FreeManListBean;
import com.ps.app.support.utils.DateFormat;

public class DetailActivity extends BaseActivity {
    private static final int FREEMAN = 8;
    private ViewStub viewStub;
    private TextView tv_show_all_info;

    private TextView tv_detail_name_info;
    private TextView tv_detail_time_info;
    private TextView tv_detail_thing_name_info;
    private TextView tv_detail_id_info;
    private int source = 0;

    private boolean isShowAllInfo = false;
    private AssetListBean.DataBean.ListBean listBean;
    private FreeManListBean.DataBean.ListBean freeManBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initActionBar(-1, "详情");
        findView();
        initData();

    }

    private void initData() {
        if (getIntent().getIntExtra("source", 1) == FREEMAN) {
            //保外人员
            source = FREEMAN;
            freeManBean = (FreeManListBean.DataBean.ListBean) getIntent().getSerializableExtra("listBean");
            tv_detail_name_info.setText(freeManBean.getName());
            tv_detail_time_info.setText(DateFormat.dateFormat(freeManBean.getCreateTime()));
            // tv_detail_thing_name_info.setText(freeManBean.getMemberTo().getName());
            //  tv_detail_id_info.setText(freeManBean.getCaseTo().getId() + "");
        } else {
            //资产查封
            listBean = (AssetListBean.DataBean.ListBean) getIntent().getSerializableExtra("listBean");
            tv_detail_name_info.setText(listBean.getMemberTo().getDisplayName());
            tv_detail_time_info.setText(DateFormat.dateFormat(listBean.getEndTime()));
            //案件名字
            tv_detail_thing_name_info.setText(listBean.getMemberTo().getName());
            tv_detail_id_info.setText(listBean.getCaseTo().getId() + "");
        }
    }

    private void findView() {
        viewStub = (ViewStub) findViewById(R.id.vs_stub);
        tv_detail_name_info = (TextView) findViewById(R.id.tv_detail_name_info);
        tv_detail_time_info = (TextView) findViewById(R.id.tv_detail_time_info);
        tv_detail_thing_name_info = (TextView) findViewById(R.id.tv_detail_thing_name_info);
        tv_show_all_info = (TextView) findViewById(R.id.tv_show_all_info);
        tv_detail_id_info = (TextView) findViewById(R.id.tv_detail_id_info);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (source==FREEMAN){
            getMenuInflater().inflate(R.menu.menu_detail, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.route_line) {
            Intent intent = new Intent(DetailActivity.this, RouterActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAllInfo(View v) {
        tv_show_all_info.setVisibility(View.GONE);
        View vs = viewStub.inflate();
        TextView tv_phone = (TextView) vs.findViewById(R.id.tv_detail_phone_info);
        TextView tv_time = (TextView) vs.findViewById(R.id.tv_detail_time_info);
        TextView tv_type = (TextView) vs.findViewById(R.id.tv_detail_type_info);
        if (source == FREEMAN) {

        } else {
            tv_phone.setText(listBean.getMemberTo().getPhone());
            tv_time.setText(DateFormat.dateFormat(listBean.getStartTime()));
            tv_type.setText(listBean.getMemberTo().getType() + "");
        }
        // tv_info.setText("哈哈: ");
        View view = findViewById(R.id.vs_stub);
        view = findViewById(R.id.ac_detail_id_after_inflate);
    }

}
