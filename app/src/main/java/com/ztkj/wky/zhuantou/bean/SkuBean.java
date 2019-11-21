package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class SkuBean implements Serializable {


    /**
     * errno : 200
     * errmsg : 请求成功
     * data : [{"sk_id":"1","sk_name":"颜色","sk_arr":[{"sk_id":"9","sk_name":"蓝黑","sk_price":"0.00","sk_stock":"0"},{"sk_id":"2","sk_name":"黑色","sk_price":"0.00","sk_stock":"0"}]},{"sk_id":"3","sk_name":"材质","sk_arr":[{"sk_id":"10","sk_name":"不锈钢","sk_price":"0.00","sk_stock":"0"},{"sk_id":"11","sk_name":"铁","sk_price":"0.00","sk_stock":"0"},{"sk_id":"18","sk_name":"不锈钢","sk_price":"0.00","sk_stock":"0"},{"sk_id":"19","sk_name":"铁","sk_price":"0.00","sk_stock":"0"}]},{"sk_id":"12","sk_name":"尺寸","sk_arr":[{"sk_id":"13","sk_name":"5.6英寸","sk_price":"150.00","sk_stock":"15"},{"sk_id":"14","sk_name":"6.5英寸","sk_price":"100.00","sk_stock":"20"},{"sk_id":"17","sk_name":"8.0英寸","sk_price":"30.00","sk_stock":"0"},{"sk_id":"20","sk_name":"5.6英寸","sk_price":"123.00","sk_stock":"10"},{"sk_id":"21","sk_name":"6.5英寸","sk_price":"99.00","sk_stock":"13"},{"sk_id":"22","sk_name":"8.0英寸","sk_price":"50.00","sk_stock":"100"},{"sk_id":"23","sk_name":"5.6英寸","sk_price":"12.00","sk_stock":"0"},{"sk_id":"24","sk_name":"6.5英寸","sk_price":"306.00","sk_stock":"1"},{"sk_id":"25","sk_name":"8.0英寸","sk_price":"512.00","sk_stock":"10"},{"sk_id":"26","sk_name":"5.6英寸","sk_price":"122.12","sk_stock":"123"},{"sk_id":"27","sk_name":"6.5英寸","sk_price":"12.12","sk_stock":"12"},{"sk_id":"28","sk_name":"8.0英寸","sk_price":"58.90","sk_stock":"0"}]},{"sk_id":"30","sk_name":"测试四层","sk_arr":[{"sk_id":"31","sk_name":"测试四层1","sk_price":"0.00","sk_stock":"0"}]},{"sk_id":"32","sk_name":"测试五层","sk_arr":[{"sk_id":"33","sk_name":"测试五层1","sk_price":"0.00","sk_stock":"0"}]}]
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
         * sk_id : 1
         * sk_name : 颜色
         * sk_arr : [{"sk_id":"9","sk_name":"蓝黑","sk_price":"0.00","sk_stock":"0"},{"sk_id":"2","sk_name":"黑色","sk_price":"0.00","sk_stock":"0"}]
         */

        private String sk_id;
        private String sk_name;
        private List<SkArrBean> sk_arr;

        public String getSk_id() {
            return sk_id;
        }

        public void setSk_id(String sk_id) {
            this.sk_id = sk_id;
        }

        public String getSk_name() {
            return sk_name;
        }

        public void setSk_name(String sk_name) {
            this.sk_name = sk_name;
        }

        public List<SkArrBean> getSk_arr() {
            return sk_arr;
        }

        public void setSk_arr(List<SkArrBean> sk_arr) {
            this.sk_arr = sk_arr;
        }

        public static class SkArrBean {
            @Override
            public String toString() {
                return "SkArrBean{" +
                        "sk_id='" + sk_id + '\'' +
                        ", sk_name='" + sk_name + '\'' +
                        ", sk_price='" + sk_price + '\'' +
                        ", sk_stock='" + sk_stock + '\'' +
                        '}';
            }

            /**
             * sk_id : 9
             * sk_name : 蓝黑
             * sk_price : 0.00
             * sk_stock : 0
             */

            private String sk_id;
            private String sk_name;
            private String sk_price;
            private String sk_stock;

            public String getSk_id() {
                return sk_id;
            }

            public void setSk_id(String sk_id) {
                this.sk_id = sk_id;
            }

            public String getSk_name() {
                return sk_name;
            }

            public void setSk_name(String sk_name) {
                this.sk_name = sk_name;
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
