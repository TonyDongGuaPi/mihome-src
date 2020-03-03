package com.xiaomi.mimc.proto;

import com.xiaomi.mimc.protobuf.AbstractMessageLite;
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
import java.util.Collections;
import java.util.List;

public final class Mimc {

    public interface AppinfoOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        String g();

        ByteString h();

        boolean i();

        long j();

        boolean l();

        String m();

        ByteString n();

        boolean o();

        String p();

        ByteString q();

        boolean r();

        String s();

        ByteString t();
    }

    public interface FeInfoOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        String b();

        ByteString c();

        boolean d();

        int e();

        boolean f();

        int g();

        boolean h();

        long i();

        boolean j();

        long l();

        boolean m();

        long n();
    }

    public interface FilterRequestOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        String b();

        ByteString c();

        boolean d();

        MIMCPacket e();
    }

    public interface FilterResponseOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        String b();

        ByteString c();

        boolean d();

        MIMC_MSG_TYPE e();

        boolean f();

        ErrorCode g();

        boolean h();

        boolean i();
    }

    public interface MIMCGroupOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        long d();
    }

    public interface MIMCP2PMessageOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        MIMCUser b();

        boolean c();

        MIMCUser d();

        boolean e();

        ByteString f();

        boolean g();

        boolean h();

        boolean i();

        String j();

        ByteString l();
    }

    public interface MIMCP2TMessageOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        MIMCUser b();

        boolean c();

        MIMCGroup d();

        boolean e();

        ByteString f();

        boolean g();

        boolean h();

        boolean i();

        String j();

        ByteString l();
    }

    public interface MIMCPacketAckOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        String b();

        ByteString c();

        boolean d();

        long e();

        boolean f();

        String g();

        ByteString h();

        boolean i();

        long j();

        boolean l();

        long m();

        boolean n();

        String o();

        ByteString p();

        boolean q();

        String r();

        ByteString s();
    }

    public interface MIMCPacketListOrBuilder extends MessageLiteOrBuilder {
        MIMCPacket a(int i);

        boolean a();

        long b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        long g();

        List<MIMCPacket> h();

        int j();
    }

    public interface MIMCPacketOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        String b();

        ByteString c();

        boolean d();

        String e();

        ByteString f();

        boolean g();

        long h();

        boolean i();

        MIMC_MSG_TYPE j();

        boolean l();

        ByteString m();

        boolean n();

        long o();
    }

    public interface MIMCPullOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        String d();

        ByteString e();
    }

    public interface MIMCSequenceAckOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        long g();
    }

    public interface MIMCUserOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        long g();

        boolean h();

        String i();

        ByteString j();
    }

    public interface P2PPushMesageOrBuilder extends MessageLiteOrBuilder {
        MIMCUser a(int i);

        boolean a();

        MIMCUser b();

        List<MIMCUser> c();

        int e();

        boolean f();

        ByteString g();

        boolean h();

        boolean i();
    }

    public interface P2TPushMesageOrBuilder extends MessageLiteOrBuilder {
        MIMCGroup a(int i);

        boolean a();

        MIMCUser b();

        List<MIMCGroup> c();

        int e();

        boolean f();

        ByteString g();

        boolean h();

        boolean i();
    }

    public interface PullMessageRequestOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        String d();

        ByteString e();
    }

    public interface QueryAppinfoRequestOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        String d();

        ByteString e();
    }

    public interface TopicMessageOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        MIMCPacket d();

        boolean e();

        long f();

        boolean g();

        String h();

        ByteString i();
    }

    public interface UCDismissOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        UCGroup b();
    }

    public interface UCExchangeOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        FeInfo b();

        boolean c();

        UCPacket d();

        boolean e();

        String f();

        ByteString g();
    }

    public interface UCGroupOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        long d();
    }

    public interface UCJoinOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        UCGroup b();
    }

    public interface UCJoinRespOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        UCGroup b();

        boolean c();

        int d();

        boolean e();

        String f();

        ByteString g();
    }

    public interface UCMessageListOrBuilder extends MessageLiteOrBuilder {
        UCMessage a(int i);

        boolean a();

        UCGroup b();

        List<UCMessage> c();

        int e();

        boolean f();

        long g();
    }

    public interface UCMessageOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        UCGroup b();

        boolean c();

        ByteString d();

        boolean e();

        long f();

        boolean g();

        boolean h();

        boolean i();

        MIMCUser j();

        boolean l();

        long m();

        boolean n();

        String o();

        ByteString p();

        boolean q();

        String r();

        ByteString s();
    }

    public interface UCPacketOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        MIMCUser b();

        boolean c();

        UC_MSG_TYPE d();

        boolean e();

        ByteString f();

        boolean g();

        String h();

        ByteString i();
    }

    public interface UCPingOrBuilder extends MessageLiteOrBuilder {
        UCGroup a(int i);

        List<UCGroup> a();

        int c();
    }

    public interface UCPushMessageOrBuilder extends MessageLiteOrBuilder {
        UCGroup a(int i);

        boolean a();

        MIMCUser b();

        ByteString c(int i);

        List<UCGroup> c();

        int e();

        List<ByteString> f();

        int g();

        boolean h();

        boolean i();

        boolean j();

        long l();

        boolean m();

        String n();

        ByteString o();
    }

    public interface UCQueryOnlineUsersOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        UCGroup b();
    }

    public interface UCQueryOnlineUsersRespOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        UCGroup b();

        boolean c();

        long d();
    }

    public interface UCQuitOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        UCGroup b();
    }

    public interface UCQuitRespOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        UCGroup b();

        boolean c();

        int d();

        boolean e();

        String f();

        ByteString g();
    }

    public interface UCSequenceAckOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        UCGroup b();

        boolean c();

        long d();
    }

    public static void a(ExtensionRegistryLite extensionRegistryLite) {
    }

    private Mimc() {
    }

    public enum ErrorCode implements Internal.EnumLite {
        OK(0),
        INTERNAL_ERROR(1);
        
        public static final int INTERNAL_ERROR_VALUE = 1;
        public static final int OK_VALUE = 0;
        private static final Internal.EnumLiteMap<ErrorCode> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<ErrorCode>() {
                /* renamed from: a */
                public ErrorCode b(int i) {
                    return ErrorCode.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ErrorCode valueOf(int i) {
            return forNumber(i);
        }

        public static ErrorCode forNumber(int i) {
            switch (i) {
                case 0:
                    return OK;
                case 1:
                    return INTERNAL_ERROR;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<ErrorCode> internalGetValueMap() {
            return internalValueMap;
        }

        private ErrorCode(int i) {
            this.value = i;
        }
    }

    public enum MIMC_MSG_TYPE implements Internal.EnumLite {
        P2P_MESSAGE(1),
        P2T_MESSAGE(2),
        SEQUENCE_ACK(3),
        PACKET_ACK(4),
        PULL(5),
        COMPOUND(6),
        RTS_SIGNAL(7),
        UC_PACKET(8),
        P2P_PUSH_MESSAGE(9),
        P2T_PUSH_MESSAGE(10);
        
        public static final int COMPOUND_VALUE = 6;
        public static final int P2P_MESSAGE_VALUE = 1;
        public static final int P2P_PUSH_MESSAGE_VALUE = 9;
        public static final int P2T_MESSAGE_VALUE = 2;
        public static final int P2T_PUSH_MESSAGE_VALUE = 10;
        public static final int PACKET_ACK_VALUE = 4;
        public static final int PULL_VALUE = 5;
        public static final int RTS_SIGNAL_VALUE = 7;
        public static final int SEQUENCE_ACK_VALUE = 3;
        public static final int UC_PACKET_VALUE = 8;
        private static final Internal.EnumLiteMap<MIMC_MSG_TYPE> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<MIMC_MSG_TYPE>() {
                /* renamed from: a */
                public MIMC_MSG_TYPE b(int i) {
                    return MIMC_MSG_TYPE.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static MIMC_MSG_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static MIMC_MSG_TYPE forNumber(int i) {
            switch (i) {
                case 1:
                    return P2P_MESSAGE;
                case 2:
                    return P2T_MESSAGE;
                case 3:
                    return SEQUENCE_ACK;
                case 4:
                    return PACKET_ACK;
                case 5:
                    return PULL;
                case 6:
                    return COMPOUND;
                case 7:
                    return RTS_SIGNAL;
                case 8:
                    return UC_PACKET;
                case 9:
                    return P2P_PUSH_MESSAGE;
                case 10:
                    return P2T_PUSH_MESSAGE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<MIMC_MSG_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        private MIMC_MSG_TYPE(int i) {
            this.value = i;
        }
    }

    public enum UC_MSG_TYPE implements Internal.EnumLite {
        PING(1),
        PONG(2),
        JOIN(3),
        JOIN_RESP(4),
        QUIT(5),
        QUIT_RESP(6),
        SEQ_ACK(7),
        MESSAGE(8),
        MESSAGE_LIST(9),
        DISMISS(10),
        QUERY_ONLINE_USERS(11),
        QUERY_ONLINE_USERS_RESP(12);
        
        public static final int DISMISS_VALUE = 10;
        public static final int JOIN_RESP_VALUE = 4;
        public static final int JOIN_VALUE = 3;
        public static final int MESSAGE_LIST_VALUE = 9;
        public static final int MESSAGE_VALUE = 8;
        public static final int PING_VALUE = 1;
        public static final int PONG_VALUE = 2;
        public static final int QUERY_ONLINE_USERS_RESP_VALUE = 12;
        public static final int QUERY_ONLINE_USERS_VALUE = 11;
        public static final int QUIT_RESP_VALUE = 6;
        public static final int QUIT_VALUE = 5;
        public static final int SEQ_ACK_VALUE = 7;
        private static final Internal.EnumLiteMap<UC_MSG_TYPE> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<UC_MSG_TYPE>() {
                /* renamed from: a */
                public UC_MSG_TYPE b(int i) {
                    return UC_MSG_TYPE.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static UC_MSG_TYPE valueOf(int i) {
            return forNumber(i);
        }

        public static UC_MSG_TYPE forNumber(int i) {
            switch (i) {
                case 1:
                    return PING;
                case 2:
                    return PONG;
                case 3:
                    return JOIN;
                case 4:
                    return JOIN_RESP;
                case 5:
                    return QUIT;
                case 6:
                    return QUIT_RESP;
                case 7:
                    return SEQ_ACK;
                case 8:
                    return MESSAGE;
                case 9:
                    return MESSAGE_LIST;
                case 10:
                    return DISMISS;
                case 11:
                    return QUERY_ONLINE_USERS;
                case 12:
                    return QUERY_ONLINE_USERS_RESP;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<UC_MSG_TYPE> internalGetValueMap() {
            return internalValueMap;
        }

        private UC_MSG_TYPE(int i) {
            this.value = i;
        }
    }

    public static final class FilterRequest extends GeneratedMessageLite<FilterRequest, Builder> implements FilterRequestOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11225a = 1;
        public static final int b = 2;
        /* access modifiers changed from: private */
        public static final FilterRequest g = new FilterRequest();
        private static volatile Parser<FilterRequest> h;
        private int c;
        private String d = "";
        private MIMCPacket e;
        private byte f = -1;

        private FilterRequest() {
        }

        public boolean a() {
            return (this.c & 1) == 1;
        }

        public String b() {
            return this.d;
        }

        public ByteString c() {
            return ByteString.copyFromUtf8(this.d);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.c |= 1;
                this.d = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void j() {
            this.c &= -2;
            this.d = g().b();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.c |= 1;
                this.d = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean d() {
            return (this.c & 2) == 2;
        }

        public MIMCPacket e() {
            return this.e == null ? MIMCPacket.q() : this.e;
        }

        /* access modifiers changed from: private */
        public void a(MIMCPacket mIMCPacket) {
            if (mIMCPacket != null) {
                this.e = mIMCPacket;
                this.c |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(MIMCPacket.Builder builder) {
            this.e = (MIMCPacket) builder.Z();
            this.c |= 2;
        }

        /* access modifiers changed from: private */
        public void b(MIMCPacket mIMCPacket) {
            if (this.e == null || this.e == MIMCPacket.q()) {
                this.e = mIMCPacket;
            } else {
                this.e = (MIMCPacket) ((MIMCPacket.Builder) MIMCPacket.a(this.e).b(mIMCPacket)).Y();
            }
            this.c |= 2;
        }

        /* access modifiers changed from: private */
        public void l() {
            this.e = null;
            this.c &= -3;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.c & 1) == 1) {
                codedOutputStream.a(1, b());
            }
            if ((this.c & 2) == 2) {
                codedOutputStream.a(2, (MessageLite) e());
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
                i2 = 0 + CodedOutputStream.b(1, b());
            }
            if ((this.c & 2) == 2) {
                i2 += CodedOutputStream.c(2, (MessageLite) e());
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static FilterRequest a(ByteString byteString) throws InvalidProtocolBufferException {
            return (FilterRequest) GeneratedMessageLite.a(g, byteString);
        }

        public static FilterRequest a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FilterRequest) GeneratedMessageLite.a(g, byteString, extensionRegistryLite);
        }

        public static FilterRequest a(byte[] bArr) throws InvalidProtocolBufferException {
            return (FilterRequest) GeneratedMessageLite.a(g, bArr);
        }

        public static FilterRequest a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FilterRequest) GeneratedMessageLite.a(g, bArr, extensionRegistryLite);
        }

        public static FilterRequest a(InputStream inputStream) throws IOException {
            return (FilterRequest) GeneratedMessageLite.a(g, inputStream);
        }

        public static FilterRequest a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FilterRequest) GeneratedMessageLite.a(g, inputStream, extensionRegistryLite);
        }

        public static FilterRequest b(InputStream inputStream) throws IOException {
            return (FilterRequest) b(g, inputStream);
        }

        public static FilterRequest b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FilterRequest) b(g, inputStream, extensionRegistryLite);
        }

        public static FilterRequest a(CodedInputStream codedInputStream) throws IOException {
            return (FilterRequest) GeneratedMessageLite.b(g, codedInputStream);
        }

        public static FilterRequest a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FilterRequest) GeneratedMessageLite.b(g, codedInputStream, extensionRegistryLite);
        }

        public static Builder f() {
            return (Builder) g.Y();
        }

        public static Builder a(FilterRequest filterRequest) {
            return (Builder) ((Builder) g.Y()).b(filterRequest);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<FilterRequest, Builder> implements FilterRequestOrBuilder {
            private Builder() {
                super(FilterRequest.g);
            }

            public boolean a() {
                return ((FilterRequest) this.f11310a).a();
            }

            public String b() {
                return ((FilterRequest) this.f11310a).b();
            }

            public ByteString c() {
                return ((FilterRequest) this.f11310a).c();
            }

            public Builder a(String str) {
                S();
                ((FilterRequest) this.f11310a).a(str);
                return this;
            }

            public Builder f() {
                S();
                ((FilterRequest) this.f11310a).j();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((FilterRequest) this.f11310a).c(byteString);
                return this;
            }

            public boolean d() {
                return ((FilterRequest) this.f11310a).d();
            }

            public MIMCPacket e() {
                return ((FilterRequest) this.f11310a).e();
            }

            public Builder a(MIMCPacket mIMCPacket) {
                S();
                ((FilterRequest) this.f11310a).a(mIMCPacket);
                return this;
            }

            public Builder a(MIMCPacket.Builder builder) {
                S();
                ((FilterRequest) this.f11310a).a(builder);
                return this;
            }

            public Builder b(MIMCPacket mIMCPacket) {
                S();
                ((FilterRequest) this.f11310a).b(mIMCPacket);
                return this;
            }

            public Builder g() {
                S();
                ((FilterRequest) this.f11310a).l();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new FilterRequest();
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
                    } else if (!d()) {
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
                    FilterRequest filterRequest = (FilterRequest) obj2;
                    this.d = visitor.a(a(), this.d, filterRequest.a(), filterRequest.d);
                    this.e = (MIMCPacket) visitor.a(this.e, filterRequest.e);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.c |= filterRequest.c;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    String l = codedInputStream.l();
                                    this.c |= 1;
                                    this.d = l;
                                } else if (a2 == 18) {
                                    MIMCPacket.Builder builder = (this.c & 2) == 2 ? (MIMCPacket.Builder) this.e.Y() : null;
                                    this.e = (MIMCPacket) codedInputStream.a(MIMCPacket.r(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.e);
                                        this.e = (MIMCPacket) builder.Y();
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
                    if (h == null) {
                        synchronized (FilterRequest.class) {
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

        public static FilterRequest g() {
            return g;
        }

        public static Parser<FilterRequest> h() {
            return g.M();
        }
    }

    public static final class FilterResponse extends GeneratedMessageLite<FilterResponse, Builder> implements FilterResponseOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11226a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        /* access modifiers changed from: private */
        public static final FilterResponse k = new FilterResponse();
        private static volatile Parser<FilterResponse> l;
        private int e;
        private String f = "";
        private int g = 1;
        private int h;
        private boolean i;
        private byte j = -1;

        private FilterResponse() {
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
        public void o() {
            this.e &= -2;
            this.f = l().b();
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

        public MIMC_MSG_TYPE e() {
            MIMC_MSG_TYPE forNumber = MIMC_MSG_TYPE.forNumber(this.g);
            return forNumber == null ? MIMC_MSG_TYPE.P2P_MESSAGE : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(MIMC_MSG_TYPE mimc_msg_type) {
            if (mimc_msg_type != null) {
                this.e |= 2;
                this.g = mimc_msg_type.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void p() {
            this.e &= -3;
            this.g = 1;
        }

        public boolean f() {
            return (this.e & 4) == 4;
        }

        public ErrorCode g() {
            ErrorCode forNumber = ErrorCode.forNumber(this.h);
            return forNumber == null ? ErrorCode.OK : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(ErrorCode errorCode) {
            if (errorCode != null) {
                this.e |= 4;
                this.h = errorCode.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void q() {
            this.e &= -5;
            this.h = 0;
        }

        public boolean h() {
            return (this.e & 8) == 8;
        }

        public boolean i() {
            return this.i;
        }

        /* access modifiers changed from: private */
        public void a(boolean z) {
            this.e |= 8;
            this.i = z;
        }

        /* access modifiers changed from: private */
        public void r() {
            this.e &= -9;
            this.i = false;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.e & 1) == 1) {
                codedOutputStream.a(1, b());
            }
            if ((this.e & 2) == 2) {
                codedOutputStream.g(2, this.g);
            }
            if ((this.e & 4) == 4) {
                codedOutputStream.g(3, this.h);
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
                i3 += CodedOutputStream.m(2, this.g);
            }
            if ((this.e & 4) == 4) {
                i3 += CodedOutputStream.m(3, this.h);
            }
            if ((this.e & 8) == 8) {
                i3 += CodedOutputStream.b(4, this.i);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static FilterResponse a(ByteString byteString) throws InvalidProtocolBufferException {
            return (FilterResponse) GeneratedMessageLite.a(k, byteString);
        }

        public static FilterResponse a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FilterResponse) GeneratedMessageLite.a(k, byteString, extensionRegistryLite);
        }

        public static FilterResponse a(byte[] bArr) throws InvalidProtocolBufferException {
            return (FilterResponse) GeneratedMessageLite.a(k, bArr);
        }

        public static FilterResponse a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FilterResponse) GeneratedMessageLite.a(k, bArr, extensionRegistryLite);
        }

        public static FilterResponse a(InputStream inputStream) throws IOException {
            return (FilterResponse) GeneratedMessageLite.a(k, inputStream);
        }

        public static FilterResponse a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FilterResponse) GeneratedMessageLite.a(k, inputStream, extensionRegistryLite);
        }

        public static FilterResponse b(InputStream inputStream) throws IOException {
            return (FilterResponse) b(k, inputStream);
        }

        public static FilterResponse b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FilterResponse) b(k, inputStream, extensionRegistryLite);
        }

        public static FilterResponse a(CodedInputStream codedInputStream) throws IOException {
            return (FilterResponse) GeneratedMessageLite.b(k, codedInputStream);
        }

        public static FilterResponse a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FilterResponse) GeneratedMessageLite.b(k, codedInputStream, extensionRegistryLite);
        }

        public static Builder j() {
            return (Builder) k.Y();
        }

        public static Builder a(FilterResponse filterResponse) {
            return (Builder) ((Builder) k.Y()).b(filterResponse);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<FilterResponse, Builder> implements FilterResponseOrBuilder {
            private Builder() {
                super(FilterResponse.k);
            }

            public boolean a() {
                return ((FilterResponse) this.f11310a).a();
            }

            public String b() {
                return ((FilterResponse) this.f11310a).b();
            }

            public ByteString c() {
                return ((FilterResponse) this.f11310a).c();
            }

            public Builder a(String str) {
                S();
                ((FilterResponse) this.f11310a).a(str);
                return this;
            }

            public Builder j() {
                S();
                ((FilterResponse) this.f11310a).o();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((FilterResponse) this.f11310a).c(byteString);
                return this;
            }

            public boolean d() {
                return ((FilterResponse) this.f11310a).d();
            }

            public MIMC_MSG_TYPE e() {
                return ((FilterResponse) this.f11310a).e();
            }

            public Builder a(MIMC_MSG_TYPE mimc_msg_type) {
                S();
                ((FilterResponse) this.f11310a).a(mimc_msg_type);
                return this;
            }

            public Builder k() {
                S();
                ((FilterResponse) this.f11310a).p();
                return this;
            }

            public boolean f() {
                return ((FilterResponse) this.f11310a).f();
            }

            public ErrorCode g() {
                return ((FilterResponse) this.f11310a).g();
            }

            public Builder a(ErrorCode errorCode) {
                S();
                ((FilterResponse) this.f11310a).a(errorCode);
                return this;
            }

            public Builder l() {
                S();
                ((FilterResponse) this.f11310a).q();
                return this;
            }

            public boolean h() {
                return ((FilterResponse) this.f11310a).h();
            }

            public boolean i() {
                return ((FilterResponse) this.f11310a).i();
            }

            public Builder a(boolean z) {
                S();
                ((FilterResponse) this.f11310a).a(z);
                return this;
            }

            public Builder m() {
                S();
                ((FilterResponse) this.f11310a).r();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new FilterResponse();
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
                    FilterResponse filterResponse = (FilterResponse) obj2;
                    this.f = visitor.a(a(), this.f, filterResponse.a(), filterResponse.f);
                    this.g = visitor.a(d(), this.g, filterResponse.d(), filterResponse.g);
                    this.h = visitor.a(f(), this.h, filterResponse.f(), filterResponse.h);
                    this.i = visitor.a(h(), this.i, filterResponse.h(), filterResponse.i);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.e |= filterResponse.e;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    String l2 = codedInputStream.l();
                                    this.e |= 1;
                                    this.f = l2;
                                } else if (a2 == 16) {
                                    int r = codedInputStream.r();
                                    if (MIMC_MSG_TYPE.forNumber(r) == null) {
                                        super.a(2, r);
                                    } else {
                                        this.e |= 2;
                                        this.g = r;
                                    }
                                } else if (a2 == 24) {
                                    int r2 = codedInputStream.r();
                                    if (ErrorCode.forNumber(r2) == null) {
                                        super.a(3, r2);
                                    } else {
                                        this.e |= 4;
                                        this.h = r2;
                                    }
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
                        synchronized (FilterResponse.class) {
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

        public static FilterResponse l() {
            return k;
        }

        public static Parser<FilterResponse> m() {
            return k.M();
        }
    }

    public static final class PullMessageRequest extends GeneratedMessageLite<PullMessageRequest, Builder> implements PullMessageRequestOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11238a = 1;
        public static final int b = 2;
        /* access modifiers changed from: private */
        public static final PullMessageRequest g = new PullMessageRequest();
        private static volatile Parser<PullMessageRequest> h;
        private int c;
        private long d;
        private String e = "";
        private byte f = -1;

        private PullMessageRequest() {
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

        public static PullMessageRequest a(ByteString byteString) throws InvalidProtocolBufferException {
            return (PullMessageRequest) GeneratedMessageLite.a(g, byteString);
        }

        public static PullMessageRequest a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PullMessageRequest) GeneratedMessageLite.a(g, byteString, extensionRegistryLite);
        }

        public static PullMessageRequest a(byte[] bArr) throws InvalidProtocolBufferException {
            return (PullMessageRequest) GeneratedMessageLite.a(g, bArr);
        }

        public static PullMessageRequest a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PullMessageRequest) GeneratedMessageLite.a(g, bArr, extensionRegistryLite);
        }

        public static PullMessageRequest a(InputStream inputStream) throws IOException {
            return (PullMessageRequest) GeneratedMessageLite.a(g, inputStream);
        }

        public static PullMessageRequest a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PullMessageRequest) GeneratedMessageLite.a(g, inputStream, extensionRegistryLite);
        }

        public static PullMessageRequest b(InputStream inputStream) throws IOException {
            return (PullMessageRequest) b(g, inputStream);
        }

        public static PullMessageRequest b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PullMessageRequest) b(g, inputStream, extensionRegistryLite);
        }

        public static PullMessageRequest a(CodedInputStream codedInputStream) throws IOException {
            return (PullMessageRequest) GeneratedMessageLite.b(g, codedInputStream);
        }

        public static PullMessageRequest a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PullMessageRequest) GeneratedMessageLite.b(g, codedInputStream, extensionRegistryLite);
        }

        public static Builder f() {
            return (Builder) g.Y();
        }

        public static Builder a(PullMessageRequest pullMessageRequest) {
            return (Builder) ((Builder) g.Y()).b(pullMessageRequest);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PullMessageRequest, Builder> implements PullMessageRequestOrBuilder {
            private Builder() {
                super(PullMessageRequest.g);
            }

            public boolean a() {
                return ((PullMessageRequest) this.f11310a).a();
            }

            public long b() {
                return ((PullMessageRequest) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((PullMessageRequest) this.f11310a).a(j);
                return this;
            }

            public Builder f() {
                S();
                ((PullMessageRequest) this.f11310a).j();
                return this;
            }

            public boolean c() {
                return ((PullMessageRequest) this.f11310a).c();
            }

            public String d() {
                return ((PullMessageRequest) this.f11310a).d();
            }

            public ByteString e() {
                return ((PullMessageRequest) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((PullMessageRequest) this.f11310a).a(str);
                return this;
            }

            public Builder g() {
                S();
                ((PullMessageRequest) this.f11310a).l();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((PullMessageRequest) this.f11310a).c(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new PullMessageRequest();
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
                    PullMessageRequest pullMessageRequest = (PullMessageRequest) obj2;
                    this.d = visitor.a(a(), this.d, pullMessageRequest.a(), pullMessageRequest.d);
                    this.e = visitor.a(c(), this.e, pullMessageRequest.c(), pullMessageRequest.e);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.c |= pullMessageRequest.c;
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
                        synchronized (PullMessageRequest.class) {
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

        public static PullMessageRequest g() {
            return g;
        }

        public static Parser<PullMessageRequest> h() {
            return g.M();
        }
    }

    public static final class TopicMessage extends GeneratedMessageLite<TopicMessage, Builder> implements TopicMessageOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11240a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        /* access modifiers changed from: private */
        public static final TopicMessage k = new TopicMessage();
        private static volatile Parser<TopicMessage> l;
        private int e;
        private long f;
        private MIMCPacket g;
        private long h;
        private String i = "";
        private byte j = -1;

        private TopicMessage() {
        }

        public boolean a() {
            return (this.e & 1) == 1;
        }

        public long b() {
            return this.f;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.e |= 1;
            this.f = j2;
        }

        /* access modifiers changed from: private */
        public void o() {
            this.e &= -2;
            this.f = 0;
        }

        public boolean c() {
            return (this.e & 2) == 2;
        }

        public MIMCPacket d() {
            return this.g == null ? MIMCPacket.q() : this.g;
        }

        /* access modifiers changed from: private */
        public void a(MIMCPacket mIMCPacket) {
            if (mIMCPacket != null) {
                this.g = mIMCPacket;
                this.e |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(MIMCPacket.Builder builder) {
            this.g = (MIMCPacket) builder.Z();
            this.e |= 2;
        }

        /* access modifiers changed from: private */
        public void b(MIMCPacket mIMCPacket) {
            if (this.g == null || this.g == MIMCPacket.q()) {
                this.g = mIMCPacket;
            } else {
                this.g = (MIMCPacket) ((MIMCPacket.Builder) MIMCPacket.a(this.g).b(mIMCPacket)).Y();
            }
            this.e |= 2;
        }

        /* access modifiers changed from: private */
        public void p() {
            this.g = null;
            this.e &= -3;
        }

        public boolean e() {
            return (this.e & 4) == 4;
        }

        public long f() {
            return this.h;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.e |= 4;
            this.h = j2;
        }

        /* access modifiers changed from: private */
        public void q() {
            this.e &= -5;
            this.h = 0;
        }

        public boolean g() {
            return (this.e & 8) == 8;
        }

        public String h() {
            return this.i;
        }

        public ByteString i() {
            return ByteString.copyFromUtf8(this.i);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.e |= 8;
                this.i = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void r() {
            this.e &= -9;
            this.i = l().h();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.e |= 8;
                this.i = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.e & 1) == 1) {
                codedOutputStream.b(1, this.f);
            }
            if ((this.e & 2) == 2) {
                codedOutputStream.a(2, (MessageLite) d());
            }
            if ((this.e & 4) == 4) {
                codedOutputStream.a(3, this.h);
            }
            if ((this.e & 8) == 8) {
                codedOutputStream.a(4, h());
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
                i3 = 0 + CodedOutputStream.g(1, this.f);
            }
            if ((this.e & 2) == 2) {
                i3 += CodedOutputStream.c(2, (MessageLite) d());
            }
            if ((this.e & 4) == 4) {
                i3 += CodedOutputStream.f(3, this.h);
            }
            if ((this.e & 8) == 8) {
                i3 += CodedOutputStream.b(4, h());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static TopicMessage a(ByteString byteString) throws InvalidProtocolBufferException {
            return (TopicMessage) GeneratedMessageLite.a(k, byteString);
        }

        public static TopicMessage a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TopicMessage) GeneratedMessageLite.a(k, byteString, extensionRegistryLite);
        }

        public static TopicMessage a(byte[] bArr) throws InvalidProtocolBufferException {
            return (TopicMessage) GeneratedMessageLite.a(k, bArr);
        }

        public static TopicMessage a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TopicMessage) GeneratedMessageLite.a(k, bArr, extensionRegistryLite);
        }

        public static TopicMessage a(InputStream inputStream) throws IOException {
            return (TopicMessage) GeneratedMessageLite.a(k, inputStream);
        }

        public static TopicMessage a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TopicMessage) GeneratedMessageLite.a(k, inputStream, extensionRegistryLite);
        }

        public static TopicMessage b(InputStream inputStream) throws IOException {
            return (TopicMessage) b(k, inputStream);
        }

        public static TopicMessage b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TopicMessage) b(k, inputStream, extensionRegistryLite);
        }

        public static TopicMessage a(CodedInputStream codedInputStream) throws IOException {
            return (TopicMessage) GeneratedMessageLite.b(k, codedInputStream);
        }

        public static TopicMessage a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TopicMessage) GeneratedMessageLite.b(k, codedInputStream, extensionRegistryLite);
        }

        public static Builder j() {
            return (Builder) k.Y();
        }

        public static Builder a(TopicMessage topicMessage) {
            return (Builder) ((Builder) k.Y()).b(topicMessage);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<TopicMessage, Builder> implements TopicMessageOrBuilder {
            private Builder() {
                super(TopicMessage.k);
            }

            public boolean a() {
                return ((TopicMessage) this.f11310a).a();
            }

            public long b() {
                return ((TopicMessage) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((TopicMessage) this.f11310a).a(j);
                return this;
            }

            public Builder j() {
                S();
                ((TopicMessage) this.f11310a).o();
                return this;
            }

            public boolean c() {
                return ((TopicMessage) this.f11310a).c();
            }

            public MIMCPacket d() {
                return ((TopicMessage) this.f11310a).d();
            }

            public Builder a(MIMCPacket mIMCPacket) {
                S();
                ((TopicMessage) this.f11310a).a(mIMCPacket);
                return this;
            }

            public Builder a(MIMCPacket.Builder builder) {
                S();
                ((TopicMessage) this.f11310a).a(builder);
                return this;
            }

            public Builder b(MIMCPacket mIMCPacket) {
                S();
                ((TopicMessage) this.f11310a).b(mIMCPacket);
                return this;
            }

            public Builder k() {
                S();
                ((TopicMessage) this.f11310a).p();
                return this;
            }

            public boolean e() {
                return ((TopicMessage) this.f11310a).e();
            }

            public long f() {
                return ((TopicMessage) this.f11310a).f();
            }

            public Builder b(long j) {
                S();
                ((TopicMessage) this.f11310a).b(j);
                return this;
            }

            public Builder l() {
                S();
                ((TopicMessage) this.f11310a).q();
                return this;
            }

            public boolean g() {
                return ((TopicMessage) this.f11310a).g();
            }

            public String h() {
                return ((TopicMessage) this.f11310a).h();
            }

            public ByteString i() {
                return ((TopicMessage) this.f11310a).i();
            }

            public Builder a(String str) {
                S();
                ((TopicMessage) this.f11310a).a(str);
                return this;
            }

            public Builder m() {
                S();
                ((TopicMessage) this.f11310a).r();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((TopicMessage) this.f11310a).c(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new TopicMessage();
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
                    TopicMessage topicMessage = (TopicMessage) obj2;
                    this.f = visitor.a(a(), this.f, topicMessage.a(), topicMessage.f);
                    this.g = (MIMCPacket) visitor.a(this.g, topicMessage.g);
                    this.h = visitor.a(e(), this.h, topicMessage.e(), topicMessage.h);
                    this.i = visitor.a(g(), this.i, topicMessage.g(), topicMessage.i);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.e |= topicMessage.e;
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
                                    this.f = codedInputStream.f();
                                } else if (a2 == 18) {
                                    MIMCPacket.Builder builder = (this.e & 2) == 2 ? (MIMCPacket.Builder) this.g.Y() : null;
                                    this.g = (MIMCPacket) codedInputStream.a(MIMCPacket.r(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.g);
                                        this.g = (MIMCPacket) builder.Y();
                                    }
                                    this.e |= 2;
                                } else if (a2 == 24) {
                                    this.e |= 4;
                                    this.h = codedInputStream.g();
                                } else if (a2 == 34) {
                                    String l2 = codedInputStream.l();
                                    this.e = 8 | this.e;
                                    this.i = l2;
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
                        synchronized (TopicMessage.class) {
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

        public static TopicMessage l() {
            return k;
        }

        public static Parser<TopicMessage> m() {
            return k.M();
        }
    }

    public static final class QueryAppinfoRequest extends GeneratedMessageLite<QueryAppinfoRequest, Builder> implements QueryAppinfoRequestOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11239a = 1;
        public static final int b = 2;
        /* access modifiers changed from: private */
        public static final QueryAppinfoRequest g = new QueryAppinfoRequest();
        private static volatile Parser<QueryAppinfoRequest> h;
        private int c;
        private long d;
        private String e = "";
        private byte f = -1;

        private QueryAppinfoRequest() {
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

        public static QueryAppinfoRequest a(ByteString byteString) throws InvalidProtocolBufferException {
            return (QueryAppinfoRequest) GeneratedMessageLite.a(g, byteString);
        }

        public static QueryAppinfoRequest a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (QueryAppinfoRequest) GeneratedMessageLite.a(g, byteString, extensionRegistryLite);
        }

        public static QueryAppinfoRequest a(byte[] bArr) throws InvalidProtocolBufferException {
            return (QueryAppinfoRequest) GeneratedMessageLite.a(g, bArr);
        }

        public static QueryAppinfoRequest a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (QueryAppinfoRequest) GeneratedMessageLite.a(g, bArr, extensionRegistryLite);
        }

        public static QueryAppinfoRequest a(InputStream inputStream) throws IOException {
            return (QueryAppinfoRequest) GeneratedMessageLite.a(g, inputStream);
        }

        public static QueryAppinfoRequest a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (QueryAppinfoRequest) GeneratedMessageLite.a(g, inputStream, extensionRegistryLite);
        }

        public static QueryAppinfoRequest b(InputStream inputStream) throws IOException {
            return (QueryAppinfoRequest) b(g, inputStream);
        }

        public static QueryAppinfoRequest b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (QueryAppinfoRequest) b(g, inputStream, extensionRegistryLite);
        }

        public static QueryAppinfoRequest a(CodedInputStream codedInputStream) throws IOException {
            return (QueryAppinfoRequest) GeneratedMessageLite.b(g, codedInputStream);
        }

        public static QueryAppinfoRequest a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (QueryAppinfoRequest) GeneratedMessageLite.b(g, codedInputStream, extensionRegistryLite);
        }

        public static Builder f() {
            return (Builder) g.Y();
        }

        public static Builder a(QueryAppinfoRequest queryAppinfoRequest) {
            return (Builder) ((Builder) g.Y()).b(queryAppinfoRequest);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<QueryAppinfoRequest, Builder> implements QueryAppinfoRequestOrBuilder {
            private Builder() {
                super(QueryAppinfoRequest.g);
            }

            public boolean a() {
                return ((QueryAppinfoRequest) this.f11310a).a();
            }

            public long b() {
                return ((QueryAppinfoRequest) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((QueryAppinfoRequest) this.f11310a).a(j);
                return this;
            }

            public Builder f() {
                S();
                ((QueryAppinfoRequest) this.f11310a).j();
                return this;
            }

            public boolean c() {
                return ((QueryAppinfoRequest) this.f11310a).c();
            }

            public String d() {
                return ((QueryAppinfoRequest) this.f11310a).d();
            }

            public ByteString e() {
                return ((QueryAppinfoRequest) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((QueryAppinfoRequest) this.f11310a).a(str);
                return this;
            }

            public Builder g() {
                S();
                ((QueryAppinfoRequest) this.f11310a).l();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((QueryAppinfoRequest) this.f11310a).c(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new QueryAppinfoRequest();
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
                    }
                    if (booleanValue) {
                        this.f = 1;
                    }
                    return g;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    QueryAppinfoRequest queryAppinfoRequest = (QueryAppinfoRequest) obj2;
                    this.d = visitor.a(a(), this.d, queryAppinfoRequest.a(), queryAppinfoRequest.d);
                    this.e = visitor.a(c(), this.e, queryAppinfoRequest.c(), queryAppinfoRequest.e);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.c |= queryAppinfoRequest.c;
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
                        synchronized (QueryAppinfoRequest.class) {
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

        public static QueryAppinfoRequest g() {
            return g;
        }

        public static Parser<QueryAppinfoRequest> h() {
            return g.M();
        }
    }

    public static final class Appinfo extends GeneratedMessageLite<Appinfo, Builder> implements AppinfoOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11223a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        /* access modifiers changed from: private */
        public static final Appinfo t = new Appinfo();
        private static volatile Parser<Appinfo> u;
        private int h;
        private long i;
        private String j = "";
        private String k = "";
        private long l;
        private String p = "";
        private String q = "";
        private String r = "";
        private byte s = -1;

        private Appinfo() {
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
        public void y() {
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
        public void z() {
            this.h &= -3;
            this.j = v().d();
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
        public void A() {
            this.h &= -5;
            this.k = v().g();
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

        public long j() {
            return this.l;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.h |= 8;
            this.l = j2;
        }

        /* access modifiers changed from: private */
        public void B() {
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
        public void C() {
            this.h &= -17;
            this.p = v().m();
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

        public String p() {
            return this.q;
        }

        public ByteString q() {
            return ByteString.copyFromUtf8(this.q);
        }

        /* access modifiers changed from: private */
        public void d(String str) {
            if (str != null) {
                this.h |= 32;
                this.q = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void D() {
            this.h &= -33;
            this.q = v().p();
        }

        /* access modifiers changed from: private */
        public void f(ByteString byteString) {
            if (byteString != null) {
                this.h |= 32;
                this.q = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean r() {
            return (this.h & 64) == 64;
        }

        public String s() {
            return this.r;
        }

        public ByteString t() {
            return ByteString.copyFromUtf8(this.r);
        }

        /* access modifiers changed from: private */
        public void e(String str) {
            if (str != null) {
                this.h |= 64;
                this.r = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void E() {
            this.h &= -65;
            this.r = v().s();
        }

        /* access modifiers changed from: private */
        public void g(ByteString byteString) {
            if (byteString != null) {
                this.h |= 64;
                this.r = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
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
                codedOutputStream.a(6, p());
            }
            if ((this.h & 64) == 64) {
                codedOutputStream.a(7, s());
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
                i3 += CodedOutputStream.g(4, this.l);
            }
            if ((this.h & 16) == 16) {
                i3 += CodedOutputStream.b(5, m());
            }
            if ((this.h & 32) == 32) {
                i3 += CodedOutputStream.b(6, p());
            }
            if ((this.h & 64) == 64) {
                i3 += CodedOutputStream.b(7, s());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static Appinfo a(ByteString byteString) throws InvalidProtocolBufferException {
            return (Appinfo) GeneratedMessageLite.a(t, byteString);
        }

        public static Appinfo a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Appinfo) GeneratedMessageLite.a(t, byteString, extensionRegistryLite);
        }

        public static Appinfo a(byte[] bArr) throws InvalidProtocolBufferException {
            return (Appinfo) GeneratedMessageLite.a(t, bArr);
        }

        public static Appinfo a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Appinfo) GeneratedMessageLite.a(t, bArr, extensionRegistryLite);
        }

        public static Appinfo a(InputStream inputStream) throws IOException {
            return (Appinfo) GeneratedMessageLite.a(t, inputStream);
        }

        public static Appinfo a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Appinfo) GeneratedMessageLite.a(t, inputStream, extensionRegistryLite);
        }

        public static Appinfo b(InputStream inputStream) throws IOException {
            return (Appinfo) b(t, inputStream);
        }

        public static Appinfo b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Appinfo) b(t, inputStream, extensionRegistryLite);
        }

        public static Appinfo a(CodedInputStream codedInputStream) throws IOException {
            return (Appinfo) GeneratedMessageLite.b(t, codedInputStream);
        }

        public static Appinfo a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Appinfo) GeneratedMessageLite.b(t, codedInputStream, extensionRegistryLite);
        }

        public static Builder u() {
            return (Builder) t.Y();
        }

        public static Builder a(Appinfo appinfo) {
            return (Builder) ((Builder) t.Y()).b(appinfo);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Appinfo, Builder> implements AppinfoOrBuilder {
            private Builder() {
                super(Appinfo.t);
            }

            public boolean a() {
                return ((Appinfo) this.f11310a).a();
            }

            public long b() {
                return ((Appinfo) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((Appinfo) this.f11310a).a(j);
                return this;
            }

            public Builder k() {
                S();
                ((Appinfo) this.f11310a).y();
                return this;
            }

            public boolean c() {
                return ((Appinfo) this.f11310a).c();
            }

            public String d() {
                return ((Appinfo) this.f11310a).d();
            }

            public ByteString e() {
                return ((Appinfo) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((Appinfo) this.f11310a).a(str);
                return this;
            }

            public Builder u() {
                S();
                ((Appinfo) this.f11310a).z();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((Appinfo) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((Appinfo) this.f11310a).f();
            }

            public String g() {
                return ((Appinfo) this.f11310a).g();
            }

            public ByteString h() {
                return ((Appinfo) this.f11310a).h();
            }

            public Builder b(String str) {
                S();
                ((Appinfo) this.f11310a).b(str);
                return this;
            }

            public Builder v() {
                S();
                ((Appinfo) this.f11310a).A();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((Appinfo) this.f11310a).d(byteString);
                return this;
            }

            public boolean i() {
                return ((Appinfo) this.f11310a).i();
            }

            public long j() {
                return ((Appinfo) this.f11310a).j();
            }

            public Builder b(long j) {
                S();
                ((Appinfo) this.f11310a).b(j);
                return this;
            }

            public Builder w() {
                S();
                ((Appinfo) this.f11310a).B();
                return this;
            }

            public boolean l() {
                return ((Appinfo) this.f11310a).l();
            }

            public String m() {
                return ((Appinfo) this.f11310a).m();
            }

            public ByteString n() {
                return ((Appinfo) this.f11310a).n();
            }

            public Builder c(String str) {
                S();
                ((Appinfo) this.f11310a).c(str);
                return this;
            }

            public Builder x() {
                S();
                ((Appinfo) this.f11310a).C();
                return this;
            }

            public Builder c(ByteString byteString) {
                S();
                ((Appinfo) this.f11310a).e(byteString);
                return this;
            }

            public boolean o() {
                return ((Appinfo) this.f11310a).o();
            }

            public String p() {
                return ((Appinfo) this.f11310a).p();
            }

            public ByteString q() {
                return ((Appinfo) this.f11310a).q();
            }

            public Builder d(String str) {
                S();
                ((Appinfo) this.f11310a).d(str);
                return this;
            }

            public Builder y() {
                S();
                ((Appinfo) this.f11310a).D();
                return this;
            }

            public Builder d(ByteString byteString) {
                S();
                ((Appinfo) this.f11310a).f(byteString);
                return this;
            }

            public boolean r() {
                return ((Appinfo) this.f11310a).r();
            }

            public String s() {
                return ((Appinfo) this.f11310a).s();
            }

            public ByteString t() {
                return ((Appinfo) this.f11310a).t();
            }

            public Builder e(String str) {
                S();
                ((Appinfo) this.f11310a).e(str);
                return this;
            }

            public Builder z() {
                S();
                ((Appinfo) this.f11310a).E();
                return this;
            }

            public Builder e(ByteString byteString) {
                S();
                ((Appinfo) this.f11310a).g(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new Appinfo();
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
                    }
                    if (booleanValue) {
                        this.s = 1;
                    }
                    return t;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    Appinfo appinfo = (Appinfo) obj2;
                    this.i = visitor.a(a(), this.i, appinfo.a(), appinfo.i);
                    this.j = visitor.a(c(), this.j, appinfo.c(), appinfo.j);
                    this.k = visitor.a(f(), this.k, appinfo.f(), appinfo.k);
                    this.l = visitor.a(i(), this.l, appinfo.i(), appinfo.l);
                    this.p = visitor.a(l(), this.p, appinfo.l(), appinfo.p);
                    this.q = visitor.a(o(), this.q, appinfo.o(), appinfo.q);
                    this.r = visitor.a(r(), this.r, appinfo.r(), appinfo.r);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.h |= appinfo.h;
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
                                    this.l = codedInputStream.f();
                                } else if (a2 == 42) {
                                    String l4 = codedInputStream.l();
                                    this.h |= 16;
                                    this.p = l4;
                                } else if (a2 == 50) {
                                    String l5 = codedInputStream.l();
                                    this.h |= 32;
                                    this.q = l5;
                                } else if (a2 == 58) {
                                    String l6 = codedInputStream.l();
                                    this.h |= 64;
                                    this.r = l6;
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
                        synchronized (Appinfo.class) {
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

        public static Appinfo v() {
            return t;
        }

        public static Parser<Appinfo> w() {
            return t.M();
        }
    }

    public static final class MIMCPacket extends GeneratedMessageLite<MIMCPacket, Builder> implements MIMCPacketOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11230a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        /* access modifiers changed from: private */
        public static final MIMCPacket q = new MIMCPacket();
        private static volatile Parser<MIMCPacket> r;
        private int g;
        private String h = "";
        private String i = "";
        private long j;
        private int k = 1;
        private ByteString l = ByteString.EMPTY;
        private long p;

        private MIMCPacket() {
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
        public void t() {
            this.g &= -2;
            this.h = q().b();
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
        public void u() {
            this.g &= -3;
            this.i = q().e();
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

        public long h() {
            return this.j;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.g |= 4;
            this.j = j2;
        }

        /* access modifiers changed from: private */
        public void v() {
            this.g &= -5;
            this.j = 0;
        }

        public boolean i() {
            return (this.g & 8) == 8;
        }

        public MIMC_MSG_TYPE j() {
            MIMC_MSG_TYPE forNumber = MIMC_MSG_TYPE.forNumber(this.k);
            return forNumber == null ? MIMC_MSG_TYPE.P2P_MESSAGE : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(MIMC_MSG_TYPE mimc_msg_type) {
            if (mimc_msg_type != null) {
                this.g |= 8;
                this.k = mimc_msg_type.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void w() {
            this.g &= -9;
            this.k = 1;
        }

        public boolean l() {
            return (this.g & 16) == 16;
        }

        public ByteString m() {
            return this.l;
        }

        /* access modifiers changed from: private */
        public void e(ByteString byteString) {
            if (byteString != null) {
                this.g |= 16;
                this.l = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void x() {
            this.g &= -17;
            this.l = q().m();
        }

        public boolean n() {
            return (this.g & 32) == 32;
        }

        public long o() {
            return this.p;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.g |= 32;
            this.p = j2;
        }

        /* access modifiers changed from: private */
        public void y() {
            this.g &= -33;
            this.p = 0;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.g & 1) == 1) {
                codedOutputStream.a(1, b());
            }
            if ((this.g & 2) == 2) {
                codedOutputStream.a(2, e());
            }
            if ((this.g & 4) == 4) {
                codedOutputStream.a(3, this.j);
            }
            if ((this.g & 8) == 8) {
                codedOutputStream.g(4, this.k);
            }
            if ((this.g & 16) == 16) {
                codedOutputStream.a(5, this.l);
            }
            if ((this.g & 32) == 32) {
                codedOutputStream.a(6, this.p);
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
                i3 += CodedOutputStream.f(3, this.j);
            }
            if ((this.g & 8) == 8) {
                i3 += CodedOutputStream.m(4, this.k);
            }
            if ((this.g & 16) == 16) {
                i3 += CodedOutputStream.c(5, this.l);
            }
            if ((this.g & 32) == 32) {
                i3 += CodedOutputStream.f(6, this.p);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static MIMCPacket a(ByteString byteString) throws InvalidProtocolBufferException {
            return (MIMCPacket) GeneratedMessageLite.a(q, byteString);
        }

        public static MIMCPacket a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCPacket) GeneratedMessageLite.a(q, byteString, extensionRegistryLite);
        }

        public static MIMCPacket a(byte[] bArr) throws InvalidProtocolBufferException {
            return (MIMCPacket) GeneratedMessageLite.a(q, bArr);
        }

        public static MIMCPacket a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCPacket) GeneratedMessageLite.a(q, bArr, extensionRegistryLite);
        }

        public static MIMCPacket a(InputStream inputStream) throws IOException {
            return (MIMCPacket) GeneratedMessageLite.a(q, inputStream);
        }

        public static MIMCPacket a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCPacket) GeneratedMessageLite.a(q, inputStream, extensionRegistryLite);
        }

        public static MIMCPacket b(InputStream inputStream) throws IOException {
            return (MIMCPacket) b(q, inputStream);
        }

        public static MIMCPacket b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCPacket) b(q, inputStream, extensionRegistryLite);
        }

        public static MIMCPacket a(CodedInputStream codedInputStream) throws IOException {
            return (MIMCPacket) GeneratedMessageLite.b(q, codedInputStream);
        }

        public static MIMCPacket a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCPacket) GeneratedMessageLite.b(q, codedInputStream, extensionRegistryLite);
        }

        public static Builder p() {
            return (Builder) q.Y();
        }

        public static Builder a(MIMCPacket mIMCPacket) {
            return (Builder) ((Builder) q.Y()).b(mIMCPacket);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MIMCPacket, Builder> implements MIMCPacketOrBuilder {
            private Builder() {
                super(MIMCPacket.q);
            }

            public boolean a() {
                return ((MIMCPacket) this.f11310a).a();
            }

            public String b() {
                return ((MIMCPacket) this.f11310a).b();
            }

            public ByteString c() {
                return ((MIMCPacket) this.f11310a).c();
            }

            public Builder a(String str) {
                S();
                ((MIMCPacket) this.f11310a).a(str);
                return this;
            }

            public Builder k() {
                S();
                ((MIMCPacket) this.f11310a).t();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((MIMCPacket) this.f11310a).c(byteString);
                return this;
            }

            public boolean d() {
                return ((MIMCPacket) this.f11310a).d();
            }

            public String e() {
                return ((MIMCPacket) this.f11310a).e();
            }

            public ByteString f() {
                return ((MIMCPacket) this.f11310a).f();
            }

            public Builder b(String str) {
                S();
                ((MIMCPacket) this.f11310a).b(str);
                return this;
            }

            public Builder p() {
                S();
                ((MIMCPacket) this.f11310a).u();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((MIMCPacket) this.f11310a).d(byteString);
                return this;
            }

            public boolean g() {
                return ((MIMCPacket) this.f11310a).g();
            }

            public long h() {
                return ((MIMCPacket) this.f11310a).h();
            }

            public Builder a(long j) {
                S();
                ((MIMCPacket) this.f11310a).a(j);
                return this;
            }

            public Builder q() {
                S();
                ((MIMCPacket) this.f11310a).v();
                return this;
            }

            public boolean i() {
                return ((MIMCPacket) this.f11310a).i();
            }

            public MIMC_MSG_TYPE j() {
                return ((MIMCPacket) this.f11310a).j();
            }

            public Builder a(MIMC_MSG_TYPE mimc_msg_type) {
                S();
                ((MIMCPacket) this.f11310a).a(mimc_msg_type);
                return this;
            }

            public Builder r() {
                S();
                ((MIMCPacket) this.f11310a).w();
                return this;
            }

            public boolean l() {
                return ((MIMCPacket) this.f11310a).l();
            }

            public ByteString m() {
                return ((MIMCPacket) this.f11310a).m();
            }

            public Builder c(ByteString byteString) {
                S();
                ((MIMCPacket) this.f11310a).e(byteString);
                return this;
            }

            public Builder s() {
                S();
                ((MIMCPacket) this.f11310a).x();
                return this;
            }

            public boolean n() {
                return ((MIMCPacket) this.f11310a).n();
            }

            public long o() {
                return ((MIMCPacket) this.f11310a).o();
            }

            public Builder b(long j) {
                S();
                ((MIMCPacket) this.f11310a).b(j);
                return this;
            }

            public Builder t() {
                S();
                ((MIMCPacket) this.f11310a).y();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new MIMCPacket();
                case IS_INITIALIZED:
                    return q;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    MIMCPacket mIMCPacket = (MIMCPacket) obj2;
                    this.h = visitor.a(a(), this.h, mIMCPacket.a(), mIMCPacket.h);
                    this.i = visitor.a(d(), this.i, mIMCPacket.d(), mIMCPacket.i);
                    this.j = visitor.a(g(), this.j, mIMCPacket.g(), mIMCPacket.j);
                    this.k = visitor.a(i(), this.k, mIMCPacket.i(), mIMCPacket.k);
                    this.l = visitor.a(l(), this.l, mIMCPacket.l(), mIMCPacket.l);
                    this.p = visitor.a(n(), this.p, mIMCPacket.n(), mIMCPacket.p);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.g |= mIMCPacket.g;
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
                                } else if (a2 == 24) {
                                    this.g |= 4;
                                    this.j = codedInputStream.g();
                                } else if (a2 == 32) {
                                    int r2 = codedInputStream.r();
                                    if (MIMC_MSG_TYPE.forNumber(r2) == null) {
                                        super.a(4, r2);
                                    } else {
                                        this.g |= 8;
                                        this.k = r2;
                                    }
                                } else if (a2 == 42) {
                                    this.g |= 16;
                                    this.l = codedInputStream.n();
                                } else if (a2 == 48) {
                                    this.g |= 32;
                                    this.p = codedInputStream.g();
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
                        synchronized (MIMCPacket.class) {
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

        public static MIMCPacket q() {
            return q;
        }

        public static Parser<MIMCPacket> r() {
            return q.M();
        }
    }

    public static final class MIMCPacketList extends GeneratedMessageLite<MIMCPacketList, Builder> implements MIMCPacketListOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11232a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        /* access modifiers changed from: private */
        public static final MIMCPacketList j = new MIMCPacketList();
        private static volatile Parser<MIMCPacketList> k;
        private int e;
        private long f;
        private String g = "";
        private long h;
        private Internal.ProtobufList<MIMCPacket> i = X();

        private MIMCPacketList() {
        }

        public boolean a() {
            return (this.e & 1) == 1;
        }

        public long b() {
            return this.f;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.e |= 1;
            this.f = j2;
        }

        /* access modifiers changed from: private */
        public void p() {
            this.e &= -2;
            this.f = 0;
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
        public void q() {
            this.e &= -3;
            this.g = m().d();
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

        public long g() {
            return this.h;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.e |= 4;
            this.h = j2;
        }

        /* access modifiers changed from: private */
        public void r() {
            this.e &= -5;
            this.h = 0;
        }

        public List<MIMCPacket> h() {
            return this.i;
        }

        public List<? extends MIMCPacketOrBuilder> i() {
            return this.i;
        }

        public int j() {
            return this.i.size();
        }

        public MIMCPacket a(int i2) {
            return (MIMCPacket) this.i.get(i2);
        }

        public MIMCPacketOrBuilder b(int i2) {
            return (MIMCPacketOrBuilder) this.i.get(i2);
        }

        private void s() {
            if (!this.i.a()) {
                this.i = GeneratedMessageLite.a(this.i);
            }
        }

        /* access modifiers changed from: private */
        public void a(int i2, MIMCPacket mIMCPacket) {
            if (mIMCPacket != null) {
                s();
                this.i.set(i2, mIMCPacket);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(int i2, MIMCPacket.Builder builder) {
            s();
            this.i.set(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(MIMCPacket mIMCPacket) {
            if (mIMCPacket != null) {
                s();
                this.i.add(mIMCPacket);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void b(int i2, MIMCPacket mIMCPacket) {
            if (mIMCPacket != null) {
                s();
                this.i.add(i2, mIMCPacket);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(MIMCPacket.Builder builder) {
            s();
            this.i.add(builder.Z());
        }

        /* access modifiers changed from: private */
        public void b(int i2, MIMCPacket.Builder builder) {
            s();
            this.i.add(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(Iterable<? extends MIMCPacket> iterable) {
            s();
            AbstractMessageLite.a(iterable, this.i);
        }

        /* access modifiers changed from: private */
        public void t() {
            this.i = X();
        }

        /* access modifiers changed from: private */
        public void c(int i2) {
            s();
            this.i.remove(i2);
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.e & 1) == 1) {
                codedOutputStream.a(1, this.f);
            }
            if ((this.e & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.e & 4) == 4) {
                codedOutputStream.a(3, this.h);
            }
            for (int i2 = 0; i2 < this.i.size(); i2++) {
                codedOutputStream.a(4, (MessageLite) this.i.get(i2));
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int f2 = (this.e & 1) == 1 ? CodedOutputStream.f(1, this.f) + 0 : 0;
            if ((this.e & 2) == 2) {
                f2 += CodedOutputStream.b(2, d());
            }
            if ((this.e & 4) == 4) {
                f2 += CodedOutputStream.f(3, this.h);
            }
            for (int i3 = 0; i3 < this.i.size(); i3++) {
                f2 += CodedOutputStream.c(4, (MessageLite) this.i.get(i3));
            }
            int e2 = f2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static MIMCPacketList a(ByteString byteString) throws InvalidProtocolBufferException {
            return (MIMCPacketList) GeneratedMessageLite.a(j, byteString);
        }

        public static MIMCPacketList a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCPacketList) GeneratedMessageLite.a(j, byteString, extensionRegistryLite);
        }

        public static MIMCPacketList a(byte[] bArr) throws InvalidProtocolBufferException {
            return (MIMCPacketList) GeneratedMessageLite.a(j, bArr);
        }

        public static MIMCPacketList a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCPacketList) GeneratedMessageLite.a(j, bArr, extensionRegistryLite);
        }

        public static MIMCPacketList a(InputStream inputStream) throws IOException {
            return (MIMCPacketList) GeneratedMessageLite.a(j, inputStream);
        }

        public static MIMCPacketList a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCPacketList) GeneratedMessageLite.a(j, inputStream, extensionRegistryLite);
        }

        public static MIMCPacketList b(InputStream inputStream) throws IOException {
            return (MIMCPacketList) b(j, inputStream);
        }

        public static MIMCPacketList b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCPacketList) b(j, inputStream, extensionRegistryLite);
        }

        public static MIMCPacketList a(CodedInputStream codedInputStream) throws IOException {
            return (MIMCPacketList) GeneratedMessageLite.b(j, codedInputStream);
        }

        public static MIMCPacketList a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCPacketList) GeneratedMessageLite.b(j, codedInputStream, extensionRegistryLite);
        }

        public static Builder l() {
            return (Builder) j.Y();
        }

        public static Builder a(MIMCPacketList mIMCPacketList) {
            return (Builder) ((Builder) j.Y()).b(mIMCPacketList);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MIMCPacketList, Builder> implements MIMCPacketListOrBuilder {
            private Builder() {
                super(MIMCPacketList.j);
            }

            public boolean a() {
                return ((MIMCPacketList) this.f11310a).a();
            }

            public long b() {
                return ((MIMCPacketList) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((MIMCPacketList) this.f11310a).a(j);
                return this;
            }

            public Builder i() {
                S();
                ((MIMCPacketList) this.f11310a).p();
                return this;
            }

            public boolean c() {
                return ((MIMCPacketList) this.f11310a).c();
            }

            public String d() {
                return ((MIMCPacketList) this.f11310a).d();
            }

            public ByteString e() {
                return ((MIMCPacketList) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((MIMCPacketList) this.f11310a).a(str);
                return this;
            }

            public Builder k() {
                S();
                ((MIMCPacketList) this.f11310a).q();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((MIMCPacketList) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((MIMCPacketList) this.f11310a).f();
            }

            public long g() {
                return ((MIMCPacketList) this.f11310a).g();
            }

            public Builder b(long j) {
                S();
                ((MIMCPacketList) this.f11310a).b(j);
                return this;
            }

            public Builder l() {
                S();
                ((MIMCPacketList) this.f11310a).r();
                return this;
            }

            public List<MIMCPacket> h() {
                return Collections.unmodifiableList(((MIMCPacketList) this.f11310a).h());
            }

            public int j() {
                return ((MIMCPacketList) this.f11310a).j();
            }

            public MIMCPacket a(int i) {
                return ((MIMCPacketList) this.f11310a).a(i);
            }

            public Builder a(int i, MIMCPacket mIMCPacket) {
                S();
                ((MIMCPacketList) this.f11310a).a(i, mIMCPacket);
                return this;
            }

            public Builder a(int i, MIMCPacket.Builder builder) {
                S();
                ((MIMCPacketList) this.f11310a).a(i, builder);
                return this;
            }

            public Builder a(MIMCPacket mIMCPacket) {
                S();
                ((MIMCPacketList) this.f11310a).a(mIMCPacket);
                return this;
            }

            public Builder b(int i, MIMCPacket mIMCPacket) {
                S();
                ((MIMCPacketList) this.f11310a).b(i, mIMCPacket);
                return this;
            }

            public Builder a(MIMCPacket.Builder builder) {
                S();
                ((MIMCPacketList) this.f11310a).a(builder);
                return this;
            }

            public Builder b(int i, MIMCPacket.Builder builder) {
                S();
                ((MIMCPacketList) this.f11310a).b(i, builder);
                return this;
            }

            public Builder a(Iterable<? extends MIMCPacket> iterable) {
                S();
                ((MIMCPacketList) this.f11310a).a(iterable);
                return this;
            }

            public Builder m() {
                S();
                ((MIMCPacketList) this.f11310a).t();
                return this;
            }

            public Builder b(int i) {
                S();
                ((MIMCPacketList) this.f11310a).c(i);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new MIMCPacketList();
                case IS_INITIALIZED:
                    return j;
                case MAKE_IMMUTABLE:
                    this.i.b();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    MIMCPacketList mIMCPacketList = (MIMCPacketList) obj2;
                    this.f = visitor.a(a(), this.f, mIMCPacketList.a(), mIMCPacketList.f);
                    this.g = visitor.a(c(), this.g, mIMCPacketList.c(), mIMCPacketList.g);
                    this.h = visitor.a(f(), this.h, mIMCPacketList.f(), mIMCPacketList.h);
                    this.i = visitor.a(this.i, mIMCPacketList.i);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.e |= mIMCPacketList.e;
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
                                    this.f = codedInputStream.g();
                                } else if (a2 == 18) {
                                    String l = codedInputStream.l();
                                    this.e |= 2;
                                    this.g = l;
                                } else if (a2 == 24) {
                                    this.e |= 4;
                                    this.h = codedInputStream.g();
                                } else if (a2 == 34) {
                                    if (!this.i.a()) {
                                        this.i = GeneratedMessageLite.a(this.i);
                                    }
                                    this.i.add(codedInputStream.a(MIMCPacket.r(), extensionRegistryLite));
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
                        synchronized (MIMCPacketList.class) {
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

        public static MIMCPacketList m() {
            return j;
        }

        public static Parser<MIMCPacketList> n() {
            return j.M();
        }
    }

    public static final class MIMCPacketAck extends GeneratedMessageLite<MIMCPacketAck, Builder> implements MIMCPacketAckOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11231a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        /* access modifiers changed from: private */
        public static final MIMCPacketAck s = new MIMCPacketAck();
        private static volatile Parser<MIMCPacketAck> t;
        private int h;
        private String i = "";
        private long j;
        private String k = "";
        private long l;
        private long p;
        private String q = "";
        private String r = "";

        private MIMCPacketAck() {
        }

        public boolean a() {
            return (this.h & 1) == 1;
        }

        public String b() {
            return this.i;
        }

        public ByteString c() {
            return ByteString.copyFromUtf8(this.i);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.h |= 1;
                this.i = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void x() {
            this.h &= -2;
            this.i = u().b();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.h |= 1;
                this.i = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean d() {
            return (this.h & 2) == 2;
        }

        public long e() {
            return this.j;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.h |= 2;
            this.j = j2;
        }

        /* access modifiers changed from: private */
        public void y() {
            this.h &= -3;
            this.j = 0;
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
        public void z() {
            this.h &= -5;
            this.k = u().g();
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

        public long j() {
            return this.l;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.h |= 8;
            this.l = j2;
        }

        /* access modifiers changed from: private */
        public void A() {
            this.h &= -9;
            this.l = 0;
        }

        public boolean l() {
            return (this.h & 16) == 16;
        }

        public long m() {
            return this.p;
        }

        /* access modifiers changed from: private */
        public void c(long j2) {
            this.h |= 16;
            this.p = j2;
        }

        /* access modifiers changed from: private */
        public void B() {
            this.h &= -17;
            this.p = 0;
        }

        public boolean n() {
            return (this.h & 32) == 32;
        }

        public String o() {
            return this.q;
        }

        public ByteString p() {
            return ByteString.copyFromUtf8(this.q);
        }

        /* access modifiers changed from: private */
        public void c(String str) {
            if (str != null) {
                this.h |= 32;
                this.q = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void C() {
            this.h &= -33;
            this.q = u().o();
        }

        /* access modifiers changed from: private */
        public void e(ByteString byteString) {
            if (byteString != null) {
                this.h |= 32;
                this.q = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean q() {
            return (this.h & 64) == 64;
        }

        public String r() {
            return this.r;
        }

        public ByteString s() {
            return ByteString.copyFromUtf8(this.r);
        }

        /* access modifiers changed from: private */
        public void d(String str) {
            if (str != null) {
                this.h |= 64;
                this.r = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void D() {
            this.h &= -65;
            this.r = u().r();
        }

        /* access modifiers changed from: private */
        public void f(ByteString byteString) {
            if (byteString != null) {
                this.h |= 64;
                this.r = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.h & 1) == 1) {
                codedOutputStream.a(1, b());
            }
            if ((this.h & 2) == 2) {
                codedOutputStream.a(2, this.j);
            }
            if ((this.h & 4) == 4) {
                codedOutputStream.a(3, g());
            }
            if ((this.h & 8) == 8) {
                codedOutputStream.a(4, this.l);
            }
            if ((this.h & 16) == 16) {
                codedOutputStream.a(5, this.p);
            }
            if ((this.h & 32) == 32) {
                codedOutputStream.a(6, o());
            }
            if ((this.h & 64) == 64) {
                codedOutputStream.a(7, r());
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
                i3 = 0 + CodedOutputStream.b(1, b());
            }
            if ((this.h & 2) == 2) {
                i3 += CodedOutputStream.f(2, this.j);
            }
            if ((this.h & 4) == 4) {
                i3 += CodedOutputStream.b(3, g());
            }
            if ((this.h & 8) == 8) {
                i3 += CodedOutputStream.f(4, this.l);
            }
            if ((this.h & 16) == 16) {
                i3 += CodedOutputStream.f(5, this.p);
            }
            if ((this.h & 32) == 32) {
                i3 += CodedOutputStream.b(6, o());
            }
            if ((this.h & 64) == 64) {
                i3 += CodedOutputStream.b(7, r());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static MIMCPacketAck a(ByteString byteString) throws InvalidProtocolBufferException {
            return (MIMCPacketAck) GeneratedMessageLite.a(s, byteString);
        }

        public static MIMCPacketAck a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCPacketAck) GeneratedMessageLite.a(s, byteString, extensionRegistryLite);
        }

        public static MIMCPacketAck a(byte[] bArr) throws InvalidProtocolBufferException {
            return (MIMCPacketAck) GeneratedMessageLite.a(s, bArr);
        }

        public static MIMCPacketAck a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCPacketAck) GeneratedMessageLite.a(s, bArr, extensionRegistryLite);
        }

        public static MIMCPacketAck a(InputStream inputStream) throws IOException {
            return (MIMCPacketAck) GeneratedMessageLite.a(s, inputStream);
        }

        public static MIMCPacketAck a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCPacketAck) GeneratedMessageLite.a(s, inputStream, extensionRegistryLite);
        }

        public static MIMCPacketAck b(InputStream inputStream) throws IOException {
            return (MIMCPacketAck) b(s, inputStream);
        }

        public static MIMCPacketAck b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCPacketAck) b(s, inputStream, extensionRegistryLite);
        }

        public static MIMCPacketAck a(CodedInputStream codedInputStream) throws IOException {
            return (MIMCPacketAck) GeneratedMessageLite.b(s, codedInputStream);
        }

        public static MIMCPacketAck a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCPacketAck) GeneratedMessageLite.b(s, codedInputStream, extensionRegistryLite);
        }

        public static Builder t() {
            return (Builder) s.Y();
        }

        public static Builder a(MIMCPacketAck mIMCPacketAck) {
            return (Builder) ((Builder) s.Y()).b(mIMCPacketAck);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MIMCPacketAck, Builder> implements MIMCPacketAckOrBuilder {
            private Builder() {
                super(MIMCPacketAck.s);
            }

            public boolean a() {
                return ((MIMCPacketAck) this.f11310a).a();
            }

            public String b() {
                return ((MIMCPacketAck) this.f11310a).b();
            }

            public ByteString c() {
                return ((MIMCPacketAck) this.f11310a).c();
            }

            public Builder a(String str) {
                S();
                ((MIMCPacketAck) this.f11310a).a(str);
                return this;
            }

            public Builder k() {
                S();
                ((MIMCPacketAck) this.f11310a).x();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((MIMCPacketAck) this.f11310a).c(byteString);
                return this;
            }

            public boolean d() {
                return ((MIMCPacketAck) this.f11310a).d();
            }

            public long e() {
                return ((MIMCPacketAck) this.f11310a).e();
            }

            public Builder a(long j) {
                S();
                ((MIMCPacketAck) this.f11310a).a(j);
                return this;
            }

            public Builder t() {
                S();
                ((MIMCPacketAck) this.f11310a).y();
                return this;
            }

            public boolean f() {
                return ((MIMCPacketAck) this.f11310a).f();
            }

            public String g() {
                return ((MIMCPacketAck) this.f11310a).g();
            }

            public ByteString h() {
                return ((MIMCPacketAck) this.f11310a).h();
            }

            public Builder b(String str) {
                S();
                ((MIMCPacketAck) this.f11310a).b(str);
                return this;
            }

            public Builder u() {
                S();
                ((MIMCPacketAck) this.f11310a).z();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((MIMCPacketAck) this.f11310a).d(byteString);
                return this;
            }

            public boolean i() {
                return ((MIMCPacketAck) this.f11310a).i();
            }

            public long j() {
                return ((MIMCPacketAck) this.f11310a).j();
            }

            public Builder b(long j) {
                S();
                ((MIMCPacketAck) this.f11310a).b(j);
                return this;
            }

            public Builder v() {
                S();
                ((MIMCPacketAck) this.f11310a).A();
                return this;
            }

            public boolean l() {
                return ((MIMCPacketAck) this.f11310a).l();
            }

            public long m() {
                return ((MIMCPacketAck) this.f11310a).m();
            }

            public Builder c(long j) {
                S();
                ((MIMCPacketAck) this.f11310a).c(j);
                return this;
            }

            public Builder w() {
                S();
                ((MIMCPacketAck) this.f11310a).B();
                return this;
            }

            public boolean n() {
                return ((MIMCPacketAck) this.f11310a).n();
            }

            public String o() {
                return ((MIMCPacketAck) this.f11310a).o();
            }

            public ByteString p() {
                return ((MIMCPacketAck) this.f11310a).p();
            }

            public Builder c(String str) {
                S();
                ((MIMCPacketAck) this.f11310a).c(str);
                return this;
            }

            public Builder x() {
                S();
                ((MIMCPacketAck) this.f11310a).C();
                return this;
            }

            public Builder c(ByteString byteString) {
                S();
                ((MIMCPacketAck) this.f11310a).e(byteString);
                return this;
            }

            public boolean q() {
                return ((MIMCPacketAck) this.f11310a).q();
            }

            public String r() {
                return ((MIMCPacketAck) this.f11310a).r();
            }

            public ByteString s() {
                return ((MIMCPacketAck) this.f11310a).s();
            }

            public Builder d(String str) {
                S();
                ((MIMCPacketAck) this.f11310a).d(str);
                return this;
            }

            public Builder y() {
                S();
                ((MIMCPacketAck) this.f11310a).D();
                return this;
            }

            public Builder d(ByteString byteString) {
                S();
                ((MIMCPacketAck) this.f11310a).f(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new MIMCPacketAck();
                case IS_INITIALIZED:
                    return s;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    MIMCPacketAck mIMCPacketAck = (MIMCPacketAck) obj2;
                    this.i = visitor.a(a(), this.i, mIMCPacketAck.a(), mIMCPacketAck.i);
                    this.j = visitor.a(d(), this.j, mIMCPacketAck.d(), mIMCPacketAck.j);
                    this.k = visitor.a(f(), this.k, mIMCPacketAck.f(), mIMCPacketAck.k);
                    this.l = visitor.a(i(), this.l, mIMCPacketAck.i(), mIMCPacketAck.l);
                    this.p = visitor.a(l(), this.p, mIMCPacketAck.l(), mIMCPacketAck.p);
                    this.q = visitor.a(n(), this.q, mIMCPacketAck.n(), mIMCPacketAck.q);
                    this.r = visitor.a(q(), this.r, mIMCPacketAck.q(), mIMCPacketAck.r);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.h |= mIMCPacketAck.h;
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
                                    this.h = 1 | this.h;
                                    this.i = l2;
                                } else if (a2 == 16) {
                                    this.h |= 2;
                                    this.j = codedInputStream.g();
                                } else if (a2 == 26) {
                                    String l3 = codedInputStream.l();
                                    this.h |= 4;
                                    this.k = l3;
                                } else if (a2 == 32) {
                                    this.h |= 8;
                                    this.l = codedInputStream.g();
                                } else if (a2 == 40) {
                                    this.h |= 16;
                                    this.p = codedInputStream.g();
                                } else if (a2 == 50) {
                                    String l4 = codedInputStream.l();
                                    this.h |= 32;
                                    this.q = l4;
                                } else if (a2 == 58) {
                                    String l5 = codedInputStream.l();
                                    this.h |= 64;
                                    this.r = l5;
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
                    if (t == null) {
                        synchronized (MIMCPacketAck.class) {
                            if (t == null) {
                                t = new GeneratedMessageLite.DefaultInstanceBasedParser(s);
                            }
                        }
                    }
                    return t;
                default:
                    throw new UnsupportedOperationException();
            }
            return s;
        }

        static {
            s.P();
        }

        public static MIMCPacketAck u() {
            return s;
        }

        public static Parser<MIMCPacketAck> v() {
            return s.M();
        }
    }

    public static final class MIMCP2PMessage extends GeneratedMessageLite<MIMCP2PMessage, Builder> implements MIMCP2PMessageOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11228a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        /* access modifiers changed from: private */
        public static final MIMCP2PMessage l = new MIMCP2PMessage();
        private static volatile Parser<MIMCP2PMessage> p;
        private int f;
        private MIMCUser g;
        private MIMCUser h;
        private ByteString i = ByteString.EMPTY;
        private boolean j;
        private String k = "";

        private MIMCP2PMessage() {
        }

        public boolean a() {
            return (this.f & 1) == 1;
        }

        public MIMCUser b() {
            return this.g == null ? MIMCUser.m() : this.g;
        }

        /* access modifiers changed from: private */
        public void a(MIMCUser mIMCUser) {
            if (mIMCUser != null) {
                this.g = mIMCUser;
                this.f |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(MIMCUser.Builder builder) {
            this.g = (MIMCUser) builder.Z();
            this.f |= 1;
        }

        /* access modifiers changed from: private */
        public void b(MIMCUser mIMCUser) {
            if (this.g == null || this.g == MIMCUser.m()) {
                this.g = mIMCUser;
            } else {
                this.g = (MIMCUser) ((MIMCUser.Builder) MIMCUser.a(this.g).b(mIMCUser)).Y();
            }
            this.f |= 1;
        }

        /* access modifiers changed from: private */
        public void q() {
            this.g = null;
            this.f &= -2;
        }

        public boolean c() {
            return (this.f & 2) == 2;
        }

        public MIMCUser d() {
            return this.h == null ? MIMCUser.m() : this.h;
        }

        /* access modifiers changed from: private */
        public void c(MIMCUser mIMCUser) {
            if (mIMCUser != null) {
                this.h = mIMCUser;
                this.f |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void b(MIMCUser.Builder builder) {
            this.h = (MIMCUser) builder.Z();
            this.f |= 2;
        }

        /* access modifiers changed from: private */
        public void d(MIMCUser mIMCUser) {
            if (this.h == null || this.h == MIMCUser.m()) {
                this.h = mIMCUser;
            } else {
                this.h = (MIMCUser) ((MIMCUser.Builder) MIMCUser.a(this.h).b(mIMCUser)).Y();
            }
            this.f |= 2;
        }

        /* access modifiers changed from: private */
        public void r() {
            this.h = null;
            this.f &= -3;
        }

        public boolean e() {
            return (this.f & 4) == 4;
        }

        public ByteString f() {
            return this.i;
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.f |= 4;
                this.i = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void s() {
            this.f &= -5;
            this.i = n().f();
        }

        public boolean g() {
            return (this.f & 8) == 8;
        }

        public boolean h() {
            return this.j;
        }

        /* access modifiers changed from: private */
        public void a(boolean z) {
            this.f |= 8;
            this.j = z;
        }

        /* access modifiers changed from: private */
        public void t() {
            this.f &= -9;
            this.j = false;
        }

        public boolean i() {
            return (this.f & 16) == 16;
        }

        public String j() {
            return this.k;
        }

        public ByteString l() {
            return ByteString.copyFromUtf8(this.k);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.f |= 16;
                this.k = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void u() {
            this.f &= -17;
            this.k = n().j();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.f |= 16;
                this.k = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.f & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
            }
            if ((this.f & 2) == 2) {
                codedOutputStream.a(2, (MessageLite) d());
            }
            if ((this.f & 4) == 4) {
                codedOutputStream.a(3, this.i);
            }
            if ((this.f & 8) == 8) {
                codedOutputStream.a(4, this.j);
            }
            if ((this.f & 16) == 16) {
                codedOutputStream.a(5, j());
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
                i3 = 0 + CodedOutputStream.c(1, (MessageLite) b());
            }
            if ((this.f & 2) == 2) {
                i3 += CodedOutputStream.c(2, (MessageLite) d());
            }
            if ((this.f & 4) == 4) {
                i3 += CodedOutputStream.c(3, this.i);
            }
            if ((this.f & 8) == 8) {
                i3 += CodedOutputStream.b(4, this.j);
            }
            if ((this.f & 16) == 16) {
                i3 += CodedOutputStream.b(5, j());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static MIMCP2PMessage a(ByteString byteString) throws InvalidProtocolBufferException {
            return (MIMCP2PMessage) GeneratedMessageLite.a(l, byteString);
        }

        public static MIMCP2PMessage a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCP2PMessage) GeneratedMessageLite.a(l, byteString, extensionRegistryLite);
        }

        public static MIMCP2PMessage a(byte[] bArr) throws InvalidProtocolBufferException {
            return (MIMCP2PMessage) GeneratedMessageLite.a(l, bArr);
        }

        public static MIMCP2PMessage a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCP2PMessage) GeneratedMessageLite.a(l, bArr, extensionRegistryLite);
        }

        public static MIMCP2PMessage a(InputStream inputStream) throws IOException {
            return (MIMCP2PMessage) GeneratedMessageLite.a(l, inputStream);
        }

        public static MIMCP2PMessage a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCP2PMessage) GeneratedMessageLite.a(l, inputStream, extensionRegistryLite);
        }

        public static MIMCP2PMessage b(InputStream inputStream) throws IOException {
            return (MIMCP2PMessage) b(l, inputStream);
        }

        public static MIMCP2PMessage b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCP2PMessage) b(l, inputStream, extensionRegistryLite);
        }

        public static MIMCP2PMessage a(CodedInputStream codedInputStream) throws IOException {
            return (MIMCP2PMessage) GeneratedMessageLite.b(l, codedInputStream);
        }

        public static MIMCP2PMessage a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCP2PMessage) GeneratedMessageLite.b(l, codedInputStream, extensionRegistryLite);
        }

        public static Builder m() {
            return (Builder) l.Y();
        }

        public static Builder a(MIMCP2PMessage mIMCP2PMessage) {
            return (Builder) ((Builder) l.Y()).b(mIMCP2PMessage);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MIMCP2PMessage, Builder> implements MIMCP2PMessageOrBuilder {
            private Builder() {
                super(MIMCP2PMessage.l);
            }

            public boolean a() {
                return ((MIMCP2PMessage) this.f11310a).a();
            }

            public MIMCUser b() {
                return ((MIMCP2PMessage) this.f11310a).b();
            }

            public Builder a(MIMCUser mIMCUser) {
                S();
                ((MIMCP2PMessage) this.f11310a).a(mIMCUser);
                return this;
            }

            public Builder a(MIMCUser.Builder builder) {
                S();
                ((MIMCP2PMessage) this.f11310a).a(builder);
                return this;
            }

            public Builder b(MIMCUser mIMCUser) {
                S();
                ((MIMCP2PMessage) this.f11310a).b(mIMCUser);
                return this;
            }

            public Builder k() {
                S();
                ((MIMCP2PMessage) this.f11310a).q();
                return this;
            }

            public boolean c() {
                return ((MIMCP2PMessage) this.f11310a).c();
            }

            public MIMCUser d() {
                return ((MIMCP2PMessage) this.f11310a).d();
            }

            public Builder c(MIMCUser mIMCUser) {
                S();
                ((MIMCP2PMessage) this.f11310a).c(mIMCUser);
                return this;
            }

            public Builder b(MIMCUser.Builder builder) {
                S();
                ((MIMCP2PMessage) this.f11310a).b(builder);
                return this;
            }

            public Builder d(MIMCUser mIMCUser) {
                S();
                ((MIMCP2PMessage) this.f11310a).d(mIMCUser);
                return this;
            }

            public Builder m() {
                S();
                ((MIMCP2PMessage) this.f11310a).r();
                return this;
            }

            public boolean e() {
                return ((MIMCP2PMessage) this.f11310a).e();
            }

            public ByteString f() {
                return ((MIMCP2PMessage) this.f11310a).f();
            }

            public Builder a(ByteString byteString) {
                S();
                ((MIMCP2PMessage) this.f11310a).c(byteString);
                return this;
            }

            public Builder n() {
                S();
                ((MIMCP2PMessage) this.f11310a).s();
                return this;
            }

            public boolean g() {
                return ((MIMCP2PMessage) this.f11310a).g();
            }

            public boolean h() {
                return ((MIMCP2PMessage) this.f11310a).h();
            }

            public Builder a(boolean z) {
                S();
                ((MIMCP2PMessage) this.f11310a).a(z);
                return this;
            }

            public Builder o() {
                S();
                ((MIMCP2PMessage) this.f11310a).t();
                return this;
            }

            public boolean i() {
                return ((MIMCP2PMessage) this.f11310a).i();
            }

            public String j() {
                return ((MIMCP2PMessage) this.f11310a).j();
            }

            public ByteString l() {
                return ((MIMCP2PMessage) this.f11310a).l();
            }

            public Builder a(String str) {
                S();
                ((MIMCP2PMessage) this.f11310a).a(str);
                return this;
            }

            public Builder p() {
                S();
                ((MIMCP2PMessage) this.f11310a).u();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((MIMCP2PMessage) this.f11310a).d(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new MIMCP2PMessage();
                case IS_INITIALIZED:
                    return l;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    MIMCP2PMessage mIMCP2PMessage = (MIMCP2PMessage) obj2;
                    this.g = (MIMCUser) visitor.a(this.g, mIMCP2PMessage.g);
                    this.h = (MIMCUser) visitor.a(this.h, mIMCP2PMessage.h);
                    this.i = visitor.a(e(), this.i, mIMCP2PMessage.e(), mIMCP2PMessage.i);
                    this.j = visitor.a(g(), this.j, mIMCP2PMessage.g(), mIMCP2PMessage.j);
                    this.k = visitor.a(i(), this.k, mIMCP2PMessage.i(), mIMCP2PMessage.k);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.f |= mIMCP2PMessage.f;
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
                                    MIMCUser.Builder builder = (this.f & 1) == 1 ? (MIMCUser.Builder) this.g.Y() : null;
                                    this.g = (MIMCUser) codedInputStream.a(MIMCUser.n(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.g);
                                        this.g = (MIMCUser) builder.Y();
                                    }
                                    this.f |= 1;
                                } else if (a2 == 18) {
                                    MIMCUser.Builder builder2 = (this.f & 2) == 2 ? (MIMCUser.Builder) this.h.Y() : null;
                                    this.h = (MIMCUser) codedInputStream.a(MIMCUser.n(), extensionRegistryLite);
                                    if (builder2 != null) {
                                        builder2.b(this.h);
                                        this.h = (MIMCUser) builder2.Y();
                                    }
                                    this.f |= 2;
                                } else if (a2 == 26) {
                                    this.f |= 4;
                                    this.i = codedInputStream.n();
                                } else if (a2 == 32) {
                                    this.f |= 8;
                                    this.j = codedInputStream.k();
                                } else if (a2 == 42) {
                                    String l2 = codedInputStream.l();
                                    this.f |= 16;
                                    this.k = l2;
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
                    if (p == null) {
                        synchronized (MIMCP2PMessage.class) {
                            if (p == null) {
                                p = new GeneratedMessageLite.DefaultInstanceBasedParser(l);
                            }
                        }
                    }
                    return p;
                default:
                    throw new UnsupportedOperationException();
            }
            return l;
        }

        static {
            l.P();
        }

        public static MIMCP2PMessage n() {
            return l;
        }

        public static Parser<MIMCP2PMessage> o() {
            return l.M();
        }
    }

    public static final class MIMCP2TMessage extends GeneratedMessageLite<MIMCP2TMessage, Builder> implements MIMCP2TMessageOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11229a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        /* access modifiers changed from: private */
        public static final MIMCP2TMessage l = new MIMCP2TMessage();
        private static volatile Parser<MIMCP2TMessage> p;
        private int f;
        private MIMCUser g;
        private MIMCGroup h;
        private ByteString i = ByteString.EMPTY;
        private boolean j;
        private String k = "";

        private MIMCP2TMessage() {
        }

        public boolean a() {
            return (this.f & 1) == 1;
        }

        public MIMCUser b() {
            return this.g == null ? MIMCUser.m() : this.g;
        }

        /* access modifiers changed from: private */
        public void a(MIMCUser mIMCUser) {
            if (mIMCUser != null) {
                this.g = mIMCUser;
                this.f |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(MIMCUser.Builder builder) {
            this.g = (MIMCUser) builder.Z();
            this.f |= 1;
        }

        /* access modifiers changed from: private */
        public void b(MIMCUser mIMCUser) {
            if (this.g == null || this.g == MIMCUser.m()) {
                this.g = mIMCUser;
            } else {
                this.g = (MIMCUser) ((MIMCUser.Builder) MIMCUser.a(this.g).b(mIMCUser)).Y();
            }
            this.f |= 1;
        }

        /* access modifiers changed from: private */
        public void q() {
            this.g = null;
            this.f &= -2;
        }

        public boolean c() {
            return (this.f & 2) == 2;
        }

        public MIMCGroup d() {
            return this.h == null ? MIMCGroup.f() : this.h;
        }

        /* access modifiers changed from: private */
        public void a(MIMCGroup mIMCGroup) {
            if (mIMCGroup != null) {
                this.h = mIMCGroup;
                this.f |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(MIMCGroup.Builder builder) {
            this.h = (MIMCGroup) builder.Z();
            this.f |= 2;
        }

        /* access modifiers changed from: private */
        public void b(MIMCGroup mIMCGroup) {
            if (this.h == null || this.h == MIMCGroup.f()) {
                this.h = mIMCGroup;
            } else {
                this.h = (MIMCGroup) ((MIMCGroup.Builder) MIMCGroup.a(this.h).b(mIMCGroup)).Y();
            }
            this.f |= 2;
        }

        /* access modifiers changed from: private */
        public void r() {
            this.h = null;
            this.f &= -3;
        }

        public boolean e() {
            return (this.f & 4) == 4;
        }

        public ByteString f() {
            return this.i;
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.f |= 4;
                this.i = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void s() {
            this.f &= -5;
            this.i = n().f();
        }

        public boolean g() {
            return (this.f & 8) == 8;
        }

        public boolean h() {
            return this.j;
        }

        /* access modifiers changed from: private */
        public void a(boolean z) {
            this.f |= 8;
            this.j = z;
        }

        /* access modifiers changed from: private */
        public void t() {
            this.f &= -9;
            this.j = false;
        }

        public boolean i() {
            return (this.f & 16) == 16;
        }

        public String j() {
            return this.k;
        }

        public ByteString l() {
            return ByteString.copyFromUtf8(this.k);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.f |= 16;
                this.k = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void u() {
            this.f &= -17;
            this.k = n().j();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.f |= 16;
                this.k = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.f & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
            }
            if ((this.f & 2) == 2) {
                codedOutputStream.a(2, (MessageLite) d());
            }
            if ((this.f & 4) == 4) {
                codedOutputStream.a(3, this.i);
            }
            if ((this.f & 8) == 8) {
                codedOutputStream.a(4, this.j);
            }
            if ((this.f & 16) == 16) {
                codedOutputStream.a(5, j());
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
                i3 = 0 + CodedOutputStream.c(1, (MessageLite) b());
            }
            if ((this.f & 2) == 2) {
                i3 += CodedOutputStream.c(2, (MessageLite) d());
            }
            if ((this.f & 4) == 4) {
                i3 += CodedOutputStream.c(3, this.i);
            }
            if ((this.f & 8) == 8) {
                i3 += CodedOutputStream.b(4, this.j);
            }
            if ((this.f & 16) == 16) {
                i3 += CodedOutputStream.b(5, j());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static MIMCP2TMessage a(ByteString byteString) throws InvalidProtocolBufferException {
            return (MIMCP2TMessage) GeneratedMessageLite.a(l, byteString);
        }

        public static MIMCP2TMessage a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCP2TMessage) GeneratedMessageLite.a(l, byteString, extensionRegistryLite);
        }

        public static MIMCP2TMessage a(byte[] bArr) throws InvalidProtocolBufferException {
            return (MIMCP2TMessage) GeneratedMessageLite.a(l, bArr);
        }

        public static MIMCP2TMessage a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCP2TMessage) GeneratedMessageLite.a(l, bArr, extensionRegistryLite);
        }

        public static MIMCP2TMessage a(InputStream inputStream) throws IOException {
            return (MIMCP2TMessage) GeneratedMessageLite.a(l, inputStream);
        }

        public static MIMCP2TMessage a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCP2TMessage) GeneratedMessageLite.a(l, inputStream, extensionRegistryLite);
        }

        public static MIMCP2TMessage b(InputStream inputStream) throws IOException {
            return (MIMCP2TMessage) b(l, inputStream);
        }

        public static MIMCP2TMessage b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCP2TMessage) b(l, inputStream, extensionRegistryLite);
        }

        public static MIMCP2TMessage a(CodedInputStream codedInputStream) throws IOException {
            return (MIMCP2TMessage) GeneratedMessageLite.b(l, codedInputStream);
        }

        public static MIMCP2TMessage a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCP2TMessage) GeneratedMessageLite.b(l, codedInputStream, extensionRegistryLite);
        }

        public static Builder m() {
            return (Builder) l.Y();
        }

        public static Builder a(MIMCP2TMessage mIMCP2TMessage) {
            return (Builder) ((Builder) l.Y()).b(mIMCP2TMessage);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MIMCP2TMessage, Builder> implements MIMCP2TMessageOrBuilder {
            private Builder() {
                super(MIMCP2TMessage.l);
            }

            public boolean a() {
                return ((MIMCP2TMessage) this.f11310a).a();
            }

            public MIMCUser b() {
                return ((MIMCP2TMessage) this.f11310a).b();
            }

            public Builder a(MIMCUser mIMCUser) {
                S();
                ((MIMCP2TMessage) this.f11310a).a(mIMCUser);
                return this;
            }

            public Builder a(MIMCUser.Builder builder) {
                S();
                ((MIMCP2TMessage) this.f11310a).a(builder);
                return this;
            }

            public Builder b(MIMCUser mIMCUser) {
                S();
                ((MIMCP2TMessage) this.f11310a).b(mIMCUser);
                return this;
            }

            public Builder k() {
                S();
                ((MIMCP2TMessage) this.f11310a).q();
                return this;
            }

            public boolean c() {
                return ((MIMCP2TMessage) this.f11310a).c();
            }

            public MIMCGroup d() {
                return ((MIMCP2TMessage) this.f11310a).d();
            }

            public Builder a(MIMCGroup mIMCGroup) {
                S();
                ((MIMCP2TMessage) this.f11310a).a(mIMCGroup);
                return this;
            }

            public Builder a(MIMCGroup.Builder builder) {
                S();
                ((MIMCP2TMessage) this.f11310a).a(builder);
                return this;
            }

            public Builder b(MIMCGroup mIMCGroup) {
                S();
                ((MIMCP2TMessage) this.f11310a).b(mIMCGroup);
                return this;
            }

            public Builder m() {
                S();
                ((MIMCP2TMessage) this.f11310a).r();
                return this;
            }

            public boolean e() {
                return ((MIMCP2TMessage) this.f11310a).e();
            }

            public ByteString f() {
                return ((MIMCP2TMessage) this.f11310a).f();
            }

            public Builder a(ByteString byteString) {
                S();
                ((MIMCP2TMessage) this.f11310a).c(byteString);
                return this;
            }

            public Builder n() {
                S();
                ((MIMCP2TMessage) this.f11310a).s();
                return this;
            }

            public boolean g() {
                return ((MIMCP2TMessage) this.f11310a).g();
            }

            public boolean h() {
                return ((MIMCP2TMessage) this.f11310a).h();
            }

            public Builder a(boolean z) {
                S();
                ((MIMCP2TMessage) this.f11310a).a(z);
                return this;
            }

            public Builder o() {
                S();
                ((MIMCP2TMessage) this.f11310a).t();
                return this;
            }

            public boolean i() {
                return ((MIMCP2TMessage) this.f11310a).i();
            }

            public String j() {
                return ((MIMCP2TMessage) this.f11310a).j();
            }

            public ByteString l() {
                return ((MIMCP2TMessage) this.f11310a).l();
            }

            public Builder a(String str) {
                S();
                ((MIMCP2TMessage) this.f11310a).a(str);
                return this;
            }

            public Builder p() {
                S();
                ((MIMCP2TMessage) this.f11310a).u();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((MIMCP2TMessage) this.f11310a).d(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new MIMCP2TMessage();
                case IS_INITIALIZED:
                    return l;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    MIMCP2TMessage mIMCP2TMessage = (MIMCP2TMessage) obj2;
                    this.g = (MIMCUser) visitor.a(this.g, mIMCP2TMessage.g);
                    this.h = (MIMCGroup) visitor.a(this.h, mIMCP2TMessage.h);
                    this.i = visitor.a(e(), this.i, mIMCP2TMessage.e(), mIMCP2TMessage.i);
                    this.j = visitor.a(g(), this.j, mIMCP2TMessage.g(), mIMCP2TMessage.j);
                    this.k = visitor.a(i(), this.k, mIMCP2TMessage.i(), mIMCP2TMessage.k);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.f |= mIMCP2TMessage.f;
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
                                    MIMCUser.Builder builder = (this.f & 1) == 1 ? (MIMCUser.Builder) this.g.Y() : null;
                                    this.g = (MIMCUser) codedInputStream.a(MIMCUser.n(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.g);
                                        this.g = (MIMCUser) builder.Y();
                                    }
                                    this.f |= 1;
                                } else if (a2 == 18) {
                                    MIMCGroup.Builder builder2 = (this.f & 2) == 2 ? (MIMCGroup.Builder) this.h.Y() : null;
                                    this.h = (MIMCGroup) codedInputStream.a(MIMCGroup.g(), extensionRegistryLite);
                                    if (builder2 != null) {
                                        builder2.b(this.h);
                                        this.h = (MIMCGroup) builder2.Y();
                                    }
                                    this.f |= 2;
                                } else if (a2 == 26) {
                                    this.f |= 4;
                                    this.i = codedInputStream.n();
                                } else if (a2 == 32) {
                                    this.f |= 8;
                                    this.j = codedInputStream.k();
                                } else if (a2 == 42) {
                                    String l2 = codedInputStream.l();
                                    this.f |= 16;
                                    this.k = l2;
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
                    if (p == null) {
                        synchronized (MIMCP2TMessage.class) {
                            if (p == null) {
                                p = new GeneratedMessageLite.DefaultInstanceBasedParser(l);
                            }
                        }
                    }
                    return p;
                default:
                    throw new UnsupportedOperationException();
            }
            return l;
        }

        static {
            l.P();
        }

        public static MIMCP2TMessage n() {
            return l;
        }

        public static Parser<MIMCP2TMessage> o() {
            return l.M();
        }
    }

    public static final class MIMCSequenceAck extends GeneratedMessageLite<MIMCSequenceAck, Builder> implements MIMCSequenceAckOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11234a = 1;
        public static final int b = 2;
        public static final int c = 3;
        /* access modifiers changed from: private */
        public static final MIMCSequenceAck h = new MIMCSequenceAck();
        private static volatile Parser<MIMCSequenceAck> i;
        private int d;
        private long e;
        private String f = "";
        private long g;

        private MIMCSequenceAck() {
        }

        public boolean a() {
            return (this.d & 1) == 1;
        }

        public long b() {
            return this.e;
        }

        /* access modifiers changed from: private */
        public void a(long j) {
            this.d |= 1;
            this.e = j;
        }

        /* access modifiers changed from: private */
        public void m() {
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
        public void n() {
            this.d &= -3;
            this.f = i().d();
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

        public long g() {
            return this.g;
        }

        /* access modifiers changed from: private */
        public void b(long j) {
            this.d |= 4;
            this.g = j;
        }

        /* access modifiers changed from: private */
        public void o() {
            this.d &= -5;
            this.g = 0;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.d & 1) == 1) {
                codedOutputStream.a(1, this.e);
            }
            if ((this.d & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.d & 4) == 4) {
                codedOutputStream.a(3, this.g);
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
                i3 = 0 + CodedOutputStream.f(1, this.e);
            }
            if ((this.d & 2) == 2) {
                i3 += CodedOutputStream.b(2, d());
            }
            if ((this.d & 4) == 4) {
                i3 += CodedOutputStream.f(3, this.g);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static MIMCSequenceAck a(ByteString byteString) throws InvalidProtocolBufferException {
            return (MIMCSequenceAck) GeneratedMessageLite.a(h, byteString);
        }

        public static MIMCSequenceAck a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCSequenceAck) GeneratedMessageLite.a(h, byteString, extensionRegistryLite);
        }

        public static MIMCSequenceAck a(byte[] bArr) throws InvalidProtocolBufferException {
            return (MIMCSequenceAck) GeneratedMessageLite.a(h, bArr);
        }

        public static MIMCSequenceAck a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCSequenceAck) GeneratedMessageLite.a(h, bArr, extensionRegistryLite);
        }

        public static MIMCSequenceAck a(InputStream inputStream) throws IOException {
            return (MIMCSequenceAck) GeneratedMessageLite.a(h, inputStream);
        }

        public static MIMCSequenceAck a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCSequenceAck) GeneratedMessageLite.a(h, inputStream, extensionRegistryLite);
        }

        public static MIMCSequenceAck b(InputStream inputStream) throws IOException {
            return (MIMCSequenceAck) b(h, inputStream);
        }

        public static MIMCSequenceAck b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCSequenceAck) b(h, inputStream, extensionRegistryLite);
        }

        public static MIMCSequenceAck a(CodedInputStream codedInputStream) throws IOException {
            return (MIMCSequenceAck) GeneratedMessageLite.b(h, codedInputStream);
        }

        public static MIMCSequenceAck a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCSequenceAck) GeneratedMessageLite.b(h, codedInputStream, extensionRegistryLite);
        }

        public static Builder h() {
            return (Builder) h.Y();
        }

        public static Builder a(MIMCSequenceAck mIMCSequenceAck) {
            return (Builder) ((Builder) h.Y()).b(mIMCSequenceAck);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MIMCSequenceAck, Builder> implements MIMCSequenceAckOrBuilder {
            private Builder() {
                super(MIMCSequenceAck.h);
            }

            public boolean a() {
                return ((MIMCSequenceAck) this.f11310a).a();
            }

            public long b() {
                return ((MIMCSequenceAck) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((MIMCSequenceAck) this.f11310a).a(j);
                return this;
            }

            public Builder h() {
                S();
                ((MIMCSequenceAck) this.f11310a).m();
                return this;
            }

            public boolean c() {
                return ((MIMCSequenceAck) this.f11310a).c();
            }

            public String d() {
                return ((MIMCSequenceAck) this.f11310a).d();
            }

            public ByteString e() {
                return ((MIMCSequenceAck) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((MIMCSequenceAck) this.f11310a).a(str);
                return this;
            }

            public Builder i() {
                S();
                ((MIMCSequenceAck) this.f11310a).n();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((MIMCSequenceAck) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((MIMCSequenceAck) this.f11310a).f();
            }

            public long g() {
                return ((MIMCSequenceAck) this.f11310a).g();
            }

            public Builder b(long j) {
                S();
                ((MIMCSequenceAck) this.f11310a).b(j);
                return this;
            }

            public Builder j() {
                S();
                ((MIMCSequenceAck) this.f11310a).o();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new MIMCSequenceAck();
                case IS_INITIALIZED:
                    return h;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    MIMCSequenceAck mIMCSequenceAck = (MIMCSequenceAck) obj2;
                    this.e = visitor.a(a(), this.e, mIMCSequenceAck.a(), mIMCSequenceAck.e);
                    this.f = visitor.a(c(), this.f, mIMCSequenceAck.c(), mIMCSequenceAck.f);
                    this.g = visitor.a(f(), this.g, mIMCSequenceAck.f(), mIMCSequenceAck.g);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.d |= mIMCSequenceAck.d;
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
                                    this.d |= 1;
                                    this.e = codedInputStream.g();
                                } else if (a2 == 18) {
                                    String l = codedInputStream.l();
                                    this.d |= 2;
                                    this.f = l;
                                } else if (a2 == 24) {
                                    this.d |= 4;
                                    this.g = codedInputStream.g();
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
                    if (i == null) {
                        synchronized (MIMCSequenceAck.class) {
                            if (i == null) {
                                i = new GeneratedMessageLite.DefaultInstanceBasedParser(h);
                            }
                        }
                    }
                    return i;
                default:
                    throw new UnsupportedOperationException();
            }
            return h;
        }

        static {
            h.P();
        }

        public static MIMCSequenceAck i() {
            return h;
        }

        public static Parser<MIMCSequenceAck> j() {
            return h.M();
        }
    }

    public static final class MIMCPull extends GeneratedMessageLite<MIMCPull, Builder> implements MIMCPullOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11233a = 1;
        public static final int b = 2;
        /* access modifiers changed from: private */
        public static final MIMCPull f = new MIMCPull();
        private static volatile Parser<MIMCPull> g;
        private int c;
        private long d;
        private String e = "";

        private MIMCPull() {
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
                codedOutputStream.a(1, this.d);
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
                i2 = 0 + CodedOutputStream.f(1, this.d);
            }
            if ((this.c & 2) == 2) {
                i2 += CodedOutputStream.b(2, d());
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static MIMCPull a(ByteString byteString) throws InvalidProtocolBufferException {
            return (MIMCPull) GeneratedMessageLite.a(f, byteString);
        }

        public static MIMCPull a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCPull) GeneratedMessageLite.a(f, byteString, extensionRegistryLite);
        }

        public static MIMCPull a(byte[] bArr) throws InvalidProtocolBufferException {
            return (MIMCPull) GeneratedMessageLite.a(f, bArr);
        }

        public static MIMCPull a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCPull) GeneratedMessageLite.a(f, bArr, extensionRegistryLite);
        }

        public static MIMCPull a(InputStream inputStream) throws IOException {
            return (MIMCPull) GeneratedMessageLite.a(f, inputStream);
        }

        public static MIMCPull a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCPull) GeneratedMessageLite.a(f, inputStream, extensionRegistryLite);
        }

        public static MIMCPull b(InputStream inputStream) throws IOException {
            return (MIMCPull) b(f, inputStream);
        }

        public static MIMCPull b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCPull) b(f, inputStream, extensionRegistryLite);
        }

        public static MIMCPull a(CodedInputStream codedInputStream) throws IOException {
            return (MIMCPull) GeneratedMessageLite.b(f, codedInputStream);
        }

        public static MIMCPull a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCPull) GeneratedMessageLite.b(f, codedInputStream, extensionRegistryLite);
        }

        public static Builder f() {
            return (Builder) f.Y();
        }

        public static Builder a(MIMCPull mIMCPull) {
            return (Builder) ((Builder) f.Y()).b(mIMCPull);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MIMCPull, Builder> implements MIMCPullOrBuilder {
            private Builder() {
                super(MIMCPull.f);
            }

            public boolean a() {
                return ((MIMCPull) this.f11310a).a();
            }

            public long b() {
                return ((MIMCPull) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((MIMCPull) this.f11310a).a(j);
                return this;
            }

            public Builder f() {
                S();
                ((MIMCPull) this.f11310a).j();
                return this;
            }

            public boolean c() {
                return ((MIMCPull) this.f11310a).c();
            }

            public String d() {
                return ((MIMCPull) this.f11310a).d();
            }

            public ByteString e() {
                return ((MIMCPull) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((MIMCPull) this.f11310a).a(str);
                return this;
            }

            public Builder g() {
                S();
                ((MIMCPull) this.f11310a).l();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((MIMCPull) this.f11310a).c(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new MIMCPull();
                case IS_INITIALIZED:
                    return f;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    MIMCPull mIMCPull = (MIMCPull) obj2;
                    this.d = visitor.a(a(), this.d, mIMCPull.a(), mIMCPull.d);
                    this.e = visitor.a(c(), this.e, mIMCPull.c(), mIMCPull.e);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.c |= mIMCPull.c;
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
                                    this.d = codedInputStream.g();
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
                        synchronized (MIMCPull.class) {
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

        public static MIMCPull g() {
            return f;
        }

        public static Parser<MIMCPull> h() {
            return f.M();
        }
    }

    public static final class P2PPushMesage extends GeneratedMessageLite<P2PPushMesage, Builder> implements P2PPushMesageOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11236a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        /* access modifiers changed from: private */
        public static final P2PPushMesage j = new P2PPushMesage();
        private static volatile Parser<P2PPushMesage> k;
        private int e;
        private MIMCUser f;
        private Internal.ProtobufList<MIMCUser> g = X();
        private ByteString h = ByteString.EMPTY;
        private boolean i;

        private P2PPushMesage() {
        }

        public boolean a() {
            return (this.e & 1) == 1;
        }

        public MIMCUser b() {
            return this.f == null ? MIMCUser.m() : this.f;
        }

        /* access modifiers changed from: private */
        public void a(MIMCUser mIMCUser) {
            if (mIMCUser != null) {
                this.f = mIMCUser;
                this.e |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(MIMCUser.Builder builder) {
            this.f = (MIMCUser) builder.Z();
            this.e |= 1;
        }

        /* access modifiers changed from: private */
        public void b(MIMCUser mIMCUser) {
            if (this.f == null || this.f == MIMCUser.m()) {
                this.f = mIMCUser;
            } else {
                this.f = (MIMCUser) ((MIMCUser.Builder) MIMCUser.a(this.f).b(mIMCUser)).Y();
            }
            this.e |= 1;
        }

        /* access modifiers changed from: private */
        public void o() {
            this.f = null;
            this.e &= -2;
        }

        public List<MIMCUser> c() {
            return this.g;
        }

        public List<? extends MIMCUserOrBuilder> d() {
            return this.g;
        }

        public int e() {
            return this.g.size();
        }

        public MIMCUser a(int i2) {
            return (MIMCUser) this.g.get(i2);
        }

        public MIMCUserOrBuilder b(int i2) {
            return (MIMCUserOrBuilder) this.g.get(i2);
        }

        private void p() {
            if (!this.g.a()) {
                this.g = GeneratedMessageLite.a(this.g);
            }
        }

        /* access modifiers changed from: private */
        public void a(int i2, MIMCUser mIMCUser) {
            if (mIMCUser != null) {
                p();
                this.g.set(i2, mIMCUser);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(int i2, MIMCUser.Builder builder) {
            p();
            this.g.set(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void c(MIMCUser mIMCUser) {
            if (mIMCUser != null) {
                p();
                this.g.add(mIMCUser);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void b(int i2, MIMCUser mIMCUser) {
            if (mIMCUser != null) {
                p();
                this.g.add(i2, mIMCUser);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void b(MIMCUser.Builder builder) {
            p();
            this.g.add(builder.Z());
        }

        /* access modifiers changed from: private */
        public void b(int i2, MIMCUser.Builder builder) {
            p();
            this.g.add(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(Iterable<? extends MIMCUser> iterable) {
            p();
            AbstractMessageLite.a(iterable, this.g);
        }

        /* access modifiers changed from: private */
        public void q() {
            this.g = X();
        }

        /* access modifiers changed from: private */
        public void c(int i2) {
            p();
            this.g.remove(i2);
        }

        public boolean f() {
            return (this.e & 2) == 2;
        }

        public ByteString g() {
            return this.h;
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.e |= 2;
                this.h = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void r() {
            this.e &= -3;
            this.h = l().g();
        }

        public boolean h() {
            return (this.e & 4) == 4;
        }

        public boolean i() {
            return this.i;
        }

        /* access modifiers changed from: private */
        public void a(boolean z) {
            this.e |= 4;
            this.i = z;
        }

        /* access modifiers changed from: private */
        public void s() {
            this.e &= -5;
            this.i = false;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.e & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
            }
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                codedOutputStream.a(2, (MessageLite) this.g.get(i2));
            }
            if ((this.e & 2) == 2) {
                codedOutputStream.a(3, this.h);
            }
            if ((this.e & 4) == 4) {
                codedOutputStream.a(4, this.i);
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int c2 = (this.e & 1) == 1 ? CodedOutputStream.c(1, (MessageLite) b()) + 0 : 0;
            for (int i3 = 0; i3 < this.g.size(); i3++) {
                c2 += CodedOutputStream.c(2, (MessageLite) this.g.get(i3));
            }
            if ((this.e & 2) == 2) {
                c2 += CodedOutputStream.c(3, this.h);
            }
            if ((this.e & 4) == 4) {
                c2 += CodedOutputStream.b(4, this.i);
            }
            int e2 = c2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static P2PPushMesage a(ByteString byteString) throws InvalidProtocolBufferException {
            return (P2PPushMesage) GeneratedMessageLite.a(j, byteString);
        }

        public static P2PPushMesage a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (P2PPushMesage) GeneratedMessageLite.a(j, byteString, extensionRegistryLite);
        }

        public static P2PPushMesage a(byte[] bArr) throws InvalidProtocolBufferException {
            return (P2PPushMesage) GeneratedMessageLite.a(j, bArr);
        }

        public static P2PPushMesage a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (P2PPushMesage) GeneratedMessageLite.a(j, bArr, extensionRegistryLite);
        }

        public static P2PPushMesage a(InputStream inputStream) throws IOException {
            return (P2PPushMesage) GeneratedMessageLite.a(j, inputStream);
        }

        public static P2PPushMesage a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (P2PPushMesage) GeneratedMessageLite.a(j, inputStream, extensionRegistryLite);
        }

        public static P2PPushMesage b(InputStream inputStream) throws IOException {
            return (P2PPushMesage) b(j, inputStream);
        }

        public static P2PPushMesage b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (P2PPushMesage) b(j, inputStream, extensionRegistryLite);
        }

        public static P2PPushMesage a(CodedInputStream codedInputStream) throws IOException {
            return (P2PPushMesage) GeneratedMessageLite.b(j, codedInputStream);
        }

        public static P2PPushMesage a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (P2PPushMesage) GeneratedMessageLite.b(j, codedInputStream, extensionRegistryLite);
        }

        public static Builder j() {
            return (Builder) j.Y();
        }

        public static Builder a(P2PPushMesage p2PPushMesage) {
            return (Builder) ((Builder) j.Y()).b(p2PPushMesage);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<P2PPushMesage, Builder> implements P2PPushMesageOrBuilder {
            private Builder() {
                super(P2PPushMesage.j);
            }

            public boolean a() {
                return ((P2PPushMesage) this.f11310a).a();
            }

            public MIMCUser b() {
                return ((P2PPushMesage) this.f11310a).b();
            }

            public Builder a(MIMCUser mIMCUser) {
                S();
                ((P2PPushMesage) this.f11310a).a(mIMCUser);
                return this;
            }

            public Builder a(MIMCUser.Builder builder) {
                S();
                ((P2PPushMesage) this.f11310a).a(builder);
                return this;
            }

            public Builder b(MIMCUser mIMCUser) {
                S();
                ((P2PPushMesage) this.f11310a).b(mIMCUser);
                return this;
            }

            public Builder d() {
                S();
                ((P2PPushMesage) this.f11310a).o();
                return this;
            }

            public List<MIMCUser> c() {
                return Collections.unmodifiableList(((P2PPushMesage) this.f11310a).c());
            }

            public int e() {
                return ((P2PPushMesage) this.f11310a).e();
            }

            public MIMCUser a(int i) {
                return ((P2PPushMesage) this.f11310a).a(i);
            }

            public Builder a(int i, MIMCUser mIMCUser) {
                S();
                ((P2PPushMesage) this.f11310a).a(i, mIMCUser);
                return this;
            }

            public Builder a(int i, MIMCUser.Builder builder) {
                S();
                ((P2PPushMesage) this.f11310a).a(i, builder);
                return this;
            }

            public Builder c(MIMCUser mIMCUser) {
                S();
                ((P2PPushMesage) this.f11310a).c(mIMCUser);
                return this;
            }

            public Builder b(int i, MIMCUser mIMCUser) {
                S();
                ((P2PPushMesage) this.f11310a).b(i, mIMCUser);
                return this;
            }

            public Builder b(MIMCUser.Builder builder) {
                S();
                ((P2PPushMesage) this.f11310a).b(builder);
                return this;
            }

            public Builder b(int i, MIMCUser.Builder builder) {
                S();
                ((P2PPushMesage) this.f11310a).b(i, builder);
                return this;
            }

            public Builder a(Iterable<? extends MIMCUser> iterable) {
                S();
                ((P2PPushMesage) this.f11310a).a(iterable);
                return this;
            }

            public Builder j() {
                S();
                ((P2PPushMesage) this.f11310a).q();
                return this;
            }

            public Builder b(int i) {
                S();
                ((P2PPushMesage) this.f11310a).c(i);
                return this;
            }

            public boolean f() {
                return ((P2PPushMesage) this.f11310a).f();
            }

            public ByteString g() {
                return ((P2PPushMesage) this.f11310a).g();
            }

            public Builder a(ByteString byteString) {
                S();
                ((P2PPushMesage) this.f11310a).c(byteString);
                return this;
            }

            public Builder k() {
                S();
                ((P2PPushMesage) this.f11310a).r();
                return this;
            }

            public boolean h() {
                return ((P2PPushMesage) this.f11310a).h();
            }

            public boolean i() {
                return ((P2PPushMesage) this.f11310a).i();
            }

            public Builder a(boolean z) {
                S();
                ((P2PPushMesage) this.f11310a).a(z);
                return this;
            }

            public Builder l() {
                S();
                ((P2PPushMesage) this.f11310a).s();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new P2PPushMesage();
                case IS_INITIALIZED:
                    return j;
                case MAKE_IMMUTABLE:
                    this.g.b();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    P2PPushMesage p2PPushMesage = (P2PPushMesage) obj2;
                    this.f = (MIMCUser) visitor.a(this.f, p2PPushMesage.f);
                    this.g = visitor.a(this.g, p2PPushMesage.g);
                    this.h = visitor.a(f(), this.h, p2PPushMesage.f(), p2PPushMesage.h);
                    this.i = visitor.a(h(), this.i, p2PPushMesage.h(), p2PPushMesage.i);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.e |= p2PPushMesage.e;
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
                                    MIMCUser.Builder builder = (this.e & 1) == 1 ? (MIMCUser.Builder) this.f.Y() : null;
                                    this.f = (MIMCUser) codedInputStream.a(MIMCUser.n(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.f);
                                        this.f = (MIMCUser) builder.Y();
                                    }
                                    this.e |= 1;
                                } else if (a2 == 18) {
                                    if (!this.g.a()) {
                                        this.g = GeneratedMessageLite.a(this.g);
                                    }
                                    this.g.add(codedInputStream.a(MIMCUser.n(), extensionRegistryLite));
                                } else if (a2 == 26) {
                                    this.e |= 2;
                                    this.h = codedInputStream.n();
                                } else if (a2 == 32) {
                                    this.e |= 4;
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
                    if (k == null) {
                        synchronized (P2PPushMesage.class) {
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

        public static P2PPushMesage l() {
            return j;
        }

        public static Parser<P2PPushMesage> m() {
            return j.M();
        }
    }

    public static final class P2TPushMesage extends GeneratedMessageLite<P2TPushMesage, Builder> implements P2TPushMesageOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11237a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        /* access modifiers changed from: private */
        public static final P2TPushMesage j = new P2TPushMesage();
        private static volatile Parser<P2TPushMesage> k;
        private int e;
        private MIMCUser f;
        private Internal.ProtobufList<MIMCGroup> g = X();
        private ByteString h = ByteString.EMPTY;
        private boolean i;

        private P2TPushMesage() {
        }

        public boolean a() {
            return (this.e & 1) == 1;
        }

        public MIMCUser b() {
            return this.f == null ? MIMCUser.m() : this.f;
        }

        /* access modifiers changed from: private */
        public void a(MIMCUser mIMCUser) {
            if (mIMCUser != null) {
                this.f = mIMCUser;
                this.e |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(MIMCUser.Builder builder) {
            this.f = (MIMCUser) builder.Z();
            this.e |= 1;
        }

        /* access modifiers changed from: private */
        public void b(MIMCUser mIMCUser) {
            if (this.f == null || this.f == MIMCUser.m()) {
                this.f = mIMCUser;
            } else {
                this.f = (MIMCUser) ((MIMCUser.Builder) MIMCUser.a(this.f).b(mIMCUser)).Y();
            }
            this.e |= 1;
        }

        /* access modifiers changed from: private */
        public void o() {
            this.f = null;
            this.e &= -2;
        }

        public List<MIMCGroup> c() {
            return this.g;
        }

        public List<? extends MIMCGroupOrBuilder> d() {
            return this.g;
        }

        public int e() {
            return this.g.size();
        }

        public MIMCGroup a(int i2) {
            return (MIMCGroup) this.g.get(i2);
        }

        public MIMCGroupOrBuilder b(int i2) {
            return (MIMCGroupOrBuilder) this.g.get(i2);
        }

        private void p() {
            if (!this.g.a()) {
                this.g = GeneratedMessageLite.a(this.g);
            }
        }

        /* access modifiers changed from: private */
        public void a(int i2, MIMCGroup mIMCGroup) {
            if (mIMCGroup != null) {
                p();
                this.g.set(i2, mIMCGroup);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(int i2, MIMCGroup.Builder builder) {
            p();
            this.g.set(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(MIMCGroup mIMCGroup) {
            if (mIMCGroup != null) {
                p();
                this.g.add(mIMCGroup);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void b(int i2, MIMCGroup mIMCGroup) {
            if (mIMCGroup != null) {
                p();
                this.g.add(i2, mIMCGroup);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(MIMCGroup.Builder builder) {
            p();
            this.g.add(builder.Z());
        }

        /* access modifiers changed from: private */
        public void b(int i2, MIMCGroup.Builder builder) {
            p();
            this.g.add(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(Iterable<? extends MIMCGroup> iterable) {
            p();
            AbstractMessageLite.a(iterable, this.g);
        }

        /* access modifiers changed from: private */
        public void q() {
            this.g = X();
        }

        /* access modifiers changed from: private */
        public void c(int i2) {
            p();
            this.g.remove(i2);
        }

        public boolean f() {
            return (this.e & 2) == 2;
        }

        public ByteString g() {
            return this.h;
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.e |= 2;
                this.h = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void r() {
            this.e &= -3;
            this.h = l().g();
        }

        public boolean h() {
            return (this.e & 4) == 4;
        }

        public boolean i() {
            return this.i;
        }

        /* access modifiers changed from: private */
        public void a(boolean z) {
            this.e |= 4;
            this.i = z;
        }

        /* access modifiers changed from: private */
        public void s() {
            this.e &= -5;
            this.i = false;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.e & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
            }
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                codedOutputStream.a(2, (MessageLite) this.g.get(i2));
            }
            if ((this.e & 2) == 2) {
                codedOutputStream.a(3, this.h);
            }
            if ((this.e & 4) == 4) {
                codedOutputStream.a(4, this.i);
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int c2 = (this.e & 1) == 1 ? CodedOutputStream.c(1, (MessageLite) b()) + 0 : 0;
            for (int i3 = 0; i3 < this.g.size(); i3++) {
                c2 += CodedOutputStream.c(2, (MessageLite) this.g.get(i3));
            }
            if ((this.e & 2) == 2) {
                c2 += CodedOutputStream.c(3, this.h);
            }
            if ((this.e & 4) == 4) {
                c2 += CodedOutputStream.b(4, this.i);
            }
            int e2 = c2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static P2TPushMesage a(ByteString byteString) throws InvalidProtocolBufferException {
            return (P2TPushMesage) GeneratedMessageLite.a(j, byteString);
        }

        public static P2TPushMesage a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (P2TPushMesage) GeneratedMessageLite.a(j, byteString, extensionRegistryLite);
        }

        public static P2TPushMesage a(byte[] bArr) throws InvalidProtocolBufferException {
            return (P2TPushMesage) GeneratedMessageLite.a(j, bArr);
        }

        public static P2TPushMesage a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (P2TPushMesage) GeneratedMessageLite.a(j, bArr, extensionRegistryLite);
        }

        public static P2TPushMesage a(InputStream inputStream) throws IOException {
            return (P2TPushMesage) GeneratedMessageLite.a(j, inputStream);
        }

        public static P2TPushMesage a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (P2TPushMesage) GeneratedMessageLite.a(j, inputStream, extensionRegistryLite);
        }

        public static P2TPushMesage b(InputStream inputStream) throws IOException {
            return (P2TPushMesage) b(j, inputStream);
        }

        public static P2TPushMesage b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (P2TPushMesage) b(j, inputStream, extensionRegistryLite);
        }

        public static P2TPushMesage a(CodedInputStream codedInputStream) throws IOException {
            return (P2TPushMesage) GeneratedMessageLite.b(j, codedInputStream);
        }

        public static P2TPushMesage a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (P2TPushMesage) GeneratedMessageLite.b(j, codedInputStream, extensionRegistryLite);
        }

        public static Builder j() {
            return (Builder) j.Y();
        }

        public static Builder a(P2TPushMesage p2TPushMesage) {
            return (Builder) ((Builder) j.Y()).b(p2TPushMesage);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<P2TPushMesage, Builder> implements P2TPushMesageOrBuilder {
            private Builder() {
                super(P2TPushMesage.j);
            }

            public boolean a() {
                return ((P2TPushMesage) this.f11310a).a();
            }

            public MIMCUser b() {
                return ((P2TPushMesage) this.f11310a).b();
            }

            public Builder a(MIMCUser mIMCUser) {
                S();
                ((P2TPushMesage) this.f11310a).a(mIMCUser);
                return this;
            }

            public Builder a(MIMCUser.Builder builder) {
                S();
                ((P2TPushMesage) this.f11310a).a(builder);
                return this;
            }

            public Builder b(MIMCUser mIMCUser) {
                S();
                ((P2TPushMesage) this.f11310a).b(mIMCUser);
                return this;
            }

            public Builder d() {
                S();
                ((P2TPushMesage) this.f11310a).o();
                return this;
            }

            public List<MIMCGroup> c() {
                return Collections.unmodifiableList(((P2TPushMesage) this.f11310a).c());
            }

            public int e() {
                return ((P2TPushMesage) this.f11310a).e();
            }

            public MIMCGroup a(int i) {
                return ((P2TPushMesage) this.f11310a).a(i);
            }

            public Builder a(int i, MIMCGroup mIMCGroup) {
                S();
                ((P2TPushMesage) this.f11310a).a(i, mIMCGroup);
                return this;
            }

            public Builder a(int i, MIMCGroup.Builder builder) {
                S();
                ((P2TPushMesage) this.f11310a).a(i, builder);
                return this;
            }

            public Builder a(MIMCGroup mIMCGroup) {
                S();
                ((P2TPushMesage) this.f11310a).a(mIMCGroup);
                return this;
            }

            public Builder b(int i, MIMCGroup mIMCGroup) {
                S();
                ((P2TPushMesage) this.f11310a).b(i, mIMCGroup);
                return this;
            }

            public Builder a(MIMCGroup.Builder builder) {
                S();
                ((P2TPushMesage) this.f11310a).a(builder);
                return this;
            }

            public Builder b(int i, MIMCGroup.Builder builder) {
                S();
                ((P2TPushMesage) this.f11310a).b(i, builder);
                return this;
            }

            public Builder a(Iterable<? extends MIMCGroup> iterable) {
                S();
                ((P2TPushMesage) this.f11310a).a(iterable);
                return this;
            }

            public Builder j() {
                S();
                ((P2TPushMesage) this.f11310a).q();
                return this;
            }

            public Builder b(int i) {
                S();
                ((P2TPushMesage) this.f11310a).c(i);
                return this;
            }

            public boolean f() {
                return ((P2TPushMesage) this.f11310a).f();
            }

            public ByteString g() {
                return ((P2TPushMesage) this.f11310a).g();
            }

            public Builder a(ByteString byteString) {
                S();
                ((P2TPushMesage) this.f11310a).c(byteString);
                return this;
            }

            public Builder k() {
                S();
                ((P2TPushMesage) this.f11310a).r();
                return this;
            }

            public boolean h() {
                return ((P2TPushMesage) this.f11310a).h();
            }

            public boolean i() {
                return ((P2TPushMesage) this.f11310a).i();
            }

            public Builder a(boolean z) {
                S();
                ((P2TPushMesage) this.f11310a).a(z);
                return this;
            }

            public Builder l() {
                S();
                ((P2TPushMesage) this.f11310a).s();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new P2TPushMesage();
                case IS_INITIALIZED:
                    return j;
                case MAKE_IMMUTABLE:
                    this.g.b();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    P2TPushMesage p2TPushMesage = (P2TPushMesage) obj2;
                    this.f = (MIMCUser) visitor.a(this.f, p2TPushMesage.f);
                    this.g = visitor.a(this.g, p2TPushMesage.g);
                    this.h = visitor.a(f(), this.h, p2TPushMesage.f(), p2TPushMesage.h);
                    this.i = visitor.a(h(), this.i, p2TPushMesage.h(), p2TPushMesage.i);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.e |= p2TPushMesage.e;
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
                                    MIMCUser.Builder builder = (this.e & 1) == 1 ? (MIMCUser.Builder) this.f.Y() : null;
                                    this.f = (MIMCUser) codedInputStream.a(MIMCUser.n(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.f);
                                        this.f = (MIMCUser) builder.Y();
                                    }
                                    this.e |= 1;
                                } else if (a2 == 18) {
                                    if (!this.g.a()) {
                                        this.g = GeneratedMessageLite.a(this.g);
                                    }
                                    this.g.add(codedInputStream.a(MIMCGroup.g(), extensionRegistryLite));
                                } else if (a2 == 26) {
                                    this.e |= 2;
                                    this.h = codedInputStream.n();
                                } else if (a2 == 32) {
                                    this.e |= 4;
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
                    if (k == null) {
                        synchronized (P2TPushMesage.class) {
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

        public static P2TPushMesage l() {
            return j;
        }

        public static Parser<P2TPushMesage> m() {
            return j.M();
        }
    }

    public static final class MIMCUser extends GeneratedMessageLite<MIMCUser, Builder> implements MIMCUserOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11235a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        /* access modifiers changed from: private */
        public static final MIMCUser j = new MIMCUser();
        private static volatile Parser<MIMCUser> k;
        private int e;
        private long f;
        private String g = "";
        private long h;
        private String i = "";

        private MIMCUser() {
        }

        public boolean a() {
            return (this.e & 1) == 1;
        }

        public long b() {
            return this.f;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.e |= 1;
            this.f = j2;
        }

        /* access modifiers changed from: private */
        public void p() {
            this.e &= -2;
            this.f = 0;
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
        public void q() {
            this.e &= -3;
            this.g = m().d();
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

        public long g() {
            return this.h;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.e |= 4;
            this.h = j2;
        }

        /* access modifiers changed from: private */
        public void r() {
            this.e &= -5;
            this.h = 0;
        }

        public boolean h() {
            return (this.e & 8) == 8;
        }

        public String i() {
            return this.i;
        }

        public ByteString j() {
            return ByteString.copyFromUtf8(this.i);
        }

        /* access modifiers changed from: private */
        public void b(String str) {
            if (str != null) {
                this.e |= 8;
                this.i = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void s() {
            this.e &= -9;
            this.i = m().i();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
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
                codedOutputStream.a(3, this.h);
            }
            if ((this.e & 8) == 8) {
                codedOutputStream.a(4, i());
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
                i3 = 0 + CodedOutputStream.f(1, this.f);
            }
            if ((this.e & 2) == 2) {
                i3 += CodedOutputStream.b(2, d());
            }
            if ((this.e & 4) == 4) {
                i3 += CodedOutputStream.f(3, this.h);
            }
            if ((this.e & 8) == 8) {
                i3 += CodedOutputStream.b(4, i());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static MIMCUser a(ByteString byteString) throws InvalidProtocolBufferException {
            return (MIMCUser) GeneratedMessageLite.a(j, byteString);
        }

        public static MIMCUser a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCUser) GeneratedMessageLite.a(j, byteString, extensionRegistryLite);
        }

        public static MIMCUser a(byte[] bArr) throws InvalidProtocolBufferException {
            return (MIMCUser) GeneratedMessageLite.a(j, bArr);
        }

        public static MIMCUser a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCUser) GeneratedMessageLite.a(j, bArr, extensionRegistryLite);
        }

        public static MIMCUser a(InputStream inputStream) throws IOException {
            return (MIMCUser) GeneratedMessageLite.a(j, inputStream);
        }

        public static MIMCUser a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCUser) GeneratedMessageLite.a(j, inputStream, extensionRegistryLite);
        }

        public static MIMCUser b(InputStream inputStream) throws IOException {
            return (MIMCUser) b(j, inputStream);
        }

        public static MIMCUser b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCUser) b(j, inputStream, extensionRegistryLite);
        }

        public static MIMCUser a(CodedInputStream codedInputStream) throws IOException {
            return (MIMCUser) GeneratedMessageLite.b(j, codedInputStream);
        }

        public static MIMCUser a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCUser) GeneratedMessageLite.b(j, codedInputStream, extensionRegistryLite);
        }

        public static Builder l() {
            return (Builder) j.Y();
        }

        public static Builder a(MIMCUser mIMCUser) {
            return (Builder) ((Builder) j.Y()).b(mIMCUser);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MIMCUser, Builder> implements MIMCUserOrBuilder {
            private Builder() {
                super(MIMCUser.j);
            }

            public boolean a() {
                return ((MIMCUser) this.f11310a).a();
            }

            public long b() {
                return ((MIMCUser) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((MIMCUser) this.f11310a).a(j);
                return this;
            }

            public Builder k() {
                S();
                ((MIMCUser) this.f11310a).p();
                return this;
            }

            public boolean c() {
                return ((MIMCUser) this.f11310a).c();
            }

            public String d() {
                return ((MIMCUser) this.f11310a).d();
            }

            public ByteString e() {
                return ((MIMCUser) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((MIMCUser) this.f11310a).a(str);
                return this;
            }

            public Builder l() {
                S();
                ((MIMCUser) this.f11310a).q();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((MIMCUser) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((MIMCUser) this.f11310a).f();
            }

            public long g() {
                return ((MIMCUser) this.f11310a).g();
            }

            public Builder b(long j) {
                S();
                ((MIMCUser) this.f11310a).b(j);
                return this;
            }

            public Builder m() {
                S();
                ((MIMCUser) this.f11310a).r();
                return this;
            }

            public boolean h() {
                return ((MIMCUser) this.f11310a).h();
            }

            public String i() {
                return ((MIMCUser) this.f11310a).i();
            }

            public ByteString j() {
                return ((MIMCUser) this.f11310a).j();
            }

            public Builder b(String str) {
                S();
                ((MIMCUser) this.f11310a).b(str);
                return this;
            }

            public Builder n() {
                S();
                ((MIMCUser) this.f11310a).s();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((MIMCUser) this.f11310a).d(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new MIMCUser();
                case IS_INITIALIZED:
                    return j;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    MIMCUser mIMCUser = (MIMCUser) obj2;
                    this.f = visitor.a(a(), this.f, mIMCUser.a(), mIMCUser.f);
                    this.g = visitor.a(c(), this.g, mIMCUser.c(), mIMCUser.g);
                    this.h = visitor.a(f(), this.h, mIMCUser.f(), mIMCUser.h);
                    this.i = visitor.a(h(), this.i, mIMCUser.h(), mIMCUser.i);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.e |= mIMCUser.e;
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
                                    this.f = codedInputStream.g();
                                } else if (a2 == 18) {
                                    String l = codedInputStream.l();
                                    this.e |= 2;
                                    this.g = l;
                                } else if (a2 == 24) {
                                    this.e |= 4;
                                    this.h = codedInputStream.g();
                                } else if (a2 == 34) {
                                    String l2 = codedInputStream.l();
                                    this.e |= 8;
                                    this.i = l2;
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
                        synchronized (MIMCUser.class) {
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

        public static MIMCUser m() {
            return j;
        }

        public static Parser<MIMCUser> n() {
            return j.M();
        }
    }

    public static final class MIMCGroup extends GeneratedMessageLite<MIMCGroup, Builder> implements MIMCGroupOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11227a = 1;
        public static final int b = 2;
        /* access modifiers changed from: private */
        public static final MIMCGroup f = new MIMCGroup();
        private static volatile Parser<MIMCGroup> g;
        private int c;
        private long d;
        private long e;

        private MIMCGroup() {
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
        public void i() {
            this.c &= -2;
            this.d = 0;
        }

        public boolean c() {
            return (this.c & 2) == 2;
        }

        public long d() {
            return this.e;
        }

        /* access modifiers changed from: private */
        public void b(long j) {
            this.c |= 2;
            this.e = j;
        }

        /* access modifiers changed from: private */
        public void j() {
            this.c &= -3;
            this.e = 0;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.c & 1) == 1) {
                codedOutputStream.a(1, this.d);
            }
            if ((this.c & 2) == 2) {
                codedOutputStream.a(2, this.e);
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
                i2 = 0 + CodedOutputStream.f(1, this.d);
            }
            if ((this.c & 2) == 2) {
                i2 += CodedOutputStream.f(2, this.e);
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static MIMCGroup a(ByteString byteString) throws InvalidProtocolBufferException {
            return (MIMCGroup) GeneratedMessageLite.a(f, byteString);
        }

        public static MIMCGroup a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCGroup) GeneratedMessageLite.a(f, byteString, extensionRegistryLite);
        }

        public static MIMCGroup a(byte[] bArr) throws InvalidProtocolBufferException {
            return (MIMCGroup) GeneratedMessageLite.a(f, bArr);
        }

        public static MIMCGroup a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MIMCGroup) GeneratedMessageLite.a(f, bArr, extensionRegistryLite);
        }

        public static MIMCGroup a(InputStream inputStream) throws IOException {
            return (MIMCGroup) GeneratedMessageLite.a(f, inputStream);
        }

        public static MIMCGroup a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCGroup) GeneratedMessageLite.a(f, inputStream, extensionRegistryLite);
        }

        public static MIMCGroup b(InputStream inputStream) throws IOException {
            return (MIMCGroup) b(f, inputStream);
        }

        public static MIMCGroup b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCGroup) b(f, inputStream, extensionRegistryLite);
        }

        public static MIMCGroup a(CodedInputStream codedInputStream) throws IOException {
            return (MIMCGroup) GeneratedMessageLite.b(f, codedInputStream);
        }

        public static MIMCGroup a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MIMCGroup) GeneratedMessageLite.b(f, codedInputStream, extensionRegistryLite);
        }

        public static Builder e() {
            return (Builder) f.Y();
        }

        public static Builder a(MIMCGroup mIMCGroup) {
            return (Builder) ((Builder) f.Y()).b(mIMCGroup);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<MIMCGroup, Builder> implements MIMCGroupOrBuilder {
            private Builder() {
                super(MIMCGroup.f);
            }

            public boolean a() {
                return ((MIMCGroup) this.f11310a).a();
            }

            public long b() {
                return ((MIMCGroup) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((MIMCGroup) this.f11310a).a(j);
                return this;
            }

            public Builder e() {
                S();
                ((MIMCGroup) this.f11310a).i();
                return this;
            }

            public boolean c() {
                return ((MIMCGroup) this.f11310a).c();
            }

            public long d() {
                return ((MIMCGroup) this.f11310a).d();
            }

            public Builder b(long j) {
                S();
                ((MIMCGroup) this.f11310a).b(j);
                return this;
            }

            public Builder f() {
                S();
                ((MIMCGroup) this.f11310a).j();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new MIMCGroup();
                case IS_INITIALIZED:
                    return f;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    MIMCGroup mIMCGroup = (MIMCGroup) obj2;
                    this.d = visitor.a(a(), this.d, mIMCGroup.a(), mIMCGroup.d);
                    this.e = visitor.a(c(), this.e, mIMCGroup.c(), mIMCGroup.e);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.c |= mIMCGroup.c;
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
                                    this.d = codedInputStream.g();
                                } else if (a2 == 16) {
                                    this.c |= 2;
                                    this.e = codedInputStream.g();
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
                        synchronized (MIMCGroup.class) {
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

        public static MIMCGroup f() {
            return f;
        }

        public static Parser<MIMCGroup> g() {
            return f.M();
        }
    }

    public static final class UCGroup extends GeneratedMessageLite<UCGroup, Builder> implements UCGroupOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11243a = 1;
        public static final int b = 2;
        /* access modifiers changed from: private */
        public static final UCGroup f = new UCGroup();
        private static volatile Parser<UCGroup> g;
        private int c;
        private long d;
        private long e;

        private UCGroup() {
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
        public void i() {
            this.c &= -2;
            this.d = 0;
        }

        public boolean c() {
            return (this.c & 2) == 2;
        }

        public long d() {
            return this.e;
        }

        /* access modifiers changed from: private */
        public void b(long j) {
            this.c |= 2;
            this.e = j;
        }

        /* access modifiers changed from: private */
        public void j() {
            this.c &= -3;
            this.e = 0;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.c & 1) == 1) {
                codedOutputStream.a(1, this.d);
            }
            if ((this.c & 2) == 2) {
                codedOutputStream.a(2, this.e);
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
                i2 = 0 + CodedOutputStream.f(1, this.d);
            }
            if ((this.c & 2) == 2) {
                i2 += CodedOutputStream.f(2, this.e);
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UCGroup a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UCGroup) GeneratedMessageLite.a(f, byteString);
        }

        public static UCGroup a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCGroup) GeneratedMessageLite.a(f, byteString, extensionRegistryLite);
        }

        public static UCGroup a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UCGroup) GeneratedMessageLite.a(f, bArr);
        }

        public static UCGroup a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCGroup) GeneratedMessageLite.a(f, bArr, extensionRegistryLite);
        }

        public static UCGroup a(InputStream inputStream) throws IOException {
            return (UCGroup) GeneratedMessageLite.a(f, inputStream);
        }

        public static UCGroup a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCGroup) GeneratedMessageLite.a(f, inputStream, extensionRegistryLite);
        }

        public static UCGroup b(InputStream inputStream) throws IOException {
            return (UCGroup) b(f, inputStream);
        }

        public static UCGroup b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCGroup) b(f, inputStream, extensionRegistryLite);
        }

        public static UCGroup a(CodedInputStream codedInputStream) throws IOException {
            return (UCGroup) GeneratedMessageLite.b(f, codedInputStream);
        }

        public static UCGroup a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCGroup) GeneratedMessageLite.b(f, codedInputStream, extensionRegistryLite);
        }

        public static Builder e() {
            return (Builder) f.Y();
        }

        public static Builder a(UCGroup uCGroup) {
            return (Builder) ((Builder) f.Y()).b(uCGroup);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UCGroup, Builder> implements UCGroupOrBuilder {
            private Builder() {
                super(UCGroup.f);
            }

            public boolean a() {
                return ((UCGroup) this.f11310a).a();
            }

            public long b() {
                return ((UCGroup) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((UCGroup) this.f11310a).a(j);
                return this;
            }

            public Builder e() {
                S();
                ((UCGroup) this.f11310a).i();
                return this;
            }

            public boolean c() {
                return ((UCGroup) this.f11310a).c();
            }

            public long d() {
                return ((UCGroup) this.f11310a).d();
            }

            public Builder b(long j) {
                S();
                ((UCGroup) this.f11310a).b(j);
                return this;
            }

            public Builder f() {
                S();
                ((UCGroup) this.f11310a).j();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UCGroup();
                case IS_INITIALIZED:
                    return f;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    UCGroup uCGroup = (UCGroup) obj2;
                    this.d = visitor.a(a(), this.d, uCGroup.a(), uCGroup.d);
                    this.e = visitor.a(c(), this.e, uCGroup.c(), uCGroup.e);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.c |= uCGroup.c;
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
                                    this.d = codedInputStream.g();
                                } else if (a2 == 16) {
                                    this.c |= 2;
                                    this.e = codedInputStream.g();
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
                        synchronized (UCGroup.class) {
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

        public static UCGroup f() {
            return f;
        }

        public static Parser<UCGroup> g() {
            return f.M();
        }
    }

    public static final class UCPacket extends GeneratedMessageLite<UCPacket, Builder> implements UCPacketOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11248a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        /* access modifiers changed from: private */
        public static final UCPacket k = new UCPacket();
        private static volatile Parser<UCPacket> l;
        private int e;
        private MIMCUser f;
        private int g = 1;
        private ByteString h = ByteString.EMPTY;
        private String i = "";
        private byte j = -1;

        private UCPacket() {
        }

        public boolean a() {
            return (this.e & 1) == 1;
        }

        public MIMCUser b() {
            return this.f == null ? MIMCUser.m() : this.f;
        }

        /* access modifiers changed from: private */
        public void a(MIMCUser mIMCUser) {
            if (mIMCUser != null) {
                this.f = mIMCUser;
                this.e |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(MIMCUser.Builder builder) {
            this.f = (MIMCUser) builder.Z();
            this.e |= 1;
        }

        /* access modifiers changed from: private */
        public void b(MIMCUser mIMCUser) {
            if (this.f == null || this.f == MIMCUser.m()) {
                this.f = mIMCUser;
            } else {
                this.f = (MIMCUser) ((MIMCUser.Builder) MIMCUser.a(this.f).b(mIMCUser)).Y();
            }
            this.e |= 1;
        }

        /* access modifiers changed from: private */
        public void o() {
            this.f = null;
            this.e &= -2;
        }

        public boolean c() {
            return (this.e & 2) == 2;
        }

        public UC_MSG_TYPE d() {
            UC_MSG_TYPE forNumber = UC_MSG_TYPE.forNumber(this.g);
            return forNumber == null ? UC_MSG_TYPE.PING : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(UC_MSG_TYPE uc_msg_type) {
            if (uc_msg_type != null) {
                this.e |= 2;
                this.g = uc_msg_type.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void p() {
            this.e &= -3;
            this.g = 1;
        }

        public boolean e() {
            return (this.e & 4) == 4;
        }

        public ByteString f() {
            return this.h;
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.e |= 4;
                this.h = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void q() {
            this.e &= -5;
            this.h = l().f();
        }

        public boolean g() {
            return (this.e & 8) == 8;
        }

        public String h() {
            return this.i;
        }

        public ByteString i() {
            return ByteString.copyFromUtf8(this.i);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.e |= 8;
                this.i = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void r() {
            this.e &= -9;
            this.i = l().h();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.e |= 8;
                this.i = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.e & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
            }
            if ((this.e & 2) == 2) {
                codedOutputStream.g(2, this.g);
            }
            if ((this.e & 4) == 4) {
                codedOutputStream.a(3, this.h);
            }
            if ((this.e & 8) == 8) {
                codedOutputStream.a(4, h());
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
                i3 = 0 + CodedOutputStream.c(1, (MessageLite) b());
            }
            if ((this.e & 2) == 2) {
                i3 += CodedOutputStream.m(2, this.g);
            }
            if ((this.e & 4) == 4) {
                i3 += CodedOutputStream.c(3, this.h);
            }
            if ((this.e & 8) == 8) {
                i3 += CodedOutputStream.b(4, h());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UCPacket a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UCPacket) GeneratedMessageLite.a(k, byteString);
        }

        public static UCPacket a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCPacket) GeneratedMessageLite.a(k, byteString, extensionRegistryLite);
        }

        public static UCPacket a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UCPacket) GeneratedMessageLite.a(k, bArr);
        }

        public static UCPacket a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCPacket) GeneratedMessageLite.a(k, bArr, extensionRegistryLite);
        }

        public static UCPacket a(InputStream inputStream) throws IOException {
            return (UCPacket) GeneratedMessageLite.a(k, inputStream);
        }

        public static UCPacket a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCPacket) GeneratedMessageLite.a(k, inputStream, extensionRegistryLite);
        }

        public static UCPacket b(InputStream inputStream) throws IOException {
            return (UCPacket) b(k, inputStream);
        }

        public static UCPacket b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCPacket) b(k, inputStream, extensionRegistryLite);
        }

        public static UCPacket a(CodedInputStream codedInputStream) throws IOException {
            return (UCPacket) GeneratedMessageLite.b(k, codedInputStream);
        }

        public static UCPacket a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCPacket) GeneratedMessageLite.b(k, codedInputStream, extensionRegistryLite);
        }

        public static Builder j() {
            return (Builder) k.Y();
        }

        public static Builder a(UCPacket uCPacket) {
            return (Builder) ((Builder) k.Y()).b(uCPacket);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UCPacket, Builder> implements UCPacketOrBuilder {
            private Builder() {
                super(UCPacket.k);
            }

            public boolean a() {
                return ((UCPacket) this.f11310a).a();
            }

            public MIMCUser b() {
                return ((UCPacket) this.f11310a).b();
            }

            public Builder a(MIMCUser mIMCUser) {
                S();
                ((UCPacket) this.f11310a).a(mIMCUser);
                return this;
            }

            public Builder a(MIMCUser.Builder builder) {
                S();
                ((UCPacket) this.f11310a).a(builder);
                return this;
            }

            public Builder b(MIMCUser mIMCUser) {
                S();
                ((UCPacket) this.f11310a).b(mIMCUser);
                return this;
            }

            public Builder j() {
                S();
                ((UCPacket) this.f11310a).o();
                return this;
            }

            public boolean c() {
                return ((UCPacket) this.f11310a).c();
            }

            public UC_MSG_TYPE d() {
                return ((UCPacket) this.f11310a).d();
            }

            public Builder a(UC_MSG_TYPE uc_msg_type) {
                S();
                ((UCPacket) this.f11310a).a(uc_msg_type);
                return this;
            }

            public Builder k() {
                S();
                ((UCPacket) this.f11310a).p();
                return this;
            }

            public boolean e() {
                return ((UCPacket) this.f11310a).e();
            }

            public ByteString f() {
                return ((UCPacket) this.f11310a).f();
            }

            public Builder a(ByteString byteString) {
                S();
                ((UCPacket) this.f11310a).c(byteString);
                return this;
            }

            public Builder l() {
                S();
                ((UCPacket) this.f11310a).q();
                return this;
            }

            public boolean g() {
                return ((UCPacket) this.f11310a).g();
            }

            public String h() {
                return ((UCPacket) this.f11310a).h();
            }

            public ByteString i() {
                return ((UCPacket) this.f11310a).i();
            }

            public Builder a(String str) {
                S();
                ((UCPacket) this.f11310a).a(str);
                return this;
            }

            public Builder m() {
                S();
                ((UCPacket) this.f11310a).r();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((UCPacket) this.f11310a).d(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UCPacket();
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
                    } else if (!e()) {
                        if (booleanValue) {
                            this.j = 0;
                        }
                        return null;
                    } else if (!g()) {
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
                    UCPacket uCPacket = (UCPacket) obj2;
                    this.f = (MIMCUser) visitor.a(this.f, uCPacket.f);
                    this.g = visitor.a(c(), this.g, uCPacket.c(), uCPacket.g);
                    this.h = visitor.a(e(), this.h, uCPacket.e(), uCPacket.h);
                    this.i = visitor.a(g(), this.i, uCPacket.g(), uCPacket.i);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.e |= uCPacket.e;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    MIMCUser.Builder builder = (this.e & 1) == 1 ? (MIMCUser.Builder) this.f.Y() : null;
                                    this.f = (MIMCUser) codedInputStream.a(MIMCUser.n(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.f);
                                        this.f = (MIMCUser) builder.Y();
                                    }
                                    this.e |= 1;
                                } else if (a2 == 16) {
                                    int r = codedInputStream.r();
                                    if (UC_MSG_TYPE.forNumber(r) == null) {
                                        super.a(2, r);
                                    } else {
                                        this.e |= 2;
                                        this.g = r;
                                    }
                                } else if (a2 == 26) {
                                    this.e |= 4;
                                    this.h = codedInputStream.n();
                                } else if (a2 == 34) {
                                    String l2 = codedInputStream.l();
                                    this.e |= 8;
                                    this.i = l2;
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
                        synchronized (UCPacket.class) {
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

        public static UCPacket l() {
            return k;
        }

        public static Parser<UCPacket> m() {
            return k.M();
        }
    }

    public static final class UCExchange extends GeneratedMessageLite<UCExchange, Builder> implements UCExchangeOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11242a = 1;
        public static final int b = 2;
        public static final int c = 3;
        /* access modifiers changed from: private */
        public static final UCExchange i = new UCExchange();
        private static volatile Parser<UCExchange> j;
        private int d;
        private FeInfo e;
        private UCPacket f;
        private String g = "";
        private byte h = -1;

        private UCExchange() {
        }

        public boolean a() {
            return (this.d & 1) == 1;
        }

        public FeInfo b() {
            return this.e == null ? FeInfo.p() : this.e;
        }

        /* access modifiers changed from: private */
        public void a(FeInfo feInfo) {
            if (feInfo != null) {
                this.e = feInfo;
                this.d |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(FeInfo.Builder builder) {
            this.e = (FeInfo) builder.Z();
            this.d |= 1;
        }

        /* access modifiers changed from: private */
        public void b(FeInfo feInfo) {
            if (this.e == null || this.e == FeInfo.p()) {
                this.e = feInfo;
            } else {
                this.e = (FeInfo) ((FeInfo.Builder) FeInfo.a(this.e).b(feInfo)).Y();
            }
            this.d |= 1;
        }

        /* access modifiers changed from: private */
        public void m() {
            this.e = null;
            this.d &= -2;
        }

        public boolean c() {
            return (this.d & 2) == 2;
        }

        public UCPacket d() {
            return this.f == null ? UCPacket.l() : this.f;
        }

        /* access modifiers changed from: private */
        public void a(UCPacket uCPacket) {
            if (uCPacket != null) {
                this.f = uCPacket;
                this.d |= 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UCPacket.Builder builder) {
            this.f = (UCPacket) builder.Z();
            this.d |= 2;
        }

        /* access modifiers changed from: private */
        public void b(UCPacket uCPacket) {
            if (this.f == null || this.f == UCPacket.l()) {
                this.f = uCPacket;
            } else {
                this.f = (UCPacket) ((UCPacket.Builder) UCPacket.a(this.f).b(uCPacket)).Y();
            }
            this.d |= 2;
        }

        /* access modifiers changed from: private */
        public void n() {
            this.f = null;
            this.d &= -3;
        }

        public boolean e() {
            return (this.d & 4) == 4;
        }

        public String f() {
            return this.g;
        }

        public ByteString g() {
            return ByteString.copyFromUtf8(this.g);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.d |= 4;
                this.g = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void o() {
            this.d &= -5;
            this.g = i().f();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.d |= 4;
                this.g = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.d & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
            }
            if ((this.d & 2) == 2) {
                codedOutputStream.a(2, (MessageLite) d());
            }
            if ((this.d & 4) == 4) {
                codedOutputStream.a(3, f());
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
                i3 = 0 + CodedOutputStream.c(1, (MessageLite) b());
            }
            if ((this.d & 2) == 2) {
                i3 += CodedOutputStream.c(2, (MessageLite) d());
            }
            if ((this.d & 4) == 4) {
                i3 += CodedOutputStream.b(3, f());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UCExchange a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UCExchange) GeneratedMessageLite.a(i, byteString);
        }

        public static UCExchange a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCExchange) GeneratedMessageLite.a(i, byteString, extensionRegistryLite);
        }

        public static UCExchange a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UCExchange) GeneratedMessageLite.a(i, bArr);
        }

        public static UCExchange a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCExchange) GeneratedMessageLite.a(i, bArr, extensionRegistryLite);
        }

        public static UCExchange a(InputStream inputStream) throws IOException {
            return (UCExchange) GeneratedMessageLite.a(i, inputStream);
        }

        public static UCExchange a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCExchange) GeneratedMessageLite.a(i, inputStream, extensionRegistryLite);
        }

        public static UCExchange b(InputStream inputStream) throws IOException {
            return (UCExchange) b(i, inputStream);
        }

        public static UCExchange b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCExchange) b(i, inputStream, extensionRegistryLite);
        }

        public static UCExchange a(CodedInputStream codedInputStream) throws IOException {
            return (UCExchange) GeneratedMessageLite.b(i, codedInputStream);
        }

        public static UCExchange a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCExchange) GeneratedMessageLite.b(i, codedInputStream, extensionRegistryLite);
        }

        public static Builder h() {
            return (Builder) i.Y();
        }

        public static Builder a(UCExchange uCExchange) {
            return (Builder) ((Builder) i.Y()).b(uCExchange);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UCExchange, Builder> implements UCExchangeOrBuilder {
            private Builder() {
                super(UCExchange.i);
            }

            public boolean a() {
                return ((UCExchange) this.f11310a).a();
            }

            public FeInfo b() {
                return ((UCExchange) this.f11310a).b();
            }

            public Builder a(FeInfo feInfo) {
                S();
                ((UCExchange) this.f11310a).a(feInfo);
                return this;
            }

            public Builder a(FeInfo.Builder builder) {
                S();
                ((UCExchange) this.f11310a).a(builder);
                return this;
            }

            public Builder b(FeInfo feInfo) {
                S();
                ((UCExchange) this.f11310a).b(feInfo);
                return this;
            }

            public Builder h() {
                S();
                ((UCExchange) this.f11310a).m();
                return this;
            }

            public boolean c() {
                return ((UCExchange) this.f11310a).c();
            }

            public UCPacket d() {
                return ((UCExchange) this.f11310a).d();
            }

            public Builder a(UCPacket uCPacket) {
                S();
                ((UCExchange) this.f11310a).a(uCPacket);
                return this;
            }

            public Builder a(UCPacket.Builder builder) {
                S();
                ((UCExchange) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UCPacket uCPacket) {
                S();
                ((UCExchange) this.f11310a).b(uCPacket);
                return this;
            }

            public Builder i() {
                S();
                ((UCExchange) this.f11310a).n();
                return this;
            }

            public boolean e() {
                return ((UCExchange) this.f11310a).e();
            }

            public String f() {
                return ((UCExchange) this.f11310a).f();
            }

            public ByteString g() {
                return ((UCExchange) this.f11310a).g();
            }

            public Builder a(String str) {
                S();
                ((UCExchange) this.f11310a).a(str);
                return this;
            }

            public Builder j() {
                S();
                ((UCExchange) this.f11310a).o();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((UCExchange) this.f11310a).c(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UCExchange();
                case IS_INITIALIZED:
                    byte b2 = this.h;
                    if (b2 == 1) {
                        return i;
                    }
                    if (b2 == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!c()) {
                        if (booleanValue) {
                            this.h = 0;
                        }
                        return null;
                    } else if (!e()) {
                        if (booleanValue) {
                            this.h = 0;
                        }
                        return null;
                    } else if (!d().h_()) {
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
                    UCExchange uCExchange = (UCExchange) obj2;
                    this.e = (FeInfo) visitor.a(this.e, uCExchange.e);
                    this.f = (UCPacket) visitor.a(this.f, uCExchange.f);
                    this.g = visitor.a(e(), this.g, uCExchange.e(), uCExchange.g);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.d |= uCExchange.d;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    FeInfo.Builder builder = (this.d & 1) == 1 ? (FeInfo.Builder) this.e.Y() : null;
                                    this.e = (FeInfo) codedInputStream.a(FeInfo.q(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.e);
                                        this.e = (FeInfo) builder.Y();
                                    }
                                    this.d |= 1;
                                } else if (a2 == 18) {
                                    UCPacket.Builder builder2 = (this.d & 2) == 2 ? (UCPacket.Builder) this.f.Y() : null;
                                    this.f = (UCPacket) codedInputStream.a(UCPacket.m(), extensionRegistryLite);
                                    if (builder2 != null) {
                                        builder2.b(this.f);
                                        this.f = (UCPacket) builder2.Y();
                                    }
                                    this.d |= 2;
                                } else if (a2 == 26) {
                                    String l = codedInputStream.l();
                                    this.d |= 4;
                                    this.g = l;
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
                        synchronized (UCExchange.class) {
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

        public static UCExchange i() {
            return i;
        }

        public static Parser<UCExchange> j() {
            return i.M();
        }
    }

    public static final class UCJoin extends GeneratedMessageLite<UCJoin, Builder> implements UCJoinOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11244a = 1;
        /* access modifiers changed from: private */
        public static final UCJoin e = new UCJoin();
        private static volatile Parser<UCJoin> f;
        private int b;
        private UCGroup c;
        private byte d = -1;

        private UCJoin() {
        }

        public boolean a() {
            return (this.b & 1) == 1;
        }

        public UCGroup b() {
            return this.c == null ? UCGroup.f() : this.c;
        }

        /* access modifiers changed from: private */
        public void a(UCGroup uCGroup) {
            if (uCGroup != null) {
                this.c = uCGroup;
                this.b |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UCGroup.Builder builder) {
            this.c = (UCGroup) builder.Z();
            this.b |= 1;
        }

        /* access modifiers changed from: private */
        public void b(UCGroup uCGroup) {
            if (this.c == null || this.c == UCGroup.f()) {
                this.c = uCGroup;
            } else {
                this.c = (UCGroup) ((UCGroup.Builder) UCGroup.a(this.c).b(uCGroup)).Y();
            }
            this.b |= 1;
        }

        /* access modifiers changed from: private */
        public void g() {
            this.c = null;
            this.b &= -2;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.b & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
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
                i2 = 0 + CodedOutputStream.c(1, (MessageLite) b());
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UCJoin a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UCJoin) GeneratedMessageLite.a(e, byteString);
        }

        public static UCJoin a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCJoin) GeneratedMessageLite.a(e, byteString, extensionRegistryLite);
        }

        public static UCJoin a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UCJoin) GeneratedMessageLite.a(e, bArr);
        }

        public static UCJoin a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCJoin) GeneratedMessageLite.a(e, bArr, extensionRegistryLite);
        }

        public static UCJoin a(InputStream inputStream) throws IOException {
            return (UCJoin) GeneratedMessageLite.a(e, inputStream);
        }

        public static UCJoin a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCJoin) GeneratedMessageLite.a(e, inputStream, extensionRegistryLite);
        }

        public static UCJoin b(InputStream inputStream) throws IOException {
            return (UCJoin) b(e, inputStream);
        }

        public static UCJoin b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCJoin) b(e, inputStream, extensionRegistryLite);
        }

        public static UCJoin a(CodedInputStream codedInputStream) throws IOException {
            return (UCJoin) GeneratedMessageLite.b(e, codedInputStream);
        }

        public static UCJoin a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCJoin) GeneratedMessageLite.b(e, codedInputStream, extensionRegistryLite);
        }

        public static Builder c() {
            return (Builder) e.Y();
        }

        public static Builder a(UCJoin uCJoin) {
            return (Builder) ((Builder) e.Y()).b(uCJoin);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UCJoin, Builder> implements UCJoinOrBuilder {
            private Builder() {
                super(UCJoin.e);
            }

            public boolean a() {
                return ((UCJoin) this.f11310a).a();
            }

            public UCGroup b() {
                return ((UCJoin) this.f11310a).b();
            }

            public Builder a(UCGroup uCGroup) {
                S();
                ((UCJoin) this.f11310a).a(uCGroup);
                return this;
            }

            public Builder a(UCGroup.Builder builder) {
                S();
                ((UCJoin) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UCGroup uCGroup) {
                S();
                ((UCJoin) this.f11310a).b(uCGroup);
                return this;
            }

            public Builder c() {
                S();
                ((UCJoin) this.f11310a).g();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UCJoin();
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
                    UCJoin uCJoin = (UCJoin) obj2;
                    this.c = (UCGroup) visitor.a(this.c, uCJoin.c);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.b |= uCJoin.b;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    UCGroup.Builder builder = (this.b & 1) == 1 ? (UCGroup.Builder) this.c.Y() : null;
                                    this.c = (UCGroup) codedInputStream.a(UCGroup.g(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.c);
                                        this.c = (UCGroup) builder.Y();
                                    }
                                    this.b |= 1;
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
                        synchronized (UCJoin.class) {
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

        public static UCJoin d() {
            return e;
        }

        public static Parser<UCJoin> e() {
            return e.M();
        }
    }

    public static final class UCJoinResp extends GeneratedMessageLite<UCJoinResp, Builder> implements UCJoinRespOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11245a = 1;
        public static final int b = 2;
        public static final int c = 3;
        /* access modifiers changed from: private */
        public static final UCJoinResp i = new UCJoinResp();
        private static volatile Parser<UCJoinResp> j;
        private int d;
        private UCGroup e;
        private int f;
        private String g = "";
        private byte h = -1;

        private UCJoinResp() {
        }

        public boolean a() {
            return (this.d & 1) == 1;
        }

        public UCGroup b() {
            return this.e == null ? UCGroup.f() : this.e;
        }

        /* access modifiers changed from: private */
        public void a(UCGroup uCGroup) {
            if (uCGroup != null) {
                this.e = uCGroup;
                this.d |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UCGroup.Builder builder) {
            this.e = (UCGroup) builder.Z();
            this.d |= 1;
        }

        /* access modifiers changed from: private */
        public void b(UCGroup uCGroup) {
            if (this.e == null || this.e == UCGroup.f()) {
                this.e = uCGroup;
            } else {
                this.e = (UCGroup) ((UCGroup.Builder) UCGroup.a(this.e).b(uCGroup)).Y();
            }
            this.d |= 1;
        }

        /* access modifiers changed from: private */
        public void m() {
            this.e = null;
            this.d &= -2;
        }

        public boolean c() {
            return (this.d & 2) == 2;
        }

        public int d() {
            return this.f;
        }

        /* access modifiers changed from: private */
        public void a(int i2) {
            this.d |= 2;
            this.f = i2;
        }

        /* access modifiers changed from: private */
        public void n() {
            this.d &= -3;
            this.f = 0;
        }

        public boolean e() {
            return (this.d & 4) == 4;
        }

        public String f() {
            return this.g;
        }

        public ByteString g() {
            return ByteString.copyFromUtf8(this.g);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.d |= 4;
                this.g = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void o() {
            this.d &= -5;
            this.g = i().f();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.d |= 4;
                this.g = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.d & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
            }
            if ((this.d & 2) == 2) {
                codedOutputStream.b(2, this.f);
            }
            if ((this.d & 4) == 4) {
                codedOutputStream.a(3, f());
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
                i3 = 0 + CodedOutputStream.c(1, (MessageLite) b());
            }
            if ((this.d & 2) == 2) {
                i3 += CodedOutputStream.h(2, this.f);
            }
            if ((this.d & 4) == 4) {
                i3 += CodedOutputStream.b(3, f());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UCJoinResp a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UCJoinResp) GeneratedMessageLite.a(i, byteString);
        }

        public static UCJoinResp a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCJoinResp) GeneratedMessageLite.a(i, byteString, extensionRegistryLite);
        }

        public static UCJoinResp a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UCJoinResp) GeneratedMessageLite.a(i, bArr);
        }

        public static UCJoinResp a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCJoinResp) GeneratedMessageLite.a(i, bArr, extensionRegistryLite);
        }

        public static UCJoinResp a(InputStream inputStream) throws IOException {
            return (UCJoinResp) GeneratedMessageLite.a(i, inputStream);
        }

        public static UCJoinResp a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCJoinResp) GeneratedMessageLite.a(i, inputStream, extensionRegistryLite);
        }

        public static UCJoinResp b(InputStream inputStream) throws IOException {
            return (UCJoinResp) b(i, inputStream);
        }

        public static UCJoinResp b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCJoinResp) b(i, inputStream, extensionRegistryLite);
        }

        public static UCJoinResp a(CodedInputStream codedInputStream) throws IOException {
            return (UCJoinResp) GeneratedMessageLite.b(i, codedInputStream);
        }

        public static UCJoinResp a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCJoinResp) GeneratedMessageLite.b(i, codedInputStream, extensionRegistryLite);
        }

        public static Builder h() {
            return (Builder) i.Y();
        }

        public static Builder a(UCJoinResp uCJoinResp) {
            return (Builder) ((Builder) i.Y()).b(uCJoinResp);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UCJoinResp, Builder> implements UCJoinRespOrBuilder {
            private Builder() {
                super(UCJoinResp.i);
            }

            public boolean a() {
                return ((UCJoinResp) this.f11310a).a();
            }

            public UCGroup b() {
                return ((UCJoinResp) this.f11310a).b();
            }

            public Builder a(UCGroup uCGroup) {
                S();
                ((UCJoinResp) this.f11310a).a(uCGroup);
                return this;
            }

            public Builder a(UCGroup.Builder builder) {
                S();
                ((UCJoinResp) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UCGroup uCGroup) {
                S();
                ((UCJoinResp) this.f11310a).b(uCGroup);
                return this;
            }

            public Builder h() {
                S();
                ((UCJoinResp) this.f11310a).m();
                return this;
            }

            public boolean c() {
                return ((UCJoinResp) this.f11310a).c();
            }

            public int d() {
                return ((UCJoinResp) this.f11310a).d();
            }

            public Builder a(int i) {
                S();
                ((UCJoinResp) this.f11310a).a(i);
                return this;
            }

            public Builder i() {
                S();
                ((UCJoinResp) this.f11310a).n();
                return this;
            }

            public boolean e() {
                return ((UCJoinResp) this.f11310a).e();
            }

            public String f() {
                return ((UCJoinResp) this.f11310a).f();
            }

            public ByteString g() {
                return ((UCJoinResp) this.f11310a).g();
            }

            public Builder a(String str) {
                S();
                ((UCJoinResp) this.f11310a).a(str);
                return this;
            }

            public Builder j() {
                S();
                ((UCJoinResp) this.f11310a).o();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((UCJoinResp) this.f11310a).c(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UCJoinResp();
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
                    UCJoinResp uCJoinResp = (UCJoinResp) obj2;
                    this.e = (UCGroup) visitor.a(this.e, uCJoinResp.e);
                    this.f = visitor.a(c(), this.f, uCJoinResp.c(), uCJoinResp.f);
                    this.g = visitor.a(e(), this.g, uCJoinResp.e(), uCJoinResp.g);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.d |= uCJoinResp.d;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    UCGroup.Builder builder = (this.d & 1) == 1 ? (UCGroup.Builder) this.e.Y() : null;
                                    this.e = (UCGroup) codedInputStream.a(UCGroup.g(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.e);
                                        this.e = (UCGroup) builder.Y();
                                    }
                                    this.d |= 1;
                                } else if (a2 == 16) {
                                    this.d |= 2;
                                    this.f = codedInputStream.h();
                                } else if (a2 == 26) {
                                    String l = codedInputStream.l();
                                    this.d |= 4;
                                    this.g = l;
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
                        synchronized (UCJoinResp.class) {
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

        public static UCJoinResp i() {
            return i;
        }

        public static Parser<UCJoinResp> j() {
            return i.M();
        }
    }

    public static final class UCQuit extends GeneratedMessageLite<UCQuit, Builder> implements UCQuitOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11253a = 1;
        /* access modifiers changed from: private */
        public static final UCQuit e = new UCQuit();
        private static volatile Parser<UCQuit> f;
        private int b;
        private UCGroup c;
        private byte d = -1;

        private UCQuit() {
        }

        public boolean a() {
            return (this.b & 1) == 1;
        }

        public UCGroup b() {
            return this.c == null ? UCGroup.f() : this.c;
        }

        /* access modifiers changed from: private */
        public void a(UCGroup uCGroup) {
            if (uCGroup != null) {
                this.c = uCGroup;
                this.b |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UCGroup.Builder builder) {
            this.c = (UCGroup) builder.Z();
            this.b |= 1;
        }

        /* access modifiers changed from: private */
        public void b(UCGroup uCGroup) {
            if (this.c == null || this.c == UCGroup.f()) {
                this.c = uCGroup;
            } else {
                this.c = (UCGroup) ((UCGroup.Builder) UCGroup.a(this.c).b(uCGroup)).Y();
            }
            this.b |= 1;
        }

        /* access modifiers changed from: private */
        public void g() {
            this.c = null;
            this.b &= -2;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.b & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
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
                i2 = 0 + CodedOutputStream.c(1, (MessageLite) b());
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UCQuit a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UCQuit) GeneratedMessageLite.a(e, byteString);
        }

        public static UCQuit a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCQuit) GeneratedMessageLite.a(e, byteString, extensionRegistryLite);
        }

        public static UCQuit a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UCQuit) GeneratedMessageLite.a(e, bArr);
        }

        public static UCQuit a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCQuit) GeneratedMessageLite.a(e, bArr, extensionRegistryLite);
        }

        public static UCQuit a(InputStream inputStream) throws IOException {
            return (UCQuit) GeneratedMessageLite.a(e, inputStream);
        }

        public static UCQuit a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCQuit) GeneratedMessageLite.a(e, inputStream, extensionRegistryLite);
        }

        public static UCQuit b(InputStream inputStream) throws IOException {
            return (UCQuit) b(e, inputStream);
        }

        public static UCQuit b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCQuit) b(e, inputStream, extensionRegistryLite);
        }

        public static UCQuit a(CodedInputStream codedInputStream) throws IOException {
            return (UCQuit) GeneratedMessageLite.b(e, codedInputStream);
        }

        public static UCQuit a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCQuit) GeneratedMessageLite.b(e, codedInputStream, extensionRegistryLite);
        }

        public static Builder c() {
            return (Builder) e.Y();
        }

        public static Builder a(UCQuit uCQuit) {
            return (Builder) ((Builder) e.Y()).b(uCQuit);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UCQuit, Builder> implements UCQuitOrBuilder {
            private Builder() {
                super(UCQuit.e);
            }

            public boolean a() {
                return ((UCQuit) this.f11310a).a();
            }

            public UCGroup b() {
                return ((UCQuit) this.f11310a).b();
            }

            public Builder a(UCGroup uCGroup) {
                S();
                ((UCQuit) this.f11310a).a(uCGroup);
                return this;
            }

            public Builder a(UCGroup.Builder builder) {
                S();
                ((UCQuit) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UCGroup uCGroup) {
                S();
                ((UCQuit) this.f11310a).b(uCGroup);
                return this;
            }

            public Builder c() {
                S();
                ((UCQuit) this.f11310a).g();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UCQuit();
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
                    UCQuit uCQuit = (UCQuit) obj2;
                    this.c = (UCGroup) visitor.a(this.c, uCQuit.c);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.b |= uCQuit.b;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    UCGroup.Builder builder = (this.b & 1) == 1 ? (UCGroup.Builder) this.c.Y() : null;
                                    this.c = (UCGroup) codedInputStream.a(UCGroup.g(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.c);
                                        this.c = (UCGroup) builder.Y();
                                    }
                                    this.b |= 1;
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
                        synchronized (UCQuit.class) {
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

        public static UCQuit d() {
            return e;
        }

        public static Parser<UCQuit> e() {
            return e.M();
        }
    }

    public static final class UCQuitResp extends GeneratedMessageLite<UCQuitResp, Builder> implements UCQuitRespOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11254a = 1;
        public static final int b = 2;
        public static final int c = 3;
        /* access modifiers changed from: private */
        public static final UCQuitResp i = new UCQuitResp();
        private static volatile Parser<UCQuitResp> j;
        private int d;
        private UCGroup e;
        private int f;
        private String g = "";
        private byte h = -1;

        private UCQuitResp() {
        }

        public boolean a() {
            return (this.d & 1) == 1;
        }

        public UCGroup b() {
            return this.e == null ? UCGroup.f() : this.e;
        }

        /* access modifiers changed from: private */
        public void a(UCGroup uCGroup) {
            if (uCGroup != null) {
                this.e = uCGroup;
                this.d |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UCGroup.Builder builder) {
            this.e = (UCGroup) builder.Z();
            this.d |= 1;
        }

        /* access modifiers changed from: private */
        public void b(UCGroup uCGroup) {
            if (this.e == null || this.e == UCGroup.f()) {
                this.e = uCGroup;
            } else {
                this.e = (UCGroup) ((UCGroup.Builder) UCGroup.a(this.e).b(uCGroup)).Y();
            }
            this.d |= 1;
        }

        /* access modifiers changed from: private */
        public void m() {
            this.e = null;
            this.d &= -2;
        }

        public boolean c() {
            return (this.d & 2) == 2;
        }

        public int d() {
            return this.f;
        }

        /* access modifiers changed from: private */
        public void a(int i2) {
            this.d |= 2;
            this.f = i2;
        }

        /* access modifiers changed from: private */
        public void n() {
            this.d &= -3;
            this.f = 0;
        }

        public boolean e() {
            return (this.d & 4) == 4;
        }

        public String f() {
            return this.g;
        }

        public ByteString g() {
            return ByteString.copyFromUtf8(this.g);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.d |= 4;
                this.g = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void o() {
            this.d &= -5;
            this.g = i().f();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.d |= 4;
                this.g = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.d & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
            }
            if ((this.d & 2) == 2) {
                codedOutputStream.b(2, this.f);
            }
            if ((this.d & 4) == 4) {
                codedOutputStream.a(3, f());
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
                i3 = 0 + CodedOutputStream.c(1, (MessageLite) b());
            }
            if ((this.d & 2) == 2) {
                i3 += CodedOutputStream.h(2, this.f);
            }
            if ((this.d & 4) == 4) {
                i3 += CodedOutputStream.b(3, f());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UCQuitResp a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UCQuitResp) GeneratedMessageLite.a(i, byteString);
        }

        public static UCQuitResp a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCQuitResp) GeneratedMessageLite.a(i, byteString, extensionRegistryLite);
        }

        public static UCQuitResp a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UCQuitResp) GeneratedMessageLite.a(i, bArr);
        }

        public static UCQuitResp a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCQuitResp) GeneratedMessageLite.a(i, bArr, extensionRegistryLite);
        }

        public static UCQuitResp a(InputStream inputStream) throws IOException {
            return (UCQuitResp) GeneratedMessageLite.a(i, inputStream);
        }

        public static UCQuitResp a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCQuitResp) GeneratedMessageLite.a(i, inputStream, extensionRegistryLite);
        }

        public static UCQuitResp b(InputStream inputStream) throws IOException {
            return (UCQuitResp) b(i, inputStream);
        }

        public static UCQuitResp b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCQuitResp) b(i, inputStream, extensionRegistryLite);
        }

        public static UCQuitResp a(CodedInputStream codedInputStream) throws IOException {
            return (UCQuitResp) GeneratedMessageLite.b(i, codedInputStream);
        }

        public static UCQuitResp a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCQuitResp) GeneratedMessageLite.b(i, codedInputStream, extensionRegistryLite);
        }

        public static Builder h() {
            return (Builder) i.Y();
        }

        public static Builder a(UCQuitResp uCQuitResp) {
            return (Builder) ((Builder) i.Y()).b(uCQuitResp);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UCQuitResp, Builder> implements UCQuitRespOrBuilder {
            private Builder() {
                super(UCQuitResp.i);
            }

            public boolean a() {
                return ((UCQuitResp) this.f11310a).a();
            }

            public UCGroup b() {
                return ((UCQuitResp) this.f11310a).b();
            }

            public Builder a(UCGroup uCGroup) {
                S();
                ((UCQuitResp) this.f11310a).a(uCGroup);
                return this;
            }

            public Builder a(UCGroup.Builder builder) {
                S();
                ((UCQuitResp) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UCGroup uCGroup) {
                S();
                ((UCQuitResp) this.f11310a).b(uCGroup);
                return this;
            }

            public Builder h() {
                S();
                ((UCQuitResp) this.f11310a).m();
                return this;
            }

            public boolean c() {
                return ((UCQuitResp) this.f11310a).c();
            }

            public int d() {
                return ((UCQuitResp) this.f11310a).d();
            }

            public Builder a(int i) {
                S();
                ((UCQuitResp) this.f11310a).a(i);
                return this;
            }

            public Builder i() {
                S();
                ((UCQuitResp) this.f11310a).n();
                return this;
            }

            public boolean e() {
                return ((UCQuitResp) this.f11310a).e();
            }

            public String f() {
                return ((UCQuitResp) this.f11310a).f();
            }

            public ByteString g() {
                return ((UCQuitResp) this.f11310a).g();
            }

            public Builder a(String str) {
                S();
                ((UCQuitResp) this.f11310a).a(str);
                return this;
            }

            public Builder j() {
                S();
                ((UCQuitResp) this.f11310a).o();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((UCQuitResp) this.f11310a).c(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UCQuitResp();
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
                    UCQuitResp uCQuitResp = (UCQuitResp) obj2;
                    this.e = (UCGroup) visitor.a(this.e, uCQuitResp.e);
                    this.f = visitor.a(c(), this.f, uCQuitResp.c(), uCQuitResp.f);
                    this.g = visitor.a(e(), this.g, uCQuitResp.e(), uCQuitResp.g);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.d |= uCQuitResp.d;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    UCGroup.Builder builder = (this.d & 1) == 1 ? (UCGroup.Builder) this.e.Y() : null;
                                    this.e = (UCGroup) codedInputStream.a(UCGroup.g(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.e);
                                        this.e = (UCGroup) builder.Y();
                                    }
                                    this.d |= 1;
                                } else if (a2 == 16) {
                                    this.d |= 2;
                                    this.f = codedInputStream.h();
                                } else if (a2 == 26) {
                                    String l = codedInputStream.l();
                                    this.d |= 4;
                                    this.g = l;
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
                        synchronized (UCQuitResp.class) {
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

        public static UCQuitResp i() {
            return i;
        }

        public static Parser<UCQuitResp> j() {
            return i.M();
        }
    }

    public static final class UCPing extends GeneratedMessageLite<UCPing, Builder> implements UCPingOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11249a = 1;
        /* access modifiers changed from: private */
        public static final UCPing c = new UCPing();
        private static volatile Parser<UCPing> d;
        private Internal.ProtobufList<UCGroup> b = X();

        private UCPing() {
        }

        public List<UCGroup> a() {
            return this.b;
        }

        public List<? extends UCGroupOrBuilder> b() {
            return this.b;
        }

        public int c() {
            return this.b.size();
        }

        public UCGroup a(int i) {
            return (UCGroup) this.b.get(i);
        }

        public UCGroupOrBuilder b(int i) {
            return (UCGroupOrBuilder) this.b.get(i);
        }

        private void h() {
            if (!this.b.a()) {
                this.b = GeneratedMessageLite.a(this.b);
            }
        }

        /* access modifiers changed from: private */
        public void a(int i, UCGroup uCGroup) {
            if (uCGroup != null) {
                h();
                this.b.set(i, uCGroup);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(int i, UCGroup.Builder builder) {
            h();
            this.b.set(i, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(UCGroup uCGroup) {
            if (uCGroup != null) {
                h();
                this.b.add(uCGroup);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void b(int i, UCGroup uCGroup) {
            if (uCGroup != null) {
                h();
                this.b.add(i, uCGroup);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UCGroup.Builder builder) {
            h();
            this.b.add(builder.Z());
        }

        /* access modifiers changed from: private */
        public void b(int i, UCGroup.Builder builder) {
            h();
            this.b.add(i, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(Iterable<? extends UCGroup> iterable) {
            h();
            AbstractMessageLite.a(iterable, this.b);
        }

        /* access modifiers changed from: private */
        public void i() {
            this.b = X();
        }

        /* access modifiers changed from: private */
        public void c(int i) {
            h();
            this.b.remove(i);
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.b.size(); i++) {
                codedOutputStream.a(1, (MessageLite) this.b.get(i));
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i = this.o;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.b.size(); i3++) {
                i2 += CodedOutputStream.c(1, (MessageLite) this.b.get(i3));
            }
            int e = i2 + this.n.e();
            this.o = e;
            return e;
        }

        public static UCPing a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UCPing) GeneratedMessageLite.a(c, byteString);
        }

        public static UCPing a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCPing) GeneratedMessageLite.a(c, byteString, extensionRegistryLite);
        }

        public static UCPing a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UCPing) GeneratedMessageLite.a(c, bArr);
        }

        public static UCPing a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCPing) GeneratedMessageLite.a(c, bArr, extensionRegistryLite);
        }

        public static UCPing a(InputStream inputStream) throws IOException {
            return (UCPing) GeneratedMessageLite.a(c, inputStream);
        }

        public static UCPing a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCPing) GeneratedMessageLite.a(c, inputStream, extensionRegistryLite);
        }

        public static UCPing b(InputStream inputStream) throws IOException {
            return (UCPing) b(c, inputStream);
        }

        public static UCPing b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCPing) b(c, inputStream, extensionRegistryLite);
        }

        public static UCPing a(CodedInputStream codedInputStream) throws IOException {
            return (UCPing) GeneratedMessageLite.b(c, codedInputStream);
        }

        public static UCPing a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCPing) GeneratedMessageLite.b(c, codedInputStream, extensionRegistryLite);
        }

        public static Builder d() {
            return (Builder) c.Y();
        }

        public static Builder a(UCPing uCPing) {
            return (Builder) ((Builder) c.Y()).b(uCPing);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UCPing, Builder> implements UCPingOrBuilder {
            private Builder() {
                super(UCPing.c);
            }

            public List<UCGroup> a() {
                return Collections.unmodifiableList(((UCPing) this.f11310a).a());
            }

            public int c() {
                return ((UCPing) this.f11310a).c();
            }

            public UCGroup a(int i) {
                return ((UCPing) this.f11310a).a(i);
            }

            public Builder a(int i, UCGroup uCGroup) {
                S();
                ((UCPing) this.f11310a).a(i, uCGroup);
                return this;
            }

            public Builder a(int i, UCGroup.Builder builder) {
                S();
                ((UCPing) this.f11310a).a(i, builder);
                return this;
            }

            public Builder a(UCGroup uCGroup) {
                S();
                ((UCPing) this.f11310a).a(uCGroup);
                return this;
            }

            public Builder b(int i, UCGroup uCGroup) {
                S();
                ((UCPing) this.f11310a).b(i, uCGroup);
                return this;
            }

            public Builder a(UCGroup.Builder builder) {
                S();
                ((UCPing) this.f11310a).a(builder);
                return this;
            }

            public Builder b(int i, UCGroup.Builder builder) {
                S();
                ((UCPing) this.f11310a).b(i, builder);
                return this;
            }

            public Builder a(Iterable<? extends UCGroup> iterable) {
                S();
                ((UCPing) this.f11310a).a(iterable);
                return this;
            }

            public Builder b() {
                S();
                ((UCPing) this.f11310a).i();
                return this;
            }

            public Builder b(int i) {
                S();
                ((UCPing) this.f11310a).c(i);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UCPing();
                case IS_INITIALIZED:
                    return c;
                case MAKE_IMMUTABLE:
                    this.b.b();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    this.b = ((GeneratedMessageLite.Visitor) obj).a(this.b, ((UCPing) obj2).b);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.f11318a;
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
                                    if (!this.b.a()) {
                                        this.b = GeneratedMessageLite.a(this.b);
                                    }
                                    this.b.add(codedInputStream.a(UCGroup.g(), extensionRegistryLite));
                                } else if (!a(a2, codedInputStream)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case GET_DEFAULT_INSTANCE:
                    break;
                case GET_PARSER:
                    if (d == null) {
                        synchronized (UCPing.class) {
                            if (d == null) {
                                d = new GeneratedMessageLite.DefaultInstanceBasedParser(c);
                            }
                        }
                    }
                    return d;
                default:
                    throw new UnsupportedOperationException();
            }
            return c;
        }

        static {
            c.P();
        }

        public static UCPing e() {
            return c;
        }

        public static Parser<UCPing> f() {
            return c.M();
        }
    }

    public static final class UCMessage extends GeneratedMessageLite<UCMessage, Builder> implements UCMessageOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11246a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        public static final int h = 8;
        /* access modifiers changed from: private */
        public static final UCMessage v = new UCMessage();
        private static volatile Parser<UCMessage> w;
        private int i;
        private UCGroup j;
        private ByteString k = ByteString.EMPTY;
        private long l;
        private boolean p;
        private MIMCUser q;
        private long r;
        private String s = "";
        private String t = "";
        private byte u = -1;

        private UCMessage() {
        }

        public boolean a() {
            return (this.i & 1) == 1;
        }

        public UCGroup b() {
            return this.j == null ? UCGroup.f() : this.j;
        }

        /* access modifiers changed from: private */
        public void a(UCGroup uCGroup) {
            if (uCGroup != null) {
                this.j = uCGroup;
                this.i |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UCGroup.Builder builder) {
            this.j = (UCGroup) builder.Z();
            this.i |= 1;
        }

        /* access modifiers changed from: private */
        public void b(UCGroup uCGroup) {
            if (this.j == null || this.j == UCGroup.f()) {
                this.j = uCGroup;
            } else {
                this.j = (UCGroup) ((UCGroup.Builder) UCGroup.a(this.j).b(uCGroup)).Y();
            }
            this.i |= 1;
        }

        /* access modifiers changed from: private */
        public void x() {
            this.j = null;
            this.i &= -2;
        }

        public boolean c() {
            return (this.i & 2) == 2;
        }

        public ByteString d() {
            return this.k;
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.i |= 2;
                this.k = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void y() {
            this.i &= -3;
            this.k = u().d();
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

        public boolean h() {
            return this.p;
        }

        /* access modifiers changed from: private */
        public void a(boolean z) {
            this.i |= 8;
            this.p = z;
        }

        /* access modifiers changed from: private */
        public void A() {
            this.i &= -9;
            this.p = false;
        }

        public boolean i() {
            return (this.i & 16) == 16;
        }

        public MIMCUser j() {
            return this.q == null ? MIMCUser.m() : this.q;
        }

        /* access modifiers changed from: private */
        public void a(MIMCUser mIMCUser) {
            if (mIMCUser != null) {
                this.q = mIMCUser;
                this.i |= 16;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(MIMCUser.Builder builder) {
            this.q = (MIMCUser) builder.Z();
            this.i |= 16;
        }

        /* access modifiers changed from: private */
        public void b(MIMCUser mIMCUser) {
            if (this.q == null || this.q == MIMCUser.m()) {
                this.q = mIMCUser;
            } else {
                this.q = (MIMCUser) ((MIMCUser.Builder) MIMCUser.a(this.q).b(mIMCUser)).Y();
            }
            this.i |= 16;
        }

        /* access modifiers changed from: private */
        public void B() {
            this.q = null;
            this.i &= -17;
        }

        public boolean l() {
            return (this.i & 32) == 32;
        }

        public long m() {
            return this.r;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.i |= 32;
            this.r = j2;
        }

        /* access modifiers changed from: private */
        public void C() {
            this.i &= -33;
            this.r = 0;
        }

        public boolean n() {
            return (this.i & 64) == 64;
        }

        public String o() {
            return this.s;
        }

        public ByteString p() {
            return ByteString.copyFromUtf8(this.s);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.i |= 64;
                this.s = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void D() {
            this.i &= -65;
            this.s = u().o();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.i |= 64;
                this.s = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
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
                codedOutputStream.a(1, (MessageLite) b());
            }
            if ((this.i & 2) == 2) {
                codedOutputStream.a(2, this.k);
            }
            if ((this.i & 4) == 4) {
                codedOutputStream.a(3, this.l);
            }
            if ((this.i & 8) == 8) {
                codedOutputStream.a(4, this.p);
            }
            if ((this.i & 16) == 16) {
                codedOutputStream.a(5, (MessageLite) j());
            }
            if ((this.i & 32) == 32) {
                codedOutputStream.a(6, this.r);
            }
            if ((this.i & 64) == 64) {
                codedOutputStream.a(7, o());
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
                i3 = 0 + CodedOutputStream.c(1, (MessageLite) b());
            }
            if ((this.i & 2) == 2) {
                i3 += CodedOutputStream.c(2, this.k);
            }
            if ((this.i & 4) == 4) {
                i3 += CodedOutputStream.f(3, this.l);
            }
            if ((this.i & 8) == 8) {
                i3 += CodedOutputStream.b(4, this.p);
            }
            if ((this.i & 16) == 16) {
                i3 += CodedOutputStream.c(5, (MessageLite) j());
            }
            if ((this.i & 32) == 32) {
                i3 += CodedOutputStream.f(6, this.r);
            }
            if ((this.i & 64) == 64) {
                i3 += CodedOutputStream.b(7, o());
            }
            if ((this.i & 128) == 128) {
                i3 += CodedOutputStream.b(8, r());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UCMessage a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UCMessage) GeneratedMessageLite.a(v, byteString);
        }

        public static UCMessage a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCMessage) GeneratedMessageLite.a(v, byteString, extensionRegistryLite);
        }

        public static UCMessage a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UCMessage) GeneratedMessageLite.a(v, bArr);
        }

        public static UCMessage a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCMessage) GeneratedMessageLite.a(v, bArr, extensionRegistryLite);
        }

        public static UCMessage a(InputStream inputStream) throws IOException {
            return (UCMessage) GeneratedMessageLite.a(v, inputStream);
        }

        public static UCMessage a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCMessage) GeneratedMessageLite.a(v, inputStream, extensionRegistryLite);
        }

        public static UCMessage b(InputStream inputStream) throws IOException {
            return (UCMessage) b(v, inputStream);
        }

        public static UCMessage b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCMessage) b(v, inputStream, extensionRegistryLite);
        }

        public static UCMessage a(CodedInputStream codedInputStream) throws IOException {
            return (UCMessage) GeneratedMessageLite.b(v, codedInputStream);
        }

        public static UCMessage a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCMessage) GeneratedMessageLite.b(v, codedInputStream, extensionRegistryLite);
        }

        public static Builder t() {
            return (Builder) v.Y();
        }

        public static Builder a(UCMessage uCMessage) {
            return (Builder) ((Builder) v.Y()).b(uCMessage);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UCMessage, Builder> implements UCMessageOrBuilder {
            private Builder() {
                super(UCMessage.v);
            }

            public boolean a() {
                return ((UCMessage) this.f11310a).a();
            }

            public UCGroup b() {
                return ((UCMessage) this.f11310a).b();
            }

            public Builder a(UCGroup uCGroup) {
                S();
                ((UCMessage) this.f11310a).a(uCGroup);
                return this;
            }

            public Builder a(UCGroup.Builder builder) {
                S();
                ((UCMessage) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UCGroup uCGroup) {
                S();
                ((UCMessage) this.f11310a).b(uCGroup);
                return this;
            }

            public Builder k() {
                S();
                ((UCMessage) this.f11310a).x();
                return this;
            }

            public boolean c() {
                return ((UCMessage) this.f11310a).c();
            }

            public ByteString d() {
                return ((UCMessage) this.f11310a).d();
            }

            public Builder a(ByteString byteString) {
                S();
                ((UCMessage) this.f11310a).c(byteString);
                return this;
            }

            public Builder t() {
                S();
                ((UCMessage) this.f11310a).y();
                return this;
            }

            public boolean e() {
                return ((UCMessage) this.f11310a).e();
            }

            public long f() {
                return ((UCMessage) this.f11310a).f();
            }

            public Builder a(long j) {
                S();
                ((UCMessage) this.f11310a).a(j);
                return this;
            }

            public Builder u() {
                S();
                ((UCMessage) this.f11310a).z();
                return this;
            }

            public boolean g() {
                return ((UCMessage) this.f11310a).g();
            }

            public boolean h() {
                return ((UCMessage) this.f11310a).h();
            }

            public Builder a(boolean z) {
                S();
                ((UCMessage) this.f11310a).a(z);
                return this;
            }

            public Builder v() {
                S();
                ((UCMessage) this.f11310a).A();
                return this;
            }

            public boolean i() {
                return ((UCMessage) this.f11310a).i();
            }

            public MIMCUser j() {
                return ((UCMessage) this.f11310a).j();
            }

            public Builder a(MIMCUser mIMCUser) {
                S();
                ((UCMessage) this.f11310a).a(mIMCUser);
                return this;
            }

            public Builder a(MIMCUser.Builder builder) {
                S();
                ((UCMessage) this.f11310a).a(builder);
                return this;
            }

            public Builder b(MIMCUser mIMCUser) {
                S();
                ((UCMessage) this.f11310a).b(mIMCUser);
                return this;
            }

            public Builder w() {
                S();
                ((UCMessage) this.f11310a).B();
                return this;
            }

            public boolean l() {
                return ((UCMessage) this.f11310a).l();
            }

            public long m() {
                return ((UCMessage) this.f11310a).m();
            }

            public Builder b(long j) {
                S();
                ((UCMessage) this.f11310a).b(j);
                return this;
            }

            public Builder x() {
                S();
                ((UCMessage) this.f11310a).C();
                return this;
            }

            public boolean n() {
                return ((UCMessage) this.f11310a).n();
            }

            public String o() {
                return ((UCMessage) this.f11310a).o();
            }

            public ByteString p() {
                return ((UCMessage) this.f11310a).p();
            }

            public Builder a(String str) {
                S();
                ((UCMessage) this.f11310a).a(str);
                return this;
            }

            public Builder y() {
                S();
                ((UCMessage) this.f11310a).D();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((UCMessage) this.f11310a).d(byteString);
                return this;
            }

            public boolean q() {
                return ((UCMessage) this.f11310a).q();
            }

            public String r() {
                return ((UCMessage) this.f11310a).r();
            }

            public ByteString s() {
                return ((UCMessage) this.f11310a).s();
            }

            public Builder b(String str) {
                S();
                ((UCMessage) this.f11310a).b(str);
                return this;
            }

            public Builder z() {
                S();
                ((UCMessage) this.f11310a).E();
                return this;
            }

            public Builder c(ByteString byteString) {
                S();
                ((UCMessage) this.f11310a).e(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UCMessage();
                case IS_INITIALIZED:
                    byte b2 = this.u;
                    if (b2 == 1) {
                        return v;
                    }
                    if (b2 == 0) {
                        return null;
                    }
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    if (!a()) {
                        if (booleanValue) {
                            this.u = 0;
                        }
                        return null;
                    } else if (!c()) {
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
                    UCMessage uCMessage = (UCMessage) obj2;
                    this.j = (UCGroup) visitor.a(this.j, uCMessage.j);
                    this.k = visitor.a(c(), this.k, uCMessage.c(), uCMessage.k);
                    this.l = visitor.a(e(), this.l, uCMessage.e(), uCMessage.l);
                    this.p = visitor.a(g(), this.p, uCMessage.g(), uCMessage.p);
                    this.q = (MIMCUser) visitor.a(this.q, uCMessage.q);
                    this.r = visitor.a(l(), this.r, uCMessage.l(), uCMessage.r);
                    this.s = visitor.a(n(), this.s, uCMessage.n(), uCMessage.s);
                    this.t = visitor.a(q(), this.t, uCMessage.q(), uCMessage.t);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.i |= uCMessage.i;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    UCGroup.Builder builder = (this.i & 1) == 1 ? (UCGroup.Builder) this.j.Y() : null;
                                    this.j = (UCGroup) codedInputStream.a(UCGroup.g(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.j);
                                        this.j = (UCGroup) builder.Y();
                                    }
                                    this.i |= 1;
                                } else if (a2 == 18) {
                                    this.i |= 2;
                                    this.k = codedInputStream.n();
                                } else if (a2 == 24) {
                                    this.i |= 4;
                                    this.l = codedInputStream.g();
                                } else if (a2 == 32) {
                                    this.i |= 8;
                                    this.p = codedInputStream.k();
                                } else if (a2 == 42) {
                                    MIMCUser.Builder builder2 = (this.i & 16) == 16 ? (MIMCUser.Builder) this.q.Y() : null;
                                    this.q = (MIMCUser) codedInputStream.a(MIMCUser.n(), extensionRegistryLite);
                                    if (builder2 != null) {
                                        builder2.b(this.q);
                                        this.q = (MIMCUser) builder2.Y();
                                    }
                                    this.i |= 16;
                                } else if (a2 == 48) {
                                    this.i |= 32;
                                    this.r = codedInputStream.g();
                                } else if (a2 == 58) {
                                    String l2 = codedInputStream.l();
                                    this.i |= 64;
                                    this.s = l2;
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
                        synchronized (UCMessage.class) {
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

        public static UCMessage u() {
            return v;
        }

        public static Parser<UCMessage> v() {
            return v.M();
        }
    }

    public static final class UCPushMessage extends GeneratedMessageLite<UCPushMessage, Builder> implements UCPushMessageOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11250a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        /* access modifiers changed from: private */
        public static final UCPushMessage r = new UCPushMessage();
        private static volatile Parser<UCPushMessage> s;
        private int g;
        private MIMCUser h;
        private Internal.ProtobufList<UCGroup> i = X();
        private Internal.ProtobufList<ByteString> j = X();
        private boolean k;
        private long l;
        private String p = "";
        private byte q = -1;

        private UCPushMessage() {
        }

        public boolean a() {
            return (this.g & 1) == 1;
        }

        public MIMCUser b() {
            return this.h == null ? MIMCUser.m() : this.h;
        }

        /* access modifiers changed from: private */
        public void a(MIMCUser mIMCUser) {
            if (mIMCUser != null) {
                this.h = mIMCUser;
                this.g |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(MIMCUser.Builder builder) {
            this.h = (MIMCUser) builder.Z();
            this.g |= 1;
        }

        /* access modifiers changed from: private */
        public void b(MIMCUser mIMCUser) {
            if (this.h == null || this.h == MIMCUser.m()) {
                this.h = mIMCUser;
            } else {
                this.h = (MIMCUser) ((MIMCUser.Builder) MIMCUser.a(this.h).b(mIMCUser)).Y();
            }
            this.g |= 1;
        }

        /* access modifiers changed from: private */
        public void t() {
            this.h = null;
            this.g &= -2;
        }

        public List<UCGroup> c() {
            return this.i;
        }

        public List<? extends UCGroupOrBuilder> d() {
            return this.i;
        }

        public int e() {
            return this.i.size();
        }

        public UCGroup a(int i2) {
            return (UCGroup) this.i.get(i2);
        }

        public UCGroupOrBuilder b(int i2) {
            return (UCGroupOrBuilder) this.i.get(i2);
        }

        private void u() {
            if (!this.i.a()) {
                this.i = GeneratedMessageLite.a(this.i);
            }
        }

        /* access modifiers changed from: private */
        public void a(int i2, UCGroup uCGroup) {
            if (uCGroup != null) {
                u();
                this.i.set(i2, uCGroup);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(int i2, UCGroup.Builder builder) {
            u();
            this.i.set(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(UCGroup uCGroup) {
            if (uCGroup != null) {
                u();
                this.i.add(uCGroup);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void b(int i2, UCGroup uCGroup) {
            if (uCGroup != null) {
                u();
                this.i.add(i2, uCGroup);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UCGroup.Builder builder) {
            u();
            this.i.add(builder.Z());
        }

        /* access modifiers changed from: private */
        public void b(int i2, UCGroup.Builder builder) {
            u();
            this.i.add(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(Iterable<? extends UCGroup> iterable) {
            u();
            AbstractMessageLite.a(iterable, this.i);
        }

        /* access modifiers changed from: private */
        public void v() {
            this.i = X();
        }

        /* access modifiers changed from: private */
        public void d(int i2) {
            u();
            this.i.remove(i2);
        }

        public List<ByteString> f() {
            return this.j;
        }

        public int g() {
            return this.j.size();
        }

        public ByteString c(int i2) {
            return (ByteString) this.j.get(i2);
        }

        private void w() {
            if (!this.j.a()) {
                this.j = GeneratedMessageLite.a(this.j);
            }
        }

        /* access modifiers changed from: private */
        public void b(int i2, ByteString byteString) {
            if (byteString != null) {
                w();
                this.j.set(i2, byteString);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                w();
                this.j.add(byteString);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void b(Iterable<? extends ByteString> iterable) {
            w();
            AbstractMessageLite.a(iterable, this.j);
        }

        /* access modifiers changed from: private */
        public void x() {
            this.j = X();
        }

        public boolean h() {
            return (this.g & 2) == 2;
        }

        public boolean i() {
            return this.k;
        }

        /* access modifiers changed from: private */
        public void a(boolean z) {
            this.g |= 2;
            this.k = z;
        }

        /* access modifiers changed from: private */
        public void y() {
            this.g &= -3;
            this.k = false;
        }

        public boolean j() {
            return (this.g & 4) == 4;
        }

        public long l() {
            return this.l;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.g |= 4;
            this.l = j2;
        }

        /* access modifiers changed from: private */
        public void z() {
            this.g &= -5;
            this.l = 0;
        }

        public boolean m() {
            return (this.g & 8) == 8;
        }

        public String n() {
            return this.p;
        }

        public ByteString o() {
            return ByteString.copyFromUtf8(this.p);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.g |= 8;
                this.p = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void A() {
            this.g &= -9;
            this.p = q().n();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.g |= 8;
                this.p = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.g & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
            }
            for (int i2 = 0; i2 < this.i.size(); i2++) {
                codedOutputStream.a(2, (MessageLite) this.i.get(i2));
            }
            for (int i3 = 0; i3 < this.j.size(); i3++) {
                codedOutputStream.a(3, (ByteString) this.j.get(i3));
            }
            if ((this.g & 2) == 2) {
                codedOutputStream.a(4, this.k);
            }
            if ((this.g & 4) == 4) {
                codedOutputStream.a(5, this.l);
            }
            if ((this.g & 8) == 8) {
                codedOutputStream.a(6, n());
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int c2 = (this.g & 1) == 1 ? CodedOutputStream.c(1, (MessageLite) b()) + 0 : 0;
            for (int i3 = 0; i3 < this.i.size(); i3++) {
                c2 += CodedOutputStream.c(2, (MessageLite) this.i.get(i3));
            }
            int i4 = 0;
            for (int i5 = 0; i5 < this.j.size(); i5++) {
                i4 += CodedOutputStream.c((ByteString) this.j.get(i5));
            }
            int size = c2 + i4 + (f().size() * 1);
            if ((this.g & 2) == 2) {
                size += CodedOutputStream.b(4, this.k);
            }
            if ((this.g & 4) == 4) {
                size += CodedOutputStream.f(5, this.l);
            }
            if ((this.g & 8) == 8) {
                size += CodedOutputStream.b(6, n());
            }
            int e2 = size + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UCPushMessage a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UCPushMessage) GeneratedMessageLite.a(r, byteString);
        }

        public static UCPushMessage a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCPushMessage) GeneratedMessageLite.a(r, byteString, extensionRegistryLite);
        }

        public static UCPushMessage a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UCPushMessage) GeneratedMessageLite.a(r, bArr);
        }

        public static UCPushMessage a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCPushMessage) GeneratedMessageLite.a(r, bArr, extensionRegistryLite);
        }

        public static UCPushMessage a(InputStream inputStream) throws IOException {
            return (UCPushMessage) GeneratedMessageLite.a(r, inputStream);
        }

        public static UCPushMessage a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCPushMessage) GeneratedMessageLite.a(r, inputStream, extensionRegistryLite);
        }

        public static UCPushMessage b(InputStream inputStream) throws IOException {
            return (UCPushMessage) b(r, inputStream);
        }

        public static UCPushMessage b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCPushMessage) b(r, inputStream, extensionRegistryLite);
        }

        public static UCPushMessage a(CodedInputStream codedInputStream) throws IOException {
            return (UCPushMessage) GeneratedMessageLite.b(r, codedInputStream);
        }

        public static UCPushMessage a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCPushMessage) GeneratedMessageLite.b(r, codedInputStream, extensionRegistryLite);
        }

        public static Builder p() {
            return (Builder) r.Y();
        }

        public static Builder a(UCPushMessage uCPushMessage) {
            return (Builder) ((Builder) r.Y()).b(uCPushMessage);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UCPushMessage, Builder> implements UCPushMessageOrBuilder {
            private Builder() {
                super(UCPushMessage.r);
            }

            public boolean a() {
                return ((UCPushMessage) this.f11310a).a();
            }

            public MIMCUser b() {
                return ((UCPushMessage) this.f11310a).b();
            }

            public Builder a(MIMCUser mIMCUser) {
                S();
                ((UCPushMessage) this.f11310a).a(mIMCUser);
                return this;
            }

            public Builder a(MIMCUser.Builder builder) {
                S();
                ((UCPushMessage) this.f11310a).a(builder);
                return this;
            }

            public Builder b(MIMCUser mIMCUser) {
                S();
                ((UCPushMessage) this.f11310a).b(mIMCUser);
                return this;
            }

            public Builder d() {
                S();
                ((UCPushMessage) this.f11310a).t();
                return this;
            }

            public List<UCGroup> c() {
                return Collections.unmodifiableList(((UCPushMessage) this.f11310a).c());
            }

            public int e() {
                return ((UCPushMessage) this.f11310a).e();
            }

            public UCGroup a(int i) {
                return ((UCPushMessage) this.f11310a).a(i);
            }

            public Builder a(int i, UCGroup uCGroup) {
                S();
                ((UCPushMessage) this.f11310a).a(i, uCGroup);
                return this;
            }

            public Builder a(int i, UCGroup.Builder builder) {
                S();
                ((UCPushMessage) this.f11310a).a(i, builder);
                return this;
            }

            public Builder a(UCGroup uCGroup) {
                S();
                ((UCPushMessage) this.f11310a).a(uCGroup);
                return this;
            }

            public Builder b(int i, UCGroup uCGroup) {
                S();
                ((UCPushMessage) this.f11310a).b(i, uCGroup);
                return this;
            }

            public Builder a(UCGroup.Builder builder) {
                S();
                ((UCPushMessage) this.f11310a).a(builder);
                return this;
            }

            public Builder b(int i, UCGroup.Builder builder) {
                S();
                ((UCPushMessage) this.f11310a).b(i, builder);
                return this;
            }

            public Builder a(Iterable<? extends UCGroup> iterable) {
                S();
                ((UCPushMessage) this.f11310a).a(iterable);
                return this;
            }

            public Builder k() {
                S();
                ((UCPushMessage) this.f11310a).v();
                return this;
            }

            public Builder b(int i) {
                S();
                ((UCPushMessage) this.f11310a).d(i);
                return this;
            }

            public List<ByteString> f() {
                return Collections.unmodifiableList(((UCPushMessage) this.f11310a).f());
            }

            public int g() {
                return ((UCPushMessage) this.f11310a).g();
            }

            public ByteString c(int i) {
                return ((UCPushMessage) this.f11310a).c(i);
            }

            public Builder a(int i, ByteString byteString) {
                S();
                ((UCPushMessage) this.f11310a).b(i, byteString);
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((UCPushMessage) this.f11310a).c(byteString);
                return this;
            }

            public Builder b(Iterable<? extends ByteString> iterable) {
                S();
                ((UCPushMessage) this.f11310a).b(iterable);
                return this;
            }

            public Builder p() {
                S();
                ((UCPushMessage) this.f11310a).x();
                return this;
            }

            public boolean h() {
                return ((UCPushMessage) this.f11310a).h();
            }

            public boolean i() {
                return ((UCPushMessage) this.f11310a).i();
            }

            public Builder a(boolean z) {
                S();
                ((UCPushMessage) this.f11310a).a(z);
                return this;
            }

            public Builder q() {
                S();
                ((UCPushMessage) this.f11310a).y();
                return this;
            }

            public boolean j() {
                return ((UCPushMessage) this.f11310a).j();
            }

            public long l() {
                return ((UCPushMessage) this.f11310a).l();
            }

            public Builder a(long j) {
                S();
                ((UCPushMessage) this.f11310a).a(j);
                return this;
            }

            public Builder r() {
                S();
                ((UCPushMessage) this.f11310a).z();
                return this;
            }

            public boolean m() {
                return ((UCPushMessage) this.f11310a).m();
            }

            public String n() {
                return ((UCPushMessage) this.f11310a).n();
            }

            public ByteString o() {
                return ((UCPushMessage) this.f11310a).o();
            }

            public Builder a(String str) {
                S();
                ((UCPushMessage) this.f11310a).a(str);
                return this;
            }

            public Builder s() {
                S();
                ((UCPushMessage) this.f11310a).A();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((UCPushMessage) this.f11310a).d(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UCPushMessage();
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
                    }
                    if (booleanValue) {
                        this.q = 1;
                    }
                    return r;
                case MAKE_IMMUTABLE:
                    this.i.b();
                    this.j.b();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    UCPushMessage uCPushMessage = (UCPushMessage) obj2;
                    this.h = (MIMCUser) visitor.a(this.h, uCPushMessage.h);
                    this.i = visitor.a(this.i, uCPushMessage.i);
                    this.j = visitor.a(this.j, uCPushMessage.j);
                    this.k = visitor.a(h(), this.k, uCPushMessage.h(), uCPushMessage.k);
                    this.l = visitor.a(j(), this.l, uCPushMessage.j(), uCPushMessage.l);
                    this.p = visitor.a(m(), this.p, uCPushMessage.m(), uCPushMessage.p);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.g |= uCPushMessage.g;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    MIMCUser.Builder builder = (this.g & 1) == 1 ? (MIMCUser.Builder) this.h.Y() : null;
                                    this.h = (MIMCUser) codedInputStream.a(MIMCUser.n(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.h);
                                        this.h = (MIMCUser) builder.Y();
                                    }
                                    this.g |= 1;
                                } else if (a2 == 18) {
                                    if (!this.i.a()) {
                                        this.i = GeneratedMessageLite.a(this.i);
                                    }
                                    this.i.add(codedInputStream.a(UCGroup.g(), extensionRegistryLite));
                                } else if (a2 == 26) {
                                    if (!this.j.a()) {
                                        this.j = GeneratedMessageLite.a(this.j);
                                    }
                                    this.j.add(codedInputStream.n());
                                } else if (a2 == 32) {
                                    this.g |= 2;
                                    this.k = codedInputStream.k();
                                } else if (a2 == 40) {
                                    this.g |= 4;
                                    this.l = codedInputStream.g();
                                } else if (a2 == 50) {
                                    String l2 = codedInputStream.l();
                                    this.g |= 8;
                                    this.p = l2;
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
                        synchronized (UCPushMessage.class) {
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

        public static UCPushMessage q() {
            return r;
        }

        public static Parser<UCPushMessage> r() {
            return r.M();
        }
    }

    public static final class UCMessageList extends GeneratedMessageLite<UCMessageList, Builder> implements UCMessageListOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11247a = 1;
        public static final int b = 2;
        public static final int c = 3;
        /* access modifiers changed from: private */
        public static final UCMessageList i = new UCMessageList();
        private static volatile Parser<UCMessageList> j;
        private int d;
        private UCGroup e;
        private Internal.ProtobufList<UCMessage> f = X();
        private long g;
        private byte h = -1;

        private UCMessageList() {
        }

        public boolean a() {
            return (this.d & 1) == 1;
        }

        public UCGroup b() {
            return this.e == null ? UCGroup.f() : this.e;
        }

        /* access modifiers changed from: private */
        public void a(UCGroup uCGroup) {
            if (uCGroup != null) {
                this.e = uCGroup;
                this.d |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UCGroup.Builder builder) {
            this.e = (UCGroup) builder.Z();
            this.d |= 1;
        }

        /* access modifiers changed from: private */
        public void b(UCGroup uCGroup) {
            if (this.e == null || this.e == UCGroup.f()) {
                this.e = uCGroup;
            } else {
                this.e = (UCGroup) ((UCGroup.Builder) UCGroup.a(this.e).b(uCGroup)).Y();
            }
            this.d |= 1;
        }

        /* access modifiers changed from: private */
        public void m() {
            this.e = null;
            this.d &= -2;
        }

        public List<UCMessage> c() {
            return this.f;
        }

        public List<? extends UCMessageOrBuilder> d() {
            return this.f;
        }

        public int e() {
            return this.f.size();
        }

        public UCMessage a(int i2) {
            return (UCMessage) this.f.get(i2);
        }

        public UCMessageOrBuilder b(int i2) {
            return (UCMessageOrBuilder) this.f.get(i2);
        }

        private void n() {
            if (!this.f.a()) {
                this.f = GeneratedMessageLite.a(this.f);
            }
        }

        /* access modifiers changed from: private */
        public void a(int i2, UCMessage uCMessage) {
            if (uCMessage != null) {
                n();
                this.f.set(i2, uCMessage);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(int i2, UCMessage.Builder builder) {
            n();
            this.f.set(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(UCMessage uCMessage) {
            if (uCMessage != null) {
                n();
                this.f.add(uCMessage);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void b(int i2, UCMessage uCMessage) {
            if (uCMessage != null) {
                n();
                this.f.add(i2, uCMessage);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UCMessage.Builder builder) {
            n();
            this.f.add(builder.Z());
        }

        /* access modifiers changed from: private */
        public void b(int i2, UCMessage.Builder builder) {
            n();
            this.f.add(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(Iterable<? extends UCMessage> iterable) {
            n();
            AbstractMessageLite.a(iterable, this.f);
        }

        /* access modifiers changed from: private */
        public void o() {
            this.f = X();
        }

        /* access modifiers changed from: private */
        public void c(int i2) {
            n();
            this.f.remove(i2);
        }

        public boolean f() {
            return (this.d & 2) == 2;
        }

        public long g() {
            return this.g;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.d |= 2;
            this.g = j2;
        }

        /* access modifiers changed from: private */
        public void p() {
            this.d &= -3;
            this.g = 0;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.d & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
            }
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                codedOutputStream.a(2, (MessageLite) this.f.get(i2));
            }
            if ((this.d & 2) == 2) {
                codedOutputStream.a(3, this.g);
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int c2 = (this.d & 1) == 1 ? CodedOutputStream.c(1, (MessageLite) b()) + 0 : 0;
            for (int i3 = 0; i3 < this.f.size(); i3++) {
                c2 += CodedOutputStream.c(2, (MessageLite) this.f.get(i3));
            }
            if ((this.d & 2) == 2) {
                c2 += CodedOutputStream.f(3, this.g);
            }
            int e2 = c2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UCMessageList a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UCMessageList) GeneratedMessageLite.a(i, byteString);
        }

        public static UCMessageList a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCMessageList) GeneratedMessageLite.a(i, byteString, extensionRegistryLite);
        }

        public static UCMessageList a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UCMessageList) GeneratedMessageLite.a(i, bArr);
        }

        public static UCMessageList a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCMessageList) GeneratedMessageLite.a(i, bArr, extensionRegistryLite);
        }

        public static UCMessageList a(InputStream inputStream) throws IOException {
            return (UCMessageList) GeneratedMessageLite.a(i, inputStream);
        }

        public static UCMessageList a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCMessageList) GeneratedMessageLite.a(i, inputStream, extensionRegistryLite);
        }

        public static UCMessageList b(InputStream inputStream) throws IOException {
            return (UCMessageList) b(i, inputStream);
        }

        public static UCMessageList b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCMessageList) b(i, inputStream, extensionRegistryLite);
        }

        public static UCMessageList a(CodedInputStream codedInputStream) throws IOException {
            return (UCMessageList) GeneratedMessageLite.b(i, codedInputStream);
        }

        public static UCMessageList a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCMessageList) GeneratedMessageLite.b(i, codedInputStream, extensionRegistryLite);
        }

        public static Builder h() {
            return (Builder) i.Y();
        }

        public static Builder a(UCMessageList uCMessageList) {
            return (Builder) ((Builder) i.Y()).b(uCMessageList);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UCMessageList, Builder> implements UCMessageListOrBuilder {
            private Builder() {
                super(UCMessageList.i);
            }

            public boolean a() {
                return ((UCMessageList) this.f11310a).a();
            }

            public UCGroup b() {
                return ((UCMessageList) this.f11310a).b();
            }

            public Builder a(UCGroup uCGroup) {
                S();
                ((UCMessageList) this.f11310a).a(uCGroup);
                return this;
            }

            public Builder a(UCGroup.Builder builder) {
                S();
                ((UCMessageList) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UCGroup uCGroup) {
                S();
                ((UCMessageList) this.f11310a).b(uCGroup);
                return this;
            }

            public Builder d() {
                S();
                ((UCMessageList) this.f11310a).m();
                return this;
            }

            public List<UCMessage> c() {
                return Collections.unmodifiableList(((UCMessageList) this.f11310a).c());
            }

            public int e() {
                return ((UCMessageList) this.f11310a).e();
            }

            public UCMessage a(int i) {
                return ((UCMessageList) this.f11310a).a(i);
            }

            public Builder a(int i, UCMessage uCMessage) {
                S();
                ((UCMessageList) this.f11310a).a(i, uCMessage);
                return this;
            }

            public Builder a(int i, UCMessage.Builder builder) {
                S();
                ((UCMessageList) this.f11310a).a(i, builder);
                return this;
            }

            public Builder a(UCMessage uCMessage) {
                S();
                ((UCMessageList) this.f11310a).a(uCMessage);
                return this;
            }

            public Builder b(int i, UCMessage uCMessage) {
                S();
                ((UCMessageList) this.f11310a).b(i, uCMessage);
                return this;
            }

            public Builder a(UCMessage.Builder builder) {
                S();
                ((UCMessageList) this.f11310a).a(builder);
                return this;
            }

            public Builder b(int i, UCMessage.Builder builder) {
                S();
                ((UCMessageList) this.f11310a).b(i, builder);
                return this;
            }

            public Builder a(Iterable<? extends UCMessage> iterable) {
                S();
                ((UCMessageList) this.f11310a).a(iterable);
                return this;
            }

            public Builder h() {
                S();
                ((UCMessageList) this.f11310a).o();
                return this;
            }

            public Builder b(int i) {
                S();
                ((UCMessageList) this.f11310a).c(i);
                return this;
            }

            public boolean f() {
                return ((UCMessageList) this.f11310a).f();
            }

            public long g() {
                return ((UCMessageList) this.f11310a).g();
            }

            public Builder a(long j) {
                S();
                ((UCMessageList) this.f11310a).a(j);
                return this;
            }

            public Builder i() {
                S();
                ((UCMessageList) this.f11310a).p();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UCMessageList();
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
                    }
                    for (int i2 = 0; i2 < e(); i2++) {
                        if (!a(i2).h_()) {
                            if (booleanValue) {
                                this.h = 0;
                            }
                            return null;
                        }
                    }
                    if (booleanValue) {
                        this.h = 1;
                    }
                    return i;
                case MAKE_IMMUTABLE:
                    this.f.b();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    UCMessageList uCMessageList = (UCMessageList) obj2;
                    this.e = (UCGroup) visitor.a(this.e, uCMessageList.e);
                    this.f = visitor.a(this.f, uCMessageList.f);
                    this.g = visitor.a(f(), this.g, uCMessageList.f(), uCMessageList.g);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.d |= uCMessageList.d;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    UCGroup.Builder builder = (this.d & 1) == 1 ? (UCGroup.Builder) this.e.Y() : null;
                                    this.e = (UCGroup) codedInputStream.a(UCGroup.g(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.e);
                                        this.e = (UCGroup) builder.Y();
                                    }
                                    this.d |= 1;
                                } else if (a2 == 18) {
                                    if (!this.f.a()) {
                                        this.f = GeneratedMessageLite.a(this.f);
                                    }
                                    this.f.add(codedInputStream.a(UCMessage.v(), extensionRegistryLite));
                                } else if (a2 == 24) {
                                    this.d |= 2;
                                    this.g = codedInputStream.g();
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
                        synchronized (UCMessageList.class) {
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

        public static UCMessageList i() {
            return i;
        }

        public static Parser<UCMessageList> j() {
            return i.M();
        }
    }

    public static final class UCSequenceAck extends GeneratedMessageLite<UCSequenceAck, Builder> implements UCSequenceAckOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11255a = 1;
        public static final int b = 2;
        /* access modifiers changed from: private */
        public static final UCSequenceAck g = new UCSequenceAck();
        private static volatile Parser<UCSequenceAck> h;
        private int c;
        private UCGroup d;
        private long e;
        private byte f = -1;

        private UCSequenceAck() {
        }

        public boolean a() {
            return (this.c & 1) == 1;
        }

        public UCGroup b() {
            return this.d == null ? UCGroup.f() : this.d;
        }

        /* access modifiers changed from: private */
        public void a(UCGroup uCGroup) {
            if (uCGroup != null) {
                this.d = uCGroup;
                this.c |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UCGroup.Builder builder) {
            this.d = (UCGroup) builder.Z();
            this.c |= 1;
        }

        /* access modifiers changed from: private */
        public void b(UCGroup uCGroup) {
            if (this.d == null || this.d == UCGroup.f()) {
                this.d = uCGroup;
            } else {
                this.d = (UCGroup) ((UCGroup.Builder) UCGroup.a(this.d).b(uCGroup)).Y();
            }
            this.c |= 1;
        }

        /* access modifiers changed from: private */
        public void i() {
            this.d = null;
            this.c &= -2;
        }

        public boolean c() {
            return (this.c & 2) == 2;
        }

        public long d() {
            return this.e;
        }

        /* access modifiers changed from: private */
        public void a(long j) {
            this.c |= 2;
            this.e = j;
        }

        /* access modifiers changed from: private */
        public void j() {
            this.c &= -3;
            this.e = 0;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.c & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
            }
            if ((this.c & 2) == 2) {
                codedOutputStream.a(2, this.e);
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
                i2 = 0 + CodedOutputStream.c(1, (MessageLite) b());
            }
            if ((this.c & 2) == 2) {
                i2 += CodedOutputStream.f(2, this.e);
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UCSequenceAck a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UCSequenceAck) GeneratedMessageLite.a(g, byteString);
        }

        public static UCSequenceAck a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCSequenceAck) GeneratedMessageLite.a(g, byteString, extensionRegistryLite);
        }

        public static UCSequenceAck a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UCSequenceAck) GeneratedMessageLite.a(g, bArr);
        }

        public static UCSequenceAck a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCSequenceAck) GeneratedMessageLite.a(g, bArr, extensionRegistryLite);
        }

        public static UCSequenceAck a(InputStream inputStream) throws IOException {
            return (UCSequenceAck) GeneratedMessageLite.a(g, inputStream);
        }

        public static UCSequenceAck a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCSequenceAck) GeneratedMessageLite.a(g, inputStream, extensionRegistryLite);
        }

        public static UCSequenceAck b(InputStream inputStream) throws IOException {
            return (UCSequenceAck) b(g, inputStream);
        }

        public static UCSequenceAck b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCSequenceAck) b(g, inputStream, extensionRegistryLite);
        }

        public static UCSequenceAck a(CodedInputStream codedInputStream) throws IOException {
            return (UCSequenceAck) GeneratedMessageLite.b(g, codedInputStream);
        }

        public static UCSequenceAck a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCSequenceAck) GeneratedMessageLite.b(g, codedInputStream, extensionRegistryLite);
        }

        public static Builder e() {
            return (Builder) g.Y();
        }

        public static Builder a(UCSequenceAck uCSequenceAck) {
            return (Builder) ((Builder) g.Y()).b(uCSequenceAck);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UCSequenceAck, Builder> implements UCSequenceAckOrBuilder {
            private Builder() {
                super(UCSequenceAck.g);
            }

            public boolean a() {
                return ((UCSequenceAck) this.f11310a).a();
            }

            public UCGroup b() {
                return ((UCSequenceAck) this.f11310a).b();
            }

            public Builder a(UCGroup uCGroup) {
                S();
                ((UCSequenceAck) this.f11310a).a(uCGroup);
                return this;
            }

            public Builder a(UCGroup.Builder builder) {
                S();
                ((UCSequenceAck) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UCGroup uCGroup) {
                S();
                ((UCSequenceAck) this.f11310a).b(uCGroup);
                return this;
            }

            public Builder e() {
                S();
                ((UCSequenceAck) this.f11310a).i();
                return this;
            }

            public boolean c() {
                return ((UCSequenceAck) this.f11310a).c();
            }

            public long d() {
                return ((UCSequenceAck) this.f11310a).d();
            }

            public Builder a(long j) {
                S();
                ((UCSequenceAck) this.f11310a).a(j);
                return this;
            }

            public Builder f() {
                S();
                ((UCSequenceAck) this.f11310a).j();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UCSequenceAck();
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
                    UCSequenceAck uCSequenceAck = (UCSequenceAck) obj2;
                    this.d = (UCGroup) visitor.a(this.d, uCSequenceAck.d);
                    this.e = visitor.a(c(), this.e, uCSequenceAck.c(), uCSequenceAck.e);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.c |= uCSequenceAck.c;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    UCGroup.Builder builder = (this.c & 1) == 1 ? (UCGroup.Builder) this.d.Y() : null;
                                    this.d = (UCGroup) codedInputStream.a(UCGroup.g(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.d);
                                        this.d = (UCGroup) builder.Y();
                                    }
                                    this.c |= 1;
                                } else if (a2 == 16) {
                                    this.c |= 2;
                                    this.e = codedInputStream.g();
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
                        synchronized (UCSequenceAck.class) {
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

        public static UCSequenceAck f() {
            return g;
        }

        public static Parser<UCSequenceAck> g() {
            return g.M();
        }
    }

    public static final class UCDismiss extends GeneratedMessageLite<UCDismiss, Builder> implements UCDismissOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11241a = 1;
        /* access modifiers changed from: private */
        public static final UCDismiss e = new UCDismiss();
        private static volatile Parser<UCDismiss> f;
        private int b;
        private UCGroup c;
        private byte d = -1;

        private UCDismiss() {
        }

        public boolean a() {
            return (this.b & 1) == 1;
        }

        public UCGroup b() {
            return this.c == null ? UCGroup.f() : this.c;
        }

        /* access modifiers changed from: private */
        public void a(UCGroup uCGroup) {
            if (uCGroup != null) {
                this.c = uCGroup;
                this.b |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UCGroup.Builder builder) {
            this.c = (UCGroup) builder.Z();
            this.b |= 1;
        }

        /* access modifiers changed from: private */
        public void b(UCGroup uCGroup) {
            if (this.c == null || this.c == UCGroup.f()) {
                this.c = uCGroup;
            } else {
                this.c = (UCGroup) ((UCGroup.Builder) UCGroup.a(this.c).b(uCGroup)).Y();
            }
            this.b |= 1;
        }

        /* access modifiers changed from: private */
        public void g() {
            this.c = null;
            this.b &= -2;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.b & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
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
                i2 = 0 + CodedOutputStream.c(1, (MessageLite) b());
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UCDismiss a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UCDismiss) GeneratedMessageLite.a(e, byteString);
        }

        public static UCDismiss a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCDismiss) GeneratedMessageLite.a(e, byteString, extensionRegistryLite);
        }

        public static UCDismiss a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UCDismiss) GeneratedMessageLite.a(e, bArr);
        }

        public static UCDismiss a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCDismiss) GeneratedMessageLite.a(e, bArr, extensionRegistryLite);
        }

        public static UCDismiss a(InputStream inputStream) throws IOException {
            return (UCDismiss) GeneratedMessageLite.a(e, inputStream);
        }

        public static UCDismiss a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCDismiss) GeneratedMessageLite.a(e, inputStream, extensionRegistryLite);
        }

        public static UCDismiss b(InputStream inputStream) throws IOException {
            return (UCDismiss) b(e, inputStream);
        }

        public static UCDismiss b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCDismiss) b(e, inputStream, extensionRegistryLite);
        }

        public static UCDismiss a(CodedInputStream codedInputStream) throws IOException {
            return (UCDismiss) GeneratedMessageLite.b(e, codedInputStream);
        }

        public static UCDismiss a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCDismiss) GeneratedMessageLite.b(e, codedInputStream, extensionRegistryLite);
        }

        public static Builder c() {
            return (Builder) e.Y();
        }

        public static Builder a(UCDismiss uCDismiss) {
            return (Builder) ((Builder) e.Y()).b(uCDismiss);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UCDismiss, Builder> implements UCDismissOrBuilder {
            private Builder() {
                super(UCDismiss.e);
            }

            public boolean a() {
                return ((UCDismiss) this.f11310a).a();
            }

            public UCGroup b() {
                return ((UCDismiss) this.f11310a).b();
            }

            public Builder a(UCGroup uCGroup) {
                S();
                ((UCDismiss) this.f11310a).a(uCGroup);
                return this;
            }

            public Builder a(UCGroup.Builder builder) {
                S();
                ((UCDismiss) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UCGroup uCGroup) {
                S();
                ((UCDismiss) this.f11310a).b(uCGroup);
                return this;
            }

            public Builder c() {
                S();
                ((UCDismiss) this.f11310a).g();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UCDismiss();
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
                    UCDismiss uCDismiss = (UCDismiss) obj2;
                    this.c = (UCGroup) visitor.a(this.c, uCDismiss.c);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.b |= uCDismiss.b;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    UCGroup.Builder builder = (this.b & 1) == 1 ? (UCGroup.Builder) this.c.Y() : null;
                                    this.c = (UCGroup) codedInputStream.a(UCGroup.g(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.c);
                                        this.c = (UCGroup) builder.Y();
                                    }
                                    this.b |= 1;
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
                        synchronized (UCDismiss.class) {
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

        public static UCDismiss d() {
            return e;
        }

        public static Parser<UCDismiss> e() {
            return e.M();
        }
    }

    public static final class UCQueryOnlineUsers extends GeneratedMessageLite<UCQueryOnlineUsers, Builder> implements UCQueryOnlineUsersOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11251a = 1;
        /* access modifiers changed from: private */
        public static final UCQueryOnlineUsers e = new UCQueryOnlineUsers();
        private static volatile Parser<UCQueryOnlineUsers> f;
        private int b;
        private UCGroup c;
        private byte d = -1;

        private UCQueryOnlineUsers() {
        }

        public boolean a() {
            return (this.b & 1) == 1;
        }

        public UCGroup b() {
            return this.c == null ? UCGroup.f() : this.c;
        }

        /* access modifiers changed from: private */
        public void a(UCGroup uCGroup) {
            if (uCGroup != null) {
                this.c = uCGroup;
                this.b |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UCGroup.Builder builder) {
            this.c = (UCGroup) builder.Z();
            this.b |= 1;
        }

        /* access modifiers changed from: private */
        public void b(UCGroup uCGroup) {
            if (this.c == null || this.c == UCGroup.f()) {
                this.c = uCGroup;
            } else {
                this.c = (UCGroup) ((UCGroup.Builder) UCGroup.a(this.c).b(uCGroup)).Y();
            }
            this.b |= 1;
        }

        /* access modifiers changed from: private */
        public void g() {
            this.c = null;
            this.b &= -2;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.b & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
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
                i2 = 0 + CodedOutputStream.c(1, (MessageLite) b());
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UCQueryOnlineUsers a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UCQueryOnlineUsers) GeneratedMessageLite.a(e, byteString);
        }

        public static UCQueryOnlineUsers a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCQueryOnlineUsers) GeneratedMessageLite.a(e, byteString, extensionRegistryLite);
        }

        public static UCQueryOnlineUsers a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UCQueryOnlineUsers) GeneratedMessageLite.a(e, bArr);
        }

        public static UCQueryOnlineUsers a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCQueryOnlineUsers) GeneratedMessageLite.a(e, bArr, extensionRegistryLite);
        }

        public static UCQueryOnlineUsers a(InputStream inputStream) throws IOException {
            return (UCQueryOnlineUsers) GeneratedMessageLite.a(e, inputStream);
        }

        public static UCQueryOnlineUsers a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCQueryOnlineUsers) GeneratedMessageLite.a(e, inputStream, extensionRegistryLite);
        }

        public static UCQueryOnlineUsers b(InputStream inputStream) throws IOException {
            return (UCQueryOnlineUsers) b(e, inputStream);
        }

        public static UCQueryOnlineUsers b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCQueryOnlineUsers) b(e, inputStream, extensionRegistryLite);
        }

        public static UCQueryOnlineUsers a(CodedInputStream codedInputStream) throws IOException {
            return (UCQueryOnlineUsers) GeneratedMessageLite.b(e, codedInputStream);
        }

        public static UCQueryOnlineUsers a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCQueryOnlineUsers) GeneratedMessageLite.b(e, codedInputStream, extensionRegistryLite);
        }

        public static Builder c() {
            return (Builder) e.Y();
        }

        public static Builder a(UCQueryOnlineUsers uCQueryOnlineUsers) {
            return (Builder) ((Builder) e.Y()).b(uCQueryOnlineUsers);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UCQueryOnlineUsers, Builder> implements UCQueryOnlineUsersOrBuilder {
            private Builder() {
                super(UCQueryOnlineUsers.e);
            }

            public boolean a() {
                return ((UCQueryOnlineUsers) this.f11310a).a();
            }

            public UCGroup b() {
                return ((UCQueryOnlineUsers) this.f11310a).b();
            }

            public Builder a(UCGroup uCGroup) {
                S();
                ((UCQueryOnlineUsers) this.f11310a).a(uCGroup);
                return this;
            }

            public Builder a(UCGroup.Builder builder) {
                S();
                ((UCQueryOnlineUsers) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UCGroup uCGroup) {
                S();
                ((UCQueryOnlineUsers) this.f11310a).b(uCGroup);
                return this;
            }

            public Builder c() {
                S();
                ((UCQueryOnlineUsers) this.f11310a).g();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UCQueryOnlineUsers();
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
                    UCQueryOnlineUsers uCQueryOnlineUsers = (UCQueryOnlineUsers) obj2;
                    this.c = (UCGroup) visitor.a(this.c, uCQueryOnlineUsers.c);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.b |= uCQueryOnlineUsers.b;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    UCGroup.Builder builder = (this.b & 1) == 1 ? (UCGroup.Builder) this.c.Y() : null;
                                    this.c = (UCGroup) codedInputStream.a(UCGroup.g(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.c);
                                        this.c = (UCGroup) builder.Y();
                                    }
                                    this.b |= 1;
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
                        synchronized (UCQueryOnlineUsers.class) {
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

        public static UCQueryOnlineUsers d() {
            return e;
        }

        public static Parser<UCQueryOnlineUsers> e() {
            return e.M();
        }
    }

    public static final class UCQueryOnlineUsersResp extends GeneratedMessageLite<UCQueryOnlineUsersResp, Builder> implements UCQueryOnlineUsersRespOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11252a = 1;
        public static final int b = 2;
        /* access modifiers changed from: private */
        public static final UCQueryOnlineUsersResp g = new UCQueryOnlineUsersResp();
        private static volatile Parser<UCQueryOnlineUsersResp> h;
        private int c;
        private UCGroup d;
        private long e;
        private byte f = -1;

        private UCQueryOnlineUsersResp() {
        }

        public boolean a() {
            return (this.c & 1) == 1;
        }

        public UCGroup b() {
            return this.d == null ? UCGroup.f() : this.d;
        }

        /* access modifiers changed from: private */
        public void a(UCGroup uCGroup) {
            if (uCGroup != null) {
                this.d = uCGroup;
                this.c |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UCGroup.Builder builder) {
            this.d = (UCGroup) builder.Z();
            this.c |= 1;
        }

        /* access modifiers changed from: private */
        public void b(UCGroup uCGroup) {
            if (this.d == null || this.d == UCGroup.f()) {
                this.d = uCGroup;
            } else {
                this.d = (UCGroup) ((UCGroup.Builder) UCGroup.a(this.d).b(uCGroup)).Y();
            }
            this.c |= 1;
        }

        /* access modifiers changed from: private */
        public void i() {
            this.d = null;
            this.c &= -2;
        }

        public boolean c() {
            return (this.c & 2) == 2;
        }

        public long d() {
            return this.e;
        }

        /* access modifiers changed from: private */
        public void a(long j) {
            this.c |= 2;
            this.e = j;
        }

        /* access modifiers changed from: private */
        public void j() {
            this.c &= -3;
            this.e = 0;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.c & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
            }
            if ((this.c & 2) == 2) {
                codedOutputStream.a(2, this.e);
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
                i2 = 0 + CodedOutputStream.c(1, (MessageLite) b());
            }
            if ((this.c & 2) == 2) {
                i2 += CodedOutputStream.f(2, this.e);
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UCQueryOnlineUsersResp a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UCQueryOnlineUsersResp) GeneratedMessageLite.a(g, byteString);
        }

        public static UCQueryOnlineUsersResp a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCQueryOnlineUsersResp) GeneratedMessageLite.a(g, byteString, extensionRegistryLite);
        }

        public static UCQueryOnlineUsersResp a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UCQueryOnlineUsersResp) GeneratedMessageLite.a(g, bArr);
        }

        public static UCQueryOnlineUsersResp a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UCQueryOnlineUsersResp) GeneratedMessageLite.a(g, bArr, extensionRegistryLite);
        }

        public static UCQueryOnlineUsersResp a(InputStream inputStream) throws IOException {
            return (UCQueryOnlineUsersResp) GeneratedMessageLite.a(g, inputStream);
        }

        public static UCQueryOnlineUsersResp a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCQueryOnlineUsersResp) GeneratedMessageLite.a(g, inputStream, extensionRegistryLite);
        }

        public static UCQueryOnlineUsersResp b(InputStream inputStream) throws IOException {
            return (UCQueryOnlineUsersResp) b(g, inputStream);
        }

        public static UCQueryOnlineUsersResp b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCQueryOnlineUsersResp) b(g, inputStream, extensionRegistryLite);
        }

        public static UCQueryOnlineUsersResp a(CodedInputStream codedInputStream) throws IOException {
            return (UCQueryOnlineUsersResp) GeneratedMessageLite.b(g, codedInputStream);
        }

        public static UCQueryOnlineUsersResp a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UCQueryOnlineUsersResp) GeneratedMessageLite.b(g, codedInputStream, extensionRegistryLite);
        }

        public static Builder e() {
            return (Builder) g.Y();
        }

        public static Builder a(UCQueryOnlineUsersResp uCQueryOnlineUsersResp) {
            return (Builder) ((Builder) g.Y()).b(uCQueryOnlineUsersResp);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UCQueryOnlineUsersResp, Builder> implements UCQueryOnlineUsersRespOrBuilder {
            private Builder() {
                super(UCQueryOnlineUsersResp.g);
            }

            public boolean a() {
                return ((UCQueryOnlineUsersResp) this.f11310a).a();
            }

            public UCGroup b() {
                return ((UCQueryOnlineUsersResp) this.f11310a).b();
            }

            public Builder a(UCGroup uCGroup) {
                S();
                ((UCQueryOnlineUsersResp) this.f11310a).a(uCGroup);
                return this;
            }

            public Builder a(UCGroup.Builder builder) {
                S();
                ((UCQueryOnlineUsersResp) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UCGroup uCGroup) {
                S();
                ((UCQueryOnlineUsersResp) this.f11310a).b(uCGroup);
                return this;
            }

            public Builder e() {
                S();
                ((UCQueryOnlineUsersResp) this.f11310a).i();
                return this;
            }

            public boolean c() {
                return ((UCQueryOnlineUsersResp) this.f11310a).c();
            }

            public long d() {
                return ((UCQueryOnlineUsersResp) this.f11310a).d();
            }

            public Builder a(long j) {
                S();
                ((UCQueryOnlineUsersResp) this.f11310a).a(j);
                return this;
            }

            public Builder f() {
                S();
                ((UCQueryOnlineUsersResp) this.f11310a).j();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UCQueryOnlineUsersResp();
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
                    UCQueryOnlineUsersResp uCQueryOnlineUsersResp = (UCQueryOnlineUsersResp) obj2;
                    this.d = (UCGroup) visitor.a(this.d, uCQueryOnlineUsersResp.d);
                    this.e = visitor.a(c(), this.e, uCQueryOnlineUsersResp.c(), uCQueryOnlineUsersResp.e);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.c |= uCQueryOnlineUsersResp.c;
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 != 0) {
                                if (a2 == 10) {
                                    UCGroup.Builder builder = (this.c & 1) == 1 ? (UCGroup.Builder) this.d.Y() : null;
                                    this.d = (UCGroup) codedInputStream.a(UCGroup.g(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.d);
                                        this.d = (UCGroup) builder.Y();
                                    }
                                    this.c |= 1;
                                } else if (a2 == 16) {
                                    this.c |= 2;
                                    this.e = codedInputStream.g();
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
                        synchronized (UCQueryOnlineUsersResp.class) {
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

        public static UCQueryOnlineUsersResp f() {
            return g;
        }

        public static Parser<UCQueryOnlineUsersResp> g() {
            return g.M();
        }
    }

    public static final class FeInfo extends GeneratedMessageLite<FeInfo, Builder> implements FeInfoOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11224a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        /* access modifiers changed from: private */
        public static final FeInfo q = new FeInfo();
        private static volatile Parser<FeInfo> r;
        private int g;
        private String h = "";
        private int i;
        private int j;
        private long k;
        private long l;
        private long p;

        private FeInfo() {
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
        public void s() {
            this.g &= -2;
            this.h = p().b();
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

        public int e() {
            return this.i;
        }

        /* access modifiers changed from: private */
        public void a(int i2) {
            this.g |= 2;
            this.i = i2;
        }

        /* access modifiers changed from: private */
        public void t() {
            this.g &= -3;
            this.i = 0;
        }

        public boolean f() {
            return (this.g & 4) == 4;
        }

        public int g() {
            return this.j;
        }

        /* access modifiers changed from: private */
        public void b(int i2) {
            this.g |= 4;
            this.j = i2;
        }

        /* access modifiers changed from: private */
        public void u() {
            this.g &= -5;
            this.j = 0;
        }

        public boolean h() {
            return (this.g & 8) == 8;
        }

        public long i() {
            return this.k;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.g |= 8;
            this.k = j2;
        }

        /* access modifiers changed from: private */
        public void v() {
            this.g &= -9;
            this.k = 0;
        }

        public boolean j() {
            return (this.g & 16) == 16;
        }

        public long l() {
            return this.l;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.g |= 16;
            this.l = j2;
        }

        /* access modifiers changed from: private */
        public void w() {
            this.g &= -17;
            this.l = 0;
        }

        public boolean m() {
            return (this.g & 32) == 32;
        }

        public long n() {
            return this.p;
        }

        /* access modifiers changed from: private */
        public void c(long j2) {
            this.g |= 32;
            this.p = j2;
        }

        /* access modifiers changed from: private */
        public void x() {
            this.g &= -33;
            this.p = 0;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.g & 1) == 1) {
                codedOutputStream.a(1, b());
            }
            if ((this.g & 2) == 2) {
                codedOutputStream.b(2, this.i);
            }
            if ((this.g & 4) == 4) {
                codedOutputStream.b(3, this.j);
            }
            if ((this.g & 8) == 8) {
                codedOutputStream.b(4, this.k);
            }
            if ((this.g & 16) == 16) {
                codedOutputStream.b(5, this.l);
            }
            if ((this.g & 32) == 32) {
                codedOutputStream.b(6, this.p);
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
                i3 += CodedOutputStream.h(2, this.i);
            }
            if ((this.g & 4) == 4) {
                i3 += CodedOutputStream.h(3, this.j);
            }
            if ((this.g & 8) == 8) {
                i3 += CodedOutputStream.g(4, this.k);
            }
            if ((this.g & 16) == 16) {
                i3 += CodedOutputStream.g(5, this.l);
            }
            if ((this.g & 32) == 32) {
                i3 += CodedOutputStream.g(6, this.p);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static FeInfo a(ByteString byteString) throws InvalidProtocolBufferException {
            return (FeInfo) GeneratedMessageLite.a(q, byteString);
        }

        public static FeInfo a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FeInfo) GeneratedMessageLite.a(q, byteString, extensionRegistryLite);
        }

        public static FeInfo a(byte[] bArr) throws InvalidProtocolBufferException {
            return (FeInfo) GeneratedMessageLite.a(q, bArr);
        }

        public static FeInfo a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FeInfo) GeneratedMessageLite.a(q, bArr, extensionRegistryLite);
        }

        public static FeInfo a(InputStream inputStream) throws IOException {
            return (FeInfo) GeneratedMessageLite.a(q, inputStream);
        }

        public static FeInfo a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FeInfo) GeneratedMessageLite.a(q, inputStream, extensionRegistryLite);
        }

        public static FeInfo b(InputStream inputStream) throws IOException {
            return (FeInfo) b(q, inputStream);
        }

        public static FeInfo b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FeInfo) b(q, inputStream, extensionRegistryLite);
        }

        public static FeInfo a(CodedInputStream codedInputStream) throws IOException {
            return (FeInfo) GeneratedMessageLite.b(q, codedInputStream);
        }

        public static FeInfo a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FeInfo) GeneratedMessageLite.b(q, codedInputStream, extensionRegistryLite);
        }

        public static Builder o() {
            return (Builder) q.Y();
        }

        public static Builder a(FeInfo feInfo) {
            return (Builder) ((Builder) q.Y()).b(feInfo);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<FeInfo, Builder> implements FeInfoOrBuilder {
            private Builder() {
                super(FeInfo.q);
            }

            public boolean a() {
                return ((FeInfo) this.f11310a).a();
            }

            public String b() {
                return ((FeInfo) this.f11310a).b();
            }

            public ByteString c() {
                return ((FeInfo) this.f11310a).c();
            }

            public Builder a(String str) {
                S();
                ((FeInfo) this.f11310a).a(str);
                return this;
            }

            public Builder k() {
                S();
                ((FeInfo) this.f11310a).s();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((FeInfo) this.f11310a).c(byteString);
                return this;
            }

            public boolean d() {
                return ((FeInfo) this.f11310a).d();
            }

            public int e() {
                return ((FeInfo) this.f11310a).e();
            }

            public Builder a(int i) {
                S();
                ((FeInfo) this.f11310a).a(i);
                return this;
            }

            public Builder o() {
                S();
                ((FeInfo) this.f11310a).t();
                return this;
            }

            public boolean f() {
                return ((FeInfo) this.f11310a).f();
            }

            public int g() {
                return ((FeInfo) this.f11310a).g();
            }

            public Builder b(int i) {
                S();
                ((FeInfo) this.f11310a).b(i);
                return this;
            }

            public Builder p() {
                S();
                ((FeInfo) this.f11310a).u();
                return this;
            }

            public boolean h() {
                return ((FeInfo) this.f11310a).h();
            }

            public long i() {
                return ((FeInfo) this.f11310a).i();
            }

            public Builder a(long j) {
                S();
                ((FeInfo) this.f11310a).a(j);
                return this;
            }

            public Builder q() {
                S();
                ((FeInfo) this.f11310a).v();
                return this;
            }

            public boolean j() {
                return ((FeInfo) this.f11310a).j();
            }

            public long l() {
                return ((FeInfo) this.f11310a).l();
            }

            public Builder b(long j) {
                S();
                ((FeInfo) this.f11310a).b(j);
                return this;
            }

            public Builder r() {
                S();
                ((FeInfo) this.f11310a).w();
                return this;
            }

            public boolean m() {
                return ((FeInfo) this.f11310a).m();
            }

            public long n() {
                return ((FeInfo) this.f11310a).n();
            }

            public Builder c(long j) {
                S();
                ((FeInfo) this.f11310a).c(j);
                return this;
            }

            public Builder s() {
                S();
                ((FeInfo) this.f11310a).x();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new FeInfo();
                case IS_INITIALIZED:
                    return q;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    FeInfo feInfo = (FeInfo) obj2;
                    this.h = visitor.a(a(), this.h, feInfo.a(), feInfo.h);
                    this.i = visitor.a(d(), this.i, feInfo.d(), feInfo.i);
                    this.j = visitor.a(f(), this.j, feInfo.f(), feInfo.j);
                    this.k = visitor.a(h(), this.k, feInfo.h(), feInfo.k);
                    this.l = visitor.a(j(), this.l, feInfo.j(), feInfo.l);
                    this.p = visitor.a(m(), this.p, feInfo.m(), feInfo.p);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.g |= feInfo.g;
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
                                } else if (a2 == 16) {
                                    this.g |= 2;
                                    this.i = codedInputStream.h();
                                } else if (a2 == 24) {
                                    this.g |= 4;
                                    this.j = codedInputStream.h();
                                } else if (a2 == 32) {
                                    this.g |= 8;
                                    this.k = codedInputStream.f();
                                } else if (a2 == 40) {
                                    this.g |= 16;
                                    this.l = codedInputStream.f();
                                } else if (a2 == 48) {
                                    this.g |= 32;
                                    this.p = codedInputStream.f();
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
                        synchronized (FeInfo.class) {
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

        public static FeInfo p() {
            return q;
        }

        public static Parser<FeInfo> q() {
            return q.M();
        }
    }
}
