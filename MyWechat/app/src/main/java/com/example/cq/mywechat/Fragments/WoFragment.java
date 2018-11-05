package com.example.cq.mywechat.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cq.mywechat.Activity.LoginActivity;
import com.example.cq.mywechat.R;

public class WoFragment extends Fragment {

    private Button btn_back;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.wo_layout,container,false);
        initView();
        initEvent();

        return view;
    }

    public void initView(){
        btn_back = view.findViewById(R.id.btn_back);
}
    public void initEvent(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                public String quitRequest(String id){
//                    Map<String,Object> map=new HashMap<>();
//                    map.put("identify_id",id);
//                    map.put("success",-1);
//                    return getResult(1103,map);
//                }
//                Map<String,Object> map=new HashMap<>();
//                map.put("flag",flag);
//                map.put("main",main);
//                JSONObject jsonObject=new JSONObject(map);
//                return jsonObject.toString();

//                Map<String,Object> map=new HashMap<>();
//                map.put("flag",1103);
//                map.put("main",null);
//                String str = new JSONObject(map).toString();
//                Log.i("str", str);
//                OutputStream outputStream = null;
//                try {
//                    outputStream = sk.getOutputStream();
//                    outputStream.write((str + "\n").getBytes());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }
        });
    }

}
