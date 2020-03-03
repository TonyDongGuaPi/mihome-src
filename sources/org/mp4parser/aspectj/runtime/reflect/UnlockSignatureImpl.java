package org.mp4parser.aspectj.runtime.reflect;

import com.taobao.weex.el.parse.Operators;
import org.mp4parser.aspectj.lang.reflect.UnlockSignature;

class UnlockSignatureImpl extends SignatureImpl implements UnlockSignature {

    /* renamed from: a  reason: collision with root package name */
    private Class f3781a;

    UnlockSignatureImpl(Class cls) {
        super(8, "unlock", cls);
        this.f3781a = cls;
    }

    UnlockSignatureImpl(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public String a(StringMaker stringMaker) {
        if (this.f3781a == null) {
            this.f3781a = c(3);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("unlock(");
        stringBuffer.append(stringMaker.a(this.f3781a));
        stringBuffer.append(Operators.BRACKET_END_STR);
        return stringBuffer.toString();
    }

    public Class g() {
        if (this.f3781a == null) {
            this.f3781a = c(3);
        }
        return this.f3781a;
    }
}
