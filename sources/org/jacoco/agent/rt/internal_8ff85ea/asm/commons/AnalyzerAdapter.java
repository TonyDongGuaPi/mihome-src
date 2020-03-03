package org.jacoco.agent.rt.internal_8ff85ea.asm.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Handle;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Type;

public class AnalyzerAdapter extends MethodVisitor {
    public List<Object> c;
    public List<Object> d;
    public Map<Object, Object> e;
    private List<Label> f;
    private int g;
    private int h;
    private String i;

    public AnalyzerAdapter(String str, int i2, String str2, String str3, MethodVisitor methodVisitor) {
        this(327680, str, i2, str2, str3, methodVisitor);
        if (getClass() != AnalyzerAdapter.class) {
            throw new IllegalStateException();
        }
    }

    protected AnalyzerAdapter(int i2, String str, int i3, String str2, String str3, MethodVisitor methodVisitor) {
        super(i2, methodVisitor);
        this.i = str;
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = new HashMap();
        if ((i3 & 8) == 0) {
            if ("<init>".equals(str2)) {
                this.c.add(Opcodes.ah);
            } else {
                this.c.add(str);
            }
        }
        Type[] d2 = Type.d(str3);
        for (int i4 = 0; i4 < d2.length; i4++) {
            switch (d2[i4].a()) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    this.c.add(Opcodes.ac);
                    break;
                case 6:
                    this.c.add(Opcodes.ad);
                    break;
                case 7:
                    this.c.add(Opcodes.af);
                    this.c.add(Opcodes.ab);
                    break;
                case 8:
                    this.c.add(Opcodes.ae);
                    this.c.add(Opcodes.ab);
                    break;
                case 9:
                    this.c.add(d2[i4].i());
                    break;
                default:
                    this.c.add(d2[i4].e());
                    break;
            }
        }
        this.h = this.c.size();
    }

    public void a(int i2, int i3, Object[] objArr, int i4, Object[] objArr2) {
        if (i2 == -1) {
            if (this.Q_ != null) {
                this.Q_.a(i2, i3, objArr, i4, objArr2);
            }
            if (this.c != null) {
                this.c.clear();
                this.d.clear();
            } else {
                this.c = new ArrayList();
                this.d = new ArrayList();
            }
            a(i3, objArr, this.c);
            a(i4, objArr2, this.d);
            this.g = Math.max(this.g, this.d.size());
            return;
        }
        throw new IllegalStateException("ClassReader.accept() should be called with EXPAND_FRAMES flag");
    }

    private static void a(int i2, Object[] objArr, List<Object> list) {
        for (int i3 = 0; i3 < i2; i3++) {
            Integer num = objArr[i3];
            list.add(num);
            if (num == Opcodes.af || num == Opcodes.ae) {
                list.add(Opcodes.ab);
            }
        }
    }

    public void e_(int i2) {
        if (this.Q_ != null) {
            this.Q_.e_(i2);
        }
        a(i2, 0, (String) null);
        if ((i2 >= 172 && i2 <= 177) || i2 == 191) {
            this.c = null;
            this.d = null;
        }
    }

    public void a(int i2, int i3) {
        if (this.Q_ != null) {
            this.Q_.a(i2, i3);
        }
        a(i2, i3, (String) null);
    }

    public void b(int i2, int i3) {
        if (this.Q_ != null) {
            this.Q_.b(i2, i3);
        }
        a(i2, i3, (String) null);
    }

    public void a(int i2, String str) {
        if (i2 == 187) {
            if (this.f == null) {
                Label label = new Label();
                this.f = new ArrayList(3);
                this.f.add(label);
                if (this.Q_ != null) {
                    this.Q_.a(label);
                }
            }
            for (int i3 = 0; i3 < this.f.size(); i3++) {
                this.e.put(this.f.get(i3), str);
            }
        }
        if (this.Q_ != null) {
            this.Q_.a(i2, str);
        }
        a(i2, 0, str);
    }

    public void a(int i2, String str, String str2, String str3) {
        if (this.Q_ != null) {
            this.Q_.a(i2, str, str2, str3);
        }
        a(i2, 0, str3);
    }

    @Deprecated
    public void b(int i2, String str, String str2, String str3) {
        if (this.P_ >= 327680) {
            super.b(i2, str, str2, str3);
        } else {
            b(i2, str, str2, str3, i2 == 185);
        }
    }

    public void a(int i2, String str, String str2, String str3, boolean z) {
        if (this.P_ < 327680) {
            super.a(i2, str, str2, str3, z);
        } else {
            b(i2, str, str2, str3, z);
        }
    }

    private void b(int i2, String str, String str2, String str3, boolean z) {
        Object obj;
        if (this.Q_ != null) {
            this.Q_.a(i2, str, str2, str3, z);
        }
        if (this.c == null) {
            this.f = null;
            return;
        }
        b(str3);
        if (i2 != 184) {
            Object d2 = d();
            if (i2 == 183) {
                if (str2.charAt(0) == '<') {
                    if (d2 == Opcodes.ah) {
                        obj = this.i;
                    } else {
                        obj = this.e.get(d2);
                    }
                    for (int i3 = 0; i3 < this.c.size(); i3++) {
                        if (this.c.get(i3) == d2) {
                            this.c.set(i3, obj);
                        }
                    }
                    for (int i4 = 0; i4 < this.d.size(); i4++) {
                        if (this.d.get(i4) == d2) {
                            this.d.set(i4, obj);
                        }
                    }
                }
            }
        }
        a(str3);
        this.f = null;
    }

    public void a(String str, String str2, Handle handle, Object... objArr) {
        if (this.Q_ != null) {
            this.Q_.a(str, str2, handle, objArr);
        }
        if (this.c == null) {
            this.f = null;
            return;
        }
        b(str2);
        a(str2);
        this.f = null;
    }

    public void a(int i2, Label label) {
        if (this.Q_ != null) {
            this.Q_.a(i2, label);
        }
        a(i2, 0, (String) null);
        if (i2 == 167) {
            this.c = null;
            this.d = null;
        }
    }

    public void a(Label label) {
        if (this.Q_ != null) {
            this.Q_.a(label);
        }
        if (this.f == null) {
            this.f = new ArrayList(3);
        }
        this.f.add(label);
    }

    public void a(Object obj) {
        if (this.Q_ != null) {
            this.Q_.a(obj);
        }
        if (this.c == null) {
            this.f = null;
            return;
        }
        if (obj instanceof Integer) {
            b((Object) Opcodes.ac);
        } else if (obj instanceof Long) {
            b((Object) Opcodes.af);
            b((Object) Opcodes.ab);
        } else if (obj instanceof Float) {
            b((Object) Opcodes.ad);
        } else if (obj instanceof Double) {
            b((Object) Opcodes.ae);
            b((Object) Opcodes.ab);
        } else if (obj instanceof String) {
            b((Object) "java/lang/String");
        } else if (obj instanceof Type) {
            int a2 = ((Type) obj).a();
            if (a2 == 10 || a2 == 9) {
                b((Object) "java/lang/Class");
            } else if (a2 == 11) {
                b((Object) "java/lang/invoke/MethodType");
            } else {
                throw new IllegalArgumentException();
            }
        } else if (obj instanceof Handle) {
            b((Object) "java/lang/invoke/MethodHandle");
        } else {
            throw new IllegalArgumentException();
        }
        this.f = null;
    }

    public void c(int i2, int i3) {
        if (this.Q_ != null) {
            this.Q_.c(i2, i3);
        }
        a(132, i2, (String) null);
    }

    public void a(int i2, int i3, Label label, Label... labelArr) {
        if (this.Q_ != null) {
            this.Q_.a(i2, i3, label, labelArr);
        }
        a(170, 0, (String) null);
        this.c = null;
        this.d = null;
    }

    public void a(Label label, int[] iArr, Label[] labelArr) {
        if (this.Q_ != null) {
            this.Q_.a(label, iArr, labelArr);
        }
        a(171, 0, (String) null);
        this.c = null;
        this.d = null;
    }

    public void b(String str, int i2) {
        if (this.Q_ != null) {
            this.Q_.b(str, i2);
        }
        a((int) Opcodes.dg, i2, str);
    }

    public void d(int i2, int i3) {
        if (this.Q_ != null) {
            this.g = Math.max(this.g, i2);
            this.h = Math.max(this.h, i3);
            this.Q_.d(this.g, this.h);
        }
    }

    private Object b(int i2) {
        this.h = Math.max(this.h, i2 + 1);
        return i2 < this.c.size() ? this.c.get(i2) : Opcodes.ab;
    }

    private void a(int i2, Object obj) {
        this.h = Math.max(this.h, i2 + 1);
        while (i2 >= this.c.size()) {
            this.c.add(Opcodes.ab);
        }
        this.c.set(i2, obj);
    }

    private void b(Object obj) {
        this.d.add(obj);
        this.g = Math.max(this.g, this.d.size());
    }

    private void a(String str) {
        int i2 = 0;
        if (str.charAt(0) == '(') {
            i2 = str.indexOf(41) + 1;
        }
        switch (str.charAt(i2)) {
            case 'B':
            case 'C':
            case 'I':
            case 'S':
            case 'Z':
                b((Object) Opcodes.ac);
                return;
            case 'D':
                b((Object) Opcodes.ae);
                b((Object) Opcodes.ab);
                return;
            case 'F':
                b((Object) Opcodes.ad);
                return;
            case 'J':
                b((Object) Opcodes.af);
                b((Object) Opcodes.ab);
                return;
            case 'V':
                return;
            case '[':
                if (i2 == 0) {
                    b((Object) str);
                    return;
                } else {
                    b((Object) str.substring(i2, str.length()));
                    return;
                }
            default:
                if (i2 == 0) {
                    b((Object) str.substring(1, str.length() - 1));
                    return;
                } else {
                    b((Object) str.substring(i2 + 1, str.length() - 1));
                    return;
                }
        }
    }

    private Object d() {
        return this.d.remove(this.d.size() - 1);
    }

    private void c(int i2) {
        int size = this.d.size();
        int i3 = size - i2;
        for (int i4 = size - 1; i4 >= i3; i4--) {
            this.d.remove(i4);
        }
    }

    private void b(String str) {
        char charAt = str.charAt(0);
        if (charAt == '(') {
            Type[] d2 = Type.d(str);
            int i2 = 0;
            for (Type j : d2) {
                i2 += j.j();
            }
            c(i2);
        } else if (charAt == 'J' || charAt == 'D') {
            c(2);
        } else {
            c(1);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c9, code lost:
        c(1);
        b((java.lang.Object) org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ac);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x01e8, code lost:
        c(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x020a, code lost:
        r7 = r7 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0227, code lost:
        r7 = r7 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0258, code lost:
        c(2);
        b((java.lang.Object) org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ae);
        b((java.lang.Object) org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ab);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x026f, code lost:
        c(2);
        b((java.lang.Object) org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.af);
        b((java.lang.Object) org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ab);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(int r6, int r7, java.lang.String r8) {
        /*
            r5 = this;
            java.util.List<java.lang.Object> r0 = r5.c
            r1 = 0
            if (r0 != 0) goto L_0x0008
            r5.f = r1
            return
        L_0x0008:
            switch(r6) {
                case 0: goto L_0x02c2;
                case 1: goto L_0x02bd;
                case 2: goto L_0x02b7;
                case 3: goto L_0x02b7;
                case 4: goto L_0x02b7;
                case 5: goto L_0x02b7;
                case 6: goto L_0x02b7;
                case 7: goto L_0x02b7;
                case 8: goto L_0x02b7;
                case 9: goto L_0x02ac;
                case 10: goto L_0x02ac;
                case 11: goto L_0x02a6;
                case 12: goto L_0x02a6;
                case 13: goto L_0x02a6;
                case 14: goto L_0x029b;
                case 15: goto L_0x029b;
                case 16: goto L_0x02b7;
                case 17: goto L_0x02b7;
                default: goto L_0x000b;
            }
        L_0x000b:
            switch(r6) {
                case 21: goto L_0x0293;
                case 22: goto L_0x0286;
                case 23: goto L_0x0293;
                case 24: goto L_0x0286;
                case 25: goto L_0x0293;
                default: goto L_0x000e;
            }
        L_0x000e:
            r0 = 2
            r2 = 1
            switch(r6) {
                case 46: goto L_0x027d;
                case 47: goto L_0x026f;
                case 48: goto L_0x0266;
                case 49: goto L_0x0258;
                case 50: goto L_0x023b;
                case 51: goto L_0x027d;
                case 52: goto L_0x027d;
                case 53: goto L_0x027d;
                case 54: goto L_0x021e;
                case 55: goto L_0x01f7;
                case 56: goto L_0x021e;
                case 57: goto L_0x01f7;
                case 58: goto L_0x021e;
                default: goto L_0x0013;
            }
        L_0x0013:
            r3 = 3
            r4 = 4
            switch(r6) {
                case 79: goto L_0x01f2;
                case 80: goto L_0x01ed;
                case 81: goto L_0x01f2;
                case 82: goto L_0x01ed;
                case 83: goto L_0x01f2;
                case 84: goto L_0x01f2;
                case 85: goto L_0x01f2;
                case 86: goto L_0x01f2;
                case 87: goto L_0x01e8;
                case 88: goto L_0x01e3;
                case 89: goto L_0x01d7;
                case 90: goto L_0x01c4;
                case 91: goto L_0x01aa;
                case 92: goto L_0x0194;
                case 93: goto L_0x0177;
                case 94: goto L_0x0153;
                case 95: goto L_0x0143;
                case 96: goto L_0x0139;
                case 97: goto L_0x012a;
                case 98: goto L_0x0120;
                case 99: goto L_0x0111;
                case 100: goto L_0x0139;
                case 101: goto L_0x012a;
                case 102: goto L_0x0120;
                case 103: goto L_0x0111;
                case 104: goto L_0x0139;
                case 105: goto L_0x012a;
                case 106: goto L_0x0120;
                case 107: goto L_0x0111;
                case 108: goto L_0x0139;
                case 109: goto L_0x012a;
                case 110: goto L_0x0120;
                case 111: goto L_0x0111;
                case 112: goto L_0x0139;
                case 113: goto L_0x012a;
                case 114: goto L_0x0120;
                case 115: goto L_0x0111;
                case 116: goto L_0x02c2;
                case 117: goto L_0x02c2;
                case 118: goto L_0x02c2;
                case 119: goto L_0x02c2;
                case 120: goto L_0x0139;
                case 121: goto L_0x0102;
                case 122: goto L_0x0139;
                case 123: goto L_0x0102;
                case 124: goto L_0x0139;
                case 125: goto L_0x0102;
                case 126: goto L_0x0139;
                case 127: goto L_0x012a;
                case 128: goto L_0x0139;
                case 129: goto L_0x012a;
                case 130: goto L_0x0139;
                case 131: goto L_0x012a;
                case 132: goto L_0x00fb;
                case 133: goto L_0x00ec;
                case 134: goto L_0x00e2;
                case 135: goto L_0x00d3;
                case 136: goto L_0x0139;
                case 137: goto L_0x0120;
                case 138: goto L_0x0258;
                case 139: goto L_0x00c9;
                case 140: goto L_0x00ec;
                case 141: goto L_0x00d3;
                case 142: goto L_0x0139;
                case 143: goto L_0x026f;
                case 144: goto L_0x0120;
                case 145: goto L_0x02c2;
                case 146: goto L_0x02c2;
                case 147: goto L_0x02c2;
                case 148: goto L_0x00bf;
                case 149: goto L_0x0139;
                case 150: goto L_0x0139;
                case 151: goto L_0x00bf;
                case 152: goto L_0x00bf;
                case 153: goto L_0x01e8;
                case 154: goto L_0x01e8;
                case 155: goto L_0x01e8;
                case 156: goto L_0x01e8;
                case 157: goto L_0x01e8;
                case 158: goto L_0x01e8;
                case 159: goto L_0x01e3;
                case 160: goto L_0x01e3;
                case 161: goto L_0x01e3;
                case 162: goto L_0x01e3;
                case 163: goto L_0x01e3;
                case 164: goto L_0x01e3;
                case 165: goto L_0x01e3;
                case 166: goto L_0x01e3;
                case 167: goto L_0x02c2;
                case 168: goto L_0x00b7;
                case 169: goto L_0x00b7;
                case 170: goto L_0x01e8;
                case 171: goto L_0x01e8;
                case 172: goto L_0x01e8;
                case 173: goto L_0x01e3;
                case 174: goto L_0x01e8;
                case 175: goto L_0x01e3;
                case 176: goto L_0x01e8;
                case 177: goto L_0x02c2;
                case 178: goto L_0x00b2;
                case 179: goto L_0x00ad;
                case 180: goto L_0x00a5;
                case 181: goto L_0x009d;
                default: goto L_0x0018;
            }
        L_0x0018:
            switch(r6) {
                case 187: goto L_0x0091;
                case 188: goto L_0x0053;
                case 189: goto L_0x0036;
                case 190: goto L_0x00c9;
                case 191: goto L_0x01e8;
                case 192: goto L_0x0026;
                case 193: goto L_0x00c9;
                case 194: goto L_0x01e8;
                case 195: goto L_0x01e8;
                default: goto L_0x001b;
            }
        L_0x001b:
            switch(r6) {
                case 198: goto L_0x01e8;
                case 199: goto L_0x01e8;
                default: goto L_0x001e;
            }
        L_0x001e:
            r5.c(r7)
            r5.a((java.lang.String) r8)
            goto L_0x02c2
        L_0x0026:
            r5.d()
            org.jacoco.agent.rt.internal_8ff85ea.asm.Type r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Type.b((java.lang.String) r8)
            java.lang.String r6 = r6.i()
            r5.a((java.lang.String) r6)
            goto L_0x02c2
        L_0x0036:
            r5.d()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "["
            r6.append(r7)
            org.jacoco.agent.rt.internal_8ff85ea.asm.Type r7 = org.jacoco.agent.rt.internal_8ff85ea.asm.Type.b((java.lang.String) r8)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.a((java.lang.String) r6)
            goto L_0x02c2
        L_0x0053:
            r5.d()
            switch(r7) {
                case 4: goto L_0x008a;
                case 5: goto L_0x0083;
                case 6: goto L_0x007c;
                case 7: goto L_0x0075;
                case 8: goto L_0x006e;
                case 9: goto L_0x0067;
                case 10: goto L_0x0060;
                default: goto L_0x0059;
            }
        L_0x0059:
            java.lang.String r6 = "[J"
            r5.a((java.lang.String) r6)
            goto L_0x02c2
        L_0x0060:
            java.lang.String r6 = "[I"
            r5.a((java.lang.String) r6)
            goto L_0x02c2
        L_0x0067:
            java.lang.String r6 = "[S"
            r5.a((java.lang.String) r6)
            goto L_0x02c2
        L_0x006e:
            java.lang.String r6 = "[B"
            r5.a((java.lang.String) r6)
            goto L_0x02c2
        L_0x0075:
            java.lang.String r6 = "[D"
            r5.a((java.lang.String) r6)
            goto L_0x02c2
        L_0x007c:
            java.lang.String r6 = "[F"
            r5.a((java.lang.String) r6)
            goto L_0x02c2
        L_0x0083:
            java.lang.String r6 = "[C"
            r5.a((java.lang.String) r6)
            goto L_0x02c2
        L_0x008a:
            java.lang.String r6 = "[Z"
            r5.a((java.lang.String) r6)
            goto L_0x02c2
        L_0x0091:
            java.util.List<org.jacoco.agent.rt.internal_8ff85ea.asm.Label> r6 = r5.f
            r7 = 0
            java.lang.Object r6 = r6.get(r7)
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x009d:
            r5.b((java.lang.String) r8)
            r5.d()
            goto L_0x02c2
        L_0x00a5:
            r5.c(r2)
            r5.a((java.lang.String) r8)
            goto L_0x02c2
        L_0x00ad:
            r5.b((java.lang.String) r8)
            goto L_0x02c2
        L_0x00b2:
            r5.a((java.lang.String) r8)
            goto L_0x02c2
        L_0x00b7:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.String r7 = "JSR/RET are not supported"
            r6.<init>(r7)
            throw r6
        L_0x00bf:
            r5.c(r4)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ac
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x00c9:
            r5.c(r2)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ac
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x00d3:
            r5.c(r2)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ae
            r5.b((java.lang.Object) r6)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ab
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x00e2:
            r5.c(r2)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ad
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x00ec:
            r5.c(r2)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.af
            r5.b((java.lang.Object) r6)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ab
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x00fb:
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ac
            r5.a((int) r7, (java.lang.Object) r6)
            goto L_0x02c2
        L_0x0102:
            r5.c(r3)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.af
            r5.b((java.lang.Object) r6)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ab
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x0111:
            r5.c(r4)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ae
            r5.b((java.lang.Object) r6)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ab
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x0120:
            r5.c(r0)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ad
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x012a:
            r5.c(r4)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.af
            r5.b((java.lang.Object) r6)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ab
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x0139:
            r5.c(r0)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ac
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x0143:
            java.lang.Object r6 = r5.d()
            java.lang.Object r7 = r5.d()
            r5.b((java.lang.Object) r6)
            r5.b((java.lang.Object) r7)
            goto L_0x02c2
        L_0x0153:
            java.lang.Object r6 = r5.d()
            java.lang.Object r7 = r5.d()
            java.lang.Object r8 = r5.d()
            java.lang.Object r0 = r5.d()
            r5.b((java.lang.Object) r7)
            r5.b((java.lang.Object) r6)
            r5.b((java.lang.Object) r0)
            r5.b((java.lang.Object) r8)
            r5.b((java.lang.Object) r7)
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x0177:
            java.lang.Object r6 = r5.d()
            java.lang.Object r7 = r5.d()
            java.lang.Object r8 = r5.d()
            r5.b((java.lang.Object) r7)
            r5.b((java.lang.Object) r6)
            r5.b((java.lang.Object) r8)
            r5.b((java.lang.Object) r7)
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x0194:
            java.lang.Object r6 = r5.d()
            java.lang.Object r7 = r5.d()
            r5.b((java.lang.Object) r7)
            r5.b((java.lang.Object) r6)
            r5.b((java.lang.Object) r7)
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x01aa:
            java.lang.Object r6 = r5.d()
            java.lang.Object r7 = r5.d()
            java.lang.Object r8 = r5.d()
            r5.b((java.lang.Object) r6)
            r5.b((java.lang.Object) r8)
            r5.b((java.lang.Object) r7)
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x01c4:
            java.lang.Object r6 = r5.d()
            java.lang.Object r7 = r5.d()
            r5.b((java.lang.Object) r6)
            r5.b((java.lang.Object) r7)
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x01d7:
            java.lang.Object r6 = r5.d()
            r5.b((java.lang.Object) r6)
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x01e3:
            r5.c(r0)
            goto L_0x02c2
        L_0x01e8:
            r5.c(r2)
            goto L_0x02c2
        L_0x01ed:
            r5.c(r4)
            goto L_0x02c2
        L_0x01f2:
            r5.c(r3)
            goto L_0x02c2
        L_0x01f7:
            r5.c(r2)
            java.lang.Object r6 = r5.d()
            r5.a((int) r7, (java.lang.Object) r6)
            int r6 = r7 + 1
            java.lang.Integer r8 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ab
            r5.a((int) r6, (java.lang.Object) r8)
            if (r7 <= 0) goto L_0x02c2
            int r7 = r7 - r2
            java.lang.Object r6 = r5.b((int) r7)
            java.lang.Integer r8 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.af
            if (r6 == r8) goto L_0x0217
            java.lang.Integer r8 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ae
            if (r6 != r8) goto L_0x02c2
        L_0x0217:
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ab
            r5.a((int) r7, (java.lang.Object) r6)
            goto L_0x02c2
        L_0x021e:
            java.lang.Object r6 = r5.d()
            r5.a((int) r7, (java.lang.Object) r6)
            if (r7 <= 0) goto L_0x02c2
            int r7 = r7 - r2
            java.lang.Object r6 = r5.b((int) r7)
            java.lang.Integer r8 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.af
            if (r6 == r8) goto L_0x0234
            java.lang.Integer r8 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ae
            if (r6 != r8) goto L_0x02c2
        L_0x0234:
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ab
            r5.a((int) r7, (java.lang.Object) r6)
            goto L_0x02c2
        L_0x023b:
            r5.c(r2)
            java.lang.Object r6 = r5.d()
            boolean r7 = r6 instanceof java.lang.String
            if (r7 == 0) goto L_0x0251
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r6 = r6.substring(r2)
            r5.a((java.lang.String) r6)
            goto L_0x02c2
        L_0x0251:
            java.lang.String r6 = "java/lang/Object"
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x0258:
            r5.c(r0)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ae
            r5.b((java.lang.Object) r6)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ab
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x0266:
            r5.c(r0)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ad
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x026f:
            r5.c(r0)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.af
            r5.b((java.lang.Object) r6)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ab
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x027d:
            r5.c(r0)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ac
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x0286:
            java.lang.Object r6 = r5.b((int) r7)
            r5.b((java.lang.Object) r6)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ab
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x0293:
            java.lang.Object r6 = r5.b((int) r7)
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x029b:
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ae
            r5.b((java.lang.Object) r6)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ab
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x02a6:
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ad
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x02ac:
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.af
            r5.b((java.lang.Object) r6)
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ab
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x02b7:
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ac
            r5.b((java.lang.Object) r6)
            goto L_0x02c2
        L_0x02bd:
            java.lang.Integer r6 = org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes.ag
            r5.b((java.lang.Object) r6)
        L_0x02c2:
            r5.f = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jacoco.agent.rt.internal_8ff85ea.asm.commons.AnalyzerAdapter.a(int, int, java.lang.String):void");
    }
}
