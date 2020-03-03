package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.smarthome.R;

public class MultiViewPager extends ViewPager {

    /* renamed from: a  reason: collision with root package name */
    private int f18898a;
    private int b;
    private int c;
    private boolean d;
    private final Point e;
    private final Point f;
    private ViewPager.PageTransformer g;
    private boolean h;

    private static void a(Point point, Point point2) {
        if (point2.x >= 0 && point.x > point2.x) {
            point.x = point2.x;
        }
        if (point2.y >= 0 && point.y > point2.y) {
            point.y = point2.y;
        }
    }

    public MultiViewPager(Context context) {
        super(context);
        this.f18898a = -1;
        this.b = -1;
        this.h = true;
        this.e = new Point();
        this.f = new Point();
    }

    public MultiViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f18898a = -1;
        this.b = -1;
        this.h = true;
        a(context, attributeSet);
        this.e = new Point();
        this.f = new Point();
    }

    private void a(Context context, AttributeSet attributeSet) {
        setClipChildren(false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MultiViewPager);
        setMaxWidth(obtainStyledAttributes.getDimensionPixelSize(0, -1));
        setMaxHeight(obtainStyledAttributes.getDimensionPixelSize(1, -1));
        setMatchChildWidth(obtainStyledAttributes.getResourceId(2, 0));
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        this.e.set(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        if (this.f18898a >= 0 || this.b >= 0) {
            this.f.set(this.f18898a, this.b);
            a(this.e, this.f);
            i = View.MeasureSpec.makeMeasureSpec(this.e.x, 1073741824);
            i2 = View.MeasureSpec.makeMeasureSpec(this.e.y, 1073741824);
        }
        super.onMeasure(i, i2);
        onMeasurePage(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onMeasurePage(int i, int i2) {
        if (this.d) {
            if (this.c == 0) {
                this.d = false;
            } else if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                childAt.measure(i, i2);
                int measuredWidth = childAt.getMeasuredWidth();
                View findViewById = childAt.findViewById(this.c);
                if (findViewById != null) {
                    int measuredWidth2 = findViewById.getMeasuredWidth();
                    if (measuredWidth2 > 0) {
                        this.d = false;
                        setPageMargin(-((int) (((float) (measuredWidth - measuredWidth2)) + (getResources().getDisplayMetrics().density * 18.0f))));
                        setOffscreenPageLimit(((int) Math.ceil((double) (((float) measuredWidth) / ((float) measuredWidth2)))) + 1);
                    }
                    if (this.h && getChildCount() > 1 && this.g != null) {
                        this.h = false;
                        if (this.g != null) {
                            int scrollX = getScrollX() + childAt.getMeasuredWidth();
                            int childCount = getChildCount();
                            for (int i3 = 1; i3 < childCount; i3++) {
                                View childAt2 = getChildAt(i3);
                                if (!((ViewPager.LayoutParams) childAt2.getLayoutParams()).isDecor) {
                                    this.g.transformPage(childAt2, ((float) (((childAt2.getLeft() - scrollX) - measuredWidth) + measuredWidth2)) / ((float) ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())));
                                    scrollX += childAt2.getMeasuredWidth();
                                }
                            }
                        }
                    }
                    requestLayout();
                    return;
                }
                throw new NullPointerException("MatchWithChildResId did not find that ID in the first fragment of the ViewPager; is that view defined in the child view's layout? Note that MultiViewPager only measures the child for index 0.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.d = true;
    }

    public void setMatchChildWidth(int i) {
        if (this.c != i) {
            this.c = i;
            this.d = true;
        }
    }

    public void setMaxWidth(int i) {
        this.f18898a = i;
    }

    public void setMaxHeight(int i) {
        this.b = i;
    }

    public void setPageTransformer(boolean z, ViewPager.PageTransformer pageTransformer) {
        super.setPageTransformer(z, pageTransformer);
        this.g = pageTransformer;
    }
}
