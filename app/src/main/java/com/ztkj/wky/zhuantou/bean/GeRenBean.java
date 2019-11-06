package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class GeRenBean {

    /**
     * errno : 200
     * errmsg : 编辑成功
     * data : []
     */

    private String errno;
    private String errmsg;
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
