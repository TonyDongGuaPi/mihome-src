package com.xiaomi.zxing.aztec.encoder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public final class HighLevelEncoder {

    /* renamed from: a  reason: collision with root package name */
    static final String[] f1641a = {"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};
    static final int b = 0;
    static final int c = 1;
    static final int d = 2;
    static final int e = 3;
    static final int f = 4;
    static final int[][] g = {new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};
    static final int[][] h = ((int[][]) Array.newInstance(int.class, new int[]{6, 6}));
    private static final int[][] i = ((int[][]) Array.newInstance(int.class, new int[]{5, 256}));
    private final byte[] j;

    static {
        i[0][32] = 1;
        for (int i2 = 65; i2 <= 90; i2++) {
            i[0][i2] = (i2 - 65) + 2;
        }
        i[1][32] = 1;
        for (int i3 = 97; i3 <= 122; i3++) {
            i[1][i3] = (i3 - 97) + 2;
        }
        i[2][32] = 1;
        for (int i4 = 48; i4 <= 57; i4++) {
            i[2][i4] = (i4 - 48) + 2;
        }
        i[2][44] = 12;
        i[2][46] = 13;
        int[] iArr = {0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, 126, 127};
        for (int i5 = 0; i5 < iArr.length; i5++) {
            i[3][iArr[i5]] = i5;
        }
        int[] iArr2 = {0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 125};
        for (int i6 = 0; i6 < iArr2.length; i6++) {
            if (iArr2[i6] > 0) {
                i[4][iArr2[i6]] = i6;
            }
        }
        for (int[] fill : h) {
            Arrays.fill(fill, -1);
        }
        h[0][4] = 0;
        h[1][4] = 0;
        h[1][0] = 28;
        h[3][4] = 0;
        h[2][4] = 0;
        h[2][0] = 15;
    }

    public HighLevelEncoder(byte[] bArr) {
        this.j = bArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.zxing.common.BitArray a() {
        /*
            r8 = this;
            com.xiaomi.zxing.aztec.encoder.State r0 = com.xiaomi.zxing.aztec.encoder.State.f1643a
            java.util.List r0 = java.util.Collections.singletonList(r0)
            r1 = 0
            r2 = r0
            r0 = 0
        L_0x0009:
            byte[] r3 = r8.j
            int r3 = r3.length
            if (r0 >= r3) goto L_0x0054
            int r3 = r0 + 1
            byte[] r4 = r8.j
            int r4 = r4.length
            if (r3 >= r4) goto L_0x001a
            byte[] r4 = r8.j
            byte r4 = r4[r3]
            goto L_0x001b
        L_0x001a:
            r4 = 0
        L_0x001b:
            byte[] r5 = r8.j
            byte r5 = r5[r0]
            r6 = 13
            if (r5 == r6) goto L_0x003f
            r6 = 44
            r7 = 32
            if (r5 == r6) goto L_0x003b
            r6 = 46
            if (r5 == r6) goto L_0x0037
            r6 = 58
            if (r5 == r6) goto L_0x0033
        L_0x0031:
            r4 = 0
            goto L_0x0044
        L_0x0033:
            if (r4 != r7) goto L_0x0031
            r4 = 5
            goto L_0x0044
        L_0x0037:
            if (r4 != r7) goto L_0x0031
            r4 = 3
            goto L_0x0044
        L_0x003b:
            if (r4 != r7) goto L_0x0031
            r4 = 4
            goto L_0x0044
        L_0x003f:
            r5 = 10
            if (r4 != r5) goto L_0x0031
            r4 = 2
        L_0x0044:
            if (r4 <= 0) goto L_0x004d
            java.util.Collection r0 = a((java.lang.Iterable<com.xiaomi.zxing.aztec.encoder.State>) r2, (int) r0, (int) r4)
            r2 = r0
            r0 = r3
            goto L_0x0051
        L_0x004d:
            java.util.Collection r2 = r8.a(r2, r0)
        L_0x0051:
            int r0 = r0 + 1
            goto L_0x0009
        L_0x0054:
            com.xiaomi.zxing.aztec.encoder.HighLevelEncoder$1 r0 = new com.xiaomi.zxing.aztec.encoder.HighLevelEncoder$1
            r0.<init>()
            java.lang.Object r0 = java.util.Collections.min(r2, r0)
            com.xiaomi.zxing.aztec.encoder.State r0 = (com.xiaomi.zxing.aztec.encoder.State) r0
            byte[] r1 = r8.j
            com.xiaomi.zxing.common.BitArray r0 = r0.a((byte[]) r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.aztec.encoder.HighLevelEncoder.a():com.xiaomi.zxing.common.BitArray");
    }

    private Collection<State> a(Iterable<State> iterable, int i2) {
        LinkedList linkedList = new LinkedList();
        for (State a2 : iterable) {
            a(a2, i2, (Collection<State>) linkedList);
        }
        return a(linkedList);
    }

    private void a(State state, int i2, Collection<State> collection) {
        char c2 = (char) (this.j[i2] & 255);
        boolean z = i[state.a()][c2] > 0;
        State state2 = null;
        for (int i3 = 0; i3 <= 4; i3++) {
            int i4 = i[i3][c2];
            if (i4 > 0) {
                if (state2 == null) {
                    state2 = state.b(i2);
                }
                if (!z || i3 == state.a() || i3 == 2) {
                    collection.add(state2.a(i3, i4));
                }
                if (!z && h[state.a()][i3] >= 0) {
                    collection.add(state2.b(i3, i4));
                }
            }
        }
        if (state.c() > 0 || i[state.a()][c2] == 0) {
            collection.add(state.a(i2));
        }
    }

    private static Collection<State> a(Iterable<State> iterable, int i2, int i3) {
        LinkedList linkedList = new LinkedList();
        for (State a2 : iterable) {
            a(a2, i2, i3, linkedList);
        }
        return a(linkedList);
    }

    private static void a(State state, int i2, int i3, Collection<State> collection) {
        State b2 = state.b(i2);
        collection.add(b2.a(4, i3));
        if (state.a() != 4) {
            collection.add(b2.b(4, i3));
        }
        if (i3 == 3 || i3 == 4) {
            collection.add(b2.a(2, 16 - i3).a(2, 1));
        }
        if (state.c() > 0) {
            collection.add(state.a(i2).a(i2 + 1));
        }
    }

    private static Collection<State> a(Iterable<State> iterable) {
        LinkedList linkedList = new LinkedList();
        for (State next : iterable) {
            boolean z = true;
            Iterator it = linkedList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                State state = (State) it.next();
                if (state.a(next)) {
                    z = false;
                    break;
                } else if (next.a(state)) {
                    it.remove();
                }
            }
            if (z) {
                linkedList.add(next);
            }
        }
        return linkedList;
    }
}
