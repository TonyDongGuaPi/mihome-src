package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LazyHeaders implements Headers {
    private final Map<String, List<LazyHeaderFactory>> c;
    private volatile Map<String, String> d;

    LazyHeaders(Map<String, List<LazyHeaderFactory>> map) {
        this.c = Collections.unmodifiableMap(map);
    }

    public Map<String, String> a() {
        if (this.d == null) {
            synchronized (this) {
                if (this.d == null) {
                    this.d = Collections.unmodifiableMap(b());
                }
            }
        }
        return this.d;
    }

    private Map<String, String> b() {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.c.entrySet()) {
            String a2 = a((List) next.getValue());
            if (!TextUtils.isEmpty(a2)) {
                hashMap.put(next.getKey(), a2);
            }
        }
        return hashMap;
    }

    @NonNull
    private String a(@NonNull List<LazyHeaderFactory> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String a2 = list.get(i).a();
            if (!TextUtils.isEmpty(a2)) {
                sb.append(a2);
                if (i != list.size() - 1) {
                    sb.append(',');
                }
            }
        }
        return sb.toString();
    }

    public String toString() {
        return "LazyHeaders{headers=" + this.c + Operators.BLOCK_END;
    }

    public boolean equals(Object obj) {
        if (obj instanceof LazyHeaders) {
            return this.c.equals(((LazyHeaders) obj).c);
        }
        return false;
    }

    public int hashCode() {
        return this.c.hashCode();
    }

    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        private static final String f4950a = "User-Agent";
        private static final String b = b();
        private static final Map<String, List<LazyHeaderFactory>> c;
        private boolean d = true;
        private Map<String, List<LazyHeaderFactory>> e = c;
        private boolean f = true;

        static {
            HashMap hashMap = new HashMap(2);
            if (!TextUtils.isEmpty(b)) {
                hashMap.put("User-Agent", Collections.singletonList(new StringHeaderFactory(b)));
            }
            c = Collections.unmodifiableMap(hashMap);
        }

        public Builder a(@NonNull String str, @NonNull String str2) {
            return a(str, (LazyHeaderFactory) new StringHeaderFactory(str2));
        }

        public Builder a(@NonNull String str, @NonNull LazyHeaderFactory lazyHeaderFactory) {
            if (this.f && "User-Agent".equalsIgnoreCase(str)) {
                return b(str, lazyHeaderFactory);
            }
            c();
            a(str).add(lazyHeaderFactory);
            return this;
        }

        public Builder b(@NonNull String str, @Nullable String str2) {
            return b(str, (LazyHeaderFactory) str2 == null ? null : new StringHeaderFactory(str2));
        }

        public Builder b(@NonNull String str, @Nullable LazyHeaderFactory lazyHeaderFactory) {
            c();
            if (lazyHeaderFactory == null) {
                this.e.remove(str);
            } else {
                List<LazyHeaderFactory> a2 = a(str);
                a2.clear();
                a2.add(lazyHeaderFactory);
            }
            if (this.f && "User-Agent".equalsIgnoreCase(str)) {
                this.f = false;
            }
            return this;
        }

        private List<LazyHeaderFactory> a(String str) {
            List<LazyHeaderFactory> list = this.e.get(str);
            if (list != null) {
                return list;
            }
            ArrayList arrayList = new ArrayList();
            this.e.put(str, arrayList);
            return arrayList;
        }

        private void c() {
            if (this.d) {
                this.d = false;
                this.e = d();
            }
        }

        public LazyHeaders a() {
            this.d = true;
            return new LazyHeaders(this.e);
        }

        private Map<String, List<LazyHeaderFactory>> d() {
            HashMap hashMap = new HashMap(this.e.size());
            for (Map.Entry next : this.e.entrySet()) {
                hashMap.put(next.getKey(), new ArrayList((Collection) next.getValue()));
            }
            return hashMap;
        }

        @VisibleForTesting
        static String b() {
            String property = System.getProperty("http.agent");
            if (TextUtils.isEmpty(property)) {
                return property;
            }
            int length = property.length();
            StringBuilder sb = new StringBuilder(property.length());
            for (int i = 0; i < length; i++) {
                char charAt = property.charAt(i);
                if ((charAt > 31 || charAt == 9) && charAt < 127) {
                    sb.append(charAt);
                } else {
                    sb.append(Operators.CONDITION_IF);
                }
            }
            return sb.toString();
        }
    }

    static final class StringHeaderFactory implements LazyHeaderFactory {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        private final String f4951a;

        StringHeaderFactory(@NonNull String str) {
            this.f4951a = str;
        }

        public String a() {
            return this.f4951a;
        }

        public String toString() {
            return "StringHeaderFactory{value='" + this.f4951a + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }

        public boolean equals(Object obj) {
            if (obj instanceof StringHeaderFactory) {
                return this.f4951a.equals(((StringHeaderFactory) obj).f4951a);
            }
            return false;
        }

        public int hashCode() {
            return this.f4951a.hashCode();
        }
    }
}
