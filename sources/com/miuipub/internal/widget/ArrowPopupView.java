package com.miuipub.internal.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import miuipub.v6.R;
import miuipub.view.EditActionMode;
import miuipub.widget.ArrowPopupWindow;

public class ArrowPopupView extends FrameLayout implements View.OnTouchListener {
    public static final int ARROW_BOTTOM_MODE = 0;
    public static final int ARROW_LEFT_MODE = 3;
    public static final int ARROW_NONE_MODE = -1;
    public static final int ARROW_RIGHT_MODE = 2;
    public static final int ARROW_TOP_MODE = 1;
    private static final int J = 4;

    /* renamed from: a  reason: collision with root package name */
    private static final String f8340a = "ArrowPopupView";
    /* access modifiers changed from: private */
    public AnimatorSet A;
    /* access modifiers changed from: private */
    public boolean B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    /* access modifiers changed from: private */
    public boolean H;
    /* access modifiers changed from: private */
    public int I;
    /* access modifiers changed from: private */
    public Animator.AnimatorListener K;
    private Animator.AnimatorListener L;
    private int M;
    private float N;
    private View b;
    /* access modifiers changed from: private */
    public ImageView c;
    private FrameLayout d;
    /* access modifiers changed from: private */
    public LinearLayout e;
    private LinearLayout f;
    private TextView g;
    private Button h;
    private Button i;
    private WrapperOnClickListener j;
    private WrapperOnClickListener k;
    private int l;
    private int m;
    private Drawable n;
    private Drawable o;
    private Drawable p;
    private Drawable q;
    private Drawable r;
    private Drawable s;
    private Drawable t;
    private Drawable u;
    private Drawable v;
    /* access modifiers changed from: private */
    public ArrowPopupWindow w;
    private View.OnTouchListener x;
    private Rect y;
    private RectF z;

    public ArrowPopupView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ArrowPopupView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.v6_arrowPopupViewStyle);
    }

    public ArrowPopupView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.y = new Rect();
        this.z = new RectF();
        this.H = false;
        this.K = new AnimatorListenerAdapter() {
            private boolean b;

            public void onAnimationStart(Animator animator) {
                this.b = false;
            }

            public void onAnimationCancel(Animator animator) {
                this.b = true;
            }

            public void onAnimationEnd(Animator animator) {
                if (!this.b) {
                    AnimatorSet unused = ArrowPopupView.this.A = null;
                    if (ArrowPopupView.this.H) {
                        ArrowPopupView.this.d();
                    }
                }
            }
        };
        this.L = new AnimatorListenerAdapter() {
            private boolean b;

            public void onAnimationStart(Animator animator) {
                this.b = false;
                boolean unused = ArrowPopupView.this.B = true;
            }

            public void onAnimationCancel(Animator animator) {
                this.b = true;
            }

            public void onAnimationEnd(Animator animator) {
                if (!this.b) {
                    boolean unused = ArrowPopupView.this.B = false;
                    AnimatorSet unused2 = ArrowPopupView.this.A = null;
                    ArrowPopupView.this.w.dismiss();
                    ArrowPopupView.this.setArrowMode(-1);
                }
            }
        };
        this.M = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.V6_ArrowPopupView, i2, 0);
        this.n = obtainStyledAttributes.getDrawable(R.styleable.V6_ArrowPopupView_v6_contentBackground);
        this.o = obtainStyledAttributes.getDrawable(R.styleable.V6_ArrowPopupView_v6_backgroundLeft);
        this.p = obtainStyledAttributes.getDrawable(R.styleable.V6_ArrowPopupView_v6_backgroundRight);
        this.q = obtainStyledAttributes.getDrawable(R.styleable.V6_ArrowPopupView_v6_titleBackground);
        this.r = obtainStyledAttributes.getDrawable(R.styleable.V6_ArrowPopupView_v6_topArrow);
        this.s = obtainStyledAttributes.getDrawable(R.styleable.V6_ArrowPopupView_v6_topArrowWithTitle);
        this.t = obtainStyledAttributes.getDrawable(R.styleable.V6_ArrowPopupView_v6_bottomArrow);
        this.u = obtainStyledAttributes.getDrawable(R.styleable.V6_ArrowPopupView_v6_rightArrow);
        this.v = obtainStyledAttributes.getDrawable(R.styleable.V6_ArrowPopupView_v6_leftArrow);
        obtainStyledAttributes.recycle();
        this.C = context.getResources().getDimensionPixelOffset(R.dimen.v6_arrow_popup_window_min_border);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.c = (ImageView) findViewById(R.id.popup_arrow);
        this.d = (FrameLayout) findViewById(16908290);
        this.e = (LinearLayout) findViewById(R.id.content_wrapper);
        this.e.setBackgroundDrawable(this.n);
        if (!(this.o == null || this.p == null)) {
            Rect rect = new Rect();
            this.o.getPadding(rect);
            this.e.setPadding(rect.top, rect.top, rect.top, rect.top);
        }
        this.f = (LinearLayout) findViewById(R.id.title_layout);
        this.f.setBackgroundDrawable(this.q);
        this.g = (TextView) findViewById(16908310);
        this.h = (Button) findViewById(EditActionMode.b);
        this.i = (Button) findViewById(EditActionMode.f3057a);
        this.j = new WrapperOnClickListener();
        this.k = new WrapperOnClickListener();
        this.h.setOnClickListener(this.j);
        this.i.setOnClickListener(this.k);
    }

    public void setContentView(View view) {
        setContentView(view, new ViewGroup.LayoutParams(-2, -2));
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.d.removeAllViews();
        if (view != null) {
            this.d.addView(view, layoutParams);
        }
    }

    public View getContentView() {
        if (this.d.getChildCount() > 0) {
            return this.d.getChildAt(0);
        }
        return null;
    }

    public void setContentView(int i2) {
        setContentView(LayoutInflater.from(getContext()).inflate(i2, (ViewGroup) null));
    }

    public void setTitle(CharSequence charSequence) {
        this.f.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        this.g.setText(charSequence);
    }

    public void setNegativeButton(CharSequence charSequence, View.OnClickListener onClickListener) {
        this.i.setText(charSequence);
        this.i.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        this.k.a(onClickListener);
    }

    public void setPositiveButton(CharSequence charSequence, View.OnClickListener onClickListener) {
        this.h.setText(charSequence);
        this.h.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        this.j.a(onClickListener);
    }

    public Button getPositiveButton() {
        return this.h;
    }

    public Button getNegativeButton() {
        return this.i;
    }

    private void a() {
        if (this.M == 0 || this.M == 1) {
            b();
        } else {
            c();
        }
        View contentView = getContentView();
        if (contentView != null) {
            ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
            if (contentView.getMeasuredHeight() > this.e.getMeasuredHeight() - this.f.getMeasuredHeight()) {
                layoutParams.height = this.e.getMeasuredHeight() - this.f.getMeasuredHeight();
                contentView.setLayoutParams(layoutParams);
            } else if (contentView.getMeasuredWidth() > this.e.getMeasuredWidth()) {
                layoutParams.width = this.e.getMeasuredWidth();
                contentView.setLayoutParams(layoutParams);
            }
            if (layoutParams.height <= 0 || layoutParams.width <= 0) {
                Log.w(f8340a, "Invalid LayoutPrams of content view, please check the anchor view");
            }
        }
    }

    private void b() {
        int i2;
        int width = this.b.getWidth();
        int height = this.b.getHeight();
        int width2 = getWidth();
        int height2 = getHeight();
        int measuredWidth = this.e.getMeasuredWidth() > this.e.getMinimumWidth() ? this.e.getMeasuredWidth() : this.e.getMinimumWidth();
        int measuredHeight = this.e.getMeasuredHeight() > this.e.getMinimumHeight() ? this.e.getMeasuredHeight() : this.e.getMinimumHeight();
        int measuredWidth2 = this.c.getMeasuredWidth();
        int measuredHeight2 = this.c.getMeasuredHeight();
        int[] iArr = new int[2];
        this.b.getLocationInWindow(iArr);
        int i3 = iArr[0];
        int i4 = iArr[1];
        getLocationInWindow(iArr);
        this.D = ((width / 2) + i3) - iArr[0];
        int i5 = width2 - this.D;
        this.F = (i3 + ((width - measuredWidth2) / 2)) - iArr[0];
        this.E = getTop() + this.m;
        if (this.M == 0) {
            this.E += ((i4 - iArr[1]) - measuredHeight) + (this.e.getPaddingBottom() - measuredHeight2);
            i2 = this.m + ((i4 - iArr[1]) - measuredHeight2);
        } else if (this.M == 1) {
            this.E += (((i4 + height) - iArr[1]) - this.e.getPaddingTop()) + measuredHeight2;
            i2 = this.E;
        } else {
            i2 = 0;
        }
        int i6 = measuredWidth / 2;
        int i7 = measuredWidth - i6;
        if (this.D >= i6 && i5 >= i7) {
            this.D -= i6;
        } else if (i5 < i7) {
            this.D = width2 - measuredWidth;
        } else if (this.D < i6) {
            this.D = 0;
        }
        this.D += this.l;
        this.F += this.l;
        this.e.layout(Math.max(this.D, 0), Math.max(this.E, 0), Math.min(this.D + measuredWidth, width2), Math.min(this.E + measuredHeight, height2));
        this.c.layout(this.F, i2, this.F + measuredWidth2, measuredHeight2 + i2);
    }

    private void c() {
        int i2;
        int[] iArr = new int[2];
        this.b.getLocationInWindow(iArr);
        int i3 = iArr[0];
        int i4 = iArr[1];
        getLocationInWindow(iArr);
        int width = this.b.getWidth();
        int height = this.b.getHeight();
        int width2 = getWidth();
        int height2 = getHeight();
        int measuredWidth = this.e.getMeasuredWidth() > this.e.getMinimumWidth() ? this.e.getMeasuredWidth() : this.e.getMinimumWidth();
        int measuredHeight = this.e.getMeasuredHeight() > this.e.getMinimumHeight() ? this.e.getMeasuredHeight() : this.e.getMinimumHeight();
        int measuredWidth2 = this.c.getMeasuredWidth();
        int measuredHeight2 = this.c.getMeasuredHeight();
        this.E = ((height / 2) + i4) - iArr[1];
        int i5 = height2 - this.E;
        this.G = (i4 + ((height - measuredHeight2) / 2)) - iArr[1];
        int i6 = measuredHeight / 2;
        int i7 = measuredHeight - i6;
        this.D = getLeft() + this.l;
        if (this.M == 2) {
            this.D += (((i3 - measuredWidth) + this.e.getPaddingRight()) - measuredWidth2) - iArr[0];
            i2 = ((i3 - measuredWidth2) - iArr[0]) + this.l;
        } else if (this.M == 3) {
            this.D += (((i3 + width) - this.e.getPaddingLeft()) + measuredWidth2) - iArr[0];
            i2 = this.D;
        } else {
            i2 = 0;
        }
        if (this.E >= i6 && i5 >= i7) {
            this.E = (this.E - i6) + this.m;
        } else if (i5 < i7) {
            this.E = (height2 - measuredHeight) + this.m;
        } else if (this.E < i6) {
            this.E = this.m;
        }
        this.G += this.m;
        this.e.layout(Math.max(this.D, 0), Math.max(this.E, 0), Math.min(this.D + measuredWidth, width2), Math.min(this.E + measuredHeight, height2));
        this.c.layout(i2, this.G, measuredWidth2 + i2, this.G + measuredHeight2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        if (this.M == -1) {
            e();
        } else {
            a();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i2;
        int i3;
        float f2;
        if (this.n == null) {
            int width = this.D + (this.e.getWidth() / 2);
            int height = this.E + (this.e.getHeight() / 2);
            RectF rectF = this.z;
            if (this.N != 1.0f) {
                rectF.left = 0.0f;
                rectF.top = 0.0f;
                rectF.right = (float) this.e.getWidth();
                rectF.bottom = (float) this.e.getHeight();
            }
            switch (this.M) {
                case 0:
                    f2 = 180.0f;
                    int measuredWidth = this.F + (this.c.getMeasuredWidth() / 2);
                    i2 = this.e.getRight() - measuredWidth;
                    i3 = measuredWidth - this.D;
                    break;
                case 1:
                    int measuredWidth2 = this.F + (this.c.getMeasuredWidth() / 2);
                    i2 = measuredWidth2 - this.D;
                    i3 = this.e.getRight() - measuredWidth2;
                    f2 = 0.0f;
                    break;
                case 2:
                    f2 = 90.0f;
                    int measuredHeight = this.G + (this.c.getMeasuredHeight() / 2);
                    i2 = measuredHeight - this.E;
                    i3 = this.e.getBottom() - measuredHeight;
                    break;
                case 3:
                    f2 = -90.0f;
                    int measuredHeight2 = this.G + (this.c.getMeasuredHeight() / 2);
                    i2 = this.e.getBottom() - measuredHeight2;
                    i3 = measuredHeight2 - this.E;
                    break;
                default:
                    f2 = 0.0f;
                    i3 = 0;
                    i2 = 0;
                    break;
            }
            int save = canvas.save();
            canvas.rotate(f2, (float) width, (float) height);
            switch (this.M) {
                case 0:
                case 1:
                    canvas.translate((float) this.D, (float) this.E);
                    this.o.setBounds(0, 0, i2, this.e.getHeight());
                    if (this.N != 1.0f) {
                        rectF.bottom = (float) ((int) (((float) this.e.getHeight()) * this.N));
                        canvas.clipRect(rectF);
                    } else {
                        canvas.translate(0.0f, (float) (this.M == 1 ? this.I : -this.I));
                    }
                    this.o.draw(canvas);
                    canvas.translate((float) i2, 0.0f);
                    this.p.setBounds(0, 0, i3, this.e.getHeight());
                    this.p.draw(canvas);
                    break;
                case 2:
                case 3:
                    canvas.translate((float) (width - (this.e.getHeight() / 2)), (float) (height - (this.e.getWidth() / 2)));
                    this.o.setBounds(0, 0, i2, this.e.getWidth());
                    if (this.N != 1.0f) {
                        rectF.bottom = (float) ((int) (((float) this.e.getWidth()) * this.N));
                        canvas.clipRect(rectF);
                    } else {
                        canvas.translate(0.0f, (float) (this.M == 3 ? this.I : -this.I));
                    }
                    this.o.draw(canvas);
                    canvas.translate((float) i2, 0.0f);
                    this.p.setBounds(0, 0, i3, this.e.getWidth());
                    this.p.draw(canvas);
                    break;
            }
            canvas.restoreToCount(save);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (this.N != 1.0f) {
            RectF rectF = this.z;
            rectF.left = (float) this.e.getLeft();
            rectF.right = (float) this.e.getRight();
            rectF.bottom = (float) this.e.getBottom();
            rectF.top = (float) this.e.getTop();
            float f2 = 1.0f - this.N;
            switch (this.M) {
                case 0:
                    rectF.top = (float) ((int) (((float) this.e.getTop()) + (((float) this.e.getHeight()) * f2)));
                    break;
                case 1:
                    rectF.bottom = (float) ((int) (((float) this.e.getBottom()) - (((float) this.e.getHeight()) * f2)));
                    break;
                case 2:
                    rectF.left = (float) ((int) (((float) this.e.getLeft()) + (((float) this.e.getWidth()) * f2)));
                    break;
                case 3:
                    rectF.right = (float) ((int) (((float) this.e.getRight()) - (((float) this.e.getWidth()) * f2)));
                    break;
            }
            canvas.saveLayer(rectF, (Paint) null, 31);
            super.dispatchDraw(canvas);
            canvas.restore();
            return;
        }
        super.dispatchDraw(canvas);
    }

    public float getRollingPercent() {
        return this.N;
    }

    public void setRollingPercent(float f2) {
        this.N = f2;
        postInvalidateOnAnimation();
    }

    public void animateToShow() {
        getBackground().setAlpha(0);
        setRollingPercent(0.0f);
        this.e.setAlpha(0.0f);
        invalidate();
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                ArrowPopupView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                if (ArrowPopupView.this.A != null) {
                    ArrowPopupView.this.A.cancel();
                }
                AnimatorSet unused = ArrowPopupView.this.A = new AnimatorSet();
                ObjectAnimator ofInt = ObjectAnimator.ofInt(ArrowPopupView.this.getBackground(), "alpha", new int[]{0, 166});
                ofInt.setDuration(300);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(ArrowPopupView.this.e, View.ALPHA, new float[]{1.0f});
                ofFloat.setStartDelay(30);
                ofFloat.setDuration(100);
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(ArrowPopupView.this.c, View.ALPHA, new float[]{1.0f});
                ofFloat2.setStartDelay(30);
                ofFloat2.setDuration(100);
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(ArrowPopupView.this, "RollingPercent", new float[]{1.0f});
                ofFloat3.setStartDelay(30);
                ofFloat3.setDuration(400);
                ofFloat3.setInterpolator(new DecelerateInterpolator(1.2f));
                ArrowPopupView.this.A.playTogether(new Animator[]{ofInt, ofFloat, ofFloat2, ofFloat3});
                ArrowPopupView.this.A.addListener(ArrowPopupView.this.K);
                ArrowPopupView.this.A.start();
                return true;
            }
        });
    }

    public void animateToDismiss() {
        if (!this.B) {
            if (this.A != null) {
                this.A.cancel();
            }
            this.A = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.e, View.ALPHA, new float[]{0.0f});
            ofFloat.setDuration(180);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.c, View.ALPHA, new float[]{0.0f});
            ofFloat2.setDuration(180);
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this, "RollingPercent", new float[]{0.0f});
            ofFloat3.setDuration(150);
            this.A.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
            this.A.addListener(this.L);
            this.A.start();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.A != null) {
            this.A.cancel();
        }
        this.A = new AnimatorSet();
        ObjectAnimator ofInt = ObjectAnimator.ofInt(getBackground(), "alpha", new int[]{166, 0});
        ofInt.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ArrowPopupView.this.getBackground().setAlpha(0);
            }
        });
        ofInt.setDuration(1900);
        float f2 = getContext().getResources().getDisplayMetrics().density * 4.0f;
        Property property = View.TRANSLATION_Y;
        int i2 = this.M;
        if (i2 != 0) {
            switch (i2) {
                case 2:
                    f2 = -f2;
                    property = View.TRANSLATION_X;
                    break;
                case 3:
                    property = View.TRANSLATION_X;
                    break;
            }
        } else {
            f2 = -f2;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.e, property, new float[]{0.0f, f2, 0.0f});
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.setDuration(1200);
        ofFloat.setRepeatCount(-1);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int unused = ArrowPopupView.this.I = ((Float) valueAnimator.getAnimatedValue()).intValue();
                int abs = Math.abs(ArrowPopupView.this.I);
                ArrowPopupView.this.invalidate(ArrowPopupView.this.e.getLeft() - abs, ArrowPopupView.this.e.getTop() - abs, ArrowPopupView.this.e.getRight() + abs, ArrowPopupView.this.e.getBottom() + abs);
            }
        });
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.c, property, new float[]{0.0f, f2, 0.0f});
        ofFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat2.setDuration(1200);
        ofFloat2.setRepeatCount(-1);
        this.A.playTogether(new Animator[]{ofInt, ofFloat, ofFloat2});
        this.A.start();
    }

    private void e() {
        int[] iArr = new int[2];
        this.b.getLocationInWindow(iArr);
        int width = getWidth();
        int height = getHeight();
        int measuredWidth = this.e.getMeasuredWidth();
        int measuredHeight = this.e.getMeasuredHeight();
        int i2 = 0;
        int[] iArr2 = {iArr[1] - measuredHeight, ((height - iArr[1]) - this.b.getHeight()) - measuredHeight, iArr[0] - measuredWidth, ((width - iArr[0]) - this.b.getWidth()) - measuredWidth};
        int i3 = 0;
        int i4 = Integer.MIN_VALUE;
        while (true) {
            if (i2 >= 4) {
                break;
            } else if (iArr2[i2] >= this.C) {
                i3 = i2;
                break;
            } else {
                if (iArr2[i2] > i4) {
                    i4 = iArr2[i2];
                    i3 = i2;
                }
                i2++;
            }
        }
        setArrowMode(i3);
    }

    public int getArrowMode() {
        return this.M;
    }

    public void setArrowMode(int i2) {
        this.M = i2;
        switch (i2) {
            case 0:
                this.c.setImageDrawable(this.t);
                return;
            case 1:
                this.c.setImageDrawable(this.f.getVisibility() == 0 ? this.s : this.r);
                return;
            case 2:
                this.c.setImageDrawable(this.u);
                return;
            case 3:
                this.c.setImageDrawable(this.v);
                return;
            default:
                return;
        }
    }

    public void setAnchor(View view) {
        this.b = view;
    }

    public void setOffset(int i2, int i3) {
        this.l = i2;
        this.m = i3;
    }

    public void setArrowPopupWindow(ArrowPopupWindow arrowPopupWindow) {
        this.w = arrowPopupWindow;
    }

    public void setTouchInterceptor(View.OnTouchListener onTouchListener) {
        this.x = onTouchListener;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x2 = (int) motionEvent.getX();
        int y2 = (int) motionEvent.getY();
        Rect rect = this.y;
        this.e.getHitRect(rect);
        if (motionEvent.getAction() == 0 && !rect.contains(x2, y2)) {
            this.w.a(true);
            return true;
        } else if (this.x == null || !this.x.onTouch(view, motionEvent)) {
            return false;
        } else {
            return true;
        }
    }

    public void enableShowingAnimation(boolean z2) {
        this.H = z2;
    }

    class WrapperOnClickListener implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        public View.OnClickListener f8346a;

        WrapperOnClickListener() {
        }

        public void a(View.OnClickListener onClickListener) {
            this.f8346a = onClickListener;
        }

        public void onClick(View view) {
            if (this.f8346a != null) {
                this.f8346a.onClick(view);
            }
            ArrowPopupView.this.w.a(true);
        }
    }
}
