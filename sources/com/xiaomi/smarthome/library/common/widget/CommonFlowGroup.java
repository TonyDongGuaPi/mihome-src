package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import java.util.List;

public class CommonFlowGroup extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    private int f18788a;
    private View b;
    private boolean c = true;
    private TagClickListener d;
    private int e;
    private int f;
    private int g = -1;
    private float h = 66.0f;
    private TagCreateListener i = new TagCreateListener() {
        public View onAddCreate(Context context) {
            return LayoutInflater.from(context).inflate(R.layout.common_flow_tag_add_item, (ViewGroup) null);
        }

        public TextView onTagCreate(Context context, int i) {
            return (TextView) LayoutInflater.from(context).inflate(R.layout.common_flow_tag_item, (ViewGroup) null);
        }
    };

    public interface TagClickListener {
        void a();

        void a(int i);
    }

    public interface TagCreateListener {
        View onAddCreate(Context context);

        TextView onTagCreate(Context context, int i);
    }

    public CommonFlowGroup(Context context) {
        super(context);
        init();
    }

    public CommonFlowGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CommonFlowGroup(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.b = this.i.onAddCreate(getContext());
        if (this.b != null) {
            this.b.setContentDescription(getContext().getString(R.string.tag_add));
            this.b.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    CommonFlowGroup.this.b(view);
                }
            });
        }
        this.e = DisplayUtils.a(12.0f);
        this.f = DisplayUtils.a(12.0f);
        this.f18788a = (getResources().getDisplayMetrics().widthPixels * 2) / 5;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        if (this.d != null) {
            this.d.a();
        }
    }

    public void showAddView(Boolean bool) {
        if (this.c != bool.booleanValue() && this.b != null) {
            this.c = bool.booleanValue();
            if (bool.booleanValue()) {
                addView(this.b);
            } else {
                removeView(this.b);
            }
        }
    }

    public void setData(List<String> list) {
        removeAllViews();
        if (list != null) {
            int i2 = 0;
            for (String text : list) {
                TextView onTagCreate = this.i.onTagCreate(getContext(), i2);
                onTagCreate.setText(text);
                onTagCreate.setTag(Integer.valueOf(i2));
                onTagCreate.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        CommonFlowGroup.this.a(view);
                    }
                });
                addView(onTagCreate);
                i2++;
            }
        }
        if (this.c && this.b != null) {
            addView(this.b);
        }
        requestLayout();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        try {
            setSelectIndex(((Integer) view.getTag()).intValue());
            if (this.d != null) {
                this.d.a(this.g);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public int getSelectIndex() {
        return this.g;
    }

    public void setSelectIndex(int i2) {
        if (getChildCount() > i2) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                getChildAt(i3).setSelected(false);
            }
            if (i2 < 0) {
                this.g = i2;
                return;
            }
            getChildAt(i2).setSelected(true);
            this.g = i2;
        }
    }

    public void setMinWidth(float f2) {
        this.h = f2;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int childCount = getChildCount();
        int i4 = 0;
        if (this.b != null) {
            this.b.measure(0, 0);
        }
        int size = View.MeasureSpec.getSize(i2);
        if (childCount > 0) {
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                if (childAt.getVisibility() != 8) {
                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.f18788a, Integer.MIN_VALUE);
                    childAt.measure(makeMeasureSpec, 0);
                    if (childAt.getMeasuredWidth() < DisplayUtils.a(this.h)) {
                        int a2 = ((DisplayUtils.a(this.h) - childAt.getMeasuredWidth()) / 2) + childAt.getPaddingLeft();
                        childAt.setPadding(a2, childAt.getPaddingTop(), a2, childAt.getPaddingBottom());
                        childAt.measure(makeMeasureSpec, 0);
                    }
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight() + this.f;
                    if (i6 + measuredWidth > size) {
                        i5 += measuredHeight;
                        i6 = 0;
                    }
                    i6 += measuredWidth + this.e;
                }
            }
            i4 = i5 + getChildAt(0).getMeasuredHeight() + getPaddingBottom() + getPaddingTop();
        }
        setMeasuredDimension(size, i4);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int i6 = i4 - i2;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 <= childCount - 1; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                if (i7 + measuredWidth > i6) {
                    i8 += this.f + measuredHeight;
                    i7 = 0;
                }
                childAt.layout(i7, getPaddingTop() + i8, measuredWidth + i7, getPaddingBottom() + i8 + measuredHeight);
                i7 += childAt.getWidth() + this.e;
            }
        }
    }

    public void setOnTagCreateListener(TagCreateListener tagCreateListener) {
        this.i = tagCreateListener;
    }

    public void setOnTagClickListener(TagClickListener tagClickListener) {
        this.d = tagClickListener;
    }
}
