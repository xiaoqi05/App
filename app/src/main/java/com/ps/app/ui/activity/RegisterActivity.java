package com.ps.app.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.ps.app.R;
import com.ps.app.base.Constant;
import com.rey.material.widget.Button;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private static final int REQUEST_READ_CONTACTS = 0;

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    private UserLoginTask mAuthTask = null;

    // UI references.
    private com.rey.material.widget.EditText mUserName;
    private com.rey.material.widget.EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button bt_next_step;
    private Button bt_get_verification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initActionBar(-1, "警员注册");
        // Set up the login form.
        findView();
        initData();
        
    }

    private void getVerificationCode() {
        SMSSDK.getVerificationCode(Constant.DEFAULT_COUNTRY_ID,"15682070830");
    }

    private void initData() {
        SMSSDK.initSDK(this, Constant.APPKEY,Constant.APPSECRET,true);
        EventHandler eh=new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        System.out.println("提交验证码成功");
                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        System.out.println("获取验证码成功");
                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
                        System.out.println("返回支持发送验证码的国家列表");

                    }
                }else{
                    System.out.println("返回支持发送验证码的cuowu");
                    ((Throwable)data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    private void findView() {
        mUserName = (com.rey.material.widget.EditText) findViewById(R.id.et_register_name);
        mPasswordView = (com.rey.material.widget.EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    //attemptLogin();
                    return true;
                }
                return false;
            }
        });


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        bt_next_step = (Button) findViewById(R.id.bt_next_step);
        assert bt_next_step != null;
        bt_next_step.setOnClickListener(this);
        bt_get_verification = (Button) findViewById(R.id.bt_get_verification);
        assert bt_get_verification != null;
        bt_get_verification.setOnClickListener(this);

    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mUserName.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mUserName.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mUserName.setError(getString(R.string.error_field_required));
            focusView = mUserName;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
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

                startActivity(new Intent(RegisterActivity.this, SetPassWordActivity.class));
                break;


            case R.id.bt_get_verification:

                new CountDownTimer(60000, 1000) {
                    // 第一个参数是总的倒计时时间
                    // 第二个参数是每隔多少时间(ms)调用一次onTick()方法
                    public void onTick(long millisUntilFinished) {
                        bt_get_verification.setText(millisUntilFinished / 1000 + "s后重新发送");
                        bt_get_verification.setEnabled(false);
                        getVerificationCode();

                    }

                    public void onFinish() {
                        bt_get_verification.setText("重新获取验证码");
                        bt_get_verification.setEnabled(true);
                    }
                }.start();
        }
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }
}

