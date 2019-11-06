package com.ztkj.wky.zhuantou.adapter;

public class CreateTeamBean {


    /**
     * errno : 200
     * errmsg : 创建成功
     * data : {"team_name":"超大云科技有限公司","team_size":"21-50","team_industry":"制造业","jurisdiction":"1"}
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
         * team_name : 超大云科技有限公司
         * team_size : 21-50
         * team_industry : 制造业
         * jurisdiction : 1
         */

        private String team_name;
        private String team_size;
        private String team_industry;
        private String jurisdiction;

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }

        public String getTeam_size() {
            return team_size;
        }

        public void setTeam_size(String team_size) {
            this.team_size = team_size;
        }

        public String getTeam_industry() {
            return team_industry;
        }

        public void setTeam_industry(String team_industry) {
            this.team_industry = team_industry;
        }

        public String getJurisdiction() {
            return jurisdiction;
        }

        public void setJurisdiction(String jurisdiction) {
            this.jurisdiction = jurisdiction;
        }
    }
}
