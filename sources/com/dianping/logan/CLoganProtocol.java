package com.dianping.logan;

import com.dianping.logan.ConstantCode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

class CLoganProtocol implements LoganProtocolHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5159a = "logan";
    private static CLoganProtocol b;
    private static boolean c;
    private boolean d;
    private boolean e;
    private DateFormat f = new SimpleDateFormat("HH:mm:ss:SSS");
    private OnLoganProtocolStatus g;
    private Set<Integer> h = Collections.synchronizedSet(new HashSet());

    private native void clogan_debug(boolean z);

    private native void clogan_flush();

    private native int clogan_init(String str, String str2, int i, String str3, String str4);

    private native int clogan_open(String str);

    private native int clogan_write(int i, String str, long j, String str2, long j2, int i2);

    CLoganProtocol() {
    }

    static {
        try {
            if (!Util.a(f5159a, CLoganProtocol.class)) {
                System.loadLibrary(f5159a);
            }
            c = true;
        } catch (Throwable th) {
            th.printStackTrace();
            c = false;
        }
    }

    static boolean a() {
        return c;
    }

    static CLoganProtocol b() {
        if (b == null) {
            synchronized (CLoganProtocol.class) {
                if (b == null) {
                    b = new CLoganProtocol();
                }
            }
        }
        return b;
    }

    public void a(String str, String str2, int i, String str3, String str4) {
        if (!this.d) {
            if (!c) {
                a(ConstantCode.CloganStatus.w, ConstantCode.CloganStatus.x);
                return;
            }
            try {
                int clogan_init = clogan_init(str, str2, i, str3, str4);
                this.d = true;
                a(ConstantCode.CloganStatus.f5160a, clogan_init);
            } catch (UnsatisfiedLinkError e2) {
                e2.printStackTrace();
                a(ConstantCode.CloganStatus.f5160a, ConstantCode.CloganStatus.g);
            }
        }
    }

    public void a(boolean z) {
        if (this.d && c) {
            try {
                clogan_debug(z);
            } catch (UnsatisfiedLinkError e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(OnLoganProtocolStatus onLoganProtocolStatus) {
        this.g = onLoganProtocolStatus;
    }

    public void a(String str) {
        if (this.d && c) {
            try {
                int clogan_open = clogan_open(str);
                this.e = true;
                a(ConstantCode.CloganStatus.h, clogan_open);
            } catch (UnsatisfiedLinkError e2) {
                e2.printStackTrace();
                a(ConstantCode.CloganStatus.h, ConstantCode.CloganStatus.o);
            }
        }
    }

    public void c() {
        if (this.e && c) {
            try {
                clogan_flush();
            } catch (UnsatisfiedLinkError e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(int i, String str, long j, String str2, long j2, boolean z) {
        if (this.e && c) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(" ");
                long j3 = j;
                sb.append(this.f.format(new Date(j)));
                sb.append(" ");
                String str3 = str;
                sb.append(str);
                sb.append("\n");
                int clogan_write = clogan_write(i, sb.toString(), j, str2, j2, z ? 1 : 0);
                if (clogan_write != -4010 || Logan.f5161a) {
                    a(ConstantCode.CloganStatus.p, clogan_write);
                }
            } catch (UnsatisfiedLinkError e2) {
                e2.printStackTrace();
                a(ConstantCode.CloganStatus.p, ConstantCode.CloganStatus.v);
            }
        }
    }

    private void a(String str, int i) {
        if (i < 0) {
            if (ConstantCode.CloganStatus.p.endsWith(str) && i != -4060) {
                if (!this.h.contains(Integer.valueOf(i))) {
                    this.h.add(Integer.valueOf(i));
                } else {
                    return;
                }
            }
            if (this.g != null) {
                this.g.a(str, i);
            }
        }
    }
}
