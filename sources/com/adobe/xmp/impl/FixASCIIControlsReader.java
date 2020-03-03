package com.adobe.xmp.impl;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;

public class FixASCIIControlsReader extends PushbackReader {

    /* renamed from: a  reason: collision with root package name */
    private static final int f684a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static final int g = 8;
    private int h = 0;
    private int i = 0;
    private int j = 0;

    public FixASCIIControlsReader(Reader reader) {
        super(reader, 8);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003a, code lost:
        if (com.adobe.xmp.impl.Utils.a((char) r10.i) != false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007e, code lost:
        if (com.adobe.xmp.impl.Utils.a((char) r10.i) != false) goto L_0x003c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private char a(char r11) {
        /*
            r10 = this;
            int r0 = r10.h
            r1 = 59
            r2 = 3
            r3 = 10
            r4 = 4
            r5 = 57
            r6 = 48
            r7 = 1
            r8 = 0
            r9 = 5
            switch(r0) {
                case 0: goto L_0x00aa;
                case 1: goto L_0x009f;
                case 2: goto L_0x0082;
                case 3: goto L_0x0045;
                case 4: goto L_0x0016;
                case 5: goto L_0x0013;
                default: goto L_0x0012;
            }
        L_0x0012:
            return r11
        L_0x0013:
            r10.h = r8
            return r11
        L_0x0016:
            if (r6 > r11) goto L_0x0031
            if (r11 > r5) goto L_0x0031
            int r0 = r10.i
            int r0 = r0 * 10
            int r1 = java.lang.Character.digit(r11, r3)
            int r0 = r0 + r1
            r10.i = r0
            int r0 = r10.j
            int r0 = r0 + r7
            r10.j = r0
            int r0 = r10.j
            if (r0 > r9) goto L_0x0042
            r10.h = r4
            goto L_0x0044
        L_0x0031:
            if (r11 != r1) goto L_0x0042
            int r0 = r10.i
            char r0 = (char) r0
            boolean r0 = com.adobe.xmp.impl.Utils.a((char) r0)
            if (r0 == 0) goto L_0x0042
        L_0x003c:
            r10.h = r8
            int r11 = r10.i
            char r11 = (char) r11
            return r11
        L_0x0042:
            r10.h = r9
        L_0x0044:
            return r11
        L_0x0045:
            if (r6 > r11) goto L_0x0049
            if (r11 <= r5) goto L_0x0059
        L_0x0049:
            r0 = 97
            if (r0 > r11) goto L_0x0051
            r0 = 102(0x66, float:1.43E-43)
            if (r11 <= r0) goto L_0x0059
        L_0x0051:
            r0 = 65
            if (r0 > r11) goto L_0x0075
            r0 = 70
            if (r11 > r0) goto L_0x0075
        L_0x0059:
            int r0 = r10.i
            r1 = 16
            int r0 = r0 * 16
            int r1 = java.lang.Character.digit(r11, r1)
            int r0 = r0 + r1
            r10.i = r0
            int r0 = r10.j
            int r0 = r0 + r7
            r10.j = r0
            int r0 = r10.j
            if (r0 > r4) goto L_0x0072
            r10.h = r2
            goto L_0x0081
        L_0x0072:
            r10.h = r9
            goto L_0x0081
        L_0x0075:
            if (r11 != r1) goto L_0x0072
            int r0 = r10.i
            char r0 = (char) r0
            boolean r0 = com.adobe.xmp.impl.Utils.a((char) r0)
            if (r0 == 0) goto L_0x0072
            goto L_0x003c
        L_0x0081:
            return r11
        L_0x0082:
            r0 = 120(0x78, float:1.68E-43)
            if (r11 != r0) goto L_0x008d
            r10.i = r8
            r10.j = r8
            r10.h = r2
            goto L_0x009e
        L_0x008d:
            if (r6 > r11) goto L_0x009c
            if (r11 > r5) goto L_0x009c
            int r0 = java.lang.Character.digit(r11, r3)
            r10.i = r0
            r10.j = r7
            r10.h = r4
            goto L_0x009e
        L_0x009c:
            r10.h = r9
        L_0x009e:
            return r11
        L_0x009f:
            r0 = 35
            if (r11 != r0) goto L_0x00a7
            r0 = 2
            r10.h = r0
            goto L_0x00a9
        L_0x00a7:
            r10.h = r9
        L_0x00a9:
            return r11
        L_0x00aa:
            r0 = 38
            if (r11 != r0) goto L_0x00b0
            r10.h = r7
        L_0x00b0:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.FixASCIIControlsReader.a(char):char");
    }

    public int read(char[] cArr, int i2, int i3) throws IOException {
        char[] cArr2 = new char[8];
        int i4 = i2;
        boolean z = true;
        int i5 = 0;
        loop0:
        while (true) {
            int i6 = 0;
            while (z && i5 < i3) {
                z = super.read(cArr2, i6, 1) == 1;
                if (z) {
                    char a2 = a(cArr2[i6]);
                    if (this.h == 0) {
                        if (Utils.a(a2)) {
                            a2 = ' ';
                        }
                        cArr[i4] = a2;
                        i5++;
                        i4++;
                    } else if (this.h == 5) {
                        unread(cArr2, 0, i6 + 1);
                    } else {
                        i6++;
                    }
                } else if (i6 > 0) {
                    unread(cArr2, 0, i6);
                    this.h = 5;
                    z = true;
                }
            }
        }
        if (i5 > 0 || z) {
            return i5;
        }
        return -1;
    }
}
