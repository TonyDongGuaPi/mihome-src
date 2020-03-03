package com.reactnative.camera.tasks;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.media.ExifInterface;
import android.util.Base64;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.reactnative.camera.RNCameraViewHelper;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.viewshot.ViewShot;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ResolveTakenPictureAsyncTask extends AsyncTask<Void, Void, WritableMap> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8681a = "E_TAKING_PICTURE_FAILED";
    private Promise b;
    private byte[] c;
    private ReadableMap d;
    private File e;
    private Bitmap f;

    private int a(int i) {
        if (i == 3) {
            return 180;
        }
        if (i != 6) {
            return i != 8 ? 0 : 270;
        }
        return 90;
    }

    public ResolveTakenPictureAsyncTask(byte[] bArr, Promise promise, ReadableMap readableMap) {
        this.b = promise;
        this.d = readableMap;
        this.c = bArr;
    }

    public ResolveTakenPictureAsyncTask(byte[] bArr, Promise promise, ReadableMap readableMap, File file) {
        this.b = promise;
        this.d = readableMap;
        this.c = bArr;
        this.e = file;
    }

    private int a() {
        return (int) (this.d.getDouble(Constants.Name.QUALITY) * 100.0d);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public WritableMap doInBackground(Void... voidArr) {
        ByteArrayInputStream byteArrayInputStream;
        WritableMap createMap = Arguments.createMap();
        if (this.f == null) {
            this.f = BitmapFactory.decodeByteArray(this.c, 0, this.c.length);
            byteArrayInputStream = new ByteArrayInputStream(this.c);
        } else {
            byteArrayInputStream = null;
        }
        if (byteArrayInputStream != null) {
            try {
                ExifInterface exifInterface = new ExifInterface((InputStream) byteArrayInputStream);
                int attributeInt = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
                if (this.d.hasKey("width")) {
                    this.f = b(this.f, this.d.getInt("width"));
                }
                if (this.d.hasKey("fixOrientation") && this.d.getBoolean("fixOrientation") && attributeInt != 0) {
                    this.f = a(this.f, a(attributeInt));
                }
                if (this.d.hasKey("mirrorImage") && this.d.getBoolean("mirrorImage")) {
                    this.f = a(this.f);
                }
                if (this.d.hasKey("exif") && this.d.getBoolean("exif")) {
                    createMap.putMap("exif", RNCameraViewHelper.a(exifInterface));
                }
            } catch (Resources.NotFoundException e2) {
                this.b.reject(f8681a, "Documents directory of the app could not be found.", (Throwable) e2);
                e2.printStackTrace();
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                return null;
            } catch (IOException e3) {
                this.b.reject(f8681a, "An unknown I/O exception has occurred.", (Throwable) e3);
                e3.printStackTrace();
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return null;
            } catch (Throwable th) {
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        }
        createMap.putInt("width", this.f.getWidth());
        createMap.putInt("height", this.f.getHeight());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.f.compress(Bitmap.CompressFormat.JPEG, a(), byteArrayOutputStream);
        createMap.putString("uri", Uri.fromFile(new File(a(byteArrayOutputStream))).toString());
        if (this.d.hasKey(ViewShot.Results.BASE_64) && this.d.getBoolean(ViewShot.Results.BASE_64)) {
            createMap.putString(ViewShot.Results.BASE_64, Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
        }
        byteArrayOutputStream.close();
        if (byteArrayInputStream != null) {
            byteArrayInputStream.close();
            byteArrayInputStream = null;
        }
        if (byteArrayInputStream != null) {
            try {
                byteArrayInputStream.close();
            } catch (IOException e6) {
                e6.printStackTrace();
            }
        }
        return createMap;
    }

    private Bitmap a(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private Bitmap b(Bitmap bitmap, int i) {
        return Bitmap.createScaledBitmap(bitmap, i, (int) (((float) bitmap.getHeight()) * (((float) i) / ((float) bitmap.getWidth()))), true);
    }

    private Bitmap a(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        r4 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x001f A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0001] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002a A[SYNTHETIC, Splitter:B:21:0x002a] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x002f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0035 A[SYNTHETIC, Splitter:B:29:0x0035] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.io.ByteArrayOutputStream r4) throws java.io.IOException {
        /*
            r3 = this;
            r0 = 0
            java.io.File r1 = r3.e     // Catch:{ IOException -> 0x0021, all -> 0x001f }
            java.lang.String r2 = ".jpg"
            java.lang.String r1 = com.reactnative.camera.utils.RNFileUtils.a(r1, r2)     // Catch:{ IOException -> 0x0021, all -> 0x001f }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x001c, all -> 0x001f }
            r2.<init>(r1)     // Catch:{ IOException -> 0x001c, all -> 0x001f }
            r4.writeTo(r2)     // Catch:{ IOException -> 0x001a }
            r2.close()     // Catch:{ IOException -> 0x0015 }
            goto L_0x002d
        L_0x0015:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x002d
        L_0x001a:
            r4 = move-exception
            goto L_0x0024
        L_0x001c:
            r4 = move-exception
            r2 = r0
            goto L_0x0024
        L_0x001f:
            r4 = move-exception
            goto L_0x0033
        L_0x0021:
            r4 = move-exception
            r1 = r0
            r2 = r1
        L_0x0024:
            r0 = r4
            r0.printStackTrace()     // Catch:{ all -> 0x0031 }
            if (r2 == 0) goto L_0x002d
            r2.close()     // Catch:{ IOException -> 0x0015 }
        L_0x002d:
            if (r0 != 0) goto L_0x0030
            return r1
        L_0x0030:
            throw r0
        L_0x0031:
            r4 = move-exception
            r0 = r2
        L_0x0033:
            if (r0 == 0) goto L_0x003d
            r0.close()     // Catch:{ IOException -> 0x0039 }
            goto L_0x003d
        L_0x0039:
            r0 = move-exception
            r0.printStackTrace()
        L_0x003d:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnative.camera.tasks.ResolveTakenPictureAsyncTask.a(java.io.ByteArrayOutputStream):java.lang.String");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(WritableMap writableMap) {
        super.onPostExecute(writableMap);
        if (writableMap != null) {
            this.b.resolve(writableMap);
        }
    }
}
