package com.xiaomi.mimc.protobuf;

import com.xiaomi.mimc.protobuf.MessageLite;
import com.xiaomi.mimc.protobuf.WireFormat;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

public class MapEntryLite<K, V> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f11331a = 1;
    private static final int b = 2;
    private final Metadata<K, V> c;
    private final K d;
    private final V e;

    static class Metadata<K, V> {

        /* renamed from: a  reason: collision with root package name */
        public final WireFormat.FieldType f11333a;
        public final K b;
        public final WireFormat.FieldType c;
        public final V d;

        public Metadata(WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
            this.f11333a = fieldType;
            this.b = k;
            this.c = fieldType2;
            this.d = v;
        }
    }

    private MapEntryLite(WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
        this.c = new Metadata<>(fieldType, k, fieldType2, v);
        this.d = k;
        this.e = v;
    }

    private MapEntryLite(Metadata<K, V> metadata, K k, V v) {
        this.c = metadata;
        this.d = k;
        this.e = v;
    }

    public K a() {
        return this.d;
    }

    public V b() {
        return this.e;
    }

    public static <K, V> MapEntryLite<K, V> a(WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
        return new MapEntryLite<>(fieldType, k, fieldType2, v);
    }

    static <K, V> void a(CodedOutputStream codedOutputStream, Metadata<K, V> metadata, K k, V v) throws IOException {
        FieldSet.a(codedOutputStream, metadata.f11333a, 1, k);
        FieldSet.a(codedOutputStream, metadata.c, 2, v);
    }

    static <K, V> int a(Metadata<K, V> metadata, K k, V v) {
        return FieldSet.a(metadata.f11333a, 1, (Object) k) + FieldSet.a(metadata.c, 2, (Object) v);
    }

    static <T> T a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, WireFormat.FieldType fieldType, T t) throws IOException {
        switch (fieldType) {
            case MESSAGE:
                MessageLite.Builder Y = ((MessageLite) t).Y();
                codedInputStream.a(Y, extensionRegistryLite);
                return Y.Y();
            case ENUM:
                return Integer.valueOf(codedInputStream.r());
            case GROUP:
                throw new RuntimeException("Groups are not allowed in maps.");
            default:
                return FieldSet.a(codedInputStream, fieldType, true);
        }
    }

    public void a(CodedOutputStream codedOutputStream, int i, K k, V v) throws IOException {
        codedOutputStream.a(i, 2);
        codedOutputStream.d(a(this.c, k, v));
        a(codedOutputStream, this.c, k, v);
    }

    public int a(int i, K k, V v) {
        return CodedOutputStream.i(i) + CodedOutputStream.p(a(this.c, k, v));
    }

    public Map.Entry<K, V> a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return a(byteString.newCodedInput(), this.c, extensionRegistryLite);
    }

    static <K, V> Map.Entry<K, V> a(CodedInputStream codedInputStream, Metadata<K, V> metadata, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        K k = metadata.b;
        V v = metadata.d;
        while (true) {
            int a2 = codedInputStream.a();
            if (a2 == 0) {
                break;
            } else if (a2 == WireFormat.a(1, metadata.f11333a.getWireType())) {
                k = a(codedInputStream, extensionRegistryLite, metadata.f11333a, k);
            } else if (a2 == WireFormat.a(2, metadata.c.getWireType())) {
                v = a(codedInputStream, extensionRegistryLite, metadata.c, v);
            } else if (!codedInputStream.b(a2)) {
                break;
            }
        }
        return new AbstractMap.SimpleImmutableEntry(k, v);
    }

    public void a(MapFieldLite<K, V> mapFieldLite, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int f = codedInputStream.f(codedInputStream.w());
        K k = this.c.b;
        V v = this.c.d;
        while (true) {
            int a2 = codedInputStream.a();
            if (a2 == 0) {
                break;
            } else if (a2 == WireFormat.a(1, this.c.f11333a.getWireType())) {
                k = a(codedInputStream, extensionRegistryLite, this.c.f11333a, k);
            } else if (a2 == WireFormat.a(2, this.c.c.getWireType())) {
                v = a(codedInputStream, extensionRegistryLite, this.c.c, v);
            } else if (!codedInputStream.b(a2)) {
                break;
            }
        }
        codedInputStream.a(0);
        codedInputStream.g(f);
        mapFieldLite.put(k, v);
    }
}
