package com.hoyaok2.bestbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login_Joy extends AppCompatActivity {

    String nickname;
    EditText login_id, login_pw;
    Button login_button, login_signup;

    ArrayList<LoginItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_joy);

        login_id = findViewById(R.id.login_id);
        login_pw = findViewById(R.id.login_pw);
        login_button = findViewById(R.id.login_Button);
        login_signup = findViewById(R.id.login_signup);

        Toolbar logintoolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(logintoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Login_Joy.this, "클릭", Toast.LENGTH_SHORT).show();
                String loginid = login_id.getText().toString();
                String loginpw = login_pw.getText().toString();

                Retrofit retrofit = Retrofit_Helper.getRetrofitInstanceGson();
                Retrofit_Service retrofit_service = retrofit.create(Retrofit_Service.class);

                Call<LoginItem> call = retrofit_service.Userlogin(loginid, loginpw);
                call.enqueue(new Callback<LoginItem>() {
                    @Override
                    public void onResponse(Call<LoginItem> call, Response<LoginItem> response) {
                        LoginItem item = response.body();

                        if (item != null) {
                            G.userID = item.loginid;
                            G.nickname = item.loginnicname;
                            G.profile = "http://tnswh9107.dothome.co.kr/BuddyLogin/" + item.file;

                            Toast.makeText(Login_Joy.this, "환영합니다", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login_Joy.this, MainActivity.class));
                            finish();
                        } else
                            Toast.makeText(Login_Joy.this, "아이디와 비밀번호를 다시 확인해주세요", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onFailure(Call<LoginItem> call, Throwable t) {
                        Toast.makeText(Login_Joy.this, "서버에러, 잠시후 다시 시도해 주세요", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    ///툴바 백버튼 눌렀을때 홈으로


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void clickSignupBtn(View view) {
        Intent intent = new Intent(this, Makemember.class);
        startActivity(intent);
    }


}