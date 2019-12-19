package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;

public class AdressUpdateBean implements Serializable {

    private String addressId;
    private String username;
    private String userphone;
    private String useraddress;
    private String sra_province_city_area;
    private String sra_pca_address;

    public String getSra_province_city_area() {
        return sra_province_city_area;
    }

    public void setSra_province_city_area(String sra_province_city_area) {
        this.sra_province_city_area = sra_province_city_area;
    }

    public String getSra_pca_address() {
        return sra_pca_address;
    }

    public void setSra_pca_address(String sra_pca_address) {
        this.sra_pca_address = sra_pca_address;
    }

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
