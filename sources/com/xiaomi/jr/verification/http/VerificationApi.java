package com.xiaomi.jr.verification.http;

import com.xiaomi.jr.http.RetainRawResponse;
import com.xiaomi.jr.http.model.MiFiResponse;
import com.xiaomi.jr.verification.livenessdetection.DetectorConfig;
import com.xiaomi.jr.verification.model.VerifyResult;
import com.xiaomi.jr.verification.model.ZimInfo;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface VerificationApi {
    @GET("v1/user/cert/timesLeft")
    Call<MiFiResponse<Integer>> a();

    @RetainRawResponse
    @GET("v1/user/cert/system/bizToken")
    Call<MiFiResponse<String>> a(@Query("provider") int i);

    @GET("v1/appConfig/livenessDetector/v2")
    Call<MiFiResponse<DetectorConfig>> a(@Query("timesLeft") int i, @Query("provider") int i2);

    @FormUrlEncoded
    @POST("v1/user/cert/system/zimId")
    Call<MiFiResponse<ZimInfo>> a(@Field("metaInfo") String str);

    @POST("v1/user/cert/system/complete/v2")
    @Multipart
    Call<MiFiResponse<VerifyResult>> a(@Part("imei") String str, @Part("longitude") double d, @Part("latitude") double d2, @Part("fblackbox") String str2, @Part("por") String str3, @Part("provider") int i, @Part("sdkVersion") int i2, @Part("extra") String str4, @Part("bizToken") String str5, @Part("data") String str6, @Part("zimId") String str7, @Part("zolozBizId") String str8, @Part MultipartBody.Part part, @Part("delta") String str9, @Part MultipartBody.Part part2);

    @POST("v1/user/cert/manual/v2")
    @Multipart
    Call<MiFiResponse<Integer>> a(@Part("imei") String str, @Part("longitude") double d, @Part("latitude") double d2, @Part("por") String str2, @Part MultipartBody.Part part, @Part("extra") String str3);

    @POST("v1/user/cert/preUpload")
    Call<MiFiResponse<Void>> a(@Field("data") String str, @Field("imei") String str2, @Field("por") String str3, @Field("fblackbox") String str4);
}
