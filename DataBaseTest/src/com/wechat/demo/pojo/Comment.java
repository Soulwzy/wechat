package com.wechat.demo.pojo;


public class Comment {
    private int id;
    private int fcId;
    private int sUId;
    private int rUId;
    private String content;
    private long insertTime;

    public Comment() {
    }

    public Comment(int id, int fcId, int sUId, int rUId, String content, long insertTime) {
        this.id = id;
        this.fcId = fcId;
        this.sUId = sUId;
        this.rUId = rUId;
        this.content = content;
        this.insertTime = insertTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFcId() {
        return fcId;
    }

    public void setFcId(int fcId) {
        this.fcId = fcId;
    }

    public int getsUId() {
        return sUId;
    }

    public void setsUId(int sUId) {
        this.sUId = sUId;
    }

    public int getrUId() {
        return rUId;
    }

    public void setrUId(int rUId) {
        this.rUId = rUId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
    }
}
