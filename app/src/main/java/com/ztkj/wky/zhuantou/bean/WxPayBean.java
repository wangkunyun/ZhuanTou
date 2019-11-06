package com.ztkj.wky.zhuantou.bean;

import com.google.gson.annotations.SerializedName;

public class WxPayBean {

    /**
     * errno : 200
     * errmsg : 操作成功
     * data : {"appid":"wx3ebb6c166d251e40","noncestr":"3CFFL7x9LFjTA5AY","package":"Sign=WXPay","partnerid":"1540618911","prepayid":"wx1016005227451567fb0c8f191684597800","timestamp":1570694452,"sign":"15372F99B45D5B8E95B8BC78FA8E5921"}
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
         * appid : wx3ebb6c166d251e40
         * noncestr : 3CFFL7x9LFjTA5AY
         * package : Sign=WXPay
         * partnerid : 1540618911
         * prepayid : wx1016005227451567fb0c8f191684597800
         * timestamp : 1570694452
         * sign : 15372F99B45D5B8E95B8BC78FA8E5921
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private int timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
