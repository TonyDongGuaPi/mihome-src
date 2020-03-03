package org.jacoco.agent.rt.internal_8ff85ea.asm;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Type {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3600a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    public static final int i = 8;
    public static final int j = 9;
    public static final int k = 10;
    public static final int l = 11;
    public static final Type m = new Type(0, (char[]) null, 1443168256, 1);
    public static final Type n = new Type(1, (char[]) null, 1509950721, 1);
    public static final Type o = new Type(2, (char[]) null, 1124075009, 1);
    public static final Type p = new Type(3, (char[]) null, 1107297537, 1);
    public static final Type q = new Type(4, (char[]) null, 1392510721, 1);
    public static final Type r = new Type(5, (char[]) null, 1224736769, 1);
    public static final Type s = new Type(6, (char[]) null, 1174536705, 1);
    public static final Type t = new Type(7, (char[]) null, 1241579778, 1);
    public static final Type u = new Type(8, (char[]) null, 1141048066, 1);
    private final int v;
    private final char[] w;
    private final int x;
    private final int y;

    private Type(int i2, char[] cArr, int i3, int i4) {
        this.v = i2;
        this.w = cArr;
        this.x = i3;
        this.y = i4;
    }

    public static Type a(String str) {
        return a(str.toCharArray(), 0);
    }

    public static Type b(String str) {
        char[] charArray = str.toCharArray();
        return new Type(charArray[0] == '[' ? 9 : 10, charArray, 0, charArray.length);
    }

    public static Type c(String str) {
        return a(str.toCharArray(), 0);
    }

    public static Type a(Type type, Type... typeArr) {
        return a(b(type, typeArr));
    }

    public static Type a(Class<?> cls) {
        if (!cls.isPrimitive()) {
            return a(c(cls));
        }
        if (cls == Integer.TYPE) {
            return r;
        }
        if (cls == Void.TYPE) {
            return m;
        }
        if (cls == Boolean.TYPE) {
            return n;
        }
        if (cls == Byte.TYPE) {
            return p;
        }
        if (cls == Character.TYPE) {
            return o;
        }
        if (cls == Short.TYPE) {
            return q;
        }
        if (cls == Double.TYPE) {
            return u;
        }
        if (cls == Float.TYPE) {
            return s;
        }
        return t;
    }

    public static Type a(Constructor<?> constructor) {
        return a(b(constructor));
    }

    public static Type a(Method method) {
        return a(d(method));
    }

    public static Type[] d(String str) {
        char[] charArray = str.toCharArray();
        int i2 = 1;
        int i3 = 1;
        int i4 = 0;
        while (true) {
            int i5 = i3 + 1;
            char c2 = charArray[i3];
            if (c2 == ')') {
                break;
            } else if (c2 == 'L') {
                while (true) {
                    i3 = i5 + 1;
                    if (charArray[i5] == ';') {
                        break;
                    }
                    i5 = i3;
                }
                i4++;
            } else {
                if (c2 != '[') {
                    i4++;
                }
                i3 = i5;
            }
        }
        Type[] typeArr = new Type[i4];
        int i6 = 0;
        while (charArray[i2] != ')') {
            typeArr[i6] = a(charArray, i2);
            i2 += typeArr[i6].y + (typeArr[i6].v == 10 ? 2 : 0);
            i6++;
        }
        return typeArr;
    }

    public static Type[] b(Method method) {
        Class[] parameterTypes = method.getParameterTypes();
        Type[] typeArr = new Type[parameterTypes.length];
        for (int length = parameterTypes.length - 1; length >= 0; length--) {
            typeArr[length] = a((Class<?>) parameterTypes[length]);
        }
        return typeArr;
    }

    public static Type e(String str) {
        char[] charArray = str.toCharArray();
        int i2 = 1;
        while (true) {
            int i3 = i2 + 1;
            char c2 = charArray[i2];
            if (c2 == ')') {
                return a(charArray, i3);
            }
            if (c2 == 'L') {
                while (true) {
                    i2 = i3 + 1;
                    if (charArray[i3] == ';') {
                        break;
                    }
                    i3 = i2;
                }
            } else {
                i2 = i3;
            }
        }
    }

    public static Type c(Method method) {
        return a(method.getReturnType());
    }

    public static int f(String str) {
        int i2;
        char charAt;
        int i3 = 1;
        int i4 = 1;
        int i5 = 1;
        while (true) {
            i2 = i4 + 1;
            char charAt2 = str.charAt(i4);
            if (charAt2 == ')') {
                break;
            } else if (charAt2 == 'L') {
                while (true) {
                    i4 = i2 + 1;
                    if (str.charAt(i2) == ';') {
                        break;
                    }
                    i2 = i4;
                }
                i5++;
            } else {
                if (charAt2 == '[') {
                    while (true) {
                        charAt = str.charAt(i2);
                        if (charAt != '[') {
                            break;
                        }
                        i2++;
                    }
                    if (charAt == 'D' || charAt == 'J') {
                        i5--;
                    }
                } else {
                    i5 = (charAt2 == 'D' || charAt2 == 'J') ? i5 + 2 : i5 + 1;
                }
                i4 = i2;
            }
        }
        char charAt3 = str.charAt(i2);
        int i6 = i5 << 2;
        if (charAt3 == 'V') {
            i3 = 0;
        } else if (charAt3 == 'D' || charAt3 == 'J') {
            i3 = 2;
        }
        return i6 | i3;
    }

    private static Type a(char[] cArr, int i2) {
        switch (cArr[i2]) {
            case 'B':
                return p;
            case 'C':
                return o;
            case 'D':
                return u;
            case 'F':
                return s;
            case 'I':
                return r;
            case 'J':
                return t;
            case 'L':
                int i3 = 1;
                while (cArr[i2 + i3] != ';') {
                    i3++;
                }
                return new Type(10, cArr, i2 + 1, i3 - 1);
            case 'S':
                return q;
            case 'V':
                return m;
            case 'Z':
                return n;
            case '[':
                int i4 = 1;
                while (true) {
                    int i5 = i2 + i4;
                    if (cArr[i5] == '[') {
                        i4++;
                    } else {
                        if (cArr[i5] == 'L') {
                            while (true) {
                                i4++;
                                if (cArr[i2 + i4] != ';') {
                                }
                            }
                        }
                        return new Type(9, cArr, i2, i4 + 1);
                    }
                }
            default:
                return new Type(11, cArr, i2, cArr.length - i2);
        }
    }

    public int a() {
        return this.v;
    }

    public int b() {
        int i2 = 1;
        while (this.w[this.x + i2] == '[') {
            i2++;
        }
        return i2;
    }

    public Type c() {
        return a(this.w, this.x + b());
    }

    public String d() {
        switch (this.v) {
            case 0:
                return "void";
            case 1:
                return "boolean";
            case 2:
                return "char";
            case 3:
                return "byte";
            case 4:
                return "short";
            case 5:
                return "int";
            case 6:
                return "float";
            case 7:
                return "long";
            case 8:
                return "double";
            case 9:
                StringBuilder sb = new StringBuilder(c().d());
                for (int b2 = b(); b2 > 0; b2--) {
                    sb.append(XMPConst.ai);
                }
                return sb.toString();
            case 10:
                return new String(this.w, this.x, this.y).replace(IOUtils.f15883a, '.');
            default:
                return null;
        }
    }

    public String e() {
        return new String(this.w, this.x, this.y);
    }

    public Type[] f() {
        return d(i());
    }

    public Type g() {
        return e(i());
    }

    public int h() {
        return f(i());
    }

    public String i() {
        StringBuilder sb = new StringBuilder();
        a(sb);
        return sb.toString();
    }

    public static String b(Type type, Type... typeArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.BRACKET_START);
        for (Type a2 : typeArr) {
            a2.a(sb);
        }
        sb.append(Operators.BRACKET_END);
        type.a(sb);
        return sb.toString();
    }

    private void a(StringBuilder sb) {
        if (this.w == null) {
            sb.append((char) ((this.x & -16777216) >>> 24));
        } else if (this.v == 10) {
            sb.append('L');
            sb.append(this.w, this.x, this.y);
            sb.append(';');
        } else {
            sb.append(this.w, this.x, this.y);
        }
    }

    public static String b(Class<?> cls) {
        return cls.getName().replace('.', IOUtils.f15883a);
    }

    public static String c(Class<?> cls) {
        StringBuilder sb = new StringBuilder();
        a(sb, cls);
        return sb.toString();
    }

    public static String b(Constructor<?> constructor) {
        Class[] parameterTypes = constructor.getParameterTypes();
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.BRACKET_START);
        for (Class a2 : parameterTypes) {
            a(sb, (Class<?>) a2);
        }
        sb.append(")V");
        return sb.toString();
    }

    public static String d(Method method) {
        Class[] parameterTypes = method.getParameterTypes();
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.BRACKET_START);
        for (Class a2 : parameterTypes) {
            a(sb, (Class<?>) a2);
        }
        sb.append(Operators.BRACKET_END);
        a(sb, method.getReturnType());
        return sb.toString();
    }

    private static void a(StringBuilder sb, Class<?> cls) {
        char c2;
        while (!cls.isPrimitive()) {
            if (cls.isArray()) {
                sb.append(Operators.ARRAY_START);
                cls = cls.getComponentType();
            } else {
                sb.append('L');
                String name = cls.getName();
                int length = name.length();
                for (int i2 = 0; i2 < length; i2++) {
                    char charAt = name.charAt(i2);
                    if (charAt == '.') {
                        charAt = IOUtils.f15883a;
                    }
                    sb.append(charAt);
                }
                sb.append(';');
                return;
            }
        }
        if (cls == Integer.TYPE) {
            c2 = 'I';
        } else if (cls == Void.TYPE) {
            c2 = 'V';
        } else if (cls == Boolean.TYPE) {
            c2 = 'Z';
        } else if (cls == Byte.TYPE) {
            c2 = 'B';
        } else if (cls == Character.TYPE) {
            c2 = 'C';
        } else if (cls == Short.TYPE) {
            c2 = 'S';
        } else if (cls == Double.TYPE) {
            c2 = 'D';
        } else {
            c2 = cls == Float.TYPE ? 'F' : 'J';
        }
        sb.append(c2);
    }

    public int j() {
        if (this.w == null) {
            return this.x & 255;
        }
        return 1;
    }

    public int a(int i2) {
        int i3 = 4;
        if (i2 == 46 || i2 == 79) {
            if (this.w == null) {
                i3 = (this.x & 65280) >> 8;
            }
            return i2 + i3;
        }
        if (this.w == null) {
            i3 = (this.x & 16711680) >> 16;
        }
        return i2 + i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Type)) {
            return false;
        }
        Type type = (Type) obj;
        if (this.v != type.v) {
            return false;
        }
        if (this.v >= 9) {
            if (this.y != type.y) {
                return false;
            }
            int i2 = this.x;
            int i3 = type.x;
            int i4 = this.y + i2;
            while (i2 < i4) {
                if (this.w[i2] != type.w[i3]) {
                    return false;
                }
                i2++;
                i3++;
            }
        }
        return true;
    }

    public int hashCode() {
        int i2 = this.v * 13;
        if (this.v >= 9) {
            int i3 = this.x;
            int i4 = this.y + i3;
            while (i3 < i4) {
                i2 = (i2 + this.w[i3]) * 17;
                i3++;
            }
        }
        return i2;
    }

    public String toString() {
        return i();
    }
}
