package com.lidroid.xutils.http.client.multipart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

class MinimalFieldHeader implements Iterable<MinimalField> {

    /* renamed from: a  reason: collision with root package name */
    private final List<MinimalField> f6350a = new LinkedList();
    private final Map<String, List<MinimalField>> b = new HashMap();

    public void a(MinimalField minimalField) {
        if (minimalField != null) {
            String lowerCase = minimalField.a().toLowerCase(Locale.US);
            List list = this.b.get(lowerCase);
            if (list == null) {
                list = new LinkedList();
                this.b.put(lowerCase, list);
            }
            list.add(minimalField);
            this.f6350a.add(minimalField);
        }
    }

    public List<MinimalField> a() {
        return new ArrayList(this.f6350a);
    }

    public MinimalField a(String str) {
        List list;
        if (str == null || (list = this.b.get(str.toLowerCase(Locale.US))) == null || list.isEmpty()) {
            return null;
        }
        return (MinimalField) list.get(0);
    }

    public List<MinimalField> b(String str) {
        if (str == null) {
            return null;
        }
        List list = this.b.get(str.toLowerCase(Locale.US));
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList(list);
    }

    public int c(String str) {
        List remove;
        if (str == null || (remove = this.b.remove(str.toLowerCase(Locale.US))) == null || remove.isEmpty()) {
            return 0;
        }
        this.f6350a.removeAll(remove);
        return remove.size();
    }

    public void b(MinimalField minimalField) {
        if (minimalField != null) {
            List list = this.b.get(minimalField.a().toLowerCase(Locale.US));
            if (list == null || list.isEmpty()) {
                a(minimalField);
                return;
            }
            list.clear();
            list.add(minimalField);
            Iterator<MinimalField> it = this.f6350a.iterator();
            int i = -1;
            int i2 = 0;
            while (it.hasNext()) {
                if (it.next().a().equalsIgnoreCase(minimalField.a())) {
                    it.remove();
                    if (i == -1) {
                        i = i2;
                    }
                }
                i2++;
            }
            this.f6350a.add(i, minimalField);
        }
    }

    public Iterator<MinimalField> iterator() {
        return Collections.unmodifiableList(this.f6350a).iterator();
    }

    public String toString() {
        return this.f6350a.toString();
    }
}
