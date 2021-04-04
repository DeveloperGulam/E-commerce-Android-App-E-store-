package com.dattaprabodhinee.estore.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dattaprabodhinee.estore.Adapters.Products;
import com.dattaprabodhinee.estore.Api;
import com.dattaprabodhinee.estore.Models.ProductModel;
import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.RetrofitIntance;
import com.dattaprabodhinee.estore.databinding.FragmentNewProductBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewProductFragment extends Fragment {

    public NewProductFragment() {
        // Required empty public constructor
    }

    FragmentNewProductBinding binding;
    ArrayList<ProductModel> products;
    Products adapter;
    ProgressDialog dialog;
    Api api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewProductBinding.inflate(inflater, container, false);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");
        dialog.show();

        api = RetrofitIntance.getRetrofit().create(Api.class);
        api.GetNewProducts().enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        products = response.body();
                        adapter = new Products(getContext(), products);
                        binding.productList.setAdapter(adapter);
                    }
                    else {
                        Toast.makeText(getContext(), "No Data Found.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }
}