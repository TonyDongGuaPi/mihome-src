package org.greenrobot.greendao.query;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;

public class Join<SRC, DST> {

    /* renamed from: a  reason: collision with root package name */
    final String f3535a;
    final AbstractDao<DST, ?> b;
    final Property c;
    final Property d;
    final String e;
    final WhereCollector<DST> f;

    public Join(String str, Property property, AbstractDao<DST, ?> abstractDao, Property property2, String str2) {
        this.f3535a = str;
        this.c = property;
        this.b = abstractDao;
        this.d = property2;
        this.e = str2;
        this.f = new WhereCollector<>(abstractDao, str2);
    }

    public Join<SRC, DST> a(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        this.f.a(whereCondition, whereConditionArr);
        return this;
    }

    public Join<SRC, DST> a(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        this.f.a(b(whereCondition, whereCondition2, whereConditionArr), new WhereCondition[0]);
        return this;
    }

    public WhereCondition b(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        return this.f.a(" OR ", whereCondition, whereCondition2, whereConditionArr);
    }

    public WhereCondition c(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        return this.f.a(" AND ", whereCondition, whereCondition2, whereConditionArr);
    }

    public String a() {
        return this.e;
    }
}
