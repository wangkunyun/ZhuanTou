package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class ReimbursementDetailsBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"rid":"8","cid":"9","uid":"11","total_money":"5000","category":"采购啊","detailed":"我才够了","enclosure":null,"approver":{"name":"郑伟","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-01/5d42ba32f011d.png"},"copier":[{"name":"小砖开门","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d43a69925e40.png"},{"name":"大大云","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d44029fa3c69.png"}],"auditor":null,"status":"0","addtime":"2019-08-15 15:25:00","approver_time":null}
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
         * rid : 8
         * cid : 9
         * uid : 11
         * total_money : 5000
         * category : 采购啊
         * detailed : 我才够了
         * enclosure : null
         * approver : {"name":"郑伟","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-01/5d42ba32f011d.png"}
         * copier : [{"name":"小砖开门","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d43a69925e40.png"},{"name":"大大云","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d44029fa3c69.png"}]
         * auditor : null
         * status : 0
         * addtime : 2019-08-15 15:25:00
         * approver_time : null
         */

        private String rid;
        private String cid;
        private String uid;
        private String total_money;
        private String category;
        private String detailed;
        private Object enclosure;
        private ApproverBean approver;
        private Object auditor;
        private String status;
        private String addtime;
        private Object approver_time;
        private List<CopierBean> copier;

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDetailed() {
            return detailed;
        }

        public void setDetailed(String detailed) {
            this.detailed = detailed;
        }

        public Object getEnclosure() {
            return enclosure;
        }

        public void setEnclosure(Object enclosure) {
            this.enclosure = enclosure;
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
