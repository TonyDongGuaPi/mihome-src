package org.jacoco.agent.rt.internal_8ff85ea.asm;

public class Attribute {

    /* renamed from: a  reason: collision with root package name */
    public final String f3585a;
    byte[] b;
    Attribute c;

    public boolean a() {
        return true;
    }

    public boolean b() {
        return false;
    }

    /* access modifiers changed from: protected */
    public Label[] c() {
        return null;
    }

    protected Attribute(String str) {
        this.f3585a = str;
    }

    /* access modifiers changed from: protected */
    public Attribute a(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        Attribute attribute = new Attribute(this.f3585a);
        attribute.b = new byte[i2];
        System.arraycopy(classReader.k, i, attribute.b, 0, i2);
        return attribute;
    }

    /* access modifiers changed from: protected */
    public ByteVector a(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3) {
        ByteVector byteVector = new ByteVector();
        byteVector.f3586a = this.b;
        byteVector.b = this.b.length;
        return byteVector;
    }

    /* access modifiers changed from: package-private */
    public final int d() {
        int i = 0;
        for (Attribute attribute = this; attribute != null; attribute = attribute.c) {
            i++;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public final int b(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        for (Attribute attribute = this; attribute != null; attribute = attribute.c) {
            classWriter.a(attribute.f3585a);
            i4 += attribute.a(classWriter, bArr, i, i2, i3).b + 6;
        }
        return i4;
    }

    /* access modifiers changed from: package-private */
    public final void a(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3, ByteVector byteVector) {
        for (Attribute attribute = this; attribute != null; attribute = attribute.c) {
            ByteVector a2 = attribute.a(classWriter, bArr, i, i2, i3);
            byteVector.b(classWriter.a(attribute.f3585a)).c(a2.b);
            byteVector.a(a2.f3586a, 0, a2.b);
        }
    }
}
