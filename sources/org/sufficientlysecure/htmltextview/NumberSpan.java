package org.sufficientlysecure.htmltextview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.LeadingMarginSpan;

public class NumberSpan implements LeadingMarginSpan {

    /* renamed from: a  reason: collision with root package name */
    private final String f4192a;
    private final int b;

    public NumberSpan(TextPaint textPaint, int i) {
        this.f4192a = Integer.toString(i).concat(". ");
        this.b = (int) textPaint.measureText(this.f4192a);
    }

    public int getLeadingMargin(boolean z) {
        return this.b;
    }

    public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
        if ((charSequence instanceof Spanned) && ((Spanned) charSequence).getSpanStart(this) == i6) {
            canvas.drawText(this.f4192a, (float) i, (float) i4, paint);
        }
    }
}
