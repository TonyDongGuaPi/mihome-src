package com.xiaomi.passport.ui.internal.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.xiaomi.accountsdk.utils.AccountLog;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BitmapUtils {
    private static final String TAG = "BitmapUtils";

    public static Bitmap getImageBitmap(Context context, String str) {
        File fileStreamPath = context.getFileStreamPath(str);
        if (!fileStreamPath.isFile() || !fileStreamPath.exists()) {
            return null;
        }
        return BitmapFactory.decodeFile(fileStreamPath.getAbsolutePath());
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:12|13|14|15|16|17) */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:5|6|7|8|9|11) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x002d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0021 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File saveAsFile(android.content.Context r5, java.io.InputStream r6, java.lang.String r7) throws java.io.IOException {
        /*
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream
            r0.<init>(r6)
            java.io.BufferedOutputStream r6 = new java.io.BufferedOutputStream
            r1 = 0
            java.io.FileOutputStream r2 = r5.openFileOutput(r7, r1)
            r6.<init>(r2)
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]
        L_0x0013:
            int r3 = r0.read(r2)     // Catch:{ all -> 0x0029 }
            r4 = -1
            if (r3 == r4) goto L_0x001e
            r6.write(r2, r1, r3)     // Catch:{ all -> 0x0029 }
            goto L_0x0013
        L_0x001e:
            r6.flush()     // Catch:{ IOException -> 0x0021 }
        L_0x0021:
            r6.close()     // Catch:{ IOException -> 0x0024 }
        L_0x0024:
            java.io.File r5 = r5.getFileStreamPath(r7)
            return r5
        L_0x0029:
            r5 = move-exception
            r6.flush()     // Catch:{ IOException -> 0x002d }
        L_0x002d:
            r6.close()     // Catch:{ IOException -> 0x0030 }
        L_0x0030:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.internal.util.BitmapUtils.saveAsFile(android.content.Context, java.io.InputStream, java.lang.String):java.io.File");
    }

    public static Bitmap getUserAvatarByAbsPath(Context context, String str) {
        FileInputStream openFileInput;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            openFileInput = context.openFileInput(str);
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = openFileInput.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            closeQuietly((OutputStream) byteArrayOutputStream);
            closeQuietly((InputStream) openFileInput);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (byteArray != null) {
                return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            }
            return null;
        } catch (FileNotFoundException unused) {
            AccountLog.e(TAG, "File is not found");
            return null;
        } catch (IOException unused2) {
            AccountLog.e(TAG, "Read data error");
            return null;
        } catch (Throwable th) {
            closeQuietly((OutputStream) byteArrayOutputStream);
            closeQuietly((InputStream) openFileInput);
            throw th;
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:30:0x0060=Splitter:B:30:0x0060, B:24:0x0053=Splitter:B:24:0x0053} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String saveUserAvatarByUrl(android.content.Context r3, java.lang.String r4, java.lang.String r5) {
        /*
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ Exception -> 0x0066 }
            r1.<init>(r4)     // Catch:{ Exception -> 0x0066 }
            java.io.InputStream r1 = r1.openStream()     // Catch:{ Exception -> 0x0066 }
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r1)     // Catch:{ Exception -> 0x0066 }
            if (r1 != 0) goto L_0x0011
            return r0
        L_0x0011:
            android.net.Uri r4 = android.net.Uri.parse(r4)     // Catch:{ Exception -> 0x0066 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0066 }
            r2.<init>()     // Catch:{ Exception -> 0x0066 }
            r2.append(r5)     // Catch:{ Exception -> 0x0066 }
            java.lang.String r5 = "_"
            r2.append(r5)     // Catch:{ Exception -> 0x0066 }
            java.lang.String r4 = r4.getLastPathSegment()     // Catch:{ Exception -> 0x0066 }
            r2.append(r4)     // Catch:{ Exception -> 0x0066 }
            java.lang.String r4 = r2.toString()     // Catch:{ Exception -> 0x0066 }
            r5 = 0
            java.io.FileOutputStream r3 = r3.openFileOutput(r4, r5)     // Catch:{ FileNotFoundException -> 0x0057, IOException -> 0x004b }
            android.graphics.Bitmap$CompressFormat r5 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ FileNotFoundException -> 0x0046, IOException -> 0x0043, all -> 0x0040 }
            r0 = 100
            r1.compress(r5, r0, r3)     // Catch:{ FileNotFoundException -> 0x0046, IOException -> 0x0043, all -> 0x0040 }
            r3.flush()     // Catch:{ FileNotFoundException -> 0x0046, IOException -> 0x0043, all -> 0x0040 }
            closeQuietly((java.io.OutputStream) r3)     // Catch:{ Exception -> 0x0064 }
            goto L_0x006f
        L_0x0040:
            r5 = move-exception
            r0 = r3
            goto L_0x0060
        L_0x0043:
            r5 = move-exception
            r0 = r3
            goto L_0x004c
        L_0x0046:
            r5 = move-exception
            r0 = r3
            goto L_0x0058
        L_0x0049:
            r5 = move-exception
            goto L_0x0060
        L_0x004b:
            r5 = move-exception
        L_0x004c:
            java.lang.String r3 = "BitmapUtils"
            java.lang.String r1 = "Save file exception"
            com.xiaomi.accountsdk.utils.AccountLog.e(r3, r1, r5)     // Catch:{ all -> 0x0049 }
        L_0x0053:
            closeQuietly((java.io.OutputStream) r0)     // Catch:{ Exception -> 0x0064 }
            goto L_0x006f
        L_0x0057:
            r5 = move-exception
        L_0x0058:
            java.lang.String r3 = "BitmapUtils"
            java.lang.String r1 = "File is not found"
            com.xiaomi.accountsdk.utils.AccountLog.e(r3, r1, r5)     // Catch:{ all -> 0x0049 }
            goto L_0x0053
        L_0x0060:
            closeQuietly((java.io.OutputStream) r0)     // Catch:{ Exception -> 0x0064 }
            throw r5     // Catch:{ Exception -> 0x0064 }
        L_0x0064:
            r3 = move-exception
            goto L_0x0068
        L_0x0066:
            r3 = move-exception
            r4 = r0
        L_0x0068:
            java.lang.String r5 = "BitmapUtils"
            java.lang.String r0 = "Get data exception"
            com.xiaomi.accountsdk.utils.AccountLog.e(r5, r0, r3)
        L_0x006f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.internal.util.BitmapUtils.saveUserAvatarByUrl(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
    }

    private static void closeQuietly(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:1|2|3|4|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void closeQuietly(java.io.OutputStream r0) {
        /*
            if (r0 == 0) goto L_0x0008
            r0.flush()     // Catch:{ IOException -> 0x0005 }
        L_0x0005:
            r0.close()     // Catch:{ IOException -> 0x0008 }
        L_0x0008:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.internal.util.BitmapUtils.closeQuietly(java.io.OutputStream):void");
    }
}
