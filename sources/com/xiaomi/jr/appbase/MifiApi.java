package com.xiaomi.jr.appbase;

import com.xiaomi.jr.appbase.configuration.Configuration;
import com.xiaomi.jr.http.model.MiFiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MifiApi {
    @GET("v1/appConfig")
    Call<MiFiResponse<Configuration>> a(@Query("packageName") String str, @Query("deviceId") String str2);
}
