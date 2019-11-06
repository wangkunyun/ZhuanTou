package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class Team_ScaleBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"id":"1","scale_type":"0-9"},{"id":"2","scale_type":"10-20"},{"id":"3","scale_type":"21-50"},{"id":"4","scale_type":"51-100"},{"id":"5","scale_type":"101-500"},{"id":"6","scale_type":"501-2000"},{"id":"7","scale_type":">2000"}]
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
         * id : 1
         * scale_type : 0-9
         */

        private String id;
        private String scale_type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getScale_type() {
            return scale_type;
        }

        public void setScale_type(String scale_type) {
            this.scale_type = scale_type;
        }
    }
}
