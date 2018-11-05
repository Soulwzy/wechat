package com.example.cq.mywechat.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cq.mywechat.Entity.ChatMsgEntity;
import com.example.cq.mywechat.Entity.PyqInfo;
import com.example.cq.mywechat.Entity.UserInfo;

import java.util.ArrayList;

public class DBManager {
    private MyHelper myHelper;
    private MyHelperPyq myHelperPyq;
    private MyHelperChat myHelperChat;
    private SQLiteDatabase db;
    private SQLiteDatabase db1;

    //朋友圈
    private SQLiteDatabase db2;
    private SQLiteDatabase db3;

    //聊天界面
    private SQLiteDatabase db4;
    private SQLiteDatabase db5;

    public DBManager(Context context){
        myHelper = new MyHelper(context);
        myHelperPyq = new MyHelperPyq(context);
        myHelperChat = new MyHelperChat(context);

        db = myHelper.getWritableDatabase();
        db1 = myHelper.getReadableDatabase();

        db2 = myHelperPyq.getWritableDatabase();
        db3 = myHelperPyq.getReadableDatabase();

        db4 = myHelperChat.getWritableDatabase();
        db5 = myHelperChat.getReadableDatabase();

    }

   //添加好友
    public void add(){
        Log.d("cq","AAAADDDDDDDDDDDD");
        ContentValues cv = new ContentValues();
        cv.put("username","优秀");
        cv.put("password","12345");
        cv.put("userimage","http://i.gtimg.cn/music/photo/mid_album_300/l/o/003y8dsH2wBHlo.jpg");

        db.insert("userinfo",null,cv);
    }

    //查询好友
    public ArrayList<UserInfo> queryUser(){
        Log.d("cq", "QURRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRUser: ");
        String sql = "select * from userinfo";
        ArrayList<UserInfo> user = new ArrayList<>();
        Cursor c = db1.rawQuery(sql,null);
        while(c.moveToNext()){
            String username = c.getString(c.getColumnIndex("username"));
            user.add(new UserInfo(username));
        }

        return user;
    }


   //添加朋友圈
   public void addPyq(){
       Log.d("cq","AAAADDDDDDDDDDDD");
       ContentValues cv = new ContentValues();
       /*cv.put("username","优秀");
       cv.put("pyqmsg","我喜欢随遇而安，如果你想饱览天下美景，享受飞翔的快感，那就躲到我的怀里来，跟我去远方。");

       cv.put("userimage","http://i.gtimg.cn/music/photo/mid_album_300/l/o/003y8dsH2wBHlo.jpg");
*/
       cv.put("username","优秀");
       cv.put("pyqmsg","我想爱情大概是每天睁开眼的第一个亲吻，和每天睡前的最后一个晚安！");

       cv.put("userimage","http://i.gtimg.cn/music/photo/mid_album_300/l/o/003y8dsH2wBHlo.jpg");


       db2.insert("pyqmsg",null,cv);
   }

    //查询朋友圈
    public ArrayList<PyqInfo> queryMsg(){
        Log.d("cq", "QURRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRPYQ: ");
        String sql = "select * from pyqmsg";
        ArrayList<PyqInfo> pyq = new ArrayList<>();
        Cursor c = db3.rawQuery(sql,null);
        while(c.moveToNext()){
            String username = c.getString(c.getColumnIndex("username"));
            String pyqmsg = c.getString(c.getColumnIndex("pyqmsg"));
            pyq.add(new PyqInfo(username,pyqmsg));
        }
        return pyq;
    }

   //添加聊天记录
   public void addChat(){
       Log.d("cq","聊天记录");
       ContentValues cv = new ContentValues();

       cv.put("username","李四");
       cv.put("msgdate","2018-11-03 10:28");
       cv.put("maincontent","哈哈哈哈哈哈哈哈");
       cv.put("iscommeg",1);

       /*cv.put("username","张三");
       cv.put("msgdate","2018-11-03 10:26");
       cv.put("maincontent","A:你们家经济条件怎么样？\n B:我爸开的兰博基尼，我妈开的法拉利\n A:那你开的什么？\nB:我。。。我开玩笑。。。");
       cv.put("iscommeg",0);*/

       Log.d("插入聊天记录","rr"+cv.toString());
       db4.insert("chatmsg_a",null,cv);
   }

   public void addChat(String username,String msgdate,String maincontent,int iscommeg){
       ContentValues cv = new ContentValues();
       cv.put("username",username);
       cv.put("msgdate",msgdate);
       cv.put("maincontent",maincontent);
       cv.put("iscommeg",iscommeg);

       db4.insert("chatmsg_a",null,cv);

   }

    //查询聊天记录
    public ArrayList<ChatMsgEntity> queryChatMsg(){
        Log.d("cq", "QURRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRPYQ: ");
        String sql = "select * from chatmsg_a";
        ArrayList<ChatMsgEntity> msginfo = new ArrayList<>();
        Cursor c = db5.rawQuery(sql,null);
        while(c.moveToNext()){
            String username = c.getString(c.getColumnIndex("username"));
            String msgdate = c.getString(c.getColumnIndex("msgdate"));
            String maincontent = c.getString(c.getColumnIndex("maincontent"));
            int iscommeg = c.getInt(c.getColumnIndex("iscommeg"));

            msginfo.add(new ChatMsgEntity(username,msgdate,maincontent,iscommeg));
        }
        return msginfo;
    }


}
