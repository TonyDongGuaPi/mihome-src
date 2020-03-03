package com.xiaomi.zxing.oned.rss.expanded;

import java.util.ArrayList;
import java.util.List;

final class ExpandedRow {

    /* renamed from: a  reason: collision with root package name */
    private final List<ExpandedPair> f1717a;
    private final int b;
    private final boolean c;

    ExpandedRow(List<ExpandedPair> list, int i, boolean z) {
        this.f1717a = new ArrayList(list);
        this.b = i;
        this.c = z;
    }

    /* access modifiers changed from: package-private */
    public List<ExpandedPair> a() {
        return this.f1717a;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public boolean a(List<ExpandedPair> list) {
        return this.f1717a.equals(list);
    }

    public String toString() {
        return "{ " + this.f1717a + " }";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ExpandedRow)) {
            return false;
        }
        ExpandedRow expandedRow = (ExpandedRow) obj;
        if (!this.f1717a.equals(expandedRow.a()) || this.c != expandedRow.c) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.f1717a.hashCode() ^ Boolean.valueOf(this.c).hashCode();
    }
}
