package com.xiaomi.mimc.protobuf;

import com.mi.global.shop.model.Tags;
import com.xiaomi.smarthome.fastvideo.IOUtils;

final class TextFormatEscaper {

    private interface ByteSequence {
        byte a(int i);

        int a();
    }

    private TextFormatEscaper() {
    }

    static String a(ByteSequence byteSequence) {
        StringBuilder sb = new StringBuilder(byteSequence.a());
        for (int i = 0; i < byteSequence.a(); i++) {
            byte a2 = byteSequence.a(i);
            if (a2 == 34) {
                sb.append("\\\"");
            } else if (a2 == 39) {
                sb.append("\\'");
            } else if (a2 != 92) {
                switch (a2) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (a2 >= 32 && a2 <= 126) {
                            sb.append((char) a2);
                            break;
                        } else {
                            sb.append(IOUtils.b);
                            sb.append((char) (((a2 >>> 6) & 3) + 48));
                            sb.append((char) (((a2 >>> 3) & 7) + 48));
                            sb.append((char) ((a2 & 7) + 48));
                            break;
                        }
                        break;
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }

    static String a(final ByteString byteString) {
        return a((ByteSequence) new ByteSequence() {
            public int a() {
                return byteString.size();
            }

            public byte a(int i) {
                return byteString.byteAt(i);
            }
        });
    }

    static String a(final byte[] bArr) {
        return a((ByteSequence) new ByteSequence() {
            public int a() {
                return bArr.length;
            }

            public byte a(int i) {
                return bArr[i];
            }
        });
    }

    static String a(String str) {
        return a(ByteString.copyFromUtf8(str));
    }

    static String b(String str) {
        return str.replace(Tags.MiHome.TEL_SEPARATOR4, "\\\\").replace("\"", "\\\"");
    }
}
