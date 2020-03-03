package com.xiaomi.zxing.datamatrix.encoder;

final class ASCIIEncoder implements Encoder {
    public int a() {
        return 0;
    }

    ASCIIEncoder() {
    }

    public void a(EncoderContext encoderContext) {
        if (HighLevelEncoder.a((CharSequence) encoderContext.a(), encoderContext.f1673a) >= 2) {
            encoderContext.a(a(encoderContext.a().charAt(encoderContext.f1673a), encoderContext.a().charAt(encoderContext.f1673a + 1)));
            encoderContext.f1673a += 2;
            return;
        }
        char b = encoderContext.b();
        int a2 = HighLevelEncoder.a(encoderContext.a(), encoderContext.f1673a, a());
        if (a2 != a()) {
            switch (a2) {
                case 1:
                    encoderContext.a(230);
                    encoderContext.b(1);
                    return;
                case 2:
                    encoderContext.a(239);
                    encoderContext.b(2);
                    return;
                case 3:
                    encoderContext.a(238);
                    encoderContext.b(3);
                    return;
                case 4:
                    encoderContext.a(240);
                    encoderContext.b(4);
                    return;
                case 5:
                    encoderContext.a(231);
                    encoderContext.b(5);
                    return;
                default:
                    throw new IllegalStateException("Illegal mode: " + a2);
            }
        } else if (HighLevelEncoder.b(b)) {
            encoderContext.a(235);
            encoderContext.a((char) ((b - 128) + 1));
            encoderContext.f1673a++;
        } else {
            encoderContext.a((char) (b + 1));
            encoderContext.f1673a++;
        }
    }

    private static char a(char c, char c2) {
        if (HighLevelEncoder.a(c) && HighLevelEncoder.a(c2)) {
            return (char) (((c - '0') * 10) + (c2 - '0') + 130);
        }
        throw new IllegalArgumentException("not digits: " + c + c2);
    }
}
