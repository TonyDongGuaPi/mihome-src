package com.xiaomi.infra.galaxy.fds.android.util;

import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Util {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10150a = 4096;
    private static final ThreadLocal<SimpleDateFormat> b = new ThreadLocal<SimpleDateFormat>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public SimpleDateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat;
        }
    };

    /* JADX WARNING: Removed duplicated region for block: B:29:0x007d A[Catch:{ IOException -> 0x0080 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(com.xiaomi.infra.galaxy.fds.android.model.FDSObject r6, java.io.File r7, boolean r8) throws com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException {
        /*
            java.io.File r0 = r7.getParentFile()
            if (r8 != 0) goto L_0x0011
            if (r0 == 0) goto L_0x0011
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x0011
            r0.mkdirs()
        L_0x0011:
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r1 = new byte[r0]
            java.io.InputStream r2 = r6.d()
            r3 = 0
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x003f }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x003f }
            r5.<init>(r7, r8)     // Catch:{ IOException -> 0x003f }
            r4.<init>(r5)     // Catch:{ IOException -> 0x003f }
        L_0x0024:
            r7 = 0
            int r8 = r2.read(r1, r7, r0)     // Catch:{ IOException -> 0x003a, all -> 0x0037 }
            r3 = -1
            if (r8 == r3) goto L_0x0030
            r4.write(r1, r7, r8)     // Catch:{ IOException -> 0x003a, all -> 0x0037 }
            goto L_0x0024
        L_0x0030:
            r2.close()     // Catch:{ IOException -> 0x0036 }
            r4.close()     // Catch:{ IOException -> 0x0036 }
        L_0x0036:
            return
        L_0x0037:
            r6 = move-exception
            r3 = r4
            goto L_0x0078
        L_0x003a:
            r7 = move-exception
            r3 = r4
            goto L_0x0040
        L_0x003d:
            r6 = move-exception
            goto L_0x0078
        L_0x003f:
            r7 = move-exception
        L_0x0040:
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r8 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException     // Catch:{ all -> 0x003d }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x003d }
            r0.<init>()     // Catch:{ all -> 0x003d }
            java.lang.String r1 = "Unable to store object["
            r0.append(r1)     // Catch:{ all -> 0x003d }
            java.lang.String r1 = r6.b()     // Catch:{ all -> 0x003d }
            r0.append(r1)     // Catch:{ all -> 0x003d }
            java.lang.String r1 = "/"
            r0.append(r1)     // Catch:{ all -> 0x003d }
            java.lang.String r6 = r6.a()     // Catch:{ all -> 0x003d }
            r0.append(r6)     // Catch:{ all -> 0x003d }
            java.lang.String r6 = "] content "
            r0.append(r6)     // Catch:{ all -> 0x003d }
            java.lang.String r6 = "to disk:"
            r0.append(r6)     // Catch:{ all -> 0x003d }
            java.lang.String r6 = r7.getMessage()     // Catch:{ all -> 0x003d }
            r0.append(r6)     // Catch:{ all -> 0x003d }
            java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x003d }
            r8.<init>(r6, r7)     // Catch:{ all -> 0x003d }
            throw r8     // Catch:{ all -> 0x003d }
        L_0x0078:
            r2.close()     // Catch:{ IOException -> 0x0080 }
            if (r3 == 0) goto L_0x0080
            r3.close()     // Catch:{ IOException -> 0x0080 }
        L_0x0080:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.infra.galaxy.fds.android.util.Util.a(com.xiaomi.infra.galaxy.fds.android.model.FDSObject, java.io.File, boolean):void");
    }

    public static String a(Exception exc) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exc.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static String a(File file) {
        int i;
        String mimeTypeFromExtension;
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf <= 0 || (i = lastIndexOf + 1) >= name.length() || (mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(name.substring(i))) == null) {
            return Consts.g;
        }
        return mimeTypeFromExtension;
    }

    public static Date a(String str) throws ParseException {
        return b.get().parse(str);
    }

    public static String a(Date date) {
        return b.get().format(date);
    }
}
