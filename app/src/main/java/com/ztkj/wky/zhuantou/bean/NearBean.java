package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class NearBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"bu_id":"1","bu_name":"鹿角巷","bu_address":"北京市通州区世界侨商中心10号楼1011","bu_image":"https://hiphotos.baidu.com/feed/pic/item/b17eca8065380cd7b779a1d2ac44ad34598281ad.jpg","key_word":"咖啡,奶茶,吧台"},{"bu_id":"2","bu_name":"鹿角巷","bu_address":"北京市通州区世界侨商中心10号楼1011","bu_image":"https://hiphotos.baidu.com/feed/pic/item/b17eca8065380cd7b779a1d2ac44ad34598281ad.jpg","key_word":"咖啡,奶茶,吧台"}]
     */

    private String errno;
    private String errmsg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bu_id : 1
         * bu_name : 鹿角巷
         * bu_address : 北京市通州区世界侨商中心10号楼1011
         * bu_image : https://hiphotos.baidu.com/feed/pic/item/b17eca8065380cd7b779a1d2ac44ad34598281ad.jpg
         * key_word : 咖啡,奶茶,吧台
         */

        private String bu_id;
        private String bu_name;
        private String bu_address;
        private String bu_image;
        private String key_word;

        public String getBu_id() {
            return bu_id;
        }

        public void setBu_id(String bu_id) {
            this.bu_id = bu_id;
        }

        public String getBu_name() {
            return bu_name;
        }

        public void setBu_name(String bu_name) {
            this.bu_name = bu_name;
        }

        public String getBu_address() {
            return bu_address;
        }

        public void setBu_address(String bu_address) {
            this.bu_address = bu_address;
        }

        public String getBu_image() {
            return bu_image;
        }

        public void setBu_image(String bu_image) {
            this.bu_image = bu_image;
        }

        public String getKey_word() {
            return key_word;
        }

        public void setKey_word(String key_word) {
            this.key_word = key_word;
        }
    }
}
