package com.wechat.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wechat.demo.bean.FriendCircle;
import com.wechat.demo.util.DBconn;

public class FriendCircleDao {


	public FriendCircleDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<FriendCircle> getIdentityFriendCircle(int identityId) {
		List<FriendCircle> list = new ArrayList<FriendCircle>();
    	try {
		    DBconn.init();
			ResultSet rs = DBconn.selectSql("select * from friend_circle where uid = "+identityId+" order by insert_time desc");
			while(rs.next()){
				FriendCircle friendCircle = new FriendCircle();
				friendCircle.setId(rs.getInt("id"));
				friendCircle.setUid(rs.getInt("uid"));
				friendCircle.setContent(rs.getString("content"));
				friendCircle.setInsertTime(rs.getLong("insert_time"));
				friendCircle.setNumGood(rs.getInt("num_good"));
				friendCircle.setPicPaths(rs.getString("pic_paths"));
				list.add(friendCircle);
			}
			DBconn.closeConn();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<FriendCircle> getFriendCircles(int identityId) {
		List<FriendCircle> list = new ArrayList<FriendCircle>();
		try {
			DBconn.init();
			ResultSet rs = DBconn.selectSql("select * from friend_circle where uid in ( select friend_id from user2user where host_id = "+identityId+") order by insert_time desc");
			while(rs.next()){
				FriendCircle friendCircle = new FriendCircle();
				friendCircle.setId(rs.getInt("id"));
				friendCircle.setUid(rs.getInt("uid"));
				friendCircle.setContent(rs.getString("content"));
				friendCircle.setInsertTime(rs.getLong("insert_time"));
				friendCircle.setNumGood(rs.getInt("num_good"));
				friendCircle.setPicPaths(rs.getString("pic_paths"));
				list.add(friendCircle);
			}
			DBconn.closeConn();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
