package com.ps.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.ps.app.R;
import com.ps.app.base.Constant;
import com.ps.app.support.utils.CheckPhone;
import com.rey.material.widget.Button;
import com.rey.material.widget.EditText;

import java.lang.ref.WeakReference;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener {

    private static final int VER_SUCCESS = 1;
    private static final int GET_VER_SUCCESS = 2;
    private static final int ERROR_GET_VER = 3;
    private static final int REGISTER_SUCCESS = 5;
    private Button bt_get_verification;
    private Button bt_next_step;
    private EditText et_phone;
    private EditText et_reset_verification;
    private String phone;

    private MyHandler myHandler = new MyHandler(ResetPasswordActivity.this);
    private boolean isGetVerificaton = false;

    private static class MyHandler extends Handler {

        private static final int RESETPASSWORD = 1;
        private WeakReference<ResetPasswordActivity> activityWeakReference;

        public MyHandler(ResetPasswordActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            ResetPasswordActivity activity = activityWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case VER_SUCCESS:
                        System.out.println("提交验证码成功");
                        activity.showShortToast("验证成功");
                        Intent intent = new Intent(activity, SetPassWordActivity.class);
                        intent.putExtra("phone", activity.phone);
                        intent.putExtra("source", RESETPASSWORD);
                        activity.startActivityForResult(intent, 1);
                        break;
                    case GET_VER_SUCCESS:
                        activity.showShortToast("获取验证码成功");
                        System.out.println("获取验证码成功");
                        break;
                    case ERROR_GET_VER:
                        activity.showShortToast("获取验证码失败，请重试");
                        System.out.println("获取验证码失败，请重试");
                        break;

                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initActionBar(-1, "验证手机");
        findView();
        initData();
    }

    private void findView() {
        bt_next_step = (Button) findViewById(R.id.bt_next_step);
        assert bt_next_step != null;
        bt_next_step.setOnClickListener(this);
        bt_get_verification = (Button) findViewById(R.id.bt_get_verification);
        assert bt_get_verification != null;
        bt_get_verification.setOnClickListener(this);
        et_phone = (EditText) findViewById(R.id.et_reset_phone);
        et_reset_verification = (EditText) findViewById(R.id.et_reset_verification);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.bt_get_verification:
                if (getIntent().getIntExtra("source", 0) == 8) {
                    et_phone.setEnabled(true);
                    phone = et_phone.getText().toString().trim();
                }
                if (TextUtils.isEmpty(phone)) {
                    showSnackbar(v, "请输入电话号码");
                    // et_phone.setEnabled(true);
                    //phone = et_phone.getText().toString().trim();
                    return;
                }
                if (!CheckPhone.isMobileNO(phone)) {
                    showSnackbar(v, "请输入正确电话号码");
                    return;
                }
                new CountDownTimer(60000, 1000) {
                    // 第一个参数是总的倒计时时间
                    // 第二个参数是每隔多少时间(ms)调用一次onTick()方法
                    public void onTick(long millisUntilFinished) {
                        bt_get_verification.setText(millisUntilFinished / 1000 + "s后重新发送");
                        bt_get_verification.setEnabled(false);
                        if (TextUtils.isEmpty(phone)) {
                            showSnackbar(v, "请输入电话号码");
                            isGetVerificaton = false;
                            return;
                        }
                        if (!isGetVerificaton) {
                            getVerificationCode(et_phone.getText().toString().trim());
                            isGetVerificaton = true;
                        }

                    }

                    public void onFinish() {
                        bt_get_verification.setText("重新获取验证码");
                        bt_get_verification.setEnabled(true);
                        isGetVerificaton = false;
                    }
                }.start();
                break;

            case R.id.bt_next_step:
                // Intent intent = new Intent(ResetPasswordActivity.this,SetPassWordActivity.class);
                // startActivity(intent);
                if (getIntent().getIntExtra("source", 0) == 8) {
                    et_phone.setEnabled(false);
                    phone = et_phone.getText().toString().trim();
                }
                String verificationCode = et_reset_verification.getText().toString().trim();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(verificationCode)) {
                    showSnackbar(v, "请填写完整信息");
                } else {
                    //验证手机验证码
                    SMSSDK.submitVerificationCode("86", phone, verificationCode);
                }
                break;

            default:
                break;

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getVerificationCode(String phone) {
        SMSSDK.getVerificationCode(Constant.DEFAULT_COUNTRY_ID, phone);
    }

    private void initData() {
        //=8来自与mainActivity
        if (getIntent().getIntExtra("source", 0) == 8) {
            et_phone.setEnabled(true);
            phone = et_phone.getText().toString().trim();
        } else {
            phone = getSharePreference("").getString("phone", "");
            et_phone.setEnabled(false);
            et_phone.setText(phone);
        }

        if (!TextUtils.isEmpty(phone)) {
            showShortToast("请输入手机号码");
            return;
        }
        SMSSDK.initSDK(this, Constant.APPKEY, Constant.APPSECRET, false);
        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        myHandler.sendEmptyMessage(VER_SUCCESS);
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        myHandler.sendEmptyMessage(GET_VER_SUCCESS);
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                        System.out.println("返回支持发送验证码的国家列表");

                    }
                } else {
                    myHandler.sendEmptyMessage(ERROR_GET_VER);
                    showLongToast("返回支持发送验证码的cuowu");
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    @Override
    protected void onDestroy() {
        SMSSDK.unregisterAllEventHandler();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == REGISTER_SUCCESS) {
                //注册成功时finish
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
