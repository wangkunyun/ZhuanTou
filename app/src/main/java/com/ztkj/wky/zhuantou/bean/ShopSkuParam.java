package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class ShopSkuParam implements Serializable {


    /**
     * errno : 200
     * errmsg : 请求成功
     * data : [[{"name":"颜色","arr":{"sk_name":"蓝黑","sk_id":"9","sk_price":"0.00","sk_stock":"54"}},{"name":"材质","arr":{"sk_name":"不锈钢","sk_id":"10","sk_price":"0.00","sk_stock":"58","sk_e_parentid":"3"}},{"name":"尺寸","arr":{"sk_name":"5.6英寸","sk_id":"13","sk_price":"150.00","sk_stock":"63","sk_e_parentid":"12"}}],[{"name":"颜色","arr":{"sk_name":"蓝黑","sk_id":"9","sk_price":"0.00","sk_stock":"54"}},{"name":"材质","arr":{"sk_name":"不锈钢","sk_id":"10","sk_price":"0.00","sk_stock":"58","sk_e_parentid":"3"}},{"name":"尺寸","arr":{"sk_name":"6.5英寸","sk_id":"14","sk_price":"100.00","sk_stock":"196","sk_e_parentid":"12"}}],[{"name":"颜色","arr":{"sk_name":"蓝黑","sk_id":"9","sk_price":"0.00","sk_stock":"54"}},{"name":"材质","arr":{"sk_name":"不锈钢","sk_id":"10","sk_price":"0.00","sk_stock":"58","sk_e_parentid":"3"}},{"name":"尺寸","arr":{"sk_name":"8.0英寸","sk_id":"17","sk_price":"30.00","sk_stock":"199","sk_e_parentid":"12"}}],[{"name":"颜色","arr":{"sk_name":"蓝黑","sk_id":"9","sk_price":"0.00","sk_stock":"54"}},{"name":"材质","arr":{"sk_name":"铁","sk_id":"11","sk_price":"0.00","sk_stock":"196","sk_e_parentid":"3"}},{"name":"尺寸","arr":{"sk_name":"5.6英寸","sk_id":"23","sk_price":"12.00","sk_stock":"200","sk_e_parentid":"12"}}],[{"name":"颜色","arr":{"sk_name":"蓝黑","sk_id":"9","sk_price":"0.00","sk_stock":"54"}},{"name":"材质","arr":{"sk_name":"铁","sk_id":"11","sk_price":"0.00","sk_stock":"196","sk_e_parentid":"3"}},{"name":"尺寸","arr":{"sk_name":"6.5英寸","sk_id":"24","sk_price":"306.00","sk_stock":"196","sk_e_parentid":"12"}}],[{"name":"颜色","arr":{"sk_name":"蓝黑","sk_id":"9","sk_price":"0.00","sk_stock":"54"}},{"name":"材质","arr":{"sk_name":"铁","sk_id":"11","sk_price":"0.00","sk_stock":"196","sk_e_parentid":"3"}},{"name":"尺寸","arr":{"sk_name":"8.0英寸","sk_id":"25","sk_price":"512.00","sk_stock":"200","sk_e_parentid":"12"}}],[{"name":"颜色","arr":{"sk_name":"黑色","sk_id":"2","sk_price":"0.00","sk_stock":"177"}},{"name":"材质","arr":{"sk_name":"不锈钢","sk_id":"18","sk_price":"0.00","sk_stock":"167","sk_e_parentid":"3"}},{"name":"尺寸","arr":{"sk_name":"5.6英寸","sk_id":"26","sk_price":"122.12","sk_stock":"192","sk_e_parentid":"12"}}],[{"name":"颜色","arr":{"sk_name":"黑色","sk_id":"2","sk_price":"0.00","sk_stock":"177"}},{"name":"材质","arr":{"sk_name":"不锈钢","sk_id":"18","sk_price":"0.00","sk_stock":"167","sk_e_parentid":"3"}},{"name":"尺寸","arr":{"sk_name":"6.5英寸","sk_id":"27","sk_price":"12.12","sk_stock":"186","sk_e_parentid":"12"}}],[{"name":"颜色","arr":{"sk_name":"黑色","sk_id":"2","sk_price":"0.00","sk_stock":"177"}},{"name":"材质","arr":{"sk_name":"不锈钢","sk_id":"18","sk_price":"0.00","sk_stock":"167","sk_e_parentid":"3"}},{"name":"尺寸","arr":{"sk_name":"8.0英寸","sk_id":"28","sk_price":"58.90","sk_stock":"189","sk_e_parentid":"12"}}],[{"name":"颜色","arr":{"sk_name":"黑色","sk_id":"2","sk_price":"0.00","sk_stock":"177"}},{"name":"材质","arr":{"sk_name":"铁","sk_id":"19","sk_price":"0.00","sk_stock":"151","sk_e_parentid":"3"}},{"name":"尺寸","arr":{"sk_name":"5.6英寸","sk_id":"20","sk_price":"123.00","sk_stock":"200","sk_e_parentid":"12"}}],[{"name":"颜色","arr":{"sk_name":"黑色","sk_id":"2","sk_price":"0.00","sk_stock":"177"}},{"name":"材质","arr":{"sk_name":"铁","sk_id":"19","sk_price":"0.00","sk_stock":"151","sk_e_parentid":"3"}},{"name":"尺寸","arr":{"sk_name":"6.5英寸","sk_id":"21","sk_price":"99.00","sk_stock":"171","sk_e_parentid":"12"}}],[{"name":"颜色","arr":{"sk_name":"黑色","sk_id":"2","sk_price":"0.00","sk_stock":"177"}},{"name":"材质","arr":{"sk_name":"铁","sk_id":"19","sk_price":"0.00","sk_stock":"151","sk_e_parentid":"3"}},{"name":"尺寸","arr":{"sk_name":"8.0英寸","sk_id":"22","sk_price":"50.00","sk_stock":"180","sk_e_parentid":"12"}}]]
     */

    private String errno;
    private String errmsg;
    private List<List<DataBean>> data;

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

    public List<List<DataBean>> getData() {
        return data;
    }

    public void setData(List<List<DataBean>> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 颜色
         * arr : {"sk_name":"蓝黑","sk_id":"9","sk_price":"0.00","sk_stock":"54"}
         */

        private String name;
        private ArrBean arr;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrBean getArr() {
            return arr;
        }

        public void setArr(ArrBean arr) {
            this.arr = arr;
        }

        public static class ArrBean {
            /**
             * sk_name : 蓝黑
             * sk_id : 9
             * sk_price : 0.00
             * sk_stock : 54
             */

            private String sk_name;
            private String sk_id;
            private String sk_price;
            private String sk_stock;

            public String getSk_name() {
                return sk_name;
            }

            public void setSk_name(String sk_name) {
                this.sk_name = sk_name;
            }

            public String getSk_id() {
                return sk_id;
            }

            public void setSk_id(String sk_id) {
                this.sk_id = sk_id;
            }

            public String getSk_price() {
                return sk_price;
            }

            public void setSk_price(String sk_price) {
                this.sk_price = sk_price;
            }

            public String getSk_stock() {
                return sk_stock;
            }

            public void setSk_stock(String sk_stock) {
                this.sk_stock = sk_stock;
            }
        }
    }
}
