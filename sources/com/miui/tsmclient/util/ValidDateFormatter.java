package com.miui.tsmclient.util;

import android.text.Editable;
import android.text.TextUtils;

public class ValidDateFormatter extends Formatter {
    private static final char SEPARATOR = '/';

    public void cover(Editable editable) {
    }

    public boolean isSeparator(char c) {
        return c == '/';
    }

    public void clean(Editable editable) {
        int i = 0;
        while (i < editable.length()) {
            if (!isValidCharacter(editable.charAt(i))) {
                editable.delete(i, i + 1);
            } else if (i != 0 && editable.charAt(i) == '0' && editable.charAt(i - 1) == '0') {
                editable.delete(i, i + 1);
            } else {
                i++;
            }
        }
    }

    public void format(Editable editable) {
        StringBuilder sb = new StringBuilder(clean(editable.toString()));
        if (sb.length() > 4) {
            sb.delete(4, sb.length());
        }
        int i = 0;
        while (true) {
            if (i >= sb.length()) {
                break;
            }
            char charAt = sb.charAt(i);
            boolean z = true;
            if ((i != 0 || charAt <= '1' || charAt > '9') && (i != 1 || charAt <= '2' || sb.charAt(0) <= '0')) {
                z = false;
            }
            if (z) {
                sb.insert(0, "0");
                break;
            }
            i++;
        }
        if (sb.length() > 4) {
            sb.delete(4, sb.length());
        }
        if ((editable.length() != 3 && sb.length() == 2) || (sb.length() > 2 && sb.charAt(2) != '/')) {
            sb.insert(2, "/");
        }
        if (!TextUtils.equals(editable, sb)) {
            editable.replace(0, editable.length(), sb);
        }
    }
}
