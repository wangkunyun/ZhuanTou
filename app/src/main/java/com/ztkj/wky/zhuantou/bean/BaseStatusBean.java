package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class BaseStatusBean implements Serializable {


    /**
     * errno : 206
     * errmsg : 下单信息有误请退出重试
     * data : []
     */

    private String errno;
    private String errmsg;

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
}
