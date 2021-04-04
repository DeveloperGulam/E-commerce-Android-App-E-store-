package com.dattaprabodhinee.estore;

import com.dattaprabodhinee.estore.Models.CategoriesModel;
import com.dattaprabodhinee.estore.Models.ProductModel;
import com.dattaprabodhinee.estore.Models.ResponseModel;
import com.dattaprabodhinee.estore.Models.UserResponseModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @GET("Api/categories")
    Call<ArrayList<CategoriesModel>> getAllCategories();

    @GET("Api/nine_categories")
    Call<ArrayList<CategoriesModel>> getNineCategories();

    @GET("Api/banner_products")
    Call<ArrayList<ProductModel>> BannerProducts();

    @GET("Api/astrology_books")
    Call<ArrayList<ProductModel>> AstrologyBooks();

    @GET("Api/photos_and_murties")
    Call<ArrayList<ProductModel>> PhotosAndMurties();

    @GET("Api/latest_products")
    Call<ArrayList<ProductModel>> LatestProducts();

    @FormUrlEncoded
    @POST("api/product_details")
    Call<ResponseModel> GetProductDetails(@Field("pid") int pid);

    @FormUrlEncoded
    @POST("api/search_product")
    Call<ArrayList<ProductModel>> SearchProduct(@Field("input_value") String input_value);

    @FormUrlEncoded
    @POST("Api/signup")
    Call<ResponseModel> SignUpMethod(
            @Field("name") String name,
            @Field("email") String email,
            @Field("number") String number,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("Api/user_login")
    Call<UserResponseModel> LoginMethod(
            @Field("username") String username,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("Api/verify_otp")
    Call<UserResponseModel> VerifyOtp(
            @Field("otpcode") int otpcode,
            @Field("otpnumber") String otpnumber,
            @Field("insert_id") String insert_id,
            @Field("message") String message
    );

    @FormUrlEncoded
    @POST("Api/update_profile")
    Call<UserResponseModel> UpdateAccount(
            @Field("uid") int uid,
            @Field("name") String name,
            @Field("email") String email,
            @Field("number") String number,
            @Field("city") String city,
            @Field("state") String state,
            @Field("pincode") String pincode,
            @Field("landmark") String landmark,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("Api/get_products")
    Call<ArrayList<ProductModel>> GetProductByCategory(@Field("cid") int cid);

    @FormUrlEncoded
    @POST("Api/productByCategory")
    Call<ArrayList<ProductModel>> productByCategory(@Field("cid") int cid);

    @FormUrlEncoded
    @POST("Api/my_orders")
    Call<ArrayList<ProductModel>> getMyOrders(@Field("uid") int uid);

    @GET("Api/all_products")
    Call<ArrayList<ProductModel>> GetNewProducts();

    @FormUrlEncoded
    @POST("api/add_to_cart")
    Call<ResponseModel> AddToCart(@Field("pid") int pid);
}
