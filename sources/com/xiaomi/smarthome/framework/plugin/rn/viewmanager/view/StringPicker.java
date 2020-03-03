package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class StringPicker extends LinearLayout {
    static final int SELECTOR_WHEEL_ITEM_COUNT = 5;
    public static final Formatter TWO_DIGIT_FORMATTER = new Formatter() {

        /* renamed from: a  reason: collision with root package name */
        final StringBuilder f17631a = new StringBuilder();
        final java.util.Formatter b = new java.util.Formatter(this.f17631a, Locale.US);
        final Object[] c = new Object[1];

        public String a(int i) {
            this.c[0] = Integer.valueOf(i);
            this.f17631a.delete(0, this.f17631a.length());
            this.b.format("%02d", this.c);
            return this.b.toString();
        }
    };
    public static final short TYPE_NUMBER_PICKER = 2;
    public static final short TYPE_STRING_PICKER = 1;

    /* renamed from: a  reason: collision with root package name */
    private static final long f17630a = 300;
    private static final int aC = 100001;
    /* access modifiers changed from: private */
    public static final char[] aG = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final int[] al = {16842919};
    private static final int an = 30;
    private static final int b = 2;
    private static final int c = 8;
    private static final int d = 800;
    private static final int e = 300;
    private static final float f = 0.9f;
    private static final int g = 2;
    private static final int h = 48;
    private static final int i = -1;
    /* access modifiers changed from: private */
    public long A;
    private final SparseArray<String> B;
    private final int[] C;
    private final Paint D;
    private final Drawable E;
    private int F;
    private int G;
    private int H;
    private final Scroller I;
    private final Scroller J;
    private int K;
    private SetSelectionCommand L;
    private ChangeCurrentByOneFromLongPressCommand M;
    private BeginSoftInputOnLongPressCommand N;
    private float O;
    private long P;
    private float Q;
    private VelocityTracker R;
    private int S;
    private int T;
    private int U;
    private final boolean V;
    /* access modifiers changed from: private */
    public final int W;
    private SoundPool aA;
    private int aB;
    /* access modifiers changed from: private */
    public int aD;
    /* access modifiers changed from: private */
    public Handler aE;
    private long aF;
    private int aa;
    /* access modifiers changed from: private */
    public boolean ab;
    private boolean ac;
    /* access modifiers changed from: private */
    public int ad;
    /* access modifiers changed from: private */
    public int ae;
    private int af;
    /* access modifiers changed from: private */
    public boolean ag;
    /* access modifiers changed from: private */
    public boolean ah;
    private AccessibilityNodeProviderImpl ai;
    private final PressedStateHelper aj;
    private int ak;
    private Paint am;
    private float ao;
    private int ap;
    private int aq;
    private int ar;
    private float as;
    private float at;
    private int au;
    private CharSequence av;
    private short aw;
    private final int[] ax;
    private final int ay;
    private final int az;
    /* access modifiers changed from: private */
    public final EditText j;
    private final int k;
    private final int l;
    private final int m;
    protected Paint mLinePaint;
    protected int mSelectItemBgColor;
    protected int mSelectTextColor;
    protected int mUnSelectTextColor;
    protected String mUnit;
    protected int mUnitFontSize;
    protected float mUnitPos;
    protected int mUnitTextColor;
    protected boolean mWrapSelectorWheel;
    private final int n;
    private int o;
    private final boolean p;
    private final int q;
    private int r;
    /* access modifiers changed from: private */
    public String[] s;
    /* access modifiers changed from: private */
    public int t;
    /* access modifiers changed from: private */
    public int u;
    /* access modifiers changed from: private */
    public int v;
    /* access modifiers changed from: private */
    public int w;
    /* access modifiers changed from: private */
    public OnValueChangeListener x;
    private OnScrollListener y;
    private Formatter z;

    public interface Formatter {
        String a(int i);
    }

    public interface OnScrollListener {

        /* renamed from: a  reason: collision with root package name */
        public static final int f17638a = 0;
        public static final int b = 1;
        public static final int c = 2;

        void a(StringPicker stringPicker, int i);
    }

    public interface OnValueChangeListener {
        void a(StringPicker stringPicker, int i, int i2);

        void b(StringPicker stringPicker, int i, int i2);
    }

    private float a(float f2, int i2, int i3) {
        return f2 >= 1.0f ? (float) i3 : (f2 * ((float) (i3 - i2))) + ((float) i2);
    }

    /* access modifiers changed from: protected */
    public float getBottomFadingEdgeStrength() {
        return f;
    }

    /* access modifiers changed from: protected */
    public float getTopFadingEdgeStrength() {
        return f;
    }

    public StringPicker(Context context) {
        this(context, (AttributeSet) null);
    }

    public StringPicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.numberPickerStyle);
    }

    public StringPicker(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet);
        this.A = f17630a;
        this.B = new SparseArray<>();
        this.C = new int[5];
        this.mSelectTextColor = Color.parseColor("#1dc58a");
        this.mUnSelectTextColor = 2130706432;
        this.mSelectItemBgColor = 0;
        this.G = Integer.MIN_VALUE;
        this.aa = 0;
        this.ak = -1;
        this.mLinePaint = null;
        this.ap = 25;
        this.aq = 14;
        this.ar = 10;
        this.at = 56.0f;
        this.au = 260;
        this.mUnit = null;
        this.mUnitPos = 0.0f;
        this.mUnitFontSize = 0;
        this.mUnitTextColor = Color.parseColor("#1dc58a");
        this.aw = 1;
        this.ax = new int[]{16842901, 16843087};
        this.ay = 0;
        this.az = 1;
        this.aD = 0;
        this.aE = new Handler() {

            /* renamed from: a  reason: collision with root package name */
            int f17632a = StringPicker.this.aD;

            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 100001) {
                    if (this.f17632a == StringPicker.this.aD) {
                        int unused = StringPicker.this.aD = 0;
                        this.f17632a = -1;
                        if (StringPicker.this.x != null) {
                            StringPicker.this.x.b(StringPicker.this, StringPicker.this.w, StringPicker.this.v);
                        }
                    } else {
                        StringPicker.this.aE.sendMessageDelayed(StringPicker.this.aE.obtainMessage(100001), StringPicker.f17630a);
                        this.f17632a = StringPicker.this.aD;
                    }
                    if (StringPicker.this.aD >= 100001) {
                        int unused2 = StringPicker.this.aD = 0;
                    }
                }
            }
        };
        this.aF = 0;
        float f2 = getResources().getDisplayMetrics().density;
        if (f2 != 1.0f) {
            this.aq = (int) (((float) this.aq) * f2);
            this.ap = (int) (((float) this.ap) * f2);
            this.ar = (int) (((float) this.ar) * f2);
            this.at *= f2;
            this.au = (int) (((float) this.au) * f2);
        }
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, this.ax, 0, 0);
        this.av = obtainStyledAttributes.getText(1);
        this.ap = obtainStyledAttributes.getDimensionPixelSize(0, this.ap);
        obtainStyledAttributes.recycle();
        this.aA = new SoundPool(1, 1, 0);
        try {
            this.aB = this.aA.load(getContext(), R.raw.numberpicker_value_change, 1);
        } catch (Exception unused) {
            this.aB = -1;
        }
        this.V = true;
        this.W = (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics());
        this.k = (int) this.at;
        this.l = -1;
        this.m = this.au;
        if (this.l == -1 || this.m == -1 || this.l <= this.m) {
            this.n = -1;
            this.o = -1;
            if (this.n == -1 || this.o == -1 || this.n <= this.o) {
                this.p = this.o == -1;
                this.E = getResources().getDrawable(R.drawable.rn_string_picker_virtual_button);
                this.aj = new PressedStateHelper();
                setWillNotDraw(!this.V);
                ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.numberpicker_layout, this, true);
                this.j = (EditText) findViewById(R.id.numberpicker_input);
                this.j.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    public void onFocusChange(View view, boolean z) {
                        if (z) {
                            StringPicker.this.j.selectAll();
                            return;
                        }
                        StringPicker.this.j.setSelection(0, 0);
                        StringPicker.this.a(view);
                    }
                });
                this.j.setFilters(new InputFilter[]{new InputTextFilter()});
                this.j.setRawInputType(2);
                this.j.setImeOptions(6);
                this.j.setVisibility(4);
                this.j.setGravity(3);
                if (Build.VERSION.SDK_INT >= 14) {
                    this.j.setScaleX(0.0f);
                }
                this.j.setSaveEnabled(false);
                this.j.setPadding(30, this.j.getPaddingTop(), 30, this.j.getPaddingRight());
                ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
                this.S = viewConfiguration.getScaledTouchSlop();
                this.T = viewConfiguration.getScaledMinimumFlingVelocity();
                this.U = viewConfiguration.getScaledMaximumFlingVelocity() / 8;
                this.q = (int) this.j.getTextSize();
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setTextSize((float) this.ap);
                paint.setTypeface(this.j.getTypeface());
                paint.setColor(this.j.getTextColors().getColorForState(ENABLED_STATE_SET, -1));
                this.D = paint;
                this.as = paint.ascent();
                this.am = new Paint();
                this.am.setAntiAlias(true);
                this.am.setFakeBoldText(true);
                this.am.setColor(getResources().getColor(R.color.class_text_17));
                this.am.setTextSize((float) this.ar);
                if (Build.VERSION.SDK_INT >= 14) {
                    this.I = new Scroller(getContext(), (Interpolator) null, true);
                } else {
                    this.I = new Scroller(getContext(), (Interpolator) null);
                }
                this.J = new Scroller(getContext(), new DecelerateInterpolator(2.5f));
                e();
                if (Build.VERSION.SDK_INT >= 16 && getImportantForAccessibility() == 0) {
                    setImportantForAccessibility(1);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("minWidth > maxWidth");
        }
        throw new IllegalArgumentException("minHeight > maxHeight");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        if (!this.V) {
            super.onLayout(z2, i2, i3, i4, i5);
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int measuredWidth2 = this.j.getMeasuredWidth();
        int measuredHeight2 = this.j.getMeasuredHeight();
        int i6 = (measuredWidth - measuredWidth2) / 2;
        int i7 = (measuredHeight - measuredHeight2) / 2;
        this.j.layout(i6, i7, measuredWidth2 + i6, measuredHeight2 + i7);
        if (z2) {
            c();
            d();
            this.ad = ((getHeight() - this.k) / 2) - this.W;
            this.ae = this.ad + (this.W * 2) + this.k;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            viewGroup.setClipChildren(true);
        }
        if (!this.V) {
            super.onMeasure(i2, i3);
            return;
        }
        super.onMeasure(a(i2, this.o), a(i3, this.m));
        setMeasuredDimension(a(this.n, getMeasuredWidth(), i2), a(this.l, getMeasuredHeight(), i3));
    }

    private boolean a(Scroller scroller) {
        scroller.forceFinished(true);
        int finalY = scroller.getFinalY() - scroller.getCurrY();
        int i2 = this.G - ((this.H + finalY) % this.F);
        if (i2 == 0) {
            return false;
        }
        if (Math.abs(i2) > this.F / 2) {
            if (i2 > 0) {
                i2 -= this.F;
            } else {
                i2 += this.F;
            }
        }
        scrollBy(0, finalY + i2);
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.V || !isEnabled()) {
            return false;
        }
        if (motionEvent.getActionMasked() != 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
            return super.onInterceptTouchEvent(motionEvent);
        }
        i();
        this.j.setVisibility(4);
        float y2 = motionEvent.getY();
        this.O = y2;
        this.Q = y2;
        this.P = motionEvent.getEventTime();
        this.ab = false;
        this.ac = false;
        if (this.O < ((float) this.ad)) {
            if (this.aa == 0) {
                this.aj.a(2);
            }
        } else if (this.O > ((float) this.ae) && this.aa == 0) {
            this.aj.a(1);
        }
        if (!this.I.isFinished()) {
            this.I.forceFinished(true);
            this.J.forceFinished(true);
            a(0);
        } else if (!this.J.isFinished()) {
            this.I.forceFinished(true);
            this.J.forceFinished(true);
        } else if (this.O < ((float) this.ad)) {
            a(false, (long) ViewConfiguration.getLongPressTimeout());
        } else if (this.O > ((float) this.ae)) {
            a(true, (long) ViewConfiguration.getLongPressTimeout());
        } else {
            this.ac = true;
            g();
        }
        getParent().requestDisallowInterceptTouchEvent(true);
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || !this.V) {
            return false;
        }
        if (this.R == null) {
            this.R = VelocityTracker.obtain();
        }
        this.R.addMovement(motionEvent);
        switch (motionEvent.getActionMasked()) {
            case 1:
                h();
                f();
                this.aj.a();
                VelocityTracker velocityTracker = this.R;
                velocityTracker.computeCurrentVelocity(1000, (float) this.U);
                int yVelocity = (int) velocityTracker.getYVelocity();
                if (Math.abs(yVelocity) > this.T) {
                    b(yVelocity);
                    a(2);
                    this.aE.sendMessageDelayed(this.aE.obtainMessage(100001), 400);
                } else {
                    this.aE.sendMessageDelayed(this.aE.obtainMessage(100001), 100);
                    int y2 = (int) motionEvent.getY();
                    int abs = (int) Math.abs(((float) y2) - this.O);
                    long eventTime = motionEvent.getEventTime() - this.P;
                    if (abs > this.S || eventTime >= ((long) ViewConfiguration.getTapTimeout())) {
                        j();
                    } else if (this.ac) {
                        this.ac = false;
                    } else {
                        int i2 = (y2 / this.F) - 2;
                        if (i2 > 0) {
                            a(true);
                            this.aj.b(1);
                        } else if (i2 < 0) {
                            a(false);
                            this.aj.b(2);
                        }
                    }
                    a(0);
                }
                this.R.recycle();
                this.R = null;
                break;
            case 2:
                if (!this.ab) {
                    float y3 = motionEvent.getY();
                    if (this.aa == 1) {
                        scrollBy(0, (int) (y3 - this.Q));
                        invalidate();
                    } else if (((int) Math.abs(y3 - this.O)) > this.S) {
                        i();
                        a(1);
                    }
                    this.Q = y3;
                    break;
                }
                break;
        }
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            i();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 23 && keyCode != 66) {
            switch (keyCode) {
                case 19:
                case 20:
                    if (this.V) {
                        switch (keyEvent.getAction()) {
                            case 0:
                                if (this.mWrapSelectorWheel || keyCode == 20 ? getValue() < getMaxValue() : getValue() > getMinValue()) {
                                    requestFocus();
                                    this.ak = keyCode;
                                    i();
                                    if (this.I.isFinished()) {
                                        a(keyCode == 20);
                                    }
                                    return true;
                                }
                            case 1:
                                if (this.ak == keyCode) {
                                    this.ak = -1;
                                    return true;
                                }
                                break;
                        }
                    }
                    break;
            }
        } else {
            i();
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            i();
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        int i2;
        if (!this.V) {
            return super.dispatchHoverEvent(motionEvent);
        }
        if (!((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            return false;
        }
        int y2 = (int) motionEvent.getY();
        if (y2 < this.ad) {
            i2 = 3;
        } else {
            i2 = y2 > this.ae ? 1 : 2;
        }
        int actionMasked = motionEvent.getActionMasked();
        AccessibilityNodeProviderImpl accessibilityNodeProviderImpl = (AccessibilityNodeProviderImpl) getAccessibilityNodeProvider();
        if (actionMasked != 7) {
            switch (actionMasked) {
                case 9:
                    accessibilityNodeProviderImpl.a(i2, 128);
                    this.af = i2;
                    accessibilityNodeProviderImpl.performAction(i2, 64, (Bundle) null);
                    return false;
                case 10:
                    accessibilityNodeProviderImpl.a(i2, 256);
                    this.af = -1;
                    return false;
                default:
                    return false;
            }
        } else if (this.af == i2 || this.af == -1) {
            return false;
        } else {
            accessibilityNodeProviderImpl.a(this.af, 256);
            accessibilityNodeProviderImpl.a(i2, 128);
            this.af = i2;
            accessibilityNodeProviderImpl.performAction(i2, 64, (Bundle) null);
            return false;
        }
    }

    public void computeScroll() {
        Scroller scroller = this.I;
        if (scroller.isFinished()) {
            scroller = this.J;
            if (scroller.isFinished()) {
                return;
            }
        }
        scroller.computeScrollOffset();
        int currY = scroller.getCurrY();
        if (this.K == 0) {
            this.K = scroller.getStartY();
        }
        scrollBy(0, currY - this.K);
        this.K = currY;
        if (scroller.isFinished()) {
            b(scroller);
        } else {
            invalidate();
        }
    }

    public void scrollBy(int i2, int i3) {
        int[] iArr = this.C;
        if (!this.mWrapSelectorWheel && i3 > 0 && iArr[2] <= this.t) {
            this.H = this.G;
        } else if (this.mWrapSelectorWheel || i3 >= 0 || iArr[2] < this.u) {
            this.H += i3;
            while (this.H - this.G > this.r) {
                this.H -= this.F;
                b(iArr);
                a(iArr[2], true);
                if (!this.mWrapSelectorWheel && iArr[2] <= this.t) {
                    this.H = this.G;
                }
            }
            while (this.H - this.G < (-this.r)) {
                this.H += this.F;
                a(iArr);
                a(iArr[2], true);
                if (!this.mWrapSelectorWheel && iArr[2] >= this.u) {
                    this.H = this.G;
                }
            }
        } else {
            this.H = this.G;
        }
    }

    public void setLabel(String str) {
        if ((this.av == null && str != null) || (this.av != null && !this.av.equals(str))) {
            this.av = str;
            invalidate();
        }
    }

    public void setOnValueChangedListener(OnValueChangeListener onValueChangeListener) {
        this.x = onValueChangeListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.y = onScrollListener;
    }

    public void setFormatter(Formatter formatter) {
        if (formatter != this.z) {
            this.z = formatter;
            b();
            e();
        }
    }

    public void setValue(int i2) {
        a(i2, false);
    }

    /* access modifiers changed from: protected */
    public float measureLabelSize(String str) {
        return this.D.measureText(str);
    }

    private void a() {
        if (this.p) {
            float f2 = -1.0f;
            this.D.setTextSize((float) this.ap);
            int i2 = 0;
            if (this.s == null) {
                float f3 = 0.0f;
                while (i2 < 9) {
                    float measureText = this.D.measureText(String.valueOf(i2));
                    if (measureText > f3) {
                        f3 = measureText;
                    }
                    i2++;
                }
                f2 = (float) ((int) (((float) e(this.u).length()) * f3));
            } else {
                int length = this.s.length;
                String[] strArr = this.s;
                int length2 = strArr.length;
                while (i2 < length2) {
                    float measureText2 = this.D.measureText(strArr[i2]);
                    if (measureText2 > f2) {
                        f2 = measureText2;
                    }
                    i2++;
                }
            }
            this.ao = f2;
            float paddingLeft = f2 + ((float) (this.j.getPaddingLeft() + this.j.getPaddingRight())) + ((float) getPaddingLeft()) + ((float) getPaddingRight());
            if (((float) this.o) == paddingLeft) {
                return;
            }
            if (paddingLeft > ((float) this.n)) {
                this.o = (int) paddingLeft;
            } else {
                this.o = this.n;
            }
        }
    }

    public boolean getWrapSelectorWheel() {
        return this.mWrapSelectorWheel;
    }

    public void setWrapSelectorWheel(boolean z2) {
        boolean z3 = this.u - this.t >= this.C.length;
        if ((!z2 || z3) && z2 != this.mWrapSelectorWheel) {
            this.mWrapSelectorWheel = z2;
        }
        k();
    }

    public void setOnLongPressUpdateInterval(long j2) {
        this.A = j2;
    }

    public int getValue() {
        return this.v;
    }

    public int getMinValue() {
        return this.t;
    }

    public void setMinValue(int i2) {
        if (this.t != i2) {
            if (i2 >= 0) {
                this.t = i2;
                if (this.t > this.v) {
                    this.v = this.t;
                }
                setWrapSelectorWheel(this.u - this.t > this.C.length);
                b();
                e();
                a();
                invalidate();
                return;
            }
            throw new IllegalArgumentException("minValue must be >= 0");
        }
    }

    public void updateValues(int i2, int i3, int i4) {
        this.t = i2;
        this.u = i3;
        this.v = i4;
    }

    public int getMaxValue() {
        return this.u;
    }

    public void setMaxValue(int i2) {
        if (this.u != i2) {
            if (i2 >= 0) {
                this.u = i2;
                if (this.u < this.v) {
                    this.v = this.u;
                }
                setWrapSelectorWheel(this.u - this.t > this.C.length);
                b();
                e();
                a();
                invalidate();
                return;
            }
            throw new IllegalArgumentException("maxValue must be >= 0");
        }
    }

    public String[] getDisplayedValues() {
        return this.s;
    }

    public void setDisplayedValues(String[] strArr) {
        if (this.s != strArr) {
            this.s = strArr;
            if (this.s != null) {
                this.j.setRawInputType(524289);
            } else {
                this.j.setRawInputType(2);
            }
            cleanSlectorIndices();
            e();
            b();
            a();
        }
    }

    public void cleanSlectorIndices() {
        for (int i2 = 0; i2 < this.C.length; i2++) {
            this.C[i2] = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        i();
        this.aA.release();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f2;
        Canvas canvas2 = canvas;
        if (!this.V) {
            super.onDraw(canvas);
            return;
        }
        int width = getWidth();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        float right = (float) ((((getRight() - getLeft()) + paddingLeft) - paddingRight) / 2);
        float f3 = (float) this.H;
        boolean z2 = false;
        if (this.E != null && this.aa == 0) {
            if (this.ah) {
                this.E.setState(al);
                this.E.setBounds(paddingLeft, 0, width - paddingRight, this.ad);
                this.E.draw(canvas2);
            }
            if (this.ag) {
                this.E.setState(al);
                this.E.setBounds(paddingLeft, this.ae, width - paddingRight, getBottom());
                this.E.draw(canvas2);
            }
        }
        float f4 = (float) (this.G + (this.F * 2));
        SparseArray<String> sparseArray = this.B;
        int[] iArr = this.C;
        float height = ((float) (getHeight() / 2)) - (((float) this.F) * 0.44f);
        float f5 = height + ((float) this.F);
        this.D.setColor(this.mSelectItemBgColor);
        canvas.drawRect(0.0f, height, (float) getWidth(), f5, this.D);
        Paint paint = this.mLinePaint;
        if (paint != null) {
            Canvas canvas3 = canvas;
            float f6 = height;
            float f7 = (float) width;
            float f8 = height;
            Paint paint2 = paint;
            canvas3.drawLine(0.0f, f6, f7, f8, paint);
            canvas3.drawLine(0.0f, f5, f7, f5, paint);
        }
        int length = iArr.length;
        int i2 = 0;
        while (i2 < length) {
            String str = sparseArray.get(iArr[i2]);
            float abs = Math.abs(f4 - f3) / ((float) this.F);
            float a2 = a(abs, this.ap, this.aq);
            this.D.setTextSize(a2);
            this.D.setColor(a(abs, this.mUnSelectTextColor, z2));
            canvas2.drawText(str, right, ((a2 - ((float) this.aq)) / 2.0f) + f3, this.D);
            if (abs < 1.0f) {
                float a3 = a(abs, this.ap, this.aq);
                this.D.setTextSize(a3);
                this.D.setColor(a(abs, this.mSelectTextColor, true));
                canvas2.drawText(str, right, ((a3 - ((float) this.aq)) / 2.0f) + f3, this.D);
                if (this.mUnitPos < a3) {
                    this.mUnitPos = a3;
                }
            }
            f3 += (float) this.F;
            i2++;
            z2 = false;
        }
        if (!TextUtils.isEmpty(this.av)) {
            canvas2.drawText(this.av.toString(), (this.ao / 2.0f) + right, ((float) ((this.ap - this.aq) / 2)) + f4 + this.as + ((float) this.ar), this.am);
        }
        if (!TextUtils.isEmpty(this.mUnit)) {
            if (this.aw == 1) {
                f2 = (f4 - ((float) (this.F / 4))) + 2.0f;
            } else {
                f2 = (f4 - ((float) (this.F / 4))) + 2.0f;
            }
            if (this.mUnitPos < this.ao) {
                this.mUnitPos = this.ao;
            }
            if (this.mUnitFontSize > 0) {
                this.am.setTextSize((float) this.mUnitFontSize);
            }
            this.am.setColor(this.mUnitTextColor);
            canvas2.drawText(this.mUnit, right + (this.mUnitPos / 2.0f), f2, this.am);
        }
    }

    private int a(float f2, int i2, boolean z2) {
        int i3;
        if (f2 >= 1.0f) {
            return i2;
        }
        if (z2) {
            i3 = (int) (((-f2) * ((float) Color.alpha(i2))) + ((float) Color.alpha(i2)));
        } else {
            i3 = (int) (f2 * ((float) Color.alpha(i2)));
        }
        return (i3 << 24) | (i2 & 16777215);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(StringPicker.class.getName());
        accessibilityEvent.setScrollable(true);
        accessibilityEvent.setScrollY((this.t + this.v) * this.F);
        accessibilityEvent.setMaxScrollY((this.u - this.t) * this.F);
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        if (!this.V) {
            return super.getAccessibilityNodeProvider();
        }
        if (this.ai == null) {
            this.ai = new AccessibilityNodeProviderImpl();
        }
        return this.ai;
    }

    private int a(int i2, int i3) {
        if (i3 == -1) {
            return i2;
        }
        int size = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), 1073741824);
        }
        if (mode == 0) {
            return View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
        }
        if (mode == 1073741824) {
            return i2;
        }
        throw new IllegalArgumentException("Unknown measure mode: " + mode);
    }

    private int a(int i2, int i3, int i4) {
        return i2 != -1 ? resolveSizeAndState(Math.max(i2, i3), i4, 0) : i3;
    }

    private void b() {
        this.B.clear();
        int[] iArr = this.C;
        int value = getValue();
        for (int i2 = 0; i2 < this.C.length; i2++) {
            int i3 = (i2 - 2) + value;
            if (this.mWrapSelectorWheel) {
                i3 = c(i3);
            }
            iArr[i2] = i3;
            d(iArr[i2]);
        }
    }

    private void a(int i2, boolean z2) {
        int i3;
        if (this.v != i2) {
            if (this.mWrapSelectorWheel) {
                i3 = c(i2);
            } else {
                i3 = Math.min(Math.max(i2, this.t), this.u);
            }
            int i4 = this.v;
            this.v = i3;
            e();
            if (z2) {
                b(i4, i3);
            }
            b();
            invalidate();
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z2) {
        if (this.V) {
            this.j.setVisibility(4);
            if (!a(this.I)) {
                a(this.J);
            }
            this.K = 0;
            if (z2) {
                this.I.startScroll(0, 0, 0, -this.F, 300);
            } else {
                this.I.startScroll(0, 0, 0, this.F, 300);
            }
            invalidate();
        } else if (z2) {
            a(this.v + 1, true);
        } else {
            a(this.v - 1, true);
        }
    }

    private void c() {
        b();
        int[] iArr = this.C;
        this.r = (int) ((((float) ((getBottom() - getTop()) - (iArr.length * this.q))) / ((float) iArr.length)) + 0.5f);
        this.F = this.q + this.r;
        this.G = (this.j.getBaseline() + this.j.getTop()) - (this.F * 2);
        this.H = this.G;
        e();
    }

    private void d() {
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(((getBottom() - getTop()) - this.q) / 2);
    }

    private void b(Scroller scroller) {
        if (scroller == this.I) {
            if (!j()) {
                e();
            }
            a(0);
        } else if (this.aa != 1) {
            e();
        }
    }

    private void a(int i2) {
        if (this.aa != i2) {
            this.aa = i2;
            if (this.y != null) {
                this.y.a(this, i2);
            }
        }
    }

    private void b(int i2) {
        this.K = 0;
        if (i2 > 0) {
            this.I.fling(0, 0, 0, i2, 0, 0, 0, Integer.MAX_VALUE);
        } else {
            this.I.fling(0, Integer.MAX_VALUE, 0, i2, 0, 0, 0, Integer.MAX_VALUE);
        }
        invalidate();
    }

    /* access modifiers changed from: private */
    public int c(int i2) {
        if (i2 > this.u) {
            return (this.t + ((i2 - this.u) % (this.u - this.t))) - 1;
        }
        return i2 < this.t ? (this.u - ((this.t - i2) % (this.u - this.t))) + 1 : i2;
    }

    private void a(int[] iArr) {
        System.arraycopy(iArr, 1, iArr, 0, iArr.length - 1);
        int i2 = iArr[iArr.length - 2] + 1;
        if (this.mWrapSelectorWheel && i2 > this.u) {
            i2 = this.t;
        }
        iArr[iArr.length - 1] = i2;
        d(i2);
    }

    private void b(int[] iArr) {
        System.arraycopy(iArr, 0, iArr, 1, iArr.length - 1);
        int i2 = iArr[1] - 1;
        if (this.mWrapSelectorWheel && i2 < this.t) {
            i2 = this.u;
        }
        iArr[0] = i2;
        d(i2);
    }

    private void d(int i2) {
        String str;
        SparseArray<String> sparseArray = this.B;
        if (sparseArray.get(i2) == null) {
            if (i2 < this.t || i2 > this.u) {
                str = "";
            } else if (this.s != null) {
                str = this.s[i2 - this.t];
            } else {
                str = e(i2);
            }
            sparseArray.put(i2, str);
        }
    }

    /* access modifiers changed from: private */
    public String e(int i2) {
        return this.z != null ? this.z.a(i2) : String.valueOf(i2);
    }

    /* access modifiers changed from: private */
    public void a(View view) {
        String valueOf = String.valueOf(((TextView) view).getText());
        if (TextUtils.isEmpty(valueOf)) {
            e();
        } else {
            a(a(valueOf.toString()), true);
        }
    }

    private boolean e() {
        String e2 = this.s == null ? e(this.v) : this.s[this.v - this.t];
        if (TextUtils.isEmpty(e2) || e2.equals(this.j.getText().toString())) {
            return false;
        }
        this.j.setText(e2);
        return true;
    }

    private void b(int i2, int i3) {
        long currentTimeMillis = System.currentTimeMillis();
        this.aD++;
        this.w = i2;
        if (this.aB != -1 && currentTimeMillis - this.aF >= 100) {
            this.aA.play(this.aB, 1.0f, 1.0f, 0, 0, 1.0f);
            this.aF = currentTimeMillis;
        }
        if (this.x != null) {
            this.x.a(this, i2, this.v);
        }
    }

    private void a(boolean z2, long j2) {
        if (this.M == null) {
            this.M = new ChangeCurrentByOneFromLongPressCommand();
        } else {
            removeCallbacks(this.M);
        }
        this.M.a(z2);
        postDelayed(this.M, j2);
    }

    private void f() {
        if (this.M != null) {
            removeCallbacks(this.M);
        }
    }

    private void g() {
        if (this.N == null) {
            this.N = new BeginSoftInputOnLongPressCommand();
        } else {
            removeCallbacks(this.N);
        }
        postDelayed(this.N, (long) ViewConfiguration.getLongPressTimeout());
    }

    private void h() {
        if (this.N != null) {
            removeCallbacks(this.N);
        }
    }

    private void i() {
        if (this.M != null) {
            removeCallbacks(this.M);
        }
        if (this.L != null) {
            removeCallbacks(this.L);
        }
        if (this.N != null) {
            removeCallbacks(this.N);
        }
        this.aj.a();
    }

    /* access modifiers changed from: private */
    public int a(String str) {
        if (this.s == null) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                return this.t;
            }
        } else {
            for (int i2 = 0; i2 < this.s.length; i2++) {
                str = str.toLowerCase();
                if (this.s[i2].toLowerCase().startsWith(str)) {
                    return this.t + i2;
                }
            }
            return Integer.parseInt(str);
        }
    }

    /* access modifiers changed from: private */
    public void c(int i2, int i3) {
        if (this.L == null) {
            this.L = new SetSelectionCommand();
        } else {
            removeCallbacks(this.L);
        }
        int unused = this.L.b = i2;
        int unused2 = this.L.c = i3;
        post(this.L);
    }

    class InputTextFilter extends NumberKeyListener {
        public int getInputType() {
            return 1;
        }

        InputTextFilter() {
        }

        /* access modifiers changed from: protected */
        public char[] getAcceptedChars() {
            return StringPicker.aG;
        }

        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            if (StringPicker.this.s == null) {
                CharSequence filter = super.filter(charSequence, i, i2, spanned, i3, i4);
                if (filter == null) {
                    filter = charSequence.subSequence(i, i2);
                }
                String str = String.valueOf(spanned.subSequence(0, i3)) + filter + spanned.subSequence(i4, spanned.length());
                if ("".equals(str)) {
                    return str;
                }
                return (StringPicker.this.a(str) > StringPicker.this.u || str.length() > String.valueOf(StringPicker.this.u).length()) ? "" : filter;
            }
            String valueOf = String.valueOf(charSequence.subSequence(i, i2));
            if (TextUtils.isEmpty(valueOf)) {
                return "";
            }
            String str2 = String.valueOf(spanned.subSequence(0, i3)) + valueOf + spanned.subSequence(i4, spanned.length());
            String lowerCase = String.valueOf(str2).toLowerCase();
            for (String str3 : StringPicker.this.s) {
                if (str3.toLowerCase().startsWith(lowerCase)) {
                    StringPicker.this.c(str2.length(), str3.length());
                    return str3.subSequence(i3, str3.length());
                }
            }
            return "";
        }
    }

    private boolean j() {
        int i2 = this.G - this.H;
        if (i2 == 0) {
            return false;
        }
        this.K = 0;
        if (Math.abs(i2) > this.F / 2) {
            i2 += i2 > 0 ? -this.F : this.F;
        }
        this.J.startScroll(0, 0, 0, i2, 800);
        invalidate();
        return true;
    }

    class PressedStateHelper implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public static final int f17639a = 1;
        public static final int b = 2;
        private final int d = 1;
        private final int e = 2;
        private int f;
        private int g;

        PressedStateHelper() {
        }

        public void a() {
            this.g = 0;
            this.f = 0;
            StringPicker.this.removeCallbacks(this);
            if (StringPicker.this.ag) {
                boolean unused = StringPicker.this.ag = false;
                StringPicker.this.invalidate(0, StringPicker.this.ae, StringPicker.this.getRight(), StringPicker.this.getBottom());
            }
            if (StringPicker.this.ah) {
                boolean unused2 = StringPicker.this.ah = false;
                StringPicker.this.invalidate(0, 0, StringPicker.this.getRight(), StringPicker.this.ad);
            }
        }

        public void a(int i) {
            a();
            this.g = 1;
            this.f = i;
            StringPicker.this.postDelayed(this, (long) ViewConfiguration.getTapTimeout());
        }

        public void b(int i) {
            a();
            this.g = 2;
            this.f = i;
            StringPicker.this.post(this);
        }

        public void run() {
            switch (this.g) {
                case 1:
                    switch (this.f) {
                        case 1:
                            boolean unused = StringPicker.this.ag = true;
                            StringPicker.this.invalidate(0, StringPicker.this.ae, StringPicker.this.getRight(), StringPicker.this.getBottom());
                            return;
                        case 2:
                            boolean unused2 = StringPicker.this.ah = true;
                            StringPicker.this.invalidate(0, 0, StringPicker.this.getRight(), StringPicker.this.ad);
                            return;
                        default:
                            return;
                    }
                case 2:
                    switch (this.f) {
                        case 1:
                            if (!StringPicker.this.ag) {
                                StringPicker.this.postDelayed(this, (long) ViewConfiguration.getPressedStateDuration());
                            }
                            boolean unused3 = StringPicker.this.ag = true ^ StringPicker.this.ag;
                            StringPicker.this.invalidate(0, StringPicker.this.ae, StringPicker.this.getRight(), StringPicker.this.getBottom());
                            return;
                        case 2:
                            if (!StringPicker.this.ah) {
                                StringPicker.this.postDelayed(this, (long) ViewConfiguration.getPressedStateDuration());
                            }
                            boolean unused4 = StringPicker.this.ah = true ^ StringPicker.this.ah;
                            StringPicker.this.invalidate(0, 0, StringPicker.this.getRight(), StringPicker.this.ad);
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    }

    class SetSelectionCommand implements Runnable {
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public int c;

        SetSelectionCommand() {
        }

        public void run() {
            StringPicker.this.j.setSelection(this.b, this.c);
        }
    }

    class ChangeCurrentByOneFromLongPressCommand implements Runnable {
        private boolean b;

        ChangeCurrentByOneFromLongPressCommand() {
        }

        /* access modifiers changed from: private */
        public void a(boolean z) {
            this.b = z;
        }

        public void run() {
            StringPicker.this.a(this.b);
            StringPicker.this.postDelayed(this, StringPicker.this.A);
        }
    }

    public static class CustomEditText extends AppCompatEditText {
        public CustomEditText(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void onEditorAction(int i) {
            super.onEditorAction(i);
            if (i == 6) {
                clearFocus();
            }
        }
    }

    class BeginSoftInputOnLongPressCommand implements Runnable {
        BeginSoftInputOnLongPressCommand() {
        }

        public void run() {
            boolean unused = StringPicker.this.ab = true;
        }
    }

    class AccessibilityNodeProviderImpl extends AccessibilityNodeProvider {
        private static final int b = Integer.MIN_VALUE;
        private static final int c = 1;
        private static final int d = 2;
        private static final int e = 3;
        private final Rect f = new Rect();
        private final int[] g = new int[2];
        private int h = Integer.MIN_VALUE;

        AccessibilityNodeProviderImpl() {
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            if (i == -1) {
                return b(StringPicker.this.getScrollX(), StringPicker.this.getScrollY(), StringPicker.this.getScrollX() + (StringPicker.this.getRight() - StringPicker.this.getLeft()), StringPicker.this.getScrollY() + (StringPicker.this.getBottom() - StringPicker.this.getTop()));
            }
            switch (i) {
                case 1:
                    return a(1, d(), StringPicker.this.getScrollX(), StringPicker.this.ae - StringPicker.this.W, StringPicker.this.getScrollX() + (StringPicker.this.getRight() - StringPicker.this.getLeft()), StringPicker.this.getScrollY() + (StringPicker.this.getBottom() - StringPicker.this.getTop()));
                case 2:
                    return a(StringPicker.this.getScrollX(), StringPicker.this.ad + StringPicker.this.W, StringPicker.this.getScrollX() + (StringPicker.this.getRight() - StringPicker.this.getLeft()), StringPicker.this.ae - StringPicker.this.W);
                case 3:
                    return a(3, c(), StringPicker.this.getScrollX(), StringPicker.this.getScrollY(), StringPicker.this.getScrollX() + (StringPicker.this.getRight() - StringPicker.this.getLeft()), StringPicker.this.ad + StringPicker.this.W);
                default:
                    return super.createAccessibilityNodeInfo(i);
            }
        }

        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            if (TextUtils.isEmpty(str)) {
                return Collections.emptyList();
            }
            String lowerCase = str.toLowerCase();
            ArrayList arrayList = new ArrayList();
            if (i != -1) {
                switch (i) {
                    case 1:
                    case 2:
                    case 3:
                        a(lowerCase, i, (List<AccessibilityNodeInfo>) arrayList);
                        return arrayList;
                    default:
                        return super.findAccessibilityNodeInfosByText(str, i);
                }
            } else {
                a(lowerCase, 3, (List<AccessibilityNodeInfo>) arrayList);
                a(lowerCase, 2, (List<AccessibilityNodeInfo>) arrayList);
                a(lowerCase, 1, (List<AccessibilityNodeInfo>) arrayList);
                return arrayList;
            }
        }

        public boolean performAction(int i, int i2, Bundle bundle) {
            boolean z = false;
            if (i != -1) {
                switch (i) {
                    case 1:
                        if (i2 != 16) {
                            if (i2 != 64) {
                                if (i2 != 128 || this.h != i) {
                                    return false;
                                }
                                this.h = Integer.MIN_VALUE;
                                a(i, 65536);
                                StringPicker.this.invalidate(0, StringPicker.this.ae, StringPicker.this.getRight(), StringPicker.this.getBottom());
                                return true;
                            } else if (this.h == i) {
                                return false;
                            } else {
                                this.h = i;
                                a(i, 32768);
                                StringPicker.this.invalidate(0, StringPicker.this.ae, StringPicker.this.getRight(), StringPicker.this.getBottom());
                                return true;
                            }
                        } else if (!StringPicker.this.isEnabled()) {
                            return false;
                        } else {
                            StringPicker.this.a(true);
                            a(i, 1);
                            return true;
                        }
                    case 2:
                        if (i2 == 16) {
                            return StringPicker.this.isEnabled();
                        }
                        if (i2 != 64) {
                            if (i2 != 128) {
                                switch (i2) {
                                    case 1:
                                        if (!StringPicker.this.isEnabled() || StringPicker.this.j.isFocused()) {
                                            return false;
                                        }
                                        return StringPicker.this.j.requestFocus();
                                    case 2:
                                        if (!StringPicker.this.isEnabled() || !StringPicker.this.j.isFocused()) {
                                            return false;
                                        }
                                        StringPicker.this.j.clearFocus();
                                        return true;
                                    default:
                                        return StringPicker.this.j.performAccessibilityAction(i2, bundle);
                                }
                            } else if (this.h != i) {
                                return false;
                            } else {
                                this.h = Integer.MIN_VALUE;
                                a(i, 65536);
                                StringPicker.this.j.invalidate();
                                return true;
                            }
                        } else if (this.h == i) {
                            return false;
                        } else {
                            this.h = i;
                            a(i, 32768);
                            StringPicker.this.j.invalidate();
                            return true;
                        }
                    case 3:
                        if (i2 != 16) {
                            if (i2 != 64) {
                                if (i2 != 128 || this.h != i) {
                                    return false;
                                }
                                this.h = Integer.MIN_VALUE;
                                a(i, 65536);
                                StringPicker.this.invalidate(0, 0, StringPicker.this.getRight(), StringPicker.this.ad);
                                return true;
                            } else if (this.h == i) {
                                return false;
                            } else {
                                this.h = i;
                                a(i, 32768);
                                StringPicker.this.invalidate(0, 0, StringPicker.this.getRight(), StringPicker.this.ad);
                                return true;
                            }
                        } else if (!StringPicker.this.isEnabled()) {
                            return false;
                        } else {
                            if (i == 1) {
                                z = true;
                            }
                            StringPicker.this.a(z);
                            a(i, 1);
                            return true;
                        }
                }
            } else if (i2 != 64) {
                if (i2 != 128) {
                    if (i2 != 4096) {
                        if (i2 == 8192) {
                            if (!StringPicker.this.isEnabled() || (!StringPicker.this.getWrapSelectorWheel() && StringPicker.this.getValue() <= StringPicker.this.getMinValue())) {
                                return false;
                            }
                            StringPicker.this.a(false);
                            return true;
                        }
                    } else if (!StringPicker.this.isEnabled() || (!StringPicker.this.getWrapSelectorWheel() && StringPicker.this.getValue() >= StringPicker.this.getMaxValue())) {
                        return false;
                    } else {
                        StringPicker.this.a(true);
                        return true;
                    }
                } else if (this.h != i) {
                    return false;
                } else {
                    this.h = Integer.MIN_VALUE;
                    return true;
                }
            } else if (this.h == i) {
                return false;
            } else {
                this.h = i;
                return true;
            }
            return super.performAction(i, i2, bundle);
        }

        public void a(int i, int i2) {
            switch (i) {
                case 1:
                    if (b()) {
                        a(i, i2, d());
                        return;
                    }
                    return;
                case 2:
                    a(i2);
                    return;
                case 3:
                    if (a()) {
                        a(i, i2, c());
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        private void a(int i) {
            if (((AccessibilityManager) StringPicker.this.getContext().getSystemService("accessibility")).isEnabled()) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(i);
                StringPicker.this.j.onInitializeAccessibilityEvent(obtain);
                StringPicker.this.j.onPopulateAccessibilityEvent(obtain);
                obtain.setSource(StringPicker.this, 2);
                StringPicker.this.requestSendAccessibilityEvent(StringPicker.this, obtain);
            }
        }

        private void a(int i, int i2, String str) {
            if (((AccessibilityManager) StringPicker.this.getContext().getSystemService("accessibility")).isEnabled()) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
                obtain.setClassName(Button.class.getName());
                obtain.setPackageName(StringPicker.this.getContext().getPackageName());
                obtain.getText().add(str);
                obtain.setEnabled(StringPicker.this.isEnabled());
                obtain.setSource(StringPicker.this, i);
                StringPicker.this.requestSendAccessibilityEvent(StringPicker.this, obtain);
            }
        }

        private void a(String str, int i, List<AccessibilityNodeInfo> list) {
            switch (i) {
                case 1:
                    String d2 = d();
                    if (!TextUtils.isEmpty(d2) && d2.toString().toLowerCase().contains(str)) {
                        list.add(createAccessibilityNodeInfo(1));
                        return;
                    }
                    return;
                case 2:
                    Editable text = StringPicker.this.j.getText();
                    if (TextUtils.isEmpty(text) || !text.toString().toLowerCase().contains(str)) {
                        Editable text2 = StringPicker.this.j.getText();
                        if (!TextUtils.isEmpty(text2) && text2.toString().toLowerCase().contains(str)) {
                            list.add(createAccessibilityNodeInfo(2));
                            return;
                        }
                        return;
                    }
                    list.add(createAccessibilityNodeInfo(2));
                    return;
                case 3:
                    String c2 = c();
                    if (!TextUtils.isEmpty(c2) && c2.toString().toLowerCase().contains(str)) {
                        list.add(createAccessibilityNodeInfo(3));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        private AccessibilityNodeInfo a(int i, int i2, int i3, int i4) {
            AccessibilityNodeInfo createAccessibilityNodeInfo = StringPicker.this.j.createAccessibilityNodeInfo();
            createAccessibilityNodeInfo.setSource(StringPicker.this, 2);
            if (this.h != 2) {
                createAccessibilityNodeInfo.addAction(64);
            }
            if (this.h == 2) {
                createAccessibilityNodeInfo.addAction(128);
            }
            Rect rect = this.f;
            rect.set(i, i2, i3, i4);
            createAccessibilityNodeInfo.setVisibleToUser(StringPicker.this.getVisibility() == 0);
            createAccessibilityNodeInfo.setBoundsInParent(rect);
            int[] iArr = this.g;
            StringPicker.this.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            createAccessibilityNodeInfo.setBoundsInScreen(rect);
            return createAccessibilityNodeInfo;
        }

        private AccessibilityNodeInfo a(int i, String str, int i2, int i3, int i4, int i5) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
            obtain.setClassName(Button.class.getName());
            obtain.setPackageName(StringPicker.this.getContext().getPackageName());
            obtain.setSource(StringPicker.this, i);
            obtain.setParent(StringPicker.this);
            obtain.setText(str);
            obtain.setClickable(true);
            obtain.setLongClickable(true);
            obtain.setEnabled(StringPicker.this.isEnabled());
            Rect rect = this.f;
            rect.set(i2, i3, i4, i5);
            obtain.setVisibleToUser(StringPicker.this.getVisibility() == 0);
            obtain.setBoundsInParent(rect);
            int[] iArr = this.g;
            StringPicker.this.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            obtain.setBoundsInScreen(rect);
            if (this.h != i) {
                obtain.addAction(64);
            }
            if (this.h == i) {
                obtain.addAction(128);
            }
            if (StringPicker.this.isEnabled()) {
                obtain.addAction(16);
            }
            return obtain;
        }

        private AccessibilityNodeInfo b(int i, int i2, int i3, int i4) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
            obtain.setClassName(StringPicker.class.getName());
            obtain.setPackageName(StringPicker.this.getContext().getPackageName());
            obtain.setSource(StringPicker.this);
            if (a()) {
                obtain.addChild(StringPicker.this, 3);
            }
            obtain.addChild(StringPicker.this, 2);
            if (b()) {
                obtain.addChild(StringPicker.this, 1);
            }
            obtain.setParent((View) StringPicker.this.getParentForAccessibility());
            obtain.setEnabled(StringPicker.this.isEnabled());
            obtain.setScrollable(true);
            Rect rect = this.f;
            rect.set(i, i2, i3, i4);
            obtain.setBoundsInParent(rect);
            obtain.setVisibleToUser(StringPicker.this.getVisibility() == 0);
            int[] iArr = this.g;
            StringPicker.this.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            obtain.setBoundsInScreen(rect);
            if (this.h != -1) {
                obtain.addAction(64);
            }
            if (this.h == -1) {
                obtain.addAction(128);
            }
            if (StringPicker.this.isEnabled()) {
                if (StringPicker.this.getWrapSelectorWheel() || StringPicker.this.getValue() < StringPicker.this.getMaxValue()) {
                    obtain.addAction(4096);
                }
                if (StringPicker.this.getWrapSelectorWheel() || StringPicker.this.getValue() > StringPicker.this.getMinValue()) {
                    obtain.addAction(8192);
                }
            }
            return obtain;
        }

        private boolean a() {
            return StringPicker.this.getWrapSelectorWheel() || StringPicker.this.getValue() > StringPicker.this.getMinValue();
        }

        private boolean b() {
            return StringPicker.this.getWrapSelectorWheel() || StringPicker.this.getValue() < StringPicker.this.getMaxValue();
        }

        private String c() {
            int access$300 = StringPicker.this.v - 1;
            if (StringPicker.this.mWrapSelectorWheel) {
                access$300 = StringPicker.this.c(access$300);
            }
            if (access$300 < StringPicker.this.t) {
                return null;
            }
            if (StringPicker.this.s == null) {
                return StringPicker.this.e(access$300);
            }
            return StringPicker.this.s[access$300 - StringPicker.this.t];
        }

        private String d() {
            int access$300 = StringPicker.this.v + 1;
            if (StringPicker.this.mWrapSelectorWheel) {
                access$300 = StringPicker.this.c(access$300);
            }
            if (access$300 > StringPicker.this.u) {
                return null;
            }
            if (StringPicker.this.s == null) {
                return StringPicker.this.e(access$300);
            }
            return StringPicker.this.s[access$300 - StringPicker.this.t];
        }
    }

    public void setSelectTextColor(int i2) {
        this.mSelectTextColor = i2;
    }

    public void setUnSelectTextColor(int i2) {
        this.mUnSelectTextColor = i2;
    }

    public void setSelectItemBgColor(int i2) {
        this.mSelectItemBgColor = i2;
    }

    public void setSelectTextSize(int i2) {
        if (i2 > 0) {
            this.ap = i2;
        }
    }

    public void setUnSelectTextSize(int i2) {
        if (i2 > 0) {
            this.aq = i2;
        }
    }

    public void setUnit(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mUnit = null;
        } else {
            this.mUnit = str;
        }
    }

    public void setUnitFontSize(int i2) {
        if (i2 < 1) {
            this.mUnitFontSize = this.ar;
        } else {
            this.mUnitFontSize = i2;
        }
    }

    public void setUnitTextColor(int i2) {
        this.mUnitTextColor = i2;
    }

    public void setPickerType(short s2) {
        this.aw = s2;
    }

    private void k() {
        b();
        invalidate();
    }
}
