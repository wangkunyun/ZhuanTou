package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class AddressListBean implements Serializable {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"sra_id":"2","sra_username":"海荣","sra_phone":"15500103041","sra_address":"北京市北京市东城区"},{"sra_id":"3","sra_username":"海海荣2荣","sra_phone":"15500103041","sra_address":"北京市北京市通州区"}]
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
         * sra_id : 2
         * sra_username : 海荣
         * sra_phone : 15500103041
         * sra_address : 北京市北京市东城区
         */

        private String sra_id;
        private String sra_username;
        private String sra_phone;
        private String sra_address;

        public String getSra_id() {
            return sra_id;
        }

        public void setSra_id(String sra_id) {
            this.sra_id = sra_id;
        }

        public String getSra_username() {
            return sra_username;
        }

        public void setSra_username(String sra_username) {
            this.sra_username = sra_username;
        }

        public String getSra_phone() {
            return sra_phone;
        }

        public void setSra_phone(String sra_phone) {
            this.sra_phone = sra_phone;
        }

        public String getSra_address() {
            return sra_address;
        }

        public void setSra_address(String sra_address) {
            this.sra_address = sra_address;
        }
    }
}
