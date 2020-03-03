package com.xiaomi.jr.feature.feedback;

import com.xiaomi.jr.http.model.MiFiResponse;
import java.util.List;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface FeedbackApi {
    @POST
    @Multipart
    Call<MiFiResponse<Void>> a(@Url String str, @Header("X-Mifi-Token") String str2, @Part List<MultipartBody.Part> list);
}
