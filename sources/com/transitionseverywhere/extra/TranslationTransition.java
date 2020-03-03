package com.transitionseverywhere.extra;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionValues;
import com.transitionseverywhere.utils.AnimatorUtils;
import com.transitionseverywhere.utils.PointFProperty;

@TargetApi(14)
public class TranslationTransition extends Transition {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9493a = "TranslationTransition:translationX";
    private static final String b = "TranslationTransition:translationY";
    private static final PointFProperty<View> c;

    static {
        if (Build.VERSION.SDK_INT >= 14) {
            c = new PointFProperty<View>() {
                /* renamed from: a */
                public void set(View view, PointF pointF) {
                    view.setTranslationX(pointF.x);
                    view.setTranslationY(pointF.y);
                }

                /* renamed from: a */
                public PointF get(View view) {
                    return new PointF(view.getTranslationX(), view.getTranslationY());
                }
            };
        } else {
            c = null;
        }
    }

    public TranslationTransition() {
    }

    public TranslationTransition(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void d(TransitionValues transitionValues) {
        if (transitionValues.f9482a != null) {
            transitionValues.b.put(f9493a, Float.valueOf(transitionValues.f9482a.getTranslationX()));
            transitionValues.b.put(b, Float.valueOf(transitionValues.f9482a.getTranslationY()));
        }
    }

    public void a(TransitionValues transitionValues) {
        d(transitionValues);
    }

    public void b(TransitionValues transitionValues) {
        d(transitionValues);
    }

    public Animator a(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null || c == null) {
            return null;
        }
        float floatValue = ((Float) transitionValues.b.get(f9493a)).floatValue();
        float floatValue2 = ((Float) transitionValues.b.get(b)).floatValue();
        float floatValue3 = ((Float) transitionValues2.b.get(f9493a)).floatValue();
        float floatValue4 = ((Float) transitionValues2.b.get(b)).floatValue();
        transitionValues2.f9482a.setTranslationX(floatValue);
        transitionValues2.f9482a.setTranslationY(floatValue2);
        return AnimatorUtils.a(transitionValues2.f9482a, c, s(), floatValue, floatValue2, floatValue3, floatValue4);
    }
}
