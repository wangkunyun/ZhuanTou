package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class ShopSearchBean implements Serializable {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"commodity":[{"sc_id":"8","sc_name":"【最高优惠300】Huawei/华为 nova 4 自拍极点全面屏超广角三摄正品易烊千玺代言智能手机","sc_img":"https://img.alicdn.com/imgextra/i2/2206495733033/O1CN01qtNTyI1YH9pq9E6EI_!!2206495733033.jpg_430x430q90.jpg","sc_original_price":"2078.00","sc_present_price":"1568.00"},{"sc_id":"7","sc_name":"新品当天发【6期免息】Huawei/华为 畅享10 plus 官方旗舰店正品畅想9plus官网直降荣耀9x全网通5g手机mate30","sc_img":"https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i2/2201793785103/O1CN01Em88341nZDgSHKp5I_!!2201793785103.jpg_430x430q90.jpg","sc_original_price":"2009.01","sc_present_price":"1199.01"},{"sc_id":"6","sc_name":"【最高优惠300】Huawei/华为 nova 4 自拍极点全面屏超广角三摄正品易烊千玺代言智能手机","sc_img":"https://img.alicdn.com/imgextra/i2/2206495733033/O1CN01qtNTyI1YH9pq9E6EI_!!2206495733033.jpg_430x430q90.jpg","sc_original_price":"2078.00","sc_present_price":"1568.00"},{"sc_id":"5","sc_name":"新品当天发【6期免息】Huawei/华为 畅享10 plus 官方旗舰店正品畅想9plus官网直降荣耀9x全网通5g手机mate30","sc_img":"https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i2/2201793785103/O1CN01Em88341nZDgSHKp5I_!!2201793785103.jpg_430x430q90.jpg","sc_original_price":"2009.01","sc_present_price":"1199.01"}],"brandList":[]}
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

    public static class DataBean implements Serializable{
        private List<CommodityBean> commodity;
        private List<?> brandList;

        public List<CommodityBean> getCommodity() {
            return commodity;
        }

        public void setCommodity(List<CommodityBean> commodity) {
            this.commodity = commodity;
        }

        public List<?> getBrandList() {
            return brandList;
        }

        public void setBrandList(List<?> brandList) {
            this.brandList = brandList;
        }

        public static class CommodityBean implements Serializable{
            /**
             * sc_id : 8
             * sc_name : 【最高优惠300】Huawei/华为 nova 4 自拍极点全面屏超广角三摄正品易烊千玺代言智能手机
             * sc_img : https://img.alicdn.com/imgextra/i2/2206495733033/O1CN01qtNTyI1YH9pq9E6EI_!!2206495733033.jpg_430x430q90.jpg
             * sc_original_price : 2078.00
             * sc_present_price : 1568.00
             */

            private String sc_id;
            private String sc_name;
            private String sc_img;
            private String sc_original_price;
            private String sc_present_price;

            public String getSc_id() {
                return sc_id;
            }

            public void setSc_id(String sc_id) {
                this.sc_id = sc_id;
            }

            public String getSc_name() {
                return sc_name;
            }

            public void setSc_name(String sc_name) {
                this.sc_name = sc_name;
            }

            public String getSc_img() {
                return sc_img;
            }

            public void setSc_img(String sc_img) {
                this.sc_img = sc_img;
            }

            public String getSc_original_price() {
                return sc_original_price;
            }

            public void setSc_original_price(String sc_original_price) {
                this.sc_original_price = sc_original_price;
            }

            public String getSc_present_price() {
                return sc_present_price;
            }

            public void setSc_present_price(String sc_present_price) {
                this.sc_present_price = sc_present_price;
            }
        }
    }
}
