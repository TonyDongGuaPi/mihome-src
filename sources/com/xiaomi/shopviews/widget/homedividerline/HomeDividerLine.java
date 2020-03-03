package com.xiaomi.shopviews.widget.homedividerline;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.IHomeItemView;

public class HomeDividerLine extends View implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private String f13251a = "";
    private int b = 0;

    public void draw(HomeSection homeSection) {
    }

    public HomeDividerLine(Context context) {
        super(context);
    }

    public void bind(HomeSection homeSection, int i, int i2) {
        if (homeSection.mBody != null) {
            String str = homeSection.mBody.mLineColor;
            if (TextUtils.isEmpty(str)) {
                setBackgroundColor(0);
            } else if ((str.length() == 7 || str.length() == 9) && !this.f13251a.equals(str)) {
                setBackgroundColor(Color.parseColor(str));
                this.f13251a = str;
            }
            this.b = homeSection.mBody.mLineHeight;
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(i, this.b);
    }
}
