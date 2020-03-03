package org.xutils.image;

final class MemCacheKey {

    /* renamed from: a  reason: collision with root package name */
    public final String f11939a;
    public final ImageOptions b;

    public MemCacheKey(String str, ImageOptions imageOptions) {
        this.f11939a = str;
        this.b = imageOptions;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MemCacheKey memCacheKey = (MemCacheKey) obj;
        if (!this.f11939a.equals(memCacheKey.f11939a)) {
            return false;
        }
        return this.b.equals(memCacheKey.b);
    }

    public int hashCode() {
        return (this.f11939a.hashCode() * 31) + this.b.hashCode();
    }

    public String toString() {
        return this.f11939a + this.b.toString();
    }
}
