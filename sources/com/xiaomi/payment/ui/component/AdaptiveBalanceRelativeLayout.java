package com.xiaomi.payment.ui.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.platform.R;

public class AdaptiveBalanceRelativeLayout extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private static float f12447a = 1.5f;
    private static float b = 1.5f;
    private static int c = 15;
    private TextView d;
    private TextView e;
    private TextView f;
    private float g;
    private float h;
    private TextPaint i;
    private TextPaint j;
    private TextPaint k;
    private GravityStyle l = GravityStyle.WHOLE_CENTER;

    enum GravityStyle {
        WHOLE_CENTER,
        WHOLE_LEFT,
        INTEGER_CENTER;

        public static GravityStyle fromInt(int i) {
            switch (i) {
                case 0:
                    return WHOLE_CENTER;
                case 1:
                    return INTEGER_CENTER;
                case 2:
                    return WHOLE_LEFT;
                default:
                    return WHOLE_CENTER;
            }
        }
    }

    public AdaptiveBalanceRelativeLayout(Context context) {
        super(context);
    }

    public AdaptiveBalanceRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Mibi_AdaptiveBalanceRelativeLayout);
        this.l = GravityStyle.fromInt(obtainStyledAttributes.getInteger(R.styleable.Mibi_AdaptiveBalanceRelativeLayout_mibi_gravityStyle, 0));
        if (this.l == GravityStyle.INTEGER_CENTER) {
            b = 0.5f;
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.d = (TextView) findViewById(R.id.integer_part);
        this.e = (TextView) findViewById(R.id.fractional_part);
        this.f = (TextView) findViewById(R.id.unit);
        this.g = this.d.getTextSize();
        this.h = this.e.getTextSize();
        this.i = this.d.getPaint();
        this.j = this.e.getPaint();
        this.k = this.f.getPaint();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        String charSequence = this.d.getText().toString();
        String charSequence2 = this.e.getText().toString();
        String charSequence3 = this.f.getText().toString();
        float measureText = this.i.measureText(charSequence);
        float measureText2 = this.j.measureText(charSequence2);
        float measureText3 = this.k.measureText(charSequence3);
        float f2 = this.g;
        float f3 = this.h;
        while (measureText + measureText2 + measureText3 + ((float) c) > ((float) size) && f2 > 0.0f && f3 > 0.0f) {
            f2 -= f12447a;
            this.i.setTextSize(f2);
            f3 -= b;
            this.j.setTextSize(f3);
            measureText = this.i.measureText(charSequence);
            measureText2 = this.j.measureText(charSequence2);
        }
        Paint.FontMetrics fontMetrics = this.i.getFontMetrics();
        Paint.FontMetrics fontMetrics2 = this.j.getFontMetrics();
        this.d.setTextSize(0, f2);
        this.e.setTextSize(0, f3);
        this.g = f2;
        this.h = f3;
        measureChild(this.d, View.MeasureSpec.makeMeasureSpec((int) measureText, 1073741824), View.MeasureSpec.makeMeasureSpec((int) (Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent)) + 2.0d), 1073741824));
        measureChild(this.e, View.MeasureSpec.makeMeasureSpec((int) measureText2, 1073741824), View.MeasureSpec.makeMeasureSpec((int) (Math.ceil((double) (fontMetrics2.descent - fontMetrics2.ascent)) + 2.0d), 1073741824));
        super.onMeasure(i2, i3);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        super.onLayout(z, i2, i3, i4, i5);
        int height = this.d.getHeight();
        Paint.FontMetrics fontMetrics = this.i.getFontMetrics();
        int ceil = (int) (Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent)) + 2.0d);
        int height2 = this.e.getHeight();
        Paint.FontMetrics fontMetrics2 = this.j.getFontMetrics();
        int ceil2 = (int) (Math.ceil((double) (fontMetrics2.descent - fontMetrics2.ascent)) + 2.0d);
        int i7 = i4 - i2;
        int width = this.d.getWidth();
        int width2 = this.e.getWidth();
        int width3 = this.f.getWidth();
        switch (this.l) {
            case WHOLE_CENTER:
                i6 = (((i7 - width) - width2) - width3) / 2;
                break;
            case INTEGER_CENTER:
                i6 = (i7 - width) / 2;
                break;
            case WHOLE_LEFT:
                i6 = 0;
                break;
            default:
                i6 = (((i7 - width) - width2) - width3) / 2;
                break;
        }
        this.d.layout(i6, this.d.getTop(), this.d.getWidth() + i6, this.d.getBottom());
        int left = this.d.getLeft() + this.d.getWidth();
        this.e.layout(left, this.e.getTop(), this.e.getWidth() + left, this.e.getBottom());
        int width4 = left + this.e.getWidth();
        this.f.layout(width4, this.f.getTop(), this.f.getWidth() + width4, this.f.getBottom());
        if (this.l == GravityStyle.INTEGER_CENTER && height > ceil && height2 > ceil2) {
            int i8 = (height - ceil) - (height2 - ceil2);
            this.e.layout(this.e.getLeft(), i8 + 10, this.e.getRight(), i8 + this.e.getHeight());
        }
    }

    public void setBalance(long j2) {
        String str;
        Utils.ValueDivided c2 = Utils.c(j2);
        String str2 = c2.f7550a;
        if (!TextUtils.isEmpty(c2.b)) {
            str = "." + c2.b;
        } else {
            str = null;
        }
        this.d.setText(str2);
        this.e.setText(str);
    }

    public void setUnit(String str) {
        this.f.setText(str);
    }

    public void setColor(int i2) {
        this.d.setTextColor(i2);
        this.e.setTextColor(i2);
        this.f.setTextColor(i2);
    }
}
