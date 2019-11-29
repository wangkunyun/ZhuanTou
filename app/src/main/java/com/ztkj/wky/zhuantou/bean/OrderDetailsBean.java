package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class OrderDetailsBean {


    /**
     * errno : 200
     * errmsg : 请求成功
     * data : {"sso_sub_order_number":"ZX83064451b71bcce40","so_addtime":"2019-11-26 10:29:23","so_state":"0","ss_name":"砖头科技店铺","ss_logo":"0","sra_username":"","sra_phone":"","sra_address":"","express":[{"time":"2019-11-25 05:24:35","context":"离开【黄村站】,下一站【北京综合邮件处理中心】","ftime":"2019-11-25 05:24:35","areaCode":"CN111515005000","areaName":"北京,大兴区,大兴区,黄村","status":"在途"},{"time":"2019-11-25 01:47:01","context":"到达【黄村站】","ftime":"2019-11-25 01:47:01","areaCode":null,"areaName":null,"status":"在途"},{"time":"2019-11-23 17:01:59","context":"离开【邮政广州广州棠溪转运站】,下一站【黄村站】","ftime":"2019-11-23 17:01:59","areaCode":"CN440100000000","areaName":"广东,广州市","status":"在途"},{"time":"2019-11-23 03:31:05","context":"到达【广州机北】","ftime":"2019-11-23 03:31:05","areaCode":"CN440100000000","areaName":"广东,广州市","status":"在途"},{"time":"2019-11-23 03:23:31","context":"离开【穗北集包中心】,下一站【广州机北】","ftime":"2019-11-23 03:23:31","areaCode":null,"areaName":null,"status":"在途"},{"time":"2019-11-23 01:31:24","context":"广州市 【穗北集包中心】已收件,揽投员:王翠玲,电话:13422316950","ftime":"2019-11-23 01:31:24","areaCode":"CN440100000000","areaName":"广东,广州市","status":"揽收"},{"time":"2019-11-22 21:04:58","context":"[穗北集包中心]客户交接,发往:广州市","ftime":"2019-11-22 21:04:58","areaCode":null,"areaName":null,"status":"在途"}],"arr":[{"sog_id":"42","sog_sub_order_id":"ZX83064451b71bcce40","sog_commodity_id":"1","sog_name":"vivo Z5x极点全面屏高通骁龙710大电池智能手机官方正品手机新品vivoz5x限量版 z3x","sog_sku_id":"1","sog_sku_name":"蓝黑 不锈钢 6.5英寸","sog_number":"1","sog_unit_price":"100.00","sog_total_price":"100.00","sc_img":"https://img.alicdn.com/imgextra/i2/883737303/O1CN012EpdOH23op2aAdpFH_!!883737303.jpg_430x430q90.jpg","sog_refund_type":"0"}]}
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
         * sso_sub_order_number : ZX83064451b71bcce40
         * so_addtime : 2019-11-26 10:29:23
         * so_state : 0
         * ss_name : 砖头科技店铺
         * ss_logo : 0
         * sra_username :
         * sra_phone :
         * sra_address :
         * express : [{"time":"2019-11-25 05:24:35","context":"离开【黄村站】,下一站【北京综合邮件处理中心】","ftime":"2019-11-25 05:24:35","areaCode":"CN111515005000","areaName":"北京,大兴区,大兴区,黄村","status":"在途"},{"time":"2019-11-25 01:47:01","context":"到达【黄村站】","ftime":"2019-11-25 01:47:01","areaCode":null,"areaName":null,"status":"在途"},{"time":"2019-11-23 17:01:59","context":"离开【邮政广州广州棠溪转运站】,下一站【黄村站】","ftime":"2019-11-23 17:01:59","areaCode":"CN440100000000","areaName":"广东,广州市","status":"在途"},{"time":"2019-11-23 03:31:05","context":"到达【广州机北】","ftime":"2019-11-23 03:31:05","areaCode":"CN440100000000","areaName":"广东,广州市","status":"在途"},{"time":"2019-11-23 03:23:31","context":"离开【穗北集包中心】,下一站【广州机北】","ftime":"2019-11-23 03:23:31","areaCode":null,"areaName":null,"status":"在途"},{"time":"2019-11-23 01:31:24","context":"广州市 【穗北集包中心】已收件,揽投员:王翠玲,电话:13422316950","ftime":"2019-11-23 01:31:24","areaCode":"CN440100000000","areaName":"广东,广州市","status":"揽收"},{"time":"2019-11-22 21:04:58","context":"[穗北集包中心]客户交接,发往:广州市","ftime":"2019-11-22 21:04:58","areaCode":null,"areaName":null,"status":"在途"}]
         * arr : [{"sog_id":"42","sog_sub_order_id":"ZX83064451b71bcce40","sog_commodity_id":"1","sog_name":"vivo Z5x极点全面屏高通骁龙710大电池智能手机官方正品手机新品vivoz5x限量版 z3x","sog_sku_id":"1","sog_sku_name":"蓝黑 不锈钢 6.5英寸","sog_number":"1","sog_unit_price":"100.00","sog_total_price":"100.00","sc_img":"https://img.alicdn.com/imgextra/i2/883737303/O1CN012EpdOH23op2aAdpFH_!!883737303.jpg_430x430q90.jpg","sog_refund_type":"0"}]
         */

        private String sso_sub_order_number;
        private String so_addtime;
        private String so_state;
        private String ss_name;
        private String ss_logo;
        private String sra_username;
        private String sra_phone;
        private String sra_address;
        private List<ExpressBean> express;
        private List<ArrBean> arr;

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

        public String getSra_username() {
            return sra_username;
        }

        public void setSra_username(String sra_username) {
            this.sra_username = sra_username;
        }

        public String getSra_phone() {
            return sra_phone;
        }

        public void setSra_phone(String sra_phone) {
            this.sra_phone = sra_phone;
        }

        public String getSra_address() {
            return sra_address;
        }

        public void setSra_address(String sra_address) {
            this.sra_address = sra_address;
        }

        public List<ExpressBean> getExpress() {
            return express;
        }

        public void setExpress(List<ExpressBean> express) {
            this.express = express;
        }

        public List<ArrBean> getArr() {
            return arr;
        }

        public void setArr(List<ArrBean> arr) {
            this.arr = arr;
        }

        public static class ExpressBean {
            /**
             * time : 2019-11-25 05:24:35
             * context : 离开【黄村站】,下一站【北京综合邮件处理中心】
             * ftime : 2019-11-25 05:24:35
             * areaCode : CN111515005000
             * areaName : 北京,大兴区,大兴区,黄村
             * status : 在途
             */

            private String time;
            private String context;
            private String ftime;
            private String areaCode;
            private String areaName;
            private String status;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }

            public String getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }

        public static class ArrBean {
            /**
             * sog_id : 42
             * sog_sub_order_id : ZX83064451b71bcce40
             * sog_commodity_id : 1
             * sog_name : vivo Z5x极点全面屏高通骁龙710大电池智能手机官方正品手机新品vivoz5x限量版 z3x
             * sog_sku_id : 1
             * sog_sku_name : 蓝黑 不锈钢 6.5英寸
             * sog_number : 1
             * sog_unit_price : 100.00
             * sog_total_price : 100.00
             * sc_img : https://img.alicdn.com/imgextra/i2/883737303/O1CN012EpdOH23op2aAdpFH_!!883737303.jpg_430x430q90.jpg
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
            private String sc_img;
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

            public String getSc_img() {
                return sc_img;
            }

            public void setSc_img(String sc_img) {
                this.sc_img = sc_img;
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
