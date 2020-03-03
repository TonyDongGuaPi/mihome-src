package com.xiaomi.jr.verification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.xiaomi.jr.QualityMonitor;
import com.xiaomi.jr.antifraud.Tongdun;
import com.xiaomi.jr.antifraud.por.PorData;
import com.xiaomi.jr.common.utils.Client;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.http.model.MiFiResponse;
import com.xiaomi.jr.stats.StatUtils;
import com.xiaomi.jr.verification.http.VerificationApi;
import com.xiaomi.jr.verification.http.VerificationHttpManager;
import com.xiaomi.jr.verification.livenessdetection.DetectorConfig;
import com.xiaomi.jr.verification.livenessdetection.LivenessDetectionActivity;
import com.xiaomi.jr.verification.model.VerifyResult;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MultipartBody;
import retrofit2.Response;

public class LivenessManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11042a = "LivenessManager";
    private static SparseArray<IVerificationDelegate> b = new SparseArray<>();

    public static void a(int i, IVerificationDelegate iVerificationDelegate) {
        b.put(i, iVerificationDelegate);
    }

    public static void a(Activity activity, String str, String str2) {
        String a2 = StatUtils.a(str, activity);
        Context applicationContext = activity.getApplicationContext();
        VerificationUtils.a(applicationContext, applicationContext.getString(R.string.liveness_loading));
        VerificationUtils.a(new Runnable(applicationContext, a2, str2, activity) {
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ Activity f$3;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                LivenessManager.a(this.f$0, this.f$1, this.f$2, this.f$3);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Context context, String str, String str2, Activity activity) {
        DetectorConfig a2 = a();
        boolean z = false;
        if (a2 == null) {
            VerificationUtils.a();
            a(context, new DetectorConfig(), str, str2);
            QualityMonitor.a(Constants.y, "get_config_fail", new String[0]);
        } else if (a2.f11050a == 1) {
            VerificationUtils.a();
            a(context, a2, str, str2);
        } else {
            IVerificationDelegate iVerificationDelegate = b.get(a2.f11050a);
            if (iVerificationDelegate != null) {
                iVerificationDelegate.a(context, a2, str, str2);
                z = true;
            } else {
                VerificationUtils.a();
                a(context, a2, str, str2);
                HashMap hashMap = new HashMap();
                hashMap.put(Constants.f11041a, String.valueOf(a2.f11050a));
                StatUtils.a(context, context.getString(R.string.stat_category_system_verify), "invalid_detector_id", (Map<String, String>) hashMap, activity.getIntent().getExtras());
            }
        }
        if (!z) {
            VerificationUtils.a();
        }
    }

    public static void a(Context context, DetectorConfig detectorConfig, String str, String str2) {
        detectorConfig.f11050a = 1;
        Intent intent = new Intent(context, LivenessDetectionActivity.class);
        intent.putExtra(Constants.f11041a, detectorConfig.f11050a);
        intent.putExtra(Constants.b, detectorConfig.b);
        intent.putExtra(Constants.c, str);
        intent.putExtra("extra", str2);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        context.startActivity(intent);
    }

    private static DetectorConfig a() {
        try {
            Response<MiFiResponse<DetectorConfig>> execute = VerificationHttpManager.a().a(0, 6).execute();
            if (execute == null || !execute.isSuccessful() || execute.body() == null || !execute.body().c()) {
                return null;
            }
            return (DetectorConfig) execute.body().d();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void a(Context context, int i, int i2, String str, String str2, String str3, String str4, String str5, String str6) {
        String str7;
        int i3;
        Context context2 = context;
        Location a2 = Utils.a(context);
        String b2 = Tongdun.b(context);
        int i4 = -1;
        boolean z = false;
        try {
            VerificationApi a3 = VerificationHttpManager.a();
            String e = Client.e(context);
            double d = 0.0d;
            double longitude = a2 != null ? a2.getLongitude() : 0.0d;
            if (a2 != null) {
                d = a2.getLatitude();
            }
            double d2 = d;
            if (b2 == null) {
                b2 = "";
            }
            Response<MiFiResponse<VerifyResult>> execute = a3.a(e, longitude, d2, b2, new PorData(context2).a().b().c().toString(), i, i2, str6, str, str2, str3, str4, (MultipartBody.Part) null, (String) null, (MultipartBody.Part) null).execute();
            MiFiResponse body = (execute == null || !execute.isSuccessful()) ? null : execute.body();
            if (body != null) {
                if (body.c()) {
                    z = true;
                    i4 = ((VerifyResult) body.d()).b;
                } else {
                    i3 = body.a();
                    str7 = str5;
                    VerificationUtils.a(context2, str7, z, i4, i3);
                }
            }
            i3 = -1;
            str7 = str5;
        } catch (Exception e2) {
            e2.printStackTrace();
            str7 = str5;
            i3 = -1;
        }
        VerificationUtils.a(context2, str7, z, i4, i3);
    }
}
