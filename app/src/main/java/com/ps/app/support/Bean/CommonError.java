package com.ps.app.support.Bean;

public class CommonError {


    /**
     * code : 2206
     * data : {}
     * desc : 无结果.
     * error : 
     */

    private int code;
    private DataBean data;
    private String desc;
    private String error;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static class DataBean {
    }
}