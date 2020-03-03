package com.unionpay.mobile.android.widgets;

import android.text.Editable;
import android.text.TextWatcher;

final class ag implements TextWatcher {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ af f9769a;
    private boolean b = true;
    private int c;
    private boolean d = false;

    ag(af afVar) {
        this.f9769a = afVar;
    }

    private String a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = charSequence.charAt(i3);
            if (charAt != ' ') {
                i2++;
                if (i3 != 0 && (i2 & 3) == 1) {
                    stringBuffer.append(' ');
                }
            }
            if (i3 == i) {
                this.c = stringBuffer.length();
            }
            if (charAt != ' ') {
                stringBuffer.append(charAt);
            }
        }
        if (i == length) {
            this.c = stringBuffer.length();
        }
        return stringBuffer.toString();
    }

    public final void afterTextChanged(Editable editable) {
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (i2 == 1 && i3 == 0 && charSequence.charAt(i) == ' ') {
            this.d = true;
        }
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.b) {
            if (this.d) {
                CharSequence subSequence = charSequence.subSequence(0, i - 1);
                if (i < charSequence.length()) {
                    charSequence = subSequence.toString() + charSequence.subSequence(i, charSequence.length());
                } else {
                    charSequence = subSequence;
                }
                i--;
                this.d = false;
            }
            this.b = false;
            this.f9769a.b.c(a(charSequence, i + i3));
            u uVar = this.f9769a.b;
            int i4 = 23;
            if (this.c <= 23) {
                i4 = this.c;
            }
            uVar.b(i4);
            this.b = true;
        }
    }
}
