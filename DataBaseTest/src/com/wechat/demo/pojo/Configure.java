package com.wechat.demo.pojo;


public class Configure {
    private int id;
    private String mcode;
    private String phone;
    private String token;

    public Configure() {
    }

    public Configure(int id, String mcode, String phone, String token) {
        this.id = id;
        this.mcode = mcode;
        this.phone = phone;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMcode() {
        return mcode;
    }

    public void setMcode(String mcode) {
        this.mcode = mcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
