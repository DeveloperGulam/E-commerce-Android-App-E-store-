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
import com.dattaprabodhinee.estore.Fragments.ProductDetailsFragment;
import com.dattaprabodhinee.estore.Models.ProductModel;
import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.databinding.ProductsLayoutBinding;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.productViewHolder> {

    Context context;
    ArrayList<ProductModel> list;
    String BASEURL = "https://estore.dattaprabodhinee.com/assets/images/product/";

    public ProductAdapter(Context context, ArrayList<ProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public productViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.products_layout, parent, false);
        return new productViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productViewHolder holder, int position) {
        ProductModel model = list.get(position);
        Glide.with(context).load(BASEURL + model.getProduct_img()).placeholder(R.drawable.fav).error(R.drawable.fav).into(holder.binding.productImg);
        holder.binding.productTitle.setText(model.getName());
        if (model.getStock() > 0) {
            holder.binding.productStock.setText("Available (In Stock)");
        }
        else {
            holder.binding.productStock.setText("Out Of Stock");
        }
        holder.binding.orignalPrice.setText("₹" + String.valueOf(model.getOrignal_price()));
        holder.binding.discountPrice.setText("₹" + String.valueOf(model.getPrice()));

        int pid = model.getId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class productViewHolder extends RecyclerView.ViewHolder {
        ProductsLayoutBinding binding;
        public productViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ProductsLayoutBinding.bind(itemView);
        }
    }
}
