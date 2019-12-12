package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class NoticeBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"sn_name":"系统通知","sn_content":"小砖商城更新啦","sn_time":"1970年01月01日 08:00"},{"sn_name":"系统通知","sn_content":"小砖商城上线啦","sn_time":"1970年01月01日 08:00"}]
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
         * sn_name : 系统通知
         * sn_content : 小砖商城更新啦
         * sn_time : 1970年01月01日 08:00
         */

        private String sn_name;
        private String sn_content;
        private String sn_time;

        public String getSn_name() {
            return sn_name;
        }

        public void setSn_name(String sn_name) {
            this.sn_name = sn_name;
        }

        public String getSn_content() {
            return sn_content;
        }

        public void setSn_content(String sn_content) {
            this.sn_content = sn_content;
        }

        public String getSn_time() {
            return sn_time;
        }

        public void setSn_time(String sn_time) {
            this.sn_time = sn_time;
        }
    }
}
