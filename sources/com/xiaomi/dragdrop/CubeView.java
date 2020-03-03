package com.xiaomi.dragdrop;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;

public class CubeView extends FrameLayout {
    static int ANIMATION_TIME = 300;
    int mAnimationDuration;
    long mAnimationStartTime;
    float mCurrentDegree = 0.0f;
    boolean mDisableRightView = false;
    int mFromDegree;
    Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
    boolean mIsAnimation = false;
    int mShowCurrentIndex = 0;
    int mToDegree;

    /* access modifiers changed from: package-private */
    public int getAnotherIndex(int i) {
        return i == 0 ? 1 : 0;
    }

    public CubeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setShowViewIndex(int i) {
        if (i == 0 || i == 1) {
            this.mShowCurrentIndex = i;
            this.mCurrentDegree = 0.0f;
            this.mToDegree = 0;
            this.mFromDegree = 0;
            postInvalidate();
        }
    }

    public void setShowViewIndex2(int i) {
        if (i == 0 || i == 1) {
            this.mShowCurrentIndex = getAnotherIndex(i);
            this.mCurrentDegree = 90.0f;
            this.mToDegree = 90;
            this.mFromDegree = 90;
            postInvalidate();
        }
    }

    public int getShowViewIndex() {
        if (this.mCurrentDegree < 45.0f) {
            return this.mShowCurrentIndex;
        }
        return getAnotherIndex(this.mShowCurrentIndex);
    }

    public void setDisableRight(boolean z) {
        this.mDisableRightView = z;
    }

    /* access modifiers changed from: package-private */
    public void setViewVisible() {
        if (getShowViewIndex() == 0) {
            getChildAt(1).setVisibility(4);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        View view;
        View view2;
        if (getChildCount() == 2) {
            long drawingTime = getDrawingTime();
            if (this.mInterpolator != null && this.mAnimationDuration > 0) {
                float currentTimeMillis = (((float) (System.currentTimeMillis() - this.mAnimationStartTime)) * 1.0f) / ((float) this.mAnimationDuration);
                if (currentTimeMillis < 0.0f || currentTimeMillis >= 1.0f) {
                    this.mIsAnimation = false;
                    currentTimeMillis = 1.0f;
                } else {
                    postInvalidate();
                }
                this.mCurrentDegree = ((float) this.mFromDegree) + (((float) (this.mToDegree - this.mFromDegree)) * this.mInterpolator.getInterpolation(currentTimeMillis));
            }
            if (this.mShowCurrentIndex == 0) {
                view2 = getChildAt(0);
                view = getChildAt(1);
                if (this.mCurrentDegree == 0.0f) {
                    if (!getChildAt(1).isShown()) {
                        getChildAt(1).setVisibility(0);
                    }
                } else if (this.mCurrentDegree == 90.0f && getChildAt(1).isShown()) {
                    getChildAt(1).setVisibility(4);
                }
            } else {
                view2 = getChildAt(1);
                view = getChildAt(0);
                if (this.mCurrentDegree == 90.0f) {
                    if (!getChildAt(1).isShown()) {
                        getChildAt(1).setVisibility(0);
                    }
                } else if (this.mCurrentDegree == 0.0f && getChildAt(1).isShown()) {
                    getChildAt(1).setVisibility(4);
                }
            }
            canvas.save();
            Camera camera = new Camera();
            Matrix matrix = new Matrix();
            camera.save();
            camera.translate((((float) (-view2.getWidth())) * this.mCurrentDegree) / 90.0f, 0.0f, 0.0f);
            camera.rotateY(90.0f - this.mCurrentDegree);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.postTranslate((float) view2.getWidth(), (float) (view2.getHeight() / 2));
            matrix.preTranslate(0.0f, (float) ((-view2.getHeight()) / 2));
            canvas.concat(matrix);
            drawChild(canvas, view2, drawingTime);
            canvas.restore();
            canvas.save();
            Camera camera2 = new Camera();
            Matrix matrix2 = new Matrix();
            camera2.save();
            camera2.translate(((float) view.getWidth()) + (((-this.mCurrentDegree) / 90.0f) * ((float) view.getWidth())), 0.0f, 0.0f);
            camera2.rotateY(-this.mCurrentDegree);
            camera2.getMatrix(matrix2);
            camera2.restore();
            matrix2.postTranslate(0.0f, (float) (view.getHeight() / 2));
            matrix2.preTranslate((float) (-view.getWidth()), (float) ((-view.getHeight()) / 2));
            canvas.concat(matrix2);
            drawChild(canvas, view, drawingTime);
            canvas.restore();
            return;
        }
        super.dispatchDraw(canvas);
    }

    public void scrollToRightViewDelay(final int i, int i2) {
        postDelayed(new Runnable() {
            public void run() {
                CubeView.this.scrollToRightView(i);
            }
        }, (long) i2);
    }

    public void scrollToLeftViewDelay(final int i, int i2) {
        postDelayed(new Runnable() {
            public void run() {
                CubeView.this.scrollToLeftView(i);
            }
        }, (long) i2);
    }

    public void scrollToRightView(int i) {
        if (!this.mDisableRightView) {
            int anotherIndex = getAnotherIndex(i);
            setShowViewIndex2(anotherIndex);
            this.mIsAnimation = true;
            this.mAnimationStartTime = System.currentTimeMillis();
            this.mAnimationDuration = ANIMATION_TIME;
            this.mShowCurrentIndex = anotherIndex;
            this.mFromDegree = 0;
            this.mToDegree = 90;
            postInvalidate();
        }
    }

    public void scrollToLeftView(int i) {
        setShowViewIndex(getAnotherIndex(i));
        this.mIsAnimation = true;
        this.mAnimationStartTime = System.currentTimeMillis();
        this.mAnimationDuration = ANIMATION_TIME;
        this.mShowCurrentIndex = i;
        this.mFromDegree = 90;
        this.mToDegree = 0;
        postInvalidate();
    }

    public static CubeView findCubeView(View view) {
        if (view == null) {
            return null;
        }
        if (view instanceof CubeView) {
            return (CubeView) view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                if (viewGroup.getChildAt(i) instanceof CubeView) {
                    return (CubeView) viewGroup.getChildAt(i);
                }
            }
        }
        return null;
    }
}
