package com.loc;

import java.util.HashMap;

public final class ec {

    /* renamed from: a  reason: collision with root package name */
    private HashMap<Long, ed> f6577a = new HashMap<>();
    private long b = 0;

    private static long a(int i, int i2) {
        return (((long) i2) & 65535) | ((((long) i) & 65535) << 32);
    }

    public final long a(ed edVar) {
        long j;
        int i;
        int i2;
        if (edVar == null || !edVar.p) {
            return 0;
        }
        HashMap<Long, ed> hashMap = this.f6577a;
        switch (edVar.k) {
            case 1:
            case 3:
            case 4:
                i2 = edVar.c;
                i = edVar.d;
                break;
            case 2:
                i2 = edVar.h;
                i = edVar.i;
                break;
            default:
                j = 0;
                break;
        }
        j = a(i2, i);
        ed edVar2 = hashMap.get(Long.valueOf(j));
        if (edVar2 == null) {
            edVar.m = fa.c();
            hashMap.put(Long.valueOf(j), edVar);
            return 0;
        } else if (edVar2.j != edVar.j) {
            edVar.m = fa.c();
            hashMap.put(Long.valueOf(j), edVar);
            return 0;
        } else {
            edVar.m = edVar2.m;
            hashMap.put(Long.valueOf(j), edVar);
            return (fa.c() - edVar2.m) / 1000;
        }
    }

    public final void a() {
        this.f6577a.clear();
        this.b = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.util.ArrayList<? extends com.loc.ed> r13) {
        /*
            r12 = this;
            if (r13 == 0) goto L_0x0090
            long r0 = com.loc.fa.c()
            long r2 = r12.b
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x001a
            long r2 = r12.b
            long r2 = r0 - r2
            r6 = 60000(0xea60, double:2.9644E-319)
            int r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x001a
            return
        L_0x001a:
            java.util.HashMap<java.lang.Long, com.loc.ed> r2 = r12.f6577a
            int r3 = r13.size()
            r6 = 0
            r7 = r4
            r4 = 0
        L_0x0023:
            if (r4 >= r3) goto L_0x005e
            java.lang.Object r5 = r13.get(r4)
            com.loc.ed r5 = (com.loc.ed) r5
            boolean r9 = r5.p
            if (r9 == 0) goto L_0x005b
            int r9 = r5.k
            switch(r9) {
                case 1: goto L_0x003a;
                case 2: goto L_0x0035;
                case 3: goto L_0x003a;
                case 4: goto L_0x003a;
                default: goto L_0x0034;
            }
        L_0x0034:
            goto L_0x0042
        L_0x0035:
            int r7 = r5.h
            int r8 = r5.i
            goto L_0x003e
        L_0x003a:
            int r7 = r5.c
            int r8 = r5.d
        L_0x003e:
            long r7 = a(r7, r8)
        L_0x0042:
            java.lang.Long r9 = java.lang.Long.valueOf(r7)
            java.lang.Object r9 = r2.get(r9)
            com.loc.ed r9 = (com.loc.ed) r9
            if (r9 == 0) goto L_0x005b
            int r10 = r9.j
            int r11 = r5.j
            if (r10 != r11) goto L_0x0059
            long r9 = r9.m
            r5.m = r9
            goto L_0x005b
        L_0x0059:
            r5.m = r0
        L_0x005b:
            int r4 = r4 + 1
            goto L_0x0023
        L_0x005e:
            r2.clear()
            int r3 = r13.size()
        L_0x0065:
            if (r6 >= r3) goto L_0x008e
            java.lang.Object r4 = r13.get(r6)
            com.loc.ed r4 = (com.loc.ed) r4
            boolean r5 = r4.p
            if (r5 == 0) goto L_0x008b
            int r5 = r4.k
            switch(r5) {
                case 1: goto L_0x007c;
                case 2: goto L_0x0077;
                case 3: goto L_0x007c;
                case 4: goto L_0x007c;
                default: goto L_0x0076;
            }
        L_0x0076:
            goto L_0x0084
        L_0x0077:
            int r5 = r4.h
            int r7 = r4.i
            goto L_0x0080
        L_0x007c:
            int r5 = r4.c
            int r7 = r4.d
        L_0x0080:
            long r7 = a(r5, r7)
        L_0x0084:
            java.lang.Long r5 = java.lang.Long.valueOf(r7)
            r2.put(r5, r4)
        L_0x008b:
            int r6 = r6 + 1
            goto L_0x0065
        L_0x008e:
            r12.b = r0
        L_0x0090:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ec.a(java.util.ArrayList):void");
    }
}
