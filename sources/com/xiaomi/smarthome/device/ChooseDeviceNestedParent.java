package com.xiaomi.smarthome.device;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import com.xiaomi.smarthome.framework.log.LogUtil;

public class ChooseDeviceNestedParent extends LinearLayout implements NestedScrollingParent {
    private static final String c = "ChooseDeviceNestedParen";

    /* renamed from: a  reason: collision with root package name */
    private View f14801a;
    private int b;
    private int d = 0;
    private ScanViewDisplayState e = ScanViewDisplayState.show;
    private OnScanViewChangeListener f;
    private OffsetAnimator g;
    boolean isHideOrShow = false;
    boolean thisTouchHiddenTop = false;

    public interface OnScanViewChangeListener {
        void a();

        void a(float f);

        void b();
    }

    enum ScanViewDisplayState {
        show,
        medium,
        hide
    }

    public int getNestedScrollAxes() {
        return 0;
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
    }

    public ChooseDeviceNestedParent(Context context) {
        super(context);
    }

    public ChooseDeviceNestedParent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ChooseDeviceNestedParent(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setScanView(View view) {
        this.f14801a = view;
        this.f14801a.post(new Runnable() {
            public final void run() {
                ChooseDeviceNestedParent.this.b();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b() {
        this.b = this.f14801a.getHeight();
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        return i == 2 && this.f14801a != null;
    }

    public void onStopNestedScroll(View view) {
        a();
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        Log.d(c, "onNestedScroll: dyConsumed: " + i2 + ";dyUnconsumed: " + i4);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 3) {
            switch (action) {
                case 0:
                case 1:
                    break;
            }
        }
        this.thisTouchHiddenTop = false;
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (Throwable unused) {
            return true;
        }
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        int i3;
        boolean z = i2 > 0 && (-getTop()) < this.b;
        boolean z2 = (i2 < 0 && (-getTop()) > 0 && !view.canScrollVertically(-1)) || (i2 < 0 && (-getTop()) > 0 && (-getTop()) < this.b && this.thisTouchHiddenTop);
        this.isHideOrShow = false;
        if (z || z2) {
            if (z) {
                this.thisTouchHiddenTop = true;
            }
            int top = this.b + getTop();
            if (i2 > 0) {
                i3 = Math.min(i2, top);
            } else {
                i3 = -Math.min(-getTop(), -i2);
            }
            offsetTopAndBottom(-i3);
            iArr[1] = i3;
        }
    }

    public void offsetTopAndBottom(int i) {
        super.offsetTopAndBottom(i);
        this.d = getTop();
        requestLayout();
        if (this.f == null) {
            return;
        }
        if (getTop() == 0 && this.e != ScanViewDisplayState.show) {
            this.e = ScanViewDisplayState.show;
            this.f.b();
        } else if ((-getTop()) == this.b && this.e != ScanViewDisplayState.hide) {
            this.e = ScanViewDisplayState.hide;
            this.f.a();
        } else if (this.e != ScanViewDisplayState.medium) {
            this.f.a(((float) (-getTop())) / ((float) this.b));
        }
    }

    public void setOnScanViewChangeListener(OnScanViewChangeListener onScanViewChangeListener) {
        this.f = onScanViewChangeListener;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.d != 0) {
            super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() - this.d, 1073741824));
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.d != 0) {
            offsetTopAndBottom(this.d - i2);
        }
    }

    public boolean onNestedPreFling(View view, float f2, float f3) {
        LogUtil.a(c, "onNestedPreFling: ");
        boolean z = f3 > 0.0f && (-getTop()) < this.b;
        boolean z2 = f3 < 0.0f && (-getTop()) > 0 && !view.canScrollVertically(-1);
        if (!z && !z2) {
            return false;
        }
        this.isHideOrShow = false;
        a();
        return true;
    }

    private void a() {
        if (!this.isHideOrShow) {
            this.isHideOrShow = true;
            LogUtil.a(c, "hideOrShow: ");
            int top = getTop();
            if (Math.abs(top) != this.b) {
                int i = -top;
                if (i > this.b / 3) {
                    i = (-this.b) - top;
                }
                if (this.g != null) {
                    this.g.cancel();
                }
                this.g = OffsetAnimator.a(i);
                this.g.a((OffsetAnimator.IncrementListener) new OffsetAnimator.IncrementListener() {
                    public void a(int i) {
                        Log.d(ChooseDeviceNestedParent.c, "onChanged: " + i);
                        ChooseDeviceNestedParent.this.setOffset(i);
                    }
                });
                this.g.start();
            }
        }
    }

    static class OffsetAnimator extends ValueAnimator {

        /* renamed from: a  reason: collision with root package name */
        int f14803a = -1;
        /* access modifiers changed from: private */
        public IncrementListener b;

        interface IncrementListener {
            void a(int i);
        }

        static OffsetAnimator a(int i) {
            OffsetAnimator offsetAnimator = new OffsetAnimator();
            offsetAnimator.setInterpolator(new DecelerateInterpolator());
            offsetAnimator.setIntValues(new int[]{0, i});
            return offsetAnimator;
        }

        public void a(IncrementListener incrementListener) {
            this.b = incrementListener;
        }

        OffsetAnimator() {
            addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int i;
                    if (OffsetAnimator.this.b != null) {
                        Integer num = (Integer) valueAnimator.getAnimatedValue();
                        if (OffsetAnimator.this.f14803a == -1) {
                            OffsetAnimator offsetAnimator = OffsetAnimator.this;
                            i = num.intValue();
                            offsetAnimator.f14803a = i;
                        } else {
                            int intValue = num.intValue() - OffsetAnimator.this.f14803a;
                            OffsetAnimator.this.f14803a = num.intValue();
                            i = intValue;
                        }
                        OffsetAnimator.this.b.a(i);
                    }
                }
            });
            addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    OffsetAnimator.this.f14803a = -1;
                }
            });
        }
    }

    public void setOffset(int i) {
        offsetTopAndBottom(i);
    }
}
