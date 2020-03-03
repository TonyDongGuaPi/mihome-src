package com.xiaomi.miot.store.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.facebook.react.bridge.ReadableMap;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.miot.store.utils.entity.BitmapParams;
import com.xiaomi.miot.store.utils.entity.ResultBean;
import com.xiaomi.youpin.common.thread.AsyncTaskUtils;
import com.xiaomi.youpin.common.util.ConvertUtils;
import com.xiaomi.youpin.common.util.ImageUtils;
import com.xiaomi.youpin.yp_permission.SimplePermissionCallback;
import com.xiaomi.youpin.yp_permission.YouPinPermissionManager;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ScreenshotManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11415a = "ScreenshotManager";

    public interface Callback {
        void callback(ResultBean resultBean);
    }

    private ScreenshotManager() {
    }

    public static ScreenshotManager a() {
        return ScreenShotManagerHolder.f11422a;
    }

    private void a(final MyHandler myHandler, final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                View decorView = activity.getWindow().getDecorView();
                decorView.setDrawingCacheEnabled(true);
                decorView.buildDrawingCache();
                Bitmap drawingCache = decorView.getDrawingCache();
                Bitmap createBitmap = Bitmap.createBitmap(drawingCache.getWidth(), drawingCache.getHeight(), drawingCache.getConfig());
                new Canvas(createBitmap).drawBitmap(drawingCache, new Matrix(), new Paint());
                Message obtain = Message.obtain();
                obtain.obj = createBitmap;
                decorView.setDrawingCacheEnabled(false);
                myHandler.sendMessage(obtain);
            }
        });
    }

    public void a(final Activity activity, final ReadableMap readableMap, final Callback callback) {
        if (YouPinPermissionManager.a((Context) activity, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            c(activity, readableMap, callback);
        } else {
            YouPinPermissionManager.a(activity, "android.permission.WRITE_EXTERNAL_STORAGE", (SimplePermissionCallback) new SimplePermissionCallback() {
                public void a() {
                    ScreenshotManager.this.c(activity, readableMap, callback);
                }

                public void b() {
                    callback.callback(ScreenshotManager.this.c());
                }
            });
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"StaticFieldLeak"})
    public void c(final Activity activity, final ReadableMap readableMap, final Callback callback) {
        if (readableMap == null) {
            callback.callback(d());
            return;
        }
        MyHandler myHandler = new MyHandler();
        a(myHandler, activity);
        myHandler.a(new ICallback() {
            public void callback(Map map) {
                final Bitmap bitmap = (Bitmap) map.get("bitmap");
                final Context applicationContext = activity.getApplicationContext();
                final BitmapParams bitmapParams = new BitmapParams(readableMap);
                if (!bitmapParams.a()) {
                    bitmapParams.a(bitmap);
                }
                AsyncTaskUtils.a(new AsyncTask<Object, Object, ResultBean>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(ResultBean resultBean) {
                        callback.callback(resultBean);
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public ResultBean doInBackground(Object... objArr) {
                        return ScreenshotManager.this.a(bitmap, applicationContext, bitmapParams);
                    }
                }, new Object[0]);
            }
        });
    }

    @SuppressLint({"StaticFieldLeak"})
    public void b(Activity activity, ReadableMap readableMap, final Callback callback) {
        if (readableMap == null) {
            callback.callback(d());
            return;
        }
        final BitmapParams bitmapParams = new BitmapParams(readableMap);
        if (!TextUtils.isEmpty(bitmapParams.h()) || !TextUtils.isEmpty(bitmapParams.i())) {
            final Context applicationContext = activity.getApplicationContext();
            AsyncTaskUtils.a(new AsyncTask<Object, Object, ResultBean>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public ResultBean doInBackground(Object... objArr) {
                    return ScreenshotManager.this.a(applicationContext, bitmapParams);
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(ResultBean resultBean) {
                    callback.callback(resultBean);
                }
            }, new Object[0]);
            return;
        }
        callback.callback(a("both url and base64 are null"));
    }

    /* access modifiers changed from: private */
    public ResultBean a(Bitmap bitmap, Context context, BitmapParams bitmapParams) {
        if (!bitmapParams.a()) {
            bitmap = Bitmap.createBitmap(bitmap, bitmapParams.b(), bitmapParams.c(), bitmapParams.d(), bitmapParams.e());
        }
        String a2 = a(context, a("screenshot", bitmapParams.f()), bitmap, bitmapParams);
        if (a2 != null) {
            return a(ConvertUtils.b((float) bitmap.getWidth()), ConvertUtils.b((float) bitmap.getHeight()), bitmap.getRowBytes(), a2);
        }
        return f();
    }

    /* access modifiers changed from: private */
    public ResultBean a(Context context, BitmapParams bitmapParams) {
        if (TextUtils.isEmpty(bitmapParams.h())) {
            Bitmap e = ImageUtils.e(bitmapParams.i());
            if (e == null) {
                return a("can not decode bitmap from base64 code");
            }
            if (!bitmapParams.a()) {
                bitmapParams.a(e);
                e = Bitmap.createBitmap(e, bitmapParams.b(), bitmapParams.c(), bitmapParams.d(), bitmapParams.e());
                if (e == null) {
                    return e();
                }
            }
            String a2 = a(context, a("saveImg", bitmapParams.f()), e, bitmapParams);
            if (a2 != null) {
                return a(ConvertUtils.b((float) e.getWidth()), ConvertUtils.b((float) e.getHeight()), e.getRowBytes(), a2);
            }
            return f();
        }
        try {
            Response execute = new OkHttpClient().newCall(new Request.Builder().url(bitmapParams.h()).build()).execute();
            if (execute.isSuccessful()) {
                Bitmap decodeStream = BitmapFactory.decodeStream(execute.body().byteStream());
                if (!bitmapParams.a()) {
                    bitmapParams.a(decodeStream);
                    decodeStream = Bitmap.createBitmap(decodeStream, bitmapParams.b(), bitmapParams.c(), bitmapParams.d(), bitmapParams.e());
                    if (decodeStream == null) {
                        return e();
                    }
                }
                String a3 = a(context, a("saveImg", bitmapParams.f()), decodeStream, bitmapParams);
                if (a3 != null) {
                    return a(ConvertUtils.b((float) decodeStream.getWidth()), ConvertUtils.b((float) decodeStream.getHeight()), decodeStream.getRowBytes(), a3);
                }
                return f();
            }
            execute.body().close();
            return a("can not get bitmap from" + bitmapParams.h() + "\nerror code:" + execute.code());
        } catch (IOException e2) {
            e2.printStackTrace();
            return a(e2.toString());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0122 A[SYNTHETIC, Splitter:B:58:0x0122] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0156 A[SYNTHETIC, Splitter:B:68:0x0156] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0166  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x018b A[SYNTHETIC, Splitter:B:78:0x018b] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:52:0x00f4=Splitter:B:52:0x00f4, B:62:0x0128=Splitter:B:62:0x0128} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(android.content.Context r5, java.lang.String r6, android.graphics.Bitmap r7, com.xiaomi.miot.store.utils.entity.BitmapParams r8) {
        /*
            r4 = this;
            r0 = 0
            if (r7 == 0) goto L_0x0194
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            if (r1 == 0) goto L_0x000b
            goto L_0x0194
        L_0x000b:
            boolean r1 = r8.k()
            if (r1 == 0) goto L_0x0039
            java.io.File r1 = new java.io.File
            java.lang.String r2 = android.os.Environment.DIRECTORY_DCIM
            java.io.File r2 = android.os.Environment.getExternalStoragePublicDirectory(r2)
            java.lang.String r3 = "YouPin"
            r1.<init>(r2, r3)
            boolean r2 = r1.exists()
            if (r2 != 0) goto L_0x0027
            r1.mkdir()
        L_0x0027:
            java.io.File r2 = new java.io.File
            r2.<init>(r1, r6)
            boolean r6 = r2.exists()
            if (r6 == 0) goto L_0x0081
            boolean r6 = r2.delete()
            if (r6 != 0) goto L_0x0081
            return r0
        L_0x0039:
            java.lang.String r1 = r8.g()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x0051
            java.io.File r2 = new java.io.File
            java.io.File r1 = r5.getExternalCacheDir()
            java.lang.String r1 = r1.getAbsolutePath()
            r2.<init>(r1, r6)
            goto L_0x0081
        L_0x0051:
            java.io.File r1 = new java.io.File
            java.io.File r2 = r5.getExternalCacheDir()
            java.lang.String r2 = r2.getAbsolutePath()
            java.lang.String r3 = r8.g()
            r1.<init>(r2, r3)
            boolean r2 = r1.exists()
            if (r2 != 0) goto L_0x006f
            boolean r2 = r1.mkdir()
            if (r2 != 0) goto L_0x006f
            return r0
        L_0x006f:
            java.io.File r2 = new java.io.File
            r2.<init>(r1, r6)
            boolean r6 = r2.exists()
            if (r6 == 0) goto L_0x0081
            boolean r6 = r2.delete()
            if (r6 != 0) goto L_0x0081
            return r0
        L_0x0081:
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0126, IOException -> 0x00f2, all -> 0x00ef }
            r6.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0126, IOException -> 0x00f2, all -> 0x00ef }
            java.lang.String r1 = r8.f()     // Catch:{ FileNotFoundException -> 0x00ed, IOException -> 0x00eb }
            java.lang.String r3 = "jpeg"
            boolean r1 = r1.equals(r3)     // Catch:{ FileNotFoundException -> 0x00ed, IOException -> 0x00eb }
            if (r1 != 0) goto L_0x00a9
            java.lang.String r1 = r8.f()     // Catch:{ FileNotFoundException -> 0x00ed, IOException -> 0x00eb }
            java.lang.String r3 = "jpg"
            boolean r1 = r1.equals(r3)     // Catch:{ FileNotFoundException -> 0x00ed, IOException -> 0x00eb }
            if (r1 == 0) goto L_0x009f
            goto L_0x00a9
        L_0x009f:
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ FileNotFoundException -> 0x00ed, IOException -> 0x00eb }
            int r3 = r8.j()     // Catch:{ FileNotFoundException -> 0x00ed, IOException -> 0x00eb }
            r7.compress(r1, r3, r6)     // Catch:{ FileNotFoundException -> 0x00ed, IOException -> 0x00eb }
            goto L_0x00b2
        L_0x00a9:
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ FileNotFoundException -> 0x00ed, IOException -> 0x00eb }
            int r3 = r8.j()     // Catch:{ FileNotFoundException -> 0x00ed, IOException -> 0x00eb }
            r7.compress(r1, r3, r6)     // Catch:{ FileNotFoundException -> 0x00ed, IOException -> 0x00eb }
        L_0x00b2:
            r6.flush()     // Catch:{ FileNotFoundException -> 0x00ed, IOException -> 0x00eb }
            java.lang.String r7 = r2.getAbsolutePath()     // Catch:{ FileNotFoundException -> 0x00ed, IOException -> 0x00eb }
            boolean r8 = r8.k()
            if (r8 == 0) goto L_0x00e2
            android.content.Intent r8 = new android.content.Intent
            java.lang.String r0 = "android.intent.action.MEDIA_SCANNER_SCAN_FILE"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "file://"
            r1.append(r3)
            java.lang.String r2 = r2.getAbsolutePath()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.net.Uri r1 = android.net.Uri.parse(r1)
            r8.<init>(r0, r1)
            r5.sendBroadcast(r8)
        L_0x00e2:
            r6.close()     // Catch:{ IOException -> 0x00e6 }
            goto L_0x00ea
        L_0x00e6:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00ea:
            return r7
        L_0x00eb:
            r7 = move-exception
            goto L_0x00f4
        L_0x00ed:
            r7 = move-exception
            goto L_0x0128
        L_0x00ef:
            r7 = move-exception
            r6 = r0
            goto L_0x0160
        L_0x00f2:
            r7 = move-exception
            r6 = r0
        L_0x00f4:
            r7.printStackTrace()     // Catch:{ all -> 0x015f }
            boolean r7 = r8.k()
            if (r7 == 0) goto L_0x0120
            android.content.Intent r7 = new android.content.Intent
            java.lang.String r8 = "android.intent.action.MEDIA_SCANNER_SCAN_FILE"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "file://"
            r1.append(r3)
            java.lang.String r2 = r2.getAbsolutePath()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.net.Uri r1 = android.net.Uri.parse(r1)
            r7.<init>(r8, r1)
            r5.sendBroadcast(r7)
        L_0x0120:
            if (r6 == 0) goto L_0x015e
            r6.close()     // Catch:{ IOException -> 0x015a }
            goto L_0x015e
        L_0x0126:
            r7 = move-exception
            r6 = r0
        L_0x0128:
            r7.printStackTrace()     // Catch:{ all -> 0x015f }
            boolean r7 = r8.k()
            if (r7 == 0) goto L_0x0154
            android.content.Intent r7 = new android.content.Intent
            java.lang.String r8 = "android.intent.action.MEDIA_SCANNER_SCAN_FILE"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "file://"
            r1.append(r3)
            java.lang.String r2 = r2.getAbsolutePath()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.net.Uri r1 = android.net.Uri.parse(r1)
            r7.<init>(r8, r1)
            r5.sendBroadcast(r7)
        L_0x0154:
            if (r6 == 0) goto L_0x015e
            r6.close()     // Catch:{ IOException -> 0x015a }
            goto L_0x015e
        L_0x015a:
            r5 = move-exception
            r5.printStackTrace()
        L_0x015e:
            return r0
        L_0x015f:
            r7 = move-exception
        L_0x0160:
            boolean r8 = r8.k()
            if (r8 == 0) goto L_0x0189
            android.content.Intent r8 = new android.content.Intent
            java.lang.String r0 = "android.intent.action.MEDIA_SCANNER_SCAN_FILE"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "file://"
            r1.append(r3)
            java.lang.String r2 = r2.getAbsolutePath()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.net.Uri r1 = android.net.Uri.parse(r1)
            r8.<init>(r0, r1)
            r5.sendBroadcast(r8)
        L_0x0189:
            if (r6 == 0) goto L_0x0193
            r6.close()     // Catch:{ IOException -> 0x018f }
            goto L_0x0193
        L_0x018f:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0193:
            throw r7
        L_0x0194:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miot.store.utils.ScreenshotManager.a(android.content.Context, java.lang.String, android.graphics.Bitmap, com.xiaomi.miot.store.utils.entity.BitmapParams):java.lang.String");
    }

    @SuppressLint({"DefaultLocale"})
    private String a(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date date = new Date();
        return str + simpleDateFormat.format(date) + "." + str2;
    }

    private ResultBean a(int i, int i2, int i3, String str) {
        ResultBean resultBean = new ResultBean();
        resultBean.a(0);
        resultBean.b(i);
        resultBean.c(i2);
        resultBean.d(i3 * i2);
        resultBean.b(str);
        return resultBean;
    }

    /* access modifiers changed from: private */
    public ResultBean c() {
        return a("permission denied");
    }

    private ResultBean d() {
        return a("map is null");
    }

    private ResultBean e() {
        return a("Bitmap.createBitmap() error");
    }

    public ResultBean b() {
        return a("can not get current activity");
    }

    private ResultBean f() {
        return a("bitmap can not be saved to file");
    }

    private ResultBean a(String str) {
        ResultBean resultBean = new ResultBean();
        resultBean.a(1);
        resultBean.a(str);
        return resultBean;
    }

    private static class MyHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        public ICallback f11421a;

        private MyHandler() {
        }

        public void a(ICallback iCallback) {
            this.f11421a = iCallback;
        }

        public void handleMessage(Message message) {
            if (this.f11421a != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("bitmap", (Bitmap) message.obj);
                this.f11421a.callback(hashMap);
            }
        }
    }

    private static class ScreenShotManagerHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static ScreenshotManager f11422a = new ScreenshotManager();

        private ScreenShotManagerHolder() {
        }
    }
}
