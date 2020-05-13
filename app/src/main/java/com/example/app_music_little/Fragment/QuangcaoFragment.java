package com.example.app_music_little.Fragment;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

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
import java.util.logging.Logger;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuangcaoFragment extends Fragment {
    private View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    QuangcaoAdapter quangcaoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quangcao, container, false);
        anhXa();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GetData();
    }

    private void anhXa() {
        viewPager = view.findViewById(R.id.viewPaper);
        circleIndicator = view.findViewById(R.id.indicatorQC);
        //phải new adapter ở ngoài, khi lấy được danh sách chỉ cần update lại.
        quangcaoAdapter = new QuangcaoAdapter(requireContext());
        viewPager.setAdapter(quangcaoAdapter);
        circleIndicator.setViewPager(viewPager);
    }

    private void GetData() {
        DataService dataservice = RetrofitServer.getService();
        Call<List<Quangcao>> callback = dataservice.GetDataQuangCao();
        //noinspection NullableProblems
        callback.enqueue(new Callback<List<Quangcao>>() {

            @Override
            public void onResponse(Call<List<Quangcao>> call, Response<List<Quangcao>> response) {
                List<Quangcao> quangcaoList = response.body();
                if (quangcaoList != null && quangcaoList.size() > 0) {
                    for (int i = 0; i < quangcaoList.size(); i++) {
                        LayoutInflater inflater = requireActivity().getLayoutInflater();
                        RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.dong_quangcao, null);
                        quangcaoAdapter.addView(view, i,quangcaoList.get(i));
                    }
                    quangcaoAdapter.notifyDataSetChanged();
                    circleIndicator.getDataSetObserver().onChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Quangcao>> call, Throwable t) {

            }
        });
    }
}
