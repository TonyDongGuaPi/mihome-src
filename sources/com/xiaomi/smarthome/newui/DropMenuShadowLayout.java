package com.xiaomi.smarthome.newui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.BitmapUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.newui.widget.topnavi.widgets.CardConstraintLayout;

public class DropMenuShadowLayout extends CardConstraintLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20256a = "DropMenuShadowLayout";
    private Drawable b;
    private Rect c;
    private int d;
    private int e;

    public DropMenuShadowLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public DropMenuShadowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DropMenuShadowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new Rect();
        setWillNotDraw(false);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (!(i == i3 && i2 == i4)) {
            float a2 = (float) DisplayUtils.a(14.0f);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
            this.d = a((float) marginLayoutParams.topMargin, (float) marginLayoutParams.leftMargin, a2);
            this.e = a((float) marginLayoutParams.bottomMargin, (float) marginLayoutParams.rightMargin, a2);
        }
        try {
            if (this.b == null) {
                this.b = new BitmapDrawable(getResources(), BitmapUtils.a(getResources(), (int) R.drawable.radius_shadow, -1, -1));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private int a(float... fArr) {
        float f = Float.MAX_VALUE;
        for (float f2 : fArr) {
            if (f2 > 0.0f) {
                f = Math.min(f2, f);
            }
        }
        return (int) f;
    }
}
