package com.xiaomi.zxing.datamatrix.encoder;

final class X12Encoder extends C40Encoder {
    public int a() {
        return 3;
    }

    X12Encoder() {
    }

    public void a(EncoderContext encoderContext) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!encoderContext.h()) {
                break;
            }
            char b = encoderContext.b();
            encoderContext.f1673a++;
            a(b, sb);
            if (sb.length() % 3 == 0) {
                a(encoderContext, sb);
                int a2 = HighLevelEncoder.a(encoderContext.a(), encoderContext.f1673a, a());
                if (a2 != a()) {
                    encoderContext.b(a2);
                    break;
                }
            }
        }
        b(encoderContext, sb);
    }

    /* access modifiers changed from: package-private */
    public int a(char c, StringBuilder sb) {
        if (c == 13) {
            sb.append(0);
        } else if (c == '*') {
            sb.append(1);
        } else if (c == '>') {
            sb.append(2);
        } else if (c == ' ') {
            sb.append(3);
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
        } else if (c < 'A' || c > 'Z') {
            HighLevelEncoder.c(c);
        } else {
            sb.append((char) ((c - 'A') + 14));
        }
        return 1;
    }

    /* access modifiers changed from: package-private */
    public void b(EncoderContext encoderContext, StringBuilder sb) {
        encoderContext.k();
        int i = encoderContext.j().i() - encoderContext.e();
        encoderContext.f1673a -= sb.length();
        if (encoderContext.i() > 1 || i > 1 || encoderContext.i() != i) {
            encoderContext.a(254);
        }
        if (encoderContext.f() < 0) {
            encoderContext.b(0);
        }
    }
}
