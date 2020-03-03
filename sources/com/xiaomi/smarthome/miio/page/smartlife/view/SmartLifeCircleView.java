package com.xiaomi.smarthome.miio.page.smartlife.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.page.smartlife.SmartLifeItem;

public class SmartLifeCircleView extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final String f19936a = "SmartLifeCircleView";
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private int f = 3;
    private int g = 0;
    private boolean h = false;
    private boolean i = false;
    /* access modifiers changed from: private */
    public View j;
    private TextView k;
    private View l;
    private View m;
    private SmartLifeItem n;
    /* access modifiers changed from: private */
    public boolean o = false;
    private Resources p;
    private Drawable q;
    private String r;
    private int s;
    private SelectListener t;
    private AnimatorSet u;
    private boolean v = false;

    public interface SelectListener {
        void a(boolean z, SmartLifeItem smartLifeItem);
    }

    public SmartLifeCircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public SmartLifeCircleView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.p = getResources();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SmartLifeCircle);
        this.f = obtainStyledAttributes.getInt(3, 3);
        this.q = obtainStyledAttributes.getDrawable(0);
        this.r = obtainStyledAttributes.getString(2);
        this.s = obtainStyledAttributes.getInt(1, -1);
        obtainStyledAttributes.recycle();
        this.g = (int) (this.p.getDisplayMetrics().density * 5.0f);
        this.j = LayoutInflater.from(context).inflate(R.layout.smart_life_circle, (ViewGroup) null);
        this.k = (TextView) this.j.findViewById(R.id.content_tv);
        this.l = this.j.findViewById(R.id.normal_bg);
        this.m = this.j.findViewById(R.id.select_bg);
        this.j.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean unused = SmartLifeCircleView.this.o = !SmartLifeCircleView.this.o;
                SmartLifeCircleView.this.updateState(SmartLifeCircleView.this.o);
            }
        });
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartLifeCircleView.this.j.performClick();
            }
        });
        if (this.s != -1 && !TextUtils.isEmpty(this.r)) {
            this.n = new SmartLifeItem();
            this.n.f19933a = this.s;
            this.n.b = this.r;
            setData(this.n, false);
        }
        addView(this.j);
        setWillNotDraw(false);
    }

    private int a(String str) {
        if (TextUtils.equals(str, "size_1")) {
            return 0;
        }
        if (TextUtils.equals(str, "size_2")) {
            return 1;
        }
        if (TextUtils.equals(str, "size_3")) {
            return 2;
        }
        return TextUtils.equals(str, "size_4") ? 3 : 3;
    }

    public void setData(SmartLifeItem smartLifeItem, boolean z) {
        Drawable a2;
        if (smartLifeItem != null) {
            this.o = z;
            this.n = smartLifeItem;
            this.k.setText(smartLifeItem.b);
            if (this.q != null) {
                this.k.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, this.q, (Drawable) null, (Drawable) null);
                this.k.setCompoundDrawablePadding((int) (this.p.getDisplayMetrics().density * -3.0f));
            } else if (!(this.f == 3 || (a2 = a(smartLifeItem.f19933a)) == null)) {
                this.k.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, a2, (Drawable) null, (Drawable) null);
                this.k.setCompoundDrawablePadding((int) (this.p.getDisplayMetrics().density * -3.0f));
            }
            updateState(z);
        }
    }

    public void updateState(boolean z) {
        this.o = z;
        int i2 = 0;
        this.l.setVisibility(z ? 8 : 0);
        View view = this.m;
        if (!z) {
            i2 = 8;
        }
        view.setVisibility(i2);
        if (this.t != null) {
            this.t.a(z, this.n);
        }
    }

    private Drawable a(int i2) {
        switch (i2) {
            case 1:
                return getResources().getDrawable(R.drawable.std_lifechoice_icon_aircleaner);
            case 2:
                return getResources().getDrawable(R.drawable.std_lifechoice_icon_airconditioner);
            case 3:
                return getResources().getDrawable(R.drawable.std_lifechoice_icon_coldfan);
            case 6:
                return getResources().getDrawable(R.drawable.std_lifechoice_icon_electricheating);
            case 7:
                return getResources().getDrawable(R.drawable.std_lifechoice_icon_electrickettle);
            case 8:
                return getResources().getDrawable(R.drawable.std_lifechoice_icon_fan);
            case 9:
                return getResources().getDrawable(R.drawable.std_lifechoice_icon_heater);
            case 10:
                return getResources().getDrawable(R.drawable.std_lifechoice_icon_humidifier);
            case 12:
                return getResources().getDrawable(R.drawable.std_lifechoice_icon_light);
            case 13:
                return getResources().getDrawable(R.drawable.std_lifechoice_icon_microwaveoven);
            case 14:
                return getResources().getDrawable(R.drawable.std_lifechoice_icon_ricecooker);
            case 15:
                return getResources().getDrawable(R.drawable.std_lifechoice_icon_socket);
            case 16:
                return getResources().getDrawable(R.drawable.std_lifechoice_icon_sound);
            case 18:
                return getResources().getDrawable(R.drawable.std_lifechoice_icon_tv);
            case 19:
                return getResources().getDrawable(R.drawable.std_lifechoice_icon_waterpurifier);
            default:
                return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int i4 = 140;
        if (this.f != 0) {
            if (this.f == 1) {
                i4 = 100;
            } else if (this.f == 2) {
                i4 = 80;
            } else if (this.f == 3) {
                i4 = 60;
            }
        }
        int i5 = (int) (this.p.getDisplayMetrics().density * ((float) i4));
        setMeasuredDimension(resolveSize(i5, i2), resolveSize(i5, i3));
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            measureChild(getChildAt(i6), View.MeasureSpec.makeMeasureSpec(i5, 1073741824), View.MeasureSpec.makeMeasureSpec(i5, 1073741824));
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            childAt.layout(0, 0, childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
        }
    }

    public void setOnSelectListener(SelectListener selectListener) {
        this.t = selectListener;
    }

    public SmartLifeItem getData() {
        return this.n;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.j.clearAnimation();
        if (this.u != null) {
            this.u.cancel();
        }
        this.t = null;
        this.h = true;
        this.v = false;
        this.i = true;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.v) {
            startAnimation();
        }
    }

    public void startAnimation() {
        if (!this.v && !this.h) {
            this.v = true;
            this.i = false;
            a(new PointF(0.0f, 0.0f));
        }
    }

    public void stopAnim() {
        this.i = true;
        this.v = false;
        this.j.clearAnimation();
        if (this.u != null) {
            this.u.cancel();
        }
    }

    /* access modifiers changed from: private */
    public void a(PointF pointF) {
        if (!this.h && !this.i) {
            final PointF pointF2 = new PointF();
            float random = (float) Math.random();
            if (random < 0.25f) {
                pointF2.y = (float) (-this.g);
                pointF2.x = ((((float) (this.g * 2)) * random) * 4.0f) - ((float) this.g);
            } else if (random < 0.5f) {
                pointF2.x = (float) this.g;
                double d2 = (double) (this.g * 2);
                double d3 = (double) random;
                Double.isNaN(d3);
                Double.isNaN(d2);
                double d4 = (double) this.g;
                Double.isNaN(d4);
                pointF2.y = (float) (((d2 * (d3 - 0.25d)) * 4.0d) - d4);
            } else if (random < 0.75f) {
                pointF2.y = (float) this.g;
                double d5 = (double) (this.g * 2);
                double d6 = (double) random;
                Double.isNaN(d6);
                Double.isNaN(d5);
                double d7 = (double) this.g;
                Double.isNaN(d7);
                pointF2.x = (float) (((d5 * (d6 - 0.5d)) * 4.0d) - d7);
            } else if (random < 1.0f) {
                pointF2.x = (float) (-this.g);
                double d8 = (double) (this.g * 2);
                double d9 = (double) random;
                Double.isNaN(d9);
                Double.isNaN(d8);
                double d10 = (double) this.g;
                Double.isNaN(d10);
                pointF2.y = (float) (((d8 * (d9 - 0.75d)) * 4.0d) - d10);
            }
            startAnimation(pointF, pointF2, new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    SmartLifeCircleView.this.a(pointF2);
                }
            });
        }
    }

    public void startAnimation(PointF pointF, PointF pointF2, final Animator.AnimatorListener animatorListener) {
        double d2 = (double) this.g;
        Double.isNaN(d2);
        double sqrt = Math.sqrt(Math.pow((double) Math.abs(pointF2.x - pointF.x), 2.0d) + Math.pow((double) Math.abs(pointF2.y - pointF.y), 2.0d));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.j, View.X, new float[]{pointF.x, pointF2.x});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.j, View.Y, new float[]{pointF.y, pointF2.y});
        this.u = new AnimatorSet();
        this.u.setDuration((long) (sqrt / (d2 / 1500.0d))).play(ofFloat).with(ofFloat2);
        this.u.start();
        this.u.addListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animator) {
                if (animatorListener != null) {
                    animatorListener.onAnimationStart(animator);
                }
            }

            public void onAnimationEnd(Animator animator) {
                if (animatorListener != null) {
                    animatorListener.onAnimationEnd(animator);
                }
            }

            public void onAnimationCancel(Animator animator) {
                if (animatorListener != null) {
                    animatorListener.onAnimationCancel(animator);
                }
            }

            public void onAnimationRepeat(Animator animator) {
                if (animatorListener != null) {
                    animatorListener.onAnimationRepeat(animator);
                }
            }
        });
    }
}
