package org.aspectj.runtime.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import org.aspectj.lang.reflect.InitializerSignature;

class InitializerSignatureImpl extends CodeSignatureImpl implements InitializerSignature {

    /* renamed from: a  reason: collision with root package name */
    private Constructor f3470a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InitializerSignatureImpl(int i, Class cls) {
        super(i, Modifier.isStatic(i) ? "<clinit>" : "<init>", cls, SignatureImpl.m, SignatureImpl.l, SignatureImpl.m);
    }

    InitializerSignatureImpl(String str) {
        super(str);
    }

    public String c() {
        return Modifier.isStatic(d()) ? "<clinit>" : "<init>";
    }

    /* access modifiers changed from: protected */
    public String a(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringMaker.a(d()));
        stringBuffer.append(stringMaker.a(e(), f()));
        stringBuffer.append(".");
        stringBuffer.append(c());
        return stringBuffer.toString();
    }

    public Constructor g() {
        if (this.f3470a == null) {
            try {
                this.f3470a = e().getDeclaredConstructor(i());
            } catch (Exception unused) {
            }
        }
        return this.f3470a;
    }
}
