package com.unionpay.mobile.android.resource;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public final class c {
    private static c c;

    /* renamed from: a  reason: collision with root package name */
    private HashMap<Integer, WeakReference<Drawable.ConstantState>> f9704a = new HashMap<>();
    private Context b = null;

    private c(Context context) {
        this.b = context;
    }

    public static c a(Context context) {
        if (c == null) {
            c = new c(context);
        }
        return c;
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [android.graphics.drawable.BitmapDrawable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.drawable.Drawable a(int r20, int r21, int r22) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            r0 = r21
            r3 = r22
            r4 = 0
            if (r2 >= 0) goto L_0x000c
            return r4
        L_0x000c:
            java.util.HashMap<java.lang.Integer, java.lang.ref.WeakReference<android.graphics.drawable.Drawable$ConstantState>> r5 = r1.f9704a
            java.lang.Integer r6 = java.lang.Integer.valueOf(r20)
            java.lang.Object r5 = r5.get(r6)
            java.lang.ref.WeakReference r5 = (java.lang.ref.WeakReference) r5
            if (r5 == 0) goto L_0x0030
            java.lang.Object r5 = r5.get()
            android.graphics.drawable.Drawable$ConstantState r5 = (android.graphics.drawable.Drawable.ConstantState) r5
            if (r5 == 0) goto L_0x0027
            android.graphics.drawable.Drawable r0 = r5.newDrawable()
            return r0
        L_0x0027:
            java.util.HashMap<java.lang.Integer, java.lang.ref.WeakReference<android.graphics.drawable.Drawable$ConstantState>> r5 = r1.f9704a
            java.lang.Integer r6 = java.lang.Integer.valueOf(r20)
            r5.remove(r6)
        L_0x0030:
            int r5 = r2 / 1000
            int r5 = r5 * 1000
            int r6 = r2 - r5
            r7 = 2000(0x7d0, float:2.803E-42)
            r8 = 3
            r9 = 2
            r10 = 0
            r11 = 1
            if (r5 == r7) goto L_0x0076
            r7 = 3000(0xbb8, float:4.204E-42)
            if (r5 == r7) goto L_0x0059
            r7 = 4000(0xfa0, float:5.605E-42)
            if (r5 == r7) goto L_0x0048
            r5 = r4
            goto L_0x0096
        L_0x0048:
            int[] r5 = com.unionpay.mobile.android.resource.b.f
            r5 = r5[r6]
            int[] r7 = com.unionpay.mobile.android.resource.b.g
            r7 = r7[r6]
            float[] r8 = com.unionpay.mobile.android.resource.b.h
            r6 = r8[r6]
            android.graphics.drawable.ShapeDrawable r5 = com.unionpay.mobile.android.utils.h.a(r5, r7, r6)
            goto L_0x0096
        L_0x0059:
            int[] r5 = com.unionpay.mobile.android.resource.b.e
            r12 = r5[r6]
            int[][] r5 = com.unionpay.mobile.android.resource.b.d
            r13 = r5[r6]
            float[][] r5 = com.unionpay.mobile.android.resource.b.b
            r14 = r5[r6]
            float[][] r5 = com.unionpay.mobile.android.resource.b.c
            r5 = r5[r6]
            r15 = r5[r10]
            r16 = r5[r11]
            r17 = r5[r9]
            r18 = r5[r8]
            android.graphics.drawable.Drawable r5 = com.unionpay.mobile.android.utils.h.a(r12, r13, r14, r15, r16, r17, r18)
            goto L_0x0096
        L_0x0076:
            int[][] r5 = com.unionpay.mobile.android.resource.b.f9703a
            r5 = r5[r6]
            r6 = r5[r10]
            android.graphics.drawable.Drawable r6 = r1.a(r6, r0, r3)
            r7 = r5[r11]
            android.graphics.drawable.Drawable r7 = r1.a(r7, r0, r3)
            r9 = r5[r9]
            android.graphics.drawable.Drawable r9 = r1.a(r9, r0, r3)
            r5 = r5[r8]
            android.graphics.drawable.Drawable r5 = r1.a(r5, r0, r3)
            android.graphics.drawable.Drawable r5 = com.unionpay.mobile.android.utils.h.a((android.graphics.drawable.Drawable) r6, (android.graphics.drawable.Drawable) r7, (android.graphics.drawable.Drawable) r9, (android.graphics.drawable.Drawable) r5)
        L_0x0096:
            if (r5 != 0) goto L_0x0195
            java.lang.Class<com.unionpay.mobile.android.resource.a> r5 = com.unionpay.mobile.android.resource.a.class
            java.lang.ClassLoader r5 = r5.getClassLoader()
            java.lang.String r6 = "assets/data.bin"
            java.io.InputStream r5 = r5.getResourceAsStream(r6)
            java.io.DataInputStream r6 = new java.io.DataInputStream
            r6.<init>(r5)
            int r7 = r2 + -1000
            int r7 = r7 * 8
            r8 = r7
        L_0x00ae:
            long r8 = (long) r8
            long r12 = r6.skip(r8)     // Catch:{ IOException -> 0x018f }
            int r10 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r10 >= 0) goto L_0x00ba
            long r8 = r8 - r12
            int r8 = (int) r8     // Catch:{ IOException -> 0x018f }
            goto L_0x00ae
        L_0x00ba:
            int r8 = r6.readInt()     // Catch:{ IOException -> 0x018f }
            int r9 = r6.readInt()     // Catch:{ IOException -> 0x018f }
            int r7 = r7 + 8
            int r8 = r8 - r7
        L_0x00c5:
            long r7 = (long) r8     // Catch:{ IOException -> 0x018f }
            long r12 = r6.skip(r7)     // Catch:{ IOException -> 0x018f }
            int r10 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
            if (r10 >= 0) goto L_0x00d1
            long r7 = r7 - r12
            int r8 = (int) r7     // Catch:{ IOException -> 0x018f }
            goto L_0x00c5
        L_0x00d1:
            r6.mark(r9)     // Catch:{ IOException -> 0x018f }
            android.graphics.Bitmap r14 = android.graphics.BitmapFactory.decodeStream(r6)     // Catch:{ IOException -> 0x018f }
            android.graphics.Rect r16 = new android.graphics.Rect     // Catch:{ IOException -> 0x018f }
            r16.<init>()     // Catch:{ IOException -> 0x018f }
            byte[] r7 = r14.getNinePatchChunk()     // Catch:{ IOException -> 0x018f }
            if (r7 != 0) goto L_0x0175
            r7 = -1
            if (r7 == r3) goto L_0x00fe
            if (r7 == r0) goto L_0x00fe
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createScaledBitmap(r14, r0, r3, r11)     // Catch:{ IOException -> 0x018f }
            if (r0 == r14) goto L_0x00f1
            r14.recycle()     // Catch:{ IOException -> 0x018f }
        L_0x00f1:
            android.graphics.drawable.BitmapDrawable r3 = new android.graphics.drawable.BitmapDrawable     // Catch:{ IOException -> 0x018f }
            android.content.Context r7 = r1.b     // Catch:{ IOException -> 0x018f }
            android.content.res.Resources r7 = r7.getResources()     // Catch:{ IOException -> 0x018f }
            r3.<init>(r7, r0)     // Catch:{ IOException -> 0x018f }
            goto L_0x0188
        L_0x00fe:
            if (r7 == r3) goto L_0x0141
            if (r7 != r0) goto L_0x0141
            int r0 = r14.getWidth()     // Catch:{ IOException -> 0x018f }
            float r0 = (float) r0     // Catch:{ IOException -> 0x018f }
            int r7 = r14.getHeight()     // Catch:{ IOException -> 0x018f }
            float r7 = (float) r7     // Catch:{ IOException -> 0x018f }
            float r0 = r0 / r7
            float r7 = (float) r3     // Catch:{ IOException -> 0x018f }
            float r0 = r0 * r7
            int r0 = (int) r0     // Catch:{ IOException -> 0x018f }
            java.lang.String r7 = "img"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x018f }
            java.lang.String r9 = "w="
            r8.<init>(r9)     // Catch:{ IOException -> 0x018f }
            r8.append(r0)     // Catch:{ IOException -> 0x018f }
            java.lang.String r9 = ",h="
            r8.append(r9)     // Catch:{ IOException -> 0x018f }
            r8.append(r3)     // Catch:{ IOException -> 0x018f }
            java.lang.String r8 = r8.toString()     // Catch:{ IOException -> 0x018f }
            com.unionpay.mobile.android.utils.k.a(r7, r8)     // Catch:{ IOException -> 0x018f }
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createScaledBitmap(r14, r0, r3, r11)     // Catch:{ IOException -> 0x018f }
            if (r0 == r14) goto L_0x0135
            r14.recycle()     // Catch:{ IOException -> 0x018f }
        L_0x0135:
            android.graphics.drawable.BitmapDrawable r3 = new android.graphics.drawable.BitmapDrawable     // Catch:{ IOException -> 0x018f }
            android.content.Context r7 = r1.b     // Catch:{ IOException -> 0x018f }
            android.content.res.Resources r7 = r7.getResources()     // Catch:{ IOException -> 0x018f }
            r3.<init>(r7, r0)     // Catch:{ IOException -> 0x018f }
            goto L_0x0188
        L_0x0141:
            if (r7 == r0) goto L_0x0169
            if (r7 != r3) goto L_0x0169
            int r3 = r14.getHeight()     // Catch:{ IOException -> 0x018f }
            float r3 = (float) r3     // Catch:{ IOException -> 0x018f }
            int r7 = r14.getWidth()     // Catch:{ IOException -> 0x018f }
            float r7 = (float) r7     // Catch:{ IOException -> 0x018f }
            float r3 = r3 / r7
            float r7 = (float) r0     // Catch:{ IOException -> 0x018f }
            float r3 = r3 * r7
            int r3 = (int) r3     // Catch:{ IOException -> 0x018f }
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createScaledBitmap(r14, r0, r3, r11)     // Catch:{ IOException -> 0x018f }
            if (r0 == r14) goto L_0x015d
            r14.recycle()     // Catch:{ IOException -> 0x018f }
        L_0x015d:
            android.graphics.drawable.BitmapDrawable r3 = new android.graphics.drawable.BitmapDrawable     // Catch:{ IOException -> 0x018f }
            android.content.Context r7 = r1.b     // Catch:{ IOException -> 0x018f }
            android.content.res.Resources r7 = r7.getResources()     // Catch:{ IOException -> 0x018f }
            r3.<init>(r7, r0)     // Catch:{ IOException -> 0x018f }
            goto L_0x0188
        L_0x0169:
            android.graphics.drawable.BitmapDrawable r0 = new android.graphics.drawable.BitmapDrawable     // Catch:{ IOException -> 0x018f }
            android.content.Context r3 = r1.b     // Catch:{ IOException -> 0x018f }
            android.content.res.Resources r3 = r3.getResources()     // Catch:{ IOException -> 0x018f }
            r0.<init>(r3, r14)     // Catch:{ IOException -> 0x018f }
            goto L_0x0187
        L_0x0175:
            android.graphics.drawable.NinePatchDrawable r0 = new android.graphics.drawable.NinePatchDrawable     // Catch:{ IOException -> 0x018f }
            android.content.Context r3 = r1.b     // Catch:{ IOException -> 0x018f }
            android.content.res.Resources r13 = r3.getResources()     // Catch:{ IOException -> 0x018f }
            byte[] r15 = r14.getNinePatchChunk()     // Catch:{ IOException -> 0x018f }
            r17 = 0
            r12 = r0
            r12.<init>(r13, r14, r15, r16, r17)     // Catch:{ IOException -> 0x018f }
        L_0x0187:
            r3 = r0
        L_0x0188:
            r6.close()     // Catch:{ IOException -> 0x018f }
            r5.close()     // Catch:{ IOException -> 0x018f }
            goto L_0x0196
        L_0x018f:
            r0 = move-exception
            r0.printStackTrace()
            r3 = r4
            goto L_0x0196
        L_0x0195:
            r3 = r5
        L_0x0196:
            if (r3 == 0) goto L_0x01aa
            java.util.HashMap<java.lang.Integer, java.lang.ref.WeakReference<android.graphics.drawable.Drawable$ConstantState>> r0 = r1.f9704a
            java.lang.Integer r2 = java.lang.Integer.valueOf(r20)
            java.lang.ref.WeakReference r4 = new java.lang.ref.WeakReference
            android.graphics.drawable.Drawable$ConstantState r5 = r3.getConstantState()
            r4.<init>(r5)
            r0.put(r2, r4)
        L_0x01aa:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.resource.c.a(int, int, int):android.graphics.drawable.Drawable");
    }

    public final void a() {
        for (WeakReference<Drawable.ConstantState> weakReference : this.f9704a.values()) {
            Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
            if (constantState != null) {
                Drawable newDrawable = constantState.newDrawable();
                if (newDrawable instanceof BitmapDrawable) {
                    ((BitmapDrawable) newDrawable).getBitmap().recycle();
                }
            }
        }
        this.f9704a.clear();
        c = null;
    }
}
