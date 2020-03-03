package com.mibi.common.data;

import android.text.Editable;

public class CommonNumberFormatter extends Formatter {

    /* renamed from: a  reason: collision with root package name */
    private static final char f7508a = ' ';

    public boolean a(char c) {
        return c == ' ';
    }

    public boolean a(String str) {
        return true;
    }

    public boolean b(char c) {
        return Character.isDigit(c);
    }

    public void a(Editable editable) {
        c(editable);
        if (editable.length() > 4) {
            int i = 0;
            int i2 = 0;
            while (i < editable.length()) {
                if (i2 % 4 == 0 && i2 != 0) {
                    editable.insert(i, Character.toString(' '));
                    i++;
                }
                i++;
                i2++;
            }
        }
    }

    public void b(Editable editable) {
        a(editable);
        int i = 0;
        for (int length = editable.length() - 1; length >= 0; length--) {
            if (!a(editable.charAt(length)) && (i = i + 1) > 4) {
                editable.replace(length, length + 1, "*");
            }
        }
    }
}
