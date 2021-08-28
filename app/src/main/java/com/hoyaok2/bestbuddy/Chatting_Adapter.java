package com.hoyaok2.bestbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chatting_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Chatting_item> items;

    public Chatting_Adapter(Context context, ArrayList<Chatting_item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //현재 보여줄 x번쨰 (position)의 데이터 얻어오기
        Chatting_item item = items.get(position);

        //재활용할 뷰[converView]는 사용 하지 않을 것임
        View itemView=null;
        LayoutInflater inflater = LayoutInflater.from(context);

        //메세지 주인
        if(item.name.equals(G.nickname)){
            itemView = inflater.inflate(R.layout.mymessage,parent,false);
        }else {
            itemView = inflater.inflate(R.layout.other_message,parent,false);
        }

        CircleImageView civ=itemView.findViewById(R.id.naviv);
        TextView tvName = itemView.findViewById(R.id.tv_name);
        TextView tvMsg = itemView.findViewById(R.id.tv_msg);
        TextView tvTime = itemView.findViewById(R.id.tv_time);

        tvName.setText(item.name);
        tvMsg.setText(item.message);
        tvTime.setText(item.visualtime);

        Glide.with(context).load(item.profileUrl).into(civ);

        return itemView;
    }
}
