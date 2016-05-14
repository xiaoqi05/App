package com.ps.app.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.ps.app.R;

public class OperateInstructionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate_instruction);
        initActionBar(-1,"操作说明");
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
    
}
