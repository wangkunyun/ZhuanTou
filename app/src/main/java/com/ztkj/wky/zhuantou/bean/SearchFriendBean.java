package com.ztkj.wky.zhuantou.bean;

public class SearchFriendBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"nickname":"小砖开门了","phone":"13460720766","head":"","types":0,"position":"","cname":"北京砖头智能科技有限公司"}
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
         * nickname : 小砖开门了
         * phone : 13460720766
         * head :
         * types : 0
         * position :
         * cname : 北京砖头智能科技有限公司
         */

        private String nickname;
        private String phone;
        private String head;
        private int types;
        private String position;
        private String cname;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public int getTypes() {
            return types;
        }

        public void setTypes(int types) {
            this.types = types;
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
    }
}
