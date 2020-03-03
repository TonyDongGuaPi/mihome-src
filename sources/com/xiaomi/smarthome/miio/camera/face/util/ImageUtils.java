package com.xiaomi.smarthome.miio.camera.face.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.View;
import com.mijia.debug.SDKLog;
import com.xiaomi.smarthome.miio.camera.face.widget.CameraCircleView;

public class ImageUtils {
    public static final String TAG = "ImageUtils";

    public static Bitmap getRotatedBitmap(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }

    public static Bitmap getCroped(Bitmap bitmap, CameraCircleView cameraCircleView, View view) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        String str = TAG;
        SDKLog.b(str, width + "-" + height);
        if (height > width) {
            float measuredHeight = (((float) height) * cameraCircleView.offset) / ((float) view.getMeasuredHeight());
            String str2 = TAG;
            SDKLog.b(str2, "diff=" + measuredHeight);
            return Bitmap.createBitmap(bitmap, 0, 0, width, width);
        }
        int i = (width - height) / 2;
        String str3 = TAG;
        SDKLog.b(str3, "diff=" + i);
        return Bitmap.createBitmap(bitmap, i, 0, height, height);
    }

    public static Bitmap getFlipBitmap(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.setScale(-1.0f, 1.0f);
        matrix.postTranslate((float) bitmap.getWidth(), 0.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }
}
