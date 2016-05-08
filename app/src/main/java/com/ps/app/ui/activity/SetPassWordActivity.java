package com.ps.app.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.ps.app.R;
import com.ps.app.base.MyApplication;
import com.ps.app.service.LocationService;
import com.ps.app.support.utils.MD5Util;
import com.rey.material.widget.EditText;


public class SetPassWordActivity extends BaseActivity {
    private String IMEI;
    private String phone_type;
    private LocationService mLocationService;
    private EditText et_set_password;
    private EditText et_confirm_password;
    private boolean isGetLocationInfo = false;
    private double longitude;
    private double latitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pass_word);
        initActionBar(-1, "设置密码");
        findView();
        getPhoneInfo();
        getLocation();

    }

    private void findView() {
        et_set_password = (EditText) findViewById(R.id.et_set_password);
        et_confirm_password = (EditText) findViewById(R.id.et_confirm_password);

    }

    private void getLocation() {
        mLocationService = ((MyApplication) getApplicationContext()).locationService;
        mLocationService.setLocationOption(mLocationService.getDefaultLocationClientOption());
        mLocationService.registerListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                showShortToast(bdLocation.getAddrStr());
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

    public void finish_login(View view) {
        String paw = et_set_password.getText().toString().trim();
        String confirm_paw = et_confirm_password.getText().toString().trim();
        if (!TextUtils.isEmpty(paw) && !TextUtils.isEmpty(confirm_paw)) {
            if (paw.equals(confirm_paw)) {
                if (isGetLocationInfo) {
                    //// TODO: 2016-05-08 完成注册 
                    showSnackbar(view, "完成注册"+MD5Util.md5(paw));
                    System.out.println(MD5Util.md5(paw));
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
}

