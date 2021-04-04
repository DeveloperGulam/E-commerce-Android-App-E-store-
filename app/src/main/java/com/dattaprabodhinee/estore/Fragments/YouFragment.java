package com.dattaprabodhinee.estore.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dattaprabodhinee.estore.Activities.EditProfileActivity;
import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.SharedPrefManager;
import com.dattaprabodhinee.estore.databinding.FragmentYouBinding;

public class YouFragment extends Fragment {

    public YouFragment() {
        // Required empty public constructor
    }

    FragmentYouBinding binding;
    SharedPrefManager prefManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentYouBinding.inflate(inflater, container, false);

        prefManager = new SharedPrefManager(getContext());
        if (prefManager.isLoggedIn()) {

            binding.username.setText(prefManager.getUser().getName());
            binding.fullName.setText(prefManager.getUser().getName());
            binding.mobileNumber.setText(prefManager.getUser().getNumber());
            binding.contactNumber.setText(prefManager.getUser().getNumber());
            binding.userEmail.setText(prefManager.getUser().getEmail());
            binding.userCity.setText(prefManager.getUser().getCity());
            binding.userState.setText(prefManager.getUser().getState());
            binding.userPincode.setText(prefManager.getUser().getZipcode());
            binding.userLandmark.setText(prefManager.getUser().getLandmark());
            binding.userAddress.setText(prefManager.getUser().getAddress());
            Glide.with(getContext())
                    .load(prefManager.getUser().getCustomer_img())
                    .placeholder(R.drawable.default_profile_picture)
                    .error(R.drawable.default_profile_picture)
                    .into(binding.profileImage);

            binding.editProfileBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), EditProfileActivity.class);
                    startActivity(intent);
                }
            });
        }
        else {
            getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new LoginFragment()).commit();
        }

        return binding.getRoot();
    }
}