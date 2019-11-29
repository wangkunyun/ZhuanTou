package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class OrderInfo  implements Serializable {


    /**
     * errno : 200
     * errmsg : 生成订单成功
     * data : {"order_number":"7c63c5519b2a0ea4be24681a1022ab46","arr":[{"so_order_number":"7c63c5519b2a0ea4be24681a1022ab46","sso_sub_order_number":"ZX9b2a0ea4be24681a0","so_addtime":"2019-11-28 17:40:53","so_state":"0","ss_name":"砖头科技店铺","ss_logo":"0","arr":[{"sog_id":"106","sog_sub_order_id":"ZX9b2a0ea4be24681a0","sog_commodity_id":"4","sog_name":"【12期免息】Apple/苹果 iPhone 11 Pro MAX 2019新品 全网通手机 苹果11promax 拍照智能手机","sog_sku_id":"1","sog_sku_name":"黑色 铁 8.0英寸","sog_number":"1","sog_unit_price":"50.00","sog_total_price":"50.00","sog_refund_type":"0"},{"sog_id":"107","sog_sub_order_id":"ZX9b2a0ea4be24681a0","sog_commodity_id":"1","sog_name":"vivo Z5x极点全面屏高通骁龙710大电池智能手机官方正品手机新品vivoz5x限量版 z3x","sog_sku_id":"1","sog_sku_name":"黑色 铁 8.0英寸","sog_number":"1","sog_unit_price":"50.00","sog_total_price":"50.00","sog_refund_type":"0"}]}]}
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
         * order_number : 7c63c5519b2a0ea4be24681a1022ab46
         * arr : [{"so_order_number":"7c63c5519b2a0ea4be24681a1022ab46","sso_sub_order_number":"ZX9b2a0ea4be24681a0","so_addtime":"2019-11-28 17:40:53","so_state":"0","ss_name":"砖头科技店铺","ss_logo":"0","arr":[{"sog_id":"106","sog_sub_order_id":"ZX9b2a0ea4be24681a0","sog_commodity_id":"4","sog_name":"【12期免息】Apple/苹果 iPhone 11 Pro MAX 2019新品 全网通手机 苹果11promax 拍照智能手机","sog_sku_id":"1","sog_sku_name":"黑色 铁 8.0英寸","sog_number":"1","sog_unit_price":"50.00","sog_total_price":"50.00","sog_refund_type":"0"},{"sog_id":"107","sog_sub_order_id":"ZX9b2a0ea4be24681a0","sog_commodity_id":"1","sog_name":"vivo Z5x极点全面屏高通骁龙710大电池智能手机官方正品手机新品vivoz5x限量版 z3x","sog_sku_id":"1","sog_sku_name":"黑色 铁 8.0英寸","sog_number":"1","sog_unit_price":"50.00","sog_total_price":"50.00","sog_refund_type":"0"}]}]
         */

        private String order_number;
        private List<ArrBeanX> arr;

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public List<ArrBeanX> getArr() {
            return arr;
        }

        public void setArr(List<ArrBeanX> arr) {
            this.arr = arr;
        }

        public static class ArrBeanX {
            /**
             * so_order_number : 7c63c5519b2a0ea4be24681a1022ab46
             * sso_sub_order_number : ZX9b2a0ea4be24681a0
             * so_addtime : 2019-11-28 17:40:53
             * so_state : 0
             * ss_name : 砖头科技店铺
             * ss_logo : 0
             * arr : [{"sog_id":"106","sog_sub_order_id":"ZX9b2a0ea4be24681a0","sog_commodity_id":"4","sog_name":"【12期免息】Apple/苹果 iPhone 11 Pro MAX 2019新品 全网通手机 苹果11promax 拍照智能手机","sog_sku_id":"1","sog_sku_name":"黑色 铁 8.0英寸","sog_number":"1","sog_unit_price":"50.00","sog_total_price":"50.00","sog_refund_type":"0"},{"sog_id":"107","sog_sub_order_id":"ZX9b2a0ea4be24681a0","sog_commodity_id":"1","sog_name":"vivo Z5x极点全面屏高通骁龙710大电池智能手机官方正品手机新品vivoz5x限量版 z3x","sog_sku_id":"1","sog_sku_name":"黑色 铁 8.0英寸","sog_number":"1","sog_unit_price":"50.00","sog_total_price":"50.00","sog_refund_type":"0"}]
             */

            private String so_order_number;
            private String sso_sub_order_number;
            private String so_addtime;
            private String so_state;
            private String ss_name;
            private String ss_logo;
            private List<ArrBean> arr;

            public String getSo_order_number() {
                return so_order_number;
            }

            public void setSo_order_number(String so_order_number) {
                this.so_order_number = so_order_number;
            }

            public String getSso_sub_order_number() {
                return sso_sub_order_number;
            }

            public void setSso_sub_order_number(String sso_sub_order_number) {
                this.sso_sub_order_number = sso_sub_order_number;
            }

            public String getSo_addtime() {
                return so_addtime;
            }

            public void setSo_addtime(String so_addtime) {
                this.so_addtime = so_addtime;
            }

            public String getSo_state() {
                return so_state;
            }

            public void setSo_state(String so_state) {
                this.so_state = so_state;
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
                 * sog_id : 106
                 * sog_sub_order_id : ZX9b2a0ea4be24681a0
                 * sog_commodity_id : 4
                 * sog_name : 【12期免息】Apple/苹果 iPhone 11 Pro MAX 2019新品 全网通手机 苹果11promax 拍照智能手机
                 * sog_sku_id : 1
                 * sog_sku_name : 黑色 铁 8.0英寸
                 * sog_number : 1
                 * sog_unit_price : 50.00
                 * sog_total_price : 50.00
                 * sog_refund_type : 0
                 */

                private String sog_id;
                private String sog_sub_order_id;
                private String sog_commodity_id;
                private String sog_name;
                private String sog_sku_id;
                private String sog_sku_name;
                private String sog_number;
                private String sog_unit_price;
                private String sog_total_price;
                private String sog_refund_type;

                public String getSog_id() {
                    return sog_id;
                }

                public void setSog_id(String sog_id) {
                    this.sog_id = sog_id;
                }

                public String getSog_sub_order_id() {
                    return sog_sub_order_id;
                }

                public void setSog_sub_order_id(String sog_sub_order_id) {
                    this.sog_sub_order_id = sog_sub_order_id;
                }

                public String getSog_commodity_id() {
                    return sog_commodity_id;
                }

                public void setSog_commodity_id(String sog_commodity_id) {
                    this.sog_commodity_id = sog_commodity_id;
                }

                public String getSog_name() {
                    return sog_name;
                }

                public void setSog_name(String sog_name) {
                    this.sog_name = sog_name;
                }

                public String getSog_sku_id() {
                    return sog_sku_id;
                }

                public void setSog_sku_id(String sog_sku_id) {
                    this.sog_sku_id = sog_sku_id;
                }

                public String getSog_sku_name() {
                    return sog_sku_name;
                }

                public void setSog_sku_name(String sog_sku_name) {
                    this.sog_sku_name = sog_sku_name;
                }

                public String getSog_number() {
                    return sog_number;
                }

                public void setSog_number(String sog_number) {
                    this.sog_number = sog_number;
                }

                public String getSog_unit_price() {
                    return sog_unit_price;
                }

                public void setSog_unit_price(String sog_unit_price) {
                    this.sog_unit_price = sog_unit_price;
                }

                public String getSog_total_price() {
                    return sog_total_price;
                }

                public void setSog_total_price(String sog_total_price) {
                    this.sog_total_price = sog_total_price;
                }

                public String getSog_refund_type() {
                    return sog_refund_type;
                }

                public void setSog_refund_type(String sog_refund_type) {
                    this.sog_refund_type = sog_refund_type;
                }
            }
        }
    }
}
