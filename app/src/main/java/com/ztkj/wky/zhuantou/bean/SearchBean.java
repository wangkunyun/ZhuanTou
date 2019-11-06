package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class SearchBean {

    /**
     * errno : 200
     * errmsg : success
     * data : {"list":[{"aid":"1","title":"又一起！斯里兰卡部队在餐厅内引爆一个可疑包裹水","introduce":"海外网4月24日电据斯里兰卡当地媒体News1st报道，斯里兰卡","cover":"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=4212289692,115634387&fm=173&app=25&f=JPEG?w=218&h=146&s=257059824E339BDC7ECDE09D03009082","popular":"0"},{"aid":"2","title":"又一起！斯里兰卡部队在餐厅内引爆一个可疑包裹水","introduce":"海外网4月24日电据斯里兰卡当地媒体News1st报道，斯里兰卡","cover":"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=4212289692,115634387&fm=173&app=25&f=JPEG?w=218&h=146&s=257059824E339BDC7ECDE09D03009082","popular":"1"}],"count":"2","enterprise":[{"ename":"企业用水","elogo":"http://39.97.75.100/birck/public/anzhuo/qiyeyongshui.png","sname":"企业用水店铺","scover":"http://39.97.75.100/birck/public/anzhuo/qiyeyongshui.png","sintroduce":"用水用水用水用水用水用水","sphone":"15888888888","province":"北京省","city":"北京市","area":"通州区","address":"世界侨商中心10号楼10层1011"}]}
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
         * list : [{"aid":"1","title":"又一起！斯里兰卡部队在餐厅内引爆一个可疑包裹水","introduce":"海外网4月24日电据斯里兰卡当地媒体News1st报道，斯里兰卡","cover":"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=4212289692,115634387&fm=173&app=25&f=JPEG?w=218&h=146&s=257059824E339BDC7ECDE09D03009082","popular":"0"},{"aid":"2","title":"又一起！斯里兰卡部队在餐厅内引爆一个可疑包裹水","introduce":"海外网4月24日电据斯里兰卡当地媒体News1st报道，斯里兰卡","cover":"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=4212289692,115634387&fm=173&app=25&f=JPEG?w=218&h=146&s=257059824E339BDC7ECDE09D03009082","popular":"1"}]
         * count : 2
         * enterprise : [{"ename":"企业用水","elogo":"http://39.97.75.100/birck/public/anzhuo/qiyeyongshui.png","sname":"企业用水店铺","scover":"http://39.97.75.100/birck/public/anzhuo/qiyeyongshui.png","sintroduce":"用水用水用水用水用水用水","sphone":"15888888888","province":"北京省","city":"北京市","area":"通州区","address":"世界侨商中心10号楼10层1011"}]
         */

        private String count;
        private List<ListBean> list;
        private List<EnterpriseBean> enterprise;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<EnterpriseBean> getEnterprise() {
            return enterprise;
        }

        public void setEnterprise(List<EnterpriseBean> enterprise) {
            this.enterprise = enterprise;
        }

        public static class ListBean {
            /**
             * aid : 1
             * title : 又一起！斯里兰卡部队在餐厅内引爆一个可疑包裹水
             * introduce : 海外网4月24日电据斯里兰卡当地媒体News1st报道，斯里兰卡
             * cover : https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=4212289692,115634387&fm=173&app=25&f=JPEG?w=218&h=146&s=257059824E339BDC7ECDE09D03009082
             * popular : 0
             */

            private String aid;
            private String title;
            private String introduce;
            private String cover;
            private String popular;

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getPopular() {
                return popular;
            }

            public void setPopular(String popular) {
                this.popular = popular;
            }
        }

        public static class EnterpriseBean {
            /**
             * ename : 企业用水
             * elogo : http://39.97.75.100/birck/public/anzhuo/qiyeyongshui.png
             * sname : 企业用水店铺
             * scover : http://39.97.75.100/birck/public/anzhuo/qiyeyongshui.png
             * sintroduce : 用水用水用水用水用水用水
             * sphone : 15888888888
             * province : 北京省
             * city : 北京市
             * area : 通州区
             * address : 世界侨商中心10号楼10层1011
             */

            private String ename;
            private String elogo;
            private String sname;
            private String scover;
            private String sintroduce;
            private String sphone;
            private String province;
            private String city;
            private String area;
            private String address;
            private String eid;

            public String getEid() {
                return eid;
            }

            public void setEid(String eid) {
                this.eid = eid;
            }

            public String getEname() {
                return ename;
            }

            public void setEname(String ename) {
                this.ename = ename;
            }

            public String getElogo() {
                return elogo;
            }

            public void setElogo(String elogo) {
                this.elogo = elogo;
            }

            public String getSname() {
                return sname;
            }

            public void setSname(String sname) {
                this.sname = sname;
            }

            public String getScover() {
                return scover;
            }

            public void setScover(String scover) {
                this.scover = scover;
            }

            public String getSintroduce() {
                return sintroduce;
            }

            public void setSintroduce(String sintroduce) {
                this.sintroduce = sintroduce;
            }

            public String getSphone() {
                return sphone;
            }

            public void setSphone(String sphone) {
                this.sphone = sphone;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
    }
}
