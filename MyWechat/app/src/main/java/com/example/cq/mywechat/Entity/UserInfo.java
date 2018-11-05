package com.example.cq.mywechat.Entity;

public class UserInfo {

    private int id;
    private String userName;
    private String passWord;
    private int userImage;

    public UserInfo(){}

    public UserInfo(String userName) {
        this.userName = userName;

    }
    public UserInfo(String userName, String passWord, int userImage) {
        this.userName = userName;
        this.passWord = passWord;
        this.userImage = userImage;
    }
    public UserInfo(int id, String userName, String passWord, int userImage) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.userImage = userImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }
}
