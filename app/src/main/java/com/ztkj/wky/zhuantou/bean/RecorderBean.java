package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class RecorderBean implements Serializable {


    /**
     * errno : 200
     * errmsg : 请求成功
     * data : [{"time":"2019-12-02","arr":[{"sf_id":"6","sf_time":"1575267138","sf_addtime":"2019-12-02","sf_trade_name":"121221213123","sf_trade_img":"1212213213","sf_trade_price":"99999999.99","sf_commodity_id":"36","sf_user_id":"1"},{"sf_id":"5","sf_time":"1575267134","sf_addtime":"2019-12-02","sf_trade_name":"121221213123","sf_trade_img":"1212213213","sf_trade_price":"99999999.99","sf_commodity_id":"44","sf_user_id":"1"},{"sf_id":"4","sf_time":"1575267130","sf_addtime":"2019-12-02","sf_trade_name":"121221213123","sf_trade_img":"1212213213","sf_trade_price":"99999999.99","sf_commodity_id":"2","sf_user_id":"1"},{"sf_id":"3","sf_time":"1575267123","sf_addtime":"2019-12-02","sf_trade_name":"121221213123","sf_trade_img":"1212213213","sf_trade_price":"12213213.00","sf_commodity_id":"121","sf_user_id":"1"},{"sf_id":"2","sf_time":"1575267118","sf_addtime":"2019-12-02","sf_trade_name":"121221213","sf_trade_img":"1212213","sf_trade_price":"12213.00","sf_commodity_id":"12","sf_user_id":"1"},{"sf_id":"1","sf_time":"1575267112","sf_addtime":"2019-12-02","sf_trade_name":"1212","sf_trade_img":"1212","sf_trade_price":"12.00","sf_commodity_id":"1","sf_user_id":"1"}]}]
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

    public static class DataBean implements Serializable{
        /**
         * time : 2019-12-02
         * arr : [{"sf_id":"6","sf_time":"1575267138","sf_addtime":"2019-12-02","sf_trade_name":"121221213123","sf_trade_img":"1212213213","sf_trade_price":"99999999.99","sf_commodity_id":"36","sf_user_id":"1"},{"sf_id":"5","sf_time":"1575267134","sf_addtime":"2019-12-02","sf_trade_name":"121221213123","sf_trade_img":"1212213213","sf_trade_price":"99999999.99","sf_commodity_id":"44","sf_user_id":"1"},{"sf_id":"4","sf_time":"1575267130","sf_addtime":"2019-12-02","sf_trade_name":"121221213123","sf_trade_img":"1212213213","sf_trade_price":"99999999.99","sf_commodity_id":"2","sf_user_id":"1"},{"sf_id":"3","sf_time":"1575267123","sf_addtime":"2019-12-02","sf_trade_name":"121221213123","sf_trade_img":"1212213213","sf_trade_price":"12213213.00","sf_commodity_id":"121","sf_user_id":"1"},{"sf_id":"2","sf_time":"1575267118","sf_addtime":"2019-12-02","sf_trade_name":"121221213","sf_trade_img":"1212213","sf_trade_price":"12213.00","sf_commodity_id":"12","sf_user_id":"1"},{"sf_id":"1","sf_time":"1575267112","sf_addtime":"2019-12-02","sf_trade_name":"1212","sf_trade_img":"1212","sf_trade_price":"12.00","sf_commodity_id":"1","sf_user_id":"1"}]
         */

        private String time;
        private List<ArrBean> arr;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<ArrBean> getArr() {
            return arr;
        }

        public void setArr(List<ArrBean> arr) {
            this.arr = arr;
        }

        public static class ArrBean implements Serializable{
            /**
             * sf_id : 6
             * sf_time : 1575267138
             * sf_addtime : 2019-12-02
             * sf_trade_name : 121221213123
             * sf_trade_img : 1212213213
             * sf_trade_price : 99999999.99
             * sf_commodity_id : 36
             * sf_user_id : 1
             */

            private String sf_id;
            private String sf_time;
            private String sf_addtime;
            private String sf_trade_name;
            private String sf_trade_img;
            private String sf_trade_price;
            private String sf_commodity_id;
            private String sf_user_id;

            public String getSf_id() {
                return sf_id;
            }

            public void setSf_id(String sf_id) {
                this.sf_id = sf_id;
            }

            public String getSf_time() {
                return sf_time;
            }

            public void setSf_time(String sf_time) {
                this.sf_time = sf_time;
            }

            public String getSf_addtime() {
                return sf_addtime;
            }

            public void setSf_addtime(String sf_addtime) {
                this.sf_addtime = sf_addtime;
            }

            public String getSf_trade_name() {
                return sf_trade_name;
            }

            public void setSf_trade_name(String sf_trade_name) {
                this.sf_trade_name = sf_trade_name;
            }

            public String getSf_trade_img() {
                return sf_trade_img;
            }

            public void setSf_trade_img(String sf_trade_img) {
                this.sf_trade_img = sf_trade_img;
            }

            public String getSf_trade_price() {
                return sf_trade_price;
            }

            public void setSf_trade_price(String sf_trade_price) {
                this.sf_trade_price = sf_trade_price;
            }

            public String getSf_commodity_id() {
                return sf_commodity_id;
            }

            public void setSf_commodity_id(String sf_commodity_id) {
                this.sf_commodity_id = sf_commodity_id;
            }

            public String getSf_user_id() {
                return sf_user_id;
            }

            public void setSf_user_id(String sf_user_id) {
                this.sf_user_id = sf_user_id;
            }
        }
    }
}
