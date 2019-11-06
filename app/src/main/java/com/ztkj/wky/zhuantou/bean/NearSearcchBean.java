package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class NearSearcchBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"co_id":"1","co_name":"世界侨商中心社区","co_address":"北京市通州区世界侨商中心"},{"co_id":"2","co_name":"霍营社区","co_address":"北京市昌平区霍营乡"},{"co_id":"3","co_name":"回龙观社区","co_address":"北京市昌平区回龙观"}]
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
         * co_id : 1
         * co_name : 世界侨商中心社区
         * co_address : 北京市通州区世界侨商中心
         */

        private String co_id;
        private String co_name;
        private String co_address;

        public String getCo_id() {
            return co_id;
        }

        public void setCo_id(String co_id) {
            this.co_id = co_id;
        }

        public String getCo_name() {
            return co_name;
        }

        public void setCo_name(String co_name) {
            this.co_name = co_name;
        }

        public String getCo_address() {
            return co_address;
        }

        public void setCo_address(String co_address) {
            this.co_address = co_address;
        }
    }
}
