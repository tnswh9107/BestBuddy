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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Pic_Adapter extends RecyclerView.Adapter{

    Context context;
    ArrayList<Pic_Item> items;

    public Pic_Adapter(Context context,ArrayList<Pic_Item> items){
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.pic_item,parent,false);
        VH holder = new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh=(VH)holder;

        Pic_Item item = items.get(position);

        ((VH) holder).title.setText(item.title);
        ((VH) holder).subtitle.setText(item.subtitle);

        String imgUrl="http://tnswh9107.dothome.co.kr/BuddyPicture/"+item.file;


        Glide.with(context).load(imgUrl).into(((VH) holder).iv);

        if (item.favor==0) ((VH) holder).favor.setChecked(false);
        else ((VH) holder).favor.setChecked(true);
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView title;
        TextView subtitle;
        ToggleButton favor;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.pic_pic);
            title = itemView.findViewById(R.id.pic_title);
            subtitle = itemView.findViewById(R.id.pic_subtitle);
            favor = itemView.findViewById(R.id.pic_favor);

            favor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = getLayoutPosition();
                    Pic_Item item = items.get(position);
//                    item.favor = isChecked? 1:0;

                    Retrofit retrofit = Retrofit_Helper.getRetrofitInstanceGson();
                    Retrofit_Service retrofit_service = retrofit.create(Retrofit_Service.class);
                    Call<Pic_Item> call = retrofit_service.updateDataPicture("updateFavor.php",item);
                    call.enqueue(new Callback<Pic_Item>() {
                        @Override
                        public void onResponse(Call<Pic_Item> call, Response<Pic_Item> response) {
                            Toast.makeText(context, "좋아요", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Pic_Item> call, Throwable t) {

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

                    Intent intent = new Intent(context,Pic_Show.class);
                    intent.putExtra("pic",pic);
                    intent.putExtra("title",title);
                    intent.putExtra("subtitle",subtitle);

                    //클릭시 애니메이션 효과
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
