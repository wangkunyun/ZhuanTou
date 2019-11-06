package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class AllApplyBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"id":"5","username":"王坤云的请假申请","addtime":"2019-08-12 14:24:00","reason":"小砖要请假","status":"0","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d44029fa3c69.png","type":"lid"},{"id":"4","username":"王坤云的请假申请","addtime":"2019-08-12 14:19:00","reason":"jckf","status":"0","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d44029fa3c69.png","type":"lid"}]
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
         * id : 5
         * username : 王坤云的请假申请
         * addtime : 2019-08-12 14:24:00
         * reason : 小砖要请假
         * status : 0
         * head : https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d44029fa3c69.png
         * type : lid
         */

        private String id;
        private String username;
        private String addtime;
        private String reason;
        private String status;
        private String head;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
