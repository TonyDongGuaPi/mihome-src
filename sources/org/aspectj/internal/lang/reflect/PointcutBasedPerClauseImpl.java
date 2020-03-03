package org.aspectj.internal.lang.reflect;

import com.taobao.weex.el.parse.Operators;
import org.aspectj.lang.reflect.PerClauseKind;
import org.aspectj.lang.reflect.PointcutBasedPerClause;
import org.aspectj.lang.reflect.PointcutExpression;

public class PointcutBasedPerClauseImpl extends PerClauseImpl implements PointcutBasedPerClause {

    /* renamed from: a  reason: collision with root package name */
    private final PointcutExpression f3445a;

    public PointcutBasedPerClauseImpl(PerClauseKind perClauseKind, String str) {
        super(perClauseKind);
        this.f3445a = new PointcutExpressionImpl(str);
    }

    public PointcutExpression b() {
        return this.f3445a;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        switch (a()) {
            case PERCFLOW:
                stringBuffer.append("percflow(");
                break;
            case PERCFLOWBELOW:
                stringBuffer.append("percflowbelow(");
                break;
            case PERTARGET:
                stringBuffer.append("pertarget(");
                break;
            case PERTHIS:
                stringBuffer.append("perthis(");
                break;
        }
        stringBuffer.append(this.f3445a.a());
        stringBuffer.append(Operators.BRACKET_END_STR);
        return stringBuffer.toString();
    }
}
