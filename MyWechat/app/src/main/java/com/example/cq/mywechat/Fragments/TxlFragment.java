package com.example.cq.mywechat.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.cq.mywechat.Activity.ChatActivity;
import com.example.cq.mywechat.Adapters.UserAdapter;
import com.example.cq.mywechat.Entity.UserInfo;
import com.example.cq.mywechat.R;
import com.example.cq.mywechat.util.DataHandleUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static com.example.cq.mywechat.Activity.LoginActivity.sk;
import static com.example.cq.mywechat.Activity.LoginActivity.user_id;

public class TxlFragment extends Fragment{

    private ListView lv_txl;
    //private DBManager dbManager;
    private List<UserInfo> user = new ArrayList<>();
    private UserAdapter userAdapter;
    private View view;
    private DataHandleUtil dataHandleUtil;
    private Socket socket;
    int i = 0;
    private Handler handler;
    private ImageView iv_userimage;

    private UserInfo userInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                userAdapter = new UserAdapter(getActivity(),user);
                lv_txl.setAdapter(userAdapter);
            }
        };

        if (view == null){
            view = inflater.inflate(R.layout.txl_layout,container,false);
            Bundle bundle = getArguments();

            lv_txl = view.findViewById(R.id.lv_txl);

            dataHandleUtil = new DataHandleUtil();
            this.socket = sk;

            initData();
            initEvent();


        }else{
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null){
                parent.removeView(view);
            }
        }
        /*final String[] name = new String[]{"李四","王五","赵六","刘十","马八"};
        final int[] photo = new int[]{R.drawable.leishao, R.drawable.xueyan, R.drawable.xiaona, R.drawable.lanshan, R.drawable.yuhong};
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for(int i = 0  ; i < photo.length ; i++) {
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("photo", photo[i]);
            map1.put("name",name[i]);
            data.add(map1);
            lv_txl.setAdapter(new SimpleAdapter(getActivity(), data, R.layout.txl_item, new String[]{"photo","name"}, new int[]{R.id.iv_userimage,R.id.tv_username}));
        }*/
            return view;
    }

    private void initEvent(){

        lv_txl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("cq","CLICKKKKKKKKKKKKKKKK"+position);
                int ID = user.get(position).getId();
                Log.i("对方ID为", ID + "");

                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("other_id",ID);
                intent.setClass(getActivity(),ChatActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData(){


        OutputStream output = null;
        InputStream input = null;
        try {
            output = socket.getOutputStream();
            String str = dataHandleUtil.getFriendListRequest(user_id);
            //Log.i("回传字符串为", str);
            output.write( (str + "\n").getBytes());
            //Log.d("发送好友列表：","STR"+str);
            new Thread(new Runnable() {

                @Override
                public void run() {
                    Log.d("Thread","dddddddd");

                    BufferedReader reader= null;
                    try {
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        //Log.d("开始接受返回字符串","字符串");
                        String resv=reader.readLine();
                        //Log.i("返回字符串", resv);
                        user = dataHandleUtil.getFriendListResponse(resv);

                        Message msg=Message.obtain();
                        handler.sendMessage(msg);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
