package com.hoyaok2.bestbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Play_Show extends AppCompatActivity {


    ImageView showiv;
    TextView showtitle,showsub,showprice,showphone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play__show);

        showiv = findViewById(R.id.play_show_pic);
        showtitle = findViewById(R.id.play_show_name);
        showsub = findViewById(R.id.play_show_story);
        showprice = findViewById(R.id.play_pay);
        showphone = findViewById(R.id.play_phon);

        Intent intent = getIntent();

        String pic = intent.getStringExtra("pic");
        String title = intent.getStringExtra("title");
        String subtitle = intent.getStringExtra("subtitle");
        String price = intent.getStringExtra("price");
        String phone = intent.getStringExtra("phone");




        String imgUrl = "http://tnswh9107.dothome.co.kr/BuddyPlay/"+pic;

        Log.d("test",imgUrl);

//        Log.d("쪼매니 혜니",)

        if (imgUrl==null) Glide.with(this).load(R.drawable.noimage).into(showiv);
        else Glide.with(this).load(imgUrl).into(showiv);

        showtitle.setText(title);
        showsub.setText(subtitle);
        showprice.setText(price);
        showphone.setText(phone);

        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            showiv.setTransitionName("PlayShow");
        }




    }
}