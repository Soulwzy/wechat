package com.example.cq.mywechat.Entity;

public class ChatMsgEntity{
    private String name;//消息来自
    private String msgdate;//消息日期
    private String message;//消息内容
    private int isComMeg ;// 是否为收到的消息

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMsgdate() {
        return msgdate;
    }

    public void setMsgdate(String msgdate) {
        this.msgdate = msgdate;
    }

    public int getMsgType(){
        return isComMeg;
    }
    public void setMsgType(int isComMsg) {
        isComMeg = isComMsg;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public ChatMsgEntity(){

    }

    public ChatMsgEntity(String name,String msgdate,String text, int isComMsg){
        super();
        this.name = name;
        this.msgdate = msgdate;
        this.message = text;
        this.isComMeg = isComMsg;
    }
}

