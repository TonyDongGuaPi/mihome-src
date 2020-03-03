package com.hianalytics.android.b.a;

import android.content.Context;
import android.content.SharedPreferences;
import com.taobao.weex.annotation.JSMethod;

public final class c {
    public static SharedPreferences a(Context context, String str) {
        String packageName = context.getPackageName();
        return context.getSharedPreferences("hianalytics_" + str + JSMethod.NOT_SET + packageName, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002b A[SYNTHETIC, Splitter:B:15:0x002b] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0037 A[SYNTHETIC, Splitter:B:24:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0043 A[SYNTHETIC, Splitter:B:33:0x0043] */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r2, org.json.JSONObject r3, java.lang.String r4) {
        /*
            java.lang.String r4 = d(r2, r4)
            r0 = 0
            r1 = 0
            java.io.FileOutputStream r2 = r2.openFileOutput(r4, r0)     // Catch:{ FileNotFoundException -> 0x0040, IOException -> 0x0034, all -> 0x0027 }
            java.lang.String r3 = r3.toString()     // Catch:{ FileNotFoundException -> 0x0041, IOException -> 0x0035, all -> 0x0025 }
            java.lang.String r4 = "UTF-8"
            byte[] r3 = r3.getBytes(r4)     // Catch:{ FileNotFoundException -> 0x0041, IOException -> 0x0035, all -> 0x0025 }
            r2.write(r3)     // Catch:{ FileNotFoundException -> 0x0041, IOException -> 0x0035, all -> 0x0025 }
            r2.flush()     // Catch:{ FileNotFoundException -> 0x0041, IOException -> 0x0035, all -> 0x0025 }
            if (r2 == 0) goto L_0x0024
            r2.close()     // Catch:{ IOException -> 0x0020 }
            return
        L_0x0020:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0024:
            return
        L_0x0025:
            r3 = move-exception
            goto L_0x0029
        L_0x0027:
            r3 = move-exception
            r2 = r1
        L_0x0029:
            if (r2 == 0) goto L_0x0033
            r2.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0033
        L_0x002f:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0033:
            throw r3
        L_0x0034:
            r2 = r1
        L_0x0035:
            if (r2 == 0) goto L_0x003f
            r2.close()     // Catch:{ IOException -> 0x003b }
            return
        L_0x003b:
            r2 = move-exception
            r2.printStackTrace()
        L_0x003f:
            return
        L_0x0040:
            r2 = r1
        L_0x0041:
            if (r2 == 0) goto L_0x004b
            r2.close()     // Catch:{ IOException -> 0x0047 }
            return
        L_0x0047:
            r2 = move-exception
            r2.printStackTrace()
        L_0x004b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hianalytics.android.b.a.c.a(android.content.Context, org.json.JSONObject, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x00cf A[SYNTHETIC, Splitter:B:100:0x00cf] */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x00dc A[SYNTHETIC, Splitter:B:109:0x00dc] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x00e6 A[SYNTHETIC, Splitter:B:114:0x00e6] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x007a A[SYNTHETIC, Splitter:B:54:0x007a] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0084 A[SYNTHETIC, Splitter:B:59:0x0084] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0098 A[SYNTHETIC, Splitter:B:69:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00a2 A[SYNTHETIC, Splitter:B:74:0x00a2] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00ae A[SYNTHETIC, Splitter:B:81:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x00b8 A[SYNTHETIC, Splitter:B:86:0x00b8] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x00c5 A[SYNTHETIC, Splitter:B:95:0x00c5] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:66:0x0090=Splitter:B:66:0x0090, B:51:0x0075=Splitter:B:51:0x0075} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject b(android.content.Context r5, java.lang.String r6) {
        /*
            r0 = 0
            java.lang.String r1 = d(r5, r6)     // Catch:{ FileNotFoundException -> 0x00d8, IOException -> 0x00c1, JSONException -> 0x008d, Exception -> 0x0072, all -> 0x006e }
            java.io.FileInputStream r1 = r5.openFileInput(r1)     // Catch:{ FileNotFoundException -> 0x00d8, IOException -> 0x00c1, JSONException -> 0x008d, Exception -> 0x0072, all -> 0x006e }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x006b, IOException -> 0x0068, JSONException -> 0x0065, Exception -> 0x0062, all -> 0x005f }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ FileNotFoundException -> 0x006b, IOException -> 0x0068, JSONException -> 0x0065, Exception -> 0x0062, all -> 0x005f }
            java.lang.String r4 = "UTF-8"
            r3.<init>(r1, r4)     // Catch:{ FileNotFoundException -> 0x006b, IOException -> 0x0068, JSONException -> 0x0065, Exception -> 0x0062, all -> 0x005f }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x006b, IOException -> 0x0068, JSONException -> 0x0065, Exception -> 0x0062, all -> 0x005f }
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c3, JSONException -> 0x005d, Exception -> 0x005b }
            java.lang.String r4 = ""
            r3.<init>(r4)     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c3, JSONException -> 0x005d, Exception -> 0x005b }
        L_0x001c:
            java.lang.String r4 = r2.readLine()     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c3, JSONException -> 0x005d, Exception -> 0x005b }
            if (r4 != 0) goto L_0x0057
            int r4 = r3.length()     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c3, JSONException -> 0x005d, Exception -> 0x005b }
            if (r4 != 0) goto L_0x003b
            r2.close()     // Catch:{ IOException -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0030:
            if (r1 == 0) goto L_0x003a
            r1.close()     // Catch:{ IOException -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r5 = move-exception
            r5.printStackTrace()
        L_0x003a:
            return r0
        L_0x003b:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c3, JSONException -> 0x005d, Exception -> 0x005b }
            java.lang.String r3 = r3.toString()     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c3, JSONException -> 0x005d, Exception -> 0x005b }
            r4.<init>(r3)     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c3, JSONException -> 0x005d, Exception -> 0x005b }
            r2.close()     // Catch:{ IOException -> 0x0048 }
            goto L_0x004c
        L_0x0048:
            r5 = move-exception
            r5.printStackTrace()
        L_0x004c:
            if (r1 == 0) goto L_0x0056
            r1.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0056:
            return r4
        L_0x0057:
            r3.append(r4)     // Catch:{ FileNotFoundException -> 0x00da, IOException -> 0x00c3, JSONException -> 0x005d, Exception -> 0x005b }
            goto L_0x001c
        L_0x005b:
            r5 = move-exception
            goto L_0x0075
        L_0x005d:
            r3 = move-exception
            goto L_0x0090
        L_0x005f:
            r5 = move-exception
            r2 = r0
            goto L_0x00ac
        L_0x0062:
            r5 = move-exception
            r2 = r0
            goto L_0x0075
        L_0x0065:
            r3 = move-exception
            r2 = r0
            goto L_0x0090
        L_0x0068:
            r2 = r0
            goto L_0x00c3
        L_0x006b:
            r2 = r0
            goto L_0x00da
        L_0x006e:
            r5 = move-exception
            r1 = r0
            r2 = r1
            goto L_0x00ac
        L_0x0072:
            r5 = move-exception
            r1 = r0
            r2 = r1
        L_0x0075:
            r5.printStackTrace()     // Catch:{ all -> 0x00ab }
            if (r2 == 0) goto L_0x0082
            r2.close()     // Catch:{ IOException -> 0x007e }
            goto L_0x0082
        L_0x007e:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0082:
            if (r1 == 0) goto L_0x008c
            r1.close()     // Catch:{ IOException -> 0x0088 }
            goto L_0x008c
        L_0x0088:
            r5 = move-exception
            r5.printStackTrace()
        L_0x008c:
            return r0
        L_0x008d:
            r3 = move-exception
            r1 = r0
            r2 = r1
        L_0x0090:
            r3.printStackTrace()     // Catch:{ all -> 0x00ab }
            c(r5, r6)     // Catch:{ all -> 0x00ab }
            if (r2 == 0) goto L_0x00a0
            r2.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x00a0
        L_0x009c:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00a0:
            if (r1 == 0) goto L_0x00aa
            r1.close()     // Catch:{ IOException -> 0x00a6 }
            goto L_0x00aa
        L_0x00a6:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00aa:
            return r0
        L_0x00ab:
            r5 = move-exception
        L_0x00ac:
            if (r2 == 0) goto L_0x00b6
            r2.close()     // Catch:{ IOException -> 0x00b2 }
            goto L_0x00b6
        L_0x00b2:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00b6:
            if (r1 == 0) goto L_0x00c0
            r1.close()     // Catch:{ IOException -> 0x00bc }
            goto L_0x00c0
        L_0x00bc:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00c0:
            throw r5
        L_0x00c1:
            r1 = r0
            r2 = r1
        L_0x00c3:
            if (r2 == 0) goto L_0x00cd
            r2.close()     // Catch:{ IOException -> 0x00c9 }
            goto L_0x00cd
        L_0x00c9:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00cd:
            if (r1 == 0) goto L_0x00d7
            r1.close()     // Catch:{ IOException -> 0x00d3 }
            goto L_0x00d7
        L_0x00d3:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00d7:
            return r0
        L_0x00d8:
            r1 = r0
            r2 = r1
        L_0x00da:
            if (r2 == 0) goto L_0x00e4
            r2.close()     // Catch:{ IOException -> 0x00e0 }
            goto L_0x00e4
        L_0x00e0:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00e4:
            if (r1 == 0) goto L_0x00ee
            r1.close()     // Catch:{ IOException -> 0x00ea }
            goto L_0x00ee
        L_0x00ea:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00ee:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hianalytics.android.b.a.c.b(android.content.Context, java.lang.String):org.json.JSONObject");
    }

    public static void c(Context context, String str) {
        context.deleteFile(d(context, str));
    }

    private static String d(Context context, String str) {
        String packageName = context.getPackageName();
        return "hianalytics_" + str + JSMethod.NOT_SET + packageName;
    }
}
