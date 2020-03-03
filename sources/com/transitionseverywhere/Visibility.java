package com.transitionseverywhere;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.utils.ViewGroupUtils;
import com.transitionseverywhere.utils.ViewUtils;

@TargetApi(14)
public abstract class Visibility extends Transition {
    public static final int M = 1;
    public static final int N = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final String f9486a = "android:visibility:parent";
    private static final String[] b = {d, f9486a};
    static final String d = "android:visibility:visibility";
    protected static final String e = "android:visibility:screenLocation";
    private int O = -1;
    private int P = -1;
    private int c = 3;

    public Animator a(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    public Animator b(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    private static class VisibilityInfo {

        /* renamed from: a  reason: collision with root package name */
        boolean f9489a;
        boolean b;
        int c;
        int d;
        ViewGroup e;
        ViewGroup f;

        private VisibilityInfo() {
        }
    }

    public Visibility() {
    }

    public Visibility(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.VisibilityTransition);
        int i = obtainStyledAttributes.getInt(R.styleable.VisibilityTransition_transitionVisibilityMode, 0);
        obtainStyledAttributes.recycle();
        if (i != 0) {
            b(i);
        }
    }

    public Visibility b(int i) {
        if ((i & -4) == 0) {
            this.c = i;
            return this;
        }
        throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
    }

    public int c() {
        return this.c;
    }

    public String[] a() {
        return b;
    }

    private void a(TransitionValues transitionValues, int i) {
        if (i == -1) {
            i = transitionValues.f9482a.getVisibility();
        }
        transitionValues.b.put(d, Integer.valueOf(i));
        transitionValues.b.put(f9486a, transitionValues.f9482a.getParent());
        int[] iArr = new int[2];
        transitionValues.f9482a.getLocationOnScreen(iArr);
        transitionValues.b.put(e, iArr);
    }

    public void a(TransitionValues transitionValues) {
        a(transitionValues, this.O);
    }

    public void b(TransitionValues transitionValues) {
        a(transitionValues, this.P);
    }

    public void c(int i, boolean z) {
        if (z) {
            this.O = i;
        } else {
            this.P = i;
        }
    }

    public boolean d(TransitionValues transitionValues) {
        if (transitionValues == null) {
            return false;
        }
        int intValue = ((Integer) transitionValues.b.get(d)).intValue();
        View view = (View) transitionValues.b.get(f9486a);
        if (intValue != 0 || view == null) {
            return false;
        }
        return true;
    }

    private static VisibilityInfo b(TransitionValues transitionValues, TransitionValues transitionValues2) {
        VisibilityInfo visibilityInfo = new VisibilityInfo();
        visibilityInfo.f9489a = false;
        visibilityInfo.b = false;
        if (transitionValues == null || !transitionValues.b.containsKey(d)) {
            visibilityInfo.c = -1;
            visibilityInfo.e = null;
        } else {
            visibilityInfo.c = ((Integer) transitionValues.b.get(d)).intValue();
            visibilityInfo.e = (ViewGroup) transitionValues.b.get(f9486a);
        }
        if (transitionValues2 == null || !transitionValues2.b.containsKey(d)) {
            visibilityInfo.d = -1;
            visibilityInfo.f = null;
        } else {
            visibilityInfo.d = ((Integer) transitionValues2.b.get(d)).intValue();
            visibilityInfo.f = (ViewGroup) transitionValues2.b.get(f9486a);
        }
        if (transitionValues == null || transitionValues2 == null) {
            if (transitionValues == null && visibilityInfo.d == 0) {
                visibilityInfo.b = true;
                visibilityInfo.f9489a = true;
            } else if (transitionValues2 == null && visibilityInfo.c == 0) {
                visibilityInfo.b = false;
                visibilityInfo.f9489a = true;
            }
        } else if (visibilityInfo.c == visibilityInfo.d && visibilityInfo.e == visibilityInfo.f) {
            return visibilityInfo;
        } else {
            if (visibilityInfo.c != visibilityInfo.d) {
                if (visibilityInfo.c == 0) {
                    visibilityInfo.b = false;
                    visibilityInfo.f9489a = true;
                } else if (visibilityInfo.d == 0) {
                    visibilityInfo.b = true;
                    visibilityInfo.f9489a = true;
                }
            } else if (visibilityInfo.e != visibilityInfo.f) {
                if (visibilityInfo.f == null) {
                    visibilityInfo.b = false;
                    visibilityInfo.f9489a = true;
                } else if (visibilityInfo.e == null) {
                    visibilityInfo.b = true;
                    visibilityInfo.f9489a = true;
                }
            }
        }
        return visibilityInfo;
    }

    public Animator a(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        VisibilityInfo b2 = b(transitionValues, transitionValues2);
        if (!b2.f9489a) {
            return null;
        }
        if (b2.e == null && b2.f == null) {
            return null;
        }
        if (b2.b) {
            return a(viewGroup, transitionValues, b2.c, transitionValues2, b2.d);
        }
        return b(viewGroup, transitionValues, b2.c, transitionValues2, b2.d);
    }

    public Animator a(ViewGroup viewGroup, TransitionValues transitionValues, int i, TransitionValues transitionValues2, int i2) {
        if ((this.c & 1) != 1 || transitionValues2 == null) {
            return null;
        }
        boolean z = false;
        if (transitionValues == null) {
            View view = (View) transitionValues2.f9482a.getParent();
            if (b(d(view, false), c(view, false)).f9489a) {
                return null;
            }
        }
        if (!(this.O == -1 && this.P == -1)) {
            z = true;
        }
        if (z) {
            Object tag = transitionValues2.f9482a.getTag(R.id.transitionAlpha);
            if (tag instanceof Float) {
                transitionValues2.f9482a.setAlpha(((Float) tag).floatValue());
                transitionValues2.f9482a.setTag(R.id.transitionAlpha, (Object) null);
            }
        }
        return a(viewGroup, transitionValues2.f9482a, transitionValues, transitionValues2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.animation.Animator b(android.view.ViewGroup r9, com.transitionseverywhere.TransitionValues r10, int r11, com.transitionseverywhere.TransitionValues r12, int r13) {
        /*
            r8 = this;
            int r11 = r8.c
            r0 = 2
            r11 = r11 & r0
            r1 = 0
            if (r11 == r0) goto L_0x0008
            return r1
        L_0x0008:
            if (r10 == 0) goto L_0x000e
            android.view.View r11 = r10.f9482a
            r7 = r11
            goto L_0x000f
        L_0x000e:
            r7 = r1
        L_0x000f:
            if (r12 == 0) goto L_0x0014
            android.view.View r11 = r12.f9482a
            goto L_0x0015
        L_0x0014:
            r11 = r1
        L_0x0015:
            r0 = -1
            r2 = 0
            r3 = 1
            if (r11 == 0) goto L_0x002a
            android.view.ViewParent r4 = r11.getParent()
            if (r4 != 0) goto L_0x0021
            goto L_0x002a
        L_0x0021:
            r4 = 4
            if (r13 != r4) goto L_0x0025
            goto L_0x0027
        L_0x0025:
            if (r7 != r11) goto L_0x004d
        L_0x0027:
            r4 = r11
            r5 = r1
            goto L_0x002e
        L_0x002a:
            if (r11 == 0) goto L_0x0031
        L_0x002c:
            r5 = r11
            r4 = r1
        L_0x002e:
            r11 = 0
            goto L_0x0090
        L_0x0031:
            if (r7 == 0) goto L_0x008d
            int r11 = com.transitionseverywhere.R.id.overlay_view
            java.lang.Object r11 = r7.getTag(r11)
            if (r11 == 0) goto L_0x0047
            int r11 = com.transitionseverywhere.R.id.overlay_view
            java.lang.Object r11 = r7.getTag(r11)
            android.view.View r11 = (android.view.View) r11
            r5 = r11
            r4 = r1
            r11 = 1
            goto L_0x0090
        L_0x0047:
            android.view.ViewParent r11 = r7.getParent()
            if (r11 != 0) goto L_0x0050
        L_0x004d:
            r4 = r1
            r5 = r7
            goto L_0x002e
        L_0x0050:
            android.view.ViewParent r11 = r7.getParent()
            boolean r11 = r11 instanceof android.view.View
            if (r11 == 0) goto L_0x008d
            android.view.ViewParent r11 = r7.getParent()
            android.view.View r11 = (android.view.View) r11
            com.transitionseverywhere.TransitionValues r4 = r8.c((android.view.View) r11, (boolean) r3)
            com.transitionseverywhere.TransitionValues r5 = r8.d(r11, r3)
            com.transitionseverywhere.Visibility$VisibilityInfo r4 = b(r4, r5)
            boolean r4 = r4.f9489a
            if (r4 != 0) goto L_0x0073
            android.view.View r11 = com.transitionseverywhere.TransitionUtils.a((android.view.ViewGroup) r9, (android.view.View) r7, (android.view.View) r11)
            goto L_0x002c
        L_0x0073:
            android.view.ViewParent r4 = r11.getParent()
            if (r4 != 0) goto L_0x008b
            int r11 = r11.getId()
            if (r11 == r0) goto L_0x008b
            android.view.View r11 = r9.findViewById(r11)
            if (r11 == 0) goto L_0x008b
            boolean r11 = r8.D
            if (r11 == 0) goto L_0x008b
            r11 = r7
            goto L_0x002c
        L_0x008b:
            r11 = r1
            goto L_0x002c
        L_0x008d:
            r4 = r1
            r5 = r4
            goto L_0x002e
        L_0x0090:
            if (r5 == 0) goto L_0x00c5
            java.util.Map<java.lang.String, java.lang.Object> r13 = r10.b
            java.lang.String r0 = "android:visibility:screenLocation"
            java.lang.Object r13 = r13.get(r0)
            r6 = r13
            int[] r6 = (int[]) r6
            if (r11 != 0) goto L_0x00a6
            r13 = r6[r2]
            r0 = r6[r3]
            com.transitionseverywhere.utils.ViewGroupOverlayUtils.a(r9, r5, r13, r0)
        L_0x00a6:
            android.animation.Animator r10 = r8.b(r9, r5, r10, r12)
            if (r10 != 0) goto L_0x00b0
            com.transitionseverywhere.utils.ViewGroupOverlayUtils.a(r9, r5)
            goto L_0x00c4
        L_0x00b0:
            if (r11 != 0) goto L_0x00c4
            if (r7 == 0) goto L_0x00b9
            int r11 = com.transitionseverywhere.R.id.overlay_view
            r7.setTag(r11, r5)
        L_0x00b9:
            com.transitionseverywhere.Visibility$1 r11 = new com.transitionseverywhere.Visibility$1
            r2 = r11
            r3 = r8
            r4 = r9
            r2.<init>(r4, r5, r6, r7)
            r8.a((com.transitionseverywhere.Transition.TransitionListener) r11)
        L_0x00c4:
            return r10
        L_0x00c5:
            if (r4 == 0) goto L_0x00f5
            int r11 = r8.O
            if (r11 != r0) goto L_0x00d1
            int r11 = r8.P
            if (r11 == r0) goto L_0x00d0
            goto L_0x00d1
        L_0x00d0:
            r3 = 0
        L_0x00d1:
            if (r3 != 0) goto L_0x00da
            int r0 = r4.getVisibility()
            com.transitionseverywhere.utils.ViewUtils.a((android.view.View) r4, (int) r2)
        L_0x00da:
            android.animation.Animator r9 = r8.b(r9, r4, r10, r12)
            if (r9 == 0) goto L_0x00ef
            com.transitionseverywhere.Visibility$DisappearListener r10 = new com.transitionseverywhere.Visibility$DisappearListener
            r10.<init>(r4, r13, r3)
            r9.addListener(r10)
            com.transitionseverywhere.utils.AnimatorUtils.a(r9, r10)
            r8.a((com.transitionseverywhere.Transition.TransitionListener) r10)
            goto L_0x00f4
        L_0x00ef:
            if (r3 != 0) goto L_0x00f4
            com.transitionseverywhere.utils.ViewUtils.a((android.view.View) r4, (int) r0)
        L_0x00f4:
            return r9
        L_0x00f5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.transitionseverywhere.Visibility.b(android.view.ViewGroup, com.transitionseverywhere.TransitionValues, int, com.transitionseverywhere.TransitionValues, int):android.animation.Animator");
    }

    public boolean a(TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null && transitionValues2 == null) {
            return false;
        }
        if (transitionValues != null && transitionValues2 != null && transitionValues2.b.containsKey(d) != transitionValues.b.containsKey(d)) {
            return false;
        }
        VisibilityInfo b2 = b(transitionValues, transitionValues2);
        if (!b2.f9489a) {
            return false;
        }
        if (b2.c == 0 || b2.d == 0) {
            return true;
        }
        return false;
    }

    private static class DisappearListener extends AnimatorListenerAdapter implements Transition.TransitionListener {

        /* renamed from: a  reason: collision with root package name */
        boolean f9488a = false;
        private final boolean b;
        private final View c;
        private final int d;
        private final ViewGroup e;
        private boolean f;
        private boolean g;

        public void a(Transition transition) {
        }

        public void e(Transition transition) {
        }

        public DisappearListener(View view, int i, boolean z) {
            this.c = view;
            this.b = z;
            this.d = i;
            this.e = (ViewGroup) view.getParent();
            a(true);
        }

        public void onAnimationPause(Animator animator) {
            if (!this.f9488a && !this.b) {
                ViewUtils.a(this.c, this.d);
            }
        }

        public void onAnimationResume(Animator animator) {
            if (!this.f9488a && !this.b) {
                ViewUtils.a(this.c, 0);
            }
        }

        public void onAnimationCancel(Animator animator) {
            this.f9488a = true;
        }

        public void onAnimationEnd(Animator animator) {
            a();
        }

        public void b(Transition transition) {
            a();
        }

        public void c(Transition transition) {
            a(false);
        }

        public void d(Transition transition) {
            a(true);
        }

        private void a() {
            if (!this.f9488a) {
                if (this.b) {
                    this.c.setTag(R.id.transitionAlpha, Float.valueOf(this.c.getAlpha()));
                    this.c.setAlpha(0.0f);
                } else if (!this.g) {
                    ViewUtils.a(this.c, this.d);
                    if (this.e != null) {
                        this.e.invalidate();
                    }
                    this.g = true;
                }
            }
            a(false);
        }

        private void a(boolean z) {
            if (this.f != z && this.e != null && !this.b) {
                this.f = z;
                ViewGroupUtils.a(this.e, z);
            }
        }
    }
}
