package com.dattaprabodhinee.estore;

import android.content.Context;
import android.content.SharedPreferences;

import com.dattaprabodhinee.estore.Models.ProductModel;
import com.dattaprabodhinee.estore.Models.UserData;

public class SharedPrefManager {
    private static String PREF_NAME = "Gulam Gaus";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveUser(UserData userData){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("uid", userData.getId());
        editor.putString("name", userData.getName());
        editor.putString("profileImage", userData.getCustomer_img());
        editor.putString("email", userData.getEmail());
        editor.putString("number", userData.getNumber());
        editor.putString("city", userData.getCity());
        editor.putString("pincode", userData.getZipcode());
        editor.putString("state", userData.getState());
        editor.putString("landmark", userData.getLandmark());
        editor.putString("address", userData.getAddress());
        editor.putBoolean("logged", true);
        editor.apply();
    }

    public boolean isLoggedIn() {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged", false);
    }

    public UserData getUser() {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return new UserData(
                sharedPreferences.getInt("uid", -1),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("profileImage", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("number", null),
                sharedPreferences.getString("city", null),
                sharedPreferences.getString("pincode", null),
                sharedPreferences.getString("state", null),
                sharedPreferences.getString("landmark", null),
                sharedPreferences.getString("address", null)
        );
    }

    public void updateUser(UserData userData){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("name", userData.getName());
        editor.putString("email", userData.getEmail());
        editor.putString("number", userData.getNumber());
        editor.putString("city", userData.getCity());
        editor.putString("pincode", userData.getZipcode());
        editor.putString("state", userData.getState());
        editor.putString("landmark", userData.getLandmark());
        editor.putString("address", userData.getAddress());
        editor.commit();
    }

    public void logout(){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

//    public void AddToCart(ProductModel productModel) {
//        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//        editor.putInt("pid", productModel.getId());
//        editor.putString("seller_id", productModel.getUser_id());
//        editor.putString("product_name", productModel.getName());
//        editor.putString("qty", "1");
//        editor.putString("product_image", productModel.getProduct_img());
//        editor.putString("product_no", productModel.getProduct_no());
//        editor.putString("orignal_price", productModel.getOrignal_price());
//        editor.putString("discount_price", productModel.getPrice());
//        editor.putString("packing_size", productModel.getPacking_size());
//        editor.putString("packing_type", productModel.getPacking_type());
//        editor.putString("sub_category", "0");
//    }
}
