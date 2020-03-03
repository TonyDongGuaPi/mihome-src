package com.transitionseverywhere;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import com.transitionseverywhere.utils.ViewUtils;

@TargetApi(14)
public class ChangeClipBounds extends Transition {

    /* renamed from: a  reason: collision with root package name */
    public static final Property<View, Rect> f9439a;
    private static final String b = "ChangeTransform";
    private static final String c = "android:clipBounds:clip";
    private static final String d = "android:clipBounds:bounds";
    private static final String[] e = {c};

    static {
        if (Build.VERSION.SDK_INT >= 14) {
            f9439a = new Property<View, Rect>(Rect.class, "clipBounds") {
                /* renamed from: a */
                public void set(View view, Rect rect) {
                    ViewUtils.a(view, rect);
                }

                /* renamed from: a */
                public Rect get(View view) {
                    return ViewUtils.b(view);
                }
            };
        } else {
            f9439a = null;
        }
    }

    public ChangeClipBounds() {
    }

    public ChangeClipBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public String[] a() {
        return e;
    }

    private void d(TransitionValues transitionValues) {
        View view = transitionValues.f9482a;
        if (view.getVisibility() != 8) {
            Rect b2 = ViewUtils.b(view);
            transitionValues.b.put(c, b2);
            if (b2 == null) {
                transitionValues.b.put(d, new Rect(0, 0, view.getWidth(), view.getHeight()));
            }
        }
    }

    public void a(TransitionValues transitionValues) {
        d(transitionValues);
    }

    public void b(TransitionValues transitionValues) {
        d(transitionValues);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: android.graphics.Rect} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: android.graphics.Rect} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.animation.Animator a(android.view.ViewGroup r5, com.transitionseverywhere.TransitionValues r6, com.transitionseverywhere.TransitionValues r7) {
        /*
            r4 = this;
            r5 = 0
            if (r6 == 0) goto L_0x0076
            if (r7 == 0) goto L_0x0076
            java.util.Map<java.lang.String, java.lang.Object> r0 = r6.b
            java.lang.String r1 = "android:clipBounds:clip"
            boolean r0 = r0.containsKey(r1)
            if (r0 == 0) goto L_0x0076
            java.util.Map<java.lang.String, java.lang.Object> r0 = r7.b
            java.lang.String r1 = "android:clipBounds:clip"
            boolean r0 = r0.containsKey(r1)
            if (r0 != 0) goto L_0x001a
            goto L_0x0076
        L_0x001a:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r6.b
            java.lang.String r1 = "android:clipBounds:clip"
            java.lang.Object r0 = r0.get(r1)
            android.graphics.Rect r0 = (android.graphics.Rect) r0
            java.util.Map<java.lang.String, java.lang.Object> r1 = r7.b
            java.lang.String r2 = "android:clipBounds:clip"
            java.lang.Object r1 = r1.get(r2)
            android.graphics.Rect r1 = (android.graphics.Rect) r1
            if (r0 != 0) goto L_0x0033
            if (r1 != 0) goto L_0x0033
            return r5
        L_0x0033:
            if (r0 != 0) goto L_0x0041
            java.util.Map<java.lang.String, java.lang.Object> r6 = r6.b
            java.lang.String r0 = "android:clipBounds:bounds"
            java.lang.Object r6 = r6.get(r0)
            r0 = r6
            android.graphics.Rect r0 = (android.graphics.Rect) r0
            goto L_0x004e
        L_0x0041:
            if (r1 != 0) goto L_0x004e
            java.util.Map<java.lang.String, java.lang.Object> r6 = r7.b
            java.lang.String r1 = "android:clipBounds:bounds"
            java.lang.Object r6 = r6.get(r1)
            r1 = r6
            android.graphics.Rect r1 = (android.graphics.Rect) r1
        L_0x004e:
            boolean r6 = r0.equals(r1)
            if (r6 == 0) goto L_0x0055
            return r5
        L_0x0055:
            android.view.View r5 = r7.f9482a
            com.transitionseverywhere.utils.ViewUtils.a((android.view.View) r5, (android.graphics.Rect) r0)
            com.transitionseverywhere.utils.RectEvaluator r5 = new com.transitionseverywhere.utils.RectEvaluator
            android.graphics.Rect r6 = new android.graphics.Rect
            r6.<init>()
            r5.<init>(r6)
            android.view.View r6 = r7.f9482a
            android.util.Property<android.view.View, android.graphics.Rect> r7 = f9439a
            r2 = 2
            android.graphics.Rect[] r2 = new android.graphics.Rect[r2]
            r3 = 0
            r2[r3] = r0
            r0 = 1
            r2[r0] = r1
            android.animation.ObjectAnimator r5 = android.animation.ObjectAnimator.ofObject(r6, r7, r5, r2)
            return r5
        L_0x0076:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.transitionseverywhere.ChangeClipBounds.a(android.view.ViewGroup, com.transitionseverywhere.TransitionValues, com.transitionseverywhere.TransitionValues):android.animation.Animator");
    }
}
