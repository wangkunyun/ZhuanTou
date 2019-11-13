package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class JsonBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * title : 原创设计女装【隐侠】秋冬新款 高领百…
         * img : https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d44029fa3c69.png
         * money : 1457
         */

        private String title;
        private String img;
        private String money;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
