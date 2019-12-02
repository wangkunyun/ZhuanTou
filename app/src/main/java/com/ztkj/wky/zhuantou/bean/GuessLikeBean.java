package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class GuessLikeBean implements Serializable {


    /**
     * errno : 200
     * errmsg : 请求成功
     * data : [{"sc_id":"2","sc_name":"【12期免息】Apple/苹果 iPhone 11 Pro MAX 2019新品 全网通手机 苹果11promax 拍照智能手机","sc_img":"https://img.alicdn.com/imgextra/i3/197232874/O1CN01RP04Ji1X6KreCJKUZ_!!197232874.jpg_430x430q90.jpg","sc_original_price":"10899.00","sc_present_price":"9599.00"},{"sc_id":"4","sc_name":"【12期免息】Apple/苹果 iPhone 11 Pro MAX 2019新品 全网通手机 苹果11promax 拍照智能手机","sc_img":"https://img.alicdn.com/imgextra/i3/197232874/O1CN01RP04Ji1X6KreCJKUZ_!!197232874.jpg_430x430q90.jpg","sc_original_price":"10899.00","sc_present_price":"9599.00"},{"sc_id":"1","sc_name":"vivo Z5x极点全面屏高通骁龙710大电池智能手机官方正品手机新品vivoz5x限量版 z3x","sc_img":"https://img.alicdn.com/imgextra/i2/883737303/O1CN012EpdOH23op2aAdpFH_!!883737303.jpg_430x430q90.jpg","sc_original_price":"1998.00","sc_present_price":"1298.00"},{"sc_id":"2","sc_name":"【12期免息】Apple/苹果 iPhone 11 Pro MAX 2019新品 全网通手机 苹果11promax 拍照智能手机","sc_img":"https://img.alicdn.com/imgextra/i3/197232874/O1CN01RP04Ji1X6KreCJKUZ_!!197232874.jpg_430x430q90.jpg","sc_original_price":"10899.00","sc_present_price":"9599.00"},{"sc_id":"3","sc_name":"vivo Z5x极点全面屏高通骁龙710大电池智能手机官方正品手机新品vivoz5x限量版 z3x","sc_img":"https://img.alicdn.com/imgextra/i2/883737303/O1CN012EpdOH23op2aAdpFH_!!883737303.jpg_430x430q90.jpg","sc_original_price":"1998.00","sc_present_price":"1298.00"},{"sc_id":"4","sc_name":"【12期免息】Apple/苹果 iPhone 11 Pro MAX 2019新品 全网通手机 苹果11promax 拍照智能手机","sc_img":"https://img.alicdn.com/imgextra/i3/197232874/O1CN01RP04Ji1X6KreCJKUZ_!!197232874.jpg_430x430q90.jpg","sc_original_price":"10899.00","sc_present_price":"9599.00"},{"sc_id":"5","sc_name":"新品当天发【6期免息】Huawei/华为 畅享10 plus 官方旗舰店正品畅想9plus官网直降荣耀9x全网通5g手机mate30","sc_img":"https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i2/2201793785103/O1CN01Em88341nZDgSHKp5I_!!2201793785103.jpg_430x430q90.jpg","sc_original_price":"2009.01","sc_present_price":"1199.01"},{"sc_id":"2","sc_name":"【12期免息】Apple/苹果 iPhone 11 Pro MAX 2019新品 全网通手机 苹果11promax 拍照智能手机","sc_img":"https://img.alicdn.com/imgextra/i3/197232874/O1CN01RP04Ji1X6KreCJKUZ_!!197232874.jpg_430x430q90.jpg","sc_original_price":"10899.00","sc_present_price":"9599.00"},{"sc_id":"4","sc_name":"【12期免息】Apple/苹果 iPhone 11 Pro MAX 2019新品 全网通手机 苹果11promax 拍照智能手机","sc_img":"https://img.alicdn.com/imgextra/i3/197232874/O1CN01RP04Ji1X6KreCJKUZ_!!197232874.jpg_430x430q90.jpg","sc_original_price":"10899.00","sc_present_price":"9599.00"},{"sc_id":"2","sc_name":"【12期免息】Apple/苹果 iPhone 11 Pro MAX 2019新品 全网通手机 苹果11promax 拍照智能手机","sc_img":"https://img.alicdn.com/imgextra/i3/197232874/O1CN01RP04Ji1X6KreCJKUZ_!!197232874.jpg_430x430q90.jpg","sc_original_price":"10899.00","sc_present_price":"9599.00"},{"sc_id":"4","sc_name":"【12期免息】Apple/苹果 iPhone 11 Pro MAX 2019新品 全网通手机 苹果11promax 拍照智能手机","sc_img":"https://img.alicdn.com/imgextra/i3/197232874/O1CN01RP04Ji1X6KreCJKUZ_!!197232874.jpg_430x430q90.jpg","sc_original_price":"10899.00","sc_present_price":"9599.00"},{"sc_id":"2","sc_name":"【12期免息】Apple/苹果 iPhone 11 Pro MAX 2019新品 全网通手机 苹果11promax 拍照智能手机","sc_img":"https://img.alicdn.com/imgextra/i3/197232874/O1CN01RP04Ji1X6KreCJKUZ_!!197232874.jpg_430x430q90.jpg","sc_original_price":"10899.00","sc_present_price":"9599.00"},{"sc_id":"4","sc_name":"【12期免息】Apple/苹果 iPhone 11 Pro MAX 2019新品 全网通手机 苹果11promax 拍照智能手机","sc_img":"https://img.alicdn.com/imgextra/i3/197232874/O1CN01RP04Ji1X6KreCJKUZ_!!197232874.jpg_430x430q90.jpg","sc_original_price":"10899.00","sc_present_price":"9599.00"}]
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
         * sc_id : 2
         * sc_name : 【12期免息】Apple/苹果 iPhone 11 Pro MAX 2019新品 全网通手机 苹果11promax 拍照智能手机
         * sc_img : https://img.alicdn.com/imgextra/i3/197232874/O1CN01RP04Ji1X6KreCJKUZ_!!197232874.jpg_430x430q90.jpg
         * sc_original_price : 10899.00
         * sc_present_price : 9599.00
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
