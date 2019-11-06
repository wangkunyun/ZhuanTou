package com.ztkj.wky.zhuantou.bean;

public class MineStatiscallBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"workingHours":"1.4","attendanceDays":"9","rest":"12","late":"3","leaveEarly":"6","missingCard":"3","absenteeism":"11","fieldPersonnel":"","overtime":"0.9"}
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
         * workingHours : 1.4
         * attendanceDays : 9
         * rest : 12
         * late : 3
         * leaveEarly : 6
         * missingCard : 3
         * absenteeism : 11
         * fieldPersonnel :
         * overtime : 0.9
         */

        private String workingHours;
        private String attendanceDays;
        private String rest;
        private String late;
        private String leaveEarly;
        private String missingCard;
        private String absenteeism;
        private String fieldPersonnel;
        private String overtime;

        public String getWorkingHours() {
            return workingHours;
        }

        public void setWorkingHours(String workingHours) {
            this.workingHours = workingHours;
        }

        public String getAttendanceDays() {
            return attendanceDays;
        }

        public void setAttendanceDays(String attendanceDays) {
            this.attendanceDays = attendanceDays;
        }

        public String getRest() {
            return rest;
        }

        public void setRest(String rest) {
            this.rest = rest;
        }

        public String getLate() {
            return late;
        }

        public void setLate(String late) {
            this.late = late;
        }

        public String getLeaveEarly() {
            return leaveEarly;
        }

        public void setLeaveEarly(String leaveEarly) {
            this.leaveEarly = leaveEarly;
        }

        public String getMissingCard() {
            return missingCard;
        }

        public void setMissingCard(String missingCard) {
            this.missingCard = missingCard;
        }

        public String getAbsenteeism() {
            return absenteeism;
        }

        public void setAbsenteeism(String absenteeism) {
            this.absenteeism = absenteeism;
        }

        public String getFieldPersonnel() {
            return fieldPersonnel;
        }

        public void setFieldPersonnel(String fieldPersonnel) {
            this.fieldPersonnel = fieldPersonnel;
        }

        public String getOvertime() {
            return overtime;
        }

        public void setOvertime(String overtime) {
            this.overtime = overtime;
        }
    }
}
