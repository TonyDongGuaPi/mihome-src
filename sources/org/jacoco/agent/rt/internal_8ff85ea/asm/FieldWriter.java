package org.jacoco.agent.rt.internal_8ff85ea.asm;

final class FieldWriter extends FieldVisitor {
    private final ClassWriter c;
    private final int d;
    private final int e;
    private final int f;
    private int g;
    private int h;
    private AnnotationWriter i;
    private AnnotationWriter j;
    private AnnotationWriter k;
    private AnnotationWriter l;
    private Attribute m;

    public void a() {
    }

    FieldWriter(ClassWriter classWriter, int i2, String str, String str2, String str3, Object obj) {
        super(327680);
        if (classWriter.ai == null) {
            classWriter.ai = this;
        } else {
            classWriter.aj.b = this;
        }
        classWriter.aj = this;
        this.c = classWriter;
        this.d = i2;
        this.e = classWriter.a(str);
        this.f = classWriter.a(str2);
        if (str3 != null) {
            this.g = classWriter.a(str3);
        }
        if (obj != null) {
            this.h = classWriter.a(obj).f3595a;
        }
    }

    public AnnotationVisitor a(String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.b(this.c.a(str)).b(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.c, true, byteVector, byteVector, 2);
        if (z) {
            annotationWriter.c = this.i;
            this.i = annotationWriter;
        } else {
            annotationWriter.c = this.j;
            this.j = annotationWriter;
        }
        return annotationWriter;
    }

    public AnnotationVisitor a(int i2, TypePath typePath, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.a(i2, typePath, byteVector);
        byteVector.b(this.c.a(str)).b(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.c, true, byteVector, byteVector, byteVector.b - 2);
        if (z) {
            annotationWriter.c = this.k;
            this.k = annotationWriter;
        } else {
            annotationWriter.c = this.l;
            this.l = annotationWriter;
        }
        return annotationWriter;
    }

    public void a(Attribute attribute) {
        attribute.c = this.m;
        this.m = attribute;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        int i2;
        if (this.h != 0) {
            this.c.a("ConstantValue");
            i2 = 16;
        } else {
            i2 = 8;
        }
        if ((this.d & 4096) != 0 && ((this.c.V & 65535) < 49 || (this.d & 262144) != 0)) {
            this.c.a("Synthetic");
            i2 += 6;
        }
        if ((this.d & 131072) != 0) {
            this.c.a("Deprecated");
            i2 += 6;
        }
        if (this.g != 0) {
            this.c.a("Signature");
            i2 += 8;
        }
        if (this.i != null) {
            this.c.a("RuntimeVisibleAnnotations");
            i2 += this.i.b() + 8;
        }
        if (this.j != null) {
            this.c.a("RuntimeInvisibleAnnotations");
            i2 += this.j.b() + 8;
        }
        if (this.k != null) {
            this.c.a("RuntimeVisibleTypeAnnotations");
            i2 += this.k.b() + 8;
        }
        if (this.l != null) {
            this.c.a("RuntimeInvisibleTypeAnnotations");
            i2 += this.l.b() + 8;
        }
        return this.m != null ? i2 + this.m.b(this.c, (byte[]) null, 0, -1, -1) : i2;
    }

    /* access modifiers changed from: package-private */
    public void a(ByteVector byteVector) {
        byteVector.b(((((this.d & 262144) / 64) | 393216) ^ -1) & this.d).b(this.e).b(this.f);
        int i2 = this.h != 0 ? 1 : 0;
        if ((this.d & 4096) != 0 && ((this.c.V & 65535) < 49 || (this.d & 262144) != 0)) {
            i2++;
        }
        if ((this.d & 131072) != 0) {
            i2++;
        }
        if (this.g != 0) {
            i2++;
        }
        if (this.i != null) {
            i2++;
        }
        if (this.j != null) {
            i2++;
        }
        if (this.k != null) {
            i2++;
        }
        if (this.l != null) {
            i2++;
        }
        if (this.m != null) {
            i2 += this.m.d();
        }
        byteVector.b(i2);
        if (this.h != 0) {
            byteVector.b(this.c.a("ConstantValue"));
            byteVector.c(2).b(this.h);
        }
        if ((this.d & 4096) != 0 && ((this.c.V & 65535) < 49 || (this.d & 262144) != 0)) {
            byteVector.b(this.c.a("Synthetic")).c(0);
        }
        if ((this.d & 131072) != 0) {
            byteVector.b(this.c.a("Deprecated")).c(0);
        }
        if (this.g != 0) {
            byteVector.b(this.c.a("Signature"));
            byteVector.c(2).b(this.g);
        }
        if (this.i != null) {
            byteVector.b(this.c.a("RuntimeVisibleAnnotations"));
            this.i.a(byteVector);
        }
        if (this.j != null) {
            byteVector.b(this.c.a("RuntimeInvisibleAnnotations"));
            this.j.a(byteVector);
        }
        if (this.k != null) {
            byteVector.b(this.c.a("RuntimeVisibleTypeAnnotations"));
            this.k.a(byteVector);
        }
        if (this.l != null) {
            byteVector.b(this.c.a("RuntimeInvisibleTypeAnnotations"));
            this.l.a(byteVector);
        }
        if (this.m != null) {
            this.m.a(this.c, (byte[]) null, 0, -1, -1, byteVector);
        }
    }
}
