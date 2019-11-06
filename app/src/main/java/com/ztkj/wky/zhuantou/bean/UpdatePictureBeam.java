package com.ztkj.wky.zhuantou.bean;

public class UpdatePictureBeam {


    /**
     * errno : 200
     * errmsg : 编辑成功
     * data : {"business_license":"https://api.zhuantoukj.com/birck/Public/BusinessLicense/2019-07-26/5d3ab554c24b1.png"}
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
         * business_license : https://api.zhuantoukj.com/birck/Public/BusinessLicense/2019-07-26/5d3ab554c24b1.png
         */

        private String business_license;

        public String getBusiness_license() {
            return business_license;
        }

        public void setBusiness_license(String business_license) {
            this.business_license = business_license;
        }
    }
}
