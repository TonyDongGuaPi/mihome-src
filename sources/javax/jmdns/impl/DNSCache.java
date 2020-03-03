package javax.jmdns.impl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;

public class DNSCache extends AbstractMap<String, List<? extends DNSEntry>> {

    /* renamed from: a  reason: collision with root package name */
    public static final DNSCache f2626a = new _EmptyCache();
    private transient Set<Map.Entry<String, List<? extends DNSEntry>>> b;

    static final class _EmptyCache extends DNSCache {
        /* renamed from: a */
        public List<DNSEntry> get(Object obj) {
            return null;
        }

        /* renamed from: a */
        public List<? extends DNSEntry> put(String str, List<? extends DNSEntry> list) {
            return null;
        }

        public boolean containsKey(Object obj) {
            return false;
        }

        public boolean containsValue(Object obj) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public boolean isEmpty() {
            return true;
        }

        public int size() {
            return 0;
        }

        _EmptyCache() {
        }

        public Set<String> keySet() {
            return Collections.emptySet();
        }

        public Collection<List<? extends DNSEntry>> values() {
            return Collections.emptySet();
        }

        public Set<Map.Entry<String, List<? extends DNSEntry>>> entrySet() {
            return Collections.emptySet();
        }

        public boolean equals(Object obj) {
            return (obj instanceof Map) && ((Map) obj).size() == 0;
        }
    }

    protected static class _CacheEntry implements Map.Entry<String, List<? extends DNSEntry>> {

        /* renamed from: a  reason: collision with root package name */
        private List<? extends DNSEntry> f2627a;
        private String b;

        protected _CacheEntry(String str, List<? extends DNSEntry> list) {
            this.b = str != null ? str.trim().toLowerCase() : null;
            this.f2627a = list;
        }

        protected _CacheEntry(Map.Entry<String, List<? extends DNSEntry>> entry) {
            if (entry instanceof _CacheEntry) {
                this.b = entry.getKey();
                this.f2627a = ((_CacheEntry) entry).getValue();
            }
        }

        /* renamed from: a */
        public String getKey() {
            return this.b != null ? this.b : "";
        }

        /* renamed from: b */
        public List<? extends DNSEntry> getValue() {
            return this.f2627a;
        }

        /* renamed from: a */
        public List<? extends DNSEntry> setValue(List<? extends DNSEntry> list) {
            List<? extends DNSEntry> list2 = this.f2627a;
            this.f2627a = list;
            return list2;
        }

        public boolean c() {
            return getValue().isEmpty();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (!getKey().equals(entry.getKey()) || !getValue().equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            if (this.b == null) {
                return 0;
            }
            return this.b.hashCode();
        }

        public synchronized String toString() {
            StringBuilder sb;
            sb = new StringBuilder(200);
            sb.append("\n\t\tname '");
            sb.append(this.b);
            sb.append("' ");
            if (this.f2627a == null || this.f2627a.isEmpty()) {
                sb.append(" no entries");
            } else {
                for (DNSEntry dNSEntry : this.f2627a) {
                    sb.append("\n\t\t\t");
                    sb.append(dNSEntry.toString());
                }
            }
            return sb.toString();
        }
    }

    public DNSCache() {
        this(1024);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DNSCache(DNSCache dNSCache) {
        this(dNSCache != null ? dNSCache.size() : 1024);
        if (dNSCache != null) {
            putAll(dNSCache);
        }
    }

    public DNSCache(int i) {
        this.b = null;
        this.b = new HashSet(i);
    }

    public Set<Map.Entry<String, List<? extends DNSEntry>>> entrySet() {
        if (this.b == null) {
            this.b = new HashSet();
        }
        return this.b;
    }

    /* access modifiers changed from: protected */
    public Map.Entry<String, List<? extends DNSEntry>> a(String str) {
        String lowerCase = str != null ? str.trim().toLowerCase() : null;
        for (Map.Entry<String, List<? extends DNSEntry>> next : entrySet()) {
            if (lowerCase != null) {
                if (lowerCase.equals(next.getKey())) {
                    return next;
                }
            } else if (next.getKey() == null) {
                return next;
            }
        }
        return null;
    }

    /* renamed from: a */
    public List<? extends DNSEntry> put(String str, List<? extends DNSEntry> list) {
        List<? extends DNSEntry> list2;
        synchronized (this) {
            list2 = null;
            Map.Entry<String, List<? extends DNSEntry>> a2 = a(str);
            if (a2 != null) {
                list2 = a2.setValue(list);
            } else {
                entrySet().add(new _CacheEntry(str, list));
            }
        }
        return list2;
    }

    /* access modifiers changed from: protected */
    public Object clone() throws CloneNotSupportedException {
        return new DNSCache(this);
    }

    public synchronized Collection<DNSEntry> a() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (List list : values()) {
            if (list != null) {
                arrayList.addAll(list);
            }
        }
        return arrayList;
    }

    public synchronized Collection<? extends DNSEntry> b(String str) {
        Collection<? extends DNSEntry> collection;
        Collection<? extends DNSEntry> c = c(str);
        if (c != null) {
            collection = new ArrayList<>(c);
        } else {
            collection = Collections.emptyList();
        }
        return collection;
    }

    private Collection<? extends DNSEntry> c(String str) {
        return (Collection) get(str != null ? str.toLowerCase() : null);
    }

    public synchronized DNSEntry a(DNSEntry dNSEntry) {
        DNSEntry dNSEntry2;
        dNSEntry2 = null;
        if (dNSEntry != null) {
            Collection<? extends DNSEntry> c = c(dNSEntry.d());
            if (c != null) {
                Iterator<? extends DNSEntry> it = c.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DNSEntry dNSEntry3 = (DNSEntry) it.next();
                    if (dNSEntry3.a(dNSEntry)) {
                        dNSEntry2 = dNSEntry3;
                        break;
                    }
                }
            }
        }
        return dNSEntry2;
    }

    public synchronized DNSEntry a(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass) {
        DNSEntry dNSEntry;
        DNSEntry dNSEntry2;
        dNSEntry = null;
        Collection<? extends DNSEntry> c = c(str);
        if (c != null) {
            Iterator<? extends DNSEntry> it = c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                dNSEntry2 = (DNSEntry) it.next();
                if (!dNSEntry2.e().equals(dNSRecordType) || (DNSRecordClass.CLASS_ANY != dNSRecordClass && !dNSEntry2.f().equals(dNSRecordClass))) {
                }
            }
            dNSEntry = dNSEntry2;
        }
        return dNSEntry;
    }

    public synchronized Collection<? extends DNSEntry> b(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass) {
        Collection<? extends DNSEntry> collection;
        Collection<? extends DNSEntry> c = c(str);
        if (c != null) {
            collection = new ArrayList<>(c);
            Iterator<? extends DNSEntry> it = collection.iterator();
            while (it.hasNext()) {
                DNSEntry dNSEntry = (DNSEntry) it.next();
                if (!dNSEntry.e().equals(dNSRecordType) || (DNSRecordClass.CLASS_ANY != dNSRecordClass && !dNSEntry.f().equals(dNSRecordClass))) {
                    it.remove();
                }
            }
        } else {
            collection = Collections.emptyList();
        }
        return collection;
    }

    public synchronized boolean b(DNSEntry dNSEntry) {
        boolean z;
        ArrayList arrayList;
        z = false;
        if (dNSEntry != null) {
            Map.Entry<String, List<? extends DNSEntry>> a2 = a(dNSEntry.d());
            if (a2 != null) {
                arrayList = new ArrayList(a2.getValue());
            } else {
                arrayList = new ArrayList();
            }
            arrayList.add(dNSEntry);
            if (a2 != null) {
                a2.setValue(arrayList);
            } else {
                entrySet().add(new _CacheEntry(dNSEntry.d(), arrayList));
            }
            z = true;
        }
        return z;
    }

    public synchronized boolean c(DNSEntry dNSEntry) {
        boolean z;
        z = false;
        if (dNSEntry != null) {
            Map.Entry<String, List<? extends DNSEntry>> a2 = a(dNSEntry.d());
            if (a2 != null) {
                z = a2.getValue().remove(dNSEntry);
                if (a2.getValue().isEmpty()) {
                    entrySet().remove(a2);
                }
            }
        }
        return z;
    }

    public synchronized boolean a(DNSEntry dNSEntry, DNSEntry dNSEntry2) {
        boolean z;
        ArrayList arrayList;
        z = false;
        if (!(dNSEntry == null || dNSEntry2 == null)) {
            if (dNSEntry.d().equals(dNSEntry2.d())) {
                Map.Entry<String, List<? extends DNSEntry>> a2 = a(dNSEntry.d());
                if (a2 != null) {
                    arrayList = new ArrayList(a2.getValue());
                } else {
                    arrayList = new ArrayList();
                }
                arrayList.remove(dNSEntry2);
                arrayList.add(dNSEntry);
                if (a2 != null) {
                    a2.setValue(arrayList);
                } else {
                    entrySet().add(new _CacheEntry(dNSEntry.d(), arrayList));
                }
                z = true;
            }
        }
        return z;
    }

    public synchronized String toString() {
        StringBuilder sb;
        sb = new StringBuilder(2000);
        sb.append("\t---- cache ----");
        for (Map.Entry<String, List<? extends DNSEntry>> obj : entrySet()) {
            sb.append("\n\t\t");
            sb.append(obj.toString());
        }
        return sb.toString();
    }
}
