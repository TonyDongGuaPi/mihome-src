package com.xiaomi.jr.mipay.codepay.api;

import com.xiaomi.jr.http.model.MiFiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MifiApi {
    @GET("v1/consumptionLoan/category")
    Call<MiFiResponse<Integer>> a();

    @GET("v1/mipay/chooseBankCard/h5")
    Call<MiFiResponse<String>> a(@Query("returnUrl") String str, @Query("skipChooseCard") int i, @Query("extraInfo") String str2);
}
