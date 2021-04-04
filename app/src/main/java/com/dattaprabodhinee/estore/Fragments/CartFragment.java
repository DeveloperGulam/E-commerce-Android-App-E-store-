package com.dattaprabodhinee.estore.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dattaprabodhinee.estore.Activities.CheckoutActivity;
import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.databinding.FragmentCartBinding;

public class CartFragment extends Fragment {

    public CartFragment() {
        // Required empty public constructor
    }

    FragmentCartBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false);

        binding.checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CheckoutActivity.class);
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }
}