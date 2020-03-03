package com.xiaomi.jr;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface QualityApi {
    @FormUrlEncoded
    @POST("credit/api/monitor/log")
    Call<Void> a(@Field("end") int i, @Field("count") boolean z, @Field("category") String str, @Field("event") String str2, @Field("data") String str3, @Field("time") long j);
}
