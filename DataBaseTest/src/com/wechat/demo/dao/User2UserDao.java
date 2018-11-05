package com.wechat.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wechat.demo.bean.User;
import com.wechat.demo.pojo.Friend;
import com.wechat.demo.util.DBconn;

public class User2UserDao {
    public List<Friend> getFriends(int hostId){
            List<Friend> list=new ArrayList<Friend>();
            UserDao ud=new UserDao();
            User user=new User();
            try {
                DBconn.init();
                ResultSet rs = DBconn.selectSql("select * from User2User where host_id=" + hostId + "");
                while (rs.next()) {
                    Friend friend=new Friend();
                    user=ud.getInfo(rs.getInt("friend_id"));

                    friend.setId(user.getId());
                    friend.setCity(user.getCity());
                    friend.setFriendPicPath(user.getFriendPicPath());
                    friend.setHeadPicPath(user.getHeadPicPath());
                    friend.setMcode(user.getMcode());
                    friend.setMotto(user.getMotto());
                    friend.setName(user.getName());
                    friend.setProvince(user.getProvince());

                   list.add(friend);
                }
                DBconn.closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
            
    }



}
