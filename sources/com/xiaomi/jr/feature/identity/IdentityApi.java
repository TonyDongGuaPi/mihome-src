package com.xiaomi.jr.feature.identity;

import com.xiaomi.jr.http.model.MiFiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IdentityApi {
    @GET("jr/identity/sign")
    Call<MiFiResponse<MiFiSignInfo>> a(@Query("partnerId") String str, @Query("logId") String str2);
}
