package com.xiaomi.aiot.mibeacon.distance;

import android.content.Context;
import com.xiaomi.aiot.mibeacon.logging.LogManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ModelSpecificDistanceCalculator implements DistanceCalculator {
    private static final String b = "model-distance-calculations.json";
    private static final String c = "ModelSpecificCalculator";

    /* renamed from: a  reason: collision with root package name */
    Map<AndroidModel, DistanceCalculator> f9977a;
    private AndroidModel d;
    private DistanceCalculator e;
    private AndroidModel f;
    private AndroidModel g;
    private String h;
    private Context i;
    private final ReentrantLock j;

    private void d() {
    }

    public ModelSpecificDistanceCalculator(Context context, String str) {
        this(context, str, AndroidModel.a());
    }

    public ModelSpecificDistanceCalculator(Context context, String str, AndroidModel androidModel) {
        this.h = null;
        this.j = new ReentrantLock();
        this.g = androidModel;
        this.h = str;
        this.i = context;
        c();
        this.e = a(androidModel);
    }

    public AndroidModel a() {
        return this.f;
    }

    public AndroidModel b() {
        return this.g;
    }

    public double a(int i2, double d2) {
        if (this.e != null) {
            return this.e.a(i2, d2);
        }
        LogManager.d(c, "distance calculator has not been set", new Object[0]);
        return -1.0d;
    }

    /* access modifiers changed from: package-private */
    public DistanceCalculator a(AndroidModel androidModel) {
        this.j.lock();
        try {
            return b(androidModel);
        } finally {
            this.j.unlock();
        }
    }

    private DistanceCalculator b(AndroidModel androidModel) {
        if (this.f9977a == null) {
            LogManager.b(c, "Cannot get distance calculator because modelMap was never initialized", new Object[0]);
            return null;
        }
        AndroidModel androidModel2 = null;
        int i2 = 0;
        for (AndroidModel next : this.f9977a.keySet()) {
            if (next.a(androidModel) > i2) {
                i2 = next.a(androidModel);
                androidModel2 = next;
            }
        }
        if (androidModel2 != null) {
            LogManager.b(c, "found a match with score %s", Integer.valueOf(i2));
            LogManager.b(c, "Finding best distance calculator for %s, %s, %s, %s", androidModel2.b(), androidModel2.c(), androidModel2.d(), androidModel2.e());
            this.f = androidModel2;
        } else {
            this.f = this.d;
            LogManager.d(c, "Cannot find match for this device.  Using default", new Object[0]);
        }
        return this.f9977a.get(this.f);
    }

    private void c() {
        e();
        this.e = a(this.g);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:7|8|9|10|11) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003c, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003d, code lost:
        com.xiaomi.aiot.mibeacon.logging.LogManager.e(r3, c, "Cannot update distance models from online database at %s with JSON: %s", r8.h, r0.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0051, code lost:
        return false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0034 */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006b A[SYNTHETIC, Splitter:B:27:0x006b] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x008a A[SYNTHETIC, Splitter:B:36:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0090 A[SYNTHETIC, Splitter:B:41:0x0090] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean e() {
        /*
            r8 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 1
            r2 = 0
            r3 = 0
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x005d }
            android.content.Context r5 = r8.i     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x005d }
            android.content.res.Resources r5 = r5.getResources()     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x005d }
            android.content.res.AssetManager r5 = r5.getAssets()     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x005d }
            java.lang.String r6 = "model-distance-calculations.json"
            java.io.InputStream r5 = r5.open(r6)     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x005d }
            r4.<init>(r5)     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x005d }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x005d }
            r5.<init>(r4)     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x005d }
        L_0x0022:
            java.lang.String r3 = r5.readLine()     // Catch:{ FileNotFoundException -> 0x0058, IOException -> 0x0055, all -> 0x0052 }
            if (r3 == 0) goto L_0x0031
            r0.append(r3)     // Catch:{ FileNotFoundException -> 0x0058, IOException -> 0x0055, all -> 0x0052 }
            java.lang.String r3 = "\n"
            r0.append(r3)     // Catch:{ FileNotFoundException -> 0x0058, IOException -> 0x0055, all -> 0x0052 }
            goto L_0x0022
        L_0x0031:
            r5.close()     // Catch:{ Exception -> 0x0034 }
        L_0x0034:
            java.lang.String r3 = r0.toString()     // Catch:{ JSONException -> 0x003c }
            r8.a((java.lang.String) r3)     // Catch:{ JSONException -> 0x003c }
            return r1
        L_0x003c:
            r3 = move-exception
            java.lang.String r4 = "ModelSpecificCalculator"
            java.lang.String r5 = "Cannot update distance models from online database at %s with JSON: %s"
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]
            java.lang.String r7 = r8.h
            r6[r2] = r7
            java.lang.String r0 = r0.toString()
            r6[r1] = r0
            com.xiaomi.aiot.mibeacon.logging.LogManager.e(r3, r4, r5, r6)
            return r2
        L_0x0052:
            r0 = move-exception
            r3 = r5
            goto L_0x008e
        L_0x0055:
            r0 = move-exception
            r3 = r5
            goto L_0x005e
        L_0x0058:
            r0 = move-exception
            r3 = r5
            goto L_0x0070
        L_0x005b:
            r0 = move-exception
            goto L_0x008e
        L_0x005d:
            r0 = move-exception
        L_0x005e:
            java.lang.String r4 = "ModelSpecificCalculator"
            java.lang.String r5 = "Cannot open distance model file"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x005b }
            r1[r2] = r0     // Catch:{ all -> 0x005b }
            com.xiaomi.aiot.mibeacon.logging.LogManager.e(r4, r5, r1)     // Catch:{ all -> 0x005b }
            if (r3 == 0) goto L_0x006e
            r3.close()     // Catch:{ Exception -> 0x006e }
        L_0x006e:
            return r2
        L_0x006f:
            r0 = move-exception
        L_0x0070:
            java.lang.String r1 = "ModelSpecificCalculator"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x005b }
            r4.<init>()     // Catch:{ all -> 0x005b }
            java.lang.String r5 = "loadModelMapFromAsstes "
            r4.append(r5)     // Catch:{ all -> 0x005b }
            r4.append(r0)     // Catch:{ all -> 0x005b }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x005b }
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ all -> 0x005b }
            com.xiaomi.aiot.mibeacon.logging.LogManager.b(r1, r0, r4)     // Catch:{ all -> 0x005b }
            if (r3 == 0) goto L_0x008d
            r3.close()     // Catch:{ Exception -> 0x008d }
        L_0x008d:
            return r2
        L_0x008e:
            if (r3 == 0) goto L_0x0093
            r3.close()     // Catch:{ Exception -> 0x0093 }
        L_0x0093:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.aiot.mibeacon.distance.ModelSpecificDistanceCalculator.e():boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0023 A[SYNTHETIC, Splitter:B:15:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0029 A[SYNTHETIC, Splitter:B:21:0x0029] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b(java.lang.String r5) {
        /*
            r4 = this;
            r0 = 0
            r1 = 0
            android.content.Context r2 = r4.i     // Catch:{ Exception -> 0x0027, all -> 0x0020 }
            java.lang.String r3 = "model-distance-calculations.json"
            java.io.FileOutputStream r2 = r2.openFileOutput(r3, r0)     // Catch:{ Exception -> 0x0027, all -> 0x0020 }
            byte[] r5 = r5.getBytes()     // Catch:{ Exception -> 0x001e, all -> 0x001b }
            r2.write(r5)     // Catch:{ Exception -> 0x001e, all -> 0x001b }
            r2.close()     // Catch:{ Exception -> 0x001e, all -> 0x001b }
            if (r2 == 0) goto L_0x0019
            r2.close()     // Catch:{ Exception -> 0x0019 }
        L_0x0019:
            r5 = 1
            return r5
        L_0x001b:
            r5 = move-exception
            r1 = r2
            goto L_0x0021
        L_0x001e:
            r1 = r2
            goto L_0x0027
        L_0x0020:
            r5 = move-exception
        L_0x0021:
            if (r1 == 0) goto L_0x0026
            r1.close()     // Catch:{ Exception -> 0x0026 }
        L_0x0026:
            throw r5
        L_0x0027:
            if (r1 == 0) goto L_0x002c
            r1.close()     // Catch:{ Exception -> 0x002c }
        L_0x002c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.aiot.mibeacon.distance.ModelSpecificDistanceCalculator.b(java.lang.String):boolean");
    }

    /* access modifiers changed from: package-private */
    public void a(String str) throws JSONException {
        this.j.lock();
        try {
            c(str);
        } finally {
            this.j.unlock();
        }
    }

    private void c(String str) throws JSONException {
        HashMap hashMap = new HashMap();
        JSONArray jSONArray = new JSONObject(str).getJSONArray("models");
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            boolean z = jSONObject.has("default") ? jSONObject.getBoolean("default") : false;
            Double valueOf = Double.valueOf(jSONObject.getDouble("coefficient1"));
            Double valueOf2 = Double.valueOf(jSONObject.getDouble("coefficient2"));
            Double valueOf3 = Double.valueOf(jSONObject.getDouble("coefficient3"));
            String string = jSONObject.getString("version");
            String string2 = jSONObject.getString("build_number");
            String string3 = jSONObject.getString("model");
            String string4 = jSONObject.getString("manufacturer");
            double doubleValue = valueOf.doubleValue();
            double doubleValue2 = valueOf2.doubleValue();
            double doubleValue3 = valueOf3.doubleValue();
            CurveFittedDistanceCalculator curveFittedDistanceCalculator = r13;
            CurveFittedDistanceCalculator curveFittedDistanceCalculator2 = new CurveFittedDistanceCalculator(doubleValue, doubleValue2, doubleValue3);
            AndroidModel androidModel = new AndroidModel(string, string2, string3, string4);
            hashMap.put(androidModel, curveFittedDistanceCalculator);
            if (z) {
                this.d = androidModel;
            }
        }
        this.f9977a = hashMap;
    }

    private void f() {
        try {
            c(d(b));
        } catch (Exception unused) {
            this.f9977a = new HashMap();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String d(java.lang.String r7) throws java.io.IOException {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.lang.Class<com.xiaomi.aiot.mibeacon.distance.ModelSpecificDistanceCalculator> r2 = com.xiaomi.aiot.mibeacon.distance.ModelSpecificDistanceCalculator.class
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0089 }
            r3.<init>()     // Catch:{ all -> 0x0089 }
            java.lang.String r4 = "/"
            r3.append(r4)     // Catch:{ all -> 0x0089 }
            r3.append(r7)     // Catch:{ all -> 0x0089 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0089 }
            java.io.InputStream r2 = r2.getResourceAsStream(r3)     // Catch:{ all -> 0x0089 }
            if (r2 != 0) goto L_0x0040
            java.lang.Class r3 = r6.getClass()     // Catch:{ all -> 0x003e }
            java.lang.ClassLoader r3 = r3.getClassLoader()     // Catch:{ all -> 0x003e }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x003e }
            r4.<init>()     // Catch:{ all -> 0x003e }
            java.lang.String r5 = "/"
            r4.append(r5)     // Catch:{ all -> 0x003e }
            r4.append(r7)     // Catch:{ all -> 0x003e }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x003e }
            java.io.InputStream r3 = r3.getResourceAsStream(r4)     // Catch:{ all -> 0x003e }
            r2 = r3
            goto L_0x0040
        L_0x003e:
            r7 = move-exception
            goto L_0x008b
        L_0x0040:
            if (r2 == 0) goto L_0x0072
            java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch:{ all -> 0x003e }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x003e }
            java.lang.String r4 = "UTF-8"
            r3.<init>(r2, r4)     // Catch:{ all -> 0x003e }
            r7.<init>(r3)     // Catch:{ all -> 0x003e }
            java.lang.String r1 = r7.readLine()     // Catch:{ all -> 0x006e }
        L_0x0052:
            if (r1 == 0) goto L_0x0061
            r0.append(r1)     // Catch:{ all -> 0x006e }
            r1 = 10
            r0.append(r1)     // Catch:{ all -> 0x006e }
            java.lang.String r1 = r7.readLine()     // Catch:{ all -> 0x006e }
            goto L_0x0052
        L_0x0061:
            r7.close()
            if (r2 == 0) goto L_0x0069
            r2.close()
        L_0x0069:
            java.lang.String r7 = r0.toString()
            return r7
        L_0x006e:
            r0 = move-exception
            r1 = r7
            r7 = r0
            goto L_0x008b
        L_0x0072:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x003e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x003e }
            r3.<init>()     // Catch:{ all -> 0x003e }
            java.lang.String r4 = "Cannot load resource at "
            r3.append(r4)     // Catch:{ all -> 0x003e }
            r3.append(r7)     // Catch:{ all -> 0x003e }
            java.lang.String r7 = r3.toString()     // Catch:{ all -> 0x003e }
            r0.<init>(r7)     // Catch:{ all -> 0x003e }
            throw r0     // Catch:{ all -> 0x003e }
        L_0x0089:
            r7 = move-exception
            r2 = r1
        L_0x008b:
            if (r1 == 0) goto L_0x0090
            r1.close()
        L_0x0090:
            if (r2 == 0) goto L_0x0095
            r2.close()
        L_0x0095:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.aiot.mibeacon.distance.ModelSpecificDistanceCalculator.d(java.lang.String):java.lang.String");
    }
}
