package com.xiaomi.ai.utils;

import android.content.Context;
import com.brentvatne.react.ReactVideoViewManager;
import com.xiaomi.ai.SendWakeupDataStatusInterface;
import com.xiaomi.ai.m;
import com.xiaomi.ai.mibrain.MibrainRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public class UploadHelper {
    private static final String d = "UploadHelper";

    /* renamed from: a  reason: collision with root package name */
    long f9947a;
    MibrainRequest b;
    AtomicBoolean c = new AtomicBoolean(false);

    public static class ASRRecordInfo {

        /* renamed from: a  reason: collision with root package name */
        String f9948a;
        String b;
        String c;
        String d;
        String e;
        String f;
        JSONObject g;
        JSONObject h;
        JSONObject i;

        public String a() {
            return this.f9948a;
        }

        public void a(String str) {
            this.f9948a = str;
        }

        public void a(JSONObject jSONObject) {
            this.g = jSONObject;
        }

        public String b() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public void b(JSONObject jSONObject) {
            this.h = jSONObject;
        }

        public String c() {
            return this.c;
        }

        public void c(String str) {
            this.c = str;
        }

        public void c(JSONObject jSONObject) {
            this.i = jSONObject;
        }

        public String d() {
            return this.d;
        }

        public void d(String str) {
            this.d = str;
        }

        public String e() {
            return this.e;
        }

        public void e(String str) {
            this.e = str;
        }

        public String f() {
            return this.f;
        }

        public void f(String str) {
            this.f = str;
        }

        public JSONObject g() {
            return this.g;
        }

        public JSONObject h() {
            return this.h;
        }

        public JSONObject i() {
            return this.i;
        }
    }

    public static class WakeupDataInfo {

        /* renamed from: a  reason: collision with root package name */
        String f9949a;
        String b;
        JSONObject c;
        JSONObject d;
        String e;
        String f;
        String g;
        String h;
        int i;
        int j;
        String k;
        JSONObject l;
        Boolean m;

        public String a() {
            return this.b;
        }

        public void a(int i2) {
            this.i = i2;
        }

        public void a(Boolean bool) {
            this.m = bool;
        }

        public void a(String str) {
            this.b = str;
        }

        public void a(JSONObject jSONObject) {
            this.c = jSONObject;
        }

        public Boolean b() {
            return this.m;
        }

        public void b(int i2) {
            this.j = i2;
        }

        public void b(String str) {
            this.f9949a = str;
        }

        public void b(JSONObject jSONObject) {
            this.d = jSONObject;
        }

        public String c() {
            return this.f9949a;
        }

        public void c(String str) {
            this.e = str;
        }

        public void c(JSONObject jSONObject) {
            this.l = jSONObject;
        }

        public JSONObject d() {
            return this.c;
        }

        public void d(String str) {
            this.f = str;
        }

        public JSONObject e() {
            return this.d;
        }

        public void e(String str) {
            this.g = str;
        }

        public String f() {
            return this.e;
        }

        public void f(String str) {
            this.h = str;
        }

        public String g() {
            return this.f;
        }

        public void g(String str) {
            this.k = str;
        }

        public String h() {
            return this.g;
        }

        public String i() {
            return this.h;
        }

        public int j() {
            return this.i;
        }

        public int k() {
            return this.j;
        }

        public String l() {
            return this.k;
        }

        public JSONObject m() {
            return this.l;
        }
    }

    public static String a() {
        return "{\"meta\": {\"type\": \"VOICE_SPLITTER\"}}";
    }

    public static String a(Context context, ASRRecordInfo aSRRecordInfo) {
        if (aSRRecordInfo == null) {
            return null;
        }
        if (aSRRecordInfo.b() == null) {
            Log.a("generateASRRecordMessage", "missing params");
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            a(jSONObject2, "ASR_RECORD", "type");
            a(jSONObject, jSONObject2, "meta");
            if (aSRRecordInfo.a() == null) {
                aSRRecordInfo.a(n.b(context));
            }
            if (aSRRecordInfo.c() == null) {
                aSRRecordInfo.c(d());
            }
            a(jSONObject3, aSRRecordInfo.a(), "device_id");
            a(jSONObject3, aSRRecordInfo.b(), "app_id");
            a(jSONObject3, aSRRecordInfo.c(), "request_id");
            a(jSONObject3, aSRRecordInfo.d(), "locale");
            a(jSONObject3, aSRRecordInfo.e(), "asr_vendor");
            a(jSONObject3, aSRRecordInfo.f(), "asr_record_words");
            a(jSONObject3, aSRRecordInfo.g(), "device");
            a(jSONObject3, aSRRecordInfo.h(), "user_info");
            a(jSONObject, jSONObject3, "request");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static String a(Context context, WakeupDataInfo wakeupDataInfo) {
        if (wakeupDataInfo == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        try {
            jSONObject2.put("type", "WAKEUP_AUDIO");
            jSONObject.put("meta", jSONObject2);
            if (wakeupDataInfo.c() == null) {
                wakeupDataInfo.b(n.b(context));
            }
            if (wakeupDataInfo.a() == null) {
                wakeupDataInfo.a(d());
            }
            a(jSONObject3, wakeupDataInfo.c(), "device_id");
            a(jSONObject3, wakeupDataInfo.a(), "request_id");
            a(jSONObject3, wakeupDataInfo.d(), "device");
            a(jSONObject3, wakeupDataInfo.e(), "user_info");
            a(jSONObject3, wakeupDataInfo.f(), "wakeup_vendor");
            a(jSONObject3, wakeupDataInfo.g(), "wakeup_word");
            a(jSONObject3, wakeupDataInfo.h(), "wakeup_type");
            a(jSONObject4, wakeupDataInfo.i(), "codec");
            a(jSONObject4, Integer.valueOf(wakeupDataInfo.j()), "channel");
            a(jSONObject4, Integer.valueOf(wakeupDataInfo.k()), ReactVideoViewManager.PROP_RATE);
            a(jSONObject3, jSONObject4, "audio_meta");
            a(jSONObject3, wakeupDataInfo.l(), "audio_info");
            a(jSONObject3, wakeupDataInfo.m(), "acoustic_info");
            a(jSONObject, jSONObject3, "request");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static String a(WakeupDataInfo wakeupDataInfo) {
        if (wakeupDataInfo == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            a(jSONObject, wakeupDataInfo.f(), "vendor");
            a(jSONObject, wakeupDataInfo.g(), "word");
            a(jSONObject, wakeupDataInfo.m(), "acoustic_info");
            a(jSONObject, wakeupDataInfo.h(), "wakeup_type");
            a(jSONObject, wakeupDataInfo.l(), "audio_info");
            a(jSONObject2, wakeupDataInfo.i(), "codec");
            a(jSONObject2, Integer.valueOf(wakeupDataInfo.j()), "channel");
            a(jSONObject2, Integer.valueOf(wakeupDataInfo.k()), ReactVideoViewManager.PROP_RATE);
            a(jSONObject, jSONObject2, "audio_meta");
            a(jSONObject, wakeupDataInfo.b(), "need_vor");
            a(jSONObject, wakeupDataInfo.m(), "acoustic_info");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    private static void a(JSONObject jSONObject, Object obj, String str) {
        if (jSONObject != null && obj != null) {
            jSONObject.put(str, obj);
        }
    }

    public static String b() {
        return "{\"meta\": {\"type\": \"VAD_BEGIN\"}}";
    }

    private static String d() {
        String str = "";
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(UUID.randomUUID().toString().getBytes());
            int length = digest.length;
            int i = 0;
            while (i < length) {
                String hexString = Integer.toHexString(digest[i] & 255);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                i++;
                str = str + hexString;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str.toLowerCase(Locale.US);
    }

    public synchronized int a(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, m mVar, int i, int i2, String str, String str2, String str3, String str4) {
        try {
        } catch (Throwable th) {
            throw th;
        }
        return a(sendWakeupDataStatusInterface, mVar, i, i2, str, str2, str3, str4, (String) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0012, code lost:
        return r4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0065  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int a(com.xiaomi.ai.SendWakeupDataStatusInterface r4, com.xiaomi.ai.m r5, int r6, int r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.util.concurrent.atomic.AtomicBoolean r0 = r3.c     // Catch:{ all -> 0x0094 }
            boolean r0 = r0.get()     // Catch:{ all -> 0x0094 }
            if (r0 == 0) goto L_0x0013
            java.lang.String r4 = "UploadHelper"
            java.lang.String r5 = "start startUploadRequest Already"
            com.xiaomi.ai.utils.Log.d(r4, r5)     // Catch:{ all -> 0x0094 }
            r4 = -2
        L_0x0011:
            monitor-exit(r3)
            return r4
        L_0x0013:
            java.util.concurrent.atomic.AtomicBoolean r0 = r3.c     // Catch:{ all -> 0x0094 }
            r1 = 1
            r2 = 0
            r0.compareAndSet(r2, r1)     // Catch:{ all -> 0x0094 }
            com.xiaomi.ai.utils.m r0 = new com.xiaomi.ai.utils.m     // Catch:{ all -> 0x0094 }
            r0.<init>(r3, r4, r5)     // Catch:{ all -> 0x0094 }
            com.xiaomi.ai.mibrain.MibrainWakeupRequestParams r4 = new com.xiaomi.ai.mibrain.MibrainWakeupRequestParams     // Catch:{ all -> 0x0094 }
            r4.<init>()     // Catch:{ all -> 0x0094 }
            r4.setApiKey(r10)     // Catch:{ all -> 0x0094 }
            r4.setAppId(r8)     // Catch:{ all -> 0x0094 }
            int r5 = com.xiaomi.ai.SpeechEngine.d     // Catch:{ all -> 0x0094 }
            r8 = 0
            if (r7 == r5) goto L_0x0040
            int r5 = com.xiaomi.ai.SpeechEngine.f     // Catch:{ all -> 0x0094 }
            if (r7 == r5) goto L_0x0040
            int r5 = com.xiaomi.ai.SpeechEngine.g     // Catch:{ all -> 0x0094 }
            if (r7 == r5) goto L_0x0040
            int r5 = com.xiaomi.ai.SpeechEngine.h     // Catch:{ all -> 0x0094 }
            if (r7 != r5) goto L_0x003c
            goto L_0x0040
        L_0x003c:
            r4.setToken(r9)     // Catch:{ all -> 0x0094 }
            goto L_0x0043
        L_0x0040:
            r4.setToken(r8)     // Catch:{ all -> 0x0094 }
        L_0x0043:
            r4.setScopeData(r11)     // Catch:{ all -> 0x0094 }
            boolean r5 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x0094 }
            if (r5 != 0) goto L_0x004f
            r4.setUserAgent(r12)     // Catch:{ all -> 0x0094 }
        L_0x004f:
            com.xiaomi.ai.mibrain.MibrainRequest r5 = new com.xiaomi.ai.mibrain.MibrainRequest     // Catch:{ all -> 0x0094 }
            r5.<init>((com.xiaomi.ai.mibrain.MibrainWakeupRequestParams) r4)     // Catch:{ all -> 0x0094 }
            r5.setRequestListener(r0)     // Catch:{ all -> 0x0094 }
            long r6 = r5.startUploadRequest(r6, r7)     // Catch:{ all -> 0x0094 }
            r3.f9947a = r6     // Catch:{ all -> 0x0094 }
            long r6 = r3.f9947a     // Catch:{ all -> 0x0094 }
            r9 = 0
            int r4 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r4 == 0) goto L_0x0072
            long r6 = r3.f9947a     // Catch:{ all -> 0x0094 }
            r9 = -1
            int r4 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r4 != 0) goto L_0x006e
            goto L_0x0072
        L_0x006e:
            r3.b = r5     // Catch:{ all -> 0x0094 }
            monitor-exit(r3)
            return r2
        L_0x0072:
            java.util.concurrent.atomic.AtomicBoolean r4 = r3.c     // Catch:{ all -> 0x0094 }
            r4.compareAndSet(r1, r2)     // Catch:{ all -> 0x0094 }
            java.lang.String r4 = "UploadHelper"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0094 }
            r5.<init>()     // Catch:{ all -> 0x0094 }
            java.lang.String r6 = "upload data new context failed "
            r5.append(r6)     // Catch:{ all -> 0x0094 }
            long r6 = r3.f9947a     // Catch:{ all -> 0x0094 }
            r5.append(r6)     // Catch:{ all -> 0x0094 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0094 }
            com.xiaomi.ai.utils.Log.d(r4, r5)     // Catch:{ all -> 0x0094 }
            r3.b = r8     // Catch:{ all -> 0x0094 }
            r4 = -1
            goto L_0x0011
        L_0x0094:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.utils.UploadHelper.a(com.xiaomi.ai.SendWakeupDataStatusInterface, com.xiaomi.ai.m, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):int");
    }

    public synchronized int a(byte[] bArr, boolean z) {
        if (this.b == null || this.f9947a == 0 || this.f9947a == -1) {
            Log.d(d, "addUploadData failed" + this.f9947a);
            return -3;
        }
        return this.b.addUploadData(this.f9947a, bArr, z ? 1 : 0);
    }

    public synchronized int c() {
        int i;
        i = -3;
        if (!(this.b == null || this.f9947a == 0 || this.f9947a == -1)) {
            i = this.b.endUploadData(this.f9947a);
            this.f9947a = 0;
            this.b = null;
            this.c.compareAndSet(true, false);
        }
        return i;
    }
}
