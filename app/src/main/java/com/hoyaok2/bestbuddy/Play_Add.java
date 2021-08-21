package com.hoyaok2.bestbuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Play_Add extends AppCompatActivity {

    ImageView iv;
    EditText name, story, pay, phone ;
    String imgpath;
    TextView dialogtv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play__add);
        iv = findViewById(R.id.play_pic);
        name = findViewById(R.id.play_name);
        story = findViewById(R.id.play_story);
        pay = findViewById(R.id.play_pay);
        phone = findViewById(R.id.play_phone);
    }


   ////////////////////카메라 갤러리 시작
    public void pic_upload(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("iamge/*");
        startActivityForResult(intent,10);
    }
    //절대경로 c+v
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }
    ////////////////카메라 갤러리 끝


//    카메라 requestCode 10번
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==10&& resultCode==RESULT_OK)
        {
            Uri uri = data.getData();
            if (uri != null)
            {
                Glide.with(this).load(uri).into(iv);
                imgpath = getRealPathFromUri(uri);
            }
        }
    }

    /////
    public void btnok(View view) {

    }


    AlertDialog dialog;
    public void btnno(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.cancle_dialog,null);

        dialogtv = layout.findViewById(R.id.dialog);

        builder.setView(layout);

        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}