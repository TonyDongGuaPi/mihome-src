package com.taobao.weex.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ImageDrawable extends PaintDrawable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private int bitmapHeight;
    private int bitmapWidth;
    private float[] radii;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3260895186587483229L, "com/taobao/weex/utils/ImageDrawable", 55);
        $jacocoData = a2;
        return a2;
    }

    public static Drawable createImageDrawable(@Nullable Drawable drawable, @NonNull ImageView.ScaleType scaleType, @Nullable float[] fArr, int i, int i2, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (z) {
            $jacocoInit[0] = true;
        } else if (i <= 0) {
            $jacocoInit[1] = true;
        } else if (i2 <= 0) {
            $jacocoInit[2] = true;
        } else {
            if (!(drawable instanceof BitmapDrawable)) {
                $jacocoInit[3] = true;
            } else {
                $jacocoInit[4] = true;
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                if (bitmap == null) {
                    $jacocoInit[5] = true;
                } else {
                    $jacocoInit[6] = true;
                    ImageDrawable imageDrawable = new ImageDrawable();
                    $jacocoInit[7] = true;
                    imageDrawable.bitmapWidth = bitmap.getWidth();
                    $jacocoInit[8] = true;
                    imageDrawable.bitmapHeight = bitmap.getHeight();
                    $jacocoInit[9] = true;
                    BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                    $jacocoInit[10] = true;
                    updateShaderAndSize(scaleType, i, i2, imageDrawable, bitmapShader);
                    $jacocoInit[11] = true;
                    imageDrawable.getPaint().setShader(bitmapShader);
                    $jacocoInit[12] = true;
                    return imageDrawable;
                }
            }
            if (!(drawable instanceof ImageDrawable)) {
                $jacocoInit[13] = true;
            } else {
                ImageDrawable imageDrawable2 = (ImageDrawable) drawable;
                $jacocoInit[14] = true;
                if (imageDrawable2.getPaint() == null) {
                    $jacocoInit[15] = true;
                } else {
                    $jacocoInit[16] = true;
                    if (!(imageDrawable2.getPaint().getShader() instanceof BitmapShader)) {
                        $jacocoInit[17] = true;
                    } else {
                        $jacocoInit[18] = true;
                        $jacocoInit[19] = true;
                        updateShaderAndSize(scaleType, i, i2, imageDrawable2, (BitmapShader) imageDrawable2.getPaint().getShader());
                        $jacocoInit[20] = true;
                        return imageDrawable2;
                    }
                }
            }
        }
        $jacocoInit[21] = true;
        return drawable;
    }

    private static void updateShaderAndSize(@NonNull ImageView.ScaleType scaleType, int i, int i2, ImageDrawable imageDrawable, BitmapShader bitmapShader) {
        boolean[] $jacocoInit = $jacocoInit();
        Matrix createShaderMatrix = createShaderMatrix(scaleType, i, i2, imageDrawable.bitmapWidth, imageDrawable.bitmapHeight);
        if (scaleType != ImageView.ScaleType.FIT_CENTER) {
            $jacocoInit[22] = true;
        } else {
            $jacocoInit[23] = true;
            RectF rectF = new RectF(0.0f, 0.0f, (float) imageDrawable.bitmapWidth, (float) imageDrawable.bitmapHeight);
            RectF rectF2 = new RectF();
            $jacocoInit[24] = true;
            createShaderMatrix.mapRect(rectF2, rectF);
            $jacocoInit[25] = true;
            i = (int) rectF2.width();
            $jacocoInit[26] = true;
            i2 = (int) rectF2.height();
            $jacocoInit[27] = true;
            createShaderMatrix = createShaderMatrix(scaleType, i, i2, imageDrawable.bitmapWidth, imageDrawable.bitmapHeight);
            $jacocoInit[28] = true;
        }
        imageDrawable.setIntrinsicWidth(i);
        $jacocoInit[29] = true;
        imageDrawable.setIntrinsicHeight(i2);
        $jacocoInit[30] = true;
        bitmapShader.setLocalMatrix(createShaderMatrix);
        $jacocoInit[31] = true;
    }

    @NonNull
    private static Matrix createShaderMatrix(@NonNull ImageView.ScaleType scaleType, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        boolean[] $jacocoInit = $jacocoInit();
        if (i3 * i2 > i4 * i) {
            f3 = ((float) i2) / ((float) i4);
            f2 = (((float) i) - (((float) i3) * f3)) * 0.5f;
            $jacocoInit[32] = true;
            f = 0.0f;
        } else {
            f3 = ((float) i) / ((float) i3);
            $jacocoInit[33] = true;
            f = (((float) i2) - (((float) i4) * f3)) * 0.5f;
            f2 = 0.0f;
        }
        Matrix matrix = new Matrix();
        if (scaleType == ImageView.ScaleType.FIT_XY) {
            $jacocoInit[34] = true;
            matrix.setScale(((float) i) / ((float) i3), ((float) i2) / ((float) i4));
            $jacocoInit[35] = true;
        } else if (scaleType == ImageView.ScaleType.FIT_CENTER) {
            $jacocoInit[36] = true;
            RectF rectF = new RectF(0.0f, 0.0f, (float) i3, (float) i4);
            $jacocoInit[37] = true;
            RectF rectF2 = new RectF(0.0f, 0.0f, (float) i, (float) i2);
            $jacocoInit[38] = true;
            matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
            $jacocoInit[39] = true;
        } else if (scaleType != ImageView.ScaleType.CENTER_CROP) {
            $jacocoInit[40] = true;
        } else {
            $jacocoInit[41] = true;
            matrix.setScale(f3, f3);
            $jacocoInit[42] = true;
            matrix.postTranslate(f2 + 0.5f, f + 0.5f);
            $jacocoInit[43] = true;
        }
        $jacocoInit[44] = true;
        return matrix;
    }

    private ImageDrawable() {
        $jacocoInit()[45] = true;
    }

    public void setCornerRadii(float[] fArr) {
        boolean[] $jacocoInit = $jacocoInit();
        this.radii = fArr;
        $jacocoInit[46] = true;
        super.setCornerRadii(fArr);
        $jacocoInit[47] = true;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Shape shape, Canvas canvas, Paint paint) {
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT != 21) {
            $jacocoInit[48] = true;
        } else {
            $jacocoInit[49] = true;
            paint.setAntiAlias(false);
            $jacocoInit[50] = true;
        }
        super.onDraw(shape, canvas, paint);
        $jacocoInit[51] = true;
    }

    @Nullable
    public float[] getCornerRadii() {
        boolean[] $jacocoInit = $jacocoInit();
        float[] fArr = this.radii;
        $jacocoInit[52] = true;
        return fArr;
    }

    public int getBitmapHeight() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.bitmapHeight;
        $jacocoInit[53] = true;
        return i;
    }

    public int getBitmapWidth() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.bitmapWidth;
        $jacocoInit[54] = true;
        return i;
    }
}
