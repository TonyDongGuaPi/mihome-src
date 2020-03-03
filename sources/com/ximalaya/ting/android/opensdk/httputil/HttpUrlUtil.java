package com.ximalaya.ting.android.opensdk.httputil;

import android.support.annotation.NonNull;
import com.ximalaya.ting.android.opensdk.httputil.util.freeflow.FreeFlowServiceUtil;
import com.ximalaya.ting.android.opensdk.util.Logger;
import com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class HttpUrlUtil {

    /* renamed from: a  reason: collision with root package name */
    public static Config f1992a;

    public static HttpURLConnection a(String str, Config config, String str2, FreeFlowServiceUtil.ISetHttpUrlConnectAttribute iSetHttpUrlConnectAttribute) throws IOException {
        return FreeFlowServiceUtil.a(config, str, str2, iSetHttpUrlConnectAttribute);
    }

    private static void a(HttpURLConnection httpURLConnection) {
        Map requestProperties = httpURLConnection.getRequestProperties();
        String str = "OpenSDK.class header头 :";
        if (requestProperties.containsKey("Authorization")) {
            str = str + "Authorization:" + requestProperties.get("Authorization") + "   ";
        }
        if (requestProperties.containsKey("spid")) {
            str = str + "spid:" + requestProperties.get("spid") + "   ";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String str2 = simpleDateFormat.format(new Date(System.currentTimeMillis())) + "     " + (str + "是否使用了代理 " + httpURLConnection.usingProxy());
        Logger.a(str2);
        Logger.a((Object) str2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0072 A[SYNTHETIC, Splitter:B:33:0x0072] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x007d A[SYNTHETIC, Splitter:B:39:0x007d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
            r0 = -1
            r1 = 0
            com.ximalaya.ting.android.opensdk.httputil.Config r2 = f1992a     // Catch:{ IOException -> 0x006c }
            java.lang.String r3 = "GET"
            java.net.HttpURLConnection r7 = a(r7, r2, r3, r1)     // Catch:{ IOException -> 0x006c }
            r7.connect()     // Catch:{ IOException -> 0x006c }
            int r2 = r7.getResponseCode()     // Catch:{ IOException -> 0x006c }
            java.lang.String r3 = "Content-Length"
            java.lang.String r3 = r7.getHeaderField(r3)     // Catch:{ IOException -> 0x006c }
            r4 = 200(0xc8, float:2.8E-43)
            if (r2 != r4) goto L_0x0069
            boolean r2 = android.text.TextUtils.isEmpty(r3)     // Catch:{ IOException -> 0x006c }
            if (r2 != 0) goto L_0x0069
            long r2 = java.lang.Long.parseLong(r3)     // Catch:{ IOException -> 0x006c }
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 > 0) goto L_0x002c
            goto L_0x0069
        L_0x002c:
            java.io.InputStream r7 = r7.getInputStream()     // Catch:{ IOException -> 0x006c }
            r2 = 8192(0x2000, float:1.14794E-41)
            byte[] r2 = new byte[r2]     // Catch:{ IOException -> 0x006c }
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x006c }
            r3.<init>(r8)     // Catch:{ IOException -> 0x006c }
            boolean r8 = r3.exists()     // Catch:{ IOException -> 0x006c }
            if (r8 != 0) goto L_0x0042
            r3.mkdirs()     // Catch:{ IOException -> 0x006c }
        L_0x0042:
            java.io.File r8 = new java.io.File     // Catch:{ IOException -> 0x006c }
            r8.<init>(r3, r9)     // Catch:{ IOException -> 0x006c }
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x006c }
            r3 = 0
            r9.<init>(r8, r3)     // Catch:{ IOException -> 0x006c }
        L_0x004d:
            int r8 = r7.read(r2)     // Catch:{ IOException -> 0x0066, all -> 0x0063 }
            if (r8 <= 0) goto L_0x0057
            r9.write(r2, r3, r8)     // Catch:{ IOException -> 0x0066, all -> 0x0063 }
            goto L_0x004d
        L_0x0057:
            r9.flush()     // Catch:{ IOException -> 0x0066, all -> 0x0063 }
            r9.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x0062
        L_0x005e:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0062:
            return r3
        L_0x0063:
            r7 = move-exception
            r1 = r9
            goto L_0x007b
        L_0x0066:
            r7 = move-exception
            r1 = r9
            goto L_0x006d
        L_0x0069:
            return r0
        L_0x006a:
            r7 = move-exception
            goto L_0x007b
        L_0x006c:
            r7 = move-exception
        L_0x006d:
            r7.printStackTrace()     // Catch:{ all -> 0x006a }
            if (r1 == 0) goto L_0x007a
            r1.close()     // Catch:{ IOException -> 0x0076 }
            goto L_0x007a
        L_0x0076:
            r7 = move-exception
            r7.printStackTrace()
        L_0x007a:
            return r0
        L_0x007b:
            if (r1 == 0) goto L_0x0085
            r1.close()     // Catch:{ IOException -> 0x0081 }
            goto L_0x0085
        L_0x0081:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0085:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.httputil.HttpUrlUtil.a(java.lang.String, java.lang.String, java.lang.String):int");
    }

    public static int a(String str, String str2) {
        try {
            final File file = new File(str2);
            if (!file.exists() || file.isDirectory()) {
                return -2;
            }
            if (!file.canRead()) {
                return -2;
            }
            try {
                OutputStream outputStream = a(str, f1992a, "POST", new FreeFlowServiceUtil.ISetHttpUrlConnectAttribute() {
                    public void a(@NonNull HttpURLConnection httpURLConnection) {
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setChunkedStreamingMode(134217728);
                        httpURLConnection.setRequestProperty("Charset", "UTF-8");
                        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;file=" + file.getName());
                        httpURLConnection.setRequestProperty("filename", file.getName());
                        httpURLConnection.setRequestProperty("Content-Length", "" + file.length());
                    }
                }).getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read > 0) {
                        outputStream.write(bArr, 0, read);
                    } else {
                        outputStream.flush();
                        fileInputStream.close();
                        outputStream.close();
                        return -1;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            } catch (Exception e2) {
                e2.printStackTrace();
                return -1;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            return -1;
        }
    }

    public static void a(CdnCollectDataForPlay cdnCollectDataForPlay) {
        try {
            Class e = BaseCall.e();
            if (e != null) {
                e.getDeclaredMethod("statDownLoadCDN", new Class[]{CdnCollectDataForPlay.class}).invoke(e, new Object[]{cdnCollectDataForPlay});
            }
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }
    }
}
