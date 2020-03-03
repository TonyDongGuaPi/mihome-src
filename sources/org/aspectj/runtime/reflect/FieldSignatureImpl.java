package org.aspectj.runtime.reflect;

import java.lang.reflect.Field;
import org.aspectj.lang.reflect.FieldSignature;

public class FieldSignatureImpl extends MemberSignatureImpl implements FieldSignature {

    /* renamed from: a  reason: collision with root package name */
    Class f3469a;
    private Field b;

    FieldSignatureImpl(int i, String str, Class cls, Class cls2) {
        super(i, str, cls);
        this.f3469a = cls2;
    }

    FieldSignatureImpl(String str) {
        super(str);
    }

    public Class g() {
        if (this.f3469a == null) {
            this.f3469a = c(3);
        }
        return this.f3469a;
    }

    /* access modifiers changed from: protected */
    public String a(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringMaker.a(d()));
        if (stringMaker.b) {
            stringBuffer.append(stringMaker.a(g()));
        }
        if (stringMaker.b) {
            stringBuffer.append(" ");
        }
        stringBuffer.append(stringMaker.a(e(), f()));
        stringBuffer.append(".");
        stringBuffer.append(c());
        return stringBuffer.toString();
    }

    public Field h() {
        if (this.b == null) {
            try {
                this.b = e().getDeclaredField(c());
            } catch (Exception unused) {
            }
        }
        return this.b;
    }
}
