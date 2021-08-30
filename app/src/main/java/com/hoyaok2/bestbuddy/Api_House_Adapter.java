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


        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView houseImg;
        TextView name;
        TextView lineintro;
        ToggleButton tbFavor;

        public VH(@NonNull View itemView) {
            super(itemView);

            houseImg = itemView.findViewById(R.id.apihouse_iv);
            name = itemView.findViewById(R.id.apihouse_name);
            lineintro = itemView.findViewById(R.id.apihouse_lineintro);
            tbFavor = itemView.findViewById(R.id.apihouse_tbfavor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getLayoutPosition();

                    String nameId = items.get(position).name; //상호명
                    String lineintroId = items.get(position).lineintro;    //소개
                    String houseingimgid = items.get(position).houseingimg;    //이미지
                    String induty = items.get(position).induty;    //업종
                    String addr1 = items.get(position).addr1;    //주소
                    String addr2 = items.get(position).addr2;    //상세주소
                    String mapX = items.get(position).mapX;    //맵x
                    String mapY = items.get(position).mapY;    //맵y

//                    Intent intent = new Intent(context)
                }
            });


        }
    }


}
