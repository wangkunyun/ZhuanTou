package com.ztkj.wky.zhuantou.bean;

public class CommentBean {

    /**
     * errno : 200
     * errmsg : success
     * data : {"addNumber":"1"}
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
         * addNumber : 1
         */

        private String addNumber;

        public String getAddNumber() {
            return addNumber;
        }

        public void setAddNumber(String addNumber) {
            this.addNumber = addNumber;
        }
    }
}
