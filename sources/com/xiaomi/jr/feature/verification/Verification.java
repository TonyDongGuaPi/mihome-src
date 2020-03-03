package com.xiaomi.jr.feature.verification;

import android.app.Activity;
import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.common.app.ActivityChecker;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.HybridUtils;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;
import com.xiaomi.jr.permission.PermissionManager;
import com.xiaomi.jr.permission.Request;
import com.xiaomi.jr.verification.LivenessManager;

@Feature("Verification")
public class Verification extends HybridFeature {

    private static class FaceVerifyParam {
        @SerializedName("resultUrl")

        /* renamed from: a  reason: collision with root package name */
        String f10802a;
        @SerializedName("extra")
        String b;

        private FaceVerifyParam() {
        }
    }

    @Action(paramClazz = FaceVerifyParam.class)
    public Response faceVerify(final Request<FaceVerifyParam> request) {
        PermissionManager.a(HybridUtils.a((Request) request), new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"}, (Request.Callback) new Request.Callback() {
            public /* synthetic */ void b() {
                Request.Callback.CC.$default$b(this);
            }

            public void a() {
                HybridUtils.a((Runnable) new Runnable() {
                    public void run() {
                        Activity b = HybridUtils.b(request);
                        if (ActivityChecker.a(b)) {
                            LivenessManager.a(b, ((FaceVerifyParam) request.c()).f10802a, ((FaceVerifyParam) request.c()).b);
                        }
                    }
                });
            }

            public void a(String str) {
                HybridUtils.a(request, (Response) new Response.PermissionDeniedResponse(str));
            }
        });
        return Response.j;
    }
}
