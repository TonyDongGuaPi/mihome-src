package com.xiaomi.smarthome.newui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.support.animation.SpringAnimation;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.transition.VisibilityPropagation;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@RequiresApi(api = 19)
public class SpringVertialSlide extends Visibility {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20889a = "Slide";
    private static final TimeInterpolator b = new DecelerateInterpolator();
    private static final TimeInterpolator c = new AccelerateInterpolator();
    private static final String d = "android:slide:screenPosition";
    private static final CalculateSlide h = new CalculateSlideHorizontal() {
        public float a(ViewGroup viewGroup, View view, float f) {
            return view.getTranslationX() - (((float) viewGroup.getWidth()) * f);
        }
    };
    private static final CalculateSlide i = new CalculateSlideHorizontal() {
        public float a(ViewGroup viewGroup, View view, float f) {
            boolean z = true;
            if (viewGroup.getLayoutDirection() != 1) {
                z = false;
            }
            if (z) {
                return view.getTranslationX() + (((float) viewGroup.getWidth()) * f);
            }
            return view.getTranslationX() - (((float) viewGroup.getWidth()) * f);
        }
    };
    private static final CalculateSlide j = new CalculateSlideVertical() {
        public float b(ViewGroup viewGroup, View view, float f) {
            return view.getTranslationY() - (((float) viewGroup.getHeight()) * f);
        }
    };
    private static final CalculateSlide k = new CalculateSlideHorizontal() {
        public float a(ViewGroup viewGroup, View view, float f) {
            return view.getTranslationX() + (((float) viewGroup.getWidth()) * f);
        }
    };
    private static final CalculateSlide l = new CalculateSlideHorizontal() {
        public float a(ViewGroup viewGroup, View view, float f) {
            boolean z = true;
            if (viewGroup.getLayoutDirection() != 1) {
                z = false;
            }
            if (z) {
                return view.getTranslationX() - (((float) viewGroup.getWidth()) * f);
            }
            return view.getTranslationX() + (((float) viewGroup.getWidth()) * f);
        }
    };
    private static final CalculateSlide m = new CalculateSlideVertical() {
        public float b(ViewGroup viewGroup, View view, float f) {
            return view.getTranslationY() + (((float) viewGroup.getHeight()) * f);
        }
    };
    private CalculateSlide e = m;
    private int f = 80;
    private float g = 1.0f;

    @Retention(RetentionPolicy.SOURCE)
    public @interface GravityFlag {
    }

    private interface CalculateSlide {
        float a(ViewGroup viewGroup, View view, float f);

        float b(ViewGroup viewGroup, View view, float f);
    }

    private static abstract class CalculateSlideHorizontal implements CalculateSlide {
        private CalculateSlideHorizontal() {
        }

        public float b(ViewGroup viewGroup, View view, float f) {
            return view.getTranslationY();
        }
    }

    private static abstract class CalculateSlideVertical implements CalculateSlide {
        private CalculateSlideVertical() {
        }

        public float a(ViewGroup viewGroup, View view, float f) {
            return view.getTranslationX();
        }
    }

    @RequiresApi(api = 21)
    public SpringVertialSlide() {
        a(80);
    }

    @RequiresApi(api = 21)
    public SpringVertialSlide(int i2) {
        a(i2);
    }

    private void a(TransitionValues transitionValues) {
        int[] iArr = new int[2];
        transitionValues.view.getLocationOnScreen(iArr);
        transitionValues.values.put(d, iArr);
    }

    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        a(transitionValues);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);
        a(transitionValues);
    }

    @RequiresApi(api = 21)
    public void a(int i2) {
        if (i2 == 3) {
            this.e = h;
        } else if (i2 == 5) {
            this.e = k;
        } else if (i2 == 48) {
            this.e = j;
        } else if (i2 == 80) {
            this.e = m;
        } else if (i2 == 8388611) {
            this.e = i;
        } else if (i2 == 8388613) {
            this.e = l;
        } else {
            throw new IllegalArgumentException("Invalid slide direction");
        }
        this.f = i2;
        SidePropagation sidePropagation = new SidePropagation();
        sidePropagation.a(i2);
        setPropagation(sidePropagation);
    }

    public int a() {
        return this.f;
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues2 == null) {
            return null;
        }
        int[] iArr = (int[]) transitionValues2.values.get(d);
        view.getTranslationX();
        float translationY = view.getTranslationY();
        this.e.a(viewGroup, view, this.g);
        return a(view, this.e.b(viewGroup, view, this.g), translationY);
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null) {
            return null;
        }
        int[] iArr = (int[]) transitionValues.values.get(d);
        view.getTranslationX();
        float translationY = view.getTranslationY();
        this.e.a(viewGroup, view, this.g);
        return a(view, translationY, this.e.b(viewGroup, view, this.g));
    }

    private Animator a(final View view, float f2, final float f3) {
        view.setTranslationY(f2);
        ValueAnimator ofInt = ObjectAnimator.ofInt(new int[]{0, 1});
        ofInt.addListener(new AnimatorListenerAdapter() {
            private SpringAnimation d;

            public void onAnimationStart(Animator animator) {
                this.d = new SpringAnimation(view, SpringAnimation.TRANSLATION_Y, f3);
                ((SpringAnimation) this.d.setMinimumVisibleChange(1.0f)).getSpring().setStiffness(200.0f).setDampingRatio(1.0f);
                this.d.start();
            }

            public void onAnimationCancel(Animator animator) {
                if (this.d != null) {
                    this.d.skipToEnd();
                }
            }
        });
        return ofInt;
    }

    public void a(float f2) {
        this.g = f2;
    }

    @RequiresApi(api = 21)
    static class SidePropagation extends VisibilityPropagation {

        /* renamed from: a  reason: collision with root package name */
        private static final String f20891a = "SlidePropagation";
        private float b = 3.0f;
        private int c = 80;

        SidePropagation() {
        }

        public void a(int i) {
            this.c = i;
        }

        public void a(float f) {
            if (f != 0.0f) {
                this.b = f;
                return;
            }
            throw new IllegalArgumentException("propagationSpeed may not be 0");
        }

        public long getStartDelay(ViewGroup viewGroup, Transition transition, TransitionValues transitionValues, TransitionValues transitionValues2) {
            int i;
            int i2;
            int i3;
            TransitionValues transitionValues3 = transitionValues;
            if (transitionValues3 == null && transitionValues2 == null) {
                return 0;
            }
            Rect epicenter = transition.getEpicenter();
            if (transitionValues2 == null || getViewVisibility(transitionValues3) == 0) {
                i = -1;
            } else {
                transitionValues3 = transitionValues2;
                i = 1;
            }
            int viewX = getViewX(transitionValues3);
            int viewY = getViewY(transitionValues3);
            int[] iArr = new int[2];
            viewGroup.getLocationOnScreen(iArr);
            int round = iArr[0] + Math.round(viewGroup.getTranslationX());
            int round2 = iArr[1] + Math.round(viewGroup.getTranslationY());
            int width = round + viewGroup.getWidth();
            int height = round2 + viewGroup.getHeight();
            if (epicenter != null) {
                i3 = epicenter.centerX();
                i2 = epicenter.centerY();
            } else {
                i3 = (round + width) / 2;
                i2 = (round2 + height) / 2;
            }
            float a2 = ((float) a(viewGroup, viewX, viewY, i3, i2, round, round2, width, height)) / ((float) a(viewGroup));
            long duration = transition.getDuration();
            if (duration < 0) {
                duration = 300;
            }
            return (long) Math.round((((float) (duration * ((long) i))) / this.b) * a2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
            if (r4 != false) goto L_0x0017;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
            if (r4 != false) goto L_0x0015;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
            r7 = 3;
         */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x002f  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0055  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private int a(android.view.View r7, int r8, int r9, int r10, int r11, int r12, int r13, int r14, int r15) {
            /*
                r6 = this;
                int r0 = r6.c
                r1 = 5
                r2 = 3
                r3 = 0
                r4 = 1
                r5 = 8388611(0x800003, float:1.1754948E-38)
                if (r0 != r5) goto L_0x0019
                int r7 = r7.getLayoutDirection()
                if (r7 != r4) goto L_0x0012
                goto L_0x0013
            L_0x0012:
                r4 = 0
            L_0x0013:
                if (r4 == 0) goto L_0x0017
            L_0x0015:
                r7 = 5
                goto L_0x002d
            L_0x0017:
                r7 = 3
                goto L_0x002d
            L_0x0019:
                int r0 = r6.c
                r5 = 8388613(0x800005, float:1.175495E-38)
                if (r0 != r5) goto L_0x002b
                int r7 = r7.getLayoutDirection()
                if (r7 != r4) goto L_0x0027
                goto L_0x0028
            L_0x0027:
                r4 = 0
            L_0x0028:
                if (r4 == 0) goto L_0x0015
                goto L_0x0017
            L_0x002b:
                int r7 = r6.c
            L_0x002d:
                if (r7 == r2) goto L_0x0055
                if (r7 == r1) goto L_0x004c
                r11 = 48
                if (r7 == r11) goto L_0x0043
                r11 = 80
                if (r7 == r11) goto L_0x003a
                goto L_0x005d
            L_0x003a:
                int r9 = r9 - r13
                int r10 = r10 - r8
                int r7 = java.lang.Math.abs(r10)
                int r3 = r9 + r7
                goto L_0x005d
            L_0x0043:
                int r15 = r15 - r9
                int r10 = r10 - r8
                int r7 = java.lang.Math.abs(r10)
                int r3 = r15 + r7
                goto L_0x005d
            L_0x004c:
                int r8 = r8 - r12
                int r11 = r11 - r9
                int r7 = java.lang.Math.abs(r11)
                int r3 = r8 + r7
                goto L_0x005d
            L_0x0055:
                int r14 = r14 - r8
                int r11 = r11 - r9
                int r7 = java.lang.Math.abs(r11)
                int r3 = r14 + r7
            L_0x005d:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.widget.SpringVertialSlide.SidePropagation.a(android.view.View, int, int, int, int, int, int, int, int):int");
        }

        private int a(ViewGroup viewGroup) {
            int i = this.c;
            if (i == 3 || i == 5 || i == 8388611 || i == 8388613) {
                return viewGroup.getWidth();
            }
            return viewGroup.getHeight();
        }
    }
}
