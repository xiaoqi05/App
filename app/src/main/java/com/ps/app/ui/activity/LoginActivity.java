package com.ps.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.ps.app.R;
import com.ps.app.base.Constant;
import com.ps.app.support.Bean.User;
import com.ps.app.support.view.Code;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private boolean isReady;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        initData();


    }

    private void initData() {
        mImageView.setImageBitmap(Code.getInstance().createBitmap());
        System.out.println(Code.getInstance().getCode() + ">>>>>>>>>>>>>");
    }

    private void findView() {
        findViewById(R.id.bt_login).setOnClickListener(this);
        findViewById(R.id.bt_register).setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.bt_get_verification);
        assert mImageView != null;
        mImageView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                login();
                break;
            case R.id.bt_get_verification:
                mImageView.setImageBitmap(Code.getInstance().createBitmap());
                break;
            case R.id.bt_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
 
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    public void resetPassword(View v) {
        Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
    }

    public abstract class UserCallback extends Callback<User> {
        @Override
        public User parseNetworkResponse(Response response) throws IOException {
            String string = response.body().string();
            User user = new Gson().fromJson(string, User.class);
            return user;
        }
    }

    public void login() {
        OkHttpUtils
                .post()//
                .url(Constant.LOGIN_URL)//
                .addParams("name", "hyman")//
                .addParams("phone", "123")//
                .addParams("pnum", "123")//
                .addParams("imei", "123")//
                .addParams("password", "123")//
                .addParams("longitude", "123")//
                .addParams("latitude", "123")//
                .addParams("phoneType", "123")//
                .build()//
                .execute(new UserCallback() {

                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println("网络异常"+e);
                    }

                    @Override
                    public void onResponse(User response) {
                        System.out.println(response.getName());
                    }
                });
    }


}
