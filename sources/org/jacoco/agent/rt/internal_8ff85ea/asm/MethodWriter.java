package org.jacoco.agent.rt.internal_8ff85ea.asm;

import com.libra.Color;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.list.template.TemplateDom;
import com.xiaomi.smarthome.download.Downloads;

class MethodWriter extends MethodVisitor {
    static final int c = 524288;
    static final int d = 0;
    static final int e = 64;
    static final int f = 128;
    static final int g = 247;
    static final int h = 248;
    static final int i = 251;
    static final int j = 252;
    static final int k = 255;
    static final int l = 0;
    static final int m = 1;
    static final int n = 2;
    static final int o = 3;
    private AnnotationWriter A;
    private AnnotationWriter B;
    private AnnotationWriter C;
    private AnnotationWriter D;
    private AnnotationWriter[] E;
    private AnnotationWriter[] F;
    private int G;
    private Attribute H;
    private ByteVector I = new ByteVector();
    private int J;
    private int K;
    private int L;
    private int M;
    private ByteVector N;
    private int O;
    private int[] P;
    private int[] Q;
    private int R;
    private Handler S;
    private Handler T;
    private int U;
    private ByteVector V;
    private int W;
    private ByteVector X;
    private int Y;
    private ByteVector Z;
    private int aa;
    private ByteVector ab;
    private int ac;
    private AnnotationWriter ad;
    private AnnotationWriter ae;
    private Attribute af;
    private int ag;
    private final int ah;
    private Label ai;
    private Label aj;
    private Label ak;
    private int al;
    private int am;
    final ClassWriter p;
    String q;
    int r;
    int s;
    int t;
    int[] u;
    private int v;
    private final int w;
    private final int x;
    private final String y;
    private ByteVector z;

    public void b() {
    }

    public void c() {
    }

    MethodWriter(ClassWriter classWriter, int i2, String str, String str2, String str3, String[] strArr, int i3) {
        super(327680);
        if (classWriter.ak == null) {
            classWriter.ak = this;
        } else {
            classWriter.al.Q_ = this;
        }
        classWriter.al = this;
        this.p = classWriter;
        this.v = i2;
        if ("<init>".equals(str)) {
            this.v |= 524288;
        }
        this.w = classWriter.a(str);
        this.x = classWriter.a(str2);
        this.y = str2;
        this.q = str3;
        if (strArr != null && strArr.length > 0) {
            this.t = strArr.length;
            this.u = new int[this.t];
            for (int i4 = 0; i4 < this.t; i4++) {
                this.u[i4] = classWriter.c(strArr[i4]);
            }
        }
        this.ah = i3;
        if (i3 != 3) {
            int f2 = Type.f(this.y) >> 2;
            f2 = (i2 & 8) != 0 ? f2 - 1 : f2;
            this.K = f2;
            this.L = f2;
            this.ai = new Label();
            this.ai.n |= 8;
            a(this.ai);
        }
    }

    public void a(String str, int i2) {
        if (this.V == null) {
            this.V = new ByteVector();
        }
        this.U++;
        this.V.b(str == null ? 0 : this.p.a(str)).b(i2);
    }

    public AnnotationVisitor a() {
        this.z = new ByteVector();
        return new AnnotationWriter(this.p, false, this.z, (ByteVector) null, 0);
    }

    public AnnotationVisitor a(String str, boolean z2) {
        ByteVector byteVector = new ByteVector();
        byteVector.b(this.p.a(str)).b(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.p, true, byteVector, byteVector, 2);
        if (z2) {
            annotationWriter.c = this.A;
            this.A = annotationWriter;
        } else {
            annotationWriter.c = this.B;
            this.B = annotationWriter;
        }
        return annotationWriter;
    }

    public AnnotationVisitor a(int i2, TypePath typePath, String str, boolean z2) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.a(i2, typePath, byteVector);
        byteVector.b(this.p.a(str)).b(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.p, true, byteVector, byteVector, byteVector.b - 2);
        if (z2) {
            annotationWriter.c = this.C;
            this.C = annotationWriter;
        } else {
            annotationWriter.c = this.D;
            this.D = annotationWriter;
        }
        return annotationWriter;
    }

    public AnnotationVisitor a(int i2, String str, boolean z2) {
        ByteVector byteVector = new ByteVector();
        if ("Ljava/lang/Synthetic;".equals(str)) {
            this.G = Math.max(this.G, i2 + 1);
            return new AnnotationWriter(this.p, false, byteVector, (ByteVector) null, 0);
        }
        byteVector.b(this.p.a(str)).b(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.p, true, byteVector, byteVector, 2);
        if (z2) {
            if (this.E == null) {
                this.E = new AnnotationWriter[Type.d(this.y).length];
            }
            annotationWriter.c = this.E[i2];
            this.E[i2] = annotationWriter;
        } else {
            if (this.F == null) {
                this.F = new AnnotationWriter[Type.d(this.y).length];
            }
            annotationWriter.c = this.F[i2];
            this.F[i2] = annotationWriter;
        }
        return annotationWriter;
    }

    public void a(Attribute attribute) {
        if (attribute.b()) {
            attribute.c = this.af;
            this.af = attribute;
            return;
        }
        attribute.c = this.H;
        this.H = attribute;
    }

    public void a(int i2, int i3, Object[] objArr, int i4, Object[] objArr2) {
        int i5;
        int i6;
        int i7;
        if (this.ah != 0) {
            if (this.ah != 1) {
                int i8 = 0;
                if (i2 == -1) {
                    if (this.P == null) {
                        f();
                    }
                    this.L = i3;
                    int a2 = a(this.I.b, i3, i4);
                    for (int i9 = 0; i9 < i3; i9++) {
                        if (objArr[i9] instanceof String) {
                            i7 = a2 + 1;
                            this.Q[a2] = 24117248 | this.p.f(objArr[i9]);
                        } else if (objArr[i9] instanceof Integer) {
                            this.Q[a2] = objArr[i9].intValue();
                            a2++;
                        } else {
                            i7 = a2 + 1;
                            this.Q[a2] = 25165824 | this.p.a("", objArr[i9].p);
                        }
                        a2 = i7;
                    }
                    while (i8 < i4) {
                        if (objArr2[i8] instanceof String) {
                            i6 = a2 + 1;
                            this.Q[a2] = this.p.f(objArr2[i8]) | 24117248;
                        } else if (objArr2[i8] instanceof Integer) {
                            i6 = a2 + 1;
                            this.Q[a2] = objArr2[i8].intValue();
                        } else {
                            i6 = a2 + 1;
                            this.Q[a2] = this.p.a("", objArr2[i8].p) | 25165824;
                        }
                        a2 = i6;
                        i8++;
                    }
                    g();
                } else {
                    if (this.N == null) {
                        this.N = new ByteVector();
                        i5 = this.I.b;
                    } else {
                        i5 = (this.I.b - this.O) - 1;
                        if (i5 < 0) {
                            if (i2 != 3) {
                                throw new IllegalStateException();
                            }
                            return;
                        }
                    }
                    switch (i2) {
                        case 0:
                            this.L = i3;
                            this.N.a(255).b(i5).b(i3);
                            for (int i10 = 0; i10 < i3; i10++) {
                                b(objArr[i10]);
                            }
                            this.N.b(i4);
                            while (i8 < i4) {
                                b(objArr2[i8]);
                                i8++;
                            }
                            break;
                        case 1:
                            this.L += i3;
                            this.N.a(i3 + i).b(i5);
                            while (i8 < i3) {
                                b(objArr[i8]);
                                i8++;
                            }
                            break;
                        case 2:
                            this.L -= i3;
                            this.N.a(i - i3).b(i5);
                            break;
                        case 3:
                            if (i5 >= 64) {
                                this.N.a((int) i).b(i5);
                                break;
                            } else {
                                this.N.a(i5);
                                break;
                            }
                        case 4:
                            if (i5 < 64) {
                                this.N.a(i5 + 64);
                            } else {
                                this.N.a((int) g).b(i5);
                            }
                            b(objArr2[0]);
                            break;
                    }
                    this.O = this.I.b;
                    this.M++;
                }
            } else if (this.ak.s == null) {
                this.ak.s = new CurrentFrame();
                this.ak.s.x = this.ak;
                this.ak.s.a(this.p, this.v, Type.d(this.y), i3);
                f();
            } else {
                if (i2 == -1) {
                    this.ak.s.a(this.p, i3, objArr, i4, objArr2);
                }
                a(this.ak.s);
            }
            this.J = Math.max(this.J, i4);
            this.K = Math.max(this.K, this.L);
        }
    }

    public void e_(int i2) {
        this.ac = this.I.b;
        this.I.a(i2);
        if (this.ak != null) {
            if (this.ah == 0 || this.ah == 1) {
                this.ak.s.a(i2, 0, (ClassWriter) null, (Item) null);
            } else {
                int i3 = this.al + Frame.w[i2];
                if (i3 > this.am) {
                    this.am = i3;
                }
                this.al = i3;
            }
            if ((i2 >= 172 && i2 <= 177) || i2 == 191) {
                e();
            }
        }
    }

    public void a(int i2, int i3) {
        this.ac = this.I.b;
        if (this.ak != null) {
            if (this.ah == 0 || this.ah == 1) {
                this.ak.s.a(i2, i3, (ClassWriter) null, (Item) null);
            } else if (i2 != 188) {
                int i4 = this.al + 1;
                if (i4 > this.am) {
                    this.am = i4;
                }
                this.al = i4;
            }
        }
        if (i2 == 17) {
            this.I.b(i2, i3);
        } else {
            this.I.a(i2, i3);
        }
    }

    public void b(int i2, int i3) {
        this.ac = this.I.b;
        if (this.ak != null) {
            if (this.ah == 0 || this.ah == 1) {
                this.ak.s.a(i2, i3, (ClassWriter) null, (Item) null);
            } else if (i2 == 169) {
                this.ak.n |= 256;
                this.ak.q = this.al;
                e();
            } else {
                int i4 = this.al + Frame.w[i2];
                if (i4 > this.am) {
                    this.am = i4;
                }
                this.al = i4;
            }
        }
        if (this.ah != 3) {
            int i5 = (i2 == 22 || i2 == 24 || i2 == 55 || i2 == 57) ? i3 + 2 : i3 + 1;
            if (i5 > this.K) {
                this.K = i5;
            }
        }
        if (i3 < 4 && i2 != 169) {
            this.I.a(i2 < 54 ? ((i2 - 21) << 2) + 26 + i3 : ((i2 - 54) << 2) + 59 + i3);
        } else if (i3 >= 256) {
            this.I.a((int) Downloads.STATUS_QUEUED_FOR_WIFI).b(i2, i3);
        } else {
            this.I.a(i2, i3);
        }
        if (i2 >= 54 && this.ah == 0 && this.R > 0) {
            a(new Label());
        }
    }

    public void a(int i2, String str) {
        this.ac = this.I.b;
        Item b = this.p.b(str);
        if (this.ak != null) {
            if (this.ah == 0 || this.ah == 1) {
                this.ak.s.a(i2, this.I.b, this.p, b);
            } else if (i2 == 187) {
                int i3 = this.al + 1;
                if (i3 > this.am) {
                    this.am = i3;
                }
                this.al = i3;
            }
        }
        this.I.b(i2, b.f3595a);
    }

    public void a(int i2, String str, String str2, String str3) {
        int i3;
        this.ac = this.I.b;
        Item b = this.p.b(str, str2, str3);
        if (this.ak != null) {
            int i4 = 0;
            if (this.ah != 0) {
                int i5 = 1;
                if (this.ah != 1) {
                    char charAt = str3.charAt(0);
                    int i6 = -2;
                    switch (i2) {
                        case 178:
                            int i7 = this.al;
                            if (charAt == 'D' || charAt == 'J') {
                                i5 = 2;
                            }
                            i3 = i7 + i5;
                            break;
                        case 179:
                            int i8 = this.al;
                            if (!(charAt == 'D' || charAt == 'J')) {
                                i6 = -1;
                            }
                            i3 = i6 + i8;
                            break;
                        case 180:
                            int i9 = this.al;
                            if (charAt == 'D' || charAt == 'J') {
                                i4 = 1;
                            }
                            i3 = i9 + i4;
                            break;
                        default:
                            int i10 = this.al;
                            if (charAt == 'D' || charAt == 'J') {
                                i6 = -3;
                            }
                            i3 = i6 + i10;
                            break;
                    }
                    if (i3 > this.am) {
                        this.am = i3;
                    }
                    this.al = i3;
                }
            }
            this.ak.s.a(i2, 0, this.p, b);
        }
        this.I.b(i2, b.f3595a);
    }

    public void a(int i2, String str, String str2, String str3, boolean z2) {
        int i3;
        this.ac = this.I.b;
        Item a2 = this.p.a(str, str2, str3, z2);
        int i4 = a2.c;
        if (this.ak != null) {
            if (this.ah == 0 || this.ah == 1) {
                this.ak.s.a(i2, 0, this.p, a2);
            } else {
                if (i4 == 0) {
                    i4 = Type.f(str3);
                    a2.c = i4;
                }
                if (i2 == 184) {
                    i3 = (this.al - (i4 >> 2)) + (i4 & 3) + 1;
                } else {
                    i3 = (this.al - (i4 >> 2)) + (i4 & 3);
                }
                if (i3 > this.am) {
                    this.am = i3;
                }
                this.al = i3;
            }
        }
        if (i2 == 185) {
            if (i4 == 0) {
                i4 = Type.f(str3);
                a2.c = i4;
            }
            this.I.b(185, a2.f3595a).a(i4 >> 2, 0);
            return;
        }
        this.I.b(i2, a2.f3595a);
    }

    public void a(String str, String str2, Handle handle, Object... objArr) {
        this.ac = this.I.b;
        Item a2 = this.p.a(str, str2, handle, objArr);
        int i2 = a2.c;
        if (this.ak != null) {
            if (this.ah == 0 || this.ah == 1) {
                this.ak.s.a((int) Opcodes.cW, 0, this.p, a2);
            } else {
                if (i2 == 0) {
                    i2 = Type.f(str2);
                    a2.c = i2;
                }
                int i3 = (this.al - (i2 >> 2)) + (i2 & 3) + 1;
                if (i3 > this.am) {
                    this.am = i3;
                }
                this.al = i3;
            }
        }
        this.I.b(Opcodes.cW, a2.f3595a);
        this.I.b(0);
    }

    public void a(int i2, Label label) {
        boolean z2 = i2 >= 200;
        if (z2) {
            i2 -= 33;
        }
        this.ac = this.I.b;
        Label label2 = null;
        if (this.ak != null) {
            if (this.ah == 0) {
                this.ak.s.a(i2, 0, (ClassWriter) null, (Item) null);
                label.b().n |= 16;
                c(0, label);
                if (i2 != 167) {
                    label2 = new Label();
                }
            } else if (this.ah == 1) {
                this.ak.s.a(i2, 0, (ClassWriter) null, (Item) null);
            } else if (i2 == 168) {
                if ((label.n & 512) == 0) {
                    label.n |= 512;
                    this.ag++;
                }
                this.ak.n |= 128;
                c(this.al + 1, label);
                label2 = new Label();
            } else {
                this.al += Frame.w[i2];
                c(this.al, label);
            }
        }
        if ((label.n & 2) != 0 && label.p - this.I.b < -32768) {
            if (i2 == 167) {
                this.I.a(200);
            } else if (i2 == 168) {
                this.I.a(201);
            } else {
                if (label2 != null) {
                    label2.n |= 16;
                }
                this.I.a(i2 <= 166 ? ((i2 + 1) ^ 1) - 1 : i2 ^ 1);
                this.I.b(8);
                this.I.a(200);
            }
            label.a(this, this.I, this.I.b - 1, true);
        } else if (z2) {
            this.I.a(i2 + 33);
            label.a(this, this.I, this.I.b - 1, true);
        } else {
            this.I.a(i2);
            label.a(this, this.I, this.I.b - 1, false);
        }
        if (this.ak != null) {
            if (label2 != null) {
                a(label2);
            }
            if (i2 == 167) {
                e();
            }
        }
    }

    public void a(Label label) {
        this.p.am |= label.a(this, this.I.b, this.I.f3586a);
        if ((label.n & 1) == 0) {
            if (this.ah == 0) {
                if (this.ak != null) {
                    if (label.p == this.ak.p) {
                        this.ak.n |= label.n & 16;
                        label.s = this.ak.s;
                        return;
                    }
                    c(0, label);
                }
                this.ak = label;
                if (label.s == null) {
                    label.s = new Frame();
                    label.s.x = label;
                }
                if (this.aj != null) {
                    if (label.p == this.aj.p) {
                        this.aj.n |= label.n & 16;
                        label.s = this.aj.s;
                        this.ak = this.aj;
                        return;
                    }
                    this.aj.t = label;
                }
                this.aj = label;
            } else if (this.ah == 1) {
                if (this.ak == null) {
                    this.ak = label;
                } else {
                    this.ak.s.x = label;
                }
            } else if (this.ah == 2) {
                if (this.ak != null) {
                    this.ak.r = this.am;
                    c(this.al, label);
                }
                this.ak = label;
                this.al = 0;
                this.am = 0;
                if (this.aj != null) {
                    this.aj.t = label;
                }
                this.aj = label;
            }
        }
    }

    public void a(Object obj) {
        int i2;
        this.ac = this.I.b;
        Item a2 = this.p.a(obj);
        if (this.ak != null) {
            if (this.ah == 0 || this.ah == 1) {
                this.ak.s.a(18, 0, this.p, a2);
            } else {
                if (a2.b == 5 || a2.b == 6) {
                    i2 = this.al + 2;
                } else {
                    i2 = this.al + 1;
                }
                if (i2 > this.am) {
                    this.am = i2;
                }
                this.al = i2;
            }
        }
        int i3 = a2.f3595a;
        if (a2.b == 5 || a2.b == 6) {
            this.I.b(20, i3);
        } else if (i3 >= 256) {
            this.I.b(19, i3);
        } else {
            this.I.a(18, i3);
        }
    }

    public void c(int i2, int i3) {
        int i4;
        this.ac = this.I.b;
        if (this.ak != null && (this.ah == 0 || this.ah == 1)) {
            this.ak.s.a(132, i2, (ClassWriter) null, (Item) null);
        }
        if (this.ah != 3 && (i4 = i2 + 1) > this.K) {
            this.K = i4;
        }
        if (i2 > 255 || i3 > 127 || i3 < -128) {
            this.I.a((int) Downloads.STATUS_QUEUED_FOR_WIFI).b(132, i2).b(i3);
        } else {
            this.I.a(132).a(i2, i3);
        }
    }

    public void a(int i2, int i3, Label label, Label... labelArr) {
        this.ac = this.I.b;
        int i4 = this.I.b;
        this.I.a(170);
        this.I.a((byte[]) null, 0, (4 - (this.I.b % 4)) % 4);
        label.a(this, this.I, i4, true);
        this.I.c(i2).c(i3);
        for (Label a2 : labelArr) {
            a2.a(this, this.I, i4, true);
        }
        a(label, labelArr);
    }

    public void a(Label label, int[] iArr, Label[] labelArr) {
        this.ac = this.I.b;
        int i2 = this.I.b;
        this.I.a(171);
        this.I.a((byte[]) null, 0, (4 - (this.I.b % 4)) % 4);
        label.a(this, this.I, i2, true);
        this.I.c(labelArr.length);
        for (int i3 = 0; i3 < labelArr.length; i3++) {
            this.I.c(iArr[i3]);
            labelArr[i3].a(this, this.I, i2, true);
        }
        a(label, labelArr);
    }

    private void a(Label label, Label[] labelArr) {
        if (this.ak != null) {
            if (this.ah == 0) {
                this.ak.s.a(171, 0, (ClassWriter) null, (Item) null);
                c(0, label);
                label.b().n |= 16;
                for (int i2 = 0; i2 < labelArr.length; i2++) {
                    c(0, labelArr[i2]);
                    labelArr[i2].b().n |= 16;
                }
            } else {
                this.al--;
                c(this.al, label);
                for (Label c2 : labelArr) {
                    c(this.al, c2);
                }
            }
            e();
        }
    }

    public void b(String str, int i2) {
        this.ac = this.I.b;
        Item b = this.p.b(str);
        if (this.ak != null) {
            if (this.ah == 0 || this.ah == 1) {
                this.ak.s.a((int) Opcodes.dg, i2, this.p, b);
            } else {
                this.al += 1 - i2;
            }
        }
        this.I.b(Opcodes.dg, b.f3595a).a(i2);
    }

    public AnnotationVisitor b(int i2, TypePath typePath, String str, boolean z2) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.a((i2 & Color.h) | (this.ac << 8), typePath, byteVector);
        byteVector.b(this.p.a(str)).b(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.p, true, byteVector, byteVector, byteVector.b - 2);
        if (z2) {
            annotationWriter.c = this.ad;
            this.ad = annotationWriter;
        } else {
            annotationWriter.c = this.ae;
            this.ae = annotationWriter;
        }
        return annotationWriter;
    }

    public void a(Label label, Label label2, Label label3, String str) {
        this.R++;
        Handler handler = new Handler();
        handler.f3594a = label;
        handler.b = label2;
        handler.c = label3;
        handler.d = str;
        handler.e = str != null ? this.p.c(str) : 0;
        if (this.T == null) {
            this.S = handler;
        } else {
            this.T.f = handler;
        }
        this.T = handler;
    }

    public AnnotationVisitor c(int i2, TypePath typePath, String str, boolean z2) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.a(i2, typePath, byteVector);
        byteVector.b(this.p.a(str)).b(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.p, true, byteVector, byteVector, byteVector.b - 2);
        if (z2) {
            annotationWriter.c = this.ad;
            this.ad = annotationWriter;
        } else {
            annotationWriter.c = this.ae;
            this.ae = annotationWriter;
        }
        return annotationWriter;
    }

    public void a(String str, String str2, String str3, Label label, Label label2, int i2) {
        int i3 = 1;
        if (str3 != null) {
            if (this.Z == null) {
                this.Z = new ByteVector();
            }
            this.Y++;
            this.Z.b(label.p).b(label2.p - label.p).b(this.p.a(str)).b(this.p.a(str3)).b(i2);
        }
        if (this.X == null) {
            this.X = new ByteVector();
        }
        this.W++;
        this.X.b(label.p).b(label2.p - label.p).b(this.p.a(str)).b(this.p.a(str2)).b(i2);
        if (this.ah != 3) {
            char charAt = str2.charAt(0);
            if (charAt == 'J' || charAt == 'D') {
                i3 = 2;
            }
            int i4 = i2 + i3;
            if (i4 > this.K) {
                this.K = i4;
            }
        }
    }

    public AnnotationVisitor a(int i2, TypePath typePath, Label[] labelArr, Label[] labelArr2, int[] iArr, String str, boolean z2) {
        ByteVector byteVector = new ByteVector();
        byteVector.a(i2 >>> 24).b(labelArr.length);
        for (int i3 = 0; i3 < labelArr.length; i3++) {
            byteVector.b(labelArr[i3].p).b(labelArr2[i3].p - labelArr[i3].p).b(iArr[i3]);
        }
        if (typePath == null) {
            byteVector.a(0);
        } else {
            byteVector.a(typePath.e, typePath.f, (typePath.e[typePath.f] * 2) + 1);
        }
        byteVector.b(this.p.a(str)).b(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.p, true, byteVector, byteVector, byteVector.b - 2);
        if (z2) {
            annotationWriter.c = this.ad;
            this.ad = annotationWriter;
        } else {
            annotationWriter.c = this.ae;
            this.ae = annotationWriter;
        }
        return annotationWriter;
    }

    public void b(int i2, Label label) {
        if (this.ab == null) {
            this.ab = new ByteVector();
        }
        this.aa++;
        this.ab.b(label.p);
        this.ab.b(i2);
    }

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void d(int r14, int r15) {
        /*
            r13 = this;
            int r0 = r13.ah
            r1 = 0
            r2 = 32
            r3 = 0
            r4 = 1
            if (r0 != 0) goto L_0x0118
            org.jacoco.agent.rt.internal_8ff85ea.asm.Handler r14 = r13.S
        L_0x000b:
            r15 = 24117248(0x1700000, float:4.4081038E-38)
            if (r14 == 0) goto L_0x004e
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r0 = r14.f3594a
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r0 = r0.b()
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r5 = r14.c
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r5 = r5.b()
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r6 = r14.b
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r6 = r6.b()
            java.lang.String r7 = r14.d
            if (r7 != 0) goto L_0x0028
            java.lang.String r7 = "java/lang/Throwable"
            goto L_0x002a
        L_0x0028:
            java.lang.String r7 = r14.d
        L_0x002a:
            org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter r8 = r13.p
            int r7 = r8.f(r7)
            r15 = r15 | r7
            int r7 = r5.n
            r7 = r7 | 16
            r5.n = r7
        L_0x0037:
            if (r0 == r6) goto L_0x004b
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r7 = new org.jacoco.agent.rt.internal_8ff85ea.asm.Edge
            r7.<init>()
            r7.c = r15
            r7.d = r5
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r8 = r0.u
            r7.e = r8
            r0.u = r7
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r0 = r0.t
            goto L_0x0037
        L_0x004b:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Handler r14 = r14.f
            goto L_0x000b
        L_0x004e:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r14 = r13.ai
            org.jacoco.agent.rt.internal_8ff85ea.asm.Frame r14 = r14.s
            org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter r0 = r13.p
            int r5 = r13.v
            java.lang.String r6 = r13.y
            org.jacoco.agent.rt.internal_8ff85ea.asm.Type[] r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Type.d((java.lang.String) r6)
            int r7 = r13.K
            r14.a((org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter) r0, (int) r5, (org.jacoco.agent.rt.internal_8ff85ea.asm.Type[]) r6, (int) r7)
            r13.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Frame) r14)
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r14 = r13.ai
            r0 = 0
        L_0x0067:
            if (r14 == 0) goto L_0x00ab
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r5 = r14.v
            r14.v = r1
            org.jacoco.agent.rt.internal_8ff85ea.asm.Frame r6 = r14.s
            int r7 = r14.n
            r7 = r7 & 16
            if (r7 == 0) goto L_0x007a
            int r7 = r14.n
            r7 = r7 | r2
            r14.n = r7
        L_0x007a:
            int r7 = r14.n
            r7 = r7 | 64
            r14.n = r7
            int[] r7 = r6.z
            int r7 = r7.length
            int r8 = r14.r
            int r7 = r7 + r8
            if (r7 <= r0) goto L_0x0089
            r0 = r7
        L_0x0089:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r14 = r14.u
        L_0x008b:
            if (r14 == 0) goto L_0x00a9
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r7 = r14.d
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r7 = r7.b()
            org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter r8 = r13.p
            org.jacoco.agent.rt.internal_8ff85ea.asm.Frame r9 = r7.s
            int r10 = r14.c
            boolean r8 = r6.a(r8, r9, r10)
            if (r8 == 0) goto L_0x00a6
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r8 = r7.v
            if (r8 != 0) goto L_0x00a6
            r7.v = r5
            r5 = r7
        L_0x00a6:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r14 = r14.e
            goto L_0x008b
        L_0x00a9:
            r14 = r5
            goto L_0x0067
        L_0x00ab:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r14 = r13.ai
        L_0x00ad:
            if (r14 == 0) goto L_0x0106
            org.jacoco.agent.rt.internal_8ff85ea.asm.Frame r1 = r14.s
            int r5 = r14.n
            r5 = r5 & r2
            if (r5 == 0) goto L_0x00b9
            r13.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Frame) r1)
        L_0x00b9:
            int r1 = r14.n
            r1 = r1 & 64
            if (r1 != 0) goto L_0x0103
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r1 = r14.t
            int r5 = r14.p
            if (r1 != 0) goto L_0x00ca
            org.jacoco.agent.rt.internal_8ff85ea.asm.ByteVector r6 = r13.I
            int r6 = r6.b
            goto L_0x00cc
        L_0x00ca:
            int r6 = r1.p
        L_0x00cc:
            int r6 = r6 - r4
            if (r6 < r5) goto L_0x0103
            int r0 = java.lang.Math.max(r0, r4)
            r7 = r5
        L_0x00d4:
            if (r7 >= r6) goto L_0x00df
            org.jacoco.agent.rt.internal_8ff85ea.asm.ByteVector r8 = r13.I
            byte[] r8 = r8.f3586a
            r8[r7] = r3
            int r7 = r7 + 1
            goto L_0x00d4
        L_0x00df:
            org.jacoco.agent.rt.internal_8ff85ea.asm.ByteVector r7 = r13.I
            byte[] r7 = r7.f3586a
            r8 = -65
            r7[r6] = r8
            int r5 = r13.a((int) r5, (int) r3, (int) r4)
            int[] r6 = r13.Q
            org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter r7 = r13.p
            java.lang.String r8 = "java/lang/Throwable"
            int r7 = r7.f(r8)
            r7 = r7 | r15
            r6[r5] = r7
            r13.g()
            org.jacoco.agent.rt.internal_8ff85ea.asm.Handler r5 = r13.S
            org.jacoco.agent.rt.internal_8ff85ea.asm.Handler r1 = org.jacoco.agent.rt.internal_8ff85ea.asm.Handler.a(r5, r14, r1)
            r13.S = r1
        L_0x0103:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r14 = r14.t
            goto L_0x00ad
        L_0x0106:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Handler r14 = r13.S
            r13.R = r3
        L_0x010a:
            if (r14 == 0) goto L_0x0114
            int r15 = r13.R
            int r15 = r15 + r4
            r13.R = r15
            org.jacoco.agent.rt.internal_8ff85ea.asm.Handler r14 = r14.f
            goto L_0x010a
        L_0x0114:
            r13.J = r0
            goto L_0x01fa
        L_0x0118:
            int r0 = r13.ah
            r5 = 2
            if (r0 != r5) goto L_0x01f6
            org.jacoco.agent.rt.internal_8ff85ea.asm.Handler r15 = r13.S
        L_0x011f:
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r15 == 0) goto L_0x0156
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r5 = r15.f3594a
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r6 = r15.c
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r7 = r15.b
        L_0x012a:
            if (r5 == r7) goto L_0x0153
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r8 = new org.jacoco.agent.rt.internal_8ff85ea.asm.Edge
            r8.<init>()
            r8.c = r0
            r8.d = r6
            int r9 = r5.n
            r9 = r9 & 128(0x80, float:1.794E-43)
            if (r9 != 0) goto L_0x0142
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r9 = r5.u
            r8.e = r9
            r5.u = r8
            goto L_0x0150
        L_0x0142:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r9 = r5.u
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r9 = r9.e
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r9 = r9.e
            r8.e = r9
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r9 = r5.u
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r9 = r9.e
            r9.e = r8
        L_0x0150:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r5 = r5.t
            goto L_0x012a
        L_0x0153:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Handler r15 = r15.f
            goto L_0x011f
        L_0x0156:
            int r15 = r13.ag
            if (r15 <= 0) goto L_0x01b5
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r15 = r13.ai
            int r5 = r13.ag
            r6 = 1
            r15.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r1, (long) r6, (int) r5)
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r15 = r13.ai
            r5 = 0
        L_0x0166:
            if (r15 == 0) goto L_0x018e
            int r8 = r15.n
            r8 = r8 & 128(0x80, float:1.794E-43)
            if (r8 == 0) goto L_0x018b
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r8 = r15.u
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r8 = r8.e
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r8 = r8.d
            int r9 = r8.n
            r9 = r9 & 1024(0x400, float:1.435E-42)
            if (r9 != 0) goto L_0x018b
            int r5 = r5 + 1
            long r9 = (long) r5
            r11 = 32
            long r9 = r9 / r11
            long r9 = r9 << r2
            int r11 = r5 % 32
            long r11 = r6 << r11
            long r9 = r9 | r11
            int r11 = r13.ag
            r8.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r1, (long) r9, (int) r11)
        L_0x018b:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r15 = r15.t
            goto L_0x0166
        L_0x018e:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r15 = r13.ai
        L_0x0190:
            if (r15 == 0) goto L_0x01b5
            int r1 = r15.n
            r1 = r1 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x01b2
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r1 = r13.ai
        L_0x019a:
            if (r1 == 0) goto L_0x01a5
            int r2 = r1.n
            r2 = r2 & -2049(0xfffffffffffff7ff, float:NaN)
            r1.n = r2
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r1 = r1.t
            goto L_0x019a
        L_0x01a5:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r1 = r15.u
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r1 = r1.e
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r1 = r1.d
            r5 = 0
            int r2 = r13.ag
            r1.a((org.jacoco.agent.rt.internal_8ff85ea.asm.Label) r15, (long) r5, (int) r2)
        L_0x01b2:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r15 = r15.t
            goto L_0x0190
        L_0x01b5:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r15 = r13.ai
        L_0x01b7:
            if (r15 == 0) goto L_0x01ef
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r1 = r15.v
            int r2 = r15.q
            int r5 = r15.r
            int r5 = r5 + r2
            if (r5 <= r3) goto L_0x01c3
            r3 = r5
        L_0x01c3:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r5 = r15.u
            int r15 = r15.n
            r15 = r15 & 128(0x80, float:1.794E-43)
            if (r15 == 0) goto L_0x01cd
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r5 = r5.e
        L_0x01cd:
            r15 = r1
        L_0x01ce:
            if (r5 == 0) goto L_0x01b7
            org.jacoco.agent.rt.internal_8ff85ea.asm.Label r1 = r5.d
            int r6 = r1.n
            r6 = r6 & 8
            if (r6 != 0) goto L_0x01ec
            int r6 = r5.c
            if (r6 != r0) goto L_0x01de
            r6 = 1
            goto L_0x01e1
        L_0x01de:
            int r6 = r5.c
            int r6 = r6 + r2
        L_0x01e1:
            r1.q = r6
            int r6 = r1.n
            r6 = r6 | 8
            r1.n = r6
            r1.v = r15
            r15 = r1
        L_0x01ec:
            org.jacoco.agent.rt.internal_8ff85ea.asm.Edge r5 = r5.e
            goto L_0x01ce
        L_0x01ef:
            int r14 = java.lang.Math.max(r14, r3)
            r13.J = r14
            goto L_0x01fa
        L_0x01f6:
            r13.J = r14
            r13.K = r15
        L_0x01fa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jacoco.agent.rt.internal_8ff85ea.asm.MethodWriter.d(int, int):void");
    }

    private void c(int i2, Label label) {
        Edge edge = new Edge();
        edge.c = i2;
        edge.d = label;
        edge.e = this.ak.u;
        this.ak.u = edge;
    }

    private void e() {
        if (this.ah == 0) {
            Label label = new Label();
            label.s = new Frame();
            label.s.x = label;
            label.a(this, this.I.b, this.I.f3586a);
            this.aj.t = label;
            this.aj = label;
        } else {
            this.ak.r = this.am;
        }
        if (this.ah != 1) {
            this.ak = null;
        }
    }

    private void a(Frame frame) {
        int[] iArr = frame.y;
        int[] iArr2 = frame.z;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < iArr.length) {
            int i6 = iArr[i3];
            if (i6 == 16777216) {
                i4++;
            } else {
                i5 += i4 + 1;
                i4 = 0;
            }
            if (i6 == 16777220 || i6 == 16777219) {
                i3++;
            }
            i3++;
        }
        int i7 = 0;
        int i8 = 0;
        while (i7 < iArr2.length) {
            int i9 = iArr2[i7];
            i8++;
            if (i9 == 16777220 || i9 == 16777219) {
                i7++;
            }
            i7++;
        }
        int a2 = a(frame.x.p, i5, i8);
        int i10 = 0;
        while (i5 > 0) {
            int i11 = iArr[i10];
            int i12 = a2 + 1;
            this.Q[a2] = i11;
            if (i11 == 16777220 || i11 == 16777219) {
                i10++;
            }
            i10++;
            i5--;
            a2 = i12;
        }
        while (i2 < iArr2.length) {
            int i13 = iArr2[i2];
            int i14 = a2 + 1;
            this.Q[a2] = i13;
            if (i13 == 16777220 || i13 == 16777219) {
                i2++;
            }
            i2++;
            a2 = i14;
        }
        g();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005d, code lost:
        if (r10.y.charAt(r5) != 'L') goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0061, code lost:
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0067, code lost:
        if (r10.y.charAt(r5) == ';') goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006c, code lost:
        r5 = r5 + 1;
        r10.Q[r4] = r10.p.f(r10.y.substring(r0, r5)) | 24117248;
        r0 = r5;
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0038, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void f() {
        /*
            r10 = this;
            java.lang.String r0 = r10.y
            int r0 = r0.length()
            r1 = 1
            int r0 = r0 + r1
            r2 = 0
            int r0 = r10.a((int) r2, (int) r0, (int) r2)
            int r2 = r10.v
            r2 = r2 & 8
            r3 = 24117248(0x1700000, float:4.4081038E-38)
            if (r2 != 0) goto L_0x0036
            int r2 = r10.v
            r4 = 524288(0x80000, float:7.34684E-40)
            r2 = r2 & r4
            if (r2 != 0) goto L_0x002e
            int[] r2 = r10.Q
            int r4 = r0 + 1
            org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter r5 = r10.p
            org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter r6 = r10.p
            java.lang.String r6 = r6.af
            int r5 = r5.f(r6)
            r5 = r5 | r3
            r2[r0] = r5
            goto L_0x0037
        L_0x002e:
            int[] r2 = r10.Q
            int r4 = r0 + 1
            r5 = 6
            r2[r0] = r5
            goto L_0x0037
        L_0x0036:
            r4 = r0
        L_0x0037:
            r0 = 1
        L_0x0038:
            java.lang.String r2 = r10.y
            int r5 = r0 + 1
            char r2 = r2.charAt(r0)
            r6 = 59
            r7 = 3
            switch(r2) {
                case 66: goto L_0x00be;
                case 67: goto L_0x00be;
                case 68: goto L_0x00b7;
                case 70: goto L_0x00af;
                case 73: goto L_0x00be;
                case 74: goto L_0x00a7;
                case 76: goto L_0x0083;
                case 83: goto L_0x00be;
                case 90: goto L_0x00be;
                case 91: goto L_0x0048;
                default: goto L_0x0046;
            }
        L_0x0046:
            goto L_0x00c8
        L_0x0048:
            java.lang.String r2 = r10.y
            char r2 = r2.charAt(r5)
            r7 = 91
            if (r2 != r7) goto L_0x0055
            int r5 = r5 + 1
            goto L_0x0048
        L_0x0055:
            java.lang.String r2 = r10.y
            char r2 = r2.charAt(r5)
            r7 = 76
            if (r2 != r7) goto L_0x006c
            int r5 = r5 + 1
        L_0x0061:
            java.lang.String r2 = r10.y
            char r2 = r2.charAt(r5)
            if (r2 == r6) goto L_0x006c
            int r5 = r5 + 1
            goto L_0x0061
        L_0x006c:
            int[] r2 = r10.Q
            int r6 = r4 + 1
            org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter r7 = r10.p
            java.lang.String r8 = r10.y
            int r5 = r5 + r1
            java.lang.String r0 = r8.substring(r0, r5)
            int r0 = r7.f(r0)
            r0 = r0 | r3
            r2[r4] = r0
            r0 = r5
            r4 = r6
            goto L_0x0038
        L_0x0083:
            r0 = r5
        L_0x0084:
            java.lang.String r2 = r10.y
            char r2 = r2.charAt(r0)
            if (r2 == r6) goto L_0x008f
            int r0 = r0 + 1
            goto L_0x0084
        L_0x008f:
            int[] r2 = r10.Q
            int r6 = r4 + 1
            org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter r7 = r10.p
            java.lang.String r8 = r10.y
            int r9 = r0 + 1
            java.lang.String r0 = r8.substring(r5, r0)
            int r0 = r7.f(r0)
            r0 = r0 | r3
            r2[r4] = r0
            r4 = r6
            r0 = r9
            goto L_0x0038
        L_0x00a7:
            int[] r0 = r10.Q
            int r2 = r4 + 1
            r6 = 4
            r0[r4] = r6
            goto L_0x00c4
        L_0x00af:
            int[] r0 = r10.Q
            int r2 = r4 + 1
            r6 = 2
            r0[r4] = r6
            goto L_0x00c4
        L_0x00b7:
            int[] r0 = r10.Q
            int r2 = r4 + 1
            r0[r4] = r7
            goto L_0x00c4
        L_0x00be:
            int[] r0 = r10.Q
            int r2 = r4 + 1
            r0[r4] = r1
        L_0x00c4:
            r4 = r2
            r0 = r5
            goto L_0x0038
        L_0x00c8:
            int[] r0 = r10.Q
            int r4 = r4 - r7
            r0[r1] = r4
            r10.g()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jacoco.agent.rt.internal_8ff85ea.asm.MethodWriter.f():void");
    }

    private int a(int i2, int i3, int i4) {
        int i5 = i3 + 3 + i4;
        if (this.Q == null || this.Q.length < i5) {
            this.Q = new int[i5];
        }
        this.Q[0] = i2;
        this.Q[1] = i3;
        this.Q[2] = i4;
        return 3;
    }

    private void g() {
        if (this.P != null) {
            if (this.N == null) {
                this.N = new ByteVector();
            }
            h();
            this.M++;
        }
        this.P = this.Q;
        this.Q = null;
    }

    private void h() {
        int i2;
        int i3;
        char c2;
        char c3;
        int i4 = this.Q[1];
        int i5 = this.Q[2];
        int i6 = 0;
        if ((this.p.V & 65535) < 50) {
            this.N.b(this.Q[0]).b(i4);
            int i7 = i4 + 3;
            e(3, i7);
            this.N.b(i5);
            e(i7, i5 + i7);
            return;
        }
        int i8 = this.P[1];
        if (this.M == 0) {
            i2 = this.Q[0];
        } else {
            i2 = (this.Q[0] - this.P[0]) - 1;
        }
        if (i5 == 0) {
            int i9 = i4 - i8;
            switch (i9) {
                case -3:
                case -2:
                case -1:
                    i8 = i4;
                    c3 = 248;
                    break;
                case 0:
                    if (i2 >= 64) {
                        c3 = 251;
                        break;
                    } else {
                        c3 = 0;
                        break;
                    }
                case 1:
                case 2:
                case 3:
                    c3 = 252;
                    break;
                default:
                    c3 = 255;
                    break;
            }
            char c4 = c3;
            i3 = i9;
            c2 = c4;
        } else {
            c2 = (i4 == i8 && i5 == 1) ? i2 < 63 ? TemplateDom.SEPARATOR : 247 : 255;
            i3 = 0;
        }
        if (c2 != 255) {
            int i10 = 3;
            while (true) {
                if (i6 < i8) {
                    if (this.Q[i10] != this.P[i10]) {
                        c2 = 255;
                    } else {
                        i10++;
                        i6++;
                    }
                }
            }
        }
        if (c2 == 0) {
            this.N.a(i2);
        } else if (c2 != '@') {
            switch (c2) {
                case g /*247*/:
                    this.N.a((int) g).b(i2);
                    e(i4 + 3, i4 + 4);
                    return;
                case h /*248*/:
                    this.N.a(i3 + i).b(i2);
                    return;
                default:
                    switch (c2) {
                        case i /*251*/:
                            this.N.a((int) i).b(i2);
                            return;
                        case j /*252*/:
                            this.N.a(i3 + i).b(i2);
                            e(i8 + 3, i4 + 3);
                            return;
                        default:
                            this.N.a(255).b(i2).b(i4);
                            int i11 = i4 + 3;
                            e(3, i11);
                            this.N.b(i5);
                            e(i11, i5 + i11);
                            return;
                    }
            }
        } else {
            this.N.a(i2 + 64);
            e(i4 + 3, i4 + 4);
        }
    }

    private void e(int i2, int i3) {
        while (i2 < i3) {
            int i4 = this.Q[i2];
            int i5 = -268435456 & i4;
            if (i5 == 0) {
                int i6 = i4 & 1048575;
                int i7 = i4 & 267386880;
                if (i7 == 24117248) {
                    this.N.a(7).b(this.p.c(this.p.ae[i6].e));
                } else if (i7 != 25165824) {
                    this.N.a(i6);
                } else {
                    this.N.a(8).b(this.p.ae[i6].c);
                }
            } else {
                StringBuilder sb = new StringBuilder();
                int i8 = i5 >> 28;
                while (true) {
                    int i9 = i8 - 1;
                    if (i8 <= 0) {
                        break;
                    }
                    sb.append(Operators.ARRAY_START);
                    i8 = i9;
                }
                if ((i4 & 267386880) != 24117248) {
                    int i10 = i4 & 15;
                    switch (i10) {
                        case 1:
                            sb.append('I');
                            break;
                        case 2:
                            sb.append('F');
                            break;
                        case 3:
                            sb.append('D');
                            break;
                        default:
                            switch (i10) {
                                case 9:
                                    sb.append('Z');
                                    break;
                                case 10:
                                    sb.append('B');
                                    break;
                                case 11:
                                    sb.append('C');
                                    break;
                                case 12:
                                    sb.append('S');
                                    break;
                                default:
                                    sb.append('J');
                                    break;
                            }
                    }
                } else {
                    sb.append('L');
                    sb.append(this.p.ae[i4 & 1048575].e);
                    sb.append(';');
                }
                this.N.a(7).b(this.p.c(sb.toString()));
            }
            i2++;
        }
    }

    private void b(Object obj) {
        if (obj instanceof String) {
            this.N.a(7).b(this.p.c((String) obj));
        } else if (obj instanceof Integer) {
            this.N.a(((Integer) obj).intValue());
        } else {
            this.N.a(8).b(((Label) obj).p);
        }
    }

    /* access modifiers changed from: package-private */
    public final int d() {
        int i2;
        if (this.r != 0) {
            return this.s + 6;
        }
        if (this.I.b <= 0) {
            i2 = 8;
        } else if (this.I.b <= 65535) {
            this.p.a("Code");
            i2 = this.I.b + 18 + (this.R * 8) + 8;
            if (this.X != null) {
                this.p.a("LocalVariableTable");
                i2 += this.X.b + 8;
            }
            if (this.Z != null) {
                this.p.a("LocalVariableTypeTable");
                i2 += this.Z.b + 8;
            }
            if (this.ab != null) {
                this.p.a("LineNumberTable");
                i2 += this.ab.b + 8;
            }
            if (this.N != null) {
                this.p.a((this.p.V & 65535) >= 50 ? "StackMapTable" : "StackMap");
                i2 += this.N.b + 8;
            }
            if (this.ad != null) {
                this.p.a("RuntimeVisibleTypeAnnotations");
                i2 += this.ad.b() + 8;
            }
            if (this.ae != null) {
                this.p.a("RuntimeInvisibleTypeAnnotations");
                i2 += this.ae.b() + 8;
            }
            if (this.af != null) {
                i2 += this.af.b(this.p, this.I.f3586a, this.I.b, this.J, this.K);
            }
        } else {
            throw new RuntimeException("Method code too large!");
        }
        if (this.t > 0) {
            this.p.a("Exceptions");
            i2 += (this.t * 2) + 8;
        }
        if ((this.v & 4096) != 0 && ((65535 & this.p.V) < 49 || (this.v & 262144) != 0)) {
            this.p.a("Synthetic");
            i2 += 6;
        }
        if ((this.v & 131072) != 0) {
            this.p.a("Deprecated");
            i2 += 6;
        }
        if (this.q != null) {
            this.p.a("Signature");
            this.p.a(this.q);
            i2 += 8;
        }
        if (this.V != null) {
            this.p.a("MethodParameters");
            i2 += this.V.b + 7;
        }
        if (this.z != null) {
            this.p.a("AnnotationDefault");
            i2 += this.z.b + 6;
        }
        if (this.A != null) {
            this.p.a("RuntimeVisibleAnnotations");
            i2 += this.A.b() + 8;
        }
        if (this.B != null) {
            this.p.a("RuntimeInvisibleAnnotations");
            i2 += this.B.b() + 8;
        }
        if (this.C != null) {
            this.p.a("RuntimeVisibleTypeAnnotations");
            i2 += this.C.b() + 8;
        }
        if (this.D != null) {
            this.p.a("RuntimeInvisibleTypeAnnotations");
            i2 += this.D.b() + 8;
        }
        if (this.E != null) {
            this.p.a("RuntimeVisibleParameterAnnotations");
            int length = i2 + ((this.E.length - this.G) * 2) + 7;
            for (int length2 = this.E.length - 1; length2 >= this.G; length2--) {
                length = i2 + (this.E[length2] == null ? 0 : this.E[length2].b());
            }
        }
        if (this.F != null) {
            this.p.a("RuntimeInvisibleParameterAnnotations");
            int length3 = i2 + ((this.F.length - this.G) * 2) + 7;
            for (int length4 = this.F.length - 1; length4 >= this.G; length4--) {
                length3 = i2 + (this.F[length4] == null ? 0 : this.F[length4].b());
            }
        }
        return this.H != null ? i2 + this.H.b(this.p, (byte[]) null, 0, -1, -1) : i2;
    }

    /* access modifiers changed from: package-private */
    public final void a(ByteVector byteVector) {
        ByteVector byteVector2 = byteVector;
        byteVector2.b(((((this.v & 262144) / 64) | 917504) ^ -1) & this.v).b(this.w).b(this.x);
        if (this.r != 0) {
            byteVector2.a(this.p.U.k, this.r, this.s);
            return;
        }
        int i2 = this.I.b > 0 ? 1 : 0;
        if (this.t > 0) {
            i2++;
        }
        if ((this.v & 4096) != 0 && ((this.p.V & 65535) < 49 || (this.v & 262144) != 0)) {
            i2++;
        }
        if ((this.v & 131072) != 0) {
            i2++;
        }
        if (this.q != null) {
            i2++;
        }
        if (this.V != null) {
            i2++;
        }
        if (this.z != null) {
            i2++;
        }
        if (this.A != null) {
            i2++;
        }
        if (this.B != null) {
            i2++;
        }
        if (this.C != null) {
            i2++;
        }
        if (this.D != null) {
            i2++;
        }
        if (this.E != null) {
            i2++;
        }
        if (this.F != null) {
            i2++;
        }
        if (this.H != null) {
            i2 += this.H.d();
        }
        byteVector2.b(i2);
        if (this.I.b > 0) {
            int i3 = this.I.b + 12 + (this.R * 8);
            if (this.X != null) {
                i3 += this.X.b + 8;
            }
            if (this.Z != null) {
                i3 += this.Z.b + 8;
            }
            if (this.ab != null) {
                i3 += this.ab.b + 8;
            }
            if (this.N != null) {
                i3 += this.N.b + 8;
            }
            if (this.ad != null) {
                i3 += this.ad.b() + 8;
            }
            if (this.ae != null) {
                i3 += this.ae.b() + 8;
            }
            if (this.af != null) {
                i3 += this.af.b(this.p, this.I.f3586a, this.I.b, this.J, this.K);
            }
            byteVector2.b(this.p.a("Code")).c(i3);
            byteVector2.b(this.J).b(this.K);
            byteVector2.c(this.I.b).a(this.I.f3586a, 0, this.I.b);
            byteVector2.b(this.R);
            if (this.R > 0) {
                for (Handler handler = this.S; handler != null; handler = handler.f) {
                    byteVector2.b(handler.f3594a.p).b(handler.b.p).b(handler.c.p).b(handler.e);
                }
            }
            int i4 = this.X != null ? 1 : 0;
            if (this.Z != null) {
                i4++;
            }
            if (this.ab != null) {
                i4++;
            }
            if (this.N != null) {
                i4++;
            }
            if (this.ad != null) {
                i4++;
            }
            if (this.ae != null) {
                i4++;
            }
            if (this.af != null) {
                i4 += this.af.d();
            }
            byteVector2.b(i4);
            if (this.X != null) {
                byteVector2.b(this.p.a("LocalVariableTable"));
                byteVector2.c(this.X.b + 2).b(this.W);
                byteVector2.a(this.X.f3586a, 0, this.X.b);
            }
            if (this.Z != null) {
                byteVector2.b(this.p.a("LocalVariableTypeTable"));
                byteVector2.c(this.Z.b + 2).b(this.Y);
                byteVector2.a(this.Z.f3586a, 0, this.Z.b);
            }
            if (this.ab != null) {
                byteVector2.b(this.p.a("LineNumberTable"));
                byteVector2.c(this.ab.b + 2).b(this.aa);
                byteVector2.a(this.ab.f3586a, 0, this.ab.b);
            }
            if (this.N != null) {
                byteVector2.b(this.p.a((this.p.V & 65535) >= 50 ? "StackMapTable" : "StackMap"));
                byteVector2.c(this.N.b + 2).b(this.M);
                byteVector2.a(this.N.f3586a, 0, this.N.b);
            }
            if (this.ad != null) {
                byteVector2.b(this.p.a("RuntimeVisibleTypeAnnotations"));
                this.ad.a(byteVector2);
            }
            if (this.ae != null) {
                byteVector2.b(this.p.a("RuntimeInvisibleTypeAnnotations"));
                this.ae.a(byteVector2);
            }
            if (this.af != null) {
                this.af.a(this.p, this.I.f3586a, this.I.b, this.K, this.J, byteVector);
            }
        }
        if (this.t > 0) {
            byteVector2.b(this.p.a("Exceptions")).c((this.t * 2) + 2);
            byteVector2.b(this.t);
            for (int i5 = 0; i5 < this.t; i5++) {
                byteVector2.b(this.u[i5]);
            }
        }
        if ((this.v & 4096) != 0 && ((this.p.V & 65535) < 49 || (this.v & 262144) != 0)) {
            byteVector2.b(this.p.a("Synthetic")).c(0);
        }
        if ((this.v & 131072) != 0) {
            byteVector2.b(this.p.a("Deprecated")).c(0);
        }
        if (this.q != null) {
            byteVector2.b(this.p.a("Signature")).c(2).b(this.p.a(this.q));
        }
        if (this.V != null) {
            byteVector2.b(this.p.a("MethodParameters"));
            byteVector2.c(this.V.b + 1).a(this.U);
            byteVector2.a(this.V.f3586a, 0, this.V.b);
        }
        if (this.z != null) {
            byteVector2.b(this.p.a("AnnotationDefault"));
            byteVector2.c(this.z.b);
            byteVector2.a(this.z.f3586a, 0, this.z.b);
        }
        if (this.A != null) {
            byteVector2.b(this.p.a("RuntimeVisibleAnnotations"));
            this.A.a(byteVector2);
        }
        if (this.B != null) {
            byteVector2.b(this.p.a("RuntimeInvisibleAnnotations"));
            this.B.a(byteVector2);
        }
        if (this.C != null) {
            byteVector2.b(this.p.a("RuntimeVisibleTypeAnnotations"));
            this.C.a(byteVector2);
        }
        if (this.D != null) {
            byteVector2.b(this.p.a("RuntimeInvisibleTypeAnnotations"));
            this.D.a(byteVector2);
        }
        if (this.E != null) {
            byteVector2.b(this.p.a("RuntimeVisibleParameterAnnotations"));
            AnnotationWriter.a(this.E, this.G, byteVector2);
        }
        if (this.F != null) {
            byteVector2.b(this.p.a("RuntimeInvisibleParameterAnnotations"));
            AnnotationWriter.a(this.F, this.G, byteVector2);
        }
        if (this.H != null) {
            this.H.a(this.p, (byte[]) null, 0, -1, -1, byteVector);
        }
    }
}
