package com.xiaomi.mimc.protobuf;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

final class Utf8 {

    /* renamed from: a  reason: collision with root package name */
    static final int f11354a = 3;
    public static final int b = 0;
    public static final int c = -1;
    private static final Processor d = (UnsafeProcessor.a() ? new UnsafeProcessor() : new SafeProcessor());
    private static final long e = -9187201950435737472L;
    private static final int f = 16;

    /* access modifiers changed from: private */
    public static int b(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    /* access modifiers changed from: private */
    public static int b(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    /* access modifiers changed from: private */
    public static int b(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static boolean a(byte[] bArr) {
        return d.a(bArr, 0, bArr.length);
    }

    public static boolean a(byte[] bArr, int i, int i2) {
        return d.a(bArr, i, i2);
    }

    public static int a(int i, byte[] bArr, int i2, int i3) {
        return d.a(i, bArr, i2, i3);
    }

    /* access modifiers changed from: private */
    public static int c(byte[] bArr, int i, int i2) {
        byte b2 = bArr[i - 1];
        switch (i2 - i) {
            case 0:
                return b(b2);
            case 1:
                return b(b2, bArr[i]);
            case 2:
                return b((int) b2, (int) bArr[i], (int) bArr[i + 1]);
            default:
                throw new AssertionError();
        }
    }

    /* access modifiers changed from: private */
    public static int b(ByteBuffer byteBuffer, int i, int i2, int i3) {
        switch (i3) {
            case 0:
                return b(i);
            case 1:
                return b(i, byteBuffer.get(i2));
            case 2:
                return b(i, (int) byteBuffer.get(i2), (int) byteBuffer.get(i2 + 1));
            default:
                throw new AssertionError();
        }
    }

    static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int i, int i2) {
            super("Unpaired surrogate at index " + i + " of " + i2);
        }
    }

    static int a(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int i2 = length;
        while (true) {
            if (i < length) {
                char charAt = charSequence.charAt(i);
                if (charAt >= 2048) {
                    i2 += a(charSequence, i);
                    break;
                }
                i2 += (127 - charAt) >>> 31;
                i++;
            } else {
                break;
            }
        }
        if (i2 >= length) {
            return i2;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i2) + IjkMediaMeta.AV_CH_WIDE_RIGHT));
    }

    private static int a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i) >= 65536) {
                        i++;
                    } else {
                        throw new UnpairedSurrogateException(i, length);
                    }
                }
            }
            i++;
        }
        return i2;
    }

    static int a(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return d.a(charSequence, bArr, i, i2);
    }

    static boolean a(ByteBuffer byteBuffer) {
        return d.a(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    static int a(int i, ByteBuffer byteBuffer, int i2, int i3) {
        return d.a(i, byteBuffer, i2, i3);
    }

    static void a(CharSequence charSequence, ByteBuffer byteBuffer) {
        d.a(charSequence, byteBuffer);
    }

    /* access modifiers changed from: private */
    public static int b(ByteBuffer byteBuffer, int i, int i2) {
        int i3 = i2 - 7;
        int i4 = i;
        while (i4 < i3 && (byteBuffer.getLong(i4) & e) == 0) {
            i4 += 8;
        }
        return i4 - i;
    }

    static abstract class Processor {
        /* access modifiers changed from: package-private */
        public abstract int a(int i, byte[] bArr, int i2, int i3);

        /* access modifiers changed from: package-private */
        public abstract int a(CharSequence charSequence, byte[] bArr, int i, int i2);

        /* access modifiers changed from: package-private */
        public abstract int b(int i, ByteBuffer byteBuffer, int i2, int i3);

        /* access modifiers changed from: package-private */
        public abstract void b(CharSequence charSequence, ByteBuffer byteBuffer);

        Processor() {
        }

        /* access modifiers changed from: package-private */
        public final boolean a(byte[] bArr, int i, int i2) {
            return a(0, bArr, i, i2) == 0;
        }

        /* access modifiers changed from: package-private */
        public final boolean a(ByteBuffer byteBuffer, int i, int i2) {
            return a(0, byteBuffer, i, i2) == 0;
        }

        /* access modifiers changed from: package-private */
        public final int a(int i, ByteBuffer byteBuffer, int i2, int i3) {
            if (byteBuffer.hasArray()) {
                int arrayOffset = byteBuffer.arrayOffset();
                return a(i, byteBuffer.array(), i2 + arrayOffset, arrayOffset + i3);
            } else if (byteBuffer.isDirect()) {
                return b(i, byteBuffer, i2, i3);
            } else {
                return c(i, byteBuffer, i2, i3);
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0048, code lost:
            if (r8.get(r9) > -65) goto L_0x004a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x0086, code lost:
            if (r8.get(r7) > -65) goto L_0x0088;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
            if (r8.get(r9) > -65) goto L_0x0019;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final int c(int r7, java.nio.ByteBuffer r8, int r9, int r10) {
            /*
                r6 = this;
                if (r7 == 0) goto L_0x0089
                if (r9 < r10) goto L_0x0005
                return r7
            L_0x0005:
                byte r0 = (byte) r7
                r1 = -32
                r2 = -1
                r3 = -65
                if (r0 >= r1) goto L_0x001a
                r7 = -62
                if (r0 < r7) goto L_0x0019
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r9 <= r3) goto L_0x008a
            L_0x0019:
                return r2
            L_0x001a:
                r4 = -16
                if (r0 >= r4) goto L_0x004b
                int r7 = r7 >> 8
                r7 = r7 ^ r2
                byte r7 = (byte) r7
                if (r7 != 0) goto L_0x0034
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r7 < r10) goto L_0x0031
                int r7 = com.xiaomi.mimc.protobuf.Utf8.b(r0, r9)
                return r7
            L_0x0031:
                r5 = r9
                r9 = r7
                r7 = r5
            L_0x0034:
                if (r7 > r3) goto L_0x004a
                r4 = -96
                if (r0 != r1) goto L_0x003c
                if (r7 < r4) goto L_0x004a
            L_0x003c:
                r1 = -19
                if (r0 != r1) goto L_0x0042
                if (r7 >= r4) goto L_0x004a
            L_0x0042:
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r9 <= r3) goto L_0x008a
            L_0x004a:
                return r2
            L_0x004b:
                int r1 = r7 >> 8
                r1 = r1 ^ r2
                byte r1 = (byte) r1
                r4 = 0
                if (r1 != 0) goto L_0x005f
                int r7 = r9 + 1
                byte r1 = r8.get(r9)
                if (r7 < r10) goto L_0x0063
                int r7 = com.xiaomi.mimc.protobuf.Utf8.b(r0, r1)
                return r7
            L_0x005f:
                int r7 = r7 >> 16
                byte r4 = (byte) r7
                r7 = r9
            L_0x0063:
                if (r4 != 0) goto L_0x0073
                int r9 = r7 + 1
                byte r4 = r8.get(r7)
                if (r9 < r10) goto L_0x0072
                int r7 = com.xiaomi.mimc.protobuf.Utf8.b((int) r0, (int) r1, (int) r4)
                return r7
            L_0x0072:
                r7 = r9
            L_0x0073:
                if (r1 > r3) goto L_0x0088
                int r9 = r0 << 28
                int r1 = r1 + 112
                int r9 = r9 + r1
                int r9 = r9 >> 30
                if (r9 != 0) goto L_0x0088
                if (r4 > r3) goto L_0x0088
                int r9 = r7 + 1
                byte r7 = r8.get(r7)
                if (r7 <= r3) goto L_0x0089
            L_0x0088:
                return r2
            L_0x0089:
                r7 = r9
            L_0x008a:
                int r7 = b(r8, r7, r10)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.Utf8.Processor.c(int, java.nio.ByteBuffer, int, int):int");
        }

        private static int b(ByteBuffer byteBuffer, int i, int i2) {
            int a2 = i + Utf8.b(byteBuffer, i, i2);
            while (a2 < i2) {
                int i3 = a2 + 1;
                byte b = byteBuffer.get(a2);
                if (b < 0) {
                    if (b < -32) {
                        if (i3 >= i2) {
                            return b;
                        }
                        if (b < -62 || byteBuffer.get(i3) > -65) {
                            return -1;
                        }
                        i3++;
                    } else if (b < -16) {
                        if (i3 >= i2 - 1) {
                            return Utf8.b(byteBuffer, b, i3, i2 - i3);
                        }
                        int i4 = i3 + 1;
                        byte b2 = byteBuffer.get(i3);
                        if (b2 > -65 || ((b == -32 && b2 < -96) || ((b == -19 && b2 >= -96) || byteBuffer.get(i4) > -65))) {
                            return -1;
                        }
                        a2 = i4 + 1;
                    } else if (i3 >= i2 - 2) {
                        return Utf8.b(byteBuffer, b, i3, i2 - i3);
                    } else {
                        int i5 = i3 + 1;
                        byte b3 = byteBuffer.get(i3);
                        if (b3 <= -65 && (((b << 28) + (b3 + Constants.TagName.ELECTRONIC_ID)) >> 30) == 0) {
                            int i6 = i5 + 1;
                            if (byteBuffer.get(i5) <= -65) {
                                i3 = i6 + 1;
                                if (byteBuffer.get(i6) > -65) {
                                }
                            }
                        }
                        return -1;
                    }
                }
                a2 = i3;
            }
            return 0;
        }

        /* access modifiers changed from: package-private */
        public final void a(CharSequence charSequence, ByteBuffer byteBuffer) {
            if (byteBuffer.hasArray()) {
                int arrayOffset = byteBuffer.arrayOffset();
                byteBuffer.position(Utf8.a(charSequence, byteBuffer.array(), byteBuffer.position() + arrayOffset, byteBuffer.remaining()) - arrayOffset);
            } else if (byteBuffer.isDirect()) {
                b(charSequence, byteBuffer);
            } else {
                c(charSequence, byteBuffer);
            }
        }

        /* access modifiers changed from: package-private */
        public final void c(CharSequence charSequence, ByteBuffer byteBuffer) {
            int i;
            int length = charSequence.length();
            int position = byteBuffer.position();
            int i2 = 0;
            while (i2 < length) {
                try {
                    char charAt = charSequence.charAt(i2);
                    if (charAt >= 128) {
                        break;
                    }
                    byteBuffer.put(position + i2, (byte) charAt);
                    i2++;
                } catch (IndexOutOfBoundsException unused) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i2) + " at index " + (byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1)));
                }
            }
            if (i2 == length) {
                byteBuffer.position(position + i2);
                return;
            }
            position += i2;
            while (i2 < length) {
                char charAt2 = charSequence.charAt(i2);
                if (charAt2 < 128) {
                    byteBuffer.put(position, (byte) charAt2);
                } else if (charAt2 < 2048) {
                    i = position + 1;
                    try {
                        byteBuffer.put(position, (byte) ((charAt2 >>> 6) | 192));
                        byteBuffer.put(i, (byte) ((charAt2 & Operators.CONDITION_IF) | 128));
                        position = i;
                    } catch (IndexOutOfBoundsException unused2) {
                        position = i;
                        throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i2) + " at index " + (byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1)));
                    }
                } else if (charAt2 < 55296 || 57343 < charAt2) {
                    i = position + 1;
                    byteBuffer.put(position, (byte) ((charAt2 >>> 12) | 224));
                    position = i + 1;
                    byteBuffer.put(i, (byte) (((charAt2 >>> 6) & 63) | 128));
                    byteBuffer.put(position, (byte) ((charAt2 & Operators.CONDITION_IF) | 128));
                } else {
                    int i3 = i2 + 1;
                    if (i3 != length) {
                        try {
                            char charAt3 = charSequence.charAt(i3);
                            if (Character.isSurrogatePair(charAt2, charAt3)) {
                                int codePoint = Character.toCodePoint(charAt2, charAt3);
                                int i4 = position + 1;
                                try {
                                    byteBuffer.put(position, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                    position = i4 + 1;
                                    byteBuffer.put(i4, (byte) (((codePoint >>> 12) & 63) | 128));
                                    i4 = position + 1;
                                    byteBuffer.put(position, (byte) (((codePoint >>> 6) & 63) | 128));
                                    byteBuffer.put(i4, (byte) ((codePoint & 63) | 128));
                                    position = i4;
                                    i2 = i3;
                                } catch (IndexOutOfBoundsException unused3) {
                                    position = i4;
                                    i2 = i3;
                                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i2) + " at index " + (byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1)));
                                }
                            } else {
                                i2 = i3;
                            }
                        } catch (IndexOutOfBoundsException unused4) {
                            i2 = i3;
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i2) + " at index " + (byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1)));
                        }
                    }
                    throw new UnpairedSurrogateException(i2, length);
                }
                i2++;
                position++;
            }
            byteBuffer.position(position);
        }
    }

    static final class SafeProcessor extends Processor {
        SafeProcessor() {
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0042, code lost:
            if (r8[r9] > -65) goto L_0x0044;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x007a, code lost:
            if (r8[r7] > -65) goto L_0x007c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
            if (r8[r9] > -65) goto L_0x0017;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int a(int r7, byte[] r8, int r9, int r10) {
            /*
                r6 = this;
                if (r7 == 0) goto L_0x007d
                if (r9 < r10) goto L_0x0005
                return r7
            L_0x0005:
                byte r0 = (byte) r7
                r1 = -32
                r2 = -1
                r3 = -65
                if (r0 >= r1) goto L_0x0018
                r7 = -62
                if (r0 < r7) goto L_0x0017
                int r7 = r9 + 1
                byte r9 = r8[r9]
                if (r9 <= r3) goto L_0x007e
            L_0x0017:
                return r2
            L_0x0018:
                r4 = -16
                if (r0 >= r4) goto L_0x0045
                int r7 = r7 >> 8
                r7 = r7 ^ r2
                byte r7 = (byte) r7
                if (r7 != 0) goto L_0x0030
                int r7 = r9 + 1
                byte r9 = r8[r9]
                if (r7 < r10) goto L_0x002d
                int r7 = com.xiaomi.mimc.protobuf.Utf8.b(r0, r9)
                return r7
            L_0x002d:
                r5 = r9
                r9 = r7
                r7 = r5
            L_0x0030:
                if (r7 > r3) goto L_0x0044
                r4 = -96
                if (r0 != r1) goto L_0x0038
                if (r7 < r4) goto L_0x0044
            L_0x0038:
                r1 = -19
                if (r0 != r1) goto L_0x003e
                if (r7 >= r4) goto L_0x0044
            L_0x003e:
                int r7 = r9 + 1
                byte r9 = r8[r9]
                if (r9 <= r3) goto L_0x007e
            L_0x0044:
                return r2
            L_0x0045:
                int r1 = r7 >> 8
                r1 = r1 ^ r2
                byte r1 = (byte) r1
                r4 = 0
                if (r1 != 0) goto L_0x0057
                int r7 = r9 + 1
                byte r1 = r8[r9]
                if (r7 < r10) goto L_0x005b
                int r7 = com.xiaomi.mimc.protobuf.Utf8.b(r0, r1)
                return r7
            L_0x0057:
                int r7 = r7 >> 16
                byte r4 = (byte) r7
                r7 = r9
            L_0x005b:
                if (r4 != 0) goto L_0x0069
                int r9 = r7 + 1
                byte r4 = r8[r7]
                if (r9 < r10) goto L_0x0068
                int r7 = com.xiaomi.mimc.protobuf.Utf8.b((int) r0, (int) r1, (int) r4)
                return r7
            L_0x0068:
                r7 = r9
            L_0x0069:
                if (r1 > r3) goto L_0x007c
                int r9 = r0 << 28
                int r1 = r1 + 112
                int r9 = r9 + r1
                int r9 = r9 >> 30
                if (r9 != 0) goto L_0x007c
                if (r4 > r3) goto L_0x007c
                int r9 = r7 + 1
                byte r7 = r8[r7]
                if (r7 <= r3) goto L_0x007d
            L_0x007c:
                return r2
            L_0x007d:
                r7 = r9
            L_0x007e:
                int r7 = b(r8, r7, r10)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.Utf8.SafeProcessor.a(int, byte[], int, int):int");
        }

        /* access modifiers changed from: package-private */
        public int b(int i, ByteBuffer byteBuffer, int i2, int i3) {
            return c(i, byteBuffer, i2, i3);
        }

        /* access modifiers changed from: package-private */
        public int a(CharSequence charSequence, byte[] bArr, int i, int i2) {
            int i3;
            int i4;
            int i5;
            char charAt;
            int length = charSequence.length();
            int i6 = i2 + i;
            int i7 = 0;
            while (i7 < length && (i5 = i7 + i) < i6 && (charAt = charSequence.charAt(i7)) < 128) {
                bArr[i5] = (byte) charAt;
                i7++;
            }
            if (i7 == length) {
                return i + length;
            }
            int i8 = i + i7;
            while (i7 < length) {
                char charAt2 = charSequence.charAt(i7);
                if (charAt2 < 128 && i8 < i6) {
                    i4 = i8 + 1;
                    bArr[i8] = (byte) charAt2;
                } else if (charAt2 < 2048 && i8 <= i6 - 2) {
                    int i9 = i8 + 1;
                    bArr[i8] = (byte) ((charAt2 >>> 6) | 960);
                    i8 = i9 + 1;
                    bArr[i9] = (byte) ((charAt2 & Operators.CONDITION_IF) | 128);
                    i7++;
                } else if ((charAt2 < 55296 || 57343 < charAt2) && i8 <= i6 - 3) {
                    int i10 = i8 + 1;
                    bArr[i8] = (byte) ((charAt2 >>> 12) | 480);
                    int i11 = i10 + 1;
                    bArr[i10] = (byte) (((charAt2 >>> 6) & 63) | 128);
                    i4 = i11 + 1;
                    bArr[i11] = (byte) ((charAt2 & Operators.CONDITION_IF) | 128);
                } else if (i8 <= i6 - 4) {
                    int i12 = i7 + 1;
                    if (i12 != charSequence.length()) {
                        char charAt3 = charSequence.charAt(i12);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            int i13 = i8 + 1;
                            bArr[i8] = (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK);
                            int i14 = i13 + 1;
                            bArr[i13] = (byte) (((codePoint >>> 12) & 63) | 128);
                            int i15 = i14 + 1;
                            bArr[i14] = (byte) (((codePoint >>> 6) & 63) | 128);
                            i8 = i15 + 1;
                            bArr[i15] = (byte) ((codePoint & 63) | 128);
                            i7 = i12;
                            i7++;
                        } else {
                            i7 = i12;
                        }
                    }
                    throw new UnpairedSurrogateException(i7 - 1, length);
                } else if (55296 > charAt2 || charAt2 > 57343 || ((i3 = i7 + 1) != charSequence.length() && Character.isSurrogatePair(charAt2, charSequence.charAt(i3)))) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i8);
                } else {
                    throw new UnpairedSurrogateException(i7, length);
                }
                i8 = i4;
                i7++;
            }
            return i8;
        }

        /* access modifiers changed from: package-private */
        public void b(CharSequence charSequence, ByteBuffer byteBuffer) {
            c(charSequence, byteBuffer);
        }

        private static int b(byte[] bArr, int i, int i2) {
            while (i < i2 && bArr[i] >= 0) {
                i++;
            }
            if (i >= i2) {
                return 0;
            }
            return c(bArr, i, i2);
        }

        private static int c(byte[] bArr, int i, int i2) {
            while (i < i2) {
                int i3 = i + 1;
                byte b = bArr[i];
                if (b < 0) {
                    if (b < -32) {
                        if (i3 >= i2) {
                            return b;
                        }
                        if (b >= -62) {
                            i = i3 + 1;
                            if (bArr[i3] > -65) {
                            }
                        }
                        return -1;
                    } else if (b < -16) {
                        if (i3 >= i2 - 1) {
                            return Utf8.c(bArr, i3, i2);
                        }
                        int i4 = i3 + 1;
                        byte b2 = bArr[i3];
                        if (b2 <= -65 && ((b != -32 || b2 >= -96) && (b != -19 || b2 < -96))) {
                            i = i4 + 1;
                            if (bArr[i4] > -65) {
                            }
                        }
                        return -1;
                    } else if (i3 >= i2 - 2) {
                        return Utf8.c(bArr, i3, i2);
                    } else {
                        int i5 = i3 + 1;
                        byte b3 = bArr[i3];
                        if (b3 <= -65 && (((b << 28) + (b3 + Constants.TagName.ELECTRONIC_ID)) >> 30) == 0) {
                            int i6 = i5 + 1;
                            if (bArr[i5] <= -65) {
                                i3 = i6 + 1;
                                if (bArr[i6] > -65) {
                                }
                            }
                        }
                        return -1;
                    }
                }
                i = i3;
            }
            return 0;
        }
    }

    static final class UnsafeProcessor extends Processor {
        UnsafeProcessor() {
        }

        static boolean a() {
            return UnsafeUtil.a() && UnsafeUtil.b();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
            if (com.xiaomi.mimc.protobuf.UnsafeUtil.a(r13, r2) > -65) goto L_0x0031;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0061, code lost:
            if (com.xiaomi.mimc.protobuf.UnsafeUtil.a(r13, r2) > -65) goto L_0x0063;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a3, code lost:
            if (com.xiaomi.mimc.protobuf.UnsafeUtil.a(r13, r2) > -65) goto L_0x00a5;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int a(int r12, byte[] r13, int r14, int r15) {
            /*
                r11 = this;
                r0 = r14 | r15
                int r1 = r13.length
                int r1 = r1 - r15
                r0 = r0 | r1
                r1 = 0
                if (r0 < 0) goto L_0x00af
                long r2 = com.xiaomi.mimc.protobuf.UnsafeUtil.c()
                long r4 = (long) r14
                long r2 = r2 + r4
                long r4 = com.xiaomi.mimc.protobuf.UnsafeUtil.c()
                long r14 = (long) r15
                long r4 = r4 + r14
                if (r12 == 0) goto L_0x00a6
                int r14 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r14 < 0) goto L_0x001b
                return r12
            L_0x001b:
                byte r14 = (byte) r12
                r15 = -32
                r0 = -1
                r6 = -65
                r7 = 1
                if (r14 >= r15) goto L_0x0032
                r12 = -62
                if (r14 < r12) goto L_0x0031
                long r14 = r2 + r7
                byte r12 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((byte[]) r13, (long) r2)
                if (r12 <= r6) goto L_0x00a7
            L_0x0031:
                return r0
            L_0x0032:
                r9 = -16
                if (r14 >= r9) goto L_0x0064
                int r12 = r12 >> 8
                r12 = r12 ^ r0
                byte r12 = (byte) r12
                if (r12 != 0) goto L_0x004c
                long r9 = r2 + r7
                byte r12 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((byte[]) r13, (long) r2)
                int r1 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
                if (r1 < 0) goto L_0x004b
                int r12 = com.xiaomi.mimc.protobuf.Utf8.b(r14, r12)
                return r12
            L_0x004b:
                r2 = r9
            L_0x004c:
                if (r12 > r6) goto L_0x0063
                r1 = -96
                if (r14 != r15) goto L_0x0054
                if (r12 < r1) goto L_0x0063
            L_0x0054:
                r15 = -19
                if (r14 != r15) goto L_0x005a
                if (r12 >= r1) goto L_0x0063
            L_0x005a:
                r12 = 0
                long r14 = r2 + r7
                byte r12 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((byte[]) r13, (long) r2)
                if (r12 <= r6) goto L_0x00a7
            L_0x0063:
                return r0
            L_0x0064:
                int r15 = r12 >> 8
                r15 = r15 ^ r0
                byte r15 = (byte) r15
                if (r15 != 0) goto L_0x007b
                long r9 = r2 + r7
                byte r15 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((byte[]) r13, (long) r2)
                int r12 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
                if (r12 < 0) goto L_0x0079
                int r12 = com.xiaomi.mimc.protobuf.Utf8.b(r14, r15)
                return r12
            L_0x0079:
                r2 = r9
                goto L_0x007e
            L_0x007b:
                int r12 = r12 >> 16
                byte r1 = (byte) r12
            L_0x007e:
                if (r1 != 0) goto L_0x0090
                long r9 = r2 + r7
                byte r1 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((byte[]) r13, (long) r2)
                int r12 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
                if (r12 < 0) goto L_0x008f
                int r12 = com.xiaomi.mimc.protobuf.Utf8.b((int) r14, (int) r15, (int) r1)
                return r12
            L_0x008f:
                r2 = r9
            L_0x0090:
                if (r15 > r6) goto L_0x00a5
                int r12 = r14 << 28
                int r15 = r15 + 112
                int r12 = r12 + r15
                int r12 = r12 >> 30
                if (r12 != 0) goto L_0x00a5
                if (r1 > r6) goto L_0x00a5
                long r14 = r2 + r7
                byte r12 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((byte[]) r13, (long) r2)
                if (r12 <= r6) goto L_0x00a7
            L_0x00a5:
                return r0
            L_0x00a6:
                r14 = r2
            L_0x00a7:
                r12 = 0
                long r4 = r4 - r14
                int r12 = (int) r4
                int r12 = b(r13, r14, r12)
                return r12
            L_0x00af:
                java.lang.ArrayIndexOutOfBoundsException r12 = new java.lang.ArrayIndexOutOfBoundsException
                r0 = 3
                java.lang.Object[] r0 = new java.lang.Object[r0]
                int r13 = r13.length
                java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
                r0[r1] = r13
                r13 = 1
                java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
                r0[r13] = r14
                r13 = 2
                java.lang.Integer r14 = java.lang.Integer.valueOf(r15)
                r0[r13] = r14
                java.lang.String r13 = "Array length=%d, index=%d, limit=%d"
                java.lang.String r13 = java.lang.String.format(r13, r0)
                r12.<init>(r13)
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.Utf8.UnsafeProcessor.a(int, byte[], int, int):int");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
            if (com.xiaomi.mimc.protobuf.UnsafeUtil.a(r2) > -65) goto L_0x0031;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0061, code lost:
            if (com.xiaomi.mimc.protobuf.UnsafeUtil.a(r2) > -65) goto L_0x0063;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a3, code lost:
            if (com.xiaomi.mimc.protobuf.UnsafeUtil.a(r2) > -65) goto L_0x00a5;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int b(int r11, java.nio.ByteBuffer r12, int r13, int r14) {
            /*
                r10 = this;
                r0 = r13 | r14
                int r1 = r12.limit()
                int r1 = r1 - r14
                r0 = r0 | r1
                r1 = 0
                if (r0 < 0) goto L_0x00af
                long r2 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((java.nio.ByteBuffer) r12)
                long r4 = (long) r13
                long r2 = r2 + r4
                int r14 = r14 - r13
                long r12 = (long) r14
                long r12 = r12 + r2
                if (r11 == 0) goto L_0x00a6
                int r14 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
                if (r14 < 0) goto L_0x001b
                return r11
            L_0x001b:
                byte r14 = (byte) r11
                r0 = -32
                r4 = -1
                r5 = -65
                r6 = 1
                if (r14 >= r0) goto L_0x0032
                r11 = -62
                if (r14 < r11) goto L_0x0031
                long r0 = r2 + r6
                byte r11 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r2)
                if (r11 <= r5) goto L_0x00a7
            L_0x0031:
                return r4
            L_0x0032:
                r8 = -16
                if (r14 >= r8) goto L_0x0064
                int r11 = r11 >> 8
                r11 = r11 ^ r4
                byte r11 = (byte) r11
                if (r11 != 0) goto L_0x004c
                long r8 = r2 + r6
                byte r11 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r2)
                int r1 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
                if (r1 < 0) goto L_0x004b
                int r11 = com.xiaomi.mimc.protobuf.Utf8.b(r14, r11)
                return r11
            L_0x004b:
                r2 = r8
            L_0x004c:
                if (r11 > r5) goto L_0x0063
                r1 = -96
                if (r14 != r0) goto L_0x0054
                if (r11 < r1) goto L_0x0063
            L_0x0054:
                r0 = -19
                if (r14 != r0) goto L_0x005a
                if (r11 >= r1) goto L_0x0063
            L_0x005a:
                r11 = 0
                long r0 = r2 + r6
                byte r11 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r2)
                if (r11 <= r5) goto L_0x00a7
            L_0x0063:
                return r4
            L_0x0064:
                int r0 = r11 >> 8
                r0 = r0 ^ r4
                byte r0 = (byte) r0
                if (r0 != 0) goto L_0x007b
                long r8 = r2 + r6
                byte r0 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r2)
                int r11 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
                if (r11 < 0) goto L_0x0079
                int r11 = com.xiaomi.mimc.protobuf.Utf8.b(r14, r0)
                return r11
            L_0x0079:
                r2 = r8
                goto L_0x007e
            L_0x007b:
                int r11 = r11 >> 16
                byte r1 = (byte) r11
            L_0x007e:
                if (r1 != 0) goto L_0x0090
                long r8 = r2 + r6
                byte r1 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r2)
                int r11 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
                if (r11 < 0) goto L_0x008f
                int r11 = com.xiaomi.mimc.protobuf.Utf8.b((int) r14, (int) r0, (int) r1)
                return r11
            L_0x008f:
                r2 = r8
            L_0x0090:
                if (r0 > r5) goto L_0x00a5
                int r11 = r14 << 28
                int r0 = r0 + 112
                int r11 = r11 + r0
                int r11 = r11 >> 30
                if (r11 != 0) goto L_0x00a5
                if (r1 > r5) goto L_0x00a5
                long r0 = r2 + r6
                byte r11 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r2)
                if (r11 <= r5) goto L_0x00a7
            L_0x00a5:
                return r4
            L_0x00a6:
                r0 = r2
            L_0x00a7:
                r11 = 0
                long r12 = r12 - r0
                int r11 = (int) r12
                int r11 = b((long) r0, (int) r11)
                return r11
            L_0x00af:
                java.lang.ArrayIndexOutOfBoundsException r11 = new java.lang.ArrayIndexOutOfBoundsException
                r0 = 3
                java.lang.Object[] r0 = new java.lang.Object[r0]
                int r12 = r12.limit()
                java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
                r0[r1] = r12
                r12 = 1
                java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
                r0[r12] = r13
                r12 = 2
                java.lang.Integer r13 = java.lang.Integer.valueOf(r14)
                r0[r12] = r13
                java.lang.String r12 = "buffer limit=%d, index=%d, limit=%d"
                java.lang.String r12 = java.lang.String.format(r12, r0)
                r11.<init>(r12)
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.Utf8.UnsafeProcessor.b(int, java.nio.ByteBuffer, int, int):int");
        }

        /* access modifiers changed from: package-private */
        public int a(CharSequence charSequence, byte[] bArr, int i, int i2) {
            long j;
            int i3;
            long j2;
            char charAt;
            CharSequence charSequence2 = charSequence;
            byte[] bArr2 = bArr;
            int i4 = i;
            int i5 = i2;
            long c = UnsafeUtil.c() + ((long) i4);
            long j3 = ((long) i5) + c;
            int length = charSequence.length();
            if (length > i5 || bArr2.length - i5 < i4) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence2.charAt(length - 1) + " at index " + (i4 + i5));
            }
            int i6 = 0;
            while (i6 < length && (charAt = charSequence2.charAt(i6)) < 128) {
                UnsafeUtil.a(bArr2, j, (byte) charAt);
                i6++;
                c = 1 + j;
            }
            if (i6 == length) {
                return (int) (j - UnsafeUtil.c());
            }
            while (i6 < length) {
                char charAt2 = charSequence2.charAt(i6);
                if (charAt2 < 128 && j < j3) {
                    j2 = j + 1;
                    UnsafeUtil.a(bArr2, j, (byte) charAt2);
                } else if (charAt2 < 2048 && j <= j3 - 2) {
                    long j4 = j + 1;
                    UnsafeUtil.a(bArr2, j, (byte) ((charAt2 >>> 6) | 960));
                    j = j4 + 1;
                    UnsafeUtil.a(bArr2, j4, (byte) ((charAt2 & Operators.CONDITION_IF) | 128));
                    i6++;
                } else if ((charAt2 < 55296 || 57343 < charAt2) && j <= j3 - 3) {
                    long j5 = j + 1;
                    UnsafeUtil.a(bArr2, j, (byte) ((charAt2 >>> 12) | 480));
                    long j6 = j5 + 1;
                    UnsafeUtil.a(bArr2, j5, (byte) (((charAt2 >>> 6) & 63) | 128));
                    j2 = j6 + 1;
                    UnsafeUtil.a(bArr2, j6, (byte) ((charAt2 & Operators.CONDITION_IF) | 128));
                } else if (j <= j3 - 4) {
                    int i7 = i6 + 1;
                    if (i7 != length) {
                        char charAt3 = charSequence2.charAt(i7);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            long j7 = j + 1;
                            UnsafeUtil.a(bArr2, j, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                            long j8 = j7 + 1;
                            UnsafeUtil.a(bArr2, j7, (byte) (((codePoint >>> 12) & 63) | 128));
                            long j9 = j8 + 1;
                            UnsafeUtil.a(bArr2, j8, (byte) (((codePoint >>> 6) & 63) | 128));
                            j = j9 + 1;
                            UnsafeUtil.a(bArr2, j9, (byte) ((codePoint & 63) | 128));
                            i6 = i7;
                            i6++;
                        }
                    } else {
                        i7 = i6;
                    }
                    throw new UnpairedSurrogateException(i7 - 1, length);
                } else if (55296 > charAt2 || charAt2 > 57343 || ((i3 = i6 + 1) != length && Character.isSurrogatePair(charAt2, charSequence2.charAt(i3)))) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + j);
                } else {
                    throw new UnpairedSurrogateException(i6, length);
                }
                j = j2;
                i6++;
            }
            return (int) (j - UnsafeUtil.c());
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:11:0x003d A[LOOP:1: B:11:0x003d->B:37:0x00fe, LOOP_START, PHI: r4 r9 r10 r11 
          PHI: (r4v5 long) = (r4v4 long), (r4v8 long) binds: [B:8:0x0035, B:37:0x00fe] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r9v3 int) = (r9v2 int), (r9v5 int) binds: [B:8:0x0035, B:37:0x00fe] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r10v1 char) = (r10v0 char), (r10v2 char) binds: [B:8:0x0035, B:37:0x00fe] A[DONT_GENERATE, DONT_INLINE]
          PHI: (r11v2 long) = (r11v1 long), (r11v3 long) binds: [B:8:0x0035, B:37:0x00fe] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x0037  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void b(java.lang.CharSequence r21, java.nio.ByteBuffer r22) {
            /*
                r20 = this;
                r0 = r21
                r1 = r22
                long r2 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((java.nio.ByteBuffer) r22)
                int r4 = r22.position()
                long r4 = (long) r4
                long r4 = r4 + r2
                int r6 = r22.limit()
                long r6 = (long) r6
                long r6 = r6 + r2
                int r8 = r21.length()
                long r9 = (long) r8
                long r11 = r6 - r4
                int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
                if (r13 > 0) goto L_0x014d
                r9 = 0
            L_0x0020:
                r10 = 128(0x80, float:1.794E-43)
                r11 = 1
                if (r9 >= r8) goto L_0x0035
                char r13 = r0.charAt(r9)
                if (r13 >= r10) goto L_0x0035
                long r11 = r11 + r4
                byte r10 = (byte) r13
                com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r4, (byte) r10)
                int r9 = r9 + 1
                r4 = r11
                goto L_0x0020
            L_0x0035:
                if (r9 != r8) goto L_0x003d
                long r4 = r4 - r2
                int r0 = (int) r4
                r1.position(r0)
                return
            L_0x003d:
                if (r9 >= r8) goto L_0x0146
                char r13 = r0.charAt(r9)
                if (r13 >= r10) goto L_0x0054
                int r14 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r14 >= 0) goto L_0x0054
                long r14 = r4 + r11
                byte r13 = (byte) r13
                com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r4, (byte) r13)
            L_0x004f:
                r4 = r11
            L_0x0050:
                r11 = 128(0x80, float:1.794E-43)
                goto L_0x00fe
            L_0x0054:
                r14 = 2048(0x800, float:2.87E-42)
                if (r13 >= r14) goto L_0x0077
                r14 = 2
                long r14 = r6 - r14
                int r16 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
                if (r16 > 0) goto L_0x0077
                long r14 = r4 + r11
                int r10 = r13 >>> 6
                r10 = r10 | 960(0x3c0, float:1.345E-42)
                byte r10 = (byte) r10
                com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r4, (byte) r10)
                long r4 = r14 + r11
                r10 = r13 & 63
                r13 = 128(0x80, float:1.794E-43)
                r10 = r10 | r13
                byte r10 = (byte) r10
                com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r14, (byte) r10)
                r14 = r4
                goto L_0x004f
            L_0x0077:
                r10 = 57343(0xdfff, float:8.0355E-41)
                r14 = 55296(0xd800, float:7.7486E-41)
                if (r13 < r14) goto L_0x0081
                if (r10 >= r13) goto L_0x00b0
            L_0x0081:
                r15 = 3
                long r15 = r6 - r15
                int r17 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
                if (r17 > 0) goto L_0x00b0
                long r14 = r4 + r11
                int r10 = r13 >>> 12
                r10 = r10 | 480(0x1e0, float:6.73E-43)
                byte r10 = (byte) r10
                com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r4, (byte) r10)
                long r4 = r14 + r11
                int r10 = r13 >>> 6
                r10 = r10 & 63
                r11 = 128(0x80, float:1.794E-43)
                r10 = r10 | r11
                byte r10 = (byte) r10
                com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r14, (byte) r10)
                r14 = 1
                long r18 = r4 + r14
                r10 = r13 & 63
                r10 = r10 | r11
                byte r10 = (byte) r10
                com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r4, (byte) r10)
                r14 = r18
                r4 = 1
                goto L_0x0050
            L_0x00b0:
                r11 = 4
                long r11 = r6 - r11
                int r15 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
                if (r15 > 0) goto L_0x010f
                int r10 = r9 + 1
                if (r10 == r8) goto L_0x0107
                char r9 = r0.charAt(r10)
                boolean r11 = java.lang.Character.isSurrogatePair(r13, r9)
                if (r11 == 0) goto L_0x0106
                int r9 = java.lang.Character.toCodePoint(r13, r9)
                r11 = 1
                long r13 = r4 + r11
                int r15 = r9 >>> 18
                r15 = r15 | 240(0xf0, float:3.36E-43)
                byte r15 = (byte) r15
                com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r4, (byte) r15)
                long r4 = r13 + r11
                int r15 = r9 >>> 12
                r15 = r15 & 63
                r11 = 128(0x80, float:1.794E-43)
                r12 = r15 | 128(0x80, float:1.794E-43)
                byte r12 = (byte) r12
                com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r13, (byte) r12)
                r12 = 1
                long r14 = r4 + r12
                int r16 = r9 >>> 6
                r12 = r16 & 63
                r12 = r12 | r11
                byte r12 = (byte) r12
                com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r4, (byte) r12)
                r4 = 1
                long r12 = r14 + r4
                r9 = r9 & 63
                r9 = r9 | r11
                byte r9 = (byte) r9
                com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r14, (byte) r9)
                r9 = r10
                r14 = r12
            L_0x00fe:
                int r9 = r9 + 1
                r11 = r4
                r4 = r14
                r10 = 128(0x80, float:1.794E-43)
                goto L_0x003d
            L_0x0106:
                r9 = r10
            L_0x0107:
                com.xiaomi.mimc.protobuf.Utf8$UnpairedSurrogateException r0 = new com.xiaomi.mimc.protobuf.Utf8$UnpairedSurrogateException
                int r9 = r9 + -1
                r0.<init>(r9, r8)
                throw r0
            L_0x010f:
                if (r14 > r13) goto L_0x0127
                if (r13 > r10) goto L_0x0127
                int r1 = r9 + 1
                if (r1 == r8) goto L_0x0121
                char r0 = r0.charAt(r1)
                boolean r0 = java.lang.Character.isSurrogatePair(r13, r0)
                if (r0 != 0) goto L_0x0127
            L_0x0121:
                com.xiaomi.mimc.protobuf.Utf8$UnpairedSurrogateException r0 = new com.xiaomi.mimc.protobuf.Utf8$UnpairedSurrogateException
                r0.<init>(r9, r8)
                throw r0
            L_0x0127:
                java.lang.ArrayIndexOutOfBoundsException r0 = new java.lang.ArrayIndexOutOfBoundsException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Failed writing "
                r1.append(r2)
                r1.append(r13)
                java.lang.String r2 = " at index "
                r1.append(r2)
                r1.append(r4)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x0146:
                r0 = 0
                long r4 = r4 - r2
                int r0 = (int) r4
                r1.position(r0)
                return
            L_0x014d:
                java.lang.ArrayIndexOutOfBoundsException r2 = new java.lang.ArrayIndexOutOfBoundsException
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Failed writing "
                r3.append(r4)
                int r8 = r8 + -1
                char r0 = r0.charAt(r8)
                r3.append(r0)
                java.lang.String r0 = " at index "
                r3.append(r0)
                int r0 = r22.limit()
                r3.append(r0)
                java.lang.String r0 = r3.toString()
                r2.<init>(r0)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.Utf8.UnsafeProcessor.b(java.lang.CharSequence, java.nio.ByteBuffer):void");
        }

        private static int a(byte[] bArr, long j, int i) {
            if (i < 16) {
                return 0;
            }
            int i2 = ((int) j) & 7;
            long j2 = j;
            int i3 = i2;
            while (i3 > 0) {
                long j3 = 1 + j2;
                if (UnsafeUtil.a(bArr, j2) < 0) {
                    return i2 - i3;
                }
                i3--;
                j2 = j3;
            }
            int i4 = i - i2;
            while (i4 >= 8 && (UnsafeUtil.b(bArr, j2) & Utf8.e) == 0) {
                j2 += 8;
                i4 -= 8;
            }
            return i - i4;
        }

        private static int a(long j, int i) {
            if (i < 16) {
                return 0;
            }
            int i2 = ((int) j) & 7;
            long j2 = j;
            int i3 = i2;
            while (i3 > 0) {
                long j3 = 1 + j2;
                if (UnsafeUtil.a(j2) < 0) {
                    return i2 - i3;
                }
                i3--;
                j2 = j3;
            }
            int i4 = i - i2;
            while (i4 >= 8 && (UnsafeUtil.b(j2) & Utf8.e) == 0) {
                j2 += 8;
                i4 -= 8;
            }
            return i - i4;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
            return -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0064, code lost:
            return -1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static int b(byte[] r8, long r9, int r11) {
            /*
                int r0 = a((byte[]) r8, (long) r9, (int) r11)
                int r11 = r11 - r0
                long r0 = (long) r0
                long r9 = r9 + r0
            L_0x0007:
                r0 = 0
                r1 = 0
            L_0x0009:
                r2 = 1
                if (r11 <= 0) goto L_0x001a
                long r4 = r9 + r2
                byte r1 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((byte[]) r8, (long) r9)
                if (r1 < 0) goto L_0x0019
                int r11 = r11 + -1
                r9 = r4
                goto L_0x0009
            L_0x0019:
                r9 = r4
            L_0x001a:
                if (r11 != 0) goto L_0x001d
                return r0
            L_0x001d:
                int r11 = r11 + -1
                r0 = -32
                r4 = -65
                r5 = -1
                if (r1 >= r0) goto L_0x003a
                if (r11 != 0) goto L_0x0029
                return r1
            L_0x0029:
                int r11 = r11 + -1
                r0 = -62
                if (r1 < r0) goto L_0x0039
                long r2 = r2 + r9
                byte r9 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((byte[]) r8, (long) r9)
                if (r9 <= r4) goto L_0x0037
                goto L_0x0039
            L_0x0037:
                r9 = r2
                goto L_0x0007
            L_0x0039:
                return r5
            L_0x003a:
                r6 = -16
                if (r1 >= r6) goto L_0x0065
                r6 = 2
                if (r11 >= r6) goto L_0x0046
                int r8 = a((byte[]) r8, (int) r1, (long) r9, (int) r11)
                return r8
            L_0x0046:
                int r11 = r11 + -2
                long r6 = r9 + r2
                byte r9 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((byte[]) r8, (long) r9)
                if (r9 > r4) goto L_0x0064
                r10 = -96
                if (r1 != r0) goto L_0x0056
                if (r9 < r10) goto L_0x0064
            L_0x0056:
                r0 = -19
                if (r1 != r0) goto L_0x005c
                if (r9 >= r10) goto L_0x0064
            L_0x005c:
                r9 = 0
                long r2 = r2 + r6
                byte r9 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((byte[]) r8, (long) r6)
                if (r9 <= r4) goto L_0x0037
            L_0x0064:
                return r5
            L_0x0065:
                r0 = 3
                if (r11 >= r0) goto L_0x006d
                int r8 = a((byte[]) r8, (int) r1, (long) r9, (int) r11)
                return r8
            L_0x006d:
                int r11 = r11 + -3
                long r6 = r9 + r2
                byte r9 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((byte[]) r8, (long) r9)
                if (r9 > r4) goto L_0x008f
                int r10 = r1 << 28
                int r9 = r9 + 112
                int r10 = r10 + r9
                int r9 = r10 >> 30
                if (r9 != 0) goto L_0x008f
                long r9 = r6 + r2
                byte r0 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((byte[]) r8, (long) r6)
                if (r0 > r4) goto L_0x008f
                long r2 = r2 + r9
                byte r9 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((byte[]) r8, (long) r9)
                if (r9 <= r4) goto L_0x0037
            L_0x008f:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.Utf8.UnsafeProcessor.b(byte[], long, int):int");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
            return -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0064, code lost:
            return -1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static int b(long r8, int r10) {
            /*
                int r0 = a(r8, r10)
                long r1 = (long) r0
                long r8 = r8 + r1
                int r10 = r10 - r0
            L_0x0007:
                r0 = 0
                r1 = 0
            L_0x0009:
                r2 = 1
                if (r10 <= 0) goto L_0x001a
                long r4 = r8 + r2
                byte r1 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r8)
                if (r1 < 0) goto L_0x0019
                int r10 = r10 + -1
                r8 = r4
                goto L_0x0009
            L_0x0019:
                r8 = r4
            L_0x001a:
                if (r10 != 0) goto L_0x001d
                return r0
            L_0x001d:
                int r10 = r10 + -1
                r0 = -32
                r4 = -65
                r5 = -1
                if (r1 >= r0) goto L_0x003a
                if (r10 != 0) goto L_0x0029
                return r1
            L_0x0029:
                int r10 = r10 + -1
                r0 = -62
                if (r1 < r0) goto L_0x0039
                long r2 = r2 + r8
                byte r8 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r8)
                if (r8 <= r4) goto L_0x0037
                goto L_0x0039
            L_0x0037:
                r8 = r2
                goto L_0x0007
            L_0x0039:
                return r5
            L_0x003a:
                r6 = -16
                if (r1 >= r6) goto L_0x0065
                r6 = 2
                if (r10 >= r6) goto L_0x0046
                int r8 = a((long) r8, (int) r1, (int) r10)
                return r8
            L_0x0046:
                int r10 = r10 + -2
                long r6 = r8 + r2
                byte r8 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r8)
                if (r8 > r4) goto L_0x0064
                r9 = -96
                if (r1 != r0) goto L_0x0056
                if (r8 < r9) goto L_0x0064
            L_0x0056:
                r0 = -19
                if (r1 != r0) goto L_0x005c
                if (r8 >= r9) goto L_0x0064
            L_0x005c:
                r8 = 0
                long r2 = r2 + r6
                byte r8 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r6)
                if (r8 <= r4) goto L_0x0037
            L_0x0064:
                return r5
            L_0x0065:
                r0 = 3
                if (r10 >= r0) goto L_0x006d
                int r8 = a((long) r8, (int) r1, (int) r10)
                return r8
            L_0x006d:
                int r10 = r10 + -3
                long r6 = r8 + r2
                byte r8 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r8)
                if (r8 > r4) goto L_0x008f
                int r9 = r1 << 28
                int r8 = r8 + 112
                int r9 = r9 + r8
                int r8 = r9 >> 30
                if (r8 != 0) goto L_0x008f
                long r8 = r6 + r2
                byte r0 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r6)
                if (r0 > r4) goto L_0x008f
                long r2 = r2 + r8
                byte r8 = com.xiaomi.mimc.protobuf.UnsafeUtil.a((long) r8)
                if (r8 <= r4) goto L_0x0037
            L_0x008f:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.Utf8.UnsafeProcessor.b(long, int):int");
        }

        private static int a(byte[] bArr, int i, long j, int i2) {
            switch (i2) {
                case 0:
                    return Utf8.b(i);
                case 1:
                    return Utf8.b(i, UnsafeUtil.a(bArr, j));
                case 2:
                    return Utf8.b(i, (int) UnsafeUtil.a(bArr, j), (int) UnsafeUtil.a(bArr, j + 1));
                default:
                    throw new AssertionError();
            }
        }

        private static int a(long j, int i, int i2) {
            switch (i2) {
                case 0:
                    return Utf8.b(i);
                case 1:
                    return Utf8.b(i, UnsafeUtil.a(j));
                case 2:
                    return Utf8.b(i, (int) UnsafeUtil.a(j), (int) UnsafeUtil.a(j + 1));
                default:
                    throw new AssertionError();
            }
        }
    }

    private Utf8() {
    }
}
