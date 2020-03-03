package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import java.util.ArrayList;
import java.util.List;

public class CustomTextListItemView extends ListItemView {

    /* renamed from: a  reason: collision with root package name */
    private static int f18809a = 24;
    private int b;
    private int c;
    private int d;
    private TextView e;
    private TextView f;
    private int g = 1;
    private List<View> h = new ArrayList();

    public CustomTextListItemView(Context context) {
        super(context);
        a(context, (AttributeSet) null);
    }

    public CustomTextListItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CustomTextListItemView);
        this.b = obtainStyledAttributes.getResourceId(0, -1);
        this.c = obtainStyledAttributes.getResourceId(1, -1);
        if (this.b == -1 && this.c != -1) {
            this.b = this.c;
            this.c = -1;
        }
        this.d = DisplayUtils.b(context);
        setFocusable(true);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        this.h.clear();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 >= getChildCount()) {
                break;
            }
            if (!(getChildAt(i4) instanceof TextView)) {
                this.h.add(getChildAt(i4));
            } else if (this.b == -1) {
                this.b = getChildAt(i4).getId();
                this.e = (TextView) getChildAt(i4);
                this.e.setSingleLine();
                break;
            } else if (this.c == -1) {
                this.c = getChildAt(i4).getId();
                this.f = (TextView) getChildAt(i4);
                this.f.setSingleLine();
                break;
            } else {
                if (this.b == getChildAt(i4).getId()) {
                    this.e = (TextView) getChildAt(i4);
                    this.e.setSingleLine();
                }
                if (this.c == getChildAt(i4).getId()) {
                    this.f = (TextView) getChildAt(i4);
                    this.f.setSingleLine();
                }
            }
            i4++;
        }
        b();
        if (this.e != null) {
            this.e.setTextSize(15.0f);
            this.e.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.e.getLayoutParams();
            int measuredWidth = layoutParams.rightMargin + layoutParams.leftMargin + this.e.getMeasuredWidth();
            if (measuredWidth > this.d) {
                a();
            } else {
                if (this.f != null) {
                    this.f.setTextSize(15.0f);
                    this.f.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
                    i3 = this.f.getMeasuredWidth() + layoutParams2.rightMargin + layoutParams2.leftMargin;
                    this.f.setMaxLines(2);
                }
                if (measuredWidth + i3 + DisplayUtils.a((float) f18809a) > this.d) {
                    a();
                } else {
                    b();
                }
            }
        }
        ViewGroup.LayoutParams layoutParams3 = getLayoutParams();
        layoutParams3.height = getResources().getDimensionPixelSize(R.dimen.std_list_item_height_single_line);
        setLayoutParams(layoutParams3);
        requestLayout();
        getParent().requestLayout();
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    private void a() {
        if (this.e != null && this.f != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.e.getLayoutParams();
            int[] rules = layoutParams.getRules();
            for (int i = 0; i < rules.length; i++) {
                rules[i] = 0;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.h.size(); i3++) {
                this.h.get(i3).measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                i2 += this.h.get(i3).getMeasuredWidth();
            }
            layoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.custom_text_item_margin_left), 0, getResources().getDimensionPixelSize(R.dimen.custom_text_item_margin_right) + i2, 0);
            layoutParams.addRule(15, 0);
            this.e.setLayoutParams(layoutParams);
            this.f.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
            int[] rules2 = layoutParams.getRules();
            for (int i4 = 0; i4 < rules2.length; i4++) {
                rules2[i4] = 0;
            }
            int measuredWidth = layoutParams2.rightMargin + layoutParams2.leftMargin + this.f.getMeasuredWidth();
            if (measuredWidth > 0) {
                if (measuredWidth <= this.d) {
                    this.g = 2;
                } else {
                    this.g = 3;
                }
            }
            layoutParams2.setMargins(layoutParams.leftMargin, 0, layoutParams.rightMargin, 0);
            layoutParams2.addRule(15, 0);
            layoutParams2.addRule(3, this.e.getId());
            layoutParams2.addRule(9);
            this.f.setLayoutParams(layoutParams2);
            this.f.setTextSize(12.0f);
        }
    }

    private void b() {
        if (this.e != null && this.f != null) {
            this.e.setTextSize(15.0f);
            this.f.setTextSize(15.0f);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.e.getLayoutParams();
            layoutParams.addRule(15);
            layoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.custom_text_item_margin_left), 0, 0, 0);
            this.e.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
            int[] rules = layoutParams2.getRules();
            for (int i = 0; i < rules.length; i++) {
                rules[i] = 0;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.h.size(); i3++) {
                this.h.get(i3).measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                i2 += this.h.get(i3).getMeasuredWidth();
            }
            layoutParams2.setMargins(0, 0, getResources().getDimensionPixelSize(R.dimen.custom_text_item_margin_right) + i2, 0);
            layoutParams2.addRule(15);
            layoutParams2.addRule(9, 0);
            layoutParams2.addRule(11);
            layoutParams2.addRule(3, 0);
            this.f.setLayoutParams(layoutParams2);
        }
    }
}
