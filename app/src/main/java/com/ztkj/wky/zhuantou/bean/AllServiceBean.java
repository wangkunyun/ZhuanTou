package com.ztkj.wky.zhuantou.bean;

public class AllServiceBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"sname":"快递服务店铺","scover":"http://39.97.75.100/birck/public/anzhuo/kuaidifuwu.png","sintroduce":"快递服务快递服务快递服务快递服务快递服务快递服务快递服务","sphone":"15333333333","province":"北京省","city":"北京市","area":"通州区","address":"世界侨商中心10号楼10层1011"}
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
         * sname : 快递服务店铺
         * scover : http://39.97.75.100/birck/public/anzhuo/kuaidifuwu.png
         * sintroduce : 快递服务快递服务快递服务快递服务快递服务快递服务快递服务
         * sphone : 15333333333
         * province : 北京省
         * city : 北京市
         * area : 通州区
         * address : 世界侨商中心10号楼10层1011
         */

        private String sname;
        private String scover;
        private String sintroduce;
        private String sphone;
        private String province;
        private String city;
        private String area;
        private String address;

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
