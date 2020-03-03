package org.aspectj.internal.lang.reflect;

import com.taobao.weex.el.parse.Operators;
import org.aspectj.lang.reflect.PerClauseKind;
import org.aspectj.lang.reflect.TypePattern;
import org.aspectj.lang.reflect.TypePatternBasedPerClause;

public class TypePatternBasedPerClauseImpl extends PerClauseImpl implements TypePatternBasedPerClause {

    /* renamed from: a  reason: collision with root package name */
    private TypePattern f3451a;

    public TypePatternBasedPerClauseImpl(PerClauseKind perClauseKind, String str) {
        super(perClauseKind);
        this.f3451a = new TypePatternImpl(str);
    }

    public TypePattern b() {
        return this.f3451a;
    }

    public String toString() {
        return "pertypewithin(" + this.f3451a.a() + Operators.BRACKET_END_STR;
    }
}
