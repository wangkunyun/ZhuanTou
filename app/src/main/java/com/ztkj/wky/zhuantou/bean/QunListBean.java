package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class QunListBean {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"myGroup":[{"groupid":"92569549733889","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569548685313","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569548685314","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569547636737","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569545539585","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569549733890","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569549733892","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569549733893","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569549733894","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569550782465","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569550782466","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569550782467","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569550782468","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569550782469","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569550782470","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569551831041","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569551831042","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569552879617","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569552879618","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569552879619","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569554976769","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569554976770","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569554976771","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569572802561","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569572802563","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6},{"groupid":"92569572802564","imgname":"超大云研究所","imglogo":"0","imgintroduction":"","affiliations_count":6}],"stayGroup":[{"groupid":"92568188682241","imgname":"小砖的家","imglogo":"0","imgintroduction":"","affiliations_count":5}]}
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
        private List<MyGroupBean> myGroup;
        private List<StayGroupBean> stayGroup;

        public List<MyGroupBean> getMyGroup() {
            return myGroup;
        }

        public void setMyGroup(List<MyGroupBean> myGroup) {
            this.myGroup = myGroup;
        }

        public List<StayGroupBean> getStayGroup() {
            return stayGroup;
        }

        public void setStayGroup(List<StayGroupBean> stayGroup) {
            this.stayGroup = stayGroup;
        }

        public static class MyGroupBean {
            /**
             * groupid : 92569549733889
             * imgname : 超大云研究所
             * imglogo : 0
             * imgintroduction :
             * affiliations_count : 6
             */

            private String groupid;
            private String imgname;
            private String imglogo;
            private String imgintroduction;
            private int affiliations_count;

            public String getGroupid() {
                return groupid;
            }

            public void setGroupid(String groupid) {
                this.groupid = groupid;
            }

            public String getImgname() {
                return imgname;
            }

            public void setImgname(String imgname) {
                this.imgname = imgname;
            }

            public String getImglogo() {
                return imglogo;
            }

            public void setImglogo(String imglogo) {
                this.imglogo = imglogo;
            }

            public String getImgintroduction() {
                return imgintroduction;
            }

            public void setImgintroduction(String imgintroduction) {
                this.imgintroduction = imgintroduction;
            }

            public int getAffiliations_count() {
                return affiliations_count;
            }

            public void setAffiliations_count(int affiliations_count) {
                this.affiliations_count = affiliations_count;
            }
        }

        public static class StayGroupBean {
            /**
             * groupid : 92568188682241
             * imgname : 小砖的家
             * imglogo : 0
             * imgintroduction :
             * affiliations_count : 5
             */

            private String groupid;
            private String imgname;
            private String imglogo;
            private String imgintroduction;
            private int affiliations_count;

            public StayGroupBean(String groupid, String imgname, String imglogo, String imgintroduction, int affiliations_count) {
                this.groupid = groupid;
                this.imgname = imgname;
                this.imglogo = imglogo;
                this.imgintroduction = imgintroduction;
                this.affiliations_count = affiliations_count;

            }

            public String getGroupid() {
                return groupid;
            }

            public void setGroupid(String groupid) {
                this.groupid = groupid;
            }

            public String getImgname() {
                return imgname;
            }

            public void setImgname(String imgname) {
                this.imgname = imgname;
            }

            public String getImglogo() {
                return imglogo;
            }

            public void setImglogo(String imglogo) {
                this.imglogo = imglogo;
            }

            public String getImgintroduction() {
                return imgintroduction;
            }

            public void setImgintroduction(String imgintroduction) {
                this.imgintroduction = imgintroduction;
            }

            public int getAffiliations_count() {
                return affiliations_count;
            }

            public void setAffiliations_count(int affiliations_count) {
                this.affiliations_count = affiliations_count;
            }
        }
    }
}
