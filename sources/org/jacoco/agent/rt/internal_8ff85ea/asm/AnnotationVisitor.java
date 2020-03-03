package org.jacoco.agent.rt.internal_8ff85ea.asm;

public abstract class AnnotationVisitor {

    /* renamed from: a  reason: collision with root package name */
    protected final int f3584a;
    protected AnnotationVisitor b;

    public AnnotationVisitor(int i) {
        this(i, (AnnotationVisitor) null);
    }

    public AnnotationVisitor(int i, AnnotationVisitor annotationVisitor) {
        if (i == 262144 || i == 327680) {
            this.f3584a = i;
            this.b = annotationVisitor;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void a(String str, Object obj) {
        if (this.b != null) {
            this.b.a(str, obj);
        }
    }

    public void a(String str, String str2, String str3) {
        if (this.b != null) {
            this.b.a(str, str2, str3);
        }
    }

    public AnnotationVisitor a(String str, String str2) {
        if (this.b != null) {
            return this.b.a(str, str2);
        }
        return null;
    }

    public AnnotationVisitor a(String str) {
        if (this.b != null) {
            return this.b.a(str);
        }
        return null;
    }

    public void a() {
        if (this.b != null) {
            this.b.a();
        }
    }
}
