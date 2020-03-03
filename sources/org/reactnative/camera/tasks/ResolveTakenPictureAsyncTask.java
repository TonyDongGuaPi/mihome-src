package org.reactnative.camera.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.taobao.weex.common.Constants;
import java.io.File;
import java.io.IOException;

public class ResolveTakenPictureAsyncTask extends AsyncTask<Void, Void, WritableMap> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4159a = "E_TAKING_PICTURE_FAILED";
    private Promise b;
    private Bitmap c;
    private byte[] d;
    private ReadableMap e;
    private File f;
    private int g;
    private PictureSavedDelegate h;

    private int a(int i) {
        if (i == 3) {
            return 180;
        }
        if (i != 6) {
            return i != 8 ? 0 : 270;
        }
        return 90;
    }

    public ResolveTakenPictureAsyncTask(byte[] bArr, Promise promise, ReadableMap readableMap, File file, int i, PictureSavedDelegate pictureSavedDelegate) {
        this.b = promise;
        this.e = readableMap;
        this.d = bArr;
        this.f = file;
        this.g = i;
        this.h = pictureSavedDelegate;
    }

    private int a() {
        return (int) (this.e.getDouble(Constants.Name.QUALITY) * 100.0d);
    }

    private void b() throws IOException {
        if (this.c == null) {
            this.c = BitmapFactory.decodeByteArray(this.d, 0, this.d.length);
        }
        if (this.c == null) {
            throw new IOException("Failed to decode Image Bitmap");
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x027d A[SYNTHETIC, Splitter:B:108:0x027d] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0291 A[SYNTHETIC, Splitter:B:115:0x0291] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x029d A[SYNTHETIC, Splitter:B:122:0x029d] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006d A[Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b3 A[Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b5 A[Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c0 A[Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ec A[Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f9 A[Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00fa A[Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0110 A[Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x012f A[Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0139 A[Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01dd A[Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:112:0x0283=Splitter:B:112:0x0283, B:105:0x026f=Splitter:B:105:0x026f} */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.bridge.WritableMap doInBackground(java.lang.Void... r12) {
        /*
            r11 = this;
            com.facebook.react.bridge.WritableMap r12 = com.facebook.react.bridge.Arguments.createMap()
            java.lang.String r0 = "deviceOrientation"
            int r1 = r11.g
            r12.putInt(r0, r1)
            java.lang.String r0 = "pictureOrientation"
            com.facebook.react.bridge.ReadableMap r1 = r11.e
            java.lang.String r2 = "orientation"
            boolean r1 = r1.hasKey(r2)
            if (r1 == 0) goto L_0x0020
            com.facebook.react.bridge.ReadableMap r1 = r11.e
            java.lang.String r2 = "orientation"
            int r1 = r1.getInt(r2)
            goto L_0x0022
        L_0x0020:
            int r1 = r11.g
        L_0x0022:
            r12.putInt(r0, r1)
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ NotFoundException -> 0x0281, IOException -> 0x026d, all -> 0x026a }
            byte[] r2 = r11.d     // Catch:{ NotFoundException -> 0x0281, IOException -> 0x026d, all -> 0x026a }
            r1.<init>(r2)     // Catch:{ NotFoundException -> 0x0281, IOException -> 0x026d, all -> 0x026a }
            com.facebook.react.bridge.ReadableMap r2 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r3 = "fixOrientation"
            boolean r2 = r2.hasKey(r3)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0061
            com.facebook.react.bridge.ReadableMap r2 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r5 = "fixOrientation"
            boolean r2 = r2.getBoolean(r5)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r2 == 0) goto L_0x0061
            android.support.media.ExifInterface r2 = new android.support.media.ExifInterface     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r2.<init>((java.io.InputStream) r1)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r5 = "Orientation"
            int r5 = r2.getAttributeInt(r5, r3)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r5 == 0) goto L_0x0062
            r11.b()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            android.graphics.Bitmap r6 = r11.c     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            int r5 = r11.a((int) r5)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            android.graphics.Bitmap r5 = r11.a(r6, r5)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r11.c = r5     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r5 = 1
            goto L_0x0063
        L_0x0061:
            r2 = r0
        L_0x0062:
            r5 = 0
        L_0x0063:
            com.facebook.react.bridge.ReadableMap r6 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r7 = "width"
            boolean r6 = r6.hasKey(r7)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r6 == 0) goto L_0x0080
            r11.b()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            android.graphics.Bitmap r6 = r11.c     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            com.facebook.react.bridge.ReadableMap r7 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r8 = "width"
            int r7 = r7.getInt(r8)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            android.graphics.Bitmap r6 = r11.b(r6, r7)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r11.c = r6     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
        L_0x0080:
            com.facebook.react.bridge.ReadableMap r6 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r7 = "mirrorImage"
            boolean r6 = r6.hasKey(r7)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r6 == 0) goto L_0x009f
            com.facebook.react.bridge.ReadableMap r6 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r7 = "mirrorImage"
            boolean r6 = r6.getBoolean(r7)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r6 == 0) goto L_0x009f
            r11.b()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            android.graphics.Bitmap r6 = r11.c     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            android.graphics.Bitmap r6 = r11.a((android.graphics.Bitmap) r6)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r11.c = r6     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
        L_0x009f:
            com.facebook.react.bridge.ReadableMap r6 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r7 = "exif"
            boolean r6 = r6.hasKey(r7)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r6 == 0) goto L_0x00b5
            com.facebook.react.bridge.ReadableMap r6 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r7 = "exif"
            boolean r6 = r6.getBoolean(r7)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r6 == 0) goto L_0x00b5
            r6 = 1
            goto L_0x00b6
        L_0x00b5:
            r6 = 0
        L_0x00b6:
            com.facebook.react.bridge.ReadableMap r7 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r8 = "writeExif"
            boolean r7 = r7.hasKey(r8)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r7 == 0) goto L_0x00e8
            int[] r7 = org.reactnative.camera.tasks.ResolveTakenPictureAsyncTask.AnonymousClass1.f4160a     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            com.facebook.react.bridge.ReadableMap r8 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r9 = "writeExif"
            com.facebook.react.bridge.ReadableType r8 = r8.getType(r9)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            int r8 = r8.ordinal()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r7 = r7[r8]     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            switch(r7) {
                case 1: goto L_0x00de;
                case 2: goto L_0x00d4;
                default: goto L_0x00d3;
            }     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
        L_0x00d3:
            goto L_0x00e8
        L_0x00d4:
            com.facebook.react.bridge.ReadableMap r7 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r8 = "writeExif"
            com.facebook.react.bridge.ReadableMap r7 = r7.getMap(r8)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r8 = r7
            goto L_0x00e9
        L_0x00de:
            com.facebook.react.bridge.ReadableMap r7 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r8 = "writeExif"
            boolean r7 = r7.getBoolean(r8)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r8 = r0
            goto L_0x00ea
        L_0x00e8:
            r8 = r0
        L_0x00e9:
            r7 = 1
        L_0x00ea:
            if (r6 != 0) goto L_0x00f1
            if (r7 == 0) goto L_0x00ef
            goto L_0x00f1
        L_0x00ef:
            r2 = r0
            goto L_0x0134
        L_0x00f1:
            android.graphics.Bitmap r9 = r11.c     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r9 != 0) goto L_0x00fc
            if (r8 != 0) goto L_0x00fc
            if (r6 == 0) goto L_0x00fa
            goto L_0x00fc
        L_0x00fa:
            r2 = r0
            goto L_0x010c
        L_0x00fc:
            if (r2 != 0) goto L_0x0103
            android.support.media.ExifInterface r2 = new android.support.media.ExifInterface     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r2.<init>((java.io.InputStream) r1)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
        L_0x0103:
            com.facebook.react.bridge.WritableMap r2 = org.reactnative.camera.RNCameraViewHelper.a((android.support.media.ExifInterface) r2)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r8 == 0) goto L_0x010c
            r2.merge(r8)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
        L_0x010c:
            android.graphics.Bitmap r9 = r11.c     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r9 == 0) goto L_0x012d
            java.lang.String r9 = "width"
            android.graphics.Bitmap r10 = r11.c     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            int r10 = r10.getWidth()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r2.putInt(r9, r10)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r9 = "height"
            android.graphics.Bitmap r10 = r11.c     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            int r10 = r10.getHeight()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r2.putInt(r9, r10)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r5 == 0) goto L_0x012d
            java.lang.String r5 = "Orientation"
            r2.putInt(r5, r4)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
        L_0x012d:
            if (r6 == 0) goto L_0x0134
            java.lang.String r5 = "exif"
            r12.putMap(r5, r2)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
        L_0x0134:
            android.graphics.Bitmap r5 = r11.c     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r6 = 2
            if (r5 != 0) goto L_0x01dd
            android.graphics.BitmapFactory$Options r2 = new android.graphics.BitmapFactory$Options     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r2.<init>()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r2.inJustDecodeBounds = r4     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            byte[] r4 = r11.d     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            byte[] r5 = r11.d     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            int r5 = r5.length     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            android.graphics.BitmapFactory.decodeByteArray(r4, r3, r5, r2)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r3 = "width"
            int r4 = r2.outWidth     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r12.putInt(r3, r4)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r3 = "height"
            int r2 = r2.outHeight     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r12.putInt(r3, r2)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            com.facebook.react.bridge.ReadableMap r2 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r3 = "doNotSave"
            boolean r2 = r2.hasKey(r3)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r2 == 0) goto L_0x016a
            com.facebook.react.bridge.ReadableMap r2 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r3 = "doNotSave"
            boolean r2 = r2.getBoolean(r3)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r2 != 0) goto L_0x01bc
        L_0x016a:
            java.io.File r2 = new java.io.File     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.io.File r3 = r11.f     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r4 = ".jpg"
            java.lang.String r3 = org.reactnative.camera.utils.RNFileUtils.a(r3, r4)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r2.<init>(r3)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r2.createNewFile()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r3.<init>(r2)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            byte[] r4 = r11.d     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r3.write(r4)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r3.flush()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r3.close()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r7 == 0) goto L_0x019e
            if (r8 == 0) goto L_0x019e
            android.support.media.ExifInterface r3 = new android.support.media.ExifInterface     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r4 = r2.getAbsolutePath()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r3.<init>((java.lang.String) r4)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            org.reactnative.camera.RNCameraViewHelper.a((android.support.media.ExifInterface) r3, (com.facebook.react.bridge.ReadableMap) r8)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r3.saveAttributes()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            goto L_0x01af
        L_0x019e:
            if (r7 != 0) goto L_0x01af
            android.support.media.ExifInterface r3 = new android.support.media.ExifInterface     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r4 = r2.getAbsolutePath()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r3.<init>((java.lang.String) r4)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            org.reactnative.camera.RNCameraViewHelper.b((android.support.media.ExifInterface) r3)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r3.saveAttributes()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
        L_0x01af:
            android.net.Uri r2 = android.net.Uri.fromFile(r2)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r2 = r2.toString()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r3 = "uri"
            r12.putString(r3, r2)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
        L_0x01bc:
            com.facebook.react.bridge.ReadableMap r2 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r3 = "base64"
            boolean r2 = r2.hasKey(r3)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r2 == 0) goto L_0x025d
            com.facebook.react.bridge.ReadableMap r2 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r3 = "base64"
            boolean r2 = r2.getBoolean(r3)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r2 == 0) goto L_0x025d
            java.lang.String r2 = "base64"
            byte[] r3 = r11.d     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r3 = android.util.Base64.encodeToString(r3, r6)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r12.putString(r2, r3)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            goto L_0x025d
        L_0x01dd:
            java.lang.String r3 = "width"
            android.graphics.Bitmap r4 = r11.c     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            int r4 = r4.getWidth()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r12.putInt(r3, r4)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r3 = "height"
            android.graphics.Bitmap r4 = r11.c     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            int r4 = r4.getHeight()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r12.putInt(r3, r4)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r3.<init>()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            android.graphics.Bitmap r4 = r11.c     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            android.graphics.Bitmap$CompressFormat r5 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            int r8 = r11.a()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r4.compress(r5, r8, r3)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            com.facebook.react.bridge.ReadableMap r4 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r5 = "doNotSave"
            boolean r4 = r4.hasKey(r5)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r4 == 0) goto L_0x0217
            com.facebook.react.bridge.ReadableMap r4 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r5 = "doNotSave"
            boolean r4 = r4.getBoolean(r5)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r4 != 0) goto L_0x023c
        L_0x0217:
            java.lang.String r4 = r11.a((java.io.ByteArrayOutputStream) r3)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r7 == 0) goto L_0x022a
            if (r2 == 0) goto L_0x022a
            android.support.media.ExifInterface r5 = new android.support.media.ExifInterface     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r5.<init>((java.lang.String) r4)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            org.reactnative.camera.RNCameraViewHelper.a((android.support.media.ExifInterface) r5, (com.facebook.react.bridge.ReadableMap) r2)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r5.saveAttributes()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
        L_0x022a:
            java.io.File r2 = new java.io.File     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r2.<init>(r4)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            android.net.Uri r2 = android.net.Uri.fromFile(r2)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r2 = r2.toString()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r4 = "uri"
            r12.putString(r4, r2)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
        L_0x023c:
            com.facebook.react.bridge.ReadableMap r2 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r4 = "base64"
            boolean r2 = r2.hasKey(r4)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r2 == 0) goto L_0x025d
            com.facebook.react.bridge.ReadableMap r2 = r11.e     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r4 = "base64"
            boolean r2 = r2.getBoolean(r4)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            if (r2 == 0) goto L_0x025d
            java.lang.String r2 = "base64"
            byte[] r3 = r3.toByteArray()     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            java.lang.String r3 = android.util.Base64.encodeToString(r3, r6)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
            r12.putString(r2, r3)     // Catch:{ NotFoundException -> 0x0268, IOException -> 0x0266 }
        L_0x025d:
            r1.close()     // Catch:{ IOException -> 0x0261 }
            goto L_0x0265
        L_0x0261:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0265:
            return r12
        L_0x0266:
            r12 = move-exception
            goto L_0x026f
        L_0x0268:
            r12 = move-exception
            goto L_0x0283
        L_0x026a:
            r12 = move-exception
            r1 = r0
            goto L_0x029b
        L_0x026d:
            r12 = move-exception
            r1 = r0
        L_0x026f:
            com.facebook.react.bridge.Promise r2 = r11.b     // Catch:{ all -> 0x029a }
            java.lang.String r3 = "E_TAKING_PICTURE_FAILED"
            java.lang.String r4 = "An unknown I/O exception has occurred."
            r2.reject((java.lang.String) r3, (java.lang.String) r4, (java.lang.Throwable) r12)     // Catch:{ all -> 0x029a }
            r12.printStackTrace()     // Catch:{ all -> 0x029a }
            if (r1 == 0) goto L_0x0299
            r1.close()     // Catch:{ IOException -> 0x0295 }
            goto L_0x0299
        L_0x0281:
            r12 = move-exception
            r1 = r0
        L_0x0283:
            com.facebook.react.bridge.Promise r2 = r11.b     // Catch:{ all -> 0x029a }
            java.lang.String r3 = "E_TAKING_PICTURE_FAILED"
            java.lang.String r4 = "Documents directory of the app could not be found."
            r2.reject((java.lang.String) r3, (java.lang.String) r4, (java.lang.Throwable) r12)     // Catch:{ all -> 0x029a }
            r12.printStackTrace()     // Catch:{ all -> 0x029a }
            if (r1 == 0) goto L_0x0299
            r1.close()     // Catch:{ IOException -> 0x0295 }
            goto L_0x0299
        L_0x0295:
            r12 = move-exception
            r12.printStackTrace()
        L_0x0299:
            return r0
        L_0x029a:
            r12 = move-exception
        L_0x029b:
            if (r1 == 0) goto L_0x02a5
            r1.close()     // Catch:{ IOException -> 0x02a1 }
            goto L_0x02a5
        L_0x02a1:
            r0 = move-exception
            r0.printStackTrace()
        L_0x02a5:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.reactnative.camera.tasks.ResolveTakenPictureAsyncTask.doInBackground(java.lang.Void[]):com.facebook.react.bridge.WritableMap");
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
            java.io.File r1 = r3.f     // Catch:{ IOException -> 0x0021, all -> 0x001f }
            java.lang.String r2 = ".jpg"
            java.lang.String r1 = org.reactnative.camera.utils.RNFileUtils.a(r1, r2)     // Catch:{ IOException -> 0x0021, all -> 0x001f }
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
        throw new UnsupportedOperationException("Method not decompiled: org.reactnative.camera.tasks.ResolveTakenPictureAsyncTask.a(java.io.ByteArrayOutputStream):java.lang.String");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(WritableMap writableMap) {
        super.onPostExecute(writableMap);
        if (writableMap == null) {
            return;
        }
        if (!this.e.hasKey("fastMode") || !this.e.getBoolean("fastMode")) {
            this.b.resolve(writableMap);
            return;
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", this.e.getInt("id"));
        createMap.putMap("data", writableMap);
        this.h.onPictureSaved(createMap);
    }
}
