package com.wechat.demo.pojo;


public class ChatList {
    private int id;
    private int fid;
    private long lastTime;

    public ChatList() {
    }

    public ChatList(int id, int fid, long lastTime) {
        this.id = id;
        this.fid = fid;
        this.lastTime = lastTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }
}
