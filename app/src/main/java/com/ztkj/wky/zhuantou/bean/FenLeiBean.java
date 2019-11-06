package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class FenLeiBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"cl_id":"1","cl_name":"最新资讯","subordinate":[{"cl_id":"3","cl_name":"企业动态","pid":"1"},{"cl_id":"4","cl_name":"职场资讯","pid":"1"},{"cl_id":"5","cl_name":"能力提升","pid":"1"},{"cl_id":"6","cl_name":"视野开阔","pid":"1"}]},{"cl_id":"2","cl_name":"热门话题","subordinate":[{"cl_id":"7","cl_name":"言行仪表","pid":"2"},{"cl_id":"8","cl_name":"言谈举止","pid":"2"},{"cl_id":"9","cl_name":"没有BUG","pid":"2"},{"cl_id":"10","cl_name":"阿弥陀佛","pid":"2"}]}]
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
         * cl_id : 1
         * cl_name : 最新资讯
         * subordinate : [{"cl_id":"3","cl_name":"企业动态","pid":"1"},{"cl_id":"4","cl_name":"职场资讯","pid":"1"},{"cl_id":"5","cl_name":"能力提升","pid":"1"},{"cl_id":"6","cl_name":"视野开阔","pid":"1"}]
         */

        private String cl_id;
        private String cl_name;
        private List<SubordinateBean> subordinate;

        public String getCl_id() {
            return cl_id;
        }

        public void setCl_id(String cl_id) {
            this.cl_id = cl_id;
        }

        public String getCl_name() {
            return cl_name;
        }

        public void setCl_name(String cl_name) {
            this.cl_name = cl_name;
        }

        public List<SubordinateBean> getSubordinate() {
            return subordinate;
        }

        public void setSubordinate(List<SubordinateBean> subordinate) {
            this.subordinate = subordinate;
        }

        public static class SubordinateBean {
            /**
             * cl_id : 3
             * cl_name : 企业动态
             * pid : 1
             */

            private String cl_id;
            private String cl_name;
            private String pid;

            public String getCl_id() {
                return cl_id;
            }

            public void setCl_id(String cl_id) {
                this.cl_id = cl_id;
            }

            public String getCl_name() {
                return cl_name;
            }

            public void setCl_name(String cl_name) {
                this.cl_name = cl_name;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }
        }
    }
}
