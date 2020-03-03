package org.jacoco.agent.rt.internal_8ff85ea.asm;

import com.taobao.weex.ui.view.refresh.circlebar.CircleProgressBar;
import com.tencent.tinker.loader.shareutil.ShareElfFile;

class Frame {
    private static final int B = 33554432;
    private static final int C = 50331648;

    /* renamed from: a  reason: collision with root package name */
    static final int f3592a = -268435456;
    static final int b = 268435456;
    static final int c = -268435456;
    static final int d = 251658240;
    static final int e = 8388608;
    static final int f = 8388607;
    static final int g = 267386880;
    static final int h = 1048575;
    static final int i = 16777216;
    static final int j = 24117248;
    static final int k = 25165824;
    static final int l = 16777216;
    static final int m = 16777225;
    static final int n = 16777226;
    static final int o = 16777227;
    static final int p = 16777228;
    static final int q = 16777217;
    static final int r = 16777218;
    static final int s = 16777219;
    static final int t = 16777220;
    static final int u = 16777221;
    static final int v = 16777222;
    static final int[] w;
    int A;
    private int[] D;
    private int[] E;
    private int F;
    private int[] G;
    Label x;
    int[] y;
    int[] z;

    Frame() {
    }

    static {
        int[] iArr = new int[202];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEFEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE".charAt(i2) - 'E';
        }
        w = iArr;
    }

    /* access modifiers changed from: package-private */
    public final void a(ClassWriter classWriter, int i2, Object[] objArr, int i3, Object[] objArr2) {
        for (int a2 = a(classWriter, i2, objArr, this.y); a2 < objArr.length; a2++) {
            this.y[a2] = 16777216;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            if (objArr2[i5] == Opcodes.af || objArr2[i5] == Opcodes.ae) {
                i4++;
            }
        }
        this.z = new int[(i4 + i3)];
        a(classWriter, i3, objArr2, this.z);
        this.A = 0;
        this.F = 0;
    }

    private static int a(ClassWriter classWriter, int i2, Object[] objArr, int[] iArr) {
        int i3;
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            if (objArr[i5] instanceof Integer) {
                i3 = i4 + 1;
                iArr[i4] = objArr[i5].intValue() | 16777216;
                if (objArr[i5] == Opcodes.af || objArr[i5] == Opcodes.ae) {
                    i4 = i3 + 1;
                    iArr[i3] = 16777216;
                }
            } else if (objArr[i5] instanceof String) {
                i3 = i4 + 1;
                iArr[i4] = b(classWriter, Type.b(objArr[i5]).i());
            } else {
                i3 = i4 + 1;
                iArr[i4] = k | classWriter.a("", objArr[i5].p);
            }
            i4 = i3;
        }
        return i4;
    }

    /* access modifiers changed from: package-private */
    public final void a(Frame frame) {
        this.y = frame.y;
        this.z = frame.z;
        this.D = frame.D;
        this.E = frame.E;
        this.A = frame.A;
        this.F = frame.F;
        this.G = frame.G;
    }

    private int a(int i2) {
        if (this.D == null || i2 >= this.D.length) {
            return i2 | B;
        }
        int i3 = this.D[i2];
        if (i3 != 0) {
            return i3;
        }
        int[] iArr = this.D;
        int i4 = B | i2;
        iArr[i2] = i4;
        return i4;
    }

    private void a(int i2, int i3) {
        if (this.D == null) {
            this.D = new int[10];
        }
        int length = this.D.length;
        if (i2 >= length) {
            int[] iArr = new int[Math.max(i2 + 1, length * 2)];
            System.arraycopy(this.D, 0, iArr, 0, length);
            this.D = iArr;
        }
        this.D[i2] = i3;
    }

    private void b(int i2) {
        if (this.E == null) {
            this.E = new int[10];
        }
        int length = this.E.length;
        if (this.A >= length) {
            int[] iArr = new int[Math.max(this.A + 1, length * 2)];
            System.arraycopy(this.E, 0, iArr, 0, length);
            this.E = iArr;
        }
        int[] iArr2 = this.E;
        int i3 = this.A;
        this.A = i3 + 1;
        iArr2[i3] = i2;
        int i4 = this.x.q + this.A;
        if (i4 > this.x.r) {
            this.x.r = i4;
        }
    }

    private void a(ClassWriter classWriter, String str) {
        int b2 = b(classWriter, str);
        if (b2 != 0) {
            b(b2);
            if (b2 == t || b2 == s) {
                b(16777216);
            }
        }
    }

    private static int b(ClassWriter classWriter, String str) {
        int indexOf = str.charAt(0) == '(' ? str.indexOf(41) + 1 : 0;
        char charAt = str.charAt(indexOf);
        int i2 = r;
        if (charAt == 'F') {
            return r;
        }
        if (charAt == 'L') {
            return classWriter.f(str.substring(indexOf + 1, str.length() - 1)) | j;
        }
        if (charAt != 'S') {
            if (charAt == 'V') {
                return 0;
            }
            if (charAt != 'Z') {
                switch (charAt) {
                    case 'B':
                    case 'C':
                        break;
                    case 'D':
                        return s;
                    default:
                        switch (charAt) {
                            case 'I':
                                break;
                            case 'J':
                                return t;
                            default:
                                int i3 = indexOf + 1;
                                while (str.charAt(i3) == '[') {
                                    i3++;
                                }
                                char charAt2 = str.charAt(i3);
                                if (charAt2 != 'F') {
                                    if (charAt2 == 'S') {
                                        i2 = p;
                                    } else if (charAt2 != 'Z') {
                                        switch (charAt2) {
                                            case 'B':
                                                i2 = n;
                                                break;
                                            case 'C':
                                                i2 = o;
                                                break;
                                            case 'D':
                                                i2 = s;
                                                break;
                                            default:
                                                switch (charAt2) {
                                                    case 'I':
                                                        i2 = q;
                                                        break;
                                                    case 'J':
                                                        i2 = t;
                                                        break;
                                                    default:
                                                        i2 = classWriter.f(str.substring(i3 + 1, str.length() - 1)) | j;
                                                        break;
                                                }
                                        }
                                    } else {
                                        i2 = m;
                                    }
                                }
                                return ((i3 - indexOf) << 28) | i2;
                        }
                }
            }
        }
        return q;
    }

    private int a() {
        if (this.A > 0) {
            int[] iArr = this.E;
            int i2 = this.A - 1;
            this.A = i2;
            return iArr[i2];
        }
        Label label = this.x;
        int i3 = label.q - 1;
        label.q = i3;
        return C | (-i3);
    }

    private void c(int i2) {
        if (this.A >= i2) {
            this.A -= i2;
            return;
        }
        this.x.q -= i2 - this.A;
        this.A = 0;
    }

    private void a(String str) {
        char charAt = str.charAt(0);
        if (charAt == '(') {
            c((Type.f(str) >> 2) - 1);
        } else if (charAt == 'J' || charAt == 'D') {
            c(2);
        } else {
            c(1);
        }
    }

    private void d(int i2) {
        if (this.G == null) {
            this.G = new int[2];
        }
        int length = this.G.length;
        if (this.F >= length) {
            int[] iArr = new int[Math.max(this.F + 1, length * 2)];
            System.arraycopy(this.G, 0, iArr, 0, length);
            this.G = iArr;
        }
        int[] iArr2 = this.G;
        int i3 = this.F;
        this.F = i3 + 1;
        iArr2[i3] = i2;
    }

    private int a(ClassWriter classWriter, int i2) {
        int i3;
        if (i2 == v) {
            i3 = classWriter.f(classWriter.af) | j;
        } else if ((-1048576 & i2) != k) {
            return i2;
        } else {
            i3 = classWriter.f(classWriter.ae[h & i2].e) | j;
        }
        for (int i4 = 0; i4 < this.F; i4++) {
            int i5 = this.G[i4];
            int i6 = -268435456 & i5;
            int i7 = d & i5;
            if (i7 == B) {
                i5 = this.y[i5 & f] + i6;
            } else if (i7 == C) {
                i5 = this.z[this.z.length - (i5 & f)] + i6;
            }
            if (i2 == i5) {
                return i3;
            }
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public final void a(ClassWriter classWriter, int i2, Type[] typeArr, int i3) {
        int i4;
        this.y = new int[i3];
        this.z = new int[0];
        int i5 = 1;
        if ((i2 & 8) != 0) {
            i5 = 0;
        } else if ((i2 & 524288) == 0) {
            this.y[0] = j | classWriter.f(classWriter.af);
        } else {
            this.y[0] = v;
        }
        for (Type i6 : typeArr) {
            int b2 = b(classWriter, i6.i());
            int i7 = i4 + 1;
            this.y[i4] = b2;
            if (b2 == t || b2 == s) {
                i4 = i7 + 1;
                this.y[i7] = 16777216;
            } else {
                i4 = i7;
            }
        }
        while (i4 < i3) {
            this.y[i4] = 16777216;
            i4++;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x032b, code lost:
        b(s);
        b(16777216);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0332, code lost:
        b(r);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0336, code lost:
        b(t);
        b(16777216);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x033d, code lost:
        b(q);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0243, code lost:
        c(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x02af, code lost:
        c(2);
        b(s);
        b(16777216);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x02c3, code lost:
        c(2);
        b(t);
        b(16777216);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r17, int r18, org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter r19, org.jacoco.agent.rt.internal_8ff85ea.asm.Item r20) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            r4 = r20
            r5 = 24117248(0x1700000, float:4.4081038E-38)
            r6 = 16777218(0x1000002, float:2.3509893E-38)
            r7 = 16777219(0x1000003, float:2.3509895E-38)
            r8 = 16777217(0x1000001, float:2.350989E-38)
            r9 = 16777220(0x1000004, float:2.3509898E-38)
            r10 = 16777216(0x1000000, float:2.3509887E-38)
            switch(r1) {
                case 0: goto L_0x0347;
                case 1: goto L_0x0341;
                case 2: goto L_0x033d;
                case 3: goto L_0x033d;
                case 4: goto L_0x033d;
                case 5: goto L_0x033d;
                case 6: goto L_0x033d;
                case 7: goto L_0x033d;
                case 8: goto L_0x033d;
                case 9: goto L_0x0336;
                case 10: goto L_0x0336;
                case 11: goto L_0x0332;
                case 12: goto L_0x0332;
                case 13: goto L_0x0332;
                case 14: goto L_0x032b;
                case 15: goto L_0x032b;
                case 16: goto L_0x033d;
                case 17: goto L_0x033d;
                case 18: goto L_0x02e0;
                default: goto L_0x001d;
            }
        L_0x001d:
            switch(r1) {
                case 21: goto L_0x033d;
                case 22: goto L_0x0336;
                case 23: goto L_0x0332;
                case 24: goto L_0x032b;
                case 25: goto L_0x02d7;
                default: goto L_0x0020;
            }
        L_0x0020:
            r11 = 8388608(0x800000, float:1.17549435E-38)
            r12 = 251658240(0xf000000, float:6.3108872E-30)
            r14 = 1
            switch(r1) {
                case 46: goto L_0x02ce;
                case 47: goto L_0x0139;
                case 48: goto L_0x02ba;
                case 49: goto L_0x0144;
                case 50: goto L_0x02a0;
                case 51: goto L_0x02ce;
                case 52: goto L_0x02ce;
                case 53: goto L_0x02ce;
                case 54: goto L_0x027d;
                case 55: goto L_0x0252;
                case 56: goto L_0x027d;
                case 57: goto L_0x0252;
                case 58: goto L_0x027d;
                default: goto L_0x0028;
            }
        L_0x0028:
            r11 = 3
            r12 = 91
            r15 = 0
            r13 = 4
            switch(r1) {
                case 79: goto L_0x024d;
                case 80: goto L_0x0248;
                case 81: goto L_0x024d;
                case 82: goto L_0x0248;
                case 83: goto L_0x024d;
                case 84: goto L_0x024d;
                case 85: goto L_0x024d;
                case 86: goto L_0x024d;
                case 87: goto L_0x0243;
                case 88: goto L_0x023d;
                case 89: goto L_0x0231;
                case 90: goto L_0x021e;
                case 91: goto L_0x0204;
                case 92: goto L_0x01ee;
                case 93: goto L_0x01d1;
                case 94: goto L_0x01ad;
                case 95: goto L_0x019d;
                case 96: goto L_0x0194;
                case 97: goto L_0x0189;
                case 98: goto L_0x0180;
                case 99: goto L_0x0175;
                case 100: goto L_0x0194;
                case 101: goto L_0x0189;
                case 102: goto L_0x0180;
                case 103: goto L_0x0175;
                case 104: goto L_0x0194;
                case 105: goto L_0x0189;
                case 106: goto L_0x0180;
                case 107: goto L_0x0175;
                case 108: goto L_0x0194;
                case 109: goto L_0x0189;
                case 110: goto L_0x0180;
                case 111: goto L_0x0175;
                case 112: goto L_0x0194;
                case 113: goto L_0x0189;
                case 114: goto L_0x0180;
                case 115: goto L_0x0175;
                case 116: goto L_0x0347;
                case 117: goto L_0x0347;
                case 118: goto L_0x0347;
                case 119: goto L_0x0347;
                case 120: goto L_0x0194;
                case 121: goto L_0x016a;
                case 122: goto L_0x0194;
                case 123: goto L_0x016a;
                case 124: goto L_0x0194;
                case 125: goto L_0x016a;
                case 126: goto L_0x0194;
                case 127: goto L_0x0189;
                case 128: goto L_0x0194;
                case 129: goto L_0x0189;
                case 130: goto L_0x0194;
                case 131: goto L_0x0189;
                case 132: goto L_0x0165;
                case 133: goto L_0x015a;
                case 134: goto L_0x0152;
                case 135: goto L_0x0147;
                case 136: goto L_0x0194;
                case 137: goto L_0x0180;
                case 138: goto L_0x0144;
                case 139: goto L_0x013c;
                case 140: goto L_0x015a;
                case 141: goto L_0x0147;
                case 142: goto L_0x0194;
                case 143: goto L_0x0139;
                case 144: goto L_0x0180;
                case 145: goto L_0x0347;
                case 146: goto L_0x0347;
                case 147: goto L_0x0347;
                case 148: goto L_0x0131;
                case 149: goto L_0x0194;
                case 150: goto L_0x0194;
                case 151: goto L_0x0131;
                case 152: goto L_0x0131;
                case 153: goto L_0x0243;
                case 154: goto L_0x0243;
                case 155: goto L_0x0243;
                case 156: goto L_0x0243;
                case 157: goto L_0x0243;
                case 158: goto L_0x0243;
                case 159: goto L_0x023d;
                case 160: goto L_0x023d;
                case 161: goto L_0x023d;
                case 162: goto L_0x023d;
                case 163: goto L_0x023d;
                case 164: goto L_0x023d;
                case 165: goto L_0x023d;
                case 166: goto L_0x023d;
                case 167: goto L_0x0347;
                case 168: goto L_0x0129;
                case 169: goto L_0x0129;
                case 170: goto L_0x0243;
                case 171: goto L_0x0243;
                case 172: goto L_0x0243;
                case 173: goto L_0x023d;
                case 174: goto L_0x0243;
                case 175: goto L_0x023d;
                case 176: goto L_0x0243;
                case 177: goto L_0x0347;
                case 178: goto L_0x0122;
                case 179: goto L_0x011b;
                case 180: goto L_0x0111;
                case 181: goto L_0x0107;
                case 182: goto L_0x00e2;
                case 183: goto L_0x00e2;
                case 184: goto L_0x00e2;
                case 185: goto L_0x00e2;
                case 186: goto L_0x00d6;
                case 187: goto L_0x00c8;
                case 188: goto L_0x0082;
                case 189: goto L_0x0057;
                case 190: goto L_0x013c;
                case 191: goto L_0x0243;
                case 192: goto L_0x003d;
                case 193: goto L_0x013c;
                case 194: goto L_0x0243;
                case 195: goto L_0x0243;
                default: goto L_0x0030;
            }
        L_0x0030:
            switch(r1) {
                case 198: goto L_0x0243;
                case 199: goto L_0x0243;
                default: goto L_0x0033;
            }
        L_0x0033:
            r0.c(r2)
            java.lang.String r1 = r4.e
            r0.a((org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter) r3, (java.lang.String) r1)
            goto L_0x0347
        L_0x003d:
            java.lang.String r1 = r4.e
            r16.a()
            char r2 = r1.charAt(r15)
            if (r2 != r12) goto L_0x004d
            r0.a((org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter) r3, (java.lang.String) r1)
            goto L_0x0347
        L_0x004d:
            int r1 = r3.f(r1)
            r1 = r1 | r5
            r0.b(r1)
            goto L_0x0347
        L_0x0057:
            java.lang.String r1 = r4.e
            r16.a()
            char r2 = r1.charAt(r15)
            if (r2 != r12) goto L_0x0076
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r12)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.a((org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter) r3, (java.lang.String) r1)
            goto L_0x0347
        L_0x0076:
            r2 = 292552704(0x11700000, float:1.8932662E-28)
            int r1 = r3.f(r1)
            r1 = r1 | r2
            r0.b(r1)
            goto L_0x0347
        L_0x0082:
            r16.a()
            switch(r2) {
                case 4: goto L_0x00c0;
                case 5: goto L_0x00b8;
                case 6: goto L_0x00b0;
                case 7: goto L_0x00a8;
                case 8: goto L_0x00a0;
                case 9: goto L_0x0098;
                case 10: goto L_0x0090;
                default: goto L_0x0088;
            }
        L_0x0088:
            r1 = 285212676(0x11000004, float:1.0097424E-28)
            r0.b(r1)
            goto L_0x0347
        L_0x0090:
            r1 = 285212673(0x11000001, float:1.0097421E-28)
            r0.b(r1)
            goto L_0x0347
        L_0x0098:
            r1 = 285212684(0x1100000c, float:1.0097434E-28)
            r0.b(r1)
            goto L_0x0347
        L_0x00a0:
            r1 = 285212682(0x1100000a, float:1.0097432E-28)
            r0.b(r1)
            goto L_0x0347
        L_0x00a8:
            r1 = 285212675(0x11000003, float:1.0097423E-28)
            r0.b(r1)
            goto L_0x0347
        L_0x00b0:
            r1 = 285212674(0x11000002, float:1.0097422E-28)
            r0.b(r1)
            goto L_0x0347
        L_0x00b8:
            r1 = 285212683(0x1100000b, float:1.0097433E-28)
            r0.b(r1)
            goto L_0x0347
        L_0x00c0:
            r1 = 285212681(0x11000009, float:1.009743E-28)
            r0.b(r1)
            goto L_0x0347
        L_0x00c8:
            r1 = 25165824(0x1800000, float:4.7019774E-38)
            java.lang.String r4 = r4.e
            int r2 = r3.a((java.lang.String) r4, (int) r2)
            r1 = r1 | r2
            r0.b(r1)
            goto L_0x0347
        L_0x00d6:
            java.lang.String r1 = r4.f
            r0.a((java.lang.String) r1)
            java.lang.String r1 = r4.f
            r0.a((org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter) r3, (java.lang.String) r1)
            goto L_0x0347
        L_0x00e2:
            java.lang.String r2 = r4.g
            r0.a((java.lang.String) r2)
            r2 = 184(0xb8, float:2.58E-43)
            if (r1 == r2) goto L_0x0100
            int r2 = r16.a()
            r5 = 183(0xb7, float:2.56E-43)
            if (r1 != r5) goto L_0x0100
            java.lang.String r1 = r4.f
            char r1 = r1.charAt(r15)
            r5 = 60
            if (r1 != r5) goto L_0x0100
            r0.d(r2)
        L_0x0100:
            java.lang.String r1 = r4.g
            r0.a((org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter) r3, (java.lang.String) r1)
            goto L_0x0347
        L_0x0107:
            java.lang.String r1 = r4.g
            r0.a((java.lang.String) r1)
            r16.a()
            goto L_0x0347
        L_0x0111:
            r0.c(r14)
            java.lang.String r1 = r4.g
            r0.a((org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter) r3, (java.lang.String) r1)
            goto L_0x0347
        L_0x011b:
            java.lang.String r1 = r4.g
            r0.a((java.lang.String) r1)
            goto L_0x0347
        L_0x0122:
            java.lang.String r1 = r4.g
            r0.a((org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter) r3, (java.lang.String) r1)
            goto L_0x0347
        L_0x0129:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "JSR/RET are not supported with computeFrames option"
            r1.<init>(r2)
            throw r1
        L_0x0131:
            r0.c(r13)
            r0.b(r8)
            goto L_0x0347
        L_0x0139:
            r1 = 2
            goto L_0x02c3
        L_0x013c:
            r0.c(r14)
            r0.b(r8)
            goto L_0x0347
        L_0x0144:
            r1 = 2
            goto L_0x02af
        L_0x0147:
            r0.c(r14)
            r0.b(r7)
            r0.b(r10)
            goto L_0x0347
        L_0x0152:
            r0.c(r14)
            r0.b(r6)
            goto L_0x0347
        L_0x015a:
            r0.c(r14)
            r0.b(r9)
            r0.b(r10)
            goto L_0x0347
        L_0x0165:
            r0.a((int) r2, (int) r8)
            goto L_0x0347
        L_0x016a:
            r0.c(r11)
            r0.b(r9)
            r0.b(r10)
            goto L_0x0347
        L_0x0175:
            r0.c(r13)
            r0.b(r7)
            r0.b(r10)
            goto L_0x0347
        L_0x0180:
            r1 = 2
            r0.c(r1)
            r0.b(r6)
            goto L_0x0347
        L_0x0189:
            r0.c(r13)
            r0.b(r9)
            r0.b(r10)
            goto L_0x0347
        L_0x0194:
            r1 = 2
            r0.c(r1)
            r0.b(r8)
            goto L_0x0347
        L_0x019d:
            int r1 = r16.a()
            int r2 = r16.a()
            r0.b(r1)
            r0.b(r2)
            goto L_0x0347
        L_0x01ad:
            int r1 = r16.a()
            int r2 = r16.a()
            int r3 = r16.a()
            int r4 = r16.a()
            r0.b(r2)
            r0.b(r1)
            r0.b(r4)
            r0.b(r3)
            r0.b(r2)
            r0.b(r1)
            goto L_0x0347
        L_0x01d1:
            int r1 = r16.a()
            int r2 = r16.a()
            int r3 = r16.a()
            r0.b(r2)
            r0.b(r1)
            r0.b(r3)
            r0.b(r2)
            r0.b(r1)
            goto L_0x0347
        L_0x01ee:
            int r1 = r16.a()
            int r2 = r16.a()
            r0.b(r2)
            r0.b(r1)
            r0.b(r2)
            r0.b(r1)
            goto L_0x0347
        L_0x0204:
            int r1 = r16.a()
            int r2 = r16.a()
            int r3 = r16.a()
            r0.b(r1)
            r0.b(r3)
            r0.b(r2)
            r0.b(r1)
            goto L_0x0347
        L_0x021e:
            int r1 = r16.a()
            int r2 = r16.a()
            r0.b(r1)
            r0.b(r2)
            r0.b(r1)
            goto L_0x0347
        L_0x0231:
            int r1 = r16.a()
            r0.b(r1)
            r0.b(r1)
            goto L_0x0347
        L_0x023d:
            r1 = 2
            r0.c(r1)
            goto L_0x0347
        L_0x0243:
            r0.c(r14)
            goto L_0x0347
        L_0x0248:
            r0.c(r13)
            goto L_0x0347
        L_0x024d:
            r0.c(r11)
            goto L_0x0347
        L_0x0252:
            r0.c(r14)
            int r1 = r16.a()
            r0.a((int) r2, (int) r1)
            int r1 = r2 + 1
            r0.a((int) r1, (int) r10)
            if (r2 <= 0) goto L_0x0347
            int r1 = r2 + -1
            int r2 = r0.a((int) r1)
            if (r2 == r9) goto L_0x0278
            if (r2 != r7) goto L_0x026e
            goto L_0x0278
        L_0x026e:
            r3 = r2 & r12
            if (r3 == r10) goto L_0x0347
            r2 = r2 | r11
            r0.a((int) r1, (int) r2)
            goto L_0x0347
        L_0x0278:
            r0.a((int) r1, (int) r10)
            goto L_0x0347
        L_0x027d:
            int r1 = r16.a()
            r0.a((int) r2, (int) r1)
            if (r2 <= 0) goto L_0x0347
            int r1 = r2 + -1
            int r2 = r0.a((int) r1)
            if (r2 == r9) goto L_0x029b
            if (r2 != r7) goto L_0x0291
            goto L_0x029b
        L_0x0291:
            r3 = r2 & r12
            if (r3 == r10) goto L_0x0347
            r2 = r2 | r11
            r0.a((int) r1, (int) r2)
            goto L_0x0347
        L_0x029b:
            r0.a((int) r1, (int) r10)
            goto L_0x0347
        L_0x02a0:
            r0.c(r14)
            int r1 = r16.a()
            r2 = -268435456(0xfffffffff0000000, float:-1.58456325E29)
            int r1 = r1 + r2
            r0.b(r1)
            goto L_0x0347
        L_0x02af:
            r0.c(r1)
            r0.b(r7)
            r0.b(r10)
            goto L_0x0347
        L_0x02ba:
            r1 = 2
            r0.c(r1)
            r0.b(r6)
            goto L_0x0347
        L_0x02c3:
            r0.c(r1)
            r0.b(r9)
            r0.b(r10)
            goto L_0x0347
        L_0x02ce:
            r1 = 2
            r0.c(r1)
            r0.b(r8)
            goto L_0x0347
        L_0x02d7:
            int r1 = r0.a((int) r2)
            r0.b(r1)
            goto L_0x0347
        L_0x02e0:
            int r1 = r4.b
            r2 = 16
            if (r1 == r2) goto L_0x0320
            switch(r1) {
                case 3: goto L_0x031c;
                case 4: goto L_0x0318;
                case 5: goto L_0x0311;
                case 6: goto L_0x030a;
                case 7: goto L_0x02ff;
                case 8: goto L_0x02f4;
                default: goto L_0x02e9;
            }
        L_0x02e9:
            java.lang.String r1 = "java/lang/invoke/MethodHandle"
            int r1 = r3.f(r1)
            r1 = r1 | r5
            r0.b(r1)
            goto L_0x0347
        L_0x02f4:
            java.lang.String r1 = "java/lang/String"
            int r1 = r3.f(r1)
            r1 = r1 | r5
            r0.b(r1)
            goto L_0x0347
        L_0x02ff:
            java.lang.String r1 = "java/lang/Class"
            int r1 = r3.f(r1)
            r1 = r1 | r5
            r0.b(r1)
            goto L_0x0347
        L_0x030a:
            r0.b(r7)
            r0.b(r10)
            goto L_0x0347
        L_0x0311:
            r0.b(r9)
            r0.b(r10)
            goto L_0x0347
        L_0x0318:
            r0.b(r6)
            goto L_0x0347
        L_0x031c:
            r0.b(r8)
            goto L_0x0347
        L_0x0320:
            java.lang.String r1 = "java/lang/invoke/MethodType"
            int r1 = r3.f(r1)
            r1 = r1 | r5
            r0.b(r1)
            goto L_0x0347
        L_0x032b:
            r0.b(r7)
            r0.b(r10)
            goto L_0x0347
        L_0x0332:
            r0.b(r6)
            goto L_0x0347
        L_0x0336:
            r0.b(r9)
            r0.b(r10)
            goto L_0x0347
        L_0x033d:
            r0.b(r8)
            goto L_0x0347
        L_0x0341:
            r1 = 16777221(0x1000005, float:2.35099E-38)
            r0.b(r1)
        L_0x0347:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jacoco.agent.rt.internal_8ff85ea.asm.Frame.a(int, int, org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter, org.jacoco.agent.rt.internal_8ff85ea.asm.Item):void");
    }

    /* access modifiers changed from: package-private */
    public final boolean a(ClassWriter classWriter, Frame frame, int i2) {
        boolean z2;
        int i3;
        int i4;
        boolean z3;
        int i5;
        ClassWriter classWriter2 = classWriter;
        Frame frame2 = frame;
        int i6 = i2;
        int length = this.y.length;
        int length2 = this.z.length;
        if (frame2.y == null) {
            frame2.y = new int[length];
            z2 = true;
        } else {
            z2 = false;
        }
        boolean z4 = z2;
        int i7 = 0;
        while (true) {
            i3 = ShareElfFile.SectionHeader.A;
            int i8 = 16777216;
            if (i7 >= length) {
                break;
            }
            if (this.D == null || i7 >= this.D.length) {
                i8 = this.y[i7];
            } else {
                int i9 = this.D[i7];
                if (i9 == 0) {
                    i8 = this.y[i7];
                } else {
                    int i10 = -268435456 & i9;
                    int i11 = d & i9;
                    if (i11 == 16777216) {
                        i8 = i9;
                    } else {
                        if (i11 == B) {
                            i5 = i10 + this.y[i9 & f];
                        } else {
                            i5 = i10 + this.z[length2 - (i9 & f)];
                        }
                        if ((i9 & 8388608) == 0 || !(i5 == t || i5 == s)) {
                            i8 = i5;
                        }
                    }
                }
            }
            if (this.G != null) {
                i8 = a(classWriter2, i8);
            }
            z4 |= a(classWriter2, i8, frame2.y, i7);
            i7++;
        }
        if (i6 > 0) {
            boolean z5 = z4;
            for (int i12 = 0; i12 < length; i12++) {
                z5 |= a(classWriter2, this.y[i12], frame2.y, i12);
            }
            if (frame2.z == null) {
                z3 = true;
                frame2.z = new int[1];
            } else {
                z3 = z5;
            }
            return a(classWriter2, i6, frame2.z, 0) | z3;
        }
        boolean z6 = true;
        int length3 = this.z.length + this.x.q;
        if (frame2.z == null) {
            frame2.z = new int[(this.A + length3)];
        } else {
            z6 = z4;
        }
        boolean z7 = z6;
        for (int i13 = 0; i13 < length3; i13++) {
            int i14 = this.z[i13];
            if (this.G != null) {
                i14 = a(classWriter2, i14);
            }
            z7 |= a(classWriter2, i14, frame2.z, i13);
        }
        int i15 = 0;
        while (i15 < this.A) {
            int i16 = this.E[i15];
            int i17 = i16 & i3;
            int i18 = i16 & d;
            if (i18 != 16777216) {
                if (i18 == B) {
                    i4 = i17 + this.y[i16 & f];
                } else {
                    i4 = i17 + this.z[length2 - (i16 & f)];
                }
                i16 = ((i16 & 8388608) == 0 || !(i4 == t || i4 == s)) ? i4 : 16777216;
            }
            if (this.G != null) {
                i16 = a(classWriter2, i16);
            }
            z7 |= a(classWriter2, i16, frame2.z, length3 + i15);
            i15++;
            i3 = ShareElfFile.SectionHeader.A;
        }
        return z7;
    }

    private static boolean a(ClassWriter classWriter, int i2, int[] iArr, int i3) {
        int i4 = iArr[i3];
        if (i4 == i2) {
            return false;
        }
        if ((268435455 & i2) == u) {
            if (i4 == u) {
                return false;
            }
            i2 = u;
        }
        if (i4 == 0) {
            iArr[i3] = i2;
            return true;
        }
        int i5 = i4 & g;
        int i6 = 16777216;
        int i7 = ShareElfFile.SectionHeader.A;
        if (i5 == j || (i4 & ShareElfFile.SectionHeader.A) != 0) {
            if (i2 == u) {
                return false;
            }
            if ((i2 & CircleProgressBar.DEFAULT_CIRCLE_COLOR) == (-1048576 & i4)) {
                i6 = i5 == j ? (i2 & ShareElfFile.SectionHeader.A) | j | classWriter.a(i2 & h, h & i4) : ((i4 & ShareElfFile.SectionHeader.A) + ShareElfFile.SectionHeader.A) | j | classWriter.f("java/lang/Object");
            } else {
                int i8 = i2 & g;
                if (i8 == j || (i2 & ShareElfFile.SectionHeader.A) != 0) {
                    int i9 = i2 & ShareElfFile.SectionHeader.A;
                    int i10 = ((i9 == 0 || i8 == j) ? 0 : ShareElfFile.SectionHeader.A) + i9;
                    int i11 = i4 & ShareElfFile.SectionHeader.A;
                    if (i11 == 0 || i5 == j) {
                        i7 = 0;
                    }
                    i6 = Math.min(i10, i7 + i11) | j | classWriter.f("java/lang/Object");
                }
            }
        } else if (i4 == u && ((i2 & g) == j || (i2 & ShareElfFile.SectionHeader.A) != 0)) {
            i6 = i2;
        }
        if (i4 == i6) {
            return false;
        }
        iArr[i3] = i6;
        return true;
    }
}
