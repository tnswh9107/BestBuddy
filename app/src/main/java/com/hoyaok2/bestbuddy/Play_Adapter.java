package com.hoyaok2.bestbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Play_Adapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Play_item> items;

    public Play_Adapter(Context context, ArrayList<Play_item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.play_item,parent,false);
        VH holder = new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh=(VH)holder;

        Play_item item = items.get(position);

        ((VH) holder).tvTitle1.setText(item.title);
        ((VH) holder).tvTitle2.setText(item.title2);

//        String imgUrl =

        Glide.with(context).load(item.pic).into(((VH) holder).iv);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }




    class VH extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tvTitle1;
        TextView tvTitle2;
        ToggleButton favor;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.play_pic);
            tvTitle1 = itemView.findViewById(R.id.play_Title);
            tvTitle2 = itemView.findViewById(R.id.play_title2);
            favor = itemView.findViewById(R.id.favor);
        }
    }
}
