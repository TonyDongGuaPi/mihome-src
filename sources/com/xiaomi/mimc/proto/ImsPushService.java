package com.xiaomi.mimc.proto;

import com.xiaomi.mimc.proto.ControlMessage;
import com.xiaomi.mimc.protobuf.ByteString;
import com.xiaomi.mimc.protobuf.CodedInputStream;
import com.xiaomi.mimc.protobuf.CodedOutputStream;
import com.xiaomi.mimc.protobuf.ExtensionRegistryLite;
import com.xiaomi.mimc.protobuf.GeneratedMessageLite;
import com.xiaomi.mimc.protobuf.Internal;
import com.xiaomi.mimc.protobuf.InvalidProtocolBufferException;
import com.xiaomi.mimc.protobuf.MessageLite;
import com.xiaomi.mimc.protobuf.MessageLiteOrBuilder;
import com.xiaomi.mimc.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ImsPushService {

    public interface ClientHeaderOrBuilder extends MessageLiteOrBuilder {
        boolean A();

        String B();

        ByteString C();

        boolean a();

        int b();

        boolean c();

        long d();

        boolean e();

        String f();

        ByteString g();

        boolean h();

        String i();

        ByteString j();

        boolean l();

        String m();

        ByteString n();

        boolean o();

        String p();

        ByteString q();

        boolean r();

        String s();

        ByteString t();

        boolean u();

        ClientHeader.MSG_DIR_FLAG v();

        boolean w();

        int x();

        boolean y();

        int z();
    }

    public interface XMMsgBindOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        String b();

        ByteString c();

        boolean d();

        String e();

        ByteString f();

        boolean g();

        String h();

        ByteString i();

        boolean j();

        String l();

        ByteString m();

        boolean n();

        String o();

        ByteString p();

        boolean q();

        String r();

        ByteString s();
    }

    public interface XMMsgBindRespOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        boolean b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        String g();

        ByteString h();

        boolean i();

        String j();

        ByteString l();
    }

    public interface XMMsgConnOrBuilder extends MessageLiteOrBuilder {
        int A();

        boolean B();

        ByteString C();

        boolean D();

        ByteString E();

        boolean a();

        int b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        String g();

        ByteString h();

        boolean i();

        String j();

        ByteString l();

        boolean m();

        int n();

        boolean o();

        String p();

        ByteString q();

        boolean r();

        String s();

        ByteString t();

        boolean u();

        String v();

        ByteString w();

        boolean x();

        ControlMessage.PushServiceConfigMsg y();

        boolean z();
    }

    public interface XMMsgConnRespOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        String b();

        ByteString c();

        boolean d();

        String e();

        ByteString f();

        boolean g();

        ControlMessage.PushServiceConfigMsg h();

        boolean i();

        ByteString j();
    }

    public interface XMMsgNotifyOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        int b();

        boolean c();

        String d();

        ByteString e();
    }

    public interface XMMsgPingOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        ByteString b();

        boolean c();

        ControlMessage.PushServiceConfigMsg d();
    }

    public static void a(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ImsPushService() {
    }

    public static final class ClientHeader extends GeneratedMessageLite<ClientHeader, Builder> implements ClientHeaderOrBuilder {
        /* access modifiers changed from: private */
        public static final ClientHeader A = new ClientHeader();
        private static volatile Parser<ClientHeader> B = null;

        /* renamed from: a  reason: collision with root package name */
        public static final int f11215a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        public static final int h = 8;
        public static final int i = 9;
        public static final int j = 10;
        public static final int k = 11;
        private int l;
        private int p;
        private long q;
        private String r = "";
        private String s = "";
        private String t = "";
        private String u = "";
        private String v = "";
        private int w = 1;
        private int x;
        private int y;
        private String z = "";

        private ClientHeader() {
        }

        public enum MSG_DIR_FLAG implements Internal.EnumLite {
            CS_ONEWAY(1),
            CS_REQ(2),
            CS_RESP(3),
            SC_ONEWAY(4),
            SC_REQ(5),
            SC_RESP(6);
            
            public static final int CS_ONEWAY_VALUE = 1;
            public static final int CS_REQ_VALUE = 2;
            public static final int CS_RESP_VALUE = 3;
            public static final int SC_ONEWAY_VALUE = 4;
            public static final int SC_REQ_VALUE = 5;
            public static final int SC_RESP_VALUE = 6;
            private static final Internal.EnumLiteMap<MSG_DIR_FLAG> internalValueMap = null;
            private final int value;

            static {
                internalValueMap = new Internal.EnumLiteMap<MSG_DIR_FLAG>() {
                    /* renamed from: a */
                    public MSG_DIR_FLAG b(int i) {
                        return MSG_DIR_FLAG.forNumber(i);
                    }
                };
            }

            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static MSG_DIR_FLAG valueOf(int i) {
                return forNumber(i);
            }

            public static MSG_DIR_FLAG forNumber(int i) {
                switch (i) {
                    case 1:
                        return CS_ONEWAY;
                    case 2:
                        return CS_REQ;
                    case 3:
                        return CS_RESP;
                    case 4:
                        return SC_ONEWAY;
                    case 5:
                        return SC_REQ;
                    case 6:
                        return SC_RESP;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<MSG_DIR_FLAG> internalGetValueMap() {
                return internalValueMap;
            }

            private MSG_DIR_FLAG(int i) {
                this.value = i;
            }
        }

        public boolean a() {
            return (this.l & 1) == 1;
        }

        public int b() {
            return this.p;
        }

        /* access modifiers changed from: private */
        public void a(int i2) {
            this.l |= 1;
            this.p = i2;
        }

        /* access modifiers changed from: private */
        public void H() {
            this.l &= -2;
            this.p = 0;
        }

        public boolean c() {
            return (this.l & 2) == 2;
        }

        public long d() {
            return this.q;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.l |= 2;
            this.q = j2;
        }

        /* access modifiers changed from: private */
        public void I() {
            this.l &= -3;
            this.q = 0;
        }

        public boolean e() {
            return (this.l & 4) == 4;
        }

        public String f() {
            return this.r;
        }

        public ByteString g() {
            return ByteString.copyFromUtf8(this.r);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.l |= 4;
                this.r = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ab() {
            this.l &= -5;
            this.r = E().f();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.l |= 4;
                this.r = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean h() {
            return (this.l & 8) == 8;
        }

        public String i() {
            return this.s;
        }

        public ByteString j() {
            return ByteString.copyFromUtf8(this.s);
        }

        /* access modifiers changed from: private */
        public void b(String str) {
            if (str != null) {
                this.l |= 8;
                this.s = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ac() {
            this.l &= -9;
            this.s = E().i();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.l |= 8;
                this.s = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean l() {
            return (this.l & 16) == 16;
        }

        public String m() {
            return this.t;
        }

        public ByteString n() {
            return ByteString.copyFromUtf8(this.t);
        }

        /* access modifiers changed from: private */
        public void c(String str) {
            if (str != null) {
                this.l |= 16;
                this.t = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ad() {
            this.l &= -17;
            this.t = E().m();
        }

        /* access modifiers changed from: private */
        public void e(ByteString byteString) {
            if (byteString != null) {
                this.l |= 16;
                this.t = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean o() {
            return (this.l & 32) == 32;
        }

        public String p() {
            return this.u;
        }

        public ByteString q() {
            return ByteString.copyFromUtf8(this.u);
        }

        /* access modifiers changed from: private */
        public void d(String str) {
            if (str != null) {
                this.l |= 32;
                this.u = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ae() {
            this.l &= -33;
            this.u = E().p();
        }

        /* access modifiers changed from: private */
        public void f(ByteString byteString) {
            if (byteString != null) {
                this.l |= 32;
                this.u = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean r() {
            return (this.l & 64) == 64;
        }

        public String s() {
            return this.v;
        }

        public ByteString t() {
            return ByteString.copyFromUtf8(this.v);
        }

        /* access modifiers changed from: private */
        public void e(String str) {
            if (str != null) {
                this.l |= 64;
                this.v = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void af() {
            this.l &= -65;
            this.v = E().s();
        }

        /* access modifiers changed from: private */
        public void g(ByteString byteString) {
            if (byteString != null) {
                this.l |= 64;
                this.v = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean u() {
            return (this.l & 128) == 128;
        }

        public MSG_DIR_FLAG v() {
            MSG_DIR_FLAG forNumber = MSG_DIR_FLAG.forNumber(this.w);
            return forNumber == null ? MSG_DIR_FLAG.CS_ONEWAY : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(MSG_DIR_FLAG msg_dir_flag) {
            if (msg_dir_flag != null) {
                this.l |= 128;
                this.w = msg_dir_flag.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ag() {
            this.l &= -129;
            this.w = 1;
        }

        public boolean w() {
            return (this.l & 256) == 256;
        }

        public int x() {
            return this.x;
        }

        /* access modifiers changed from: private */
        public void b(int i2) {
            this.l |= 256;
            this.x = i2;
        }

        /* access modifiers changed from: private */
        public void ah() {
            this.l &= -257;
            this.x = 0;
        }

        public boolean y() {
            return (this.l & 512) == 512;
        }

        public int z() {
            return this.y;
        }

        /* access modifiers changed from: private */
        public void c(int i2) {
            this.l |= 512;
            this.y = i2;
        }

        /* access modifiers changed from: private */
        public void ai() {
            this.l &= -513;
            this.y = 0;
        }

        public boolean A() {
            return (this.l & 1024) == 1024;
        }

        public String B() {
            return this.z;
        }

        public ByteString C() {
            return ByteString.copyFromUtf8(this.z);
        }

        /* access modifiers changed from: private */
        public void f(String str) {
            if (str != null) {
                this.l |= 1024;
                this.z = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void aj() {
            this.l &= -1025;
            this.z = E().B();
        }

        /* access modifiers changed from: private */
        public void h(ByteString byteString) {
            if (byteString != null) {
                this.l |= 1024;
                this.z = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.l & 1) == 1) {
                codedOutputStream.b(1, this.p);
            }
            if ((this.l & 2) == 2) {
                codedOutputStream.a(2, this.q);
            }
            if ((this.l & 4) == 4) {
                codedOutputStream.a(3, f());
            }
            if ((this.l & 8) == 8) {
                codedOutputStream.a(4, i());
            }
            if ((this.l & 16) == 16) {
                codedOutputStream.a(5, m());
            }
            if ((this.l & 32) == 32) {
                codedOutputStream.a(6, p());
            }
            if ((this.l & 64) == 64) {
                codedOutputStream.a(7, s());
            }
            if ((this.l & 128) == 128) {
                codedOutputStream.g(8, this.w);
            }
            if ((this.l & 256) == 256) {
                codedOutputStream.b(9, this.x);
            }
            if ((this.l & 512) == 512) {
                codedOutputStream.b(10, this.y);
            }
            if ((this.l & 1024) == 1024) {
                codedOutputStream.a(11, B());
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int i3 = 0;
            if ((this.l & 1) == 1) {
                i3 = 0 + CodedOutputStream.h(1, this.p);
            }
            if ((this.l & 2) == 2) {
                i3 += CodedOutputStream.f(2, this.q);
            }
            if ((this.l & 4) == 4) {
                i3 += CodedOutputStream.b(3, f());
            }
            if ((this.l & 8) == 8) {
                i3 += CodedOutputStream.b(4, i());
            }
            if ((this.l & 16) == 16) {
                i3 += CodedOutputStream.b(5, m());
            }
            if ((this.l & 32) == 32) {
                i3 += CodedOutputStream.b(6, p());
            }
            if ((this.l & 64) == 64) {
                i3 += CodedOutputStream.b(7, s());
            }
            if ((this.l & 128) == 128) {
                i3 += CodedOutputStream.m(8, this.w);
            }
            if ((this.l & 256) == 256) {
                i3 += CodedOutputStream.h(9, this.x);
            }
            if ((this.l & 512) == 512) {
                i3 += CodedOutputStream.h(10, this.y);
            }
            if ((this.l & 1024) == 1024) {
                i3 += CodedOutputStream.b(11, B());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static ClientHeader a(ByteString byteString) throws InvalidProtocolBufferException {
            return (ClientHeader) GeneratedMessageLite.a(A, byteString);
        }

        public static ClientHeader a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ClientHeader) GeneratedMessageLite.a(A, byteString, extensionRegistryLite);
        }

        public static ClientHeader a(byte[] bArr) throws InvalidProtocolBufferException {
            return (ClientHeader) GeneratedMessageLite.a(A, bArr);
        }

        public static ClientHeader a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ClientHeader) GeneratedMessageLite.a(A, bArr, extensionRegistryLite);
        }

        public static ClientHeader a(InputStream inputStream) throws IOException {
            return (ClientHeader) GeneratedMessageLite.a(A, inputStream);
        }

        public static ClientHeader a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ClientHeader) GeneratedMessageLite.a(A, inputStream, extensionRegistryLite);
        }

        public static ClientHeader b(InputStream inputStream) throws IOException {
            return (ClientHeader) b(A, inputStream);
        }

        public static ClientHeader b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ClientHeader) b(A, inputStream, extensionRegistryLite);
        }

        public static ClientHeader a(CodedInputStream codedInputStream) throws IOException {
            return (ClientHeader) GeneratedMessageLite.b(A, codedInputStream);
        }

        public static ClientHeader a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ClientHeader) GeneratedMessageLite.b(A, codedInputStream, extensionRegistryLite);
        }

        public static Builder D() {
            return (Builder) A.Y();
        }

        public static Builder a(ClientHeader clientHeader) {
            return (Builder) ((Builder) A.Y()).b(clientHeader);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ClientHeader, Builder> implements ClientHeaderOrBuilder {
            private Builder() {
                super(ClientHeader.A);
            }

            public boolean a() {
                return ((ClientHeader) this.f11310a).a();
            }

            public int b() {
                return ((ClientHeader) this.f11310a).b();
            }

            public Builder a(int i) {
                S();
                ((ClientHeader) this.f11310a).a(i);
                return this;
            }

            public Builder k() {
                S();
                ((ClientHeader) this.f11310a).H();
                return this;
            }

            public boolean c() {
                return ((ClientHeader) this.f11310a).c();
            }

            public long d() {
                return ((ClientHeader) this.f11310a).d();
            }

            public Builder a(long j) {
                S();
                ((ClientHeader) this.f11310a).a(j);
                return this;
            }

            public Builder D() {
                S();
                ((ClientHeader) this.f11310a).I();
                return this;
            }

            public boolean e() {
                return ((ClientHeader) this.f11310a).e();
            }

            public String f() {
                return ((ClientHeader) this.f11310a).f();
            }

            public ByteString g() {
                return ((ClientHeader) this.f11310a).g();
            }

            public Builder a(String str) {
                S();
                ((ClientHeader) this.f11310a).a(str);
                return this;
            }

            public Builder E() {
                S();
                ((ClientHeader) this.f11310a).ab();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((ClientHeader) this.f11310a).c(byteString);
                return this;
            }

            public boolean h() {
                return ((ClientHeader) this.f11310a).h();
            }

            public String i() {
                return ((ClientHeader) this.f11310a).i();
            }

            public ByteString j() {
                return ((ClientHeader) this.f11310a).j();
            }

            public Builder b(String str) {
                S();
                ((ClientHeader) this.f11310a).b(str);
                return this;
            }

            public Builder F() {
                S();
                ((ClientHeader) this.f11310a).ac();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((ClientHeader) this.f11310a).d(byteString);
                return this;
            }

            public boolean l() {
                return ((ClientHeader) this.f11310a).l();
            }

            public String m() {
                return ((ClientHeader) this.f11310a).m();
            }

            public ByteString n() {
                return ((ClientHeader) this.f11310a).n();
            }

            public Builder c(String str) {
                S();
                ((ClientHeader) this.f11310a).c(str);
                return this;
            }

            public Builder G() {
                S();
                ((ClientHeader) this.f11310a).ad();
                return this;
            }

            public Builder c(ByteString byteString) {
                S();
                ((ClientHeader) this.f11310a).e(byteString);
                return this;
            }

            public boolean o() {
                return ((ClientHeader) this.f11310a).o();
            }

            public String p() {
                return ((ClientHeader) this.f11310a).p();
            }

            public ByteString q() {
                return ((ClientHeader) this.f11310a).q();
            }

            public Builder d(String str) {
                S();
                ((ClientHeader) this.f11310a).d(str);
                return this;
            }

            public Builder H() {
                S();
                ((ClientHeader) this.f11310a).ae();
                return this;
            }

            public Builder d(ByteString byteString) {
                S();
                ((ClientHeader) this.f11310a).f(byteString);
                return this;
            }

            public boolean r() {
                return ((ClientHeader) this.f11310a).r();
            }

            public String s() {
                return ((ClientHeader) this.f11310a).s();
            }

            public ByteString t() {
                return ((ClientHeader) this.f11310a).t();
            }

            public Builder e(String str) {
                S();
                ((ClientHeader) this.f11310a).e(str);
                return this;
            }

            public Builder I() {
                S();
                ((ClientHeader) this.f11310a).af();
                return this;
            }

            public Builder e(ByteString byteString) {
                S();
                ((ClientHeader) this.f11310a).g(byteString);
                return this;
            }

            public boolean u() {
                return ((ClientHeader) this.f11310a).u();
            }

            public MSG_DIR_FLAG v() {
                return ((ClientHeader) this.f11310a).v();
            }

            public Builder a(MSG_DIR_FLAG msg_dir_flag) {
                S();
                ((ClientHeader) this.f11310a).a(msg_dir_flag);
                return this;
            }

            public Builder J() {
                S();
                ((ClientHeader) this.f11310a).ag();
                return this;
            }

            public boolean w() {
                return ((ClientHeader) this.f11310a).w();
            }

            public int x() {
                return ((ClientHeader) this.f11310a).x();
            }

            public Builder b(int i) {
                S();
                ((ClientHeader) this.f11310a).b(i);
                return this;
            }

            public Builder K() {
                S();
                ((ClientHeader) this.f11310a).ah();
                return this;
            }

            public boolean y() {
                return ((ClientHeader) this.f11310a).y();
            }

            public int z() {
                return ((ClientHeader) this.f11310a).z();
            }

            public Builder c(int i) {
                S();
                ((ClientHeader) this.f11310a).c(i);
                return this;
            }

            public Builder L() {
                S();
                ((ClientHeader) this.f11310a).ai();
                return this;
            }

            public boolean A() {
                return ((ClientHeader) this.f11310a).A();
            }

            public String B() {
                return ((ClientHeader) this.f11310a).B();
            }

            public ByteString C() {
                return ((ClientHeader) this.f11310a).C();
            }

            public Builder f(String str) {
                S();
                ((ClientHeader) this.f11310a).f(str);
                return this;
            }

            public Builder M() {
                S();
                ((ClientHeader) this.f11310a).aj();
                return this;
            }

            public Builder f(ByteString byteString) {
                S();
                ((ClientHeader) this.f11310a).h(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new ClientHeader();
                case IS_INITIALIZED:
                    return A;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ClientHeader clientHeader = (ClientHeader) obj2;
                    this.p = visitor.a(a(), this.p, clientHeader.a(), clientHeader.p);
                    this.q = visitor.a(c(), this.q, clientHeader.c(), clientHeader.q);
                    this.r = visitor.a(e(), this.r, clientHeader.e(), clientHeader.r);
                    this.s = visitor.a(h(), this.s, clientHeader.h(), clientHeader.s);
                    this.t = visitor.a(l(), this.t, clientHeader.l(), clientHeader.t);
                    this.u = visitor.a(o(), this.u, clientHeader.o(), clientHeader.u);
                    this.v = visitor.a(r(), this.v, clientHeader.r(), clientHeader.v);
                    this.w = visitor.a(u(), this.w, clientHeader.u(), clientHeader.w);
                    this.x = visitor.a(w(), this.x, clientHeader.w(), clientHeader.x);
                    this.y = visitor.a(y(), this.y, clientHeader.y(), clientHeader.y);
                    this.z = visitor.a(A(), this.z, clientHeader.A(), clientHeader.z);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.l |= clientHeader.l;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z2 = false;
                    while (!z2) {
                        try {
                            int a2 = codedInputStream.a();
                            switch (a2) {
                                case 0:
                                    z2 = true;
                                    break;
                                case 8:
                                    this.l |= 1;
                                    this.p = codedInputStream.h();
                                    break;
                                case 16:
                                    this.l |= 2;
                                    this.q = codedInputStream.g();
                                    break;
                                case 26:
                                    String l2 = codedInputStream.l();
                                    this.l |= 4;
                                    this.r = l2;
                                    break;
                                case 34:
                                    String l3 = codedInputStream.l();
                                    this.l = 8 | this.l;
                                    this.s = l3;
                                    break;
                                case 42:
                                    String l4 = codedInputStream.l();
                                    this.l |= 16;
                                    this.t = l4;
                                    break;
                                case 50:
                                    String l5 = codedInputStream.l();
                                    this.l |= 32;
                                    this.u = l5;
                                    break;
                                case 58:
                                    String l6 = codedInputStream.l();
                                    this.l |= 64;
                                    this.v = l6;
                                    break;
                                case 64:
                                    int r2 = codedInputStream.r();
                                    if (MSG_DIR_FLAG.forNumber(r2) != null) {
                                        this.l |= 128;
                                        this.w = r2;
                                        break;
                                    } else {
                                        super.a(8, r2);
                                        break;
                                    }
                                case 72:
                                    this.l |= 256;
                                    this.x = codedInputStream.h();
                                    break;
                                case 80:
                                    this.l |= 512;
                                    this.y = codedInputStream.h();
                                    break;
                                case 90:
                                    String l7 = codedInputStream.l();
                                    this.l |= 1024;
                                    this.z = l7;
                                    break;
                                default:
                                    if (a(a2, codedInputStream)) {
                                        break;
                                    }
                                    z2 = true;
                                    break;
                            }
                        } catch (InvalidProtocolBufferException e2) {
                            throw new RuntimeException(e2.setUnfinishedMessage(this));
                        } catch (IOException e3) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e3.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (B == null) {
                        synchronized (ClientHeader.class) {
                            if (B == null) {
                                B = new GeneratedMessageLite.DefaultInstanceBasedParser(A);
                            }
                        }
                    }
                    return B;
                default:
                    throw new UnsupportedOperationException();
            }
            return A;
        }

        static {
            A.P();
        }

        public static ClientHeader E() {
            return A;
        }

        public static Parser<ClientHeader> F() {
            return A.M();
        }
    }

    public static final class XMMsgConn extends GeneratedMessageLite<XMMsgConn, Builder> implements XMMsgConnOrBuilder {
        /* access modifiers changed from: private */
        public static final XMMsgConn C = new XMMsgConn();
        private static volatile Parser<XMMsgConn> D = null;

        /* renamed from: a  reason: collision with root package name */
        public static final int f11218a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        public static final int h = 8;
        public static final int i = 9;
        public static final int j = 10;
        public static final int k = 11;
        public static final int l = 12;
        private ByteString A = ByteString.EMPTY;
        private ByteString B = ByteString.EMPTY;
        private int p;
        private int q;
        private String r = "";
        private String s = "";
        private String t = "";
        private int u;
        private String v = "";
        private String w = "";
        private String x = "";
        private ControlMessage.PushServiceConfigMsg y;
        private int z;

        private XMMsgConn() {
        }

        public boolean a() {
            return (this.p & 1) == 1;
        }

        public int b() {
            return this.q;
        }

        /* access modifiers changed from: private */
        public void a(int i2) {
            this.p |= 1;
            this.q = i2;
        }

        /* access modifiers changed from: private */
        public void ab() {
            this.p &= -2;
            this.q = 0;
        }

        public boolean c() {
            return (this.p & 2) == 2;
        }

        public String d() {
            return this.r;
        }

        public ByteString e() {
            return ByteString.copyFromUtf8(this.r);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.p |= 2;
                this.r = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ac() {
            this.p &= -3;
            this.r = G().d();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.p |= 2;
                this.r = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean f() {
            return (this.p & 4) == 4;
        }

        public String g() {
            return this.s;
        }

        public ByteString h() {
            return ByteString.copyFromUtf8(this.s);
        }

        /* access modifiers changed from: private */
        public void b(String str) {
            if (str != null) {
                this.p |= 4;
                this.s = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ad() {
            this.p &= -5;
            this.s = G().g();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.p |= 4;
                this.s = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean i() {
            return (this.p & 8) == 8;
        }

        public String j() {
            return this.t;
        }

        public ByteString l() {
            return ByteString.copyFromUtf8(this.t);
        }

        /* access modifiers changed from: private */
        public void c(String str) {
            if (str != null) {
                this.p |= 8;
                this.t = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ae() {
            this.p &= -9;
            this.t = G().j();
        }

        /* access modifiers changed from: private */
        public void e(ByteString byteString) {
            if (byteString != null) {
                this.p |= 8;
                this.t = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean m() {
            return (this.p & 16) == 16;
        }

        public int n() {
            return this.u;
        }

        /* access modifiers changed from: private */
        public void b(int i2) {
            this.p |= 16;
            this.u = i2;
        }

        /* access modifiers changed from: private */
        public void af() {
            this.p &= -17;
            this.u = 0;
        }

        public boolean o() {
            return (this.p & 32) == 32;
        }

        public String p() {
            return this.v;
        }

        public ByteString q() {
            return ByteString.copyFromUtf8(this.v);
        }

        /* access modifiers changed from: private */
        public void d(String str) {
            if (str != null) {
                this.p |= 32;
                this.v = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ag() {
            this.p &= -33;
            this.v = G().p();
        }

        /* access modifiers changed from: private */
        public void f(ByteString byteString) {
            if (byteString != null) {
                this.p |= 32;
                this.v = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean r() {
            return (this.p & 64) == 64;
        }

        public String s() {
            return this.w;
        }

        public ByteString t() {
            return ByteString.copyFromUtf8(this.w);
        }

        /* access modifiers changed from: private */
        public void e(String str) {
            if (str != null) {
                this.p |= 64;
                this.w = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ah() {
            this.p &= -65;
            this.w = G().s();
        }

        /* access modifiers changed from: private */
        public void g(ByteString byteString) {
            if (byteString != null) {
                this.p |= 64;
                this.w = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean u() {
            return (this.p & 128) == 128;
        }

        public String v() {
            return this.x;
        }

        public ByteString w() {
            return ByteString.copyFromUtf8(this.x);
        }

        /* access modifiers changed from: private */
        public void f(String str) {
            if (str != null) {
                this.p |= 128;
                this.x = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ai() {
            this.p &= -129;
            this.x = G().v();
        }

        /* access modifiers changed from: private */
        public void h(ByteString byteString) {
            if (byteString != null) {
                this.p |= 128;
                this.x = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean x() {
            return (this.p & 256) == 256;
        }

        public ControlMessage.PushServiceConfigMsg y() {
            return this.y == null ? ControlMessage.PushServiceConfigMsg.m() : this.y;
        }

        /* access modifiers changed from: private */
        public void a(ControlMessage.PushServiceConfigMsg pushServiceConfigMsg) {
            if (pushServiceConfigMsg != null) {
                this.y = pushServiceConfigMsg;
                this.p |= 256;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(ControlMessage.PushServiceConfigMsg.Builder builder) {
            this.y = (ControlMessage.PushServiceConfigMsg) builder.Z();
            this.p |= 256;
        }

        /* access modifiers changed from: private */
        public void b(ControlMessage.PushServiceConfigMsg pushServiceConfigMsg) {
            if (this.y == null || this.y == ControlMessage.PushServiceConfigMsg.m()) {
                this.y = pushServiceConfigMsg;
            } else {
                this.y = (ControlMessage.PushServiceConfigMsg) ((ControlMessage.PushServiceConfigMsg.Builder) ControlMessage.PushServiceConfigMsg.a(this.y).b(pushServiceConfigMsg)).Y();
            }
            this.p |= 256;
        }

        /* access modifiers changed from: private */
        public void aj() {
            this.y = null;
            this.p &= -257;
        }

        public boolean z() {
            return (this.p & 512) == 512;
        }

        public int A() {
            return this.z;
        }

        /* access modifiers changed from: private */
        public void c(int i2) {
            this.p |= 512;
            this.z = i2;
        }

        /* access modifiers changed from: private */
        public void ak() {
            this.p &= -513;
            this.z = 0;
        }

        public boolean B() {
            return (this.p & 1024) == 1024;
        }

        public ByteString C() {
            return this.A;
        }

        /* access modifiers changed from: private */
        public void i(ByteString byteString) {
            if (byteString != null) {
                this.p |= 1024;
                this.A = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void al() {
            this.p &= -1025;
            this.A = G().C();
        }

        public boolean D() {
            return (this.p & 2048) == 2048;
        }

        public ByteString E() {
            return this.B;
        }

        /* access modifiers changed from: private */
        public void j(ByteString byteString) {
            if (byteString != null) {
                this.p |= 2048;
                this.B = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void am() {
            this.p &= -2049;
            this.B = G().E();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.p & 1) == 1) {
                codedOutputStream.c(1, this.q);
            }
            if ((this.p & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.p & 4) == 4) {
                codedOutputStream.a(3, g());
            }
            if ((this.p & 8) == 8) {
                codedOutputStream.a(4, j());
            }
            if ((this.p & 16) == 16) {
                codedOutputStream.b(5, this.u);
            }
            if ((this.p & 32) == 32) {
                codedOutputStream.a(6, p());
            }
            if ((this.p & 64) == 64) {
                codedOutputStream.a(7, s());
            }
            if ((this.p & 128) == 128) {
                codedOutputStream.a(8, v());
            }
            if ((this.p & 256) == 256) {
                codedOutputStream.a(9, (MessageLite) y());
            }
            if ((this.p & 512) == 512) {
                codedOutputStream.b(10, this.z);
            }
            if ((this.p & 1024) == 1024) {
                codedOutputStream.a(11, this.A);
            }
            if ((this.p & 2048) == 2048) {
                codedOutputStream.a(12, this.B);
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int i3 = 0;
            if ((this.p & 1) == 1) {
                i3 = 0 + CodedOutputStream.i(1, this.q);
            }
            if ((this.p & 2) == 2) {
                i3 += CodedOutputStream.b(2, d());
            }
            if ((this.p & 4) == 4) {
                i3 += CodedOutputStream.b(3, g());
            }
            if ((this.p & 8) == 8) {
                i3 += CodedOutputStream.b(4, j());
            }
            if ((this.p & 16) == 16) {
                i3 += CodedOutputStream.h(5, this.u);
            }
            if ((this.p & 32) == 32) {
                i3 += CodedOutputStream.b(6, p());
            }
            if ((this.p & 64) == 64) {
                i3 += CodedOutputStream.b(7, s());
            }
            if ((this.p & 128) == 128) {
                i3 += CodedOutputStream.b(8, v());
            }
            if ((this.p & 256) == 256) {
                i3 += CodedOutputStream.c(9, (MessageLite) y());
            }
            if ((this.p & 512) == 512) {
                i3 += CodedOutputStream.h(10, this.z);
            }
            if ((this.p & 1024) == 1024) {
                i3 += CodedOutputStream.c(11, this.A);
            }
            if ((this.p & 2048) == 2048) {
                i3 += CodedOutputStream.c(12, this.B);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static XMMsgConn a(ByteString byteString) throws InvalidProtocolBufferException {
            return (XMMsgConn) GeneratedMessageLite.a(C, byteString);
        }

        public static XMMsgConn a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XMMsgConn) GeneratedMessageLite.a(C, byteString, extensionRegistryLite);
        }

        public static XMMsgConn a(byte[] bArr) throws InvalidProtocolBufferException {
            return (XMMsgConn) GeneratedMessageLite.a(C, bArr);
        }

        public static XMMsgConn a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XMMsgConn) GeneratedMessageLite.a(C, bArr, extensionRegistryLite);
        }

        public static XMMsgConn a(InputStream inputStream) throws IOException {
            return (XMMsgConn) GeneratedMessageLite.a(C, inputStream);
        }

        public static XMMsgConn a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgConn) GeneratedMessageLite.a(C, inputStream, extensionRegistryLite);
        }

        public static XMMsgConn b(InputStream inputStream) throws IOException {
            return (XMMsgConn) b(C, inputStream);
        }

        public static XMMsgConn b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgConn) b(C, inputStream, extensionRegistryLite);
        }

        public static XMMsgConn a(CodedInputStream codedInputStream) throws IOException {
            return (XMMsgConn) GeneratedMessageLite.b(C, codedInputStream);
        }

        public static XMMsgConn a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgConn) GeneratedMessageLite.b(C, codedInputStream, extensionRegistryLite);
        }

        public static Builder F() {
            return (Builder) C.Y();
        }

        public static Builder a(XMMsgConn xMMsgConn) {
            return (Builder) ((Builder) C.Y()).b(xMMsgConn);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<XMMsgConn, Builder> implements XMMsgConnOrBuilder {
            private Builder() {
                super(XMMsgConn.C);
            }

            public boolean a() {
                return ((XMMsgConn) this.f11310a).a();
            }

            public int b() {
                return ((XMMsgConn) this.f11310a).b();
            }

            public Builder a(int i) {
                S();
                ((XMMsgConn) this.f11310a).a(i);
                return this;
            }

            public Builder k() {
                S();
                ((XMMsgConn) this.f11310a).ab();
                return this;
            }

            public boolean c() {
                return ((XMMsgConn) this.f11310a).c();
            }

            public String d() {
                return ((XMMsgConn) this.f11310a).d();
            }

            public ByteString e() {
                return ((XMMsgConn) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((XMMsgConn) this.f11310a).a(str);
                return this;
            }

            public Builder F() {
                S();
                ((XMMsgConn) this.f11310a).ac();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((XMMsgConn) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((XMMsgConn) this.f11310a).f();
            }

            public String g() {
                return ((XMMsgConn) this.f11310a).g();
            }

            public ByteString h() {
                return ((XMMsgConn) this.f11310a).h();
            }

            public Builder b(String str) {
                S();
                ((XMMsgConn) this.f11310a).b(str);
                return this;
            }

            public Builder G() {
                S();
                ((XMMsgConn) this.f11310a).ad();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((XMMsgConn) this.f11310a).d(byteString);
                return this;
            }

            public boolean i() {
                return ((XMMsgConn) this.f11310a).i();
            }

            public String j() {
                return ((XMMsgConn) this.f11310a).j();
            }

            public ByteString l() {
                return ((XMMsgConn) this.f11310a).l();
            }

            public Builder c(String str) {
                S();
                ((XMMsgConn) this.f11310a).c(str);
                return this;
            }

            public Builder H() {
                S();
                ((XMMsgConn) this.f11310a).ae();
                return this;
            }

            public Builder c(ByteString byteString) {
                S();
                ((XMMsgConn) this.f11310a).e(byteString);
                return this;
            }

            public boolean m() {
                return ((XMMsgConn) this.f11310a).m();
            }

            public int n() {
                return ((XMMsgConn) this.f11310a).n();
            }

            public Builder b(int i) {
                S();
                ((XMMsgConn) this.f11310a).b(i);
                return this;
            }

            public Builder I() {
                S();
                ((XMMsgConn) this.f11310a).af();
                return this;
            }

            public boolean o() {
                return ((XMMsgConn) this.f11310a).o();
            }

            public String p() {
                return ((XMMsgConn) this.f11310a).p();
            }

            public ByteString q() {
                return ((XMMsgConn) this.f11310a).q();
            }

            public Builder d(String str) {
                S();
                ((XMMsgConn) this.f11310a).d(str);
                return this;
            }

            public Builder J() {
                S();
                ((XMMsgConn) this.f11310a).ag();
                return this;
            }

            public Builder d(ByteString byteString) {
                S();
                ((XMMsgConn) this.f11310a).f(byteString);
                return this;
            }

            public boolean r() {
                return ((XMMsgConn) this.f11310a).r();
            }

            public String s() {
                return ((XMMsgConn) this.f11310a).s();
            }

            public ByteString t() {
                return ((XMMsgConn) this.f11310a).t();
            }

            public Builder e(String str) {
                S();
                ((XMMsgConn) this.f11310a).e(str);
                return this;
            }

            public Builder K() {
                S();
                ((XMMsgConn) this.f11310a).ah();
                return this;
            }

            public Builder e(ByteString byteString) {
                S();
                ((XMMsgConn) this.f11310a).g(byteString);
                return this;
            }

            public boolean u() {
                return ((XMMsgConn) this.f11310a).u();
            }

            public String v() {
                return ((XMMsgConn) this.f11310a).v();
            }

            public ByteString w() {
                return ((XMMsgConn) this.f11310a).w();
            }

            public Builder f(String str) {
                S();
                ((XMMsgConn) this.f11310a).f(str);
                return this;
            }

            public Builder L() {
                S();
                ((XMMsgConn) this.f11310a).ai();
                return this;
            }

            public Builder f(ByteString byteString) {
                S();
                ((XMMsgConn) this.f11310a).h(byteString);
                return this;
            }

            public boolean x() {
                return ((XMMsgConn) this.f11310a).x();
            }

            public ControlMessage.PushServiceConfigMsg y() {
                return ((XMMsgConn) this.f11310a).y();
            }

            public Builder a(ControlMessage.PushServiceConfigMsg pushServiceConfigMsg) {
                S();
                ((XMMsgConn) this.f11310a).a(pushServiceConfigMsg);
                return this;
            }

            public Builder a(ControlMessage.PushServiceConfigMsg.Builder builder) {
                S();
                ((XMMsgConn) this.f11310a).a(builder);
                return this;
            }

            public Builder b(ControlMessage.PushServiceConfigMsg pushServiceConfigMsg) {
                S();
                ((XMMsgConn) this.f11310a).b(pushServiceConfigMsg);
                return this;
            }

            public Builder M() {
                S();
                ((XMMsgConn) this.f11310a).aj();
                return this;
            }

            public boolean z() {
                return ((XMMsgConn) this.f11310a).z();
            }

            public int A() {
                return ((XMMsgConn) this.f11310a).A();
            }

            public Builder c(int i) {
                S();
                ((XMMsgConn) this.f11310a).c(i);
                return this;
            }

            public Builder N() {
                S();
                ((XMMsgConn) this.f11310a).ak();
                return this;
            }

            public boolean B() {
                return ((XMMsgConn) this.f11310a).B();
            }

            public ByteString C() {
                return ((XMMsgConn) this.f11310a).C();
            }

            public Builder g(ByteString byteString) {
                S();
                ((XMMsgConn) this.f11310a).i(byteString);
                return this;
            }

            public Builder O() {
                S();
                ((XMMsgConn) this.f11310a).al();
                return this;
            }

            public boolean D() {
                return ((XMMsgConn) this.f11310a).D();
            }

            public ByteString E() {
                return ((XMMsgConn) this.f11310a).E();
            }

            public Builder h(ByteString byteString) {
                S();
                ((XMMsgConn) this.f11310a).j(byteString);
                return this;
            }

            public Builder P() {
                S();
                ((XMMsgConn) this.f11310a).am();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new XMMsgConn();
                case IS_INITIALIZED:
                    return C;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    XMMsgConn xMMsgConn = (XMMsgConn) obj2;
                    this.q = visitor.a(a(), this.q, xMMsgConn.a(), xMMsgConn.q);
                    this.r = visitor.a(c(), this.r, xMMsgConn.c(), xMMsgConn.r);
                    this.s = visitor.a(f(), this.s, xMMsgConn.f(), xMMsgConn.s);
                    this.t = visitor.a(i(), this.t, xMMsgConn.i(), xMMsgConn.t);
                    this.u = visitor.a(m(), this.u, xMMsgConn.m(), xMMsgConn.u);
                    this.v = visitor.a(o(), this.v, xMMsgConn.o(), xMMsgConn.v);
                    this.w = visitor.a(r(), this.w, xMMsgConn.r(), xMMsgConn.w);
                    this.x = visitor.a(u(), this.x, xMMsgConn.u(), xMMsgConn.x);
                    this.y = (ControlMessage.PushServiceConfigMsg) visitor.a(this.y, xMMsgConn.y);
                    this.z = visitor.a(z(), this.z, xMMsgConn.z(), xMMsgConn.z);
                    this.A = visitor.a(B(), this.A, xMMsgConn.B(), xMMsgConn.A);
                    this.B = visitor.a(D(), this.B, xMMsgConn.D(), xMMsgConn.B);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.p |= xMMsgConn.p;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z2 = false;
                    while (!z2) {
                        try {
                            int a2 = codedInputStream.a();
                            switch (a2) {
                                case 0:
                                    z2 = true;
                                    break;
                                case 8:
                                    this.p |= 1;
                                    this.q = codedInputStream.q();
                                    break;
                                case 18:
                                    String l2 = codedInputStream.l();
                                    this.p |= 2;
                                    this.r = l2;
                                    break;
                                case 26:
                                    String l3 = codedInputStream.l();
                                    this.p |= 4;
                                    this.s = l3;
                                    break;
                                case 34:
                                    String l4 = codedInputStream.l();
                                    this.p |= 8;
                                    this.t = l4;
                                    break;
                                case 40:
                                    this.p |= 16;
                                    this.u = codedInputStream.h();
                                    break;
                                case 50:
                                    String l5 = codedInputStream.l();
                                    this.p |= 32;
                                    this.v = l5;
                                    break;
                                case 58:
                                    String l6 = codedInputStream.l();
                                    this.p |= 64;
                                    this.w = l6;
                                    break;
                                case 66:
                                    String l7 = codedInputStream.l();
                                    this.p |= 128;
                                    this.x = l7;
                                    break;
                                case 74:
                                    ControlMessage.PushServiceConfigMsg.Builder builder = (this.p & 256) == 256 ? (ControlMessage.PushServiceConfigMsg.Builder) this.y.Y() : null;
                                    this.y = (ControlMessage.PushServiceConfigMsg) codedInputStream.a(ControlMessage.PushServiceConfigMsg.n(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.y);
                                        this.y = (ControlMessage.PushServiceConfigMsg) builder.Y();
                                    }
                                    this.p |= 256;
                                    break;
                                case 80:
                                    this.p |= 512;
                                    this.z = codedInputStream.h();
                                    break;
                                case 90:
                                    this.p |= 1024;
                                    this.A = codedInputStream.n();
                                    break;
                                case 98:
                                    this.p |= 2048;
                                    this.B = codedInputStream.n();
                                    break;
                                default:
                                    if (a(a2, codedInputStream)) {
                                        break;
                                    }
                                    z2 = true;
                                    break;
                            }
                        } catch (InvalidProtocolBufferException e2) {
                            throw new RuntimeException(e2.setUnfinishedMessage(this));
                        } catch (IOException e3) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e3.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (D == null) {
                        synchronized (XMMsgConn.class) {
                            if (D == null) {
                                D = new GeneratedMessageLite.DefaultInstanceBasedParser(C);
                            }
                        }
                    }
                    return D;
                default:
                    throw new UnsupportedOperationException();
            }
            return C;
        }

        static {
            C.P();
        }

        public static XMMsgConn G() {
            return C;
        }

        public static Parser<XMMsgConn> H() {
            return C.M();
        }
    }

    public static final class XMMsgConnResp extends GeneratedMessageLite<XMMsgConnResp, Builder> implements XMMsgConnRespOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11219a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        /* access modifiers changed from: private */
        public static final XMMsgConnResp j = new XMMsgConnResp();
        private static volatile Parser<XMMsgConnResp> k;
        private int e;
        private String f = "";
        private String g = "";
        private ControlMessage.PushServiceConfigMsg h;
        private ByteString i = ByteString.EMPTY;

        private XMMsgConnResp() {
        }

        public boolean a() {
            return (this.e & 1) == 1;
        }

        public String b() {
            return this.f;
        }

        public ByteString c() {
            return ByteString.copyFromUtf8(this.f);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.e |= 1;
                this.f = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void p() {
            this.e &= -2;
            this.f = m().b();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.e |= 1;
                this.f = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean d() {
            return (this.e & 2) == 2;
        }

        public String e() {
            return this.g;
        }

        public ByteString f() {
            return ByteString.copyFromUtf8(this.g);
        }

        /* access modifiers changed from: private */
        public void b(String str) {
            if (str != null) {
                this.e |= 2;
                this.g = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void q() {
            this.e &= -3;
            this.g = m().e();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.e |= 2;
                this.g = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean g() {
            return (this.e & 4) == 4;
        }

        public ControlMessage.PushServiceConfigMsg h() {
            return this.h == null ? ControlMessage.PushServiceConfigMsg.m() : this.h;
        }

        /* access modifiers changed from: private */
        public void a(ControlMessage.PushServiceConfigMsg pushServiceConfigMsg) {
            if (pushServiceConfigMsg != null) {
                this.h = pushServiceConfigMsg;
                this.e |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(ControlMessage.PushServiceConfigMsg.Builder builder) {
            this.h = (ControlMessage.PushServiceConfigMsg) builder.Z();
            this.e |= 4;
        }

        /* access modifiers changed from: private */
        public void b(ControlMessage.PushServiceConfigMsg pushServiceConfigMsg) {
            if (this.h == null || this.h == ControlMessage.PushServiceConfigMsg.m()) {
                this.h = pushServiceConfigMsg;
            } else {
                this.h = (ControlMessage.PushServiceConfigMsg) ((ControlMessage.PushServiceConfigMsg.Builder) ControlMessage.PushServiceConfigMsg.a(this.h).b(pushServiceConfigMsg)).Y();
            }
            this.e |= 4;
        }

        /* access modifiers changed from: private */
        public void r() {
            this.h = null;
            this.e &= -5;
        }

        public boolean i() {
            return (this.e & 8) == 8;
        }

        public ByteString j() {
            return this.i;
        }

        /* access modifiers changed from: private */
        public void e(ByteString byteString) {
            if (byteString != null) {
                this.e |= 8;
                this.i = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void s() {
            this.e &= -9;
            this.i = m().j();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.e & 1) == 1) {
                codedOutputStream.a(1, b());
            }
            if ((this.e & 2) == 2) {
                codedOutputStream.a(2, e());
            }
            if ((this.e & 4) == 4) {
                codedOutputStream.a(3, (MessageLite) h());
            }
            if ((this.e & 8) == 8) {
                codedOutputStream.a(4, this.i);
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int i3 = 0;
            if ((this.e & 1) == 1) {
                i3 = 0 + CodedOutputStream.b(1, b());
            }
            if ((this.e & 2) == 2) {
                i3 += CodedOutputStream.b(2, e());
            }
            if ((this.e & 4) == 4) {
                i3 += CodedOutputStream.c(3, (MessageLite) h());
            }
            if ((this.e & 8) == 8) {
                i3 += CodedOutputStream.c(4, this.i);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static XMMsgConnResp a(ByteString byteString) throws InvalidProtocolBufferException {
            return (XMMsgConnResp) GeneratedMessageLite.a(j, byteString);
        }

        public static XMMsgConnResp a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XMMsgConnResp) GeneratedMessageLite.a(j, byteString, extensionRegistryLite);
        }

        public static XMMsgConnResp a(byte[] bArr) throws InvalidProtocolBufferException {
            return (XMMsgConnResp) GeneratedMessageLite.a(j, bArr);
        }

        public static XMMsgConnResp a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XMMsgConnResp) GeneratedMessageLite.a(j, bArr, extensionRegistryLite);
        }

        public static XMMsgConnResp a(InputStream inputStream) throws IOException {
            return (XMMsgConnResp) GeneratedMessageLite.a(j, inputStream);
        }

        public static XMMsgConnResp a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgConnResp) GeneratedMessageLite.a(j, inputStream, extensionRegistryLite);
        }

        public static XMMsgConnResp b(InputStream inputStream) throws IOException {
            return (XMMsgConnResp) b(j, inputStream);
        }

        public static XMMsgConnResp b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgConnResp) b(j, inputStream, extensionRegistryLite);
        }

        public static XMMsgConnResp a(CodedInputStream codedInputStream) throws IOException {
            return (XMMsgConnResp) GeneratedMessageLite.b(j, codedInputStream);
        }

        public static XMMsgConnResp a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgConnResp) GeneratedMessageLite.b(j, codedInputStream, extensionRegistryLite);
        }

        public static Builder l() {
            return (Builder) j.Y();
        }

        public static Builder a(XMMsgConnResp xMMsgConnResp) {
            return (Builder) ((Builder) j.Y()).b(xMMsgConnResp);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<XMMsgConnResp, Builder> implements XMMsgConnRespOrBuilder {
            private Builder() {
                super(XMMsgConnResp.j);
            }

            public boolean a() {
                return ((XMMsgConnResp) this.f11310a).a();
            }

            public String b() {
                return ((XMMsgConnResp) this.f11310a).b();
            }

            public ByteString c() {
                return ((XMMsgConnResp) this.f11310a).c();
            }

            public Builder a(String str) {
                S();
                ((XMMsgConnResp) this.f11310a).a(str);
                return this;
            }

            public Builder k() {
                S();
                ((XMMsgConnResp) this.f11310a).p();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((XMMsgConnResp) this.f11310a).c(byteString);
                return this;
            }

            public boolean d() {
                return ((XMMsgConnResp) this.f11310a).d();
            }

            public String e() {
                return ((XMMsgConnResp) this.f11310a).e();
            }

            public ByteString f() {
                return ((XMMsgConnResp) this.f11310a).f();
            }

            public Builder b(String str) {
                S();
                ((XMMsgConnResp) this.f11310a).b(str);
                return this;
            }

            public Builder l() {
                S();
                ((XMMsgConnResp) this.f11310a).q();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((XMMsgConnResp) this.f11310a).d(byteString);
                return this;
            }

            public boolean g() {
                return ((XMMsgConnResp) this.f11310a).g();
            }

            public ControlMessage.PushServiceConfigMsg h() {
                return ((XMMsgConnResp) this.f11310a).h();
            }

            public Builder a(ControlMessage.PushServiceConfigMsg pushServiceConfigMsg) {
                S();
                ((XMMsgConnResp) this.f11310a).a(pushServiceConfigMsg);
                return this;
            }

            public Builder a(ControlMessage.PushServiceConfigMsg.Builder builder) {
                S();
                ((XMMsgConnResp) this.f11310a).a(builder);
                return this;
            }

            public Builder b(ControlMessage.PushServiceConfigMsg pushServiceConfigMsg) {
                S();
                ((XMMsgConnResp) this.f11310a).b(pushServiceConfigMsg);
                return this;
            }

            public Builder m() {
                S();
                ((XMMsgConnResp) this.f11310a).r();
                return this;
            }

            public boolean i() {
                return ((XMMsgConnResp) this.f11310a).i();
            }

            public ByteString j() {
                return ((XMMsgConnResp) this.f11310a).j();
            }

            public Builder c(ByteString byteString) {
                S();
                ((XMMsgConnResp) this.f11310a).e(byteString);
                return this;
            }

            public Builder n() {
                S();
                ((XMMsgConnResp) this.f11310a).s();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new XMMsgConnResp();
                case IS_INITIALIZED:
                    return j;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    XMMsgConnResp xMMsgConnResp = (XMMsgConnResp) obj2;
                    this.f = visitor.a(a(), this.f, xMMsgConnResp.a(), xMMsgConnResp.f);
                    this.g = visitor.a(d(), this.g, xMMsgConnResp.d(), xMMsgConnResp.g);
                    this.h = (ControlMessage.PushServiceConfigMsg) visitor.a(this.h, xMMsgConnResp.h);
                    this.i = visitor.a(i(), this.i, xMMsgConnResp.i(), xMMsgConnResp.i);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.e |= xMMsgConnResp.e;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    String l = codedInputStream.l();
                                    this.e = 1 | this.e;
                                    this.f = l;
                                } else if (a2 == 18) {
                                    String l2 = codedInputStream.l();
                                    this.e |= 2;
                                    this.g = l2;
                                } else if (a2 == 26) {
                                    ControlMessage.PushServiceConfigMsg.Builder builder = (this.e & 4) == 4 ? (ControlMessage.PushServiceConfigMsg.Builder) this.h.Y() : null;
                                    this.h = (ControlMessage.PushServiceConfigMsg) codedInputStream.a(ControlMessage.PushServiceConfigMsg.n(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.h);
                                        this.h = (ControlMessage.PushServiceConfigMsg) builder.Y();
                                    }
                                    this.e |= 4;
                                } else if (a2 == 34) {
                                    this.e |= 8;
                                    this.i = codedInputStream.n();
                                } else if (!a(a2, codedInputStream)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e2) {
                            throw new RuntimeException(e2.setUnfinishedMessage(this));
                        } catch (IOException e3) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e3.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (k == null) {
                        synchronized (XMMsgConnResp.class) {
                            if (k == null) {
                                k = new GeneratedMessageLite.DefaultInstanceBasedParser(j);
                            }
                        }
                    }
                    return k;
                default:
                    throw new UnsupportedOperationException();
            }
            return j;
        }

        static {
            j.P();
        }

        public static XMMsgConnResp m() {
            return j;
        }

        public static Parser<XMMsgConnResp> n() {
            return j.M();
        }
    }

    public static final class XMMsgBind extends GeneratedMessageLite<XMMsgBind, Builder> implements XMMsgBindOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11216a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        /* access modifiers changed from: private */
        public static final XMMsgBind q = new XMMsgBind();
        private static volatile Parser<XMMsgBind> r;
        private int g;
        private String h = "";
        private String i = "";
        private String j = "";
        private String k = "";
        private String l = "";
        private String p = "";

        private XMMsgBind() {
        }

        public boolean a() {
            return (this.g & 1) == 1;
        }

        public String b() {
            return this.h;
        }

        public ByteString c() {
            return ByteString.copyFromUtf8(this.h);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.g |= 1;
                this.h = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void x() {
            this.g &= -2;
            this.h = u().b();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.g |= 1;
                this.h = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean d() {
            return (this.g & 2) == 2;
        }

        public String e() {
            return this.i;
        }

        public ByteString f() {
            return ByteString.copyFromUtf8(this.i);
        }

        /* access modifiers changed from: private */
        public void b(String str) {
            if (str != null) {
                this.g |= 2;
                this.i = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void y() {
            this.g &= -3;
            this.i = u().e();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.g |= 2;
                this.i = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean g() {
            return (this.g & 4) == 4;
        }

        public String h() {
            return this.j;
        }

        public ByteString i() {
            return ByteString.copyFromUtf8(this.j);
        }

        /* access modifiers changed from: private */
        public void c(String str) {
            if (str != null) {
                this.g |= 4;
                this.j = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void z() {
            this.g &= -5;
            this.j = u().h();
        }

        /* access modifiers changed from: private */
        public void e(ByteString byteString) {
            if (byteString != null) {
                this.g |= 4;
                this.j = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean j() {
            return (this.g & 8) == 8;
        }

        public String l() {
            return this.k;
        }

        public ByteString m() {
            return ByteString.copyFromUtf8(this.k);
        }

        /* access modifiers changed from: private */
        public void d(String str) {
            if (str != null) {
                this.g |= 8;
                this.k = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void A() {
            this.g &= -9;
            this.k = u().l();
        }

        /* access modifiers changed from: private */
        public void f(ByteString byteString) {
            if (byteString != null) {
                this.g |= 8;
                this.k = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean n() {
            return (this.g & 16) == 16;
        }

        public String o() {
            return this.l;
        }

        public ByteString p() {
            return ByteString.copyFromUtf8(this.l);
        }

        /* access modifiers changed from: private */
        public void e(String str) {
            if (str != null) {
                this.g |= 16;
                this.l = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void B() {
            this.g &= -17;
            this.l = u().o();
        }

        /* access modifiers changed from: private */
        public void g(ByteString byteString) {
            if (byteString != null) {
                this.g |= 16;
                this.l = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean q() {
            return (this.g & 32) == 32;
        }

        public String r() {
            return this.p;
        }

        public ByteString s() {
            return ByteString.copyFromUtf8(this.p);
        }

        /* access modifiers changed from: private */
        public void f(String str) {
            if (str != null) {
                this.g |= 32;
                this.p = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void C() {
            this.g &= -33;
            this.p = u().r();
        }

        /* access modifiers changed from: private */
        public void h(ByteString byteString) {
            if (byteString != null) {
                this.g |= 32;
                this.p = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.g & 1) == 1) {
                codedOutputStream.a(1, b());
            }
            if ((this.g & 2) == 2) {
                codedOutputStream.a(2, e());
            }
            if ((this.g & 4) == 4) {
                codedOutputStream.a(3, h());
            }
            if ((this.g & 8) == 8) {
                codedOutputStream.a(4, l());
            }
            if ((this.g & 16) == 16) {
                codedOutputStream.a(5, o());
            }
            if ((this.g & 32) == 32) {
                codedOutputStream.a(6, r());
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int i3 = 0;
            if ((this.g & 1) == 1) {
                i3 = 0 + CodedOutputStream.b(1, b());
            }
            if ((this.g & 2) == 2) {
                i3 += CodedOutputStream.b(2, e());
            }
            if ((this.g & 4) == 4) {
                i3 += CodedOutputStream.b(3, h());
            }
            if ((this.g & 8) == 8) {
                i3 += CodedOutputStream.b(4, l());
            }
            if ((this.g & 16) == 16) {
                i3 += CodedOutputStream.b(5, o());
            }
            if ((this.g & 32) == 32) {
                i3 += CodedOutputStream.b(6, r());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static XMMsgBind a(ByteString byteString) throws InvalidProtocolBufferException {
            return (XMMsgBind) GeneratedMessageLite.a(q, byteString);
        }

        public static XMMsgBind a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XMMsgBind) GeneratedMessageLite.a(q, byteString, extensionRegistryLite);
        }

        public static XMMsgBind a(byte[] bArr) throws InvalidProtocolBufferException {
            return (XMMsgBind) GeneratedMessageLite.a(q, bArr);
        }

        public static XMMsgBind a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XMMsgBind) GeneratedMessageLite.a(q, bArr, extensionRegistryLite);
        }

        public static XMMsgBind a(InputStream inputStream) throws IOException {
            return (XMMsgBind) GeneratedMessageLite.a(q, inputStream);
        }

        public static XMMsgBind a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgBind) GeneratedMessageLite.a(q, inputStream, extensionRegistryLite);
        }

        public static XMMsgBind b(InputStream inputStream) throws IOException {
            return (XMMsgBind) b(q, inputStream);
        }

        public static XMMsgBind b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgBind) b(q, inputStream, extensionRegistryLite);
        }

        public static XMMsgBind a(CodedInputStream codedInputStream) throws IOException {
            return (XMMsgBind) GeneratedMessageLite.b(q, codedInputStream);
        }

        public static XMMsgBind a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgBind) GeneratedMessageLite.b(q, codedInputStream, extensionRegistryLite);
        }

        public static Builder t() {
            return (Builder) q.Y();
        }

        public static Builder a(XMMsgBind xMMsgBind) {
            return (Builder) ((Builder) q.Y()).b(xMMsgBind);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<XMMsgBind, Builder> implements XMMsgBindOrBuilder {
            private Builder() {
                super(XMMsgBind.q);
            }

            public boolean a() {
                return ((XMMsgBind) this.f11310a).a();
            }

            public String b() {
                return ((XMMsgBind) this.f11310a).b();
            }

            public ByteString c() {
                return ((XMMsgBind) this.f11310a).c();
            }

            public Builder a(String str) {
                S();
                ((XMMsgBind) this.f11310a).a(str);
                return this;
            }

            public Builder k() {
                S();
                ((XMMsgBind) this.f11310a).x();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((XMMsgBind) this.f11310a).c(byteString);
                return this;
            }

            public boolean d() {
                return ((XMMsgBind) this.f11310a).d();
            }

            public String e() {
                return ((XMMsgBind) this.f11310a).e();
            }

            public ByteString f() {
                return ((XMMsgBind) this.f11310a).f();
            }

            public Builder b(String str) {
                S();
                ((XMMsgBind) this.f11310a).b(str);
                return this;
            }

            public Builder t() {
                S();
                ((XMMsgBind) this.f11310a).y();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((XMMsgBind) this.f11310a).d(byteString);
                return this;
            }

            public boolean g() {
                return ((XMMsgBind) this.f11310a).g();
            }

            public String h() {
                return ((XMMsgBind) this.f11310a).h();
            }

            public ByteString i() {
                return ((XMMsgBind) this.f11310a).i();
            }

            public Builder c(String str) {
                S();
                ((XMMsgBind) this.f11310a).c(str);
                return this;
            }

            public Builder u() {
                S();
                ((XMMsgBind) this.f11310a).z();
                return this;
            }

            public Builder c(ByteString byteString) {
                S();
                ((XMMsgBind) this.f11310a).e(byteString);
                return this;
            }

            public boolean j() {
                return ((XMMsgBind) this.f11310a).j();
            }

            public String l() {
                return ((XMMsgBind) this.f11310a).l();
            }

            public ByteString m() {
                return ((XMMsgBind) this.f11310a).m();
            }

            public Builder d(String str) {
                S();
                ((XMMsgBind) this.f11310a).d(str);
                return this;
            }

            public Builder v() {
                S();
                ((XMMsgBind) this.f11310a).A();
                return this;
            }

            public Builder d(ByteString byteString) {
                S();
                ((XMMsgBind) this.f11310a).f(byteString);
                return this;
            }

            public boolean n() {
                return ((XMMsgBind) this.f11310a).n();
            }

            public String o() {
                return ((XMMsgBind) this.f11310a).o();
            }

            public ByteString p() {
                return ((XMMsgBind) this.f11310a).p();
            }

            public Builder e(String str) {
                S();
                ((XMMsgBind) this.f11310a).e(str);
                return this;
            }

            public Builder w() {
                S();
                ((XMMsgBind) this.f11310a).B();
                return this;
            }

            public Builder e(ByteString byteString) {
                S();
                ((XMMsgBind) this.f11310a).g(byteString);
                return this;
            }

            public boolean q() {
                return ((XMMsgBind) this.f11310a).q();
            }

            public String r() {
                return ((XMMsgBind) this.f11310a).r();
            }

            public ByteString s() {
                return ((XMMsgBind) this.f11310a).s();
            }

            public Builder f(String str) {
                S();
                ((XMMsgBind) this.f11310a).f(str);
                return this;
            }

            public Builder x() {
                S();
                ((XMMsgBind) this.f11310a).C();
                return this;
            }

            public Builder f(ByteString byteString) {
                S();
                ((XMMsgBind) this.f11310a).h(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new XMMsgBind();
                case IS_INITIALIZED:
                    return q;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    XMMsgBind xMMsgBind = (XMMsgBind) obj2;
                    this.h = visitor.a(a(), this.h, xMMsgBind.a(), xMMsgBind.h);
                    this.i = visitor.a(d(), this.i, xMMsgBind.d(), xMMsgBind.i);
                    this.j = visitor.a(g(), this.j, xMMsgBind.g(), xMMsgBind.j);
                    this.k = visitor.a(j(), this.k, xMMsgBind.j(), xMMsgBind.k);
                    this.l = visitor.a(n(), this.l, xMMsgBind.n(), xMMsgBind.l);
                    this.p = visitor.a(q(), this.p, xMMsgBind.q(), xMMsgBind.p);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.g |= xMMsgBind.g;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    String l2 = codedInputStream.l();
                                    this.g = 1 | this.g;
                                    this.h = l2;
                                } else if (a2 == 18) {
                                    String l3 = codedInputStream.l();
                                    this.g |= 2;
                                    this.i = l3;
                                } else if (a2 == 26) {
                                    String l4 = codedInputStream.l();
                                    this.g |= 4;
                                    this.j = l4;
                                } else if (a2 == 34) {
                                    String l5 = codedInputStream.l();
                                    this.g |= 8;
                                    this.k = l5;
                                } else if (a2 == 42) {
                                    String l6 = codedInputStream.l();
                                    this.g |= 16;
                                    this.l = l6;
                                } else if (a2 == 50) {
                                    String l7 = codedInputStream.l();
                                    this.g |= 32;
                                    this.p = l7;
                                } else if (!a(a2, codedInputStream)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e2) {
                            throw new RuntimeException(e2.setUnfinishedMessage(this));
                        } catch (IOException e3) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e3.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (r == null) {
                        synchronized (XMMsgBind.class) {
                            if (r == null) {
                                r = new GeneratedMessageLite.DefaultInstanceBasedParser(q);
                            }
                        }
                    }
                    return r;
                default:
                    throw new UnsupportedOperationException();
            }
            return q;
        }

        static {
            q.P();
        }

        public static XMMsgBind u() {
            return q;
        }

        public static Parser<XMMsgBind> v() {
            return q.M();
        }
    }

    public static final class XMMsgBindResp extends GeneratedMessageLite<XMMsgBindResp, Builder> implements XMMsgBindRespOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11217a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        /* access modifiers changed from: private */
        public static final XMMsgBindResp j = new XMMsgBindResp();
        private static volatile Parser<XMMsgBindResp> k;
        private int e;
        private boolean f;
        private String g = "";
        private String h = "";
        private String i = "";

        private XMMsgBindResp() {
        }

        public boolean a() {
            return (this.e & 1) == 1;
        }

        public boolean b() {
            return this.f;
        }

        /* access modifiers changed from: private */
        public void a(boolean z) {
            this.e |= 1;
            this.f = z;
        }

        /* access modifiers changed from: private */
        public void q() {
            this.e &= -2;
            this.f = false;
        }

        public boolean c() {
            return (this.e & 2) == 2;
        }

        public String d() {
            return this.g;
        }

        public ByteString e() {
            return ByteString.copyFromUtf8(this.g);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.e |= 2;
                this.g = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void r() {
            this.e &= -3;
            this.g = n().d();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.e |= 2;
                this.g = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean f() {
            return (this.e & 4) == 4;
        }

        public String g() {
            return this.h;
        }

        public ByteString h() {
            return ByteString.copyFromUtf8(this.h);
        }

        /* access modifiers changed from: private */
        public void b(String str) {
            if (str != null) {
                this.e |= 4;
                this.h = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void s() {
            this.e &= -5;
            this.h = n().g();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.e |= 4;
                this.h = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean i() {
            return (this.e & 8) == 8;
        }

        public String j() {
            return this.i;
        }

        public ByteString l() {
            return ByteString.copyFromUtf8(this.i);
        }

        /* access modifiers changed from: private */
        public void c(String str) {
            if (str != null) {
                this.e |= 8;
                this.i = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void t() {
            this.e &= -9;
            this.i = n().j();
        }

        /* access modifiers changed from: private */
        public void e(ByteString byteString) {
            if (byteString != null) {
                this.e |= 8;
                this.i = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.e & 1) == 1) {
                codedOutputStream.a(1, this.f);
            }
            if ((this.e & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.e & 4) == 4) {
                codedOutputStream.a(3, g());
            }
            if ((this.e & 8) == 8) {
                codedOutputStream.a(4, j());
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int i3 = 0;
            if ((this.e & 1) == 1) {
                i3 = 0 + CodedOutputStream.b(1, this.f);
            }
            if ((this.e & 2) == 2) {
                i3 += CodedOutputStream.b(2, d());
            }
            if ((this.e & 4) == 4) {
                i3 += CodedOutputStream.b(3, g());
            }
            if ((this.e & 8) == 8) {
                i3 += CodedOutputStream.b(4, j());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static XMMsgBindResp a(ByteString byteString) throws InvalidProtocolBufferException {
            return (XMMsgBindResp) GeneratedMessageLite.a(j, byteString);
        }

        public static XMMsgBindResp a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XMMsgBindResp) GeneratedMessageLite.a(j, byteString, extensionRegistryLite);
        }

        public static XMMsgBindResp a(byte[] bArr) throws InvalidProtocolBufferException {
            return (XMMsgBindResp) GeneratedMessageLite.a(j, bArr);
        }

        public static XMMsgBindResp a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XMMsgBindResp) GeneratedMessageLite.a(j, bArr, extensionRegistryLite);
        }

        public static XMMsgBindResp a(InputStream inputStream) throws IOException {
            return (XMMsgBindResp) GeneratedMessageLite.a(j, inputStream);
        }

        public static XMMsgBindResp a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgBindResp) GeneratedMessageLite.a(j, inputStream, extensionRegistryLite);
        }

        public static XMMsgBindResp b(InputStream inputStream) throws IOException {
            return (XMMsgBindResp) b(j, inputStream);
        }

        public static XMMsgBindResp b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgBindResp) b(j, inputStream, extensionRegistryLite);
        }

        public static XMMsgBindResp a(CodedInputStream codedInputStream) throws IOException {
            return (XMMsgBindResp) GeneratedMessageLite.b(j, codedInputStream);
        }

        public static XMMsgBindResp a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgBindResp) GeneratedMessageLite.b(j, codedInputStream, extensionRegistryLite);
        }

        public static Builder m() {
            return (Builder) j.Y();
        }

        public static Builder a(XMMsgBindResp xMMsgBindResp) {
            return (Builder) ((Builder) j.Y()).b(xMMsgBindResp);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<XMMsgBindResp, Builder> implements XMMsgBindRespOrBuilder {
            private Builder() {
                super(XMMsgBindResp.j);
            }

            public boolean a() {
                return ((XMMsgBindResp) this.f11310a).a();
            }

            public boolean b() {
                return ((XMMsgBindResp) this.f11310a).b();
            }

            public Builder a(boolean z) {
                S();
                ((XMMsgBindResp) this.f11310a).a(z);
                return this;
            }

            public Builder k() {
                S();
                ((XMMsgBindResp) this.f11310a).q();
                return this;
            }

            public boolean c() {
                return ((XMMsgBindResp) this.f11310a).c();
            }

            public String d() {
                return ((XMMsgBindResp) this.f11310a).d();
            }

            public ByteString e() {
                return ((XMMsgBindResp) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((XMMsgBindResp) this.f11310a).a(str);
                return this;
            }

            public Builder m() {
                S();
                ((XMMsgBindResp) this.f11310a).r();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((XMMsgBindResp) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((XMMsgBindResp) this.f11310a).f();
            }

            public String g() {
                return ((XMMsgBindResp) this.f11310a).g();
            }

            public ByteString h() {
                return ((XMMsgBindResp) this.f11310a).h();
            }

            public Builder b(String str) {
                S();
                ((XMMsgBindResp) this.f11310a).b(str);
                return this;
            }

            public Builder n() {
                S();
                ((XMMsgBindResp) this.f11310a).s();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((XMMsgBindResp) this.f11310a).d(byteString);
                return this;
            }

            public boolean i() {
                return ((XMMsgBindResp) this.f11310a).i();
            }

            public String j() {
                return ((XMMsgBindResp) this.f11310a).j();
            }

            public ByteString l() {
                return ((XMMsgBindResp) this.f11310a).l();
            }

            public Builder c(String str) {
                S();
                ((XMMsgBindResp) this.f11310a).c(str);
                return this;
            }

            public Builder o() {
                S();
                ((XMMsgBindResp) this.f11310a).t();
                return this;
            }

            public Builder c(ByteString byteString) {
                S();
                ((XMMsgBindResp) this.f11310a).e(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new XMMsgBindResp();
                case IS_INITIALIZED:
                    return j;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    XMMsgBindResp xMMsgBindResp = (XMMsgBindResp) obj2;
                    this.f = visitor.a(a(), this.f, xMMsgBindResp.a(), xMMsgBindResp.f);
                    this.g = visitor.a(c(), this.g, xMMsgBindResp.c(), xMMsgBindResp.g);
                    this.h = visitor.a(f(), this.h, xMMsgBindResp.f(), xMMsgBindResp.h);
                    this.i = visitor.a(i(), this.i, xMMsgBindResp.i(), xMMsgBindResp.i);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.e |= xMMsgBindResp.e;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 8) {
                                    this.e |= 1;
                                    this.f = codedInputStream.k();
                                } else if (a2 == 18) {
                                    String l = codedInputStream.l();
                                    this.e |= 2;
                                    this.g = l;
                                } else if (a2 == 26) {
                                    String l2 = codedInputStream.l();
                                    this.e |= 4;
                                    this.h = l2;
                                } else if (a2 == 34) {
                                    String l3 = codedInputStream.l();
                                    this.e |= 8;
                                    this.i = l3;
                                } else if (!a(a2, codedInputStream)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e2) {
                            throw new RuntimeException(e2.setUnfinishedMessage(this));
                        } catch (IOException e3) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e3.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (k == null) {
                        synchronized (XMMsgBindResp.class) {
                            if (k == null) {
                                k = new GeneratedMessageLite.DefaultInstanceBasedParser(j);
                            }
                        }
                    }
                    return k;
                default:
                    throw new UnsupportedOperationException();
            }
            return j;
        }

        static {
            j.P();
        }

        public static XMMsgBindResp n() {
            return j;
        }

        public static Parser<XMMsgBindResp> o() {
            return j.M();
        }
    }

    public static final class XMMsgPing extends GeneratedMessageLite<XMMsgPing, Builder> implements XMMsgPingOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11221a = 1;
        public static final int b = 2;
        /* access modifiers changed from: private */
        public static final XMMsgPing f = new XMMsgPing();
        private static volatile Parser<XMMsgPing> g;
        private int c;
        private ByteString d = ByteString.EMPTY;
        private ControlMessage.PushServiceConfigMsg e;

        private XMMsgPing() {
        }

        public boolean a() {
            return (this.c & 1) == 1;
        }

        public ByteString b() {
            return this.d;
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.c |= 1;
                this.d = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void i() {
            this.c &= -2;
            this.d = f().b();
        }

        public boolean c() {
            return (this.c & 2) == 2;
        }

        public ControlMessage.PushServiceConfigMsg d() {
            return this.e == null ? ControlMessage.PushServiceConfigMsg.m() : this.e;
        }

        /* access modifiers changed from: private */
        public void a(ControlMessage.PushServiceConfigMsg pushServiceConfigMsg) {
            if (pushServiceConfigMsg != null) {
                this.e = pushServiceConfigMsg;
                this.c |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(ControlMessage.PushServiceConfigMsg.Builder builder) {
            this.e = (ControlMessage.PushServiceConfigMsg) builder.Z();
            this.c |= 2;
        }

        /* access modifiers changed from: private */
        public void b(ControlMessage.PushServiceConfigMsg pushServiceConfigMsg) {
            if (this.e == null || this.e == ControlMessage.PushServiceConfigMsg.m()) {
                this.e = pushServiceConfigMsg;
            } else {
                this.e = (ControlMessage.PushServiceConfigMsg) ((ControlMessage.PushServiceConfigMsg.Builder) ControlMessage.PushServiceConfigMsg.a(this.e).b(pushServiceConfigMsg)).Y();
            }
            this.c |= 2;
        }

        /* access modifiers changed from: private */
        public void j() {
            this.e = null;
            this.c &= -3;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.c & 1) == 1) {
                codedOutputStream.a(1, this.d);
            }
            if ((this.c & 2) == 2) {
                codedOutputStream.a(2, (MessageLite) d());
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i = this.o;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.c & 1) == 1) {
                i2 = 0 + CodedOutputStream.c(1, this.d);
            }
            if ((this.c & 2) == 2) {
                i2 += CodedOutputStream.c(2, (MessageLite) d());
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static XMMsgPing a(ByteString byteString) throws InvalidProtocolBufferException {
            return (XMMsgPing) GeneratedMessageLite.a(f, byteString);
        }

        public static XMMsgPing a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XMMsgPing) GeneratedMessageLite.a(f, byteString, extensionRegistryLite);
        }

        public static XMMsgPing a(byte[] bArr) throws InvalidProtocolBufferException {
            return (XMMsgPing) GeneratedMessageLite.a(f, bArr);
        }

        public static XMMsgPing a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XMMsgPing) GeneratedMessageLite.a(f, bArr, extensionRegistryLite);
        }

        public static XMMsgPing a(InputStream inputStream) throws IOException {
            return (XMMsgPing) GeneratedMessageLite.a(f, inputStream);
        }

        public static XMMsgPing a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgPing) GeneratedMessageLite.a(f, inputStream, extensionRegistryLite);
        }

        public static XMMsgPing b(InputStream inputStream) throws IOException {
            return (XMMsgPing) b(f, inputStream);
        }

        public static XMMsgPing b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgPing) b(f, inputStream, extensionRegistryLite);
        }

        public static XMMsgPing a(CodedInputStream codedInputStream) throws IOException {
            return (XMMsgPing) GeneratedMessageLite.b(f, codedInputStream);
        }

        public static XMMsgPing a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgPing) GeneratedMessageLite.b(f, codedInputStream, extensionRegistryLite);
        }

        public static Builder e() {
            return (Builder) f.Y();
        }

        public static Builder a(XMMsgPing xMMsgPing) {
            return (Builder) ((Builder) f.Y()).b(xMMsgPing);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<XMMsgPing, Builder> implements XMMsgPingOrBuilder {
            private Builder() {
                super(XMMsgPing.f);
            }

            public boolean a() {
                return ((XMMsgPing) this.f11310a).a();
            }

            public ByteString b() {
                return ((XMMsgPing) this.f11310a).b();
            }

            public Builder a(ByteString byteString) {
                S();
                ((XMMsgPing) this.f11310a).c(byteString);
                return this;
            }

            public Builder e() {
                S();
                ((XMMsgPing) this.f11310a).i();
                return this;
            }

            public boolean c() {
                return ((XMMsgPing) this.f11310a).c();
            }

            public ControlMessage.PushServiceConfigMsg d() {
                return ((XMMsgPing) this.f11310a).d();
            }

            public Builder a(ControlMessage.PushServiceConfigMsg pushServiceConfigMsg) {
                S();
                ((XMMsgPing) this.f11310a).a(pushServiceConfigMsg);
                return this;
            }

            public Builder a(ControlMessage.PushServiceConfigMsg.Builder builder) {
                S();
                ((XMMsgPing) this.f11310a).a(builder);
                return this;
            }

            public Builder b(ControlMessage.PushServiceConfigMsg pushServiceConfigMsg) {
                S();
                ((XMMsgPing) this.f11310a).b(pushServiceConfigMsg);
                return this;
            }

            public Builder f() {
                S();
                ((XMMsgPing) this.f11310a).j();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new XMMsgPing();
                case IS_INITIALIZED:
                    return f;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    XMMsgPing xMMsgPing = (XMMsgPing) obj2;
                    this.d = visitor.a(a(), this.d, xMMsgPing.a(), xMMsgPing.d);
                    this.e = (ControlMessage.PushServiceConfigMsg) visitor.a(this.e, xMMsgPing.e);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.c |= xMMsgPing.c;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    this.c |= 1;
                                    this.d = codedInputStream.n();
                                } else if (a2 == 18) {
                                    ControlMessage.PushServiceConfigMsg.Builder builder = (this.c & 2) == 2 ? (ControlMessage.PushServiceConfigMsg.Builder) this.e.Y() : null;
                                    this.e = (ControlMessage.PushServiceConfigMsg) codedInputStream.a(ControlMessage.PushServiceConfigMsg.n(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.e);
                                        this.e = (ControlMessage.PushServiceConfigMsg) builder.Y();
                                    }
                                    this.c |= 2;
                                } else if (!a(a2, codedInputStream)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e2) {
                            throw new RuntimeException(e2.setUnfinishedMessage(this));
                        } catch (IOException e3) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e3.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (g == null) {
                        synchronized (XMMsgPing.class) {
                            if (g == null) {
                                g = new GeneratedMessageLite.DefaultInstanceBasedParser(f);
                            }
                        }
                    }
                    return g;
                default:
                    throw new UnsupportedOperationException();
            }
            return f;
        }

        static {
            f.P();
        }

        public static XMMsgPing f() {
            return f;
        }

        public static Parser<XMMsgPing> g() {
            return f.M();
        }
    }

    public static final class XMMsgNotify extends GeneratedMessageLite<XMMsgNotify, Builder> implements XMMsgNotifyOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11220a = 1;
        public static final int b = 2;
        /* access modifiers changed from: private */
        public static final XMMsgNotify f = new XMMsgNotify();
        private static volatile Parser<XMMsgNotify> g;
        private int c;
        private int d;
        private String e = "";

        private XMMsgNotify() {
        }

        public boolean a() {
            return (this.c & 1) == 1;
        }

        public int b() {
            return this.d;
        }

        /* access modifiers changed from: private */
        public void a(int i) {
            this.c |= 1;
            this.d = i;
        }

        /* access modifiers changed from: private */
        public void j() {
            this.c &= -2;
            this.d = 0;
        }

        public boolean c() {
            return (this.c & 2) == 2;
        }

        public String d() {
            return this.e;
        }

        public ByteString e() {
            return ByteString.copyFromUtf8(this.e);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.c |= 2;
                this.e = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void l() {
            this.c &= -3;
            this.e = g().d();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.c |= 2;
                this.e = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.c & 1) == 1) {
                codedOutputStream.b(1, this.d);
            }
            if ((this.c & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i = this.o;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.c & 1) == 1) {
                i2 = 0 + CodedOutputStream.h(1, this.d);
            }
            if ((this.c & 2) == 2) {
                i2 += CodedOutputStream.b(2, d());
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static XMMsgNotify a(ByteString byteString) throws InvalidProtocolBufferException {
            return (XMMsgNotify) GeneratedMessageLite.a(f, byteString);
        }

        public static XMMsgNotify a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XMMsgNotify) GeneratedMessageLite.a(f, byteString, extensionRegistryLite);
        }

        public static XMMsgNotify a(byte[] bArr) throws InvalidProtocolBufferException {
            return (XMMsgNotify) GeneratedMessageLite.a(f, bArr);
        }

        public static XMMsgNotify a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XMMsgNotify) GeneratedMessageLite.a(f, bArr, extensionRegistryLite);
        }

        public static XMMsgNotify a(InputStream inputStream) throws IOException {
            return (XMMsgNotify) GeneratedMessageLite.a(f, inputStream);
        }

        public static XMMsgNotify a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgNotify) GeneratedMessageLite.a(f, inputStream, extensionRegistryLite);
        }

        public static XMMsgNotify b(InputStream inputStream) throws IOException {
            return (XMMsgNotify) b(f, inputStream);
        }

        public static XMMsgNotify b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgNotify) b(f, inputStream, extensionRegistryLite);
        }

        public static XMMsgNotify a(CodedInputStream codedInputStream) throws IOException {
            return (XMMsgNotify) GeneratedMessageLite.b(f, codedInputStream);
        }

        public static XMMsgNotify a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XMMsgNotify) GeneratedMessageLite.b(f, codedInputStream, extensionRegistryLite);
        }

        public static Builder f() {
            return (Builder) f.Y();
        }

        public static Builder a(XMMsgNotify xMMsgNotify) {
            return (Builder) ((Builder) f.Y()).b(xMMsgNotify);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<XMMsgNotify, Builder> implements XMMsgNotifyOrBuilder {
            private Builder() {
                super(XMMsgNotify.f);
            }

            public boolean a() {
                return ((XMMsgNotify) this.f11310a).a();
            }

            public int b() {
                return ((XMMsgNotify) this.f11310a).b();
            }

            public Builder a(int i) {
                S();
                ((XMMsgNotify) this.f11310a).a(i);
                return this;
            }

            public Builder f() {
                S();
                ((XMMsgNotify) this.f11310a).j();
                return this;
            }

            public boolean c() {
                return ((XMMsgNotify) this.f11310a).c();
            }

            public String d() {
                return ((XMMsgNotify) this.f11310a).d();
            }

            public ByteString e() {
                return ((XMMsgNotify) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((XMMsgNotify) this.f11310a).a(str);
                return this;
            }

            public Builder g() {
                S();
                ((XMMsgNotify) this.f11310a).l();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((XMMsgNotify) this.f11310a).c(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new XMMsgNotify();
                case IS_INITIALIZED:
                    return f;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    XMMsgNotify xMMsgNotify = (XMMsgNotify) obj2;
                    this.d = visitor.a(a(), this.d, xMMsgNotify.a(), xMMsgNotify.d);
                    this.e = visitor.a(c(), this.e, xMMsgNotify.c(), xMMsgNotify.e);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.c |= xMMsgNotify.c;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 8) {
                                    this.c |= 1;
                                    this.d = codedInputStream.h();
                                } else if (a2 == 18) {
                                    String l = codedInputStream.l();
                                    this.c |= 2;
                                    this.e = l;
                                } else if (!a(a2, codedInputStream)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e2) {
                            throw new RuntimeException(e2.setUnfinishedMessage(this));
                        } catch (IOException e3) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e3.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (g == null) {
                        synchronized (XMMsgNotify.class) {
                            if (g == null) {
                                g = new GeneratedMessageLite.DefaultInstanceBasedParser(f);
                            }
                        }
                    }
                    return g;
                default:
                    throw new UnsupportedOperationException();
            }
            return f;
        }

        static {
            f.P();
        }

        public static XMMsgNotify g() {
            return f;
        }

        public static Parser<XMMsgNotify> h() {
            return f.M();
        }
    }
}
