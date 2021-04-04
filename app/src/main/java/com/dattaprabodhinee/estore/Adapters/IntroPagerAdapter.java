package com.dattaprabodhinee.estore.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.dattaprabodhinee.estore.Models.IntroModel;
import com.dattaprabodhinee.estore.R;

import java.util.List;

public class IntroPagerAdapter extends PagerAdapter {

    Context context;
    List<IntroModel> list;

    public IntroPagerAdapter(Context context, List<IntroModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        IntroModel introModel = list.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.intro_screen_layout, null);

        ImageView intro_img = view.findViewById(R.id.intro_img);
        TextView intro_title = view.findViewById(R.id.intro_title);
        TextView intro_desc = view.findViewById(R.id.intro_description);

        intro_img.setImageResource(introModel.getIntroImage());
        intro_title.setText(introModel.getTitle());
        intro_desc.setText(introModel.getDescription());

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
