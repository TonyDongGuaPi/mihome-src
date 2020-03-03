package com.xiaomi.smarthome.library.common.widget.crop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.xiaomi.smarthome.library.common.widget.crop.HighlightView;
import java.util.ArrayList;
import java.util.Iterator;

public class CropImageView extends ImageViewTouchBase {
    private Context mContext;
    ArrayList<HighlightView> mHighlightViews = new ArrayList<>();
    float mLastX;
    float mLastY;
    int mMotionEdge;
    HighlightView mMotionHighlightView = null;

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2 - 96, i3, i4 - 96);
        if (this.mBitmapDisplayed.b() != null) {
            Iterator<HighlightView> it = this.mHighlightViews.iterator();
            while (it.hasNext()) {
                HighlightView next = it.next();
                next.p.set(getImageMatrix());
                next.d();
                if (next.l) {
                    centerBasedOnHighlightView(next);
                }
            }
        }
    }

    @SuppressLint({"WrongCall"})
    public void setTopMargine(int i) {
        onLayout(false, this.mLeft, this.mTop - i, this.mRight, this.mTop - i);
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void zoomTo(float f, float f2, float f3) {
        super.zoomTo(f, f2, f3);
        Iterator<HighlightView> it = this.mHighlightViews.iterator();
        while (it.hasNext()) {
            HighlightView next = it.next();
            next.p.set(getImageMatrix());
            next.d();
        }
    }

    /* access modifiers changed from: protected */
    public void zoomIn() {
        super.zoomIn();
        Iterator<HighlightView> it = this.mHighlightViews.iterator();
        while (it.hasNext()) {
            HighlightView next = it.next();
            next.p.set(getImageMatrix());
            next.d();
        }
    }

    /* access modifiers changed from: protected */
    public void zoomOut() {
        super.zoomOut();
        Iterator<HighlightView> it = this.mHighlightViews.iterator();
        while (it.hasNext()) {
            HighlightView next = it.next();
            next.p.set(getImageMatrix());
            next.d();
        }
    }

    /* access modifiers changed from: protected */
    public void postTranslate(float f, float f2) {
        super.postTranslate(f, f2);
        for (int i = 0; i < this.mHighlightViews.size(); i++) {
            HighlightView highlightView = this.mHighlightViews.get(i);
            highlightView.p.postTranslate(f, f2);
            highlightView.d();
        }
    }

    private void recomputeFocus(MotionEvent motionEvent) {
        int i = 0;
        for (int i2 = 0; i2 < this.mHighlightViews.size(); i2++) {
            HighlightView highlightView = this.mHighlightViews.get(i2);
            highlightView.a(false);
            highlightView.d();
        }
        while (true) {
            if (i >= this.mHighlightViews.size()) {
                break;
            }
            HighlightView highlightView2 = this.mHighlightViews.get(i);
            int a2 = highlightView2.a(motionEvent.getX(), motionEvent.getY());
            highlightView2.a(a2);
            if (a2 == 1) {
                i++;
            } else if (!highlightView2.a()) {
                highlightView2.a(true);
                highlightView2.d();
            }
        }
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        CropImageActivity cropImageActivity = (CropImageActivity) this.mContext;
        int i = 0;
        if (cropImageActivity.mSaving) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (cropImageActivity.mWaitingToPick) {
                    recomputeFocus(motionEvent);
                    break;
                } else {
                    while (true) {
                        if (i >= this.mHighlightViews.size()) {
                            break;
                        } else {
                            HighlightView highlightView = this.mHighlightViews.get(i);
                            int a2 = highlightView.a(motionEvent.getX(), motionEvent.getY());
                            if (a2 != 1) {
                                this.mMotionEdge = a2;
                                this.mMotionHighlightView = highlightView;
                                this.mLastX = motionEvent.getX();
                                this.mLastY = motionEvent.getY();
                                this.mMotionHighlightView.a(a2 == 32 ? HighlightView.ModifyMode.Move : HighlightView.ModifyMode.Grow);
                                this.mMotionHighlightView.a(a2);
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                }
            case 1:
                if (cropImageActivity.mWaitingToPick) {
                    for (int i2 = 0; i2 < this.mHighlightViews.size(); i2++) {
                        HighlightView highlightView2 = this.mHighlightViews.get(i2);
                        if (highlightView2.a()) {
                            cropImageActivity.mCrop = highlightView2;
                            for (int i3 = 0; i3 < this.mHighlightViews.size(); i3++) {
                                if (i3 != i2) {
                                    this.mHighlightViews.get(i3).b(true);
                                }
                            }
                            centerBasedOnHighlightView(highlightView2);
                            ((CropImageActivity) this.mContext).mWaitingToPick = false;
                            return true;
                        }
                    }
                } else if (this.mMotionHighlightView != null) {
                    centerBasedOnHighlightView(this.mMotionHighlightView);
                    this.mMotionHighlightView.a(HighlightView.ModifyMode.None);
                }
                this.mMotionHighlightView = null;
                break;
            case 2:
                if (!cropImageActivity.mWaitingToPick) {
                    if (this.mMotionHighlightView != null) {
                        this.mMotionHighlightView.a(this.mMotionEdge, motionEvent.getX() - this.mLastX, motionEvent.getY() - this.mLastY);
                        this.mLastX = motionEvent.getX();
                        this.mLastY = motionEvent.getY();
                        cropImageActivity.mTitleBar.invalidate();
                        break;
                    }
                } else {
                    recomputeFocus(motionEvent);
                    break;
                }
                break;
        }
        switch (motionEvent.getAction()) {
            case 1:
                center(true, true);
                break;
            case 2:
                if (getScale() == 1.0f) {
                    center(true, true);
                    break;
                }
                break;
        }
        return true;
    }

    private void ensureVisible(HighlightView highlightView) {
        Rect rect = highlightView.n;
        int max = Math.max(0, this.mLeft - rect.left);
        int min = Math.min(0, this.mRight - rect.right);
        int max2 = Math.max(0, this.mTop - rect.top);
        int min2 = Math.min(0, this.mBottom - rect.bottom);
        if (max == 0) {
            max = min;
        }
        if (max2 != 0) {
            min2 = max2;
        }
        if (max != 0 || min2 != 0) {
            panBy((float) max, (float) min2);
        }
    }

    private void centerBasedOnHighlightView(HighlightView highlightView) {
        Rect rect = highlightView.n;
        float max = Math.max(1.0f, Math.min((((float) getWidth()) / ((float) rect.width())) * 0.6f, (((float) getHeight()) / ((float) rect.height())) * 0.6f) * getScale());
        if (((double) (Math.abs(max - getScale()) / max)) > 0.1d) {
            float[] fArr = {highlightView.o.centerX(), highlightView.o.centerY()};
            getImageMatrix().mapPoints(fArr);
            zoomTo(max, fArr[0], fArr[1], 300.0f);
        }
        ensureVisible(highlightView);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < this.mHighlightViews.size(); i++) {
            this.mHighlightViews.get(i).a(canvas);
        }
    }

    public void add(HighlightView highlightView) {
        this.mHighlightViews.add(highlightView);
        invalidate();
    }
}
