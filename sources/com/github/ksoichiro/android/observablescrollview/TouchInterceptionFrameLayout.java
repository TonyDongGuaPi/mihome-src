package com.github.ksoichiro.android.observablescrollview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class TouchInterceptionFrameLayout extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private boolean f5334a;
    private boolean b;
    private boolean c;
    private boolean d;
    private PointF e;
    private MotionEvent f;
    private TouchInterceptionListener g;

    public interface TouchInterceptionListener {
        void a(MotionEvent motionEvent);

        void a(MotionEvent motionEvent, float f, float f2);

        boolean a(MotionEvent motionEvent, boolean z, float f, float f2);

        void b(MotionEvent motionEvent);
    }

    public TouchInterceptionFrameLayout(Context context) {
        super(context);
    }

    public TouchInterceptionFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TouchInterceptionFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public TouchInterceptionFrameLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setScrollInterceptionListener(TouchInterceptionListener touchInterceptionListener) {
        this.g = touchInterceptionListener;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.g == null) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.e = new PointF(motionEvent.getX(), motionEvent.getY());
            this.f = MotionEvent.obtainNoHistory(motionEvent);
            this.b = true;
            this.f5334a = this.g.a(motionEvent, false, 0.0f, 0.0f);
            this.c = this.f5334a;
            this.d = false;
            return this.f5334a;
        } else if (actionMasked != 2) {
            return false;
        } else {
            if (this.e == null) {
                this.e = new PointF(motionEvent.getX(), motionEvent.getY());
            }
            this.f5334a = this.g.a(motionEvent, true, motionEvent.getX() - this.e.x, motionEvent.getY() - this.e.y);
            return this.f5334a;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.g != null) {
            switch (motionEvent.getActionMasked()) {
                case 0:
                    if (this.f5334a) {
                        this.g.a(motionEvent);
                        a(motionEvent, new MotionEvent[0]);
                        return true;
                    }
                    break;
                case 1:
                case 3:
                    this.c = false;
                    if (this.f5334a) {
                        this.g.b(motionEvent);
                    }
                    if (!this.d) {
                        this.d = true;
                        if (this.b) {
                            this.b = false;
                            MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(this.f);
                            obtainNoHistory.setLocation(motionEvent.getX(), motionEvent.getY());
                            a(motionEvent, obtainNoHistory);
                        } else {
                            a(motionEvent, new MotionEvent[0]);
                        }
                    }
                    return true;
                case 2:
                    if (this.e == null) {
                        this.e = new PointF(motionEvent.getX(), motionEvent.getY());
                    }
                    float x = motionEvent.getX() - this.e.x;
                    float y = motionEvent.getY() - this.e.y;
                    this.f5334a = this.g.a(motionEvent, true, x, y);
                    if (!this.f5334a) {
                        if (this.b) {
                            this.b = false;
                            MotionEvent obtainNoHistory2 = MotionEvent.obtainNoHistory(this.f);
                            obtainNoHistory2.setLocation(motionEvent.getX(), motionEvent.getY());
                            a(motionEvent, obtainNoHistory2);
                        } else {
                            a(motionEvent, new MotionEvent[0]);
                        }
                        this.c = false;
                        this.d = false;
                        break;
                    } else {
                        if (!this.c) {
                            this.c = true;
                            MotionEvent obtainNoHistory3 = MotionEvent.obtainNoHistory(this.f);
                            obtainNoHistory3.setLocation(motionEvent.getX(), motionEvent.getY());
                            this.g.a(obtainNoHistory3);
                            this.e = new PointF(motionEvent.getX(), motionEvent.getY());
                            x = 0.0f;
                            y = 0.0f;
                        }
                        if (!this.d) {
                            this.d = true;
                            a(a(motionEvent, 3), new MotionEvent[0]);
                        }
                        this.g.a(motionEvent, x, y);
                        this.b = true;
                        return true;
                    }
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    private MotionEvent a(MotionEvent motionEvent, int i) {
        MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        obtainNoHistory.setAction(i);
        return obtainNoHistory;
    }

    private void a(MotionEvent motionEvent, MotionEvent... motionEventArr) {
        if (motionEvent != null) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = getChildAt(childCount);
                if (childAt != null) {
                    Rect rect = new Rect();
                    childAt.getHitRect(rect);
                    MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                    if (!rect.contains((int) obtainNoHistory.getX(), (int) obtainNoHistory.getY())) {
                        continue;
                    } else {
                        float f2 = (float) (-childAt.getLeft());
                        float f3 = (float) (-childAt.getTop());
                        int i = 0;
                        if (motionEventArr != null) {
                            int length = motionEventArr.length;
                            boolean z = false;
                            while (i < length) {
                                MotionEvent motionEvent2 = motionEventArr[i];
                                if (motionEvent2 != null) {
                                    MotionEvent obtainNoHistory2 = MotionEvent.obtainNoHistory(motionEvent2);
                                    obtainNoHistory2.offsetLocation(f2, f3);
                                    z |= childAt.dispatchTouchEvent(obtainNoHistory2);
                                }
                                i++;
                            }
                            i = z;
                        }
                        obtainNoHistory.offsetLocation(f2, f3);
                        if (childAt.dispatchTouchEvent(obtainNoHistory) || i != false) {
                            return;
                        }
                    }
                }
            }
        }
    }
}
