package com.amap.openapi;

import android.support.annotation.Nullable;
import com.loc.fc;
import java.util.List;

public class cg extends cf {
    public cg() {
        super(1000);
    }

    @Nullable
    public byte[] a(byte b, String str, byte b2, String str2, String str3, byte b3, String str4, String str5, String str6, String str7, long j, String str8, String str9, String str10, String str11, List<Long> list, List<String> list2) {
        int i;
        List<Long> list3 = list;
        List<String> list4 = list2;
        super.a();
        try {
            int a2 = this.f4661a.a(str);
            int a3 = this.f4661a.a(str2);
            int a4 = this.f4661a.a(str3);
            int a5 = this.f4661a.a(str4);
            int a6 = this.f4661a.a(str5);
            int a7 = this.f4661a.a(str6);
            int a8 = this.f4661a.a(str7);
            int a9 = this.f4661a.a(str8);
            int a10 = this.f4661a.a(str9);
            int a11 = this.f4661a.a(str10);
            int a12 = this.f4661a.a(str11);
            bl.a(this.f4661a);
            bl.a((fc) this.f4661a, b2);
            bl.a((fc) this.f4661a, a3);
            bl.b((fc) this.f4661a, a4);
            bl.b((fc) this.f4661a, b3);
            bl.e(this.f4661a, a5);
            bl.f(this.f4661a, a6);
            bl.d(this.f4661a, a7);
            bl.c(this.f4661a, a8);
            bl.a((fc) this.f4661a, j);
            bl.g(this.f4661a, a9);
            bl.h(this.f4661a, a10);
            bl.i(this.f4661a, a11);
            bl.j(this.f4661a, a12);
            int b4 = bl.b(this.f4661a);
            int i2 = 0;
            if (list3 == null || list.size() <= 0) {
                i = 0;
            } else {
                long[] jArr = new long[list.size()];
                for (int i3 = 0; i3 < list.size(); i3++) {
                    Long l = list3.get(i3);
                    if (l != null) {
                        jArr[i3] = l.longValue();
                    } else {
                        jArr[i3] = 0;
                    }
                }
                i = cj.a((fc) this.f4661a, jArr);
            }
            if (list4 != null && list2.size() > 0) {
                int[] iArr = new int[list2.size()];
                while (i2 < list2.size()) {
                    iArr[i2] = this.f4661a.a(list4.get(i2));
                    i2++;
                }
                i2 = cj.a((fc) this.f4661a, iArr);
            }
            cj.a(this.f4661a);
            cj.a((fc) this.f4661a, b);
            cj.a((fc) this.f4661a, a2);
            cj.a((fc) this.f4661a, System.currentTimeMillis() / 1000);
            cj.b(this.f4661a, b4);
            if (i > 0) {
                cj.c(this.f4661a, i);
            }
            if (i2 > 0) {
                cj.d(this.f4661a, i2);
            }
            cj.e(this.f4661a, cj.b(this.f4661a));
            return this.f4661a.f();
        } catch (Throwable unused) {
            return null;
        }
    }
}
