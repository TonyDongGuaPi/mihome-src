package com.transitionseverywhere;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Rect;
import android.view.ViewGroup;

@TargetApi(14)
public class SidePropagation extends VisibilityPropagation {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9468a = "SlidePropagation";
    private float b = 3.0f;
    private int c = 80;

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

    public long a(ViewGroup viewGroup, Transition transition, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int i;
        int i2;
        int i3;
        TransitionValues transitionValues3 = transitionValues;
        if (transitionValues3 == null && transitionValues2 == null) {
            return 0;
        }
        Rect r = transition.r();
        if (transitionValues2 == null || b(transitionValues3) == 0) {
            i = -1;
        } else {
            transitionValues3 = transitionValues2;
            i = 1;
        }
        int c2 = c(transitionValues3);
        int d = d(transitionValues3);
        int[] iArr = new int[2];
        viewGroup.getLocationOnScreen(iArr);
        int round = iArr[0] + Math.round(viewGroup.getTranslationX());
        int round2 = iArr[1] + Math.round(viewGroup.getTranslationY());
        int width = round + viewGroup.getWidth();
        int height = round2 + viewGroup.getHeight();
        if (r != null) {
            i3 = r.centerX();
            i2 = r.centerY();
        } else {
            i3 = (round + width) / 2;
            i2 = (round2 + height) / 2;
        }
        float a2 = ((float) a(viewGroup, c2, d, i3, i2, round, round2, width, height)) / ((float) a(viewGroup));
        long e = transition.e();
        if (e < 0) {
            e = 300;
        }
        return (long) Math.round((((float) (e * ((long) i))) / this.b) * a2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        if (com.transitionseverywhere.utils.ViewUtils.g(r5) != false) goto L_0x000f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0011, code lost:
        r5 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        if (com.transitionseverywhere.utils.ViewUtils.g(r5) != false) goto L_0x0011;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004c  */
    @android.annotation.SuppressLint({"RtlHardcoded"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int a(android.view.View r5, int r6, int r7, int r8, int r9, int r10, int r11, int r12, int r13) {
        /*
            r4 = this;
            int r0 = r4.c
            r1 = 3
            r2 = 5
            r3 = 8388611(0x800003, float:1.1754948E-38)
            if (r0 != r3) goto L_0x0013
            boolean r5 = com.transitionseverywhere.utils.ViewUtils.g(r5)
            if (r5 == 0) goto L_0x0011
        L_0x000f:
            r5 = 5
            goto L_0x0023
        L_0x0011:
            r5 = 3
            goto L_0x0023
        L_0x0013:
            int r0 = r4.c
            r3 = 8388613(0x800005, float:1.175495E-38)
            if (r0 != r3) goto L_0x0021
            boolean r5 = com.transitionseverywhere.utils.ViewUtils.g(r5)
            if (r5 == 0) goto L_0x000f
            goto L_0x0011
        L_0x0021:
            int r5 = r4.c
        L_0x0023:
            r0 = 0
            if (r5 == r1) goto L_0x004c
            if (r5 == r2) goto L_0x0043
            r9 = 48
            if (r5 == r9) goto L_0x003a
            r9 = 80
            if (r5 == r9) goto L_0x0031
            goto L_0x0054
        L_0x0031:
            int r7 = r7 - r11
            int r8 = r8 - r6
            int r5 = java.lang.Math.abs(r8)
            int r0 = r7 + r5
            goto L_0x0054
        L_0x003a:
            int r13 = r13 - r7
            int r8 = r8 - r6
            int r5 = java.lang.Math.abs(r8)
            int r0 = r13 + r5
            goto L_0x0054
        L_0x0043:
            int r6 = r6 - r10
            int r9 = r9 - r7
            int r5 = java.lang.Math.abs(r9)
            int r0 = r6 + r5
            goto L_0x0054
        L_0x004c:
            int r12 = r12 - r6
            int r9 = r9 - r7
            int r5 = java.lang.Math.abs(r9)
            int r0 = r12 + r5
        L_0x0054:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.transitionseverywhere.SidePropagation.a(android.view.View, int, int, int, int, int, int, int, int):int");
    }

    @SuppressLint({"RtlHardcoded"})
    private int a(ViewGroup viewGroup) {
        int i = this.c;
        if (i == 3 || i == 5 || i == 8388611 || i == 8388613) {
            return viewGroup.getWidth();
        }
        return viewGroup.getHeight();
    }
}
