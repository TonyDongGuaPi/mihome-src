package com.imagepicker.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.react.bridge.ReadableMap;
import com.imagepicker.ResponseHelper;
import com.imagepicker.media.ImageConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.UUID;
import miuipub.reflect.Field;

public class MediaUtils {
    @Nullable
    public static File a(@NonNull Context context, @NonNull ReadableMap readableMap, @NonNull boolean z) {
        File file;
        String str = "image-" + UUID.randomUUID().toString() + ".jpg";
        if (!ReadableMapUtils.a(readableMap, "storageOptions") || z) {
            file = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        } else {
            file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        }
        File file2 = new File(file, str);
        try {
            file.mkdirs();
            file2.createNewFile();
            return file2;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @NonNull
    public static ImageConfig a(@NonNull Context context, @NonNull ReadableMap readableMap, @NonNull ImageConfig imageConfig, int i, int i2, int i3) {
        int i4;
        int i5;
        ImageConfig imageConfig2;
        FileOutputStream fileOutputStream;
        Throwable th;
        ImageConfig imageConfig3 = imageConfig;
        BitmapFactory.Options options = new BitmapFactory.Options();
        boolean z = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        if (imageConfig3.c != 0 || imageConfig3.d != 0) {
            i5 = i;
            i4 = i2;
            while (true) {
                if ((imageConfig3.c != 0 && i5 <= imageConfig3.c * 2) || (imageConfig3.d != 0 && i4 <= imageConfig3.d * 2)) {
                    break;
                }
                Context context2 = context;
                ReadableMap readableMap2 = readableMap;
                int i6 = i3;
                options.inSampleSize *= 2;
                i4 /= 2;
                i5 /= 2;
            }
        } else {
            i5 = i;
            i4 = i2;
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(imageConfig3.f6065a.getAbsolutePath(), options);
        if (decodeFile == null) {
            return null;
        }
        if (imageConfig3.c == 0 || imageConfig3.c > i5) {
            imageConfig2 = imageConfig3.a(i5);
        } else {
            imageConfig2 = imageConfig3;
        }
        if (imageConfig3.d == 0 || imageConfig3.c > i4) {
            imageConfig2 = imageConfig2.b(i4);
        }
        ImageConfig imageConfig4 = imageConfig2;
        double d = (double) imageConfig4.c;
        double d2 = (double) i5;
        Double.isNaN(d);
        Double.isNaN(d2);
        double d3 = d / d2;
        double d4 = (double) imageConfig4.d;
        double d5 = (double) i4;
        Double.isNaN(d4);
        Double.isNaN(d5);
        double d6 = d4 / d5;
        if (d3 < d6) {
            d6 = d3;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) imageConfig4.f);
        float f = (float) d6;
        matrix.postScale(f, f);
        try {
            int attributeInt = new ExifInterface(imageConfig4.f6065a.getAbsolutePath()).getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, 0);
            if (attributeInt == 3) {
                matrix.postRotate(180.0f);
            } else if (attributeInt == 6) {
                matrix.postRotate(90.0f);
            } else if (attributeInt == 8) {
                matrix.postRotate(270.0f);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap createBitmap = Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, true);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.JPEG, imageConfig4.e, byteArrayOutputStream);
        if (i3 == 13001) {
            z = true;
        }
        File a2 = a(context, readableMap, !z);
        if (a2 == null) {
            if (decodeFile != null) {
                decodeFile.recycle();
            }
            if (createBitmap != null) {
                createBitmap.recycle();
            }
            return imageConfig3;
        }
        ImageConfig b = imageConfig4.b(a2);
        try {
            fileOutputStream = new FileOutputStream(b.b);
            byteArrayOutputStream.writeTo(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (Throwable unused) {
        }
        if (decodeFile != null) {
            decodeFile.recycle();
        }
        if (createBitmap != null) {
            createBitmap.recycle();
        }
        return b;
        throw th;
    }

    public static void a(int i, @NonNull ImageConfig imageConfig) {
        if (i == 13001) {
            if (imageConfig.f6065a != null && imageConfig.f6065a.exists()) {
                imageConfig.f6065a.delete();
            }
            if (imageConfig.b != null && imageConfig.b.exists()) {
                imageConfig.b.delete();
            }
        }
    }

    public static void a(@Nullable Context context, @NonNull String str) {
        if (context != null) {
            MediaScannerConnection.scanFile(context, new String[]{str}, (String[]) null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String str, Uri uri) {
                    Log.i("TAG", "Finished scanning " + str);
                }
            });
        }
    }

    public static ReadExifResult a(@NonNull ResponseHelper responseHelper, @NonNull ImageConfig imageConfig) {
        int i = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(imageConfig.f6065a.getAbsolutePath());
            float[] fArr = new float[2];
            exifInterface.getLatLong(fArr);
            float f = fArr[0];
            boolean z = true;
            float f2 = fArr[1];
            if (!(f == 0.0f && f2 == 0.0f)) {
                responseHelper.a("latitude", (double) f);
                responseHelper.a("longitude", (double) f2);
            }
            String attribute = exifInterface.getAttribute(android.support.media.ExifInterface.TAG_DATETIME);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                responseHelper.a("timestamp", simpleDateFormat2.format(simpleDateFormat.parse(attribute)) + Field.f3009a);
            } catch (Exception unused) {
            }
            int attributeInt = exifInterface.getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt != 3) {
                if (attributeInt == 6) {
                    i = 90;
                } else if (attributeInt == 8) {
                    i = 270;
                }
                z = false;
            } else {
                i = 180;
            }
            responseHelper.a("originalRotation", i);
            responseHelper.a("isVertical", z);
            return new ReadExifResult(i, (Throwable) null);
        } catch (IOException e) {
            e.printStackTrace();
            return new ReadExifResult(0, e);
        }
    }

    @Nullable
    public static RolloutPhotoResult a(@NonNull ImageConfig imageConfig) {
        ImageConfig imageConfig2;
        File file = imageConfig.b == null ? imageConfig.f6065a : imageConfig.b;
        File file2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath(), file.getName());
        try {
            a(file, file2);
            if (imageConfig.b != null) {
                imageConfig2 = imageConfig.b(file2);
            } else {
                imageConfig2 = imageConfig.a(file2);
            }
            return new RolloutPhotoResult(imageConfig2, (Throwable) null);
        } catch (IOException e) {
            e.printStackTrace();
            return new RolloutPhotoResult(imageConfig, e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x003c A[SYNTHETIC, Splitter:B:22:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0044 A[Catch:{ IOException -> 0x0040 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(@android.support.annotation.NonNull java.io.File r8, @android.support.annotation.NonNull java.io.File r9) throws java.io.IOException {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0038 }
            r1.<init>(r8)     // Catch:{ all -> 0x0038 }
            java.nio.channels.FileChannel r1 = r1.getChannel()     // Catch:{ all -> 0x0038 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x0036 }
            r2.<init>(r9)     // Catch:{ all -> 0x0036 }
            java.nio.channels.FileChannel r9 = r2.getChannel()     // Catch:{ all -> 0x0036 }
            r3 = 0
            long r5 = r1.size()     // Catch:{ all -> 0x0033 }
            r2 = r1
            r7 = r9
            r2.transferTo(r3, r5, r7)     // Catch:{ all -> 0x0033 }
            r8.delete()     // Catch:{ all -> 0x0033 }
            if (r1 == 0) goto L_0x0029
            r1.close()     // Catch:{ IOException -> 0x0027 }
            goto L_0x0029
        L_0x0027:
            r8 = move-exception
            goto L_0x002f
        L_0x0029:
            if (r9 == 0) goto L_0x0032
            r9.close()     // Catch:{ IOException -> 0x0027 }
            goto L_0x0032
        L_0x002f:
            r8.printStackTrace()
        L_0x0032:
            return
        L_0x0033:
            r8 = move-exception
            r0 = r9
            goto L_0x003a
        L_0x0036:
            r8 = move-exception
            goto L_0x003a
        L_0x0038:
            r8 = move-exception
            r1 = r0
        L_0x003a:
            if (r1 == 0) goto L_0x0042
            r1.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x0042
        L_0x0040:
            r9 = move-exception
            goto L_0x0048
        L_0x0042:
            if (r0 == 0) goto L_0x004b
            r0.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x004b
        L_0x0048:
            r9.printStackTrace()
        L_0x004b:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imagepicker.utils.MediaUtils.a(java.io.File, java.io.File):void");
    }

    public static class RolloutPhotoResult {

        /* renamed from: a  reason: collision with root package name */
        public final ImageConfig f6071a;
        public final Throwable b;

        public RolloutPhotoResult(@NonNull ImageConfig imageConfig, @Nullable Throwable th) {
            this.f6071a = imageConfig;
            this.b = th;
        }
    }

    public static class ReadExifResult {

        /* renamed from: a  reason: collision with root package name */
        public final int f6070a;
        public final Throwable b;

        public ReadExifResult(int i, @Nullable Throwable th) {
            this.f6070a = i;
            this.b = th;
        }
    }
}
