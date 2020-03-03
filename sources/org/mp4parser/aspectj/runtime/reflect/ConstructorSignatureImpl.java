package org.mp4parser.aspectj.runtime.reflect;

import java.lang.reflect.Constructor;
import org.mp4parser.aspectj.lang.reflect.ConstructorSignature;

class ConstructorSignatureImpl extends CodeSignatureImpl implements ConstructorSignature {

    /* renamed from: a  reason: collision with root package name */
    private Constructor f3770a;

    public String c() {
        return "<init>";
    }

    ConstructorSignatureImpl(int i, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2) {
        super(i, "<init>", cls, clsArr, strArr, clsArr2);
    }

    ConstructorSignatureImpl(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public String a(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringMaker.a(d()));
        stringBuffer.append(stringMaker.a(e(), f()));
        stringMaker.b(stringBuffer, i());
        stringMaker.c(stringBuffer, k());
        return stringBuffer.toString();
    }

    public Constructor g() {
        if (this.f3770a == null) {
            try {
                this.f3770a = e().getDeclaredConstructor(i());
            } catch (Exception unused) {
            }
        }
        return this.f3770a;
    }
}
