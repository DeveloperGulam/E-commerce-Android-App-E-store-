package com.dattaprabodhinee.estore.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dattaprabodhinee.estore.Activities.MainActivity;
import com.dattaprabodhinee.estore.Activities.OtpActivity;
import com.dattaprabodhinee.estore.Api;
import com.dattaprabodhinee.estore.Models.UserResponseModel;
import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.RetrofitIntance;
import com.dattaprabodhinee.estore.SharedPrefManager;
import com.dattaprabodhinee.estore.databinding.FragmentLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }
    FragmentLoginBinding binding;
    Api api;
    SharedPrefManager prefManager;
    ProgressDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        api = RetrofitIntance.getRetrofit().create(Api.class);
        prefManager = new SharedPrefManager(getActivity());
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Login...");

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.userName.getText().toString().isEmpty()) {
                    binding.userName.setError("Enter email or mobile number");
                    return;
                }
                if (binding.password.getText().toString().isEmpty()) {
                    binding.password.setError("Enter your password");
                    return;
                }
                dialog.show();
                api.LoginMethod(binding.userName.getText().toString(), binding.password.getText().toString()).enqueue(new Callback<UserResponseModel>() {
                    @Override
                    public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {
                        dialog.dismiss();
                        UserResponseModel responseModel = response.body();
                        if (response.isSuccessful()) {
                            if (responseModel.getResponse().equals("true")) {
                                prefManager.saveUser(responseModel.getUserdata());
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new HomeFragment()).commit();
                            }
                            else if (responseModel.getResponse().equals("otp")) {
                                Intent intent = new Intent(getActivity(), OtpActivity.class);
                                intent.putExtra("otpcode", responseModel.getOtpdata().getOtpcode());
                                intent.putExtra("otpnumber", responseModel.getOtpdata().getOtpnumber());
                                intent.putExtra("insert_id", responseModel.getOtpdata().getInsert_id());
                                intent.putExtra("message", responseModel.getOtpdata().getMessage());
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getContext(), responseModel.getResponse(), Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(getContext(), response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponseModel> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        binding.signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new SignupFragment()).addToBackStack(Fragment.class.getSimpleName()).commit();
            }
        });

        return binding.getRoot();
    }
}