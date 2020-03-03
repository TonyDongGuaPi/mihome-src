package com.mi.global.bbs.utils;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import com.mi.global.bbs.R;
import com.mi.global.bbs.utils.AnimUtils;

@RequiresApi(api = 19)
public class CircularReveal extends Visibility {
    private Point center;
    private View centerOn;
    @IdRes
    private int centerOnId = -1;
    private float endRadius;
    private float startRadius;

    public CircularReveal() {
    }

    @RequiresApi(api = 21)
    public CircularReveal(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircularReveal);
        this.startRadius = obtainStyledAttributes.getDimension(R.styleable.CircularReveal_startRadius, 0.0f);
        this.endRadius = obtainStyledAttributes.getDimension(R.styleable.CircularReveal_endRadius, 0.0f);
        this.centerOnId = obtainStyledAttributes.getResourceId(R.styleable.CircularReveal_centerOn, -1);
        obtainStyledAttributes.recycle();
    }

    public void setCenter(@NonNull Point point) {
        this.center = point;
    }

    public void centerOn(@NonNull View view) {
        this.centerOn = view;
    }

    public void setStartRadius(float f) {
        this.startRadius = f;
    }

    public void setEndRadius(float f) {
        this.endRadius = f;
    }

    @RequiresApi(api = 21)
    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (view == null || view.getHeight() == 0 || view.getWidth() == 0) {
            return null;
        }
        ensureCenterPoint(viewGroup, view);
        return new AnimUtils.NoPauseAnimator(ViewAnimationUtils.createCircularReveal(view, this.center.x, this.center.y, this.startRadius, getFullyRevealedRadius(view)));
    }

    @RequiresApi(api = 21)
    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (view == null || view.getHeight() == 0 || view.getWidth() == 0) {
            return null;
        }
        ensureCenterPoint(viewGroup, view);
        return new AnimUtils.NoPauseAnimator(ViewAnimationUtils.createCircularReveal(view, this.center.x, this.center.y, getFullyRevealedRadius(view), this.endRadius));
    }

    private void ensureCenterPoint(ViewGroup viewGroup, View view) {
        View view2;
        if (this.center == null) {
            if (!(this.centerOn == null && this.centerOnId == -1)) {
                if (this.centerOn != null) {
                    view2 = this.centerOn;
                } else {
                    view2 = viewGroup.findViewById(this.centerOnId);
                }
                if (view2 != null) {
                    int[] iArr = new int[2];
                    view2.getLocationInWindow(iArr);
                    view.getLocationInWindow(iArr);
                    this.center = new Point((iArr[0] + (view2.getWidth() / 2)) - iArr[0], (iArr[1] + (view2.getHeight() / 2)) - iArr[1]);
                }
            }
            if (this.center == null) {
                this.center = new Point(Math.round(view.getPivotX()), Math.round(view.getPivotY()));
            }
        }
    }

    private float getFullyRevealedRadius(@NonNull View view) {
        return (float) Math.hypot((double) Math.max(this.center.x, view.getWidth() - this.center.x), (double) Math.max(this.center.y, view.getHeight() - this.center.y));
    }
}
