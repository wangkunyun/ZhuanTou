package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;

public class SuperBean implements Serializable {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"uid":"162","username":"吕松松","phone":"13460720766","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-12-19/5dfb269a6aec5.png"}
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

    public static class DataBean implements Serializable{
        /**
         * uid : 162
         * username : 吕松松
         * phone : 13460720766
         * head : https://api.zhuantoukj.com/birck/Public/heard/2019-12-19/5dfb269a6aec5.png
         */

        private String uid;
        private String username;
        private String phone;
        private String head;

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }
    }
}
