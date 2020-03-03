package com.lidroid.xutils.db.sqlite;

import com.lidroid.xutils.db.table.ColumnUtils;
import java.util.LinkedList;

public class SqlInfo {

    /* renamed from: a  reason: collision with root package name */
    private String f6322a;
    private LinkedList<Object> b;

    public SqlInfo() {
    }

    public SqlInfo(String str) {
        this.f6322a = str;
    }

    public SqlInfo(String str, Object... objArr) {
        this.f6322a = str;
        a(objArr);
    }

    public String a() {
        return this.f6322a;
    }

    public void a(String str) {
        this.f6322a = str;
    }

    public LinkedList<Object> b() {
        return this.b;
    }

    public Object[] c() {
        if (this.b != null) {
            return this.b.toArray();
        }
        return null;
    }

    public String[] d() {
        String str;
        if (this.b == null) {
            return null;
        }
        String[] strArr = new String[this.b.size()];
        for (int i = 0; i < this.b.size(); i++) {
            Object obj = this.b.get(i);
            if (obj == null) {
                str = null;
            } else {
                str = obj.toString();
            }
            strArr[i] = str;
        }
        return strArr;
    }

    public void a(Object obj) {
        if (this.b == null) {
            this.b = new LinkedList<>();
        }
        this.b.add(ColumnUtils.a(obj));
    }

    /* access modifiers changed from: package-private */
    public void b(Object obj) {
        if (this.b == null) {
            this.b = new LinkedList<>();
        }
        this.b.add(obj);
    }

    public void a(Object... objArr) {
        if (objArr != null) {
            for (Object a2 : objArr) {
                a(a2);
            }
        }
    }
}
