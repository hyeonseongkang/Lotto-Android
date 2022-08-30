package com.mirror.lotto_android.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// singleton pattern
public class LottoClient {
    private static final String BASE_URL =  "https://www.dhlottery.co.kr/";
    private static Retrofit retrofitClient;

    public static ILottoServiceAPI getAPIService() { return getRetrofit().create(ILottoServiceAPI.class);}

    public static Retrofit getRetrofit() {
        if (retrofitClient == null) {
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofitClient;
    }
}
