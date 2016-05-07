package com.ps.app.support.Bean;

public class User {
    /*  name：姓名
      phone：手机号
      pnum：警员编号
      imei：手机串号
      password：密码
      longitude：经度
      latitude：纬度
      phoneType：手机型号*/
    private String latitude;
    private String name;
    private String phone;
    private String pnum;
    private String imei;
    private String password;
    private String longitude;
    private String phoneType;

    public User(String latitude, String name, String phone, String pnum, String imei, String password, String longitude, String phoneType) {
        this.latitude = latitude;
        this.name = name;
        this.phone = phone;
        this.pnum = pnum;
        this.imei = imei;
        this.password = password;
        this.longitude = longitude;
        this.phoneType = phoneType;
    }

    public User() {
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPnum() {
        return pnum;
    }

    public void setPnum(String pnum) {
        this.pnum = pnum;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }
}