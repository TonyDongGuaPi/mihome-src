package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;

public class FixedTextView extends TextView {
    private static final String ONE_CHAR = "字";
    private static final String TAG = "FixedTextView";
    private int mOneCharWidth;
    private float mTextSize;
    private int mXmlWidth;

    public FixedTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FixedTextView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FixedTextView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTextSize = getTextSize();
        this.mOneCharWidth = measureOneCharWidth();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FixedTextView, i, 0);
        try {
            int b = getResources().getDisplayMetrics().widthPixels - DisplayUtils.b(context, 72.0f);
            this.mXmlWidth = obtainStyledAttributes.getLayoutDimension(0, -1);
            if (this.mXmlWidth == -1) {
                this.mXmlWidth = b;
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
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

    public void setTextAutoCutOff(CharSequence charSequence, String str) {
        if (!TextUtils.isEmpty(charSequence)) {
            setText(getCutOffTextIfNeed(charSequence.toString(), str));
        }
    }

    private String getCutOffTextIfNeed(String str, String str2) {
        int i = this.mXmlWidth;
        Paint paint = new Paint();
        paint.setTextSize(this.mTextSize);
        int measureText = (int) (((float) i) + paint.measureText(""));
        if (str == null) {
            return "";
        }
        char[] charArray = str.toCharArray();
        float f = 0.0f;
        for (int i2 = 0; i2 < charArray.length; i2++) {
            float measureText2 = paint.measureText(charArray, i2, 1);
            if (((float) measureText) - f < measureText2) {
                String substring = str.substring(0, i2);
                if (str2 != null && substring.contains(str2)) {
                    substring = substring.substring(0, substring.lastIndexOf(str2));
                }
                return substring + "...";
            }
            f += measureText2;
        }
        return str;
    }
}
