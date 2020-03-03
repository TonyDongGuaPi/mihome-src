package com.xiaomi.youpin.share;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.sina.weibo.sdk.WbSdk;
import com.xiaomi.youpin.business_common.ScreenshotUtils;
import com.xiaomi.youpin.common.base.AsyncCallback;
import com.xiaomi.youpin.common.base.ExceptionError;
import com.xiaomi.youpin.common.base.YouPinError;
import com.xiaomi.youpin.common.thread.AsyncTaskUtils;
import com.xiaomi.youpin.common.util.FileFitUtils;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import com.xiaomi.zxing.WriterException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ShareUtil {

    /* renamed from: a  reason: collision with root package name */
    public static volatile Executor f23671a = Executors.newCachedThreadPool();

    public static boolean a() {
        return YouPinShareApi.a().b().isWXAppInstalled();
    }

    public static boolean a(Context context) {
        return WbSdk.isWbInstall(context);
    }

    private static boolean a(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static void a(String str, final AsyncCallback<Bitmap, YouPinError> asyncCallback) {
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).build(), (Object) null).subscribe(new BaseBitmapDataSubscriber() {
            public void onNewResultImpl(@Nullable Bitmap bitmap) {
                asyncCallback.a(bitmap);
            }

            public void onFailureImpl(DataSource dataSource) {
                asyncCallback.a(new YouPinError(-1, ""));
            }
        }, f23671a);
    }

    public static void b(String str, final AsyncCallback<Bitmap, YouPinError> asyncCallback) {
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).build(), (Object) null).subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
            /* access modifiers changed from: protected */
            public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                if (dataSource.isFinished()) {
                    CloseableReference result = dataSource.getResult();
                    Bitmap bitmap = null;
                    if (result != null && (result.get() instanceof CloseableBitmap)) {
                        bitmap = ((CloseableBitmap) result.get()).getUnderlyingBitmap();
                    }
                    if (bitmap == null) {
                        asyncCallback.b(new YouPinError(-1, ""));
                    } else {
                        asyncCallback.b(bitmap);
                    }
                }
            }

            public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                asyncCallback.b(new YouPinError(-1, ""));
            }
        }, f23671a);
    }

    @SuppressLint({"StaticFieldLeak"})
    public static void a(final String str, final int i, final int i2, final AsyncCallback<Bitmap, ExceptionError> asyncCallback) {
        AsyncTaskUtils.a(new AsyncTask<Object, Object, Pair<Bitmap, ExceptionError>>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Pair<Bitmap, ExceptionError> doInBackground(Object... objArr) {
                WriterException writerException;
                Bitmap bitmap;
                try {
                    bitmap = ScreenshotUtils.a(str, i, i2);
                    writerException = null;
                } catch (WriterException e) {
                    e.printStackTrace();
                    writerException = e;
                    bitmap = null;
                }
                if (bitmap != null) {
                    return new Pair<>(bitmap, (Object) null);
                }
                ExceptionError exceptionError = new ExceptionError(-1, "");
                exceptionError.f23224a = writerException;
                return new Pair<>((Object) null, exceptionError);
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Pair<Bitmap, ExceptionError> pair) {
                Bitmap bitmap = (Bitmap) pair.first;
                ExceptionError exceptionError = (ExceptionError) pair.second;
                if (bitmap == null) {
                    asyncCallback.b(exceptionError);
                } else {
                    asyncCallback.b(bitmap);
                }
            }
        }, new Object[0]);
    }

    @SuppressLint({"StaticFieldLeak"})
    public static void a(String str, int i, int i2, int i3, AsyncCallback<Bitmap, ExceptionError> asyncCallback) {
        if (!TextUtils.isEmpty(str)) {
            final String str2 = str;
            final int i4 = i;
            final int i5 = i2;
            final int i6 = i3;
            final AsyncCallback<Bitmap, ExceptionError> asyncCallback2 = asyncCallback;
            AsyncTaskUtils.a(new AsyncTask<Object, Object, Pair<Bitmap, ExceptionError>>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Pair<Bitmap, ExceptionError> doInBackground(Object... objArr) {
                    WriterException writerException;
                    Bitmap bitmap;
                    try {
                        bitmap = ScreenshotUtils.a(str2, i4, i5, i6);
                        writerException = null;
                    } catch (WriterException e2) {
                        e2.printStackTrace();
                        writerException = e2;
                        bitmap = null;
                    }
                    if (bitmap != null) {
                        return new Pair<>(bitmap, (Object) null);
                    }
                    ExceptionError exceptionError = new ExceptionError(-1, "");
                    exceptionError.f23224a = writerException;
                    return new Pair<>((Object) null, exceptionError);
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(Pair<Bitmap, ExceptionError> pair) {
                    Bitmap bitmap = (Bitmap) pair.first;
                    ExceptionError exceptionError = (ExceptionError) pair.second;
                    if (bitmap == null) {
                        asyncCallback2.b(exceptionError);
                    } else {
                        asyncCallback2.b(bitmap);
                    }
                }
            }, new Object[0]);
        }
    }

    public static String a(Context context, Bitmap bitmap) {
        return FileFitUtils.a(context, bitmap, "youpin_poster", "", "");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        r9 = r8.getColumnIndex(com.xiaomi.smarthome.download.Downloads._DATA);
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.net.Uri a(android.content.Context r8, android.net.Uri r9) {
        /*
            r0 = 0
            if (r9 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = r9.getScheme()
            if (r1 != 0) goto L_0x000f
            java.lang.String r8 = r9.getPath()
            goto L_0x0055
        L_0x000f:
            java.lang.String r2 = "file"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x001c
            java.lang.String r8 = r9.getPath()
            goto L_0x0055
        L_0x001c:
            java.lang.String r2 = "content"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0054
            android.content.ContentResolver r2 = r8.getContentResolver()
            r8 = 1
            java.lang.String[] r4 = new java.lang.String[r8]
            r8 = 0
            java.lang.String r1 = "_data"
            r4[r8] = r1
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r9
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)
            if (r8 == 0) goto L_0x0054
            boolean r9 = r8.moveToFirst()
            if (r9 == 0) goto L_0x004e
            java.lang.String r9 = "_data"
            int r9 = r8.getColumnIndex(r9)
            r1 = -1
            if (r9 <= r1) goto L_0x004e
            java.lang.String r9 = r8.getString(r9)
            goto L_0x004f
        L_0x004e:
            r9 = r0
        L_0x004f:
            r8.close()
            r8 = r9
            goto L_0x0055
        L_0x0054:
            r8 = r0
        L_0x0055:
            boolean r9 = android.text.TextUtils.isEmpty(r8)
            if (r9 != 0) goto L_0x0060
            android.net.Uri r8 = android.net.Uri.parse(r8)
            return r8
        L_0x0060:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.share.ShareUtil.a(android.content.Context, android.net.Uri):android.net.Uri");
    }

    public static String a(int i) {
        String valueOf = String.valueOf(((float) i) / 100.0f);
        return !valueOf.contains(".") ? valueOf : valueOf.replaceAll("0*$", "").replaceAll("\\.$", "");
    }
}
