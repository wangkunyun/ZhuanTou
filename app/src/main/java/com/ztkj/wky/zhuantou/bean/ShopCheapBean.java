package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class ShopCheapBean implements Serializable {


    /**
     * errno : 200
     * errmsg : 查询成功
     * data : [{"saa_id":"1","saa_name":"5折专区","saa_img":"http://xzshop.zhuantoukj.com/Public/zhekou/wuzhe.png","saa_label":"折扣"},{"saa_id":"2","saa_name":"买一送一","saa_img":"http://xzshop.zhuantoukj.com/Public/zhekou/maiyisongyi.png","saa_label":"限量"},{"saa_id":"3","saa_name":"满减","saa_img":"http://xzshop.zhuantoukj.com/Public/zhekou/manjian.png","saa_label":"现时"}]
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

    public static class DataBean implements Serializable{
        /**
         * saa_id : 1
         * saa_name : 5折专区
         * saa_img : http://xzshop.zhuantoukj.com/Public/zhekou/wuzhe.png
         * saa_label : 折扣
         */

        private String saa_id;
        private String saa_name;
        private String saa_img;
        private String saa_label;

        public String getSaa_id() {
            return saa_id;
        }

        public void setSaa_id(String saa_id) {
            this.saa_id = saa_id;
        }

        public String getSaa_name() {
            return saa_name;
        }

        public void setSaa_name(String saa_name) {
            this.saa_name = saa_name;
        }

        public String getSaa_img() {
            return saa_img;
        }

        public void setSaa_img(String saa_img) {
            this.saa_img = saa_img;
        }

        public String getSaa_label() {
            return saa_label;
        }

        public void setSaa_label(String saa_label) {
            this.saa_label = saa_label;
        }
    }
}
