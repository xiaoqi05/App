package com.ps.app.support.Bean;

import java.util.List;

/*********************************************************************************
 * Project Name  : PoliceSecretary
 * Package       : com.ps.app.support.Bean
 * <p>
 * <Pre>
 * TODO  描述文件做什么的
 * </Pre>
 *
 * @AUTHOR by 肖齐
 * Created by 2016-05-07 11:18.
 * <p>
 * <p>
 * ********************************************************************************
 */
public class test {

    /**
     * resultcode : 200
     * reason : successed!
     * result : [{"weatherid":"07","weather":"小雨","temp1":"18","temp2":"24","sh":"05","eh":"08","date":"20160507","sfdate":"20160507050000","efdate":"20160507080000"},{"weatherid":"09","weather":"大雨","temp1":"24","temp2":"17","sh":"08","eh":"11","date":"20160507","sfdate":"20160507080000","efdate":"20160507110000"},{"weatherid":"10","weather":"暴雨","temp1":"17","temp2":"16","sh":"11","eh":"14","date":"20160507","sfdate":"20160507110000","efdate":"20160507140000"},{"weatherid":"10","weather":"暴雨","temp1":"16","temp2":"15","sh":"14","eh":"17","date":"20160507","sfdate":"20160507140000","efdate":"20160507170000"},{"weatherid":"07","weather":"小雨","temp1":"15","temp2":"16","sh":"17","eh":"20","date":"20160507","sfdate":"20160507170000","efdate":"20160507200000"},{"weatherid":"07","weather":"小雨","temp1":"16","temp2":"16","sh":"20","eh":"23","date":"20160507","sfdate":"20160507200000","efdate":"20160507230000"},{"weatherid":"07","weather":"小雨","temp1":"16","temp2":"16","sh":"23","eh":"02","date":"20160507","sfdate":"20160507230000","efdate":"20160508020000"},{"weatherid":"08","weather":"中雨","temp1":"16","temp2":"17","sh":"02","eh":"05","date":"20160508","sfdate":"20160508020000","efdate":"20160508050000"},{"weatherid":"08","weather":"中雨","temp1":"17","temp2":"17","sh":"05","eh":"08","date":"20160508","sfdate":"20160508050000","efdate":"20160508080000"},{"weatherid":"01","weather":"多云","temp1":"17","temp2":"17","sh":"08","eh":"11","date":"20160508","sfdate":"20160508080000","efdate":"20160508110000"},{"weatherid":"02","weather":"阴","temp1":"17","temp2":"17","sh":"11","eh":"14","date":"20160508","sfdate":"20160508110000","efdate":"20160508140000"},{"weatherid":"02","weather":"阴","temp1":"17","temp2":"15","sh":"14","eh":"17","date":"20160508","sfdate":"20160508140000","efdate":"20160508170000"},{"weatherid":"07","weather":"小雨","temp1":"15","temp2":"15","sh":"17","eh":"20","date":"20160508","sfdate":"20160508170000","efdate":"20160508200000"},{"weatherid":"07","weather":"小雨","temp1":"15","temp2":"15","sh":"20","eh":"23","date":"20160508","sfdate":"20160508200000","efdate":"20160508230000"},{"weatherid":"08","weather":"中雨","temp1":"15","temp2":"15","sh":"23","eh":"02","date":"20160508","sfdate":"20160508230000","efdate":"20160509020000"},{"weatherid":"08","weather":"中雨","temp1":"15","temp2":"16","sh":"02","eh":"05","date":"20160509","sfdate":"20160509020000","efdate":"20160509050000"},{"weatherid":"08","weather":"中雨","temp1":"16","temp2":"17","sh":"05","eh":"08","date":"20160509","sfdate":"20160509050000","efdate":"20160509080000"},{"weatherid":"07","weather":"小雨","temp1":"17","temp2":"22","sh":"08","eh":"11","date":"20160509","sfdate":"20160509080000","efdate":"20160509110000"},{"weatherid":"07","weather":"小雨","temp1":"22","temp2":"23","sh":"11","eh":"14","date":"20160509","sfdate":"20160509110000","efdate":"20160509140000"},{"weatherid":"07","weather":"小雨","temp1":"23","temp2":"20","sh":"14","eh":"17","date":"20160509","sfdate":"20160509140000","efdate":"20160509170000"},{"weatherid":"07","weather":"小雨","temp1":"20","temp2":"17","sh":"17","eh":"20","date":"20160509","sfdate":"20160509170000","efdate":"20160509200000"},{"weatherid":"08","weather":"中雨","temp1":"17","temp2":"16","sh":"20","eh":"23","date":"20160509","sfdate":"20160509200000","efdate":"20160509230000"},{"weatherid":"08","weather":"中雨","temp1":"16","temp2":"16","sh":"23","eh":"02","date":"20160509","sfdate":"20160509230000","efdate":"20160510020000"},{"weatherid":"08","weather":"中雨","temp1":"16","temp2":"17","sh":"02","eh":"05","date":"20160510","sfdate":"20160510020000","efdate":"20160510050000"},{"weatherid":"08","weather":"中雨","temp1":"17","temp2":"22","sh":"05","eh":"11","date":"20160510","sfdate":"20160510050000","efdate":"20160510110000"},{"weatherid":"01","weather":"多云","temp1":"22","temp2":"17","sh":"11","eh":"17","date":"20160510","sfdate":"20160510110000","efdate":"20160510170000"},{"weatherid":"01","weather":"多云","temp1":"17","temp2":"15","sh":"17","eh":"23","date":"20160510","sfdate":"20160510170000","efdate":"20160510230000"},{"weatherid":"01","weather":"多云","temp1":"15","temp2":"19","sh":"23","eh":"05","date":"20160510","sfdate":"20160510230000","efdate":"20160511050000"},{"weatherid":"01","weather":"多云","temp1":"19","temp2":"26","sh":"05","eh":"11","date":"20160511","sfdate":"20160511050000","efdate":"20160511110000"},{"weatherid":"01","weather":"多云","temp1":"26","temp2":"19","sh":"11","eh":"17","date":"20160511","sfdate":"20160511110000","efdate":"20160511170000"},{"weatherid":"00","weather":"晴","temp1":"19","temp2":"17","sh":"17","eh":"23","date":"20160511","sfdate":"20160511170000","efdate":"20160511230000"},{"weatherid":"00","weather":"晴","temp1":"17","temp2":"21","sh":"23","eh":"05","date":"20160511","sfdate":"20160511230000","efdate":"20160512050000"},{"weatherid":"00","weather":"晴","temp1":"21","temp2":"28","sh":"05","eh":"11","date":"20160512","sfdate":"20160512050000","efdate":"20160512110000"},{"weatherid":"00","weather":"晴","temp1":"28","temp2":"20","sh":"11","eh":"17","date":"20160512","sfdate":"20160512110000","efdate":"20160512170000"},{"weatherid":"00","weather":"晴","temp1":"20","temp2":"18","sh":"17","eh":"23","date":"20160512","sfdate":"20160512170000","efdate":"20160512230000"},{"weatherid":"02","weather":"阴","temp1":"18","temp2":"21","sh":"23","eh":"05","date":"20160512","sfdate":"20160512230000","efdate":"20160513050000"},{"weatherid":"02","weather":"阴","temp1":"21","temp2":"29","sh":"05","eh":"11","date":"20160513","sfdate":"20160513050000","efdate":"20160513110000"},{"weatherid":"01","weather":"多云","temp1":"29","temp2":"22","sh":"11","eh":"17","date":"20160513","sfdate":"20160513110000","efdate":"20160513170000"},{"weatherid":"01","weather":"多云","temp1":"22","temp2":"19","sh":"17","eh":"23","date":"20160513","sfdate":"20160513170000","efdate":"20160513230000"},{"weatherid":"01","weather":"多云","temp1":"19","temp2":"22","sh":"23","eh":"05","date":"20160513","sfdate":"20160513230000","efdate":"20160514050000"}]
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private int error_code;
    /**
     * weatherid : 07
     * weather : 小雨
     * temp1 : 18
     * temp2 : 24
     * sh : 05
     * eh : 08
     * date : 20160507
     * sfdate : 20160507050000
     * efdate : 20160507080000
     */

    private List<ResultBean> result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String weatherid;
        private String weather;
        private String temp1;
        private String temp2;
        private String sh;
        private String eh;
        private String date;
        private String sfdate;
        private String efdate;

        public String getWeatherid() {
            return weatherid;
        }

        public void setWeatherid(String weatherid) {
            this.weatherid = weatherid;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemp1() {
            return temp1;
        }

        public void setTemp1(String temp1) {
            this.temp1 = temp1;
        }

        public String getTemp2() {
            return temp2;
        }

        public void setTemp2(String temp2) {
            this.temp2 = temp2;
        }

        public String getSh() {
            return sh;
        }

        public void setSh(String sh) {
            this.sh = sh;
        }

        public String getEh() {
            return eh;
        }

        public void setEh(String eh) {
            this.eh = eh;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSfdate() {
            return sfdate;
        }

        public void setSfdate(String sfdate) {
            this.sfdate = sfdate;
        }

        public String getEfdate() {
            return efdate;
        }

        public void setEfdate(String efdate) {
            this.efdate = efdate;
        }
    }
}