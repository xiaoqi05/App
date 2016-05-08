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
import com.rey.material.widget.Button;
import com.rey.material.widget.EditText;

import java.lang.ref.WeakReference;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener {

    private static final int VER_SUCCESS = 1;
    private static final int GET_VER_SUCCESS = 2;
    private static final int ERROR_GET_VER = 3;
    private Button bt_get_verification;
    private Button bt_next_step;
    private EditText et_phone;

    private MyHandler myHandler = new MyHandler(ResetPasswordActivity.this);
    private boolean isGetVerificaton =false;

    private static class MyHandler extends Handler {

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
                        activity.startActivity(new Intent(activity, SetPassWordActivity.class));
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_get_verification:
                final String newPhone = et_phone.getText().toString().trim();
              /*  if (!TextUtils.isEmpty(newPhone)){
                    getVerificationCode(newPhone);
                }else {
                    showSnackbar(v,"请填写手机号码");
                }

                if (newPhone.length() != 11) {
                    showShortToast("请输入正确的手机号码");
                    return;
                }*/
                new CountDownTimer(60000, 1000) {
                    // 第一个参数是总的倒计时时间
                    // 第二个参数是每隔多少时间(ms)调用一次onTick()方法
                    public void onTick(long millisUntilFinished) {
                        bt_get_verification.setText(millisUntilFinished / 1000 + "s后重新发送");
                        bt_get_verification.setEnabled(false);
                        if (TextUtils.isEmpty(newPhone)) {
                            showLongToast("请输入电话号码");
                            isGetVerificaton = false;
                            return;
                        } else {
                            if (!isGetVerificaton) {
                                getVerificationCode(et_phone.getText().toString().trim());
                                isGetVerificaton = true;
                            }
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
                Intent intent = new Intent(ResetPasswordActivity.this,SetPassWordActivity.class);
                startActivity(intent);
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
        SMSSDK.initSDK(this, Constant.APPKEY, Constant.APPSECRET, true);
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
                    System.out.println("返回支持发送验证码的cuowu");
                    myHandler.sendEmptyMessage(ERROR_GET_VER);
                    showLongToast("返回支持发送验证码的cuowu");
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

}
