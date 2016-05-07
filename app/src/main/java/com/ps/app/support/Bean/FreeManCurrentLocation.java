package com.ps.app.support.Bean;

public class FreeManCurrentLocation {

    /**
     * code : 2000
     * data : {"createTime":null,"id":2,"imei":"97862134523","isalarm":0,"latitude":"31","longitude":"104","mid":4,"remark":""}
     * error : 
     * desc : 成功!
     */

    private int code;
    /**
     * createTime : null
     * id : 2
     * imei : 97862134523
     * isalarm : 0
     * latitude : 31
     * longitude : 104
     * mid : 4
     * remark : 
     */

    private DataBean data;
    private String error;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class DataBean {
        private Object createTime;
        private int id;
        private String imei;
        private int isalarm;
        private String latitude;
        private String longitude;
        private int mid;
        private String remark;

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public int getIsalarm() {
            return isalarm;
        }

        public void setIsalarm(int isalarm) {
            this.isalarm = isalarm;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}