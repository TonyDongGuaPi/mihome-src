package org.mp4parser.muxer.tracks.h264.parsing;

import java.nio.ShortBuffer;

public class Debug {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f4035a = false;

    public static void a(int i) {
    }

    public static void a(String str) {
    }

    public static void a(String str, Object... objArr) {
    }

    public static void b(String str) {
    }

    public static final void a(int[] iArr) {
        int i = 0;
        int i2 = 0;
        while (i < 8) {
            int i3 = i2;
            for (int i4 = 0; i4 < 8; i4++) {
                System.out.printf("%3d, ", new Object[]{Integer.valueOf(iArr[i3])});
                i3++;
            }
            System.out.println();
            i++;
            i2 = i3;
        }
    }

    public static final void a(short[] sArr) {
        int i = 0;
        int i2 = 0;
        while (i < 8) {
            int i3 = i2;
            for (int i4 = 0; i4 < 8; i4++) {
                System.out.printf("%3d, ", new Object[]{Short.valueOf(sArr[i3])});
                i3++;
            }
            System.out.println();
            i++;
            i2 = i3;
        }
    }

    public static final void a(ShortBuffer shortBuffer) {
        for (int i = 0; i < 8; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                System.out.printf("%3d, ", new Object[]{Short.valueOf(shortBuffer.get())});
            }
            System.out.println();
        }
    }

    public static void b(short[] sArr) {
        int i = 0;
        int i2 = 0;
        while (i < 8) {
            int i3 = i2;
            for (int i4 = 0; i4 < 8; i4++) {
                System.out.printf("%3d, ", new Object[]{Short.valueOf(sArr[i3])});
                i3++;
            }
            System.out.println();
            i++;
            i2 = i3;
        }
    }
}
