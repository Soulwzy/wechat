package com.wechat.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.wechat.demo.bean.User;
import com.wechat.demo.util.DBconn;

public class UserDao {
	
    public boolean register(User user) {
        boolean flag = false;
        DBconn.init();
        int i = DBconn.addUpdDel("insert into User(name,pwd,phone,sex) " +
                "values('" + (user.getName()) + "','" + user.getPwd() + "','" + user.getPhone() + "'," + 0 + ")");
        if (i > 0) {
            flag = true;
        }
        DBconn.closeConn();
        return flag;

    }

    public int login(String name, String pwd) {
        int result=0;
        try {
            DBconn.init();
            ResultSet rs = DBconn.selectSql("select * from User where phone='" + name + "' and pwd='" + pwd + "'");
            while (rs.next()) {
                result=rs.getInt("id");
            }
            DBconn.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public User getInfo(int userID){
        User user=new User();
        try {
            DBconn.init();
            ResultSet rs = DBconn.selectSql("select * from User where id=" + userID + "");
            while (rs.next()) {
                user.setId(userID);
                user.setName(rs.getString("name"));
                user.setMcode(rs.getString("mcode"));
                user.setCity(rs.getString("city"));
                user.setProvince(rs.getString("province"));
                user.setSex(rs.getInt("sex"));
                user.setFriendPicPath(rs.getString("friend_pic_path"));
                user.setMotto(rs.getString("motto"));
                user.setHeadPicPath(rs.getString("head_pic_path"));
                user.setPhone(rs.getString("phone"));
            }
            DBconn.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean updateInfo(User user){
        boolean flag=false;
        DBconn.init();
        String sql="update User set name='"+user.getName()+"',mcode='"+user.getMcode()+"',city='"+user.getCity()+"',province='"+user.getProvince()+"',sex="+user.getSex()+",motto='"+user.getMotto()+"',pwd='"+user.getPwd()+"',friend_pic_path='"+user.getFriendPicPath()+"',head_pic_path='"+user.getHeadPicPath()+"' where id="+user.getId();
        int result = DBconn.addUpdDel(sql);
        if(result==1){
            flag=true;
        }
        DBconn.closeConn();

        return flag;


    }






}
