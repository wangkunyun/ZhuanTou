package com.ztkj.wky.zhuantou.bean;

public class EwmBean {


    /**
     * errno : 200
     * errmsg : 生成二维码成功
     * data : {"code":"MzThFufaOnVt9okuN_TcloldNejRhZWVjM2MxYjA0ZDJlMjU4Yjc3ZGYxNjVlNgO0O0OO0O0O","wenan":"今日工作时长已超过50%的人","duration":"50","tips":"提示：小砖办公，体验更简洁的工作流程\n        ","f":"1","propertyPhone":"010-9876553"}
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
         * code : MzThFufaOnVt9okuN_TcloldNejRhZWVjM2MxYjA0ZDJlMjU4Yjc3ZGYxNjVlNgO0O0OO0O0O
         * wenan : 今日工作时长已超过50%的人
         * duration : 50
         * tips : 提示：小砖办公，体验更简洁的工作流程
         * f : 1
         * propertyPhone : 010-9876553
         */

        private String code;
        private String wenan;
        private String duration;
        private String tips;
        private String f;
        private String propertyPhone;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getWenan() {
            return wenan;
        }

        public void setWenan(String wenan) {
            this.wenan = wenan;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public String getF() {
            return f;
        }

        public void setF(String f) {
            this.f = f;
        }

        public String getPropertyPhone() {
            return propertyPhone;
        }

        public void setPropertyPhone(String propertyPhone) {
            this.propertyPhone = propertyPhone;
        }
    }
}
