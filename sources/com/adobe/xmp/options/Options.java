package com.adobe.xmp.options;

import com.adobe.xmp.XMPException;
import java.util.HashMap;
import java.util.Map;

public abstract class Options {

    /* renamed from: a  reason: collision with root package name */
    private int f705a = 0;
    private Map b = null;

    public Options() {
    }

    public Options(int i) throws XMPException {
        h(i);
        f(i);
    }

    private Map a() {
        if (this.b == null) {
            this.b = new HashMap();
        }
        return this.b;
    }

    private void h(int i) throws XMPException {
        int g = (g() ^ -1) & i;
        if (g == 0) {
            g(i);
            return;
        }
        throw new XMPException("The option bit(s) 0x" + Integer.toHexString(g) + " are invalid!", 103);
    }

    private String i(int i) {
        Map a2 = a();
        Integer num = new Integer(i);
        String str = (String) a2.get(num);
        if (str != null) {
            return str;
        }
        String a3 = a(i);
        if (a3 == null) {
            return "<option name not defined>";
        }
        a2.put(num, a3);
        return a3;
    }

    /* access modifiers changed from: protected */
    public abstract String a(int i);

    public void a(int i, boolean z) {
        int i2;
        if (z) {
            i2 = i | this.f705a;
        } else {
            i2 = (i ^ -1) & this.f705a;
        }
        this.f705a = i2;
    }

    public boolean b(int i) {
        return i() == i;
    }

    public boolean c(int i) {
        return (i() & i) == i;
    }

    public boolean d(int i) {
        return (i & i()) != 0;
    }

    /* access modifiers changed from: protected */
    public boolean e(int i) {
        return (i & this.f705a) != 0;
    }

    public boolean equals(Object obj) {
        return i() == ((Options) obj).i();
    }

    public void f(int i) throws XMPException {
        h(i);
        this.f705a = i;
    }

    /* access modifiers changed from: protected */
    public abstract int g();

    /* access modifiers changed from: protected */
    public void g(int i) throws XMPException {
    }

    public void h() {
        this.f705a = 0;
    }

    public int hashCode() {
        return i();
    }

    public int i() {
        return this.f705a;
    }

    public String j() {
        if (this.f705a == 0) {
            return "<none>";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = this.f705a;
        while (i != 0) {
            int i2 = (i - 1) & i;
            stringBuffer.append(i(i ^ i2));
            if (i2 != 0) {
                stringBuffer.append(" | ");
            }
            i = i2;
        }
        return stringBuffer.toString();
    }

    public String toString() {
        return "0x" + Integer.toHexString(this.f705a);
    }
}
