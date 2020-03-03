package com.xiaomi.zxing.datamatrix.encoder;

import com.taobao.weex.el.parse.Operators;

class C40Encoder implements Encoder {
    public int a() {
        return 1;
    }

    C40Encoder() {
    }

    public void a(EncoderContext encoderContext) {
        int a2;
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!encoderContext.h()) {
                break;
            }
            char b = encoderContext.b();
            encoderContext.f1673a++;
            int a3 = a(b, sb);
            int e = encoderContext.e() + ((sb.length() / 3) * 2);
            encoderContext.c(e);
            int i = encoderContext.j().i() - e;
            if (encoderContext.h()) {
                if (sb.length() % 3 == 0 && (a2 = HighLevelEncoder.a(encoderContext.a(), encoderContext.f1673a, a())) != a()) {
                    encoderContext.b(a2);
                    break;
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                if (sb.length() % 3 == 2 && (i < 2 || i > 2)) {
                    a3 = a(encoderContext, sb, sb2, a3);
                }
                while (sb.length() % 3 == 1 && ((a3 <= 3 && i != 1) || a3 > 3)) {
                    a3 = a(encoderContext, sb, sb2, a3);
                }
            }
        }
        b(encoderContext, sb);
    }

    private int a(EncoderContext encoderContext, StringBuilder sb, StringBuilder sb2, int i) {
        int length = sb.length();
        sb.delete(length - i, length);
        encoderContext.f1673a--;
        int a2 = a(encoderContext.b(), sb2);
        encoderContext.l();
        return a2;
    }

    static void a(EncoderContext encoderContext, StringBuilder sb) {
        encoderContext.a(a((CharSequence) sb, 0));
        sb.delete(0, 3);
    }

    /* access modifiers changed from: package-private */
    public void b(EncoderContext encoderContext, StringBuilder sb) {
        int length = sb.length() % 3;
        int e = encoderContext.e() + ((sb.length() / 3) * 2);
        encoderContext.c(e);
        int i = encoderContext.j().i() - e;
        if (length == 2) {
            sb.append(0);
            while (sb.length() >= 3) {
                a(encoderContext, sb);
            }
            if (encoderContext.h()) {
                encoderContext.a(254);
            }
        } else if (i == 1 && length == 1) {
            while (sb.length() >= 3) {
                a(encoderContext, sb);
            }
            if (encoderContext.h()) {
                encoderContext.a(254);
            }
            encoderContext.f1673a--;
        } else if (length == 0) {
            while (sb.length() >= 3) {
                a(encoderContext, sb);
            }
            if (i > 0 || encoderContext.h()) {
                encoderContext.a(254);
            }
        } else {
            throw new IllegalStateException("Unexpected case. Please report!");
        }
        encoderContext.b(0);
    }

    /* access modifiers changed from: package-private */
    public int a(char c, StringBuilder sb) {
        if (c == ' ') {
            sb.append(3);
            return 1;
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
            return 1;
        } else if (c >= 'A' && c <= 'Z') {
            sb.append((char) ((c - 'A') + 14));
            return 1;
        } else if (c >= 0 && c <= 31) {
            sb.append(0);
            sb.append(c);
            return 2;
        } else if (c >= '!' && c <= '/') {
            sb.append(1);
            sb.append((char) (c - '!'));
            return 2;
        } else if (c >= ':' && c <= '@') {
            sb.append(1);
            sb.append((char) ((c - Operators.CONDITION_IF_MIDDLE) + 15));
            return 2;
        } else if (c >= '[' && c <= '_') {
            sb.append(1);
            sb.append((char) ((c - Operators.ARRAY_START) + 22));
            return 2;
        } else if (c >= '`' && c <= 127) {
            sb.append(2);
            sb.append((char) (c - '`'));
            return 2;
        } else if (c >= 128) {
            sb.append("\u0001\u001e");
            return a((char) (c - 128), sb) + 2;
        } else {
            throw new IllegalArgumentException("Illegal character: " + c);
        }
    }

    private static String a(CharSequence charSequence, int i) {
        char charAt = charSequence.charAt(i);
        char charAt2 = charSequence.charAt(i + 1);
        int charAt3 = (charAt * 1600) + (charAt2 * Operators.BRACKET_START) + charSequence.charAt(i + 2) + 1;
        return new String(new char[]{(char) (charAt3 / 256), (char) (charAt3 % 256)});
    }
}
