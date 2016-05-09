package com.ps.app.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.google.gson.Gson;
import com.ps.app.R;
import com.ps.app.base.Constant;
import com.ps.app.base.MyApplication;
import com.ps.app.service.LocationService;
import com.ps.app.support.Bean.CommonResultBean;
import com.ps.app.support.utils.MD5Util;
import com.rey.material.widget.EditText;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


public class SetPassWordActivity extends BaseActivity {
    private static final String TAG = "SetPassWordActivity";
    private static final int REGISTER_SUCCESS = 5;
    private String IMEI;
    private String phone_type;
    private LocationService mLocationService;
    private EditText et_set_password;
    private EditText et_confirm_password;
    private boolean isGetLocationInfo = false;
    private double longitude;
    private double latitude;
    private String userName;
    private String phone;
    private String policeId;
    private ProgressBar mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pass_word);
        initActionBar(-1, "设置密码");
        findView();
        getPhoneInfo();
        initData();
        getLocation();

    }

    private void initData() {
        userName = getIntent().getStringExtra("userName");
        phone = getIntent().getStringExtra("phone");
        policeId = getIntent().getStringExtra("policeId");
    }

    private void findView() {
        et_set_password = (EditText) findViewById(R.id.et_set_password);
        et_confirm_password = (EditText) findViewById(R.id.et_confirm_password);
        mProgressView = (ProgressBar) findViewById(R.id.login_progress);

    }

    public void finish_login(final View view) {
        showProgress(mProgressView,true);
        if (!isNetworkAvailable()) {
            showSnackbar(view, "请连接网络,并重试");
            return;
        }
        String paw = et_set_password.getText().toString().trim();
        String confirm_paw = et_confirm_password.getText().toString().trim();
        if (!TextUtils.isEmpty(paw) && !TextUtils.isEmpty(confirm_paw)) {
            if (paw.equals(confirm_paw)) {
                if (isGetLocationInfo) {
                    System.out.println(MD5Util.md5(paw));
                    Map<String, String> params = new HashMap<>();
                    params.put("name", userName);
                    params.put("phone", phone);
                    params.put("pnum", policeId);
                    params.put("imei", IMEI);
                    params.put("password", MD5Util.md5(paw));
                    params.put("longitude", String.valueOf(longitude));
                    params.put("latitude", String.valueOf(latitude));
                    params.put("phoneType", phone_type);
                    OkHttpUtils.post().url(Constant.POLICE_REGISTER_URL).params(params).build().execute(new UserRegCallback() {
                        @Override
                        public void onError(Call call, Exception e) {
                            Log.i(TAG, e.getMessage());
                        }

                        @Override
                        public void onResponse(CommonResultBean response) {
                            if (response.getCode() == 2000 && response.getDesc().equals("成功")) {
                                showLongToast("注册成功,请重新登录");
                                 /*  
                                Intent intent = new Intent(SetPassWordActivity.this, LoginActivity.class);
                                intent.putExtra("name", userName);
                                intent.putExtra("phone", phone);
                                intent.putExtra("policeId", policeId);
                                startActivity(intent);
                                */
                                //注册成功
                                showProgress(mProgressView,false);
                                //getSharePreference("").edit().putBoolean("isLogin", true).apply();
                                setResult(REGISTER_SUCCESS);
                                finish();
                            }
                            if (response.getCode() == 2202) {
                                showSnackbar(view, response.getData());
                            }
                        }
                    });
                } else {
                    showSnackbar(view, "请开启GPS，获取位置信息");
                }
            } else {
                showSnackbar(view, "两次密码不同,请重新输入");
            }
        } else {
            showSnackbar(view, "请输入密码");
        }
    }

    private void getLocation() {
        mLocationService = ((MyApplication) getApplicationContext()).locationService;
        mLocationService.setLocationOption(mLocationService.getDefaultLocationClientOption());
        mLocationService.registerListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                if (!TextUtils.isEmpty(bdLocation.getAddrStr())) {
                    isGetLocationInfo = true;
                    longitude = bdLocation.getLongitude();
                    latitude = bdLocation.getLatitude();
                }
            }
        });
        mLocationService.start();
    }

    private void getPhoneInfo() {
        IMEI = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).getDeviceId();
        phone_type = Build.MODEL;
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

    @Override
    protected void onStop() {
        mLocationService.stop();
        super.onStop();
    }

    public abstract class UserRegCallback extends Callback<CommonResultBean> {
        @Override
        public CommonResultBean parseNetworkResponse(Response response) throws IOException {
            String string = response.body().string();
            CommonResultBean commonResultBean = new Gson().fromJson(string, CommonResultBean.class);
            Log.i(TAG, commonResultBean.getDesc());
            return commonResultBean;
        }
    }


}

