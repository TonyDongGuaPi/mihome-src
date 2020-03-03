package com.xiaomi.smarthome.setting;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.alipay.sdk.util.i;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.file.FileUtils;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import java.io.File;
import java.util.List;
import org.json.JSONObject;

public class PluginSetting {

    /* renamed from: a  reason: collision with root package name */
    public static final int f22089a = 100;
    public static final int b = 10034;
    public static final String c = "plugin_rn_sdk";
    public static final String d = "sdk.bundle";
    public static final String e = "main.bundle";
    public static final long f = 1;
    public static final long g = 100;
    public static final long h = 1;
    public static final long i = 100;
    public static final String j = "device item clicked";
    public static final String k = "runtime load plugin";
    public static final String l = "plugin componentWillMount";
    public static final String m = "plugin componentDidMount";
    private static final String n = "/mnt/sdcard/SmartHome/logs/plugin_timestamp.log";
    private static final String o = "MIOTRNSDK10034";
    private static RnSdkInfo p;

    public static boolean a(long j2) {
        return j2 > 0;
    }

    public static boolean b(long j2) {
        return j2 > 0;
    }

    public static boolean c(long j2) {
        return 1 <= j2 && j2 <= 100;
    }

    public static boolean d(long j2) {
        return j2 > 0;
    }

    public static final boolean a() {
        return GlobalSetting.q || GlobalSetting.s;
    }

    public static final boolean b() {
        if (!a()) {
            return false;
        }
        return FileUtils.f(n);
    }

    public static final void a(boolean z) {
        if (a()) {
            if (z) {
                FileUtils.i(n);
            } else {
                FileUtils.d(n);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x007e A[SYNTHETIC, Splitter:B:25:0x007e] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0084 A[SYNTHETIC, Splitter:B:29:0x0084] */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void a(java.lang.String r5) {
        /*
            boolean r0 = a()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.String r2 = "RN_TIMESTAMP"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r5)
            java.lang.String r4 = " : "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            android.util.Log.i(r2, r3)
            boolean r2 = b()
            if (r2 != 0) goto L_0x002b
            return
        L_0x002b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = ":"
            r2.append(r0)
            r2.append(r5)
            java.lang.String r0 = ";\n"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.String r1 = "device item clicked"
            boolean r5 = r1.equals(r5)
            r1 = 0
            if (r5 == 0) goto L_0x0055
            java.lang.String r5 = "/mnt/sdcard/SmartHome/logs/plugin_timestamp.log"
            java.lang.String r2 = ""
            com.xiaomi.smarthome.frame.file.FileUtils.a(r1, r5, r0, r2)
            goto L_0x0081
        L_0x0055:
            java.io.FileWriter r5 = new java.io.FileWriter     // Catch:{ Exception -> 0x0074 }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0074 }
            java.lang.String r3 = "/mnt/sdcard/SmartHome/logs/plugin_timestamp.log"
            r2.<init>(r3)     // Catch:{ Exception -> 0x0074 }
            r3 = 1
            r5.<init>(r2, r3)     // Catch:{ Exception -> 0x0074 }
            r5.append(r0)     // Catch:{ Exception -> 0x006f, all -> 0x006c }
            r5.close()     // Catch:{ Exception -> 0x006f, all -> 0x006c }
            r5.close()     // Catch:{ Exception -> 0x0081 }
            goto L_0x0081
        L_0x006c:
            r0 = move-exception
            r1 = r5
            goto L_0x0082
        L_0x006f:
            r0 = move-exception
            r1 = r5
            goto L_0x0075
        L_0x0072:
            r0 = move-exception
            goto L_0x0082
        L_0x0074:
            r0 = move-exception
        L_0x0075:
            java.lang.String r5 = "RN_TIMESTAMP"
            java.lang.String r2 = "failed to write file"
            android.util.Log.i(r5, r2, r0)     // Catch:{ all -> 0x0072 }
            if (r1 == 0) goto L_0x0081
            r1.close()     // Catch:{ Exception -> 0x0081 }
        L_0x0081:
            return
        L_0x0082:
            if (r1 == 0) goto L_0x0087
            r1.close()     // Catch:{ Exception -> 0x0087 }
        L_0x0087:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.setting.PluginSetting.a(java.lang.String):void");
    }

    public static final void c() {
        if (a()) {
            String a2 = FileUtils.a(n);
            if (!TextUtils.isEmpty(a2)) {
                String[] split = a2.split(i.b);
                int length = split.length;
                Long l2 = null;
                Long l3 = null;
                Long l4 = null;
                Long l5 = null;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    String str = split[i2];
                    if (!TextUtils.isEmpty(str)) {
                        String[] split2 = str.split(":");
                        if (split2.length < 2) {
                            continue;
                        } else if (l3 == null) {
                            if (j.equals(split2[1].trim())) {
                                l3 = Long.valueOf(Long.parseLong(split2[0].trim()));
                            }
                        } else if (l4 == null) {
                            if (k.equals(split2[1].trim())) {
                                l4 = Long.valueOf(Long.parseLong(split2[0].trim()));
                            }
                        } else if (l5 == null) {
                            if (l.equals(split2[1].trim())) {
                                l5 = Long.valueOf(Long.parseLong(split2[0].trim()));
                            }
                        } else if (m.equals(split2[1].trim())) {
                            l2 = Long.valueOf(Long.parseLong(split2[0].trim()));
                            break;
                        }
                    }
                    i2++;
                }
                if (l2 != null) {
                    Toast.makeText(FrameManager.b().c(), "time used:\n" + "ready:   " + (l4.longValue() - l3.longValue()) + " ms\n" + "loading: " + (l5.longValue() - l4.longValue()) + " ms\n" + "loadAll: " + (l5.longValue() - l3.longValue()) + " ms\n" + "render:  " + (l2.longValue() - l5.longValue()) + " ms\n" + "all:     " + (l2.longValue() - l3.longValue()) + " ms\n", 1).show();
                }
            }
        }
    }

    public static final void a(Object... objArr) {
        if (objArr != null && objArr.length >= 1) {
            if (objArr.length == 1) {
                Log.i(o, "" + objArr[0]);
            } else if (objArr.length != 2 || !(objArr[1] instanceof Throwable)) {
                StringBuilder sb = new StringBuilder();
                Throwable th = null;
                for (Throwable th2 : objArr) {
                    if (th2 instanceof Throwable) {
                        th = th2;
                    } else {
                        sb.append(th2 + " | ");
                    }
                }
                if (th != null) {
                    Log.i(o, sb.toString(), th);
                } else {
                    Log.i(o, sb.toString());
                }
            } else {
                Log.i(o, objArr[0] + "", objArr[1]);
            }
        }
    }

    public static class RnSdkInfo {

        /* renamed from: a  reason: collision with root package name */
        public final long f22090a;
        public final long b;
        public final String c;
        public final String d;
        public final boolean e;

        public RnSdkInfo(long j, long j2, String str, boolean z) {
            this.f22090a = j;
            this.b = j2;
            if (z) {
                this.c = str;
                this.d = "asset:/plugin_rn_sdk" + File.separator;
            } else {
                if (str.endsWith(File.separator)) {
                    this.d = str;
                } else {
                    this.d = str + File.separator;
                }
                this.c = this.d + PluginSetting.d;
            }
            this.e = z;
        }

        public String toString() {
            return "api_level: " + this.f22090a + "   build_time: " + this.b + "  is_inner_sdk: " + this.e + "  install_path: " + this.c;
        }
    }

    public static final RnSdkInfo a(Context context, String str) {
        if (p != null) {
            return p;
        }
        try {
            JSONObject jSONObject = new JSONObject(FileUtils.a(context.getAssets().open("plugin_rn_sdk/project.json")));
            long j2 = jSONObject.getLong(PluginManager.c);
            long j3 = jSONObject.getLong("build_time");
            p = new RnSdkInfo(j2, j3, "assets://plugin_rn_sdk" + File.separator + d, true);
            a("create inner rn sdk info", Long.valueOf(p.f22090a), p.c);
            return p;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static final List<String> b(String str) {
        return a(str, "trigger_ids");
    }

    public static final List<String> c(String str) {
        return a(str, "action_ids");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x008a A[SYNTHETIC, Splitter:B:25:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009c A[SYNTHETIC, Splitter:B:31:0x009c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.util.List<java.lang.String> a(java.lang.String r4, java.lang.String r5) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.a((java.lang.String) r4)
            r1.append(r4)
            java.lang.String r4 = java.io.File.separator
            r1.append(r4)
            java.lang.String r4 = "project.json"
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            java.lang.String r1 = "project_json_path"
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r1, r4)
            java.io.File r1 = new java.io.File
            r1.<init>(r4)
            boolean r4 = r1.exists()
            if (r4 == 0) goto L_0x00ab
            r4 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x007c, all -> 0x0078 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x007c, all -> 0x0078 }
            int r4 = r2.available()     // Catch:{ Exception -> 0x0076 }
            byte[] r4 = new byte[r4]     // Catch:{ Exception -> 0x0076 }
            r2.read(r4)     // Catch:{ Exception -> 0x0076 }
            java.lang.String r1 = new java.lang.String     // Catch:{ Exception -> 0x0076 }
            r1.<init>(r4)     // Catch:{ Exception -> 0x0076 }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Exception -> 0x0076 }
            r4.<init>(r1)     // Catch:{ Exception -> 0x0076 }
            java.lang.String r1 = "entrance_scene"
            org.json.JSONObject r4 = r4.getJSONObject(r1)     // Catch:{ Exception -> 0x0076 }
            org.json.JSONArray r4 = r4.optJSONArray(r5)     // Catch:{ Exception -> 0x0076 }
            if (r4 == 0) goto L_0x006f
            int r5 = r4.length()     // Catch:{ Exception -> 0x0076 }
            if (r5 <= 0) goto L_0x006f
            int r5 = r4.length()     // Catch:{ Exception -> 0x0076 }
            r1 = 0
        L_0x005f:
            if (r1 >= r5) goto L_0x006f
            java.lang.Object r3 = r4.get(r1)     // Catch:{ Exception -> 0x0076 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0076 }
            r0.add(r3)     // Catch:{ Exception -> 0x0076 }
            int r1 = r1 + 1
            goto L_0x005f
        L_0x006f:
            r2.close()     // Catch:{ Exception -> 0x0076 }
            r2.close()     // Catch:{ IOException -> 0x008e }
            goto L_0x00ab
        L_0x0076:
            r4 = move-exception
            goto L_0x007f
        L_0x0078:
            r5 = move-exception
            r2 = r4
            r4 = r5
            goto L_0x009a
        L_0x007c:
            r5 = move-exception
            r2 = r4
            r4 = r5
        L_0x007f:
            java.lang.String r5 = "PluginSetting"
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0099 }
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r5, r4)     // Catch:{ all -> 0x0099 }
            if (r2 == 0) goto L_0x00ab
            r2.close()     // Catch:{ IOException -> 0x008e }
            goto L_0x00ab
        L_0x008e:
            r4 = move-exception
            java.lang.String r5 = "PluginSetting"
            java.lang.String r4 = r4.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r5, r4)
            goto L_0x00ab
        L_0x0099:
            r4 = move-exception
        L_0x009a:
            if (r2 == 0) goto L_0x00aa
            r2.close()     // Catch:{ IOException -> 0x00a0 }
            goto L_0x00aa
        L_0x00a0:
            r5 = move-exception
            java.lang.String r5 = r5.toString()
            java.lang.String r0 = "PluginSetting"
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r0, r5)
        L_0x00aa:
            throw r4
        L_0x00ab:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.setting.PluginSetting.a(java.lang.String, java.lang.String):java.util.List");
    }

    public static String a(Context context) {
        File dir = context.getDir(ShareConstants.q, 0);
        return dir.getAbsolutePath() + File.separator + "plugin";
    }

    public static String a(Context context, long j2, long j3) {
        File dir = context.getDir(ShareConstants.q, 0);
        return dir.getAbsolutePath() + File.separator + "plugin" + File.separator + j2;
    }

    public static String b(Context context) {
        return context.getFilesDir().getPath() + File.separator + "plugin" + File.separator + "install" + File.separator + "libs";
    }

    public static String c(Context context) {
        return context.getFilesDir().getPath() + File.separator + "plugin" + File.separator + "install" + File.separator + ShareConstants.o;
    }

    public static String b(Context context, long j2, long j3) {
        return c(context) + File.separator + j2 + File.separator + j3;
    }

    public static String a(Context context, long j2, long j3, String str) {
        return c(context) + File.separator + j2 + File.separator + j3 + File.separator + str;
    }
}
