package com.ximalaya.ting.android.sdkdownloader.http;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class BaseParams {

    /* renamed from: a  reason: collision with root package name */
    private String f2352a = "UTF-8";
    private final List<Header> b = new ArrayList();
    private final List<KeyValue> c = new ArrayList();

    BaseParams() {
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2352a = str;
        }
    }

    public String a() {
        return this.f2352a;
    }

    public void a(String str, String str2) {
        Header header = new Header(str, str2, true);
        Iterator<Header> it = this.b.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().b)) {
                it.remove();
            }
        }
        this.b.add(header);
    }

    public void b(String str, String str2) {
        this.b.add(new Header(str, str2, false));
    }

    public void a(String str, Object obj) {
        if (obj == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (obj instanceof List) {
            for (Object arrayItem : (List) obj) {
                this.c.add(new ArrayItem(str, arrayItem));
            }
        } else if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                this.c.add(new ArrayItem(str, Array.get(obj, i)));
            }
        } else {
            this.c.add(new KeyValue(str, obj));
        }
    }

    public void c(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.c.add(new KeyValue(str, str2));
        }
    }

    public List<Header> b() {
        return new ArrayList(this.b);
    }

    public List<KeyValue> c() {
        return new ArrayList(this.c);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.c.isEmpty()) {
            for (KeyValue next : this.c) {
                sb.append(next.b);
                sb.append("=");
                sb.append(next.c);
                sb.append(a.b);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static final class ArrayItem extends KeyValue {
        public ArrayItem(String str, Object obj) {
            super(str, obj);
        }
    }

    public static final class Header extends KeyValue {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f2353a;

        public Header(String str, String str2, boolean z) {
            super(str, str2);
            this.f2353a = z;
        }
    }
}
