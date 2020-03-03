package com.xiaomi.smarthome.newui.widget.topnavi.widgets;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;

public class ExpandRoomMenu extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private Path f20948a;
    private RectF b;
    private Paint c;
    private float d;

    public ExpandRoomMenu(Context context) {
        this(context, (AttributeSet) null);
    }

    public ExpandRoomMenu(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExpandRoomMenu(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f20948a = new Path();
        this.b = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.c = new Paint(1);
        this.c.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        this.d = (float) DisplayUtils.b(context, 10.0f);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.b.set(0.0f, 0.0f, (float) i, (float) i2);
    }
}
