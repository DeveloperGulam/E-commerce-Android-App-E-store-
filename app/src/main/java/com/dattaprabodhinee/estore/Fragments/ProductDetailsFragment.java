package com.dattaprabodhinee.estore.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dattaprabodhinee.estore.Activities.CheckoutActivity;
import com.dattaprabodhinee.estore.Api;
import com.dattaprabodhinee.estore.Models.ResponseModel;
import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.RetrofitIntance;
import com.dattaprabodhinee.estore.databinding.FragmentProductDetailsBinding;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsFragment extends Fragment {

    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    FragmentProductDetailsBinding binding;
    Api api;
    ProgressDialog dialog;
    String BASEURL = "https://estore.dattaprabodhinee.com/assets/images/product/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false);

        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.show();
        api = RetrofitIntance.getRetrofit().create(Api.class);
        int pid =  getArguments().getInt("pid");

        api.GetProductDetails(pid).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                dialog.dismiss();
                ResponseModel responseModel = response.body();
                if (response.isSuccessful()) {
                    if (response.body().getResponse().equals("true")) {
                        binding.productName.setText(responseModel.getProductInfo().getName());
                        binding.productNumber.setText(responseModel.getProductInfo().getProduct_no());
                        int stock = responseModel.getProductInfo().getStock();
                        if (stock > 0) {
                            binding.productStock.setText("Available (" +stock+ " In Stock)");
                        }
                        else {
                            binding.productStock.setText("Out Of Stock");
                        }
                        binding.discountPrice.setText("₹" + String.valueOf(responseModel.getProductInfo().getPrice()));
                        binding.oldPrice.setText("₹" + String.valueOf(responseModel.getProductInfo().getOrignal_price()));
                        binding.productDescription.setText(Html.fromHtml(responseModel.getProductInfo().getDescription()));
                        Glide.with(getActivity()).load(BASEURL + responseModel.getProductInfo().getProduct_img()).placeholder(R.drawable.fav).error(R.drawable.fav).into(binding.productImage);
                    }
                    else {
                        Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        //Buy Now
        binding.butNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CheckoutActivity.class);
                intent.putExtra("pid", pid);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}