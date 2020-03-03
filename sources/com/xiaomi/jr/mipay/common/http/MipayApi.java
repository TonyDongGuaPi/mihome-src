package com.xiaomi.jr.mipay.common.http;

import com.xiaomi.jr.mipay.common.model.DeviceIdInfo;
import com.xiaomi.jr.mipay.common.model.ProcessInfo;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MipayApi {
    @FormUrlEncoded
    @POST("api/process/start")
    Call<ProcessInfo> a(@Field("processType") String str);

    @FormUrlEncoded
    @POST("api/device")
    Call<DeviceIdInfo> a(@FieldMap Map<String, String> map);
}
