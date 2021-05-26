package com.sabroso.qms;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient2 {
    public static final String BASE_URL2 = "http://qms.sabroso.com.pk/api/Common/";
    private static Retrofit retrofit2 = null;
    public static Retrofit getClient2() {
        if (retrofit2 == null) {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(BASE_URL2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit2;
    }
}
