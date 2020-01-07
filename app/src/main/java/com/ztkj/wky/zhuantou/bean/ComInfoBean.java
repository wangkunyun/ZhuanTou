package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class ComInfoBean {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"commodity_info":{"com_id":"24","com_name":"电视机台灯","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg","com_integral":"1100","com_details":"0"},"commodity_category_list":[{"cc_id":"1","name":"红色"},{"cc_id":"2","name":"红褐色"}]}
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
         * commodity_info : {"com_id":"24","com_name":"电视机台灯","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg","com_integral":"1100","com_details":"0"}
         * commodity_category_list : [{"cc_id":"1","name":"红色"},{"cc_id":"2","name":"红褐色"}]
         */

        private CommodityInfoBean commodity_info;
        private List<CommodityCategoryListBean> commodity_category_list;

        public CommodityInfoBean getCommodity_info() {
            return commodity_info;
        }

        public void setCommodity_info(CommodityInfoBean commodity_info) {
            this.commodity_info = commodity_info;
        }

        public List<CommodityCategoryListBean> getCommodity_category_list() {
            return commodity_category_list;
        }

        public void setCommodity_category_list(List<CommodityCategoryListBean> commodity_category_list) {
            this.commodity_category_list = commodity_category_list;
        }

        public static class CommodityInfoBean {
            /**
             * com_id : 24
             * com_name : 电视机台灯
             * com_cover : https://api.zhuantoukj.com/birck/Public/jifen/6.jpg
             * com_integral : 1100
             * com_details : 0
             */

            private String com_id;
            private String com_name;
            private String com_cover;
            private String com_integral;
            private String com_details;

            public String getCom_id() {
                return com_id;
            }

            public void setCom_id(String com_id) {
                this.com_id = com_id;
            }

            public String getCom_name() {
                return com_name;
            }

            public void setCom_name(String com_name) {
                this.com_name = com_name;
            }

            public String getCom_cover() {
                return com_cover;
            }

            public void setCom_cover(String com_cover) {
                this.com_cover = com_cover;
            }

            public String getCom_integral() {
                return com_integral;
            }

            public void setCom_integral(String com_integral) {
                this.com_integral = com_integral;
            }

            public String getCom_details() {
                return com_details;
            }

            public void setCom_details(String com_details) {
                this.com_details = com_details;
            }
        }

        public static class CommodityCategoryListBean {
            /**
             * cc_id : 1
             * name : 红色
             */

            private String cc_id;
            private String name;
            private String select;

            public CommodityCategoryListBean(String cc_id, String name, String select) {
                this.cc_id = cc_id;
                this.name = name;
                this.select = select;
            }

            public String getSelect() {
                return select;
            }

            public void setSelect(String select) {
                this.select = select;
            }

            public String getCc_id() {
                return cc_id;
            }

            public void setCc_id(String cc_id) {
                this.cc_id = cc_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
