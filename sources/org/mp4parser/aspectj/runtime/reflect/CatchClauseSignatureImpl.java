package org.mp4parser.aspectj.runtime.reflect;

import com.taobao.weex.el.parse.Operators;
import org.mp4parser.aspectj.lang.reflect.CatchClauseSignature;

class CatchClauseSignatureImpl extends SignatureImpl implements CatchClauseSignature {

    /* renamed from: a  reason: collision with root package name */
    Class f3769a;
    String b;

    CatchClauseSignatureImpl(Class cls, Class cls2, String str) {
        super(0, "catch", cls);
        this.f3769a = cls2;
        this.b = str;
    }

    CatchClauseSignatureImpl(String str) {
        super(str);
    }

    public Class g() {
        if (this.f3769a == null) {
            this.f3769a = c(3);
        }
        return this.f3769a;
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
