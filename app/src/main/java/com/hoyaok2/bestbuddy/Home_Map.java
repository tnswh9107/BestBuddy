package com.hoyaok2.bestbuddy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

public class Home_Map extends Fragment implements OnMapReadyCallback {

    GoogleMap gmap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home__map,container,false);

        //fragment에서만 사용되는 방법
        MapView mapView = (MapView)view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        gmap = googleMap;

        //지도의 특정 좌표 이동 및 줌
        LatLng home = new LatLng(37.495660, 126.786151);
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(home,15));


        //지도 대표적인 설정들
        UiSettings settings = gmap.getUiSettings();
        settings.setZoomControlsEnabled(true);

        //내 위치 탐색을 지도 라이브러리 제공
        settings.setMyLocationButtonEnabled(true);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        gmap.setMyLocationEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case 0:
                for (int i=0; i<grantResults.length; i++)
                {
                    if (grantResults[i]==PackageManager.PERMISSION_DENIED)
                    {
                        Toast.makeText(getActivity(),"내 위치 사용불가",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                break;
        }
    }
}