package org.mp4parser.aspectj.runtime.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import org.mp4parser.aspectj.lang.reflect.InitializerSignature;

class InitializerSignatureImpl extends CodeSignatureImpl implements InitializerSignature {

    /* renamed from: a  reason: collision with root package name */
    private Constructor f3773a;

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
        if (this.f3773a == null) {
            try {
                this.f3773a = e().getDeclaredConstructor(i());
            } catch (Exception unused) {
            }
        }
        return this.f3773a;
    }
}
