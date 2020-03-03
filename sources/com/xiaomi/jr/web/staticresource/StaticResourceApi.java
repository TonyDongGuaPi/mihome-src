package com.xiaomi.jr.web.staticresource;

import com.xiaomi.jr.http.model.MiFiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StaticResourceApi {
    @GET("v1/appResource/getUpdateInfo")
    Call<MiFiResponse<UpdateInfo>> a(@Query("timestamp") long j, @Query("md5") String str);
}
