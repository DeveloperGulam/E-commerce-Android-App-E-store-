package com.dattaprabodhinee.estore.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dattaprabodhinee.estore.Api;
import com.dattaprabodhinee.estore.Models.ResponseModel;
import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.RetrofitIntance;
import com.dattaprabodhinee.estore.databinding.FragmentSignupBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupFragment extends Fragment {

    public SignupFragment() {
        // Required empty public constructor
    }

    FragmentSignupBinding binding;
    Api api;
    ProgressDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("We're creating your account...");

        binding.loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout, new LoginFragment()).addToBackStack(Fragment.class.getSimpleName()).commit();
            }
        });

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                api = RetrofitIntance.getRetrofit().create(Api.class);
                api.SignUpMethod(
                        binding.fullName.getText().toString(),
                        binding.email.getText().toString(),
                        binding.number.getText().toString(),
                        binding.password.getText().toString()
                ).enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        dialog.dismiss();
                        ResponseModel resp = response.body();
                        if (response.isSuccessful()) {
                            if (resp.getResponse().equals("true")){
                                Toast.makeText(getContext(), "Signup Success..", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(getContext(), resp.getResponse(), Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(getContext(), resp.getResponse(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        return binding.getRoot();
    }
}