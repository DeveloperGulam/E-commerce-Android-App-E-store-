package com.dattaprabodhinee.estore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitIntance {
    private static Retrofit retrofit;
    private static final String BASEURL = "https://estore.dattaprabodhinee.com/";
    private static OkHttpClient.Builder builder= new OkHttpClient.Builder();
    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    public static Retrofit getRetrofit() {

        if (retrofit == null) {
            Gson gson = new GsonBuilder().setLenient().create();
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);

            retrofit = new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create())
                    .client(builder.build())
                    .build();
        }
        return retrofit;
    }
}
