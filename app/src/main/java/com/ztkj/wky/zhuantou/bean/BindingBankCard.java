package com.ztkj.wky.zhuantou.bean;

public class BindingBankCard {

    /**
     * errno : 201
     * errmsg : 已经绑定
     * data : {"bank_url":"http://www.cebbank.com","bank_name":"光大银行","bank_id":"03030000","card_name":"阳光卡(银联卡)","card_type":"借记卡","bank_phone":"95595","bank_logo":"http://img.hotp.cn/8cb996.png"}
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
         * bank_url : http://www.cebbank.com
         * bank_name : 光大银行
         * bank_id : 03030000
         * card_name : 阳光卡(银联卡)
         * card_type : 借记卡
         * bank_phone : 95595
         * bank_logo : http://img.hotp.cn/8cb996.png
         */

        private String bank_url;
        private String bank_name;
        private String bank_id;
        private String card_name;
        private String card_type;
        private String bank_phone;
        private String bank_logo;

        public String getBank_url() {
            return bank_url;
        }

        public void setBank_url(String bank_url) {
            this.bank_url = bank_url;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_id() {
            return bank_id;
        }

        public void setBank_id(String bank_id) {
            this.bank_id = bank_id;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public String getBank_phone() {
            return bank_phone;
        }

        public void setBank_phone(String bank_phone) {
            this.bank_phone = bank_phone;
        }

        public String getBank_logo() {
            return bank_logo;
        }

        public void setBank_logo(String bank_logo) {
            this.bank_logo = bank_logo;
        }
    }
}
