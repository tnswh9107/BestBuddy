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

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_Mypage extends Fragment {

    RecyclerView recyclerView;
    CircleImageView profile;
    Button favorlist,setting,call,made;
    TextView nickname,logout;
    int mypage = 0;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_home__mypage, container, false);

        favorlist = view.findViewById(R.id.mypage_favorlist);
        setting = view.findViewById(R.id.mypage_setting);
        call = view.findViewById(R.id.mypage_call);
        made = view.findViewById(R.id.mypage_madeid);


        if (mypage == 0)
        {

        }

        //클릭시 만들어지는 4개의 함수 찜한 목록, 환경설정 고객센터 계정관리 시작
        //찜한목록 시작
        favorlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Mypage_favor.class);
                startActivity(intent);
            }
        });//찜한목록 끝



        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Mypage_Setting.class);
                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Mypage_Setting.class);
                startActivity(intent);
            }
        });

        made.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Mypage_Setting.class);
                startActivity(intent);
            }
        });



        return view;
    }// 클릭시 만들어지는 4개의 함수 찜한 목록, 환경설정 고객센터 계정관리 끝

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }






}