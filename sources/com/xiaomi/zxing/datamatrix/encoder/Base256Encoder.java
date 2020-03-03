package com.xiaomi.zxing.datamatrix.encoder;

final class Base256Encoder implements Encoder {
    public int a() {
        return 5;
    }

    Base256Encoder() {
    }

    public void a(EncoderContext encoderContext) {
        StringBuilder sb = new StringBuilder();
        sb.append(0);
        while (true) {
            if (!encoderContext.h()) {
                break;
            }
            sb.append(encoderContext.b());
            encoderContext.f1673a++;
            int a2 = HighLevelEncoder.a(encoderContext.a(), encoderContext.f1673a, a());
            if (a2 != a()) {
                encoderContext.b(a2);
                break;
            }
        }
        int length = sb.length() - 1;
        int e = encoderContext.e() + length + 1;
        encoderContext.c(e);
        boolean z = encoderContext.j().i() - e > 0;
        if (encoderContext.h() || z) {
            if (length <= 249) {
                sb.setCharAt(0, (char) length);
            } else if (length <= 249 || length > 1555) {
                throw new IllegalStateException("Message length not in valid ranges: " + length);
            } else {
                sb.setCharAt(0, (char) ((length / 250) + 249));
                sb.insert(1, (char) (length % 250));
            }
        }
        int length2 = sb.length();
        for (int i = 0; i < length2; i++) {
            encoderContext.a(a(sb.charAt(i), encoderContext.e() + 1));
        }
    }

    private static char a(char c, int i) {
        int i2 = c + ((i * 149) % 255) + 1;
        return i2 <= 255 ? (char) i2 : (char) (i2 - 256);
    }
}
