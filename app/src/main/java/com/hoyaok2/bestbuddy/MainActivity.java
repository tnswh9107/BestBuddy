package com.hoyaok2.bestbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.kakao.sdk.common.util.Utility;
import com.kakao.sdk.user.UserApiClient;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    //    fragment 연결
    BottomNavigationView bnv;
    Fragment[] fragments = new Fragment[5];
    FragmentManager fragmentManager;


    //    드로우 네이게이션(사이드)
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    //로그인시 접속


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        //keyhash 키해쉬값 설정
//        String keyHash = Utility.INSTANCE.getKeyHash(this);
//        Log.i("KeyHasho",keyHash);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




//        네이게이션 보여주기
        //드로우 네비게이션
        bnv = findViewById(R.id.bnv);
        drawerLayout = findViewById(R.id.layout_drawer);

        //토글 만들기
        navigationView = findViewById(R.id.naviv);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //토글 만들기 끝


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                item.setChecked(true);
                drawerLayout.closeDrawers();


                switch (item.getItemId()) {
                    case R.id.house:
                        startActivity(new Intent(getApplicationContext(), Api_House_Main.class));
                        drawerLayout.closeDrawer(navigationView);
                        break;
                    case R.id.sea:
                        startActivity(new Intent(getApplicationContext(), Login_Joy.class));
                        drawerLayout.closeDrawer(navigationView);
                        break;
                    case R.id.mountain:
                        startActivity(new Intent(getApplicationContext(), Login_Joy.class));
                        drawerLayout.closeDrawer(navigationView);
                        break;
                    case R.id.map:
                        startActivity(new Intent(getApplicationContext(), Login_Joy.class));
                        drawerLayout.closeDrawer(navigationView);
                        break;
                    case R.id.review:
                        startActivity(new Intent(getApplicationContext(), Login_Joy.class));
                        drawerLayout.closeDrawer(navigationView);
                        break;
                    case R.id.chat:
                       if (G.nickname != null) {
                           startActivity(new Intent(getApplicationContext(), Chatting_List.class));
                           drawerLayout.closeDrawer(navigationView);
                       }else Toast.makeText(MainActivity.this, "로그인이 필요한 서비스입니다.", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        });

        //로그인, 로그아웃 이미지 변경및 로그아웃
        View headerView= navigationView.getHeaderView(0);
        CircleImageView userprofile=headerView.findViewById(R.id.drawer_profile);
        TextView usernickname=headerView.findViewById(R.id.drawer_nickname);
        Button logoutbtn=headerView.findViewById(R.id.logoutbtn);
        Button loginbtn=headerView.findViewById(R.id.loginbtn);
        TextView drawer_login=headerView.findViewById(R.id.drawer_login);
        TextView drawer_logout=headerView.findViewById(R.id.drawer_logout);


        if (G.nickname !=null){
            drawer_login.setVisibility(View.GONE);
            drawer_logout.setVisibility(View.VISIBLE);
            logoutbtn.setVisibility(View.VISIBLE);
            loginbtn.setVisibility(View.GONE);

            logoutbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                        @Override
                        public Unit invoke(Throwable throwable) {
                            if(throwable !=null) {
//                            logoutbtn.setEnabled(false);
                            } else {
                                Toast.makeText(MainActivity.this, "로그아웃", Toast.LENGTH_SHORT).show();

                                //로그인 회원정보 화면들 모두 초기화
                                usernickname.setText(" ");
                                Glide.with(headerView).load(G.profile).into(userprofile);

                                drawer_login.setVisibility(View.VISIBLE);
                                drawer_logout.setVisibility(View.GONE);
                                logoutbtn.setVisibility(View.GONE);
                                loginbtn.setVisibility(View.VISIBLE);
                            }
                            return null;
                        }
                    });
                }
            });
        }


        fragmentManager = getSupportFragmentManager();

        FragmentTransaction tran = fragmentManager.beginTransaction();
        fragments[0] = new Home_Main();
        tran.add(R.id.container, fragments[0]);
        tran.commit();


        //바텀 네비게이션 연결
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction tran = fragmentManager.beginTransaction();
                if (fragments[0] != null) tran.hide(fragments[0]);
                if (fragments[1] != null) tran.hide(fragments[1]);
                if (fragments[2] != null) tran.hide(fragments[2]);
                if (fragments[3] != null) tran.hide(fragments[3]);
                if (fragments[4] != null) tran.hide(fragments[4]);

                switch (item.getItemId()) {
                    case R.id.home:
                        tran.show(fragments[0]);
                        getSupportActionBar().setTitle("홈 메뉴");
                        break;
                    case R.id.myaround:
                        if (fragments[1] == null) {
                            fragments[1] = new Home_Map();
                            tran.add(R.id.container, fragments[1]);
                        }
                        tran.show(fragments[1]);
                        getSupportActionBar().setTitle("내주변 지도");
                        break;
                    case R.id.play:
                        if (fragments[2] == null) {
                            fragments[2] = new Home_Play();

                            tran.add(R.id.container, fragments[2]);
                        }
                        tran.show(fragments[2]);
                        getSupportActionBar().setTitle("체험하기");
                        break;
                    case R.id.picture:
                        if (fragments[3] == null) {
                            fragments[3] = new Home_Picture();
                            tran.add(R.id.container, fragments[3]);
                        }
                        tran.show(fragments[3]);
                        getSupportActionBar().setTitle("일상");
                        break;

                    case R.id.mypage:
                        if (fragments[4] == null) {
                            fragments[4] = new Home_Mypage();
                            tran.add(R.id.container, fragments[4]);

                            tran.hide(fragments[4]);
                        }
                        if (G.nickname != null || G.userID != null) {
                            tran.show(fragments[4]);
                            getSupportActionBar().setTitle("마이페이지");
//                        } else if (G.nickname == null || G.userID == null) {
//                            tran.show(fragments[4]);
//                            Toast.makeText(MainActivity.this, "회원가입이 필요한 서비스입니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(MainActivity.this, Login_Joy.class);
                            startActivity(intent);
                        }
//                        tran.show(fragments[4]);
//                        getSupportActionBar().setTitle("로그인");
                        break;

                }
                tran.commit();
//                시작해라(commit)

                return true;
            }
        });        //바텀 네이게이션 연결 끝


        //지도 동작퍼미션
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 0);
            }
        }





    } //oncreate 끝

    @Override
    protected void onResume() {
        super.onResume();
        View headerView = navigationView.getHeaderView(0);
        CircleImageView userprofile = headerView.findViewById(R.id.drawer_profile);
        TextView usernickname = headerView.findViewById(R.id.drawer_nickname);

        if (G.nickname != null) {
            usernickname.setText(G.nickname + " 님 환영합니다");
            Glide.with(this).load(G.profile).into(userprofile);
//            Glide.with(this).load()
//        }else{
//            out.setVisibility(View.GONE);
//            log.setVisibility(View.VISIBLE);
        }


    }


    public void clickSignupBtn(View view) {
        Intent intent = new Intent(this, Makemember.class);
        startActivity(intent);
    }


    public void clickLoginBtn(View view) {
        Intent intent = new Intent(this, Login_Joy.class);
        startActivity(intent);

    }
//    }

    public void clickmypage(View view) {
        bnv.setSelectedItemId(R.id.mypage);
        drawerLayout.closeDrawer(navigationView);

    }
}

