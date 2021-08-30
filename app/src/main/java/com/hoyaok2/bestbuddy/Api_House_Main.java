package com.hoyaok2.bestbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Api_House_Main extends AppCompatActivity {

    //어뎁터에서 아이템 주석 풀어줘야함
    ArrayList<Api_House_recyclerItem> items = new ArrayList<>();
    RecyclerView recyclerView;
    Api_House_Adapter adapter;
    String tagName;

    Api_House_recyclerItem item = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api__house__main);

        recyclerView = findViewById(R.id.apihouse_recycler);
        adapter = new Api_House_Adapter(this, items);
        recyclerView.setAdapter(adapter);

       // items.add(new Api_House_recyclerItem(" 아무거나 ","옥이바보똥꼬",R.drawable.home_houseicon)); text

        Thread t = new Thread() {
            @Override
            public void run() {

              String address = "http://apis.data.go.kr/6460000/beachInfo/getBeachInfoList?ServiceKey=CGkdIk5%2FUJ1mLThPCJukNcwLdUxCVyuZxotg%2BG76yR6V0f%2BoNo8UZR5HEIEPPvHM3G6juI9I0tX9XCMMWzqnVw%3D%3D&searchArea=AREA_YEOSU&searchTitle=여수시";



                try {
                    URL url = new URL(address);
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp = factory.newPullParser();

                    xpp.setInput(isr);

                    int eventType = xpp.getEventType();

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:
                                break;
                            case XmlPullParser.START_TAG:
                                tagName = xpp.getName();
                                if (tagName.equals("row")) {
                                    item = new Api_House_recyclerItem();
                                } else if (tagName.equals("beachAddr")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.beachAddr = text;    //지역주소
                                } else if (tagName.equals("beachNm")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.beachNm = text;  //해수욕장 명

                                }else if (tagName.equals("beachPhone1")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.beachPhone1 = text;   //해수욕장 번호
                                }else if (tagName.equals("detailInfo")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.detailInfo = text;   //해수욕장 설명
                                } else if (tagName.equals("homepage")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.homepage = text; //해수욕장 홈페이지
                                }else if (tagName.equals("imagePath")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.imagePath = text; //이미지 사진
                                }else if (tagName.equals("mapX")) {
                                    xpp.next();
                                    if (item != null) item.mapX =xpp.getText();  //맵x
                                }else if (tagName.equals("mapY")) {
                                    xpp.next();
                                    if (item != null) item.mapY =xpp.getText(); //맵y
                                }else if (tagName.equals("openDate")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.openDate = text; //해수욕장 오픈일
                                }else if (tagName.equals("traffic")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.traffic = text;   //오는방법
                                }
                                break;

                            case XmlPullParser.TEXT:
                                break;

                                case XmlPullParser.END_TAG:
                                    String tagName2 = xpp.getName();
                                    if (tagName2.equals("row")){
                                        if (item.imagePath!=null){
                                            items.add(item);
                                        }
                                    }
                                    break;
                        }
                        eventType = xpp.next();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e)
                {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

}