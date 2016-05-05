package com.ps.app.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.ps.app.R;
import com.ps.app.base.MyApplication;
import com.ps.app.service.LocationService;
import com.rey.material.app.DatePickerDialog;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.widget.Button;

import java.text.SimpleDateFormat;

public class RouterActivity extends BaseActivity implements View.OnClickListener {
    private Button bt_get_start_time;
    private Button bt_get_end_time;
    private Button bt_search;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationService mLocationService;

    private boolean isFirstLoc = true;
    private BDLocationListener mListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //showShortToast(location.getAddrStr());
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);
        initActionBar(-1, "活动轨迹");
        findView();
        initData();
    }

    private void initData() {
        mLocationService = ((MyApplication) getApplicationContext()).locationService;
        mLocationService.registerListener(mListener);
        mLocationService.start();
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
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

        mMapView = (MapView) findViewById(R.id.mv_baidu_map);
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
                        bt_get_start_time.setText(date);
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        showShortToast("Date is Cancelled");
                        super.onNegativeActionClicked(fragment);
                    }
                };
                builder.positiveAction("确认")
                        .negativeAction("取消");
                DialogFragment fragment = DialogFragment.newInstance(builder);
                fragment.show(getSupportFragmentManager(), null);
                break;

            case R.id.bt_get_end_date:
                builder = new DatePickerDialog.Builder(R.style.Material_App_Dialog_DatePicker_Light) {
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        DatePickerDialog dialog = (DatePickerDialog) fragment.getDialog();
                        String date = dialog.getFormattedDate(SimpleDateFormat.getDateInstance());
                        bt_get_end_time.setText(date);
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        showShortToast("Date is Cancelled");
                        super.onNegativeActionClicked(fragment);
                    }
                };
                builder.positiveAction("确认")
                        .negativeAction("取消");
                DialogFragment fragments = DialogFragment.newInstance(builder);
                fragments.show(getSupportFragmentManager(), null);
                break;

            case R.id.bt_date_search:

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

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mLocationService.unregisterListener(mListener);
        mLocationService.stop();
    }

}
