package com.xiaomi.smarthome.config;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Pair;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.DateUtils;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GreyUpgradeConfigManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f13939a = "home_share";
    /* access modifiers changed from: private */
    public static final String b = "GreyUpgradeConfigManager";
    private static GreyUpgradeConfigManager c = null;
    private static final int d = 5000;
    /* access modifiers changed from: private */
    public AtomicBoolean e = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public AtomicBoolean f = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public long g = 0;
    /* access modifiers changed from: private */
    public Map<String, GreyUpgradeInfo> h = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public List<Pair<String, AsyncCallback<GreyUpgradeInfo, Error>>> i = new ArrayList();

    private GreyUpgradeConfigManager() {
        g();
    }

    public static GreyUpgradeConfigManager a() {
        if (c == null) {
            synchronized (GreyUpgradeConfigManager.class) {
                if (c == null) {
                    c = new GreyUpgradeConfigManager();
                }
            }
        }
        return c;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r5, com.xiaomi.smarthome.frame.AsyncCallback<com.xiaomi.smarthome.config.GreyUpgradeConfigManager.GreyUpgradeInfo, com.xiaomi.smarthome.frame.Error> r6) {
        /*
            r4 = this;
            java.lang.String r0 = b
            java.lang.String r1 = "fetchGreyUpgradeConfig in"
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r0, (java.lang.String) r1)
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 == 0) goto L_0x0022
            if (r6 == 0) goto L_0x0021
            java.lang.String r5 = b
            java.lang.String r0 = "fetchGreyUpgradeConfig fail: key is null"
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r5, (java.lang.String) r0)
            com.xiaomi.smarthome.frame.Error r5 = new com.xiaomi.smarthome.frame.Error
            r0 = -1
            java.lang.String r1 = "key cannot be null"
            r5.<init>(r0, r1)
            r6.onFailure(r5)
        L_0x0021:
            return
        L_0x0022:
            java.util.List<android.util.Pair<java.lang.String, com.xiaomi.smarthome.frame.AsyncCallback<com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo, com.xiaomi.smarthome.frame.Error>>> r0 = r4.i
            monitor-enter(r0)
            java.util.concurrent.atomic.AtomicBoolean r1 = r4.f     // Catch:{ all -> 0x008c }
            boolean r1 = r1.get()     // Catch:{ all -> 0x008c }
            if (r1 == 0) goto L_0x006e
            java.util.Map<java.lang.String, com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo> r1 = r4.h     // Catch:{ all -> 0x008c }
            java.lang.Object r5 = r1.get(r5)     // Catch:{ all -> 0x008c }
            com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo r5 = (com.xiaomi.smarthome.config.GreyUpgradeConfigManager.GreyUpgradeInfo) r5     // Catch:{ all -> 0x008c }
            if (r6 == 0) goto L_0x006c
            java.lang.String r1 = b     // Catch:{ all -> 0x008c }
            java.lang.String r2 = "fetchGreyUpgradeConfig success: from cache"
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r1, (java.lang.String) r2)     // Catch:{ all -> 0x008c }
            boolean r1 = com.xiaomi.smarthome.globalsetting.GlobalSetting.q     // Catch:{ all -> 0x008c }
            if (r1 == 0) goto L_0x0069
            java.lang.String r1 = b     // Catch:{ JSONException -> 0x0065 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0065 }
            r2.<init>()     // Catch:{ JSONException -> 0x0065 }
            java.lang.String r3 = "fetchGreyUpgradeConfig callback.onSuccess "
            r2.append(r3)     // Catch:{ JSONException -> 0x0065 }
            if (r5 != 0) goto L_0x0052
            r3 = 0
            goto L_0x005a
        L_0x0052:
            org.json.JSONObject r3 = r5.b()     // Catch:{ JSONException -> 0x0065 }
            java.lang.String r3 = r3.toString()     // Catch:{ JSONException -> 0x0065 }
        L_0x005a:
            r2.append(r3)     // Catch:{ JSONException -> 0x0065 }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x0065 }
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r1, (java.lang.String) r2)     // Catch:{ JSONException -> 0x0065 }
            goto L_0x0069
        L_0x0065:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x008c }
        L_0x0069:
            r6.onSuccess(r5)     // Catch:{ all -> 0x008c }
        L_0x006c:
            monitor-exit(r0)     // Catch:{ all -> 0x008c }
            return
        L_0x006e:
            java.lang.String r1 = b     // Catch:{ all -> 0x008c }
            java.lang.String r2 = "fetchGreyUpgradeConfig add callback to pending list"
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r1, (java.lang.String) r2)     // Catch:{ all -> 0x008c }
            java.util.List<android.util.Pair<java.lang.String, com.xiaomi.smarthome.frame.AsyncCallback<com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo, com.xiaomi.smarthome.frame.Error>>> r1 = r4.i     // Catch:{ all -> 0x008c }
            android.util.Pair r2 = new android.util.Pair     // Catch:{ all -> 0x008c }
            r2.<init>(r5, r6)     // Catch:{ all -> 0x008c }
            r1.add(r2)     // Catch:{ all -> 0x008c }
            java.util.concurrent.atomic.AtomicBoolean r5 = r4.e     // Catch:{ all -> 0x008c }
            boolean r5 = r5.get()     // Catch:{ all -> 0x008c }
            if (r5 != 0) goto L_0x008a
            r4.b()     // Catch:{ all -> 0x008c }
        L_0x008a:
            monitor-exit(r0)     // Catch:{ all -> 0x008c }
            return
        L_0x008c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.config.GreyUpgradeConfigManager.a(java.lang.String, com.xiaomi.smarthome.frame.AsyncCallback):void");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
            r6 = this;
            java.lang.String r0 = b
            java.lang.String r1 = "updateGreyUpgradeConfig in"
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r0, (java.lang.String) r1)
            long r0 = java.lang.System.currentTimeMillis()
            long r2 = r6.g
            long r0 = r0 - r2
            long r0 = java.lang.Math.abs(r0)
            r2 = 5000(0x1388, double:2.4703E-320)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x0019
            return
        L_0x0019:
            java.util.concurrent.atomic.AtomicBoolean r0 = r6.e
            r1 = 1
            boolean r0 = r0.getAndSet(r1)
            if (r0 == 0) goto L_0x0023
            return
        L_0x0023:
            java.lang.String r0 = b
            java.lang.String r2 = "updateGreyUpgradeConfig will issue request"
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r0, (java.lang.String) r2)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            java.lang.String r3 = "lang"
            java.lang.String r4 = "en"
            r2.put(r3, r4)     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = "name"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x008f }
            r4.<init>()     // Catch:{ Exception -> 0x008f }
            java.lang.String r5 = "android_appgreyupgrade"
            r4.append(r5)     // Catch:{ Exception -> 0x008f }
            boolean r5 = com.xiaomi.smarthome.globalsetting.GlobalSetting.E     // Catch:{ Exception -> 0x008f }
            if (r5 == 0) goto L_0x004e
            java.lang.String r5 = "_preview"
            goto L_0x0050
        L_0x004e:
            java.lang.String r5 = ""
        L_0x0050:
            r4.append(r5)     // Catch:{ Exception -> 0x008f }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x008f }
            r2.put(r3, r4)     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = "version"
            java.lang.String r4 = "1"
            r2.put(r3, r4)     // Catch:{ Exception -> 0x008f }
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r3 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair     // Catch:{ Exception -> 0x008f }
            java.lang.String r4 = "data"
            java.lang.String r5 = r2.toString()     // Catch:{ Exception -> 0x008f }
            r3.<init>(r4, r5)     // Catch:{ Exception -> 0x008f }
            r0.add(r3)     // Catch:{ Exception -> 0x008f }
            com.xiaomi.smarthome.library.http.Request$Builder r0 = new com.xiaomi.smarthome.library.http.Request$Builder     // Catch:{ Exception -> 0x008f }
            r0.<init>()     // Catch:{ Exception -> 0x008f }
            java.lang.String r3 = "GET"
            com.xiaomi.smarthome.library.http.Request$Builder r0 = r0.a((java.lang.String) r3)     // Catch:{ Exception -> 0x008f }
            java.lang.String r2 = r6.a((org.json.JSONObject) r2)     // Catch:{ Exception -> 0x008f }
            com.xiaomi.smarthome.library.http.Request$Builder r0 = r0.b((java.lang.String) r2)     // Catch:{ Exception -> 0x008f }
            com.xiaomi.smarthome.library.http.Request r0 = r0.a()     // Catch:{ Exception -> 0x008f }
            com.xiaomi.smarthome.config.GreyUpgradeConfigManager$1 r2 = new com.xiaomi.smarthome.config.GreyUpgradeConfigManager$1     // Catch:{ Exception -> 0x008f }
            r2.<init>()     // Catch:{ Exception -> 0x008f }
            com.xiaomi.smarthome.library.http.HttpApi.a((com.xiaomi.smarthome.library.http.Request) r0, (com.xiaomi.smarthome.library.http.async.AsyncHandler) r2)     // Catch:{ Exception -> 0x008f }
            goto L_0x009e
        L_0x008f:
            java.util.List<android.util.Pair<java.lang.String, com.xiaomi.smarthome.frame.AsyncCallback<com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo, com.xiaomi.smarthome.frame.Error>>> r0 = r6.i
            monitor-enter(r0)
            java.util.concurrent.atomic.AtomicBoolean r2 = r6.f     // Catch:{ all -> 0x009f }
            r2.set(r1)     // Catch:{ all -> 0x009f }
            java.util.concurrent.atomic.AtomicBoolean r1 = r6.e     // Catch:{ all -> 0x009f }
            r2 = 0
            r1.set(r2)     // Catch:{ all -> 0x009f }
            monitor-exit(r0)     // Catch:{ all -> 0x009f }
        L_0x009e:
            return
        L_0x009f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x009f }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.config.GreyUpgradeConfigManager.b():void");
    }

    /* access modifiers changed from: private */
    public void d() {
        try {
            a(e(), f());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean a(String str, String str2) {
        try {
            File file = new File(str2);
            if (file.exists()) {
                file.delete();
            } else {
                file.mkdirs();
                file.createNewFile();
            }
            new PrintWriter(file).write(str);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    private void b(String str) throws JSONException {
        JSONArray optJSONArray = new JSONObject(str).optJSONArray("data");
        if (optJSONArray != null && optJSONArray.length() != 0) {
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                GreyUpgradeInfo a2 = GreyUpgradeInfo.a(optJSONArray.optJSONObject(i2));
                if (a2 != null && !TextUtils.isEmpty(a2.c())) {
                    concurrentHashMap.put(a2.c(), a2);
                }
            }
            this.h = concurrentHashMap;
            this.f.set(true);
        }
    }

    private String e() throws JSONException {
        try {
            HashSet<GreyUpgradeInfo> hashSet = new HashSet<>(this.h.values());
            JSONArray jSONArray = new JSONArray();
            for (GreyUpgradeInfo greyUpgradeInfo : hashSet) {
                if (greyUpgradeInfo != null) {
                    jSONArray.put(greyUpgradeInfo.b());
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("data", jSONArray);
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private String f() {
        ServerBean a2 = ServerCompact.a(SHApplication.getAppContext());
        StringBuilder sb = new StringBuilder();
        sb.append(SHApplication.getAppContext().getFilesDir());
        sb.append(File.separator);
        sb.append("app_grey_upgrade_config_");
        sb.append(a2 == null ? "" : a2.f1530a);
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003a, code lost:
        r1.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002a A[ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0012] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r4) throws java.io.IOException {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            boolean r4 = r0.exists()
            r1 = 0
            if (r4 != 0) goto L_0x000d
            return r1
        L_0x000d:
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Exception -> 0x0033 }
            r4.<init>(r0)     // Catch:{ Exception -> 0x0033 }
            long r2 = r0.length()     // Catch:{ Exception -> 0x002c, all -> 0x002a }
            int r0 = (int) r2     // Catch:{ Exception -> 0x002c, all -> 0x002a }
            char[] r0 = new char[r0]     // Catch:{ Exception -> 0x002c, all -> 0x002a }
            r4.read(r0)     // Catch:{ Exception -> 0x002c, all -> 0x002a }
            java.lang.String r2 = new java.lang.String     // Catch:{ Exception -> 0x002c, all -> 0x002a }
            r2.<init>(r0)     // Catch:{ Exception -> 0x002c, all -> 0x002a }
            r4.close()     // Catch:{ Exception -> 0x0028, all -> 0x002a }
            r4.close()
            goto L_0x003d
        L_0x0028:
            r0 = move-exception
            goto L_0x002e
        L_0x002a:
            r0 = move-exception
            goto L_0x003e
        L_0x002c:
            r0 = move-exception
            r2 = r1
        L_0x002e:
            r1 = r4
            goto L_0x0035
        L_0x0030:
            r0 = move-exception
            r4 = r1
            goto L_0x003e
        L_0x0033:
            r0 = move-exception
            r2 = r1
        L_0x0035:
            r0.printStackTrace()     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x003d
            r1.close()
        L_0x003d:
            return r2
        L_0x003e:
            if (r4 == 0) goto L_0x0043
            r4.close()
        L_0x0043:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.config.GreyUpgradeConfigManager.a(java.lang.String):java.lang.String");
    }

    private void g() {
        try {
            String a2 = a(f());
            if (!TextUtils.isEmpty(a2)) {
                b(a2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @NonNull
    private String a(JSONObject jSONObject) throws UnsupportedEncodingException {
        return ServerRouteUtil.a(SHApplication.getAppContext()) + "/app/service/getappconfig?data=" + URLEncoder.encode(jSONObject.toString(), "UTF-8");
    }

    public boolean a(GreyUpgradeInfo greyUpgradeInfo) {
        if (greyUpgradeInfo == null || TextUtils.isEmpty(greyUpgradeInfo.e())) {
            return false;
        }
        long d2 = greyUpgradeInfo.d();
        if (d2 == 0) {
            return false;
        }
        int i2 = GlobalSetting.n;
        if (i2 == 0) {
            try {
                i2 = SHApplication.getApplication().getPackageManager().getPackageInfo(SHApplication.getApplication().getPackageName(), 0).versionCode;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (((long) i2) >= d2) {
            return false;
        }
        return true;
    }

    public static class GreyUpgradeInfo {

        /* renamed from: a  reason: collision with root package name */
        private String f13941a;
        private long b;
        private String c;
        private long d;

        public static GreyUpgradeInfo a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            String optString = jSONObject.optString("key");
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            GreyUpgradeInfo greyUpgradeInfo = new GreyUpgradeInfo();
            greyUpgradeInfo.a(optString);
            greyUpgradeInfo.b(jSONObject.optLong("timestamp", System.currentTimeMillis()));
            JSONObject optJSONObject = jSONObject.optJSONObject("value");
            greyUpgradeInfo.a((long) optJSONObject.optInt("min_app_v"));
            greyUpgradeInfo.b(optJSONObject.optString("download_url"));
            return greyUpgradeInfo;
        }

        public boolean a() {
            return System.currentTimeMillis() - this.d > DateUtils.d;
        }

        public JSONObject b() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("min_app_v", this.b);
            jSONObject2.put("download_url", this.c);
            jSONObject.put("value", jSONObject2);
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put("key", this.f13941a);
            return jSONObject;
        }

        public String c() {
            return this.f13941a;
        }

        public void a(String str) {
            this.f13941a = str;
        }

        public long d() {
            return this.b;
        }

        public void a(long j) {
            this.b = j;
        }

        public String e() {
            return this.c;
        }

        public void b(String str) {
            this.c = str;
        }

        public long f() {
            return this.d;
        }

        public void b(long j) {
            this.d = j;
        }
    }
}
