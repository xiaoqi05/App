package com.ps.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ps.app.R;
import com.ps.app.support.view.Code;

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
        

    }

    private void initData() {
        mImageView.setImageBitmap(Code.getInstance().createBitmap());
        System.out.println(Code.getInstance().getCode()+">>>>>>>>>>>>>");
    }

    private void findView() {
        findViewById(R.id.bt_login).setOnClickListener(this);
        findViewById(R.id.bt_register).setOnClickListener(this);
        mImageView= (ImageView) findViewById(R.id.bt_get_verification);
        assert mImageView != null;
        mImageView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
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

  
}
