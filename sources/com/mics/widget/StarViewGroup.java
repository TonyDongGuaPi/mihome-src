package com.mics.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.mics.R;

public class StarViewGroup extends LinearLayout {
    private static final int f = 5;

    /* renamed from: a  reason: collision with root package name */
    private String f7825a;
    private String b;
    private String c;
    private String d;
    private String e;
    private boolean g;
    private OnStarChangeListener h;
    private ImageView[] i;
    private String[] j;
    private int k;

    public interface OnStarChangeListener {
        void a(int i);
    }

    public StarViewGroup(Context context) {
        this(context, (AttributeSet) null);
    }

    public StarViewGroup(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StarViewGroup(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f7825a = "非常失望";
        this.b = "失望";
        this.c = "一般";
        this.d = "满意";
        this.e = "非常满意";
        this.g = true;
        this.j = new String[]{this.f7825a, this.b, this.c, this.d, this.e};
        this.i = new ImageView[5];
        int applyDimension = (int) TypedValue.applyDimension(1, 23.0f, getResources().getDisplayMetrics());
        for (int i3 = 0; i3 < 5; i3++) {
            this.i[i3] = new ImageView(context);
            this.i[i3].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            this.i[i3].setImageResource(R.drawable.mics_icon_info_star);
            this.i[i3].setTag(Integer.valueOf(i3));
            addView(this.i[i3]);
            if (i3 != 4) {
                ((ViewGroup.MarginLayoutParams) this.i[i3].getLayoutParams()).rightMargin = applyDimension;
            }
        }
    }

    public void setCurrentStar(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        b(i2);
    }

    private void a(int i2) {
        if (i2 <= 0) {
            i2 = 1;
        }
        b(i2);
    }

    public int getCurrentStar() {
        return this.k;
    }

    public String getStarDesc() {
        if (this.k == 0) {
            return null;
        }
        return this.j[this.k - 1];
    }

    private void b(int i2) {
        if (i2 >= this.i.length) {
            i2 = this.i.length;
        }
        if (i2 != this.k) {
            for (int i3 = 0; i3 < this.i.length; i3++) {
                if (i3 < i2) {
                    this.i[i3].setImageResource(R.drawable.mics_icon_info_star_on);
                } else {
                    this.i[i3].setImageResource(R.drawable.mics_icon_info_star);
                }
            }
            this.k = i2;
            if (this.h != null) {
                this.h.a(i2);
            }
        }
    }

    public boolean isEditable() {
        return this.g;
    }

    public void disableEditable() {
        this.g = false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.g) {
            return super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action != 0 && action != 2) {
            return super.onTouchEvent(motionEvent);
        }
        float x = motionEvent.getX();
        int i2 = 0;
        while (i2 < 5 && this.i[i2].getX() < x) {
            i2++;
        }
        a(i2);
        return true;
    }

    public void setOnStarChangeListener(OnStarChangeListener onStarChangeListener) {
        this.h = onStarChangeListener;
    }
}
