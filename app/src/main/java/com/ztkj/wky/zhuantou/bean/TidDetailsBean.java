package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class TidDetailsBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"tid":"1","cid":"1","uid":"2","title":"213123","content":"2313","start_time":"12313","end_time":"123123","files":",https://api.zhuantoukj.com/birck/Public/SummaryFile/2019-07-25/5d3925748359c.doc","files_name":"","approver":",8","reader":[{"name":"杰杰","head":"0"}],"addtime":"2019-07-26 15:09"}
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
         * tid : 1
         * cid : 1
         * uid : 2
         * title : 213123
         * content : 2313
         * start_time : 12313
         * end_time : 123123
         * files : ,https://api.zhuantoukj.com/birck/Public/SummaryFile/2019-07-25/5d3925748359c.doc
         * files_name :
         * approver : ,8
         * reader : [{"name":"杰杰","head":"0"}]
         * addtime : 2019-07-26 15:09
         */

        private String tid;
        private String cid;
        private String uid;
        private String title;
        private String content;
        private String start_time;
        private String end_time;
        private String files;
        private String files_name;
        private String approver;
        private String addtime;
        private String plan;
        private List<ReaderBean> reader;

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getPlan() {
            return plan;
        }

        public void setPlan(String plan) {
            this.plan = plan;
        }


        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getFiles() {
            return files;
        }

        public void setFiles(String files) {
            this.files = files;
        }

        public String getFiles_name() {
            return files_name;
        }

        public void setFiles_name(String files_name) {
            this.files_name = files_name;
        }

        public String getApprover() {
            return approver;
        }

        public void setApprover(String approver) {
            this.approver = approver;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public List<ReaderBean> getReader() {
            return reader;
        }

        public void setReader(List<ReaderBean> reader) {
            this.reader = reader;
        }

        public static class ReaderBean {
            /**
             * name : 杰杰
             * head : 0
             */

            private String name;
            private String head;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }
        }
    }
}
