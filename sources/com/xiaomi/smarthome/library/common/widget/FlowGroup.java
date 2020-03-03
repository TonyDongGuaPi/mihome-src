package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class FlowGroup extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    private boolean f18837a = true;
    private int b;
    private int c;
    private int d = 0;
    /* access modifiers changed from: private */
    public boolean e = true;
    /* access modifiers changed from: private */
    public TagMoreClickListener f;

    public interface TagMoreClickListener {
        void onClick(View view, boolean z);
    }

    public FlowGroup(Context context) {
        super(context);
        init();
    }

    public FlowGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public FlowGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.b = DisplayUtils.a(16.0f);
        this.c = DisplayUtils.a(18.0f);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int childCount = getChildCount();
        if (childCount == 0) {
            super.onMeasure(i, i2);
            return;
        }
        ((ImageView) getChildAt(childCount - 1)).measure(0, 0);
        boolean z = true;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1;
        int i6 = 0;
        while (true) {
            if (i3 >= childCount) {
                break;
            }
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                childAt.measure(0, 0);
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = getChildAt(0).getMeasuredHeight() + this.c;
                if (i4 + measuredWidth > size) {
                    if (i5 < 3 || this.f18837a) {
                        i5++;
                        i6 += measuredHeight;
                        i4 = 0;
                    } else if (i3 < childCount) {
                        z = false;
                    }
                }
                i4 += measuredWidth + this.b;
            }
            i3++;
        }
        this.e = z;
        this.d = i5;
        setMeasuredDimension(size, i6 + getChildAt(0).getMeasuredHeight() + DisplayUtils.a(26.0f));
    }

    private boolean a(int i, int i2, int i3, int i4, int i5) {
        if (i < this.d) {
            return false;
        }
        int i6 = i2 + i4;
        if (this.b + i6 + i5 >= i3 && i6 >= i3) {
            return true;
        }
        return false;
    }

    public void setExpand(boolean z) {
        this.f18837a = z;
        requestLayout();
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int childCount = getChildCount();
        if (childCount != 0) {
            int i6 = i3 - i;
            int i7 = childCount - 1;
            ImageView imageView = (ImageView) getChildAt(i7);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            int measuredWidth = imageView.getMeasuredWidth();
            int i8 = 1;
            int i9 = 0;
            int i10 = 0;
            for (int i11 = 0; i11 < i7; i11++) {
                View childAt = getChildAt(i11);
                if (childAt.getVisibility() != 8) {
                    int measuredWidth2 = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    View view = childAt;
                    if (a(i8, i9, i6, measuredWidth2, measuredWidth)) {
                        break;
                    }
                    if (i9 + measuredWidth2 > i6) {
                        i8++;
                        i10 += measuredHeight + this.c;
                        i9 = 0;
                    }
                    view.layout(i9, i10, i9 + measuredWidth2, i10 + measuredHeight);
                    i9 += measuredWidth2 + this.b;
                }
            }
            if (!this.e) {
                imageView.setImageResource(R.drawable.choose_tag_more);
            } else {
                imageView.setImageResource(R.drawable.choose_tag_add);
            }
            if (imageView.getMeasuredWidth() + i9 > i6) {
                i5 = 0;
                i10 += getChildAt(0).getMeasuredHeight() + this.c;
                i9 = 0;
            } else {
                i5 = 0;
            }
            imageView.layout(i9, i10, imageView.getMeasuredWidth() + i9, getChildAt(i5).getMeasuredHeight() + i10);
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FlowGroup.this.f != null) {
                        FlowGroup.this.f.onClick(view, FlowGroup.this.e);
                    }
                }
            });
        }
    }

    public void setMoreClickListener(TagMoreClickListener tagMoreClickListener) {
        this.f = tagMoreClickListener;
    }
}
