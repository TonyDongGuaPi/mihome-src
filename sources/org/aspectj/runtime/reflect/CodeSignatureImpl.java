package org.aspectj.runtime.reflect;

import org.aspectj.lang.reflect.CodeSignature;

abstract class CodeSignatureImpl extends MemberSignatureImpl implements CodeSignature {
    Class[] b;
    String[] c;
    Class[] d;

    CodeSignatureImpl(int i, String str, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2) {
        super(i, str, cls);
        this.b = clsArr;
        this.c = strArr;
        this.d = clsArr2;
    }

    CodeSignatureImpl(String str) {
        super(str);
    }

    public Class[] i() {
        if (this.b == null) {
            this.b = e(3);
        }
        return this.b;
    }

    public String[] j() {
        if (this.c == null) {
            this.c = d(4);
        }
        return this.c;
    }

    public Class[] k() {
        if (this.d == null) {
            this.d = e(5);
        }
        return this.d;
    }
}
