package com.miuipub.internal.hybrid;

import android.content.Context;
import com.miuipub.internal.util.PackageConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import org.apache.commons.lang.CharUtils;

public class SecurityManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8266a = "hybrid_key.pem";
    private static String b;
    private Config c;
    private long d;
    private String e;
    private Boolean f;

    public SecurityManager(Config config, Context context) {
        this.c = config;
        if (!(config == null || config.a() == null)) {
            this.d = config.a().b();
            this.e = config.a().a();
        }
        if (b == null) {
            b = a(context);
        }
    }

    public boolean a() {
        return 0 < this.d && this.d < System.currentTimeMillis();
    }

    public boolean b() {
        if (this.f == null) {
            try {
                this.f = Boolean.valueOf(a(ConfigUtils.a(this.c), this.e));
            } catch (Exception unused) {
                this.f = false;
            }
        }
        return this.f.booleanValue();
    }

    private boolean a(String str, String str2) throws Exception {
        return str2 != null && SignUtils.a(str, SignUtils.a(b), str2);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:17|16|19|20|21) */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0042, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0052, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0053, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004e A[SYNTHETIC, Splitter:B:23:0x004e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(android.content.Context r4) {
        /*
            r3 = this;
            java.io.File r0 = new java.io.File
            java.io.File r1 = r3.b(r4)
            java.lang.String r2 = "hybrid_key.pem"
            r0.<init>(r1, r2)
            r1 = 0
            boolean r2 = r0.exists()     // Catch:{ IOException -> 0x0044 }
            if (r2 == 0) goto L_0x0018
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0044 }
            r4.<init>(r0)     // Catch:{ IOException -> 0x0044 }
            goto L_0x0026
        L_0x0018:
            android.content.res.Resources r4 = r4.getResources()     // Catch:{ IOException -> 0x0044 }
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch:{ IOException -> 0x0044 }
            java.lang.String r0 = "keys/hybrid_key.pem"
            java.io.InputStream r4 = r4.open(r0)     // Catch:{ IOException -> 0x0044 }
        L_0x0026:
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0044 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0044 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0044 }
            r0.<init>(r2)     // Catch:{ IOException -> 0x0044 }
            java.lang.String r4 = r3.a((java.io.BufferedReader) r0)     // Catch:{ IOException -> 0x0040, all -> 0x003d }
            r0.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x003c
        L_0x0038:
            r0 = move-exception
            r0.printStackTrace()
        L_0x003c:
            return r4
        L_0x003d:
            r4 = move-exception
            r1 = r0
            goto L_0x004c
        L_0x0040:
            r1 = r0
            goto L_0x0044
        L_0x0042:
            r4 = move-exception
            goto L_0x004c
        L_0x0044:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0042 }
            java.lang.String r0 = "cannot read hybrid key."
            r4.<init>(r0)     // Catch:{ all -> 0x0042 }
            throw r4     // Catch:{ all -> 0x0042 }
        L_0x004c:
            if (r1 == 0) goto L_0x0056
            r1.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0056:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miuipub.internal.hybrid.SecurityManager.a(android.content.Context):java.lang.String");
    }

    private String a(BufferedReader bufferedReader) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return sb.substring(0, sb.length() - 1);
            }
            if (!"".equals(readLine.trim()) && !readLine.startsWith("-----")) {
                sb.append(readLine);
                sb.append(CharUtils.b);
            }
        }
    }

    private File b(Context context) {
        return new File(context.getFilesDir(), PackageConstants.g);
    }
}
