package com.ztkj.wky.zhuantou.bean;

public class EnterpriseLogoBean {

    /**
     * errno : 200
     * errmsg : 编辑成功
     * data : {"team_logo":"https://api.zhuantoukj.com/birck/Public/team_logo/2019-07-24/5d37d241b308d.png"}
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
         * team_logo : https://api.zhuantoukj.com/birck/Public/team_logo/2019-07-24/5d37d241b308d.png
         */

        private String team_logo;

        public String getTeam_logo() {
            return team_logo;
        }

        public void setTeam_logo(String team_logo) {
            this.team_logo = team_logo;
        }
    }
}
