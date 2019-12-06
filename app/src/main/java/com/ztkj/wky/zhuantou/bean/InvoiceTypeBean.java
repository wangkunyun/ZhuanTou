package com.ztkj.wky.zhuantou.bean;

public class InvoiceTypeBean {

    /**
     * errno : 200
     * errmsg : 查询成功
     * data : {"type":"0","si_enterprise_name":"北京砖头智能科技有限公司","si_enterprise_tax_number":"123456789123456","si_invoice_amount":"150","si_ticket_collection_box":"","si_addtime":"1970.01.01"}
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
         * type : 0
         * si_enterprise_name : 北京砖头智能科技有限公司
         * si_enterprise_tax_number : 123456789123456
         * si_invoice_amount : 150
         * si_ticket_collection_box :
         * si_addtime : 1970.01.01
         */

        private String type;
        private String si_enterprise_name;
        private String si_enterprise_tax_number;
        private String si_invoice_amount;
        private String si_ticket_collection_box;
        private String si_addtime;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSi_enterprise_name() {
            return si_enterprise_name;
        }

        public void setSi_enterprise_name(String si_enterprise_name) {
            this.si_enterprise_name = si_enterprise_name;
        }

        public String getSi_enterprise_tax_number() {
            return si_enterprise_tax_number;
        }

        public void setSi_enterprise_tax_number(String si_enterprise_tax_number) {
            this.si_enterprise_tax_number = si_enterprise_tax_number;
        }

        public String getSi_invoice_amount() {
            return si_invoice_amount;
        }

        public void setSi_invoice_amount(String si_invoice_amount) {
            this.si_invoice_amount = si_invoice_amount;
        }

        public String getSi_ticket_collection_box() {
            return si_ticket_collection_box;
        }

        public void setSi_ticket_collection_box(String si_ticket_collection_box) {
            this.si_ticket_collection_box = si_ticket_collection_box;
        }

        public String getSi_addtime() {
            return si_addtime;
        }

        public void setSi_addtime(String si_addtime) {
            this.si_addtime = si_addtime;
        }
    }
}
