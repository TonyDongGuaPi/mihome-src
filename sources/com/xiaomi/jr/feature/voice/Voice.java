package com.xiaomi.jr.feature.voice;

import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.feature.voice.Voice;
import com.xiaomi.jr.feature.voice.VoiceHelper;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.HybridUtils;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;
import com.xiaomi.jr.permission.PermissionManager;
import com.xiaomi.jr.permission.Request;

@Feature("Voice")
public class Voice extends HybridFeature {
    private static final String CODE_SPEECH_PERMISSION_DENIED = "100";
    private static final String TIP_SPEECH_PERMISSION_DENIED = "speech permission denied";

    public static class RecognizeResult {
        @SerializedName("isFinal")

        /* renamed from: a  reason: collision with root package name */
        boolean f10805a;
        @SerializedName("text")
        String b;
        @SerializedName("errorCode")
        String c;
        @SerializedName("errorContent")
        String d;
    }

    @Action(paramClazz = String.class)
    public Response startRecord(final Request request) {
        PermissionManager.a(HybridUtils.a(request), "android.permission.RECORD_AUDIO", (Request.Callback) new Request.Callback() {
            public /* synthetic */ void b() {
                Request.Callback.CC.$default$b(this);
            }

            public void a() {
                HybridUtils.a((Runnable) new Runnable() {
                    public final void run() {
                        VoiceHelper.a().a(HybridUtils.a(com.xiaomi.jr.hybrid.Request.this).getApplicationContext(), (String) com.xiaomi.jr.hybrid.Request.this.c(), new VoiceHelper.RecognizeResultListener() {
                            public final void onResult(Voice.RecognizeResult recognizeResult) {
                                HybridUtils.a(com.xiaomi.jr.hybrid.Request.this, new Response(recognizeResult));
                            }
                        });
                    }
                });
            }

            public void a(String str) {
                RecognizeResult recognizeResult = new RecognizeResult();
                recognizeResult.f10805a = false;
                recognizeResult.b = "";
                recognizeResult.c = "100";
                recognizeResult.d = Voice.TIP_SPEECH_PERMISSION_DENIED;
                HybridUtils.a(request, new Response(recognizeResult));
            }
        });
        return Response.j;
    }

    @Action
    public Response stopRecord(com.xiaomi.jr.hybrid.Request request) {
        VoiceHelper.a().c();
        return Response.j;
    }

    @Action
    public Response cancelRecord(com.xiaomi.jr.hybrid.Request request) {
        VoiceHelper.a().b();
        return Response.j;
    }

    public void cleanup() {
        VoiceHelper.a().b();
    }
}
