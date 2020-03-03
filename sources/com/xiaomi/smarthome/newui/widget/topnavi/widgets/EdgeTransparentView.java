package com.xiaomi.smarthome.newui.widget.topnavi.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.nestscroll.UIUtils;

public class EdgeTransparentView extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private Paint f20947a;
    private int b;
    private float c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int[] j;
    private float[] k;

    public EdgeTransparentView(Context context) {
        this(context, (AttributeSet) null);
    }

    public EdgeTransparentView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EdgeTransparentView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.d = 1;
        this.e = this.d << 1;
        this.f = this.d << 2;
        this.g = this.d << 3;
        this.j = new int[]{-1, 0};
        this.k = new float[]{0.0f, 1.0f};
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.f20947a = new Paint(1);
        this.f20947a.setStyle(Paint.Style.FILL);
        this.f20947a.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.EdgeTransparentView);
        this.b = obtainStyledAttributes.getInt(0, 0);
        this.c = obtainStyledAttributes.getDimension(1, (float) UIUtils.a(20));
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        a();
        this.h = getWidth();
        this.i = getHeight();
    }

    private void a() {
        this.f20947a.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, this.c, this.j, this.k, Shader.TileMode.CLAMP));
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j2) {
        Canvas canvas2 = canvas;
        if (isInEditMode()) {
            return true;
        }
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), (Paint) null, 31);
        boolean drawChild = super.drawChild(canvas, view, j2);
        if (this.b == 0 || (this.b & this.d) != 0) {
            canvas.drawRect(0.0f, 0.0f, (float) this.h, this.c, this.f20947a);
        }
        if (this.b == 0 || (this.b & this.e) != 0) {
            int save = canvas.save();
            canvas.rotate(180.0f, ((float) this.h) / 2.0f, ((float) this.i) / 2.0f);
            canvas.drawRect(0.0f, 0.0f, (float) this.h, this.c, this.f20947a);
            canvas.restoreToCount(save);
        }
        float f2 = ((float) (this.i - this.h)) / 2.0f;
        if (this.b == 0 || (this.b & this.f) != 0) {
            int save2 = canvas.save();
            canvas.rotate(270.0f, ((float) this.h) / 2.0f, ((float) this.i) / 2.0f);
            canvas.translate(0.0f, f2);
            canvas.drawRect(0.0f - f2, 0.0f, ((float) this.h) + f2, this.c, this.f20947a);
            canvas.restoreToCount(save2);
        }
        if (this.b == 0 || (this.b & this.g) != 0) {
            int save3 = canvas.save();
            canvas.rotate(90.0f, ((float) this.h) / 2.0f, ((float) this.i) / 2.0f);
            canvas.translate(0.0f, f2);
            canvas.drawRect(0.0f - f2, 0.0f, ((float) this.h) + f2, this.c, this.f20947a);
            canvas.restoreToCount(save3);
        }
        canvas.restoreToCount(saveLayer);
        return drawChild;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
