package com.ztkj.wky.zhuantou.bean;

public class GetUserMessageBean {


    /**
     * errno : 200
     * errmsg : success
     * data : {"reach":"0","uid":"11","username":"大大云","phone":"15721695007","type":"0","name":"大大云","integral":"173","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-09-05/5d70cef697b8e.png","sex":"0","area":"0","birthday":"","address":"","cname":"北京砖头智能科技有限公司","caddress":"北京 北京市 通州区 侨商中心园区 侨商 1号楼 A座 10层 1011室","balance":"0","real_name":"0"}
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
         * reach : 0
         * uid : 11
         * username : 大大云
         * phone : 15721695007
         * type : 0
         * name : 大大云
         * integral : 173
         * head : https://api.zhuantoukj.com/birck/Public/heard/2019-09-05/5d70cef697b8e.png
         * sex : 0
         * area : 0
         * birthday :
         * address :
         * cname : 北京砖头智能科技有限公司
         * caddress : 北京 北京市 通州区 侨商中心园区 侨商 1号楼 A座 10层 1011室
         * balance : 0
         * real_name : 0
         */

        private String reach;
        private String uid;
        private String username;
        private String phone;
        private String type;
        private String name;
        private String integral;
        private String head;
        private String sex;
        private String area;
        private String birthday;
        private String address;
        private String cname;
        private String caddress;
        private String balance;
        private String real_name;

        public String getReach() {
            return reach;
        }

        public void setReach(String reach) {
            this.reach = reach;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getCaddress() {
            return caddress;
        }

        public void setCaddress(String caddress) {
            this.caddress = caddress;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }
    }
}
