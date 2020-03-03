package org.aspectj.runtime.reflect;

import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Modifier;

class StringMaker {
    static StringMaker j = new StringMaker();
    static StringMaker k = new StringMaker();
    static StringMaker l = new StringMaker();

    /* renamed from: a  reason: collision with root package name */
    boolean f3477a = true;
    boolean b = true;
    boolean c = false;
    boolean d = false;
    boolean e = false;
    boolean f = true;
    boolean g = true;
    boolean h = true;
    int i;

    StringMaker() {
    }

    static {
        j.f3477a = true;
        j.b = false;
        j.c = false;
        j.d = false;
        j.e = true;
        j.f = false;
        j.g = false;
        j.i = 0;
        k.f3477a = true;
        k.b = true;
        k.c = false;
        k.d = false;
        k.e = false;
        j.i = 1;
        l.f3477a = false;
        l.b = true;
        l.c = false;
        l.d = true;
        l.e = false;
        l.h = false;
        l.i = 2;
    }

    /* access modifiers changed from: package-private */
    public String a(String str) {
        int lastIndexOf = str.lastIndexOf(45);
        if (lastIndexOf == -1) {
            return str;
        }
        return str.substring(lastIndexOf + 1);
    }

    /* access modifiers changed from: package-private */
    public String a(int i2) {
        if (!this.d) {
            return "";
        }
        String modifier = Modifier.toString(i2);
        if (modifier.length() == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(modifier);
        stringBuffer.append(" ");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String b(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return str;
        }
        return str.substring(lastIndexOf + 1);
    }

    /* access modifiers changed from: package-private */
    public String a(Class cls, String str, boolean z) {
        if (cls == null) {
            return "ANONYMOUS";
        }
        if (cls.isArray()) {
            Class<?> componentType = cls.getComponentType();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(a(componentType, componentType.getName(), z));
            stringBuffer.append(XMPConst.ai);
            return stringBuffer.toString();
        } else if (z) {
            return b(str).replace('$', '.');
        } else {
            return str.replace('$', '.');
        }
    }

    public String a(Class cls) {
        return a(cls, cls.getName(), this.f3477a);
    }

    public String a(Class cls, String str) {
        return a(cls, str, this.e);
    }

    public void a(StringBuffer stringBuffer, Class[] clsArr) {
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            if (i2 > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(a(clsArr[i2]));
        }
    }

    public void b(StringBuffer stringBuffer, Class[] clsArr) {
        if (clsArr != null) {
            if (this.b) {
                stringBuffer.append(Operators.BRACKET_START_STR);
                a(stringBuffer, clsArr);
                stringBuffer.append(Operators.BRACKET_END_STR);
            } else if (clsArr.length == 0) {
                stringBuffer.append("()");
            } else {
                stringBuffer.append("(..)");
            }
        }
    }

    public void c(StringBuffer stringBuffer, Class[] clsArr) {
        if (this.c && clsArr != null && clsArr.length != 0) {
            stringBuffer.append(" throws ");
            a(stringBuffer, clsArr);
        }
    }
}
