package com.biubiu.kit.core;

public class Mapping {

    /* renamed from: a  reason: collision with root package name */
    private String f4800a;
    private String b;

    public Mapping(String str, String str2) {
        this.f4800a = str;
        this.b = str2;
    }

    public boolean a(Class<?> cls) {
        return cls != null && cls.getName().equals(this.f4800a);
    }

    public String a() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Mapping mapping = (Mapping) obj;
        if (this.f4800a == null ? mapping.f4800a != null : !this.f4800a.equals(mapping.f4800a)) {
            return false;
        }
        if (this.b != null) {
            return this.b.equals(mapping.b);
        }
        if (mapping.b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.f4800a != null ? this.f4800a.hashCode() : 0) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode + i;
    }
}
