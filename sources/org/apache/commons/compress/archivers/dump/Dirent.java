package org.apache.commons.compress.archivers.dump;

class Dirent {

    /* renamed from: a  reason: collision with root package name */
    private final int f3211a;
    private final int b;
    private final int c;
    private final String d;

    Dirent(int i, int i2, int i3, String str) {
        this.f3211a = i;
        this.b = i2;
        this.c = i3;
        this.d = str;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.f3211a;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public String d() {
        return this.d;
    }

    public String toString() {
        return String.format("[%d]: %s", new Object[]{Integer.valueOf(this.f3211a), this.d});
    }
}
