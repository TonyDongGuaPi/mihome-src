package com.xiaomi.jr.http.netopt;

import com.xiaomi.jr.http.model.MiFiResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DiagnosisApi {
    @FormUrlEncoded
    @POST("v1/app/net")
    Call<MiFiResponse<Void>> a(@Field("data") String str);
}
