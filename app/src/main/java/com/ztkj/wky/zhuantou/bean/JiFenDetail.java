package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;

public class JiFenDetail implements Serializable {


    /**
     * errno : 200
     * errmsg : 请求成功
     * data : {"gid":"2","gname":"电视机台灯","sku":"白色\b","integral":"1100","consignee":"王鹏杰","phone":"15312341234","address":"北京市通州区世界侨商中心"}
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
         * gid : 2
         * gname : 电视机台灯
         * sku : 白色
         * integral : 1100
         * consignee : 王鹏杰
         * phone : 15312341234
         * address : 北京市通州区世界侨商中心
         */

        private String gid;
        private String gname;
        private String sku;
        private String integral;
        private String consignee;
        private String phone;
        private String address;

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
