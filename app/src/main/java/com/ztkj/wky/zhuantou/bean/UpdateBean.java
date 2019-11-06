package com.ztkj.wky.zhuantou.bean;

public class UpdateBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"android_number":"1.0.0","android_address":"http://39.97.75.100/android/app-release.apk.1"}
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
         * android_number : 1.0.0
         * android_address : http://39.97.75.100/android/app-release.apk.1
         */

        private String android_number;
        private String android_address;

        public String getAndroid_number() {
            return android_number;
        }

        public void setAndroid_number(String android_number) {
            this.android_number = android_number;
        }

        public String getAndroid_address() {
            return android_address;
        }

        public void setAndroid_address(String android_address) {
            this.android_address = android_address;
        }
    }
}
