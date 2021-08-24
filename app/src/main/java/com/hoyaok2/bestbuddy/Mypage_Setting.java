package com.hoyaok2.bestbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Mypage_Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage__setting);

//        Switch switchButton=findViewById(R.id.mypage_switchbutton_setting2);
//
//        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
//                        @Override
//                        public void onComplete(@NonNull Task<String> task) {
//                            if(!task.isSuccessful()){
//                                Toast.makeText(Mypage_Setting.this, "앱 등록실패", Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                            String token=task.getResult();
//
//                            Toast.makeText(Mypage_Setting.this, ""+token, Toast.LENGTH_SHORT).show();
//                            Log.i("TOKEN",token);
//
//                        }
//                    });
//                }
//            }
//        });
    }
}