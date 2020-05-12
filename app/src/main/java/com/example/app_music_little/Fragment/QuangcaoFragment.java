package com.example.app_music_little.Fragment;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.app_music_little.API.DataService;
import com.example.app_music_little.API.RetrofitServer;
import com.example.app_music_little.Adapter.QuangcaoAdapter;
import com.example.app_music_little.Model.Quangcao;
import com.example.app_music_little.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuangcaoFragment extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    QuangcaoAdapter quangcaoAdapter;
    Handler handler;
    Runnable runnable;
    int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_quangcao,container,false);
        anhxa();
        GetData();
        return view;
    }

    private void anhxa() {
        viewPager = view.findViewById(R.id.viewPaper);
        circleIndicator = view.findViewById(R.id.indicatorQC);
    }

    private void GetData() {
        DataService dataservice = RetrofitServer.getService();
        Call<List<Quangcao>> callback = dataservice.GetDataQuangcao();
        callback.enqueue(new Callback<List<Quangcao>>() {


            @Override
            public void onResponse(Call<List<Quangcao>> call, Response<List<Quangcao>> response) {
                ArrayList<Quangcao> banners = (ArrayList<Quangcao>) response.body();
                quangcaoAdapter = new QuangcaoAdapter(getActivity(), banners);
                viewPager.setAdapter(quangcaoAdapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {

                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if (currentItem >= viewPager.getAdapter().getCount()) {
                            currentItem = 0;
                        }
                        viewPager.setCurrentItem(currentItem, true);
                        handler.postDelayed(runnable, 4500);
                    }
                };
                handler.postDelayed(runnable, 4500);

            }

            @Override
            public void onFailure(Call<List<Quangcao>> call, Throwable t) {

            }
        });


    }
}
