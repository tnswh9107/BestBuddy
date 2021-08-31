package com.hoyaok2.bestbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import de.hdodenhof.circleimageview.CircleImageView;

public class Play_Show extends AppCompatActivity {

//    FCM작업할 것 3단계
//    1.FCM SDK library 추가 [Firebase console 앱등록]
//    2. FCM서버에서 보낸 메세지를 수신하기 위한 Service클래스 작성
//    3. 앱을 FCM서버에 등록하여 토큰 받기

//    먼저저

    TextView dialogtv;

    CircleImageView showprofile;
    ImageView showiv;
    TextView showtitle, showsub, showprice, showphone, shownickname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play__show);

        showiv = findViewById(R.id.play_show_pic);
        showtitle = findViewById(R.id.play_show_name);
        showsub = findViewById(R.id.play_show_story);
        showprice = findViewById(R.id.play_pay);
        showphone = findViewById(R.id.play_phon);
        showprofile = findViewById(R.id.play_profile);
        shownickname = findViewById(R.id.play_nickname);


        Intent intent = getIntent();

        String pic = intent.getStringExtra("pic");
        String title = intent.getStringExtra("title");
        String subtitle = intent.getStringExtra("subtitle");
        String price = intent.getStringExtra("price");
        String phone = intent.getStringExtra("phone");
        String usernickname = intent.getStringExtra("usernickname");
        String userprofile = intent.getStringExtra("userprofile");


        String imgUrl = "http://tnswh9107.dothome.co.kr/BuddyPlay/" + pic;

        Log.d("test", imgUrl);


        if (imgUrl == null) Glide.with(this).load(R.drawable.noimage).into(showiv);
        else Glide.with(this).load(imgUrl).into(showiv);

        showtitle.setText(title);
        showsub.setText(subtitle);
        showprice.setText(price);
        showphone.setText(phone);
        shownickname.setText(usernickname);
        Glide.with(this).load(userprofile).into(showprofile);

        G.othernickname=usernickname;
        G.otherprofile=userprofile;
         Log.d("hihi",""+G.othernickname);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            showiv.setTransitionName("PlayShow");
        }


    }


    AlertDialog dialog;
    public void Play_Chatting(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.dialogchat,null);

        dialogtv = layout.findViewById(R.id.dialogchat);

        builder.setView(layout);
        if (G.nickname !=null)
        {
            builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Play_Show.this,Chatting.class);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }else Toast.makeText(this, "로그인이 필요한 서비스 입니다.", Toast.LENGTH_SHORT).show();
    }


//    public void Play_FCMmessage(View view) {
//        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
//            @Override
//            public void onComplete(@NonNull Task<String> task) {
//                if (!task.isSuccessful()) {
//                    Toast.makeText(Play_Show.this, "앱 등록 실패", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                String token = task.getResult(); //토큰값 결과 얻어오기
//
//                //원래는 token값을 웹서버에 보내서 DB에 저장해 놓아야함
//                //추후 token 값을 dothme 값은 웹서버에 사용하기 위해 Log로 출력해보기
//                //토스트와 Logcat모두 출력
//                Toast.makeText(Play_Show.this, "" + token, Toast.LENGTH_SHORT).show();
//                Log.i("TOKEN", token);
//
//            }
//
//        });
//    }
}