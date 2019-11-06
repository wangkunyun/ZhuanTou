package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class BillListBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"biid":"2","uid":"1","orderid":"20191008163040","type":"1","money":"100","reason":"充值余额","addtime":"1570523441","state":"1"},{"biid":"1","uid":"1","orderid":"20191008162907","type":"1","money":"10000","reason":"充值余额","addtime":"1570523348","state":"1"}]
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
         * biid : 2
         * uid : 1
         * orderid : 20191008163040
         * type : 1
         * money : 100
         * reason : 充值余额
         * addtime : 1570523441
         * state : 1
         */

        private String biid;
        private String uid;
        private String orderid;
        private String type;
        private String money;
        private String reason;
        private String addtime;
        private String state;

        public String getBiid() {
            return biid;
        }

        public void setBiid(String biid) {
            this.biid = biid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
