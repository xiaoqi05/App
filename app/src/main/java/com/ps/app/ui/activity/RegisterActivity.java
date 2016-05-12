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


public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private static final int REQUEST_READ_CONTACTS = 0;

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    public static final int DISMISS_PROGRESSBAR = 1;

    private static final int VER_SUCCESS = 2;
    private static final int GET_VER_SUCCESS = 3;
    private static final int ERROR_GET_VER = 4;
    private static final int REGISTER_SUCCESS = 5;

    // UI references.
    private com.rey.material.widget.EditText mUserName;
    private View mProgressView;
    private View mLoginFormView;
    private Button bt_next_step;

    private Button bt_get_verification;
    private EditText et_register_name;
    private EditText et_register_police_id;
    private EditText et_register_phone;
    private EditText et_register_verification;
    private String userName;
    private String phone;
    private String policeId;

    private boolean isGetVerificaton = false;
    private MyHandler myHandler = new MyHandler(this);


    private static class MyHandler extends Handler {
        private WeakReference<RegisterActivity> activityWeakReference;

        public MyHandler(RegisterActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            RegisterActivity activity = activityWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case DISMISS_PROGRESSBAR:
                        break;
                    case VER_SUCCESS:
                        System.out.println("提交验证码成功");
                        activity.showShortToast("验证成功");
                        Intent intent = new Intent(activity, SetPassWordActivity.class);
                        intent.putExtra("userName", activity.userName);
                        intent.putExtra("phone", activity.phone);
                        intent.putExtra("policeId", activity.policeId);
                        activity.startActivityForResult(intent, 0);
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
        setContentView(R.layout.activity_register);
        initActionBar(-1, "警员注册");
        // Set up the login form.
        findView();
        initData();

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
                    // showLongToast("返回支持发送验证码的cuowu");
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    private void findView() {
        //// TODO: 2016-05-12 move unused object 
        mUserName = (com.rey.material.widget.EditText) findViewById(R.id.et_register_name);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        bt_next_step = (Button) findViewById(R.id.bt_next_step);
        assert bt_next_step != null;
        bt_next_step.setOnClickListener(this);
        bt_get_verification = (Button) findViewById(R.id.bt_get_verification);
        assert bt_get_verification != null;
        bt_get_verification.setOnClickListener(this);

        et_register_name = (EditText) findViewById(R.id.et_register_name);
        et_register_police_id = (EditText) findViewById(R.id.et_register_police_id);
        et_register_phone = (EditText) findViewById(R.id.et_register_phone);
        et_register_verification = (EditText) findViewById(R.id.et_register_verification);

    }




    private boolean isPasswordValid(String password) {
        return password.length() > 4;
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_next_step:
                userName = et_register_name.getText().toString().trim();
                policeId = et_register_police_id.getText().toString().trim();
                phone = et_register_phone.getText().toString().trim();
                String verificationCode = et_register_verification.getText().toString().trim();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(policeId) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(verificationCode)) {
                    showLongToast("请填写完整信息");
                } else {
                    //验证手机验证码
                    SMSSDK.submitVerificationCode("86", phone, verificationCode);

                }
                break;

            case R.id.bt_get_verification:
                final String newPhone = et_register_phone.getText().toString().trim();

                if (newPhone.length() != 11) {
                    showShortToast("请输入正确的手机号码");
                    return;
                }
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
                                getVerificationCode(et_register_phone.getText().toString().trim());
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
        }
    }



    @Override
    protected void onDestroy() {
        // 销毁回调监听接口
        SMSSDK.unregisterAllEventHandler();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == REGISTER_SUCCESS) {
                //注册成功时finish
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

