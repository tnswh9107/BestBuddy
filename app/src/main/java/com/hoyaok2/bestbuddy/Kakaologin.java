package com.hoyaok2.bestbuddy;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class Kakaologin extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //kakao SDK 초기화
        KakaoSdk.init(this,"9d7ebbf2dc964849b72e79473249d4c2");
    }
}
