package com.example.cq.mywechat.util;

import android.text.TextUtils;

import com.example.cq.mywechat.Entity.ChatMsgEntity;
import com.example.cq.mywechat.Entity.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.json.JSONObject;

public class DataHandleUtil {
    ///////发送请求
    /**
     * 请求注册
     * @param phone
     * @param name
     * @param password
     * @return 注册请求字符串处理
     */
    public String registerRequest(String phone, String name, String password){
        Map<String,Object> main=new HashMap<String,Object>();
        main.put("phone",phone);
        main.put("name",name);
        main.put("password",password);
        String result = getResult(1101,main);
        return result;

    }

    /**
     * 请求登录
     * @param phone
     * @param password
     * @return Token
     */
    public String loginRequest(String phone, String password){
        Map<String,Object> map=new HashMap<>();
        map.put("phone",phone);
        map.put("password",password);
        return getResult(1102,map);
    }
    /**
     * 登录成功字符串处理
     * @param result
     * @return Token
     */
    public boolean loginResponse(String result , int myid){
        try{
            if(!TextUtils.isEmpty(result)){
                org.json.JSONObject jsonObject = new org.json.JSONObject(result);
//                Log.i("888",result);
                int j=jsonObject.getInt("success");
                int id=jsonObject.getInt("id");
                myid=id;
                if (j==1){
                    return true;
                }else {
                    return false;
                }
            }
            return false;
        }catch (JSONException e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 安全退出
     * @param id
     * @return
     */
    public String quitRequest(String id){
        Map<String,Object> map=new HashMap<>();
        map.put("identify_id",id);
        map.put("success",-1);
        return getResult(1103,map);
    }
    /**
     * 请求好友列表
     * @param identifyId 请求人ID
     * @return 好友集合
     */
    public String getFriendListRequest(int identifyId){
        Map<String,Object> map=new HashMap<>();
        map.put("identify_id",identifyId);
        return getResult(1201,map);
    }
    /**
     *  发送加好友请求
     * @param identifyId
     * @param otherId
     * @return
     *//*
    public String getFriendRequest(int identifyId,int otherId ){
        Map<String,Object> map=new HashMap<>();

        map.put("identify_id",identifyId);
        map.put("other_id",otherId);

        return getResult();
    }*/
    /**
     * 发送消息请求
     * @param identifyId
     * @param chatMsgEntity
     * @return
     */
    public String sendChatContentRequest(int identifyId, int other_id,List<ChatMsgEntity> chatMsgEntity){
        Map<String,Object> map=new HashMap<>();
        map.put("identify_id",identifyId);
        map.put("other_id",other_id);
        map.put("list",new JSONArray(chatMsgEntity).toString());
        return getResult(1301,map);
    }

    public String sendChatContentRequest(int identifyId, int other_id,String chatMsgEntity){
        Map<String,Object> map=new HashMap<>();
        map.put("identify_id",identifyId);
        map.put("other_id",other_id);
        map.put("list",chatMsgEntity);
        return getResult(1301,map);
    }
    /**
     *  请求个人朋友圈
     * @param
     * @return
     */
    public String  getFriendCircleRequest(int identifyId, int otherId ){
        Map<String,Object> map=new HashMap<>();
        map.put("identify_id",identifyId);
        map.put("other_id",otherId);
        return getResult(1401,map);
    }
    /**
     *  请求朋友圈
     * @param identifyId
     * @return
     */
    public String getFriendCirclesRequest(int identifyId){
        Map<String,Object> map=new HashMap<>();
        map.put("identify_id",identifyId);
        map.put("flag",1402);
        return getResult(1402,map);
    }
//////////得到数据
    /**
     * 注册返回字符串处理
     * @param result
     * @return 注册结果处理
     */
    public int registerRespones(String result){
        try {
            org.json.JSONObject jsonObject = new org.json.JSONObject(result);
            int success=jsonObject.getInt("success");
            return success;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * 安全退出
     * @param result
     * @return
     */
    public boolean quitResponse(String result){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            if (jsonObject.getInt("success")==1){
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<UserInfo> getFriendListResponse(String result){
        JSONObject jsonObject = null;
        List<UserInfo>lists=new ArrayList<UserInfo>();
        try {
            JSONObject obj=new JSONObject(result);
            String res=obj.getString("list");
            JSONArray jsonArray = new JSONArray(res);
            for(int i=0;i<jsonArray.length();i++){
                UserInfo userInfo=new UserInfo();
                jsonObject=jsonArray.getJSONObject(i);
                userInfo.setId(jsonObject.getInt("id"));
                userInfo.setUserName(jsonObject.getString("name"));
//                userInfo.setCity(jsonObject.getString("city"));
//                friend.setFriendPicPath(jsonObject.getString("friendPicPath"));
//                friend.setHeadPicPath(jsonObject.getString("headPicPath"));
//                friend.setMark(jsonObject.getString("mark"));
//                friend.setMcode(jsonObject.getString("mcode"));
//                friend.setProvince(jsonObject.getString("province"));
                lists.add(userInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lists;
    }

    /**
     * 发送消息请求
     * @param result
     * @return
     */
    public List<ChatMsgEntity> sendChatContentResponse(String result){

//        private String name;//消息来自
//        private String date;//消息日期
//        private String message;//消息内容
//        private boolean isComMeg = true;// 是否为收到的消息

        List<ChatMsgEntity> lists=new ArrayList<ChatMsgEntity>();

        try {
            JSONObject jsonObject = new JSONObject(result);
            String res=jsonObject.getString("list");
            JSONArray jsonArray = new JSONArray(res);
            for(int i=0;i<jsonArray.length();i++){
                //ChatMsgEntity ChatContent=new ChatMsgEntity();
                ChatMsgEntity chatMsgEntity = new ChatMsgEntity();

                jsonObject=jsonArray.getJSONObject(i);

                chatMsgEntity.setMessage(jsonObject.getString("content"));
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                chatMsgEntity.setMsgdate(format2.format(new Date(jsonObject.getLong("insertTime"))));
                if (jsonObject.getInt("isMine")==1){
                    chatMsgEntity.setMsgType(0);

                }else {
                    chatMsgEntity.setMsgType(1);
                }
                lists.add(chatMsgEntity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lists;
    }
    /**
     *  请求个人朋友圈
    // * @param result
     * @return
     */
//    public List<FriendCircle> getFriendCircleResponse(String result){
//        JSONObject jsonObject = null;
//        List<FriendCircle>lists=new ArrayList<FriendCircle>();
//        try {
//
//            JSONArray jsonArray = new JSONArray(result);
//            for(int i=0;i<jsonArray.length();i++){
//                FriendCircle FriendCircle=new FriendCircle();
//                jsonObject=jsonArray.getJSONObject(i);
//                FriendCircle.setId(jsonObject.getInt("id"));
//                FriendCircle.setContent(jsonObject.getString("content"));
//                FriendCircle.setUid(jsonObject.getInt("uid"));
//                FriendCircle.setIsGood(jsonObject.getInt("isGood"));
//                FriendCircle.setInsertTime(jsonObject.getLong("insertTime"));
//                FriendCircle.setNumGood(jsonObject.getInt("numGood"));
//                lists.add(FriendCircle);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return lists;
//
//    }

    private String getResult(int flag, Map<String,Object> main){
        Map<String,Object> map=new HashMap<>();
        map.put("flag",flag);
        map.put("main",main);
        JSONObject jsonObject=new JSONObject(map);
        return jsonObject.toString();
    }


}

