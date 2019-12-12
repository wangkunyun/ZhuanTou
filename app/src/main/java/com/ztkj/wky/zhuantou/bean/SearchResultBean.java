package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class SearchResultBean implements Serializable {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"skw_word":"手机"}]
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
         * skw_word : 手机
         */

        private String skw_word;

        public String getSkw_word() {
            return skw_word;
        }

        public void setSkw_word(String skw_word) {
            this.skw_word = skw_word;
        }
    }
}
