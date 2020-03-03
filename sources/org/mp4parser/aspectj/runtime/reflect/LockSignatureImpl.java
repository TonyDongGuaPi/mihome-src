package org.mp4parser.aspectj.runtime.reflect;

import com.taobao.weex.el.parse.Operators;
import org.mp4parser.aspectj.lang.reflect.LockSignature;

class LockSignatureImpl extends SignatureImpl implements LockSignature {

    /* renamed from: a  reason: collision with root package name */
    private Class f3775a;

    LockSignatureImpl(Class cls) {
        super(8, "lock", cls);
        this.f3775a = cls;
    }

    LockSignatureImpl(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public String a(StringMaker stringMaker) {
        if (this.f3775a == null) {
            this.f3775a = c(3);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("lock(");
        stringBuffer.append(stringMaker.a(this.f3775a));
        stringBuffer.append(Operators.BRACKET_END_STR);
        return stringBuffer.toString();
    }

    public Class g() {
        if (this.f3775a == null) {
            this.f3775a = c(3);
        }
        return this.f3775a;
    }
}
