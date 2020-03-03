package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.DexLoader;

class bf {

    /* renamed from: a  reason: collision with root package name */
    private DexLoader f9159a = null;

    public bf(DexLoader dexLoader) {
        this.f9159a = dexLoader;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0013, code lost:
        r12 = r11.f9159a.invokeMethod((r6 = r11.f9159a.newInstance("com.tencent.tbs.utils.TbsVideoUtilsProxy", new java.lang.Class[0], new java.lang.Object[0])), "com.tencent.tbs.utils.TbsVideoUtilsProxy", "getCurWDPDecodeType", new java.lang.Class[]{android.content.Context.class}, r12);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(android.content.Context r12) {
        /*
            r11 = this;
            com.tencent.smtt.export.external.DexLoader r0 = r11.f9159a
            if (r0 == 0) goto L_0x002f
            com.tencent.smtt.export.external.DexLoader r0 = r11.f9159a
            java.lang.String r1 = "com.tencent.tbs.utils.TbsVideoUtilsProxy"
            r2 = 0
            java.lang.Class[] r3 = new java.lang.Class[r2]
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.Object r6 = r0.newInstance(r1, r3, r4)
            if (r6 == 0) goto L_0x002f
            com.tencent.smtt.export.external.DexLoader r5 = r11.f9159a
            java.lang.String r7 = "com.tencent.tbs.utils.TbsVideoUtilsProxy"
            java.lang.String r8 = "getCurWDPDecodeType"
            r0 = 1
            java.lang.Class[] r9 = new java.lang.Class[r0]
            java.lang.Class<android.content.Context> r1 = android.content.Context.class
            r9[r2] = r1
            java.lang.Object[] r10 = new java.lang.Object[r0]
            r10[r2] = r12
            java.lang.Object r12 = r5.invokeMethod(r6, r7, r8, r9, r10)
            if (r12 == 0) goto L_0x002f
            java.lang.String r12 = java.lang.String.valueOf(r12)
            return r12
        L_0x002f:
            java.lang.String r12 = ""
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.bf.a(android.content.Context):java.lang.String");
    }

    public void a(Context context, String str) {
        Object newInstance;
        if (this.f9159a != null && (newInstance = this.f9159a.newInstance("com.tencent.tbs.utils.TbsVideoUtilsProxy", new Class[0], new Object[0])) != null) {
            this.f9159a.invokeMethod(newInstance, "com.tencent.tbs.utils.TbsVideoUtilsProxy", "deleteVideoCache", new Class[]{Context.class, String.class}, context, str);
        }
    }
}
