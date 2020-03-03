package org.mp4parser.aspectj.internal.lang.reflect;

import org.mp4parser.aspectj.lang.reflect.PerClause;
import org.mp4parser.aspectj.lang.reflect.PerClauseKind;

public class PerClauseImpl implements PerClause {

    /* renamed from: a  reason: collision with root package name */
    private final PerClauseKind f3745a;

    public String toString() {
        return "issingleton()";
    }

    protected PerClauseImpl(PerClauseKind perClauseKind) {
        this.f3745a = perClauseKind;
    }

    public PerClauseKind a() {
        return this.f3745a;
    }
}
