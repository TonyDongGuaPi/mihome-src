package org.jacoco.agent.rt.internal_8ff85ea.asm;

final class Item {

    /* renamed from: a  reason: collision with root package name */
    int f3595a;
    int b;
    int c;
    long d;
    String e;
    String f;
    String g;
    int h;
    Item i;

    Item() {
    }

    Item(int i2) {
        this.f3595a = i2;
    }

    Item(int i2, Item item) {
        this.f3595a = i2;
        this.b = item.b;
        this.c = item.c;
        this.d = item.d;
        this.e = item.e;
        this.f = item.f;
        this.g = item.g;
        this.h = item.h;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        this.b = 3;
        this.c = i2;
        this.h = Integer.MAX_VALUE & (this.b + i2);
    }

    /* access modifiers changed from: package-private */
    public void a(long j) {
        this.b = 5;
        this.d = j;
        this.h = Integer.MAX_VALUE & (this.b + ((int) j));
    }

    /* access modifiers changed from: package-private */
    public void a(float f2) {
        this.b = 4;
        this.c = Float.floatToRawIntBits(f2);
        this.h = Integer.MAX_VALUE & (this.b + ((int) f2));
    }

    /* access modifiers changed from: package-private */
    public void a(double d2) {
        this.b = 6;
        this.d = Double.doubleToRawLongBits(d2);
        this.h = Integer.MAX_VALUE & (this.b + ((int) d2));
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, String str, String str2, String str3) {
        this.b = i2;
        this.e = str;
        this.f = str2;
        this.g = str3;
        if (i2 != 1) {
            if (i2 == 12) {
                this.h = (i2 + (str.hashCode() * str2.hashCode())) & Integer.MAX_VALUE;
                return;
            } else if (!(i2 == 16 || i2 == 30)) {
                switch (i2) {
                    case 7:
                        this.c = 0;
                        break;
                    case 8:
                        break;
                    default:
                        this.h = (i2 + (str.hashCode() * str2.hashCode() * str3.hashCode())) & Integer.MAX_VALUE;
                        return;
                }
            }
        }
        this.h = (i2 + str.hashCode()) & Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2, int i2) {
        this.b = 18;
        this.d = (long) i2;
        this.e = str;
        this.f = str2;
        this.h = Integer.MAX_VALUE & ((i2 * this.e.hashCode() * this.f.hashCode()) + 18);
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3) {
        this.b = 33;
        this.c = i2;
        this.h = i3;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0052, code lost:
        if (r9.d != r8.d) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0055, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(org.jacoco.agent.rt.internal_8ff85ea.asm.Item r9) {
        /*
            r8 = this;
            int r0 = r8.b
            r1 = 1
            if (r0 == r1) goto L_0x0096
            r2 = 12
            r3 = 0
            if (r0 == r2) goto L_0x007f
            r2 = 16
            if (r0 == r2) goto L_0x0096
            r2 = 18
            if (r0 == r2) goto L_0x0060
            switch(r0) {
                case 3: goto L_0x0057;
                case 4: goto L_0x0057;
                case 5: goto L_0x004c;
                case 6: goto L_0x004c;
                case 7: goto L_0x0096;
                case 8: goto L_0x0096;
                default: goto L_0x0015;
            }
        L_0x0015:
            switch(r0) {
                case 30: goto L_0x0096;
                case 31: goto L_0x0039;
                case 32: goto L_0x004c;
                default: goto L_0x0018;
            }
        L_0x0018:
            java.lang.String r0 = r9.e
            java.lang.String r2 = r8.e
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0037
            java.lang.String r0 = r9.f
            java.lang.String r2 = r8.f
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0037
            java.lang.String r9 = r9.g
            java.lang.String r0 = r8.g
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L_0x0037
            goto L_0x0038
        L_0x0037:
            r1 = 0
        L_0x0038:
            return r1
        L_0x0039:
            int r0 = r9.c
            int r2 = r8.c
            if (r0 != r2) goto L_0x004a
            java.lang.String r9 = r9.e
            java.lang.String r0 = r8.e
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L_0x004a
            goto L_0x004b
        L_0x004a:
            r1 = 0
        L_0x004b:
            return r1
        L_0x004c:
            long r4 = r9.d
            long r6 = r8.d
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 != 0) goto L_0x0055
            goto L_0x0056
        L_0x0055:
            r1 = 0
        L_0x0056:
            return r1
        L_0x0057:
            int r9 = r9.c
            int r0 = r8.c
            if (r9 != r0) goto L_0x005e
            goto L_0x005f
        L_0x005e:
            r1 = 0
        L_0x005f:
            return r1
        L_0x0060:
            long r4 = r9.d
            long r6 = r8.d
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x007d
            java.lang.String r0 = r9.e
            java.lang.String r2 = r8.e
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x007d
            java.lang.String r9 = r9.f
            java.lang.String r0 = r8.f
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L_0x007d
            goto L_0x007e
        L_0x007d:
            r1 = 0
        L_0x007e:
            return r1
        L_0x007f:
            java.lang.String r0 = r9.e
            java.lang.String r2 = r8.e
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0094
            java.lang.String r9 = r9.f
            java.lang.String r0 = r8.f
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L_0x0094
            goto L_0x0095
        L_0x0094:
            r1 = 0
        L_0x0095:
            return r1
        L_0x0096:
            java.lang.String r9 = r9.e
            java.lang.String r0 = r8.e
            boolean r9 = r9.equals(r0)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jacoco.agent.rt.internal_8ff85ea.asm.Item.a(org.jacoco.agent.rt.internal_8ff85ea.asm.Item):boolean");
    }
}
