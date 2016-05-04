package com.ps.app.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.ps.app.R;

public class DetailActivity extends BaseActivity {
    private ViewStub viewStub;
    private TextView tv_show_all_info;
    private boolean isShowAllInfo=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initActionBar(-1, "详情");
        findView();

    }

    private void findView() {
        viewStub = (ViewStub) findViewById(R.id.vs_stub);
        tv_show_all_info = (TextView) findViewById(R.id.tv_show_all_info);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void showAllInfo(View v){
        tv_show_all_info.setVisibility(View.GONE);
        View vs = viewStub.inflate();
        TextView tv_info = (TextView) vs.findViewById(R.id.tv_show_other_info);
       // tv_info.setText("哈哈: ");
        View view = findViewById(R.id.vs_stub);
        view = findViewById(R.id.ac_detail_id_after_inflate);
    }
}
