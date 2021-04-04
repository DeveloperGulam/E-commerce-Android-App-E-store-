package com.dattaprabodhinee.estore.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.databinding.FragmentMyOrdersBinding;

public class MyOrdersFragment extends Fragment {

    public MyOrdersFragment() {
        // Required empty public constructor
    }

    FragmentMyOrdersBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMyOrdersBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}