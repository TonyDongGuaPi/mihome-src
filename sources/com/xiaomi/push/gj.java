package com.xiaomi.push;

import android.os.Bundle;
import com.coloros.mcssdk.mode.CommandMessage;
import java.util.HashMap;
import java.util.Map;

public class gj extends gl {
    private a c = a.f12751a;
    private final Map<String, String> d = new HashMap();

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final a f12751a = new a("get");
        public static final a b = new a("set");
        public static final a c = new a("result");
        public static final a d = new a("error");
        public static final a e = new a(CommandMessage.COMMAND);
        private String f;

        private a(String str) {
            this.f = str;
        }

        public static a a(String str) {
            if (str == null) {
                return null;
            }
            String lowerCase = str.toLowerCase();
            if (f12751a.toString().equals(lowerCase)) {
                return f12751a;
            }
            if (b.toString().equals(lowerCase)) {
                return b;
            }
            if (d.toString().equals(lowerCase)) {
                return d;
            }
            if (c.toString().equals(lowerCase)) {
                return c;
            }
            if (e.toString().equals(lowerCase)) {
                return e;
            }
            return null;
        }

        public String toString() {
            return this.f;
        }
    }

    public gj() {
    }

    public gj(Bundle bundle) {
        super(bundle);
        if (bundle.containsKey("ext_iq_type")) {
            this.c = a.a(bundle.getString("ext_iq_type"));
        }
    }

    public a a() {
        return this.c;
    }

    public void a(a aVar) {
        if (aVar == null) {
            aVar = a.f12751a;
        }
        this.c = aVar;
    }

    public synchronized void a(Map<String, String> map) {
        this.d.putAll(map);
    }

    public Bundle b() {
        Bundle b = super.b();
        if (this.c != null) {
            b.putString("ext_iq_type", this.c.toString());
        }
        return b;
    }

    public String c() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("<iq ");
        if (k() != null) {
            sb.append("id=\"" + k() + "\" ");
        }
        if (m() != null) {
            sb.append("to=\"");
            sb.append(gw.a(m()));
            sb.append("\" ");
        }
        if (n() != null) {
            sb.append("from=\"");
            sb.append(gw.a(n()));
            sb.append("\" ");
        }
        if (l() != null) {
            sb.append("chid=\"");
            sb.append(gw.a(l()));
            sb.append("\" ");
        }
        for (Map.Entry next : this.d.entrySet()) {
            sb.append(gw.a((String) next.getKey()));
            sb.append("=\"");
            sb.append(gw.a((String) next.getValue()));
            sb.append("\" ");
        }
        if (this.c == null) {
            str = "type=\"get\">";
        } else {
            sb.append("type=\"");
            sb.append(a());
            str = "\">";
        }
        sb.append(str);
        String d2 = d();
        if (d2 != null) {
            sb.append(d2);
        }
        sb.append(s());
        gp p = p();
        if (p != null) {
            sb.append(p.b());
        }
        sb.append("</iq>");
        return sb.toString();
    }

    public String d() {
        return null;
    }
}
