package com.ps.app.base;

import android.app.Application;
import android.os.Vibrator;

import com.ps.app.service.LocationService;


public class MyApplication extends Application {

    public LocationService locationService;
    public Vibrator mVibrator;
    @Override
    public void onCreate() {
        super.onCreate();
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        //locationService = new LocationService(getApplicationContext());
       // mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        //SDKInitializer.initialize(getApplicationContext());

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
