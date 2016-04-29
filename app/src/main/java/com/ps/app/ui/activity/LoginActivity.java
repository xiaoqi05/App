package com.ps.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ps.app.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initActionBar(-1," ");
        findView();
        initData();

    }

    private void initData() {

    }

    private void findView() {
        findViewById(R.id.bt_login).setOnClickListener(this);
        findViewById(R.id.bt_register).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_login:
                break;
            case R.id.bt_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            
        }
    }
}
