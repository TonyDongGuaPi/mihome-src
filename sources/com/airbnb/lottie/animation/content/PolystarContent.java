package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class PolystarContent implements KeyPathElementContent, PathContent, BaseKeyframeAnimation.AnimationListener {
    private static final float POLYGON_MAGIC_NUMBER = 0.25f;
    private static final float POLYSTAR_MAGIC_NUMBER = 0.47829f;
    @Nullable
    private final BaseKeyframeAnimation<?, Float> innerRadiusAnimation;
    @Nullable
    private final BaseKeyframeAnimation<?, Float> innerRoundednessAnimation;
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<?, Float> outerRadiusAnimation;
    private final BaseKeyframeAnimation<?, Float> outerRoundednessAnimation;
    private final Path path = new Path();
    private final BaseKeyframeAnimation<?, Float> pointsAnimation;
    private final BaseKeyframeAnimation<?, PointF> positionAnimation;
    private final BaseKeyframeAnimation<?, Float> rotationAnimation;
    @Nullable
    private TrimPathContent trimPath;
    private final PolystarShape.Type type;

    public PolystarContent(LottieDrawable lottieDrawable2, BaseLayer baseLayer, PolystarShape polystarShape) {
        this.lottieDrawable = lottieDrawable2;
        this.name = polystarShape.getName();
        this.type = polystarShape.getType();
        this.pointsAnimation = polystarShape.getPoints().createAnimation();
        this.positionAnimation = polystarShape.getPosition().createAnimation();
        this.rotationAnimation = polystarShape.getRotation().createAnimation();
        this.outerRadiusAnimation = polystarShape.getOuterRadius().createAnimation();
        this.outerRoundednessAnimation = polystarShape.getOuterRoundedness().createAnimation();
        if (this.type == PolystarShape.Type.Star) {
            this.innerRadiusAnimation = polystarShape.getInnerRadius().createAnimation();
            this.innerRoundednessAnimation = polystarShape.getInnerRoundedness().createAnimation();
        } else {
            this.innerRadiusAnimation = null;
            this.innerRoundednessAnimation = null;
        }
        baseLayer.addAnimation(this.pointsAnimation);
        baseLayer.addAnimation(this.positionAnimation);
        baseLayer.addAnimation(this.rotationAnimation);
        baseLayer.addAnimation(this.outerRadiusAnimation);
        baseLayer.addAnimation(this.outerRoundednessAnimation);
        if (this.type == PolystarShape.Type.Star) {
            baseLayer.addAnimation(this.innerRadiusAnimation);
            baseLayer.addAnimation(this.innerRoundednessAnimation);
        }
        this.pointsAnimation.addUpdateListener(this);
        this.positionAnimation.addUpdateListener(this);
        this.rotationAnimation.addUpdateListener(this);
        this.outerRadiusAnimation.addUpdateListener(this);
        this.outerRoundednessAnimation.addUpdateListener(this);
        if (this.type == PolystarShape.Type.Star) {
            this.innerRadiusAnimation.addUpdateListener(this);
            this.innerRoundednessAnimation.addUpdateListener(this);
        }
    }

    public void onValueChanged() {
        invalidate();
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    public void setContents(List<Content> list, List<Content> list2) {
        for (int i = 0; i < list.size(); i++) {
            Content content = list.get(i);
            if (content instanceof TrimPathContent) {
                TrimPathContent trimPathContent = (TrimPathContent) content;
                if (trimPathContent.getType() == ShapeTrimPath.Type.Simultaneously) {
                    this.trimPath = trimPathContent;
                    this.trimPath.addListener(this);
                }
            }
        }
    }

    public Path getPath() {
        if (this.isPathValid) {
            return this.path;
        }
        this.path.reset();
        switch (this.type) {
            case Star:
                createStarPath();
                break;
            case Polygon:
                createPolygonPath();
                break;
        }
        this.path.close();
        Utils.applyTrimPathIfNeeded(this.path, this.trimPath);
        this.isPathValid = true;
        return this.path;
    }

    public String getName() {
        return this.name;
    }

    private void createStarPath() {
        float f;
        double d;
        float f2;
        float f3;
        float f4;
        double d2;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        float f13;
        float floatValue = this.pointsAnimation.getValue().floatValue();
        double radians = Math.toRadians((this.rotationAnimation == null ? 0.0d : (double) this.rotationAnimation.getValue().floatValue()) - 90.0d);
        double d3 = (double) floatValue;
        Double.isNaN(d3);
        float f14 = (float) (6.283185307179586d / d3);
        float f15 = f14 / 2.0f;
        float f16 = floatValue - ((float) ((int) floatValue));
        if (f16 != 0.0f) {
            double d4 = (double) ((1.0f - f16) * f15);
            Double.isNaN(d4);
            radians += d4;
        }
        float floatValue2 = this.outerRadiusAnimation.getValue().floatValue();
        float floatValue3 = this.innerRadiusAnimation.getValue().floatValue();
        float floatValue4 = this.innerRoundednessAnimation != null ? this.innerRoundednessAnimation.getValue().floatValue() / 100.0f : 0.0f;
        float floatValue5 = this.outerRoundednessAnimation != null ? this.outerRoundednessAnimation.getValue().floatValue() / 100.0f : 0.0f;
        if (f16 != 0.0f) {
            f2 = ((floatValue2 - floatValue3) * f16) + floatValue3;
            double d5 = (double) f2;
            double cos = Math.cos(radians);
            Double.isNaN(d5);
            d = d3;
            f4 = (float) (d5 * cos);
            double sin = Math.sin(radians);
            Double.isNaN(d5);
            f3 = (float) (d5 * sin);
            this.path.moveTo(f4, f3);
            double d6 = (double) ((f14 * f16) / 2.0f);
            Double.isNaN(d6);
            d2 = radians + d6;
            f = floatValue2;
        } else {
            d = d3;
            float f17 = floatValue2;
            double d7 = (double) f17;
            double cos2 = Math.cos(radians);
            Double.isNaN(d7);
            float f18 = (float) (cos2 * d7);
            double sin2 = Math.sin(radians);
            Double.isNaN(d7);
            float f19 = (float) (d7 * sin2);
            this.path.moveTo(f18, f19);
            f = f17;
            double d8 = (double) f15;
            Double.isNaN(d8);
            d2 = radians + d8;
            f4 = f18;
            f3 = f19;
            f2 = 0.0f;
        }
        double ceil = Math.ceil(d) * 2.0d;
        int i = 0;
        double d9 = d2;
        float f20 = f4;
        float f21 = f3;
        boolean z = false;
        while (true) {
            double d10 = (double) i;
            if (d10 < ceil) {
                float f22 = z ? f : floatValue3;
                if (f2 == 0.0f || d10 != ceil - 2.0d) {
                    f5 = f22;
                    f6 = f15;
                } else {
                    f5 = f22;
                    f6 = (f14 * f16) / 2.0f;
                }
                if (f2 == 0.0f || d10 != ceil - 1.0d) {
                    f7 = f2;
                    f8 = f5;
                } else {
                    f8 = f2;
                    f7 = f8;
                }
                double d11 = (double) f8;
                double cos3 = Math.cos(d9);
                Double.isNaN(d11);
                float f23 = f6;
                float f24 = f14;
                float f25 = (float) (d11 * cos3);
                double sin3 = Math.sin(d9);
                Double.isNaN(d11);
                float f26 = (float) (d11 * sin3);
                if (floatValue4 == 0.0f && floatValue5 == 0.0f) {
                    this.path.lineTo(f25, f26);
                    f9 = f26;
                    f12 = floatValue3;
                    f11 = floatValue4;
                    f10 = floatValue5;
                    f13 = f23;
                } else {
                    f12 = floatValue3;
                    f11 = floatValue4;
                    float f27 = f21;
                    f10 = floatValue5;
                    float f28 = f27;
                    float f29 = f20;
                    double atan2 = (double) ((float) (Math.atan2((double) f27, (double) f29) - 1.5707963267948966d));
                    float cos4 = (float) Math.cos(atan2);
                    float sin4 = (float) Math.sin(atan2);
                    float f30 = f29;
                    f9 = f26;
                    double d12 = d10;
                    double atan22 = (double) ((float) (Math.atan2((double) f26, (double) f25) - 1.5707963267948966d));
                    float cos5 = (float) Math.cos(atan22);
                    float sin5 = (float) Math.sin(atan22);
                    float f31 = z ? f11 : f10;
                    float f32 = z ? f10 : f11;
                    float f33 = z ? f12 : f;
                    float f34 = z ? f : f12;
                    float f35 = f33 * f31 * POLYSTAR_MAGIC_NUMBER;
                    float f36 = cos4 * f35;
                    float f37 = f35 * sin4;
                    float f38 = f34 * f32 * POLYSTAR_MAGIC_NUMBER;
                    float f39 = cos5 * f38;
                    float f40 = f38 * sin5;
                    if (f16 != 0.0f) {
                        if (i == 0) {
                            f36 *= f16;
                            f37 *= f16;
                        } else if (d12 == ceil - 1.0d) {
                            f39 *= f16;
                            f40 *= f16;
                        }
                    }
                    this.path.cubicTo(f30 - f36, f28 - f37, f25 + f39, f9 + f40, f25, f9);
                    f13 = f23;
                }
                double d13 = (double) f13;
                Double.isNaN(d13);
                d9 += d13;
                z = !z;
                i++;
                f20 = f25;
                f2 = f7;
                f14 = f24;
                floatValue3 = f12;
                floatValue4 = f11;
                floatValue5 = f10;
                f21 = f9;
            } else {
                PointF value = this.positionAnimation.getValue();
                this.path.offset(value.x, value.y);
                this.path.close();
                return;
            }
        }
    }

    private void createPolygonPath() {
        double d;
        double d2;
        int i;
        double d3;
        int floor = (int) Math.floor((double) this.pointsAnimation.getValue().floatValue());
        double radians = Math.toRadians((this.rotationAnimation == null ? 0.0d : (double) this.rotationAnimation.getValue().floatValue()) - 90.0d);
        double d4 = (double) floor;
        Double.isNaN(d4);
        float floatValue = this.outerRoundednessAnimation.getValue().floatValue() / 100.0f;
        float floatValue2 = this.outerRadiusAnimation.getValue().floatValue();
        double d5 = (double) floatValue2;
        double cos = Math.cos(radians);
        Double.isNaN(d5);
        float f = (float) (cos * d5);
        double sin = Math.sin(radians);
        Double.isNaN(d5);
        float f2 = (float) (sin * d5);
        this.path.moveTo(f, f2);
        double d6 = (double) ((float) (6.283185307179586d / d4));
        Double.isNaN(d6);
        double d7 = radians + d6;
        double ceil = Math.ceil(d4);
        int i2 = 0;
        while (((double) i2) < ceil) {
            double cos2 = Math.cos(d7);
            Double.isNaN(d5);
            float f3 = (float) (cos2 * d5);
            double sin2 = Math.sin(d7);
            Double.isNaN(d5);
            double d8 = ceil;
            float f4 = (float) (d5 * sin2);
            if (floatValue != 0.0f) {
                d3 = d5;
                i = i2;
                d2 = d7;
                double atan2 = (double) ((float) (Math.atan2((double) f2, (double) f) - 1.5707963267948966d));
                float cos3 = (float) Math.cos(atan2);
                d = d6;
                double atan22 = (double) ((float) (Math.atan2((double) f4, (double) f3) - 1.5707963267948966d));
                float f5 = floatValue2 * floatValue * POLYGON_MAGIC_NUMBER;
                this.path.cubicTo(f - (cos3 * f5), f2 - (((float) Math.sin(atan2)) * f5), f3 + (((float) Math.cos(atan22)) * f5), f4 + (f5 * ((float) Math.sin(atan22))), f3, f4);
            } else {
                i = i2;
                d2 = d7;
                d3 = d5;
                d = d6;
                this.path.lineTo(f3, f4);
            }
            Double.isNaN(d);
            d7 = d2 + d;
            i2 = i + 1;
            f2 = f4;
            f = f3;
            ceil = d8;
            d5 = d3;
            d6 = d;
        }
        PointF value = this.positionAnimation.getValue();
        this.path.offset(value.x, value.y);
        this.path.close();
    }

    public void resolveKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
        MiscUtils.resolveKeyPath(keyPath, i, list, keyPath2, this);
    }

    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        if (t == LottieProperty.POLYSTAR_POINTS) {
            this.pointsAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_ROTATION) {
            this.rotationAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POSITION) {
            this.positionAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_INNER_RADIUS && this.innerRadiusAnimation != null) {
            this.innerRadiusAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_OUTER_RADIUS) {
            this.outerRadiusAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_INNER_ROUNDEDNESS && this.innerRoundednessAnimation != null) {
            this.innerRoundednessAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_OUTER_ROUNDEDNESS) {
            this.outerRoundednessAnimation.setValueCallback(lottieValueCallback);
        }
    }
}
