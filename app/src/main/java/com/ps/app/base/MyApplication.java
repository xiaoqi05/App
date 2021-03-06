package com.ps.app.base;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.ps.app.service.LocationService;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import im.fir.sdk.FIR;


public class MyApplication extends Application {

    public LocationService locationService;
    public Vibrator mVibrator;
    @Override
    public void onCreate() {
        super.onCreate();
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
        OkHttpUtils.getInstance().setConnectTimeout(10000, TimeUnit.MILLISECONDS);
        FIR.init(this);
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

    }
    private void setupTheme() {
      
    }

    public void setupCrash() {
      
    }

    // 刷新定时发布任务
    public static void refreshPublishAlarm() {
        
    }

    public static void removeAllPublishAlarm() {
       
    }


}
