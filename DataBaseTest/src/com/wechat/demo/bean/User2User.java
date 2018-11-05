package com.wechat.demo.bean;

public class User2User {
    private int id;
    private int hostId;
    private int friendId;
    private String mark;

    public User2User(int id, int hostId, int friendId, String mark) {
        this.id = id;
        this.hostId = hostId;
        this.friendId = friendId;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
