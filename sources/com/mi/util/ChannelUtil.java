package com.mi.util;

import android.content.Context;
import com.mi.util.Utils;

public class ChannelUtil {
    protected static ChannelUtil sChannelUtil;
    protected String[] channels;

    public static ChannelUtil getInstance() {
        return sChannelUtil;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x005f A[SYNTHETIC, Splitter:B:30:0x005f] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x006b A[SYNTHETIC, Splitter:B:37:0x006b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getChannel(android.content.Context r4) {
        /*
            com.mi.util.ChannelUtil r0 = sChannelUtil
            r1 = 0
            if (r0 == 0) goto L_0x0074
            com.mi.util.ChannelUtil r0 = sChannelUtil
            java.lang.String[] r0 = r0.channels
            if (r0 != 0) goto L_0x000d
            goto L_0x0074
        L_0x000d:
            android.content.pm.ApplicationInfo r4 = r4.getApplicationInfo()
            java.lang.String r4 = r4.sourceDir
            java.util.zip.ZipFile r0 = new java.util.zip.ZipFile     // Catch:{ IOException -> 0x0058, all -> 0x0055 }
            r0.<init>(r4)     // Catch:{ IOException -> 0x0058, all -> 0x0055 }
            r4 = 0
        L_0x0019:
            com.mi.util.ChannelUtil r2 = sChannelUtil     // Catch:{ IOException -> 0x0053 }
            java.lang.String[] r2 = r2.channels     // Catch:{ IOException -> 0x0053 }
            int r2 = r2.length     // Catch:{ IOException -> 0x0053 }
            if (r4 >= r2) goto L_0x004f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0053 }
            r2.<init>()     // Catch:{ IOException -> 0x0053 }
            java.lang.String r3 = "META-INF/michannel_"
            r2.append(r3)     // Catch:{ IOException -> 0x0053 }
            com.mi.util.ChannelUtil r3 = sChannelUtil     // Catch:{ IOException -> 0x0053 }
            java.lang.String[] r3 = r3.channels     // Catch:{ IOException -> 0x0053 }
            r3 = r3[r4]     // Catch:{ IOException -> 0x0053 }
            r2.append(r3)     // Catch:{ IOException -> 0x0053 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0053 }
            java.util.zip.ZipEntry r2 = r0.getEntry(r2)     // Catch:{ IOException -> 0x0053 }
            if (r2 == 0) goto L_0x004c
            com.mi.util.ChannelUtil r2 = sChannelUtil     // Catch:{ IOException -> 0x0053 }
            java.lang.String[] r2 = r2.channels     // Catch:{ IOException -> 0x0053 }
            r4 = r2[r4]     // Catch:{ IOException -> 0x0053 }
            r0.close()     // Catch:{ IOException -> 0x0047 }
            goto L_0x004b
        L_0x0047:
            r0 = move-exception
            r0.printStackTrace()
        L_0x004b:
            return r4
        L_0x004c:
            int r4 = r4 + 1
            goto L_0x0019
        L_0x004f:
            r0.close()     // Catch:{ IOException -> 0x0063 }
            goto L_0x0067
        L_0x0053:
            r4 = move-exception
            goto L_0x005a
        L_0x0055:
            r4 = move-exception
            r0 = r1
            goto L_0x0069
        L_0x0058:
            r4 = move-exception
            r0 = r1
        L_0x005a:
            r4.printStackTrace()     // Catch:{ all -> 0x0068 }
            if (r0 == 0) goto L_0x0067
            r0.close()     // Catch:{ IOException -> 0x0063 }
            goto L_0x0067
        L_0x0063:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0067:
            return r1
        L_0x0068:
            r4 = move-exception
        L_0x0069:
            if (r0 == 0) goto L_0x0073
            r0.close()     // Catch:{ IOException -> 0x006f }
            goto L_0x0073
        L_0x006f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0073:
            throw r4
        L_0x0074:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.util.ChannelUtil.getChannel(android.content.Context):java.lang.String");
    }

    public static String getChannelCode(Context context) {
        String stringPref = Utils.Preference.getStringPref(context, "pref_channel", (String) null);
        if (stringPref != null && stringPref.length() > 0) {
            return stringPref;
        }
        String channel = getChannel(context);
        if (channel == null) {
            return "unknown";
        }
        Utils.Preference.setStringPref(context, "pref_channel", channel);
        return channel;
    }
}
