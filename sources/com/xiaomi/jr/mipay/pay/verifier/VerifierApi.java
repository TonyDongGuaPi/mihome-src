package com.xiaomi.jr.mipay.pay.verifier;

import com.xiaomi.jr.mipay.pay.verifier.model.VerifyResult;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface VerifierApi {
    @FormUrlEncoded
    @POST("api/paypass/validate")
    Call<VerifyResult> a(@Field("processId") String str, @Field("payPass") String str2, @Field("encrypted") boolean z);
}
