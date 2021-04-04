package com.dattaprabodhinee.estore.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dattaprabodhinee.estore.Api;
import com.dattaprabodhinee.estore.Models.UserResponseModel;
import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.RetrofitIntance;
import com.dattaprabodhinee.estore.SharedPrefManager;
import com.dattaprabodhinee.estore.databinding.ActivityEditProfileBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    ActivityEditProfileBinding binding;
    SharedPrefManager prefManager;
    Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        api = RetrofitIntance.getRetrofit().create(Api.class);

        prefManager = new SharedPrefManager(EditProfileActivity.this);
        if (prefManager.isLoggedIn()) {
            int uid = prefManager.getUser().getId();
            binding.fullName.setText(prefManager.getUser().getName());
            binding.number.setText(prefManager.getUser().getNumber());
            binding.email.setText(prefManager.getUser().getEmail());
            binding.city.setText(prefManager.getUser().getCity());
            binding.state.setText(prefManager.getUser().getState());
            binding.pincode.setText(prefManager.getUser().getZipcode());
            binding.landmark.setText(prefManager.getUser().getLandmark());
            binding.address.setText(prefManager.getUser().getAddress());

            binding.updateProfileBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    api.UpdateAccount(
                            uid,
                            binding.fullName.getText().toString(),
                            binding.email.getText().toString(),
                            binding.number.getText().toString(),
                            binding.city.getText().toString(),
                            binding.state.getText().toString(),
                            binding.pincode.getText().toString(),
                            binding.landmark.getText().toString(),
                            binding.address.getText().toString()
                    ).enqueue(new Callback<UserResponseModel>() {
                        @Override
                        public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getResponse().equals("true")) {
                                    //prefManager.updateUser(response.body().getUserdata());
                                    Toast.makeText(EditProfileActivity.this, "Profile Updated Successfully.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                }
                                else {
                                    Toast.makeText(EditProfileActivity.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(EditProfileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponseModel> call, Throwable t) {
                            Toast.makeText(EditProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        else {
            Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
            startActivity(intent);
            finishAffinity();
        }
    }
}