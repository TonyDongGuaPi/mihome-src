package com.mibi.common.data;

import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;
import com.mibi.common.data.Formatter;

public class PhoneFormatter extends Formatter {

    /* renamed from: a  reason: collision with root package name */
    private static final int f7536a = 3;
    private static final int b = 9;
    private static final char c = ' ';
    private static final int[] d = {3, 7};

    public boolean a(char c2) {
        return c2 == ' ';
    }

    public boolean b(char c2) {
        return Character.isDigit(c2);
    }

    public boolean a(String str) {
        return !TextUtils.isEmpty(str) && str.matches("^1\\d{10}$");
    }

    public void a(EditText editText) {
        editText.addTextChangedListener(new Formatter.FormattingTextWatcher());
    }

    public void a(Editable editable) {
        c(editable);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < editable.length()) {
            if (i2 < d.length && i3 == d[i2]) {
                editable.insert(i, Character.toString(' '));
                i2++;
                i++;
            }
            i++;
            i3++;
        }
    }

    public void b(Editable editable) {
        a(editable, 3, 9);
    }
}
