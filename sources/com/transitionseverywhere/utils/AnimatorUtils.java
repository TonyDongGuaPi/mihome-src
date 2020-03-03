package com.transitionseverywhere.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.annotation.TargetApi;
import android.graphics.Path;
import android.os.Build;
import android.util.Property;
import android.view.View;
import com.transitionseverywhere.PathMotion;

public class AnimatorUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final BaseAnimatorCompat f9494a;

    static class BaseAnimatorCompat {
        public <T> Animator a(T t, PointFProperty<T> pointFProperty, float f, float f2, float f3, float f4) {
            return null;
        }

        public <T> Animator a(T t, PointFProperty<T> pointFProperty, Path path) {
            return null;
        }

        public ObjectAnimator a(View view, Property<View, Float> property, float f, float f2) {
            return null;
        }

        public void a(Animator animator) {
        }

        public void a(Animator animator, Animator.AnimatorPauseListener animatorPauseListener) {
        }

        public boolean a(View view) {
            return true;
        }

        public ObjectAnimator b(View view, Property<View, Integer> property, float f, float f2) {
            return null;
        }

        public void b(Animator animator) {
        }

        public boolean c(Animator animator) {
            return false;
        }

        BaseAnimatorCompat() {
        }
    }

    @TargetApi(14)
    static class IceCreamSandwichAnimatorCompat extends BaseAnimatorCompat {
        IceCreamSandwichAnimatorCompat() {
        }

        public void a(Animator animator) {
            animator.cancel();
        }

        public <T> Animator a(T t, PointFProperty<T> pointFProperty, float f, float f2, float f3, float f4) {
            return PointFAnimator.a(t, pointFProperty, f, f2, f3, f4);
        }

        public <T> Animator a(T t, PointFProperty<T> pointFProperty, Path path) {
            return PathAnimatorCompat.a(t, pointFProperty, path);
        }

        public boolean c(Animator animator) {
            return animator.isStarted();
        }

        public ObjectAnimator a(View view, Property<View, Float> property, float f, float f2) {
            float floatValue = property.get(view).floatValue() * f;
            float floatValue2 = property.get(view).floatValue() * f2;
            if (floatValue == floatValue2) {
                return null;
            }
            property.set(view, Float.valueOf(floatValue));
            return ObjectAnimator.ofFloat(view, property, new float[]{floatValue2});
        }

        public ObjectAnimator b(View view, Property<View, Integer> property, float f, float f2) {
            int intValue = (int) (((float) property.get(view).intValue()) * f);
            int intValue2 = (int) (((float) property.get(view).intValue()) * f2);
            if (intValue == intValue2) {
                return null;
            }
            property.set(view, Integer.valueOf(intValue));
            return ObjectAnimator.ofInt(view, property, new int[]{intValue2});
        }
    }

    @TargetApi(16)
    static class JellyBeanCompat extends IceCreamSandwichAnimatorCompat {
        JellyBeanCompat() {
        }

        public boolean a(View view) {
            return view.hasOverlappingRendering();
        }
    }

    @TargetApi(19)
    static class KitKatAnimatorCompat extends JellyBeanCompat {
        KitKatAnimatorCompat() {
        }

        public void a(Animator animator, Animator.AnimatorPauseListener animatorPauseListener) {
            animator.addPauseListener(animatorPauseListener);
        }

        public void a(Animator animator) {
            animator.pause();
        }

        public void b(Animator animator) {
            animator.resume();
        }
    }

    @TargetApi(21)
    static class LollipopAnimatorCompat extends KitKatAnimatorCompat {
        LollipopAnimatorCompat() {
        }

        public <T> Animator a(T t, PointFProperty<T> pointFProperty, Path path) {
            return ObjectAnimator.ofObject(t, pointFProperty, (TypeConverter) null, path);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            f9494a = new LollipopAnimatorCompat();
        } else if (i >= 19) {
            f9494a = new KitKatAnimatorCompat();
        } else if (i >= 16) {
            f9494a = new JellyBeanCompat();
        } else if (i >= 14) {
            f9494a = new IceCreamSandwichAnimatorCompat();
        } else {
            f9494a = new BaseAnimatorCompat();
        }
    }

    public static void a(Animator animator, Animator.AnimatorPauseListener animatorPauseListener) {
        f9494a.a(animator, animatorPauseListener);
    }

    public static void a(Animator animator) {
        f9494a.a(animator);
    }

    public static void b(Animator animator) {
        f9494a.b(animator);
    }

    public static <T> Animator a(T t, PointFProperty<T> pointFProperty, float f, float f2, float f3, float f4) {
        return f9494a.a(t, pointFProperty, f, f2, f3, f4);
    }

    public static <T> Animator a(T t, PointFProperty<T> pointFProperty, Path path) {
        if (path != null) {
            return f9494a.a(t, pointFProperty, path);
        }
        return null;
    }

    public static <T> Animator a(T t, PointFProperty<T> pointFProperty, PathMotion pathMotion, float f, float f2, float f3, float f4) {
        if (f == f3 && f2 == f4) {
            return null;
        }
        if (pathMotion == null || pathMotion.equals(PathMotion.f9461a)) {
            return a(t, pointFProperty, f, f2, f3, f4);
        }
        return a(t, pointFProperty, pathMotion.a(f, f2, f3, f4));
    }

    public static boolean c(Animator animator) {
        return f9494a.c(animator);
    }

    public static boolean a(View view) {
        return f9494a.a(view);
    }

    public static ObjectAnimator a(View view, Property<View, Float> property, float f, float f2) {
        return f9494a.a(view, property, f, f2);
    }

    public static ObjectAnimator b(View view, Property<View, Integer> property, float f, float f2) {
        return f9494a.b(view, property, f, f2);
    }
}
