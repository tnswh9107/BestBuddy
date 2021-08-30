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

    ArrayList<Api_House_recyclerItem> items = new ArrayList<>();
    RecyclerView recyclerView;
    Api_House_Adapter adapter;
    String tagName;

    Api_House_recyclerItem item = null;

    String apiKey = "4752ab44f1274a1aa25fca0d24c5a3b8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api__house__main);

        recyclerView = findViewById(R.id.apihouse_recycler);
        adapter = new Api_House_Adapter(this, items);
        recyclerView.setAdapter(adapter);

        Thread t = new Thread() {
            @Override
            public void run() {

                String address = "http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/basedList?ServiceKey="
                        + apiKey + "&MobileOS=AND" + "&MobileApp=campinggo&numOfRows=200";

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
                                if (tagName.equals("item")) {
                                    item = new Api_House_recyclerItem();
                                } else if (tagName.equals("firstImageUrl")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.houseingimg = text;
                                } else if (tagName.equals("facltNm")) {
//                                    xpp.next();
//                                    item.addr+= xpp.getText();
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.name = text;

                                }else if (tagName.equals("lineIntro")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.lineintro = text;
                                }else if (tagName.equals("addr1")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.addr1 = text;
                                } else if (tagName.equals("addr2")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.addr2 = text;
                                }else if (tagName.equals("mapX")) {
                                    xpp.next();
//                                    xpp.getText();
                                    if (item != null) item.mapX =xpp.getText();
                                }else if (tagName.equals("mapY")) {
                                    xpp.next();
//                                    String text = xpp.getText();
                                    if (item != null) item.mapY =xpp.getText();
                                }else if (tagName.equals("open")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.addr2 = text;
                                }else if (tagName.equals("open")) {
                                    xpp.next();
                                    String text = xpp.getText();
                                    if (item != null) item.close = text;
                                }
                                break;

                            case XmlPullParser.TEXT:
                                break;

                                case XmlPullParser.END_TAG:
                                    String tagName2 = xpp.getName();
                                    if (tagName2.equals("item")){
                                        if (item.houseingimg!=null && item.induty.equals("숙박 찾기")){
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