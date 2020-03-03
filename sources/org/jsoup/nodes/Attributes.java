package org.jsoup.nodes;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Document;

public class Attributes implements Cloneable, Iterable<Attribute> {

    /* renamed from: a  reason: collision with root package name */
    protected static final String f3657a = "data-";
    static final int b = -1;
    private static final int e = 4;
    private static final int f = 2;
    private static final String[] g = new String[0];
    private static final String h = "";
    String[] c = g;
    String[] d = g;
    /* access modifiers changed from: private */
    public int i = 0;

    static String b(String str) {
        return str == null ? "" : str;
    }

    private void a(int i2) {
        Validate.a(i2 >= this.i);
        int length = this.c.length;
        if (length < i2) {
            int i3 = length >= 4 ? this.i * 2 : 4;
            if (i2 <= i3) {
                i2 = i3;
            }
            this.c = a(this.c, i2);
            this.d = a(this.d, i2);
        }
    }

    private static String[] a(String[] strArr, int i2) {
        String[] strArr2 = new String[i2];
        System.arraycopy(strArr, 0, strArr2, 0, Math.min(strArr.length, i2));
        return strArr2;
    }

    /* access modifiers changed from: package-private */
    public int a(String str) {
        Validate.a((Object) str);
        for (int i2 = 0; i2 < this.i; i2++) {
            if (str.equals(this.c[i2])) {
                return i2;
            }
        }
        return -1;
    }

    private int j(String str) {
        Validate.a((Object) str);
        for (int i2 = 0; i2 < this.i; i2++) {
            if (str.equalsIgnoreCase(this.c[i2])) {
                return i2;
            }
        }
        return -1;
    }

    public String c(String str) {
        int a2 = a(str);
        if (a2 == -1) {
            return "";
        }
        return b(this.d[a2]);
    }

    public String d(String str) {
        int j = j(str);
        if (j == -1) {
            return "";
        }
        return b(this.d[j]);
    }

    private void c(String str, String str2) {
        a(this.i + 1);
        this.c[this.i] = str;
        this.d[this.i] = str2;
        this.i++;
    }

    public Attributes a(String str, String str2) {
        int a2 = a(str);
        if (a2 != -1) {
            this.d[a2] = str2;
        } else {
            c(str, str2);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public void b(String str, String str2) {
        int j = j(str);
        if (j != -1) {
            this.d[j] = str2;
            if (!this.c[j].equals(str)) {
                this.c[j] = str;
                return;
            }
            return;
        }
        c(str, str2);
    }

    public Attributes a(String str, boolean z) {
        if (z) {
            b(str, (String) null);
        } else {
            e(str);
        }
        return this;
    }

    public Attributes a(Attribute attribute) {
        Validate.a((Object) attribute);
        a(attribute.getKey(), attribute.getValue());
        attribute.f3656a = this;
        return this;
    }

    /* access modifiers changed from: private */
    public void b(int i2) {
        Validate.b(i2 >= this.i);
        int i3 = (this.i - i2) - 1;
        if (i3 > 0) {
            int i4 = i2 + 1;
            System.arraycopy(this.c, i4, this.c, i2, i3);
            System.arraycopy(this.d, i4, this.d, i2, i3);
        }
        this.i--;
        this.c[this.i] = null;
        this.d[this.i] = null;
    }

    public void e(String str) {
        int a2 = a(str);
        if (a2 != -1) {
            b(a2);
        }
    }

    public void f(String str) {
        int j = j(str);
        if (j != -1) {
            b(j);
        }
    }

    public boolean g(String str) {
        return a(str) != -1;
    }

    public boolean h(String str) {
        return j(str) != -1;
    }

    public int a() {
        return this.i;
    }

    public void a(Attributes attributes) {
        if (attributes.a() != 0) {
            a(this.i + attributes.i);
            Iterator<Attribute> it = attributes.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
        }
    }

    public Iterator<Attribute> iterator() {
        return new Iterator<Attribute>() {

            /* renamed from: a  reason: collision with root package name */
            int f3658a = 0;

            public boolean hasNext() {
                return this.f3658a < Attributes.this.i;
            }

            /* renamed from: a */
            public Attribute next() {
                Attribute attribute = new Attribute(Attributes.this.c[this.f3658a], Attributes.this.d[this.f3658a], Attributes.this);
                this.f3658a++;
                return attribute;
            }

            public void remove() {
                Attributes attributes = Attributes.this;
                int i = this.f3658a - 1;
                this.f3658a = i;
                attributes.b(i);
            }
        };
    }

    public List<Attribute> b() {
        Object obj;
        ArrayList arrayList = new ArrayList(this.i);
        for (int i2 = 0; i2 < this.i; i2++) {
            if (this.d[i2] == null) {
                obj = new BooleanAttribute(this.c[i2]);
            } else {
                obj = new Attribute(this.c[i2], this.d[i2], this);
            }
            arrayList.add(obj);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Map<String, String> c() {
        return new Dataset();
    }

    public String d() {
        StringBuilder sb = new StringBuilder();
        try {
            a((Appendable) sb, new Document("").m());
            return sb.toString();
        } catch (IOException e2) {
            throw new SerializationException((Throwable) e2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(Appendable appendable, Document.OutputSettings outputSettings) throws IOException {
        int i2 = this.i;
        for (int i3 = 0; i3 < i2; i3++) {
            String str = this.c[i3];
            String str2 = this.d[i3];
            appendable.append(' ').append(str);
            if (outputSettings.e() != Document.OutputSettings.Syntax.html || (str2 != null && (!str2.equals(str) || !Attribute.d(str)))) {
                appendable.append("=\"");
                if (str2 == null) {
                    str2 = "";
                }
                Entities.a(appendable, str2, outputSettings, true, false, false);
                appendable.append('\"');
            }
        }
    }

    public String toString() {
        return d();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Attributes attributes = (Attributes) obj;
        if (this.i == attributes.i && Arrays.equals(this.c, attributes.c)) {
            return Arrays.equals(this.d, attributes.d);
        }
        return false;
    }

    public int hashCode() {
        return (((this.i * 31) + Arrays.hashCode(this.c)) * 31) + Arrays.hashCode(this.d);
    }

    /* renamed from: e */
    public Attributes clone() {
        try {
            Attributes attributes = (Attributes) super.clone();
            attributes.i = this.i;
            this.c = a(this.c, this.i);
            this.d = a(this.d, this.i);
            return attributes;
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    public void f() {
        for (int i2 = 0; i2 < this.i; i2++) {
            this.c[i2] = Normalizer.a(this.c[i2]);
        }
    }

    private static class Dataset extends AbstractMap<String, String> {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final Attributes f3659a;

        private Dataset(Attributes attributes) {
            this.f3659a = attributes;
        }

        public Set<Map.Entry<String, String>> entrySet() {
            return new EntrySet();
        }

        /* renamed from: a */
        public String put(String str, String str2) {
            String i = Attributes.k(str);
            String c = this.f3659a.g(i) ? this.f3659a.c(i) : null;
            this.f3659a.a(i, str2);
            return c;
        }

        private class EntrySet extends AbstractSet<Map.Entry<String, String>> {
            private EntrySet() {
            }

            public Iterator<Map.Entry<String, String>> iterator() {
                return new DatasetIterator();
            }

            public int size() {
                int i = 0;
                while (new DatasetIterator().hasNext()) {
                    i++;
                }
                return i;
            }
        }

        private class DatasetIterator implements Iterator<Map.Entry<String, String>> {
            private Iterator<Attribute> b;
            private Attribute c;

            private DatasetIterator() {
                this.b = Dataset.this.f3659a.iterator();
            }

            public boolean hasNext() {
                while (this.b.hasNext()) {
                    this.c = this.b.next();
                    if (this.c.d()) {
                        return true;
                    }
                }
                return false;
            }

            /* renamed from: a */
            public Map.Entry<String, String> next() {
                return new Attribute(this.c.getKey().substring(Attributes.f3657a.length()), this.c.getValue());
            }

            public void remove() {
                Dataset.this.f3659a.e(this.c.getKey());
            }
        }
    }

    /* access modifiers changed from: private */
    public static String k(String str) {
        return f3657a + str;
    }
}
