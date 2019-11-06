package com.ztkj.wky.zhuantou.bean;

public class WeekIDBean {

    /**
     * errno : 200
     * errmsg : 获取周报id成功
     * data : {"wid":"1"}
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
         * wid : 1
         */

        private String wid;

        public String getWid() {
            return wid;
        }

        public void setWid(String wid) {
            this.wid = wid;
        }
    }
}
