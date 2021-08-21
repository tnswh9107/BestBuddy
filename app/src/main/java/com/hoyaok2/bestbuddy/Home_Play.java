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

public class Home_Play extends Fragment {

    FloatingActionButton button;

    //    Play_Adapter 연결
    ArrayList<Play_item> items = new ArrayList<>();
    RecyclerView recyclerView;
    Play_Adapter play_adapter;
    SwipeRefreshLayout refreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home__play, container, false);

//        items.add(new Play_item(R.drawable.home_houseicon," 아무거나 ",""));

        button = view.findViewById(R.id.addplay);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Play_Add.class);
                startActivity(intent);


            }
        });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler);
        play_adapter = new Play_Adapter(getActivity(), items);
        recyclerView.setAdapter(play_adapter);

        refreshLayout = view.findViewById(R.id.layout_refresh);

        refreshLayout = view.findViewById(R.id.layout_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                refreshLayout.setRefreshing(false);
            }
        });

        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (ActivityCompat.checkSelfPermission(getContext(), permissions[0]) == PackageManager.PERMISSION_DENIED)
            ;
        {
            ActivityCompat.requestPermissions(getActivity(), permissions, 100);
        }
//        items.add(new Play_item(R.drawable.home_houseicon," 아무거나 ","옥이바보똥꼬"));
//        items.add(new Play_item(R.drawable.home_houseicon," 아무거나 ","옥이바보똥꼬"));
//        items.add(new Play_item(R.drawable.home_houseicon," 아무거나 ","옥이바보똥꼬"));
//        items.add(new Play_item(R.drawable.home_houseicon," 아무거나 ","옥이바보똥꼬"));
//        items.add(new Play_item(R.drawable.home_houseicon," 아무거나 ","옥이바보똥꼬"));
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    void loadData()
    {
        Retrofit retrofit = Retrofit_Helper.getRetrofitInstanceGson();
        Retrofit_Service retrofit_service1 = retrofit.create(Retrofit_Service.class);
        Call<ArrayList<Play_item>> call =retrofit_service1.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<Play_item>>() {
            @Override
            public void onResponse(Call<ArrayList<Play_item>> call, Response<ArrayList<Play_item>> response) {
                items.clear();
                play_adapter.notifyDataSetChanged();

                ArrayList<Play_item> list = response.body();
                for (Play_item item: list){
                    items.add(0,item);
                    play_adapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Play_item>> call, Throwable t) {

            }
        });
    }


}
