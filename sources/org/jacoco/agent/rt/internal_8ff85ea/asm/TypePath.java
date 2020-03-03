package org.jacoco.agent.rt.internal_8ff85ea.asm;

import com.taobao.weex.el.parse.Operators;

public class TypePath {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3601a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    byte[] e;
    int f;

    TypePath(byte[] bArr, int i) {
        this.e = bArr;
        this.f = i;
    }

    public int a() {
        return this.e[this.f];
    }

    public int a(int i) {
        return this.e[this.f + (i * 2) + 1];
    }

    public int b(int i) {
        return this.e[this.f + (i * 2) + 2];
    }

    public static TypePath a(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int length = str.length();
        ByteVector byteVector = new ByteVector(length);
        byteVector.a(0);
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            char charAt = str.charAt(i);
            if (charAt == '[') {
                byteVector.a(0, 0);
            } else if (charAt == '.') {
                byteVector.a(1, 0);
            } else if (charAt == '*') {
                byteVector.a(2, 0);
            } else if (charAt >= '0' && charAt <= '9') {
                int i3 = charAt - '0';
                while (i2 < length) {
                    char charAt2 = str.charAt(i2);
                    if (charAt2 < '0' || charAt2 > '9') {
                        break;
                    }
                    i3 = ((i3 * 10) + charAt2) - 48;
                    i2++;
                }
                if (i2 < length && str.charAt(i2) == ';') {
                    i2++;
                }
                byteVector.a(3, i3);
            }
            i = i2;
        }
        byteVector.f3586a[0] = (byte) (byteVector.b / 2);
        return new TypePath(byteVector.f3586a, 0);
    }

    public String toString() {
        int a2 = a();
        StringBuilder sb = new StringBuilder(a2 * 2);
        for (int i = 0; i < a2; i++) {
            switch (a(i)) {
                case 0:
                    sb.append(Operators.ARRAY_START);
                    break;
                case 1:
                    sb.append('.');
                    break;
                case 2:
                    sb.append('*');
                    break;
                case 3:
                    sb.append(b(i));
                    sb.append(';');
                    break;
                default:
                    sb.append('_');
                    break;
            }
        }
        return sb.toString();
    }
}
