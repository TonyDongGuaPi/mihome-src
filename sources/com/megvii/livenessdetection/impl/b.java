package com.megvii.livenessdetection.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.YuvImage;
import com.megvii.livenessdetection.DetectionConfig;
import com.megvii.livenessdetection.DetectionFrame;
import com.megvii.livenessdetection.Detector;
import com.megvii.livenessdetection.bean.FaceInfo;
import com.megvii.livenessdetection.obf.d;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class b extends DetectionFrame {

    /* renamed from: a  reason: collision with root package name */
    private int f6683a;
    private int b;
    private Detector.DetectionType c;
    private byte[] d;
    private int e;

    public b(byte[] bArr, int i, int i2, int i3, Detector.DetectionType detectionType) {
        this.f6683a = i;
        this.b = i2;
        this.e = i3;
        System.currentTimeMillis();
        this.c = detectionType;
        this.d = bArr;
    }

    public final int getRotation() {
        return this.e;
    }

    public final Detector.DetectionType a() {
        return this.c;
    }

    public final int getImageWidth() {
        return this.f6683a;
    }

    public final int getImageHeight() {
        return this.b;
    }

    public final byte[] getYUVData() {
        return this.d;
    }

    private synchronized byte[] a(int i, Rect rect) {
        return a(i, rect, 80);
    }

    private synchronized byte[] a(int i, Rect rect, int i2) {
        int i3 = i;
        int i4 = i2;
        synchronized (this) {
            if (!hasFace()) {
                return null;
            }
            YuvImage yuvImage = new YuvImage(this.d, 17, this.f6683a, this.b, (int[]) null);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Matrix matrix = new Matrix();
            matrix.postRotate((float) (360 - this.e), 0.5f, 0.5f);
            RectF rectF = new RectF();
            matrix.mapRect(rectF, this.mFaceInfo.position);
            Rect rect2 = new Rect();
            rect2.left = (int) (rectF.left * ((float) this.f6683a));
            rect2.right = (int) (rectF.right * ((float) this.f6683a));
            rect2.top = (int) (rectF.top * ((float) this.b));
            rect2.bottom = (int) (rectF.bottom * ((float) this.b));
            Matrix matrix2 = new Matrix();
            matrix2.postScale(1.5f, 1.5f, (float) rect2.centerX(), (float) rect2.centerY());
            RectF rectF2 = new RectF();
            matrix2.mapRect(rectF2, new RectF(rect2));
            rectF2.left = Math.max(0.0f, rectF2.left);
            rectF2.top = Math.max(0.0f, rectF2.top);
            rectF2.right = Math.min(rectF2.right, (float) this.f6683a);
            rectF2.bottom = Math.min(rectF2.bottom, (float) this.b);
            yuvImage.compressToJpeg(new Rect((int) rectF2.left, (int) rectF2.top, (int) rectF2.right, (int) rectF2.bottom), i4, byteArrayOutputStream);
            try {
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                d.a(e2.toString());
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            Matrix matrix3 = new Matrix();
            matrix3.postRotate((float) this.e);
            if (i3 != -1) {
                float max = ((float) Math.max(decodeByteArray.getWidth(), decodeByteArray.getHeight())) / ((float) i3);
                if (max > 1.0f) {
                    float f = 1.0f / max;
                    matrix3.postScale(f, f);
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix3, true);
            float centerX = ((float) rect2.centerX()) - rectF2.left;
            float centerY = ((float) rect2.centerY()) - rectF2.top;
            Matrix matrix4 = new Matrix();
            matrix4.setRotate((float) this.e, 0.5f, 0.5f);
            float[] fArr = new float[2];
            matrix4.mapPoints(fArr, new float[]{centerX / rectF2.width(), centerY / rectF2.height()});
            float width = fArr[0] * ((float) createBitmap.getWidth());
            float height = fArr[1] * ((float) createBitmap.getHeight());
            float max2 = ((float) Math.max(createBitmap.getWidth(), createBitmap.getHeight())) / 1.5f;
            Rect rect3 = rect == null ? new Rect() : rect;
            float f2 = max2 / 2.0f;
            rect3.left = (int) (width - f2);
            rect3.top = (int) (height - f2);
            rect3.right = (int) (width + f2);
            rect3.bottom = (int) (height + f2);
            String a2 = com.megvii.livenessdetection.obf.b.a(rect3);
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            createBitmap.compress(Bitmap.CompressFormat.JPEG, i4, byteArrayOutputStream2);
            try {
                byteArrayOutputStream2.close();
                byte[] a3 = EncodeImpl.a(byteArrayOutputStream2.toByteArray(), false, false, 1824058797, a2);
                return a3;
            } catch (IOException unused) {
                return null;
            }
        }
    }

    private synchronized byte[] b(int i, Rect rect, int i2) {
        byte[] byteArray;
        YuvImage yuvImage = new YuvImage(this.d, 17, this.f6683a, this.b, (int[]) null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, this.f6683a, this.b), i2, byteArrayOutputStream);
        try {
            byteArrayOutputStream.close();
        } catch (IOException e2) {
            d.a(e2.toString());
        }
        byte[] byteArray2 = byteArrayOutputStream.toByteArray();
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray2, 0, byteArray2.length);
        Matrix matrix = new Matrix();
        matrix.postRotate((float) this.e);
        if (i != -1) {
            float max = ((float) Math.max(decodeByteArray.getWidth(), decodeByteArray.getHeight())) / ((float) i);
            if (max > 1.0f) {
                float f = 1.0f / max;
                matrix.postScale(f, f);
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, true);
        if (rect == null) {
            rect = new Rect();
        }
        if (hasFace()) {
            rect.left = (int) (this.mFaceInfo.position.left * ((float) createBitmap.getWidth()));
            rect.top = (int) (this.mFaceInfo.position.top * ((float) createBitmap.getHeight()));
            rect.right = (int) (this.mFaceInfo.position.right * ((float) createBitmap.getWidth()));
            rect.bottom = (int) (this.mFaceInfo.position.bottom * ((float) createBitmap.getHeight()));
        }
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream2);
        try {
            byteArrayOutputStream2.close();
            byteArray = byteArrayOutputStream2.toByteArray();
            if (hasFace()) {
                byteArray = EncodeImpl.a(byteArray, false, false, 1824058797, com.megvii.livenessdetection.obf.b.a(rect));
            }
        } catch (IOException unused) {
            return null;
        }
        return byteArray;
    }

    public final synchronized byte[] getCroppedFaceImageData() {
        return a(-1, (Rect) null);
    }

    public final synchronized byte[] getCroppedFaceImageData(int i) {
        return a(i, (Rect) null);
    }

    public final byte[] getCroppedFaceImageData(Rect rect) {
        return a(-1, rect);
    }

    public final byte[] getCroppedFaceImageData(int i, Rect rect) {
        return a(i, rect);
    }

    public final byte[] getEncodedFaceImageData(int i, int i2, Rect rect) {
        return EncodeImpl.a(getCroppedFaceImageData(i2, rect), true, true, i);
    }

    public final byte[] getImageData(Rect rect, boolean z, int i, int i2, boolean z2, boolean z3, int i3) {
        if (((z2 || z3) && i3 < 0) || i < 0 || i > 100) {
            return null;
        }
        if (z) {
            if (i2 <= 0) {
                i2 = -1;
            }
            return EncodeImpl.a(a(i2, rect, i), z2, z3, i3);
        }
        if (i2 <= 0) {
            i2 = -1;
        }
        return EncodeImpl.a(b(i2, rect, i), z2, z3, i3);
    }

    public final boolean a(DetectionFrame detectionFrame) {
        return detectionFrame == null || !detectionFrame.hasFace() || (this.mFaceInfo == null ? 0.0f : this.mFaceInfo.smoothQuality) > detectionFrame.getFaceInfo().smoothQuality;
    }

    public final void a(String str, DetectionConfig detectionConfig, com.megvii.livenessdetection.obf.b bVar) {
        this.mFaceInfo = FaceInfo.FaceInfoFactory.createFaceInfo(str);
        bVar.a(this.mFaceInfo);
    }
}
