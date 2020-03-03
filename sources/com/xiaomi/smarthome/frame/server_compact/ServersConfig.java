package com.xiaomi.smarthome.frame.server_compact;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.frame.file.FileUtils;
import java.io.File;
import java.util.Locale;

public class ServersConfig {

    /* renamed from: a  reason: collision with root package name */
    private static final String f16369a = "server_config";
    private static final String b = "ServersConfig";

    public static String a(@NonNull Context context, @NonNull Locale locale) {
        String a2 = FileUtils.a(context, f16369a + File.separator + LocaleUtil.b(locale).toLowerCase() + ".json");
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        String a3 = FileUtils.a(context, f16369a + File.separator + a(locale.getLanguage().toLowerCase()) + ".json");
        if (!TextUtils.isEmpty(a3)) {
            return a3;
        }
        return FileUtils.a(a(context, LocaleUtil.b(locale).toLowerCase()));
    }

    private static String a(String str) {
        if (TextUtils.equals("iw", str)) {
            return "he";
        }
        if (TextUtils.equals("ji", str)) {
            return "yi";
        }
        return TextUtils.equals("in", str) ? "id" : str;
    }

    public static boolean b(@NonNull Context context, @NonNull Locale locale) {
        if (!TextUtils.isEmpty(FileUtils.a(context, f16369a + File.separator + LocaleUtil.b(locale).toLowerCase() + ".json"))) {
            return true;
        }
        if (!TextUtils.isEmpty(FileUtils.a(context, f16369a + File.separator + a(locale.getLanguage().toLowerCase()) + ".json"))) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.io.File] */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.io.OutputStreamWriter] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004d, code lost:
        r6 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0050 A[Catch:{ Exception -> 0x0054 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x005a A[SYNTHETIC, Splitter:B:33:0x005a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(@android.support.annotation.NonNull android.content.Context r4, java.lang.String r5, java.lang.String r6) {
        /*
            r0 = 0
            r1 = 0
            java.lang.String r4 = a((android.content.Context) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x0057 }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x0057 }
            r5.<init>(r4)     // Catch:{ Exception -> 0x0057 }
            boolean r2 = r5.exists()     // Catch:{ Exception -> 0x0058 }
            if (r2 == 0) goto L_0x002b
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0058 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0058 }
            r3.<init>()     // Catch:{ Exception -> 0x0058 }
            r3.append(r4)     // Catch:{ Exception -> 0x0058 }
            java.lang.String r4 = ".bak"
            r3.append(r4)     // Catch:{ Exception -> 0x0058 }
            java.lang.String r4 = r3.toString()     // Catch:{ Exception -> 0x0058 }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0058 }
            r5.renameTo(r2)     // Catch:{ Exception -> 0x0054 }
            goto L_0x002f
        L_0x002b:
            com.xiaomi.smarthome.frame.file.FileUtils.i(r4)     // Catch:{ Exception -> 0x0058 }
            r2 = r1
        L_0x002f:
            java.io.OutputStreamWriter r4 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x004d }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x004d }
            r3.<init>(r5)     // Catch:{ all -> 0x004d }
            r4.<init>(r3)     // Catch:{ all -> 0x004d }
            int r1 = r6.length()     // Catch:{ all -> 0x004a }
            r4.write(r6, r0, r1)     // Catch:{ all -> 0x004a }
            if (r2 == 0) goto L_0x0045
            r2.deleteOnExit()     // Catch:{ all -> 0x004a }
        L_0x0045:
            r4.close()     // Catch:{ Exception -> 0x0054 }
            r4 = 1
            return r4
        L_0x004a:
            r6 = move-exception
            r1 = r4
            goto L_0x004e
        L_0x004d:
            r6 = move-exception
        L_0x004e:
            if (r1 == 0) goto L_0x0056
            r1.close()     // Catch:{ Exception -> 0x0054 }
            goto L_0x0056
        L_0x0054:
            r1 = r2
            goto L_0x0058
        L_0x0056:
            throw r6     // Catch:{ Exception -> 0x0054 }
        L_0x0057:
            r5 = r1
        L_0x0058:
            if (r5 == 0) goto L_0x005d
            r5.deleteOnExit()     // Catch:{ Exception -> 0x0064 }
        L_0x005d:
            if (r1 == 0) goto L_0x0064
            if (r5 == 0) goto L_0x0064
            r1.renameTo(r5)     // Catch:{ Exception -> 0x0064 }
        L_0x0064:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.server_compact.ServersConfig.a(android.content.Context, java.lang.String, java.lang.String):boolean");
    }

    private static String a(Context context, String str) {
        return context.getCacheDir().getPath() + File.separator + f16369a + File.separator + str.toLowerCase();
    }
}
