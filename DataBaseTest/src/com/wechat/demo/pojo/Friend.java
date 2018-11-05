package com.wechat.demo.pojo;


public class Friend {
    private int id;
    private String name;
    private String mcode;
    private String mark;
    private String headPicPath;
    private String friendPicPath;
    private String province;
    private String city;
    private String motto;

    public Friend() {
    }

    public Friend(int id, String name, String mcode, String mark, String headPicPath, String friendPicPath, String province, String city, String motto) {
        this.id = id;
        this.name = name;
        this.mcode = mcode;
        this.mark = mark;
        this.headPicPath = headPicPath;
        this.friendPicPath = friendPicPath;
        this.province = province;
        this.city = city;
        this.motto = motto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMcode() {
        return mcode;
    }

    public void setMcode(String mcode) {
        this.mcode = mcode;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getHeadPicPath() {
        return headPicPath;
    }

    public void setHeadPicPath(String headPicPath) {
        this.headPicPath = headPicPath;
    }

    public String getFriendPicPath() {
        return friendPicPath;
    }

    public void setFriendPicPath(String friendPicPath) {
        this.friendPicPath = friendPicPath;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }
}
