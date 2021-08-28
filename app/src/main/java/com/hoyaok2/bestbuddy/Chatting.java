package com.hoyaok2.bestbuddy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Chatting extends AppCompatActivity {

    ListView listView;
    EditText et_chat;

    ArrayList<Chatting_item> items;
    Chatting_Adapter adapter;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        listView = findViewById(R.id.listview);
        et_chat = findViewById(R.id.et);

        items = new ArrayList<>();
        adapter = new Chatting_Adapter(this,items);
        listView.setAdapter(adapter);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("chat").child(G.othernickname+"&&"+G.nickname);
        if (getIntent().getStringExtra("server")!=null){
            databaseReference = firebaseDatabase.getReference("chat").child(getIntent().getStringExtra("server"));
        }
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Chatting_item item = snapshot.getValue(Chatting_item.class);
                items.add(item);
                adapter.notifyDataSetChanged();
                listView.setSelection(items.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void clickSend(View view) {
        String message = et_chat.getText().toString();
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String visualTime = new SimpleDateFormat("HH : mm").format(new Date());

        if(message.equals("")) return;;

        databaseReference.child(time).setValue(new Chatting_item(G.nickname,message,visualTime,G.profile)).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                et_chat.setText("");
            }
        });

    }
}