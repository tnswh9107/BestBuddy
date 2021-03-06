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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        ((VH) holder).tvTitle2.setText(item.subtitle);

        String imgUrl="http://tnswh9107.dothome.co.kr/BuddyPlay/"+item.file;


        Glide.with(context).load(imgUrl).into(((VH) holder).iv);

        if (item.favor==0) ((VH) holder).favor.setChecked(false);
        else ((VH) holder).favor.setChecked(true);

    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }




    class VH extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tvTitle1;
        TextView tvTitle2;
        TextView tvnictitle;
        ToggleButton favor;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.play_pic);
            tvTitle1 = itemView.findViewById(R.id.play_Title);
            tvTitle2 = itemView.findViewById(R.id.play_title2);
            favor = itemView.findViewById(R.id.favor);


            //?????????
            favor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int positon = getLayoutPosition();
                    Play_item item = items.get(positon);
                    item.favor = isChecked? 1:0;

                    Retrofit retrofit =Retrofit_Helper.getRetrofitInstanceGson();
                    Retrofit_Service retrofit_service = retrofit.create(Retrofit_Service.class);
                    Call<Play_item> call = retrofit_service.updataDataPlay("updateFavor.php",item);
                    call.enqueue(new Callback<Play_item>() {
                        @Override
                        public void onResponse(Call<Play_item> call, Response<Play_item> response) {
                            Toast.makeText(context, "?????????", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Play_item> call, Throwable t) {

                        }
                    });
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();

                    String pic = items.get(position).file;
                    String title = items.get(position).title;
                    String subtitle = items.get(position).subtitle;
                    String price = items.get(position).price;
                    String phone = items.get(position).phone;
                    String usernickname = items.get(position).usernickname;
                    String userprofile = items.get(position).userprofile;




                    Intent intent = new Intent(context,Play_Show.class);
                    intent.putExtra("pic",pic);
                    intent.putExtra("title",title);
                    intent.putExtra("subtitle",subtitle);
                    intent.putExtra("price",price);
                    intent.putExtra("phone",phone);
                    intent.putExtra("usernickname",usernickname);
                    intent.putExtra("userprofile",userprofile);


                    //????????? ??????????????? ??????
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity)context,new Pair<View,String>(iv,"PlayShow"));
                        context.startActivity(intent,options.toBundle());
                    }else {
                        context.startActivity(intent);
                    }


                }
            });

        }
    }



}
