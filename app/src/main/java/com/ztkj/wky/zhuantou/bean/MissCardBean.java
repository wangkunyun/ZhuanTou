package com.ztkj.wky.zhuantou.bean;

public class MissCardBean {
    private String addTime;
    private String noon;

    public MissCardBean(String addTime, String noon) {
        this.addTime = addTime;
        this.noon = noon;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getNoon() {
        return noon;
    }

    public void setNoon(String noon) {
        this.noon = noon;
    }
}
