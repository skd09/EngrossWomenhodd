package com.sharvari.engrosswomenhodd.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sharvaridivekar on 10/03/18.
 */

public class RetrofitClient {

    public static final String BASE_URL = "https://womaniya.herokuapp.com/";
    //public static final String BASE_URL = "https://183.87.195.108:5000/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
