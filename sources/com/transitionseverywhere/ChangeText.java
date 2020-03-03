package com.transitionseverywhere;

import android.annotation.TargetApi;
import android.widget.EditText;
import android.widget.TextView;

@TargetApi(14)
public class ChangeText extends Transition {
    private static final String M = "android:textchange:text";
    private static final String N = "android:textchange:textSelectionStart";
    private static final String O = "android:textchange:textSelectionEnd";
    private static final String P = "android:textchange:textColor";
    private static final String[] R = {M, N, O};

    /* renamed from: a  reason: collision with root package name */
    public static final int f9442a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    private static final String e = "TextChange";
    /* access modifiers changed from: private */
    public int Q = 0;

    public ChangeText a(int i) {
        if (i >= 0 && i <= 3) {
            this.Q = i;
        }
        return this;
    }

    public String[] a() {
        return R;
    }

    public int b() {
        return this.Q;
    }

    private void d(TransitionValues transitionValues) {
        if (transitionValues.f9482a instanceof TextView) {
            TextView textView = (TextView) transitionValues.f9482a;
            transitionValues.b.put(M, textView.getText());
            if (textView instanceof EditText) {
                transitionValues.b.put(N, Integer.valueOf(textView.getSelectionStart()));
                transitionValues.b.put(O, Integer.valueOf(textView.getSelectionEnd()));
            }
            if (this.Q > 0) {
                transitionValues.b.put(P, Integer.valueOf(textView.getCurrentTextColor()));
            }
        }
    }

    public void a(TransitionValues transitionValues) {
        d(transitionValues);
    }

    public void b(TransitionValues transitionValues) {
        d(transitionValues);
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x0196  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.animation.Animator a(android.view.ViewGroup r24, com.transitionseverywhere.TransitionValues r25, com.transitionseverywhere.TransitionValues r26) {
        /*
            r23 = this;
            r10 = r23
            r0 = r25
            r1 = r26
            if (r0 == 0) goto L_0x01b1
            if (r1 == 0) goto L_0x01b1
            android.view.View r2 = r0.f9482a
            boolean r2 = r2 instanceof android.widget.TextView
            if (r2 == 0) goto L_0x01b1
            android.view.View r2 = r1.f9482a
            boolean r2 = r2 instanceof android.widget.TextView
            if (r2 != 0) goto L_0x0018
            goto L_0x01b1
        L_0x0018:
            android.view.View r2 = r1.f9482a
            r9 = r2
            android.widget.TextView r9 = (android.widget.TextView) r9
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.b
            java.util.Map<java.lang.String, java.lang.Object> r1 = r1.b
            java.lang.String r2 = "android:textchange:text"
            java.lang.Object r2 = r0.get(r2)
            if (r2 == 0) goto L_0x0033
            java.lang.String r2 = "android:textchange:text"
            java.lang.Object r2 = r0.get(r2)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
        L_0x0031:
            r11 = r2
            goto L_0x0036
        L_0x0033:
            java.lang.String r2 = ""
            goto L_0x0031
        L_0x0036:
            java.lang.String r2 = "android:textchange:text"
            java.lang.Object r2 = r1.get(r2)
            if (r2 == 0) goto L_0x0048
            java.lang.String r2 = "android:textchange:text"
            java.lang.Object r2 = r1.get(r2)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
        L_0x0046:
            r12 = r2
            goto L_0x004b
        L_0x0048:
            java.lang.String r2 = ""
            goto L_0x0046
        L_0x004b:
            boolean r2 = r9 instanceof android.widget.EditText
            r3 = -1
            if (r2 == 0) goto L_0x00ac
            java.lang.String r4 = "android:textchange:textSelectionStart"
            java.lang.Object r4 = r0.get(r4)
            if (r4 == 0) goto L_0x0065
            java.lang.String r4 = "android:textchange:textSelectionStart"
            java.lang.Object r4 = r0.get(r4)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            goto L_0x0066
        L_0x0065:
            r4 = -1
        L_0x0066:
            java.lang.String r5 = "android:textchange:textSelectionEnd"
            java.lang.Object r5 = r0.get(r5)
            if (r5 == 0) goto L_0x007b
            java.lang.String r5 = "android:textchange:textSelectionEnd"
            java.lang.Object r5 = r0.get(r5)
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            goto L_0x007c
        L_0x007b:
            r5 = r4
        L_0x007c:
            java.lang.String r6 = "android:textchange:textSelectionStart"
            java.lang.Object r6 = r1.get(r6)
            if (r6 == 0) goto L_0x0090
            java.lang.String r3 = "android:textchange:textSelectionStart"
            java.lang.Object r3 = r1.get(r3)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
        L_0x0090:
            java.lang.String r6 = "android:textchange:textSelectionEnd"
            java.lang.Object r6 = r1.get(r6)
            if (r6 == 0) goto L_0x00a5
            java.lang.String r6 = "android:textchange:textSelectionEnd"
            java.lang.Object r6 = r1.get(r6)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            goto L_0x00a6
        L_0x00a5:
            r6 = r3
        L_0x00a6:
            r15 = r3
            r13 = r4
            r14 = r5
            r16 = r6
            goto L_0x00b1
        L_0x00ac:
            r13 = -1
            r14 = -1
            r15 = -1
            r16 = -1
        L_0x00b1:
            boolean r3 = r11.equals(r12)
            if (r3 != 0) goto L_0x01af
            int r3 = r10.Q
            r7 = 2
            if (r3 == r7) goto L_0x00c7
            r9.setText(r11)
            if (r2 == 0) goto L_0x00c7
            r2 = r9
            android.widget.EditText r2 = (android.widget.EditText) r2
            r10.a((android.widget.EditText) r2, (int) r13, (int) r14)
        L_0x00c7:
            int r2 = r10.Q
            r17 = 0
            if (r2 != 0) goto L_0x00f0
            float[] r0 = new float[r7]
            r0 = {0, 1065353216} // fill-array
            android.animation.ValueAnimator r7 = android.animation.ValueAnimator.ofFloat(r0)
            com.transitionseverywhere.ChangeText$1 r8 = new com.transitionseverywhere.ChangeText$1
            r0 = r8
            r1 = r23
            r2 = r11
            r3 = r9
            r4 = r12
            r5 = r15
            r6 = r16
            r0.<init>(r2, r3, r4, r5, r6)
            r7.addListener(r8)
            r18 = r7
            r20 = r13
            r19 = r14
            r6 = 0
            goto L_0x0199
        L_0x00f0:
            java.lang.String r2 = "android:textchange:textColor"
            java.lang.Object r0 = r0.get(r2)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            java.lang.String r2 = "android:textchange:textColor"
            java.lang.Object r1 = r1.get(r2)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r6 = r1.intValue()
            int r1 = r10.Q
            r5 = 1
            r4 = 3
            if (r1 == r4) goto L_0x011e
            int r1 = r10.Q
            if (r1 != r5) goto L_0x0113
            goto L_0x011e
        L_0x0113:
            r22 = r6
            r20 = r13
            r19 = r14
            r7 = 0
            r13 = 3
            r21 = 1
            goto L_0x014e
        L_0x011e:
            int[] r1 = new int[r7]
            r1 = {255, 0} // fill-array
            android.animation.ValueAnimator r3 = android.animation.ValueAnimator.ofInt(r1)
            com.transitionseverywhere.ChangeText$2 r1 = new com.transitionseverywhere.ChangeText$2
            r1.<init>(r9, r0)
            r3.addUpdateListener(r1)
            com.transitionseverywhere.ChangeText$3 r2 = new com.transitionseverywhere.ChangeText$3
            r0 = r2
            r1 = r23
            r8 = r2
            r2 = r11
            r19 = r14
            r14 = r3
            r3 = r9
            r20 = r13
            r13 = 3
            r4 = r12
            r21 = 1
            r5 = r15
            r22 = r6
            r6 = r16
            r7 = r22
            r0.<init>(r2, r3, r4, r5, r6, r7)
            r14.addListener(r8)
            r7 = r14
        L_0x014e:
            int r0 = r10.Q
            if (r0 == r13) goto L_0x015d
            int r0 = r10.Q
            r1 = 2
            if (r0 != r1) goto L_0x0158
            goto L_0x015e
        L_0x0158:
            r2 = r22
            r18 = 0
            goto L_0x017b
        L_0x015d:
            r1 = 2
        L_0x015e:
            int[] r0 = new int[r1]
            r0 = {0, 255} // fill-array
            android.animation.ValueAnimator r8 = android.animation.ValueAnimator.ofInt(r0)
            com.transitionseverywhere.ChangeText$4 r0 = new com.transitionseverywhere.ChangeText$4
            r2 = r22
            r0.<init>(r9, r2)
            r8.addUpdateListener(r0)
            com.transitionseverywhere.ChangeText$5 r0 = new com.transitionseverywhere.ChangeText$5
            r0.<init>(r9, r2)
            r8.addListener(r0)
            r18 = r8
        L_0x017b:
            if (r7 == 0) goto L_0x0194
            if (r18 == 0) goto L_0x0194
            android.animation.AnimatorSet r0 = new android.animation.AnimatorSet
            r0.<init>()
            r3 = r0
            android.animation.AnimatorSet r3 = (android.animation.AnimatorSet) r3
            android.animation.Animator[] r1 = new android.animation.Animator[r1]
            r1[r17] = r7
            r1[r21] = r18
            r3.playSequentially(r1)
            r18 = r0
        L_0x0192:
            r6 = r2
            goto L_0x0199
        L_0x0194:
            if (r7 == 0) goto L_0x0192
            r6 = r2
            r18 = r7
        L_0x0199:
            com.transitionseverywhere.ChangeText$6 r13 = new com.transitionseverywhere.ChangeText$6
            r0 = r13
            r1 = r23
            r2 = r9
            r3 = r12
            r4 = r15
            r5 = r16
            r7 = r11
            r8 = r20
            r9 = r19
            r0.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            r10.a((com.transitionseverywhere.Transition.TransitionListener) r13)
            return r18
        L_0x01af:
            r0 = 0
            return r0
        L_0x01b1:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.transitionseverywhere.ChangeText.a(android.view.ViewGroup, com.transitionseverywhere.TransitionValues, com.transitionseverywhere.TransitionValues):android.animation.Animator");
    }

    /* access modifiers changed from: private */
    public void a(EditText editText, int i, int i2) {
        if (i >= 0 && i2 >= 0) {
            editText.setSelection(i, i2);
        }
    }
}
