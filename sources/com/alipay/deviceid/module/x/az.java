package com.alipay.deviceid.module.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;

public final class az extends be {

    /* renamed from: a  reason: collision with root package name */
    String f886a;
    byte[] b;
    String c = "application/x-www-form-urlencoded";
    ArrayList<Header> d = new ArrayList<>();
    boolean e;
    private Map<String, String> h = new HashMap();

    public az(String str) {
        this.f886a = str;
    }

    public final void a(Header header) {
        this.d.add(header);
    }

    public final void a(String str, String str2) {
        if (this.h == null) {
            this.h = new HashMap();
        }
        this.h.put(str, str2);
    }

    public final String a(String str) {
        if (this.h == null) {
            return null;
        }
        return this.h.get(str);
    }

    public final String toString() {
        return String.format("Url : %s,HttpHeader: %s", new Object[]{this.f886a, this.d});
    }

    public final int hashCode() {
        return (((this.h == null || !this.h.containsKey("id")) ? 1 : this.h.get("id").hashCode() + 31) * 31) + (this.f886a == null ? 0 : this.f886a.hashCode());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        az azVar = (az) obj;
        if (this.b == null) {
            if (azVar.b != null) {
                return false;
            }
        } else if (!this.b.equals(azVar.b)) {
            return false;
        }
        if (this.f886a == null) {
            if (azVar.f886a != null) {
                return false;
            }
        } else if (!this.f886a.equals(azVar.f886a)) {
            return false;
        }
        return true;
    }
}
