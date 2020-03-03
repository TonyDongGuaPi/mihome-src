package com.xiaomi.mimc.proto;

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

public final class RtsData {

    public interface BindRelayRequestOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        String g();

        ByteString h();

        boolean i();

        int j();

        boolean l();

        String m();

        ByteString n();

        boolean o();

        StreamConfig p();

        boolean q();

        StreamConfig r();
    }

    public interface BindRelayResponseOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        boolean b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        int g();

        boolean h();

        String i();

        ByteString j();

        boolean l();

        int m();

        boolean n();

        int o();
    }

    public interface BurrowPacketOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        long g();

        boolean h();

        long i();

        boolean j();

        BURROW_TYPE l();
    }

    public interface PingRelayRequestOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        String d();

        ByteString e();
    }

    public interface PingRelayResponseOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        boolean b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        int g();

        boolean h();

        int i();
    }

    public interface RelayKickRequestOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        KICK_REASON b();
    }

    public interface RelayKickResponseOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        boolean b();
    }

    public interface RouterPacketOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        long d();

        boolean e();

        String f();

        ByteString g();

        boolean h();

        long i();

        boolean j();

        String l();

        ByteString m();

        boolean n();

        long o();

        boolean p();

        STREAM_TYPE q();

        boolean r();

        ByteString s();

        boolean t();

        long u();

        boolean v();

        String w();

        ByteString x();
    }

    public interface StreamConfigOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        STREAM_STRATEGY b();

        boolean c();

        int d();

        boolean e();

        int f();

        boolean g();

        boolean h();
    }

    public interface UnBindRelayRequestOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        String g();

        ByteString h();
    }

    public interface UnBindRelayResponseOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        boolean b();
    }

    public interface UserPacketOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        int b();

        boolean c();

        PKT_TYPE d();

        boolean e();

        long f();

        boolean g();

        String h();

        ByteString i();

        boolean j();

        long l();

        boolean m();

        ByteString n();

        boolean o();

        long p();

        boolean q();

        String r();

        ByteString s();
    }

    public static void a(ExtensionRegistryLite extensionRegistryLite) {
    }

    private RtsData() {
    }

    public enum PKT_TYPE implements Internal.EnumLite {
        BIND_RELAY_REQUEST(1),
        BIND_RELAY_RESPONSE(2),
        PING_RELAY_REQUEST(3),
        PING_RELAY_RESPONSE(4),
        RELAY_KICK_REQUEST(5),
        RELAY_KICK_RESPONSE(6),
        USER_DATA_AUDIO(7),
        USER_DATA_VIDEO(8),
        RELAY_CONN_REQUEST(9),
        INTRANET_CONN_REQUEST(10),
        INTERNET_CONN_REQUEST(11);
        
        public static final int BIND_RELAY_REQUEST_VALUE = 1;
        public static final int BIND_RELAY_RESPONSE_VALUE = 2;
        public static final int INTERNET_CONN_REQUEST_VALUE = 11;
        public static final int INTRANET_CONN_REQUEST_VALUE = 10;
        public static final int PING_RELAY_REQUEST_VALUE = 3;
        public static final int PING_RELAY_RESPONSE_VALUE = 4;
        public static final int RELAY_CONN_REQUEST_VALUE = 9;
        public static final int RELAY_KICK_REQUEST_VALUE = 5;
        public static final int RELAY_KICK_RESPONSE_VALUE = 6;
        public static final int USER_DATA_AUDIO_VALUE = 7;
        public static final int USER_DATA_VIDEO_VALUE = 8;
        private static final Internal.EnumLiteMap<PKT_TYPE> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<PKT_TYPE>() {
                /* renamed from: a */
                public PKT_TYPE b(int i) {
                    return PKT_TYPE.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static PKT_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static PKT_TYPE forNumber(int i) {
            switch (i) {
                case 1:
                    return BIND_RELAY_REQUEST;
                case 2:
                    return BIND_RELAY_RESPONSE;
                case 3:
                    return PING_RELAY_REQUEST;
                case 4:
                    return PING_RELAY_RESPONSE;
                case 5:
                    return RELAY_KICK_REQUEST;
                case 6:
                    return RELAY_KICK_RESPONSE;
                case 7:
                    return USER_DATA_AUDIO;
                case 8:
                    return USER_DATA_VIDEO;
                case 9:
                    return RELAY_CONN_REQUEST;
                case 10:
                    return INTRANET_CONN_REQUEST;
                case 11:
                    return INTERNET_CONN_REQUEST;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<PKT_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        private PKT_TYPE(int i) {
            this.value = i;
        }
    }

    public enum STREAM_STRATEGY implements Internal.EnumLite {
        FEC_STRATEGY(1),
        ACK_STRATEGY(2);
        
        public static final int ACK_STRATEGY_VALUE = 2;
        public static final int FEC_STRATEGY_VALUE = 1;
        private static final Internal.EnumLiteMap<STREAM_STRATEGY> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<STREAM_STRATEGY>() {
                /* renamed from: a */
                public STREAM_STRATEGY b(int i) {
                    return STREAM_STRATEGY.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static STREAM_STRATEGY valueOf(int i) {
            return forNumber(i);
        }

        public static STREAM_STRATEGY forNumber(int i) {
            switch (i) {
                case 1:
                    return FEC_STRATEGY;
                case 2:
                    return ACK_STRATEGY;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<STREAM_STRATEGY> internalGetValueMap() {
            return internalValueMap;
        }

        private STREAM_STRATEGY(int i) {
            this.value = i;
        }
    }

    public enum KICK_REASON implements Internal.EnumLite {
        REALY_CHEANGED(1);
        
        public static final int REALY_CHEANGED_VALUE = 1;
        private static final Internal.EnumLiteMap<KICK_REASON> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<KICK_REASON>() {
                /* renamed from: a */
                public KICK_REASON b(int i) {
                    return KICK_REASON.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static KICK_REASON valueOf(int i) {
            return forNumber(i);
        }

        public static KICK_REASON forNumber(int i) {
            if (i != 1) {
                return null;
            }
            return REALY_CHEANGED;
        }

        public static Internal.EnumLiteMap<KICK_REASON> internalGetValueMap() {
            return internalValueMap;
        }

        private KICK_REASON(int i) {
            this.value = i;
        }
    }

    public enum STREAM_TYPE implements Internal.EnumLite {
        SIGNAL_STREAM(1),
        AUDIO_STREAM(2),
        VIDEO_STREAM(3);
        
        public static final int AUDIO_STREAM_VALUE = 2;
        public static final int SIGNAL_STREAM_VALUE = 1;
        public static final int VIDEO_STREAM_VALUE = 3;
        private static final Internal.EnumLiteMap<STREAM_TYPE> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<STREAM_TYPE>() {
                /* renamed from: a */
                public STREAM_TYPE b(int i) {
                    return STREAM_TYPE.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static STREAM_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static STREAM_TYPE forNumber(int i) {
            switch (i) {
                case 1:
                    return SIGNAL_STREAM;
                case 2:
                    return AUDIO_STREAM;
                case 3:
                    return VIDEO_STREAM;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<STREAM_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        private STREAM_TYPE(int i) {
            this.value = i;
        }
    }

    public enum CHANNEL_TYPE implements Internal.EnumLite {
        RELAY(1),
        P2P(2);
        
        public static final int P2P_VALUE = 2;
        public static final int RELAY_VALUE = 1;
        private static final Internal.EnumLiteMap<CHANNEL_TYPE> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<CHANNEL_TYPE>() {
                /* renamed from: a */
                public CHANNEL_TYPE b(int i) {
                    return CHANNEL_TYPE.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static CHANNEL_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static CHANNEL_TYPE forNumber(int i) {
            switch (i) {
                case 1:
                    return RELAY;
                case 2:
                    return P2P;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<CHANNEL_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        private CHANNEL_TYPE(int i) {
            this.value = i;
        }
    }

    public enum BURROW_TYPE implements Internal.EnumLite {
        INTRANET_BURROW_REQUEST(1),
        INTRANET_BURROW_RESPONSE(2),
        INTERNET_BURROW_REQUEST(3),
        INTERNET_BURROW_RESPONSE(4);
        
        public static final int INTERNET_BURROW_REQUEST_VALUE = 3;
        public static final int INTERNET_BURROW_RESPONSE_VALUE = 4;
        public static final int INTRANET_BURROW_REQUEST_VALUE = 1;
        public static final int INTRANET_BURROW_RESPONSE_VALUE = 2;
        private static final Internal.EnumLiteMap<BURROW_TYPE> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<BURROW_TYPE>() {
                /* renamed from: a */
                public BURROW_TYPE b(int i) {
                    return BURROW_TYPE.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static BURROW_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static BURROW_TYPE forNumber(int i) {
            switch (i) {
                case 1:
                    return INTRANET_BURROW_REQUEST;
                case 2:
                    return INTRANET_BURROW_RESPONSE;
                case 3:
                    return INTERNET_BURROW_REQUEST;
                case 4:
                    return INTERNET_BURROW_RESPONSE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<BURROW_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        private BURROW_TYPE(int i) {
            this.value = i;
        }
    }

    public static final class UserPacket extends GeneratedMessageLite<UserPacket, Builder> implements UserPacketOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11268a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        public static final int h = 8;
        /* access modifiers changed from: private */
        public static final UserPacket v = new UserPacket();
        private static volatile Parser<UserPacket> w;
        private int i;
        private int j;
        private int k = 1;
        private long l;
        private String p = "";
        private long q;
        private ByteString r = ByteString.EMPTY;
        private long s;
        private String t = "";
        private byte u = -1;

        private UserPacket() {
        }

        public boolean a() {
            return (this.i & 1) == 1;
        }

        public int b() {
            return this.j;
        }

        /* access modifiers changed from: private */
        public void a(int i2) {
            this.i |= 1;
            this.j = i2;
        }

        /* access modifiers changed from: private */
        public void x() {
            this.i &= -2;
            this.j = 0;
        }

        public boolean c() {
            return (this.i & 2) == 2;
        }

        public PKT_TYPE d() {
            PKT_TYPE forNumber = PKT_TYPE.forNumber(this.k);
            return forNumber == null ? PKT_TYPE.BIND_RELAY_REQUEST : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(PKT_TYPE pkt_type) {
            if (pkt_type != null) {
                this.i |= 2;
                this.k = pkt_type.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void y() {
            this.i &= -3;
            this.k = 1;
        }

        public boolean e() {
            return (this.i & 4) == 4;
        }

        public long f() {
            return this.l;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.i |= 4;
            this.l = j2;
        }

        /* access modifiers changed from: private */
        public void z() {
            this.i &= -5;
            this.l = 0;
        }

        public boolean g() {
            return (this.i & 8) == 8;
        }

        public String h() {
            return this.p;
        }

        public ByteString i() {
            return ByteString.copyFromUtf8(this.p);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.i |= 8;
                this.p = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void A() {
            this.i &= -9;
            this.p = u().h();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.i |= 8;
                this.p = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean j() {
            return (this.i & 16) == 16;
        }

        public long l() {
            return this.q;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.i |= 16;
            this.q = j2;
        }

        /* access modifiers changed from: private */
        public void B() {
            this.i &= -17;
            this.q = 0;
        }

        public boolean m() {
            return (this.i & 32) == 32;
        }

        public ByteString n() {
            return this.r;
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.i |= 32;
                this.r = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void C() {
            this.i &= -33;
            this.r = u().n();
        }

        public boolean o() {
            return (this.i & 64) == 64;
        }

        public long p() {
            return this.s;
        }

        /* access modifiers changed from: private */
        public void c(long j2) {
            this.i |= 64;
            this.s = j2;
        }

        /* access modifiers changed from: private */
        public void D() {
            this.i &= -65;
            this.s = 0;
        }

        public boolean q() {
            return (this.i & 128) == 128;
        }

        public String r() {
            return this.t;
        }

        public ByteString s() {
            return ByteString.copyFromUtf8(this.t);
        }

        /* access modifiers changed from: private */
        public void b(String str) {
            if (str != null) {
                this.i |= 128;
                this.t = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void E() {
            this.i &= -129;
            this.t = u().r();
        }

        /* access modifiers changed from: private */
        public void e(ByteString byteString) {
            if (byteString != null) {
                this.i |= 128;
                this.t = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.i & 1) == 1) {
                codedOutputStream.c(1, this.j);
            }
            if ((this.i & 2) == 2) {
                codedOutputStream.g(2, this.k);
            }
            if ((this.i & 4) == 4) {
                codedOutputStream.b(3, this.l);
            }
            if ((this.i & 8) == 8) {
                codedOutputStream.a(4, h());
            }
            if ((this.i & 16) == 16) {
                codedOutputStream.b(5, this.q);
            }
            if ((this.i & 32) == 32) {
                codedOutputStream.a(6, this.r);
            }
            if ((this.i & 64) == 64) {
                codedOutputStream.a(7, this.s);
            }
            if ((this.i & 128) == 128) {
                codedOutputStream.a(8, r());
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int i3 = 0;
            if ((this.i & 1) == 1) {
                i3 = 0 + CodedOutputStream.i(1, this.j);
            }
            if ((this.i & 2) == 2) {
                i3 += CodedOutputStream.m(2, this.k);
            }
            if ((this.i & 4) == 4) {
                i3 += CodedOutputStream.g(3, this.l);
            }
            if ((this.i & 8) == 8) {
                i3 += CodedOutputStream.b(4, h());
            }
            if ((this.i & 16) == 16) {
                i3 += CodedOutputStream.g(5, this.q);
            }
            if ((this.i & 32) == 32) {
                i3 += CodedOutputStream.c(6, this.r);
            }
            if ((this.i & 64) == 64) {
                i3 += CodedOutputStream.f(7, this.s);
            }
            if ((this.i & 128) == 128) {
                i3 += CodedOutputStream.b(8, r());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UserPacket a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UserPacket) GeneratedMessageLite.a(v, byteString);
        }

        public static UserPacket a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UserPacket) GeneratedMessageLite.a(v, byteString, extensionRegistryLite);
        }

        public static UserPacket a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UserPacket) GeneratedMessageLite.a(v, bArr);
        }

        public static UserPacket a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UserPacket) GeneratedMessageLite.a(v, bArr, extensionRegistryLite);
        }

        public static UserPacket a(InputStream inputStream) throws IOException {
            return (UserPacket) GeneratedMessageLite.a(v, inputStream);
        }

        public static UserPacket a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UserPacket) GeneratedMessageLite.a(v, inputStream, extensionRegistryLite);
        }

        public static UserPacket b(InputStream inputStream) throws IOException {
            return (UserPacket) b(v, inputStream);
        }

        public static UserPacket b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UserPacket) b(v, inputStream, extensionRegistryLite);
        }

        public static UserPacket a(CodedInputStream codedInputStream) throws IOException {
            return (UserPacket) GeneratedMessageLite.b(v, codedInputStream);
        }

        public static UserPacket a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UserPacket) GeneratedMessageLite.b(v, codedInputStream, extensionRegistryLite);
        }

        public static Builder t() {
            return (Builder) v.Y();
        }

        public static Builder a(UserPacket userPacket) {
            return (Builder) ((Builder) v.Y()).b(userPacket);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UserPacket, Builder> implements UserPacketOrBuilder {
            private Builder() {
                super(UserPacket.v);
            }

            public boolean a() {
                return ((UserPacket) this.f11310a).a();
            }

            public int b() {
                return ((UserPacket) this.f11310a).b();
            }

            public Builder a(int i) {
                S();
                ((UserPacket) this.f11310a).a(i);
                return this;
            }

            public Builder k() {
                S();
                ((UserPacket) this.f11310a).x();
                return this;
            }

            public boolean c() {
                return ((UserPacket) this.f11310a).c();
            }

            public PKT_TYPE d() {
                return ((UserPacket) this.f11310a).d();
            }

            public Builder a(PKT_TYPE pkt_type) {
                S();
                ((UserPacket) this.f11310a).a(pkt_type);
                return this;
            }

            public Builder t() {
                S();
                ((UserPacket) this.f11310a).y();
                return this;
            }

            public boolean e() {
                return ((UserPacket) this.f11310a).e();
            }

            public long f() {
                return ((UserPacket) this.f11310a).f();
            }

            public Builder a(long j) {
                S();
                ((UserPacket) this.f11310a).a(j);
                return this;
            }

            public Builder u() {
                S();
                ((UserPacket) this.f11310a).z();
                return this;
            }

            public boolean g() {
                return ((UserPacket) this.f11310a).g();
            }

            public String h() {
                return ((UserPacket) this.f11310a).h();
            }

            public ByteString i() {
                return ((UserPacket) this.f11310a).i();
            }

            public Builder a(String str) {
                S();
                ((UserPacket) this.f11310a).a(str);
                return this;
            }

            public Builder v() {
                S();
                ((UserPacket) this.f11310a).A();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((UserPacket) this.f11310a).c(byteString);
                return this;
            }

            public boolean j() {
                return ((UserPacket) this.f11310a).j();
            }

            public long l() {
                return ((UserPacket) this.f11310a).l();
            }

            public Builder b(long j) {
                S();
                ((UserPacket) this.f11310a).b(j);
                return this;
            }

            public Builder w() {
                S();
                ((UserPacket) this.f11310a).B();
                return this;
            }

            public boolean m() {
                return ((UserPacket) this.f11310a).m();
            }

            public ByteString n() {
                return ((UserPacket) this.f11310a).n();
            }

            public Builder b(ByteString byteString) {
                S();
                ((UserPacket) this.f11310a).d(byteString);
                return this;
            }

            public Builder x() {
                S();
                ((UserPacket) this.f11310a).C();
                return this;
            }

            public boolean o() {
                return ((UserPacket) this.f11310a).o();
            }

            public long p() {
                return ((UserPacket) this.f11310a).p();
            }

            public Builder c(long j) {
                S();
                ((UserPacket) this.f11310a).c(j);
                return this;
            }

            public Builder y() {
                S();
                ((UserPacket) this.f11310a).D();
                return this;
            }

            public boolean q() {
                return ((UserPacket) this.f11310a).q();
            }

            public String r() {
                return ((UserPacket) this.f11310a).r();
            }

            public ByteString s() {
                return ((UserPacket) this.f11310a).s();
            }

            public Builder b(String str) {
                S();
                ((UserPacket) this.f11310a).b(str);
                return this;
            }

            public Builder z() {
                S();
                ((UserPacket) this.f11310a).E();
                return this;
            }

            public Builder c(ByteString byteString) {
                S();
                ((UserPacket) this.f11310a).e(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UserPacket();
                case IS_INITIALIZED:
                    byte b2 = this.u;
                    if (b2 == 1) {
                        return v;
                    }
                    if (b2 == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!c()) {
                        if (booleanValue) {
                            this.u = 0;
                        }
                        return null;
                    } else if (!e()) {
                        if (booleanValue) {
                            this.u = 0;
                        }
                        return null;
                    } else if (!g()) {
                        if (booleanValue) {
                            this.u = 0;
                        }
                        return null;
                    } else {
                        if (booleanValue) {
                            this.u = 1;
                        }
                        return v;
                    }
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    UserPacket userPacket = (UserPacket) obj2;
                    this.j = visitor.a(a(), this.j, userPacket.a(), userPacket.j);
                    this.k = visitor.a(c(), this.k, userPacket.c(), userPacket.k);
                    this.l = visitor.a(e(), this.l, userPacket.e(), userPacket.l);
                    this.p = visitor.a(g(), this.p, userPacket.g(), userPacket.p);
                    this.q = visitor.a(j(), this.q, userPacket.j(), userPacket.q);
                    this.r = visitor.a(m(), this.r, userPacket.m(), userPacket.r);
                    this.s = visitor.a(o(), this.s, userPacket.o(), userPacket.s);
                    this.t = visitor.a(q(), this.t, userPacket.q(), userPacket.t);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.i |= userPacket.i;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 8) {
                                    this.i |= 1;
                                    this.j = codedInputStream.q();
                                } else if (a2 == 16) {
                                    int r2 = codedInputStream.r();
                                    if (PKT_TYPE.forNumber(r2) == null) {
                                        super.a(2, r2);
                                    } else {
                                        this.i |= 2;
                                        this.k = r2;
                                    }
                                } else if (a2 == 24) {
                                    this.i |= 4;
                                    this.l = codedInputStream.f();
                                } else if (a2 == 34) {
                                    String l2 = codedInputStream.l();
                                    this.i = 8 | this.i;
                                    this.p = l2;
                                } else if (a2 == 40) {
                                    this.i |= 16;
                                    this.q = codedInputStream.f();
                                } else if (a2 == 50) {
                                    this.i |= 32;
                                    this.r = codedInputStream.n();
                                } else if (a2 == 56) {
                                    this.i |= 64;
                                    this.s = codedInputStream.g();
                                } else if (a2 == 66) {
                                    String l3 = codedInputStream.l();
                                    this.i |= 128;
                                    this.t = l3;
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
                    if (w == null) {
                        synchronized (UserPacket.class) {
                            if (w == null) {
                                w = new GeneratedMessageLite.DefaultInstanceBasedParser(v);
                            }
                        }
                    }
                    return w;
                default:
                    throw new UnsupportedOperationException();
            }
            return v;
        }

        static {
            v.P();
        }

        public static UserPacket u() {
            return v;
        }

        public static Parser<UserPacket> v() {
            return v.M();
        }
    }

    public static final class BindRelayRequest extends GeneratedMessageLite<BindRelayRequest, Builder> implements BindRelayRequestOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11257a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        /* access modifiers changed from: private */
        public static final BindRelayRequest t = new BindRelayRequest();
        private static volatile Parser<BindRelayRequest> u;
        private int h;
        private long i;
        private String j = "";
        private String k = "";
        private int l;
        private String p = "";
        private StreamConfig q;
        private StreamConfig r;
        private byte s = -1;

        private BindRelayRequest() {
        }

        public boolean a() {
            return (this.h & 1) == 1;
        }

        public long b() {
            return this.i;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.h |= 1;
            this.i = j2;
        }

        /* access modifiers changed from: private */
        public void w() {
            this.h &= -2;
            this.i = 0;
        }

        public boolean c() {
            return (this.h & 2) == 2;
        }

        public String d() {
            return this.j;
        }

        public ByteString e() {
            return ByteString.copyFromUtf8(this.j);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.h |= 2;
                this.j = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void x() {
            this.h &= -3;
            this.j = t().d();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.h |= 2;
                this.j = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean f() {
            return (this.h & 4) == 4;
        }

        public String g() {
            return this.k;
        }

        public ByteString h() {
            return ByteString.copyFromUtf8(this.k);
        }

        /* access modifiers changed from: private */
        public void b(String str) {
            if (str != null) {
                this.h |= 4;
                this.k = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void y() {
            this.h &= -5;
            this.k = t().g();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.h |= 4;
                this.k = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean i() {
            return (this.h & 8) == 8;
        }

        public int j() {
            return this.l;
        }

        /* access modifiers changed from: private */
        public void a(int i2) {
            this.h |= 8;
            this.l = i2;
        }

        /* access modifiers changed from: private */
        public void z() {
            this.h &= -9;
            this.l = 0;
        }

        public boolean l() {
            return (this.h & 16) == 16;
        }

        public String m() {
            return this.p;
        }

        public ByteString n() {
            return ByteString.copyFromUtf8(this.p);
        }

        /* access modifiers changed from: private */
        public void c(String str) {
            if (str != null) {
                this.h |= 16;
                this.p = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void A() {
            this.h &= -17;
            this.p = t().m();
        }

        /* access modifiers changed from: private */
        public void e(ByteString byteString) {
            if (byteString != null) {
                this.h |= 16;
                this.p = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean o() {
            return (this.h & 32) == 32;
        }

        public StreamConfig p() {
            return this.q == null ? StreamConfig.j() : this.q;
        }

        /* access modifiers changed from: private */
        public void a(StreamConfig streamConfig) {
            if (streamConfig != null) {
                this.q = streamConfig;
                this.h |= 32;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(StreamConfig.Builder builder) {
            this.q = (StreamConfig) builder.Z();
            this.h |= 32;
        }

        /* access modifiers changed from: private */
        public void b(StreamConfig streamConfig) {
            if (this.q == null || this.q == StreamConfig.j()) {
                this.q = streamConfig;
            } else {
                this.q = (StreamConfig) ((StreamConfig.Builder) StreamConfig.a(this.q).b(streamConfig)).Y();
            }
            this.h |= 32;
        }

        /* access modifiers changed from: private */
        public void B() {
            this.q = null;
            this.h &= -33;
        }

        public boolean q() {
            return (this.h & 64) == 64;
        }

        public StreamConfig r() {
            return this.r == null ? StreamConfig.j() : this.r;
        }

        /* access modifiers changed from: private */
        public void c(StreamConfig streamConfig) {
            if (streamConfig != null) {
                this.r = streamConfig;
                this.h |= 64;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void b(StreamConfig.Builder builder) {
            this.r = (StreamConfig) builder.Z();
            this.h |= 64;
        }

        /* access modifiers changed from: private */
        public void d(StreamConfig streamConfig) {
            if (this.r == null || this.r == StreamConfig.j()) {
                this.r = streamConfig;
            } else {
                this.r = (StreamConfig) ((StreamConfig.Builder) StreamConfig.a(this.r).b(streamConfig)).Y();
            }
            this.h |= 64;
        }

        /* access modifiers changed from: private */
        public void C() {
            this.r = null;
            this.h &= -65;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.h & 1) == 1) {
                codedOutputStream.b(1, this.i);
            }
            if ((this.h & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.h & 4) == 4) {
                codedOutputStream.a(3, g());
            }
            if ((this.h & 8) == 8) {
                codedOutputStream.b(4, this.l);
            }
            if ((this.h & 16) == 16) {
                codedOutputStream.a(5, m());
            }
            if ((this.h & 32) == 32) {
                codedOutputStream.a(6, (MessageLite) p());
            }
            if ((this.h & 64) == 64) {
                codedOutputStream.a(7, (MessageLite) r());
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int i3 = 0;
            if ((this.h & 1) == 1) {
                i3 = 0 + CodedOutputStream.g(1, this.i);
            }
            if ((this.h & 2) == 2) {
                i3 += CodedOutputStream.b(2, d());
            }
            if ((this.h & 4) == 4) {
                i3 += CodedOutputStream.b(3, g());
            }
            if ((this.h & 8) == 8) {
                i3 += CodedOutputStream.h(4, this.l);
            }
            if ((this.h & 16) == 16) {
                i3 += CodedOutputStream.b(5, m());
            }
            if ((this.h & 32) == 32) {
                i3 += CodedOutputStream.c(6, (MessageLite) p());
            }
            if ((this.h & 64) == 64) {
                i3 += CodedOutputStream.c(7, (MessageLite) r());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static BindRelayRequest a(ByteString byteString) throws InvalidProtocolBufferException {
            return (BindRelayRequest) GeneratedMessageLite.a(t, byteString);
        }

        public static BindRelayRequest a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BindRelayRequest) GeneratedMessageLite.a(t, byteString, extensionRegistryLite);
        }

        public static BindRelayRequest a(byte[] bArr) throws InvalidProtocolBufferException {
            return (BindRelayRequest) GeneratedMessageLite.a(t, bArr);
        }

        public static BindRelayRequest a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BindRelayRequest) GeneratedMessageLite.a(t, bArr, extensionRegistryLite);
        }

        public static BindRelayRequest a(InputStream inputStream) throws IOException {
            return (BindRelayRequest) GeneratedMessageLite.a(t, inputStream);
        }

        public static BindRelayRequest a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BindRelayRequest) GeneratedMessageLite.a(t, inputStream, extensionRegistryLite);
        }

        public static BindRelayRequest b(InputStream inputStream) throws IOException {
            return (BindRelayRequest) b(t, inputStream);
        }

        public static BindRelayRequest b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BindRelayRequest) b(t, inputStream, extensionRegistryLite);
        }

        public static BindRelayRequest a(CodedInputStream codedInputStream) throws IOException {
            return (BindRelayRequest) GeneratedMessageLite.b(t, codedInputStream);
        }

        public static BindRelayRequest a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BindRelayRequest) GeneratedMessageLite.b(t, codedInputStream, extensionRegistryLite);
        }

        public static Builder s() {
            return (Builder) t.Y();
        }

        public static Builder a(BindRelayRequest bindRelayRequest) {
            return (Builder) ((Builder) t.Y()).b(bindRelayRequest);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BindRelayRequest, Builder> implements BindRelayRequestOrBuilder {
            private Builder() {
                super(BindRelayRequest.t);
            }

            public boolean a() {
                return ((BindRelayRequest) this.f11310a).a();
            }

            public long b() {
                return ((BindRelayRequest) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((BindRelayRequest) this.f11310a).a(j);
                return this;
            }

            public Builder k() {
                S();
                ((BindRelayRequest) this.f11310a).w();
                return this;
            }

            public boolean c() {
                return ((BindRelayRequest) this.f11310a).c();
            }

            public String d() {
                return ((BindRelayRequest) this.f11310a).d();
            }

            public ByteString e() {
                return ((BindRelayRequest) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((BindRelayRequest) this.f11310a).a(str);
                return this;
            }

            public Builder s() {
                S();
                ((BindRelayRequest) this.f11310a).x();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((BindRelayRequest) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((BindRelayRequest) this.f11310a).f();
            }

            public String g() {
                return ((BindRelayRequest) this.f11310a).g();
            }

            public ByteString h() {
                return ((BindRelayRequest) this.f11310a).h();
            }

            public Builder b(String str) {
                S();
                ((BindRelayRequest) this.f11310a).b(str);
                return this;
            }

            public Builder t() {
                S();
                ((BindRelayRequest) this.f11310a).y();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((BindRelayRequest) this.f11310a).d(byteString);
                return this;
            }

            public boolean i() {
                return ((BindRelayRequest) this.f11310a).i();
            }

            public int j() {
                return ((BindRelayRequest) this.f11310a).j();
            }

            public Builder a(int i) {
                S();
                ((BindRelayRequest) this.f11310a).a(i);
                return this;
            }

            public Builder u() {
                S();
                ((BindRelayRequest) this.f11310a).z();
                return this;
            }

            public boolean l() {
                return ((BindRelayRequest) this.f11310a).l();
            }

            public String m() {
                return ((BindRelayRequest) this.f11310a).m();
            }

            public ByteString n() {
                return ((BindRelayRequest) this.f11310a).n();
            }

            public Builder c(String str) {
                S();
                ((BindRelayRequest) this.f11310a).c(str);
                return this;
            }

            public Builder v() {
                S();
                ((BindRelayRequest) this.f11310a).A();
                return this;
            }

            public Builder c(ByteString byteString) {
                S();
                ((BindRelayRequest) this.f11310a).e(byteString);
                return this;
            }

            public boolean o() {
                return ((BindRelayRequest) this.f11310a).o();
            }

            public StreamConfig p() {
                return ((BindRelayRequest) this.f11310a).p();
            }

            public Builder a(StreamConfig streamConfig) {
                S();
                ((BindRelayRequest) this.f11310a).a(streamConfig);
                return this;
            }

            public Builder a(StreamConfig.Builder builder) {
                S();
                ((BindRelayRequest) this.f11310a).a(builder);
                return this;
            }

            public Builder b(StreamConfig streamConfig) {
                S();
                ((BindRelayRequest) this.f11310a).b(streamConfig);
                return this;
            }

            public Builder w() {
                S();
                ((BindRelayRequest) this.f11310a).B();
                return this;
            }

            public boolean q() {
                return ((BindRelayRequest) this.f11310a).q();
            }

            public StreamConfig r() {
                return ((BindRelayRequest) this.f11310a).r();
            }

            public Builder c(StreamConfig streamConfig) {
                S();
                ((BindRelayRequest) this.f11310a).c(streamConfig);
                return this;
            }

            public Builder b(StreamConfig.Builder builder) {
                S();
                ((BindRelayRequest) this.f11310a).b(builder);
                return this;
            }

            public Builder d(StreamConfig streamConfig) {
                S();
                ((BindRelayRequest) this.f11310a).d(streamConfig);
                return this;
            }

            public Builder x() {
                S();
                ((BindRelayRequest) this.f11310a).C();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new BindRelayRequest();
                case IS_INITIALIZED:
                    byte b2 = this.s;
                    if (b2 == 1) {
                        return t;
                    }
                    if (b2 == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!a()) {
                        if (booleanValue) {
                            this.s = 0;
                        }
                        return null;
                    } else if (!c()) {
                        if (booleanValue) {
                            this.s = 0;
                        }
                        return null;
                    } else if (o() && !p().h_()) {
                        if (booleanValue) {
                            this.s = 0;
                        }
                        return null;
                    } else if (!q() || r().h_()) {
                        if (booleanValue) {
                            this.s = 1;
                        }
                        return t;
                    } else {
                        if (booleanValue) {
                            this.s = 0;
                        }
                        return null;
                    }
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    BindRelayRequest bindRelayRequest = (BindRelayRequest) obj2;
                    this.i = visitor.a(a(), this.i, bindRelayRequest.a(), bindRelayRequest.i);
                    this.j = visitor.a(c(), this.j, bindRelayRequest.c(), bindRelayRequest.j);
                    this.k = visitor.a(f(), this.k, bindRelayRequest.f(), bindRelayRequest.k);
                    this.l = visitor.a(i(), this.l, bindRelayRequest.i(), bindRelayRequest.l);
                    this.p = visitor.a(l(), this.p, bindRelayRequest.l(), bindRelayRequest.p);
                    this.q = (StreamConfig) visitor.a(this.q, bindRelayRequest.q);
                    this.r = (StreamConfig) visitor.a(this.r, bindRelayRequest.r);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.h |= bindRelayRequest.h;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 8) {
                                    this.h |= 1;
                                    this.i = codedInputStream.f();
                                } else if (a2 == 18) {
                                    String l2 = codedInputStream.l();
                                    this.h |= 2;
                                    this.j = l2;
                                } else if (a2 == 26) {
                                    String l3 = codedInputStream.l();
                                    this.h |= 4;
                                    this.k = l3;
                                } else if (a2 == 32) {
                                    this.h |= 8;
                                    this.l = codedInputStream.h();
                                } else if (a2 == 42) {
                                    String l4 = codedInputStream.l();
                                    this.h |= 16;
                                    this.p = l4;
                                } else if (a2 == 50) {
                                    StreamConfig.Builder builder = (this.h & 32) == 32 ? (StreamConfig.Builder) this.q.Y() : null;
                                    this.q = (StreamConfig) codedInputStream.a(StreamConfig.l(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.q);
                                        this.q = (StreamConfig) builder.Y();
                                    }
                                    this.h |= 32;
                                } else if (a2 == 58) {
                                    StreamConfig.Builder builder2 = (this.h & 64) == 64 ? (StreamConfig.Builder) this.r.Y() : null;
                                    this.r = (StreamConfig) codedInputStream.a(StreamConfig.l(), extensionRegistryLite);
                                    if (builder2 != null) {
                                        builder2.b(this.r);
                                        this.r = (StreamConfig) builder2.Y();
                                    }
                                    this.h |= 64;
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
                    if (u == null) {
                        synchronized (BindRelayRequest.class) {
                            if (u == null) {
                                u = new GeneratedMessageLite.DefaultInstanceBasedParser(t);
                            }
                        }
                    }
                    return u;
                default:
                    throw new UnsupportedOperationException();
            }
            return t;
        }

        static {
            t.P();
        }

        public static BindRelayRequest t() {
            return t;
        }

        public static Parser<BindRelayRequest> u() {
            return t.M();
        }
    }

    public static final class StreamConfig extends GeneratedMessageLite<StreamConfig, Builder> implements StreamConfigOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11265a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        /* access modifiers changed from: private */
        public static final StreamConfig k = new StreamConfig();
        private static volatile Parser<StreamConfig> l;
        private int e;
        private int f = 1;
        private int g;
        private int h;
        private boolean i;
        private byte j = -1;

        private StreamConfig() {
        }

        public boolean a() {
            return (this.e & 1) == 1;
        }

        public STREAM_STRATEGY b() {
            STREAM_STRATEGY forNumber = STREAM_STRATEGY.forNumber(this.f);
            return forNumber == null ? STREAM_STRATEGY.FEC_STRATEGY : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(STREAM_STRATEGY stream_strategy) {
            if (stream_strategy != null) {
                this.e |= 1;
                this.f = stream_strategy.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void n() {
            this.e &= -2;
            this.f = 1;
        }

        public boolean c() {
            return (this.e & 2) == 2;
        }

        public int d() {
            return this.g;
        }

        /* access modifiers changed from: private */
        public void a(int i2) {
            this.e |= 2;
            this.g = i2;
        }

        /* access modifiers changed from: private */
        public void o() {
            this.e &= -3;
            this.g = 0;
        }

        public boolean e() {
            return (this.e & 4) == 4;
        }

        public int f() {
            return this.h;
        }

        /* access modifiers changed from: private */
        public void b(int i2) {
            this.e |= 4;
            this.h = i2;
        }

        /* access modifiers changed from: private */
        public void p() {
            this.e &= -5;
            this.h = 0;
        }

        public boolean g() {
            return (this.e & 8) == 8;
        }

        public boolean h() {
            return this.i;
        }

        /* access modifiers changed from: private */
        public void a(boolean z) {
            this.e |= 8;
            this.i = z;
        }

        /* access modifiers changed from: private */
        public void q() {
            this.e &= -9;
            this.i = false;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.e & 1) == 1) {
                codedOutputStream.g(1, this.f);
            }
            if ((this.e & 2) == 2) {
                codedOutputStream.c(2, this.g);
            }
            if ((this.e & 4) == 4) {
                codedOutputStream.c(3, this.h);
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
                i3 = 0 + CodedOutputStream.m(1, this.f);
            }
            if ((this.e & 2) == 2) {
                i3 += CodedOutputStream.i(2, this.g);
            }
            if ((this.e & 4) == 4) {
                i3 += CodedOutputStream.i(3, this.h);
            }
            if ((this.e & 8) == 8) {
                i3 += CodedOutputStream.b(4, this.i);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static StreamConfig a(ByteString byteString) throws InvalidProtocolBufferException {
            return (StreamConfig) GeneratedMessageLite.a(k, byteString);
        }

        public static StreamConfig a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StreamConfig) GeneratedMessageLite.a(k, byteString, extensionRegistryLite);
        }

        public static StreamConfig a(byte[] bArr) throws InvalidProtocolBufferException {
            return (StreamConfig) GeneratedMessageLite.a(k, bArr);
        }

        public static StreamConfig a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StreamConfig) GeneratedMessageLite.a(k, bArr, extensionRegistryLite);
        }

        public static StreamConfig a(InputStream inputStream) throws IOException {
            return (StreamConfig) GeneratedMessageLite.a(k, inputStream);
        }

        public static StreamConfig a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StreamConfig) GeneratedMessageLite.a(k, inputStream, extensionRegistryLite);
        }

        public static StreamConfig b(InputStream inputStream) throws IOException {
            return (StreamConfig) b(k, inputStream);
        }

        public static StreamConfig b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StreamConfig) b(k, inputStream, extensionRegistryLite);
        }

        public static StreamConfig a(CodedInputStream codedInputStream) throws IOException {
            return (StreamConfig) GeneratedMessageLite.b(k, codedInputStream);
        }

        public static StreamConfig a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StreamConfig) GeneratedMessageLite.b(k, codedInputStream, extensionRegistryLite);
        }

        public static Builder i() {
            return (Builder) k.Y();
        }

        public static Builder a(StreamConfig streamConfig) {
            return (Builder) ((Builder) k.Y()).b(streamConfig);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<StreamConfig, Builder> implements StreamConfigOrBuilder {
            private Builder() {
                super(StreamConfig.k);
            }

            public boolean a() {
                return ((StreamConfig) this.f11310a).a();
            }

            public STREAM_STRATEGY b() {
                return ((StreamConfig) this.f11310a).b();
            }

            public Builder a(STREAM_STRATEGY stream_strategy) {
                S();
                ((StreamConfig) this.f11310a).a(stream_strategy);
                return this;
            }

            public Builder i() {
                S();
                ((StreamConfig) this.f11310a).n();
                return this;
            }

            public boolean c() {
                return ((StreamConfig) this.f11310a).c();
            }

            public int d() {
                return ((StreamConfig) this.f11310a).d();
            }

            public Builder a(int i) {
                S();
                ((StreamConfig) this.f11310a).a(i);
                return this;
            }

            public Builder j() {
                S();
                ((StreamConfig) this.f11310a).o();
                return this;
            }

            public boolean e() {
                return ((StreamConfig) this.f11310a).e();
            }

            public int f() {
                return ((StreamConfig) this.f11310a).f();
            }

            public Builder b(int i) {
                S();
                ((StreamConfig) this.f11310a).b(i);
                return this;
            }

            public Builder k() {
                S();
                ((StreamConfig) this.f11310a).p();
                return this;
            }

            public boolean g() {
                return ((StreamConfig) this.f11310a).g();
            }

            public boolean h() {
                return ((StreamConfig) this.f11310a).h();
            }

            public Builder a(boolean z) {
                S();
                ((StreamConfig) this.f11310a).a(z);
                return this;
            }

            public Builder l() {
                S();
                ((StreamConfig) this.f11310a).q();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new StreamConfig();
                case IS_INITIALIZED:
                    byte b2 = this.j;
                    if (b2 == 1) {
                        return k;
                    }
                    if (b2 == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!a()) {
                        if (booleanValue) {
                            this.j = 0;
                        }
                        return null;
                    }
                    if (booleanValue) {
                        this.j = 1;
                    }
                    return k;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    StreamConfig streamConfig = (StreamConfig) obj2;
                    this.f = visitor.a(a(), this.f, streamConfig.a(), streamConfig.f);
                    this.g = visitor.a(c(), this.g, streamConfig.c(), streamConfig.g);
                    this.h = visitor.a(e(), this.h, streamConfig.e(), streamConfig.h);
                    this.i = visitor.a(g(), this.i, streamConfig.g(), streamConfig.i);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.e |= streamConfig.e;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 8) {
                                    int r = codedInputStream.r();
                                    if (STREAM_STRATEGY.forNumber(r) == null) {
                                        super.a(1, r);
                                    } else {
                                        this.e |= 1;
                                        this.f = r;
                                    }
                                } else if (a2 == 16) {
                                    this.e |= 2;
                                    this.g = codedInputStream.q();
                                } else if (a2 == 24) {
                                    this.e |= 4;
                                    this.h = codedInputStream.q();
                                } else if (a2 == 32) {
                                    this.e |= 8;
                                    this.i = codedInputStream.k();
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
                    if (l == null) {
                        synchronized (StreamConfig.class) {
                            if (l == null) {
                                l = new GeneratedMessageLite.DefaultInstanceBasedParser(k);
                            }
                        }
                    }
                    return l;
                default:
                    throw new UnsupportedOperationException();
            }
            return k;
        }

        static {
            k.P();
        }

        public static StreamConfig j() {
            return k;
        }

        public static Parser<StreamConfig> l() {
            return k.M();
        }
    }

    public static final class BindRelayResponse extends GeneratedMessageLite<BindRelayResponse, Builder> implements BindRelayResponseOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11258a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        /* access modifiers changed from: private */
        public static final BindRelayResponse r = new BindRelayResponse();
        private static volatile Parser<BindRelayResponse> s;
        private int g;
        private boolean h;
        private String i = "";
        private int j;
        private String k = "";
        private int l;
        private int p;
        private byte q = -1;

        private BindRelayResponse() {
        }

        public boolean a() {
            return (this.g & 1) == 1;
        }

        public boolean b() {
            return this.h;
        }

        /* access modifiers changed from: private */
        public void a(boolean z) {
            this.g |= 1;
            this.h = z;
        }

        /* access modifiers changed from: private */
        public void t() {
            this.g &= -2;
            this.h = false;
        }

        public boolean c() {
            return (this.g & 2) == 2;
        }

        public String d() {
            return this.i;
        }

        public ByteString e() {
            return ByteString.copyFromUtf8(this.i);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.g |= 2;
                this.i = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void u() {
            this.g &= -3;
            this.i = q().d();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.g |= 2;
                this.i = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean f() {
            return (this.g & 4) == 4;
        }

        public int g() {
            return this.j;
        }

        /* access modifiers changed from: private */
        public void a(int i2) {
            this.g |= 4;
            this.j = i2;
        }

        /* access modifiers changed from: private */
        public void v() {
            this.g &= -5;
            this.j = 0;
        }

        public boolean h() {
            return (this.g & 8) == 8;
        }

        public String i() {
            return this.k;
        }

        public ByteString j() {
            return ByteString.copyFromUtf8(this.k);
        }

        /* access modifiers changed from: private */
        public void b(String str) {
            if (str != null) {
                this.g |= 8;
                this.k = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void w() {
            this.g &= -9;
            this.k = q().i();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.g |= 8;
                this.k = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean l() {
            return (this.g & 16) == 16;
        }

        public int m() {
            return this.l;
        }

        /* access modifiers changed from: private */
        public void b(int i2) {
            this.g |= 16;
            this.l = i2;
        }

        /* access modifiers changed from: private */
        public void x() {
            this.g &= -17;
            this.l = 0;
        }

        public boolean n() {
            return (this.g & 32) == 32;
        }

        public int o() {
            return this.p;
        }

        /* access modifiers changed from: private */
        public void c(int i2) {
            this.g |= 32;
            this.p = i2;
        }

        /* access modifiers changed from: private */
        public void y() {
            this.g &= -33;
            this.p = 0;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.g & 1) == 1) {
                codedOutputStream.a(1, this.h);
            }
            if ((this.g & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.g & 4) == 4) {
                codedOutputStream.b(3, this.j);
            }
            if ((this.g & 8) == 8) {
                codedOutputStream.a(4, i());
            }
            if ((this.g & 16) == 16) {
                codedOutputStream.b(5, this.l);
            }
            if ((this.g & 32) == 32) {
                codedOutputStream.c(6, this.p);
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
                i3 = 0 + CodedOutputStream.b(1, this.h);
            }
            if ((this.g & 2) == 2) {
                i3 += CodedOutputStream.b(2, d());
            }
            if ((this.g & 4) == 4) {
                i3 += CodedOutputStream.h(3, this.j);
            }
            if ((this.g & 8) == 8) {
                i3 += CodedOutputStream.b(4, i());
            }
            if ((this.g & 16) == 16) {
                i3 += CodedOutputStream.h(5, this.l);
            }
            if ((this.g & 32) == 32) {
                i3 += CodedOutputStream.i(6, this.p);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static BindRelayResponse a(ByteString byteString) throws InvalidProtocolBufferException {
            return (BindRelayResponse) GeneratedMessageLite.a(r, byteString);
        }

        public static BindRelayResponse a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BindRelayResponse) GeneratedMessageLite.a(r, byteString, extensionRegistryLite);
        }

        public static BindRelayResponse a(byte[] bArr) throws InvalidProtocolBufferException {
            return (BindRelayResponse) GeneratedMessageLite.a(r, bArr);
        }

        public static BindRelayResponse a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BindRelayResponse) GeneratedMessageLite.a(r, bArr, extensionRegistryLite);
        }

        public static BindRelayResponse a(InputStream inputStream) throws IOException {
            return (BindRelayResponse) GeneratedMessageLite.a(r, inputStream);
        }

        public static BindRelayResponse a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BindRelayResponse) GeneratedMessageLite.a(r, inputStream, extensionRegistryLite);
        }

        public static BindRelayResponse b(InputStream inputStream) throws IOException {
            return (BindRelayResponse) b(r, inputStream);
        }

        public static BindRelayResponse b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BindRelayResponse) b(r, inputStream, extensionRegistryLite);
        }

        public static BindRelayResponse a(CodedInputStream codedInputStream) throws IOException {
            return (BindRelayResponse) GeneratedMessageLite.b(r, codedInputStream);
        }

        public static BindRelayResponse a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BindRelayResponse) GeneratedMessageLite.b(r, codedInputStream, extensionRegistryLite);
        }

        public static Builder p() {
            return (Builder) r.Y();
        }

        public static Builder a(BindRelayResponse bindRelayResponse) {
            return (Builder) ((Builder) r.Y()).b(bindRelayResponse);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BindRelayResponse, Builder> implements BindRelayResponseOrBuilder {
            private Builder() {
                super(BindRelayResponse.r);
            }

            public boolean a() {
                return ((BindRelayResponse) this.f11310a).a();
            }

            public boolean b() {
                return ((BindRelayResponse) this.f11310a).b();
            }

            public Builder a(boolean z) {
                S();
                ((BindRelayResponse) this.f11310a).a(z);
                return this;
            }

            public Builder k() {
                S();
                ((BindRelayResponse) this.f11310a).t();
                return this;
            }

            public boolean c() {
                return ((BindRelayResponse) this.f11310a).c();
            }

            public String d() {
                return ((BindRelayResponse) this.f11310a).d();
            }

            public ByteString e() {
                return ((BindRelayResponse) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((BindRelayResponse) this.f11310a).a(str);
                return this;
            }

            public Builder p() {
                S();
                ((BindRelayResponse) this.f11310a).u();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((BindRelayResponse) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((BindRelayResponse) this.f11310a).f();
            }

            public int g() {
                return ((BindRelayResponse) this.f11310a).g();
            }

            public Builder a(int i) {
                S();
                ((BindRelayResponse) this.f11310a).a(i);
                return this;
            }

            public Builder q() {
                S();
                ((BindRelayResponse) this.f11310a).v();
                return this;
            }

            public boolean h() {
                return ((BindRelayResponse) this.f11310a).h();
            }

            public String i() {
                return ((BindRelayResponse) this.f11310a).i();
            }

            public ByteString j() {
                return ((BindRelayResponse) this.f11310a).j();
            }

            public Builder b(String str) {
                S();
                ((BindRelayResponse) this.f11310a).b(str);
                return this;
            }

            public Builder r() {
                S();
                ((BindRelayResponse) this.f11310a).w();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((BindRelayResponse) this.f11310a).d(byteString);
                return this;
            }

            public boolean l() {
                return ((BindRelayResponse) this.f11310a).l();
            }

            public int m() {
                return ((BindRelayResponse) this.f11310a).m();
            }

            public Builder b(int i) {
                S();
                ((BindRelayResponse) this.f11310a).b(i);
                return this;
            }

            public Builder s() {
                S();
                ((BindRelayResponse) this.f11310a).x();
                return this;
            }

            public boolean n() {
                return ((BindRelayResponse) this.f11310a).n();
            }

            public int o() {
                return ((BindRelayResponse) this.f11310a).o();
            }

            public Builder c(int i) {
                S();
                ((BindRelayResponse) this.f11310a).c(i);
                return this;
            }

            public Builder t() {
                S();
                ((BindRelayResponse) this.f11310a).y();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new BindRelayResponse();
                case IS_INITIALIZED:
                    byte b2 = this.q;
                    if (b2 == 1) {
                        return r;
                    }
                    if (b2 == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!a()) {
                        if (booleanValue) {
                            this.q = 0;
                        }
                        return null;
                    } else if (!c()) {
                        if (booleanValue) {
                            this.q = 0;
                        }
                        return null;
                    } else if (!f()) {
                        if (booleanValue) {
                            this.q = 0;
                        }
                        return null;
                    } else {
                        if (booleanValue) {
                            this.q = 1;
                        }
                        return r;
                    }
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    BindRelayResponse bindRelayResponse = (BindRelayResponse) obj2;
                    this.h = visitor.a(a(), this.h, bindRelayResponse.a(), bindRelayResponse.h);
                    this.i = visitor.a(c(), this.i, bindRelayResponse.c(), bindRelayResponse.i);
                    this.j = visitor.a(f(), this.j, bindRelayResponse.f(), bindRelayResponse.j);
                    this.k = visitor.a(h(), this.k, bindRelayResponse.h(), bindRelayResponse.k);
                    this.l = visitor.a(l(), this.l, bindRelayResponse.l(), bindRelayResponse.l);
                    this.p = visitor.a(n(), this.p, bindRelayResponse.n(), bindRelayResponse.p);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.g |= bindRelayResponse.g;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 8) {
                                    this.g |= 1;
                                    this.h = codedInputStream.k();
                                } else if (a2 == 18) {
                                    String l2 = codedInputStream.l();
                                    this.g |= 2;
                                    this.i = l2;
                                } else if (a2 == 24) {
                                    this.g |= 4;
                                    this.j = codedInputStream.h();
                                } else if (a2 == 34) {
                                    String l3 = codedInputStream.l();
                                    this.g = 8 | this.g;
                                    this.k = l3;
                                } else if (a2 == 40) {
                                    this.g |= 16;
                                    this.l = codedInputStream.h();
                                } else if (a2 == 48) {
                                    this.g |= 32;
                                    this.p = codedInputStream.q();
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
                    if (s == null) {
                        synchronized (BindRelayResponse.class) {
                            if (s == null) {
                                s = new GeneratedMessageLite.DefaultInstanceBasedParser(r);
                            }
                        }
                    }
                    return s;
                default:
                    throw new UnsupportedOperationException();
            }
            return r;
        }

        static {
            r.P();
        }

        public static BindRelayResponse q() {
            return r;
        }

        public static Parser<BindRelayResponse> r() {
            return r.M();
        }
    }

    public static final class PingRelayRequest extends GeneratedMessageLite<PingRelayRequest, Builder> implements PingRelayRequestOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11260a = 1;
        public static final int b = 2;
        /* access modifiers changed from: private */
        public static final PingRelayRequest g = new PingRelayRequest();
        private static volatile Parser<PingRelayRequest> h;
        private int c;
        private long d;
        private String e = "";
        private byte f = -1;

        private PingRelayRequest() {
        }

        public boolean a() {
            return (this.c & 1) == 1;
        }

        public long b() {
            return this.d;
        }

        /* access modifiers changed from: private */
        public void a(long j) {
            this.c |= 1;
            this.d = j;
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
                i2 = 0 + CodedOutputStream.g(1, this.d);
            }
            if ((this.c & 2) == 2) {
                i2 += CodedOutputStream.b(2, d());
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static PingRelayRequest a(ByteString byteString) throws InvalidProtocolBufferException {
            return (PingRelayRequest) GeneratedMessageLite.a(g, byteString);
        }

        public static PingRelayRequest a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PingRelayRequest) GeneratedMessageLite.a(g, byteString, extensionRegistryLite);
        }

        public static PingRelayRequest a(byte[] bArr) throws InvalidProtocolBufferException {
            return (PingRelayRequest) GeneratedMessageLite.a(g, bArr);
        }

        public static PingRelayRequest a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PingRelayRequest) GeneratedMessageLite.a(g, bArr, extensionRegistryLite);
        }

        public static PingRelayRequest a(InputStream inputStream) throws IOException {
            return (PingRelayRequest) GeneratedMessageLite.a(g, inputStream);
        }

        public static PingRelayRequest a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PingRelayRequest) GeneratedMessageLite.a(g, inputStream, extensionRegistryLite);
        }

        public static PingRelayRequest b(InputStream inputStream) throws IOException {
            return (PingRelayRequest) b(g, inputStream);
        }

        public static PingRelayRequest b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PingRelayRequest) b(g, inputStream, extensionRegistryLite);
        }

        public static PingRelayRequest a(CodedInputStream codedInputStream) throws IOException {
            return (PingRelayRequest) GeneratedMessageLite.b(g, codedInputStream);
        }

        public static PingRelayRequest a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PingRelayRequest) GeneratedMessageLite.b(g, codedInputStream, extensionRegistryLite);
        }

        public static Builder f() {
            return (Builder) g.Y();
        }

        public static Builder a(PingRelayRequest pingRelayRequest) {
            return (Builder) ((Builder) g.Y()).b(pingRelayRequest);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PingRelayRequest, Builder> implements PingRelayRequestOrBuilder {
            private Builder() {
                super(PingRelayRequest.g);
            }

            public boolean a() {
                return ((PingRelayRequest) this.f11310a).a();
            }

            public long b() {
                return ((PingRelayRequest) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((PingRelayRequest) this.f11310a).a(j);
                return this;
            }

            public Builder f() {
                S();
                ((PingRelayRequest) this.f11310a).j();
                return this;
            }

            public boolean c() {
                return ((PingRelayRequest) this.f11310a).c();
            }

            public String d() {
                return ((PingRelayRequest) this.f11310a).d();
            }

            public ByteString e() {
                return ((PingRelayRequest) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((PingRelayRequest) this.f11310a).a(str);
                return this;
            }

            public Builder g() {
                S();
                ((PingRelayRequest) this.f11310a).l();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((PingRelayRequest) this.f11310a).c(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new PingRelayRequest();
                case IS_INITIALIZED:
                    byte b2 = this.f;
                    if (b2 == 1) {
                        return g;
                    }
                    if (b2 == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!a()) {
                        if (booleanValue) {
                            this.f = 0;
                        }
                        return null;
                    } else if (!c()) {
                        if (booleanValue) {
                            this.f = 0;
                        }
                        return null;
                    } else {
                        if (booleanValue) {
                            this.f = 1;
                        }
                        return g;
                    }
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    PingRelayRequest pingRelayRequest = (PingRelayRequest) obj2;
                    this.d = visitor.a(a(), this.d, pingRelayRequest.a(), pingRelayRequest.d);
                    this.e = visitor.a(c(), this.e, pingRelayRequest.c(), pingRelayRequest.e);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.c |= pingRelayRequest.c;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 8) {
                                    this.c |= 1;
                                    this.d = codedInputStream.f();
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
                    if (h == null) {
                        synchronized (PingRelayRequest.class) {
                            if (h == null) {
                                h = new GeneratedMessageLite.DefaultInstanceBasedParser(g);
                            }
                        }
                    }
                    return h;
                default:
                    throw new UnsupportedOperationException();
            }
            return g;
        }

        static {
            g.P();
        }

        public static PingRelayRequest g() {
            return g;
        }

        public static Parser<PingRelayRequest> h() {
            return g.M();
        }
    }

    public static final class PingRelayResponse extends GeneratedMessageLite<PingRelayResponse, Builder> implements PingRelayResponseOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11261a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        /* access modifiers changed from: private */
        public static final PingRelayResponse k = new PingRelayResponse();
        private static volatile Parser<PingRelayResponse> l;
        private int e;
        private boolean f;
        private String g = "";
        private int h;
        private int i;
        private byte j = -1;

        private PingRelayResponse() {
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
        public void o() {
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
        public void p() {
            this.e &= -3;
            this.g = l().d();
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

        public int g() {
            return this.h;
        }

        /* access modifiers changed from: private */
        public void a(int i2) {
            this.e |= 4;
            this.h = i2;
        }

        /* access modifiers changed from: private */
        public void q() {
            this.e &= -5;
            this.h = 0;
        }

        public boolean h() {
            return (this.e & 8) == 8;
        }

        public int i() {
            return this.i;
        }

        /* access modifiers changed from: private */
        public void b(int i2) {
            this.e |= 8;
            this.i = i2;
        }

        /* access modifiers changed from: private */
        public void r() {
            this.e &= -9;
            this.i = 0;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.e & 1) == 1) {
                codedOutputStream.a(1, this.f);
            }
            if ((this.e & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.e & 4) == 4) {
                codedOutputStream.b(3, this.h);
            }
            if ((this.e & 8) == 8) {
                codedOutputStream.c(4, this.i);
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
                i3 += CodedOutputStream.h(3, this.h);
            }
            if ((this.e & 8) == 8) {
                i3 += CodedOutputStream.i(4, this.i);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static PingRelayResponse a(ByteString byteString) throws InvalidProtocolBufferException {
            return (PingRelayResponse) GeneratedMessageLite.a(k, byteString);
        }

        public static PingRelayResponse a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PingRelayResponse) GeneratedMessageLite.a(k, byteString, extensionRegistryLite);
        }

        public static PingRelayResponse a(byte[] bArr) throws InvalidProtocolBufferException {
            return (PingRelayResponse) GeneratedMessageLite.a(k, bArr);
        }

        public static PingRelayResponse a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PingRelayResponse) GeneratedMessageLite.a(k, bArr, extensionRegistryLite);
        }

        public static PingRelayResponse a(InputStream inputStream) throws IOException {
            return (PingRelayResponse) GeneratedMessageLite.a(k, inputStream);
        }

        public static PingRelayResponse a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PingRelayResponse) GeneratedMessageLite.a(k, inputStream, extensionRegistryLite);
        }

        public static PingRelayResponse b(InputStream inputStream) throws IOException {
            return (PingRelayResponse) b(k, inputStream);
        }

        public static PingRelayResponse b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PingRelayResponse) b(k, inputStream, extensionRegistryLite);
        }

        public static PingRelayResponse a(CodedInputStream codedInputStream) throws IOException {
            return (PingRelayResponse) GeneratedMessageLite.b(k, codedInputStream);
        }

        public static PingRelayResponse a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PingRelayResponse) GeneratedMessageLite.b(k, codedInputStream, extensionRegistryLite);
        }

        public static Builder j() {
            return (Builder) k.Y();
        }

        public static Builder a(PingRelayResponse pingRelayResponse) {
            return (Builder) ((Builder) k.Y()).b(pingRelayResponse);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PingRelayResponse, Builder> implements PingRelayResponseOrBuilder {
            private Builder() {
                super(PingRelayResponse.k);
            }

            public boolean a() {
                return ((PingRelayResponse) this.f11310a).a();
            }

            public boolean b() {
                return ((PingRelayResponse) this.f11310a).b();
            }

            public Builder a(boolean z) {
                S();
                ((PingRelayResponse) this.f11310a).a(z);
                return this;
            }

            public Builder j() {
                S();
                ((PingRelayResponse) this.f11310a).o();
                return this;
            }

            public boolean c() {
                return ((PingRelayResponse) this.f11310a).c();
            }

            public String d() {
                return ((PingRelayResponse) this.f11310a).d();
            }

            public ByteString e() {
                return ((PingRelayResponse) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((PingRelayResponse) this.f11310a).a(str);
                return this;
            }

            public Builder k() {
                S();
                ((PingRelayResponse) this.f11310a).p();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((PingRelayResponse) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((PingRelayResponse) this.f11310a).f();
            }

            public int g() {
                return ((PingRelayResponse) this.f11310a).g();
            }

            public Builder a(int i) {
                S();
                ((PingRelayResponse) this.f11310a).a(i);
                return this;
            }

            public Builder l() {
                S();
                ((PingRelayResponse) this.f11310a).q();
                return this;
            }

            public boolean h() {
                return ((PingRelayResponse) this.f11310a).h();
            }

            public int i() {
                return ((PingRelayResponse) this.f11310a).i();
            }

            public Builder b(int i) {
                S();
                ((PingRelayResponse) this.f11310a).b(i);
                return this;
            }

            public Builder m() {
                S();
                ((PingRelayResponse) this.f11310a).r();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new PingRelayResponse();
                case IS_INITIALIZED:
                    byte b2 = this.j;
                    if (b2 == 1) {
                        return k;
                    }
                    if (b2 == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!a()) {
                        if (booleanValue) {
                            this.j = 0;
                        }
                        return null;
                    } else if (!c()) {
                        if (booleanValue) {
                            this.j = 0;
                        }
                        return null;
                    } else if (!f()) {
                        if (booleanValue) {
                            this.j = 0;
                        }
                        return null;
                    } else {
                        if (booleanValue) {
                            this.j = 1;
                        }
                        return k;
                    }
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    PingRelayResponse pingRelayResponse = (PingRelayResponse) obj2;
                    this.f = visitor.a(a(), this.f, pingRelayResponse.a(), pingRelayResponse.f);
                    this.g = visitor.a(c(), this.g, pingRelayResponse.c(), pingRelayResponse.g);
                    this.h = visitor.a(f(), this.h, pingRelayResponse.f(), pingRelayResponse.h);
                    this.i = visitor.a(h(), this.i, pingRelayResponse.h(), pingRelayResponse.i);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.e |= pingRelayResponse.e;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 8) {
                                    this.e |= 1;
                                    this.f = codedInputStream.k();
                                } else if (a2 == 18) {
                                    String l2 = codedInputStream.l();
                                    this.e |= 2;
                                    this.g = l2;
                                } else if (a2 == 24) {
                                    this.e |= 4;
                                    this.h = codedInputStream.h();
                                } else if (a2 == 32) {
                                    this.e |= 8;
                                    this.i = codedInputStream.q();
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
                    if (l == null) {
                        synchronized (PingRelayResponse.class) {
                            if (l == null) {
                                l = new GeneratedMessageLite.DefaultInstanceBasedParser(k);
                            }
                        }
                    }
                    return l;
                default:
                    throw new UnsupportedOperationException();
            }
            return k;
        }

        static {
            k.P();
        }

        public static PingRelayResponse l() {
            return k;
        }

        public static Parser<PingRelayResponse> m() {
            return k.M();
        }
    }

    public static final class UnBindRelayRequest extends GeneratedMessageLite<UnBindRelayRequest, Builder> implements UnBindRelayRequestOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11266a = 1;
        public static final int b = 2;
        public static final int c = 3;
        /* access modifiers changed from: private */
        public static final UnBindRelayRequest i = new UnBindRelayRequest();
        private static volatile Parser<UnBindRelayRequest> j;
        private int d;
        private long e;
        private String f = "";
        private String g = "";
        private byte h = -1;

        private UnBindRelayRequest() {
        }

        public boolean a() {
            return (this.d & 1) == 1;
        }

        public long b() {
            return this.e;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.d |= 1;
            this.e = j2;
        }

        /* access modifiers changed from: private */
        public void n() {
            this.d &= -2;
            this.e = 0;
        }

        public boolean c() {
            return (this.d & 2) == 2;
        }

        public String d() {
            return this.f;
        }

        public ByteString e() {
            return ByteString.copyFromUtf8(this.f);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.d |= 2;
                this.f = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void o() {
            this.d &= -3;
            this.f = j().d();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.d |= 2;
                this.f = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean f() {
            return (this.d & 4) == 4;
        }

        public String g() {
            return this.g;
        }

        public ByteString h() {
            return ByteString.copyFromUtf8(this.g);
        }

        /* access modifiers changed from: private */
        public void b(String str) {
            if (str != null) {
                this.d |= 4;
                this.g = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void p() {
            this.d &= -5;
            this.g = j().g();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.d |= 4;
                this.g = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.d & 1) == 1) {
                codedOutputStream.b(1, this.e);
            }
            if ((this.d & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.d & 4) == 4) {
                codedOutputStream.a(3, g());
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int i3 = 0;
            if ((this.d & 1) == 1) {
                i3 = 0 + CodedOutputStream.g(1, this.e);
            }
            if ((this.d & 2) == 2) {
                i3 += CodedOutputStream.b(2, d());
            }
            if ((this.d & 4) == 4) {
                i3 += CodedOutputStream.b(3, g());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UnBindRelayRequest a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UnBindRelayRequest) GeneratedMessageLite.a(i, byteString);
        }

        public static UnBindRelayRequest a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnBindRelayRequest) GeneratedMessageLite.a(i, byteString, extensionRegistryLite);
        }

        public static UnBindRelayRequest a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UnBindRelayRequest) GeneratedMessageLite.a(i, bArr);
        }

        public static UnBindRelayRequest a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnBindRelayRequest) GeneratedMessageLite.a(i, bArr, extensionRegistryLite);
        }

        public static UnBindRelayRequest a(InputStream inputStream) throws IOException {
            return (UnBindRelayRequest) GeneratedMessageLite.a(i, inputStream);
        }

        public static UnBindRelayRequest a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnBindRelayRequest) GeneratedMessageLite.a(i, inputStream, extensionRegistryLite);
        }

        public static UnBindRelayRequest b(InputStream inputStream) throws IOException {
            return (UnBindRelayRequest) b(i, inputStream);
        }

        public static UnBindRelayRequest b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnBindRelayRequest) b(i, inputStream, extensionRegistryLite);
        }

        public static UnBindRelayRequest a(CodedInputStream codedInputStream) throws IOException {
            return (UnBindRelayRequest) GeneratedMessageLite.b(i, codedInputStream);
        }

        public static UnBindRelayRequest a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnBindRelayRequest) GeneratedMessageLite.b(i, codedInputStream, extensionRegistryLite);
        }

        public static Builder i() {
            return (Builder) i.Y();
        }

        public static Builder a(UnBindRelayRequest unBindRelayRequest) {
            return (Builder) ((Builder) i.Y()).b(unBindRelayRequest);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UnBindRelayRequest, Builder> implements UnBindRelayRequestOrBuilder {
            private Builder() {
                super(UnBindRelayRequest.i);
            }

            public boolean a() {
                return ((UnBindRelayRequest) this.f11310a).a();
            }

            public long b() {
                return ((UnBindRelayRequest) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((UnBindRelayRequest) this.f11310a).a(j);
                return this;
            }

            public Builder i() {
                S();
                ((UnBindRelayRequest) this.f11310a).n();
                return this;
            }

            public boolean c() {
                return ((UnBindRelayRequest) this.f11310a).c();
            }

            public String d() {
                return ((UnBindRelayRequest) this.f11310a).d();
            }

            public ByteString e() {
                return ((UnBindRelayRequest) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((UnBindRelayRequest) this.f11310a).a(str);
                return this;
            }

            public Builder j() {
                S();
                ((UnBindRelayRequest) this.f11310a).o();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((UnBindRelayRequest) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((UnBindRelayRequest) this.f11310a).f();
            }

            public String g() {
                return ((UnBindRelayRequest) this.f11310a).g();
            }

            public ByteString h() {
                return ((UnBindRelayRequest) this.f11310a).h();
            }

            public Builder b(String str) {
                S();
                ((UnBindRelayRequest) this.f11310a).b(str);
                return this;
            }

            public Builder k() {
                S();
                ((UnBindRelayRequest) this.f11310a).p();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((UnBindRelayRequest) this.f11310a).d(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UnBindRelayRequest();
                case IS_INITIALIZED:
                    byte b2 = this.h;
                    if (b2 == 1) {
                        return i;
                    }
                    if (b2 == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!a()) {
                        if (booleanValue) {
                            this.h = 0;
                        }
                        return null;
                    } else if (!c()) {
                        if (booleanValue) {
                            this.h = 0;
                        }
                        return null;
                    } else {
                        if (booleanValue) {
                            this.h = 1;
                        }
                        return i;
                    }
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    UnBindRelayRequest unBindRelayRequest = (UnBindRelayRequest) obj2;
                    this.e = visitor.a(a(), this.e, unBindRelayRequest.a(), unBindRelayRequest.e);
                    this.f = visitor.a(c(), this.f, unBindRelayRequest.c(), unBindRelayRequest.f);
                    this.g = visitor.a(f(), this.g, unBindRelayRequest.f(), unBindRelayRequest.g);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.d |= unBindRelayRequest.d;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 8) {
                                    this.d |= 1;
                                    this.e = codedInputStream.f();
                                } else if (a2 == 18) {
                                    String l = codedInputStream.l();
                                    this.d |= 2;
                                    this.f = l;
                                } else if (a2 == 26) {
                                    String l2 = codedInputStream.l();
                                    this.d |= 4;
                                    this.g = l2;
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
                    if (j == null) {
                        synchronized (UnBindRelayRequest.class) {
                            if (j == null) {
                                j = new GeneratedMessageLite.DefaultInstanceBasedParser(i);
                            }
                        }
                    }
                    return j;
                default:
                    throw new UnsupportedOperationException();
            }
            return i;
        }

        static {
            i.P();
        }

        public static UnBindRelayRequest j() {
            return i;
        }

        public static Parser<UnBindRelayRequest> l() {
            return i.M();
        }
    }

    public static final class UnBindRelayResponse extends GeneratedMessageLite<UnBindRelayResponse, Builder> implements UnBindRelayResponseOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11267a = 1;
        /* access modifiers changed from: private */
        public static final UnBindRelayResponse e = new UnBindRelayResponse();
        private static volatile Parser<UnBindRelayResponse> f;
        private int b;
        private boolean c;
        private byte d = -1;

        private UnBindRelayResponse() {
        }

        public boolean a() {
            return (this.b & 1) == 1;
        }

        public boolean b() {
            return this.c;
        }

        /* access modifiers changed from: private */
        public void a(boolean z) {
            this.b |= 1;
            this.c = z;
        }

        /* access modifiers changed from: private */
        public void g() {
            this.b &= -2;
            this.c = false;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.b & 1) == 1) {
                codedOutputStream.a(1, this.c);
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i = this.o;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.b & 1) == 1) {
                i2 = 0 + CodedOutputStream.b(1, this.c);
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UnBindRelayResponse a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UnBindRelayResponse) GeneratedMessageLite.a(e, byteString);
        }

        public static UnBindRelayResponse a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnBindRelayResponse) GeneratedMessageLite.a(e, byteString, extensionRegistryLite);
        }

        public static UnBindRelayResponse a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UnBindRelayResponse) GeneratedMessageLite.a(e, bArr);
        }

        public static UnBindRelayResponse a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnBindRelayResponse) GeneratedMessageLite.a(e, bArr, extensionRegistryLite);
        }

        public static UnBindRelayResponse a(InputStream inputStream) throws IOException {
            return (UnBindRelayResponse) GeneratedMessageLite.a(e, inputStream);
        }

        public static UnBindRelayResponse a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnBindRelayResponse) GeneratedMessageLite.a(e, inputStream, extensionRegistryLite);
        }

        public static UnBindRelayResponse b(InputStream inputStream) throws IOException {
            return (UnBindRelayResponse) b(e, inputStream);
        }

        public static UnBindRelayResponse b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnBindRelayResponse) b(e, inputStream, extensionRegistryLite);
        }

        public static UnBindRelayResponse a(CodedInputStream codedInputStream) throws IOException {
            return (UnBindRelayResponse) GeneratedMessageLite.b(e, codedInputStream);
        }

        public static UnBindRelayResponse a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnBindRelayResponse) GeneratedMessageLite.b(e, codedInputStream, extensionRegistryLite);
        }

        public static Builder c() {
            return (Builder) e.Y();
        }

        public static Builder a(UnBindRelayResponse unBindRelayResponse) {
            return (Builder) ((Builder) e.Y()).b(unBindRelayResponse);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UnBindRelayResponse, Builder> implements UnBindRelayResponseOrBuilder {
            private Builder() {
                super(UnBindRelayResponse.e);
            }

            public boolean a() {
                return ((UnBindRelayResponse) this.f11310a).a();
            }

            public boolean b() {
                return ((UnBindRelayResponse) this.f11310a).b();
            }

            public Builder a(boolean z) {
                S();
                ((UnBindRelayResponse) this.f11310a).a(z);
                return this;
            }

            public Builder c() {
                S();
                ((UnBindRelayResponse) this.f11310a).g();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UnBindRelayResponse();
                case IS_INITIALIZED:
                    byte b2 = this.d;
                    if (b2 == 1) {
                        return e;
                    }
                    if (b2 == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!a()) {
                        if (booleanValue) {
                            this.d = 0;
                        }
                        return null;
                    }
                    if (booleanValue) {
                        this.d = 1;
                    }
                    return e;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    UnBindRelayResponse unBindRelayResponse = (UnBindRelayResponse) obj2;
                    this.c = visitor.a(a(), this.c, unBindRelayResponse.a(), unBindRelayResponse.c);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.b |= unBindRelayResponse.b;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 8) {
                                    this.b |= 1;
                                    this.c = codedInputStream.k();
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
                    if (f == null) {
                        synchronized (UnBindRelayResponse.class) {
                            if (f == null) {
                                f = new GeneratedMessageLite.DefaultInstanceBasedParser(e);
                            }
                        }
                    }
                    return f;
                default:
                    throw new UnsupportedOperationException();
            }
            return e;
        }

        static {
            e.P();
        }

        public static UnBindRelayResponse d() {
            return e;
        }

        public static Parser<UnBindRelayResponse> e() {
            return e.M();
        }
    }

    public static final class RelayKickRequest extends GeneratedMessageLite<RelayKickRequest, Builder> implements RelayKickRequestOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11262a = 1;
        /* access modifiers changed from: private */
        public static final RelayKickRequest e = new RelayKickRequest();
        private static volatile Parser<RelayKickRequest> f;
        private int b;
        private int c = 1;
        private byte d = -1;

        private RelayKickRequest() {
        }

        public boolean a() {
            return (this.b & 1) == 1;
        }

        public KICK_REASON b() {
            KICK_REASON forNumber = KICK_REASON.forNumber(this.c);
            return forNumber == null ? KICK_REASON.REALY_CHEANGED : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(KICK_REASON kick_reason) {
            if (kick_reason != null) {
                this.b |= 1;
                this.c = kick_reason.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void g() {
            this.b &= -2;
            this.c = 1;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.b & 1) == 1) {
                codedOutputStream.g(1, this.c);
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i = this.o;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.b & 1) == 1) {
                i2 = 0 + CodedOutputStream.m(1, this.c);
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static RelayKickRequest a(ByteString byteString) throws InvalidProtocolBufferException {
            return (RelayKickRequest) GeneratedMessageLite.a(e, byteString);
        }

        public static RelayKickRequest a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RelayKickRequest) GeneratedMessageLite.a(e, byteString, extensionRegistryLite);
        }

        public static RelayKickRequest a(byte[] bArr) throws InvalidProtocolBufferException {
            return (RelayKickRequest) GeneratedMessageLite.a(e, bArr);
        }

        public static RelayKickRequest a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RelayKickRequest) GeneratedMessageLite.a(e, bArr, extensionRegistryLite);
        }

        public static RelayKickRequest a(InputStream inputStream) throws IOException {
            return (RelayKickRequest) GeneratedMessageLite.a(e, inputStream);
        }

        public static RelayKickRequest a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RelayKickRequest) GeneratedMessageLite.a(e, inputStream, extensionRegistryLite);
        }

        public static RelayKickRequest b(InputStream inputStream) throws IOException {
            return (RelayKickRequest) b(e, inputStream);
        }

        public static RelayKickRequest b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RelayKickRequest) b(e, inputStream, extensionRegistryLite);
        }

        public static RelayKickRequest a(CodedInputStream codedInputStream) throws IOException {
            return (RelayKickRequest) GeneratedMessageLite.b(e, codedInputStream);
        }

        public static RelayKickRequest a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RelayKickRequest) GeneratedMessageLite.b(e, codedInputStream, extensionRegistryLite);
        }

        public static Builder c() {
            return (Builder) e.Y();
        }

        public static Builder a(RelayKickRequest relayKickRequest) {
            return (Builder) ((Builder) e.Y()).b(relayKickRequest);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<RelayKickRequest, Builder> implements RelayKickRequestOrBuilder {
            private Builder() {
                super(RelayKickRequest.e);
            }

            public boolean a() {
                return ((RelayKickRequest) this.f11310a).a();
            }

            public KICK_REASON b() {
                return ((RelayKickRequest) this.f11310a).b();
            }

            public Builder a(KICK_REASON kick_reason) {
                S();
                ((RelayKickRequest) this.f11310a).a(kick_reason);
                return this;
            }

            public Builder c() {
                S();
                ((RelayKickRequest) this.f11310a).g();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new RelayKickRequest();
                case IS_INITIALIZED:
                    byte b2 = this.d;
                    if (b2 == 1) {
                        return e;
                    }
                    if (b2 == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!a()) {
                        if (booleanValue) {
                            this.d = 0;
                        }
                        return null;
                    }
                    if (booleanValue) {
                        this.d = 1;
                    }
                    return e;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    RelayKickRequest relayKickRequest = (RelayKickRequest) obj2;
                    this.c = visitor.a(a(), this.c, relayKickRequest.a(), relayKickRequest.c);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.b |= relayKickRequest.b;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 8) {
                                    int r = codedInputStream.r();
                                    if (KICK_REASON.forNumber(r) == null) {
                                        super.a(1, r);
                                    } else {
                                        this.b |= 1;
                                        this.c = r;
                                    }
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
                    if (f == null) {
                        synchronized (RelayKickRequest.class) {
                            if (f == null) {
                                f = new GeneratedMessageLite.DefaultInstanceBasedParser(e);
                            }
                        }
                    }
                    return f;
                default:
                    throw new UnsupportedOperationException();
            }
            return e;
        }

        static {
            e.P();
        }

        public static RelayKickRequest d() {
            return e;
        }

        public static Parser<RelayKickRequest> e() {
            return e.M();
        }
    }

    public static final class RelayKickResponse extends GeneratedMessageLite<RelayKickResponse, Builder> implements RelayKickResponseOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11263a = 1;
        /* access modifiers changed from: private */
        public static final RelayKickResponse e = new RelayKickResponse();
        private static volatile Parser<RelayKickResponse> f;
        private int b;
        private boolean c;
        private byte d = -1;

        private RelayKickResponse() {
        }

        public boolean a() {
            return (this.b & 1) == 1;
        }

        public boolean b() {
            return this.c;
        }

        /* access modifiers changed from: private */
        public void a(boolean z) {
            this.b |= 1;
            this.c = z;
        }

        /* access modifiers changed from: private */
        public void g() {
            this.b &= -2;
            this.c = false;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.b & 1) == 1) {
                codedOutputStream.a(1, this.c);
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i = this.o;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.b & 1) == 1) {
                i2 = 0 + CodedOutputStream.b(1, this.c);
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static RelayKickResponse a(ByteString byteString) throws InvalidProtocolBufferException {
            return (RelayKickResponse) GeneratedMessageLite.a(e, byteString);
        }

        public static RelayKickResponse a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RelayKickResponse) GeneratedMessageLite.a(e, byteString, extensionRegistryLite);
        }

        public static RelayKickResponse a(byte[] bArr) throws InvalidProtocolBufferException {
            return (RelayKickResponse) GeneratedMessageLite.a(e, bArr);
        }

        public static RelayKickResponse a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RelayKickResponse) GeneratedMessageLite.a(e, bArr, extensionRegistryLite);
        }

        public static RelayKickResponse a(InputStream inputStream) throws IOException {
            return (RelayKickResponse) GeneratedMessageLite.a(e, inputStream);
        }

        public static RelayKickResponse a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RelayKickResponse) GeneratedMessageLite.a(e, inputStream, extensionRegistryLite);
        }

        public static RelayKickResponse b(InputStream inputStream) throws IOException {
            return (RelayKickResponse) b(e, inputStream);
        }

        public static RelayKickResponse b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RelayKickResponse) b(e, inputStream, extensionRegistryLite);
        }

        public static RelayKickResponse a(CodedInputStream codedInputStream) throws IOException {
            return (RelayKickResponse) GeneratedMessageLite.b(e, codedInputStream);
        }

        public static RelayKickResponse a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RelayKickResponse) GeneratedMessageLite.b(e, codedInputStream, extensionRegistryLite);
        }

        public static Builder c() {
            return (Builder) e.Y();
        }

        public static Builder a(RelayKickResponse relayKickResponse) {
            return (Builder) ((Builder) e.Y()).b(relayKickResponse);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<RelayKickResponse, Builder> implements RelayKickResponseOrBuilder {
            private Builder() {
                super(RelayKickResponse.e);
            }

            public boolean a() {
                return ((RelayKickResponse) this.f11310a).a();
            }

            public boolean b() {
                return ((RelayKickResponse) this.f11310a).b();
            }

            public Builder a(boolean z) {
                S();
                ((RelayKickResponse) this.f11310a).a(z);
                return this;
            }

            public Builder c() {
                S();
                ((RelayKickResponse) this.f11310a).g();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new RelayKickResponse();
                case IS_INITIALIZED:
                    byte b2 = this.d;
                    if (b2 == 1) {
                        return e;
                    }
                    if (b2 == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!a()) {
                        if (booleanValue) {
                            this.d = 0;
                        }
                        return null;
                    }
                    if (booleanValue) {
                        this.d = 1;
                    }
                    return e;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    RelayKickResponse relayKickResponse = (RelayKickResponse) obj2;
                    this.c = visitor.a(a(), this.c, relayKickResponse.a(), relayKickResponse.c);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.b |= relayKickResponse.b;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 8) {
                                    this.b |= 1;
                                    this.c = codedInputStream.k();
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
                    if (f == null) {
                        synchronized (RelayKickResponse.class) {
                            if (f == null) {
                                f = new GeneratedMessageLite.DefaultInstanceBasedParser(e);
                            }
                        }
                    }
                    return f;
                default:
                    throw new UnsupportedOperationException();
            }
            return e;
        }

        static {
            e.P();
        }

        public static RelayKickResponse d() {
            return e;
        }

        public static Parser<RelayKickResponse> e() {
            return e.M();
        }
    }

    public static final class RouterPacket extends GeneratedMessageLite<RouterPacket, Builder> implements RouterPacketOrBuilder {
        private static volatile Parser<RouterPacket> A = null;

        /* renamed from: a  reason: collision with root package name */
        public static final int f11264a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        public static final int h = 8;
        public static final int i = 9;
        public static final int j = 10;
        /* access modifiers changed from: private */
        public static final RouterPacket z = new RouterPacket();
        private int k;
        private long l;
        private long p;
        private String q = "";
        private long r;
        private String s = "";
        private long t;
        private int u = 1;
        private ByteString v = ByteString.EMPTY;
        private long w;
        private String x = "";
        private byte y = -1;

        private RouterPacket() {
        }

        public boolean a() {
            return (this.k & 1) == 1;
        }

        public long b() {
            return this.l;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.k |= 1;
            this.l = j2;
        }

        /* access modifiers changed from: private */
        public void C() {
            this.k &= -2;
            this.l = 0;
        }

        public boolean c() {
            return (this.k & 2) == 2;
        }

        public long d() {
            return this.p;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.k |= 2;
            this.p = j2;
        }

        /* access modifiers changed from: private */
        public void D() {
            this.k &= -3;
            this.p = 0;
        }

        public boolean e() {
            return (this.k & 4) == 4;
        }

        public String f() {
            return this.q;
        }

        public ByteString g() {
            return ByteString.copyFromUtf8(this.q);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.k |= 4;
                this.q = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void E() {
            this.k &= -5;
            this.q = z().f();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.k |= 4;
                this.q = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean h() {
            return (this.k & 8) == 8;
        }

        public long i() {
            return this.r;
        }

        /* access modifiers changed from: private */
        public void c(long j2) {
            this.k |= 8;
            this.r = j2;
        }

        /* access modifiers changed from: private */
        public void F() {
            this.k &= -9;
            this.r = 0;
        }

        public boolean j() {
            return (this.k & 16) == 16;
        }

        public String l() {
            return this.s;
        }

        public ByteString m() {
            return ByteString.copyFromUtf8(this.s);
        }

        /* access modifiers changed from: private */
        public void b(String str) {
            if (str != null) {
                this.k |= 16;
                this.s = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void G() {
            this.k &= -17;
            this.s = z().l();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.k |= 16;
                this.s = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean n() {
            return (this.k & 32) == 32;
        }

        public long o() {
            return this.t;
        }

        /* access modifiers changed from: private */
        public void d(long j2) {
            this.k |= 32;
            this.t = j2;
        }

        /* access modifiers changed from: private */
        public void H() {
            this.k &= -33;
            this.t = 0;
        }

        public boolean p() {
            return (this.k & 64) == 64;
        }

        public STREAM_TYPE q() {
            STREAM_TYPE forNumber = STREAM_TYPE.forNumber(this.u);
            return forNumber == null ? STREAM_TYPE.SIGNAL_STREAM : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(STREAM_TYPE stream_type) {
            if (stream_type != null) {
                this.k |= 64;
                this.u = stream_type.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void I() {
            this.k &= -65;
            this.u = 1;
        }

        public boolean r() {
            return (this.k & 128) == 128;
        }

        public ByteString s() {
            return this.v;
        }

        /* access modifiers changed from: private */
        public void e(ByteString byteString) {
            if (byteString != null) {
                this.k |= 128;
                this.v = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ab() {
            this.k &= -129;
            this.v = z().s();
        }

        public boolean t() {
            return (this.k & 256) == 256;
        }

        public long u() {
            return this.w;
        }

        /* access modifiers changed from: private */
        public void e(long j2) {
            this.k |= 256;
            this.w = j2;
        }

        /* access modifiers changed from: private */
        public void ac() {
            this.k &= -257;
            this.w = 0;
        }

        public boolean v() {
            return (this.k & 512) == 512;
        }

        public String w() {
            return this.x;
        }

        public ByteString x() {
            return ByteString.copyFromUtf8(this.x);
        }

        /* access modifiers changed from: private */
        public void c(String str) {
            if (str != null) {
                this.k |= 512;
                this.x = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ad() {
            this.k &= -513;
            this.x = z().w();
        }

        /* access modifiers changed from: private */
        public void f(ByteString byteString) {
            if (byteString != null) {
                this.k |= 512;
                this.x = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.k & 1) == 1) {
                codedOutputStream.b(1, this.l);
            }
            if ((this.k & 2) == 2) {
                codedOutputStream.b(2, this.p);
            }
            if ((this.k & 4) == 4) {
                codedOutputStream.a(3, f());
            }
            if ((this.k & 8) == 8) {
                codedOutputStream.b(4, this.r);
            }
            if ((this.k & 16) == 16) {
                codedOutputStream.a(5, l());
            }
            if ((this.k & 32) == 32) {
                codedOutputStream.b(6, this.t);
            }
            if ((this.k & 64) == 64) {
                codedOutputStream.g(7, this.u);
            }
            if ((this.k & 128) == 128) {
                codedOutputStream.a(8, this.v);
            }
            if ((this.k & 256) == 256) {
                codedOutputStream.a(9, this.w);
            }
            if ((this.k & 512) == 512) {
                codedOutputStream.a(10, w());
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int i3 = 0;
            if ((this.k & 1) == 1) {
                i3 = 0 + CodedOutputStream.g(1, this.l);
            }
            if ((this.k & 2) == 2) {
                i3 += CodedOutputStream.g(2, this.p);
            }
            if ((this.k & 4) == 4) {
                i3 += CodedOutputStream.b(3, f());
            }
            if ((this.k & 8) == 8) {
                i3 += CodedOutputStream.g(4, this.r);
            }
            if ((this.k & 16) == 16) {
                i3 += CodedOutputStream.b(5, l());
            }
            if ((this.k & 32) == 32) {
                i3 += CodedOutputStream.g(6, this.t);
            }
            if ((this.k & 64) == 64) {
                i3 += CodedOutputStream.m(7, this.u);
            }
            if ((this.k & 128) == 128) {
                i3 += CodedOutputStream.c(8, this.v);
            }
            if ((this.k & 256) == 256) {
                i3 += CodedOutputStream.f(9, this.w);
            }
            if ((this.k & 512) == 512) {
                i3 += CodedOutputStream.b(10, w());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static RouterPacket a(ByteString byteString) throws InvalidProtocolBufferException {
            return (RouterPacket) GeneratedMessageLite.a(z, byteString);
        }

        public static RouterPacket a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RouterPacket) GeneratedMessageLite.a(z, byteString, extensionRegistryLite);
        }

        public static RouterPacket a(byte[] bArr) throws InvalidProtocolBufferException {
            return (RouterPacket) GeneratedMessageLite.a(z, bArr);
        }

        public static RouterPacket a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RouterPacket) GeneratedMessageLite.a(z, bArr, extensionRegistryLite);
        }

        public static RouterPacket a(InputStream inputStream) throws IOException {
            return (RouterPacket) GeneratedMessageLite.a(z, inputStream);
        }

        public static RouterPacket a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RouterPacket) GeneratedMessageLite.a(z, inputStream, extensionRegistryLite);
        }

        public static RouterPacket b(InputStream inputStream) throws IOException {
            return (RouterPacket) b(z, inputStream);
        }

        public static RouterPacket b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RouterPacket) b(z, inputStream, extensionRegistryLite);
        }

        public static RouterPacket a(CodedInputStream codedInputStream) throws IOException {
            return (RouterPacket) GeneratedMessageLite.b(z, codedInputStream);
        }

        public static RouterPacket a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RouterPacket) GeneratedMessageLite.b(z, codedInputStream, extensionRegistryLite);
        }

        public static Builder y() {
            return (Builder) z.Y();
        }

        public static Builder a(RouterPacket routerPacket) {
            return (Builder) ((Builder) z.Y()).b(routerPacket);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<RouterPacket, Builder> implements RouterPacketOrBuilder {
            private Builder() {
                super(RouterPacket.z);
            }

            public boolean a() {
                return ((RouterPacket) this.f11310a).a();
            }

            public long b() {
                return ((RouterPacket) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((RouterPacket) this.f11310a).a(j);
                return this;
            }

            public Builder k() {
                S();
                ((RouterPacket) this.f11310a).C();
                return this;
            }

            public boolean c() {
                return ((RouterPacket) this.f11310a).c();
            }

            public long d() {
                return ((RouterPacket) this.f11310a).d();
            }

            public Builder b(long j) {
                S();
                ((RouterPacket) this.f11310a).b(j);
                return this;
            }

            public Builder y() {
                S();
                ((RouterPacket) this.f11310a).D();
                return this;
            }

            public boolean e() {
                return ((RouterPacket) this.f11310a).e();
            }

            public String f() {
                return ((RouterPacket) this.f11310a).f();
            }

            public ByteString g() {
                return ((RouterPacket) this.f11310a).g();
            }

            public Builder a(String str) {
                S();
                ((RouterPacket) this.f11310a).a(str);
                return this;
            }

            public Builder z() {
                S();
                ((RouterPacket) this.f11310a).E();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((RouterPacket) this.f11310a).c(byteString);
                return this;
            }

            public boolean h() {
                return ((RouterPacket) this.f11310a).h();
            }

            public long i() {
                return ((RouterPacket) this.f11310a).i();
            }

            public Builder c(long j) {
                S();
                ((RouterPacket) this.f11310a).c(j);
                return this;
            }

            public Builder A() {
                S();
                ((RouterPacket) this.f11310a).F();
                return this;
            }

            public boolean j() {
                return ((RouterPacket) this.f11310a).j();
            }

            public String l() {
                return ((RouterPacket) this.f11310a).l();
            }

            public ByteString m() {
                return ((RouterPacket) this.f11310a).m();
            }

            public Builder b(String str) {
                S();
                ((RouterPacket) this.f11310a).b(str);
                return this;
            }

            public Builder B() {
                S();
                ((RouterPacket) this.f11310a).G();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((RouterPacket) this.f11310a).d(byteString);
                return this;
            }

            public boolean n() {
                return ((RouterPacket) this.f11310a).n();
            }

            public long o() {
                return ((RouterPacket) this.f11310a).o();
            }

            public Builder d(long j) {
                S();
                ((RouterPacket) this.f11310a).d(j);
                return this;
            }

            public Builder C() {
                S();
                ((RouterPacket) this.f11310a).H();
                return this;
            }

            public boolean p() {
                return ((RouterPacket) this.f11310a).p();
            }

            public STREAM_TYPE q() {
                return ((RouterPacket) this.f11310a).q();
            }

            public Builder a(STREAM_TYPE stream_type) {
                S();
                ((RouterPacket) this.f11310a).a(stream_type);
                return this;
            }

            public Builder D() {
                S();
                ((RouterPacket) this.f11310a).I();
                return this;
            }

            public boolean r() {
                return ((RouterPacket) this.f11310a).r();
            }

            public ByteString s() {
                return ((RouterPacket) this.f11310a).s();
            }

            public Builder c(ByteString byteString) {
                S();
                ((RouterPacket) this.f11310a).e(byteString);
                return this;
            }

            public Builder E() {
                S();
                ((RouterPacket) this.f11310a).ab();
                return this;
            }

            public boolean t() {
                return ((RouterPacket) this.f11310a).t();
            }

            public long u() {
                return ((RouterPacket) this.f11310a).u();
            }

            public Builder e(long j) {
                S();
                ((RouterPacket) this.f11310a).e(j);
                return this;
            }

            public Builder F() {
                S();
                ((RouterPacket) this.f11310a).ac();
                return this;
            }

            public boolean v() {
                return ((RouterPacket) this.f11310a).v();
            }

            public String w() {
                return ((RouterPacket) this.f11310a).w();
            }

            public ByteString x() {
                return ((RouterPacket) this.f11310a).x();
            }

            public Builder c(String str) {
                S();
                ((RouterPacket) this.f11310a).c(str);
                return this;
            }

            public Builder G() {
                S();
                ((RouterPacket) this.f11310a).ad();
                return this;
            }

            public Builder d(ByteString byteString) {
                S();
                ((RouterPacket) this.f11310a).f(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z2 = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new RouterPacket();
                case IS_INITIALIZED:
                    byte b2 = this.y;
                    if (b2 == 1) {
                        return z;
                    }
                    if (b2 == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!a()) {
                        if (booleanValue) {
                            this.y = 0;
                        }
                        return null;
                    }
                    if (booleanValue) {
                        this.y = 1;
                    }
                    return z;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    RouterPacket routerPacket = (RouterPacket) obj2;
                    this.l = visitor.a(a(), this.l, routerPacket.a(), routerPacket.l);
                    this.p = visitor.a(c(), this.p, routerPacket.c(), routerPacket.p);
                    this.q = visitor.a(e(), this.q, routerPacket.e(), routerPacket.q);
                    this.r = visitor.a(h(), this.r, routerPacket.h(), routerPacket.r);
                    this.s = visitor.a(j(), this.s, routerPacket.j(), routerPacket.s);
                    this.t = visitor.a(n(), this.t, routerPacket.n(), routerPacket.t);
                    this.u = visitor.a(p(), this.u, routerPacket.p(), routerPacket.u);
                    this.v = visitor.a(r(), this.v, routerPacket.r(), routerPacket.v);
                    this.w = visitor.a(t(), this.w, routerPacket.t(), routerPacket.w);
                    this.x = visitor.a(v(), this.x, routerPacket.v(), routerPacket.x);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.k |= routerPacket.k;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z2) {
                        try {
                            int a2 = codedInputStream.a();
                            switch (a2) {
                                case 0:
                                    z2 = true;
                                    break;
                                case 8:
                                    this.k |= 1;
                                    this.l = codedInputStream.f();
                                    break;
                                case 16:
                                    this.k |= 2;
                                    this.p = codedInputStream.f();
                                    break;
                                case 26:
                                    String l2 = codedInputStream.l();
                                    this.k |= 4;
                                    this.q = l2;
                                    break;
                                case 32:
                                    this.k |= 8;
                                    this.r = codedInputStream.f();
                                    break;
                                case 42:
                                    String l3 = codedInputStream.l();
                                    this.k |= 16;
                                    this.s = l3;
                                    break;
                                case 48:
                                    this.k |= 32;
                                    this.t = codedInputStream.f();
                                    break;
                                case 56:
                                    int r2 = codedInputStream.r();
                                    if (STREAM_TYPE.forNumber(r2) != null) {
                                        this.k |= 64;
                                        this.u = r2;
                                        break;
                                    } else {
                                        super.a(7, r2);
                                        break;
                                    }
                                case 66:
                                    this.k |= 128;
                                    this.v = codedInputStream.n();
                                    break;
                                case 72:
                                    this.k |= 256;
                                    this.w = codedInputStream.g();
                                    break;
                                case 82:
                                    String l4 = codedInputStream.l();
                                    this.k |= 512;
                                    this.x = l4;
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
                    if (A == null) {
                        synchronized (RouterPacket.class) {
                            if (A == null) {
                                A = new GeneratedMessageLite.DefaultInstanceBasedParser(z);
                            }
                        }
                    }
                    return A;
                default:
                    throw new UnsupportedOperationException();
            }
            return z;
        }

        static {
            z.P();
        }

        public static RouterPacket z() {
            return z;
        }

        public static Parser<RouterPacket> A() {
            return z.M();
        }
    }

    public static final class BurrowPacket extends GeneratedMessageLite<BurrowPacket, Builder> implements BurrowPacketOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11259a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        /* access modifiers changed from: private */
        public static final BurrowPacket p = new BurrowPacket();
        private static volatile Parser<BurrowPacket> q;
        private int f;
        private long g;
        private String h = "";
        private long i;
        private long j;
        private int k = 1;
        private byte l = -1;

        private BurrowPacket() {
        }

        public boolean a() {
            return (this.f & 1) == 1;
        }

        public long b() {
            return this.g;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.f |= 1;
            this.g = j2;
        }

        /* access modifiers changed from: private */
        public void q() {
            this.f &= -2;
            this.g = 0;
        }

        public boolean c() {
            return (this.f & 2) == 2;
        }

        public String d() {
            return this.h;
        }

        public ByteString e() {
            return ByteString.copyFromUtf8(this.h);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.f |= 2;
                this.h = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void r() {
            this.f &= -3;
            this.h = n().d();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.f |= 2;
                this.h = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean f() {
            return (this.f & 4) == 4;
        }

        public long g() {
            return this.i;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.f |= 4;
            this.i = j2;
        }

        /* access modifiers changed from: private */
        public void s() {
            this.f &= -5;
            this.i = 0;
        }

        public boolean h() {
            return (this.f & 8) == 8;
        }

        public long i() {
            return this.j;
        }

        /* access modifiers changed from: private */
        public void c(long j2) {
            this.f |= 8;
            this.j = j2;
        }

        /* access modifiers changed from: private */
        public void t() {
            this.f &= -9;
            this.j = 0;
        }

        public boolean j() {
            return (this.f & 16) == 16;
        }

        public BURROW_TYPE l() {
            BURROW_TYPE forNumber = BURROW_TYPE.forNumber(this.k);
            return forNumber == null ? BURROW_TYPE.INTRANET_BURROW_REQUEST : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(BURROW_TYPE burrow_type) {
            if (burrow_type != null) {
                this.f |= 16;
                this.k = burrow_type.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void u() {
            this.f &= -17;
            this.k = 1;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.f & 1) == 1) {
                codedOutputStream.b(1, this.g);
            }
            if ((this.f & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.f & 4) == 4) {
                codedOutputStream.b(3, this.i);
            }
            if ((this.f & 8) == 8) {
                codedOutputStream.b(4, this.j);
            }
            if ((this.f & 16) == 16) {
                codedOutputStream.g(5, this.k);
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int i3 = 0;
            if ((this.f & 1) == 1) {
                i3 = 0 + CodedOutputStream.g(1, this.g);
            }
            if ((this.f & 2) == 2) {
                i3 += CodedOutputStream.b(2, d());
            }
            if ((this.f & 4) == 4) {
                i3 += CodedOutputStream.g(3, this.i);
            }
            if ((this.f & 8) == 8) {
                i3 += CodedOutputStream.g(4, this.j);
            }
            if ((this.f & 16) == 16) {
                i3 += CodedOutputStream.m(5, this.k);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static BurrowPacket a(ByteString byteString) throws InvalidProtocolBufferException {
            return (BurrowPacket) GeneratedMessageLite.a(p, byteString);
        }

        public static BurrowPacket a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BurrowPacket) GeneratedMessageLite.a(p, byteString, extensionRegistryLite);
        }

        public static BurrowPacket a(byte[] bArr) throws InvalidProtocolBufferException {
            return (BurrowPacket) GeneratedMessageLite.a(p, bArr);
        }

        public static BurrowPacket a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BurrowPacket) GeneratedMessageLite.a(p, bArr, extensionRegistryLite);
        }

        public static BurrowPacket a(InputStream inputStream) throws IOException {
            return (BurrowPacket) GeneratedMessageLite.a(p, inputStream);
        }

        public static BurrowPacket a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BurrowPacket) GeneratedMessageLite.a(p, inputStream, extensionRegistryLite);
        }

        public static BurrowPacket b(InputStream inputStream) throws IOException {
            return (BurrowPacket) b(p, inputStream);
        }

        public static BurrowPacket b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BurrowPacket) b(p, inputStream, extensionRegistryLite);
        }

        public static BurrowPacket a(CodedInputStream codedInputStream) throws IOException {
            return (BurrowPacket) GeneratedMessageLite.b(p, codedInputStream);
        }

        public static BurrowPacket a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BurrowPacket) GeneratedMessageLite.b(p, codedInputStream, extensionRegistryLite);
        }

        public static Builder m() {
            return (Builder) p.Y();
        }

        public static Builder a(BurrowPacket burrowPacket) {
            return (Builder) ((Builder) p.Y()).b(burrowPacket);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<BurrowPacket, Builder> implements BurrowPacketOrBuilder {
            private Builder() {
                super(BurrowPacket.p);
            }

            public boolean a() {
                return ((BurrowPacket) this.f11310a).a();
            }

            public long b() {
                return ((BurrowPacket) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((BurrowPacket) this.f11310a).a(j);
                return this;
            }

            public Builder k() {
                S();
                ((BurrowPacket) this.f11310a).q();
                return this;
            }

            public boolean c() {
                return ((BurrowPacket) this.f11310a).c();
            }

            public String d() {
                return ((BurrowPacket) this.f11310a).d();
            }

            public ByteString e() {
                return ((BurrowPacket) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((BurrowPacket) this.f11310a).a(str);
                return this;
            }

            public Builder m() {
                S();
                ((BurrowPacket) this.f11310a).r();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((BurrowPacket) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((BurrowPacket) this.f11310a).f();
            }

            public long g() {
                return ((BurrowPacket) this.f11310a).g();
            }

            public Builder b(long j) {
                S();
                ((BurrowPacket) this.f11310a).b(j);
                return this;
            }

            public Builder n() {
                S();
                ((BurrowPacket) this.f11310a).s();
                return this;
            }

            public boolean h() {
                return ((BurrowPacket) this.f11310a).h();
            }

            public long i() {
                return ((BurrowPacket) this.f11310a).i();
            }

            public Builder c(long j) {
                S();
                ((BurrowPacket) this.f11310a).c(j);
                return this;
            }

            public Builder o() {
                S();
                ((BurrowPacket) this.f11310a).t();
                return this;
            }

            public boolean j() {
                return ((BurrowPacket) this.f11310a).j();
            }

            public BURROW_TYPE l() {
                return ((BurrowPacket) this.f11310a).l();
            }

            public Builder a(BURROW_TYPE burrow_type) {
                S();
                ((BurrowPacket) this.f11310a).a(burrow_type);
                return this;
            }

            public Builder p() {
                S();
                ((BurrowPacket) this.f11310a).u();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new BurrowPacket();
                case IS_INITIALIZED:
                    byte b2 = this.l;
                    if (b2 == 1) {
                        return p;
                    }
                    if (b2 == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!a()) {
                        if (booleanValue) {
                            this.l = 0;
                        }
                        return null;
                    } else if (!c()) {
                        if (booleanValue) {
                            this.l = 0;
                        }
                        return null;
                    } else if (!f()) {
                        if (booleanValue) {
                            this.l = 0;
                        }
                        return null;
                    } else if (!h()) {
                        if (booleanValue) {
                            this.l = 0;
                        }
                        return null;
                    } else if (!j()) {
                        if (booleanValue) {
                            this.l = 0;
                        }
                        return null;
                    } else {
                        if (booleanValue) {
                            this.l = 1;
                        }
                        return p;
                    }
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    BurrowPacket burrowPacket = (BurrowPacket) obj2;
                    this.g = visitor.a(a(), this.g, burrowPacket.a(), burrowPacket.g);
                    this.h = visitor.a(c(), this.h, burrowPacket.c(), burrowPacket.h);
                    this.i = visitor.a(f(), this.i, burrowPacket.f(), burrowPacket.i);
                    this.j = visitor.a(h(), this.j, burrowPacket.h(), burrowPacket.j);
                    this.k = visitor.a(j(), this.k, burrowPacket.j(), burrowPacket.k);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.f |= burrowPacket.f;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 8) {
                                    this.f |= 1;
                                    this.g = codedInputStream.f();
                                } else if (a2 == 18) {
                                    String l2 = codedInputStream.l();
                                    this.f |= 2;
                                    this.h = l2;
                                } else if (a2 == 24) {
                                    this.f |= 4;
                                    this.i = codedInputStream.f();
                                } else if (a2 == 32) {
                                    this.f |= 8;
                                    this.j = codedInputStream.f();
                                } else if (a2 == 40) {
                                    int r = codedInputStream.r();
                                    if (BURROW_TYPE.forNumber(r) == null) {
                                        super.a(5, r);
                                    } else {
                                        this.f |= 16;
                                        this.k = r;
                                    }
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
                    if (q == null) {
                        synchronized (BurrowPacket.class) {
                            if (q == null) {
                                q = new GeneratedMessageLite.DefaultInstanceBasedParser(p);
                            }
                        }
                    }
                    return q;
                default:
                    throw new UnsupportedOperationException();
            }
            return p;
        }

        static {
            p.P();
        }

        public static BurrowPacket n() {
            return p;
        }

        public static Parser<BurrowPacket> o() {
            return p.M();
        }
    }
}
