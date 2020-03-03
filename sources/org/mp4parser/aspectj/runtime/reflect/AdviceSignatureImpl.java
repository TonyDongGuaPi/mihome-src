package org.mp4parser.aspectj.runtime.reflect;

import java.lang.reflect.Method;
import org.mp4parser.aspectj.lang.reflect.AdviceSignature;

class AdviceSignatureImpl extends CodeSignatureImpl implements AdviceSignature {

    /* renamed from: a  reason: collision with root package name */
    Class f3768a;
    private Method o = null;

    AdviceSignatureImpl(int i, String str, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2, Class cls2) {
        super(i, str, cls, clsArr, strArr, clsArr2);
        this.f3768a = cls2;
    }

    AdviceSignatureImpl(String str) {
        super(str);
    }

    public Class g() {
        if (this.f3768a == null) {
            this.f3768a = c(6);
        }
        return this.f3768a;
    }

    /* access modifiers changed from: protected */
    public String a(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        if (stringMaker.b) {
            stringBuffer.append(stringMaker.a(g()));
        }
        if (stringMaker.b) {
            stringBuffer.append(" ");
        }
        stringBuffer.append(stringMaker.a(e(), f()));
        stringBuffer.append(".");
        stringBuffer.append(b(c()));
        stringMaker.b(stringBuffer, i());
        stringMaker.c(stringBuffer, k());
        return stringBuffer.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String b(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 36
            int r0 = r4.indexOf(r0)
            r1 = -1
            if (r0 != r1) goto L_0x000a
            return r4
        L_0x000a:
            java.util.StringTokenizer r0 = new java.util.StringTokenizer
            java.lang.String r1 = "$"
            r0.<init>(r4, r1)
        L_0x0011:
            boolean r1 = r0.hasMoreTokens()
            if (r1 == 0) goto L_0x0034
            java.lang.String r1 = r0.nextToken()
            java.lang.String r2 = "before"
            boolean r2 = r1.startsWith(r2)
            if (r2 != 0) goto L_0x0033
            java.lang.String r2 = "after"
            boolean r2 = r1.startsWith(r2)
            if (r2 != 0) goto L_0x0033
            java.lang.String r2 = "around"
            boolean r2 = r1.startsWith(r2)
            if (r2 == 0) goto L_0x0011
        L_0x0033:
            return r1
        L_0x0034:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mp4parser.aspectj.runtime.reflect.AdviceSignatureImpl.b(java.lang.String):java.lang.String");
    }

    public Method h() {
        if (this.o == null) {
            try {
                this.o = e().getDeclaredMethod(c(), i());
            } catch (Exception unused) {
            }
        }
        return this.o;
    }
}
