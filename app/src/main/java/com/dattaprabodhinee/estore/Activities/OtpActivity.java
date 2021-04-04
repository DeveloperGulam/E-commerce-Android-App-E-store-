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
import com.dattaprabodhinee.estore.databinding.ActivityOtpBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {

    ActivityOtpBinding binding;
    ProgressDialog dialog;
    Api api;
    SharedPrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        api = RetrofitIntance.getRetrofit().create(Api.class);
        prefManager = new SharedPrefManager(OtpActivity.this);

        dialog = new ProgressDialog(OtpActivity.this);
        dialog.setMessage("Verifying...");

        Intent intent = getIntent();
        int otp = intent.getIntExtra("otpcode", 0);
        String number = intent.getStringExtra("otpnumber");
        String uid = intent.getStringExtra("insert_id");
        String message = intent.getStringExtra("message");

        binding.otpVerifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                int user_otp = Integer.parseInt(binding.userOtp.getText().toString());
                if (user_otp == otp) {
                    api.VerifyOtp(otp, number, uid, message).enqueue(new Callback<UserResponseModel>() {
                        @Override
                        public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {
                            dialog.dismiss();
                            UserResponseModel responseModel = response.body();
                            if (response.isSuccessful()) {
                                if (responseModel.getResponse().equals("true")) {
                                    prefManager.saveUser(responseModel.getUserdata());
                                    Intent i = new Intent(OtpActivity.this, MainActivity.class);
                                    startActivity(i);
                                    finishAffinity();
                                }
                                else {
                                    Toast.makeText(OtpActivity.this, responseModel.getResponse(), Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(OtpActivity.this, response.message(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponseModel> call, Throwable t) {
                            dialog.dismiss();
                            Toast.makeText(OtpActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    dialog.dismiss();
                    binding.userOtp.setError("You have enter wrong OTP!");
                }
            }
        });
    }
}