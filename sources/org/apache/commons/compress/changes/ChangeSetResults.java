package org.apache.commons.compress.changes;

import java.util.ArrayList;
import java.util.List;

public class ChangeSetResults {

    /* renamed from: a  reason: collision with root package name */
    private final List<String> f3307a = new ArrayList();
    private final List<String> b = new ArrayList();
    private final List<String> c = new ArrayList();

    /* access modifiers changed from: package-private */
    public void a(String str) {
        this.c.add(str);
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        this.b.add(str);
    }

    /* access modifiers changed from: package-private */
    public void c(String str) {
        this.f3307a.add(str);
    }

    public List<String> a() {
        return this.f3307a;
    }

    public List<String> b() {
        return this.b;
    }

    public List<String> c() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public boolean d(String str) {
        return this.f3307a.contains(str) || this.b.contains(str);
    }
}
