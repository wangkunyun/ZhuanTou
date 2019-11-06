package com.ztkj.wky.zhuantou.bean;

public class GouWuCheBean {
    private String ecid;
    private String name;
    private String price;
    private String img;
    private int num;


    public GouWuCheBean() {
    }

    public GouWuCheBean(String ecid, String name, String price, String img, int num) {
        this.ecid = ecid;
        this.name = name;
        this.price = price;
        this.img = img;
        this.num = num;
    }

    public String getEcid() {
        return ecid;
    }

    public void setEcid(String ecid) {
        this.ecid = ecid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
