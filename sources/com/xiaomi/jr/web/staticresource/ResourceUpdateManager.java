package com.xiaomi.jr.web.staticresource;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.common.utils.FileUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.PreferenceUtils;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.http.model.MiFiResponse;
import com.xiaomi.jr.stats.StatUtils;
import com.xiaomi.jr.web.WebManager;
import com.xiaomi.jr.web.staticresource.UpdateInfo;
import com.xiaomi.market.sdk.Patcher;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.cybergarage.http.HTTP;

public class ResourceUpdateManager {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f1457a = false;
    static final String b = "static_resource";
    static final String c = "static_resource_last_modified_";
    private static final String d = "StaticResourceUpdateManager";
    private static final String e = "buildin_static_resource.zip";
    private static final String f = "static_resource.zip";
    private static final String g = "static_resource_unzip";
    private static final String h = "update.zip";
    private static final String i = "meta";
    private static final String j = "static_resource_timestamp";
    private static final int k = 1048576;
    /* access modifiers changed from: private */
    public static boolean l;

    private enum UpdateType {
        DELTA(1),
        FULL(2),
        ONLINE(3);
        
        private int value;

        private UpdateType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    private static class Meta {
        @SerializedName("add")

        /* renamed from: a  reason: collision with root package name */
        public Map<String, String> f1459a;
        @SerializedName("delete")
        public Set<String> b;
        @SerializedName("modify")
        public Set<String> c;

        private Meta() {
        }
    }

    private static class SyncResult {

        /* renamed from: a  reason: collision with root package name */
        boolean f1460a;
        long b;
        long c;

        private SyncResult() {
        }
    }

    /* access modifiers changed from: private */
    public static UpdateInfo c(Context context, String str, String str2) {
        String str3;
        UpdateInfo updateInfo = null;
        try {
            long b2 = PreferenceUtils.b(context, ResourceConstants.f11078a, j, 0);
            if (str == null || str2 == null) {
                str3 = str != null ? str : str2 != null ? str2 : null;
            } else {
                str3 = str + "," + str2;
            }
            MiFiResponse body = WebManager.a().a(b2, str3).execute().body();
            if (body != null && body.c()) {
                UpdateInfo updateInfo2 = (UpdateInfo) body.d();
                if (updateInfo2 == null) {
                    return updateInfo2;
                }
                try {
                    if (updateInfo2.d == null) {
                        return updateInfo2;
                    }
                    if (TextUtils.equals(str, updateInfo2.d.d)) {
                        updateInfo2.d.f = UpdateInfo.Info.BaseType.LAST;
                        return updateInfo2;
                    } else if (!TextUtils.equals(str2, updateInfo2.d.d)) {
                        return updateInfo2;
                    } else {
                        updateInfo2.d.f = UpdateInfo.Info.BaseType.BUILDIN;
                        return updateInfo2;
                    }
                } catch (IOException e2) {
                    updateInfo = updateInfo2;
                    e = e2;
                    MifiLog.d(d, "getStaticResourceUpdateInfo fail, " + e.getMessage());
                    return updateInfo;
                }
            }
        } catch (IOException e3) {
            e = e3;
            MifiLog.d(d, "getStaticResourceUpdateInfo fail, " + e.getMessage());
            return updateInfo;
        }
        return updateInfo;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00d6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.jr.web.staticresource.ResourceUpdateManager.SyncResult b(android.content.Context r13, com.xiaomi.jr.web.staticresource.UpdateInfo r14) {
        /*
            com.xiaomi.jr.web.staticresource.ResourceUpdateManager$SyncResult r0 = new com.xiaomi.jr.web.staticresource.ResourceUpdateManager$SyncResult
            r1 = 0
            r0.<init>()
            if (r14 == 0) goto L_0x01a3
            boolean r2 = r14.f1462a
            if (r2 == 0) goto L_0x01a3
            int r2 = r14.b
            com.xiaomi.jr.web.staticresource.ResourceUpdateManager$UpdateType r3 = com.xiaomi.jr.web.staticresource.ResourceUpdateManager.UpdateType.ONLINE
            int r3 = r3.getValue()
            if (r2 != r3) goto L_0x0018
            goto L_0x01a3
        L_0x0018:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = com.xiaomi.jr.common.utils.FileStorageUtils.a()
            r2.append(r3)
            com.xiaomi.jr.web.staticresource.UpdateInfo$Info r3 = r14.d
            java.lang.String r3 = r3.b
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.io.File r3 = r13.getFilesDir()     // Catch:{ IOException -> 0x0039 }
            java.lang.String r3 = r3.getCanonicalPath()     // Catch:{ IOException -> 0x0039 }
            r1 = r3
            goto L_0x003d
        L_0x0039:
            r3 = move-exception
            r3.printStackTrace()
        L_0x003d:
            if (r1 != 0) goto L_0x0040
            return r0
        L_0x0040:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            java.lang.String r4 = java.io.File.separator
            r3.append(r4)
            java.lang.String r4 = "update.zip"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            long r4 = java.lang.System.currentTimeMillis()
            com.xiaomi.jr.web.staticresource.Fetcher r6 = new com.xiaomi.jr.web.staticresource.Fetcher
            r6.<init>()
            long r7 = r6.a((java.lang.String) r2)
            java.lang.String r9 = "StaticResourceUpdateManager"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "check update size: "
            r10.append(r11)
            r10.append(r7)
            java.lang.String r11 = ", url: "
            r10.append(r11)
            r10.append(r2)
            java.lang.String r10 = r10.toString()
            com.xiaomi.jr.common.utils.MifiLog.b(r9, r10)
            r9 = 0
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            r9 = 0
            if (r11 <= 0) goto L_0x00ac
            boolean r10 = com.xiaomi.jr.common.utils.NetworkUtils.e(r13)
            r11 = 1048576(0x100000, double:5.180654E-318)
            if (r10 != 0) goto L_0x009e
            int r10 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r10 <= 0) goto L_0x009e
            java.lang.String r2 = "StaticResourceUpdateManager"
            java.lang.String r6 = "don't fetch updates > 1M on data connection mode."
            com.xiaomi.jr.common.utils.MifiLog.b(r2, r6)
            goto L_0x00ac
        L_0x009e:
            int r10 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r10 <= 0) goto L_0x00a7
            boolean r2 = r6.a(r13, r2, r3)
            goto L_0x00ad
        L_0x00a7:
            boolean r2 = r6.a(r2, r3)
            goto L_0x00ad
        L_0x00ac:
            r2 = 0
        L_0x00ad:
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r4
            r0.c = r6
            java.lang.String r4 = "TestTime"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "fetch file takes: "
            r5.append(r6)
            long r6 = r0.c
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.xiaomi.jr.common.utils.MifiLog.b(r4, r5)
            if (r2 != 0) goto L_0x00d6
            java.lang.String r13 = "StaticResourceUpdateManager"
            java.lang.String r14 = "fetch updates fail."
            com.xiaomi.jr.common.utils.MifiLog.b(r13, r14)
            return r0
        L_0x00d6:
            java.lang.String r2 = com.xiaomi.jr.common.utils.FileUtils.f(r3)
            com.xiaomi.jr.web.staticresource.UpdateInfo$Info r4 = r14.d
            java.lang.String r4 = r4.c
            boolean r2 = android.text.TextUtils.equals(r2, r4)
            if (r2 != 0) goto L_0x00ec
            java.lang.String r13 = "StaticResourceUpdateManager"
            java.lang.String r14 = "fetch updates not complete (invalid md5)."
            com.xiaomi.jr.common.utils.MifiLog.b(r13, r14)
            return r0
        L_0x00ec:
            java.io.File r2 = new java.io.File
            r2.<init>(r3)
            long r4 = r2.length()
            r0.b = r4
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            java.lang.String r4 = java.io.File.separator
            r2.append(r4)
            java.lang.String r4 = "static_resource.zip"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            int r4 = r14.b
            com.xiaomi.jr.web.staticresource.ResourceUpdateManager$UpdateType r5 = com.xiaomi.jr.web.staticresource.ResourceUpdateManager.UpdateType.DELTA
            int r5 = r5.getValue()
            r6 = 1
            if (r4 != r5) goto L_0x016f
            com.xiaomi.jr.web.staticresource.UpdateInfo$Info r4 = r14.d
            com.xiaomi.jr.web.staticresource.UpdateInfo$Info$BaseType r4 = r4.f
            com.xiaomi.jr.web.staticresource.UpdateInfo$Info$BaseType r5 = com.xiaomi.jr.web.staticresource.UpdateInfo.Info.BaseType.LAST
            if (r4 != r5) goto L_0x0123
            r1 = r2
            goto L_0x0139
        L_0x0123:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r1)
            java.lang.String r1 = java.io.File.separator
            r4.append(r1)
            java.lang.String r1 = "buildin_static_resource.zip"
            r4.append(r1)
            java.lang.String r1 = r4.toString()
        L_0x0139:
            boolean r4 = d(r13, r1, r3)
            if (r4 == 0) goto L_0x014e
            java.lang.String r2 = com.xiaomi.jr.common.utils.FileUtils.f(r2)
            com.xiaomi.jr.web.staticresource.UpdateInfo$Info r4 = r14.d
            java.lang.String r4 = r4.e
            boolean r2 = android.text.TextUtils.equals(r2, r4)
            if (r2 == 0) goto L_0x014e
            r9 = 1
        L_0x014e:
            java.io.File r2 = new java.io.File
            r2.<init>(r3)
            com.xiaomi.jr.common.utils.FileUtils.a((java.io.File) r2)
            if (r9 != 0) goto L_0x0187
            java.lang.String r13 = "StaticResourceUpdateManager"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r2 = "patch delta fail, check old static resource zip "
            r14.append(r2)
            r14.append(r1)
            java.lang.String r14 = r14.toString()
            com.xiaomi.jr.common.utils.MifiLog.b(r13, r14)
            return r0
        L_0x016f:
            java.io.File r1 = new java.io.File
            r1.<init>(r3)
            java.io.File r3 = new java.io.File
            r3.<init>(r2)
            boolean r1 = r1.renameTo(r3)
            if (r1 != 0) goto L_0x0187
            java.lang.String r13 = "StaticResourceUpdateManager"
            java.lang.String r14 = "override full updates fail."
            com.xiaomi.jr.common.utils.MifiLog.b(r13, r14)
            return r0
        L_0x0187:
            java.lang.String r1 = "static_resource.zip"
            boolean r13 = b((android.content.Context) r13, (java.lang.String) r1, (com.xiaomi.jr.web.staticresource.UpdateInfo) r14)
            if (r13 != 0) goto L_0x0198
            java.lang.String r13 = "StaticResourceUpdateManager"
            java.lang.String r14 = "apply static resource fail"
            com.xiaomi.jr.common.utils.MifiLog.b(r13, r14)
            return r0
        L_0x0198:
            java.lang.String r13 = "StaticResourceUpdateManager"
            java.lang.String r14 = "sync updates successfully!"
            com.xiaomi.jr.common.utils.MifiLog.b(r13, r14)
            r0.f1460a = r6
            return r0
        L_0x01a3:
            java.lang.String r13 = "StaticResourceUpdateManager"
            java.lang.String r14 = "no updates."
            com.xiaomi.jr.common.utils.MifiLog.b(r13, r14)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.web.staticresource.ResourceUpdateManager.b(android.content.Context, com.xiaomi.jr.web.staticresource.UpdateInfo):com.xiaomi.jr.web.staticresource.ResourceUpdateManager$SyncResult");
    }

    private static boolean d(Context context, String str, String str2) {
        String str3;
        try {
            str3 = context.getFilesDir().getCanonicalPath();
        } catch (IOException e2) {
            e2.printStackTrace();
            str3 = null;
        }
        if (str3 == null) {
            return false;
        }
        String str4 = str3 + File.separator + g;
        FileUtils.a(new File(str4));
        if (!ZipUtils.a(new File(str), new File(str4))) {
            a(str4);
            return false;
        }
        String str5 = str2 + "_FILES";
        FileUtils.a(new File(str5));
        if (!ZipUtils.a(new File(str2), new File(str5))) {
            a(str4, str5);
            return false;
        }
        Meta meta = (Meta) new Gson().fromJson(FileUtils.c(str5 + File.separator + "meta"), Meta.class);
        if (meta.f1459a != null) {
            for (String next : meta.f1459a.keySet()) {
                String str6 = meta.f1459a.get(next);
                new File(str4 + File.separator + str6).getParentFile().mkdirs();
                if (!new File(str5 + File.separator + next).renameTo(new File(str4 + File.separator + str6))) {
                    MifiLog.b(d, "apply adding file " + str6 + " fail.");
                    a(str4, str5);
                    return false;
                }
            }
        }
        if (meta.b != null) {
            for (String str7 : meta.b) {
                FileUtils.a(new File(str4 + File.separator + str7));
            }
        }
        if (meta.c != null) {
            for (String split : meta.c) {
                String[] split2 = split.split(HTTP.TAB);
                String str8 = split2[0];
                String str9 = TextUtils.isEmpty(split2[1]) ? str8 : split2[1];
                String str10 = split2.length > 2 ? split2[2] : null;
                if (TextUtils.isEmpty(str10)) {
                    if (!new File(str4 + File.separator + str8).renameTo(new File(str4 + File.separator + str9))) {
                        MifiLog.b(d, "apply modify fail: rename old=" + str8 + ", new=" + str9);
                        a(str4, str5);
                        return false;
                    }
                } else {
                    if (!a(str4 + File.separator + str8, str4 + File.separator + str9, str5 + File.separator + str10)) {
                        MifiLog.b(d, "apply modify fail: patch old=" + str8 + ", new=" + str9 + ", delta=" + str10);
                        a(str4, str5);
                        return false;
                    } else if (!TextUtils.equals(str8, str9)) {
                        FileUtils.a(new File(str4 + File.separator + str8));
                    }
                }
            }
        }
        String str11 = str3 + File.separator + f + "_temp";
        if (!ZipUtils.a(str4, str11)) {
            a(str4, str5, str11);
            return false;
        }
        if (!new File(str11).renameTo(new File(str3 + File.separator + f))) {
            a(str4, str5, str11);
            return false;
        }
        a(str4, str5);
        return true;
    }

    private static boolean a(String str, String str2, String str3) {
        if (!new File(str).exists() || !new File(str3).exists() || !FileUtils.b(str2) || Patcher.a(str, str2, str3) != 0) {
            return false;
        }
        return true;
    }

    private static void a(String... strArr) {
        for (String file : strArr) {
            FileUtils.a(new File(file));
        }
    }

    /* access modifiers changed from: private */
    public static boolean b(Context context, String str, UpdateInfo updateInfo) {
        String str2;
        try {
            str2 = context.getFilesDir().getCanonicalPath();
        } catch (IOException e2) {
            e2.printStackTrace();
            str2 = null;
        }
        if (str2 == null) {
            return false;
        }
        String str3 = str2 + File.separator + str;
        File file = new File(str2 + File.separator + g);
        FileUtils.a(file);
        if (!new File(str3).isFile()) {
            return false;
        }
        if (!ZipUtils.a(new File(str3), file)) {
            MifiLog.b(d, "unzip fail");
            FileUtils.a(file);
            return false;
        }
        File file2 = new File(str2 + File.separator + b);
        FileUtils.a(file2);
        if (!file.renameTo(file2)) {
            return false;
        }
        FileUtils.a(file);
        PreferenceUtils.a(context, ResourceConstants.f11078a, j, updateInfo != null ? updateInfo.c : 0);
        PreferenceUtils.c(context, ResourceConstants.f11078a, c);
        if (updateInfo == null || updateInfo.d == null || updateInfo.d.f1463a == null) {
            return true;
        }
        for (String next : updateInfo.d.f1463a.keySet()) {
            PreferenceUtils.a(context, ResourceConstants.f11078a, c + next, updateInfo.d.f1463a.get(next));
        }
        return true;
    }

    public static void a(final Context context) {
        synchronized (ResourceUpdateManager.class) {
            if (!l) {
                l = true;
                new Thread(new Runnable() {
                    public void run() {
                        SyncResult syncResult;
                        String a2 = FileUtils.a(context, ResourceUpdateManager.e);
                        boolean z = false;
                        if (a2 == null) {
                            a(false);
                            return;
                        }
                        if (!new File(a2).exists()) {
                            MifiLog.b(ResourceUpdateManager.d, "copy build-in static resource.");
                            long currentTimeMillis = System.currentTimeMillis();
                            boolean unused = ResourceUpdateManager.e(context, ResourceUpdateManager.f, ResourceUpdateManager.e);
                            MifiLog.b("TestTime", "copy build-in static resource takes: " + (System.currentTimeMillis() - currentTimeMillis));
                        }
                        String a3 = FileUtils.a(context, ResourceUpdateManager.f);
                        if (a3 == null) {
                            a(false);
                            return;
                        }
                        boolean exists = new File(a3).exists();
                        if (!exists) {
                            MifiLog.b(ResourceUpdateManager.d, "no cached static resource.");
                        }
                        long currentTimeMillis2 = System.currentTimeMillis();
                        String f = exists ? FileUtils.f(a3) : null;
                        MifiLog.b("TestTime", "resource zip md5 takes: " + (System.currentTimeMillis() - currentTimeMillis2));
                        UpdateInfo b = ResourceUpdateManager.c(context, f, FileUtils.f(a2));
                        if (b != null) {
                            if (b.b == UpdateType.ONLINE.getValue()) {
                                FileUtils.a(context.getFileStreamPath(ResourceUpdateManager.b));
                                MifiLog.b(ResourceUpdateManager.d, "use online");
                            } else {
                                if (b.f1462a) {
                                    syncResult = ResourceUpdateManager.b(context, b);
                                } else {
                                    MifiLog.b(ResourceUpdateManager.d, "no static resource updates.");
                                    syncResult = null;
                                }
                                if (!b.f1462a && !exists) {
                                    z = ResourceUpdateManager.b(context, ResourceUpdateManager.e, (UpdateInfo) null);
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("apply build-in static resource ");
                                    sb.append(z ? "successfully!" : "fail.");
                                    MifiLog.b(ResourceUpdateManager.d, sb.toString());
                                }
                                ResourceUpdateManager.b(context, b, syncResult, z);
                                a(true);
                            }
                        }
                        syncResult = null;
                        ResourceUpdateManager.b(context, b, syncResult, z);
                        a(true);
                    }

                    private void a(boolean z) {
                        synchronized (ResourceUpdateManager.class) {
                            boolean unused = ResourceUpdateManager.l = false;
                        }
                    }
                }).start();
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean e(Context context, String str, String str2) {
        FileOutputStream fileOutputStream;
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            inputStream = context.getAssets().open(str);
            try {
                fileOutputStream = new FileOutputStream(context.getFilesDir().getCanonicalPath() + File.separator + str2);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read != -1) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileOutputStream.flush();
                            Utils.a((Closeable) inputStream);
                            Utils.a((Closeable) fileOutputStream);
                            return true;
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    inputStream2 = inputStream;
                    try {
                        MifiLog.e(d, "copyFileFromAssetsToData throws exception - " + e);
                        Utils.a((Closeable) inputStream2);
                        Utils.a((Closeable) fileOutputStream);
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStream2;
                        Utils.a((Closeable) inputStream);
                        Utils.a((Closeable) fileOutputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    Utils.a((Closeable) inputStream);
                    Utils.a((Closeable) fileOutputStream);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                fileOutputStream = null;
                inputStream2 = inputStream;
                MifiLog.e(d, "copyFileFromAssetsToData throws exception - " + e);
                Utils.a((Closeable) inputStream2);
                Utils.a((Closeable) fileOutputStream);
                return false;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                Utils.a((Closeable) inputStream);
                Utils.a((Closeable) fileOutputStream);
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            fileOutputStream = null;
            MifiLog.e(d, "copyFileFromAssetsToData throws exception - " + e);
            Utils.a((Closeable) inputStream2);
            Utils.a((Closeable) fileOutputStream);
            return false;
        } catch (Throwable th4) {
            th = th4;
            inputStream = null;
            fileOutputStream = null;
            Utils.a((Closeable) inputStream);
            Utils.a((Closeable) fileOutputStream);
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public static void b(Context context, UpdateInfo updateInfo, SyncResult syncResult, boolean z) {
        String str;
        String str2;
        String str3;
        long j2 = 0;
        long b2 = PreferenceUtils.b(context, ResourceConstants.f11078a, j, 0);
        if (updateInfo != null) {
            j2 = updateInfo.c;
        }
        int i2 = updateInfo != null ? updateInfo.b : 0;
        String baseType = (updateInfo == null || updateInfo.d == null || updateInfo.d.f == null) ? null : updateInfo.d.f.toString();
        HashMap hashMap = new HashMap();
        hashMap.put("lastUpdateTime", String.valueOf(b2));
        hashMap.put("latestResourceTime", String.valueOf(j2));
        hashMap.put("updateType", String.valueOf(i2));
        hashMap.put("baseType", baseType);
        if (syncResult == null) {
            str = "false";
        } else {
            str = String.valueOf(syncResult.f1460a);
        }
        hashMap.put("syncSuc", str);
        if (syncResult == null) {
            str2 = "0";
        } else {
            str2 = String.valueOf(syncResult.b);
        }
        hashMap.put("updateSize", str2);
        if (syncResult == null) {
            str3 = "0";
        } else {
            str3 = String.valueOf(syncResult.c);
        }
        hashMap.put("syncTime", str3);
        hashMap.put("applyBuildinSuc", String.valueOf(z));
        StatUtils.a(context, "StaticResource", "StaticResourceUpdate", (Map<String, String>) hashMap, (Bundle) null);
    }
}
