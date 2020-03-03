package com.xiaomi.mishopsdk.widget.transformation;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;
import com.squareup.picasso.mishop.Transformation;

public final class RoundedTransformationBuilder {
    /* access modifiers changed from: private */
    public ColorStateList mBorderColor = ColorStateList.valueOf(-16777216);
    /* access modifiers changed from: private */
    public float mBorderWidth = 0.0f;
    /* access modifiers changed from: private */
    public float mCornerRadius = 0.0f;
    private final DisplayMetrics mDisplayMetrics = Resources.getSystem().getDisplayMetrics();
    /* access modifiers changed from: private */
    public boolean mOval = false;
    /* access modifiers changed from: private */
    public ImageView.ScaleType mScaleType = ImageView.ScaleType.FIT_CENTER;

    public RoundedTransformationBuilder scaleType(ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
        return this;
    }

    public RoundedTransformationBuilder cornerRadius(float f) {
        this.mCornerRadius = f;
        return this;
    }

    public RoundedTransformationBuilder cornerRadiusDp(float f) {
        this.mCornerRadius = TypedValue.applyDimension(1, f, this.mDisplayMetrics);
        return this;
    }

    public RoundedTransformationBuilder borderWidth(float f) {
        this.mBorderWidth = f;
        return this;
    }

    public RoundedTransformationBuilder borderWidthDp(float f) {
        this.mBorderWidth = TypedValue.applyDimension(1, f, this.mDisplayMetrics);
        return this;
    }

    public RoundedTransformationBuilder borderColor(int i) {
        this.mBorderColor = ColorStateList.valueOf(i);
        return this;
    }

    public RoundedTransformationBuilder borderColor(ColorStateList colorStateList) {
        this.mBorderColor = colorStateList;
        return this;
    }

    public RoundedTransformationBuilder oval(boolean z) {
        this.mOval = z;
        return this;
    }

    public Transformation build() {
        return new Transformation() {
            public Bitmap transform(Bitmap bitmap) {
                Bitmap bitmap2 = RoundedDrawable.fromBitmap(bitmap).setScaleType(RoundedTransformationBuilder.this.mScaleType).setCornerRadius(RoundedTransformationBuilder.this.mCornerRadius).setBorderWidth(RoundedTransformationBuilder.this.mBorderWidth).setBorderColor(RoundedTransformationBuilder.this.mBorderColor).setOval(RoundedTransformationBuilder.this.mOval).toBitmap();
                if (!bitmap.equals(bitmap2)) {
                    bitmap.recycle();
                }
                return bitmap2;
            }

            public String key() {
                return "r:" + RoundedTransformationBuilder.this.mCornerRadius + "b:" + RoundedTransformationBuilder.this.mBorderWidth + "c:" + RoundedTransformationBuilder.this.mBorderColor + "o:" + RoundedTransformationBuilder.this.mOval;
            }
        };
    }
}
