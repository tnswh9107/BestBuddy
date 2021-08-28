package com.hoyaok2.bestbuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Delayed;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Makemember extends AppCompatActivity {


    CircleImageView circleImageView;
    EditText memid, mempw, mempwc, memnicname;
    Button memsign;

    String imgpath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makemember);

        circleImageView=findViewById(R.id.memver_profile);
        memid=findViewById(R.id.memver_etid);
        mempw=findViewById(R.id.memver_etpassword);
//        mempwc=findViewById(R.id.memver_etpassword_check);
        memnicname=findViewById(R.id.memver_etnickname);
        memsign=findViewById(R.id.memver_signup);


        memsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = memid.getText().toString();
                String userPassword = mempw.getText().toString();
//                String userpwc = mempwc.getText().toString();
                String userName = memnicname.getText().toString();

                if (userID.equals("") || userPassword.equals("") || userName.equals("") || G.profile==null) {
                    Toast.makeText(Makemember.this, "모두 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (userID.length() <= 1) {
                    Toast.makeText(Makemember.this, "Id는 6글자 이상 가능합니다", Toast.LENGTH_SHORT).show();
                }
                if (userPassword.length() <= 1) {
                    Toast.makeText(Makemember.this, "비밀번호는 8글자 가능합니다", Toast.LENGTH_SHORT).show();
                }
//                if (userpw!=userpwc)
//                {
//                    Toast.makeText(Makemember.this, "비밀번호가 일치 하지 않습니다 다시확인해주세요", Toast.LENGTH_SHORT).show();
//                }
                if (userName.length() <= 1) {
                    Toast.makeText(Makemember.this, "닉네임은 2글자 이상 가능합니다.", Toast.LENGTH_SHORT).show();
                }


                Retrofit retrofit = Retrofit_Helper.getRetrofitInstanceScalars();
                Retrofit_Service retrofit_service_signup = retrofit.create(Retrofit_Service.class);

                MultipartBody.Part filePart = null;
                if (imgpath!=null){
                    File file=new File(imgpath);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
                    filePart = MultipartBody.Part.createFormData("img",file.getName(),requestBody);
                }

                Map<String,String> dataPart = new HashMap<>();
                dataPart.put("userID",userID);
                dataPart.put("userPassword",userPassword);
//                dataPart.put("userpwc",userpwc);
                dataPart.put("userName",userName);

                Call<String> call=retrofit_service_signup.postDataToServermember(dataPart,filePart);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String s =response.body();
                        Toast.makeText(Makemember.this, ""+s, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(Makemember.this, "회원가입에 실패 하였습니다.", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });




    }

    public void click_signup_Profile(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==10 && resultCode==RESULT_OK){
            Uri uri =data.getData();
            if(uri !=null){
                Glide.with(this).load(uri).into(circleImageView);
                imgpath = getRealPathFromUri(uri);
            }
        }
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

}