package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class BandingBankCardBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"card_number":"6226620211604210","card_type":"借记卡","bank_name":"光大银行"}]
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
         * card_number : 6226620211604210
         * card_type : 借记卡
         * bank_name : 光大银行
         */

        private String card_number;
        private String card_type;
        private String bank_name;

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }
    }
}
