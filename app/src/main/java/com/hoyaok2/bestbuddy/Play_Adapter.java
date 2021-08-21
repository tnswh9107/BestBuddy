package com.hoyaok2.bestbuddy;

import android.content.Context;
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

//        String imgUrl =

        Glide.with(context).load(item.pic).into(((VH) holder).iv);

        if (item.favor==0) ((VH) holder).favor.setChecked(false);
        else ((VH) holder).favor.setChecked(true);

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

            //좋아요
            favor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int positon = getLayoutPosition();
                    Play_item item = items.get(positon);
                    item.favor = isChecked? 1:0;

                    Retrofit retrofit =Retrofit_Helper.getRetrofitInstanceGson();
                    Retrofit_Service retrofit_service = retrofit.create(Retrofit_Service.class);
                    Call<Play_item> call = retrofit_service.updateData("updateFavor.php",item);
                    call.enqueue(new Callback<Play_item>() {
                        @Override
                        public void onResponse(Call<Play_item> call, Response<Play_item> response) {
                            Toast.makeText(context, "좋아요", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Play_item> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }



}
