package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class WidDetailsBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"wid":"1","cid":"1","uid":"2","weekly_completion":"121231","zhou_summary":"321321","weekly_plan":"3321321","harmonize":"33123123","notes":"3213213","images":"","files":",https://api.zhuantoukj.com/birck/Public/WeeklyFile/2019-07-25/5d39143c8912a.doc,https://api.zhuantoukj.com/birck/Public/WeeklyFile/2019-07-25/5d3914aa3adf1.doc","files_name":"","approver":",8","reader":[{"name":"杰杰","head":"0"}],"addtime":"2019-07-26 14:55"}
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
         * wid : 1
         * cid : 1
         * uid : 2
         * weekly_completion : 121231
         * zhou_summary : 321321
         * weekly_plan : 3321321
         * harmonize : 33123123
         * notes : 3213213
         * images :
         * files : ,https://api.zhuantoukj.com/birck/Public/WeeklyFile/2019-07-25/5d39143c8912a.doc,https://api.zhuantoukj.com/birck/Public/WeeklyFile/2019-07-25/5d3914aa3adf1.doc
         * files_name :
         * approver : ,8
         * reader : [{"name":"杰杰","head":"0"}]
         * addtime : 2019-07-26 14:55
         */

        private String wid;
        private String cid;
        private String uid;
        private String weekly_completion;
        private String zhou_summary;
        private String weekly_plan;
        private String harmonize;
        private String notes;
        private String images;
        private String files;
        private String files_name;
        private String approver;
        private String addtime;
        private List<ReaderBean> reader;

        public String getWid() {
            return wid;
        }

        public void setWid(String wid) {
            this.wid = wid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getWeekly_completion() {
            return weekly_completion;
        }

        public void setWeekly_completion(String weekly_completion) {
            this.weekly_completion = weekly_completion;
        }

        public String getZhou_summary() {
            return zhou_summary;
        }

        public void setZhou_summary(String zhou_summary) {
            this.zhou_summary = zhou_summary;
        }

        public String getWeekly_plan() {
            return weekly_plan;
        }

        public void setWeekly_plan(String weekly_plan) {
            this.weekly_plan = weekly_plan;
        }

        public String getHarmonize() {
            return harmonize;
        }

        public void setHarmonize(String harmonize) {
            this.harmonize = harmonize;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
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
