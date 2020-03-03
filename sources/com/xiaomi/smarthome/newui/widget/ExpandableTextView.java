package com.xiaomi.smarthome.newui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class ExpandableTextView extends LinearLayout implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20858a = "ExpandableTextView";
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 0;
    private static final int e = 8;
    private static final int f = 300;
    private static final float g = 0.7f;
    private boolean h;
    /* access modifiers changed from: private */
    public boolean i;
    private int j;
    private int k;
    private int l;
    /* access modifiers changed from: private */
    public int m;
    protected View mToggleView;
    protected TextView mTv;
    private ExpandIndicatorController n;
    /* access modifiers changed from: private */
    public int o;
    /* access modifiers changed from: private */
    public float p;
    /* access modifiers changed from: private */
    public boolean q;
    private int r;
    private int s;
    private boolean t;
    /* access modifiers changed from: private */
    public OnExpandStateChangeListener u;
    private SparseBooleanArray v;
    private int w;

    interface ExpandIndicatorController {
        void a(View view);

        void a(boolean z);
    }

    public interface OnExpandStateChangeListener {
        void a(TextView textView, boolean z);
    }

    public void setOrientation(int i2) {
    }

    public ExpandableTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ExpandableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = true;
        this.r = R.id.expandable_text;
        this.s = R.id.expand_collapse;
        a(attributeSet);
    }

    @TargetApi(11)
    public ExpandableTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.i = true;
        this.r = R.id.expandable_text;
        this.s = R.id.expand_collapse;
        a(attributeSet);
    }

    public void onClick(View view) {
        ExpandCollapseAnimation expandCollapseAnimation;
        if (this.mToggleView.getVisibility() == 0) {
            this.i = !this.i;
            this.n.a(this.i);
            if (this.v != null) {
                this.v.put(this.w, this.i);
            }
            this.q = true;
            if (this.i) {
                expandCollapseAnimation = new ExpandCollapseAnimation(this, getHeight(), this.j);
            } else {
                expandCollapseAnimation = new ExpandCollapseAnimation(this, getHeight(), (getHeight() + this.k) - this.mTv.getHeight());
            }
            expandCollapseAnimation.setFillAfter(true);
            expandCollapseAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                    ExpandableTextView.a((View) ExpandableTextView.this.mTv, ExpandableTextView.this.p);
                }

                public void onAnimationEnd(Animation animation) {
                    ExpandableTextView.this.clearAnimation();
                    boolean unused = ExpandableTextView.this.q = false;
                    if (ExpandableTextView.this.u != null) {
                        ExpandableTextView.this.u.a(ExpandableTextView.this.mTv, !ExpandableTextView.this.i);
                    }
                }
            });
            clearAnimation();
            startAnimation(expandCollapseAnimation);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.q;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        a();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        if (!this.h || getVisibility() == 8) {
            super.onMeasure(i2, i3);
            return;
        }
        this.h = false;
        this.mToggleView.setVisibility(8);
        this.mTv.setMaxLines(Integer.MAX_VALUE);
        super.onMeasure(i2, i3);
        if (this.mTv.getLineCount() > this.l) {
            this.k = a(this.mTv);
            if (this.i) {
                this.mTv.setMaxLines(this.l);
            }
            this.mToggleView.setVisibility(0);
            super.onMeasure(i2, i3);
            if (this.i) {
                this.mTv.post(new Runnable() {
                    public void run() {
                        int unused = ExpandableTextView.this.m = ExpandableTextView.this.getHeight() - ExpandableTextView.this.mTv.getHeight();
                    }
                });
                this.j = getMeasuredHeight();
            }
        }
    }

    public void setOnExpandStateChangeListener(OnExpandStateChangeListener onExpandStateChangeListener) {
        this.u = onExpandStateChangeListener;
    }

    public void setText(CharSequence charSequence) {
        this.h = true;
        this.mTv.setText(charSequence);
        setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        clearAnimation();
        getLayoutParams().height = -2;
        requestLayout();
    }

    public void setText(CharSequence charSequence, SparseBooleanArray sparseBooleanArray, int i2) {
        this.v = sparseBooleanArray;
        this.w = i2;
        boolean z = sparseBooleanArray.get(i2, true);
        clearAnimation();
        this.i = z;
        this.n.a(this.i);
        setText(charSequence);
    }

    public CharSequence getText() {
        if (this.mTv == null) {
            return "";
        }
        return this.mTv.getText();
    }

    private void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ExpandableTextView);
        this.l = obtainStyledAttributes.getInt(8, 8);
        this.o = obtainStyledAttributes.getInt(1, 300);
        this.p = obtainStyledAttributes.getFloat(0, 0.7f);
        this.r = obtainStyledAttributes.getResourceId(7, R.id.expandable_text);
        this.s = obtainStyledAttributes.getResourceId(3, R.id.expand_collapse);
        this.t = obtainStyledAttributes.getBoolean(5, true);
        this.n = a(getContext(), obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        setOrientation(0);
        setVisibility(8);
    }

    private void a() {
        this.mTv = (TextView) findViewById(this.r);
        if (this.t) {
            this.mTv.setOnClickListener(this);
        } else {
            this.mTv.setOnClickListener((View.OnClickListener) null);
        }
        this.mToggleView = findViewById(this.s);
        this.n.a(this.mToggleView);
        this.n.a(this.i);
        this.mToggleView.setOnClickListener(this);
    }

    private static boolean b() {
        return Build.VERSION.SDK_INT >= 11;
    }

    private static boolean c() {
        return Build.VERSION.SDK_INT >= 21;
    }

    /* access modifiers changed from: private */
    @TargetApi(11)
    public static void a(View view, float f2) {
        if (b()) {
            view.setAlpha(f2);
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(f2, f2);
        alphaAnimation.setDuration(0);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
    }

    @TargetApi(21)
    private static Drawable a(Context context, int i2) {
        Resources resources = context.getResources();
        if (c()) {
            return resources.getDrawable(i2, context.getTheme());
        }
        return resources.getDrawable(i2);
    }

    private static int a(TextView textView) {
        return textView.getLayout().getLineTop(textView.getLineCount()) + textView.getCompoundPaddingTop() + textView.getCompoundPaddingBottom();
    }

    private static ExpandIndicatorController a(Context context, TypedArray typedArray) {
        switch (typedArray.getInt(6, 0)) {
            case 0:
                Drawable drawable = typedArray.getDrawable(4);
                Drawable drawable2 = typedArray.getDrawable(2);
                if (drawable == null) {
                    drawable = a(context, (int) R.drawable.navi_arrow_down_collapse);
                }
                if (drawable2 == null) {
                    drawable2 = a(context, (int) R.drawable.navi_arrow_up_collapse);
                }
                return new ImageButtonExpandController(drawable, drawable2);
            case 1:
                return new TextViewExpandController(typedArray.getString(4), typedArray.getString(2));
            default:
                throw new IllegalStateException("Must be of enum: ExpandableTextView_expandToggleType, one of EXPAND_INDICATOR_IMAGE_BUTTON or EXPAND_INDICATOR_TEXT_VIEW.");
        }
    }

    class ExpandCollapseAnimation extends Animation {
        private final View b;
        private final int c;
        private final int d;

        public boolean willChangeBounds() {
            return true;
        }

        public ExpandCollapseAnimation(View view, int i, int i2) {
            this.b = view;
            this.c = i;
            this.d = i2;
            setDuration((long) ExpandableTextView.this.o);
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            int i = (int) ((((float) (this.d - this.c)) * f) + ((float) this.c));
            ExpandableTextView.this.mTv.setMaxHeight(i - ExpandableTextView.this.m);
            if (Float.compare(ExpandableTextView.this.p, 1.0f) != 0) {
                ExpandableTextView.a((View) ExpandableTextView.this.mTv, ExpandableTextView.this.p + (f * (1.0f - ExpandableTextView.this.p)));
            }
            this.b.getLayoutParams().height = i;
            this.b.requestLayout();
        }

        public void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
        }
    }

    static class ImageButtonExpandController implements ExpandIndicatorController {

        /* renamed from: a  reason: collision with root package name */
        private final Drawable f20862a;
        private final Drawable b;
        private ImageButton c;

        public ImageButtonExpandController(Drawable drawable, Drawable drawable2) {
            this.f20862a = drawable;
            this.b = drawable2;
        }

        public void a(boolean z) {
            this.c.setImageDrawable(z ? this.f20862a : this.b);
        }

        public void a(View view) {
            this.c = (ImageButton) view;
        }
    }

    static class TextViewExpandController implements ExpandIndicatorController {

        /* renamed from: a  reason: collision with root package name */
        private final String f20863a;
        private final String b;
        private TextView c;

        public TextViewExpandController(String str, String str2) {
            this.f20863a = str;
            this.b = str2;
        }

        public void a(boolean z) {
            this.c.setText(z ? this.f20863a : this.b);
        }

        public void a(View view) {
            this.c = (TextView) view;
        }
    }
}
