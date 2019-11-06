package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class DakaBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"userInfo":{"d":0,"h":0,"head":"http://39.97.75.100/birck/Public/heard/2019-04-26/5cc2ae6554409.jpeg"},"list":[{"datetime":"1日","state":"未打卡","intervaltime":"","spanlength":"无"},{"datetime":"2日","state":"未打卡","intervaltime":"","spanlength":"无"},{"datetime":"3日","state":"未打卡","intervaltime":"","spanlength":"无"},{"datetime":"4日","state":"未打卡","intervaltime":"","spanlength":"无"},{"datetime":"5日","state":"未打卡","intervaltime":"","spanlength":"无"},{"datetime":"6日","state":"未打卡","intervaltime":"","spanlength":"无"}]}
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

    public static class DataBean {
        /**
         * userInfo : {"d":0,"h":0,"head":"http://39.97.75.100/birck/Public/heard/2019-04-26/5cc2ae6554409.jpeg"}
         * list : [{"datetime":"1日","state":"未打卡","intervaltime":"","spanlength":"无"},{"datetime":"2日","state":"未打卡","intervaltime":"","spanlength":"无"},{"datetime":"3日","state":"未打卡","intervaltime":"","spanlength":"无"},{"datetime":"4日","state":"未打卡","intervaltime":"","spanlength":"无"},{"datetime":"5日","state":"未打卡","intervaltime":"","spanlength":"无"},{"datetime":"6日","state":"未打卡","intervaltime":"","spanlength":"无"}]
         */

        private UserInfoBean userInfo;
        private List<ListBean> list;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class UserInfoBean {
            /**
             * d : 0
             * h : 0
             * head : http://39.97.75.100/birck/Public/heard/2019-04-26/5cc2ae6554409.jpeg
             */

            private String d;
            private String h;
            private String head;
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getD() {
                return d;
            }

            public void setD(String d) {
                this.d = d;
            }

            public String getH() {
                return h;
            }

            public void setH(String h) {
                this.h = h;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }
        }

        public static class ListBean {
            /**
             * datetime : 1日
             * state : 未打卡
             * intervaltime :
             * spanlength : 无
             */

            private String datetime;
            private String state;
            private String intervaltime;
            private String spanlength;

            public String getDatetime() {
                return datetime;
            }

            public void setDatetime(String datetime) {
                this.datetime = datetime;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getIntervaltime() {
                return intervaltime;
            }

            public void setIntervaltime(String intervaltime) {
                this.intervaltime = intervaltime;
            }

            public String getSpanlength() {
                return spanlength;
            }

            public void setSpanlength(String spanlength) {
                this.spanlength = spanlength;
            }
        }
    }
}
