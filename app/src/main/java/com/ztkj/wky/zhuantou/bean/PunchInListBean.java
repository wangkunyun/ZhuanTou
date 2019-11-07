package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class PunchInListBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"day":"1","start_time":"1572577200","end_time":"1572577200","addtime":"2019-11-01","late":"0","leave_early":"0","start_field_address":"0","end_field_address":"0","festival":"0","week":"5"},{"day":"2","start_time":"0","end_time":"0","addtime":"2019-11-02","late":"0","leave_early":"0","start_field_address":"0","end_field_address":"0","festival":"0","week":"6"},{"day":"3","start_time":"0","end_time":"0","addtime":"2019-11-03","late":"0","leave_early":"0","start_field_address":"0","end_field_address":"0","festival":"0","week":"7"},{"day":"4","start_time":"1572829200","end_time":"1572861600","addtime":"2019-11-04","late":"0","leave_early":"0","start_field_address":"0","end_field_address":"0","festival":"0","week":"1"},{"day":"5","start_time":"0","end_time":"0","addtime":"2019-11-05","late":"0","leave_early":"0","start_field_address":"0","end_field_address":"0","festival":"0","week":"2"},{"day":"6","start_time":"0","end_time":"1573018691","addtime":"2019-11-06","late":"0","leave_early":"262","start_field_address":"0","end_field_address":"0","festival":"0","week":"3"}]
     */

    private String errno;
    private String errmsg;
    private List<DataBean> data;

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * day : 1
         * start_time : 1572577200
         * end_time : 1572577200
         * addtime : 2019-11-01
         * late : 0
         * leave_early : 0
         * start_field_address : 0
         * end_field_address : 0
         * festival : 0
         * week : 5
         */

        private String day;
        private String start_time;
        private String end_time;
        private String addtime;
        private String late;
        private String leave_early;
        private String start_field_address;
        private String end_field_address;
        private String festival;
        private String week;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getLate() {
            return late;
        }

        public void setLate(String late) {
            this.late = late;
        }

        public String getLeave_early() {
            return leave_early;
        }

        public void setLeave_early(String leave_early) {
            this.leave_early = leave_early;
        }

        public String getStart_field_address() {
            return start_field_address;
        }

        public void setStart_field_address(String start_field_address) {
            this.start_field_address = start_field_address;
        }

        public String getEnd_field_address() {
            return end_field_address;
        }

        public void setEnd_field_address(String end_field_address) {
            this.end_field_address = end_field_address;
        }

        public String getFestival() {
            return festival;
        }

        public void setFestival(String festival) {
            this.festival = festival;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }
    }
}
