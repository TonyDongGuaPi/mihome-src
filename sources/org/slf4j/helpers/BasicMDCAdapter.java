package org.slf4j.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.spi.MDCAdapter;

public class BasicMDCAdapter implements MDCAdapter {

    /* renamed from: a  reason: collision with root package name */
    private InheritableThreadLocal f4173a = new InheritableThreadLocal();

    public void a(String str, String str2) {
        if (str != null) {
            HashMap hashMap = (HashMap) this.f4173a.get();
            if (hashMap == null) {
                hashMap = new HashMap();
                this.f4173a.set(hashMap);
            }
            hashMap.put(str, str2);
            return;
        }
        throw new IllegalArgumentException("key cannot be null");
    }

    public String a(String str) {
        HashMap hashMap = (HashMap) this.f4173a.get();
        if (hashMap == null || str == null) {
            return null;
        }
        return (String) hashMap.get(str);
    }

    public void b(String str) {
        HashMap hashMap = (HashMap) this.f4173a.get();
        if (hashMap != null) {
            hashMap.remove(str);
        }
    }

    public void a() {
        HashMap hashMap = (HashMap) this.f4173a.get();
        if (hashMap != null) {
            hashMap.clear();
            this.f4173a.remove();
        }
    }

    public Set b() {
        HashMap hashMap = (HashMap) this.f4173a.get();
        if (hashMap != null) {
            return hashMap.keySet();
        }
        return null;
    }

    public Map c() {
        HashMap hashMap = (HashMap) this.f4173a.get();
        if (hashMap != null) {
            return new HashMap(hashMap);
        }
        return null;
    }

    public void a(Map map) {
        HashMap hashMap = (HashMap) this.f4173a.get();
        if (hashMap != null) {
            hashMap.clear();
            hashMap.putAll(map);
            return;
        }
        this.f4173a.set(new HashMap(map));
    }
}
