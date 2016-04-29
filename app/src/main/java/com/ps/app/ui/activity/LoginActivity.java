package com.ps.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.ps.app.R;
import com.ps.app.base.Constant;
import com.ps.app.support.view.Code;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private boolean isReady;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initActionBar(-1, " ");
        findView();
        initData();
        //短信验证
        //initSDK();

    }

    private void initData() {
        mImageView.setImageBitmap(Code.getInstance().createBitmap());
        System.out.println(Code.getInstance().getCode()+">>>>>>>>>>>>>");
    }

    private void findView() {
        findViewById(R.id.bt_login).setOnClickListener(this);
        findViewById(R.id.bt_register).setOnClickListener(this);
        mImageView= (ImageView) findViewById(R.id.iv_image);
        assert mImageView != null;
        mImageView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                break;
            case R.id.iv_image:
                mImageView.setImageBitmap(Code.getInstance().createBitmap());
                break;
            case R.id.bt_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;

        }
    }

    private void initSDK() {
        // 初始化短信SDK
        SMSSDK.initSDK(this, Constant.APPKEY, Constant.APPSECRET, true);
        EventHandler eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
            }
        };
        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
        isReady = true;
    }

    @Override
    protected void onDestroy() {
        if (isReady) {
            // 销毁回调监听接口
            SMSSDK.unregisterAllEventHandler();
        }
        super.onDestroy();
    }

   /* *//**
     * 是否请求发送验证码，对话框
     *//*
    public void showDialog(final String phone, final String code) {
        if (resId > 0) {
            final String phoneNum = "+" + code + " " + splitPhoneNum(phone);
            final Dialog dialog = new Dialog(getContext(), resId);
            ((TextView) dialog.findViewById(Res.id.tv_phone)).setText(phoneNum);
            TextView tv = (TextView) dialog.findViewById(Res.id.tv_dialog_hint);

            ((Button) dialog.findViewById(Res.id.btn_dialog_ok)).setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            // 跳转到验证码页面
                            dialog.dismiss();

                            if (pd != null && pd.isShowing()) {
                                pd.dismiss();
                            }
                            pd = CommonDialog.ProgressDialog(activity);
                            if (pd != null) {
                                pd.show();
                            }
                            Log.e("verification phone ==>>", phone);
                            SMSSDK.getVerificationCode(code, phone.trim(), osmHandler);
                        }
                    });


            ((Button) dialog.findViewById(Res.id.btn_dialog_cancel)).setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
        }
    }*/
}
