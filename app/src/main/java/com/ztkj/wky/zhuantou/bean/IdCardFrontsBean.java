package com.ztkj.wky.zhuantou.bean;

public class IdCardFrontsBean {

    /**
     * code : 1
     * msg : 查询成功
     * result : {"address":"山西省稷山县西社镇山底村第一居民组","birthday":"19961012","name":"王坤云","code":"142727199610123537","sex":"男","nation":"汉"}
     */

    private String code;
    private String msg;
    private ResultBean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * address : 山西省稷山县西社镇山底村第一居民组
         * birthday : 19961012
         * name : 王坤云
         * code : 142727199610123537
         * sex : 男
         * nation : 汉
         */

        private String address;
        private String birthday;
        private String name;
        private String code;
        private String sex;
        private String nation;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }
    }
}
