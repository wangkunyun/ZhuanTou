package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class OriginBean implements Serializable {


    /**
     * errno : 200
     * errmsg : 请求成功
     * data : [{"did":"1","department_name":"研发部","department_count":2,"executive_director":{"uid":"1","c_id":"9","username":"王鹏杰","phone":"15313947598","head":"0","jurisdiction":"0","parent_level_management":"0","position":"","cname":"北京砖头智能科技有限公司","root":"0"},"department_lise":[{"uid":"11","c_id":"9","username":"王坤云","phone":"15721695007","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-09-05/5d70cef697b8e.png","jurisdiction":"0","parent_level_management":"0","position":"","cname":"北京砖头智能科技有限公司","root":"0"},{"uid":"162","c_id":"9","username":"吕松松","phone":"13460720766","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-12-19/5dfb269a6aec5.png","jurisdiction":"0","parent_level_management":"0","position":"iOS开发工程师","cname":"北京砖头智能科技有限公司","root":"1"}]},{"did":"2","department_name":"设计部","department_count":1,"executive_director":{"uid":"240","c_id":"9","username":"曲义","phone":"15584339464","head":"0","jurisdiction":"0","parent_level_management":"0","position":"ux设计师","cname":"北京砖头智能科技有限公司","root":"0"},"department_lise":[{"uid":"240","c_id":"9","username":"曲义","phone":"15584339464","head":"0","jurisdiction":"0","parent_level_management":"0","position":"ux设计师","cname":"北京砖头智能科技有限公司","root":"0"}]}]
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

    public static class DataBean implements Serializable{
        /**
         * did : 1
         * department_name : 研发部
         * department_count : 2
         * executive_director : {"uid":"1","c_id":"9","username":"王鹏杰","phone":"15313947598","head":"0","jurisdiction":"0","parent_level_management":"0","position":"","cname":"北京砖头智能科技有限公司","root":"0"}
         * department_lise : [{"uid":"11","c_id":"9","username":"王坤云","phone":"15721695007","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-09-05/5d70cef697b8e.png","jurisdiction":"0","parent_level_management":"0","position":"","cname":"北京砖头智能科技有限公司","root":"0"},{"uid":"162","c_id":"9","username":"吕松松","phone":"13460720766","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-12-19/5dfb269a6aec5.png","jurisdiction":"0","parent_level_management":"0","position":"iOS开发工程师","cname":"北京砖头智能科技有限公司","root":"1"}]
         */

        private String did;
        private String department_name;
        private int department_count;
        private ExecutiveDirectorBean executive_director;
        private List<DepartmentLiseBean> department_lise;

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }

        public String getDepartment_name() {
            return department_name;
        }

        public void setDepartment_name(String department_name) {
            this.department_name = department_name;
        }

        public int getDepartment_count() {
            return department_count;
        }

        public void setDepartment_count(int department_count) {
            this.department_count = department_count;
        }

        public ExecutiveDirectorBean getExecutive_director() {
            return executive_director;
        }

        public void setExecutive_director(ExecutiveDirectorBean executive_director) {
            this.executive_director = executive_director;
        }

        public List<DepartmentLiseBean> getDepartment_lise() {
            return department_lise;
        }

        public void setDepartment_lise(List<DepartmentLiseBean> department_lise) {
            this.department_lise = department_lise;
        }

        public static class ExecutiveDirectorBean implements Serializable{
            /**
             * uid : 1
             * c_id : 9
             * username : 王鹏杰
             * phone : 15313947598
             * head : 0
             * jurisdiction : 0
             * parent_level_management : 0
             * position :
             * cname : 北京砖头智能科技有限公司
             * root : 0
             */

            private String uid;
            private String c_id;
            private String username;
            private String phone;
            private String head;
            private String jurisdiction;
            private String parent_level_management;
            private String position;
            private String cname;
            private String root;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getC_id() {
                return c_id;
            }

            public void setC_id(String c_id) {
                this.c_id = c_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
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

            public String getJurisdiction() {
                return jurisdiction;
            }

            public void setJurisdiction(String jurisdiction) {
                this.jurisdiction = jurisdiction;
            }

            public String getParent_level_management() {
                return parent_level_management;
            }

            public void setParent_level_management(String parent_level_management) {
                this.parent_level_management = parent_level_management;
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

            public String getRoot() {
                return root;
            }

            public void setRoot(String root) {
                this.root = root;
            }
        }

        public static class DepartmentLiseBean implements Serializable{
            /**
             * uid : 11
             * c_id : 9
             * username : 王坤云
             * phone : 15721695007
             * head : https://api.zhuantoukj.com/birck/Public/heard/2019-09-05/5d70cef697b8e.png
             * jurisdiction : 0
             * parent_level_management : 0
             * position :
             * cname : 北京砖头智能科技有限公司
             * root : 0
             */

            private String uid;
            private String c_id;
            private String username;
            private String phone;
            private String head;
            private String jurisdiction;
            private String parent_level_management;
            private String position;
            private String cname;
            private String root;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getC_id() {
                return c_id;
            }

            public void setC_id(String c_id) {
                this.c_id = c_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
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

            public String getJurisdiction() {
                return jurisdiction;
            }

            public void setJurisdiction(String jurisdiction) {
                this.jurisdiction = jurisdiction;
            }

            public String getParent_level_management() {
                return parent_level_management;
            }

            public void setParent_level_management(String parent_level_management) {
                this.parent_level_management = parent_level_management;
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

            public String getRoot() {
                return root;
            }

            public void setRoot(String root) {
                this.root = root;
            }
        }
    }
}
