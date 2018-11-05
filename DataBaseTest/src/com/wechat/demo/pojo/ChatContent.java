package com.wechat.demo.pojo;

public class ChatContent {
    private int id;
    private int cid;
    private int isMine;
    private long insertTime;
    private String content;
    private int state;

    public ChatContent() {
    }

    public ChatContent(int id, int cid, int isMine, long insertTime, String content, int state) {
        this.id = id;
        this.cid = cid;
        this.isMine = isMine;
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

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getIsMine() {
        return isMine;
    }

    public void setIsMine(int isMine) {
        this.isMine = isMine;
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
