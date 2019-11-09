package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class ReceiveReportBean {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"id":"26","username":"王坤云的周报","addtime":"2019-11-06 13:28","summary":"1. 接入统计表与统计表的补卡申请，打卡页面bug\n2.请假一天\n3.接入git，考勤记录页面切换日历显示当天考勤并申请不卡\n4.登录流程修改，申请权限表修改\n5.审批小红点，所有页面返回刷新数据\n6.人脸识别Sdk","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-09-05/5d70cef697b8e.png","type":"wid","already_read":"1"},{"id":"23","username":"王鹏杰的周报","addtime":"2019-11-05 10:27","summary":"LOL海陆空","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-22/5d5e2ba69a7e9.png","type":"wid","already_read":"1"},{"id":"17","username":"王鹏杰的日报","addtime":"2019-11-05 10:26","summary":"明明民","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-22/5d5e2ba69a7e9.png","type":"did","already_read":"1"},{"id":"5","username":"吕松松的日报","addtime":"2019-10-26 09:46","summary":"今天写一下团队考勤统计","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-10-22/5daec4d3b9c73.png","type":"did","already_read":"1"}]
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
         * id : 26
         * username : 王坤云的周报
         * addtime : 2019-11-06 13:28
         * summary : 1. 接入统计表与统计表的补卡申请，打卡页面bug
         2.请假一天
         3.接入git，考勤记录页面切换日历显示当天考勤并申请不卡
         4.登录流程修改，申请权限表修改
         5.审批小红点，所有页面返回刷新数据
         6.人脸识别Sdk
         * head : https://api.zhuantoukj.com/birck/Public/heard/2019-09-05/5d70cef697b8e.png
         * type : wid
         * already_read : 1
         */

        private String id;
        private String username;
        private String addtime;
        private String summary;
        private String head;
        private String type;
        private String already_read;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAlready_read() {
            return already_read;
        }

        public void setAlready_read(String already_read) {
            this.already_read = already_read;
        }
    }
}
