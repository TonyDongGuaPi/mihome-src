package com.daimajia.easing;

import android.animation.TypeEvaluator;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class BaseEasingMethod implements TypeEvaluator<Number> {
    protected float mDuration;
    private ArrayList<EasingListener> mListeners = new ArrayList<>();

    public interface EasingListener {
        void on(float f, float f2, float f3, float f4, float f5);
    }

    public abstract Float calculate(float f, float f2, float f3, float f4);

    public void addEasingListener(EasingListener easingListener) {
        this.mListeners.add(easingListener);
    }

    public void addEasingListeners(EasingListener... easingListenerArr) {
        for (EasingListener add : easingListenerArr) {
            this.mListeners.add(add);
        }
    }

    public void removeEasingListener(EasingListener easingListener) {
        this.mListeners.remove(easingListener);
    }

    public void clearEasingListeners() {
        this.mListeners.clear();
    }

    public BaseEasingMethod(float f) {
        this.mDuration = f;
    }

    public void setDuration(float f) {
        this.mDuration = f;
    }

    public final Float evaluate(float f, Number number, Number number2) {
        float f2 = f * this.mDuration;
        float floatValue = number.floatValue();
        float floatValue2 = number2.floatValue() - number.floatValue();
        float f3 = this.mDuration;
        float floatValue3 = calculate(f2, floatValue, floatValue2, f3).floatValue();
        Iterator<EasingListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().on(f2, floatValue3, floatValue, floatValue2, f3);
        }
        return Float.valueOf(floatValue3);
    }
}
