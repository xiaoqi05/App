package com.ps.app.ui.activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.google.gson.Gson;
import com.ps.app.R;
import com.ps.app.base.MyApplication;
import com.ps.app.service.LocationService;
import com.ps.app.support.Bean.CommonResultBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 此demo用来展示如何结合定位SDK实现定位，并使用MyLocationOverlay绘制定位位置 同时展示如何使用自定义图标绘制并点击时弹出泡泡
 */
public class LocationActivity extends BaseActivity {
    private LocationService locationService;
    private TextView locationResult;
    private Button startButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initActionBar(-1, "呵呵");
        locationResult = (TextView) findViewById(R.id.textView1);
        locationResult.setMovementMethod(ScrollingMovementMethod.getInstance());
        startButton = (Button) findViewById(R.id.addfence);

    }

    @Override
    protected void onStart() {
        super.onStart();
        startLocation();
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

    private void startLocation() {
        locationService = ((MyApplication) getApplicationContext()).locationService;
        locationService.registerListener(mListener);
        //注册监听
      /*  int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {*/
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
       /* } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }*/
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (startButton.getText().toString().equals(getString(R.string.startlocation))) {
                    locationService.start();// 定位SDK
                    // start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
                    startButton.setText(getString(R.string.stoplocation));
                } else {
                    locationService.stop();
                    startButton.setText(getString(R.string.startlocation));
                }
            }
        });
    }

    /**
     * 显示请求字符串
     *
     * @param str
     */
    public void logMsg(String str) {
        try {
            if (locationResult != null)
                locationResult.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getTime());
                sb.append("\nerror code : ");
                sb.append(location.getLocType());
                sb.append("\nlatitude : ");
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");
                sb.append(location.getLongitude());
                sb.append("\nradius : ");
                sb.append(location.getRadius());
                sb.append("\nCountryCode : ");
                sb.append(location.getCountryCode());
                sb.append("\nCountry : ");
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");
                sb.append(location.getCityCode());
                sb.append("\ncity : ");
                sb.append(location.getCity());
                sb.append("\nDistrict : ");
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");
                sb.append(location.getStreet());
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\nDescribe: ");
                sb.append(location.getLocationDescribe());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());
                sb.append("\nPoi: ");
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 单位：米
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    sb.append("\noperationers : ");
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
                logMsg(sb.toString());
            }
        }

    };

    public void test_http(View v) {
        login();
    }

    public abstract class UserCallback extends Callback<CommonResultBean> {
        @Override
        public CommonResultBean parseNetworkResponse(Response response) throws IOException {
            String string = response.body().string();
            CommonResultBean tests = new Gson().fromJson(string, CommonResultBean.class);
            return tests;
        }
    }

    //URLEncoder.encode(stringToBeEncoded, "UTF-8")
    public void login() {
        /*
name：姓名

phone：手机号

pnum：警员编号

imei：手机串号

password：密码

longitude：经度

latitude：纬度

phoneType：手机型号
        * */
        Map<String, String> params = new HashMap<>();
        params.put("name", "aqc");
        params.put("phone", "15682070838");
        params.put("pnum", "110");
        params.put("imei", "15682070834");
        params.put("password", "111222333");
        params.put("longitude", "46.54654");
        params.put("latitude", "165.4654");
        params.put("phoneType", "1");
       /* Map<String, String> params = new HashMap<>();
        params.put("cityname", "123114564");
        params.put("key", "58ab4383f67a86c8549636c0fef7a7ee");
        params.put("pnum", "123114564");
        params.put("imei", "4654654");
        params.put("password", "4654654");
        params.put("longitude", "4654654");
        params.put("latitude", "4654654");
        params.put("phoneType", "4654654");*/
        OkHttpUtils
                .post()//
                .url("http://211.149.197.241:8080/posec/m/reg.action")//
              /*  .addParams("name", "123114564")
                .addParams("phone", "123114564")
                .addParams("pnum", "123114564")
                .addParams("imei", "4654654")
                .addParams("password", "4654654")
                .addParams("longitude", "4654654")
                .addParams("latitude", "4654654")
                .addParams("phoneType", "4654654")*/
                .params(params)
                .build()//
                .execute(new UserCallback() {

                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println("网络异常" + e);
                    }

                    @Override
                    public void onResponse(CommonResultBean response) {
                        System.out.println(response.getData());
                    }
                });
    }
}
