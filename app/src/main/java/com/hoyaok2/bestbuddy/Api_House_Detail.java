package com.hoyaok2.bestbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Api_House_Detail extends AppCompatActivity {

    TextView beachAddr;  //지역주소
    TextView beachNm;    //해수욕장 명
    TextView beachPhone;     //해수욕장 번호
    TextView detailInfo;    //해수욕장 설명
    TextView homepage;    //해수욕장 홈페이지
    ImageView imagePath;    //이미지 사진
    String mapX;    //맵x
    String mapY;    //맵y
    TextView openDate;      //해수욕장 오픈일
    TextView traffic;     //오는방법
    String beachName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api__house__detail);

        beachAddr = findViewById(R.id.yeosu_beachAddr);  //지역주소
        beachNm = findViewById(R.id.yeosu_beachNm);    //해수욕장 명
        beachPhone = findViewById(R.id.yeosu_beachPhone1);     //해수욕장 번호
        detailInfo = findViewById(R.id.yeosu_detailInfo);    //해수욕장 설명
        homepage = findViewById(R.id.yeosu_homepage);    //해수욕장 홈페이지
        imagePath = findViewById(R.id.yeosu_imagePath);    //이미지 사진
        openDate = findViewById(R.id.yeosu_openDate);      //해수욕장 오픈일
        traffic = findViewById(R.id.yeosu_traffic);     //오는방법


        Intent intent = getIntent();

        String beachAddr1 = intent.getStringExtra("beachAddr");  //지역주소
        String beachNm1 = intent.getStringExtra("beachNm");    //해수욕장 명
        String beachPhone1 = intent.getStringExtra("beachPhone1");     //해수욕장 번호
        String detailInfo1 = intent.getStringExtra("detailInfo");    //해수욕장 설명
        String homepage1 = intent.getStringExtra("homepage");    //해수욕장 홈페이지
        String imagePath1 = intent.getStringExtra("imagePath1");    //이미지 사진
        String openDate1 = intent.getStringExtra("openDate");      //해수욕장 오픈일
        String traffic1 = intent.getStringExtra("traffic");     //오는방법
        mapX = intent.getStringExtra("mapX");     //mapx
        mapY = intent.getStringExtra("mapY");     //mapy

        if (imagePath1==null) Glide.with(this).load(R.drawable.noimage).into(imagePath);
        else Glide.with(this).load(imagePath1).into(imagePath);


        beachAddr.setText(beachAddr1);
        beachNm.setText(beachNm1);
        beachPhone.setText(beachPhone1);
        detailInfo.setText(detailInfo1);
        homepage.setText(homepage1);
        openDate.setText(openDate1);
        traffic.setText(traffic1);




        //클릭시 애니메이션 효과
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            imagePath.setTransitionName("Yeosu");
        }

    }


    public void house_detailmap(View view) {
        Intent intent = new Intent(Api_House_Detail.this, Api_House_DetailMap.class);
        intent.putExtra("mapx", mapX);
        intent.putExtra("mapy", mapY);
        intent.putExtra("beachNm", beachName);
    }
}