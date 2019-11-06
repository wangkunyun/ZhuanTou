package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class OutWorkDetialsBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"bt_id":"12","uid":"11","cid":"9","reason":"出去","place":"天通苑","start_time":"1565858051","end_time":"1567154057","numday":"15","approver":{"name":"郑伟","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-01/5d42ba32f011d.png"},"copier":[{"name":"小砖开门","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d43a69925e40.png"}],"auditor":null,"status":"0","addtime":"2019-08-15 16:34:00","approver_time":null}
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
         * bt_id : 12
         * uid : 11
         * cid : 9
         * reason : 出去
         * place : 天通苑
         * start_time : 1565858051
         * end_time : 1567154057
         * numday : 15
         * approver : {"name":"郑伟","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-01/5d42ba32f011d.png"}
         * copier : [{"name":"小砖开门","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d43a69925e40.png"}]
         * auditor : null
         * status : 0
         * addtime : 2019-08-15 16:34:00
         * approver_time : null
         */

        private String bt_id;
        private String uid;
        private String cid;
        private String reason;
        private String place;
        private String start_time;
        private String end_time;
        private String numday;
        private ApproverBean approver;
        private Object auditor;
        private String status;
        private String addtime;
        private Object approver_time;
        private List<CopierBean> copier;

        public String getBt_id() {
            return bt_id;
        }

        public void setBt_id(String bt_id) {
            this.bt_id = bt_id;
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

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
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

        public String getNumday() {
            return numday;
        }

        public void setNumday(String numday) {
            this.numday = numday;
        }

        public ApproverBean getApprover() {
            return approver;
        }

        public void setApprover(ApproverBean approver) {
            this.approver = approver;
        }

        public Object getAuditor() {
            return auditor;
        }

        public void setAuditor(Object auditor) {
            this.auditor = auditor;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public Object getApprover_time() {
            return approver_time;
        }

        public void setApprover_time(Object approver_time) {
            this.approver_time = approver_time;
        }

        public List<CopierBean> getCopier() {
            return copier;
        }

        public void setCopier(List<CopierBean> copier) {
            this.copier = copier;
        }

        public static class ApproverBean {
            /**
             * name : 郑伟
             * head : https://api.zhuantoukj.com/birck/Public/heard/2019-08-01/5d42ba32f011d.png
             */

            private String name;
            private String head;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }
        }

        public static class CopierBean {
            /**
             * name : 小砖开门
             * head : https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d43a69925e40.png
             */

            private String name;
            private String head;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }
        }
    }
}
