package com.dattaprabodhinee.estore.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.dattaprabodhinee.estore.Api;
import com.dattaprabodhinee.estore.Models.ResponseModel;
import com.dattaprabodhinee.estore.R;
import com.dattaprabodhinee.estore.RetrofitIntance;
import com.dattaprabodhinee.estore.databinding.ActivityCheckoutBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {

    ActivityCheckoutBinding binding;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        api = RetrofitIntance.getRetrofit().create(Api.class);

        Intent intent = getIntent();
        int pid = intent.getIntExtra("pid", 0);
        int shippingCharge = 20;
        int weight = 1000000;

        api.GetProductDetails(pid).enqueue(new Callback<ResponseModel>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                if (response.isSuccessful()){
                    if (responseModel.getResponse().equals("true")) {

                        int discountPrice = responseModel.getProductInfo().getPrice();
                        String packingType = responseModel.getProductInfo().getPacking_type();
                        int packingSize = responseModel.getProductInfo().getPacking_size();
                        int packingWeight = 0;
                        int deliveryCharge = 100;

                        binding.productName.setText(responseModel.getProductInfo().getName());
                        binding.productPrice.setText("₹" + String.valueOf(discountPrice) + ".00");
                        binding.subTotal.setText("₹" + String.valueOf(discountPrice) + ".00");
                        binding.shippingCharge.setText("₹" + String.valueOf(shippingCharge) + ".00");

                        if (packingType == "gm") {
                            packingWeight = packingSize * 1000;
                        }
                        if (packingType == "kg") {
                            packingWeight = packingSize * 1000000;
                        }
                        if (packingType == "l") {
                            packingWeight = packingSize * 1000;
                        }

                        if (packingWeight <= 1000000) {
                            deliveryCharge = 100;
                        }
                        if (packingWeight > 1000000 & packingWeight <= 2000000) {
                            deliveryCharge = 200;
                        }
                        if (packingWeight > 2000000 & packingWeight <= 3000000) {
                            deliveryCharge = 300;
                        }
                        if (packingWeight > 3000000 & packingWeight <= 4000000) {
                            deliveryCharge = 400;
                        }
                        if (packingWeight > 4000000 & packingWeight <= 5000000) {
                            deliveryCharge = 500;
                        }
                        if (packingWeight > 5000000 & packingWeight <= 6000000) {
                            deliveryCharge = 600;
                        }
                        if (packingWeight > 6000000 & packingWeight <= 7000000) {
                            deliveryCharge = 700;
                        }
                        if (packingWeight > 7000000 & packingWeight <= 8000000) {
                            deliveryCharge = 800;
                        }
                        if (packingWeight > 8000000 & packingWeight <= 9000000) {
                            deliveryCharge = 900;
                        }
                        if (packingWeight > 9000000 & packingWeight <= 10000000) {
                            deliveryCharge = 1000;
                        }
                        if (packingWeight > 10000000) {
                            binding.placeOrderBtn.setBackgroundColor(R.color.gray);
                            binding.placeOrderBtn.setTextColor(R.color.black);
                            binding.placeOrderBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(CheckoutActivity.this, "Sorry! You can't proceed with this order beacause your order weight exceed 10kg. Please sure your order weight less then 10kg.", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        binding.deliveryCharge.setText("₹" + String.valueOf(deliveryCharge) + ".00");
                        int grandTotal = discountPrice + shippingCharge + deliveryCharge;
                        binding.grandTotal.setText("₹" + String.valueOf(grandTotal) + ".00");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });


        //Place Order Button
        binding.placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = binding.addressType.getCheckedRadioButtonId();
                RadioButton selectedAddressType = (RadioButton) findViewById(selectedId);
                String addressTypeValue = selectedAddressType.getText().toString();
                String addressType = addressTypeValue.toLowerCase();

                Toast.makeText(CheckoutActivity.this, addressType, Toast.LENGTH_SHORT).show();
            }
        });
    }
}