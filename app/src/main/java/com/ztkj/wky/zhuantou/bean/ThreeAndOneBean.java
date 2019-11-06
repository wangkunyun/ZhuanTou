package com.ztkj.wky.zhuantou.bean;

public class ThreeAndOneBean {

    /**
     * errno : 200
     * errmsg : 操作成功
     * data : {"morning_attendance":"2","end_night":"2","attendance_position":"2","morning_type":"1","punchInInfo":{"piid":"5","start_time":"1568161800","end_time":"1568201400","uid":"1","cid":"9","addtime":"2019-09-12","start_field_address":"0","end_field_address":"0","field_reasons":"0","late":"0","leave_early":"0","overtime":"36","start_address":"0","end_address":"0"}}
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
         * morning_attendance : 2
         * end_night : 2
         * attendance_position : 2
         * morning_type : 1
         * punchInInfo : {"piid":"5","start_time":"1568161800","end_time":"1568201400","uid":"1","cid":"9","addtime":"2019-09-12","start_field_address":"0","end_field_address":"0","field_reasons":"0","late":"0","leave_early":"0","overtime":"36","start_address":"0","end_address":"0"}
         */

        private String morning_attendance;
        private String end_night;
        private String attendance_position;
        private String morning_type;
        private PunchInInfoBean punchInInfo;

        public String getMorning_attendance() {
            return morning_attendance;
        }

        public void setMorning_attendance(String morning_attendance) {
            this.morning_attendance = morning_attendance;
        }

        public String getEnd_night() {
            return end_night;
        }

        public void setEnd_night(String end_night) {
            this.end_night = end_night;
        }

        public String getAttendance_position() {
            return attendance_position;
        }

        public void setAttendance_position(String attendance_position) {
            this.attendance_position = attendance_position;
        }

        public String getMorning_type() {
            return morning_type;
        }

        public void setMorning_type(String morning_type) {
            this.morning_type = morning_type;
        }

        public PunchInInfoBean getPunchInInfo() {
            return punchInInfo;
        }

        public void setPunchInInfo(PunchInInfoBean punchInInfo) {
            this.punchInInfo = punchInInfo;
        }

        public static class PunchInInfoBean {
            /**
             * piid : 5
             * start_time : 1568161800
             * end_time : 1568201400
             * uid : 1
             * cid : 9
             * addtime : 2019-09-12
             * start_field_address : 0
             * end_field_address : 0
             * field_reasons : 0
             * late : 0
             * leave_early : 0
             * overtime : 36
             * start_address : 0
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
}
