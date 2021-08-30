package com.hoyaok2.bestbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Api_House_DetailMap extends AppCompatActivity {

    GoogleMap gMap;
    ArrayList<Api_House_recyclerItem> items = new ArrayList<>();
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api__house__detail_map);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.detial_map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                gMap = googleMap;
                gMap.getUiSettings().setZoomControlsEnabled(true);

                Intent intent1 =getIntent();
                String detailmapX = intent1.getStringExtra("mapx");
                String detailmapY = intent1.getStringExtra("mapy");
                String detailnameId = intent1.getStringExtra("mapId");


                double lng=Double.parseDouble(detailmapX);  //경도
                double lat=Double.parseDouble(detailmapY); //위도


                LatLng camping=new LatLng(lat,lng);
                Log.i("la",detailmapX+","+detailmapY);
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(camping,15));

                MarkerOptions marker=new MarkerOptions();
                marker.title(detailnameId);
                marker.position(camping);
//                    marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));

                marker.anchor(0.5f, 1.0f);

                gMap.addMarker(marker);
            }
        });

    }
}