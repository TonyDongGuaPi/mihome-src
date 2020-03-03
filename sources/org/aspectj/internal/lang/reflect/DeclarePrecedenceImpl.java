package org.aspectj.internal.lang.reflect;

import com.taobao.weex.el.parse.Operators;
import java.util.StringTokenizer;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.DeclarePrecedence;
import org.aspectj.lang.reflect.TypePattern;

public class DeclarePrecedenceImpl implements DeclarePrecedence {

    /* renamed from: a  reason: collision with root package name */
    private AjType<?> f3441a;
    private TypePattern[] b;
    private String c;

    public DeclarePrecedenceImpl(String str, AjType ajType) {
        this.f3441a = ajType;
        this.c = str;
        StringTokenizer stringTokenizer = new StringTokenizer(str.startsWith(Operators.BRACKET_START_STR) ? str.substring(1, str.length() - 1) : str, ",");
        this.b = new TypePattern[stringTokenizer.countTokens()];
        for (int i = 0; i < this.b.length; i++) {
            this.b[i] = new TypePatternImpl(stringTokenizer.nextToken().trim());
        }
    }

    public AjType a() {
        return this.f3441a;
    }

    public TypePattern[] b() {
        return this.b;
    }

    public String toString() {
        return "declare precedence : " + this.c;
    }
}
