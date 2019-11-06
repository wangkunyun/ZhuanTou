package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class AllReportBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"id":"1","username":"吕聪聪的时段总结","addtime":"07-25 11:00","summary":"2313","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-07-19/5d3165f0a1de4.png","type":"tid"},{"id":"1","username":"吕聪聪的月报","addtime":"07-25 10:34","summary":"1212","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-07-19/5d3165f0a1de4.png","type":"mid"},{"id":"2","username":"吕聪聪的日报","addtime":"07-24 15:57","summary":"1212","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-07-19/5d3165f0a1de4.png","type":"did"},{"id":"1","username":"吕聪聪的日报","addtime":"07-24 15:55","summary":"1212","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-07-19/5d3165f0a1de4.png","type":"did"}]
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
         * id : 1
         * username : 吕聪聪的时段总结
         * addtime : 07-25 11:00
         * summary : 2313
         * head : https://api.zhuantoukj.com/birck/Public/heard/2019-07-19/5d3165f0a1de4.png
         * type : tid
         */

        private String id;
        private String username;
        private String addtime;
        private String summary;
        private String head;
        private String type;

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
    }
}
