package com.xiaomi.smarthome.camera.v4.utils;

import android.content.Context;
import android.content.res.Resources;
import com.xiaomi.smarthome.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Util {
    public static String getSdCardStatus(Resources resources, int i) {
        String string = resources.getString(R.string.sd_card_status0);
        switch (i) {
            case 1:
                return resources.getString(R.string.sd_card_status1);
            case 2:
                return resources.getString(R.string.sd_card_status2);
            case 3:
                return resources.getString(R.string.sd_card_status3);
            case 4:
                return resources.getString(R.string.sd_card_status4);
            case 5:
                return resources.getString(R.string.sd_card_status5);
            default:
                return string;
        }
    }

    public static String getNasCycle(Resources resources, int i) {
        if (i == 86400) {
            return resources.getString(R.string.nas_recycle_day);
        }
        if (i == 604800) {
            return resources.getString(R.string.nas_recycle_week);
        }
        if (i != 2592000) {
            return i != 7776000 ? "" : resources.getString(R.string.nas_recycle_3_months);
        }
        return resources.getString(R.string.nas_recycle_month);
    }

    public static String getNasSyncInterval(Resources resources, int i) {
        if (i == 300) {
            return resources.getString(R.string.nas_sync_interval_real_time);
        }
        if (i == 3600) {
            return resources.getString(R.string.nas_sync_interval_1_hour);
        }
        if (i != 86400) {
            return null;
        }
        return resources.getString(R.string.nas_sync_interval_1_day);
    }

    public static String getProgress(Resources resources, int i) {
        if (i < 20) {
            return resources.getString(R.string.connect_step1);
        }
        if (i < 40) {
            return resources.getString(R.string.connect_step2);
        }
        if (i < 70) {
            return resources.getString(R.string.connect_step3);
        }
        if (i < 80) {
            return resources.getString(R.string.connect_step4);
        }
        if (i == 100) {
            return resources.getString(R.string.connect_step5);
        }
        return resources.getString(R.string.connect_step5);
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:27:0x0046=Splitter:B:27:0x0046, B:14:0x002d=Splitter:B:14:0x002d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.text.Spanned getRawTxt(android.content.res.Resources r2, int r3) {
        /*
            if (r3 >= 0) goto L_0x0004
            r2 = 0
            return r2
        L_0x0004:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.io.InputStream r2 = r2.openRawResource(r3)
            if (r2 == 0) goto L_0x005d
            java.io.InputStreamReader r3 = new java.io.InputStreamReader
            r3.<init>(r2)
            java.io.BufferedReader r1 = new java.io.BufferedReader
            r1.<init>(r3)
        L_0x0019:
            java.lang.String r3 = r1.readLine()     // Catch:{ IOException -> 0x0038 }
            if (r3 == 0) goto L_0x0023
            r0.append(r3)     // Catch:{ IOException -> 0x0038 }
            goto L_0x0019
        L_0x0023:
            if (r2 == 0) goto L_0x002d
            r2.close()     // Catch:{ IOException -> 0x0029 }
            goto L_0x002d
        L_0x0029:
            r2 = move-exception
            r2.printStackTrace()
        L_0x002d:
            r1.close()     // Catch:{ Exception -> 0x0031 }
            goto L_0x005d
        L_0x0031:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x005d
        L_0x0036:
            r3 = move-exception
            goto L_0x004a
        L_0x0038:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ all -> 0x0036 }
            if (r2 == 0) goto L_0x0046
            r2.close()     // Catch:{ IOException -> 0x0042 }
            goto L_0x0046
        L_0x0042:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0046:
            r1.close()     // Catch:{ Exception -> 0x0031 }
            goto L_0x005d
        L_0x004a:
            if (r2 == 0) goto L_0x0054
            r2.close()     // Catch:{ IOException -> 0x0050 }
            goto L_0x0054
        L_0x0050:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0054:
            r1.close()     // Catch:{ Exception -> 0x0058 }
            goto L_0x005c
        L_0x0058:
            r2 = move-exception
            r2.printStackTrace()
        L_0x005c:
            throw r3
        L_0x005d:
            java.lang.String r2 = r0.toString()
            android.text.Spanned r2 = android.text.Html.fromHtml(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.v4.utils.Util.getRawTxt(android.content.res.Resources, int):android.text.Spanned");
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static String getRawTxt2(Resources resources, int i) {
        if (i < 0) {
            return null;
        }
        InputStream openRawResource = resources.openRawResource(i);
        InputStreamReader inputStreamReader = new InputStreamReader(openRawResource);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append(10);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                break;
            } catch (Exception unused) {
            }
        }
        bufferedReader.close();
        inputStreamReader.close();
        openRawResource.close();
        return sb.toString();
    }
}
