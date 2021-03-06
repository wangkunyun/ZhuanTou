package com.ztkj.wky.zhuantou.bean;

public class DidIDBean {

    /**
     * errno : 200
     * errmsg : 获取日报id成功
     * data : {"did":"1"}
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
         * did : 1
         */

        private String did;

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }
    }
}
