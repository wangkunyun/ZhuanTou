package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class AddressBookBean {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"uid":"18","name":"王晓秋","phone":"18701165198","head":"0","position":"","cname":"北京砖头智能科技有限公司","types":0},{"uid":"16","name":"韩震","phone":"18801202915","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-20/5d5b57f8d0cb4.png","position":"","cname":"北京砖头智能科技有限公司","types":0},{"uid":"1","name":"杰哥","phone":"15313947598","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-22/5d5e2ba69a7e9.png","position":"","cname":"北京砖头智能科技有限公司","types":0},{"uid":"14","name":"辛文洋","phone":"13661150710","head":"0","position":"","cname":"北京砖头智能科技有限公司","types":0},{"uid":"13","name":"郑总","phone":"18618189090","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-01/5d42ba32f011d.png","position":"","cname":"北京砖头智能科技有限公司","types":0},{"uid":"12","name":"吕松松","phone":"13460720766","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-20/5d5bc4753b86e.png","position":"","cname":"北京砖头智能科技有限公司","types":0},{"uid":"11","name":"大大云","phone":"15721695007","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-29/5d674b517034c.png","position":"","cname":"北京砖头智能科技有限公司","types":0},{"uid":"19","name":"曲义","phone":"15584339464","head":"0","position":"","cname":"北京砖头智能科技有限公司","types":0},{"uid":"20","name":"王坤云","phone":"13681413195","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-20/5d5b9f8dc67be.png","position":"","cname":"北京砖头智能科技有限公司","types":0}]
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
         * uid : 18
         * name : 王晓秋
         * phone : 18701165198
         * head : 0
         * position :
         * cname : 北京砖头智能科技有限公司
         * types : 0
         */

        private String uid;
        private String name;
        private String phone;
        private String head;
        private String position;
        private String cname;
        private int types;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public int getTypes() {
            return types;
        }

        public void setTypes(int types) {
            this.types = types;
        }
    }
}
