package org.mp4parser.boxes.samplegrouping;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import org.mp4parser.tools.IsoTypeWriter;

public class RateShareEntry extends GroupEntry {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3957a = "rash";
    private short b;
    private short c;
    private List<Entry> d = new LinkedList();
    private int e;
    private int f;
    private short g;

    public String a() {
        return f3957a;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [int] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.nio.ByteBuffer r6) {
        /*
            r5 = this;
            short r0 = r6.getShort()
            r5.b = r0
            short r0 = r5.b
            r1 = 1
            if (r0 != r1) goto L_0x0012
            short r0 = r6.getShort()
            r5.c = r0
            goto L_0x0018
        L_0x0012:
            short r0 = r5.b
        L_0x0014:
            int r1 = r0 + -1
            if (r0 > 0) goto L_0x0034
        L_0x0018:
            long r0 = org.mp4parser.tools.IsoTypeReader.b(r6)
            int r0 = org.mp4parser.tools.CastUtils.a(r0)
            r5.e = r0
            long r0 = org.mp4parser.tools.IsoTypeReader.b(r6)
            int r0 = org.mp4parser.tools.CastUtils.a(r0)
            r5.f = r0
            int r6 = org.mp4parser.tools.IsoTypeReader.f(r6)
            short r6 = (short) r6
            r5.g = r6
            return
        L_0x0034:
            java.util.List<org.mp4parser.boxes.samplegrouping.RateShareEntry$Entry> r0 = r5.d
            org.mp4parser.boxes.samplegrouping.RateShareEntry$Entry r2 = new org.mp4parser.boxes.samplegrouping.RateShareEntry$Entry
            long r3 = org.mp4parser.tools.IsoTypeReader.b(r6)
            int r3 = org.mp4parser.tools.CastUtils.a(r3)
            short r4 = r6.getShort()
            r2.<init>(r3, r4)
            r0.add(r2)
            r0 = r1
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mp4parser.boxes.samplegrouping.RateShareEntry.a(java.nio.ByteBuffer):void");
    }

    public ByteBuffer b() {
        ByteBuffer allocate = ByteBuffer.allocate(this.b == 1 ? 13 : (this.b * 6) + 11);
        allocate.putShort(this.b);
        if (this.b == 1) {
            allocate.putShort(this.c);
        } else {
            for (Entry next : this.d) {
                allocate.putInt(next.a());
                allocate.putShort(next.b());
            }
        }
        allocate.putInt(this.e);
        allocate.putInt(this.f);
        IsoTypeWriter.d(allocate, (int) this.g);
        allocate.rewind();
        return allocate;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RateShareEntry rateShareEntry = (RateShareEntry) obj;
        if (this.g == rateShareEntry.g && this.e == rateShareEntry.e && this.f == rateShareEntry.f && this.b == rateShareEntry.b && this.c == rateShareEntry.c) {
            return this.d == null ? rateShareEntry.d == null : this.d.equals(rateShareEntry.d);
        }
        return false;
    }

    public int hashCode() {
        return (((((((((this.b * 31) + this.c) * 31) + (this.d != null ? this.d.hashCode() : 0)) * 31) + this.e) * 31) + this.f) * 31) + this.g;
    }

    public short c() {
        return this.b;
    }

    public void a(short s) {
        this.b = s;
    }

    public short d() {
        return this.c;
    }

    public void b(short s) {
        this.c = s;
    }

    public List<Entry> e() {
        return this.d;
    }

    public void a(List<Entry> list) {
        this.d = list;
    }

    public int f() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public int g() {
        return this.f;
    }

    public void b(int i) {
        this.f = i;
    }

    public short h() {
        return this.g;
    }

    public void c(short s) {
        this.g = s;
    }

    public static class Entry {

        /* renamed from: a  reason: collision with root package name */
        int f3958a;
        short b;

        public Entry(int i, short s) {
            this.f3958a = i;
            this.b = s;
        }

        public String toString() {
            return "{availableBitrate=" + this.f3958a + ", targetRateShare=" + this.b + Operators.BLOCK_END;
        }

        public int a() {
            return this.f3958a;
        }

        public void a(int i) {
            this.f3958a = i;
        }

        public short b() {
            return this.b;
        }

        public void a(short s) {
            this.b = s;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.f3958a == entry.f3958a && this.b == entry.b;
        }

        public int hashCode() {
            return (this.f3958a * 31) + this.b;
        }
    }
}
