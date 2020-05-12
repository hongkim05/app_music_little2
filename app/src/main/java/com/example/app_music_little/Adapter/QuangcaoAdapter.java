package com.example.app_music_little.Adapter;

import android.Manifest;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.app_music_little.Model.Quangcao;
import com.example.app_music_little.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QuangcaoAdapter extends PagerAdapter {
    Context context;
    ArrayList<Quangcao> arrayListBanner;

    public QuangcaoAdapter(Context context, ArrayList<Quangcao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_quangcao, null);
        ImageView imgbackquangcao = view.findViewById(R.id.imagequangcao);
        ImageView imgbaihatqc = view.findViewById(R.id.Imageviewquangcao);
        TextView txttenbaihat = view.findViewById(R.id.titlebaihat);
        TextView txtnoidung = view.findViewById(R.id.textnoidung);


        Picasso.with(context).load(arrayListBanner.get(position).getHinhAnh()).into(imgbackquangcao);
        Picasso.with(context).load(arrayListBanner.get(position).getHinhBaiHat()).into(imgbaihatqc);
        txttenbaihat.setText(arrayListBanner.get(position).getTenBaiHat());
        txtnoidung.setText(arrayListBanner.get(position).getNoiDung());

        container.addView(view);

     return  view;
    }
}
