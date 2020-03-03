package com.h6ah4i.android.widget.advrecyclerview.expandable;

import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

class ExpandablePositionTranslator {

    /* renamed from: a  reason: collision with root package name */
    private static final int f5715a = 256;
    private static final long b = 2147483648L;
    private static final long c = 2147483647L;
    private static final long d = 4294967295L;
    private static final long e = -4294967296L;
    private long[] f;
    private int[] g;
    private int h;
    private int i;
    private int j;
    private int k = -1;
    private ExpandableItemAdapter l;

    public void a(ExpandableItemAdapter expandableItemAdapter, boolean z) {
        int a2 = expandableItemAdapter.a();
        b(a2, false);
        long[] jArr = this.f;
        int[] iArr = this.g;
        int i2 = 0;
        for (int i3 = 0; i3 < a2; i3++) {
            long b2 = expandableItemAdapter.b(i3);
            int a3 = expandableItemAdapter.a(i3);
            if (z) {
                jArr[i3] = (((long) (i3 + i2)) << 32) | ((long) a3) | 2147483648L;
            } else {
                jArr[i3] = (((long) i3) << 32) | ((long) a3);
            }
            iArr[i3] = (int) (b2 & 4294967295L);
            i2 += a3;
        }
        this.l = expandableItemAdapter;
        this.h = a2;
        this.i = z ? a2 : 0;
        if (!z) {
            i2 = 0;
        }
        this.j = i2;
        this.k = Math.max(0, a2 - 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0054, code lost:
        if (r2.b(r6, false) != false) goto L_0x0058;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int[] r19, com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter r20, com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager.OnGroupExpandListener r21, com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager.OnGroupCollapseListener r22) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r22
            if (r1 == 0) goto L_0x00ad
            int r5 = r1.length
            if (r5 != 0) goto L_0x0011
            goto L_0x00ad
        L_0x0011:
            long[] r5 = r0.f
            if (r5 != 0) goto L_0x0016
            return
        L_0x0016:
            int r5 = r0.h
            long[] r5 = new long[r5]
            r7 = 0
        L_0x001b:
            int r8 = r0.h
            r9 = 32
            if (r7 >= r8) goto L_0x002f
            int[] r8 = r0.g
            r8 = r8[r7]
            long r10 = (long) r8
            long r8 = r10 << r9
            long r10 = (long) r7
            long r8 = r8 | r10
            r5[r7] = r8
            int r7 = r7 + 1
            goto L_0x001b
        L_0x002f:
            java.util.Arrays.sort(r5)
            r7 = 0
            r8 = 0
        L_0x0034:
            int r10 = r1.length
            r11 = 2147483647(0x7fffffff, double:1.060997895E-314)
            if (r7 >= r10) goto L_0x0088
            r10 = r1[r7]
            r13 = r8
        L_0x003d:
            int r14 = r5.length
            if (r8 >= r14) goto L_0x0082
            r14 = r5[r8]
            long r14 = r14 >> r9
            int r14 = (int) r14
            r15 = r5[r8]
            r17 = r7
            long r6 = r15 & r11
            int r6 = (int) r6
            if (r14 >= r10) goto L_0x0065
            if (r2 == 0) goto L_0x0057
            r7 = 0
            boolean r13 = r2.b((int) r6, (boolean) r7)
            if (r13 == 0) goto L_0x0063
            goto L_0x0058
        L_0x0057:
            r7 = 0
        L_0x0058:
            boolean r13 = r0.d(r6)
            if (r13 == 0) goto L_0x0063
            if (r4 == 0) goto L_0x0063
            r4.a(r6, r7)
        L_0x0063:
            r13 = r8
            goto L_0x007d
        L_0x0065:
            r7 = 0
            if (r14 != r10) goto L_0x0084
            int r13 = r8 + 1
            if (r2 == 0) goto L_0x0072
            boolean r14 = r2.a((int) r6, (boolean) r7)
            if (r14 == 0) goto L_0x007d
        L_0x0072:
            boolean r14 = r0.e(r6)
            if (r14 == 0) goto L_0x007d
            if (r3 == 0) goto L_0x007d
            r3.b(r6, r7)
        L_0x007d:
            int r8 = r8 + 1
            r7 = r17
            goto L_0x003d
        L_0x0082:
            r17 = r7
        L_0x0084:
            int r7 = r17 + 1
            r8 = r13
            goto L_0x0034
        L_0x0088:
            if (r2 != 0) goto L_0x008c
            if (r4 == 0) goto L_0x00ac
        L_0x008c:
            int r1 = r5.length
            if (r8 >= r1) goto L_0x00ac
            r6 = r5[r8]
            long r6 = r6 & r11
            int r1 = (int) r6
            if (r2 == 0) goto L_0x009d
            r3 = 0
            boolean r6 = r2.b((int) r1, (boolean) r3)
            if (r6 == 0) goto L_0x00a9
            goto L_0x009e
        L_0x009d:
            r3 = 0
        L_0x009e:
            boolean r6 = r0.d(r1)
            if (r6 == 0) goto L_0x00a9
            if (r4 == 0) goto L_0x00a9
            r4.a(r1, r3)
        L_0x00a9:
            int r8 = r8 + 1
            goto L_0x008c
        L_0x00ac:
            return
        L_0x00ad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandablePositionTranslator.a(int[], com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemAdapter, com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager$OnGroupExpandListener, com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager$OnGroupCollapseListener):void");
    }

    public int[] a() {
        int[] iArr = new int[this.i];
        int i2 = 0;
        for (int i3 = 0; i3 < this.h; i3++) {
            if ((this.f[i3] & 2147483648L) != 0) {
                iArr[i2] = this.g[i3];
                i2++;
            }
        }
        if (i2 == this.i) {
            Arrays.sort(iArr);
            return iArr;
        }
        throw new IllegalStateException("may be a bug  (index = " + i2 + ", mExpandedGroupCount = " + this.i + Operators.BRACKET_END_STR);
    }

    public int b() {
        return this.h + this.j;
    }

    public boolean a(int i2) {
        return (this.f[i2] & 2147483648L) != 0;
    }

    public int b(int i2) {
        return (int) (this.f[i2] & c);
    }

    public int c(int i2) {
        if (a(i2)) {
            return b(i2);
        }
        return 0;
    }

    public boolean d(int i2) {
        if ((this.f[i2] & 2147483648L) == 0) {
            return false;
        }
        int i3 = (int) (this.f[i2] & c);
        long[] jArr = this.f;
        jArr[i2] = jArr[i2] & -2147483649L;
        this.i--;
        this.j -= i3;
        this.k = Math.min(this.k, i2);
        return true;
    }

    public boolean e(int i2) {
        if ((this.f[i2] & 2147483648L) != 0) {
            return false;
        }
        int i3 = (int) (this.f[i2] & c);
        long[] jArr = this.f;
        jArr[i2] = 2147483648L | jArr[i2];
        this.i++;
        this.j += i3;
        this.k = Math.min(this.k, i2);
        return true;
    }

    public void a(int i2, int i3) {
        if (i2 != i3) {
            long j2 = this.f[i2];
            int i4 = this.g[i2];
            if (i3 < i2) {
                for (int i5 = i2; i5 > i3; i5--) {
                    int i6 = i5 - 1;
                    this.f[i5] = this.f[i6];
                    this.g[i5] = this.g[i6];
                }
            } else {
                int i7 = i2;
                while (i7 < i3) {
                    int i8 = i7 + 1;
                    this.f[i7] = this.f[i8];
                    this.g[i7] = this.g[i8];
                    i7 = i8;
                }
            }
            this.f[i3] = j2;
            this.g[i3] = i4;
            int min = Math.min(i2, i3);
            if (min > 0) {
                this.k = Math.min(this.k, min - 1);
            } else {
                this.k = -1;
            }
        }
    }

    public void a(int i2, int i3, int i4, int i5) {
        if (i2 != i4) {
            int i6 = (int) (this.f[i2] & c);
            int i7 = (int) (this.f[i4] & c);
            if (i6 != 0) {
                this.f[i2] = (this.f[i2] & -2147483648L) | ((long) (i6 - 1));
                this.f[i4] = ((long) (i7 + 1)) | (this.f[i4] & -2147483648L);
                if ((this.f[i2] & 2147483648L) != 0) {
                    this.j--;
                }
                if ((this.f[i4] & 2147483648L) != 0) {
                    this.j++;
                }
                int min = Math.min(i2, i4);
                if (min > 0) {
                    this.k = Math.min(this.k, min - 1);
                } else {
                    this.k = -1;
                }
            } else {
                throw new IllegalStateException("moveChildItem(fromGroupPosition = " + i2 + ", fromChildPosition = " + i3 + ", toGroupPosition = " + i4 + ", toChildPosition = " + i5 + ")  --- may be a bug.");
            }
        }
    }

    public long f(int i2) {
        int i3;
        long j2 = -1;
        if (i2 == -1) {
            return -1;
        }
        int i4 = this.h;
        int a2 = a(this.f, this.k, i2);
        int i5 = this.k;
        if (a2 == 0) {
            i3 = 0;
        } else {
            i3 = (int) (this.f[a2] >>> 32);
        }
        while (true) {
            int i6 = i5;
            i5 = a2;
            int i7 = i6;
            if (i5 >= i4) {
                i5 = i7;
                break;
            }
            long j3 = this.f[i5];
            this.f[i5] = (((long) i3) << 32) | (4294967295L & j3);
            if (i3 >= i2) {
                j2 = ExpandableAdapterHelper.a(i5);
                break;
            }
            int i8 = i3 + 1;
            if ((2147483648L & j3) != 0) {
                int i9 = (int) (j3 & c);
                if (i9 > 0 && (i8 + i9) - 1 >= i2) {
                    j2 = ExpandableAdapterHelper.a(i5, i2 - i8);
                    break;
                }
                i8 += i9;
            }
            a2 = i5 + 1;
        }
        this.k = Math.max(this.k, i5);
        return j2;
    }

    public int a(long j2) {
        int i2 = -1;
        if (j2 == -1) {
            return -1;
        }
        int b2 = ExpandableAdapterHelper.b(j2);
        int a2 = ExpandableAdapterHelper.a(j2);
        int i3 = this.h;
        if (b2 < 0 || b2 >= i3) {
            return -1;
        }
        if (a2 != -1 && !a(b2)) {
            return -1;
        }
        int max = Math.max(0, Math.min(b2, this.k));
        int i4 = this.k;
        int i5 = (int) (this.f[max] >>> 32);
        while (true) {
            int i6 = i4;
            i4 = max;
            int i7 = i6;
            if (i4 >= i3) {
                i4 = i7;
                break;
            }
            long j3 = this.f[i4];
            this.f[i4] = (((long) i5) << 32) | (4294967295L & j3);
            int i8 = (int) (c & j3);
            if (i4 != b2) {
                i5++;
                if ((j3 & 2147483648L) != 0) {
                    i5 += i8;
                }
                max = i4 + 1;
            } else if (a2 == -1) {
                i2 = i5;
            } else if (a2 < i8) {
                i2 = i5 + 1 + a2;
            }
        }
        this.k = Math.max(this.k, i4);
        return i2;
    }

    private static int a(long[] jArr, int i2, int i3) {
        int i4 = 0;
        if (i2 <= 0) {
            return 0;
        }
        int i5 = (int) (jArr[0] >>> 32);
        int i6 = (int) (jArr[i2] >>> 32);
        if (i3 <= i5) {
            return 0;
        }
        if (i3 >= i6) {
            return i2;
        }
        int i7 = 0;
        while (i4 < i2) {
            int i8 = (i4 + i2) >>> 1;
            if (((int) (jArr[i8] >>> 32)) < i3) {
                i7 = i4;
                i4 = i8 + 1;
            } else {
                i2 = i8;
            }
        }
        return i7;
    }

    public void b(int i2, int i3) {
        a(i2, i3, 1);
    }

    public void a(int i2, int i3, int i4) {
        long j2 = this.f[i2];
        int i5 = (int) (c & j2);
        if (i3 < 0 || i3 + i4 > i5) {
            throw new IllegalStateException("Invalid child position removeChildItems(groupPosition = " + i2 + ", childPosition = " + i3 + ", count = " + i4 + Operators.BRACKET_END_STR);
        }
        if ((2147483648L & j2) != 0) {
            this.j -= i4;
        }
        this.f[i2] = (j2 & -2147483648L) | ((long) (i5 - i4));
        this.k = Math.min(this.k, i2 - 1);
    }

    public void c(int i2, int i3) {
        b(i2, i3, 1);
    }

    public void b(int i2, int i3, int i4) {
        long j2 = this.f[i2];
        int i5 = (int) (c & j2);
        if (i3 < 0 || i3 > i5) {
            throw new IllegalStateException("Invalid child position insertChildItems(groupPosition = " + i2 + ", childPositionStart = " + i3 + ", count = " + i4 + Operators.BRACKET_END_STR);
        }
        if ((2147483648L & j2) != 0) {
            this.j += i4;
        }
        this.f[i2] = (j2 & -2147483648L) | ((long) (i5 + i4));
        this.k = Math.min(this.k, i2);
    }

    public int a(int i2, int i3, boolean z) {
        int i4 = 0;
        if (i3 <= 0) {
            return 0;
        }
        b(this.h + i3, true);
        ExpandableItemAdapter expandableItemAdapter = this.l;
        long[] jArr = this.f;
        int[] iArr = this.g;
        int i5 = i2 - 1;
        int i6 = i5 + i3;
        for (int i7 = (this.h - 1) + i3; i7 > i6; i7--) {
            int i8 = i7 - i3;
            jArr[i7] = jArr[i8];
            iArr[i7] = iArr[i8];
        }
        long j2 = z ? 2147483648L : 0;
        int i9 = i2 + i3;
        int i10 = i2;
        while (i10 < i9) {
            long b2 = expandableItemAdapter.b(i10);
            int a2 = expandableItemAdapter.a(i10);
            jArr[i10] = ((long) a2) | (((long) i10) << 32) | j2;
            iArr[i10] = (int) (4294967295L & b2);
            i4 += a2;
            i10++;
            expandableItemAdapter = expandableItemAdapter;
            i5 = i5;
        }
        int i11 = i5;
        this.h += i3;
        if (z) {
            this.i += i3;
            this.j += i4;
        }
        this.k = Math.min(this.k, this.h == 0 ? -1 : i11);
        return z ? i3 + i4 : i3;
    }

    public int a(int i2, boolean z) {
        return a(i2, 1, z);
    }

    public int d(int i2, int i3) {
        if (i3 <= 0) {
            return 0;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            long j2 = this.f[i2 + i5];
            if ((2147483648L & j2) != 0) {
                int i6 = (int) (j2 & c);
                i4 += i6;
                this.j -= i6;
                this.i--;
            }
        }
        int i7 = i4 + i3;
        this.h -= i3;
        for (int i8 = i2; i8 < this.h; i8++) {
            int i9 = i8 + i3;
            this.f[i8] = this.f[i9];
            this.g[i8] = this.g[i9];
        }
        this.k = Math.min(this.k, this.h == 0 ? -1 : i2 - 1);
        return i7;
    }

    public int g(int i2) {
        return d(i2, 1);
    }

    private void b(int i2, boolean z) {
        long[] jArr;
        int[] iArr;
        int i3 = (i2 + 511) & -256;
        long[] jArr2 = this.f;
        int[] iArr2 = this.g;
        if (jArr2 == null || jArr2.length < i2) {
            jArr = new long[i3];
        } else {
            jArr = jArr2;
        }
        if (iArr2 == null || iArr2.length < i2) {
            iArr = new int[i3];
        } else {
            iArr = iArr2;
        }
        if (z) {
            if (!(jArr2 == null || jArr2 == jArr)) {
                System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            }
            if (!(iArr2 == null || iArr2 == iArr)) {
                System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            }
        }
        this.f = jArr;
        this.g = iArr;
    }

    public int c() {
        return this.i;
    }

    public int d() {
        return this.h - this.i;
    }

    public boolean e() {
        return !g() && this.i == this.h;
    }

    public boolean f() {
        return g() || this.i == 0;
    }

    public boolean g() {
        return this.h == 0;
    }
}
