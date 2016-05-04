package com.ps.app.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.ps.app.R;
import com.rey.material.app.DatePickerDialog;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.widget.Button;

import java.text.SimpleDateFormat;

public class RouterActivity extends BaseActivity implements View.OnClickListener {
    private Button bt_get_start_time;
    private Button bt_get_end_time;
    private Button bt_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);
        initActionBar(-1, "活动轨迹");
        findView();
    }

    private void findView() {
        bt_get_start_time = (Button) findViewById(R.id.bt_get_start_date);
        assert bt_get_start_time != null;
        bt_get_start_time.setOnClickListener(this);
        
        bt_get_end_time = (Button) findViewById(R.id.bt_get_end_date);
        assert bt_get_end_time != null;
        bt_get_end_time.setOnClickListener(this);
        
        bt_search = (Button) findViewById(R.id.bt_date_search);
        assert bt_search != null;
        bt_search.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        Dialog.Builder builder = null;
        switch (v.getId()) {
            case R.id.bt_get_start_date:
                builder = new DatePickerDialog.Builder(R.style.Material_App_Dialog_DatePicker_Light) {
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        DatePickerDialog dialog = (DatePickerDialog) fragment.getDialog();
                        String date = dialog.getFormattedDate(SimpleDateFormat.getDateInstance());
                        showShortToast("Date is " + date);
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        showShortToast("Date is Cancelled");
                        super.onNegativeActionClicked(fragment);
                    }
                };

                builder.positiveAction("OK")
                        .negativeAction("CANCEL");
                DialogFragment fragment = DialogFragment.newInstance(builder);
                fragment.show(getSupportFragmentManager(), null);
                break;

            case R.id.bt_get_end_date:


                break;

            case R.id.bt_date_search:

                break;
        }


    }
}
