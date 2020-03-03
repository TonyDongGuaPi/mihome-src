package com.transitionseverywhere;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.utils.MatrixUtils;
import com.transitionseverywhere.utils.ViewUtils;

@TargetApi(21)
public class ChangeTransform extends Transition {
    private static final String M = "android:changeTransform:intermediateParentMatrix";
    private static final String N = "android:changeTransform:intermediateMatrix";
    private static final String[] O = {b, c, e};
    /* access modifiers changed from: private */
    public static final Property<View, Matrix> P;

    /* renamed from: a  reason: collision with root package name */
    private static final String f9449a = "ChangeTransform";
    private static final String b = "android:changeTransform:matrix";
    private static final String c = "android:changeTransform:transforms";
    private static final String d = "android:changeTransform:parent";
    private static final String e = "android:changeTransform:parentMatrix";
    /* access modifiers changed from: private */
    public boolean Q = true;
    private boolean R = true;
    private Matrix S = new Matrix();

    static {
        if (Build.VERSION.SDK_INT >= 14) {
            P = new Property<View, Matrix>(Matrix.class, "animationMatrix") {
                /* renamed from: a */
                public Matrix get(View view) {
                    return null;
                }

                /* renamed from: a */
                public void set(View view, Matrix matrix) {
                    ViewUtils.c(view, matrix);
                }
            };
        } else {
            P = null;
        }
    }

    public ChangeTransform() {
    }

    public ChangeTransform(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ChangeTransform);
        this.Q = obtainStyledAttributes.getBoolean(R.styleable.ChangeTransform_reparentWithOverlay, true);
        this.R = obtainStyledAttributes.getBoolean(R.styleable.ChangeTransform_reparent, true);
        obtainStyledAttributes.recycle();
    }

    public boolean b() {
        return this.Q;
    }

    public void a(boolean z) {
        this.Q = z;
    }

    public boolean c() {
        return this.R;
    }

    public void b(boolean z) {
        this.R = z;
    }

    public String[] a() {
        return O;
    }

    private void d(TransitionValues transitionValues) {
        if (Build.VERSION.SDK_INT >= 21) {
            View view = transitionValues.f9482a;
            if (view.getVisibility() != 8) {
                transitionValues.b.put(d, view.getParent());
                transitionValues.b.put(c, new Transforms(view));
                Matrix matrix = view.getMatrix();
                transitionValues.b.put(b, (matrix == null || matrix.isIdentity()) ? null : new Matrix(matrix));
                if (this.R) {
                    Matrix matrix2 = new Matrix();
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    ViewUtils.a((View) viewGroup, matrix2);
                    matrix2.preTranslate((float) (-viewGroup.getScrollX()), (float) (-viewGroup.getScrollY()));
                    transitionValues.b.put(e, matrix2);
                    transitionValues.b.put(N, view.getTag(R.id.transitionTransform));
                    transitionValues.b.put(M, view.getTag(R.id.parentMatrix));
                }
            }
        }
    }

    public void a(TransitionValues transitionValues) {
        d(transitionValues);
    }

    public void b(TransitionValues transitionValues) {
        d(transitionValues);
    }

    public Animator a(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null || Build.VERSION.SDK_INT < 21 || !transitionValues.b.containsKey(d) || !transitionValues2.b.containsKey(d)) {
            return null;
        }
        boolean z = this.R && !a((ViewGroup) transitionValues.b.get(d), (ViewGroup) transitionValues2.b.get(d));
        Matrix matrix = (Matrix) transitionValues.b.get(N);
        if (matrix != null) {
            transitionValues.b.put(b, matrix);
        }
        Matrix matrix2 = (Matrix) transitionValues.b.get(M);
        if (matrix2 != null) {
            transitionValues.b.put(e, matrix2);
        }
        if (z) {
            b(transitionValues, transitionValues2);
        }
        ObjectAnimator a2 = a(transitionValues, transitionValues2, z);
        if (z && a2 != null && this.Q) {
            b(viewGroup, transitionValues, transitionValues2);
        }
        return a2;
    }

    private ObjectAnimator a(TransitionValues transitionValues, TransitionValues transitionValues2, boolean z) {
        Matrix matrix = (Matrix) transitionValues.b.get(b);
        Matrix matrix2 = (Matrix) transitionValues2.b.get(b);
        if (matrix == null) {
            matrix = MatrixUtils.f9497a;
        }
        if (matrix2 == null) {
            matrix2 = MatrixUtils.f9497a;
        }
        final Matrix matrix3 = matrix2;
        if (matrix.equals(matrix3)) {
            return null;
        }
        final Transforms transforms = (Transforms) transitionValues2.b.get(c);
        final View view = transitionValues2.f9482a;
        g(view);
        ObjectAnimator ofObject = ObjectAnimator.ofObject(view, P, new MatrixUtils.MatrixEvaluator(), new Matrix[]{matrix, matrix3});
        final boolean z2 = z;
        AnonymousClass2 r1 = new AnimatorListenerAdapter() {
            private boolean f;
            private Matrix g = new Matrix();

            public void onAnimationCancel(Animator animator) {
                this.f = true;
            }

            public void onAnimationEnd(Animator animator) {
                if (!this.f) {
                    if (!z2 || !ChangeTransform.this.Q) {
                        view.setTag(R.id.transitionTransform, (Object) null);
                        view.setTag(R.id.parentMatrix, (Object) null);
                    } else {
                        a(matrix3);
                    }
                }
                ChangeTransform.P.set(view, (Object) null);
                transforms.a(view);
            }

            public void onAnimationPause(Animator animator) {
                a((Matrix) ((ValueAnimator) animator).getAnimatedValue());
            }

            public void onAnimationResume(Animator animator) {
                ChangeTransform.g(view);
            }

            private void a(Matrix matrix) {
                this.g.set(matrix);
                view.setTag(R.id.transitionTransform, this.g);
                transforms.a(view);
            }
        };
        ofObject.addListener(r1);
        ofObject.addPauseListener(r1);
        return ofObject;
    }

    private boolean a(ViewGroup viewGroup, ViewGroup viewGroup2) {
        if (b((View) viewGroup) && b((View) viewGroup2)) {
            TransitionValues d2 = d(viewGroup, true);
            if (d2 == null || viewGroup2 != d2.f9482a) {
                return false;
            }
        } else if (viewGroup == viewGroup2) {
            return true;
        } else {
            return false;
        }
        return true;
    }

    private void b(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        View view = transitionValues2.f9482a;
        Matrix matrix = (Matrix) transitionValues2.b.get(e);
        Matrix matrix2 = new Matrix(matrix);
        ViewUtils.b((View) viewGroup, matrix2);
        Transition transition = this;
        while (transition.y != null) {
            transition = transition.y;
        }
        View a2 = ViewUtils.a(view, viewGroup, matrix2);
        if (a2 != null) {
            transition.a((Transition.TransitionListener) new GhostListener(view, a2, matrix));
        }
        if (transitionValues.f9482a != transitionValues2.f9482a) {
            view.setAlpha(0.0f);
        }
        view.setAlpha(1.0f);
    }

    private void b(TransitionValues transitionValues, TransitionValues transitionValues2) {
        Matrix matrix = (Matrix) transitionValues2.b.get(e);
        transitionValues2.f9482a.setTag(R.id.parentMatrix, matrix);
        Matrix matrix2 = this.S;
        matrix2.reset();
        matrix.invert(matrix2);
        Matrix matrix3 = (Matrix) transitionValues.b.get(b);
        if (matrix3 == null) {
            matrix3 = new Matrix();
            transitionValues.b.put(b, matrix3);
        }
        matrix3.postConcat((Matrix) transitionValues.b.get(e));
        matrix3.postConcat(matrix2);
    }

    /* access modifiers changed from: private */
    public static void g(View view) {
        b(view, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
    }

    /* access modifiers changed from: private */
    public static void b(View view, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        view.setTranslationX(f);
        view.setTranslationY(f2);
        ViewUtils.a(view, f3);
        view.setScaleX(f4);
        view.setScaleY(f5);
        view.setRotationX(f6);
        view.setRotationY(f7);
        view.setRotation(f8);
    }

    private static class Transforms {

        /* renamed from: a  reason: collision with root package name */
        public final float f9452a;
        public final float b;
        public final float c;
        public final float d;
        public final float e;
        public final float f;
        public final float g;
        public final float h;

        public Transforms(View view) {
            this.f9452a = view.getTranslationX();
            this.b = view.getTranslationY();
            this.c = ViewUtils.d(view);
            this.d = view.getScaleX();
            this.e = view.getScaleY();
            this.f = view.getRotationX();
            this.g = view.getRotationY();
            this.h = view.getRotation();
        }

        public void a(View view) {
            ChangeTransform.b(view, this.f9452a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Transforms)) {
                return false;
            }
            Transforms transforms = (Transforms) obj;
            if (transforms.f9452a == this.f9452a && transforms.b == this.b && transforms.c == this.c && transforms.d == this.d && transforms.e == this.e && transforms.f == this.f && transforms.g == this.g && transforms.h == this.h) {
                return true;
            }
            return false;
        }
    }

    private static class GhostListener extends Transition.TransitionListenerAdapter {

        /* renamed from: a  reason: collision with root package name */
        private View f9451a;
        private View b;
        private Matrix c;

        public GhostListener(View view, View view2, Matrix matrix) {
            this.f9451a = view;
            this.b = view2;
            this.c = matrix;
        }

        public void b(Transition transition) {
            transition.b((Transition.TransitionListener) this);
            ViewUtils.e(this.f9451a);
            this.f9451a.setTag(R.id.transitionTransform, (Object) null);
            this.f9451a.setTag(R.id.parentMatrix, (Object) null);
        }

        public void c(Transition transition) {
            this.b.setVisibility(4);
        }

        public void d(Transition transition) {
            this.b.setVisibility(0);
        }
    }
}
