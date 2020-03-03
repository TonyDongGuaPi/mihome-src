package javax.jmdns.impl;

import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class DNSMessage {
    public static final boolean b = true;
    public static final boolean c = false;

    /* renamed from: a  reason: collision with root package name */
    private int f2632a;
    boolean d;
    protected final List<DNSQuestion> e = Collections.synchronizedList(new LinkedList());
    protected final List<DNSRecord> f = Collections.synchronizedList(new LinkedList());
    protected final List<DNSRecord> g = Collections.synchronizedList(new LinkedList());
    protected final List<DNSRecord> h = Collections.synchronizedList(new LinkedList());
    private int i;

    protected DNSMessage(int i2, int i3, boolean z) {
        this.i = i2;
        this.f2632a = i3;
        this.d = z;
    }

    public int d() {
        if (this.d) {
            return 0;
        }
        return this.f2632a;
    }

    public void a(int i2) {
        this.f2632a = i2;
    }

    public int e() {
        return this.i;
    }

    public void b(int i2) {
        this.i = i2;
    }

    public boolean f() {
        return this.d;
    }

    public Collection<? extends DNSQuestion> g() {
        return this.e;
    }

    public int h() {
        return g().size();
    }

    public Collection<? extends DNSRecord> i() {
        ArrayList arrayList = new ArrayList(this.f.size() + this.g.size() + this.h.size());
        arrayList.addAll(this.f);
        arrayList.addAll(this.g);
        arrayList.addAll(this.h);
        return arrayList;
    }

    public Collection<? extends DNSRecord> j() {
        return this.f;
    }

    public int k() {
        return j().size();
    }

    public Collection<? extends DNSRecord> l() {
        return this.g;
    }

    public int m() {
        return l().size();
    }

    public Collection<? extends DNSRecord> n() {
        return this.h;
    }

    public int o() {
        return n().size();
    }

    public boolean p() {
        return (this.i & 512) != 0;
    }

    public boolean q() {
        return (this.i & 32768) == 0;
    }

    public boolean r() {
        return (this.i & 32768) == 32768;
    }

    public boolean s() {
        return ((h() + k()) + m()) + o() == 0;
    }

    /* access modifiers changed from: package-private */
    public String t() {
        StringBuilder sb = new StringBuilder(200);
        sb.append(toString());
        sb.append("\n");
        for (DNSQuestion append : this.e) {
            sb.append("\tquestion:      ");
            sb.append(append);
            sb.append("\n");
        }
        for (DNSRecord append2 : this.f) {
            sb.append("\tanswer:        ");
            sb.append(append2);
            sb.append("\n");
        }
        for (DNSRecord append3 : this.g) {
            sb.append("\tauthoritative: ");
            sb.append(append3);
            sb.append("\n");
        }
        for (DNSRecord append4 : this.h) {
            sb.append("\tadditional:    ");
            sb.append(append4);
            sb.append("\n");
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(4000);
        int length = bArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            int min = Math.min(32, length - i2);
            if (i2 < 16) {
                sb.append(' ');
            }
            if (i2 < 256) {
                sb.append(' ');
            }
            if (i2 < 4096) {
                sb.append(' ');
            }
            sb.append(Integer.toHexString(i2));
            sb.append(Operators.CONDITION_IF_MIDDLE);
            int i3 = 0;
            while (i3 < min) {
                if (i3 % 8 == 0) {
                    sb.append(' ');
                }
                int i4 = i2 + i3;
                sb.append(Integer.toHexString((bArr[i4] & 240) >> 4));
                sb.append(Integer.toHexString((bArr[i4] & 15) >> 0));
                i3++;
            }
            if (i3 < 32) {
                while (i3 < 32) {
                    if (i3 % 8 == 0) {
                        sb.append(' ');
                    }
                    sb.append("  ");
                    i3++;
                }
            }
            sb.append("    ");
            for (int i5 = 0; i5 < min; i5++) {
                if (i5 % 8 == 0) {
                    sb.append(' ');
                }
                byte b2 = bArr[i2 + i5] & 255;
                sb.append((b2 <= 32 || b2 >= Byte.MAX_VALUE) ? '.' : (char) b2);
            }
            sb.append("\n");
            i2 += 32;
            if (i2 >= 2048) {
                sb.append("....\n");
                break;
            }
        }
        return sb.toString();
    }
}
