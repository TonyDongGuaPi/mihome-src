package com.xiaomi.mimc.protobuf;

import com.xiaomi.mimc.protobuf.FieldSet.FieldDescriptorLite;
import com.xiaomi.mimc.protobuf.Internal;
import com.xiaomi.mimc.protobuf.LazyField;
import com.xiaomi.mimc.protobuf.MessageLite;
import com.xiaomi.mimc.protobuf.WireFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class FieldSet<FieldDescriptorType extends FieldDescriptorLite<FieldDescriptorType>> {
    private static final FieldSet d = new FieldSet(true);

    /* renamed from: a  reason: collision with root package name */
    private final SmallSortedMap<FieldDescriptorType, Object> f11307a = SmallSortedMap.a(16);
    private boolean b;
    private boolean c = false;

    public interface FieldDescriptorLite<T extends FieldDescriptorLite<T>> extends Comparable<T> {
        int a();

        MessageLite.Builder a(MessageLite.Builder builder, MessageLite messageLite);

        WireFormat.FieldType b();

        WireFormat.JavaType c();

        boolean d();

        boolean e();

        Internal.EnumLiteMap<?> f();
    }

    private FieldSet() {
    }

    private FieldSet(boolean z) {
        c();
    }

    public static <T extends FieldDescriptorLite<T>> FieldSet<T> a() {
        return new FieldSet<>();
    }

    public static <T extends FieldDescriptorLite<T>> FieldSet<T> b() {
        return d;
    }

    public void c() {
        if (!this.b) {
            this.f11307a.a();
            this.b = true;
        }
    }

    public boolean d() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FieldSet)) {
            return false;
        }
        return this.f11307a.equals(((FieldSet) obj).f11307a);
    }

    public int hashCode() {
        return this.f11307a.hashCode();
    }

    /* renamed from: e */
    public FieldSet<FieldDescriptorType> clone() {
        FieldSet<FieldDescriptorType> a2 = a();
        for (int i = 0; i < this.f11307a.c(); i++) {
            Map.Entry<FieldDescriptorType, Object> c2 = this.f11307a.c(i);
            a2.a((FieldDescriptorType) (FieldDescriptorLite) c2.getKey(), c2.getValue());
        }
        for (Map.Entry next : this.f11307a.e()) {
            a2.a((FieldDescriptorType) (FieldDescriptorLite) next.getKey(), next.getValue());
        }
        a2.c = this.c;
        return a2;
    }

    public void f() {
        this.f11307a.clear();
        this.c = false;
    }

    public Map<FieldDescriptorType, Object> g() {
        if (!this.c) {
            return this.f11307a.b() ? this.f11307a : Collections.unmodifiableMap(this.f11307a);
        }
        SmallSortedMap a2 = SmallSortedMap.a(16);
        for (int i = 0; i < this.f11307a.c(); i++) {
            a(a2, this.f11307a.c(i));
        }
        for (Map.Entry<FieldDescriptorType, Object> a3 : this.f11307a.e()) {
            a(a2, a3);
        }
        if (this.f11307a.b()) {
            a2.a();
        }
        return a2;
    }

    private void a(Map<FieldDescriptorType, Object> map, Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof LazyField) {
            map.put(fieldDescriptorLite, ((LazyField) value).b());
        } else {
            map.put(fieldDescriptorLite, value);
        }
    }

    public Iterator<Map.Entry<FieldDescriptorType, Object>> h() {
        if (this.c) {
            return new LazyField.LazyIterator(this.f11307a.entrySet().iterator());
        }
        return this.f11307a.entrySet().iterator();
    }

    public boolean a(FieldDescriptorType fielddescriptortype) {
        if (!fielddescriptortype.d()) {
            return this.f11307a.get(fielddescriptortype) != null;
        }
        throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
    }

    public Object b(FieldDescriptorType fielddescriptortype) {
        Object obj = this.f11307a.get(fielddescriptortype);
        return obj instanceof LazyField ? ((LazyField) obj).b() : obj;
    }

    public void a(FieldDescriptorType fielddescriptortype, Object obj) {
        if (!fielddescriptortype.d()) {
            b(fielddescriptortype.b(), obj);
        } else if (obj instanceof List) {
            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.addAll((List) obj);
            for (Object b2 : arrayList) {
                b(fielddescriptortype.b(), b2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof LazyField) {
            this.c = true;
        }
        this.f11307a.put(fielddescriptortype, obj);
    }

    public void c(FieldDescriptorType fielddescriptortype) {
        this.f11307a.remove(fielddescriptortype);
        if (this.f11307a.isEmpty()) {
            this.c = false;
        }
    }

    public int d(FieldDescriptorType fielddescriptortype) {
        if (fielddescriptortype.d()) {
            Object b2 = b(fielddescriptortype);
            if (b2 == null) {
                return 0;
            }
            return ((List) b2).size();
        }
        throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    }

    public Object a(FieldDescriptorType fielddescriptortype, int i) {
        if (fielddescriptortype.d()) {
            Object b2 = b(fielddescriptortype);
            if (b2 != null) {
                return ((List) b2).get(i);
            }
            throw new IndexOutOfBoundsException();
        }
        throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    }

    public void a(FieldDescriptorType fielddescriptortype, int i, Object obj) {
        if (fielddescriptortype.d()) {
            Object b2 = b(fielddescriptortype);
            if (b2 != null) {
                b(fielddescriptortype.b(), obj);
                ((List) b2).set(i, obj);
                return;
            }
            throw new IndexOutOfBoundsException();
        }
        throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    }

    public void b(FieldDescriptorType fielddescriptortype, Object obj) {
        List list;
        if (fielddescriptortype.d()) {
            b(fielddescriptortype.b(), obj);
            Object b2 = b(fielddescriptortype);
            if (b2 == null) {
                list = new ArrayList();
                this.f11307a.put(fielddescriptortype, list);
            } else {
                list = (List) b2;
            }
            list.add(obj);
            return;
        }
        throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        if ((r3 instanceof com.xiaomi.mimc.protobuf.Internal.EnumLite) == false) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        if ((r3 instanceof byte[]) == false) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if ((r3 instanceof com.xiaomi.mimc.protobuf.LazyField) == false) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        r1 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void b(com.xiaomi.mimc.protobuf.WireFormat.FieldType r2, java.lang.Object r3) {
        /*
            if (r3 == 0) goto L_0x004c
            int[] r0 = com.xiaomi.mimc.protobuf.FieldSet.AnonymousClass1.f11308a
            com.xiaomi.mimc.protobuf.WireFormat$JavaType r2 = r2.getJavaType()
            int r2 = r2.ordinal()
            r2 = r0[r2]
            r0 = 1
            r1 = 0
            switch(r2) {
                case 1: goto L_0x003f;
                case 2: goto L_0x003c;
                case 3: goto L_0x0039;
                case 4: goto L_0x0036;
                case 5: goto L_0x0033;
                case 6: goto L_0x0030;
                case 7: goto L_0x0027;
                case 8: goto L_0x001e;
                case 9: goto L_0x0014;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x0041
        L_0x0014:
            boolean r2 = r3 instanceof com.xiaomi.mimc.protobuf.MessageLite
            if (r2 != 0) goto L_0x001c
            boolean r2 = r3 instanceof com.xiaomi.mimc.protobuf.LazyField
            if (r2 == 0) goto L_0x0041
        L_0x001c:
            r1 = 1
            goto L_0x0041
        L_0x001e:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L_0x001c
            boolean r2 = r3 instanceof com.xiaomi.mimc.protobuf.Internal.EnumLite
            if (r2 == 0) goto L_0x0041
            goto L_0x001c
        L_0x0027:
            boolean r2 = r3 instanceof com.xiaomi.mimc.protobuf.ByteString
            if (r2 != 0) goto L_0x001c
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L_0x0041
            goto L_0x001c
        L_0x0030:
            boolean r1 = r3 instanceof java.lang.String
            goto L_0x0041
        L_0x0033:
            boolean r1 = r3 instanceof java.lang.Boolean
            goto L_0x0041
        L_0x0036:
            boolean r1 = r3 instanceof java.lang.Double
            goto L_0x0041
        L_0x0039:
            boolean r1 = r3 instanceof java.lang.Float
            goto L_0x0041
        L_0x003c:
            boolean r1 = r3 instanceof java.lang.Long
            goto L_0x0041
        L_0x003f:
            boolean r1 = r3 instanceof java.lang.Integer
        L_0x0041:
            if (r1 == 0) goto L_0x0044
            return
        L_0x0044:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        L_0x004c:
            java.lang.NullPointerException r2 = new java.lang.NullPointerException
            r2.<init>()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.FieldSet.b(com.xiaomi.mimc.protobuf.WireFormat$FieldType, java.lang.Object):void");
    }

    public boolean i() {
        for (int i = 0; i < this.f11307a.c(); i++) {
            if (!a(this.f11307a.c(i))) {
                return false;
            }
        }
        for (Map.Entry<FieldDescriptorType, Object> a2 : this.f11307a.e()) {
            if (!a(a2)) {
                return false;
            }
        }
        return true;
    }

    private boolean a(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite) entry.getKey();
        if (fieldDescriptorLite.c() == WireFormat.JavaType.MESSAGE) {
            if (fieldDescriptorLite.d()) {
                for (MessageLite h_ : (List) entry.getValue()) {
                    if (!h_.h_()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof MessageLite) {
                    if (!((MessageLite) value).h_()) {
                        return false;
                    }
                } else if (value instanceof LazyField) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    static int a(WireFormat.FieldType fieldType, boolean z) {
        if (z) {
            return 2;
        }
        return fieldType.getWireType();
    }

    public void a(FieldSet<FieldDescriptorType> fieldSet) {
        for (int i = 0; i < fieldSet.f11307a.c(); i++) {
            b(fieldSet.f11307a.c(i));
        }
        for (Map.Entry<FieldDescriptorType, Object> b2 : fieldSet.f11307a.e()) {
            b(b2);
        }
    }

    private Object a(Object obj) {
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private void b(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof LazyField) {
            value = ((LazyField) value).b();
        }
        if (fieldDescriptorLite.d()) {
            Object b2 = b(fieldDescriptorLite);
            if (b2 == null) {
                b2 = new ArrayList();
            }
            for (Object a2 : (List) value) {
                ((List) b2).add(a(a2));
            }
            this.f11307a.put(fieldDescriptorLite, b2);
        } else if (fieldDescriptorLite.c() == WireFormat.JavaType.MESSAGE) {
            Object b3 = b(fieldDescriptorLite);
            if (b3 == null) {
                this.f11307a.put(fieldDescriptorLite, a(value));
                return;
            }
            this.f11307a.put(fieldDescriptorLite, fieldDescriptorLite.a(((MessageLite) b3).Y(), (MessageLite) value).Z());
        } else {
            this.f11307a.put(fieldDescriptorLite, a(value));
        }
    }

    public static Object a(CodedInputStream codedInputStream, WireFormat.FieldType fieldType, boolean z) throws IOException {
        if (z) {
            return WireFormat.a(codedInputStream, fieldType, WireFormat.Utf8Validation.STRICT);
        }
        return WireFormat.a(codedInputStream, fieldType, WireFormat.Utf8Validation.LOOSE);
    }

    public void a(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.f11307a.c(); i++) {
            Map.Entry<FieldDescriptorType, Object> c2 = this.f11307a.c(i);
            a((FieldDescriptorLite<?>) (FieldDescriptorLite) c2.getKey(), c2.getValue(), codedOutputStream);
        }
        for (Map.Entry next : this.f11307a.e()) {
            a((FieldDescriptorLite<?>) (FieldDescriptorLite) next.getKey(), next.getValue(), codedOutputStream);
        }
    }

    public void b(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.f11307a.c(); i++) {
            a(this.f11307a.c(i), codedOutputStream);
        }
        for (Map.Entry<FieldDescriptorType, Object> a2 : this.f11307a.e()) {
            a(a2, codedOutputStream);
        }
    }

    private void a(Map.Entry<FieldDescriptorType, Object> entry, CodedOutputStream codedOutputStream) throws IOException {
        FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite) entry.getKey();
        if (fieldDescriptorLite.c() != WireFormat.JavaType.MESSAGE || fieldDescriptorLite.d() || fieldDescriptorLite.e()) {
            a((FieldDescriptorLite<?>) fieldDescriptorLite, entry.getValue(), codedOutputStream);
            return;
        }
        Object value = entry.getValue();
        if (value instanceof LazyField) {
            value = ((LazyField) value).b();
        }
        codedOutputStream.b(((FieldDescriptorLite) entry.getKey()).a(), (MessageLite) value);
    }

    static void a(CodedOutputStream codedOutputStream, WireFormat.FieldType fieldType, int i, Object obj) throws IOException {
        if (fieldType == WireFormat.FieldType.GROUP) {
            codedOutputStream.e(i, (MessageLite) obj);
            return;
        }
        codedOutputStream.a(i, a(fieldType, false));
        a(codedOutputStream, fieldType, obj);
    }

    static void a(CodedOutputStream codedOutputStream, WireFormat.FieldType fieldType, Object obj) throws IOException {
        switch (fieldType) {
            case DOUBLE:
                codedOutputStream.a(((Double) obj).doubleValue());
                return;
            case FLOAT:
                codedOutputStream.a(((Float) obj).floatValue());
                return;
            case INT64:
                codedOutputStream.a(((Long) obj).longValue());
                return;
            case UINT64:
                codedOutputStream.b(((Long) obj).longValue());
                return;
            case INT32:
                codedOutputStream.c(((Integer) obj).intValue());
                return;
            case FIXED64:
                codedOutputStream.d(((Long) obj).longValue());
                return;
            case FIXED32:
                codedOutputStream.f(((Integer) obj).intValue());
                return;
            case BOOL:
                codedOutputStream.a(((Boolean) obj).booleanValue());
                return;
            case GROUP:
                codedOutputStream.c((MessageLite) obj);
                return;
            case MESSAGE:
                codedOutputStream.a((MessageLite) obj);
                return;
            case STRING:
                if (obj instanceof ByteString) {
                    codedOutputStream.b((ByteString) obj);
                    return;
                } else {
                    codedOutputStream.a((String) obj);
                    return;
                }
            case BYTES:
                if (obj instanceof ByteString) {
                    codedOutputStream.b((ByteString) obj);
                    return;
                } else {
                    codedOutputStream.c((byte[]) obj);
                    return;
                }
            case UINT32:
                codedOutputStream.d(((Integer) obj).intValue());
                return;
            case SFIXED32:
                codedOutputStream.g(((Integer) obj).intValue());
                return;
            case SFIXED64:
                codedOutputStream.e(((Long) obj).longValue());
                return;
            case SINT32:
                codedOutputStream.e(((Integer) obj).intValue());
                return;
            case SINT64:
                codedOutputStream.c(((Long) obj).longValue());
                return;
            case ENUM:
                if (obj instanceof Internal.EnumLite) {
                    codedOutputStream.h(((Internal.EnumLite) obj).getNumber());
                    return;
                } else {
                    codedOutputStream.h(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public static void a(FieldDescriptorLite<?> fieldDescriptorLite, Object obj, CodedOutputStream codedOutputStream) throws IOException {
        WireFormat.FieldType b2 = fieldDescriptorLite.b();
        int a2 = fieldDescriptorLite.a();
        if (fieldDescriptorLite.d()) {
            List<Object> list = (List) obj;
            if (fieldDescriptorLite.e()) {
                codedOutputStream.a(a2, 2);
                int i = 0;
                for (Object a3 : list) {
                    i += a(b2, a3);
                }
                codedOutputStream.r(i);
                for (Object a4 : list) {
                    a(codedOutputStream, b2, a4);
                }
                return;
            }
            for (Object a5 : list) {
                a(codedOutputStream, b2, a2, a5);
            }
        } else if (obj instanceof LazyField) {
            a(codedOutputStream, b2, a2, ((LazyField) obj).b());
        } else {
            a(codedOutputStream, b2, a2, obj);
        }
    }

    public int j() {
        int i = 0;
        for (int i2 = 0; i2 < this.f11307a.c(); i2++) {
            Map.Entry<FieldDescriptorType, Object> c2 = this.f11307a.c(i2);
            i += c((FieldDescriptorLite) c2.getKey(), c2.getValue());
        }
        for (Map.Entry next : this.f11307a.e()) {
            i += c((FieldDescriptorLite) next.getKey(), next.getValue());
        }
        return i;
    }

    public int k() {
        int i = 0;
        for (int i2 = 0; i2 < this.f11307a.c(); i2++) {
            i += c(this.f11307a.c(i2));
        }
        for (Map.Entry<FieldDescriptorType, Object> c2 : this.f11307a.e()) {
            i += c(c2);
        }
        return i;
    }

    private int c(Map.Entry<FieldDescriptorType, Object> entry) {
        FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite) entry.getKey();
        Object value = entry.getValue();
        if (fieldDescriptorLite.c() != WireFormat.JavaType.MESSAGE || fieldDescriptorLite.d() || fieldDescriptorLite.e()) {
            return c(fieldDescriptorLite, value);
        }
        if (value instanceof LazyField) {
            return CodedOutputStream.b(((FieldDescriptorLite) entry.getKey()).a(), (LazyFieldLite) (LazyField) value);
        }
        return CodedOutputStream.d(((FieldDescriptorLite) entry.getKey()).a(), (MessageLite) value);
    }

    static int a(WireFormat.FieldType fieldType, int i, Object obj) {
        int i2 = CodedOutputStream.i(i);
        if (fieldType == WireFormat.FieldType.GROUP) {
            i2 *= 2;
        }
        return i2 + a(fieldType, obj);
    }

    static int a(WireFormat.FieldType fieldType, Object obj) {
        switch (fieldType) {
            case DOUBLE:
                return CodedOutputStream.b(((Double) obj).doubleValue());
            case FLOAT:
                return CodedOutputStream.b(((Float) obj).floatValue());
            case INT64:
                return CodedOutputStream.f(((Long) obj).longValue());
            case UINT64:
                return CodedOutputStream.g(((Long) obj).longValue());
            case INT32:
                return CodedOutputStream.j(((Integer) obj).intValue());
            case FIXED64:
                return CodedOutputStream.i(((Long) obj).longValue());
            case FIXED32:
                return CodedOutputStream.m(((Integer) obj).intValue());
            case BOOL:
                return CodedOutputStream.b(((Boolean) obj).booleanValue());
            case GROUP:
                return CodedOutputStream.d((MessageLite) obj);
            case MESSAGE:
                if (obj instanceof LazyField) {
                    return CodedOutputStream.a((LazyFieldLite) (LazyField) obj);
                }
                return CodedOutputStream.b((MessageLite) obj);
            case STRING:
                if (obj instanceof ByteString) {
                    return CodedOutputStream.c((ByteString) obj);
                }
                return CodedOutputStream.b((String) obj);
            case BYTES:
                if (obj instanceof ByteString) {
                    return CodedOutputStream.c((ByteString) obj);
                }
                return CodedOutputStream.d((byte[]) obj);
            case UINT32:
                return CodedOutputStream.k(((Integer) obj).intValue());
            case SFIXED32:
                return CodedOutputStream.n(((Integer) obj).intValue());
            case SFIXED64:
                return CodedOutputStream.j(((Long) obj).longValue());
            case SINT32:
                return CodedOutputStream.l(((Integer) obj).intValue());
            case SINT64:
                return CodedOutputStream.h(((Long) obj).longValue());
            case ENUM:
                if (obj instanceof Internal.EnumLite) {
                    return CodedOutputStream.o(((Internal.EnumLite) obj).getNumber());
                }
                return CodedOutputStream.o(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int c(FieldDescriptorLite<?> fieldDescriptorLite, Object obj) {
        WireFormat.FieldType b2 = fieldDescriptorLite.b();
        int a2 = fieldDescriptorLite.a();
        if (!fieldDescriptorLite.d()) {
            return a(b2, a2, obj);
        }
        int i = 0;
        if (fieldDescriptorLite.e()) {
            for (Object a3 : (List) obj) {
                i += a(b2, a3);
            }
            return CodedOutputStream.i(a2) + i + CodedOutputStream.s(i);
        }
        for (Object a4 : (List) obj) {
            i += a(b2, a2, a4);
        }
        return i;
    }
}
