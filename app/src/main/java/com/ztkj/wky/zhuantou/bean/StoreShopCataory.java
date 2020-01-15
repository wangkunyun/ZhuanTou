package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class StoreShopCataory implements Serializable {


    /**
     * errno : 200
     * errmsg : 请求成功
     * data : [{"ssc_id":"87","ssc_name":"手机","childdata":[{"ssc_id":"88","ssc_name":"华为"},{"ssc_id":"89","ssc_name":"1加"}]},{"ssc_id":"79","ssc_name":"手表","childdata":[{"ssc_id":"80","ssc_name":"87575"},{"ssc_id":"81","ssc_name":"459"}]},{"ssc_id":"75","ssc_name":"食物","childdata":[{"ssc_id":"76","ssc_name":"5457854"},{"ssc_id":"77","ssc_name":"5755447"},{"ssc_id":"78","ssc_name":"44"},{"ssc_id":"84","ssc_name":"6666"}]},{"ssc_id":"70","ssc_name":"衣服","childdata":[{"ssc_id":"71","ssc_name":"5654"},{"ssc_id":"72","ssc_name":"6556"},{"ssc_id":"73","ssc_name":"32322"},{"ssc_id":"74","ssc_name":"45544"},{"ssc_id":"82","ssc_name":"99999"},{"ssc_id":"83","ssc_name":"10000"}]}]
     * info : {"ss_name":"小砖科技","ss_logo":"http://shopadmin.zhuantoukj.com/Public/Uploads/2020-01-03/5e0e9df83501c.jpg","ss_id":"1"}
     */

    private int errno;
    private String errmsg;
    private InfoBean info;
    private List<DataBean> data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class InfoBean {
        /**
         * ss_name : 小砖科技
         * ss_logo : http://shopadmin.zhuantoukj.com/Public/Uploads/2020-01-03/5e0e9df83501c.jpg
         * ss_id : 1
         */

        private String ss_name;
        private String ss_logo;
        private String ss_id;

        public String getSs_name() {
            return ss_name;
        }

        public void setSs_name(String ss_name) {
            this.ss_name = ss_name;
        }

        public String getSs_logo() {
            return ss_logo;
        }

        public void setSs_logo(String ss_logo) {
            this.ss_logo = ss_logo;
        }

        public String getSs_id() {
            return ss_id;
        }

        public void setSs_id(String ss_id) {
            this.ss_id = ss_id;
        }
    }

    public static class DataBean {
        /**
         * ssc_id : 87
         * ssc_name : 手机
         * childdata : [{"ssc_id":"88","ssc_name":"华为"},{"ssc_id":"89","ssc_name":"1加"}]
         */

        private String ssc_id;
        private String ssc_name;
        private List<ChilddataBean> childdata;

        public String getSsc_id() {
            return ssc_id;
        }

        public void setSsc_id(String ssc_id) {
            this.ssc_id = ssc_id;
        }

        public String getSsc_name() {
            return ssc_name;
        }

        public void setSsc_name(String ssc_name) {
            this.ssc_name = ssc_name;
        }

        public List<ChilddataBean> getChilddata() {
            return childdata;
        }

        public void setChilddata(List<ChilddataBean> childdata) {
            this.childdata = childdata;
        }

        public static class ChilddataBean {
            /**
             * ssc_id : 88
             * ssc_name : 华为
             */

            private String ssc_id;
            private String ssc_name;

            public String getSsc_id() {
                return ssc_id;
            }

            public void setSsc_id(String ssc_id) {
                this.ssc_id = ssc_id;
            }

            public String getSsc_name() {
                return ssc_name;
            }

            public void setSsc_name(String ssc_name) {
                this.ssc_name = ssc_name;
            }
        }
    }
}
