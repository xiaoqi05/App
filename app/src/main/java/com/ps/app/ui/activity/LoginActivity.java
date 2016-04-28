package com.ps.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ps.app.R;
import com.ps.app.support.view.Code;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initActionBar(-1," ");
        findView();
        initData();
    }

    private void initData() {
        iv_image.setImageBitmap(Code.getInstance().createBitmap());
        System.out.println(Code.getInstance().getCode()+">>>>>>>>>>>>>");
    }

    private void findView() {
        iv_image = (ImageView) findViewById(R.id.iv_image);
        assert iv_image != null;
        iv_image.setOnClickListener(this);
        findViewById(R.id.bt_login).setOnClickListener(this);
        findViewById(R.id.bt_register).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_image:
                iv_image.setImageBitmap(Code.getInstance().createBitmap());
                System.out.println(Code.getInstance().getCode()+">>>>>>>>>>>>>");
                break;
            case R.id.bt_login:
                break;
            case R.id.bt_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            
        }
    }
}
