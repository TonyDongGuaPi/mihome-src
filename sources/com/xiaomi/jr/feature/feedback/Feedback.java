package com.xiaomi.jr.feature.feedback;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.ciphersuite.AESUtils;
import com.xiaomi.jr.ciphersuite.RSAUtils;
import com.xiaomi.jr.common.AccountEnvironment;
import com.xiaomi.jr.common.utils.BitmapUtils;
import com.xiaomi.jr.common.utils.FileUtils;
import com.xiaomi.jr.common.utils.MifiHostsUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.http.MifiHttpManager;
import com.xiaomi.jr.http.model.MiFiResponse;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.HybridUtils;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;
import com.xiaomi.jr.screenshot.ScreenShotListenManager;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@Feature("Feedback")
public class Feedback extends HybridFeature {
    private static final int COLLECT_LOG_SIZE = 307200;
    private static final String RSA_PUBLIC_KEY_BASE64 = (AccountEnvironment.f1407a ? "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwcrJkbcbqNjR+kHDB3hmWh6DyoNC7gH6JYHoEeUHieNI3SklOMlo12lni0IGdTkvKWgrJgJAiI/8WflCBywTtWJ+HB/206wzYrwEWnigrGFKAunVFe5bvta5eXfZFVez+BKnRVuMqfRT7puCWOC8e69cuNGYC/uLd1Xnly7zcYQIDAQAB" : "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCypyVkh9DKe4gs2uUbXRGLBVoDZOkRQlEXv0mu087JIz7ASfxKJ0ngX8tZH0QIhOZffiDr7qYO7DHL7+JYB9dB/umE8G+8zC5oRLymg5zj68FX4XJmyoecLiOn0EDJm9oEOcVlrs7s92AUQw2EI5ORHW2M4Hags5BEW2EqnyZ/zQIDAQAB");
    private static final int THUMBNAIL_SIZE = 200;
    private static final String URL_UPLOAD = (MifiHostsUtils.c("https://help.jr.mi.com/") + "api/tool/file/uploadUserLog");
    private static FeedbackApi mFeedbackApi;

    private static class UploadParam {
        @SerializedName("feedbackId")

        /* renamed from: a  reason: collision with root package name */
        long f10393a;
        @SerializedName("uploadLog")
        boolean b;

        private UploadParam() {
        }
    }

    @Action
    public Response getScreenShotThumbnail(Request request) {
        HybridUtils.a((Runnable) new Runnable() {
            public final void run() {
                Feedback.lambda$getScreenShotThumbnail$0(Request.this);
            }
        });
        return Response.j;
    }

    static /* synthetic */ void lambda$getScreenShotThumbnail$0(Request request) {
        Bitmap a2;
        Context applicationContext = HybridUtils.a(request).getApplicationContext();
        String c = ScreenShotListenManager.a(applicationContext).c();
        if (!TextUtils.isEmpty(c) && (a2 = BitmapUtils.a(applicationContext, c, 200, 200)) != null) {
            String b = BitmapUtils.b(a2);
            if (!TextUtils.isEmpty(b)) {
                HybridUtils.a(request, new Response(b));
                return;
            }
        }
        HybridUtils.a(request, new Response(200, "getScreenShotThumbnail fail, screenshot path is " + c));
    }

    @Action(paramClazz = UploadParam.class)
    public Response upload(Request<UploadParam> request) {
        HybridUtils.a((Runnable) new Runnable(request) {
            private final /* synthetic */ Request f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                Feedback.this.doUpload(this.f$1);
            }
        });
        return Response.j;
    }

    /* access modifiers changed from: private */
    public void doUpload(Request<UploadParam> request) {
        byte[] b;
        byte[] a2;
        byte[] b2;
        Utils.b();
        UploadParam c = request.c();
        Context a3 = HybridUtils.a((Request) request);
        ArrayList arrayList = new ArrayList();
        String a4 = AESUtils.a();
        String str = null;
        arrayList.add(MultipartBody.Part.createFormData("feedbackId", (String) null, RequestBody.create(MediaType.parse("text/plain"), String.valueOf(c.f10393a))));
        if (c.b && MifiLog.a(a3.getApplicationContext(), (int) COLLECT_LOG_SIZE) && (a2 = FileUtils.a(FileUtils.a(a3, "app_log/app_log"))) != null && (b2 = AESUtils.b(a2, a4)) != null) {
            arrayList.add(MultipartBody.Part.createFormData("log", "", RequestBody.create(MediaType.parse("application/octet-stream"), b2)));
        }
        String c2 = ScreenShotListenManager.a(a3.getApplicationContext()).c();
        if (!TextUtils.isEmpty(c2)) {
            ScreenShotListenManager.a(a3.getApplicationContext()).d();
            byte[] a5 = FileUtils.a(c2);
            if (!(a5 == null || (b = AESUtils.b(a5, a4)) == null)) {
                arrayList.add(MultipartBody.Part.createFormData("img", "", RequestBody.create(MediaType.parse("application/octet-stream"), b)));
            }
        }
        int i = 200;
        try {
            retrofit2.Response<MiFiResponse<Void>> execute = getFeedbackApi().a(URL_UPLOAD, RSAUtils.a(RSAUtils.c(RSA_PUBLIC_KEY_BASE64), a4), arrayList).execute();
            if (execute == null || !execute.isSuccessful()) {
                StringBuilder sb = new StringBuilder();
                sb.append("upload faied, http code = ");
                sb.append(execute != null ? Integer.valueOf(execute.code()) : "");
                str = sb.toString();
                HybridUtils.a((Request) request, new Response(i, str));
            }
            MiFiResponse body = execute.body();
            if (body.c()) {
                i = 0;
            } else {
                str = "code = " + body.a() + ", error = " + body.b();
            }
            HybridUtils.a((Request) request, new Response(i, str));
        } catch (IOException e) {
            str = e.getMessage();
        }
    }

    private static FeedbackApi getFeedbackApi() {
        if (mFeedbackApi == null) {
            mFeedbackApi = (FeedbackApi) MifiHttpManager.a().a(FeedbackApi.class);
        }
        return mFeedbackApi;
    }
}
