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

    private TextView tv_detail_name;
    private TextView tv_detail_time;
    private TextView tv_detail_thing_name;
    private TextView tv_detail_id;
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
            tv_detail_id.setText("案件序号: ");
            tv_detail_name.setText("嫌疑人：    ");
            tv_detail_time.setText("案件名称：");
            tv_detail_thing_name.setText("立案时间：   ");
            //立案时间
            tv_detail_thing_name_info.setText(DateFormat.dateFormat(freeManBean.getCreateTime()));
            //嫌疑人
            tv_detail_name_info.setText(freeManBean.getMemberName());
            //案件名称  //DateFormat.dateFormat(freeManBean.getCreateTime()
            tv_detail_time_info.setText(freeManBean.getName());
            //案件序号
            tv_detail_id_info.setText(freeManBean.getId() + "");
        } else {
            //资产查封
            listBean = (AssetListBean.DataBean.ListBean) getIntent().getSerializableExtra("listBean");
            tv_detail_name_info.setText(listBean.getName());
            tv_detail_time_info.setText(listBean.getCreateTime());
            //案件名字
            tv_detail_thing_name_info.setText(listBean.getCaseName());
            tv_detail_id_info.setText(listBean.getId() + "");
        }
    }

    private void findView() {
        viewStub = (ViewStub) findViewById(R.id.vs_stub);
        tv_detail_name_info = (TextView) findViewById(R.id.tv_detail_name_info);
        tv_detail_time_info = (TextView) findViewById(R.id.tv_detail_time_info);
        tv_detail_thing_name_info = (TextView) findViewById(R.id.tv_detail_thing_name_info);
        tv_show_all_info = (TextView) findViewById(R.id.tv_show_all_info);
        tv_detail_id_info = (TextView) findViewById(R.id.tv_detail_id_info);

        tv_detail_name = (TextView) findViewById(R.id.tv_detail_name);
        tv_detail_time = (TextView) findViewById(R.id.tv_detail_time);
        tv_detail_thing_name = (TextView) findViewById(R.id.tv_detail_thing_name);
        tv_detail_id = (TextView) findViewById(R.id.tv_detail_id);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (source == FREEMAN) {
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
            //// TODO: 2016-05-13 保外人员mid 
            intent.putExtra("mid", freeManBean.getMemberTo().getId() + "");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAllInfo(View v) {
        tv_show_all_info.setVisibility(View.GONE);
        View vs = viewStub.inflate();

        TextView tv_gender = (TextView) vs.findViewById(R.id.tv_show_phone);
        TextView tv_type_thing_info = (TextView) vs.findViewById(R.id.tv_detail_time);
        TextView tv_detail_type1 = (TextView) vs.findViewById(R.id.tv_detail_type);
        TextView tv_detail_type2 = (TextView) vs.findViewById(R.id.tv_detail_type2);

        TextView tv_detail_more3 = (TextView) vs.findViewById(R.id.tv_detail_more3);
        TextView tv_detail_more4 = (TextView) vs.findViewById(R.id.tv_detail_more4);
        TextView tv_detail_more5 = (TextView) vs.findViewById(R.id.tv_detail_more5);
        TextView tv_detail_more6 = (TextView) vs.findViewById(R.id.tv_detail_more6);
        TextView tv_detail_more7 = (TextView) vs.findViewById(R.id.tv_detail_more7);
        TextView tv_detail_more8 = (TextView) vs.findViewById(R.id.tv_detail_more8);
        TextView tv_detail_more9 = (TextView) vs.findViewById(R.id.tv_detail_more9);
        TextView tv_detail_more10 = (TextView) vs.findViewById(R.id.tv_detail_more10);
        TextView tv_detail_more11 = (TextView) vs.findViewById(R.id.tv_detail_more11);
        TextView tv_detail_more12 = (TextView) vs.findViewById(R.id.tv_detail_more12);
        TextView tv_detail_more13 = (TextView) vs.findViewById(R.id.tv_detail_more13);


        TextView tv_phone = (TextView) vs.findViewById(R.id.tv_detail_phone_info);
        TextView tv_time = (TextView) vs.findViewById(R.id.tv_detail_time_info);
        TextView tv_type = (TextView) vs.findViewById(R.id.tv_detail_type_info);
        TextView tv_type2 = (TextView) vs.findViewById(R.id.tv_detail_type_info2);

        TextView tv_detail_more_info3 = (TextView) vs.findViewById(R.id.tv_detail_more_info3);
        TextView tv_detail_more_info4 = (TextView) vs.findViewById(R.id.tv_detail_more_info4);
        TextView tv_detail_more_info5 = (TextView) vs.findViewById(R.id.tv_detail_more_info5);
        TextView tv_detail_more_info6 = (TextView) vs.findViewById(R.id.tv_detail_more_info6);
        TextView tv_detail_more_info7 = (TextView) vs.findViewById(R.id.tv_detail_more_info7);
        TextView tv_detail_more_info8 = (TextView) vs.findViewById(R.id.tv_detail_more_info8);
        TextView tv_detail_more_info9 = (TextView) vs.findViewById(R.id.tv_detail_more_info9);
        TextView tv_detail_more_info10 = (TextView) vs.findViewById(R.id.tv_detail_more_info10);
        TextView tv_detail_more_info11 = (TextView) vs.findViewById(R.id.tv_detail_more_info11);
        TextView tv_detail_more_info12 = (TextView) vs.findViewById(R.id.tv_detail_more_info12);
        TextView tv_detail_more_info13 = (TextView) vs.findViewById(R.id.tv_detail_more_info13);

        if (source == FREEMAN) {
            //取保日期
            tv_gender.setText("拘留日期：");
            tv_phone.setText(DateFormat.dateFormat(freeManBean.getArrestStartTime()));
            //拘留到期日期
            tv_type_thing_info.setText("拘留到期日期:");
            tv_time.setText(DateFormat.dateFormat(freeManBean.getArrestEndTime()));
            //取保日期
            tv_detail_type1.setText("取保日期： ");
            tv_type.setText(DateFormat.dateFormat(freeManBean.getDetainStartTime()));
            //取保到期日期
            tv_detail_type2.setText("取保到期日期:");
            tv_type2.setText(DateFormat.dateFormat(freeManBean.getDetainEndTime()));
            //监居日期
            tv_detail_more3.setText("监居日期:");
            tv_detail_more_info3.setText(DateFormat.dateFormat(freeManBean.getPrisonedStartTime()));
            //监居到期日期
            tv_detail_more4.setText("监居到期日期:");
            tv_detail_more_info4.setText(DateFormat.dateFormat(freeManBean.getPrisonedEndTime()));
            //逮捕日期
            tv_detail_more5.setText("逮捕日期:");
            tv_detail_more_info5.setText(DateFormat.dateFormat(freeManBean.getReleaseStartTime()));
            //逮捕到期日期
            tv_detail_more6.setText("逮捕到期日期:");
            tv_detail_more_info6.setText(DateFormat.dateFormat(freeManBean.getReleaseEndTime()));
            //移诉日期
            tv_detail_more7.setText("移诉日期:");
            tv_detail_more_info7.setText(DateFormat.dateFormat(freeManBean.getShiftTime()));
            //办案民警
            tv_detail_more8.setText("办案民警:");
            tv_detail_more_info8.setText(freeManBean.getPoliceName());
            //level
            tv_detail_more9.setText("level:");
            tv_detail_more_info9.setText(freeManBean.getLevel() + "");

            tv_detail_more10.setText("(1：继续追究刑事责任:");
            tv_detail_more10.setTextSize(12);
            tv_detail_more_info10.setText("0：解除强措1年后未诉出案件需撤销)");
            tv_detail_more_info10.setTextSize(12);

            if (freeManBean.getMemberTo() != null) {
                //性别
                tv_detail_more11.setText("性别:");
                tv_detail_more_info11.setText(freeManBean.getMemberTo().getGender());
                //电话
                tv_detail_more12.setText("电话:");
                tv_detail_more_info12.setText(freeManBean.getMemberTo().getPhone());
                //身份证号码
                tv_detail_more13.setText("身份证号码:");
                tv_detail_more_info13.setText(freeManBean.getMemberTo().getIdCard());

            }

        } else {
            tv_gender.setText("承办警组：   ");
            tv_detail_type1.setText("冻结日期： ");
            tv_type_thing_info.setText("财产类型： ");
            tv_detail_type2.setText("处理方式：   ");
            //冻结日期
            tv_phone.setText(listBean.getPoliceGroup());
            tv_type.setText(listBean.getFreezeEndTime());
            tv_time.setText(listBean.getType());
            tv_type2.setText(listBean.getHandleType());
        }
        // tv_info.setText("哈哈: ");
        View view = findViewById(R.id.vs_stub);
        view = findViewById(R.id.ac_detail_id_after_inflate);
    }


}
