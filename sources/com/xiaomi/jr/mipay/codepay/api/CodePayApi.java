package com.xiaomi.jr.mipay.codepay.api;

import com.xiaomi.jr.http.RetainRawResponse;
import com.xiaomi.jr.mipay.codepay.data.DoPayResult;
import com.xiaomi.jr.mipay.codepay.data.PayCode;
import com.xiaomi.jr.mipay.codepay.data.PayResult;
import com.xiaomi.jr.mipay.codepay.data.TradeResult;
import com.xiaomi.jr.mipay.common.model.MipayResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CodePayApi {
    @FormUrlEncoded
    @RetainRawResponse
    @POST("api/codePay/code/query/result")
    Call<TradeResult> a(@Field("tradeId") String str);

    @FormUrlEncoded
    @POST("api/payment/code/v3/create")
    Call<PayCode> a(@Field("imei") String str, @Field("codePayUuid") String str2);

    @FormUrlEncoded
    @RetainRawResponse
    @POST("api/codePay/code/doPay")
    Call<DoPayResult> a(@Field("processId") String str, @Field("authCode") String str2, @Field("payTypeId") int i, @Field("tradeId") String str3, @Field("validateType") int i2, @Field("payPass") String str4, @Field("encrypted") boolean z);

    @FormUrlEncoded
    @RetainRawResponse
    @POST("api/payment/code/context/query")
    Call<PayResult> a(@Field("authCode") String str, @Field("imei") String str2, @Field("codePayUuid") String str3);

    @FormUrlEncoded
    @POST("api/captcha/sms/send")
    Call<MipayResponse> b(@Field("processId") String str);

    @FormUrlEncoded
    @RetainRawResponse
    @POST("api/codePay/code/doPay")
    Call<DoPayResult> b(@Field("processId") String str, @Field("smsCaptcha") String str2);
}
