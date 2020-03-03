package com.xiaomi.zxing.oned.rss.expanded;

import com.xiaomi.zxing.oned.rss.DataCharacter;
import com.xiaomi.zxing.oned.rss.FinderPattern;

final class ExpandedPair {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f1716a;
    private final DataCharacter b;
    private final DataCharacter c;
    private final FinderPattern d;

    ExpandedPair(DataCharacter dataCharacter, DataCharacter dataCharacter2, FinderPattern finderPattern, boolean z) {
        this.b = dataCharacter;
        this.c = dataCharacter2;
        this.d = finderPattern;
        this.f1716a = z;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.f1716a;
    }

    /* access modifiers changed from: package-private */
    public DataCharacter b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public DataCharacter c() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public FinderPattern d() {
        return this.d;
    }

    public boolean e() {
        return this.c == null;
    }

    public String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        sb.append(this.b);
        sb.append(" , ");
        sb.append(this.c);
        sb.append(" : ");
        if (this.d == null) {
            obj = "null";
        } else {
            obj = Integer.valueOf(this.d.a());
        }
        sb.append(obj);
        sb.append(" ]");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ExpandedPair)) {
            return false;
        }
        ExpandedPair expandedPair = (ExpandedPair) obj;
        if (!a(this.b, expandedPair.b) || !a(this.c, expandedPair.c) || !a(this.d, expandedPair.d)) {
            return false;
        }
        return true;
    }

    private static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public int hashCode() {
        return (a(this.b) ^ a(this.c)) ^ a(this.d);
    }

    private static int a(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }
}
