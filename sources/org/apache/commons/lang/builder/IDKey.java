package org.apache.commons.lang.builder;

final class IDKey {

    /* renamed from: a  reason: collision with root package name */
    private final Object f3378a;
    private final int b;

    public IDKey(Object obj) {
        this.b = System.identityHashCode(obj);
        this.f3378a = obj;
    }

    public int hashCode() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IDKey)) {
            return false;
        }
        IDKey iDKey = (IDKey) obj;
        if (this.b == iDKey.b && this.f3378a == iDKey.f3378a) {
            return true;
        }
        return false;
    }
}
