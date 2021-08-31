package com.hoyaok2.bestbuddy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kakao.sdk.user.UserApiClient;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Home_Mypage extends Fragment {

    RecyclerView recyclerView;
    CircleImageView profile;
    Button favorlist, setting, call, made;
    TextView nickname, logout;
    int mypage = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home__mypage, container, false);

        favorlist = view.findViewById(R.id.mypage_favorlist);
        setting = view.findViewById(R.id.mypage_setting);
        call = view.findViewById(R.id.mypage_call);
        made = view.findViewById(R.id.mypage_madeid);
        logout = view.findViewById(R.id.mypage_logout);


        //클릭시 만들어지는 4개의 함수 찜한 목록, 환경설정 고객센터 계정관리 시작
        //찜한목록 시작
        favorlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Mypage_favor.class);
                startActivity(intent);
            }
        });//찜한목록 끝


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Mypage_Setting.class);
                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Mypage_Setting.class);
                startActivity(intent);
            }
        });

        made.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Mypage_Setting.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "로그아웃클릭됨", Toast.LENGTH_SHORT).show();
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        if(throwable !=null) {

                        } else {
                            Toast.makeText(getActivity(), "로그아웃", Toast.LENGTH_SHORT).show();

                            //로그인 회원정보 화면들 모두 초기화
                            nickname.setText(" ");
                            Glide.with(getActivity()).load(R.drawable.profilenull).into(profile);
                            startActivity(new Intent(getActivity(),Login_Joy.class));
                        }
                        return null;
                    }
                });

            }
        });


        return view;
    }// 클릭시 만들어지는 4개의 함수 찜한 목록, 환경설정 고객센터 계정관리 끝

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nickname = getActivity().findViewById(R.id.mypage_nickname);
        profile = getActivity().findViewById(R.id.mypage_profile);
        nickname.setText(G.nickname);
        Glide.with(this).load(G.profile).into(profile);
    }




}