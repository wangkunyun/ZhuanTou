package com.ztkj.wky.zhuantou.adapter;

import java.util.List;

public class UserSealDetailsBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"pid":"7","cid":"9","uid":"11","u_name":"王坤云","seal_type":"法人章","pir_time":"1565845302","file_name":"某某协议","file_number":"3","pirh_note":null,"relevant_file":null,"approver":{"name":"小砖开门","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d43a69925e40.png"},"copier":[{"name":"大大云","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d44029fa3c69.png"},{"name":"郑伟","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-01/5d42ba32f011d.png"}],"auditor":null,"status":"0","addtime":"2019-08-15 13:02:00","approver_time":null}
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
         * pid : 7
         * cid : 9
         * uid : 11
         * u_name : 王坤云
         * seal_type : 法人章
         * pir_time : 1565845302
         * file_name : 某某协议
         * file_number : 3
         * pirh_note : null
         * relevant_file : null
         * approver : {"name":"小砖开门","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d43a69925e40.png"}
         * copier : [{"name":"大大云","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d44029fa3c69.png"},{"name":"郑伟","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-01/5d42ba32f011d.png"}]
         * auditor : null
         * status : 0
         * addtime : 2019-08-15 13:02:00
         * approver_time : null
         */

        private String pid;
        private String cid;
        private String uid;
        private String u_name;
        private String seal_type;
        private String pir_time;
        private String file_name;
        private String file_number;
        private Object pirh_note;
        private Object relevant_file;
        private ApproverBean approver;
        private Object auditor;
        private String status;
        private String addtime;
        private Object approver_time;
        private List<CopierBean> copier;

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
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

        public String getU_name() {
            return u_name;
        }

        public void setU_name(String u_name) {
            this.u_name = u_name;
        }

        public String getSeal_type() {
            return seal_type;
        }

        public void setSeal_type(String seal_type) {
            this.seal_type = seal_type;
        }

        public String getPir_time() {
            return pir_time;
        }

        public void setPir_time(String pir_time) {
            this.pir_time = pir_time;
        }

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getFile_number() {
            return file_number;
        }

        public void setFile_number(String file_number) {
            this.file_number = file_number;
        }

        public Object getPirh_note() {
            return pirh_note;
        }

        public void setPirh_note(Object pirh_note) {
            this.pirh_note = pirh_note;
        }

        public Object getRelevant_file() {
            return relevant_file;
        }

        public void setRelevant_file(Object relevant_file) {
            this.relevant_file = relevant_file;
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

        public static class CopierBean {
            /**
             * name : 大大云
             * head : https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d44029fa3c69.png
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
