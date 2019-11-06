package com.ztkj.wky.zhuantou.bean;

public class PunchInBean {

    /**
     * errno : 200
     * errmsg : 打卡成功
     * data : {"piid":"7","start_time":"1568690553","end_time":"0","uid":"11","cid":"9","addtime":"2019-09-17","start_field_address":"0","end_field_address":"0","field_reasons":"0","late":"0","leave_early":"0","overtime":"0","start_address":"北京市通州区盐滩路在通州北关附近","end_address":"0"}
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
         * piid : 7
         * start_time : 1568690553
         * end_time : 0
         * uid : 11
         * cid : 9
         * addtime : 2019-09-17
         * start_field_address : 0
         * end_field_address : 0
         * field_reasons : 0
         * late : 0
         * leave_early : 0
         * overtime : 0
         * start_address : 北京市通州区盐滩路在通州北关附近
         * end_address : 0
         */

        private String piid;
        private String start_time;
        private String end_time;
        private String uid;
        private String cid;
        private String addtime;
        private String start_field_address;
        private String end_field_address;
        private String field_reasons;
        private String late;
        private String leave_early;
        private String overtime;
        private String start_address;
        private String end_address;

        public String getPiid() {
            return piid;
        }

        public void setPiid(String piid) {
            this.piid = piid;
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

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
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

        public String getField_reasons() {
            return field_reasons;
        }

        public void setField_reasons(String field_reasons) {
            this.field_reasons = field_reasons;
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

        public String getOvertime() {
            return overtime;
        }

        public void setOvertime(String overtime) {
            this.overtime = overtime;
        }

        public String getStart_address() {
            return start_address;
        }

        public void setStart_address(String start_address) {
            this.start_address = start_address;
        }

        public String getEnd_address() {
            return end_address;
        }

        public void setEnd_address(String end_address) {
            this.end_address = end_address;
        }
    }
}
