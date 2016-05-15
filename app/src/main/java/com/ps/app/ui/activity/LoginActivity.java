package com.ps.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.ps.app.R;
import com.ps.app.base.Constant;
import com.ps.app.support.Bean.CommonResultBean;
import com.ps.app.support.utils.CheckPhone;
import com.ps.app.support.utils.MD5Util;
import com.ps.app.support.view.Code;
import com.rey.material.widget.EditText;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private boolean isReady;
    private ImageView mImageView;
    private EditText et_phone;
    private EditText et_ver_code;
    private EditText et_paw;
    private String phone;
    private String paw;
    private String verification_code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        initData();
    }

    private void initData() {
        mImageView.setImageBitmap(Code.getInstance().createBitmap());
        if (!TextUtils.isEmpty(getSharePreference("").getString("phone", ""))) {
            et_phone.setText(getSharePreference("").getString("phone", ""));
        }
    }

    private void findView() {
        findViewById(R.id.bt_login).setOnClickListener(this);
        findViewById(R.id.bt_register).setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.bt_get_verification);
        assert mImageView != null;
        mImageView.setOnClickListener(this);

        et_phone = (EditText) findViewById(R.id.et_login_phone_num);
        et_paw = (EditText) findViewById(R.id.et_login_paw);
        et_ver_code = (EditText) findViewById(R.id.et_ver_code);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                phone = et_phone.getText().toString().trim();
                paw = et_paw.getText().toString().trim();
                verification_code = et_ver_code.getText().toString().trim();
                if (!preLogin(v)) return;
                showNormalPrograssDailogBar(LoginActivity.this, "正在登录");
                login(phone, paw);
                break;
            case R.id.bt_get_verification:
                mImageView.setImageBitmap(Code.getInstance().createBitmap());
                break;
            case R.id.bt_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;

        }
    }

    private boolean preLogin(View v) {

        if (!isNetworkAvailable()) {
            showSnackbar(v, "请连接网络，并重试");
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            showSnackbar(v, "请填写手机号码");
            return false;
        }
        if (!TextUtils.isEmpty(phone)) {
            if (!CheckPhone.isMobileNO(phone)) {
                showSnackbar(v, "请输入正确手机号码");
                return false;
            }
        }
        if (TextUtils.isEmpty(paw)) {
            showSnackbar(v, "请填写密码");
            return false;
        }
        if (TextUtils.isEmpty(verification_code)) {
            showSnackbar(v, "请输入图片验证码");
            return false;
        }
        if (!verification_code.equalsIgnoreCase(Code.getInstance().getCode())) {
            showSnackbar(v, "图片验证码错误，请重新输入");
            return false;
        }


        return true;
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    public void resetPassword(View v) {
        Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
    }


    public void login(final String phone, String paw) {

        OkHttpUtils
                .post()//
                .url(Constant.LOGIN_URL)//
                .addParams("phone", phone)//
                .addParams("password", MD5Util.md5(paw))//MD5Util.md5(paw)
                .build()//
                .execute(new UserLoginCallback() {

                    @Override
                    public void onError(Call call, Exception e) {
                        dismissNormalPrograssDailogBar();
                        System.out.println("异常" + e
                        );
                        showShortToast("你的密码错误，请重新登录");
                    }

                    @Override
                    public void onResponse(CommonResultBean response) {
                        dismissNormalPrograssDailogBar();
                        if (response.getCode() == 2000) {
                            showLongToast("登录" + response.getDesc());
                            getSharePreference("").edit().putBoolean("isLogin", true).apply();
                            //退出的时候上传sid
                            getSharePreference("").edit().putString("sid", response.getData()).apply();
                            //cookie 持久化管理
                            List<Cookie> cookies = OkHttpUtils.getInstance().getCookieStore().getCookies();
                            getSharePreference("").edit().putString("cookie", String.valueOf(cookies.get(0))).apply();
                            getSharePreference("").edit().putString("phone", phone).apply();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        if (response.getCode() == 2202) {
                            showLongToast(response.getData());
                        }
                    }
                });
    }


    public abstract class UserLoginCallback extends Callback<CommonResultBean> {
        @Override
        public CommonResultBean parseNetworkResponse(Response response) throws IOException {

            String string = response.body().string();
            CommonResultBean commonResultBean = new Gson().fromJson(string, CommonResultBean.class);
            Log.i(TAG, commonResultBean.getDesc());
            return commonResultBean;
        }
    }


}
