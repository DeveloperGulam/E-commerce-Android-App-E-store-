package com.dattaprabodhinee.estore.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dattaprabodhinee.estore.Fragments.ProductFragment;
import com.dattaprabodhinee.estore.Models.CategoriesModel;
import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.databinding.CategoryRowBinding;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.viewHolder> {

    Context context;
    ArrayList<CategoriesModel> list;
    String BASEURL = "https://estore.dattaprabodhinee.com/assets/images/category/";

    public CategoriesAdapter(Context context, ArrayList<CategoriesModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(context).inflate(R.layout.category_row, parent, false);
        return new viewHolder(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CategoriesModel model = list.get(position);
        holder.binding.categoriesName.setText(model.getName());
        Glide.with(context).load(BASEURL + model.getCategory_img()).placeholder(R.drawable.fav).error(R.drawable.fav).into(holder.binding.categoriesImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment fragment = new ProductFragment();
                Bundle args =  new Bundle();
                args.putInt("cid", model.getId());
                args.putString("category_name", model.getName());
                fragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).addToBackStack(Fragment.class.getSimpleName()).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        CategoryRowBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CategoryRowBinding.bind(itemView);
        }
    }
}
