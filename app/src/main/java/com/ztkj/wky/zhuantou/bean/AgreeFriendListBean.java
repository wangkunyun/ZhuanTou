package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class AgreeFriendListBean {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"friendname":"吕松松","friendphone":"13460720766","friendhead":"https://api.zhuantoukj.com/birck/Public/heard/2019-09-03/5d6e43b4d4774.png","position":"","cname":"北京砖头智能科技有限公司","sayHello":""},{"friendname":"杰哥","friendphone":"15313947598","friendhead":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-22/5d5e2ba69a7e9.png","position":"","cname":"北京砖头智能科技有限公司"},{"friendname":"超大云","friendphone":"13681413195","friendhead":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-20/5d5b9f8dc67be.png","position":"","cname":"北京砖头智能科技有限公司"},{"friendname":"郑伟","friendphone":"18618189090","friendhead":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-01/5d42ba32f011d.png","position":"","cname":"北京砖头智能科技有限公司"},{"friendname":"韩震","friendphone":"18801202915","friendhead":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-20/5d5b57f8d0cb4.png","position":"","cname":"北京砖头智能科技有限公司"}]
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
         * friendname : 吕松松
         * friendphone : 13460720766
         * friendhead : https://api.zhuantoukj.com/birck/Public/heard/2019-09-03/5d6e43b4d4774.png
         * position :
         * cname : 北京砖头智能科技有限公司
         * sayHello :
         */

        private String friendname;
        private String friendphone;
        private String friendhead;
        private String position;
        private String cname;
        private String sayHello;

        public String getFriendname() {
            return friendname;
        }

        public void setFriendname(String friendname) {
            this.friendname = friendname;
        }

        public String getFriendphone() {
            return friendphone;
        }

        public void setFriendphone(String friendphone) {
            this.friendphone = friendphone;
        }

        public String getFriendhead() {
            return friendhead;
        }

        public void setFriendhead(String friendhead) {
            this.friendhead = friendhead;
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

        public String getSayHello() {
            return sayHello;
        }

        public void setSayHello(String sayHello) {
            this.sayHello = sayHello;
        }
    }
}
