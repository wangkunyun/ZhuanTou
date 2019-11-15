package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class ShopCartBean {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"cl_id":"1","cl_name":"最新资讯","subordinate":[{"cl_id":"3","cl_name":"企业动态","price":"1"},{"cl_id":"4","cl_name":"职场资讯","price":"1"}]},{"cl_id":"2","cl_name":"热门话题","subordinate":[{"cl_id":"7","cl_name":"言行仪表","price":"2"},{"cl_id":"8","cl_name":"言谈举止","price":"2"}]}]
     */

    private String errno;
    private String errmsg;
    private List<DataBean> data;


    @Override
    public String toString() {
        return "ShopCartBean{" +
                "errno='" + errno + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }

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
        @Override
        public String toString() {
            return "DataBean{" +
                    "cl_id='" + cl_id + '\'' +
                    ", cl_name='" + cl_name + '\'' +
                    ", subordinate=" + subordinate +
                    '}';
        }

        /**
         * cl_id : 1
         * cl_name : 最新资讯
         * subordinate : [{"cl_id":"3","cl_name":"企业动态","price":"1"},{"cl_id":"4","cl_name":"职场资讯","price":"1"}]
         */

        private String cl_id;
        private boolean isSelect;
        private String cl_name;
        private List<SubordinateBean> subordinate;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getCl_id() {
            return cl_id;
        }

        public void setCl_id(String cl_id) {
            this.cl_id = cl_id;
        }

        public String getCl_name() {
            return cl_name;
        }

        public void setCl_name(String cl_name) {
            this.cl_name = cl_name;
        }

        public List<SubordinateBean> getSubordinate() {
            return subordinate;
        }

        public void setSubordinate(List<SubordinateBean> subordinate) {
            this.subordinate = subordinate;
        }

        public static class SubordinateBean {
            @Override
            public String toString() {
                return "SubordinateBean{" +
                        "cl_id='" + cl_id + '\'' +
                        ", cl_name='" + cl_name + '\'' +
                        ", price='" + price + '\'' +
                        '}';
            }

            /**
             * cl_id : 3
             * cl_name : 企业动态
             * price : 1
             */

            private String cl_id;
            private String cl_name;
            private String price;
            private int num;
            private boolean isSelect;


            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getCl_id() {
                return cl_id;
            }

            public void setCl_id(String cl_id) {
                this.cl_id = cl_id;
            }

            public String getCl_name() {
                return cl_name;
            }

            public void setCl_name(String cl_name) {
                this.cl_name = cl_name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
