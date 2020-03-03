package org.jacoco.agent.rt.internal_8ff85ea.asm;

public abstract class MethodVisitor {
    protected final int P_;
    protected MethodVisitor Q_;

    public MethodVisitor(int i) {
        this(i, (MethodVisitor) null);
    }

    public MethodVisitor(int i, MethodVisitor methodVisitor) {
        if (i == 262144 || i == 327680) {
            this.P_ = i;
            this.Q_ = methodVisitor;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void a(String str, int i) {
        if (this.P_ < 327680) {
            throw new RuntimeException();
        } else if (this.Q_ != null) {
            this.Q_.a(str, i);
        }
    }

    public AnnotationVisitor a() {
        if (this.Q_ != null) {
            return this.Q_.a();
        }
        return null;
    }

    public AnnotationVisitor a(String str, boolean z) {
        if (this.Q_ != null) {
            return this.Q_.a(str, z);
        }
        return null;
    }

    public AnnotationVisitor a(int i, TypePath typePath, String str, boolean z) {
        if (this.P_ < 327680) {
            throw new RuntimeException();
        } else if (this.Q_ != null) {
            return this.Q_.a(i, typePath, str, z);
        } else {
            return null;
        }
    }

    public AnnotationVisitor a(int i, String str, boolean z) {
        if (this.Q_ != null) {
            return this.Q_.a(i, str, z);
        }
        return null;
    }

    public void a(Attribute attribute) {
        if (this.Q_ != null) {
            this.Q_.a(attribute);
        }
    }

    public void b() {
        if (this.Q_ != null) {
            this.Q_.b();
        }
    }

    public void a(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        if (this.Q_ != null) {
            this.Q_.a(i, i2, objArr, i3, objArr2);
        }
    }

    public void e_(int i) {
        if (this.Q_ != null) {
            this.Q_.e_(i);
        }
    }

    public void a(int i, int i2) {
        if (this.Q_ != null) {
            this.Q_.a(i, i2);
        }
    }

    public void b(int i, int i2) {
        if (this.Q_ != null) {
            this.Q_.b(i, i2);
        }
    }

    public void a(int i, String str) {
        if (this.Q_ != null) {
            this.Q_.a(i, str);
        }
    }

    public void a(int i, String str, String str2, String str3) {
        if (this.Q_ != null) {
            this.Q_.a(i, str, str2, str3);
        }
    }

    @Deprecated
    public void b(int i, String str, String str2, String str3) {
        if (this.P_ >= 327680) {
            a(i, str, str2, str3, i == 185);
        } else if (this.Q_ != null) {
            this.Q_.b(i, str, str2, str3);
        }
    }

    public void a(int i, String str, String str2, String str3, boolean z) {
        if (this.P_ < 327680) {
            if (z == (i == 185)) {
                b(i, str, str2, str3);
                return;
            }
            throw new IllegalArgumentException("INVOKESPECIAL/STATIC on interfaces require ASM 5");
        } else if (this.Q_ != null) {
            this.Q_.a(i, str, str2, str3, z);
        }
    }

    public void a(String str, String str2, Handle handle, Object... objArr) {
        if (this.Q_ != null) {
            this.Q_.a(str, str2, handle, objArr);
        }
    }

    public void a(int i, Label label) {
        if (this.Q_ != null) {
            this.Q_.a(i, label);
        }
    }

    public void a(Label label) {
        if (this.Q_ != null) {
            this.Q_.a(label);
        }
    }

    public void a(Object obj) {
        if (this.Q_ != null) {
            this.Q_.a(obj);
        }
    }

    public void c(int i, int i2) {
        if (this.Q_ != null) {
            this.Q_.c(i, i2);
        }
    }

    public void a(int i, int i2, Label label, Label... labelArr) {
        if (this.Q_ != null) {
            this.Q_.a(i, i2, label, labelArr);
        }
    }

    public void a(Label label, int[] iArr, Label[] labelArr) {
        if (this.Q_ != null) {
            this.Q_.a(label, iArr, labelArr);
        }
    }

    public void b(String str, int i) {
        if (this.Q_ != null) {
            this.Q_.b(str, i);
        }
    }

    public AnnotationVisitor b(int i, TypePath typePath, String str, boolean z) {
        if (this.P_ < 327680) {
            throw new RuntimeException();
        } else if (this.Q_ != null) {
            return this.Q_.b(i, typePath, str, z);
        } else {
            return null;
        }
    }

    public void a(Label label, Label label2, Label label3, String str) {
        if (this.Q_ != null) {
            this.Q_.a(label, label2, label3, str);
        }
    }

    public AnnotationVisitor c(int i, TypePath typePath, String str, boolean z) {
        if (this.P_ < 327680) {
            throw new RuntimeException();
        } else if (this.Q_ != null) {
            return this.Q_.c(i, typePath, str, z);
        } else {
            return null;
        }
    }

    public void a(String str, String str2, String str3, Label label, Label label2, int i) {
        if (this.Q_ != null) {
            this.Q_.a(str, str2, str3, label, label2, i);
        }
    }

    public AnnotationVisitor a(int i, TypePath typePath, Label[] labelArr, Label[] labelArr2, int[] iArr, String str, boolean z) {
        if (this.P_ < 327680) {
            throw new RuntimeException();
        } else if (this.Q_ != null) {
            return this.Q_.a(i, typePath, labelArr, labelArr2, iArr, str, z);
        } else {
            return null;
        }
    }

    public void b(int i, Label label) {
        if (this.Q_ != null) {
            this.Q_.b(i, label);
        }
    }

    public void d(int i, int i2) {
        if (this.Q_ != null) {
            this.Q_.d(i, i2);
        }
    }

    public void c() {
        if (this.Q_ != null) {
            this.Q_.c();
        }
    }
}
