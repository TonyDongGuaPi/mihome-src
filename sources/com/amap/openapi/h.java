package com.amap.openapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.amap.location.common.util.d;
import com.loc.fc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class h extends g {
    public h() {
        super(2048);
    }

    private int a(long j, @NonNull List<aa> list) {
        List<aa> list2 = list;
        a(list2);
        int size = list.size();
        if (size <= 0) {
            return -1;
        }
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            aa aaVar = list2.get(i);
            iArr[i] = ar.a(this.f4726a, aaVar.f4607a == j && aaVar.f4607a != -1, aaVar.f4607a, aaVar.b, this.f4726a.a(aaVar.c), aaVar.d, aaVar.f);
        }
        return aq.a((fc) this.f4726a, aq.a((fc) this.f4726a, iArr));
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00eb A[LOOP:0: B:3:0x0014->B:27:0x00eb, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ff A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int a(@android.support.annotation.NonNull com.amap.openapi.q r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            java.util.ArrayList<com.amap.openapi.r> r2 = r1.c
            r0.a((java.util.ArrayList<com.amap.openapi.r>) r2)
            java.util.ArrayList<com.amap.openapi.r> r2 = r1.c
            int r2 = r2.size()
            if (r2 <= 0) goto L_0x017d
            int[] r4 = new int[r2]
            r6 = 0
        L_0x0014:
            r7 = 2
            r8 = 1
            if (r6 >= r2) goto L_0x0100
            java.util.ArrayList<com.amap.openapi.r> r9 = r1.c
            java.lang.Object r9 = r9.get(r6)
            com.amap.openapi.r r9 = (com.amap.openapi.r) r9
            byte r10 = r9.f4741a
            if (r10 != r8) goto L_0x0060
            T r7 = r9.f
            com.amap.openapi.w r7 = (com.amap.openapi.w) r7
            byte r8 = r9.c
            if (r8 != 0) goto L_0x003e
            com.amap.openapi.i r8 = r0.f4726a
            int r10 = r7.c
            int r11 = r7.d
            int r12 = r7.e
            int r7 = r7.i
            int r7 = com.amap.openapi.al.a(r8, r10, r11, r12, r7)
            r15 = r7
        L_0x003b:
            r3 = -1
            goto L_0x00e9
        L_0x003e:
            com.amap.openapi.i r10 = r0.f4726a
            int r11 = r7.f4747a
            int r12 = r7.b
            int r13 = r7.c
            int r14 = r7.d
            int r15 = r7.e
            int r8 = r7.f
            int r5 = r7.g
            int r3 = r7.h
            int r7 = r7.i
            r16 = r8
            r17 = r5
            r18 = r3
            r19 = r7
            int r3 = com.amap.openapi.al.a(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
        L_0x005e:
            r15 = r3
            goto L_0x003b
        L_0x0060:
            byte r3 = r9.f4741a
            r5 = 3
            if (r3 != r5) goto L_0x0086
            T r3 = r9.f
            com.amap.openapi.x r3 = (com.amap.openapi.x) r3
            com.amap.openapi.i r10 = r0.f4726a
            int r11 = r3.f4748a
            int r12 = r3.b
            int r13 = r3.c
            int r14 = r3.d
            int r15 = r3.e
            int r5 = r3.f
            int r7 = r3.g
            int r3 = r3.h
            r16 = r5
            r17 = r7
            r18 = r3
            int r3 = com.amap.openapi.am.a(r10, r11, r12, r13, r14, r15, r16, r17, r18)
            goto L_0x005e
        L_0x0086:
            byte r3 = r9.f4741a
            if (r3 != r7) goto L_0x00c0
            T r3 = r9.f
            com.amap.openapi.p r3 = (com.amap.openapi.p) r3
            byte r5 = r9.c
            if (r5 != 0) goto L_0x00a7
            com.amap.openapi.i r10 = r0.f4726a
            int r11 = r3.f4739a
            int r12 = r3.b
            int r13 = r3.c
            int r14 = r3.d
            int r15 = r3.e
            int r3 = r3.f
            r16 = r3
            int r3 = com.amap.openapi.ae.a(r10, r11, r12, r13, r14, r15, r16)
            goto L_0x005e
        L_0x00a7:
            com.amap.openapi.i r10 = r0.f4726a
            int r11 = r3.f4739a
            int r12 = r3.b
            int r13 = r3.c
            int r14 = r3.d
            int r15 = r3.e
            int r5 = r3.f
            int r3 = r3.g
            r16 = r5
            r17 = r3
            int r3 = com.amap.openapi.ae.a(r10, r11, r12, r13, r14, r15, r16, r17)
            goto L_0x005e
        L_0x00c0:
            byte r3 = r9.f4741a
            r5 = 4
            if (r3 != r5) goto L_0x00e7
            T r3 = r9.f
            com.amap.openapi.z r3 = (com.amap.openapi.z) r3
            com.amap.openapi.i r10 = r0.f4726a
            int r11 = r3.f4750a
            int r12 = r3.b
            int r13 = r3.c
            int r14 = r3.d
            int r15 = r3.e
            int r5 = r3.f
            int r7 = r3.g
            int r3 = r3.h
            r16 = r5
            r17 = r7
            r18 = r3
            int r3 = com.amap.openapi.ap.a(r10, r11, r12, r13, r14, r15, r16, r17, r18)
            goto L_0x005e
        L_0x00e7:
            r3 = -1
            r15 = -1
        L_0x00e9:
            if (r15 == r3) goto L_0x00ff
            com.amap.openapi.i r10 = r0.f4726a
            byte r11 = r9.b
            byte r12 = r9.c
            short r13 = r9.d
            byte r14 = r9.f4741a
            int r3 = com.amap.openapi.ah.a(r10, r11, r12, r13, r14, r15)
            r4[r6] = r3
            int r6 = r6 + 1
            goto L_0x0014
        L_0x00ff:
            return r3
        L_0x0100:
            com.amap.openapi.i r2 = r0.f4726a
            java.lang.String r3 = r1.b
            int r2 = r2.a(r3)
            com.amap.openapi.i r3 = r0.f4726a
            int r3 = com.amap.openapi.af.a((com.loc.fc) r3, (int[]) r4)
            java.util.List<com.amap.location.common.model.CellStatus$HistoryCell> r4 = r1.d
            int r4 = r4.size()
            int[] r5 = new int[r4]
            r6 = 0
        L_0x0117:
            if (r6 >= r4) goto L_0x016e
            java.util.List<com.amap.location.common.model.CellStatus$HistoryCell> r9 = r1.d
            java.lang.Object r9 = r9.get(r6)
            com.amap.location.common.model.CellStatus$HistoryCell r9 = (com.amap.location.common.model.CellStatus.HistoryCell) r9
            long r10 = android.os.SystemClock.elapsedRealtime()
            long r12 = r9.h
            long r10 = r10 - r12
            r12 = 1000(0x3e8, double:4.94E-321)
            long r10 = r10 / r12
            r12 = 32767(0x7fff, double:1.6189E-319)
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 > 0) goto L_0x0137
            r14 = 0
            int r16 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r16 >= 0) goto L_0x0138
        L_0x0137:
            r10 = r12
        L_0x0138:
            int r12 = r9.f4586a
            if (r12 != r7) goto L_0x0154
            com.amap.openapi.i r13 = r0.f4726a
            r14 = 2
            int r15 = r9.e
            int r12 = r9.f
            int r9 = r9.g
            int r10 = (int) r10
            short r10 = (short) r10
            r16 = r12
            r17 = r9
            r18 = r10
            int r9 = com.amap.openapi.an.a(r13, r14, r15, r16, r17, r18)
            r10 = r9
            r9 = 2
            goto L_0x0162
        L_0x0154:
            com.amap.openapi.i r12 = r0.f4726a
            int r13 = r9.c
            int r9 = r9.d
            int r10 = (int) r10
            short r10 = (short) r10
            int r9 = com.amap.openapi.ao.a(r12, r8, r13, r9, r10)
            r10 = r9
            r9 = 1
        L_0x0162:
            com.amap.openapi.i r11 = r0.f4726a
            byte r9 = (byte) r9
            int r9 = com.amap.openapi.ag.a(r11, r9, r10)
            r5[r6] = r9
            int r6 = r6 + 1
            goto L_0x0117
        L_0x016e:
            com.amap.openapi.i r4 = r0.f4726a
            int r4 = com.amap.openapi.af.b((com.loc.fc) r4, (int[]) r5)
            com.amap.openapi.i r5 = r0.f4726a
            byte r1 = r1.f4740a
            int r3 = com.amap.openapi.af.a(r5, r2, r1, r3, r4)
            goto L_0x017e
        L_0x017d:
            r3 = -1
        L_0x017e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.h.a(com.amap.openapi.q):int");
    }

    private int a(@NonNull v vVar) {
        return aj.a(this.f4726a, vVar.f4746a, vVar.b, vVar.c, vVar.d, vVar.e, vVar.f, vVar.g, vVar.h, vVar.i);
    }

    private void a(ArrayList<r> arrayList) {
        int i;
        int i2;
        if (arrayList != null && arrayList.size() != 0) {
            Iterator<r> it = arrayList.iterator();
            while (it.hasNext()) {
                r next = it.next();
                if (next.f4741a == 1) {
                    w wVar = (w) next.f;
                    i = wVar.c;
                    i2 = wVar.d;
                } else if (next.f4741a == 3) {
                    x xVar = (x) next.f;
                    i = xVar.c;
                    i2 = xVar.d;
                } else if (next.f4741a == 4) {
                    z zVar = (z) next.f;
                    i = zVar.c;
                    i2 = zVar.d;
                } else if (next.f4741a == 2) {
                    p pVar = (p) next.f;
                    i = pVar.b;
                    i2 = pVar.c;
                }
                next.d = as.a(as.a(i, i2));
            }
        }
    }

    private void a(@NonNull List<aa> list) {
        for (aa next : list) {
            next.d = as.b(next.f4607a);
        }
    }

    @Nullable
    public byte[] a(@NonNull Context context, @NonNull v vVar, @Nullable q qVar, long j, @Nullable List<aa> list) {
        super.a();
        try {
            int a2 = a(vVar);
            int i = -1;
            int a3 = (qVar == null || qVar.c.size() <= 0) ? -1 : a(qVar);
            if (list != null && list.size() > 0) {
                i = a(j, list);
            }
            ab.a(this.f4726a);
            ab.a(this.f4726a, a2);
            if (a3 > 0) {
                ab.c(this.f4726a, a3);
            }
            if (i > 0) {
                ab.b(this.f4726a, i);
            }
            this.f4726a.h(ab.b(this.f4726a));
            return aw.a(az.a(context), d.a(this.f4726a.f()));
        } catch (Throwable unused) {
            return null;
        }
    }
}
