package org.mp4parser.aspectj.internal.lang.reflect;

import com.taobao.weex.el.parse.Operators;
import org.mp4parser.aspectj.lang.reflect.PerClauseKind;
import org.mp4parser.aspectj.lang.reflect.TypePattern;
import org.mp4parser.aspectj.lang.reflect.TypePatternBasedPerClause;

public class TypePatternBasedPerClauseImpl extends PerClauseImpl implements TypePatternBasedPerClause {

    /* renamed from: a  reason: collision with root package name */
    private TypePattern f3752a;

    public TypePatternBasedPerClauseImpl(PerClauseKind perClauseKind, String str) {
        super(perClauseKind);
        this.f3752a = new TypePatternImpl(str);
    }

    public TypePattern b() {
        return this.f3752a;
    }

    public String toString() {
        return "pertypewithin(" + this.f3752a.a() + Operators.BRACKET_END_STR;
    }
}
