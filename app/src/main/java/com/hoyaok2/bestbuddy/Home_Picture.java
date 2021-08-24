package com.hoyaok2.bestbuddy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Home_Picture extends Fragment {

    FloatingActionButton button;

    ArrayList<Pic_Item> items = new ArrayList<>();
    RecyclerView recyclerView;
    Pic_Adapter pic_adapter;
    SwipeRefreshLayout refreshLayout;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home__picture, container, false);

        button = view.findViewById(R.id.pic_addpic);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Pic_Add.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.pic_recycler);
        pic_adapter = new Pic_Adapter(getActivity(), items);
        recyclerView.setAdapter(pic_adapter);

        refreshLayout = view.findViewById(R.id.layout_refresh);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                refreshLayout.setRefreshing(false);
            }
        });

        String[] permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (ActivityCompat.checkSelfPermission(getContext(), permission[0]) == PackageManager.PERMISSION_DENIED);
        {
            ActivityCompat.requestPermissions(getActivity(), permission, 100);
        }
//        items.add(new Pic_Item(R.drawable.home_houseicon, " 아무거나 ", "옥이바보똥꼬"));
//        items.add(new Pic_Item(R.drawable.home_houseicon, " 아무거나 ", "옥이바보똥꼬"));
//        items.add(new Pic_Item(R.drawable.home_houseicon, " 아무거나 ", "옥이바보똥꼬"));
//        items.add(new Pic_Item(R.drawable.home_houseicon, " 아무거나 ", "옥이바보똥꼬"));
//        items.add(new Pic_Item(R.drawable.home_houseicon, " 아무거나 ", "옥이바보똥꼬"));
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    void loadData() {
        Retrofit retrofit = Retrofit_Helper.getRetrofitInstanceGson();
        Retrofit_Service retrofit_service = retrofit.create(Retrofit_Service.class);
        Call<ArrayList<Pic_Item>> call = retrofit_service.loadDataFromServerpicture();
        call.enqueue(new Callback<ArrayList<Pic_Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Pic_Item>> call, Response<ArrayList<Pic_Item>> response) {
                items.clear();
                pic_adapter.notifyDataSetChanged();

                ArrayList<Pic_Item> list = response.body();
                for (Pic_Item item : list) {
                    items.add(0, item);
                    pic_adapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pic_Item>> call, Throwable t) {

            }
        });
  }
}