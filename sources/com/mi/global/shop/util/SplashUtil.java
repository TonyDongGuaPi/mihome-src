package com.mi.global.shop.util;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.text.TextUtils;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.newmodel.notice.NewNoticeData;
import com.mi.global.shop.util.Utils;
import com.mi.log.LogUtil;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class SplashUtil {

    /* renamed from: a  reason: collision with root package name */
    protected static final int f7107a = 10000;
    private static final String b = "SplashUtil";
    /* access modifiers changed from: private */
    public static final String c = (Environment.getExternalStorageDirectory() + "/com.mi.global.shop/");
    private static final String d = "splash.jpg";
    private static final String e = "startTime";
    private static final String f = "endTime";
    private static final String g = "img";
    private static final String h = "gif";
    /* access modifiers changed from: private */
    public static ISearchConfListener i;

    public interface ISearchConfListener {
        void a(boolean z);
    }

    public interface OnNoticeCallback {
        void a(NewNoticeData newNoticeData);
    }

    public static void a(ISearchConfListener iSearchConfListener) {
        i = iSearchConfListener;
    }

    /* access modifiers changed from: private */
    public static void b(Context context, JSONObject jSONObject) {
        if (jSONObject != null && context != null) {
            try {
                String optString = jSONObject.optString("url");
                long optLong = jSONObject.optLong("startTime");
                long optLong2 = jSONObject.optLong("endTime");
                int optInt = jSONObject.optInt("duration");
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                LogUtil.b(b, "url:" + optString + ",startTime:" + optLong + ",endTime:" + optLong2 + ",duration:" + optInt);
                if (!TextUtils.isEmpty(optString) && currentTimeMillis >= optLong && currentTimeMillis < optLong2) {
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("url", optString);
                    context.startActivity(intent);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                LogUtil.b(b, "ActivityConfig parse error" + e2.toString());
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(final android.content.Context r6, final java.lang.Runnable r7, final com.mi.global.shop.util.SplashUtil.OnNoticeCallback r8) {
        /*
            java.lang.String r0 = r6.getPackageName()
            java.lang.String r0 = com.mi.util.ApkUtils.c(r6, r0)
            r1 = 0
            java.util.zip.ZipFile r2 = new java.util.zip.ZipFile     // Catch:{ IOException -> 0x0032 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x0032 }
            java.lang.String r0 = "META-INF/md5.txt"
            java.util.zip.ZipEntry r0 = r2.getEntry(r0)     // Catch:{ IOException -> 0x0032 }
            if (r0 == 0) goto L_0x0030
            java.io.InputStream r0 = r2.getInputStream(r0)     // Catch:{ IOException -> 0x0032 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0032 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0032 }
            r3.<init>(r0)     // Catch:{ IOException -> 0x0032 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0032 }
            java.lang.String r0 = r2.readLine()     // Catch:{ IOException -> 0x0032 }
            java.io.PrintStream r2 = java.lang.System.out     // Catch:{ IOException -> 0x002e }
            r2.println(r0)     // Catch:{ IOException -> 0x002e }
            goto L_0x0037
        L_0x002e:
            r2 = move-exception
            goto L_0x0034
        L_0x0030:
            r0 = r1
            goto L_0x0037
        L_0x0032:
            r2 = move-exception
            r0 = r1
        L_0x0034:
            r2.printStackTrace()
        L_0x0037:
            java.lang.String r2 = com.mi.global.shop.util.ConnectionHelper.aC()
            android.net.Uri r2 = android.net.Uri.parse(r2)
            android.net.Uri$Builder r2 = r2.buildUpon()
            java.lang.String r3 = "version"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = ""
            r4.append(r5)
            int r5 = com.mi.util.Device.r
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r2.appendQueryParameter(r3, r4)
            java.lang.String r3 = "channel"
            java.lang.String r4 = com.mi.global.shop.util.ChannelUtil.getChannel(r6)
            r2.appendQueryParameter(r3, r4)
            java.lang.String r3 = "md5"
            r2.appendQueryParameter(r3, r0)
            java.lang.String r0 = "hotfixVersion"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "pref_hotfix_version"
            r5 = -1
            int r4 = com.mi.util.Utils.Preference.getIntPref(r6, r4, r5)
            r3.append(r4)
            java.lang.String r4 = ""
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.appendQueryParameter(r0, r3)
            boolean r0 = com.mi.global.shop.ShopApp.n()
            if (r0 == 0) goto L_0x009e
            com.mi.global.shop.util.SplashUtil$1 r0 = new com.mi.global.shop.util.SplashUtil$1
            r0.<init>(r6, r8, r7)
            com.mi.global.shop.request.SimpleProtobufRequest r6 = new com.mi.global.shop.request.SimpleProtobufRequest
            java.lang.String r7 = r2.toString()
            java.lang.Class<com.mi.global.shop.newmodel.sync.NewSyncResult> r8 = com.mi.global.shop.newmodel.sync.NewSyncResult.class
            r6.<init>(r7, r8, r0)
            goto L_0x00b5
        L_0x009e:
            com.mi.global.shop.model.SyncModel.response = r1
            com.mi.global.shop.request.MiJsonObjectRequest r8 = new com.mi.global.shop.request.MiJsonObjectRequest
            r0 = 0
            java.lang.String r1 = r2.toString()
            com.mi.global.shop.util.SplashUtil$2 r2 = new com.mi.global.shop.util.SplashUtil$2
            r2.<init>(r6, r7)
            com.mi.global.shop.util.SplashUtil$3 r6 = new com.mi.global.shop.util.SplashUtil$3
            r6.<init>()
            r8.<init>(r0, r1, r2, r6)
            r6 = r8
        L_0x00b5:
            java.lang.String r7 = "SplashUtil"
            r6.setTag(r7)
            com.android.volley.RequestQueue r7 = com.mi.util.RequestQueueUtil.a()
            if (r7 == 0) goto L_0x00c7
            com.android.volley.RequestQueue r7 = com.mi.util.RequestQueueUtil.a()
            r7.add(r6)
        L_0x00c7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.util.SplashUtil.a(android.content.Context, java.lang.Runnable, com.mi.global.shop.util.SplashUtil$OnNoticeCallback):void");
    }

    private static class LoadImageThread extends Thread {

        /* renamed from: a  reason: collision with root package name */
        private JSONObject f7110a;
        private String b;

        public LoadImageThread(JSONObject jSONObject) {
            this.f7110a = jSONObject;
            this.b = jSONObject.optString(SplashUtil.h);
            if (TextUtils.isEmpty(this.b)) {
                this.b = jSONObject.optString("img");
            }
        }

        private boolean a(byte[] bArr, String str, String str2) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdir();
            }
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(str + str2)));
                bufferedOutputStream.write(bArr);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                LogUtil.b(SplashUtil.b, "save splash.jpg success");
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        public void run() {
            byte[] a2 = SplashUtil.a(this.b);
            if (a2 != null && Environment.getExternalStorageState().equals("mounted") && a(a2, SplashUtil.c, SplashUtil.d)) {
                Utils.Preference.setStringPref(ShopApp.g(), "pref_key_splash_info", this.f7110a.toString());
            }
        }
    }

    public static byte[] a(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(10000);
            return a(httpURLConnection.getInputStream());
        } catch (Exception unused) {
            LogUtil.b(b, "failed to get splash.jpg");
            return null;
        }
    }

    public static byte[] a(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static void a() {
        Utils.Preference.removePref(ShopApp.g(), "pref_key_splash_info");
        try {
            File file = new File(c + d);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception unused) {
        }
    }
}
