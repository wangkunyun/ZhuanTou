package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;

public class AdressUpdateBean implements Serializable {

    private String addressId;
    private String username;
    private String userphone;
    private String useraddress;


    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    @Override
    public String toString() {
        return "AdressUpdateBean{" +
                "username='" + username + '\'' +
                ", userphone='" + userphone + '\'' +
                ", useraddress='" + useraddress + '\'' +
                '}';
    }
}
