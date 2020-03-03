package com.xiaomi.jr.feature.identity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.common.AccountEnvironment;
import com.xiaomi.jr.common.app.ActivityChecker;
import com.xiaomi.jr.http.MifiHttpCallback;
import com.xiaomi.jr.http.MifiHttpManager;
import com.xiaomi.jr.http.model.MiFiResponse;
import com.xiaomi.jr.hybrid.HybridCallbackManager;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.HybridUtils;
import com.xiaomi.jr.hybrid.NativeInterface;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;
import com.xiaomi.jr.idcardverifier.IDCardVerifier;
import com.xiaomi.jr.idcardverifier.utils.VerifyConstants;
import com.xiaomi.jr.permission.PermissionManager;
import com.xiaomi.jr.permission.Request;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Feature("Identity")
public class Identity extends HybridFeature {
    /* access modifiers changed from: private */
    public static boolean sIdCardVerifyOngoing;

    private static class IdCardVerifyParam {
        @SerializedName("partnerId")

        /* renamed from: a  reason: collision with root package name */
        String f10399a;
        @SerializedName("needBindPartnerId")
        boolean b;
        @SerializedName("processId")
        String c;
        @SerializedName("skipDefaultSuccessPage")
        boolean d;
        @SerializedName("skipDefaultFailurePage")
        boolean e;
        @SerializedName("minLength")
        int f;
        @SerializedName("logId")
        String g;
        @SerializedName("sign")
        String h;
        @SerializedName("signTimeStamp")
        String i;

        private IdCardVerifyParam() {
        }
    }

    private static class IdCardVerifyResult {
        @SerializedName("verifyResponse")

        /* renamed from: a  reason: collision with root package name */
        public JsonObject f10400a;
        @SerializedName("verifyResult")
        public int b;

        private IdCardVerifyResult() {
            this.b = -1;
        }
    }

    private static class GetMiFiSignResult {
        @SerializedName("logId")

        /* renamed from: a  reason: collision with root package name */
        public String f10398a;
        @SerializedName("sign")
        public String b;
        @SerializedName("signTimeStamp")
        public String c;

        private GetMiFiSignResult() {
        }
    }

    @Action(paramClazz = IdCardVerifyParam.class)
    public Response idCardVerify(final Request<IdCardVerifyParam> request) {
        PermissionManager.a(HybridUtils.a((Request) request), new String[]{"android.permission.CAMERA", "android.permission.READ_PHONE_STATE", "android.permission.READ_EXTERNAL_STORAGE"}, (Request.Callback) new Request.Callback() {
            public /* synthetic */ void b() {
                Request.Callback.CC.$default$b(this);
            }

            public void a() {
                if (Identity.sIdCardVerifyOngoing) {
                    HybridUtils.a(request, new Response(200, "id card verify is ongoing"));
                } else {
                    Identity.this.performIdCardVerify(request);
                }
            }

            public void a(String str) {
                HybridUtils.a(request, (Response) new Response.PermissionDeniedResponse(str));
            }
        });
        return Response.j;
    }

    /* access modifiers changed from: private */
    public void performIdCardVerify(final com.xiaomi.jr.hybrid.Request<IdCardVerifyParam> request) {
        if (ActivityChecker.a(HybridUtils.b(request))) {
            sIdCardVerifyOngoing = true;
            IdCardVerifyParam c = request.c();
            AnonymousClass2 r1 = new NativeInterface.Callback() {
                public void a(Object... objArr) {
                    super.a(objArr);
                    int intValue = objArr[0].intValue();
                    Intent intent = objArr[1];
                    String stringExtra = intent != null ? intent.getStringExtra(VerifyConstants.n) : null;
                    IdCardVerifyResult idCardVerifyResult = new IdCardVerifyResult();
                    idCardVerifyResult.b = intValue;
                    try {
                        idCardVerifyResult.f10400a = (JsonObject) HybridUtils.a().fromJson(stringExtra, JsonObject.class);
                    } catch (JsonParseException unused) {
                    }
                    HybridUtils.a(request, new Response(idCardVerifyResult));
                    boolean unused2 = Identity.sIdCardVerifyOngoing = false;
                }
            };
            Fragment c2 = request.a().c();
            String stringExtra = c2.getActivity().getIntent().getStringExtra("from");
            String stringExtra2 = c2.getActivity().getIntent().getStringExtra("source");
            HashMap hashMap = new HashMap();
            hashMap.put("source", stringExtra2);
            new IDCardVerifier.Starter().a(c.f10399a).c(c.g).a(c.b).d(c.c).b(c.d).c(c.e).a(c.f).e(c.h).f(c.i).d(AccountEnvironment.f1407a).g(stringExtra).a((Map<String, String>) hashMap).a(c2).b(r1.a()).a((IDCardVerifier.OnStartListener) new IDCardVerifier.OnStartListener() {
                public final void onStartFinished(boolean z) {
                    Identity.lambda$performIdCardVerify$0(NativeInterface.Callback.this, z);
                }
            }).a();
        }
    }

    static /* synthetic */ void lambda$performIdCardVerify$0(NativeInterface.Callback callback, boolean z) {
        if (z) {
            HybridCallbackManager.a(callback);
        } else {
            sIdCardVerifyOngoing = false;
        }
    }

    @Action
    public Response getMiFiSignInfo(final com.xiaomi.jr.hybrid.Request request) {
        IdentityApi identityApi = (IdentityApi) MifiHttpManager.a().a(IdentityApi.class);
        final String uuid = UUID.randomUUID().toString();
        identityApi.a(AccountEnvironment.f1407a ? "900000000214" : "900000000032", uuid).enqueue(new MifiHttpCallback<MiFiResponse<MiFiSignInfo>>(HybridUtils.b(request)) {
            public void a(MiFiResponse<MiFiSignInfo> miFiResponse) {
                GetMiFiSignResult getMiFiSignResult = new GetMiFiSignResult();
                getMiFiSignResult.f10398a = uuid;
                MiFiSignInfo d = miFiResponse.d();
                if (d != null) {
                    getMiFiSignResult.b = d.f10401a;
                    getMiFiSignResult.c = d.b;
                }
                HybridUtils.a(request, new Response(getMiFiSignResult));
            }

            public void a(int i, String str, MiFiResponse<MiFiSignInfo> miFiResponse, Throwable th) {
                super.a(i, str, miFiResponse, th);
                HybridUtils.a(request, new Response(200, "getMiFiSignInfo fail"));
            }
        });
        return Response.j;
    }
}
