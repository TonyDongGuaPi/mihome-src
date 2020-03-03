package org.jacoco.agent.rt.internal_8ff85ea.asm;

public abstract class FieldVisitor {

    /* renamed from: a  reason: collision with root package name */
    protected final int f3591a;
    protected FieldVisitor b;

    public FieldVisitor(int i) {
        this(i, (FieldVisitor) null);
    }

    public FieldVisitor(int i, FieldVisitor fieldVisitor) {
        if (i == 262144 || i == 327680) {
            this.f3591a = i;
            this.b = fieldVisitor;
            return;
        }
        throw new IllegalArgumentException();
    }

    public AnnotationVisitor a(String str, boolean z) {
        if (this.b != null) {
            return this.b.a(str, z);
        }
        return null;
    }

    public AnnotationVisitor a(int i, TypePath typePath, String str, boolean z) {
        if (this.f3591a < 327680) {
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

    public void a() {
        if (this.b != null) {
            this.b.a();
        }
    }
}
