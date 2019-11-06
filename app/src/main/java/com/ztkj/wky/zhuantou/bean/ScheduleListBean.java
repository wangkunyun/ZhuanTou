package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class ScheduleListBean {

    /**
     * errno : 200
     * errmsg : 查询成功
     * data : [{"same_day":"2019-09-09","sc_id":"24","uid":"11","days":"2019-09-09","start_time":"2019-09-09 14:04","end_time":"2019-10-09 14:04","tdays":"2019-10-09","remarks":"跨月跨月跨跨月"},{"same_day":"2019-09-09","sc_id":"23","uid":"11","days":"2019-09-09","start_time":"2019-09-09 13:48","end_time":"2019-10-09 13:48","tdays":"2019-10-09","remarks":"测试4"},{"same_day":"2019-09-09","sc_id":"22","uid":"11","days":"2019-09-09","start_time":"2019-09-09 13:46","end_time":"2019-09-09 13:46","tdays":"2019-09-09","remarks":"跨月测试3"}]
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
         * same_day : 2019-09-09
         * sc_id : 24
         * uid : 11
         * days : 2019-09-09
         * start_time : 2019-09-09 14:04
         * end_time : 2019-10-09 14:04
         * tdays : 2019-10-09
         * remarks : 跨月跨月跨跨月
         */

        private String same_day;
        private String sc_id;
        private String uid;
        private String days;
        private String start_time;
        private String end_time;
        private String tdays;
        private String remarks;

        public String getSame_day() {
            return same_day;
        }

        public void setSame_day(String same_day) {
            this.same_day = same_day;
        }

        public String getSc_id() {
            return sc_id;
        }

        public void setSc_id(String sc_id) {
            this.sc_id = sc_id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
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

        public String getTdays() {
            return tdays;
        }

        public void setTdays(String tdays) {
            this.tdays = tdays;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
