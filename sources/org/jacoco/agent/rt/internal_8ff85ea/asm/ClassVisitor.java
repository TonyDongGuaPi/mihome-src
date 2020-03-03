package org.jacoco.agent.rt.internal_8ff85ea.asm;

public abstract class ClassVisitor {

    /* renamed from: a  reason: collision with root package name */
    protected final int f3588a;
    protected ClassVisitor b;

    public ClassVisitor(int i) {
        this(i, (ClassVisitor) null);
    }

    public ClassVisitor(int i, ClassVisitor classVisitor) {
        if (i == 262144 || i == 327680) {
            this.f3588a = i;
            this.b = classVisitor;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void a(int i, int i2, String str, String str2, String str3, String[] strArr) {
        if (this.b != null) {
            this.b.a(i, i2, str, str2, str3, strArr);
        }
    }

    public void a(String str, String str2) {
        if (this.b != null) {
            this.b.a(str, str2);
        }
    }

    public void a(String str, String str2, String str3) {
        if (this.b != null) {
            this.b.a(str, str2, str3);
        }
    }

    public AnnotationVisitor a(String str, boolean z) {
        if (this.b != null) {
            return this.b.a(str, z);
        }
        return null;
    }

    public AnnotationVisitor a(int i, TypePath typePath, String str, boolean z) {
        if (this.f3588a < 327680) {
            throw new RuntimeException();
        } else if (this.b != null) {
            return this.b.a(i, typePath, str, z);
        } else {
            return null;
        }
    }

    public void a(Attribute attribute) {
        if (this.b != null) {
            this.b.a(attribute);
        }
    }

    public void a(String str, String str2, String str3, int i) {
        if (this.b != null) {
            this.b.a(str, str2, str3, i);
        }
    }

    public FieldVisitor a(int i, String str, String str2, String str3, Object obj) {
        if (this.b != null) {
            return this.b.a(i, str, str2, str3, obj);
        }
        return null;
    }

    public MethodVisitor a(int i, String str, String str2, String str3, String[] strArr) {
        if (this.b != null) {
            return this.b.a(i, str, str2, str3, strArr);
        }
        return null;
    }

    public void a() {
        if (this.b != null) {
            this.b.a();
        }
    }
}
