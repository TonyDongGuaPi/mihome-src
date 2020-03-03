package com.miui.tsmclient.util;

import android.text.Editable;

public class PhoneFormatter extends Formatter {
    private static int[] PHONE_NUM_SEP = {3, 7};

    public void format(Editable editable) {
        clean(editable);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < editable.length()) {
            if (i2 < PHONE_NUM_SEP.length && i3 == PHONE_NUM_SEP[i2]) {
                editable.insert(i, Character.toString(this.SEPARATOR));
                i2++;
                i++;
            }
            i++;
            i3++;
        }
    }
}
