package com.dattaprabodhinee.estore.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dattaprabodhinee.estore.Adapters.CategoriesAdapter;
import com.dattaprabodhinee.estore.Api;
import com.dattaprabodhinee.estore.Models.CategoriesModel;
import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.RetrofitIntance;
import com.dattaprabodhinee.estore.databinding.FragmentCategoryBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    public CategoryFragment() {
        // Required empty public constructor
    }

    FragmentCategoryBinding binding;
    Api api;
    ArrayList<CategoriesModel> categoryList;
    CategoriesAdapter adapter;
    ProgressDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");
        dialog.show();

        api = RetrofitIntance.getRetrofit().create(Api.class);
        api.getAllCategories().enqueue(new Callback<ArrayList<CategoriesModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoriesModel>> call, Response<ArrayList<CategoriesModel>> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        categoryList = response.body();
                        adapter = new CategoriesAdapter(getContext(), categoryList);
                        binding.allCategoryList.setAdapter(adapter);
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
            public void onFailure(Call<ArrayList<CategoriesModel>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }
}