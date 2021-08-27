package com.hoyaok2.bestbuddy;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

//서비스 컴포넌트는 4개컴포넌트이기 떄문에 매니페스트에 등록해야한다.
public class Mypage_MyFCMService extends FirebaseMessagingService {
    //FCM 메세지를 수신하면 자동으로 발동 하는 콜백 메소드

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        //메소드 발동 확인하기 위해 Log 기록 표시 - Activity가 없으니 Toast는 사용 하지 않는다.
        Log.i("TAG", "onMessageReceived...");

        //PUSH 서비스는 앱이 꺼져있는 상태에서 서버가 강제로 Client 에게
        //정보를 보내는 것이기에 알림을 이요해서 정보를 전달한다.
        //버전업이 되면서 앱이 꺼져있으면 무조건 자동으로 Nofication을 만들어 보여줌

        //알림을 제어하고 싶다면 직접 알림객체를 이곳에서 설계
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new  NotificationChannel("ch1","push Chennel",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(this,"ch1");
        }else{
            builder = new NotificationCompat.Builder(this,"");

        }

        //FCM서버로 부터 전달되어온 알림제목, 알림메세지를 얻어오기
        //onMessageReceied() 메소드의 파라미터 전달되어온 RemoteMessage remoteMessage(원격메세지)가 정보를 가지고있음
        String notiTitle = "title"+G.nickname;     //알림정보가 없을때 기본제목
        String notiText = "message";    //알림정보가 없을때 기본글씨

        if(remoteMessage.getNotification()!=null){
            notiTitle = remoteMessage.getNotification().getTitle();
            notiText = remoteMessage.getNotification().getBody();
        }


        //알림의 주요 설정들
        builder.setSmallIcon(R.drawable.ic_fcm_noti); //상태 표시줄에 보이는 아이콘
        builder.setContentTitle(notiTitle);  //확장상태바(알림창) 의 제목 글씨
        builder.setContentText(notiText);   //확장상태바(알림창) 의 메세지 글씨

        //알림객체 생성 및 공지[알림보이기]
        Notification notification = builder.build();
        notificationManager.notify(11,notification);


    }
}
