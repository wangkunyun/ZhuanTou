package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class RvBean3 {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"eid":"1","ename":"快递服务","elogo":"http://39.97.75.100/birck/public/anzhuo/kuaidifuwu.png"},{"eid":"2","ename":"上门租车","elogo":"http://39.97.75.100/birck/public/anzhuo/shangmenzuche.png"}]
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
         * eid : 1
         * ename : 快递服务
         * elogo : http://39.97.75.100/birck/public/anzhuo/kuaidifuwu.png
         */

        private String eid;
        private String ename;
        private String elogo;

        public String getEid() {
            return eid;
        }

        public void setEid(String eid) {
            this.eid = eid;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getElogo() {
            return elogo;
        }

        public void setElogo(String elogo) {
            this.elogo = elogo;
        }
    }
}
