package com.mics.util;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.ViewGroup;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.mics.R;
import java.lang.reflect.Field;

public class DraweeViewUtil {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public SimpleDraweeView f7774a;
    private ControllerListener<ImageInfo> b = new BaseControllerListener<ImageInfo>() {
        /* renamed from: a */
        public void onIntermediateImageSet(String str, @Nullable ImageInfo imageInfo) {
        }

        public void onFailure(String str, Throwable th) {
        }

        /* renamed from: a */
        public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
            int i;
            if (imageInfo != null) {
                int width = imageInfo.getWidth();
                int height = imageInfo.getHeight();
                if (DraweeViewUtil.this.f7774a != null) {
                    ViewGroup.LayoutParams layoutParams = DraweeViewUtil.this.f7774a.getLayoutParams();
                    float applyDimension = TypedValue.applyDimension(1, 64.0f, DraweeViewUtil.this.f7774a.getResources().getDisplayMetrics());
                    int i2 = -2;
                    if (width > height) {
                        float applyDimension2 = TypedValue.applyDimension(1, 160.0f, DraweeViewUtil.this.f7774a.getResources().getDisplayMetrics());
                        int i3 = ((float) width) > applyDimension2 ? (int) applyDimension2 : width;
                        i = ((float) i3) < applyDimension ? (int) applyDimension : i3;
                    } else {
                        float applyDimension3 = TypedValue.applyDimension(1, 288.0f, DraweeViewUtil.this.f7774a.getResources().getDisplayMetrics());
                        int i4 = ((float) height) > applyDimension3 ? (int) applyDimension3 : height;
                        i2 = ((float) i4) < applyDimension ? (int) applyDimension : i4;
                        i = -2;
                    }
                    layoutParams.width = i;
                    layoutParams.height = i2;
                    DraweeViewUtil.this.f7774a.setAspectRatio((((float) width) * 1.0f) / ((float) height));
                    DraweeViewUtil.this.f7774a.setLayoutParams(layoutParams);
                }
            }
        }
    };

    private DraweeViewUtil() {
    }

    public static DraweeViewUtil a() {
        return new DraweeViewUtil();
    }

    public void a(SimpleDraweeView simpleDraweeView, String str) {
        float[] a2;
        this.f7774a = simpleDraweeView;
        AbstractDraweeController build = ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setControllerListener(this.b)).setUri(str).build();
        GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder = new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources());
        Drawable background = simpleDraweeView.getBackground();
        if (!(background == null || (a2 = a(background)) == null || a2.length != 8)) {
            a(simpleDraweeView, a2);
            genericDraweeHierarchyBuilder.setRoundingParams(RoundingParams.fromCornersRadii(a2));
        }
        simpleDraweeView.setHierarchy(genericDraweeHierarchyBuilder.setFadeDuration(300).setProgressBarImage((Drawable) new ProgressBarDrawable()).setPlaceholderImage(R.drawable.mics_placeholder_image).build());
        simpleDraweeView.setController(build);
    }

    private void a(SimpleDraweeView simpleDraweeView, float[] fArr) {
        float paddingTop = (float) simpleDraweeView.getPaddingTop();
        float paddingRight = (float) simpleDraweeView.getPaddingRight();
        float paddingBottom = (float) simpleDraweeView.getPaddingBottom();
        float paddingLeft = (float) simpleDraweeView.getPaddingLeft();
        if (fArr[0] > paddingLeft) {
            fArr[0] = fArr[0] - paddingLeft;
        }
        if (fArr[1] > paddingTop) {
            fArr[1] = fArr[1] - paddingTop;
        }
        if (fArr[2] > paddingLeft) {
            fArr[2] = fArr[2] - paddingTop;
        }
        if (fArr[3] > paddingTop) {
            fArr[3] = fArr[3] - paddingRight;
        }
        if (fArr[4] > paddingLeft) {
            fArr[4] = fArr[4] - paddingRight;
        }
        if (fArr[5] > paddingTop) {
            fArr[5] = fArr[5] - paddingBottom;
        }
        if (fArr[6] > paddingLeft) {
            fArr[6] = fArr[6] - paddingBottom;
        }
        if (fArr[7] > paddingTop) {
            fArr[7] = fArr[7] - paddingLeft;
        }
    }

    private static float[] a(Drawable drawable) {
        Drawable current = drawable.getCurrent();
        if (!(current instanceof GradientDrawable)) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                float[] cornerRadii = ((GradientDrawable) current).getCornerRadii();
                if (cornerRadii != null) {
                    return cornerRadii;
                }
                float cornerRadius = ((GradientDrawable) current).getCornerRadius();
                return new float[]{cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius};
            } catch (Exception unused) {
                float cornerRadius2 = ((GradientDrawable) current).getCornerRadius();
                return new float[]{cornerRadius2, cornerRadius2, cornerRadius2, cornerRadius2, cornerRadius2, cornerRadius2, cornerRadius2, cornerRadius2};
            }
        } else {
            try {
                Class<?> cls = Class.forName("android.graphics.drawable.GradientDrawable$GradientState");
                Field declaredField = cls.getDeclaredField("mRadiusArray");
                declaredField.setAccessible(true);
                float[] fArr = (float[]) declaredField.get(current.getConstantState());
                if (fArr != null) {
                    return fArr;
                }
                Field declaredField2 = cls.getDeclaredField("mRadius");
                declaredField2.setAccessible(true);
                float floatValue = ((Float) declaredField2.get(current.getConstantState())).floatValue();
                return new float[]{floatValue, floatValue, floatValue, floatValue, floatValue, floatValue, floatValue, floatValue};
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
