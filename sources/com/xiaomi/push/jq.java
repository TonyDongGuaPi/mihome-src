package com.xiaomi.push;

import com.xiaomi.push.jg;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class jq extends jg {
    private static int f = 10000;
    private static int g = 10000;
    private static int h = 10000;
    private static int i = 10485760;
    private static int j = 104857600;

    public static class a extends jg.a {
        public a() {
            super(false, true);
        }

        public a(boolean z, boolean z2, int i) {
            super(z, z2, i);
        }

        public jk a(ju juVar) {
            jq jqVar = new jq(juVar, this.f225a, this.b);
            if (this.f12824a != 0) {
                jqVar.c(this.f12824a);
            }
            return jqVar;
        }
    }

    public jq(ju juVar, boolean z, boolean z2) {
        super(juVar, z, z2);
    }

    public jj j() {
        byte q = q();
        byte q2 = q();
        int s = s();
        if (s <= f) {
            return new jj(q, q2, s);
        }
        throw new jl(3, "Thrift map size " + s + " out of range!");
    }

    public ji l() {
        byte q = q();
        int s = s();
        if (s <= g) {
            return new ji(q, s);
        }
        throw new jl(3, "Thrift list size " + s + " out of range!");
    }

    public jo n() {
        byte q = q();
        int s = s();
        if (s <= h) {
            return new jo(q, s);
        }
        throw new jl(3, "Thrift set size " + s + " out of range!");
    }

    public String v() {
        int s = s();
        if (s > i) {
            throw new jl(3, "Thrift string size " + s + " out of range!");
        } else if (this.e.c() < s) {
            return b(s);
        } else {
            try {
                String str = new String(this.e.a(), this.e.b(), s, "UTF-8");
                this.e.a(s);
                return str;
            } catch (UnsupportedEncodingException unused) {
                throw new je("JVM DOES NOT SUPPORT UTF-8");
            }
        }
    }

    public ByteBuffer w() {
        int s = s();
        if (s <= j) {
            d(s);
            if (this.e.c() >= s) {
                ByteBuffer wrap = ByteBuffer.wrap(this.e.a(), this.e.b(), s);
                this.e.a(s);
                return wrap;
            }
            byte[] bArr = new byte[s];
            this.e.d(bArr, 0, s);
            return ByteBuffer.wrap(bArr);
        }
        throw new jl(3, "Thrift binary size " + s + " out of range!");
    }
}
