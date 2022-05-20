package com.company;

public class Customer {
    private long soId;
    private long ppnkId;
    private String soCountCode;
    private String name;
    private String mobile;
    private String email;
    private String productList;

    public Customer() {
    }

    public Customer(long soId, long ppnkId, String soCountCode, String name, String mobile, String email, String productList) {
        this.soId = soId;
        this.ppnkId = ppnkId;
        this.soCountCode = soCountCode;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.productList = productList;
    }

    public long getSoId() {
        return soId;
    }

    public void setSoId(int soId) {
        this.soId = soId;
    }

    public long getPpnkId() {
        return ppnkId;
    }

    public void setPpnkId(int ppnkId) {
        this.ppnkId = ppnkId;
    }

    public String getSoCountCode() {
        return soCountCode;
    }

    public void setSoCountCode(String soCountCode) {
        this.soCountCode = soCountCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "soId=" + soId +
                ", ppnkId=" + ppnkId +
                ", soCountCode='" + soCountCode + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", productList='" + productList + '\'' +
                '}';
    }
}
