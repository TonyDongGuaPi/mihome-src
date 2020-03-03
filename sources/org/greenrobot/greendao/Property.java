package org.greenrobot.greendao;

import com.taobao.weex.el.parse.Operators;
import java.util.Collection;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.query.WhereCondition;

public class Property {

    /* renamed from: a  reason: collision with root package name */
    public final int f3515a;
    public final Class<?> b;
    public final String c;
    public final boolean d;
    public final String e;

    public Property(int i, Class<?> cls, String str, boolean z, String str2) {
        this.f3515a = i;
        this.b = cls;
        this.c = str;
        this.d = z;
        this.e = str2;
    }

    public WhereCondition a(Object obj) {
        return new WhereCondition.PropertyCondition(this, "=?", obj);
    }

    public WhereCondition b(Object obj) {
        return new WhereCondition.PropertyCondition(this, "<>?", obj);
    }

    public WhereCondition a(String str) {
        return new WhereCondition.PropertyCondition(this, " LIKE ?", (Object) str);
    }

    public WhereCondition a(Object obj, Object obj2) {
        return new WhereCondition.PropertyCondition(this, " BETWEEN ? AND ?", new Object[]{obj, obj2});
    }

    public WhereCondition a(Object... objArr) {
        StringBuilder sb = new StringBuilder(" IN (");
        SqlUtils.a(sb, objArr.length).append(Operators.BRACKET_END);
        return new WhereCondition.PropertyCondition(this, sb.toString(), objArr);
    }

    public WhereCondition a(Collection<?> collection) {
        return a(collection.toArray());
    }

    public WhereCondition b(Object... objArr) {
        StringBuilder sb = new StringBuilder(" NOT IN (");
        SqlUtils.a(sb, objArr.length).append(Operators.BRACKET_END);
        return new WhereCondition.PropertyCondition(this, sb.toString(), objArr);
    }

    public WhereCondition b(Collection<?> collection) {
        return b(collection.toArray());
    }

    public WhereCondition c(Object obj) {
        return new WhereCondition.PropertyCondition(this, ">?", obj);
    }

    public WhereCondition d(Object obj) {
        return new WhereCondition.PropertyCondition(this, "<?", obj);
    }

    public WhereCondition e(Object obj) {
        return new WhereCondition.PropertyCondition(this, ">=?", obj);
    }

    public WhereCondition f(Object obj) {
        return new WhereCondition.PropertyCondition(this, "<=?", obj);
    }

    public WhereCondition a() {
        return new WhereCondition.PropertyCondition(this, " IS NULL");
    }

    public WhereCondition b() {
        return new WhereCondition.PropertyCondition(this, " IS NOT NULL");
    }
}
