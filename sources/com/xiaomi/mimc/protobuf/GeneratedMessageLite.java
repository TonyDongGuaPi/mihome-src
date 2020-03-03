package com.xiaomi.mimc.protobuf;

import com.xiaomi.mimc.protobuf.AbstractMessageLite;
import com.xiaomi.mimc.protobuf.FieldSet;
import com.xiaomi.mimc.protobuf.GeneratedMessageLite;
import com.xiaomi.mimc.protobuf.GeneratedMessageLite.Builder;
import com.xiaomi.mimc.protobuf.Internal;
import com.xiaomi.mimc.protobuf.MessageLite;
import com.xiaomi.mimc.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class GeneratedMessageLite<MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> extends AbstractMessageLite<MessageType, BuilderType> {
    protected UnknownFieldSetLite n = UnknownFieldSetLite.a();
    protected int o = -1;

    public interface ExtendableMessageOrBuilder<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends MessageLiteOrBuilder {
        <Type> Type a(ExtensionLite<MessageType, List<Type>> extensionLite, int i);

        <Type> boolean a_(ExtensionLite<MessageType, Type> extensionLite);

        <Type> int b(ExtensionLite<MessageType, List<Type>> extensionLite);

        <Type> Type c(ExtensionLite<MessageType, Type> extensionLite);
    }

    public enum MethodToInvoke {
        IS_INITIALIZED,
        VISIT,
        MERGE_FROM_STREAM,
        MAKE_IMMUTABLE,
        NEW_MUTABLE_INSTANCE,
        NEW_BUILDER,
        GET_DEFAULT_INSTANCE,
        GET_PARSER
    }

    protected interface Visitor {
        double a(boolean z, double d, boolean z2, double d2);

        float a(boolean z, float f, boolean z2, float f2);

        int a(boolean z, int i, boolean z2, int i2);

        long a(boolean z, long j, boolean z2, long j2);

        ByteString a(boolean z, ByteString byteString, boolean z2, ByteString byteString2);

        FieldSet<ExtensionDescriptor> a(FieldSet<ExtensionDescriptor> fieldSet, FieldSet<ExtensionDescriptor> fieldSet2);

        Internal.BooleanList a(Internal.BooleanList booleanList, Internal.BooleanList booleanList2);

        Internal.DoubleList a(Internal.DoubleList doubleList, Internal.DoubleList doubleList2);

        Internal.FloatList a(Internal.FloatList floatList, Internal.FloatList floatList2);

        Internal.IntList a(Internal.IntList intList, Internal.IntList intList2);

        Internal.LongList a(Internal.LongList longList, Internal.LongList longList2);

        <T> Internal.ProtobufList<T> a(Internal.ProtobufList<T> protobufList, Internal.ProtobufList<T> protobufList2);

        LazyFieldLite a(LazyFieldLite lazyFieldLite, LazyFieldLite lazyFieldLite2);

        <K, V> MapFieldLite<K, V> a(MapFieldLite<K, V> mapFieldLite, MapFieldLite<K, V> mapFieldLite2);

        <T extends MessageLite> T a(T t, T t2);

        UnknownFieldSetLite a(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2);

        Object a(boolean z, Object obj, Object obj2);

        String a(boolean z, String str, boolean z2, String str2);

        void a(boolean z);

        boolean a(boolean z, boolean z2, boolean z3, boolean z4);

        Object b(boolean z, Object obj, Object obj2);

        Object c(boolean z, Object obj, Object obj2);

        Object d(boolean z, Object obj, Object obj2);

        Object e(boolean z, Object obj, Object obj2);

        Object f(boolean z, Object obj, Object obj2);

        Object g(boolean z, Object obj, Object obj2);

        Object h(boolean z, Object obj, Object obj2);

        Object i(boolean z, Object obj, Object obj2);
    }

    /* access modifiers changed from: protected */
    public abstract Object a(MethodToInvoke methodToInvoke, Object obj, Object obj2);

    public final Parser<MessageType> M() {
        return (Parser) a(MethodToInvoke.GET_PARSER);
    }

    /* renamed from: N */
    public final MessageType aa() {
        return (GeneratedMessageLite) a(MethodToInvoke.GET_DEFAULT_INSTANCE);
    }

    /* renamed from: O */
    public final BuilderType Z() {
        return (Builder) a(MethodToInvoke.NEW_BUILDER);
    }

    public String toString() {
        return MessageLiteToString.a(this, super.toString());
    }

    public int hashCode() {
        if (this.m == 0) {
            HashCodeVisitor hashCodeVisitor = new HashCodeVisitor();
            a((Visitor) hashCodeVisitor, this);
            this.m = hashCodeVisitor.f11317a;
        }
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public int a(HashCodeVisitor hashCodeVisitor) {
        if (this.m == 0) {
            int a2 = hashCodeVisitor.f11317a;
            int unused = hashCodeVisitor.f11317a = 0;
            a((Visitor) hashCodeVisitor, this);
            this.m = hashCodeVisitor.f11317a;
            int unused2 = hashCodeVisitor.f11317a = a2;
        }
        return this.m;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!aa().getClass().isInstance(obj)) {
            return false;
        }
        try {
            a((Visitor) EqualsVisitor.f11312a, (GeneratedMessageLite) obj);
            return true;
        } catch (EqualsVisitor.NotEqualsException unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(EqualsVisitor equalsVisitor, MessageLite messageLite) {
        if (this == messageLite) {
            return true;
        }
        if (!aa().getClass().isInstance(messageLite)) {
            return false;
        }
        a((Visitor) equalsVisitor, (GeneratedMessageLite) messageLite);
        return true;
    }

    private final void a() {
        if (this.n == UnknownFieldSetLite.a()) {
            this.n = UnknownFieldSetLite.b();
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, CodedInputStream codedInputStream) throws IOException {
        if (WireFormat.a(i) == 4) {
            return false;
        }
        a();
        return this.n.a(i, codedInputStream);
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2) {
        a();
        this.n.a(i, i2);
    }

    /* access modifiers changed from: protected */
    public void a(int i, ByteString byteString) {
        a();
        this.n.a(i, byteString);
    }

    /* access modifiers changed from: protected */
    public void P() {
        a(MethodToInvoke.MAKE_IMMUTABLE);
        this.n.c();
    }

    public final boolean h_() {
        return a(MethodToInvoke.IS_INITIALIZED, (Object) Boolean.TRUE) != null;
    }

    /* renamed from: R */
    public final BuilderType Y() {
        BuilderType buildertype = (Builder) a(MethodToInvoke.NEW_BUILDER);
        buildertype.b(this);
        return buildertype;
    }

    /* access modifiers changed from: protected */
    public Object a(MethodToInvoke methodToInvoke, Object obj) {
        return a(methodToInvoke, obj, (Object) null);
    }

    /* access modifiers changed from: protected */
    public Object a(MethodToInvoke methodToInvoke) {
        return a(methodToInvoke, (Object) null, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public void a(Visitor visitor, MessageType messagetype) {
        a(MethodToInvoke.VISIT, (Object) visitor, (Object) messagetype);
        this.n = visitor.a(this.n, messagetype.n);
    }

    /* access modifiers changed from: protected */
    public final void a(UnknownFieldSetLite unknownFieldSetLite) {
        this.n = UnknownFieldSetLite.a(this.n, unknownFieldSetLite);
    }

    public static abstract class Builder<MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> extends AbstractMessageLite.Builder<MessageType, BuilderType> {

        /* renamed from: a  reason: collision with root package name */
        protected MessageType f11310a;
        protected boolean b = false;
        private final MessageType c;

        protected Builder(MessageType messagetype) {
            this.c = messagetype;
            this.f11310a = (GeneratedMessageLite) messagetype.a(MethodToInvoke.NEW_MUTABLE_INSTANCE);
        }

        /* access modifiers changed from: protected */
        public void S() {
            if (this.b) {
                MessageType messagetype = (GeneratedMessageLite) this.f11310a.a(MethodToInvoke.NEW_MUTABLE_INSTANCE);
                messagetype.a((Visitor) MergeFromVisitor.f11318a, this.f11310a);
                this.f11310a = messagetype;
                this.b = false;
            }
        }

        public final boolean h_() {
            return GeneratedMessageLite.a(this.f11310a, false);
        }

        /* renamed from: T */
        public final BuilderType ab() {
            this.f11310a = (GeneratedMessageLite) this.f11310a.a(MethodToInvoke.NEW_MUTABLE_INSTANCE);
            return this;
        }

        /* renamed from: U */
        public BuilderType clone() {
            BuilderType O = aa().Z();
            O.b(Y());
            return O;
        }

        /* renamed from: V */
        public MessageType Y() {
            if (this.b) {
                return this.f11310a;
            }
            this.f11310a.P();
            this.b = true;
            return this.f11310a;
        }

        /* renamed from: W */
        public final MessageType Z() {
            MessageType V = Y();
            if (V.h_()) {
                return V;
            }
            throw b((MessageLite) V);
        }

        /* access modifiers changed from: protected */
        public BuilderType a(MessageType messagetype) {
            return b(messagetype);
        }

        public BuilderType b(MessageType messagetype) {
            S();
            this.f11310a.a((Visitor) MergeFromVisitor.f11318a, messagetype);
            return this;
        }

        /* renamed from: X */
        public MessageType aa() {
            return this.c;
        }

        /* renamed from: c */
        public BuilderType b(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            S();
            try {
                this.f11310a.a(MethodToInvoke.MERGE_FROM_STREAM, (Object) codedInputStream, (Object) extensionRegistryLite);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }
    }

    public static abstract class ExtendableMessage<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends GeneratedMessageLite<MessageType, BuilderType> implements ExtendableMessageOrBuilder<MessageType, BuilderType> {

        /* renamed from: a  reason: collision with root package name */
        protected FieldSet<ExtensionDescriptor> f11313a = FieldSet.a();

        public /* synthetic */ MessageLite.Builder Y() {
            return GeneratedMessageLite.super.Y();
        }

        public /* synthetic */ MessageLite.Builder Z() {
            return GeneratedMessageLite.super.Z();
        }

        public /* synthetic */ MessageLite aa() {
            return GeneratedMessageLite.super.aa();
        }

        /* access modifiers changed from: protected */
        public final void a(MessageType messagetype) {
            if (this.f11313a.d()) {
                this.f11313a = this.f11313a.clone();
            }
            this.f11313a.a(messagetype.f11313a);
        }

        /* access modifiers changed from: package-private */
        public final void a(Visitor visitor, MessageType messagetype) {
            GeneratedMessageLite.super.a(visitor, messagetype);
            this.f11313a = visitor.a(this.f11313a, messagetype.f11313a);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0041  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0046  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public <MessageType extends com.xiaomi.mimc.protobuf.MessageLite> boolean a(MessageType r6, com.xiaomi.mimc.protobuf.CodedInputStream r7, com.xiaomi.mimc.protobuf.ExtensionRegistryLite r8, int r9) throws java.io.IOException {
            /*
                r5 = this;
                int r0 = com.xiaomi.mimc.protobuf.WireFormat.a(r9)
                int r1 = com.xiaomi.mimc.protobuf.WireFormat.b(r9)
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$GeneratedExtension r6 = r8.a(r6, r1)
                r2 = 0
                r3 = 1
                if (r6 != 0) goto L_0x0013
            L_0x0010:
                r0 = 1
            L_0x0011:
                r4 = 0
                goto L_0x003f
            L_0x0013:
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r4 = r6.d
                com.xiaomi.mimc.protobuf.WireFormat$FieldType r4 = r4.b()
                int r4 = com.xiaomi.mimc.protobuf.FieldSet.a((com.xiaomi.mimc.protobuf.WireFormat.FieldType) r4, (boolean) r2)
                if (r0 != r4) goto L_0x0021
                r0 = 0
                goto L_0x0011
            L_0x0021:
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r4 = r6.d
                boolean r4 = r4.d
                if (r4 == 0) goto L_0x0010
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r4 = r6.d
                com.xiaomi.mimc.protobuf.WireFormat$FieldType r4 = r4.c
                boolean r4 = r4.isPackable()
                if (r4 == 0) goto L_0x0010
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r4 = r6.d
                com.xiaomi.mimc.protobuf.WireFormat$FieldType r4 = r4.b()
                int r4 = com.xiaomi.mimc.protobuf.FieldSet.a((com.xiaomi.mimc.protobuf.WireFormat.FieldType) r4, (boolean) r3)
                if (r0 != r4) goto L_0x0010
                r0 = 0
                r4 = 1
            L_0x003f:
                if (r0 == 0) goto L_0x0046
                boolean r6 = r5.a((int) r9, (com.xiaomi.mimc.protobuf.CodedInputStream) r7)
                return r6
            L_0x0046:
                if (r4 == 0) goto L_0x009a
                int r8 = r7.w()
                int r8 = r7.f(r8)
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r9 = r6.d
                com.xiaomi.mimc.protobuf.WireFormat$FieldType r9 = r9.b()
                com.xiaomi.mimc.protobuf.WireFormat$FieldType r0 = com.xiaomi.mimc.protobuf.WireFormat.FieldType.ENUM
                if (r9 != r0) goto L_0x007d
            L_0x005a:
                int r9 = r7.C()
                if (r9 <= 0) goto L_0x0095
                int r9 = r7.r()
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r0 = r6.d
                com.xiaomi.mimc.protobuf.Internal$EnumLiteMap r0 = r0.f()
                com.xiaomi.mimc.protobuf.Internal$EnumLite r9 = r0.b(r9)
                if (r9 != 0) goto L_0x0071
                return r3
            L_0x0071:
                com.xiaomi.mimc.protobuf.FieldSet<com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor> r0 = r5.f11313a
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r1 = r6.d
                java.lang.Object r9 = r6.d(r9)
                r0.b(r1, (java.lang.Object) r9)
                goto L_0x005a
            L_0x007d:
                int r9 = r7.C()
                if (r9 <= 0) goto L_0x0095
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r9 = r6.d
                com.xiaomi.mimc.protobuf.WireFormat$FieldType r9 = r9.b()
                java.lang.Object r9 = com.xiaomi.mimc.protobuf.FieldSet.a((com.xiaomi.mimc.protobuf.CodedInputStream) r7, (com.xiaomi.mimc.protobuf.WireFormat.FieldType) r9, (boolean) r2)
                com.xiaomi.mimc.protobuf.FieldSet<com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor> r0 = r5.f11313a
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r1 = r6.d
                r0.b(r1, (java.lang.Object) r9)
                goto L_0x007d
            L_0x0095:
                r7.g(r8)
                goto L_0x0125
            L_0x009a:
                int[] r9 = com.xiaomi.mimc.protobuf.GeneratedMessageLite.AnonymousClass1.f11309a
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r0 = r6.d
                com.xiaomi.mimc.protobuf.WireFormat$JavaType r0 = r0.c()
                int r0 = r0.ordinal()
                r9 = r9[r0]
                switch(r9) {
                    case 1: goto L_0x00ca;
                    case 2: goto L_0x00b6;
                    default: goto L_0x00ab;
                }
            L_0x00ab:
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r8 = r6.d
                com.xiaomi.mimc.protobuf.WireFormat$FieldType r8 = r8.b()
                java.lang.Object r8 = com.xiaomi.mimc.protobuf.FieldSet.a((com.xiaomi.mimc.protobuf.CodedInputStream) r7, (com.xiaomi.mimc.protobuf.WireFormat.FieldType) r8, (boolean) r2)
                goto L_0x0106
            L_0x00b6:
                int r7 = r7.r()
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r8 = r6.d
                com.xiaomi.mimc.protobuf.Internal$EnumLiteMap r8 = r8.f()
                com.xiaomi.mimc.protobuf.Internal$EnumLite r8 = r8.b(r7)
                if (r8 != 0) goto L_0x0106
                r5.a((int) r1, (int) r7)
                return r3
            L_0x00ca:
                r9 = 0
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r0 = r6.d
                boolean r0 = r0.d()
                if (r0 != 0) goto L_0x00e3
                com.xiaomi.mimc.protobuf.FieldSet<com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor> r0 = r5.f11313a
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r1 = r6.d
                java.lang.Object r0 = r0.b(r1)
                com.xiaomi.mimc.protobuf.MessageLite r0 = (com.xiaomi.mimc.protobuf.MessageLite) r0
                if (r0 == 0) goto L_0x00e3
                com.xiaomi.mimc.protobuf.MessageLite$Builder r9 = r0.Y()
            L_0x00e3:
                if (r9 != 0) goto L_0x00ed
                com.xiaomi.mimc.protobuf.MessageLite r9 = r6.e()
                com.xiaomi.mimc.protobuf.MessageLite$Builder r9 = r9.Z()
            L_0x00ed:
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r0 = r6.d
                com.xiaomi.mimc.protobuf.WireFormat$FieldType r0 = r0.b()
                com.xiaomi.mimc.protobuf.WireFormat$FieldType r1 = com.xiaomi.mimc.protobuf.WireFormat.FieldType.GROUP
                if (r0 != r1) goto L_0x00ff
                int r0 = r6.a()
                r7.a((int) r0, (com.xiaomi.mimc.protobuf.MessageLite.Builder) r9, (com.xiaomi.mimc.protobuf.ExtensionRegistryLite) r8)
                goto L_0x0102
            L_0x00ff:
                r7.a((com.xiaomi.mimc.protobuf.MessageLite.Builder) r9, (com.xiaomi.mimc.protobuf.ExtensionRegistryLite) r8)
            L_0x0102:
                com.xiaomi.mimc.protobuf.MessageLite r8 = r9.Z()
            L_0x0106:
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r7 = r6.d
                boolean r7 = r7.d()
                if (r7 == 0) goto L_0x011a
                com.xiaomi.mimc.protobuf.FieldSet<com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor> r7 = r5.f11313a
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r9 = r6.d
                java.lang.Object r6 = r6.d(r8)
                r7.b(r9, (java.lang.Object) r6)
                goto L_0x0125
            L_0x011a:
                com.xiaomi.mimc.protobuf.FieldSet<com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor> r7 = r5.f11313a
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtensionDescriptor r9 = r6.d
                java.lang.Object r6 = r6.d(r8)
                r7.a(r9, (java.lang.Object) r6)
            L_0x0125:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.GeneratedMessageLite.ExtendableMessage.a(com.xiaomi.mimc.protobuf.MessageLite, com.xiaomi.mimc.protobuf.CodedInputStream, com.xiaomi.mimc.protobuf.ExtensionRegistryLite, int):boolean");
        }

        private void a(GeneratedExtension<MessageType, ?> generatedExtension) {
            if (generatedExtension.g() != aa()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }

        public final <Type> boolean a_(ExtensionLite<MessageType, Type> extensionLite) {
            GeneratedExtension a2 = GeneratedMessageLite.d(extensionLite);
            a(a2);
            return this.f11313a.a(a2.d);
        }

        public final <Type> int b(ExtensionLite<MessageType, List<Type>> extensionLite) {
            GeneratedExtension a2 = GeneratedMessageLite.d(extensionLite);
            a(a2);
            return this.f11313a.d(a2.d);
        }

        public final <Type> Type c(ExtensionLite<MessageType, Type> extensionLite) {
            GeneratedExtension a2 = GeneratedMessageLite.d(extensionLite);
            a(a2);
            Object b = this.f11313a.b(a2.d);
            if (b == null) {
                return a2.b;
            }
            return a2.a(b);
        }

        public final <Type> Type a(ExtensionLite<MessageType, List<Type>> extensionLite, int i) {
            GeneratedExtension a2 = GeneratedMessageLite.d(extensionLite);
            a(a2);
            return a2.b(this.f11313a.a(a2.d, i));
        }

        /* access modifiers changed from: protected */
        public boolean a() {
            return this.f11313a.i();
        }

        /* access modifiers changed from: protected */
        public final void P() {
            GeneratedMessageLite.super.P();
            this.f11313a.c();
        }

        protected class ExtensionWriter {
            private final Iterator<Map.Entry<ExtensionDescriptor, Object>> b;
            private Map.Entry<ExtensionDescriptor, Object> c;
            private final boolean d;

            private ExtensionWriter(boolean z) {
                this.b = ExtendableMessage.this.f11313a.h();
                if (this.b.hasNext()) {
                    this.c = this.b.next();
                }
                this.d = z;
            }

            public void a(int i, CodedOutputStream codedOutputStream) throws IOException {
                while (this.c != null && this.c.getKey().a() < i) {
                    ExtensionDescriptor key = this.c.getKey();
                    if (!this.d || key.c() != WireFormat.JavaType.MESSAGE || key.d()) {
                        FieldSet.a((FieldSet.FieldDescriptorLite<?>) key, this.c.getValue(), codedOutputStream);
                    } else {
                        codedOutputStream.b(key.a(), (MessageLite) this.c.getValue());
                    }
                    if (this.b.hasNext()) {
                        this.c = this.b.next();
                    } else {
                        this.c = null;
                    }
                }
            }
        }

        /* access modifiers changed from: protected */
        public ExtendableMessage<MessageType, BuilderType>.ExtensionWriter b() {
            return new ExtensionWriter(false);
        }

        /* access modifiers changed from: protected */
        public ExtendableMessage<MessageType, BuilderType>.ExtensionWriter c() {
            return new ExtensionWriter(true);
        }

        /* access modifiers changed from: protected */
        public int d() {
            return this.f11313a.j();
        }

        /* access modifiers changed from: protected */
        public int e() {
            return this.f11313a.k();
        }
    }

    public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends Builder<MessageType, BuilderType> implements ExtendableMessageOrBuilder<MessageType, BuilderType> {
        protected ExtendableBuilder(MessageType messagetype) {
            super(messagetype);
            ((ExtendableMessage) this.f11310a).f11313a = ((ExtendableMessage) this.f11310a).f11313a.clone();
        }

        /* access modifiers changed from: package-private */
        public void a(FieldSet<ExtensionDescriptor> fieldSet) {
            S();
            ((ExtendableMessage) this.f11310a).f11313a = fieldSet;
        }

        /* access modifiers changed from: protected */
        public void S() {
            if (this.b) {
                super.S();
                ((ExtendableMessage) this.f11310a).f11313a = ((ExtendableMessage) this.f11310a).f11313a.clone();
            }
        }

        /* renamed from: a */
        public final MessageType Y() {
            if (this.b) {
                return (ExtendableMessage) this.f11310a;
            }
            ((ExtendableMessage) this.f11310a).f11313a.c();
            return (ExtendableMessage) super.Y();
        }

        private void a(GeneratedExtension<MessageType, ?> generatedExtension) {
            if (generatedExtension.g() != aa()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }

        public final <Type> boolean a_(ExtensionLite<MessageType, Type> extensionLite) {
            return ((ExtendableMessage) this.f11310a).a_(extensionLite);
        }

        public final <Type> int b(ExtensionLite<MessageType, List<Type>> extensionLite) {
            return ((ExtendableMessage) this.f11310a).b(extensionLite);
        }

        public final <Type> Type c(ExtensionLite<MessageType, Type> extensionLite) {
            return ((ExtendableMessage) this.f11310a).c(extensionLite);
        }

        /* JADX WARNING: type inference failed for: r2v0, types: [com.xiaomi.mimc.protobuf.ExtensionLite, com.xiaomi.mimc.protobuf.ExtensionLite<MessageType, java.util.List<Type>>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final <Type> Type a(com.xiaomi.mimc.protobuf.ExtensionLite<MessageType, java.util.List<Type>> r2, int r3) {
            /*
                r1 = this;
                com.xiaomi.mimc.protobuf.GeneratedMessageLite r0 = r1.f11310a
                com.xiaomi.mimc.protobuf.GeneratedMessageLite$ExtendableMessage r0 = (com.xiaomi.mimc.protobuf.GeneratedMessageLite.ExtendableMessage) r0
                java.lang.Object r2 = r0.a(r2, (int) r3)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.GeneratedMessageLite.ExtendableBuilder.a(com.xiaomi.mimc.protobuf.ExtensionLite, int):java.lang.Object");
        }

        /* renamed from: b */
        public BuilderType clone() {
            return (ExtendableBuilder) super.clone();
        }

        public final <Type> BuilderType a(ExtensionLite<MessageType, Type> extensionLite, Type type) {
            GeneratedExtension a2 = GeneratedMessageLite.d(extensionLite);
            a(a2);
            S();
            ((ExtendableMessage) this.f11310a).f11313a.a(a2.d, a2.c(type));
            return this;
        }

        public final <Type> BuilderType a(ExtensionLite<MessageType, List<Type>> extensionLite, int i, Type type) {
            GeneratedExtension a2 = GeneratedMessageLite.d(extensionLite);
            a(a2);
            S();
            ((ExtendableMessage) this.f11310a).f11313a.a(a2.d, i, a2.d(type));
            return this;
        }

        public final <Type> BuilderType b(ExtensionLite<MessageType, List<Type>> extensionLite, Type type) {
            GeneratedExtension a2 = GeneratedMessageLite.d(extensionLite);
            a(a2);
            S();
            ((ExtendableMessage) this.f11310a).f11313a.b(a2.d, a2.d(type));
            return this;
        }

        public final <Type> BuilderType d(ExtensionLite<MessageType, ?> extensionLite) {
            GeneratedExtension a2 = GeneratedMessageLite.d(extensionLite);
            a(a2);
            S();
            ((ExtendableMessage) this.f11310a).f11313a.c(a2.d);
            return this;
        }
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> a(ContainingType containingtype, Type type, MessageLite messageLite, Internal.EnumLiteMap<?> enumLiteMap, int i, WireFormat.FieldType fieldType, Class cls) {
        return new GeneratedExtension(containingtype, type, messageLite, new ExtensionDescriptor(enumLiteMap, i, fieldType, false, false), cls);
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> a(ContainingType containingtype, MessageLite messageLite, Internal.EnumLiteMap<?> enumLiteMap, int i, WireFormat.FieldType fieldType, boolean z, Class cls) {
        return new GeneratedExtension(containingtype, Collections.emptyList(), messageLite, new ExtensionDescriptor(enumLiteMap, i, fieldType, true, z), cls);
    }

    static final class ExtensionDescriptor implements FieldSet.FieldDescriptorLite<ExtensionDescriptor> {

        /* renamed from: a  reason: collision with root package name */
        final Internal.EnumLiteMap<?> f11315a;
        final int b;
        final WireFormat.FieldType c;
        final boolean d;
        final boolean e;

        ExtensionDescriptor(Internal.EnumLiteMap<?> enumLiteMap, int i, WireFormat.FieldType fieldType, boolean z, boolean z2) {
            this.f11315a = enumLiteMap;
            this.b = i;
            this.c = fieldType;
            this.d = z;
            this.e = z2;
        }

        public int a() {
            return this.b;
        }

        public WireFormat.FieldType b() {
            return this.c;
        }

        public WireFormat.JavaType c() {
            return this.c.getJavaType();
        }

        public boolean d() {
            return this.d;
        }

        public boolean e() {
            return this.e;
        }

        public Internal.EnumLiteMap<?> f() {
            return this.f11315a;
        }

        public MessageLite.Builder a(MessageLite.Builder builder, MessageLite messageLite) {
            return ((Builder) builder).b((GeneratedMessageLite) messageLite);
        }

        /* renamed from: a */
        public int compareTo(ExtensionDescriptor extensionDescriptor) {
            return this.b - extensionDescriptor.b;
        }
    }

    static Method a(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Generated message class \"" + cls.getName() + "\" missing method \"" + str + "\".", e);
        }
    }

    static Object a(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    public static class GeneratedExtension<ContainingType extends MessageLite, Type> extends ExtensionLite<ContainingType, Type> {

        /* renamed from: a  reason: collision with root package name */
        final ContainingType f11316a;
        final Type b;
        final MessageLite c;
        final ExtensionDescriptor d;

        GeneratedExtension(ContainingType containingtype, Type type, MessageLite messageLite, ExtensionDescriptor extensionDescriptor, Class cls) {
            if (containingtype == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            } else if (extensionDescriptor.b() == WireFormat.FieldType.MESSAGE && messageLite == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            } else {
                this.f11316a = containingtype;
                this.b = type;
                this.c = messageLite;
                this.d = extensionDescriptor;
            }
        }

        public ContainingType g() {
            return this.f11316a;
        }

        public int a() {
            return this.d.a();
        }

        public MessageLite e() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public Object a(Object obj) {
            if (!this.d.d()) {
                return b(obj);
            }
            if (this.d.c() != WireFormat.JavaType.ENUM) {
                return obj;
            }
            ArrayList arrayList = new ArrayList();
            for (Object b2 : (List) obj) {
                arrayList.add(b(b2));
            }
            return arrayList;
        }

        /* access modifiers changed from: package-private */
        public Object b(Object obj) {
            return this.d.c() == WireFormat.JavaType.ENUM ? this.d.f11315a.b(((Integer) obj).intValue()) : obj;
        }

        /* access modifiers changed from: package-private */
        public Object c(Object obj) {
            if (!this.d.d()) {
                return d(obj);
            }
            if (this.d.c() != WireFormat.JavaType.ENUM) {
                return obj;
            }
            ArrayList arrayList = new ArrayList();
            for (Object d2 : (List) obj) {
                arrayList.add(d(d2));
            }
            return arrayList;
        }

        /* access modifiers changed from: package-private */
        public Object d(Object obj) {
            return this.d.c() == WireFormat.JavaType.ENUM ? Integer.valueOf(((Internal.EnumLite) obj).getNumber()) : obj;
        }

        public WireFormat.FieldType b() {
            return this.d.b();
        }

        public boolean c() {
            return this.d.d;
        }

        public Type d() {
            return this.b;
        }
    }

    protected static final class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final byte[] asBytes;
        private final String messageClassName;

        public static SerializedForm of(MessageLite messageLite) {
            return new SerializedForm(messageLite);
        }

        SerializedForm(MessageLite messageLite) {
            this.messageClassName = messageLite.getClass().getName();
            this.asBytes = messageLite.K();
        }

        /* access modifiers changed from: protected */
        public Object readResolve() throws ObjectStreamException {
            try {
                Field declaredField = Class.forName(this.messageClassName).getDeclaredField("DEFAULT_INSTANCE");
                declaredField.setAccessible(true);
                return ((MessageLite) declaredField.get((Object) null)).Z().b(this.asBytes).Y();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Unable to find proto buffer class: " + this.messageClassName, e);
            } catch (NoSuchFieldException unused) {
                return a();
            } catch (SecurityException e2) {
                throw new RuntimeException("Unable to call DEFAULT_INSTANCE in " + this.messageClassName, e2);
            } catch (IllegalAccessException e3) {
                throw new RuntimeException("Unable to call parsePartialFrom", e3);
            } catch (InvalidProtocolBufferException e4) {
                throw new RuntimeException("Unable to understand proto buffer", e4);
            }
        }

        @Deprecated
        private Object a() throws ObjectStreamException {
            try {
                Field declaredField = Class.forName(this.messageClassName).getDeclaredField("defaultInstance");
                declaredField.setAccessible(true);
                return ((MessageLite) declaredField.get((Object) null)).Z().b(this.asBytes).Y();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Unable to find proto buffer class: " + this.messageClassName, e);
            } catch (NoSuchFieldException e2) {
                throw new RuntimeException("Unable to find defaultInstance in " + this.messageClassName, e2);
            } catch (SecurityException e3) {
                throw new RuntimeException("Unable to call defaultInstance in " + this.messageClassName, e3);
            } catch (IllegalAccessException e4) {
                throw new RuntimeException("Unable to call parsePartialFrom", e4);
            } catch (InvalidProtocolBufferException e5) {
                throw new RuntimeException("Unable to understand proto buffer", e5);
            }
        }
    }

    /* access modifiers changed from: private */
    public static <MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>, T> GeneratedExtension<MessageType, T> d(ExtensionLite<MessageType, T> extensionLite) {
        if (extensionLite.f()) {
            return (GeneratedExtension) extensionLite;
        }
        throw new IllegalArgumentException("Expected a lite extension.");
    }

    protected static final <T extends GeneratedMessageLite<T, ?>> boolean a(T t, boolean z) {
        return t.a(MethodToInvoke.IS_INITIALIZED, (Object) Boolean.valueOf(z)) != null;
    }

    protected static final <T extends GeneratedMessageLite<T, ?>> void a(T t) {
        t.a(MethodToInvoke.MAKE_IMMUTABLE);
    }

    protected static Internal.IntList S() {
        return IntArrayList.d();
    }

    protected static Internal.IntList a(Internal.IntList intList) {
        int size = intList.size();
        return intList.a(size == 0 ? 10 : size * 2);
    }

    protected static Internal.LongList T() {
        return LongArrayList.d();
    }

    protected static Internal.LongList a(Internal.LongList longList) {
        int size = longList.size();
        return longList.b(size == 0 ? 10 : size * 2);
    }

    protected static Internal.FloatList U() {
        return FloatArrayList.d();
    }

    protected static Internal.FloatList a(Internal.FloatList floatList) {
        int size = floatList.size();
        return floatList.a(size == 0 ? 10 : size * 2);
    }

    protected static Internal.DoubleList V() {
        return DoubleArrayList.d();
    }

    protected static Internal.DoubleList a(Internal.DoubleList doubleList) {
        int size = doubleList.size();
        return doubleList.a(size == 0 ? 10 : size * 2);
    }

    protected static Internal.BooleanList W() {
        return BooleanArrayList.d();
    }

    protected static Internal.BooleanList a(Internal.BooleanList booleanList) {
        int size = booleanList.size();
        return booleanList.a(size == 0 ? 10 : size * 2);
    }

    protected static <E> Internal.ProtobufList<E> X() {
        return ProtobufArrayList.d();
    }

    protected static <E> Internal.ProtobufList<E> a(Internal.ProtobufList<E> protobufList) {
        int size = protobufList.size();
        return protobufList.e(size == 0 ? 10 : size * 2);
    }

    protected static class DefaultInstanceBasedParser<T extends GeneratedMessageLite<T, ?>> extends AbstractParser<T> {

        /* renamed from: a  reason: collision with root package name */
        private T f11311a;

        public DefaultInstanceBasedParser(T t) {
            this.f11311a = t;
        }

        /* renamed from: c */
        public T d(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return GeneratedMessageLite.a(this.f11311a, codedInputStream, extensionRegistryLite);
        }
    }

    static <T extends GeneratedMessageLite<T, ?>> T a(T t, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        T t2 = (GeneratedMessageLite) t.a(MethodToInvoke.NEW_MUTABLE_INSTANCE);
        try {
            t2.a(MethodToInvoke.MERGE_FROM_STREAM, (Object) codedInputStream, (Object) extensionRegistryLite);
            t2.P();
            return t2;
        } catch (RuntimeException e) {
            if (e.getCause() instanceof InvalidProtocolBufferException) {
                throw ((InvalidProtocolBufferException) e.getCause());
            }
            throw e;
        }
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T a(T t, CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
        return a(t, codedInputStream, ExtensionRegistryLite.d());
    }

    private static <T extends GeneratedMessageLite<T, ?>> T b(T t) throws InvalidProtocolBufferException {
        if (t == null || t.h_()) {
            return t;
        }
        throw t.L().asInvalidProtocolBufferException().setUnfinishedMessage(t);
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T a(T t, ByteString byteString) throws InvalidProtocolBufferException {
        return b(a(t, byteString, ExtensionRegistryLite.d()));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T a(T t, ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return b(b(t, byteString, extensionRegistryLite));
    }

    private static <T extends GeneratedMessageLite<T, ?>> T b(T t, ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        T a2;
        try {
            CodedInputStream newCodedInput = byteString.newCodedInput();
            a2 = a(t, newCodedInput, extensionRegistryLite);
            newCodedInput.a(0);
            return a2;
        } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(a2);
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }

    private static <T extends GeneratedMessageLite<T, ?>> T b(T t, byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        T a2;
        try {
            CodedInputStream a3 = CodedInputStream.a(bArr);
            a2 = a(t, a3, extensionRegistryLite);
            a3.a(0);
            return a2;
        } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(a2);
        } catch (InvalidProtocolBufferException e2) {
            throw e2;
        }
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T a(T t, byte[] bArr) throws InvalidProtocolBufferException {
        return b(b(t, bArr, ExtensionRegistryLite.d()));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T a(T t, byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return b(b(t, bArr, extensionRegistryLite));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T a(T t, InputStream inputStream) throws InvalidProtocolBufferException {
        return b(a(t, CodedInputStream.a(inputStream), ExtensionRegistryLite.d()));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T a(T t, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return b(a(t, CodedInputStream.a(inputStream), extensionRegistryLite));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T b(T t, CodedInputStream codedInputStream) throws InvalidProtocolBufferException {
        return b(t, codedInputStream, ExtensionRegistryLite.d());
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T b(T t, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return b(a(t, codedInputStream, extensionRegistryLite));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T b(T t, InputStream inputStream) throws InvalidProtocolBufferException {
        return b(c(t, inputStream, ExtensionRegistryLite.d()));
    }

    protected static <T extends GeneratedMessageLite<T, ?>> T b(T t, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return b(c(t, inputStream, extensionRegistryLite));
    }

    private static <T extends GeneratedMessageLite<T, ?>> T c(T t, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        try {
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            CodedInputStream a2 = CodedInputStream.a((InputStream) new AbstractMessageLite.Builder.LimitedInputStream(inputStream, CodedInputStream.a(read, inputStream)));
            T a3 = a(t, a2, extensionRegistryLite);
            try {
                a2.a(0);
                return a3;
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(a3);
            }
        } catch (IOException e2) {
            throw new InvalidProtocolBufferException(e2.getMessage());
        }
    }

    static class EqualsVisitor implements Visitor {

        /* renamed from: a  reason: collision with root package name */
        static final EqualsVisitor f11312a = new EqualsVisitor();
        static final NotEqualsException b = new NotEqualsException();

        static final class NotEqualsException extends RuntimeException {
            NotEqualsException() {
            }
        }

        private EqualsVisitor() {
        }

        public boolean a(boolean z, boolean z2, boolean z3, boolean z4) {
            if (z == z3 && z2 == z4) {
                return z2;
            }
            throw b;
        }

        public int a(boolean z, int i, boolean z2, int i2) {
            if (z == z2 && i == i2) {
                return i;
            }
            throw b;
        }

        public double a(boolean z, double d, boolean z2, double d2) {
            if (z == z2 && d == d2) {
                return d;
            }
            throw b;
        }

        public float a(boolean z, float f, boolean z2, float f2) {
            if (z == z2 && f == f2) {
                return f;
            }
            throw b;
        }

        public long a(boolean z, long j, boolean z2, long j2) {
            if (z == z2 && j == j2) {
                return j;
            }
            throw b;
        }

        public String a(boolean z, String str, boolean z2, String str2) {
            if (z == z2 && str.equals(str2)) {
                return str;
            }
            throw b;
        }

        public ByteString a(boolean z, ByteString byteString, boolean z2, ByteString byteString2) {
            if (z == z2 && byteString.equals(byteString2)) {
                return byteString;
            }
            throw b;
        }

        public Object a(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw b;
        }

        public Object b(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw b;
        }

        public Object c(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw b;
        }

        public Object d(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw b;
        }

        public Object e(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw b;
        }

        public Object f(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw b;
        }

        public Object g(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw b;
        }

        public Object h(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw b;
        }

        public Object i(boolean z, Object obj, Object obj2) {
            if (z && ((GeneratedMessageLite) obj).a(this, (MessageLite) obj2)) {
                return obj;
            }
            throw b;
        }

        public void a(boolean z) {
            if (z) {
                throw b;
            }
        }

        public <T extends MessageLite> T a(T t, T t2) {
            if (t == null && t2 == null) {
                return null;
            }
            if (t == null || t2 == null) {
                throw b;
            }
            ((GeneratedMessageLite) t).a(this, (MessageLite) t2);
            return t;
        }

        public LazyFieldLite a(LazyFieldLite lazyFieldLite, LazyFieldLite lazyFieldLite2) {
            if (lazyFieldLite == null && lazyFieldLite2 == null) {
                return null;
            }
            if (lazyFieldLite == null || lazyFieldLite2 == null) {
                throw b;
            } else if (lazyFieldLite.equals(lazyFieldLite2)) {
                return lazyFieldLite;
            } else {
                throw b;
            }
        }

        public <T> Internal.ProtobufList<T> a(Internal.ProtobufList<T> protobufList, Internal.ProtobufList<T> protobufList2) {
            if (protobufList.equals(protobufList2)) {
                return protobufList;
            }
            throw b;
        }

        public Internal.BooleanList a(Internal.BooleanList booleanList, Internal.BooleanList booleanList2) {
            if (booleanList.equals(booleanList2)) {
                return booleanList;
            }
            throw b;
        }

        public Internal.IntList a(Internal.IntList intList, Internal.IntList intList2) {
            if (intList.equals(intList2)) {
                return intList;
            }
            throw b;
        }

        public Internal.DoubleList a(Internal.DoubleList doubleList, Internal.DoubleList doubleList2) {
            if (doubleList.equals(doubleList2)) {
                return doubleList;
            }
            throw b;
        }

        public Internal.FloatList a(Internal.FloatList floatList, Internal.FloatList floatList2) {
            if (floatList.equals(floatList2)) {
                return floatList;
            }
            throw b;
        }

        public Internal.LongList a(Internal.LongList longList, Internal.LongList longList2) {
            if (longList.equals(longList2)) {
                return longList;
            }
            throw b;
        }

        public FieldSet<ExtensionDescriptor> a(FieldSet<ExtensionDescriptor> fieldSet, FieldSet<ExtensionDescriptor> fieldSet2) {
            if (fieldSet.equals(fieldSet2)) {
                return fieldSet;
            }
            throw b;
        }

        public UnknownFieldSetLite a(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2) {
            if (unknownFieldSetLite.equals(unknownFieldSetLite2)) {
                return unknownFieldSetLite;
            }
            throw b;
        }

        public <K, V> MapFieldLite<K, V> a(MapFieldLite<K, V> mapFieldLite, MapFieldLite<K, V> mapFieldLite2) {
            if (mapFieldLite.equals(mapFieldLite2)) {
                return mapFieldLite;
            }
            throw b;
        }
    }

    private static class HashCodeVisitor implements Visitor {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f11317a;

        private HashCodeVisitor() {
            this.f11317a = 0;
        }

        public boolean a(boolean z, boolean z2, boolean z3, boolean z4) {
            this.f11317a = (this.f11317a * 53) + Internal.a(z2);
            return z2;
        }

        public int a(boolean z, int i, boolean z2, int i2) {
            this.f11317a = (this.f11317a * 53) + i;
            return i;
        }

        public double a(boolean z, double d, boolean z2, double d2) {
            this.f11317a = (this.f11317a * 53) + Internal.a(Double.doubleToLongBits(d));
            return d;
        }

        public float a(boolean z, float f, boolean z2, float f2) {
            this.f11317a = (this.f11317a * 53) + Float.floatToIntBits(f);
            return f;
        }

        public long a(boolean z, long j, boolean z2, long j2) {
            this.f11317a = (this.f11317a * 53) + Internal.a(j);
            return j;
        }

        public String a(boolean z, String str, boolean z2, String str2) {
            this.f11317a = (this.f11317a * 53) + str.hashCode();
            return str;
        }

        public ByteString a(boolean z, ByteString byteString, boolean z2, ByteString byteString2) {
            this.f11317a = (this.f11317a * 53) + byteString.hashCode();
            return byteString;
        }

        public Object a(boolean z, Object obj, Object obj2) {
            this.f11317a = (this.f11317a * 53) + Internal.a(((Boolean) obj).booleanValue());
            return obj;
        }

        public Object b(boolean z, Object obj, Object obj2) {
            this.f11317a = (this.f11317a * 53) + ((Integer) obj).intValue();
            return obj;
        }

        public Object c(boolean z, Object obj, Object obj2) {
            this.f11317a = (this.f11317a * 53) + Internal.a(Double.doubleToLongBits(((Double) obj).doubleValue()));
            return obj;
        }

        public Object d(boolean z, Object obj, Object obj2) {
            this.f11317a = (this.f11317a * 53) + Float.floatToIntBits(((Float) obj).floatValue());
            return obj;
        }

        public Object e(boolean z, Object obj, Object obj2) {
            this.f11317a = (this.f11317a * 53) + Internal.a(((Long) obj).longValue());
            return obj;
        }

        public Object f(boolean z, Object obj, Object obj2) {
            this.f11317a = (this.f11317a * 53) + obj.hashCode();
            return obj;
        }

        public Object g(boolean z, Object obj, Object obj2) {
            this.f11317a = (this.f11317a * 53) + obj.hashCode();
            return obj;
        }

        public Object h(boolean z, Object obj, Object obj2) {
            this.f11317a = (this.f11317a * 53) + obj.hashCode();
            return obj;
        }

        public Object i(boolean z, Object obj, Object obj2) {
            return a((MessageLite) obj, (MessageLite) obj2);
        }

        public void a(boolean z) {
            if (z) {
                throw new IllegalStateException();
            }
        }

        public <T extends MessageLite> T a(T t, T t2) {
            int i;
            if (t != null) {
                i = t instanceof GeneratedMessageLite ? ((GeneratedMessageLite) t).a(this) : t.hashCode();
            } else {
                i = 37;
            }
            this.f11317a = (this.f11317a * 53) + i;
            return t;
        }

        public LazyFieldLite a(LazyFieldLite lazyFieldLite, LazyFieldLite lazyFieldLite2) {
            this.f11317a = (this.f11317a * 53) + (lazyFieldLite != null ? lazyFieldLite.hashCode() : 37);
            return lazyFieldLite;
        }

        public <T> Internal.ProtobufList<T> a(Internal.ProtobufList<T> protobufList, Internal.ProtobufList<T> protobufList2) {
            this.f11317a = (this.f11317a * 53) + protobufList.hashCode();
            return protobufList;
        }

        public Internal.BooleanList a(Internal.BooleanList booleanList, Internal.BooleanList booleanList2) {
            this.f11317a = (this.f11317a * 53) + booleanList.hashCode();
            return booleanList;
        }

        public Internal.IntList a(Internal.IntList intList, Internal.IntList intList2) {
            this.f11317a = (this.f11317a * 53) + intList.hashCode();
            return intList;
        }

        public Internal.DoubleList a(Internal.DoubleList doubleList, Internal.DoubleList doubleList2) {
            this.f11317a = (this.f11317a * 53) + doubleList.hashCode();
            return doubleList;
        }

        public Internal.FloatList a(Internal.FloatList floatList, Internal.FloatList floatList2) {
            this.f11317a = (this.f11317a * 53) + floatList.hashCode();
            return floatList;
        }

        public Internal.LongList a(Internal.LongList longList, Internal.LongList longList2) {
            this.f11317a = (this.f11317a * 53) + longList.hashCode();
            return longList;
        }

        public FieldSet<ExtensionDescriptor> a(FieldSet<ExtensionDescriptor> fieldSet, FieldSet<ExtensionDescriptor> fieldSet2) {
            this.f11317a = (this.f11317a * 53) + fieldSet.hashCode();
            return fieldSet;
        }

        public UnknownFieldSetLite a(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2) {
            this.f11317a = (this.f11317a * 53) + unknownFieldSetLite.hashCode();
            return unknownFieldSetLite;
        }

        public <K, V> MapFieldLite<K, V> a(MapFieldLite<K, V> mapFieldLite, MapFieldLite<K, V> mapFieldLite2) {
            this.f11317a = (this.f11317a * 53) + mapFieldLite.hashCode();
            return mapFieldLite;
        }
    }

    protected static class MergeFromVisitor implements Visitor {

        /* renamed from: a  reason: collision with root package name */
        public static final MergeFromVisitor f11318a = new MergeFromVisitor();

        public double a(boolean z, double d, boolean z2, double d2) {
            return z2 ? d2 : d;
        }

        public float a(boolean z, float f, boolean z2, float f2) {
            return z2 ? f2 : f;
        }

        public int a(boolean z, int i, boolean z2, int i2) {
            return z2 ? i2 : i;
        }

        public long a(boolean z, long j, boolean z2, long j2) {
            return z2 ? j2 : j;
        }

        public ByteString a(boolean z, ByteString byteString, boolean z2, ByteString byteString2) {
            return z2 ? byteString2 : byteString;
        }

        public Object a(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        public String a(boolean z, String str, boolean z2, String str2) {
            return z2 ? str2 : str;
        }

        public void a(boolean z) {
        }

        public boolean a(boolean z, boolean z2, boolean z3, boolean z4) {
            return z3 ? z4 : z2;
        }

        public Object b(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        public Object c(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        public Object d(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        public Object e(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        public Object f(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        public Object g(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        private MergeFromVisitor() {
        }

        public Object h(boolean z, Object obj, Object obj2) {
            LazyFieldLite lazyFieldLite = z ? (LazyFieldLite) obj : new LazyFieldLite();
            lazyFieldLite.b((LazyFieldLite) obj2);
            return lazyFieldLite;
        }

        public Object i(boolean z, Object obj, Object obj2) {
            return z ? a((MessageLite) obj, (MessageLite) obj2) : obj2;
        }

        public <T extends MessageLite> T a(T t, T t2) {
            if (t == null || t2 == null) {
                return t != null ? t : t2;
            }
            return t.Y().c((MessageLite) t2).Z();
        }

        public LazyFieldLite a(LazyFieldLite lazyFieldLite, LazyFieldLite lazyFieldLite2) {
            if (lazyFieldLite2 != null) {
                if (lazyFieldLite == null) {
                    lazyFieldLite = new LazyFieldLite();
                }
                lazyFieldLite.b(lazyFieldLite2);
            }
            return lazyFieldLite;
        }

        public <T> Internal.ProtobufList<T> a(Internal.ProtobufList<T> protobufList, Internal.ProtobufList<T> protobufList2) {
            int size = protobufList.size();
            int size2 = protobufList2.size();
            if (size > 0 && size2 > 0) {
                if (!protobufList.a()) {
                    protobufList = protobufList.e(size2 + size);
                }
                protobufList.addAll(protobufList2);
            }
            return size > 0 ? protobufList : protobufList2;
        }

        public Internal.BooleanList a(Internal.BooleanList booleanList, Internal.BooleanList booleanList2) {
            int size = booleanList.size();
            int size2 = booleanList2.size();
            if (size > 0 && size2 > 0) {
                if (!booleanList.a()) {
                    booleanList = booleanList.a(size2 + size);
                }
                booleanList.addAll(booleanList2);
            }
            return size > 0 ? booleanList : booleanList2;
        }

        public Internal.IntList a(Internal.IntList intList, Internal.IntList intList2) {
            int size = intList.size();
            int size2 = intList2.size();
            if (size > 0 && size2 > 0) {
                if (!intList.a()) {
                    intList = intList.a(size2 + size);
                }
                intList.addAll(intList2);
            }
            return size > 0 ? intList : intList2;
        }

        public Internal.DoubleList a(Internal.DoubleList doubleList, Internal.DoubleList doubleList2) {
            int size = doubleList.size();
            int size2 = doubleList2.size();
            if (size > 0 && size2 > 0) {
                if (!doubleList.a()) {
                    doubleList = doubleList.a(size2 + size);
                }
                doubleList.addAll(doubleList2);
            }
            return size > 0 ? doubleList : doubleList2;
        }

        public Internal.FloatList a(Internal.FloatList floatList, Internal.FloatList floatList2) {
            int size = floatList.size();
            int size2 = floatList2.size();
            if (size > 0 && size2 > 0) {
                if (!floatList.a()) {
                    floatList = floatList.a(size2 + size);
                }
                floatList.addAll(floatList2);
            }
            return size > 0 ? floatList : floatList2;
        }

        public Internal.LongList a(Internal.LongList longList, Internal.LongList longList2) {
            int size = longList.size();
            int size2 = longList2.size();
            if (size > 0 && size2 > 0) {
                if (!longList.a()) {
                    longList = longList.b(size2 + size);
                }
                longList.addAll(longList2);
            }
            return size > 0 ? longList : longList2;
        }

        public FieldSet<ExtensionDescriptor> a(FieldSet<ExtensionDescriptor> fieldSet, FieldSet<ExtensionDescriptor> fieldSet2) {
            if (fieldSet.d()) {
                fieldSet = fieldSet.clone();
            }
            fieldSet.a(fieldSet2);
            return fieldSet;
        }

        public UnknownFieldSetLite a(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2) {
            return unknownFieldSetLite2 == UnknownFieldSetLite.a() ? unknownFieldSetLite : UnknownFieldSetLite.a(unknownFieldSetLite, unknownFieldSetLite2);
        }

        public <K, V> MapFieldLite<K, V> a(MapFieldLite<K, V> mapFieldLite, MapFieldLite<K, V> mapFieldLite2) {
            if (!mapFieldLite2.isEmpty()) {
                if (!mapFieldLite.isMutable()) {
                    mapFieldLite = mapFieldLite.mutableCopy();
                }
                mapFieldLite.mergeFrom(mapFieldLite2);
            }
            return mapFieldLite;
        }
    }
}
