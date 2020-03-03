package com.mibi.common.data;

import android.text.InputFilter;
import android.text.Spanned;
import com.mibi.common.data.Formatter;

public class MaxLengthFilter<T extends Formatter> implements InputFilter {

    /* renamed from: a  reason: collision with root package name */
    private int f7529a;
    private T b;

    public MaxLengthFilter(int i, T t) {
        this.f7529a = i;
        this.b = t;
    }

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (this.b.c(charSequence.subSequence(i, i2).toString()) == 0) {
            return null;
        }
        int c = this.f7529a - (this.b.c(spanned.toString()) - this.b.c(spanned.subSequence(i3, i4).toString()));
        if (c <= 0) {
            return "";
        }
        if (c >= i2 - i) {
            return null;
        }
        int i5 = c + i;
        if (!Character.isHighSurrogate(charSequence.charAt(i5 - 1)) || i5 - 1 != i) {
            return charSequence.subSequence(i, i5);
        }
        return "";
    }
}
