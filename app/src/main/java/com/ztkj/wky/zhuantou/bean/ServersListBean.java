package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class ServersListBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"eid":"92","ename":"租赁","elogo":"https://pc.zhuantoukj.com/roombalimg/2019/07/23img_0WHHDMW0aXM3d2ch7AIFD5qF.png"},{"eid":"94","ename":"送水","elogo":"https://pc.zhuantoukj.com/roombalimg/2019/07/23img_YZJQjTQNrOBoMXVRb1N8Kqv6.png"},{"eid":"96","ename":"快递","elogo":"https://pc.zhuantoukj.com/roombalimg/2019/07/23img_DKNMeN1xdSeRx7m5ch2qcvAt.png"},{"eid":"98","ename":"维修","elogo":"https://pc.zhuantoukj.com/roombalimg/2019/07/23img_uhN3L7j5hVhlCZXZxfYy0Fc4.png"},{"eid":"100","ename":"采购","elogo":"https://pc.zhuantoukj.com/roombalimg/2019/07/23img_DKNMeN1xdSeRx7m5ch2qcvAt.png"},{"eid":"103","ename":"家具","elogo":"https://pc.zhuantoukj.com/roombalimg/2019/07/23img_0WHHDMW0aXM3d2ch7AIFD5qF.png"},{"eid":"105","ename":"代办","elogo":"https://pc.zhuantoukj.com/roombalimg/2019/07/23img_uhN3L7j5hVhlCZXZxfYy0Fc4.png"},{"eid":"106","ename":"保洁","elogo":"https://pc.zhuantoukj.com/roombalimg/2019/07/23img_YZJQjTQNrOBoMXVRb1N8Kqv6.png"}]
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
         * eid : 92
         * ename : 租赁
         * elogo : https://pc.zhuantoukj.com/roombalimg/2019/07/23img_0WHHDMW0aXM3d2ch7AIFD5qF.png
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
