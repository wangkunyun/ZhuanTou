package com.ztkj.wky.zhuantou.bean;

public class DlBean {


    /**
     * errno : 200
     * errmsg : success
     * data : {"jurisdiction":"1","cid":"9","team_name":"北京砖头智能科技有限公司","team_logo":"0","authentication":"0","uid":"11","name":"大大云","username":"王坤云","phone":"15721695007","type":"0","token":"MzThUu3aMnjtEo2uO_TcUowdMeDdfMTM0ZjczODZjZDZlNzZkZmQ5Y2YzOGY1YmY4NTA1MTUO0O0O","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d44029fa3c69.png"}
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
         * jurisdiction : 1
         * cid : 9
         * team_name : 北京砖头智能科技有限公司
         * team_logo : 0
         * authentication : 0
         * uid : 11
         * name : 大大云
         * username : 王坤云
         * phone : 15721695007
         * type : 0
         * token : MzThUu3aMnjtEo2uO_TcUowdMeDdfMTM0ZjczODZjZDZlNzZkZmQ5Y2YzOGY1YmY4NTA1MTUO0O0O
         * head : https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d44029fa3c69.png
         */

        private String jurisdiction;
        private String cid;
        private String team_name;
        private String team_logo;
        private String authentication;
        private String uid;
        private String name;
        private String username;
        private String phone;
        private String type;
        private String token;
        private String head;

        public String getJurisdiction() {
            return jurisdiction;
        }

        public void setJurisdiction(String jurisdiction) {
            this.jurisdiction = jurisdiction;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }

        public String getTeam_logo() {
            return team_logo;
        }

        public void setTeam_logo(String team_logo) {
            this.team_logo = team_logo;
        }

        public String getAuthentication() {
            return authentication;
        }

        public void setAuthentication(String authentication) {
            this.authentication = authentication;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }
    }
}
