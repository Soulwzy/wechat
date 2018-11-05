package com.example.cq.mywechat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cq.mywechat.Entity.ChatMsgEntity;
import com.example.cq.mywechat.R;

import java.util.List;

public class ChatMsgViewAdapter extends BaseAdapter {
    public static interface IMsgViewType{
        int IMVT_COM_MSG = 0;// 收到对方的消息
        int IMVT_TO_MSG = 1;// 自己发送出去的消息
    }
    private static final int ITEMCOUNT = 2;// 消息类型的总数
    private List<ChatMsgEntity> coll;// 消息对象数组
    private LayoutInflater mInflater;
    //private int count;

    public ChatMsgViewAdapter(Context context, List<ChatMsgEntity> coll) {
        this.coll = coll;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return coll.size();
    }

    @Override
    public Object getItem(int position) {
        return coll.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    //得到Item类型，是对方发过来的消息，还是自己发出去的消息
    public int getItemViewType(int position){
        ChatMsgEntity entity = coll.get(position);
        if (entity.getMsgType()==1) {
            //收到的消息
            return 1;
        } else {
            //自己发送的消息
            return 0;
        }

    }

    //Item类型的总数
    public int getViewTypeCount(){
        return ITEMCOUNT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMsgEntity entity = coll.get(position);
        int isComMsg = entity.getMsgType();
        ViewHolder viewHolder = null;
        if (convertView == null){
            if (isComMsg==1){
                convertView = mInflater.inflate(
                        R.layout.chatting_item_msg_text_left, null);
            }else if(isComMsg == 0) {
                convertView = mInflater.inflate(
                        R.layout.chatting_item_msg_text_right, null);
            }
            viewHolder = new ViewHolder();
            viewHolder.tvSendTime = (TextView) convertView
                    .findViewById(R.id.tv_sendtime);
            viewHolder.tvUserName = (TextView) convertView
                    .findViewById(R.id.tv_username);
            viewHolder.tvContent = (TextView) convertView
                    .findViewById(R.id.tv_chatcontent);

            //viewHolder.isComMsg = isComMsg;
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvSendTime.setText(entity.getMsgdate());
        viewHolder.tvUserName.setText(entity.getName());
        viewHolder.tvContent.setText(entity.getMessage());
        return convertView;
    }
    static class ViewHolder{
        public TextView tvSendTime;
        public TextView tvUserName;
        public TextView tvContent;
    }

    public void notifyDataSetChanged() {
    }
}
