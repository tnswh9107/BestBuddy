package com.hoyaok2.bestbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Mypage_favor extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Play_item> items = new ArrayList<>();
    Play_Adapter recyclerAdpter;

    Spinner spinner;
    ArrayAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_favor);

        recyclerView = findViewById(R.id.favor2_recycler);
        recyclerAdpter = new Play_Adapter(this,items);
        recyclerView.setAdapter(recyclerAdpter);


        spinner = findViewById(R.id.favor2_spinner);
        toolbar=findViewById(R.id.favor2_toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle("찜한 목록");
//        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                Toast.makeText(Mypage_Favorlist.this, ""+position, Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    void loadData(){
        Retrofit retrofit = Retrofit_Helper.getRetrofitInstanceGson();
        Retrofit_Service retrofit_service = retrofit.create(Retrofit_Service.class);
            Call<ArrayList<Play_item>> call = retrofit_service.loadDataFromPlay();
            call.enqueue(new Callback<ArrayList<Play_item>>() {
                @Override
                public void onResponse(Call<ArrayList<Play_item>> call, Response<ArrayList<Play_item>> response) {

                    //기존데이터들 모두 제거
                    items.clear();
                    recyclerAdpter.notifyDataSetChanged();

                    //결과로 받아온 ArrayList<MarketItem>을 items에 추가
                    ArrayList<Play_item> list = response.body();
                    for (Play_item item : list) {
                        items.add(0, item);
                        recyclerAdpter.notifyItemInserted(0);
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<Play_item>> call, Throwable t) {
//                Toast.makeText(getActivity(), "error: ReviewFrag--"+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

    }
}