package com.ztkj.wky.zhuantou.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonBean2 {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 男性服装
         * class : [{"shop":"卫衣"},{"shop":"羽绒服"},{"shop":"紧身裤"},{"shop":"豆豆鞋"}]
         */

        private String title;
        @SerializedName("class")
        private List<ClassBean> classX;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ClassBean> getClassX() {
            return classX;
        }

        public void setClassX(List<ClassBean> classX) {
            this.classX = classX;
        }

        public static class ClassBean {
            /**
             * shop : 卫衣
             */

            private String shop;

            public String getShop() {
                return shop;
            }

            public void setShop(String shop) {
                this.shop = shop;
            }
        }
    }
}
