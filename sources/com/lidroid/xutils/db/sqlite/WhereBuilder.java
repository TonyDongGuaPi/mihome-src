package com.lidroid.xutils.db.sqlite;

import android.text.TextUtils;
import com.j256.ormlite.stmt.query.ManyClause;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.lidroid.xutils.db.converter.ColumnConverterFactory;
import com.lidroid.xutils.db.table.ColumnUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WhereBuilder {

    /* renamed from: a  reason: collision with root package name */
    private final List<String> f6323a = new ArrayList();

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
        a(this.f6323a.size() == 0 ? null : ManyClause.AND_OPERATION, str, str2, obj);
        return this;
    }

    public WhereBuilder c(String str, String str2, Object obj) {
        a(this.f6323a.size() == 0 ? null : ManyClause.OR_OPERATION, str, str2, obj);
        return this;
    }

    public WhereBuilder a(String str) {
        List<String> list = this.f6323a;
        list.add(" " + str);
        return this;
    }

    public WhereBuilder d(String str, String str2, Object obj) {
        a((String) null, str, str2, obj);
        return this;
    }

    public int b() {
        return this.f6323a.size();
    }

    public String toString() {
        if (this.f6323a.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String append : this.f6323a) {
            sb.append(append);
        }
        return sb.toString();
    }

    private void a(String str, String str2, String str3, Object obj) {
        StringBuilder sb = new StringBuilder();
        if (this.f6323a.size() > 0) {
            sb.append(" ");
        }
        if (!TextUtils.isEmpty(str)) {
            sb.append(String.valueOf(str) + " ");
        }
        sb.append(str2);
        if (Operators.NOT_EQUAL2.equals(str3)) {
            str3 = SimpleComparison.NOT_EQUAL_TO_OPERATION;
        } else if (Operators.EQUAL2.equals(str3)) {
            str3 = "=";
        }
        if (obj != null) {
            sb.append(" " + str3 + " ");
            int i = 0;
            Iterable<Object> iterable = null;
            if (ServerCompact.c.equalsIgnoreCase(str3)) {
                if (obj instanceof Iterable) {
                    iterable = (Iterable) obj;
                } else if (obj.getClass().isArray()) {
                    ArrayList arrayList = new ArrayList();
                    int length = Array.getLength(obj);
                    while (i < length) {
                        arrayList.add(Array.get(obj, i));
                        i++;
                    }
                    iterable = arrayList;
                }
                if (iterable != null) {
                    StringBuffer stringBuffer = new StringBuffer(Operators.BRACKET_START_STR);
                    for (Object a2 : iterable) {
                        Object a3 = ColumnUtils.a(a2);
                        if (ColumnDbType.TEXT.equals(ColumnConverterFactory.b(a3.getClass()))) {
                            String obj2 = a3.toString();
                            if (obj2.indexOf(39) != -1) {
                                obj2 = obj2.replace("'", "''");
                            }
                            stringBuffer.append("'" + obj2 + "'");
                        } else {
                            stringBuffer.append(a3);
                        }
                        stringBuffer.append(",");
                    }
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                    stringBuffer.append(Operators.BRACKET_END_STR);
                    sb.append(stringBuffer.toString());
                } else {
                    throw new IllegalArgumentException("value must be an Array or an Iterable.");
                }
            } else if ("BETWEEN".equalsIgnoreCase(str3)) {
                if (obj instanceof Iterable) {
                    iterable = (Iterable) obj;
                } else if (obj.getClass().isArray()) {
                    ArrayList arrayList2 = new ArrayList();
                    int length2 = Array.getLength(obj);
                    while (i < length2) {
                        arrayList2.add(Array.get(obj, i));
                        i++;
                    }
                    iterable = arrayList2;
                }
                if (iterable != null) {
                    Iterator it = iterable.iterator();
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
                                sb.append("'" + obj3 + "'");
                                sb.append(" AND ");
                                sb.append("'" + obj4 + "'");
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
                    sb.append("'" + obj5 + "'");
                } else {
                    sb.append(a6);
                }
            }
        } else if ("=".equals(str3)) {
            sb.append(" IS NULL");
        } else if (SimpleComparison.NOT_EQUAL_TO_OPERATION.equals(str3)) {
            sb.append(" IS NOT NULL");
        } else {
            sb.append(" " + str3 + " NULL");
        }
        this.f6323a.add(sb.toString());
    }
}
