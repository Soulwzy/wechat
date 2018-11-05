package com.wechat.demo.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wechat.demo.bean.ChatContent;
import com.wechat.demo.bean.User;
import com.wechat.demo.dao.ChatContentDao;
import com.wechat.demo.dao.FriendCircleDao;
import com.wechat.demo.dao.User2UserDao;
import com.wechat.demo.dao.UserDao;
import com.wechat.demo.pojo.Friend;
import com.wechat.demo.pojo.FriendCircle;

public class DataHandleUtil {
    public static Map<String,String> dataHandle(String receive){
    	if(receive == null)
    	{
    		return null;
    	}
    	Map<String,String> map=new HashMap<String,String>();
    	JSONObject jsonReceive=JSONObject.parseObject(receive);
    	int flag = jsonReceive.getIntValue("flag");
    	String main=jsonReceive.getString("main");
    	JSONObject jsonMain=JSONObject.parseObject(main);
    	int id = 0;
    	String send = "";
    	switch (flag) {
    	//注册
		case 1101:{
			String phone=jsonMain.getString("phone");
			String name=jsonMain.getString("name");
			String password=jsonMain.getString("password");
			User user=new User();
			user.setPhone(phone);
			user.setName(name);
			user.setPwd(password);
			boolean result=new UserDao().register(user);
			map.put("success", "0");
			break;
		}
		//登录
		case 1102:{
			String phone=jsonMain.getString("phone");
			String password=jsonMain.getString("password");
			id=new UserDao().login(phone, password);
			Map<String,String> sendMap=new HashMap<String,String>();
			if(id <= 0)                                                                //************************
			{
				map.put("success", "0");
				sendMap.put("success", "0"); 
			}
			else {
				map.put("success", "1");
				sendMap.put("success", "1"); 
			}                                                                                 //************************
			map.put("id", id+"");
			
			sendMap.put("id", id+"");
			map.put("send", JSON.toJSONString(sendMap));
			break;
		}
		//安全退出
		case 1103:{
			map.put("success", "-1");
			break;
		}
		//好友列表
		case 1201:{
			id=jsonMain.getIntValue("identify_id");
			List<Friend> friends=new User2UserDao().getFriends(id);
			User user=new UserDao().getInfo(id);
			Friend friend=new Friend();
			 friend.setId(user.getId());
             friend.setCity(user.getCity());
             friend.setFriendPicPath(user.getFriendPicPath());
             friend.setHeadPicPath(user.getHeadPicPath());
             friend.setMcode(user.getMcode());
             friend.setMotto(user.getMotto());
             friend.setName(user.getName());
             friend.setProvince(user.getProvince());
            friends.add(friend);
			map.put("id", id+"");
			Map<String,String> sendMap=new HashMap<String,String>();
			sendMap.put("success", "1");
			sendMap.put("list", JSON.toJSONString(friends));
			map.put("send", JSON.toJSONString(sendMap));
			break;
		}	
		//加好友
		case 1202:{
			
			break;
		}
		//请求加好友状态
		case 1203:{
			
			break;
		}	
		//发送消息
		case 1301:{
//			id=jsonMain.getIntValue("identify_id");
//			String list=jsonMain.getString("list");
//			List<ChatContent> chatContents=JSON.parseArray(list, ChatContent.class);
//			int otherId=chatContents.get(0).getFriendId();
//			boolean result=new ChatContentDao().insertChatContent(chatContents);
//			map.put("id", id+"");
//			map.put("other_id", otherId+"");
//			Map<String,String> sendMap=new HashMap<String,String>();
//			if (result) {
//				sendMap.put("success", "1");
//				sendMap.put("list", JSON.toJSONString(chatContents));
//			} else {
//				sendMap.put("success", "0");
//			}
//			map.put("send", JSON.toJSONString(sendMap));
//			break;
		}	
		//接收消息
		case 1302:{
			id=jsonMain.getIntValue("identify_id");
			List<ChatContent> chatContents=new ChatContentDao().getChatByHostId(id);
			map.put("id", id+"");
			Map<String,String> sendMap=new HashMap<String,String>();
			sendMap.put("success", "1");
			sendMap.put("list", JSON.toJSONString(chatContents));
			map.put("send", JSON.toJSONString(sendMap));
			break;
		}	
		//个人朋友圈
		case 1401:{
			id = jsonMain.getIntValue("identify_id");
			int otherId = jsonMain.getIntValue("other_id");
			List<com.wechat.demo.bean.FriendCircle> friendCircles=new FriendCircleDao().getIdentityFriendCircle(otherId);
			map.put("id", id+"");
			Map<String,String> sendMap=new HashMap<String,String>();
			sendMap.put("success", "1");
			sendMap.put("list", JSON.toJSONString(friendCircles));
			map.put("send", JSON.toJSONString(sendMap));
			break;
		}
		//朋友圈
		case 1402:{
			id = jsonMain.getIntValue("id");
			List<com.wechat.demo.bean.FriendCircle> friendCircles=new FriendCircleDao().getFriendCircles(id);
			map.put("id", id+"");
			Map<String,String> sendMap=new HashMap<String,String>();
			sendMap.put("success", "1");
			sendMap.put("list", JSON.toJSONString(friendCircles));
			map.put("send", JSON.toJSONString(sendMap));
			break;
		}
		//发送朋友圈
		case 1403:{
			return null;
//			break;
		}	

		default:
			break;
		}
    	return map;
    }
}
