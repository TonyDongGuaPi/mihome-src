package com.xiaomi.mimc.protobuf;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public class UnmodifiableLazyStringList extends AbstractList<String> implements LazyStringList, RandomAccess {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final LazyStringList f11350a;

    public LazyStringList h() {
        return this;
    }

    public UnmodifiableLazyStringList(LazyStringList lazyStringList) {
        this.f11350a = lazyStringList;
    }

    /* renamed from: a */
    public String get(int i) {
        return (String) this.f11350a.get(i);
    }

    public Object d(int i) {
        return this.f11350a.d(i);
    }

    public int size() {
        return this.f11350a.size();
    }

    public ByteString f(int i) {
        return this.f11350a.f(i);
    }

    public void a(ByteString byteString) {
        throw new UnsupportedOperationException();
    }

    public void a(int i, ByteString byteString) {
        throw new UnsupportedOperationException();
    }

    public boolean a(Collection<? extends ByteString> collection) {
        throw new UnsupportedOperationException();
    }

    public byte[] g(int i) {
        return this.f11350a.g(i);
    }

    public void a(byte[] bArr) {
        throw new UnsupportedOperationException();
    }

    public void a(int i, byte[] bArr) {
        throw new UnsupportedOperationException();
    }

    public boolean b(Collection<byte[]> collection) {
        throw new UnsupportedOperationException();
    }

    public ListIterator<String> listIterator(final int i) {
        return new ListIterator<String>() {

            /* renamed from: a  reason: collision with root package name */
            ListIterator<String> f11351a = UnmodifiableLazyStringList.this.f11350a.listIterator(i);

            public boolean hasNext() {
                return this.f11351a.hasNext();
            }

            /* renamed from: a */
            public String next() {
                return this.f11351a.next();
            }

            public boolean hasPrevious() {
                return this.f11351a.hasPrevious();
            }

            /* renamed from: b */
            public String previous() {
                return this.f11351a.previous();
            }

            public int nextIndex() {
                return this.f11351a.nextIndex();
            }

            public int previousIndex() {
                return this.f11351a.previousIndex();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            /* renamed from: a */
            public void set(String str) {
                throw new UnsupportedOperationException();
            }

            /* renamed from: b */
            public void add(String str) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public Iterator<String> iterator() {
        return new Iterator<String>() {

            /* renamed from: a  reason: collision with root package name */
            Iterator<String> f11352a = UnmodifiableLazyStringList.this.f11350a.iterator();

            public boolean hasNext() {
                return this.f11352a.hasNext();
            }

            /* renamed from: a */
            public String next() {
                return this.f11352a.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public List<?> e() {
        return this.f11350a.e();
    }

    public void a(LazyStringList lazyStringList) {
        throw new UnsupportedOperationException();
    }

    public List<byte[]> f() {
        return Collections.unmodifiableList(this.f11350a.f());
    }

    public List<ByteString> g() {
        return Collections.unmodifiableList(this.f11350a.g());
    }
}
