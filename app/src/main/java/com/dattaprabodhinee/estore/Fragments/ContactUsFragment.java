package com.dattaprabodhinee.estore.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.databinding.FragmentContactUsBinding;

public class ContactUsFragment extends Fragment {

    public ContactUsFragment() {
        // Required empty public constructor
    }

    FragmentContactUsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentContactUsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}