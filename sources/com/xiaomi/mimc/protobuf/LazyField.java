package com.xiaomi.mimc.protobuf;

import java.util.Iterator;
import java.util.Map;

public class LazyField extends LazyFieldLite {
    private final MessageLite b;

    public LazyField(MessageLite messageLite, ExtensionRegistryLite extensionRegistryLite, ByteString byteString) {
        super(extensionRegistryLite, byteString);
        this.b = messageLite;
    }

    public boolean a() {
        return super.a() || this.f11328a == this.b;
    }

    public MessageLite b() {
        return b(this.b);
    }

    public int hashCode() {
        return b().hashCode();
    }

    public boolean equals(Object obj) {
        return b().equals(obj);
    }

    public String toString() {
        return b().toString();
    }

    static class LazyEntry<K> implements Map.Entry<K, Object> {

        /* renamed from: a  reason: collision with root package name */
        private Map.Entry<K, LazyField> f11326a;

        private LazyEntry(Map.Entry<K, LazyField> entry) {
            this.f11326a = entry;
        }

        public K getKey() {
            return this.f11326a.getKey();
        }

        public Object getValue() {
            LazyField value = this.f11326a.getValue();
            if (value == null) {
                return null;
            }
            return value.b();
        }

        public LazyField a() {
            return this.f11326a.getValue();
        }

        public Object setValue(Object obj) {
            if (obj instanceof MessageLite) {
                return this.f11326a.getValue().c((MessageLite) obj);
            }
            throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
        }
    }

    static class LazyIterator<K> implements Iterator<Map.Entry<K, Object>> {

        /* renamed from: a  reason: collision with root package name */
        private Iterator<Map.Entry<K, Object>> f11327a;

        public LazyIterator(Iterator<Map.Entry<K, Object>> it) {
            this.f11327a = it;
        }

        public boolean hasNext() {
            return this.f11327a.hasNext();
        }

        /* renamed from: a */
        public Map.Entry<K, Object> next() {
            Map.Entry<K, Object> next = this.f11327a.next();
            return next.getValue() instanceof LazyField ? new LazyEntry(next) : next;
        }

        public void remove() {
            this.f11327a.remove();
        }
    }
}
