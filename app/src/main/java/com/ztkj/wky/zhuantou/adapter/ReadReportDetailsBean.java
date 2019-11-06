package com.ztkj.wky.zhuantou.adapter;

import java.util.List;

public class ReadReportDetailsBean {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"did":"1","cid":"1","uid":"2","work_today":"1212","not_finished":"121212","coordinated":"1221212","notes":"121212","images":"","files":"","files_name":"","approver":",8,81","reader":[{"name":"杰杰","head":"0"}],"addtime":"2019-07-26 14:53"}
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
         * did : 1
         * cid : 1
         * uid : 2
         * work_today : 1212
         * not_finished : 121212
         * coordinated : 1221212
         * notes : 121212
         * images :
         * files :
         * files_name :
         * approver : ,8,81
         * reader : [{"name":"杰杰","head":"0"}]
         * addtime : 2019-07-26 14:53
         */

        private String did;
        private String cid;
        private String uid;
        private String work_today;
        private String not_finished;
        private String coordinated;
        private String notes;
        private String images;
        private String files;
        private String files_name;
        private String approver;
        private String addtime;
        private List<ReaderBean> reader;

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
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

        public String getWork_today() {
            return work_today;
        }

        public void setWork_today(String work_today) {
            this.work_today = work_today;
        }

        public String getNot_finished() {
            return not_finished;
        }

        public void setNot_finished(String not_finished) {
            this.not_finished = not_finished;
        }

        public String getCoordinated() {
            return coordinated;
        }

        public void setCoordinated(String coordinated) {
            this.coordinated = coordinated;
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
