package com.xiaomi.jr.agreement.api;

import com.xiaomi.jr.agreement.AgreementUpdateInfo;
import com.xiaomi.jr.http.model.MiFiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface MifiAgreementApi {
    @GET
    Call<MiFiResponse<AgreementUpdateInfo>> a(@Url String str);

    @POST
    Call<MiFiResponse<Void>> a(@Url String str, @Query("protocolIds") String str2);
}
