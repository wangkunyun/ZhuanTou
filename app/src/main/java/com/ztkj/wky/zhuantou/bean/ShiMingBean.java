package com.ztkj.wky.zhuantou.bean;

public class ShiMingBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"fronts":"1212123","backs":"1212123","username":"121212","IDnumber":"121212"}
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
         * fronts : 1212123
         * backs : 1212123
         * username : 121212
         * IDnumber : 121212
         */

        private String fronts;
        private String backs;
        private String username;
        private String IDnumber;

        public String getFronts() {
            return fronts;
        }

        public void setFronts(String fronts) {
            this.fronts = fronts;
        }

        public String getBacks() {
            return backs;
        }

        public void setBacks(String backs) {
            this.backs = backs;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getIDnumber() {
            return IDnumber;
        }

        public void setIDnumber(String IDnumber) {
            this.IDnumber = IDnumber;
        }
    }
}
