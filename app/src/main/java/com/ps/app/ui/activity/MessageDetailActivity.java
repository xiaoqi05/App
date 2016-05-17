package com.ps.app.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ps.app.R;
import com.ps.app.base.Constant;
import com.ps.app.support.Bean.CommonResultWithErrorBean;
import com.ps.app.support.Bean.PushMsgListBean.DataBean.ListBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class MessageDetailActivity extends BaseActivity {
    private static final int FREEMAN = 8;
    private static final String TAG = "MessageDetailActivity";
    private ViewStub viewStub;
    private TextView tv_show_all_info;

    private TextView tv_msg_detail_id_info;
    private TextView tv_msg_detail_content_info;
    private TextView tv_msg_detail_time_info;
    private TextView tv_msg_detail_type_info;

    private int source = 0;

    private boolean isMarked = false;
    private ListBean listBean;
    private HashMap<String, String> message_type_map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        initActionBar(-1, "消息详情");
        findView();
        initData();
        markMsgRead();
    }

    private void markMsgRead() {
        String cookie = getSharePreference("").getString("cookie", "");
        Log.i(TAG, listBean.getId() + ">>>>>>>>>>>>>>>");
        OkHttpUtils.get().url(Constant.MARK_MSG_READ_URL).addParams("ids", listBean.getId() + "").addHeader("cookie", cookie).build().execute(new UserMsgMarkCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.i(TAG, e.toString());
            }

            @Override
            public void onResponse(CommonResultWithErrorBean response) {
                if (response.getCode() == 2000) {
                    Log.i(TAG, response.getDesc());
                    showShortToast(response.getDesc());
                    isMarked = true;
                    setResult(RESULT_OK);
                }
                if (response.getCode() == 2201) {
                    Log.i(TAG, response.getData());
                }
            }
        });
    }

    public abstract class UserMsgMarkCallback extends Callback<CommonResultWithErrorBean> {
        @Override
        public CommonResultWithErrorBean parseNetworkResponse(Response response) throws IOException {
            String string = response.body().string();
            return new Gson().fromJson(string, CommonResultWithErrorBean.class);
        }
    }

    private void initData() {
        message_type_map.put("ASSET_MANAGEMENT_MSG", "资产到期提醒");
        message_type_map.put("OUT_OF_MSG", "保外人员离开限制区域提醒");
        message_type_map.put("ZONE_WARNING", "区域警告提醒");
        message_type_map.put("SIGN_IN_TIP", "签到提醒");
        message_type_map.put("OTHER", "其它消息");
        //消息详情
        listBean = (ListBean) getIntent().getSerializableExtra("listBean");
        tv_msg_detail_id_info.setText(listBean.getId() + "");
        tv_msg_detail_content_info.setText(listBean.getMessage().getContent());
        tv_msg_detail_time_info.setText(listBean.getCreateTime());
        tv_msg_detail_type_info.setText(message_type_map.get(listBean.getMessage().getType()));

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
            setResult(RESULT_OK);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //重写onBackPressed
    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}
