package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RadioButton;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class DrawableRadioButton extends RadioButton {
    private static final String ONE_CHAR = "字";
    private int mOneCharWidth = measureOneCharWidth();
    private float mTextSize = getTextSize();

    public DrawableRadioButton(Context context) {
        super(context);
    }

    public DrawableRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DrawableRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Drawable drawable;
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (!(compoundDrawables == null || (drawable = compoundDrawables[0]) == null)) {
            float measureText = getPaint().measureText(getText().toString());
            int compoundDrawablePadding = getCompoundDrawablePadding();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            if (intrinsicWidth != DisplayUtils.a(38.0f)) {
                intrinsicWidth = DisplayUtils.a(38.0f);
            }
            canvas.translate((((float) getWidth()) - ((measureText + ((float) intrinsicWidth)) + ((float) compoundDrawablePadding))) / 2.0f, 0.0f);
        }
        super.onDraw(canvas);
    }

    private int measureOneCharWidth() {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(this.mTextSize);
        return (int) textPaint.measureText(ONE_CHAR);
    }

    public void setText(CharSequence charSequence, int i) {
        if (!TextUtils.isEmpty(charSequence)) {
            setText(handleStartListTextView(charSequence.toString(), i));
        }
    }

    private String handleStartListTextView(String str, int i) {
        int i2 = this.mOneCharWidth * i;
        Paint paint = new Paint();
        paint.setTextSize(this.mTextSize);
        int measureText = (int) (((float) i2) + paint.measureText(""));
        if (str == null) {
            return "";
        }
        char[] charArray = str.toCharArray();
        float f = 0.0f;
        for (int i3 = 0; i3 < charArray.length; i3++) {
            float measureText2 = paint.measureText(charArray, i3, 1);
            if (((float) measureText) - f < measureText2) {
                return str.subSequence(0, i3) + "";
            }
            f += measureText2;
        }
        return str;
    }
}
