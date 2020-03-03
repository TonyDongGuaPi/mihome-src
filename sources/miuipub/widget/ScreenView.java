package miuipub.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import com.miuipub.internal.util.DeviceHelper;
import com.miuipub.internal.variable.Android_View_View_class;
import java.security.InvalidParameterException;
import miuipub.v6.R;

public class ScreenView extends ViewGroup {
    public static final int FLING_ALIGN = 4;
    public static final int FLING_CANCEL = 3;
    public static final int FLING_LEFT = 1;
    public static final int FLING_RIGHT = 2;
    public static final int SCREEN_ALIGN_CENTER = 2;
    public static final int SCREEN_ALIGN_CUSTOMIZED = 0;
    public static final int SCREEN_ALIGN_LEFT = 1;
    public static final int SCREEN_ALIGN_RIGHT = 3;
    public static final int SCREEN_TRANSITION_TYPE_CLASSIC = 0;
    public static final int SCREEN_TRANSITION_TYPE_CLASSIC_NO_OVER_SHOOT = 1;
    public static final int SCREEN_TRANSITION_TYPE_CROSSFADE = 2;
    public static final int SCREEN_TRANSITION_TYPE_CUBE = 4;
    public static final int SCREEN_TRANSITION_TYPE_CUSTOM = 9;
    public static final int SCREEN_TRANSITION_TYPE_FALLDOWN = 3;
    public static final int SCREEN_TRANSITION_TYPE_LEFTPAGE = 5;
    public static final int SCREEN_TRANSITION_TYPE_RIGHTPAGE = 6;
    public static final int SCREEN_TRANSITION_TYPE_ROTATE = 8;
    public static final int SCREEN_TRANSITION_TYPE_STACK = 7;
    protected static final int TOUCH_STATE_PINCHING = 4;
    protected static final int TOUCH_STATE_REST = 0;
    protected static final int TOUCH_STATE_SCROLLING = 1;
    protected static final int TOUCH_STATE_SLIDING = 3;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final Android_View_View_class f3105a = Android_View_View_class.Factory.getInstance().get();
    private static final String b = "ScreenView";
    private static final int c = -1;
    private static final int d = 48;
    private static final LinearLayout.LayoutParams e = new LinearLayout.LayoutParams(-1, -1, 1.0f);
    private static final int f = 1000;
    private static final int g = 500;
    private static final int h = 300;
    private static final int i = -1;
    private static final float j = 1.0E9f;
    private static final float k = 0.75f;
    private static final float l = ((float) (0.016d / Math.log(0.75d)));
    private static final int m = 300;
    private static final float n = 2500.0f;
    private static final float o = 0.4f;
    private static final float p = 1.3f;
    private SeekBarIndicator A;
    /* access modifiers changed from: private */
    public SlideBar B;
    private boolean C;
    private Runnable D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    /* access modifiers changed from: private */
    public int L;
    private int M;
    private int N;
    private float O;
    private boolean P;
    /* access modifiers changed from: private */
    public Scroller Q;
    private float R;
    private int S;
    private ScaleGestureDetector T;
    /* access modifiers changed from: private */
    public int U;
    private boolean V;
    private boolean W;
    private boolean aa;
    private int ab;
    private int ac;
    private int ad;
    private float ae;
    private float af;
    private float ag;
    private int ah;
    private ScreenViewOvershootInterpolator ai;
    private int aj;
    /* access modifiers changed from: private */
    public float ak;
    private SnapScreenOnceNotification al;
    private GestureVelocityTracker am;
    protected int mChildScreenWidth;
    protected int mCurrentScreen;
    protected float mLastMotionX;
    protected float mLastMotionY;
    protected int mScreenOffset;
    protected int mVisibleRange;
    private final float q;
    private boolean r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private ArrowIndicator y;
    private ArrowIndicator z;

    private interface Indicator {
        boolean fastOffset(int i);
    }

    public interface SnapScreenOnceNotification {
        void a(ScreenView screenView);

        void b(ScreenView screenView);
    }

    /* access modifiers changed from: protected */
    public void onPinchIn(ScaleGestureDetector scaleGestureDetector) {
    }

    /* access modifiers changed from: protected */
    public void onPinchOut(ScaleGestureDetector scaleGestureDetector) {
    }

    public void onResume() {
    }

    /* access modifiers changed from: protected */
    public void updateChildStaticTransformationByScreen(View view, float f2) {
    }

    public ScreenView(Context context) {
        super(context);
        this.q = Resources.getSystem().getDisplayMetrics().density * 1280.0f;
        this.r = true;
        this.t = R.drawable.v6_screen_view_arrow_left;
        this.u = R.drawable.v6_screen_view_arrow_left_gray;
        this.v = R.drawable.v6_screen_view_arrow_right;
        this.w = R.drawable.v6_screen_view_arrow_right_gray;
        this.x = R.drawable.v6_screen_view_seek_point_selector;
        this.D = new Runnable() {
            public void run() {
                ScreenView.this.c();
            }
        };
        this.mVisibleRange = 1;
        this.I = 0;
        this.L = -1;
        this.O = 0.33333334f;
        this.R = 0.5f;
        this.S = 0;
        this.U = 0;
        this.aa = true;
        this.ad = -1;
        this.ag = 0.5f;
        this.ah = 300;
        this.aj = 0;
        this.ak = 1.3f;
        this.am = new GestureVelocityTracker();
        a();
    }

    public ScreenView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScreenView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.q = Resources.getSystem().getDisplayMetrics().density * 1280.0f;
        this.r = true;
        this.t = R.drawable.v6_screen_view_arrow_left;
        this.u = R.drawable.v6_screen_view_arrow_left_gray;
        this.v = R.drawable.v6_screen_view_arrow_right;
        this.w = R.drawable.v6_screen_view_arrow_right_gray;
        this.x = R.drawable.v6_screen_view_seek_point_selector;
        this.D = new Runnable() {
            public void run() {
                ScreenView.this.c();
            }
        };
        this.mVisibleRange = 1;
        this.I = 0;
        this.L = -1;
        this.O = 0.33333334f;
        this.R = 0.5f;
        this.S = 0;
        this.U = 0;
        this.aa = true;
        this.ad = -1;
        this.ag = 0.5f;
        this.ah = 300;
        this.aj = 0;
        this.ak = 1.3f;
        this.am = new GestureVelocityTracker();
        a();
    }

    public void setOvershootTension(float f2) {
        this.ak = f2;
        if (this.ai != null) {
            float unused = this.ai.b = f2;
        }
    }

    public void setTouchSlop(int i2) {
        this.ab = i2;
    }

    public void setConfirmHorizontalScrollRatio(float f2) {
        this.ag = f2;
    }

    public void setScreenSnapDuration(int i2) {
        this.ah = i2;
    }

    public void setMaximumSnapVelocity(int i2) {
        this.ac = i2;
    }

    public void setScrollWholeScreen(boolean z2) {
        this.P = z2;
    }

    private void a() {
        setAlwaysDrawnWithCacheEnabled(true);
        setClipToPadding(true);
        this.ai = new ScreenViewOvershootInterpolator();
        this.Q = new Scroller(getContext(), this.ai);
        setCurrentScreenInner(0);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.ab = viewConfiguration.getScaledTouchSlop();
        setMaximumSnapVelocity(viewConfiguration.getScaledMaximumFlingVelocity());
        this.T = new ScaleGestureDetector(getContext(), new ScaleDetectorListener());
    }

    public void setArrowIndicatorMarginRect(Rect rect) {
        FrameLayout.LayoutParams layoutParams;
        FrameLayout.LayoutParams layoutParams2;
        if (rect != null) {
            if (this.y == null) {
                layoutParams2 = new FrameLayout.LayoutParams(-2, -2, 19);
                this.y = new ArrowIndicator(getContext());
                this.y.setImageResource(this.t);
                addIndicator(this.y, layoutParams2);
                layoutParams = new FrameLayout.LayoutParams(-2, -2, 21);
                this.z = new ArrowIndicator(getContext());
                this.z.setImageResource(this.v);
                addIndicator(this.z, layoutParams);
            } else {
                layoutParams2 = (FrameLayout.LayoutParams) this.y.getLayoutParams();
                layoutParams = (FrameLayout.LayoutParams) this.z.getLayoutParams();
            }
            layoutParams2.setMargins(rect.left, rect.top, 0, rect.bottom);
            layoutParams.setMargins(0, rect.top, rect.right, rect.bottom);
        } else if (this.y != null) {
            removeIndicator(this.y);
            removeIndicator(this.z);
            this.y = null;
            this.z = null;
        }
    }

    public void setArrowIndicatorResource(int i2, int i3, int i4, int i5) {
        this.t = i2;
        this.u = i3;
        this.v = i4;
        this.w = i5;
    }

    public void setSeekPointResource(int i2) {
        this.x = i2;
    }

    public void setSeekBarPosition(FrameLayout.LayoutParams layoutParams) {
        if (layoutParams != null) {
            if (this.A == null) {
                this.A = new SeekBarIndicator(getContext());
                this.A.setGravity(16);
                this.A.setAnimationCacheEnabled(false);
                for (int i2 = 0; i2 < getChildCount(); i2++) {
                    this.A.addView(f(), e);
                }
                addIndicator(this.A, layoutParams);
                return;
            }
            this.A.setLayoutParams(layoutParams);
        } else if (this.A != null) {
            removeIndicator(this.A);
            this.A = null;
        }
    }

    public void setSlideBarPosition(FrameLayout.LayoutParams layoutParams) {
        setSlideBarPosition(layoutParams, R.drawable.v6_screen_view_slide_bar, R.drawable.v6_screen_view_slide_bar_bg, false);
    }

    public void setSlideBarPosition(FrameLayout.LayoutParams layoutParams, int i2, int i3, boolean z2) {
        this.C = z2;
        if (layoutParams != null) {
            if (this.B == null) {
                this.B = new SlideBar(getContext(), i2, i3);
                this.B.setOnTouchListener(new SliderTouchListener());
                this.B.setAnimationCacheEnabled(false);
                addIndicator(this.B, layoutParams);
                return;
            }
            this.B.setLayoutParams(layoutParams);
        } else if (this.B != null) {
            removeIndicator(this.B);
            this.B = null;
        }
    }

    private void b() {
        if (this.B != null && this.C) {
            removeCallbacks(this.D);
            this.B.animate().cancel();
            this.B.setAlpha(1.0f);
            this.B.setVisibility(0);
            if (this.U == 0) {
                postDelayed(this.D, 1000);
            }
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.C) {
            this.B.animate().setDuration(DeviceHelper.d ? 500 : 0).alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    ScreenView.this.B.setVisibility(4);
                }
            });
        }
    }

    public void setIndicatorBarVisibility(int i2) {
        setSeekBarVisibility(i2);
        setSlideBarVisibility(i2);
    }

    public void setSeekBarVisibility(int i2) {
        if (this.A != null) {
            this.A.setVisibility(i2);
        }
    }

    public void setSlideBarVisibility(int i2) {
        if (this.B != null) {
            this.B.setVisibility(i2);
        }
    }

    public void setScreenPadding(Rect rect) {
        if (rect != null) {
            this.E = rect.top;
            this.F = rect.bottom;
            setPadding(rect.left, 0, rect.right, 0);
            return;
        }
        throw new InvalidParameterException("The padding parameter can not be null.");
    }

    public void setScreenAlignment(int i2) {
        this.G = i2;
    }

    public void setScreenOffset(int i2) {
        this.mScreenOffset = i2;
        this.G = 0;
        requestLayout();
    }

    private void d() {
        switch (this.G) {
            case 0:
                this.H = this.mScreenOffset;
                break;
            case 1:
                this.H = 0;
                break;
            case 2:
                this.H = (this.I - this.mChildScreenWidth) / 2;
                break;
            case 3:
                this.H = this.I - this.mChildScreenWidth;
                break;
        }
        this.H += getPaddingLeft();
    }

    private void a(int i2, boolean z2) {
        int i3;
        int i4;
        if (getWidth() > 0) {
            int screenCount = getScreenCount();
            int width = getWidth();
            int height = getHeight();
            for (int i5 = 0; i5 < this.s; i5++) {
                View childAt = getChildAt(i5 + screenCount);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = layoutParams.gravity;
                if (i6 != -1) {
                    int i7 = i6 & 7;
                    int i8 = i6 & 112;
                    if (i7 == 1) {
                        i3 = (((width - measuredWidth) / 2) + layoutParams.leftMargin) - layoutParams.rightMargin;
                    } else if (i7 != 3) {
                        i3 = i7 != 5 ? layoutParams.leftMargin : (width - measuredWidth) - layoutParams.rightMargin;
                    } else {
                        i3 = layoutParams.leftMargin;
                    }
                    i4 = i8 != 16 ? i8 != 48 ? i8 != 80 ? layoutParams.topMargin : (height - measuredHeight) - layoutParams.bottomMargin : layoutParams.topMargin : (((height - measuredHeight) / 2) + layoutParams.topMargin) - layoutParams.bottomMargin;
                } else {
                    i4 = 0;
                    i3 = 0;
                }
                if (z2 || childAt.getHeight() <= 0 || childAt.getWidth() <= 0) {
                    childAt.layout(i3, i4, measuredWidth + i3, measuredHeight + i4);
                } else {
                    childAt.setTranslationX((float) i2);
                }
            }
        }
    }

    private void a(int i2) {
        int i3;
        int screenCount = getScreenCount();
        if (this.B != null && screenCount > 0) {
            int a2 = this.B.a();
            int max = Math.max((a2 / screenCount) * this.mVisibleRange, 48);
            int i4 = this.mChildScreenWidth * screenCount;
            if (i4 <= a2) {
                i3 = 0;
            } else {
                i3 = ((a2 - max) * i2) / (i4 - a2);
            }
            this.B.a(i3, max + i3);
            if (isHardwareAccelerated()) {
                this.B.invalidate();
            }
        }
    }

    private void b(int i2) {
        if (this.y != null) {
            this.y.setImageResource(i2 <= 0 ? this.u : this.t);
            this.z.setImageResource(i2 >= ((getScreenCount() * this.mChildScreenWidth) - this.I) - this.H ? this.w : this.v);
        }
    }

    public void setOverScrollRatio(float f2) {
        this.O = f2;
        e();
    }

    public void setVisibleExtentionRatio(float f2) {
        this.R = f2;
    }

    private void e() {
        this.N = ((int) (((float) (-this.mChildScreenWidth)) * this.O)) - this.H;
        if (!this.P) {
            this.M = ((int) ((((float) this.mChildScreenWidth) * (((float) getScreenCount()) + this.O)) - ((float) this.I))) + this.H;
        } else {
            this.M = (int) (((float) (((getScreenCount() - 1) / this.mVisibleRange) * this.I)) + (((float) this.mChildScreenWidth) * this.O));
        }
    }

    public void scrollToScreen(int i2) {
        if (this.P) {
            i2 -= i2 % this.mVisibleRange;
        }
        measure(this.J, this.K);
        scrollTo((this.mChildScreenWidth * i2) - this.H, 0);
    }

    public void scrollTo(int i2, int i3) {
        this.af = (float) Math.max(this.N, Math.min(i2, this.M));
        this.ae = ((float) System.nanoTime()) / j;
        super.scrollTo((int) this.af, i3);
    }

    public void computeScroll() {
        if (this.Q.computeScrollOffset()) {
            this.af = (float) this.Q.getCurrX();
            f3105a.setScrollXDirectly(this, (int) this.af);
            this.ae = ((float) System.nanoTime()) / j;
            f3105a.setScrollYDirectly(this, this.Q.getCurrY());
            ViewCompat.postInvalidateOnAnimation(this);
        } else if (this.L != -1) {
            setCurrentScreenInner(Math.max(0, Math.min(this.L, getScreenCount() - 1)));
            this.L = -1;
            if (this.al != null) {
                this.al.b(this);
                this.al = null;
            }
        } else if (this.U == 1) {
            float nanoTime = ((float) System.nanoTime()) / j;
            float scrollX = this.af - ((float) getScrollX());
            f3105a.setScrollXDirectly(this, (int) (((float) getScrollX()) + (((float) Math.exp((double) ((nanoTime - this.ae) / l))) * scrollX)));
            this.ae = nanoTime;
            if (scrollX > 1.0f || scrollX < -1.0f) {
                postInvalidate();
            }
        }
        a(getScrollX(), false);
        a(getScrollX());
        b(getScrollX());
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        computeScroll();
        b();
    }

    public void setVisibility(int i2) {
        if (i2 == 0) {
            b();
        }
        super.setVisibility(i2);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        this.J = i2;
        this.K = i3;
        int screenCount = getScreenCount();
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.s; i6++) {
            View childAt = getChildAt(i6 + screenCount);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            childAt.measure(getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight(), layoutParams.width), getChildMeasureSpec(i3, getPaddingTop() + this.E + getPaddingBottom() + this.F, layoutParams.height));
            i4 = Math.max(i4, childAt.getMeasuredWidth());
            i5 = Math.max(i5, childAt.getMeasuredHeight());
        }
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < screenCount; i9++) {
            View childAt2 = getChildAt(i9);
            ViewGroup.LayoutParams layoutParams2 = childAt2.getLayoutParams();
            childAt2.measure(getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight(), layoutParams2.width), getChildMeasureSpec(i3, getPaddingTop() + this.E + getPaddingBottom() + this.F, layoutParams2.height));
            i7 = Math.max(i7, childAt2.getMeasuredWidth());
            i8 = Math.max(i8, childAt2.getMeasuredHeight());
        }
        int max = Math.max(i7, i4);
        int max2 = Math.max(i8, i5);
        setMeasuredDimension(resolveSize(max + getPaddingLeft() + getPaddingRight(), i2), resolveSize(max2 + getPaddingTop() + this.E + getPaddingBottom() + this.F, i3));
        if (screenCount > 0) {
            this.mChildScreenWidth = i7;
            this.I = (View.MeasureSpec.getSize(i2) - getPaddingLeft()) - getPaddingRight();
            d();
            if (this.mChildScreenWidth > 0) {
                this.mVisibleRange = Math.max(1, (this.I + ((int) (((float) this.mChildScreenWidth) * this.R))) / this.mChildScreenWidth);
            }
            setOverScrollRatio(this.O);
        }
        if (this.r && this.mVisibleRange > 0) {
            this.r = false;
            setHorizontalScrollBarEnabled(false);
            setCurrentScreen(this.mCurrentScreen);
            setHorizontalScrollBarEnabled(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        a(getScrollX(), true);
        int screenCount = getScreenCount();
        int i6 = 0;
        for (int i7 = 0; i7 < screenCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                childAt.layout(i6, getPaddingTop() + this.E, childAt.getMeasuredWidth() + i6, getPaddingTop() + this.E + childAt.getMeasuredHeight());
                i6 += childAt.getMeasuredWidth();
            }
        }
        if (this.P && this.mCurrentScreen % this.mVisibleRange > 0) {
            setCurrentScreen(this.mCurrentScreen - (this.mCurrentScreen % this.mVisibleRange));
        }
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j2) {
        a(view);
        return super.drawChild(canvas, view, j2);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        int indexOfChild = indexOfChild(view);
        if (indexOfChild >= getScreenCount()) {
            return super.requestChildRectangleOnScreen(view, rect, z2);
        }
        if (indexOfChild == this.mCurrentScreen && this.Q.isFinished()) {
            return false;
        }
        snapToScreen(indexOfChild);
        return true;
    }

    public boolean dispatchUnhandledMove(View view, int i2) {
        if (i2 == 17) {
            if (this.mCurrentScreen > 0) {
                snapToScreen(this.mCurrentScreen - 1);
                return true;
            }
        } else if (i2 == 66 && this.mCurrentScreen < getScreenCount() - 1) {
            snapToScreen(this.mCurrentScreen + 1);
            return true;
        }
        return super.dispatchUnhandledMove(view, i2);
    }

    /* access modifiers changed from: protected */
    public int getTouchState() {
        return this.U;
    }

    /* access modifiers changed from: private */
    public void a(MotionEvent motionEvent, int i2) {
        this.U = i2;
        getParent().requestDisallowInterceptTouchEvent(this.U != 0);
        if (this.U == 0) {
            this.ad = -1;
            this.aa = false;
            this.am.a();
        } else {
            if (motionEvent != null) {
                this.ad = motionEvent.getPointerId(0);
                this.mLastMotionX = motionEvent.getX(motionEvent.findPointerIndex(this.ad));
            }
            if (this.aa) {
                this.aa = false;
                View childAt = getChildAt(this.mCurrentScreen);
                if (childAt != null) {
                    childAt.cancelLongPress();
                }
            }
            if (this.U == 1) {
                this.af = (float) getScrollX();
                this.ae = ((float) System.nanoTime()) / j;
            }
        }
        b();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case 0:
                motionEvent.setAction(3);
                this.T.onTouchEvent(motionEvent);
                motionEvent.setAction(0);
                this.W = false;
                this.V = false;
                this.mLastMotionX = motionEvent.getX();
                this.mLastMotionY = motionEvent.getY();
                if (!this.Q.isFinished()) {
                    this.Q.abortAnimation();
                    a(motionEvent, 1);
                    break;
                } else {
                    this.aa = true;
                    break;
                }
            case 1:
            case 3:
                a(motionEvent, 0);
                break;
            case 2:
                b(motionEvent);
                if (this.U == 0 && a(motionEvent)) {
                    a(motionEvent, 1);
                    break;
                }
        }
        if (2 != (motionEvent.getAction() & 255)) {
            b(motionEvent);
        }
        if (this.W) {
            return true;
        }
        if (this.U == 0 || this.U == 3) {
            return false;
        }
        return true;
    }

    private boolean a(MotionEvent motionEvent) {
        float abs = Math.abs(motionEvent.getX(0) - this.mLastMotionX);
        if (abs <= Math.abs(motionEvent.getY(0) - this.mLastMotionY) * this.ag || abs <= ((float) (this.ab * motionEvent.getPointerCount()))) {
            return false;
        }
        return true;
    }

    private void b(MotionEvent motionEvent) {
        this.am.a(motionEvent);
        if (this.U == 0 || 4 == this.U) {
            this.T.onTouchEvent(motionEvent);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.W) {
            return true;
        }
        if (this.V) {
            b(motionEvent);
        }
        int action = motionEvent.getAction() & 255;
        int i2 = 0;
        if (action != 6) {
            switch (action) {
                case 1:
                case 3:
                    if (this.U == 1) {
                        c(this.ad);
                    }
                    a(motionEvent, 0);
                    break;
                case 2:
                    if (this.U == 0 && a(motionEvent)) {
                        a(motionEvent, 1);
                    }
                    if (this.U == 1) {
                        int findPointerIndex = motionEvent.findPointerIndex(this.ad);
                        if (findPointerIndex == -1) {
                            a(motionEvent, 1);
                            findPointerIndex = motionEvent.findPointerIndex(this.ad);
                        }
                        float x2 = motionEvent.getX(findPointerIndex);
                        float f2 = this.mLastMotionX - x2;
                        this.mLastMotionX = x2;
                        if (f2 == 0.0f) {
                            awakenScrollBars();
                            break;
                        } else {
                            scrollTo(Math.round(this.af + f2), 0);
                            break;
                        }
                    }
                    break;
            }
        } else {
            int action2 = (motionEvent.getAction() & 65280) >> 8;
            if (motionEvent.getPointerId(action2) == this.ad) {
                if (action2 == 0) {
                    i2 = 1;
                }
                this.mLastMotionX = motionEvent.getX(i2);
                this.ad = motionEvent.getPointerId(i2);
                this.am.a(this.ad);
            }
        }
        this.V = true;
        return true;
    }

    private void c(int i2) {
        if (this.mChildScreenWidth > 0 && getCurrentScreen() != null) {
            int a2 = (int) this.am.a(1000, this.ac, i2);
            snapByVelocity(a2, this.am.a((float) Math.abs(a2)));
        }
    }

    /* access modifiers changed from: protected */
    public void snapByVelocity(int i2, int i3) {
        if (i3 == 1 && this.mCurrentScreen > 0) {
            snapToScreen(this.mCurrentScreen - this.mVisibleRange, i2, true);
        } else if (i3 == 2 && this.mCurrentScreen < getScreenCount() - 1) {
            snapToScreen(this.mCurrentScreen + this.mVisibleRange, i2, true);
        } else if (i3 == 3) {
            snapToScreen(this.mCurrentScreen, i2, true);
        } else {
            snapToScreen((getScrollX() + ((this.mChildScreenWidth * (this.P ? this.mVisibleRange : 1)) >> 1)) / this.mChildScreenWidth, 0, true);
        }
    }

    public void snapToScreen(int i2) {
        snapToScreen(i2, 0, false);
    }

    public void snapToScreen(int i2, SnapScreenOnceNotification snapScreenOnceNotification) {
        snapToScreen(i2, 0, false, snapScreenOnceNotification);
    }

    /* access modifiers changed from: protected */
    public void snapToScreen(int i2, int i3, boolean z2, SnapScreenOnceNotification snapScreenOnceNotification) {
        if (this.I > 0) {
            if (this.P) {
                this.L = Math.max(0, Math.min(i2, getScreenCount() - 1));
                this.L -= this.L % this.mVisibleRange;
            } else {
                this.L = Math.max(0, Math.min(i2, getScreenCount() - this.mVisibleRange));
            }
            int max = Math.max(1, Math.abs(this.L - this.mCurrentScreen));
            if (!this.Q.isFinished()) {
                if (this.al != null) {
                    this.al.a(this);
                }
                this.Q.abortAnimation();
            }
            this.al = snapScreenOnceNotification;
            int abs = Math.abs(i3);
            if (z2) {
                this.ai.a(max, abs);
            } else {
                this.ai.a();
            }
            int scrollX = ((this.L * this.mChildScreenWidth) - this.H) - getScrollX();
            if (scrollX != 0) {
                int abs2 = (Math.abs(scrollX) * this.ah) / this.I;
                if (abs > 0) {
                    abs2 += (int) ((((float) abs2) / (((float) abs) / n)) * 0.4f);
                }
                int max2 = Math.max(this.ah, abs2);
                this.Q.startScroll(getScrollX(), 0, scrollX, 0, max <= 1 ? Math.min(max2, this.ah * 2) : max2);
                invalidate();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void snapToScreen(int i2, int i3, boolean z2) {
        snapToScreen(i2, i3, z2, (SnapScreenOnceNotification) null);
    }

    public final int getScreenCount() {
        return this.S;
    }

    public int getCurrentScreenIndex() {
        if (this.L != -1) {
            return this.L;
        }
        return this.mCurrentScreen;
    }

    public View getCurrentScreen() {
        return getScreen(this.mCurrentScreen);
    }

    public void setCurrentScreen(int i2) {
        int i3;
        if (this.P) {
            int max = Math.max(0, Math.min(i2, getScreenCount() - 1));
            i3 = max - (max % this.mVisibleRange);
        } else {
            i3 = Math.max(0, Math.min(i2, getScreenCount() - this.mVisibleRange));
        }
        setCurrentScreenInner(i3);
        if (!this.r) {
            if (!this.Q.isFinished()) {
                this.Q.abortAnimation();
            }
            scrollToScreen(this.mCurrentScreen);
            invalidate();
        }
    }

    /* access modifiers changed from: private */
    public void setCurrentScreenInner(int i2) {
        a(this.mCurrentScreen, i2);
        this.mCurrentScreen = i2;
        this.L = -1;
    }

    public View getScreen(int i2) {
        if (i2 < 0 || i2 >= getScreenCount()) {
            return null;
        }
        return getChildAt(i2);
    }

    public int getVisibleRange() {
        return this.mVisibleRange;
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        int screenCount = getScreenCount();
        if (i2 >= 0) {
            screenCount = Math.min(i2, screenCount);
        }
        if (this.A != null) {
            this.A.addView(f(), screenCount, e);
        }
        this.S++;
        e();
        super.addView(view, screenCount, layoutParams);
    }

    public void removeView(View view) {
        throw new UnsupportedOperationException("ScreenView doesn't support remove view directly.");
    }

    public void removeViewInLayout(View view) {
        throw new UnsupportedOperationException("ScreenView doesn't support remove view directly.");
    }

    public void removeViewsInLayout(int i2, int i3) {
        throw new UnsupportedOperationException("ScreenView doesn't support remove view directly.");
    }

    public void removeViewAt(int i2) {
        throw new UnsupportedOperationException("ScreenView doesn't support remove view directly.");
    }

    public void removeViews(int i2, int i3) {
        throw new UnsupportedOperationException("ScreenView doesn't support remove view directly.");
    }

    public void removeAllViewsInLayout() {
        this.s = 0;
        this.S = 0;
        super.removeAllViewsInLayout();
    }

    /* access modifiers changed from: protected */
    public void addIndicator(View view, FrameLayout.LayoutParams layoutParams) {
        this.s++;
        super.addView(view, -1, layoutParams);
    }

    /* access modifiers changed from: protected */
    public void addIndicatorAt(View view, FrameLayout.LayoutParams layoutParams, int i2) {
        int max = Math.max(-1, Math.min(i2, this.s));
        if (max >= 0) {
            max += getScreenCount();
        }
        this.s++;
        super.addView(view, max, layoutParams);
    }

    /* access modifiers changed from: protected */
    public void removeIndicator(View view) {
        int indexOfChild = indexOfChild(view);
        if (indexOfChild >= getScreenCount()) {
            this.s--;
            super.removeViewAt(indexOfChild);
            return;
        }
        throw new InvalidParameterException("The view passed through the parameter must be indicator.");
    }

    public void removeScreen(int i2) {
        if (i2 < getScreenCount()) {
            if (i2 == this.mCurrentScreen) {
                if (!this.P) {
                    setCurrentScreen(Math.max(0, i2 - 1));
                } else if (i2 != 0 && i2 == getScreenCount() - 1) {
                    snapToScreen(i2 - 1);
                }
            }
            if (this.A != null) {
                this.A.removeViewAt(i2);
            }
            this.S--;
            super.removeViewAt(i2);
            return;
        }
        throw new InvalidParameterException("The view specified by the index must be a screen.");
    }

    public void removeAllScreens() {
        removeScreensInLayout(0, getScreenCount());
        requestLayout();
        invalidate();
    }

    public void removeScreensInLayout(int i2, int i3) {
        if (i2 >= 0 && i2 < getScreenCount()) {
            int min = Math.min(i3, getScreenCount() - i2);
            if (this.A != null) {
                this.A.removeViewsInLayout(i2, min);
            }
            this.S = 0;
            super.removeViewsInLayout(i2, min);
        }
    }

    public boolean allowLongPress() {
        return this.aa;
    }

    public void setAllowLongPress(boolean z2) {
        this.aa = z2;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        int screenCount = getScreenCount();
        for (int i2 = 0; i2 < screenCount; i2++) {
            getChildAt(i2).setOnLongClickListener(onLongClickListener);
        }
    }

    public ImageView getSeekPointView(int i2) {
        return (ImageView) this.A.getChildAt(i2);
    }

    private ImageView f() {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(this.x);
        return imageView;
    }

    /* access modifiers changed from: private */
    public void a(int i2, int i3) {
        int i4;
        int i5;
        if (this.A != null) {
            int screenCount = getScreenCount();
            int i6 = 0;
            int i7 = 0;
            while (i7 < this.mVisibleRange && (i5 = i2 + i7) < screenCount) {
                this.A.getChildAt(i5).setSelected(false);
                i7++;
            }
            while (i6 < this.mVisibleRange && (i4 = i3 + i6) < screenCount) {
                this.A.getChildAt(i4).setSelected(true);
                i6++;
            }
        }
    }

    public void onPause() {
        if (!this.Q.isFinished()) {
            setCurrentScreen((int) Math.floor((double) ((this.Q.getCurrX() + (this.mChildScreenWidth / 2)) / this.mChildScreenWidth)));
            this.Q.abortAnimation();
        }
    }

    public int getScreenTransitionType() {
        return this.aj;
    }

    public void setScreenTransitionType(int i2) {
        if (i2 != this.aj) {
            this.aj = i2;
            switch (this.aj) {
                case 0:
                    setOvershootTension(1.3f);
                    setScreenSnapDuration(300);
                    return;
                case 1:
                case 2:
                    setOvershootTension(0.0f);
                    setScreenSnapDuration(270);
                    return;
                case 3:
                    setOvershootTension(1.3f);
                    setScreenSnapDuration(300);
                    return;
                case 4:
                    setOvershootTension(0.0f);
                    setScreenSnapDuration(330);
                    return;
                case 5:
                    setOvershootTension(0.0f);
                    setScreenSnapDuration(330);
                    return;
                case 7:
                    setOvershootTension(0.0f);
                    setScreenSnapDuration(270);
                    return;
                case 8:
                    setOvershootTension(1.3f);
                    setScreenSnapDuration(330);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void resetTransformation(View view) {
        view.setAlpha(1.0f);
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        view.setPivotX(0.0f);
        view.setPivotY(0.0f);
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setCameraDistance(this.q);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
    }

    private void a(View view) {
        if (!(view instanceof Indicator)) {
            float measuredWidth = (float) view.getMeasuredWidth();
            float measuredHeight = (float) view.getMeasuredHeight();
            float f2 = measuredWidth / 2.0f;
            float f3 = measuredHeight / 2.0f;
            float scrollX = (((((float) getScrollX()) + (((float) getMeasuredWidth()) / 2.0f)) - ((float) view.getLeft())) - f2) / measuredWidth;
            switch (this.aj) {
                case 0:
                    resetTransformation(view);
                    return;
                case 1:
                    resetTransformation(view);
                    return;
                case 2:
                    if (scrollX == 0.0f || Math.abs(scrollX) > 1.0f) {
                        resetTransformation(view);
                        return;
                    }
                    view.setAlpha(((1.0f - Math.abs(scrollX)) * 0.7f) + 0.3f);
                    view.setTranslationX(0.0f);
                    view.setTranslationY(0.0f);
                    view.setScaleX(1.0f);
                    view.setScaleY(1.0f);
                    view.setPivotX(0.0f);
                    view.setPivotY(0.0f);
                    view.setRotation(0.0f);
                    view.setRotationX(0.0f);
                    view.setRotationY(0.0f);
                    view.setCameraDistance(this.q);
                    return;
                case 3:
                    if (scrollX == 0.0f || Math.abs(scrollX) > 1.0f) {
                        resetTransformation(view);
                        return;
                    }
                    view.setAlpha(1.0f);
                    view.setTranslationX(0.0f);
                    view.setTranslationY(0.0f);
                    view.setScaleX(1.0f);
                    view.setScaleY(1.0f);
                    view.setPivotX(f2);
                    view.setPivotY(measuredHeight);
                    view.setRotation((-scrollX) * 30.0f);
                    view.setRotationX(0.0f);
                    view.setRotationY(0.0f);
                    view.setCameraDistance(this.q);
                    return;
                case 4:
                    if (scrollX == 0.0f || Math.abs(scrollX) > 1.0f) {
                        resetTransformation(view);
                        return;
                    }
                    view.setAlpha(1.0f);
                    view.setTranslationX(0.0f);
                    view.setTranslationY(0.0f);
                    view.setScaleX(1.0f);
                    view.setScaleY(1.0f);
                    if (scrollX < 0.0f) {
                        measuredWidth = 0.0f;
                    }
                    view.setPivotX(measuredWidth);
                    view.setPivotY(f3);
                    view.setRotation(0.0f);
                    view.setRotationX(0.0f);
                    view.setRotationY(scrollX * -90.0f);
                    view.setCameraDistance(5000.0f);
                    return;
                case 5:
                    if (scrollX == 0.0f || Math.abs(scrollX) > 1.0f) {
                        resetTransformation(view);
                        return;
                    }
                    view.setAlpha(1.0f - Math.abs(scrollX));
                    view.setTranslationY(0.0f);
                    view.setTranslationX((measuredWidth * scrollX) - ((measuredWidth * Math.abs(scrollX)) * 0.3f));
                    float f4 = (0.3f * scrollX) + 1.0f;
                    view.setScaleX(f4);
                    view.setScaleY(f4);
                    view.setPivotX(0.0f);
                    view.setPivotY(f3);
                    view.setRotation(0.0f);
                    view.setRotationX(0.0f);
                    view.setRotationY((-scrollX) * 45.0f);
                    view.setCameraDistance(5000.0f);
                    return;
                case 7:
                    if (scrollX <= 0.0f) {
                        resetTransformation(view);
                        return;
                    }
                    float f5 = 1.0f - scrollX;
                    view.setAlpha(f5);
                    float f6 = (f5 * 0.4f) + 0.6f;
                    float f7 = 1.0f - f6;
                    view.setTranslationX(measuredWidth * f7 * 3.0f);
                    view.setTranslationY(measuredHeight * f7 * 0.5f);
                    view.setScaleX(f6);
                    view.setScaleY(f6);
                    view.setPivotX(0.0f);
                    view.setPivotY(0.0f);
                    view.setRotation(0.0f);
                    view.setRotationX(0.0f);
                    view.setRotationY(0.0f);
                    view.setCameraDistance(this.q);
                    return;
                case 8:
                    if (scrollX == 0.0f || Math.abs(scrollX) > 1.0f) {
                        resetTransformation(view);
                        return;
                    }
                    view.setAlpha(1.0f - Math.abs(scrollX));
                    view.setTranslationX(measuredWidth * scrollX);
                    view.setTranslationY(0.0f);
                    view.setScaleX(1.0f);
                    view.setScaleY(1.0f);
                    view.setPivotX(f2);
                    view.setPivotY(f3);
                    view.setRotation(0.0f);
                    view.setRotationX(0.0f);
                    view.setRotationY((-scrollX) * 90.0f);
                    view.setCameraDistance(5000.0f);
                    return;
                case 9:
                    updateChildStaticTransformationByScreen(view, scrollX);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finishCurrentGesture() {
        this.W = true;
        a((MotionEvent) null, 0);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f3109a = this.mCurrentScreen;
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.f3109a != -1) {
            setCurrentScreen(savedState.f3109a);
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        int f3109a;

        SavedState(Parcelable parcelable) {
            super(parcelable);
            this.f3109a = -1;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f3109a = -1;
            this.f3109a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f3109a);
        }
    }

    private class GestureVelocityTracker {
        private static final float b = 3.0f;
        private VelocityTracker c;
        private int d;
        private float e;
        private float f;
        private float g;

        private GestureVelocityTracker() {
            this.d = -1;
            this.e = -1.0f;
            this.f = -1.0f;
            this.g = -1.0f;
        }

        public void a() {
            if (this.c != null) {
                this.c.recycle();
                this.c = null;
            }
            b();
        }

        public void a(MotionEvent motionEvent) {
            if (this.c == null) {
                this.c = VelocityTracker.obtain();
            }
            this.c.addMovement(motionEvent);
            float x = motionEvent.getX();
            if (this.d != -1) {
                int findPointerIndex = motionEvent.findPointerIndex(this.d);
                if (findPointerIndex != -1) {
                    x = motionEvent.getX(findPointerIndex);
                } else {
                    this.d = -1;
                }
            }
            if (this.e < 0.0f) {
                this.e = x;
            } else if (this.g < 0.0f) {
                this.g = x;
            } else {
                if (this.f < 0.0f) {
                    if (((this.g > this.e && x < this.g) || (this.g < this.e && x > this.g)) && Math.abs(x - this.e) > b) {
                        this.f = this.g;
                    }
                } else if (this.f != this.g && (((this.g > this.f && x < this.g) || (this.g < this.f && x > this.g)) && Math.abs(x - this.f) > b)) {
                    this.e = this.f;
                    this.f = this.g;
                }
                this.g = x;
            }
        }

        private void b() {
            this.d = -1;
            float f2 = (float) -1;
            this.e = f2;
            this.f = f2;
            this.g = f2;
        }

        public void a(int i) {
            if (this.c == null) {
                this.c = VelocityTracker.obtain();
            } else {
                this.c.clear();
            }
            b();
            this.d = i;
        }

        public float a(int i, int i2, int i3) {
            this.c.computeCurrentVelocity(i, (float) i2);
            return this.c.getXVelocity(i3);
        }

        public int a(float f2) {
            if (f2 <= 300.0f) {
                return 4;
            }
            if (this.f < 0.0f) {
                if (this.g > this.e) {
                    return 1;
                }
                return 2;
            } else if (this.g < this.f) {
                if (ScreenView.this.getScrollX() < ScreenView.this.getCurrentScreen().getLeft()) {
                    return 3;
                }
                return 2;
            } else if (this.g <= this.f || ScreenView.this.getScrollX() > ScreenView.this.getCurrentScreen().getLeft()) {
                return 3;
            } else {
                return 1;
            }
        }
    }

    private class ScreenViewOvershootInterpolator implements Interpolator {
        /* access modifiers changed from: private */
        public float b;

        public ScreenViewOvershootInterpolator() {
            this.b = ScreenView.this.ak;
        }

        public void a(int i, int i2) {
            float f;
            if (i > 0) {
                f = ScreenView.this.ak / ((float) i);
            } else {
                f = ScreenView.this.ak;
            }
            this.b = f;
        }

        public void a() {
            this.b = 0.0f;
        }

        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * (((this.b + 1.0f) * f2) + this.b)) + 1.0f;
        }
    }

    private class ArrowIndicator extends ImageView implements Indicator {
        public ArrowIndicator(Context context) {
            super(context);
        }

        public boolean fastOffset(int i) {
            if (getLeft() == i) {
                return false;
            }
            ScreenView.f3105a.setRightDirectly(this, (getRight() + i) - getLeft());
            ScreenView.f3105a.setLeftDirectly(this, i);
            return true;
        }
    }

    private class SeekBarIndicator extends LinearLayout implements Indicator {
        public SeekBarIndicator(Context context) {
            super(context);
        }

        public boolean fastOffset(int i) {
            if (getLeft() == i) {
                return false;
            }
            ScreenView.f3105a.setRightDirectly(this, (getRight() + i) - getLeft());
            ScreenView.f3105a.setLeftDirectly(this, i);
            return true;
        }
    }

    private class SlideBar extends FrameLayout implements Indicator {
        private Bitmap b;
        private NinePatch c;
        private Rect d = new Rect();
        private Rect e = new Rect();

        public SlideBar(Context context, int i, int i2) {
            super(context);
            byte[] ninePatchChunk;
            this.b = BitmapFactory.decodeResource(getResources(), i);
            if (this.b != null && (ninePatchChunk = this.b.getNinePatchChunk()) != null) {
                this.c = new NinePatch(this.b, ninePatchChunk, (String) null);
                FrameLayout frameLayout = new FrameLayout(getContext());
                frameLayout.setBackgroundResource(i2);
                addView(frameLayout, new FrameLayout.LayoutParams(-1, -2, 80));
                this.e.left = frameLayout.getPaddingLeft();
                this.e.top = frameLayout.getPaddingTop();
                this.e.right = frameLayout.getPaddingRight();
                this.e.bottom = frameLayout.getPaddingBottom();
                this.d.top = this.e.top;
                this.d.bottom = this.d.top + this.b.getHeight();
            }
        }

        /* access modifiers changed from: protected */
        public int getSuggestedMinimumHeight() {
            return Math.max(this.b.getHeight(), super.getSuggestedMinimumHeight());
        }

        /* access modifiers changed from: protected */
        public void dispatchDraw(Canvas canvas) {
            super.dispatchDraw(canvas);
            if (this.c != null) {
                this.c.draw(canvas, this.d);
            }
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            if (this.c != null) {
                this.d.bottom = (i4 - i2) - this.e.bottom;
                this.d.top = this.d.bottom - this.c.getHeight();
            }
        }

        public void a(int i, int i2) {
            this.d.left = i + this.e.left;
            this.d.right = i2 + this.e.left;
        }

        public int a() {
            return (getMeasuredWidth() - this.e.left) - this.e.right;
        }

        public boolean fastOffset(int i) {
            if (getLeft() == i) {
                return false;
            }
            ScreenView.f3105a.setRightDirectly(this, (getRight() + i) - getLeft());
            ScreenView.f3105a.setLeftDirectly(this, i);
            return true;
        }
    }

    private class SliderTouchListener implements View.OnTouchListener {
        private SliderTouchListener() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int width = view.getWidth();
            float max = Math.max(0.0f, Math.min(motionEvent.getX(), (float) (width - 1)));
            int screenCount = ScreenView.this.getScreenCount();
            float f = (float) width;
            int floor = (int) Math.floor((double) ((((float) screenCount) * max) / f));
            switch (motionEvent.getAction()) {
                case 0:
                    if (!ScreenView.this.Q.isFinished()) {
                        ScreenView.this.Q.abortAnimation();
                    }
                    ScreenView.this.a(motionEvent, 3);
                    return true;
                case 1:
                case 3:
                    ScreenView.this.snapToScreen(floor);
                    ScreenView.this.a(ScreenView.this.mCurrentScreen, ScreenView.this.L);
                    return true;
                case 2:
                    ScreenView.this.setCurrentScreenInner(floor);
                    ScreenView.this.scrollTo((int) (((((float) (screenCount * ScreenView.this.mChildScreenWidth)) * max) / f) - ((float) (ScreenView.this.mChildScreenWidth / 2))), 0);
                    return true;
                default:
                    return true;
            }
        }
    }

    private class ScaleDetectorListener implements ScaleGestureDetector.OnScaleGestureListener {
        private static final float b = 200.0f;
        private static final float c = 0.95f;
        private static final float d = 0.8f;
        private static final float e = 1.2f;

        private ScaleDetectorListener() {
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return ScreenView.this.U == 0;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            ScreenView.this.finishCurrentGesture();
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            if (ScreenView.this.U == 0 && (((float) scaleGestureDetector.getTimeDelta()) > 200.0f || scaleFactor < 0.95f || scaleFactor > 1.0526316f)) {
                ScreenView.this.a((MotionEvent) null, 4);
            }
            if (scaleFactor < 0.8f) {
                ScreenView.this.onPinchIn(scaleGestureDetector);
                return true;
            } else if (scaleFactor <= e) {
                return false;
            } else {
                ScreenView.this.onPinchOut(scaleGestureDetector);
                return true;
            }
        }
    }
}
