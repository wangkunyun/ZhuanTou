package com.ztkj.wky.zhuantou.bean;

public class WeatherBean {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"jurisdiction":"2","username":"王坤云","cid":"9","weather":"晴","temperature":"27","images":"https://api.zhuantoukj.com/birck/Public/tianqi/qing.png","team":{"cid":"9","team_name":"北京砖头智能科技有限公司","team_logo":"https://api.zhuantoukj.com/birck/Public/team_logo/2019-09-24/5d89b922589a7.png","authentication":"0"}}
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
         * jurisdiction : 2
         * username : 王坤云
         * cid : 9
         * weather : 晴
         * temperature : 27
         * images : https://api.zhuantoukj.com/birck/Public/tianqi/qing.png
         * team : {"cid":"9","team_name":"北京砖头智能科技有限公司","team_logo":"https://api.zhuantoukj.com/birck/Public/team_logo/2019-09-24/5d89b922589a7.png","authentication":"0"}
         */

        private String jurisdiction;
        private String username;
        private String cid;
        private String weather;
        private String temperature;
        private String images;
        private TeamBean team;

        public String getJurisdiction() {
            return jurisdiction;
        }

        public void setJurisdiction(String jurisdiction) {
            this.jurisdiction = jurisdiction;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public TeamBean getTeam() {
            return team;
        }

        public void setTeam(TeamBean team) {
            this.team = team;
        }

        public static class TeamBean {
            /**
             * cid : 9
             * team_name : 北京砖头智能科技有限公司
             * team_logo : https://api.zhuantoukj.com/birck/Public/team_logo/2019-09-24/5d89b922589a7.png
             * authentication : 0
             */

            private String cid;
            private String team_name;
            private String team_logo;
            private String authentication;

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
        }
    }
}
