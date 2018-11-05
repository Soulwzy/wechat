package com.example.cq.mywechat.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cq.mywechat.Entity.PyqInfo;
import com.example.cq.mywechat.R;

import java.util.List;

public class PyqAdapter extends BaseAdapter {

    private Context context;
    private List<PyqInfo> pyq;
    private PyqAdapter pyqAdapter;

    public PyqAdapter(Context context,List<PyqInfo> pyq){
        this.context = context;
        this.pyq = pyq;
    }

    @Override
    public int getCount() {
        return pyq.size();
    }

    @Override
    public Object getItem(int position) {
        return pyq.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            //引入布局
            view = View.inflate(context, R.layout.pyq_item,null);
            holder.username = view.findViewById(R.id.tv_username);
            holder.pyqmsg = view.findViewById(R.id.tv_pyqmsg);
            //holder.userimage = view.findViewById(R.id.iv_userimage);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.username.setText(pyq.get(position).getUsername().toString());
        holder.pyqmsg.setText(pyq.get(position).getPyqmsg().toString());

        return view;
    }

    class ViewHolder{
        ImageView userimage;//用户头像
        TextView username;//用户名
        TextView pyqmsg;//朋友圈内容

    }
}
