package com.wechat.demo.bean;

public class ChatContent {
    private int id;
    private int hostId;
    private int friendId;
    private long insertTime;
    private String content;
    private int state;

    public ChatContent(int id, int hostId, int friendId, long insertTime, String content, int state) {
        this.id = id;
        this.hostId = hostId;
        this.friendId = friendId;
        this.insertTime = insertTime;
        this.content = content;
        this.state = state;
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

    public long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
