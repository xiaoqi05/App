package com.ps.app.support.utils;

import com.baidu.location.LocationClientOption;


public class LocationUtil {
    private LocationClientOption.LocationMode tempMode;
    private int frequence;
    private String tempcoor;
    private  int span;
    public LocationUtil(LocationClientOption.LocationMode tempMode,int frequence,String tempcoor) {
        this.tempMode =tempMode;
        this.frequence = frequence;
        this.tempcoor = tempcoor;
    }

    public LocationClientOption InitLocation()
    {
        LocationClientOption option = new LocationClientOption();
        //设置定位模式
        option.setLocationMode(tempMode);
        //返回的定位结果是百度经纬度，默认值bd09ll
        option.setCoorType(tempcoor);
        if(frequence == 0){
            span =100;
        }else {
            span = frequence;
        }
        //设置发起定位请求的间隔时间为100ms
        option.setScanSpan(span);
        //设置是否需要发地理汇编
        option.setIsNeedAddress(false);
        return option;
    }


}
