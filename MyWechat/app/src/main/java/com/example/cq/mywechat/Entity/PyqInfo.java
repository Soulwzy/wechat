package com.example.cq.mywechat.Entity;

public class PyqInfo {
    private String userimage;
    private String username;
    private String pyqmsg;

    public PyqInfo(String userimage, String username, String pyqmsg) {
        this.userimage = userimage;
        this.username = username;
        this.pyqmsg = pyqmsg;
    }
    public PyqInfo(String username, String pyqmsg) {
        this.userimage = userimage;
        this.username = username;
        this.pyqmsg = pyqmsg;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPyqmsg() {
        return pyqmsg;
    }

    public void setPyqmsg(String pyqmsg) {
        this.pyqmsg = pyqmsg;
    }
}
