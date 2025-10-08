package com.example.simpletax.taxApi;

import com.example.simpletax.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    /*
     * Add the follow return data to your retrofit call, for whichever url you set.
     *  [
     *      {"name":"BC Tip Deductibles","id":"BCTD","deductiblePercent":0.18,"minGrossIncome":200},
     *      {"name":"Moving Expenses","id":"","deductiblePercent":0.1,"minGrossIncome":40},
     *      {"name":"WSET Return","id":"WSET","deductiblePercent":1,"minGrossIncome":150}
     *  ]
     */
    static Retrofit retrofit;


    static Retrofit getRetrofitClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit;
    }

}
 
