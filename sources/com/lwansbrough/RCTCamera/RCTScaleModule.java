package com.lwansbrough.RCTCamera;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import java.util.Map;
import javax.annotation.Nullable;

public class RCTScaleModule extends ReactContextBaseJavaModule {
    public String getName() {
        return "AndroidScaleImage";
    }

    public RCTScaleModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Nullable
    public Map<String, Object> getConstants() {
        return super.getConstants();
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d7 A[SYNTHETIC, Splitter:B:36:0x00d7] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00dd A[SYNTHETIC, Splitter:B:39:0x00dd] */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    @com.facebook.react.bridge.ReactMethod
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void scale(com.facebook.react.bridge.ReadableMap r12, com.facebook.react.bridge.Promise r13) {
        /*
            r11 = this;
            java.lang.String r0 = "path"
            boolean r0 = r12.hasKey(r0)
            if (r0 == 0) goto L_0x00e1
            java.lang.String r0 = "path"
            java.lang.String r0 = r12.getString(r0)
            java.lang.String r1 = "targetWidth"
            boolean r1 = r12.hasKey(r1)
            r2 = 0
            if (r1 == 0) goto L_0x0020
            java.lang.String r1 = "targetWidth"
            int r1 = r12.getInt(r1)
            goto L_0x0021
        L_0x0020:
            r1 = 0
        L_0x0021:
            java.lang.String r3 = "targetHeight"
            boolean r3 = r12.hasKey(r3)
            if (r3 == 0) goto L_0x0032
            java.lang.String r3 = "targetHeight"
            int r3 = r12.getInt(r3)
            goto L_0x0033
        L_0x0032:
            r3 = 0
        L_0x0033:
            if (r1 == 0) goto L_0x0037
            if (r3 != 0) goto L_0x003f
        L_0x0037:
            java.lang.String r4 = "error"
            java.lang.String r5 = "targetWidth or targetHeight is 0!"
            r13.reject((java.lang.String) r4, (java.lang.String) r5)
        L_0x003f:
            r4 = 80
            java.lang.String r5 = "jpegQuality"
            boolean r5 = r12.hasKey(r5)
            if (r5 == 0) goto L_0x004f
            java.lang.String r4 = "jpegQuality"
            int r4 = r12.getInt(r4)
        L_0x004f:
            r12 = 0
            android.graphics.BitmapFactory$Options r5 = new android.graphics.BitmapFactory$Options     // Catch:{ Exception -> 0x00ce }
            r5.<init>()     // Catch:{ Exception -> 0x00ce }
            r6 = 1
            r5.inJustDecodeBounds = r6     // Catch:{ Exception -> 0x00ce }
            android.graphics.Bitmap$Config r7 = android.graphics.Bitmap.Config.RGB_565     // Catch:{ Exception -> 0x00ce }
            r5.inPreferredConfig = r7     // Catch:{ Exception -> 0x00ce }
            android.graphics.BitmapFactory.decodeFile(r0, r5)     // Catch:{ Exception -> 0x00ce }
            r5.inJustDecodeBounds = r2     // Catch:{ Exception -> 0x00ce }
            int r2 = r5.outWidth     // Catch:{ Exception -> 0x00ce }
            float r2 = (float) r2     // Catch:{ Exception -> 0x00ce }
            float r7 = (float) r1     // Catch:{ Exception -> 0x00ce }
            float r2 = r2 / r7
            int r7 = r5.outHeight     // Catch:{ Exception -> 0x00ce }
            float r7 = (float) r7     // Catch:{ Exception -> 0x00ce }
            float r8 = (float) r3     // Catch:{ Exception -> 0x00ce }
            float r7 = r7 / r8
            float r2 = java.lang.Math.max(r2, r7)     // Catch:{ Exception -> 0x00ce }
            r7 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r9 = (double) r2     // Catch:{ Exception -> 0x00ce }
            double r9 = java.lang.Math.sqrt(r9)     // Catch:{ Exception -> 0x00ce }
            int r2 = (int) r9     // Catch:{ Exception -> 0x00ce }
            double r9 = (double) r2     // Catch:{ Exception -> 0x00ce }
            double r7 = java.lang.Math.pow(r7, r9)     // Catch:{ Exception -> 0x00ce }
            int r2 = (int) r7     // Catch:{ Exception -> 0x00ce }
            if (r2 > r6) goto L_0x0080
            r2 = 1
        L_0x0080:
            r5.inSampleSize = r2     // Catch:{ Exception -> 0x00ce }
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeFile(r0, r5)     // Catch:{ Exception -> 0x00ce }
            android.graphics.Bitmap r1 = com.lwansbrough.RCTCamera.MutableImage.scale(r2, r1, r3)     // Catch:{ Exception -> 0x00ce }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x00ce }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ce }
            r3.<init>()     // Catch:{ Exception -> 0x00ce }
            r3.append(r0)     // Catch:{ Exception -> 0x00ce }
            java.lang.String r0 = "_scale.jpg"
            r3.append(r0)     // Catch:{ Exception -> 0x00ce }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x00ce }
            r2.<init>(r0)     // Catch:{ Exception -> 0x00ce }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00ce }
            r0.<init>(r2)     // Catch:{ Exception -> 0x00ce }
            android.graphics.Bitmap$CompressFormat r12 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x00ca, all -> 0x00c6 }
            r1.compress(r12, r4, r0)     // Catch:{ Exception -> 0x00ca, all -> 0x00c6 }
            r0.flush()     // Catch:{ Exception -> 0x00ca, all -> 0x00c6 }
            com.facebook.react.bridge.WritableNativeMap r12 = new com.facebook.react.bridge.WritableNativeMap     // Catch:{ Exception -> 0x00ca, all -> 0x00c6 }
            r12.<init>()     // Catch:{ Exception -> 0x00ca, all -> 0x00c6 }
            java.lang.String r1 = "path"
            android.net.Uri r2 = android.net.Uri.fromFile(r2)     // Catch:{ Exception -> 0x00ca, all -> 0x00c6 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00ca, all -> 0x00c6 }
            r12.putString(r1, r2)     // Catch:{ Exception -> 0x00ca, all -> 0x00c6 }
            r13.resolve(r12)     // Catch:{ Exception -> 0x00ca, all -> 0x00c6 }
            r0.close()     // Catch:{ IOException -> 0x00e8 }
            goto L_0x00e8
        L_0x00c6:
            r12 = move-exception
            r13 = r12
            r12 = r0
            goto L_0x00db
        L_0x00ca:
            r12 = r0
            goto L_0x00ce
        L_0x00cc:
            r13 = move-exception
            goto L_0x00db
        L_0x00ce:
            java.lang.String r0 = "error"
            java.lang.String r1 = "image scale fail!"
            r13.reject((java.lang.String) r0, (java.lang.String) r1)     // Catch:{ all -> 0x00cc }
            if (r12 == 0) goto L_0x00e8
            r12.close()     // Catch:{ IOException -> 0x00e8 }
            goto L_0x00e8
        L_0x00db:
            if (r12 == 0) goto L_0x00e0
            r12.close()     // Catch:{ IOException -> 0x00e0 }
        L_0x00e0:
            throw r13
        L_0x00e1:
            java.lang.String r12 = "error"
            java.lang.String r0 = "image not found!"
            r13.reject((java.lang.String) r12, (java.lang.String) r0)
        L_0x00e8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lwansbrough.RCTCamera.RCTScaleModule.scale(com.facebook.react.bridge.ReadableMap, com.facebook.react.bridge.Promise):void");
    }
}
