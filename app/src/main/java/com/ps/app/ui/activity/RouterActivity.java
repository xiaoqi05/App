package com.ps.app.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.ps.app.R;
import com.ps.app.base.MyApplication;
import com.ps.app.service.LocationService;
import com.rey.material.app.DatePickerDialog;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RouterActivity extends BaseActivity implements View.OnClickListener {
    private Button bt_get_start_time;
    private Button bt_get_end_time;
    private Button bt_search;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationService mLocationService;
    private Polyline mPolyline;
    private Polyline mColorfulPolyline;
    private Polyline mTexturePolyline;
    double i = 0;
    double j = 0;
    BitmapDescriptor mRedTexture = BitmapDescriptorFactory.fromResource(R.drawable.icon_road_red_arrow);
    BitmapDescriptor mBlueTexture = BitmapDescriptorFactory.fromResource(R.drawable.icon_road_blue_arrow);
    BitmapDescriptor mGreenTexture = BitmapDescriptorFactory.fromResource(R.drawable.icon_road_green_arrow);
    private List<LatLng> points = new ArrayList<>();

    private boolean isFirstLoc = true;
    private BDLocationListener mListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            System.out.println("经纬度" + location.getLongitude() + location.getLatitude());
            points.add(new LatLng(location.getLatitude()+j, location.getLongitude()+i));
            i=i+0.001;
            j+=0.002;
            showShortToast(location.getAddrStr()+i);
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

      //  addCustomElementsDemo();
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
                        //showShortToast("Date is Cancelled");
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
                        //showShortToast("Date is Cancelled");
                        super.onNegativeActionClicked(fragment);
                    }
                };
                builder.positiveAction("确认")
                        .negativeAction("取消");
                DialogFragment fragments = DialogFragment.newInstance(builder);
                fragments.show(getSupportFragmentManager(), null);
                break;

            case R.id.bt_date_search:
                clearClick();
                addCustomElementsDemo();
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
        mRedTexture.recycle();
        mBlueTexture.recycle();
        mGreenTexture.recycle();
    }

    /**
     * 添加点、线、多边形、圆、文字
     */
    public void addCustomElementsDemo() {
        // 添加普通折线绘制
        /*LatLng p1 = new LatLng(39.97923, 116.357428);
        LatLng p2 = new LatLng(39.94923, 116.397428);
        LatLng p3 = new LatLng(39.97923, 116.437428);
        List<LatLng> points = new ArrayList<LatLng>();
        points.add(p1);
        points.add(p2);
        points.add(p3);*/
        OverlayOptions ooPolyline = new PolylineOptions().width(10)
                .color(0xAAFF0000).points(points);
        mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
        //设置虚线
        mPolyline.setDottedLine(true);

        // 添加多颜色分段的折线绘制
        LatLng p11 = new LatLng(39.965, 116.444);
        LatLng p21 = new LatLng(39.925, 116.494);
        LatLng p31 = new LatLng(39.955, 116.534);
        LatLng p41 = new LatLng(39.905, 116.594);
        LatLng p51 = new LatLng(39.965, 116.644);
        List<LatLng> points1 = new ArrayList<LatLng>();
        points1.add(p11);
        points1.add(p21);
        points1.add(p31);
        points1.add(p41);
        points1.add(p51);
        List<Integer> colorValue = new ArrayList<Integer>();
        colorValue.add(0xAAFF0000);
        colorValue.add(0xAA00FF00);
        colorValue.add(0xAA0000FF);
        OverlayOptions ooPolyline1 = new PolylineOptions().width(10)
                .color(0xAAFF0000).points(points1).colorsValues(colorValue);
        mColorfulPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline1);

        // 添加多纹理分段的折线绘制
        LatLng p111 = new LatLng(39.865, 116.444);
        LatLng p211 = new LatLng(39.825, 116.494);
        LatLng p311 = new LatLng(39.855, 116.534);
        LatLng p411 = new LatLng(39.805, 116.594);
        List<LatLng> points11 = new ArrayList<LatLng>();
        points11.add(p111);
        points11.add(p211);
        points11.add(p311);
        points11.add(p411);
        List<BitmapDescriptor> textureList = new ArrayList<BitmapDescriptor>();
        textureList.add(mRedTexture);
        textureList.add(mBlueTexture);
        textureList.add(mGreenTexture);
        List<Integer> textureIndexs = new ArrayList<Integer>();
        textureIndexs.add(0);
        textureIndexs.add(1);
        textureIndexs.add(2);
        OverlayOptions ooPolyline11 = new PolylineOptions().width(20)
                .points(points11).dottedLine(true).customTextureList(textureList).textureIndex(textureIndexs);
        mTexturePolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline11);

    }

    public void clearClick() {
        // 清除所有图层
        mMapView.getMap().clear();
    }

}
