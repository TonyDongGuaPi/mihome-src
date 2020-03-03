package org.mp4parser.aspectj.runtime.reflect;

import org.mp4parser.aspectj.lang.reflect.SourceLocation;

class SourceLocationImpl implements SourceLocation {

    /* renamed from: a  reason: collision with root package name */
    Class f3779a;
    String b;
    int c;

    public int d() {
        return -1;
    }

    SourceLocationImpl(Class cls, String str, int i) {
        this.f3779a = cls;
        this.b = str;
        this.c = i;
    }

    public Class a() {
        return this.f3779a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(b());
        stringBuffer.append(":");
        stringBuffer.append(c());
        return stringBuffer.toString();
    }
}
