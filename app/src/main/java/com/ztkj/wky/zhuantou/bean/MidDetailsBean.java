package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class MidDetailsBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"mid":"1","cid":"1","uid":"2","monthly_work":"1212","monthly_summary":"122122","monthly_plan":"133213","harmonize":"231321","notes":"3213123","images":"","files":"","files_name":"","approver":",8","reader":[{"name":"杰杰","head":"0"}],"addtime":"2019-07-26 15:04"}
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
         * mid : 1
         * cid : 1
         * uid : 2
         * monthly_work : 1212
         * monthly_summary : 122122
         * monthly_plan : 133213
         * harmonize : 231321
         * notes : 3213123
         * images :
         * files :
         * files_name :
         * approver : ,8
         * reader : [{"name":"杰杰","head":"0"}]
         * addtime : 2019-07-26 15:04
         */

        private String mid;
        private String cid;
        private String uid;
        private String monthly_work;
        private String monthly_summary;
        private String monthly_plan;
        private String harmonize;
        private String notes;
        private String images;
        private String files;
        private String files_name;
        private String approver;
        private String addtime;
        private List<ReaderBean> reader;

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
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

        public String getMonthly_work() {
            return monthly_work;
        }

        public void setMonthly_work(String monthly_work) {
            this.monthly_work = monthly_work;
        }

        public String getMonthly_summary() {
            return monthly_summary;
        }

        public void setMonthly_summary(String monthly_summary) {
            this.monthly_summary = monthly_summary;
        }

        public String getMonthly_plan() {
            return monthly_plan;
        }

        public void setMonthly_plan(String monthly_plan) {
            this.monthly_plan = monthly_plan;
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
