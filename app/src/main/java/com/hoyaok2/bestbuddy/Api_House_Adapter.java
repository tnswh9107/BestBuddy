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

public class Api_House_Adapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Api_House_recyclerItem> items;

    public Api_House_Adapter(Context context, ArrayList<Api_House_recyclerItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.api_house_recycleritem,parent,false);
        VH holder = new VH(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;


        Api_House_recyclerItem item = items.get(position);
        ((VH) holder).beachNm.setText(item.beachNm+"의 일상");
        ((VH) holder).beachAddr.setText(item.beachAddr);
        Glide.with(context).load(item.imagePath).into(((VH) holder).imagePath);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView imagePath;
        TextView beachNm;
        TextView beachAddr;
        ToggleButton tbFavor;

        public VH(@NonNull View itemView) {
            super(itemView);

            imagePath = itemView.findViewById(R.id.apihouse_iv);
            beachNm = itemView.findViewById(R.id.apihouse_name);
            beachAddr = itemView.findViewById(R.id.apihouse_lineintro);
            tbFavor = itemView.findViewById(R.id.apihouse_tbfavor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getLayoutPosition();

                    String nameId = items.get(position).beachNm; //상호명
                    String lineintroId = items.get(position).beachAddr;    //소개
                    String houseingimgid = items.get(position).imagePath;    //이미지
//

//                    Intent intent = new Intent(context)
                }
            });


        }
    }


}
