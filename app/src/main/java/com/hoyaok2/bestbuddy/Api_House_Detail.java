package com.hoyaok2.bestbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Api_House_Detail extends AppCompatActivity {

    String mapx;
    String mapy;
    String nameId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api__house__detail);
    }


    public void house_detailmap(View view) {
        Intent intent = new Intent(Api_House_Detail.this,Api_House_DetailMap.class);
        intent.putExtra("mapx",mapx);
        intent.putExtra("mapy",mapy);
        intent.putExtra("nameId",nameId);
    }
}