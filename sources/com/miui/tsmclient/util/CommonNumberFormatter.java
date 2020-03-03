package com.miui.tsmclient.util;

import android.text.Editable;

public class CommonNumberFormatter extends Formatter {
    public void format(Editable editable) {
        clean(editable);
        if (editable.length() > 4) {
            int i = 0;
            int i2 = 0;
            while (i < editable.length()) {
                if (i2 % 4 == 0 && i2 != 0) {
                    editable.insert(i, Character.toString(this.SEPARATOR));
                    i++;
                }
                i++;
                i2++;
            }
        }
    }
}
