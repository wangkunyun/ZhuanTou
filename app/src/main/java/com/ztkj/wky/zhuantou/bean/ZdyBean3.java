package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class ZdyBean3 {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * elogo : 快递服务
         */

        private String elogo;

        public String getElogo() {
            return elogo;
        }

        public void setElogo(String elogo) {
            this.elogo = elogo;
        }
    }
}
