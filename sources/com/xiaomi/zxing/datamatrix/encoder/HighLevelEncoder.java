package com.xiaomi.zxing.datamatrix.encoder;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.zxing.Dimension;
import java.util.Arrays;

public final class HighLevelEncoder {

    /* renamed from: a  reason: collision with root package name */
    static final char f1675a = 'æ';
    static final char b = 'ç';
    static final char c = 'ë';
    static final char d = 'î';
    static final char e = 'ï';
    static final char f = 'ð';
    static final char g = 'þ';
    static final char h = 'þ';
    static final int i = 0;
    static final int j = 1;
    static final int k = 2;
    static final int l = 3;
    static final int m = 4;
    static final int n = 5;
    private static final char o = '';
    private static final char p = 'ì';
    private static final char q = 'í';
    private static final String r = "[)>\u001e05\u001d";
    private static final String s = "[)>\u001e06\u001d";
    private static final String t = "\u001e\u0004";

    static boolean a(char c2) {
        return c2 >= '0' && c2 <= '9';
    }

    static boolean b(char c2) {
        return c2 >= 128 && c2 <= 255;
    }

    private static boolean d(char c2) {
        return c2 == ' ' || (c2 >= '0' && c2 <= '9') || (c2 >= 'A' && c2 <= 'Z');
    }

    private static boolean e(char c2) {
        return c2 == ' ' || (c2 >= '0' && c2 <= '9') || (c2 >= 'a' && c2 <= 'z');
    }

    private static boolean g(char c2) {
        return c2 == 13 || c2 == '*' || c2 == '>';
    }

    private static boolean h(char c2) {
        return c2 >= ' ' && c2 <= '^';
    }

    private static boolean i(char c2) {
        return false;
    }

    private HighLevelEncoder() {
    }

    private static char a(char c2, int i2) {
        int i3 = c2 + ((i2 * 149) % 253) + 1;
        if (i3 > 254) {
            i3 -= 254;
        }
        return (char) i3;
    }

    public static String a(String str) {
        return a(str, SymbolShapeHint.FORCE_NONE, (Dimension) null, (Dimension) null);
    }

    public static String a(String str, SymbolShapeHint symbolShapeHint, Dimension dimension, Dimension dimension2) {
        int i2 = 0;
        Encoder[] encoderArr = {new ASCIIEncoder(), new C40Encoder(), new TextEncoder(), new X12Encoder(), new EdifactEncoder(), new Base256Encoder()};
        EncoderContext encoderContext = new EncoderContext(str);
        encoderContext.a(symbolShapeHint);
        encoderContext.a(dimension, dimension2);
        if (str.startsWith(r) && str.endsWith(t)) {
            encoderContext.a((char) p);
            encoderContext.a(2);
            encoderContext.f1673a += r.length();
        } else if (str.startsWith(s) && str.endsWith(t)) {
            encoderContext.a((char) q);
            encoderContext.a(2);
            encoderContext.f1673a += s.length();
        }
        while (encoderContext.h()) {
            encoderArr[i2].a(encoderContext);
            if (encoderContext.f() >= 0) {
                i2 = encoderContext.f();
                encoderContext.g();
            }
        }
        int e2 = encoderContext.e();
        encoderContext.k();
        int i3 = encoderContext.j().i();
        if (!(e2 >= i3 || i2 == 0 || i2 == 5)) {
            encoderContext.a(254);
        }
        StringBuilder d2 = encoderContext.d();
        if (d2.length() < i3) {
            d2.append(o);
        }
        while (d2.length() < i3) {
            d2.append(a((char) o, d2.length() + 1));
        }
        return encoderContext.d().toString();
    }

    static int a(CharSequence charSequence, int i2, int i3) {
        float[] fArr;
        char c2;
        CharSequence charSequence2 = charSequence;
        int i4 = i2;
        if (i4 >= charSequence.length()) {
            return i3;
        }
        if (i3 == 0) {
            fArr = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.25f};
        } else {
            float[] fArr2 = {1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.25f};
            fArr2[i3] = 0.0f;
            fArr = fArr2;
        }
        int i5 = 0;
        while (true) {
            int i6 = i4 + i5;
            if (i6 == charSequence.length()) {
                byte[] bArr = new byte[6];
                int[] iArr = new int[6];
                int a2 = a(fArr, iArr, Integer.MAX_VALUE, bArr);
                int a3 = a(bArr);
                if (iArr[0] == a2) {
                    return 0;
                }
                if (a3 == 1 && bArr[5] > 0) {
                    return 5;
                }
                if (a3 == 1 && bArr[4] > 0) {
                    return 4;
                }
                if (a3 != 1 || bArr[2] <= 0) {
                    return (a3 != 1 || bArr[3] <= 0) ? 1 : 3;
                }
                return 2;
            }
            char charAt = charSequence2.charAt(i6);
            i5++;
            if (a(charAt)) {
                double d2 = (double) fArr[0];
                Double.isNaN(d2);
                fArr[0] = (float) (d2 + 0.5d);
            } else if (b(charAt)) {
                fArr[0] = (float) ((int) Math.ceil((double) fArr[0]));
                fArr[0] = fArr[0] + 2.0f;
            } else {
                fArr[0] = (float) ((int) Math.ceil((double) fArr[0]));
                fArr[0] = fArr[0] + 1.0f;
            }
            if (d(charAt)) {
                fArr[1] = fArr[1] + 0.6666667f;
            } else if (b(charAt)) {
                fArr[1] = fArr[1] + 2.6666667f;
            } else {
                fArr[1] = fArr[1] + 1.3333334f;
            }
            if (e(charAt)) {
                fArr[2] = fArr[2] + 0.6666667f;
            } else if (b(charAt)) {
                fArr[2] = fArr[2] + 2.6666667f;
            } else {
                fArr[2] = fArr[2] + 1.3333334f;
            }
            if (f(charAt)) {
                fArr[3] = fArr[3] + 0.6666667f;
            } else if (b(charAt)) {
                fArr[3] = fArr[3] + 4.3333335f;
            } else {
                fArr[3] = fArr[3] + 3.3333333f;
            }
            if (h(charAt)) {
                fArr[4] = fArr[4] + 0.75f;
            } else if (b(charAt)) {
                fArr[4] = fArr[4] + 4.25f;
            } else {
                fArr[4] = fArr[4] + 3.25f;
            }
            if (i(charAt)) {
                c2 = 5;
                fArr[5] = fArr[5] + 4.0f;
            } else {
                c2 = 5;
                fArr[5] = fArr[5] + 1.0f;
            }
            if (i5 >= 4) {
                int[] iArr2 = new int[6];
                byte[] bArr2 = new byte[6];
                a(fArr, iArr2, Integer.MAX_VALUE, bArr2);
                int a4 = a(bArr2);
                if (iArr2[0] < iArr2[c2] && iArr2[0] < iArr2[1] && iArr2[0] < iArr2[2] && iArr2[0] < iArr2[3] && iArr2[0] < iArr2[4]) {
                    return 0;
                }
                if (iArr2[5] < iArr2[0] || bArr2[1] + bArr2[2] + bArr2[3] + bArr2[4] == 0) {
                    return 5;
                }
                if (a4 == 1 && bArr2[4] > 0) {
                    return 4;
                }
                if (a4 == 1 && bArr2[2] > 0) {
                    return 2;
                }
                if (a4 == 1 && bArr2[3] > 0) {
                    return 3;
                }
                if (iArr2[1] + 1 < iArr2[0] && iArr2[1] + 1 < iArr2[5] && iArr2[1] + 1 < iArr2[4] && iArr2[1] + 1 < iArr2[2]) {
                    if (iArr2[1] < iArr2[3]) {
                        return 1;
                    }
                    if (iArr2[1] == iArr2[3]) {
                        for (int i7 = i4 + i5 + 1; i7 < charSequence.length(); i7++) {
                            char charAt2 = charSequence2.charAt(i7);
                            if (g(charAt2)) {
                                return 3;
                            }
                            if (!f(charAt2)) {
                                break;
                            }
                        }
                        return 1;
                    }
                }
            }
        }
    }

    private static int a(float[] fArr, int[] iArr, int i2, byte[] bArr) {
        Arrays.fill(bArr, (byte) 0);
        int i3 = i2;
        for (int i4 = 0; i4 < 6; i4++) {
            iArr[i4] = (int) Math.ceil((double) fArr[i4]);
            int i5 = iArr[i4];
            if (i3 > i5) {
                Arrays.fill(bArr, (byte) 0);
                i3 = i5;
            }
            if (i3 == i5) {
                bArr[i4] = (byte) (bArr[i4] + 1);
            }
        }
        return i3;
    }

    private static int a(byte[] bArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 6; i3++) {
            i2 += bArr[i3];
        }
        return i2;
    }

    private static boolean f(char c2) {
        return g(c2) || c2 == ' ' || (c2 >= '0' && c2 <= '9') || (c2 >= 'A' && c2 <= 'Z');
    }

    public static int a(CharSequence charSequence, int i2) {
        int length = charSequence.length();
        int i3 = 0;
        if (i2 < length) {
            char charAt = charSequence.charAt(i2);
            while (a(charAt) && i2 < length) {
                i3++;
                i2++;
                if (i2 < length) {
                    charAt = charSequence.charAt(i2);
                }
            }
        }
        return i3;
    }

    static void c(char c2) {
        String hexString = Integer.toHexString(c2);
        throw new IllegalArgumentException("Illegal character: " + c2 + " (0x" + ("0000".substring(0, 4 - hexString.length()) + hexString) + Operators.BRACKET_END);
    }
}
