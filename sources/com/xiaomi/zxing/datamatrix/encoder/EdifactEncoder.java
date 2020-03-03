package com.xiaomi.zxing.datamatrix.encoder;

import com.taobao.weex.ui.component.list.template.TemplateDom;

final class EdifactEncoder implements Encoder {
    public int a() {
        return 4;
    }

    EdifactEncoder() {
    }

    public void a(EncoderContext encoderContext) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!encoderContext.h()) {
                break;
            }
            a(encoderContext.b(), sb);
            encoderContext.f1673a++;
            if (sb.length() >= 4) {
                encoderContext.a(a((CharSequence) sb, 0));
                sb.delete(0, 4);
                if (HighLevelEncoder.a(encoderContext.a(), encoderContext.f1673a, a()) != a()) {
                    encoderContext.b(0);
                    break;
                }
            }
        }
        sb.append(31);
        a(encoderContext, (CharSequence) sb);
    }

    private static void a(EncoderContext encoderContext, CharSequence charSequence) {
        try {
            int length = charSequence.length();
            if (length != 0) {
                boolean z = true;
                if (length == 1) {
                    encoderContext.k();
                    int i = encoderContext.j().i() - encoderContext.e();
                    if (encoderContext.i() == 0 && i <= 2) {
                        encoderContext.b(0);
                        return;
                    }
                }
                if (length <= 4) {
                    int i2 = length - 1;
                    String a2 = a(charSequence, 0);
                    if (!(!encoderContext.h()) || i2 > 2) {
                        z = false;
                    }
                    if (i2 <= 2) {
                        encoderContext.c(encoderContext.e() + i2);
                        if (encoderContext.j().i() - encoderContext.e() >= 3) {
                            encoderContext.c(encoderContext.e() + a2.length());
                            z = false;
                        }
                    }
                    if (z) {
                        encoderContext.l();
                        encoderContext.f1673a -= i2;
                    } else {
                        encoderContext.a(a2);
                    }
                    encoderContext.b(0);
                    return;
                }
                throw new IllegalStateException("Count must not exceed 4");
            }
        } finally {
            encoderContext.b(0);
        }
    }

    private static void a(char c, StringBuilder sb) {
        if (c >= ' ' && c <= '?') {
            sb.append(c);
        } else if (c < '@' || c > '^') {
            HighLevelEncoder.c(c);
        } else {
            sb.append((char) (c - TemplateDom.SEPARATOR));
        }
    }

    private static String a(CharSequence charSequence, int i) {
        int length = charSequence.length() - i;
        if (length != 0) {
            char charAt = charSequence.charAt(i);
            char c = 0;
            char charAt2 = length >= 2 ? charSequence.charAt(i + 1) : 0;
            char charAt3 = length >= 3 ? charSequence.charAt(i + 2) : 0;
            if (length >= 4) {
                c = charSequence.charAt(i + 3);
            }
            int i2 = (charAt << 18) + (charAt2 << 12) + (charAt3 << 6) + c;
            char c2 = (char) ((i2 >> 8) & 255);
            char c3 = (char) (i2 & 255);
            StringBuilder sb = new StringBuilder(3);
            sb.append((char) ((i2 >> 16) & 255));
            if (length >= 2) {
                sb.append(c2);
            }
            if (length >= 3) {
                sb.append(c3);
            }
            return sb.toString();
        }
        throw new IllegalStateException("StringBuilder must not be empty");
    }
}
