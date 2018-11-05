package com.wechat.demo.bean;

public class User {
    private int id;
    private String name;
    private String mcode;
    private String headPicPath;
    private String friendPicPath;
    private String province;
    private String city;
    private String motto;
    private String phone;
    private String pwd;
    private int sex;


    public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id,String name,String mcode,String headPicPath,String friendPicPath,String province,String city,String motto,String phone,String pwd,int sex){
        this.id=id;
        this.name=name;
        this.mcode=mcode;
        this.headPicPath=headPicPath;
        this.friendPicPath=friendPicPath;
        this.province=province;
        this.city=city;
        this.motto=motto;
        this.phone=phone;
        this.pwd=pwd;
        this.sex=sex;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
