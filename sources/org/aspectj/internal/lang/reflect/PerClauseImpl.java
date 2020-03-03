package org.aspectj.internal.lang.reflect;

import org.aspectj.lang.reflect.PerClause;
import org.aspectj.lang.reflect.PerClauseKind;

public class PerClauseImpl implements PerClause {

    /* renamed from: a  reason: collision with root package name */
    private final PerClauseKind f3444a;

    public String toString() {
        return "issingleton()";
    }

    protected PerClauseImpl(PerClauseKind perClauseKind) {
        this.f3444a = perClauseKind;
    }

    public PerClauseKind a() {
        return this.f3444a;
    }
}
