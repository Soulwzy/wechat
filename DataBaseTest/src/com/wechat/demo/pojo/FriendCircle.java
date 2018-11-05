package com.wechat.demo.pojo;

public class FriendCircle {
    private int id;
    private int uid;
    private String content;
    private long insertTime;
    private int isGood;
    private int numGood;
    private String picPaths;

    public FriendCircle() {
    }

    public FriendCircle(int id, int uid, String content, long insertTime, int isGood, int numGood, String picPaths) {
        this.id = id;
        this.uid = uid;
        this.content = content;
        this.insertTime = insertTime;
        this.isGood = isGood;
        this.numGood = numGood;
        this.picPaths = picPaths;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public int getIsGood() {
        return isGood;
    }

    public void setIsGood(int isGood) {
        this.isGood = isGood;
    }

    public int getNumGood() {
        return numGood;
    }

    public void setNumGood(int numGood) {
        this.numGood = numGood;
    }

    public String getPicPaths() {
        return picPaths;
    }

    public void setPicPaths(String picPaths) {
        this.picPaths = picPaths;
    }
}
