package com.ps.app.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.ps.app.R;
import com.ps.app.base.Constant;
import com.ps.app.base.MyApplication;
import com.ps.app.service.LocationService;
import com.ps.app.support.Bean.FreeManLocationRoad;
import com.ps.app.support.utils.DateFormat;
import com.rey.material.app.DatePickerDialog;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.widget.Button;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class RouterActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "RouterActivity";
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
    private List<LatLng> points = new ArrayList<>();
    private String mid;
    private String start_time;
    private String end_time;


    private boolean isFirstLoc = true;
    private BDLocationListener mListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            System.out.println("经纬度" + location.getLongitude() + location.getLatitude());
            //points.add(new LatLng(location.getLatitude() + j, location.getLongitude() + i));
            i = i + 0.001;
            j += 0.002;
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
        mid = getIntent().getStringExtra("mid");
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
                        String formattedDate = dialog.getFormattedDate(SimpleDateFormat.getDateInstance());
                        //start_time = dialog.getFormattedDate(SimpleDateFormat.getDateInstance());
                        start_time = DateFormat.dateSimpleFormat(dialog.getCalendar().getTimeInMillis());
                        Log.i(TAG, start_time + "start_time");
                        bt_get_start_time.setText(formattedDate);
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
                        bt_get_end_time.setText(dialog.getFormattedDate(SimpleDateFormat.getDateInstance()));
                        end_time = DateFormat.dateSimpleFormat(dialog.getCalendar().getTimeInMillis());
                        if (end_time.equals(start_time)) {
                            showShortToast("开始时间与结束时间不能相同，请重新选择");
                            return;
                        }
                        Log.i(TAG, end_time + "endtime");
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
                // addCustomElementsDemo();
                if (prepareForSearch()) return;
                StartSearch(mid, start_time, end_time);
                break;
        }


    }

    private boolean prepareForSearch() {
        if (!isNetworkAvailable()) {
            showShortToast("网络不可用");
            return true;
        }
        if (TextUtils.isEmpty(start_time)) {
            showShortToast("请选择开始时间");
            return true;
        }
        if (TextUtils.isEmpty(end_time)) {
            showShortToast("请选择结束时间");
            return true;
        }
        return false;
    }


    private void StartSearch(String mid, String start_date, String end_date) {
        String cookie = getSharePreference("").getString("cookie", "");
        Log.i(TAG, "start_date:" + start_date + "end_date" + end_date);
        Map<String, String> params = new HashMap<>();
        params.put("mid", mid);
        params.put("startTime", start_date);
        params.put("endTime", end_date);
        //addParams("mid",mid).addParams("start_time",start_time).addParams("endTime",end_date
        OkHttpUtils.post().params(params).addHeader("cookie", cookie)
                .url(Constant.FREE_MAN_LOCATION_SEARCH_URL).build().connTimeOut(10000).execute(new UserLocationRoadCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.i(TAG, e.toString());
                showShortToast("无历史轨迹");
            }

            @Override
            public void onResponse(FreeManLocationRoad response) {
                if (response.getCode() == 2000) {
                    //清除数据
                    points.clear();
                    Log.i(TAG, "getLatitude" + response.getData().get(0).getLatitude());
                    for (int i = 0; i < response.getData().size(); i++) {
                        double latitude = Double.parseDouble(response.getData().get(i).getLatitude());
                        double longitude = Double.parseDouble(response.getData().get(i).getLongitude());
                        points.add(new LatLng(latitude, longitude));
                    }

                }
                LatLng ll = points.get(0);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(16.0f);
                if (points.size() < 2) {
                    showShortToast("无历史轨迹");
                    return;
                }
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                clearClick();
                addCustomElementsDemo();
                if (response.getCode() == 2201) {
                    showShortToast("2201");
                    Log.i(TAG, "getLatitude" + response.getDesc());
                }
            }
        });

    }

    public abstract class UserLocationRoadCallback extends Callback<FreeManLocationRoad> {
        @Override
        public FreeManLocationRoad parseNetworkResponse(Response response) throws IOException {
            String string = response.body().string();
            Log.i(TAG, string);
           /* try {
                JSONObject jsonObject = new JSONObject(string);
                int code = (int) jsonObject.opt("code");
                if (code==2206){
                    
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
            return new Gson().fromJson(string, FreeManLocationRoad.class);
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
     /*   LatLng p11 = new LatLng(39.965, 116.444);
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
        mColorfulPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline1);*/

    }

    public void clearClick() {
        // 清除所有图层
        mMapView.getMap().clear();
    }

}
