package com.hoyaok2.bestbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class Pic_Show extends AppCompatActivity {

    CircleImageView picprofile;
    ImageView piciv;
    TextView pictitle, picsub, picnickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic__show);

        picprofile = findViewById(R.id.pic_profile);
        piciv = findViewById(R.id.pic_show_pic);
        picnickname = findViewById(R.id.pic_nickname);
        pictitle = findViewById(R.id.pic_title);
        picsub = findViewById(R.id.pic_subtitle);

        Intent intent = getIntent();

        String pic = intent.getStringExtra("pic");
        String title = intent.getStringExtra("title");
        String subtitle = intent.getStringExtra("subtitle");
        String usernickname = intent.getStringExtra("usernicname");
        String userprofile = intent.getStringExtra("userprofile");

        String imgUrl = "http://tnswh9107.dothome.co.kr/BuddyPicture/" + pic;

        if (imgUrl == null) Glide.with(this).load(R.drawable.noimage).into(piciv);
        else Glide.with(this).load(imgUrl).into(piciv);

        pictitle.setText(title);
        picsub.setText(subtitle);
        picnickname.setText(usernickname);
        Glide.with(this).load(userprofile).into(picprofile);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            piciv.setTransitionName("PicShow");
        }

    }
}