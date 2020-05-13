package com.example.app_music_little.Adapter;

import android.Manifest;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.app_music_little.Model.Quangcao;
import com.example.app_music_little.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class QuangcaoAdapter extends PagerAdapter {

    // This holds all the currently displayable views, in order from left to right.
    private List<View> views = new ArrayList<View>();
    private List<Quangcao> quangCaoList = new ArrayList<>();
    private Context context;

    public QuangcaoAdapter(Context context) {
        this.context = context;
    }

    //-----------------------------------------------------------------------------
    // Used by ViewPager.  "Object" represents the page; tell the ViewPager where the
    // page should be displayed, from left-to-right.  If the page no longer exists,
    // return POSITION_NONE.
    @Override
    public int getItemPosition(@NotNull Object object) {
        for (int index = 0; index < getCount(); index++) {
            if ((View) object == views.get(index)) {
                return index;
            }
        }
        return POSITION_NONE;
    }


    //-----------------------------------------------------------------------------
    // Used by ViewPager.  Called when ViewPager needs a page to display; it is our job
    // to add the page to the container, which is normally the ViewPager itself.  Since
    // all our pages are persistent, we simply retrieve it from our "views" ArrayList.
    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, int position) {
        Log.e(">>>",position+"");
        Log.e(">>>",quangCaoList.size()+"");
        View v = views.get(position);
        if (v.getParent() != null) {
            ((ViewGroup) v.getParent()).removeView(v);
        }
        ImageView imgbackquangcao = v.findViewById(R.id.imagequangcao);
        ImageView imgbaihatqc = v.findViewById(R.id.Imageviewquangcao);
        TextView txttenbaihat = v.findViewById(R.id.titlebaihat);
        TextView txtnoidung = v.findViewById(R.id.textnoidung);

        Quangcao quangcao = quangCaoList.get(position);
        Picasso.get().load(quangcao.getHinhAnh()).into(imgbackquangcao);
        //HinhBaiHat đang rỗng; kiểm tra lại
//        Picasso.get().load(quangcao.getHinhBaiHat()).into(imgbaihatqc);
        Picasso.get().load(quangcao.getHinhAnh()).into(imgbaihatqc);
        txttenbaihat.setText(quangcao.getTenBaiHat());
        txtnoidung.setText(quangcao.getNoiDung());
        container.addView(v);
        return v;
    }

    //-----------------------------------------------------------------------------
    // Used by ViewPager.  Called when ViewPager no longer needs a page to display; it
    // is our job to remove the page from the container, which is normally the
    // ViewPager itself.  Since all our pages are persistent, we do nothing to the
    // contents of our "views" ArrayList.
    @Override
    public void destroyItem(ViewGroup container, int position, @NotNull Object object) {
        container.removeView(views.get(position));
    }

    //-----------------------------------------------------------------------------
    // Used by ViewPager; can be used by app as well.
    // Returns the total number of pages that the ViewPage can display.  This must
    // never be 0.
    @Override
    public int getCount() {
        return views.size();
    }

    //-----------------------------------------------------------------------------
    // Used by ViewPager.
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //-----------------------------------------------------------------------------
    // Add "view" to right end of "views".
    // Returns the position of the new view.
    // The app should call this to add pages; not used by ViewPager.
    public int addView(View v, int i, Quangcao quangcao) {
        quangCaoList.add(quangCaoList.size(), quangcao);
        return addView(v, views.size());
    }

    //-----------------------------------------------------------------------------
    // Add "view" at "position" to "views".
    // Returns position of new view.
    // The app should call this to add pages; not used by ViewPager.
    public int addView(View v, int position) {
        views.add(position, v);
        return position;
    }

    //-----------------------------------------------------------------------------
    // Removes "view" from "views".
    // Retuns position of removed view.
    // The app should call this to remove pages; not used by ViewPager.
    public int removeView(ViewPager pager, View v) {
        return removeView(pager, views.indexOf(v));
    }

    //-----------------------------------------------------------------------------
    // Removes the "view" at "position" from "views".
    // Retuns position of removed view.
    // The app should call this to remove pages; not used by ViewPager.
    public int removeView(ViewPager pager, int position) {
        // ViewPager doesn't have a delete method; the closest is to set the adapter
        // again.  When doing so, it deletes all its views.  Then we can delete the view
        // from from the adapter and finally set the adapter to the pager again.  Note
        // that we set the adapter to null before removing the view from "views" - that's
        // because while ViewPager deletes all its views, it will call destroyItem which
        // will in turn cause a null pointer ref.
        pager.setAdapter(null);
        views.remove(position);
        pager.setAdapter(this);

        return position;
    }

    //-----------------------------------------------------------------------------
    // Returns the "view" at "position".
    // The app should call this to retrieve a view; not used by ViewPager.
    public View getView(int position) {
        return views.get(position);
    }

}
