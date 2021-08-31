package com.hoyaok2.bestbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class Chatting_List extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> items;
    ArrayList<Integer> serverNum;
    Chatting_RecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting__list);

        recyclerView = findViewById(R.id.chattinglistview);
        recyclerView.setLayoutManager(new LinearLayoutManagerWrapper(this,LinearLayoutManager.VERTICAL,false));
        items = new ArrayList<>();
        serverNum = new ArrayList<>();
        adapter = new Chatting_RecycleAdapter(this,items,serverNum);
        recyclerView.setAdapter(adapter);

    }

    public void onStart(){
        super.onStart();
        loadChattingData();
    }

    public void loadChattingData() {
        items.clear();
        serverNum.clear();
        FirebaseDatabase.getInstance().getReference("chat").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String[] chatNames = ds.getKey().split("&&");
                    ArrayList<String> chatNamesArray = new ArrayList<>(Arrays.asList(chatNames));
                    int index = chatNamesArray.indexOf(G.nickname);
                    if (index == 0) {
                        items.add(chatNamesArray.get(1));
                        serverNum.add(1);
                    } else if (index == 1) {
                        items.add(chatNamesArray.get(0));
                        serverNum.add(0);
                    }
                    adapter.notifyItemInserted(items.size() - 1);
                }
            }
        });

    }
}