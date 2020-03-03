package org.apache.commons.compress.archivers.sevenz;

import java.util.LinkedList;

class Folder {

    /* renamed from: a  reason: collision with root package name */
    Coder[] f3231a;
    long b;
    long c;
    BindPair[] d;
    long[] e;
    long[] f;
    boolean g;
    long h;
    int i;

    Folder() {
    }

    /* access modifiers changed from: package-private */
    public Iterable<Coder> a() {
        LinkedList linkedList = new LinkedList();
        int i2 = (int) this.e[0];
        while (i2 != -1) {
            linkedList.addLast(this.f3231a[i2]);
            int b2 = b(i2);
            i2 = b2 != -1 ? (int) this.d[b2].f3221a : -1;
        }
        return linkedList;
    }

    /* access modifiers changed from: package-private */
    public int a(int i2) {
        for (int i3 = 0; i3 < this.d.length; i3++) {
            if (this.d[i3].f3221a == ((long) i2)) {
                return i3;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int b(int i2) {
        for (int i3 = 0; i3 < this.d.length; i3++) {
            if (this.d[i3].b == ((long) i2)) {
                return i3;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public long b() {
        if (this.c == 0) {
            return 0;
        }
        for (int i2 = ((int) this.c) - 1; i2 >= 0; i2--) {
            if (b(i2) < 0) {
                return this.f[i2];
            }
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public long a(Coder coder) {
        if (this.f3231a == null) {
            return 0;
        }
        for (int i2 = 0; i2 < this.f3231a.length; i2++) {
            if (this.f3231a[i2] == coder) {
                return this.f[i2];
            }
        }
        return 0;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Folder with ");
        sb.append(this.f3231a.length);
        sb.append(" coders, ");
        sb.append(this.b);
        sb.append(" input streams, ");
        sb.append(this.c);
        sb.append(" output streams, ");
        sb.append(this.d.length);
        sb.append(" bind pairs, ");
        sb.append(this.e.length);
        sb.append(" packed streams, ");
        sb.append(this.f.length);
        sb.append(" unpack sizes, ");
        if (this.g) {
            str = "with CRC " + this.h;
        } else {
            str = "without CRC";
        }
        sb.append(str);
        sb.append(" and ");
        sb.append(this.i);
        sb.append(" unpack streams");
        return sb.toString();
    }
}
