package com.miui.tsmclient.util;

import android.text.Editable;

public class IdCardFormatter extends Formatter {
    private static int[] ID_CARD_SEP = {6, 10, 14};

    public boolean isValidCharacter(char c) {
        return Character.isDigit(c) || 'x' == Character.toLowerCase(c);
    }

    public void format(Editable editable) {
        clean(editable);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < editable.length()) {
            if (i2 < ID_CARD_SEP.length && i3 == ID_CARD_SEP[i2]) {
                editable.insert(i, Character.toString(this.SEPARATOR));
                i2++;
                i++;
            }
            i++;
            i3++;
        }
    }
}
