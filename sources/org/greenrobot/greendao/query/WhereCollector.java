package org.greenrobot.greendao.query;

import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.WhereCondition;

class WhereCollector<T> {

    /* renamed from: a  reason: collision with root package name */
    private final AbstractDao<T, ?> f3539a;
    private final List<WhereCondition> b = new ArrayList();
    private final String c;

    WhereCollector(AbstractDao<T, ?> abstractDao, String str) {
        this.f3539a = abstractDao;
        this.c = str;
    }

    /* access modifiers changed from: package-private */
    public void a(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        a(whereCondition);
        this.b.add(whereCondition);
        for (WhereCondition whereCondition2 : whereConditionArr) {
            a(whereCondition2);
            this.b.add(whereCondition2);
        }
    }

    /* access modifiers changed from: package-private */
    public WhereCondition a(String str, WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        StringBuilder sb = new StringBuilder(Operators.BRACKET_START_STR);
        ArrayList arrayList = new ArrayList();
        a(sb, (List<Object>) arrayList, whereCondition);
        sb.append(str);
        a(sb, (List<Object>) arrayList, whereCondition2);
        for (WhereCondition a2 : whereConditionArr) {
            sb.append(str);
            a(sb, (List<Object>) arrayList, a2);
        }
        sb.append(Operators.BRACKET_END);
        return new WhereCondition.StringCondition(sb.toString(), arrayList.toArray());
    }

    /* access modifiers changed from: package-private */
    public void a(StringBuilder sb, List<Object> list, WhereCondition whereCondition) {
        a(whereCondition);
        whereCondition.a(sb, this.c);
        whereCondition.a(list);
    }

    /* access modifiers changed from: package-private */
    public void a(WhereCondition whereCondition) {
        if (whereCondition instanceof WhereCondition.PropertyCondition) {
            a(((WhereCondition.PropertyCondition) whereCondition).d);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Property property) {
        if (this.f3539a != null) {
            Property[] properties = this.f3539a.getProperties();
            int length = properties.length;
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (property == properties[i]) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                throw new DaoException("Property '" + property.c + "' is not part of " + this.f3539a);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(StringBuilder sb, String str, List<Object> list) {
        ListIterator<WhereCondition> listIterator = this.b.listIterator();
        while (listIterator.hasNext()) {
            if (listIterator.hasPrevious()) {
                sb.append(" AND ");
            }
            WhereCondition next = listIterator.next();
            next.a(sb, str);
            next.a(list);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.b.isEmpty();
    }
}
