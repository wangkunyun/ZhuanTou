package com.ztkj.wky.zhuantou.bean;

/**
 * Created by AAAAA on 2016/6/21.
 */
public class MapBean {
    private String name;
    private boolean status;
    private String head;
    private String phones;

    public MapBean(String name, boolean status, String head, String phones) {
        this.name = name;
        this.status = status;
        this.head = head;
        this.phones = phones;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
