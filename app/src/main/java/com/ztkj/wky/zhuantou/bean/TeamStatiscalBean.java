package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class TeamStatiscalBean {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"workingHours":"0.3","lateNumber":"1","leaveEarlyNumber":"3","missingCardNumber":"4","absenteeismNumber":"7","fieldPersonnelNumber":"0","overtimeNumber":"2","arrayNew":{"late":[{"uid":"162","username":"吕松松","num":"8次"}],"leaveEarly":[{"uid":"162","username":"吕松松","num":"5次"},{"uid":"240","username":"曲义","num":"1次"},{"uid":"238","username":"李美欣","num":"1次"}],"missingCard":[{"uid":"18","username":"王晓秋","num":"2次"},{"uid":"162","username":"吕松松","num":"5次"},{"uid":"240","username":"曲义","num":"2次"},{"uid":"238","username":"李美欣","num":"1次"}],"absenteeism":[{"uid":"18","username":"王晓秋","num":"15次"},{"uid":"1","username":"王鹏杰","num":"17次"},{"uid":"231","username":"王靖雯","num":"17次"},{"uid":"162","username":"吕松松","num":"7次"},{"uid":"168","username":"王坤云2","num":"17次"},{"uid":"240","username":"曲义","num":"17次"},{"uid":"238","username":"李美欣","num":"17次"}],"fieldPersonnel":[{"uid":"240","username":"曲义","num":"17次"},{"uid":"238","username":"李美欣","num":"17次"}],"overtime":[{"uid":"162","username":"吕松松","num":"1.9时"},{"uid":"240","username":"曲义","num":"0.9时"}],"workingHours":[{"uid":"18","username":"王晓秋","num":"0.0"},{"uid":"1","username":"王鹏杰","num":"0.0"},{"uid":"231","username":"王靖雯","num":"0.0"},{"uid":"162","username":"吕松松","num":"1.8"},{"uid":"168","username":"王坤云2","num":"0.0"},{"uid":"240","username":"曲义","num":"0.0"},{"uid":"238","username":"李美欣","num":"0.0"}]}}
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
         * workingHours : 0.3
         * lateNumber : 1
         * leaveEarlyNumber : 3
         * missingCardNumber : 4
         * absenteeismNumber : 7
         * fieldPersonnelNumber : 0
         * overtimeNumber : 2
         * arrayNew : {"late":[{"uid":"162","username":"吕松松","num":"8次"}],"leaveEarly":[{"uid":"162","username":"吕松松","num":"5次"},{"uid":"240","username":"曲义","num":"1次"},{"uid":"238","username":"李美欣","num":"1次"}],"missingCard":[{"uid":"18","username":"王晓秋","num":"2次"},{"uid":"162","username":"吕松松","num":"5次"},{"uid":"240","username":"曲义","num":"2次"},{"uid":"238","username":"李美欣","num":"1次"}],"absenteeism":[{"uid":"18","username":"王晓秋","num":"15次"},{"uid":"1","username":"王鹏杰","num":"17次"},{"uid":"231","username":"王靖雯","num":"17次"},{"uid":"162","username":"吕松松","num":"7次"},{"uid":"168","username":"王坤云2","num":"17次"},{"uid":"240","username":"曲义","num":"17次"},{"uid":"238","username":"李美欣","num":"17次"}],"fieldPersonnel":[{"uid":"240","username":"曲义","num":"17次"},{"uid":"238","username":"李美欣","num":"17次"}],"overtime":[{"uid":"162","username":"吕松松","num":"1.9时"},{"uid":"240","username":"曲义","num":"0.9时"}],"workingHours":[{"uid":"18","username":"王晓秋","num":"0.0"},{"uid":"1","username":"王鹏杰","num":"0.0"},{"uid":"231","username":"王靖雯","num":"0.0"},{"uid":"162","username":"吕松松","num":"1.8"},{"uid":"168","username":"王坤云2","num":"0.0"},{"uid":"240","username":"曲义","num":"0.0"},{"uid":"238","username":"李美欣","num":"0.0"}]}
         */

        private String workingHours;
        private String lateNumber;
        private String leaveEarlyNumber;
        private String missingCardNumber;
        private String absenteeismNumber;
        private String fieldPersonnelNumber;
        private String overtimeNumber;
        private ArrayNewBean arrayNew;

        public String getWorkingHours() {
            return workingHours;
        }

        public void setWorkingHours(String workingHours) {
            this.workingHours = workingHours;
        }

        public String getLateNumber() {
            return lateNumber;
        }

        public void setLateNumber(String lateNumber) {
            this.lateNumber = lateNumber;
        }

        public String getLeaveEarlyNumber() {
            return leaveEarlyNumber;
        }

        public void setLeaveEarlyNumber(String leaveEarlyNumber) {
            this.leaveEarlyNumber = leaveEarlyNumber;
        }

        public String getMissingCardNumber() {
            return missingCardNumber;
        }

        public void setMissingCardNumber(String missingCardNumber) {
            this.missingCardNumber = missingCardNumber;
        }

        public String getAbsenteeismNumber() {
            return absenteeismNumber;
        }

        public void setAbsenteeismNumber(String absenteeismNumber) {
            this.absenteeismNumber = absenteeismNumber;
        }

        public String getFieldPersonnelNumber() {
            return fieldPersonnelNumber;
        }

        public void setFieldPersonnelNumber(String fieldPersonnelNumber) {
            this.fieldPersonnelNumber = fieldPersonnelNumber;
        }

        public String getOvertimeNumber() {
            return overtimeNumber;
        }

        public void setOvertimeNumber(String overtimeNumber) {
            this.overtimeNumber = overtimeNumber;
        }

        public ArrayNewBean getArrayNew() {
            return arrayNew;
        }

        public void setArrayNew(ArrayNewBean arrayNew) {
            this.arrayNew = arrayNew;
        }

        public static class ArrayNewBean {
            private List<LateBean> late;
            private List<LeaveEarlyBean> leaveEarly;
            private List<MissingCardBean> missingCard;
            private List<AbsenteeismBean> absenteeism;
            private List<FieldPersonnelBean> fieldPersonnel;
            private List<OvertimeBean> overtime;
            private List<WorkingHoursBean> workingHours;

            public List<LateBean> getLate() {
                return late;
            }

            public void setLate(List<LateBean> late) {
                this.late = late;
            }

            public List<LeaveEarlyBean> getLeaveEarly() {
                return leaveEarly;
            }

            public void setLeaveEarly(List<LeaveEarlyBean> leaveEarly) {
                this.leaveEarly = leaveEarly;
            }

            public List<MissingCardBean> getMissingCard() {
                return missingCard;
            }

            public void setMissingCard(List<MissingCardBean> missingCard) {
                this.missingCard = missingCard;
            }

            public List<AbsenteeismBean> getAbsenteeism() {
                return absenteeism;
            }

            public void setAbsenteeism(List<AbsenteeismBean> absenteeism) {
                this.absenteeism = absenteeism;
            }

            public List<FieldPersonnelBean> getFieldPersonnel() {
                return fieldPersonnel;
            }

            public void setFieldPersonnel(List<FieldPersonnelBean> fieldPersonnel) {
                this.fieldPersonnel = fieldPersonnel;
            }

            public List<OvertimeBean> getOvertime() {
                return overtime;
            }

            public void setOvertime(List<OvertimeBean> overtime) {
                this.overtime = overtime;
            }

            public List<WorkingHoursBean> getWorkingHours() {
                return workingHours;
            }

            public void setWorkingHours(List<WorkingHoursBean> workingHours) {
                this.workingHours = workingHours;
            }

            public static class LateBean {
                /**
                 * uid : 162
                 * username : 吕松松
                 * num : 8次
                 */

                private String uid;
                private String username;
                private String num;

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }
            }

            public static class LeaveEarlyBean {
                /**
                 * uid : 162
                 * username : 吕松松
                 * num : 5次
                 */

                private String uid;
                private String username;
                private String num;

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }
            }

            public static class MissingCardBean {
                /**
                 * uid : 18
                 * username : 王晓秋
                 * num : 2次
                 */

                private String uid;
                private String username;
                private String num;

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }
            }

            public static class AbsenteeismBean {
                /**
                 * uid : 18
                 * username : 王晓秋
                 * num : 15次
                 */

                private String uid;
                private String username;
                private String num;

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }
            }

            public static class FieldPersonnelBean {
                /**
                 * uid : 240
                 * username : 曲义
                 * num : 17次
                 */

                private String uid;
                private String username;
                private String num;

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }
            }

            public static class OvertimeBean {
                /**
                 * uid : 162
                 * username : 吕松松
                 * num : 1.9时
                 */

                private String uid;
                private String username;
                private String num;

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }
            }

            public static class WorkingHoursBean {
                /**
                 * uid : 18
                 * username : 王晓秋
                 * num : 0.0
                 */

                private String uid;
                private String username;
                private String num;

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }
            }
        }
    }
}
