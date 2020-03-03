package org.aspectj.runtime.reflect;

import com.taobao.weex.el.parse.Operators;
import org.aspectj.lang.reflect.CatchClauseSignature;

class CatchClauseSignatureImpl extends SignatureImpl implements CatchClauseSignature {

    /* renamed from: a  reason: collision with root package name */
    Class f3466a;
    String b;

    CatchClauseSignatureImpl(Class cls, Class cls2, String str) {
        super(0, "catch", cls);
        this.f3466a = cls2;
        this.b = str;
    }

    CatchClauseSignatureImpl(String str) {
        super(str);
    }

    public Class g() {
        if (this.f3466a == null) {
            this.f3466a = c(3);
        }
        return this.f3466a;
    }

    public String h() {
        if (this.b == null) {
            this.b = a(4);
        }
        return this.b;
    }

    /* access modifiers changed from: protected */
    public String a(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("catch(");
        stringBuffer.append(stringMaker.a(g()));
        stringBuffer.append(Operators.BRACKET_END_STR);
        return stringBuffer.toString();
    }
}
