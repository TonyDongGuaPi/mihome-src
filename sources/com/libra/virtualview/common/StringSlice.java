package com.libra.virtualview.common;

import com.libra.Log;
import com.libra.TextUtils;

public class StringSlice implements CharSequence {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6276a = "StringSlice_TMTEST";
    private String b;
    private int c;
    private int d;

    public StringSlice() {
    }

    public StringSlice(String str, int i, int i2) {
        a(str, i, i2);
    }

    public void a(int i, int i2) {
        if (i >= 0 && i2 > 0) {
            this.c = i;
            this.d = i2;
        }
    }

    public void a(String str, int i, int i2) {
        if (!TextUtils.a(str) && i >= 0 && i2 > 0) {
            Log.b(f6276a, "start:" + i + "  len:" + i2);
            this.b = str;
            this.c = i;
            this.d = i2;
        }
    }

    public int length() {
        return this.d;
    }

    public char charAt(int i) {
        return this.b.charAt(this.c + i);
    }

    public CharSequence subSequence(int i, int i2) {
        return new StringSlice(this.b, this.c + i, i2 - i);
    }

    public String toString() {
        return String.format("StringSlice:%s", new Object[]{this.b.substring(this.c, this.c + this.d)});
    }
}
