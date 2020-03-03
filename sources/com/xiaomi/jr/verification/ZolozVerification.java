package com.xiaomi.jr.verification;

import android.content.Context;
import android.os.Bundle;
import com.alipay.mobile.security.zim.api.ZIMResponse;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.jr.QualityMonitor;
import com.xiaomi.jr.stats.StatUtils;
import com.xiaomi.jr.verification.livenessdetection.DetectorConfig;
import com.xiaomi.jr.verification.model.ZimInfo;
import java.util.Map;

public class ZolozVerification implements IVerificationDelegate {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11045a = "ZolozVerification";
    private static final int b = 1000;
    private static final ZolozVerification c = new ZolozVerification();
    private static boolean d;

    private ZolozVerification() {
    }

    public static ZolozVerification a() {
        return c;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Context r15, com.xiaomi.jr.verification.livenessdetection.DetectorConfig r16, java.lang.String r17, java.lang.String r18) {
        /*
            r14 = this;
            r7 = r15
            r0 = r16
            int r1 = android.os.Build.VERSION.SDK_INT
            r8 = 0
            r2 = 18
            if (r1 < r2) goto L_0x00f2
            boolean r1 = d     // Catch:{ IOException -> 0x00dc }
            r9 = 1
            if (r1 != 0) goto L_0x0014
            com.alipay.mobile.security.zim.api.ZIMFacade.install(r15)     // Catch:{ IOException -> 0x00dc }
            d = r9     // Catch:{ IOException -> 0x00dc }
        L_0x0014:
            java.lang.String r1 = com.alipay.mobile.security.zim.api.ZIMFacade.getMetaInfos(r15)     // Catch:{ IOException -> 0x00dc }
            com.xiaomi.jr.verification.http.VerificationApi r2 = com.xiaomi.jr.verification.http.VerificationHttpManager.a()     // Catch:{ IOException -> 0x00dc }
            retrofit2.Call r1 = r2.a((java.lang.String) r1)     // Catch:{ IOException -> 0x00dc }
            retrofit2.Response r1 = r1.execute()     // Catch:{ IOException -> 0x00dc }
            if (r1 == 0) goto L_0x00e0
            boolean r2 = r1.isSuccessful()     // Catch:{ IOException -> 0x00dc }
            if (r2 == 0) goto L_0x00e0
            java.lang.Object r2 = r1.body()     // Catch:{ IOException -> 0x00dc }
            if (r2 == 0) goto L_0x00e0
            java.lang.Object r2 = r1.body()     // Catch:{ IOException -> 0x00dc }
            com.xiaomi.jr.http.model.MiFiResponse r2 = (com.xiaomi.jr.http.model.MiFiResponse) r2     // Catch:{ IOException -> 0x00dc }
            boolean r2 = r2.c()     // Catch:{ IOException -> 0x00dc }
            if (r2 == 0) goto L_0x00e0
            java.lang.Object r1 = r1.body()     // Catch:{ IOException -> 0x00dc }
            com.xiaomi.jr.http.model.MiFiResponse r1 = (com.xiaomi.jr.http.model.MiFiResponse) r1     // Catch:{ IOException -> 0x00dc }
            java.lang.Object r1 = r1.d()     // Catch:{ IOException -> 0x00dc }
            r4 = r1
            com.xiaomi.jr.verification.model.ZimInfo r4 = (com.xiaomi.jr.verification.model.ZimInfo) r4     // Catch:{ IOException -> 0x00dc }
            if (r4 == 0) goto L_0x00e0
            boolean r1 = r4.f11062a     // Catch:{ IOException -> 0x00dc }
            r10 = 0
            if (r1 == 0) goto L_0x0096
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00dc }
            r1.<init>()     // Catch:{ IOException -> 0x00dc }
            int r2 = com.xiaomi.jr.verification.R.string.stat_liveness_category     // Catch:{ IOException -> 0x00dc }
            java.lang.String r2 = r15.getString(r2)     // Catch:{ IOException -> 0x00dc }
            r1.append(r2)     // Catch:{ IOException -> 0x00dc }
            java.lang.String r2 = "_"
            r1.append(r2)     // Catch:{ IOException -> 0x00dc }
            int r2 = com.xiaomi.jr.verification.R.string.stat_start_liveness     // Catch:{ IOException -> 0x00dc }
            java.lang.String r2 = r15.getString(r2)     // Catch:{ IOException -> 0x00dc }
            r1.append(r2)     // Catch:{ IOException -> 0x00dc }
            java.lang.String r2 = "-"
            r1.append(r2)     // Catch:{ IOException -> 0x00dc }
            int r2 = r0.f11050a     // Catch:{ IOException -> 0x00dc }
            r1.append(r2)     // Catch:{ IOException -> 0x00dc }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x00dc }
            com.xiaomi.jr.stats.StatUtils.a((android.content.Context) r15, (java.lang.String) r10, (java.lang.String) r1, (java.util.Map<java.lang.String, java.lang.String>) r10, (android.os.Bundle) r10)     // Catch:{ IOException -> 0x00dc }
            com.alipay.mobile.security.zim.api.ZIMFacade r11 = com.alipay.mobile.security.zim.api.ZIMFacadeBuilder.create(r15)     // Catch:{ IOException -> 0x00dc }
            java.lang.String r12 = r4.b     // Catch:{ IOException -> 0x00dc }
            com.xiaomi.jr.verification.-$$Lambda$ZolozVerification$6CFZSjrM5jHX_HF9PuIe3avqBKg r13 = new com.xiaomi.jr.verification.-$$Lambda$ZolozVerification$6CFZSjrM5jHX_HF9PuIe3avqBKg     // Catch:{ IOException -> 0x00dc }
            r1 = r13
            r2 = r15
            r3 = r16
            r5 = r17
            r6 = r18
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ IOException -> 0x00dc }
            r11.verify(r12, r10, r13)     // Catch:{ IOException -> 0x00dc }
            goto L_0x00e1
        L_0x0096:
            com.xiaomi.jr.verification.LivenessManager.a((android.content.Context) r15, (com.xiaomi.jr.verification.livenessdetection.DetectorConfig) r16, (java.lang.String) r17, (java.lang.String) r18)     // Catch:{ IOException -> 0x00dc }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00dc }
            r1.<init>()     // Catch:{ IOException -> 0x00dc }
            int r2 = com.xiaomi.jr.verification.R.string.stat_liveness_category     // Catch:{ IOException -> 0x00dc }
            java.lang.String r2 = r15.getString(r2)     // Catch:{ IOException -> 0x00dc }
            r1.append(r2)     // Catch:{ IOException -> 0x00dc }
            java.lang.String r2 = "-"
            r1.append(r2)     // Catch:{ IOException -> 0x00dc }
            int r0 = r0.f11050a     // Catch:{ IOException -> 0x00dc }
            r1.append(r0)     // Catch:{ IOException -> 0x00dc }
            java.lang.String r0 = r1.toString()     // Catch:{ IOException -> 0x00dc }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00dc }
            r1.<init>()     // Catch:{ IOException -> 0x00dc }
            r1.append(r0)     // Catch:{ IOException -> 0x00dc }
            java.lang.String r0 = "_"
            r1.append(r0)     // Catch:{ IOException -> 0x00dc }
            int r0 = com.xiaomi.jr.verification.R.string.stat_callback     // Catch:{ IOException -> 0x00dc }
            java.lang.String r0 = r15.getString(r0)     // Catch:{ IOException -> 0x00dc }
            r1.append(r0)     // Catch:{ IOException -> 0x00dc }
            java.lang.String r0 = r1.toString()     // Catch:{ IOException -> 0x00dc }
            com.xiaomi.jr.stats.StatUtils.a((android.content.Context) r15, (java.lang.String) r10, (java.lang.String) r0, (java.util.Map<java.lang.String, java.lang.String>) r10, (android.os.Bundle) r10)     // Catch:{ IOException -> 0x00dc }
            java.lang.String r0 = "face_verify"
            java.lang.String r1 = "zoloz_zimid_empty"
            java.lang.String[] r2 = new java.lang.String[r8]     // Catch:{ IOException -> 0x00dc }
            com.xiaomi.jr.QualityMonitor.a((java.lang.String) r0, (java.lang.String) r1, (java.lang.String[]) r2)     // Catch:{ IOException -> 0x00dc }
            goto L_0x00e1
        L_0x00dc:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00e0:
            r9 = 0
        L_0x00e1:
            if (r9 != 0) goto L_0x0100
            int r0 = com.xiaomi.jr.verification.R.string.get_biztoken_fail
            com.xiaomi.jr.common.utils.Utils.a((android.content.Context) r15, (int) r0)
            java.lang.String r0 = "face_verify"
            java.lang.String r1 = "zoloz_get_zimid_fail"
            java.lang.String[] r2 = new java.lang.String[r8]
            com.xiaomi.jr.QualityMonitor.a((java.lang.String) r0, (java.lang.String) r1, (java.lang.String[]) r2)
            goto L_0x0100
        L_0x00f2:
            int r0 = com.xiaomi.jr.verification.R.string.android_version_too_low
            com.xiaomi.jr.common.utils.Utils.a((android.content.Context) r15, (int) r0)
            java.lang.String r0 = "face_verify"
            java.lang.String r1 = "zoloz_version_low"
            java.lang.String[] r2 = new java.lang.String[r8]
            com.xiaomi.jr.QualityMonitor.a((java.lang.String) r0, (java.lang.String) r1, (java.lang.String[]) r2)
        L_0x0100:
            com.xiaomi.jr.verification.VerificationUtils.a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.verification.ZolozVerification.a(android.content.Context, com.xiaomi.jr.verification.livenessdetection.DetectorConfig, java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean a(Context context, DetectorConfig detectorConfig, ZimInfo zimInfo, String str, String str2, ZIMResponse zIMResponse) {
        if (zIMResponse == null || zIMResponse.code != 1000) {
            a(context, detectorConfig.f11050a, zIMResponse != null ? zIMResponse.code : 0, str);
            return true;
        }
        VerificationUtils.a(context, context.getString(R.string.verifying));
        VerificationUtils.a(new Runnable(context, detectorConfig, zimInfo, str, str2) {
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ DetectorConfig f$1;
            private final /* synthetic */ ZimInfo f$2;
            private final /* synthetic */ String f$3;
            private final /* synthetic */ String f$4;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void run() {
                ZolozVerification.a(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
            }
        });
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Context context, DetectorConfig detectorConfig, ZimInfo zimInfo, String str, String str2) {
        LivenessManager.a(context, detectorConfig.f11050a, 6, (String) null, (String) null, zimInfo.b, zimInfo.c, str, str2);
        VerificationUtils.a();
    }

    private static void a(Context context, int i, int i2, String str) {
        String str2;
        int i3;
        String str3 = context.getString(R.string.stat_liveness_category) + "-" + i;
        if (i2 == 1001) {
            str2 = str3 + JSMethod.NOT_SET + context.getString(R.string.stat_liveness_system_error);
            i3 = -211;
        } else if (i2 == 2002) {
            str2 = str3 + JSMethod.NOT_SET + context.getString(R.string.stat_liveness_network);
            i3 = -222;
        } else if (i2 != 2006) {
            switch (i2) {
                case 1003:
                    str2 = str3 + JSMethod.NOT_SET + context.getString(R.string.stat_liveness_user_cancel);
                    i3 = -213;
                    break;
                case 1004:
                    str2 = str3 + JSMethod.NOT_SET + context.getString(R.string.stat_liveness_timeout_cancel);
                    i3 = -214;
                    break;
                default:
                    str2 = str3 + JSMethod.NOT_SET + R.string.stat_liveness_system_error + "-" + i2;
                    i3 = -220;
                    break;
            }
        } else {
            str2 = str3 + JSMethod.NOT_SET + context.getString(R.string.stat_liveness_failure);
            i3 = -226;
        }
        VerificationUtils.a(context, str, false, -1, i3);
        StatUtils.a(context, (String) null, str2, (Map<String, String>) null, (Bundle) null);
        QualityMonitor.a(Constants.y, "zoloz_liveness_fail", "code", String.valueOf(i2));
    }
}
