package com.facebook.react.views.slider;

import android.animation.StateListAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;

public class ReactSlider extends AppCompatSeekBar {
    private static int DEFAULT_TOTAL_STEPS = 128;
    private double mMaxValue = 0.0d;
    private double mMinValue = 0.0d;
    private double mStep = 0.0d;
    private double mStepCalculated = 0.0d;
    private double mValue = 0.0d;

    public ReactSlider(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        disableStateListAnimatorIfNeeded();
    }

    private void disableStateListAnimatorIfNeeded() {
        if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT < 26) {
            super.setStateListAnimator((StateListAnimator) null);
        }
    }

    /* access modifiers changed from: package-private */
    public void setMaxValue(double d) {
        this.mMaxValue = d;
        updateAll();
    }

    /* access modifiers changed from: package-private */
    public void setMinValue(double d) {
        this.mMinValue = d;
        updateAll();
    }

    /* access modifiers changed from: package-private */
    public void setValue(double d) {
        this.mValue = d;
        updateValue();
    }

    /* access modifiers changed from: package-private */
    public void setStep(double d) {
        this.mStep = d;
        updateAll();
    }

    public double toRealProgress(int i) {
        if (i == getMax()) {
            return this.mMaxValue;
        }
        double d = (double) i;
        double stepValue = getStepValue();
        Double.isNaN(d);
        return (d * stepValue) + this.mMinValue;
    }

    private void updateAll() {
        if (this.mStep == 0.0d) {
            double d = this.mMaxValue - this.mMinValue;
            double d2 = (double) DEFAULT_TOTAL_STEPS;
            Double.isNaN(d2);
            this.mStepCalculated = d / d2;
        }
        setMax(getTotalSteps());
        updateValue();
    }

    private void updateValue() {
        double d = (this.mValue - this.mMinValue) / (this.mMaxValue - this.mMinValue);
        double totalSteps = (double) getTotalSteps();
        Double.isNaN(totalSteps);
        setProgress((int) Math.round(d * totalSteps));
    }

    private int getTotalSteps() {
        return (int) Math.ceil((this.mMaxValue - this.mMinValue) / getStepValue());
    }

    private double getStepValue() {
        return this.mStep > 0.0d ? this.mStep : this.mStepCalculated;
    }
}