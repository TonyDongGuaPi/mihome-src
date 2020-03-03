package com.xiaomi.push;

import android.os.Bundle;

public class gn extends gl {
    private b c = b.f12754a;
    private String d = null;
    private int e = Integer.MIN_VALUE;
    private a f = null;

    public enum a {
        chat,
        b,
        away,
        xa,
        dnd
    }

    public enum b {
        f12754a,
        unavailable,
        subscribe,
        subscribed,
        unsubscribe,
        unsubscribed,
        error,
        probe
    }

    public gn(Bundle bundle) {
        super(bundle);
        if (bundle.containsKey("ext_pres_type")) {
            this.c = b.valueOf(bundle.getString("ext_pres_type"));
        }
        if (bundle.containsKey("ext_pres_status")) {
            this.d = bundle.getString("ext_pres_status");
        }
        if (bundle.containsKey("ext_pres_prio")) {
            this.e = bundle.getInt("ext_pres_prio");
        }
        if (bundle.containsKey("ext_pres_mode")) {
            this.f = a.valueOf(bundle.getString("ext_pres_mode"));
        }
    }

    public gn(b bVar) {
        a(bVar);
    }

    public void a(int i) {
        if (i < -128 || i > 128) {
            throw new IllegalArgumentException("Priority value " + i + " is not valid. Valid range is -128 through 128.");
        }
        this.e = i;
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    public void a(b bVar) {
        if (bVar != null) {
            this.c = bVar;
            return;
        }
        throw new NullPointerException("Type cannot be null");
    }

    public void a(String str) {
        this.d = str;
    }

    public Bundle b() {
        Bundle b2 = super.b();
        if (this.c != null) {
            b2.putString("ext_pres_type", this.c.toString());
        }
        if (this.d != null) {
            b2.putString("ext_pres_status", this.d);
        }
        if (this.e != Integer.MIN_VALUE) {
            b2.putInt("ext_pres_prio", this.e);
        }
        if (!(this.f == null || this.f == a.b)) {
            b2.putString("ext_pres_mode", this.f.toString());
        }
        return b2;
    }

    public String c() {
        StringBuilder sb = new StringBuilder();
        sb.append("<presence");
        if (t() != null) {
            sb.append(" xmlns=\"");
            sb.append(t());
            sb.append("\"");
        }
        if (k() != null) {
            sb.append(" id=\"");
            sb.append(k());
            sb.append("\"");
        }
        if (m() != null) {
            sb.append(" to=\"");
            sb.append(gw.a(m()));
            sb.append("\"");
        }
        if (n() != null) {
            sb.append(" from=\"");
            sb.append(gw.a(n()));
            sb.append("\"");
        }
        if (l() != null) {
            sb.append(" chid=\"");
            sb.append(gw.a(l()));
            sb.append("\"");
        }
        if (this.c != null) {
            sb.append(" type=\"");
            sb.append(this.c);
            sb.append("\"");
        }
        sb.append(">");
        if (this.d != null) {
            sb.append("<status>");
            sb.append(gw.a(this.d));
            sb.append("</status>");
        }
        if (this.e != Integer.MIN_VALUE) {
            sb.append("<priority>");
            sb.append(this.e);
            sb.append("</priority>");
        }
        if (!(this.f == null || this.f == a.b)) {
            sb.append("<show>");
            sb.append(this.f);
            sb.append("</show>");
        }
        sb.append(s());
        gp p = p();
        if (p != null) {
            sb.append(p.b());
        }
        sb.append("</presence>");
        return sb.toString();
    }
}
