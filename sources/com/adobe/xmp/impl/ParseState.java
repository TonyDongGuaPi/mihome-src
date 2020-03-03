package com.adobe.xmp.impl;

import com.adobe.xmp.XMPException;

class ParseState {

    /* renamed from: a  reason: collision with root package name */
    private String f686a;
    private int b = 0;

    public ParseState(String str) {
        this.f686a = str;
    }

    public char a(int i) {
        if (i < this.f686a.length()) {
            return this.f686a.charAt(i);
        }
        return 0;
    }

    public int a() {
        return this.f686a.length();
    }

    public int a(String str, int i) throws XMPException {
        char a2 = a(this.b);
        boolean z = false;
        int i2 = 0;
        while ('0' <= a2 && a2 <= '9') {
            i2 = (i2 * 10) + (a2 - '0');
            this.b++;
            a2 = a(this.b);
            z = true;
        }
        if (!z) {
            throw new XMPException(str, 5);
        } else if (i2 > i) {
            return i;
        } else {
            if (i2 < 0) {
                return 0;
            }
            return i2;
        }
    }

    public boolean b() {
        return this.b < this.f686a.length();
    }

    public char c() {
        if (this.b < this.f686a.length()) {
            return this.f686a.charAt(this.b);
        }
        return 0;
    }

    public void d() {
        this.b++;
    }

    public int e() {
        return this.b;
    }
}
