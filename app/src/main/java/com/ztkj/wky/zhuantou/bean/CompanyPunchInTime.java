package com.ztkj.wky.zhuantou.bean;

public class CompanyPunchInTime {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"start_time":"10:00","end_time":"18:00","address":"通州北关(地铁站)","radius":"200"}
     */

    private String errno;
    private String errmsg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * start_time : 10:00
         * end_time : 18:00
         * address : 通州北关(地铁站)
         * radius : 200
         */

        private String start_time;
        private String end_time;
        private String address;
        private String radius;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRadius() {
            return radius;
        }

        public void setRadius(String radius) {
            this.radius = radius;
        }
    }
}
