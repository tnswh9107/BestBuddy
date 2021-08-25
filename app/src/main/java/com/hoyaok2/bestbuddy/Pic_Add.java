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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Pic_Add extends AppCompatActivity {

    ImageView pic_iv;
    EditText pic_title, pic_subtitle;
    TextView dialogtv;
    String imgpath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic__add);
        pic_iv = findViewById(R.id.pic_pic);
        pic_title = findViewById(R.id.pic_title);
        pic_subtitle = findViewById(R.id.pic_subtitle);
    }



    public void btnok(View view) {
        String title = pic_title.getText().toString();
        String subtitle = pic_subtitle.getText().toString();


        Retrofit retrofit = Retrofit_Helper.getRetrofitInstanceScalars();
        Retrofit_Service retrofit_service = retrofit.create(Retrofit_Service.class);

        MultipartBody.Part filePart = null;
        if(imgpath!=null)
        {
            File file = new File(imgpath);
            {
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
                filePart = MultipartBody.Part.createFormData("img",file.getName(),requestBody);
            }
        }

        //서버 연동 타이틀 이름
        Map<String,String> dataPart = new HashMap<>();
        dataPart.put("title",title);
        dataPart.put("subtitle",subtitle);

        // Play(ReTrofit) => 서버
        Call<String> call = retrofit_service.postDataToServerpicture(dataPart,filePart);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body();
                Toast.makeText(Pic_Add.this, ""+s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Pic_Add.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        finish();
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

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void pic_upload(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,10);

    }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==10 && resultCode==RESULT_OK)
        {
            Uri uri = data.getData();
            if (uri != null)
            {
                Glide.with(this).load(uri).into(pic_iv);
                imgpath = getRealPathFromUri(uri);
            }
        }
    }
}