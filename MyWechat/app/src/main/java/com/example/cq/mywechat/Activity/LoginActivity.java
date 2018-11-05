package com.example.cq.mywechat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cq.mywechat.R;
import com.example.cq.mywechat.util.DataHandleUtil;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class LoginActivity extends AppCompatActivity {
    private Button btn_login;
    private EditText username;
    private EditText passward;
    public static Socket sk;
    public static int user_id;
    public static boolean flag_thread = true;
    //public int id = -1;
    DataHandleUtil dataHandleUtil;
    private Handler handler;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        dataHandleUtil = new DataHandleUtil();

        initView();
        initEvent();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //tv.setText(msg.getData().getString("info"));
                Toast.makeText(LoginActivity.this,msg.getData().getString("info"),Toast.LENGTH_LONG).show();
            }
        };
    }

    public void initView(){
        btn_login = findViewById(R.id.btn_login);
        username = findViewById(R.id.tv_user);
        passward = findViewById(R.id.tv_pass);

    }

    private void initEvent() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InetAddress addr= InetAddress.getByName("59.48.241.18");
                    sk=new Socket(addr,9000);
                    while(true){
                        BufferedReader reader=new BufferedReader(new InputStreamReader(sk.getInputStream()));
                        String resv=reader.readLine();
                        Log.i("返回字符串", resv);
                        if(resv != null)
                        {
                            boolean flg = loginResponse(resv);
                            Log.i("flg", flg + "");

                            if(flg)
                            {
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.setClass(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            }
                            else
                            {
                                Message msg=Message.obtain();
                                msg.getData().putString("info","用户名或密码不正确");
                                handler.sendMessage(msg);
                                //Log.i("else", "here");
                            }
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //点击登录
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OutputStream output = null;
                try {
                    output = sk.getOutputStream();
                    String str = dataHandleUtil.loginRequest(username.getText().toString(),passward.getText().toString());

                    //Log.i("回传字符串为", str);
                    output.write( (str + "\n").getBytes());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean loginResponse(String result){
        try{
            if(!TextUtils.isEmpty(result)){
                org.json.JSONObject jsonObject = new org.json.JSONObject(result);
//                Log.i("888",result);
                int j=jsonObject.getInt("success");
                int id=jsonObject.getInt("id");
                user_id = id;
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
    
}
