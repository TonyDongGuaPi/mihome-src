package org.jacoco.agent.rt.internal_8ff85ea.asm;

final class AnnotationWriter extends AnnotationVisitor {
    AnnotationWriter c;
    AnnotationWriter d;
    private final ClassWriter e;
    private int f;
    private final boolean g;
    private final ByteVector h;
    private final ByteVector i;
    private final int j;

    AnnotationWriter(ClassWriter classWriter, boolean z, ByteVector byteVector, ByteVector byteVector2, int i2) {
        super(327680);
        this.e = classWriter;
        this.g = z;
        this.h = byteVector;
        this.i = byteVector2;
        this.j = i2;
    }

    public void a(String str, Object obj) {
        this.f++;
        if (this.g) {
            this.h.b(this.e.a(str));
        }
        if (obj instanceof String) {
            this.h.b(115, this.e.a((String) obj));
        } else if (obj instanceof Byte) {
            this.h.b(66, this.e.a((int) ((Byte) obj).byteValue()).f3595a);
        } else if (obj instanceof Boolean) {
            this.h.b(90, this.e.a(((Boolean) obj).booleanValue() ? 1 : 0).f3595a);
        } else if (obj instanceof Character) {
            this.h.b(67, this.e.a((int) ((Character) obj).charValue()).f3595a);
        } else if (obj instanceof Short) {
            this.h.b(83, this.e.a((int) ((Short) obj).shortValue()).f3595a);
        } else if (obj instanceof Type) {
            this.h.b(99, this.e.a(((Type) obj).i()));
        } else {
            int i2 = 0;
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                this.h.b(91, bArr.length);
                while (i2 < bArr.length) {
                    this.h.b(66, this.e.a((int) bArr[i2]).f3595a);
                    i2++;
                }
            } else if (obj instanceof boolean[]) {
                boolean[] zArr = (boolean[]) obj;
                this.h.b(91, zArr.length);
                while (i2 < zArr.length) {
                    this.h.b(90, this.e.a(zArr[i2] ? 1 : 0).f3595a);
                    i2++;
                }
            } else if (obj instanceof short[]) {
                short[] sArr = (short[]) obj;
                this.h.b(91, sArr.length);
                while (i2 < sArr.length) {
                    this.h.b(83, this.e.a((int) sArr[i2]).f3595a);
                    i2++;
                }
            } else if (obj instanceof char[]) {
                char[] cArr = (char[]) obj;
                this.h.b(91, cArr.length);
                while (i2 < cArr.length) {
                    this.h.b(67, this.e.a((int) cArr[i2]).f3595a);
                    i2++;
                }
            } else if (obj instanceof int[]) {
                int[] iArr = (int[]) obj;
                this.h.b(91, iArr.length);
                while (i2 < iArr.length) {
                    this.h.b(73, this.e.a(iArr[i2]).f3595a);
                    i2++;
                }
            } else if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                this.h.b(91, jArr.length);
                while (i2 < jArr.length) {
                    this.h.b(74, this.e.a(jArr[i2]).f3595a);
                    i2++;
                }
            } else if (obj instanceof float[]) {
                float[] fArr = (float[]) obj;
                this.h.b(91, fArr.length);
                while (i2 < fArr.length) {
                    this.h.b(70, this.e.a(fArr[i2]).f3595a);
                    i2++;
                }
            } else if (obj instanceof double[]) {
                double[] dArr = (double[]) obj;
                this.h.b(91, dArr.length);
                while (i2 < dArr.length) {
                    this.h.b(68, this.e.a(dArr[i2]).f3595a);
                    i2++;
                }
            } else {
                Item a2 = this.e.a(obj);
                this.h.b(".s.IFJDCS".charAt(a2.b), a2.f3595a);
            }
        }
    }

    public void a(String str, String str2, String str3) {
        this.f++;
        if (this.g) {
            this.h.b(this.e.a(str));
        }
        this.h.b(101, this.e.a(str2)).b(this.e.a(str3));
    }

    public AnnotationVisitor a(String str, String str2) {
        this.f++;
        if (this.g) {
            this.h.b(this.e.a(str));
        }
        this.h.b(64, this.e.a(str2)).b(0);
        return new AnnotationWriter(this.e, true, this.h, this.h, this.h.b - 2);
    }

    public AnnotationVisitor a(String str) {
        this.f++;
        if (this.g) {
            this.h.b(this.e.a(str));
        }
        this.h.b(91, 0);
        return new AnnotationWriter(this.e, false, this.h, this.h, this.h.b - 2);
    }

    public void a() {
        if (this.i != null) {
            byte[] bArr = this.i.f3586a;
            bArr[this.j] = (byte) (this.f >>> 8);
            bArr[this.j + 1] = (byte) this.f;
        }
    }

    /* access modifiers changed from: package-private */
    public int b() {
        int i2 = 0;
        for (AnnotationWriter annotationWriter = this; annotationWriter != null; annotationWriter = annotationWriter.c) {
            i2 += annotationWriter.h.b;
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public void a(ByteVector byteVector) {
        AnnotationWriter annotationWriter = null;
        int i2 = 0;
        int i3 = 2;
        for (AnnotationWriter annotationWriter2 = this; annotationWriter2 != null; annotationWriter2 = annotationWriter2.c) {
            i2++;
            i3 += annotationWriter2.h.b;
            annotationWriter2.a();
            annotationWriter2.d = annotationWriter;
            annotationWriter = annotationWriter2;
        }
        byteVector.c(i3);
        byteVector.b(i2);
        while (annotationWriter != null) {
            byteVector.a(annotationWriter.h.f3586a, 0, annotationWriter.h.b);
            annotationWriter = annotationWriter.d;
        }
    }

    static void a(AnnotationWriter[] annotationWriterArr, int i2, ByteVector byteVector) {
        int length = ((annotationWriterArr.length - i2) * 2) + 1;
        int i3 = i2;
        while (true) {
            int i4 = 0;
            if (i3 >= annotationWriterArr.length) {
                break;
            }
            if (annotationWriterArr[i3] != null) {
                i4 = annotationWriterArr[i3].b();
            }
            length += i4;
            i3++;
        }
        byteVector.c(length).a(annotationWriterArr.length - i2);
        while (i2 < annotationWriterArr.length) {
            AnnotationWriter annotationWriter = null;
            int i5 = 0;
            for (AnnotationWriter annotationWriter2 = annotationWriterArr[i2]; annotationWriter2 != null; annotationWriter2 = annotationWriter2.c) {
                i5++;
                annotationWriter2.a();
                annotationWriter2.d = annotationWriter;
                annotationWriter = annotationWriter2;
            }
            byteVector.b(i5);
            while (annotationWriter != null) {
                byteVector.a(annotationWriter.h.f3586a, 0, annotationWriter.h.b);
                annotationWriter = annotationWriter.d;
            }
            i2++;
        }
    }

    static void a(int i2, TypePath typePath, ByteVector byteVector) {
        int i3 = i2 >>> 24;
        switch (i3) {
            case 0:
            case 1:
                byteVector.b(i2 >>> 16);
                break;
            default:
                switch (i3) {
                    case 19:
                    case 20:
                    case 21:
                        byteVector.a(i3);
                        break;
                    case 22:
                        break;
                    default:
                        switch (i3) {
                            case 71:
                            case 72:
                            case 73:
                            case 74:
                            case 75:
                                byteVector.c(i2);
                                break;
                            default:
                                byteVector.b(i3, (i2 & 16776960) >> 8);
                                break;
                        }
                }
                byteVector.b(i2 >>> 16);
                break;
        }
        if (typePath == null) {
            byteVector.a(0);
            return;
        }
        byteVector.a(typePath.e, typePath.f, (typePath.e[typePath.f] * 2) + 1);
    }
}
