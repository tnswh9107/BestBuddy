package com.hoyaok2.bestbuddy;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
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
        ((VH) holder).beachNm.setText(item.beachNm);
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


                    String beachAddr = items.get(position).beachAddr;;  //????????????
                    String beachNm = items.get(position).beachNm;;    //???????????? ???
                    String beachPhone1= items.get(position).beachPhone1;;     //???????????? ??????
                    String detailInfo= items.get(position).detailInfo;;    //???????????? ??????
                    String homepage= items.get(position).homepage;;    //???????????? ????????????
                    String imagePath1 = items.get(position).imagePath;;    //????????? ??????
                    String mapX= items.get(position).mapX;;    //???x
                    String mapY= items.get(position).mapY;;    //???y
                    String openDate= items.get(position).openDate;;      //???????????? ?????????
                    String traffic= items.get(position).traffic;;     //????????????

//

                    Intent intent = new Intent(context,Api_House_Detail.class);
                    intent.putExtra("beachAddr",beachAddr);
                    intent.putExtra("beachNm",beachNm);
                    intent.putExtra("beachPhone1",beachPhone1);
                    intent.putExtra("detailInfo",detailInfo);
                    intent.putExtra("imagePath",imagePath1);
                    intent.putExtra("homepage",homepage);
                    intent.putExtra("mapX",mapX);
                    intent.putExtra("mapY",mapY);
                    intent.putExtra("openDate",openDate);
                    intent.putExtra("traffic",traffic);


                    //????????? ??????????????? ??????
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity)context,new Pair<View,String>(imagePath,"Yeosu"));
                        context.startActivity(intent,options.toBundle());
                    }else {
                        context.startActivity(intent);
                    }


                }
            });

        }
    }


}
