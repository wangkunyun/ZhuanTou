package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class FangKeJiKvBean {


    /**
     * errno : 200
     * errmsg : 查询成功
     * data : [{"name":"吕松松","remarks":"朋友来访","time":"2019-07-05"},{"name":"小驴子","remarks":"朋友来访","time":"2019-07-05"}]
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
         * name : 吕松松
         * remarks : 朋友来访
         * time : 2019-07-05
         */

        private String name;
        private String remarks;
        private String time;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
