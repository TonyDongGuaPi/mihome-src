package com.mics.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.drew.metadata.iptc.IptcDirectory;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mics.R;
import com.mics.util.FrescoImageLoader;
import com.mics.widget.CategoryPop;

class CategoryPopView extends RelativeLayout implements View.OnClickListener, CategoryPop.Adapter.IView {

    /* renamed from: a  reason: collision with root package name */
    private CategoryPop.Adapter f7804a;
    private int b;
    private int c;
    private int d = 4;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private Paint j = new Paint();

    public CategoryPopView(Context context) {
        super(context);
        b();
    }

    public CategoryPopView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public CategoryPopView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        b();
    }

    private void b() {
        this.b = (int) TypedValue.applyDimension(1, 47.0f, getResources().getDisplayMetrics());
        float f2 = (((float) getResources().getDisplayMetrics().widthPixels) - (((float) this.b) * 4.0f)) / 516.0f;
        this.e = (int) (61.0f * f2);
        this.f = (int) TypedValue.applyDimension(1, 13.0f, getResources().getDisplayMetrics());
        this.g = (int) TypedValue.applyDimension(1, 15.0f, getResources().getDisplayMetrics());
        int i2 = (int) (f2 * 14.0f);
        setPadding(i2, 0, i2, (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()));
        this.h = (int) TypedValue.applyDimension(1, 8.0f, getResources().getDisplayMetrics());
    }

    public void a(CategoryPop.Adapter adapter) {
        this.f7804a = adapter;
        this.f7804a.a((Object) this);
        this.f7804a.b();
    }

    public void a() {
        this.i = this.f7804a.a();
        this.c = (int) Math.ceil((double) (((float) this.i) / 4.0f));
        c();
        d();
    }

    private void c() {
        removeAllViews();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < this.c) {
            int i5 = i4;
            int i6 = 0;
            int i7 = 0;
            while (i6 < this.d) {
                int i8 = i6 + 1;
                if ((this.d * i3) + i8 <= this.i) {
                    SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
                    int i9 = i3 * 10;
                    int i10 = i9 + IptcDirectory.au;
                    simpleDraweeView.setId(i10 + i6);
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.b + (this.e * 2), this.b);
                    layoutParams.setMargins(i2, i2, i2, this.f);
                    TextView textView = new TextView(getContext());
                    textView.setPadding(this.h, i2, this.h, this.g);
                    textView.setId(i9 + 111 + i6);
                    textView.setGravity(17);
                    textView.setTextSize(1, 11.0f);
                    textView.setTextColor(Color.parseColor("#333333"));
                    RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams2.addRule(3, simpleDraweeView.getId());
                    layoutParams2.addRule(7, simpleDraweeView.getId());
                    layoutParams2.addRule(5, simpleDraweeView.getId());
                    if (i3 > 0 && i6 == 0) {
                        layoutParams.addRule(3, i5);
                    }
                    if (i6 > 0) {
                        int i11 = i10 + (i6 - 1);
                        layoutParams.addRule(1, i11);
                        layoutParams.addRule(6, i11);
                    }
                    String b2 = this.f7804a.b(i6 + (this.d * i3));
                    float applyDimension = TypedValue.applyDimension(1, 11.0f, getResources().getDisplayMetrics());
                    if (!TextUtils.isEmpty(b2)) {
                        this.j.setTextSize(applyDimension);
                        int measureText = (int) this.j.measureText(b2);
                        if (measureText > i7) {
                            i5 = textView.getId();
                        }
                        i7 = Math.max(i7, measureText);
                    }
                    addView(simpleDraweeView, layoutParams);
                    addView(textView, layoutParams2);
                    i6 = i8;
                    i2 = 0;
                } else {
                    return;
                }
            }
            i3++;
            i4 = i5;
            i2 = 0;
        }
    }

    private void d() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int i3 = i2 / 2;
            childAt.setTag(R.id.mics_position, Integer.valueOf(i3));
            childAt.setOnClickListener(this);
            if (childAt instanceof SimpleDraweeView) {
                FrescoImageLoader.Builder builder = new FrescoImageLoader.Builder();
                builder.a(ScalingUtils.ScaleType.FIT_CENTER);
                builder.a((DraweeView<GenericDraweeHierarchy>) (SimpleDraweeView) childAt);
                builder.a(this.f7804a.a(i3));
                builder.a().a();
            } else if (childAt instanceof TextView) {
                ((TextView) childAt).setText(this.f7804a.b(i3));
            }
        }
    }

    public void onClick(View view) {
        this.f7804a.a(((Integer) view.getTag(R.id.mics_position)).intValue(), view);
    }
}
