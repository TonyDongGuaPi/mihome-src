package com.xiaomi.jr.mipay.codepay.component;

import android.content.Context;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

public class SmsCaptchaEditText extends EditText {
    private static final int SMS_CAPTCHA_MAX_LENGTH = 20;

    public SmsCaptchaEditText(Context context) {
        this(context, (AttributeSet) null);
    }

    public SmsCaptchaEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setFilters(new InputFilter[]{$$Lambda$SmsCaptchaEditText$fabpq7334BwRjOcZKjJ7Stm32Q.INSTANCE, new InputFilter.LengthFilter(6)});
    }

    static /* synthetic */ CharSequence lambda$new$0(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        String spannableStringBuilder = new SpannableStringBuilder(spanned).replace(i3, i4, charSequence, i, i2).toString();
        if (TextUtils.isEmpty(spannableStringBuilder)) {
            return null;
        }
        if (spannableStringBuilder.length() > 20) {
            return "";
        }
        CharSequence subSequence = charSequence.subSequence(i, i2);
        for (int i5 = 0; i5 < subSequence.length(); i5++) {
            if (!Character.isDigit(subSequence.charAt(i5))) {
                return "";
            }
        }
        return null;
    }

    public String getSmsCaptcha() {
        String obj = getText().toString();
        if (TextUtils.isEmpty(obj)) {
            return null;
        }
        for (int i = 0; i < obj.length(); i++) {
            if (!Character.isDigit(obj.charAt(i))) {
                return null;
            }
        }
        return obj;
    }
}
