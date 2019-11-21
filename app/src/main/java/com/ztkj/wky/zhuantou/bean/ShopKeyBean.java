package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class ShopKeyBean implements Serializable {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : ["手机","骁龙","型号","Z5x","限量"]
     */

    private String errno;
    private String errmsg;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
