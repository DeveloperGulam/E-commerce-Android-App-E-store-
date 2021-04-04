package com.dattaprabodhinee.estore.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.dattaprabodhinee.estore.Fragments.ProductDetailsFragment;
import com.dattaprabodhinee.estore.Models.ProductModel;
import com.dattaprabodhinee.estore.R;

import java.util.List;

public class BannerPagerAdapter extends PagerAdapter {

    Context context;
    List<ProductModel> list;
    String BASEURL = "https://estore.dattaprabodhinee.com/assets/images/product/";

    public BannerPagerAdapter(Context context, List<ProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ProductModel bannerModel = list.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View view1 = LayoutInflater.from(context).inflate(R.layout.banner_layout, container, false);
        View view = inflater.inflate(R.layout.banner_layout, null);

        ImageView bannerImg = view.findViewById(R.id.banner_img);
        TextView productTitle = view.findViewById(R.id.product_title);
        TextView off = view.findViewById(R.id.product_off);

        Glide.with(context).load(BASEURL + bannerModel.getProduct_img()).placeholder(R.drawable.fav).error(R.drawable.fav).into(bannerImg);
        productTitle.setText(bannerModel.getName());
        int orignal_price = bannerModel.getOrignal_price();
        int discount_price = bannerModel.getPrice();
        int value = (discount_price*100)/orignal_price;
        int off_value = 100-value;
        off.setText("GET "+off_value+"% OFF");

        int pid = bannerModel.getId();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment fragment = new ProductDetailsFragment();
                Bundle args =  new Bundle();
                args.putInt("pid", pid);
                fragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).addToBackStack(Fragment.class.getSimpleName()).commit();
            }
        });

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
