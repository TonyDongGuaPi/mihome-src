package com.mi.global.shop.widget.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;

class ImageViewTouchBase extends ImageView {
    static final float SCALE_RATE = 1.25f;
    private static final String TAG = "ImageViewTouchBase";
    protected Matrix mBaseMatrix = new Matrix();
    float mBaseZoom;
    protected final RotateBitmap mBitmapDisplayed = new RotateBitmap((Bitmap) null);
    protected final Matrix mDisplayMatrix = new Matrix();
    protected Handler mHandler = new Handler();
    private final float[] mMatrixValues = new float[9];
    float mMaxZoom;
    float mMinZoom;
    private Runnable mOnLayoutRunnable = null;
    private Recycler mRecycler;
    protected Matrix mSuppMatrix = new Matrix();
    int mThisHeight = -1;
    int mThisWidth = -1;

    public interface Recycler {
        void a(Bitmap bitmap);
    }

    public void setRecycler(Recycler recycler) {
        this.mRecycler = recycler;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mThisWidth = i3 - i;
        this.mThisHeight = i4 - i2;
        Runnable runnable = this.mOnLayoutRunnable;
        if (runnable != null) {
            this.mOnLayoutRunnable = null;
            runnable.run();
        }
        if (this.mBitmapDisplayed.b() != null) {
            getProperBaseMatrix(this.mBitmapDisplayed, this.mBaseMatrix);
            setImageMatrix(getImageViewMatrix());
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        setImageBitmap(bitmap, 0);
    }

    private void setImageBitmap(Bitmap bitmap, int i) {
        super.setImageBitmap(bitmap);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            drawable.setDither(true);
        }
        Bitmap b = this.mBitmapDisplayed.b();
        this.mBitmapDisplayed.a(bitmap);
        this.mBitmapDisplayed.a(i);
        if (b != null && b != bitmap && this.mRecycler != null) {
            this.mRecycler.a(b);
        }
    }

    public void clear() {
        setImageBitmapResetBase((Bitmap) null, true);
    }

    public void setImageBitmapResetBase(Bitmap bitmap, boolean z) {
        setImageRotateBitmapResetBase(new RotateBitmap(bitmap), z);
    }

    public void setImageRotateBitmapResetBase(final RotateBitmap rotateBitmap, final boolean z) {
        if (getWidth() <= 0) {
            this.mOnLayoutRunnable = new Runnable() {
                public void run() {
                    ImageViewTouchBase.this.setImageRotateBitmapResetBase(rotateBitmap, z);
                }
            };
            return;
        }
        if (rotateBitmap.b() != null) {
            getProperBaseMatrix(rotateBitmap, this.mBaseMatrix);
            setImageBitmap(rotateBitmap.b(), rotateBitmap.a());
        } else {
            this.mBaseMatrix.reset();
            setImageBitmap((Bitmap) null);
        }
        if (z) {
            this.mSuppMatrix.reset();
        }
        setImageMatrix(getImageViewMatrix());
        this.mMaxZoom = maxZoom();
        this.mMinZoom = minZoom();
        this.mBaseZoom = getScale(this.mBaseMatrix);
    }

    /* access modifiers changed from: protected */
    public void center(boolean z, boolean z2) {
        centerCharge(z, z2, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void centerCharge(boolean r7, boolean r8, boolean r9) {
        /*
            r6 = this;
            com.mi.global.shop.widget.gallery.RotateBitmap r0 = r6.mBitmapDisplayed
            android.graphics.Bitmap r0 = r0.b()
            if (r0 != 0) goto L_0x0009
            return
        L_0x0009:
            android.graphics.Matrix r0 = r6.getImageViewMatrix()
            android.graphics.RectF r1 = new android.graphics.RectF
            com.mi.global.shop.widget.gallery.RotateBitmap r2 = r6.mBitmapDisplayed
            android.graphics.Bitmap r2 = r2.b()
            int r2 = r2.getWidth()
            float r2 = (float) r2
            com.mi.global.shop.widget.gallery.RotateBitmap r3 = r6.mBitmapDisplayed
            android.graphics.Bitmap r3 = r3.b()
            int r3 = r3.getHeight()
            float r3 = (float) r3
            r4 = 0
            r1.<init>(r4, r4, r2, r3)
            r0.mapRect(r1)
            float r0 = r1.height()
            float r2 = r1.width()
            r3 = 1073741824(0x40000000, float:2.0)
            if (r8 == 0) goto L_0x0060
            int r8 = r6.getHeight()
            float r8 = (float) r8
            int r5 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r5 >= 0) goto L_0x0047
            float r8 = r8 - r0
            float r8 = r8 / r3
            float r0 = r1.top
            float r8 = r8 - r0
            goto L_0x0061
        L_0x0047:
            float r0 = r1.top
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0051
            float r8 = r1.top
            float r8 = -r8
            goto L_0x0061
        L_0x0051:
            float r0 = r1.bottom
            int r8 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r8 >= 0) goto L_0x0060
            int r8 = r6.getHeight()
            float r8 = (float) r8
            float r0 = r1.bottom
            float r8 = r8 - r0
            goto L_0x0061
        L_0x0060:
            r8 = 0
        L_0x0061:
            if (r7 == 0) goto L_0x0087
            int r7 = r6.getWidth()
            float r7 = (float) r7
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0073
            float r7 = r7 - r2
            float r7 = r7 / r3
            float r0 = r1.left
            float r4 = r7 - r0
            goto L_0x0087
        L_0x0073:
            float r0 = r1.left
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x007d
            float r7 = r1.left
            float r4 = -r7
            goto L_0x0087
        L_0x007d:
            float r0 = r1.right
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0087
            float r0 = r1.right
            float r4 = r7 - r0
        L_0x0087:
            r6.postTranslate(r4, r8)
            if (r9 == 0) goto L_0x008d
            goto L_0x0094
        L_0x008d:
            android.graphics.Matrix r7 = r6.getImageViewMatrix()
            r6.setImageMatrix(r7)
        L_0x0094:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.widget.gallery.ImageViewTouchBase.centerCharge(boolean, boolean, boolean):void");
    }

    /* access modifiers changed from: protected */
    public void centerWithAni(boolean z, boolean z2) {
        centerCharge(z, z2, true);
    }

    public ImageViewTouchBase(Context context) {
        super(context);
        init();
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    /* access modifiers changed from: protected */
    public float getValue(Matrix matrix, int i) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[i];
    }

    /* access modifiers changed from: protected */
    public float getScale(Matrix matrix) {
        return getValue(matrix, 0);
    }

    /* access modifiers changed from: protected */
    public float getScale() {
        return getScale(this.mSuppMatrix);
    }

    private void getProperBaseMatrix(RotateBitmap rotateBitmap, Matrix matrix) {
        float width = (float) getWidth();
        float height = (float) getHeight();
        float f = (float) rotateBitmap.f();
        float e = (float) rotateBitmap.e();
        matrix.reset();
        float min = Math.min(Math.min(width / f, 3.0f), Math.min(height / e, 3.0f));
        matrix.postConcat(rotateBitmap.c());
        matrix.postScale(min, min);
        matrix.postTranslate((width - (f * min)) / 2.0f, (height - (e * min)) / 2.0f);
    }

    /* access modifiers changed from: protected */
    public Matrix getImageViewMatrix() {
        this.mDisplayMatrix.set(this.mBaseMatrix);
        this.mDisplayMatrix.postConcat(this.mSuppMatrix);
        return this.mDisplayMatrix;
    }

    /* access modifiers changed from: protected */
    public float maxZoom() {
        if (this.mBitmapDisplayed.b() == null) {
            return 1.0f;
        }
        return Math.max(((float) this.mBitmapDisplayed.f()) / ((float) this.mThisWidth), ((float) this.mBitmapDisplayed.e()) / ((float) this.mThisHeight)) * 4.0f;
    }

    /* access modifiers changed from: protected */
    public float minZoom() {
        float scale = getScale(this.mBaseMatrix);
        if (scale < 1.0f) {
            return 1.0f;
        }
        return 1.0f / scale;
    }

    /* access modifiers changed from: protected */
    public void zoomTo(float f, float f2, float f3) {
        if (f > this.mMaxZoom) {
            f = this.mMaxZoom;
        }
        float scale = f / getScale();
        this.mSuppMatrix.postScale(scale, scale, f2, f3);
        setImageMatrix(getImageViewMatrix());
        center(true, true);
    }

    /* access modifiers changed from: protected */
    public void zoomTo(float f, float f2, float f3, float f4) {
        final float scale = (f - getScale()) / f4;
        final float scale2 = getScale();
        final long currentTimeMillis = System.currentTimeMillis();
        final float f5 = f4;
        final float f6 = f2;
        final float f7 = f3;
        this.mHandler.post(new Runnable() {
            public void run() {
                float min = Math.min(f5, (float) (System.currentTimeMillis() - currentTimeMillis));
                ImageViewTouchBase.this.zoomTo(scale2 + (scale * min), f6, f7);
                if (min < f5) {
                    ImageViewTouchBase.this.mHandler.post(this);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void zoomTo(float f) {
        zoomTo(f, ((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
    }

    /* access modifiers changed from: protected */
    public void zoomToPoint(float f, float f2, float f3) {
        float width = ((float) getWidth()) / 2.0f;
        float height = ((float) getHeight()) / 2.0f;
        panBy(width - f2, height - f3);
        zoomTo(f, width, height);
    }

    /* access modifiers changed from: protected */
    public void zoomToNoCenter(float f, float f2, float f3) {
        float scale = f / getScale();
        this.mSuppMatrix.postScale(scale, scale, f2, f3);
        setImageMatrix(getImageViewMatrix());
    }

    public class MatrixTransformAnimation extends Animation {

        /* renamed from: a  reason: collision with root package name */
        Matrix f7237a;
        Matrix b;

        public MatrixTransformAnimation(Matrix matrix, Matrix matrix2) {
            this.f7237a = matrix;
            this.b = matrix2;
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            Matrix matrix = transformation.getMatrix();
            float value = ImageViewTouchBase.this.getValue(this.f7237a, 0) / ImageViewTouchBase.this.getValue(this.b, 0);
            matrix.setScale(value, value);
        }
    }

    /* access modifiers changed from: protected */
    public void zoomToNoCenterValue(float f, float f2, float f3) {
        float scale = f / getScale();
        this.mSuppMatrix.postScale(scale, scale, f2, f3);
        getImageViewMatrix();
    }

    /* access modifiers changed from: protected */
    public void zoomToNoCenterWithAni(float f, float f2, float f3, float f4) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f, f2, f3, f4);
        scaleAnimation.setDuration(300);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        startAnimation(scaleAnimation);
    }

    /* access modifiers changed from: protected */
    public void zoomIn() {
        zoomIn(SCALE_RATE);
    }

    /* access modifiers changed from: protected */
    public void zoomOut() {
        zoomOut(SCALE_RATE);
    }

    /* access modifiers changed from: protected */
    public void zoomIn(float f) {
        if (getScale() < this.mMaxZoom && this.mBitmapDisplayed.b() != null) {
            this.mSuppMatrix.postScale(f, f, ((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
            setImageMatrix(getImageViewMatrix());
        }
    }

    /* access modifiers changed from: protected */
    public void zoomOut(float f) {
        if (this.mBitmapDisplayed.b() != null) {
            float width = ((float) getWidth()) / 2.0f;
            float height = ((float) getHeight()) / 2.0f;
            Matrix matrix = new Matrix(this.mSuppMatrix);
            float f2 = 1.0f / f;
            matrix.postScale(f2, f2, width, height);
            if (getScale(matrix) < this.mMinZoom) {
                this.mSuppMatrix.setScale(this.mMinZoom, this.mMinZoom, width, height);
            } else {
                this.mSuppMatrix.postScale(f2, f2, width, height);
            }
            setImageMatrix(getImageViewMatrix());
            center(true, true);
        }
    }

    /* access modifiers changed from: protected */
    public void postTranslate(float f, float f2) {
        this.mSuppMatrix.postTranslate(f, f2);
    }

    /* access modifiers changed from: protected */
    public void panBy(float f, float f2) {
        postTranslate(f, f2);
        setImageMatrix(getImageViewMatrix());
    }
}
