package com.mibi.common.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import com.mibi.common.R;
import java.util.HashMap;

public class MiuiDigitFontTextView extends TextView {
    private static HashMap<DigitType, Typeface> sTypefaces = new HashMap<>();

    enum DigitType {
        NORMAL_0("fonts/miui_digit_normal.ttf"),
        BOLD_0("fonts/miui_digit_bold.ttf"),
        LIGHT_0("fonts/miui_digit_light.ttf"),
        NORMAL_1("fonts/miui_normal1.ttf"),
        NORMAL_2("fonts/miui_digit2.ttf");
        
        private String mTTF;

        private DigitType(String str) {
            this.mTTF = str;
        }

        public String getFontFile() {
            return this.mTTF;
        }

        public int toInt() {
            return ordinal();
        }

        public static DigitType fromInt(int i) {
            switch (i) {
                case 0:
                    return NORMAL_0;
                case 1:
                    return BOLD_0;
                case 2:
                    return LIGHT_0;
                case 3:
                    return NORMAL_1;
                case 4:
                    return NORMAL_2;
                default:
                    return NORMAL_0;
            }
        }
    }

    public MiuiDigitFontTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MiuiDigitFontTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Mibi_MiuiDigitFontTextView);
        setTypeface(getMiuiDigitTypeface(context, DigitType.fromInt(obtainStyledAttributes.getInteger(R.styleable.Mibi_MiuiDigitFontTextView_mibi_fontStyle, 0))));
        obtainStyledAttributes.recycle();
        setTextDirection(3);
    }

    public static Typeface getMiuiDigitTypeface(Context context, DigitType digitType) {
        Typeface typeface = sTypefaces.get(digitType);
        if (typeface != null) {
            return typeface;
        }
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), digitType.getFontFile());
        sTypefaces.put(digitType, createFromAsset);
        return createFromAsset;
    }
}
