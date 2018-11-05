package com.example.cq.mywechat.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.cq.mywechat.Adapters.ChatMsgViewAdapter;
import com.example.cq.mywechat.Entity.ChatMsgEntity;
import com.example.cq.mywechat.R;
import com.example.cq.mywechat.SQLite.DBManager;
import com.example.cq.mywechat.util.DataHandleUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.cq.mywechat.Activity.LoginActivity.flag_thread;
import static com.example.cq.mywechat.Activity.LoginActivity.sk;
import static com.example.cq.mywechat.Activity.LoginActivity.user_id;

public class ChatActivity extends Activity implements View.OnClickListener{

    private Button mBtnSend;// 发送btn
    private ImageView mBtnBack;// 返回btn
    private EditText mEditTextContent;
    private ListView mListView;
    private ChatMsgViewAdapter mAdapter;// 消息视图的Adapter
    private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// 消息对象数组
    private DataHandleUtil dataHandleUtil;
    private int other_id = -1;
    private OutputStream output;
    private InputStream input;
    private Handler handler;
    private String res1;
    private String res2;
    private ChatMsgViewAdapter chatMsgViewAdapter;
    private DBManager dbManager;
    private ChatMsgEntity entity;

    protected void onCreate(Bundle savedInstanceState){
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置窗口没有标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatmain);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {

//                dbManager.addChat(entity.getName(),entity.getMsgdate(),entity.getMessage(),entity.getMsgType());
                initData();
            }
        };

        Intent intent = getIntent();
        other_id=intent.getIntExtra("other_id",other_id);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    if(!flag_thread )
                    {
                        break;
                    }
                    BufferedReader reader= null;
                    try {
                        reader = new BufferedReader(new InputStreamReader(sk.getInputStream()));
                        String resv=reader.readLine();
                        JSONObject jsonObject = null;
                        JSONObject jsonObject1 = null;
                        try {
                            jsonObject = new JSONObject(resv);
                            res1=jsonObject.getString("main");
                            Log.i("res1", res1);
                            jsonObject1 = new JSONObject(res1);
                            res2 = jsonObject1.getString("list");
                            Log.i("res2", res2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //接受消息
                        ChatMsgEntity entity = new ChatMsgEntity();
                        entity.setName("李四");       //自己用户名B
                        entity.setMsgdate(getDate());
                        entity.setMessage(res2);   //发送的信息
                        entity.setMsgType(1);       //判断左右
                        //mDataArrays.add(entity);
                        dbManager.addChat(entity.getName(),entity.getMsgdate(),entity.getMessage(),entity.getMsgType());

                        Message msg=Message.obtain();
                        handler.sendMessage(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                Log.i("asdf", "子线程结束");
            }
        }).start();

        dataHandleUtil = new DataHandleUtil();
        initView();//初始化view
        initData();// 初始化数据
        try {
            output = sk.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //初始化view
    public void initView(){
        mListView = (ListView) findViewById(R.id.listview);
        mBtnSend = (Button) findViewById(R.id.btn_send);
        mBtnSend.setOnClickListener((View.OnClickListener) this);
        mBtnBack =  (ImageView) findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener((View.OnClickListener) this);
        mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
    }

    private void initData() {
        dbManager = new DBManager(this);
        mDataArrays = new ArrayList<>();
        //dbManager.addChat();
        mDataArrays.addAll(dbManager.queryChatMsg());
        chatMsgViewAdapter = new ChatMsgViewAdapter(this, mDataArrays);
        mListView.setAdapter(chatMsgViewAdapter);
        mListView.setSelection(mListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btn_send:// 发送按钮点击事件
                try {
                    send();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i("发送", "发送失败");
                }
                break;
            case R.id.btn_back:// 返回按钮点击事件
                flag_thread = false;
                finish();// 结束,实际开发中，可以返回主界面
                break;
        }
    }

    //发送消息

    private void send() throws IOException {
        String contString = mEditTextContent.getText().toString();
        if (contString.length() > 0) {
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setName("张三");       //自己用户名
            entity.setMsgdate(getDate());
            entity.setMessage(contString);   //发送的信息
            entity.setMsgType(0);       //判断左右
            mDataArrays.add(entity);

            String resv = dataHandleUtil.sendChatContentRequest(user_id  , other_id , contString);

            Log.i("需要发送的消息", resv);
            output.write((resv + "\n").getBytes());
            Log.i("消息已发送", " ");

            dbManager.addChat(entity.getName(),entity.getMsgdate(),entity.getMessage(),entity.getMsgType());
            initData();

 //           mListView.setAdapter(chatMsgViewAdapter);
//            mAdapter.notifyDataSetChanged();// 通知ListView，数据已发生改变
            mEditTextContent.setText("");// 清空编辑框数据
            mListView.setSelection(mListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项
        }

    }
    //发送消息时，获取当前事件，当前时间
    public String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return format.format(new Date());
    }

}