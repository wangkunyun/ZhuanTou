package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class ZdyBean {

    private List<ZdyBean.DataBean> data;

    public List<ZdyBean.DataBean> getData() {
        return data;
    }

    public void setData(List<ZdyBean.DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ename : 快递服务
         */

        private String eid;

        public String getEid() {
            return eid;
        }

        public void setEid(String ename) {
            this.eid = ename;
        }
    }
}
