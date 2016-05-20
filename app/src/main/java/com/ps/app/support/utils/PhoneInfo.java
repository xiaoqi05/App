package com.ps.app.support.utils;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

public class PhoneInfo {

    public static String getImei(Context context) {
        TelephonyManager systemService = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return systemService.getDeviceId();
    }

    public static String getPhoneType() {
        return Build.MODEL;
    }
}