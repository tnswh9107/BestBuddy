package com.hoyaok2.bestbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

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

        recyclerView = findViewById(R.id.recycler);
        recyclerAdpter = new Play_Adapter(this,items);
        recyclerView.setAdapter(recyclerAdpter);

        spinner=findViewById(R.id.favor2_spinner);
//        adapter=ArrayAdapter.createFromResource(this,R.array.datas,R.layout.spinner_selected);
//        spinner.setAdapter(adapter);
//        toolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


//        getSupportActionBar().setTitle("찜한 목록");
//        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
//
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
}