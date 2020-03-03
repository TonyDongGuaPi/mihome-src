package com.xiaomi.jr.antifraud;

import com.xiaomi.jr.http.model.MiFiResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface AntiFraudApi {
    @FormUrlEncoded
    @POST
    Call<MiFiResponse<Void>> a(@Url String str, @Field("por") String str2);
}
