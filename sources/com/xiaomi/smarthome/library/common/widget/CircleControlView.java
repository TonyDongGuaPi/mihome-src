package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.xiaomi.smarthome.R;

public class CircleControlView extends RelativeLayout {
    private static final int l = 20;

    /* renamed from: a  reason: collision with root package name */
    private View f18781a;
    private View b;
    private View c;
    private View d;
    private View e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;

    private boolean a(int i2, int i3, int i4) {
        return (i2 * i2) + (i3 * i3) <= i4 * i4;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public CircleControlView(Context context) {
        super(context);
        setContentView(context);
    }

    public CircleControlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setContentView(context);
    }

    public CircleControlView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setContentView(context);
    }

    private void setContentView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.miio_widget_circle_control, this);
        a();
    }

    private void a() {
        this.f18781a = findViewById(R.id.up_btn);
        this.f18781a.setSelected(false);
        this.b = findViewById(R.id.down_btn);
        this.b.setSelected(false);
        this.c = findViewById(R.id.left_btn);
        this.c.setSelected(false);
        this.d = findViewById(R.id.right_btn);
        this.d.setSelected(false);
        this.e = findViewById(R.id.ok_btn);
        this.e.setSelected(false);
    }

    public View getUpBtn() {
        return this.f18781a;
    }

    public View getDownBtn() {
        return this.b;
    }

    public View getLeftBtn() {
        return this.c;
    }

    public View getRightBtn() {
        return this.d;
    }

    public View getOkBtn() {
        return this.e;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.g = this.e.getMeasuredWidth() / 2;
        switch (motionEvent.getAction()) {
            case 0:
                this.h = (int) motionEvent.getX();
                this.i = (int) motionEvent.getY();
                this.f = getMeasuredWidth() / 2;
                int i2 = this.h - this.f;
                int i3 = this.f - this.i;
                if (a(i2, i3, this.f)) {
                    if ((i2 * i2) + (i3 * i3) > this.g * this.g) {
                        if (i2 >= i3 || (-i2) >= i3) {
                            if (i2 >= i3 || (-i2) <= i3) {
                                if (i2 <= i3 || (-i2) >= i3) {
                                    if (i2 > i3 && (-i2) > i3) {
                                        this.b.setPressed(true);
                                        break;
                                    }
                                } else {
                                    this.d.setPressed(true);
                                    break;
                                }
                            } else {
                                this.c.setPressed(true);
                                break;
                            }
                        } else {
                            this.f18781a.setPressed(true);
                            break;
                        }
                    } else {
                        this.e.setPressed(true);
                        break;
                    }
                }
                break;
            case 1:
                this.j = (int) motionEvent.getX();
                this.k = (int) motionEvent.getY();
                int i4 = this.j - this.f;
                int i5 = this.f - this.k;
                if (a(this.h, this.i, this.j, this.k) && a(i4, i5, this.f)) {
                    if ((i4 * i4) + (i5 * i5) <= this.g * this.g) {
                        this.e.performClick();
                    } else if (i4 < i5 && (-i4) < i5) {
                        this.f18781a.performClick();
                    } else if (i4 < i5 && (-i4) > i5) {
                        this.c.performClick();
                    } else if (i4 > i5 && (-i4) < i5) {
                        this.d.performClick();
                    } else if (i4 > i5 && (-i4) > i5) {
                        this.b.performClick();
                    }
                }
                this.e.setPressed(false);
                this.f18781a.setPressed(false);
                this.c.setPressed(false);
                this.d.setPressed(false);
                this.b.setPressed(false);
                break;
        }
        return true;
    }

    private int a(int i2) {
        return (int) ((((float) i2) * getResources().getDisplayMetrics().density) + 0.5f);
    }

    private boolean a(int i2, int i3, int i4, int i5) {
        int a2 = a(20);
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        return (i6 * i6) + (i7 * i7) <= a2 * a2;
    }
}
