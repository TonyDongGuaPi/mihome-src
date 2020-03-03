package org.apache.commons.compress.archivers.sevenz;

public class SevenZMethodConfiguration {

    /* renamed from: a  reason: collision with root package name */
    private final SevenZMethod f3234a;
    private final Object b;

    public SevenZMethodConfiguration(SevenZMethod sevenZMethod) {
        this(sevenZMethod, (Object) null);
    }

    public SevenZMethodConfiguration(SevenZMethod sevenZMethod, Object obj) {
        this.f3234a = sevenZMethod;
        this.b = obj;
        if (obj != null && !Coders.a(sevenZMethod).a(obj)) {
            throw new IllegalArgumentException("The " + sevenZMethod + " method doesn't support options of type " + obj.getClass());
        }
    }

    public SevenZMethod a() {
        return this.f3234a;
    }

    public Object b() {
        return this.b;
    }
}
