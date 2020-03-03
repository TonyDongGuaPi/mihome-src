package com.xiaomi.youpin.common.util;

public class FileFitUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23250a = "FileFitUtils";

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a3 A[SYNTHETIC, Splitter:B:36:0x00a3] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r3, android.graphics.Bitmap r4, java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x00f5
            if (r3 == 0) goto L_0x00f5
            android.content.ContentResolver r1 = r3.getContentResolver()
            if (r1 != 0) goto L_0x000d
            goto L_0x00f5
        L_0x000d:
            boolean r1 = android.text.TextUtils.isEmpty(r5)
            if (r1 == 0) goto L_0x0028
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r1 = "image_"
            r5.append(r1)
            long r1 = java.lang.System.currentTimeMillis()
            r5.append(r1)
            java.lang.String r5 = r5.toString()
        L_0x0028:
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            if (r1 == 0) goto L_0x0030
            java.lang.String r7 = "DCIM"
        L_0x0030:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 29
            if (r1 < r2) goto L_0x00ec
            android.content.ContentValues r1 = new android.content.ContentValues
            r1.<init>()
            java.lang.String r2 = "description"
            r1.put(r2, r6)
            java.lang.String r6 = "mime_type"
            java.lang.String r2 = "image/jpeg"
            r1.put(r6, r2)
            java.lang.String r6 = "title"
            r1.put(r6, r5)
            java.lang.String r5 = "relative_path"
            r1.put(r5, r7)
            android.net.Uri r5 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            android.content.ContentResolver r3 = r3.getContentResolver()
            android.net.Uri r5 = r3.insert(r5, r1)
            if (r5 == 0) goto L_0x006a
            java.io.OutputStream r3 = r3.openOutputStream(r5)     // Catch:{ IOException -> 0x0066, all -> 0x0062 }
            goto L_0x006b
        L_0x0062:
            r3 = move-exception
            r4 = r3
            r3 = r0
            goto L_0x00a1
        L_0x0066:
            r3 = move-exception
            r4 = r3
            r3 = r0
            goto L_0x0078
        L_0x006a:
            r3 = r0
        L_0x006b:
            if (r3 == 0) goto L_0x00c3
            android.graphics.Bitmap$CompressFormat r6 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ IOException -> 0x0077 }
            r7 = 90
            r4.compress(r6, r7, r3)     // Catch:{ IOException -> 0x0077 }
            goto L_0x00c3
        L_0x0075:
            r4 = move-exception
            goto L_0x00a1
        L_0x0077:
            r4 = move-exception
        L_0x0078:
            java.lang.String r6 = "FileFitUtils"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0075 }
            r7.<init>()     // Catch:{ all -> 0x0075 }
            java.lang.String r1 = "fail: "
            r7.append(r1)     // Catch:{ all -> 0x0075 }
            java.lang.Throwable r4 = r4.getCause()     // Catch:{ all -> 0x0075 }
            r7.append(r4)     // Catch:{ all -> 0x0075 }
            java.lang.String r4 = r7.toString()     // Catch:{ all -> 0x0075 }
            android.util.Log.d(r6, r4)     // Catch:{ all -> 0x0075 }
            if (r3 == 0) goto L_0x00e4
            r3.close()     // Catch:{ IOException -> 0x0098 }
            goto L_0x00e4
        L_0x0098:
            r3 = move-exception
            java.lang.String r4 = "FileFitUtils"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            goto L_0x00d1
        L_0x00a1:
            if (r3 == 0) goto L_0x00c2
            r3.close()     // Catch:{ IOException -> 0x00a7 }
            goto L_0x00c2
        L_0x00a7:
            r3 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "fail in close: "
            r5.append(r6)
            java.lang.Throwable r3 = r3.getCause()
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            java.lang.String r5 = "FileFitUtils"
            android.util.Log.d(r5, r3)
        L_0x00c2:
            throw r4
        L_0x00c3:
            if (r3 == 0) goto L_0x00e4
            r3.close()     // Catch:{ IOException -> 0x00c9 }
            goto L_0x00e4
        L_0x00c9:
            r3 = move-exception
            java.lang.String r4 = "FileFitUtils"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
        L_0x00d1:
            java.lang.String r7 = "fail in close: "
            r6.append(r7)
            java.lang.Throwable r3 = r3.getCause()
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            android.util.Log.d(r4, r3)
        L_0x00e4:
            if (r5 != 0) goto L_0x00e7
            goto L_0x00eb
        L_0x00e7:
            java.lang.String r0 = r5.toString()
        L_0x00eb:
            return r0
        L_0x00ec:
            android.content.ContentResolver r3 = r3.getContentResolver()
            java.lang.String r3 = android.provider.MediaStore.Images.Media.insertImage(r3, r4, r5, r6)
            return r3
        L_0x00f5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.FileFitUtils.a(android.content.Context, android.graphics.Bitmap, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }
}
