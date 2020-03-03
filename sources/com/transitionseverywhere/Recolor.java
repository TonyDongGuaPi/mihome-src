package com.transitionseverywhere;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Property;
import android.widget.TextView;
import com.transitionseverywhere.utils.IntProperty;

@TargetApi(14)
public class Recolor extends Transition {

    /* renamed from: a  reason: collision with root package name */
    public static final Property<TextView, Integer> f9465a;
    public static final Property<ColorDrawable, Integer> b;
    private static final String c = "android:recolor:background";
    private static final String d = "android:recolor:textColor";

    static {
        if (Build.VERSION.SDK_INT >= 14) {
            f9465a = new IntProperty<TextView>() {
                public void a(TextView textView, int i) {
                    textView.setTextColor(i);
                }

                /* renamed from: a */
                public Integer get(TextView textView) {
                    return 0;
                }
            }.a();
            b = new IntProperty<ColorDrawable>() {
                public void a(ColorDrawable colorDrawable, int i) {
                    colorDrawable.setColor(i);
                }

                /* renamed from: a */
                public Integer get(ColorDrawable colorDrawable) {
                    return Integer.valueOf(colorDrawable.getColor());
                }
            }.a();
            return;
        }
        f9465a = null;
        b = null;
    }

    public Recolor() {
    }

    public Recolor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void d(TransitionValues transitionValues) {
        transitionValues.b.put(c, transitionValues.f9482a.getBackground());
        if (transitionValues.f9482a instanceof TextView) {
            transitionValues.b.put(d, Integer.valueOf(((TextView) transitionValues.f9482a).getCurrentTextColor()));
        }
    }

    public void a(TransitionValues transitionValues) {
        d(transitionValues);
    }

    public void b(TransitionValues transitionValues) {
        d(transitionValues);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.animation.Animator a(android.view.ViewGroup r10, com.transitionseverywhere.TransitionValues r11, com.transitionseverywhere.TransitionValues r12) {
        /*
            r9 = this;
            r10 = 0
            if (r11 == 0) goto L_0x009b
            if (r12 != 0) goto L_0x0007
            goto L_0x009b
        L_0x0007:
            android.view.View r0 = r12.f9482a
            java.util.Map<java.lang.String, java.lang.Object> r1 = r11.b
            java.lang.String r2 = "android:recolor:background"
            java.lang.Object r1 = r1.get(r2)
            android.graphics.drawable.Drawable r1 = (android.graphics.drawable.Drawable) r1
            java.util.Map<java.lang.String, java.lang.Object> r2 = r12.b
            java.lang.String r3 = "android:recolor:background"
            java.lang.Object r2 = r2.get(r3)
            android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2
            boolean r3 = r1 instanceof android.graphics.drawable.ColorDrawable
            r4 = 1
            r5 = 0
            r6 = 2
            if (r3 == 0) goto L_0x005a
            boolean r3 = r2 instanceof android.graphics.drawable.ColorDrawable
            if (r3 == 0) goto L_0x005a
            android.graphics.drawable.ColorDrawable r1 = (android.graphics.drawable.ColorDrawable) r1
            android.graphics.drawable.ColorDrawable r2 = (android.graphics.drawable.ColorDrawable) r2
            int r3 = r1.getColor()
            int r7 = r2.getColor()
            if (r3 == r7) goto L_0x005a
            int r3 = r2.getColor()
            int r7 = r1.getColor()
            r2.setColor(r7)
            android.util.Property<android.graphics.drawable.ColorDrawable, java.lang.Integer> r7 = b
            int[] r8 = new int[r6]
            int r1 = r1.getColor()
            r8[r5] = r1
            r8[r4] = r3
            android.animation.ObjectAnimator r1 = android.animation.ObjectAnimator.ofInt(r2, r7, r8)
            android.animation.ArgbEvaluator r2 = new android.animation.ArgbEvaluator
            r2.<init>()
            r1.setEvaluator(r2)
            goto L_0x005b
        L_0x005a:
            r1 = r10
        L_0x005b:
            boolean r2 = r0 instanceof android.widget.TextView
            if (r2 == 0) goto L_0x0096
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.util.Map<java.lang.String, java.lang.Object> r11 = r11.b
            java.lang.String r2 = "android:recolor:textColor"
            java.lang.Object r11 = r11.get(r2)
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            java.util.Map<java.lang.String, java.lang.Object> r12 = r12.b
            java.lang.String r2 = "android:recolor:textColor"
            java.lang.Object r12 = r12.get(r2)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            if (r11 == r12) goto L_0x0096
            r0.setTextColor(r12)
            android.util.Property<android.widget.TextView, java.lang.Integer> r10 = f9465a
            int[] r2 = new int[r6]
            r2[r5] = r11
            r2[r4] = r12
            android.animation.ObjectAnimator r10 = android.animation.ObjectAnimator.ofInt(r0, r10, r2)
            android.animation.ArgbEvaluator r11 = new android.animation.ArgbEvaluator
            r11.<init>()
            r10.setEvaluator(r11)
        L_0x0096:
            android.animation.Animator r10 = com.transitionseverywhere.TransitionUtils.a(r1, r10)
            return r10
        L_0x009b:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.transitionseverywhere.Recolor.a(android.view.ViewGroup, com.transitionseverywhere.TransitionValues, com.transitionseverywhere.TransitionValues):android.animation.Animator");
    }
}
