package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class ShopCatatoryBean implements Serializable {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"sc_id":"1","sc_name":"零食","arr":[{"sc_id":"6","sc_name":"小吃"},{"sc_id":"12","sc_name":"辣条"}]},{"sc_id":"2","sc_name":"文具","arr":[{"sc_id":"7","sc_name":"铅笔"}]},{"sc_id":"3","sc_name":"手机","arr":[{"sc_id":"8","sc_name":"华为"},{"sc_id":"9","sc_name":"苹果"}]},{"sc_id":"4","sc_name":"数码","arr":[{"sc_id":"10","sc_name":"新品"},{"sc_id":"11","sc_name":"摄像机"}]},{"sc_id":"5","sc_name":"ins","arr":[]}]
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
         * sc_id : 1
         * sc_name : 零食
         * arr : [{"sc_id":"6","sc_name":"小吃"},{"sc_id":"12","sc_name":"辣条"}]
         */

        private String sc_id;
        private String sc_name;
        private List<ArrBean> arr;

        public String getSc_id() {
            return sc_id;
        }

        public void setSc_id(String sc_id) {
            this.sc_id = sc_id;
        }

        public String getSc_name() {
            return sc_name;
        }

        public void setSc_name(String sc_name) {
            this.sc_name = sc_name;
        }

        public List<ArrBean> getArr() {
            return arr;
        }

        public void setArr(List<ArrBean> arr) {
            this.arr = arr;
        }

        public static class ArrBean {
            /**
             * sc_id : 6
             * sc_name : 小吃
             */

            private String sc_id;
            private String sc_name;

            public String getSc_id() {
                return sc_id;
            }

            public void setSc_id(String sc_id) {
                this.sc_id = sc_id;
            }

            public String getSc_name() {
                return sc_name;
            }

            public void setSc_name(String sc_name) {
                this.sc_name = sc_name;
            }
        }
    }
}
