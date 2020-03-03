package org.aspectj.runtime.reflect;

import com.taobao.weex.el.parse.Operators;
import org.aspectj.lang.reflect.UnlockSignature;

class UnlockSignatureImpl extends SignatureImpl implements UnlockSignature {

    /* renamed from: a  reason: collision with root package name */
    private Class f3478a;

    UnlockSignatureImpl(Class cls) {
        super(8, "unlock", cls);
        this.f3478a = cls;
    }

    UnlockSignatureImpl(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public String a(StringMaker stringMaker) {
        if (this.f3478a == null) {
            this.f3478a = c(3);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("unlock(");
        stringBuffer.append(stringMaker.a(this.f3478a));
        stringBuffer.append(Operators.BRACKET_END_STR);
        return stringBuffer.toString();
    }

    public Class g() {
        if (this.f3478a == null) {
            this.f3478a = c(3);
        }
        return this.f3478a;
    }
}
