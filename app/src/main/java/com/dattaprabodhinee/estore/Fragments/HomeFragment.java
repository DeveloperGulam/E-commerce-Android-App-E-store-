package com.dattaprabodhinee.estore.Fragments;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dattaprabodhinee.estore.Adapters.BannerPagerAdapter;
import com.dattaprabodhinee.estore.Adapters.CategorySliderAdapter;
import com.dattaprabodhinee.estore.Adapters.ProductAdapter;
import com.dattaprabodhinee.estore.Api;
import com.dattaprabodhinee.estore.Models.CategoriesModel;
import com.dattaprabodhinee.estore.Models.ProductModel;
import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.RetrofitIntance;
import com.dattaprabodhinee.estore.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    Dialog dialog;
    FragmentHomeBinding binding;
    List<ProductModel> list;
    BannerPagerAdapter adapter;

    ArrayList<CategoriesModel> categoryList;
    CategorySliderAdapter categoryAdapter;

    ArrayList<ProductModel> productList;
    ProductAdapter productAdapter;

    Api api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        dialog = new Dialog(getContext());
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.loader_layout);
        dialog.setTitle("Loading......");
        dialog.show();
        api = RetrofitIntance.getRetrofit().create(Api.class);

        //Banner
        api.BannerProducts().enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    adapter = new BannerPagerAdapter(getContext(), list);
                    binding.bannerViewpager.setAdapter(adapter);
                }
                else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //Categories
        api.getNineCategories().enqueue(new Callback<ArrayList<CategoriesModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoriesModel>> call, Response<ArrayList<CategoriesModel>> response) {
                if (response.isSuccessful()){
                    categoryList = response.body();
                    categoryAdapter = new CategorySliderAdapter(getContext(), categoryList);
                    binding.categories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    binding.categories.setAdapter(categoryAdapter);
                }
                else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CategoriesModel>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //Astrology Books
        api.AstrologyBooks().enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    productAdapter = new ProductAdapter(getContext(), productList);
                    binding.astrologyBooks.setAdapter(productAdapter);
                }
                else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.viewBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProductFragment();
                Bundle args =  new Bundle();
                args.putInt("cid", 11);
                args.putString("category_name", "Astrology Books");
                fragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).addToBackStack(Fragment.class.getSimpleName()).commit();
            }
        });

        //Photos and Murties
        api.PhotosAndMurties().enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    productAdapter = new ProductAdapter(getContext(), productList);
                    binding.photosAndMurties.setAdapter(productAdapter);
                }
                else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.viewMurties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProductFragment();
                Bundle args =  new Bundle();
                args.putInt("cid", 21);
                args.putString("category_name", "Photos & Murtis");
                fragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment).addToBackStack(Fragment.class.getSimpleName()).commit();
            }
        });

        //Latest Products
        api.LatestProducts().enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    productList = response.body();
                    productAdapter = new ProductAdapter(getContext(), productList);
                    binding.latestProducts.setAdapter(productAdapter);
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

        binding.viewAllProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new NewProductFragment()).addToBackStack(Fragment.class.getSimpleName()).commit();
            }
        });
        return binding.getRoot();
    }
}