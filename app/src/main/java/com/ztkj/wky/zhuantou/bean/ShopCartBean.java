package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class ShopCartBean {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"ss_name":"砖头科技店铺","ss_logo":"0","arr":[{"ssc_id":"9","ssc_name":"vivo Z5x极点全面屏高通骁龙710大电池智能手机官方正品手机新品vivoz5x限量版 z3x","ssc_number":"4","ssc_unit_price":"150.00","ssc_sku_name":"蓝黑 不锈钢 5.6英寸","ssc_store_id":"1","ssc_sc_id":"1"}]}]
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
         * ss_name : 砖头科技店铺
         * ss_logo : 0
         * arr : [{"ssc_id":"9","ssc_name":"vivo Z5x极点全面屏高通骁龙710大电池智能手机官方正品手机新品vivoz5x限量版 z3x","ssc_number":"4","ssc_unit_price":"150.00","ssc_sku_name":"蓝黑 不锈钢 5.6英寸","ssc_store_id":"1","ssc_sc_id":"1"}]
         */

        private String ss_name;
        private String ss_logo;
        private boolean isSelect;
        private List<ArrBean> arr;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getSs_name() {
            return ss_name;
        }

        public void setSs_name(String ss_name) {
            this.ss_name = ss_name;
        }

        public String getSs_logo() {
            return ss_logo;
        }

        public void setSs_logo(String ss_logo) {
            this.ss_logo = ss_logo;
        }

        public List<ArrBean> getArr() {
            return arr;
        }

        public void setArr(List<ArrBean> arr) {
            this.arr = arr;
        }

        public static class ArrBean {
            /**
             * ssc_id : 9
             * ssc_name : vivo Z5x极点全面屏高通骁龙710大电池智能手机官方正品手机新品vivoz5x限量版 z3x
             * ssc_number : 4
             * ssc_unit_price : 150.00
             * ssc_sku_name : 蓝黑 不锈钢 5.6英寸
             * ssc_store_id : 1
             * ssc_sc_id : 1
             */

            private String ssc_id;
            private String ssc_name;
            private String ssc_number;
            private String ssc_unit_price;
            private String ssc_sku_name;
            private String ssc_store_id;
            private String ssc_sc_id;
            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getSsc_id() {
                return ssc_id;
            }

            public void setSsc_id(String ssc_id) {
                this.ssc_id = ssc_id;
            }

            public String getSsc_name() {
                return ssc_name;
            }

            public void setSsc_name(String ssc_name) {
                this.ssc_name = ssc_name;
            }

            public String getSsc_number() {
                return ssc_number;
            }

            public void setSsc_number(String ssc_number) {
                this.ssc_number = ssc_number;
            }

            public String getSsc_unit_price() {
                return ssc_unit_price;
            }

            public void setSsc_unit_price(String ssc_unit_price) {
                this.ssc_unit_price = ssc_unit_price;
            }

            public String getSsc_sku_name() {
                return ssc_sku_name;
            }

            public void setSsc_sku_name(String ssc_sku_name) {
                this.ssc_sku_name = ssc_sku_name;
            }

            public String getSsc_store_id() {
                return ssc_store_id;
            }

            public void setSsc_store_id(String ssc_store_id) {
                this.ssc_store_id = ssc_store_id;
            }

            public String getSsc_sc_id() {
                return ssc_sc_id;
            }

            public void setSsc_sc_id(String ssc_sc_id) {
                this.ssc_sc_id = ssc_sc_id;
            }
        }
    }
}
