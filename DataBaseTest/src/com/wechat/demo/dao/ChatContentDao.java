package com.wechat.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wechat.demo.bean.ChatContent;
import com.wechat.demo.util.DBconn;



public class ChatContentDao {

	public List<ChatContent> getChatById(int id) {
		List<ChatContent> list = new ArrayList<ChatContent>();
    	try {
		    DBconn.init();
		    //ChatContent(int id, int hostId, int friendId, long insertTime, String content, int state) {
		        
			ResultSet rs = DBconn.selectSql("select * from chat_content where id = "+id);
			while(rs.next()){
				ChatContent ChatContent = new ChatContent(rs.getInt("id"),rs.getInt("host_id"),
						rs.getInt("friend_id"),rs.getLong("insert_time"),rs.getString("content"),rs.getInt("state"));
				list.add(ChatContent);
			}
			DBconn.closeConn();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<ChatContent> getChatByHostId(int hostId) {
		List<ChatContent> list = new ArrayList<ChatContent>();
    	try {
		    DBconn.init();
		    //ChatContent(int id, int hostId, int friendId, long insertTime, String content, int state) {
		        
			ResultSet rs = DBconn.selectSql("select * from chat_content where host_id = "+hostId);
			while(rs.next()){
				ChatContent ChatContent = new ChatContent(rs.getInt("id"),rs.getInt("host_id"),
						rs.getInt("friend_id"),rs.getLong("insert_time"),rs.getString("content"),rs.getInt("state"));
				list.add(ChatContent);
			}
			DBconn.closeConn();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<ChatContent> getChatByFriendId(int friendid) {
		List<ChatContent> list = new ArrayList<ChatContent>();
    	try {
		    DBconn.init();
		    
			ResultSet rs = DBconn.selectSql("select * from chat_content where friend_id = "+friendid);
			while(rs.next()){
				ChatContent ChatContent = new ChatContent(rs.getInt("id"),rs.getInt("host_id"),
						rs.getInt("friend_id"),rs.getLong("insert_time"),rs.getString("content"),rs.getInt("state"));
				list.add(ChatContent);
			}
			DBconn.closeConn();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int insertChatContent(ChatContent chatContent){
   
        String sql = "insert into chat_content " +
                "(host_id,friend_id,insert_time,content,state) " +
                "values("+chatContent.getHostId()+","+chatContent.getFriendId()+
                ","+chatContent.getInsertTime()+",'"+chatContent.getContent()+"',"+chatContent.getState()+")";
        DBconn conn=new DBconn();
        conn.init();
        int i=-1;
        try {
           i=conn.addUpdDel(sql);
           
        } catch (Exception e) {
            e.printStackTrace();
           
        } finally {
            return i;
        }
     }
	
	public boolean insertChatContent(List<ChatContent> chatContents){
		boolean flag=true;
		for (int i = 0; i < chatContents.size(); i++) {
			int j=insertChatContent(chatContents.get(i));
			if(j<=0){
				flag=false;
			}
		}
		return flag;
	}
}
