package org.xutils.db.sqlite;

import android.text.TextUtils;
import com.j256.ormlite.stmt.query.ManyClause;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xutils.db.converter.ColumnConverterFactory;
import org.xutils.db.table.ColumnUtils;

public class WhereBuilder {

    /* renamed from: a  reason: collision with root package name */
    private final List<String> f4245a = new ArrayList();

    private WhereBuilder() {
    }

    public static WhereBuilder a() {
        return new WhereBuilder();
    }

    public static WhereBuilder a(String str, String str2, Object obj) {
        WhereBuilder whereBuilder = new WhereBuilder();
        whereBuilder.a((String) null, str, str2, obj);
        return whereBuilder;
    }

    public WhereBuilder b(String str, String str2, Object obj) {
        a(this.f4245a.size() == 0 ? null : ManyClause.AND_OPERATION, str, str2, obj);
        return this;
    }

    public WhereBuilder a(WhereBuilder whereBuilder) {
        String str = this.f4245a.size() == 0 ? " " : "AND ";
        return a(str + Operators.BRACKET_START_STR + whereBuilder.toString() + Operators.BRACKET_END_STR);
    }

    public WhereBuilder c(String str, String str2, Object obj) {
        a(this.f4245a.size() == 0 ? null : ManyClause.OR_OPERATION, str, str2, obj);
        return this;
    }

    public WhereBuilder b(WhereBuilder whereBuilder) {
        String str = this.f4245a.size() == 0 ? " " : "OR ";
        return a(str + Operators.BRACKET_START_STR + whereBuilder.toString() + Operators.BRACKET_END_STR);
    }

    public WhereBuilder a(String str) {
        List<String> list = this.f4245a;
        list.add(" " + str);
        return this;
    }

    public int b() {
        return this.f4245a.size();
    }

    public String toString() {
        if (this.f4245a.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String append : this.f4245a) {
            sb.append(append);
        }
        return sb.toString();
    }

    private void a(String str, String str2, String str3, Object obj) {
        StringBuilder sb = new StringBuilder();
        if (this.f4245a.size() > 0) {
            sb.append(" ");
        }
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            sb.append(" ");
        }
        sb.append("\"");
        sb.append(str2);
        sb.append("\"");
        if (Operators.NOT_EQUAL2.equals(str3)) {
            str3 = SimpleComparison.NOT_EQUAL_TO_OPERATION;
        } else if (Operators.EQUAL2.equals(str3)) {
            str3 = "=";
        }
        if (obj != null) {
            sb.append(" ");
            sb.append(str3);
            sb.append(" ");
            int i = 0;
            ArrayList<Object> arrayList = null;
            if (ServerCompact.c.equalsIgnoreCase(str3)) {
                if (obj instanceof Iterable) {
                    arrayList = (Iterable) obj;
                } else if (obj.getClass().isArray()) {
                    int length = Array.getLength(obj);
                    ArrayList arrayList2 = new ArrayList(length);
                    while (i < length) {
                        arrayList2.add(Array.get(obj, i));
                        i++;
                    }
                    arrayList = arrayList2;
                }
                if (arrayList != null) {
                    StringBuilder sb2 = new StringBuilder(Operators.BRACKET_START_STR);
                    for (Object a2 : arrayList) {
                        Object a3 = ColumnUtils.a(a2);
                        if (ColumnDbType.TEXT.equals(ColumnConverterFactory.b(a3.getClass()))) {
                            String obj2 = a3.toString();
                            if (obj2.indexOf(39) != -1) {
                                obj2 = obj2.replace("'", "''");
                            }
                            sb2.append("'");
                            sb2.append(obj2);
                            sb2.append("'");
                        } else {
                            sb2.append(a3);
                        }
                        sb2.append(",");
                    }
                    sb2.deleteCharAt(sb2.length() - 1);
                    sb2.append(Operators.BRACKET_END_STR);
                    sb.append(sb2.toString());
                } else {
                    throw new IllegalArgumentException("value must be an Array or an Iterable.");
                }
            } else if ("BETWEEN".equalsIgnoreCase(str3)) {
                if (obj instanceof Iterable) {
                    arrayList = (Iterable) obj;
                } else if (obj.getClass().isArray()) {
                    int length2 = Array.getLength(obj);
                    ArrayList arrayList3 = new ArrayList(length2);
                    while (i < length2) {
                        arrayList3.add(Array.get(obj, i));
                        i++;
                    }
                    arrayList = arrayList3;
                }
                if (arrayList != null) {
                    Iterator it = arrayList.iterator();
                    if (it.hasNext()) {
                        Object next = it.next();
                        if (it.hasNext()) {
                            Object next2 = it.next();
                            Object a4 = ColumnUtils.a(next);
                            Object a5 = ColumnUtils.a(next2);
                            if (ColumnDbType.TEXT.equals(ColumnConverterFactory.b(a4.getClass()))) {
                                String obj3 = a4.toString();
                                if (obj3.indexOf(39) != -1) {
                                    obj3 = obj3.replace("'", "''");
                                }
                                String obj4 = a5.toString();
                                if (obj4.indexOf(39) != -1) {
                                    obj4 = obj4.replace("'", "''");
                                }
                                sb.append("'");
                                sb.append(obj3);
                                sb.append("'");
                                sb.append(" AND ");
                                sb.append("'");
                                sb.append(obj4);
                                sb.append("'");
                            } else {
                                sb.append(a4);
                                sb.append(" AND ");
                                sb.append(a5);
                            }
                        } else {
                            throw new IllegalArgumentException("value must have tow items.");
                        }
                    } else {
                        throw new IllegalArgumentException("value must have tow items.");
                    }
                } else {
                    throw new IllegalArgumentException("value must be an Array or an Iterable.");
                }
            } else {
                Object a6 = ColumnUtils.a(obj);
                if (ColumnDbType.TEXT.equals(ColumnConverterFactory.b(a6.getClass()))) {
                    String obj5 = a6.toString();
                    if (obj5.indexOf(39) != -1) {
                        obj5 = obj5.replace("'", "''");
                    }
                    sb.append("'");
                    sb.append(obj5);
                    sb.append("'");
                } else {
                    sb.append(a6);
                }
            }
        } else if ("=".equals(str3)) {
            sb.append(" IS NULL");
        } else if (SimpleComparison.NOT_EQUAL_TO_OPERATION.equals(str3)) {
            sb.append(" IS NOT NULL");
        } else {
            sb.append(" ");
            sb.append(str3);
            sb.append(" NULL");
        }
        this.f4245a.add(sb.toString());
    }
}
