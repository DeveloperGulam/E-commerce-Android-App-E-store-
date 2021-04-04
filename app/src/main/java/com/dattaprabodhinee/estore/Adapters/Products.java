package com.dattaprabodhinee.estore.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dattaprabodhinee.estore.Api;
import com.dattaprabodhinee.estore.Fragments.ProductDetailsFragment;
import com.dattaprabodhinee.estore.Models.ProductModel;
import com.dattaprabodhinee.estore.Models.ResponseModel;
import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.RetrofitIntance;
import com.dattaprabodhinee.estore.databinding.SingleProductBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Products extends RecyclerView.Adapter<Products.viewHolder> {

    Context context;
    ArrayList<ProductModel> list;
    String BASEURL = "https://estore.dattaprabodhinee.com/assets/images/product/";
    Api api;

    public Products(Context context, ArrayList<ProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_product, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ProductModel productModel = list.get(position);
        Glide.with(context).load(BASEURL + productModel.getProduct_img()).placeholder(R.drawable.fav).error(R.drawable.fav).into(holder.binding.productImg);
        holder.binding.productTitle.setText(productModel.getName());
        if (productModel.getStock() > 0) {
            holder.binding.productStock.setText("Available (In Stock)");
        }
        else {
            holder.binding.productStock.setText("Out Of Stock");
        }
        holder.binding.orignalPrice.setText("₹" + String.valueOf(productModel.getOrignal_price()));
        holder.binding.discountPrice.setText("₹" + String.valueOf(productModel.getPrice()));

        int pid = productModel.getId();
        api = RetrofitIntance.getRetrofit().create(Api.class);

        //Add To Cart
        holder.binding.cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                api.AddToCart(pid).enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getResponse().equals("true")) {
                                Toast.makeText(context, "Added Into Cart", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "Something went to wrong!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

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

    public class viewHolder extends RecyclerView.ViewHolder {

        SingleProductBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SingleProductBinding.bind(itemView);
        }
    }
}
