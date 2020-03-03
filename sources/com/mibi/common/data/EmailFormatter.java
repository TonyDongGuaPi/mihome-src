package com.mibi.common.data;

import android.text.Editable;

public class EmailFormatter extends Formatter {

    /* renamed from: a  reason: collision with root package name */
    private static final int f7518a = 9;

    public void a(Editable editable) {
    }

    public boolean a(char c) {
        return false;
    }

    public boolean a(String str) {
        return true;
    }

    public boolean b(char c) {
        return true;
    }

    private void b(Editable editable, int i, int i2) {
        int i3 = i2 - i;
        int i4 = 2;
        if (i3 == 1) {
            i4 = 0;
            i3 = 1;
        } else if (i3 == 2 || i3 == 3) {
            i4 = 1;
        } else if (!(i3 == 4 || i3 == 5)) {
            i3 = i3 <= 12 ? i3 - 1 : 11;
        }
        a(editable, i4 + i, i + i3);
    }

    public void b(Editable editable) {
        b(editable, 0, editable.toString().indexOf("@"));
    }
}
