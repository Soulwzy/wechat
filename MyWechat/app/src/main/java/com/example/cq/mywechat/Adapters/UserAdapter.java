package com.example.cq.mywechat.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cq.mywechat.Entity.UserInfo;
import com.example.cq.mywechat.R;

import java.util.List;

public class UserAdapter extends BaseAdapter{

    private Context context;
    private List<UserInfo> user;
    private UserAdapter userAdapter;
    private UserInfo userInfo;
    private int imageID;

    public UserAdapter(Context context, int resource, List<UserInfo> user){
        this.context = context;
        this.user = user;
    }
    public UserAdapter(Context context,List<UserInfo> user){
        this.context = context;
        this.user = user;
    }

    @Override
    public int getCount() {
        Log.d("cq","getCountTTTTTTTTTTTTT"+user.size());
        return user.size();
    }

    @Override
    public Object getItem(int position) {
        return user.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        //Log.d("cq","getViewGGGGGGGGGGGGGGGGGGGGGG");
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            //引入布局
            view = View.inflate(context, R.layout.txl_item,null);
            //userInfo = user.get(position);
            //imageID = userInfo.getUserImage();
            holder.username = view.findViewById(R.id.tv_username);
            //holder.userimage = view.findViewById(R.id.iv_userimage);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.username.setText(user.get(position).getUserName().toString());
        //holder.userimage.setImageResource(imageID);

        return view;
    }

    class ViewHolder{
        TextView id;//用户id
        TextView username;//用户名
        ImageView userimage;//用户头像
    }
}
