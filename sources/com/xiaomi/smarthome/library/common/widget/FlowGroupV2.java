package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import java.util.ArrayList;
import java.util.List;

public class FlowGroupV2 extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    private int f18839a;
    private int b;
    private int c = 0;
    private int d = Integer.MAX_VALUE;
    private int e;
    private View f;
    private View g;
    protected List<View> mChildren = new ArrayList();
    protected boolean mExtraViewAdded = false;
    protected int mShowCount = 0;
    protected int mWhichExtraViewShow = 0;

    public FlowGroupV2(Context context) {
        super(context);
        init();
    }

    public FlowGroupV2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public FlowGroupV2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.f18839a = DisplayUtils.a(10.0f);
        this.b = DisplayUtils.a(13.0f);
        this.e = (getResources().getDisplayMetrics().widthPixels * 2) / 5;
    }

    public void setMaxRows(int i) {
        this.d = i;
    }

    public int getMaxRows() {
        return this.d;
    }

    public void setMoreView(View view) {
        this.f = view;
    }

    public void setAddView(View view) {
        this.g = view;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int size = this.mChildren.size();
        if (size == 0) {
            super.onMeasure(i, i2);
            return;
        }
        if (this.f != null) {
            this.f.measure(0, 0);
            i3 = this.f.getMeasuredWidth();
        } else {
            i3 = 0;
        }
        if (this.g != null) {
            this.g.measure(0, 0);
            i3 = Math.max(i3, this.g.getMeasuredWidth());
        }
        int size2 = View.MeasureSpec.getSize(i);
        int i4 = 0;
        int i5 = 0;
        int i6 = 1;
        int i7 = 0;
        while (true) {
            if (i4 >= size) {
                break;
            }
            View view = this.mChildren.get(i4);
            if (view.getVisibility() != 8) {
                if (view.getParent() == null) {
                    addView(view);
                }
                view.measure(View.MeasureSpec.makeMeasureSpec(this.e, Integer.MIN_VALUE), 0);
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = getChildAt(0).getMeasuredHeight() + this.b;
                int i8 = i5 + measuredWidth;
                int i9 = this.f18839a + i8;
                if (i6 >= this.d && (this.f != null || this.g != null)) {
                    if (i9 >= (size2 - i3) - this.f18839a) {
                        this.mShowCount = i4 + 1;
                        break;
                    }
                } else if (i8 > size2) {
                    i6++;
                    i7 += measuredHeight;
                    i5 = 0;
                }
                i5 += measuredWidth + this.f18839a;
            }
            i4++;
        }
        if (this.mShowCount == 0) {
            this.mShowCount = i4 + 1;
        }
        this.c = i6;
        setMeasuredDimension(size2, i7 + getChildAt(0).getMeasuredHeight() + DisplayUtils.a(26.0f));
    }

    private boolean a(int i, int i2, int i3, int i4, int i5) {
        if (i < this.c) {
            return false;
        }
        int i6 = i2 + i4;
        if (this.f18839a + i6 + i5 >= i3 && i6 >= i3) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int size = this.mChildren.size();
        if (size != 0) {
            int i5 = i3 - i;
            this.mShowCount = this.mShowCount == 0 ? size : this.mShowCount;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            while (i6 <= size - 1 && i6 < this.mShowCount - 1) {
                View view = this.mChildren.get(i6);
                if (view.getVisibility() != 8) {
                    int measuredWidth = view.getMeasuredWidth();
                    int measuredHeight = view.getMeasuredHeight();
                    if (i7 + measuredWidth > i5) {
                        i8 += this.b + measuredHeight;
                        i7 = 0;
                    }
                    view.layout(i7, i8, measuredWidth + i7, measuredHeight + i8);
                    i7 += view.getWidth() + this.f18839a;
                }
                i6++;
            }
            if (this.mExtraViewAdded) {
                return;
            }
            if (this.f != null || this.g != null) {
                View view2 = this.mShowCount == size + 1 ? this.g : this.f;
                view2.layout(i7, i8, view2.getMeasuredWidth() + i7, view2.getMeasuredHeight() + i8);
                if (view2 != null && view2.getParent() == null) {
                    addView(view2);
                    this.mExtraViewAdded = true;
                }
            }
        }
    }
}
