package org.aspectj.runtime.reflect;

import com.taobao.weex.el.parse.Operators;
import org.aspectj.lang.reflect.LockSignature;

class LockSignatureImpl extends SignatureImpl implements LockSignature {

    /* renamed from: a  reason: collision with root package name */
    private Class f3472a;

    LockSignatureImpl(Class cls) {
        super(8, "lock", cls);
        this.f3472a = cls;
    }

    LockSignatureImpl(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public String a(StringMaker stringMaker) {
        if (this.f3472a == null) {
            this.f3472a = c(3);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("lock(");
        stringBuffer.append(stringMaker.a(this.f3472a));
        stringBuffer.append(Operators.BRACKET_END_STR);
        return stringBuffer.toString();
    }

    public Class g() {
        if (this.f3472a == null) {
            this.f3472a = c(3);
        }
        return this.f3472a;
    }
}
