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

public final class RtsSignal {

    public interface ByeRequestOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        String b();

        ByteString c();
    }

    public interface ByeResponseOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        RTSResult b();

        boolean c();

        String d();

        ByteString e();
    }

    public interface CallInfoOrBuilder extends MessageLiteOrBuilder {
        UserInfo a(int i);

        boolean a();

        long b();

        boolean c();

        CallType d();

        boolean e();

        CallStatus f();

        boolean g();

        long h();

        boolean i();

        String j();

        ByteString l();

        boolean m();

        String n();

        ByteString o();

        boolean p();

        ByteString q();

        List<UserInfo> r();

        int t();
    }

    public interface CreateChannelRequestOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        UserInfo b();

        boolean c();

        long d();

        boolean e();

        ByteString f();
    }

    public interface CreateChannelResponseOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        RTSResult b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        long g();

        boolean h();

        String i();

        ByteString j();

        boolean l();

        long m();

        boolean n();

        ByteString o();

        boolean p();

        UserInfo q();
    }

    public interface CreateRequestOrBuilder extends MessageLiteOrBuilder {
        UserInfo a(int i);

        boolean a();

        StreamDataType b();

        List<UserInfo> c();

        int e();

        boolean f();

        String g();

        ByteString h();

        boolean i();

        ByteString j();
    }

    public interface CreateResponseOrBuilder extends MessageLiteOrBuilder {
        UserInfo a(int i);

        boolean a();

        RTSResult b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        String g();

        ByteString h();

        List<UserInfo> i();

        int l();
    }

    public interface InviteRequestOrBuilder extends MessageLiteOrBuilder {
        UserInfo a(int i);

        boolean a();

        StreamDataType b();

        List<UserInfo> c();

        int e();

        boolean f();

        String g();

        ByteString h();

        boolean i();

        ByteString j();
    }

    public interface InviteResponseOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        RTSResult b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        String g();

        ByteString h();

        boolean i();

        UserInfo j();
    }

    public interface JoinChannelRequestOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        UserInfo g();
    }

    public interface JoinChannelResponseOrBuilder extends MessageLiteOrBuilder {
        UserInfo a(int i);

        boolean a();

        RTSResult b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        long g();

        boolean h();

        ByteString i();

        List<UserInfo> j();

        int m();
    }

    public interface LeaveChannelRequestOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        UserInfo g();
    }

    public interface LeaveChannelResponseOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        RTSResult b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        long g();
    }

    public interface PingRequestOrBuilder extends MessageLiteOrBuilder {
    }

    public interface PingResponseOrBuilder extends MessageLiteOrBuilder {
    }

    public interface RTSMessageOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        RTSMessageType b();

        boolean c();

        long d();

        boolean e();

        CallType f();

        boolean g();

        long h();

        boolean i();

        String j();

        ByteString l();

        boolean m();

        ByteString n();

        boolean o();

        long p();
    }

    public interface UpdateCallInfoOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        UpdateType d();
    }

    public interface UpdateRequestOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        UserInfo b();
    }

    public interface UpdateResponseOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        RTSResult b();
    }

    public interface UserInfoOrBuilder extends MessageLiteOrBuilder {
        boolean A();

        long B();

        boolean C();

        boolean D();

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

        boolean l();

        String m();

        ByteString n();

        boolean o();

        int p();

        boolean q();

        String r();

        ByteString s();

        boolean t();

        int u();

        boolean v();

        String w();

        ByteString x();

        boolean y();

        int z();
    }

    public interface UserJoinNotificationOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        UserInfo g();
    }

    public interface UserLeaveNotificationOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        long b();

        boolean c();

        String d();

        ByteString e();

        boolean f();

        UserInfo g();
    }

    public interface XmqRTSExchangeOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        String b();

        ByteString c();

        boolean d();

        String e();

        ByteString f();

        boolean g();

        long h();

        boolean i();

        String j();

        ByteString l();

        boolean m();

        RTSMessage n();

        boolean o();

        long p();
    }

    public static void a(ExtensionRegistryLite extensionRegistryLite) {
    }

    private RtsSignal() {
    }

    public enum RTSMessageType implements Internal.EnumLite {
        CREATE_REQUEST(1),
        CREATE_RESPONSE(2),
        INVITE_REQUEST(3),
        INVITE_RESPONSE(4),
        UPDATE_REQUEST(5),
        UPDATE_RESPONSE(6),
        PING_REQUEST(7),
        PING_RESPONSE(8),
        BYE_REQUEST(9),
        BYE_RESPONSE(10),
        CREATE_CHANNEL_REQUEST(11),
        CREATE_CHANNEL_RESPONSE(12),
        JOIN_CHANNEL_REQUEST(13),
        JOIN_CHANNEL_RESPONSE(14),
        LEAVE_CHANNEL_REQUEST(15),
        LEAVE_CHANNEL_RESPONSE(16),
        USER_JOIN_NOTIFICATION(17),
        USER_LEAVE_NOTIFICATION(18);
        
        public static final int BYE_REQUEST_VALUE = 9;
        public static final int BYE_RESPONSE_VALUE = 10;
        public static final int CREATE_CHANNEL_REQUEST_VALUE = 11;
        public static final int CREATE_CHANNEL_RESPONSE_VALUE = 12;
        public static final int CREATE_REQUEST_VALUE = 1;
        public static final int CREATE_RESPONSE_VALUE = 2;
        public static final int INVITE_REQUEST_VALUE = 3;
        public static final int INVITE_RESPONSE_VALUE = 4;
        public static final int JOIN_CHANNEL_REQUEST_VALUE = 13;
        public static final int JOIN_CHANNEL_RESPONSE_VALUE = 14;
        public static final int LEAVE_CHANNEL_REQUEST_VALUE = 15;
        public static final int LEAVE_CHANNEL_RESPONSE_VALUE = 16;
        public static final int PING_REQUEST_VALUE = 7;
        public static final int PING_RESPONSE_VALUE = 8;
        public static final int UPDATE_REQUEST_VALUE = 5;
        public static final int UPDATE_RESPONSE_VALUE = 6;
        public static final int USER_JOIN_NOTIFICATION_VALUE = 17;
        public static final int USER_LEAVE_NOTIFICATION_VALUE = 18;
        private static final Internal.EnumLiteMap<RTSMessageType> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<RTSMessageType>() {
                /* renamed from: a */
                public RTSMessageType b(int i) {
                    return RTSMessageType.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static RTSMessageType valueOf(int i) {
            return forNumber(i);
        }

        public static RTSMessageType forNumber(int i) {
            switch (i) {
                case 1:
                    return CREATE_REQUEST;
                case 2:
                    return CREATE_RESPONSE;
                case 3:
                    return INVITE_REQUEST;
                case 4:
                    return INVITE_RESPONSE;
                case 5:
                    return UPDATE_REQUEST;
                case 6:
                    return UPDATE_RESPONSE;
                case 7:
                    return PING_REQUEST;
                case 8:
                    return PING_RESPONSE;
                case 9:
                    return BYE_REQUEST;
                case 10:
                    return BYE_RESPONSE;
                case 11:
                    return CREATE_CHANNEL_REQUEST;
                case 12:
                    return CREATE_CHANNEL_RESPONSE;
                case 13:
                    return JOIN_CHANNEL_REQUEST;
                case 14:
                    return JOIN_CHANNEL_RESPONSE;
                case 15:
                    return LEAVE_CHANNEL_REQUEST;
                case 16:
                    return LEAVE_CHANNEL_RESPONSE;
                case 17:
                    return USER_JOIN_NOTIFICATION;
                case 18:
                    return USER_LEAVE_NOTIFICATION;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<RTSMessageType> internalGetValueMap() {
            return internalValueMap;
        }

        private RTSMessageType(int i) {
            this.value = i;
        }
    }

    public enum CallType implements Internal.EnumLite {
        SINGLE_CALL(1),
        CHANNEL_CALL(2);
        
        public static final int CHANNEL_CALL_VALUE = 2;
        public static final int SINGLE_CALL_VALUE = 1;
        private static final Internal.EnumLiteMap<CallType> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<CallType>() {
                /* renamed from: a */
                public CallType b(int i) {
                    return CallType.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static CallType valueOf(int i) {
            return forNumber(i);
        }

        public static CallType forNumber(int i) {
            switch (i) {
                case 1:
                    return SINGLE_CALL;
                case 2:
                    return CHANNEL_CALL;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<CallType> internalGetValueMap() {
            return internalValueMap;
        }

        private CallType(int i) {
            this.value = i;
        }
    }

    public enum StreamDataType implements Internal.EnumLite {
        A_STREAM(1),
        V_STREAM(2),
        AV_STREAM(3);
        
        public static final int AV_STREAM_VALUE = 3;
        public static final int A_STREAM_VALUE = 1;
        public static final int V_STREAM_VALUE = 2;
        private static final Internal.EnumLiteMap<StreamDataType> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<StreamDataType>() {
                /* renamed from: a */
                public StreamDataType b(int i) {
                    return StreamDataType.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static StreamDataType valueOf(int i) {
            return forNumber(i);
        }

        public static StreamDataType forNumber(int i) {
            switch (i) {
                case 1:
                    return A_STREAM;
                case 2:
                    return V_STREAM;
                case 3:
                    return AV_STREAM;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<StreamDataType> internalGetValueMap() {
            return internalValueMap;
        }

        private StreamDataType(int i) {
            this.value = i;
        }
    }

    public enum RTSResult implements Internal.EnumLite {
        SUCC(0),
        PEER_REFUSE(1),
        PEER_OFFLINE(2),
        CALLID_EXIST(3),
        CALLID_NOT_EXIST(4),
        SDP_FAIL(5),
        INTERNAL_ERROR1(6),
        PARAMETER_ERROR(7),
        INVALID_OPERATION(8),
        EXCEED_MAX_LIMIT(9),
        ALREADY_IN_SESSION(10),
        ERROR_USER_DEFINED(99);
        
        public static final int ALREADY_IN_SESSION_VALUE = 10;
        public static final int CALLID_EXIST_VALUE = 3;
        public static final int CALLID_NOT_EXIST_VALUE = 4;
        public static final int ERROR_USER_DEFINED_VALUE = 99;
        public static final int EXCEED_MAX_LIMIT_VALUE = 9;
        public static final int INTERNAL_ERROR1_VALUE = 6;
        public static final int INVALID_OPERATION_VALUE = 8;
        public static final int PARAMETER_ERROR_VALUE = 7;
        public static final int PEER_OFFLINE_VALUE = 2;
        public static final int PEER_REFUSE_VALUE = 1;
        public static final int SDP_FAIL_VALUE = 5;
        public static final int SUCC_VALUE = 0;
        private static final Internal.EnumLiteMap<RTSResult> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<RTSResult>() {
                /* renamed from: a */
                public RTSResult b(int i) {
                    return RTSResult.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static RTSResult valueOf(int i) {
            return forNumber(i);
        }

        public static RTSResult forNumber(int i) {
            if (i == 99) {
                return ERROR_USER_DEFINED;
            }
            switch (i) {
                case 0:
                    return SUCC;
                case 1:
                    return PEER_REFUSE;
                case 2:
                    return PEER_OFFLINE;
                case 3:
                    return CALLID_EXIST;
                case 4:
                    return CALLID_NOT_EXIST;
                case 5:
                    return SDP_FAIL;
                case 6:
                    return INTERNAL_ERROR1;
                case 7:
                    return PARAMETER_ERROR;
                case 8:
                    return INVALID_OPERATION;
                case 9:
                    return EXCEED_MAX_LIMIT;
                case 10:
                    return ALREADY_IN_SESSION;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<RTSResult> internalGetValueMap() {
            return internalValueMap;
        }

        private RTSResult(int i) {
            this.value = i;
        }
    }

    public enum CallStatus implements Internal.EnumLite {
        CREATING(1),
        IN_PROCESS(2),
        FINISH(3);
        
        public static final int CREATING_VALUE = 1;
        public static final int FINISH_VALUE = 3;
        public static final int IN_PROCESS_VALUE = 2;
        private static final Internal.EnumLiteMap<CallStatus> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<CallStatus>() {
                /* renamed from: a */
                public CallStatus b(int i) {
                    return CallStatus.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static CallStatus valueOf(int i) {
            return forNumber(i);
        }

        public static CallStatus forNumber(int i) {
            switch (i) {
                case 1:
                    return CREATING;
                case 2:
                    return IN_PROCESS;
                case 3:
                    return FINISH;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<CallStatus> internalGetValueMap() {
            return internalValueMap;
        }

        private CallStatus(int i) {
            this.value = i;
        }
    }

    public enum UpdateType implements Internal.EnumLite {
        UPDATE(1),
        DELETE(2);
        
        public static final int DELETE_VALUE = 2;
        public static final int UPDATE_VALUE = 1;
        private static final Internal.EnumLiteMap<UpdateType> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<UpdateType>() {
                /* renamed from: a */
                public UpdateType b(int i) {
                    return UpdateType.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static UpdateType valueOf(int i) {
            return forNumber(i);
        }

        public static UpdateType forNumber(int i) {
            switch (i) {
                case 1:
                    return UPDATE;
                case 2:
                    return DELETE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<UpdateType> internalGetValueMap() {
            return internalValueMap;
        }

        private UpdateType(int i) {
            this.value = i;
        }
    }

    public static final class UserInfo extends GeneratedMessageLite<UserInfo, Builder> implements UserInfoOrBuilder {
        /* access modifiers changed from: private */
        public static final UserInfo C = new UserInfo();
        private static volatile Parser<UserInfo> D = null;

        /* renamed from: a  reason: collision with root package name */
        public static final int f11289a = 1;
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
        private long A;
        private boolean B;
        private int p;
        private long q;
        private String r = "";
        private long s;
        private String t = "";
        private String u = "";
        private int v;
        private String w = "";
        private int x;
        private String y = "";
        private int z;

        private UserInfo() {
        }

        public boolean a() {
            return (this.p & 1) == 1;
        }

        public long b() {
            return this.q;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.p |= 1;
            this.q = j2;
        }

        /* access modifiers changed from: private */
        public void I() {
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
        public void ab() {
            this.p &= -3;
            this.r = F().d();
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

        public long g() {
            return this.s;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.p |= 4;
            this.s = j2;
        }

        /* access modifiers changed from: private */
        public void ac() {
            this.p &= -5;
            this.s = 0;
        }

        public boolean h() {
            return (this.p & 8) == 8;
        }

        public String i() {
            return this.t;
        }

        public ByteString j() {
            return ByteString.copyFromUtf8(this.t);
        }

        /* access modifiers changed from: private */
        public void b(String str) {
            if (str != null) {
                this.p |= 8;
                this.t = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ad() {
            this.p &= -9;
            this.t = F().i();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.p |= 8;
                this.t = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean l() {
            return (this.p & 16) == 16;
        }

        public String m() {
            return this.u;
        }

        public ByteString n() {
            return ByteString.copyFromUtf8(this.u);
        }

        /* access modifiers changed from: private */
        public void c(String str) {
            if (str != null) {
                this.p |= 16;
                this.u = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ae() {
            this.p &= -17;
            this.u = F().m();
        }

        /* access modifiers changed from: private */
        public void e(ByteString byteString) {
            if (byteString != null) {
                this.p |= 16;
                this.u = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean o() {
            return (this.p & 32) == 32;
        }

        public int p() {
            return this.v;
        }

        /* access modifiers changed from: private */
        public void a(int i2) {
            this.p |= 32;
            this.v = i2;
        }

        /* access modifiers changed from: private */
        public void af() {
            this.p &= -33;
            this.v = 0;
        }

        public boolean q() {
            return (this.p & 64) == 64;
        }

        public String r() {
            return this.w;
        }

        public ByteString s() {
            return ByteString.copyFromUtf8(this.w);
        }

        /* access modifiers changed from: private */
        public void d(String str) {
            if (str != null) {
                this.p |= 64;
                this.w = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ag() {
            this.p &= -65;
            this.w = F().r();
        }

        /* access modifiers changed from: private */
        public void f(ByteString byteString) {
            if (byteString != null) {
                this.p |= 64;
                this.w = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean t() {
            return (this.p & 128) == 128;
        }

        public int u() {
            return this.x;
        }

        /* access modifiers changed from: private */
        public void b(int i2) {
            this.p |= 128;
            this.x = i2;
        }

        /* access modifiers changed from: private */
        public void ah() {
            this.p &= -129;
            this.x = 0;
        }

        public boolean v() {
            return (this.p & 256) == 256;
        }

        public String w() {
            return this.y;
        }

        public ByteString x() {
            return ByteString.copyFromUtf8(this.y);
        }

        /* access modifiers changed from: private */
        public void e(String str) {
            if (str != null) {
                this.p |= 256;
                this.y = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void ai() {
            this.p &= -257;
            this.y = F().w();
        }

        /* access modifiers changed from: private */
        public void g(ByteString byteString) {
            if (byteString != null) {
                this.p |= 256;
                this.y = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean y() {
            return (this.p & 512) == 512;
        }

        public int z() {
            return this.z;
        }

        /* access modifiers changed from: private */
        public void c(int i2) {
            this.p |= 512;
            this.z = i2;
        }

        /* access modifiers changed from: private */
        public void aj() {
            this.p &= -513;
            this.z = 0;
        }

        public boolean A() {
            return (this.p & 1024) == 1024;
        }

        public long B() {
            return this.A;
        }

        /* access modifiers changed from: private */
        public void c(long j2) {
            this.p |= 1024;
            this.A = j2;
        }

        /* access modifiers changed from: private */
        public void ak() {
            this.p &= -1025;
            this.A = 0;
        }

        public boolean C() {
            return (this.p & 2048) == 2048;
        }

        public boolean D() {
            return this.B;
        }

        /* access modifiers changed from: private */
        public void a(boolean z2) {
            this.p |= 2048;
            this.B = z2;
        }

        /* access modifiers changed from: private */
        public void al() {
            this.p &= -2049;
            this.B = false;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.p & 1) == 1) {
                codedOutputStream.a(1, this.q);
            }
            if ((this.p & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.p & 4) == 4) {
                codedOutputStream.a(3, this.s);
            }
            if ((this.p & 8) == 8) {
                codedOutputStream.a(4, i());
            }
            if ((this.p & 16) == 16) {
                codedOutputStream.a(5, m());
            }
            if ((this.p & 32) == 32) {
                codedOutputStream.c(6, this.v);
            }
            if ((this.p & 64) == 64) {
                codedOutputStream.a(7, r());
            }
            if ((this.p & 128) == 128) {
                codedOutputStream.c(8, this.x);
            }
            if ((this.p & 256) == 256) {
                codedOutputStream.a(9, w());
            }
            if ((this.p & 512) == 512) {
                codedOutputStream.c(10, this.z);
            }
            if ((this.p & 1024) == 1024) {
                codedOutputStream.b(11, this.A);
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
                i3 = 0 + CodedOutputStream.f(1, this.q);
            }
            if ((this.p & 2) == 2) {
                i3 += CodedOutputStream.b(2, d());
            }
            if ((this.p & 4) == 4) {
                i3 += CodedOutputStream.f(3, this.s);
            }
            if ((this.p & 8) == 8) {
                i3 += CodedOutputStream.b(4, i());
            }
            if ((this.p & 16) == 16) {
                i3 += CodedOutputStream.b(5, m());
            }
            if ((this.p & 32) == 32) {
                i3 += CodedOutputStream.i(6, this.v);
            }
            if ((this.p & 64) == 64) {
                i3 += CodedOutputStream.b(7, r());
            }
            if ((this.p & 128) == 128) {
                i3 += CodedOutputStream.i(8, this.x);
            }
            if ((this.p & 256) == 256) {
                i3 += CodedOutputStream.b(9, w());
            }
            if ((this.p & 512) == 512) {
                i3 += CodedOutputStream.i(10, this.z);
            }
            if ((this.p & 1024) == 1024) {
                i3 += CodedOutputStream.g(11, this.A);
            }
            if ((this.p & 2048) == 2048) {
                i3 += CodedOutputStream.b(12, this.B);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UserInfo a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UserInfo) GeneratedMessageLite.a(C, byteString);
        }

        public static UserInfo a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UserInfo) GeneratedMessageLite.a(C, byteString, extensionRegistryLite);
        }

        public static UserInfo a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UserInfo) GeneratedMessageLite.a(C, bArr);
        }

        public static UserInfo a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UserInfo) GeneratedMessageLite.a(C, bArr, extensionRegistryLite);
        }

        public static UserInfo a(InputStream inputStream) throws IOException {
            return (UserInfo) GeneratedMessageLite.a(C, inputStream);
        }

        public static UserInfo a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UserInfo) GeneratedMessageLite.a(C, inputStream, extensionRegistryLite);
        }

        public static UserInfo b(InputStream inputStream) throws IOException {
            return (UserInfo) b(C, inputStream);
        }

        public static UserInfo b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UserInfo) b(C, inputStream, extensionRegistryLite);
        }

        public static UserInfo a(CodedInputStream codedInputStream) throws IOException {
            return (UserInfo) GeneratedMessageLite.b(C, codedInputStream);
        }

        public static UserInfo a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UserInfo) GeneratedMessageLite.b(C, codedInputStream, extensionRegistryLite);
        }

        public static Builder E() {
            return (Builder) C.Y();
        }

        public static Builder a(UserInfo userInfo) {
            return (Builder) ((Builder) C.Y()).b(userInfo);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UserInfo, Builder> implements UserInfoOrBuilder {
            private Builder() {
                super(UserInfo.C);
            }

            public boolean a() {
                return ((UserInfo) this.f11310a).a();
            }

            public long b() {
                return ((UserInfo) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((UserInfo) this.f11310a).a(j);
                return this;
            }

            public Builder k() {
                S();
                ((UserInfo) this.f11310a).I();
                return this;
            }

            public boolean c() {
                return ((UserInfo) this.f11310a).c();
            }

            public String d() {
                return ((UserInfo) this.f11310a).d();
            }

            public ByteString e() {
                return ((UserInfo) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((UserInfo) this.f11310a).a(str);
                return this;
            }

            public Builder E() {
                S();
                ((UserInfo) this.f11310a).ab();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((UserInfo) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((UserInfo) this.f11310a).f();
            }

            public long g() {
                return ((UserInfo) this.f11310a).g();
            }

            public Builder b(long j) {
                S();
                ((UserInfo) this.f11310a).b(j);
                return this;
            }

            public Builder F() {
                S();
                ((UserInfo) this.f11310a).ac();
                return this;
            }

            public boolean h() {
                return ((UserInfo) this.f11310a).h();
            }

            public String i() {
                return ((UserInfo) this.f11310a).i();
            }

            public ByteString j() {
                return ((UserInfo) this.f11310a).j();
            }

            public Builder b(String str) {
                S();
                ((UserInfo) this.f11310a).b(str);
                return this;
            }

            public Builder G() {
                S();
                ((UserInfo) this.f11310a).ad();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((UserInfo) this.f11310a).d(byteString);
                return this;
            }

            public boolean l() {
                return ((UserInfo) this.f11310a).l();
            }

            public String m() {
                return ((UserInfo) this.f11310a).m();
            }

            public ByteString n() {
                return ((UserInfo) this.f11310a).n();
            }

            public Builder c(String str) {
                S();
                ((UserInfo) this.f11310a).c(str);
                return this;
            }

            public Builder H() {
                S();
                ((UserInfo) this.f11310a).ae();
                return this;
            }

            public Builder c(ByteString byteString) {
                S();
                ((UserInfo) this.f11310a).e(byteString);
                return this;
            }

            public boolean o() {
                return ((UserInfo) this.f11310a).o();
            }

            public int p() {
                return ((UserInfo) this.f11310a).p();
            }

            public Builder a(int i) {
                S();
                ((UserInfo) this.f11310a).a(i);
                return this;
            }

            public Builder I() {
                S();
                ((UserInfo) this.f11310a).af();
                return this;
            }

            public boolean q() {
                return ((UserInfo) this.f11310a).q();
            }

            public String r() {
                return ((UserInfo) this.f11310a).r();
            }

            public ByteString s() {
                return ((UserInfo) this.f11310a).s();
            }

            public Builder d(String str) {
                S();
                ((UserInfo) this.f11310a).d(str);
                return this;
            }

            public Builder J() {
                S();
                ((UserInfo) this.f11310a).ag();
                return this;
            }

            public Builder d(ByteString byteString) {
                S();
                ((UserInfo) this.f11310a).f(byteString);
                return this;
            }

            public boolean t() {
                return ((UserInfo) this.f11310a).t();
            }

            public int u() {
                return ((UserInfo) this.f11310a).u();
            }

            public Builder b(int i) {
                S();
                ((UserInfo) this.f11310a).b(i);
                return this;
            }

            public Builder K() {
                S();
                ((UserInfo) this.f11310a).ah();
                return this;
            }

            public boolean v() {
                return ((UserInfo) this.f11310a).v();
            }

            public String w() {
                return ((UserInfo) this.f11310a).w();
            }

            public ByteString x() {
                return ((UserInfo) this.f11310a).x();
            }

            public Builder e(String str) {
                S();
                ((UserInfo) this.f11310a).e(str);
                return this;
            }

            public Builder L() {
                S();
                ((UserInfo) this.f11310a).ai();
                return this;
            }

            public Builder e(ByteString byteString) {
                S();
                ((UserInfo) this.f11310a).g(byteString);
                return this;
            }

            public boolean y() {
                return ((UserInfo) this.f11310a).y();
            }

            public int z() {
                return ((UserInfo) this.f11310a).z();
            }

            public Builder c(int i) {
                S();
                ((UserInfo) this.f11310a).c(i);
                return this;
            }

            public Builder M() {
                S();
                ((UserInfo) this.f11310a).aj();
                return this;
            }

            public boolean A() {
                return ((UserInfo) this.f11310a).A();
            }

            public long B() {
                return ((UserInfo) this.f11310a).B();
            }

            public Builder c(long j) {
                S();
                ((UserInfo) this.f11310a).c(j);
                return this;
            }

            public Builder N() {
                S();
                ((UserInfo) this.f11310a).ak();
                return this;
            }

            public boolean C() {
                return ((UserInfo) this.f11310a).C();
            }

            public boolean D() {
                return ((UserInfo) this.f11310a).D();
            }

            public Builder a(boolean z) {
                S();
                ((UserInfo) this.f11310a).a(z);
                return this;
            }

            public Builder O() {
                S();
                ((UserInfo) this.f11310a).al();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UserInfo();
                case IS_INITIALIZED:
                    return C;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    UserInfo userInfo = (UserInfo) obj2;
                    this.q = visitor.a(a(), this.q, userInfo.a(), userInfo.q);
                    this.r = visitor.a(c(), this.r, userInfo.c(), userInfo.r);
                    this.s = visitor.a(f(), this.s, userInfo.f(), userInfo.s);
                    this.t = visitor.a(h(), this.t, userInfo.h(), userInfo.t);
                    this.u = visitor.a(l(), this.u, userInfo.l(), userInfo.u);
                    this.v = visitor.a(o(), this.v, userInfo.o(), userInfo.v);
                    this.w = visitor.a(q(), this.w, userInfo.q(), userInfo.w);
                    this.x = visitor.a(t(), this.x, userInfo.t(), userInfo.x);
                    this.y = visitor.a(v(), this.y, userInfo.v(), userInfo.y);
                    this.z = visitor.a(y(), this.z, userInfo.y(), userInfo.z);
                    this.A = visitor.a(A(), this.A, userInfo.A(), userInfo.A);
                    this.B = visitor.a(C(), this.B, userInfo.C(), userInfo.B);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.p |= userInfo.p;
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
                                    this.q = codedInputStream.g();
                                    break;
                                case 18:
                                    String l2 = codedInputStream.l();
                                    this.p |= 2;
                                    this.r = l2;
                                    break;
                                case 24:
                                    this.p |= 4;
                                    this.s = codedInputStream.g();
                                    break;
                                case 34:
                                    String l3 = codedInputStream.l();
                                    this.p |= 8;
                                    this.t = l3;
                                    break;
                                case 42:
                                    String l4 = codedInputStream.l();
                                    this.p |= 16;
                                    this.u = l4;
                                    break;
                                case 48:
                                    this.p |= 32;
                                    this.v = codedInputStream.q();
                                    break;
                                case 58:
                                    String l5 = codedInputStream.l();
                                    this.p |= 64;
                                    this.w = l5;
                                    break;
                                case 64:
                                    this.p |= 128;
                                    this.x = codedInputStream.q();
                                    break;
                                case 74:
                                    String l6 = codedInputStream.l();
                                    this.p |= 256;
                                    this.y = l6;
                                    break;
                                case 80:
                                    this.p |= 512;
                                    this.z = codedInputStream.q();
                                    break;
                                case 88:
                                    this.p |= 1024;
                                    this.A = codedInputStream.f();
                                    break;
                                case 96:
                                    this.p |= 2048;
                                    this.B = codedInputStream.k();
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
                        synchronized (UserInfo.class) {
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

        public static UserInfo F() {
            return C;
        }

        public static Parser<UserInfo> G() {
            return C.M();
        }
    }

    public static final class CallInfo extends GeneratedMessageLite<CallInfo, Builder> implements CallInfoOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11272a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        public static final int h = 8;
        /* access modifiers changed from: private */
        public static final CallInfo u = new CallInfo();
        private static volatile Parser<CallInfo> v;
        private int i;
        private long j;
        private int k = 1;
        private int l = 1;
        private long p;
        private String q = "";
        private String r = "";
        private ByteString s = ByteString.EMPTY;
        private Internal.ProtobufList<UserInfo> t = X();

        private CallInfo() {
        }

        public boolean a() {
            return (this.i & 1) == 1;
        }

        public long b() {
            return this.j;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.i |= 1;
            this.j = j2;
        }

        /* access modifiers changed from: private */
        public void y() {
            this.i &= -2;
            this.j = 0;
        }

        public boolean c() {
            return (this.i & 2) == 2;
        }

        public CallType d() {
            CallType forNumber = CallType.forNumber(this.k);
            return forNumber == null ? CallType.SINGLE_CALL : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(CallType callType) {
            if (callType != null) {
                this.i |= 2;
                this.k = callType.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void z() {
            this.i &= -3;
            this.k = 1;
        }

        public boolean e() {
            return (this.i & 4) == 4;
        }

        public CallStatus f() {
            CallStatus forNumber = CallStatus.forNumber(this.l);
            return forNumber == null ? CallStatus.CREATING : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(CallStatus callStatus) {
            if (callStatus != null) {
                this.i |= 4;
                this.l = callStatus.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void A() {
            this.i &= -5;
            this.l = 1;
        }

        public boolean g() {
            return (this.i & 8) == 8;
        }

        public long h() {
            return this.p;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.i |= 8;
            this.p = j2;
        }

        /* access modifiers changed from: private */
        public void B() {
            this.i &= -9;
            this.p = 0;
        }

        public boolean i() {
            return (this.i & 16) == 16;
        }

        public String j() {
            return this.q;
        }

        public ByteString l() {
            return ByteString.copyFromUtf8(this.q);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.i |= 16;
                this.q = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void C() {
            this.i &= -17;
            this.q = v().j();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.i |= 16;
                this.q = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean m() {
            return (this.i & 32) == 32;
        }

        public String n() {
            return this.r;
        }

        public ByteString o() {
            return ByteString.copyFromUtf8(this.r);
        }

        /* access modifiers changed from: private */
        public void b(String str) {
            if (str != null) {
                this.i |= 32;
                this.r = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void D() {
            this.i &= -33;
            this.r = v().n();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.i |= 32;
                this.r = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean p() {
            return (this.i & 64) == 64;
        }

        public ByteString q() {
            return this.s;
        }

        /* access modifiers changed from: private */
        public void e(ByteString byteString) {
            if (byteString != null) {
                this.i |= 64;
                this.s = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void E() {
            this.i &= -65;
            this.s = v().q();
        }

        public List<UserInfo> r() {
            return this.t;
        }

        public List<? extends UserInfoOrBuilder> s() {
            return this.t;
        }

        public int t() {
            return this.t.size();
        }

        public UserInfo a(int i2) {
            return (UserInfo) this.t.get(i2);
        }

        public UserInfoOrBuilder b(int i2) {
            return (UserInfoOrBuilder) this.t.get(i2);
        }

        private void F() {
            if (!this.t.a()) {
                this.t = GeneratedMessageLite.a(this.t);
            }
        }

        /* access modifiers changed from: private */
        public void a(int i2, UserInfo userInfo) {
            if (userInfo != null) {
                F();
                this.t.set(i2, userInfo);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(int i2, UserInfo.Builder builder) {
            F();
            this.t.set(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(UserInfo userInfo) {
            if (userInfo != null) {
                F();
                this.t.add(userInfo);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void b(int i2, UserInfo userInfo) {
            if (userInfo != null) {
                F();
                this.t.add(i2, userInfo);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UserInfo.Builder builder) {
            F();
            this.t.add(builder.Z());
        }

        /* access modifiers changed from: private */
        public void b(int i2, UserInfo.Builder builder) {
            F();
            this.t.add(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(Iterable<? extends UserInfo> iterable) {
            F();
            AbstractMessageLite.a(iterable, this.t);
        }

        /* access modifiers changed from: private */
        public void G() {
            this.t = X();
        }

        /* access modifiers changed from: private */
        public void c(int i2) {
            F();
            this.t.remove(i2);
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.i & 1) == 1) {
                codedOutputStream.b(1, this.j);
            }
            if ((this.i & 2) == 2) {
                codedOutputStream.g(2, this.k);
            }
            if ((this.i & 4) == 4) {
                codedOutputStream.g(3, this.l);
            }
            if ((this.i & 8) == 8) {
                codedOutputStream.a(4, this.p);
            }
            if ((this.i & 16) == 16) {
                codedOutputStream.a(5, j());
            }
            if ((this.i & 32) == 32) {
                codedOutputStream.a(6, n());
            }
            if ((this.i & 64) == 64) {
                codedOutputStream.a(7, this.s);
            }
            for (int i2 = 0; i2 < this.t.size(); i2++) {
                codedOutputStream.a(8, (MessageLite) this.t.get(i2));
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int g2 = (this.i & 1) == 1 ? CodedOutputStream.g(1, this.j) + 0 : 0;
            if ((this.i & 2) == 2) {
                g2 += CodedOutputStream.m(2, this.k);
            }
            if ((this.i & 4) == 4) {
                g2 += CodedOutputStream.m(3, this.l);
            }
            if ((this.i & 8) == 8) {
                g2 += CodedOutputStream.f(4, this.p);
            }
            if ((this.i & 16) == 16) {
                g2 += CodedOutputStream.b(5, j());
            }
            if ((this.i & 32) == 32) {
                g2 += CodedOutputStream.b(6, n());
            }
            if ((this.i & 64) == 64) {
                g2 += CodedOutputStream.c(7, this.s);
            }
            for (int i3 = 0; i3 < this.t.size(); i3++) {
                g2 += CodedOutputStream.c(8, (MessageLite) this.t.get(i3));
            }
            int e2 = g2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static CallInfo a(ByteString byteString) throws InvalidProtocolBufferException {
            return (CallInfo) GeneratedMessageLite.a(u, byteString);
        }

        public static CallInfo a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CallInfo) GeneratedMessageLite.a(u, byteString, extensionRegistryLite);
        }

        public static CallInfo a(byte[] bArr) throws InvalidProtocolBufferException {
            return (CallInfo) GeneratedMessageLite.a(u, bArr);
        }

        public static CallInfo a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CallInfo) GeneratedMessageLite.a(u, bArr, extensionRegistryLite);
        }

        public static CallInfo a(InputStream inputStream) throws IOException {
            return (CallInfo) GeneratedMessageLite.a(u, inputStream);
        }

        public static CallInfo a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallInfo) GeneratedMessageLite.a(u, inputStream, extensionRegistryLite);
        }

        public static CallInfo b(InputStream inputStream) throws IOException {
            return (CallInfo) b(u, inputStream);
        }

        public static CallInfo b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallInfo) b(u, inputStream, extensionRegistryLite);
        }

        public static CallInfo a(CodedInputStream codedInputStream) throws IOException {
            return (CallInfo) GeneratedMessageLite.b(u, codedInputStream);
        }

        public static CallInfo a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CallInfo) GeneratedMessageLite.b(u, codedInputStream, extensionRegistryLite);
        }

        public static Builder u() {
            return (Builder) u.Y();
        }

        public static Builder a(CallInfo callInfo) {
            return (Builder) ((Builder) u.Y()).b(callInfo);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CallInfo, Builder> implements CallInfoOrBuilder {
            private Builder() {
                super(CallInfo.u);
            }

            public boolean a() {
                return ((CallInfo) this.f11310a).a();
            }

            public long b() {
                return ((CallInfo) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((CallInfo) this.f11310a).a(j);
                return this;
            }

            public Builder k() {
                S();
                ((CallInfo) this.f11310a).y();
                return this;
            }

            public boolean c() {
                return ((CallInfo) this.f11310a).c();
            }

            public CallType d() {
                return ((CallInfo) this.f11310a).d();
            }

            public Builder a(CallType callType) {
                S();
                ((CallInfo) this.f11310a).a(callType);
                return this;
            }

            public Builder s() {
                S();
                ((CallInfo) this.f11310a).z();
                return this;
            }

            public boolean e() {
                return ((CallInfo) this.f11310a).e();
            }

            public CallStatus f() {
                return ((CallInfo) this.f11310a).f();
            }

            public Builder a(CallStatus callStatus) {
                S();
                ((CallInfo) this.f11310a).a(callStatus);
                return this;
            }

            public Builder u() {
                S();
                ((CallInfo) this.f11310a).A();
                return this;
            }

            public boolean g() {
                return ((CallInfo) this.f11310a).g();
            }

            public long h() {
                return ((CallInfo) this.f11310a).h();
            }

            public Builder b(long j) {
                S();
                ((CallInfo) this.f11310a).b(j);
                return this;
            }

            public Builder v() {
                S();
                ((CallInfo) this.f11310a).B();
                return this;
            }

            public boolean i() {
                return ((CallInfo) this.f11310a).i();
            }

            public String j() {
                return ((CallInfo) this.f11310a).j();
            }

            public ByteString l() {
                return ((CallInfo) this.f11310a).l();
            }

            public Builder a(String str) {
                S();
                ((CallInfo) this.f11310a).a(str);
                return this;
            }

            public Builder w() {
                S();
                ((CallInfo) this.f11310a).C();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((CallInfo) this.f11310a).c(byteString);
                return this;
            }

            public boolean m() {
                return ((CallInfo) this.f11310a).m();
            }

            public String n() {
                return ((CallInfo) this.f11310a).n();
            }

            public ByteString o() {
                return ((CallInfo) this.f11310a).o();
            }

            public Builder b(String str) {
                S();
                ((CallInfo) this.f11310a).b(str);
                return this;
            }

            public Builder x() {
                S();
                ((CallInfo) this.f11310a).D();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((CallInfo) this.f11310a).d(byteString);
                return this;
            }

            public boolean p() {
                return ((CallInfo) this.f11310a).p();
            }

            public ByteString q() {
                return ((CallInfo) this.f11310a).q();
            }

            public Builder c(ByteString byteString) {
                S();
                ((CallInfo) this.f11310a).e(byteString);
                return this;
            }

            public Builder y() {
                S();
                ((CallInfo) this.f11310a).E();
                return this;
            }

            public List<UserInfo> r() {
                return Collections.unmodifiableList(((CallInfo) this.f11310a).r());
            }

            public int t() {
                return ((CallInfo) this.f11310a).t();
            }

            public UserInfo a(int i) {
                return ((CallInfo) this.f11310a).a(i);
            }

            public Builder a(int i, UserInfo userInfo) {
                S();
                ((CallInfo) this.f11310a).a(i, userInfo);
                return this;
            }

            public Builder a(int i, UserInfo.Builder builder) {
                S();
                ((CallInfo) this.f11310a).a(i, builder);
                return this;
            }

            public Builder a(UserInfo userInfo) {
                S();
                ((CallInfo) this.f11310a).a(userInfo);
                return this;
            }

            public Builder b(int i, UserInfo userInfo) {
                S();
                ((CallInfo) this.f11310a).b(i, userInfo);
                return this;
            }

            public Builder a(UserInfo.Builder builder) {
                S();
                ((CallInfo) this.f11310a).a(builder);
                return this;
            }

            public Builder b(int i, UserInfo.Builder builder) {
                S();
                ((CallInfo) this.f11310a).b(i, builder);
                return this;
            }

            public Builder a(Iterable<? extends UserInfo> iterable) {
                S();
                ((CallInfo) this.f11310a).a(iterable);
                return this;
            }

            public Builder z() {
                S();
                ((CallInfo) this.f11310a).G();
                return this;
            }

            public Builder b(int i) {
                S();
                ((CallInfo) this.f11310a).c(i);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new CallInfo();
                case IS_INITIALIZED:
                    return u;
                case MAKE_IMMUTABLE:
                    this.t.b();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    CallInfo callInfo = (CallInfo) obj2;
                    this.j = visitor.a(a(), this.j, callInfo.a(), callInfo.j);
                    this.k = visitor.a(c(), this.k, callInfo.c(), callInfo.k);
                    this.l = visitor.a(e(), this.l, callInfo.e(), callInfo.l);
                    this.p = visitor.a(g(), this.p, callInfo.g(), callInfo.p);
                    this.q = visitor.a(i(), this.q, callInfo.i(), callInfo.q);
                    this.r = visitor.a(m(), this.r, callInfo.m(), callInfo.r);
                    this.s = visitor.a(p(), this.s, callInfo.p(), callInfo.s);
                    this.t = visitor.a(this.t, callInfo.t);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.i |= callInfo.i;
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
                                    this.i |= 1;
                                    this.j = codedInputStream.f();
                                } else if (a2 == 16) {
                                    int r2 = codedInputStream.r();
                                    if (CallType.forNumber(r2) == null) {
                                        super.a(2, r2);
                                    } else {
                                        this.i |= 2;
                                        this.k = r2;
                                    }
                                } else if (a2 == 24) {
                                    int r3 = codedInputStream.r();
                                    if (CallStatus.forNumber(r3) == null) {
                                        super.a(3, r3);
                                    } else {
                                        this.i |= 4;
                                        this.l = r3;
                                    }
                                } else if (a2 == 32) {
                                    this.i |= 8;
                                    this.p = codedInputStream.g();
                                } else if (a2 == 42) {
                                    String l2 = codedInputStream.l();
                                    this.i |= 16;
                                    this.q = l2;
                                } else if (a2 == 50) {
                                    String l3 = codedInputStream.l();
                                    this.i |= 32;
                                    this.r = l3;
                                } else if (a2 == 58) {
                                    this.i |= 64;
                                    this.s = codedInputStream.n();
                                } else if (a2 == 66) {
                                    if (!this.t.a()) {
                                        this.t = GeneratedMessageLite.a(this.t);
                                    }
                                    this.t.add(codedInputStream.a(UserInfo.G(), extensionRegistryLite));
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
                    if (v == null) {
                        synchronized (CallInfo.class) {
                            if (v == null) {
                                v = new GeneratedMessageLite.DefaultInstanceBasedParser(u);
                            }
                        }
                    }
                    return v;
                default:
                    throw new UnsupportedOperationException();
            }
            return u;
        }

        static {
            u.P();
        }

        public static CallInfo v() {
            return u;
        }

        public static Parser<CallInfo> w() {
            return u.M();
        }
    }

    public static final class XmqRTSExchange extends GeneratedMessageLite<XmqRTSExchange, Builder> implements XmqRTSExchangeOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11292a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        /* access modifiers changed from: private */
        public static final XmqRTSExchange q = new XmqRTSExchange();
        private static volatile Parser<XmqRTSExchange> r;
        private int g;
        private String h = "";
        private String i = "";
        private long j;
        private String k = "";
        private RTSMessage l;
        private long p;

        private XmqRTSExchange() {
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
        public void u() {
            this.g &= -2;
            this.h = r().b();
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
        public void v() {
            this.g &= -3;
            this.i = r().e();
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
        public void w() {
            this.g &= -5;
            this.j = 0;
        }

        public boolean i() {
            return (this.g & 8) == 8;
        }

        public String j() {
            return this.k;
        }

        public ByteString l() {
            return ByteString.copyFromUtf8(this.k);
        }

        /* access modifiers changed from: private */
        public void c(String str) {
            if (str != null) {
                this.g |= 8;
                this.k = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void x() {
            this.g &= -9;
            this.k = r().j();
        }

        /* access modifiers changed from: private */
        public void e(ByteString byteString) {
            if (byteString != null) {
                this.g |= 8;
                this.k = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean m() {
            return (this.g & 16) == 16;
        }

        public RTSMessage n() {
            return this.l == null ? RTSMessage.r() : this.l;
        }

        /* access modifiers changed from: private */
        public void a(RTSMessage rTSMessage) {
            if (rTSMessage != null) {
                this.l = rTSMessage;
                this.g |= 16;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(RTSMessage.Builder builder) {
            this.l = (RTSMessage) builder.Z();
            this.g |= 16;
        }

        /* access modifiers changed from: private */
        public void b(RTSMessage rTSMessage) {
            if (this.l == null || this.l == RTSMessage.r()) {
                this.l = rTSMessage;
            } else {
                this.l = (RTSMessage) ((RTSMessage.Builder) RTSMessage.a(this.l).b(rTSMessage)).Y();
            }
            this.g |= 16;
        }

        /* access modifiers changed from: private */
        public void y() {
            this.l = null;
            this.g &= -17;
        }

        public boolean o() {
            return (this.g & 32) == 32;
        }

        public long p() {
            return this.p;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.g |= 32;
            this.p = j2;
        }

        /* access modifiers changed from: private */
        public void z() {
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
                codedOutputStream.a(4, j());
            }
            if ((this.g & 16) == 16) {
                codedOutputStream.a(5, (MessageLite) n());
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
                i3 += CodedOutputStream.b(4, j());
            }
            if ((this.g & 16) == 16) {
                i3 += CodedOutputStream.c(5, (MessageLite) n());
            }
            if ((this.g & 32) == 32) {
                i3 += CodedOutputStream.f(6, this.p);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static XmqRTSExchange a(ByteString byteString) throws InvalidProtocolBufferException {
            return (XmqRTSExchange) GeneratedMessageLite.a(q, byteString);
        }

        public static XmqRTSExchange a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XmqRTSExchange) GeneratedMessageLite.a(q, byteString, extensionRegistryLite);
        }

        public static XmqRTSExchange a(byte[] bArr) throws InvalidProtocolBufferException {
            return (XmqRTSExchange) GeneratedMessageLite.a(q, bArr);
        }

        public static XmqRTSExchange a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (XmqRTSExchange) GeneratedMessageLite.a(q, bArr, extensionRegistryLite);
        }

        public static XmqRTSExchange a(InputStream inputStream) throws IOException {
            return (XmqRTSExchange) GeneratedMessageLite.a(q, inputStream);
        }

        public static XmqRTSExchange a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XmqRTSExchange) GeneratedMessageLite.a(q, inputStream, extensionRegistryLite);
        }

        public static XmqRTSExchange b(InputStream inputStream) throws IOException {
            return (XmqRTSExchange) b(q, inputStream);
        }

        public static XmqRTSExchange b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XmqRTSExchange) b(q, inputStream, extensionRegistryLite);
        }

        public static XmqRTSExchange a(CodedInputStream codedInputStream) throws IOException {
            return (XmqRTSExchange) GeneratedMessageLite.b(q, codedInputStream);
        }

        public static XmqRTSExchange a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (XmqRTSExchange) GeneratedMessageLite.b(q, codedInputStream, extensionRegistryLite);
        }

        public static Builder q() {
            return (Builder) q.Y();
        }

        public static Builder a(XmqRTSExchange xmqRTSExchange) {
            return (Builder) ((Builder) q.Y()).b(xmqRTSExchange);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<XmqRTSExchange, Builder> implements XmqRTSExchangeOrBuilder {
            private Builder() {
                super(XmqRTSExchange.q);
            }

            public boolean a() {
                return ((XmqRTSExchange) this.f11310a).a();
            }

            public String b() {
                return ((XmqRTSExchange) this.f11310a).b();
            }

            public ByteString c() {
                return ((XmqRTSExchange) this.f11310a).c();
            }

            public Builder a(String str) {
                S();
                ((XmqRTSExchange) this.f11310a).a(str);
                return this;
            }

            public Builder k() {
                S();
                ((XmqRTSExchange) this.f11310a).u();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((XmqRTSExchange) this.f11310a).c(byteString);
                return this;
            }

            public boolean d() {
                return ((XmqRTSExchange) this.f11310a).d();
            }

            public String e() {
                return ((XmqRTSExchange) this.f11310a).e();
            }

            public ByteString f() {
                return ((XmqRTSExchange) this.f11310a).f();
            }

            public Builder b(String str) {
                S();
                ((XmqRTSExchange) this.f11310a).b(str);
                return this;
            }

            public Builder q() {
                S();
                ((XmqRTSExchange) this.f11310a).v();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((XmqRTSExchange) this.f11310a).d(byteString);
                return this;
            }

            public boolean g() {
                return ((XmqRTSExchange) this.f11310a).g();
            }

            public long h() {
                return ((XmqRTSExchange) this.f11310a).h();
            }

            public Builder a(long j) {
                S();
                ((XmqRTSExchange) this.f11310a).a(j);
                return this;
            }

            public Builder r() {
                S();
                ((XmqRTSExchange) this.f11310a).w();
                return this;
            }

            public boolean i() {
                return ((XmqRTSExchange) this.f11310a).i();
            }

            public String j() {
                return ((XmqRTSExchange) this.f11310a).j();
            }

            public ByteString l() {
                return ((XmqRTSExchange) this.f11310a).l();
            }

            public Builder c(String str) {
                S();
                ((XmqRTSExchange) this.f11310a).c(str);
                return this;
            }

            public Builder s() {
                S();
                ((XmqRTSExchange) this.f11310a).x();
                return this;
            }

            public Builder c(ByteString byteString) {
                S();
                ((XmqRTSExchange) this.f11310a).e(byteString);
                return this;
            }

            public boolean m() {
                return ((XmqRTSExchange) this.f11310a).m();
            }

            public RTSMessage n() {
                return ((XmqRTSExchange) this.f11310a).n();
            }

            public Builder a(RTSMessage rTSMessage) {
                S();
                ((XmqRTSExchange) this.f11310a).a(rTSMessage);
                return this;
            }

            public Builder a(RTSMessage.Builder builder) {
                S();
                ((XmqRTSExchange) this.f11310a).a(builder);
                return this;
            }

            public Builder b(RTSMessage rTSMessage) {
                S();
                ((XmqRTSExchange) this.f11310a).b(rTSMessage);
                return this;
            }

            public Builder t() {
                S();
                ((XmqRTSExchange) this.f11310a).y();
                return this;
            }

            public boolean o() {
                return ((XmqRTSExchange) this.f11310a).o();
            }

            public long p() {
                return ((XmqRTSExchange) this.f11310a).p();
            }

            public Builder b(long j) {
                S();
                ((XmqRTSExchange) this.f11310a).b(j);
                return this;
            }

            public Builder u() {
                S();
                ((XmqRTSExchange) this.f11310a).z();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new XmqRTSExchange();
                case IS_INITIALIZED:
                    return q;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    XmqRTSExchange xmqRTSExchange = (XmqRTSExchange) obj2;
                    this.h = visitor.a(a(), this.h, xmqRTSExchange.a(), xmqRTSExchange.h);
                    this.i = visitor.a(d(), this.i, xmqRTSExchange.d(), xmqRTSExchange.i);
                    this.j = visitor.a(g(), this.j, xmqRTSExchange.g(), xmqRTSExchange.j);
                    this.k = visitor.a(i(), this.k, xmqRTSExchange.i(), xmqRTSExchange.k);
                    this.l = (RTSMessage) visitor.a(this.l, xmqRTSExchange.l);
                    this.p = visitor.a(o(), this.p, xmqRTSExchange.o(), xmqRTSExchange.p);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.g |= xmqRTSExchange.g;
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
                                } else if (a2 == 34) {
                                    String l4 = codedInputStream.l();
                                    this.g |= 8;
                                    this.k = l4;
                                } else if (a2 == 42) {
                                    RTSMessage.Builder builder = (this.g & 16) == 16 ? (RTSMessage.Builder) this.l.Y() : null;
                                    this.l = (RTSMessage) codedInputStream.a(RTSMessage.s(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.l);
                                        this.l = (RTSMessage) builder.Y();
                                    }
                                    this.g |= 16;
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
                        synchronized (XmqRTSExchange.class) {
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

        public static XmqRTSExchange r() {
            return q;
        }

        public static Parser<XmqRTSExchange> s() {
            return q.M();
        }
    }

    public static final class RTSMessage extends GeneratedMessageLite<RTSMessage, Builder> implements RTSMessageOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11285a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        /* access modifiers changed from: private */
        public static final RTSMessage s = new RTSMessage();
        private static volatile Parser<RTSMessage> t;
        private int h;
        private int i = 1;
        private long j;
        private int k = 1;
        private long l;
        private String p = "";
        private ByteString q = ByteString.EMPTY;
        private long r;

        private RTSMessage() {
        }

        public boolean a() {
            return (this.h & 1) == 1;
        }

        public RTSMessageType b() {
            RTSMessageType forNumber = RTSMessageType.forNumber(this.i);
            return forNumber == null ? RTSMessageType.CREATE_REQUEST : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(RTSMessageType rTSMessageType) {
            if (rTSMessageType != null) {
                this.h |= 1;
                this.i = rTSMessageType.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void u() {
            this.h &= -2;
            this.i = 1;
        }

        public boolean c() {
            return (this.h & 2) == 2;
        }

        public long d() {
            return this.j;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.h |= 2;
            this.j = j2;
        }

        /* access modifiers changed from: private */
        public void v() {
            this.h &= -3;
            this.j = 0;
        }

        public boolean e() {
            return (this.h & 4) == 4;
        }

        public CallType f() {
            CallType forNumber = CallType.forNumber(this.k);
            return forNumber == null ? CallType.SINGLE_CALL : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(CallType callType) {
            if (callType != null) {
                this.h |= 4;
                this.k = callType.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void w() {
            this.h &= -5;
            this.k = 1;
        }

        public boolean g() {
            return (this.h & 8) == 8;
        }

        public long h() {
            return this.l;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.h |= 8;
            this.l = j2;
        }

        /* access modifiers changed from: private */
        public void x() {
            this.h &= -9;
            this.l = 0;
        }

        public boolean i() {
            return (this.h & 16) == 16;
        }

        public String j() {
            return this.p;
        }

        public ByteString l() {
            return ByteString.copyFromUtf8(this.p);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.h |= 16;
                this.p = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void y() {
            this.h &= -17;
            this.p = r().j();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.h |= 16;
                this.p = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean m() {
            return (this.h & 32) == 32;
        }

        public ByteString n() {
            return this.q;
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.h |= 32;
                this.q = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void z() {
            this.h &= -33;
            this.q = r().n();
        }

        public boolean o() {
            return (this.h & 64) == 64;
        }

        public long p() {
            return this.r;
        }

        /* access modifiers changed from: private */
        public void c(long j2) {
            this.h |= 64;
            this.r = j2;
        }

        /* access modifiers changed from: private */
        public void A() {
            this.h &= -65;
            this.r = 0;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.h & 1) == 1) {
                codedOutputStream.g(1, this.i);
            }
            if ((this.h & 2) == 2) {
                codedOutputStream.b(2, this.j);
            }
            if ((this.h & 4) == 4) {
                codedOutputStream.g(3, this.k);
            }
            if ((this.h & 8) == 8) {
                codedOutputStream.a(4, this.l);
            }
            if ((this.h & 16) == 16) {
                codedOutputStream.a(5, j());
            }
            if ((this.h & 32) == 32) {
                codedOutputStream.a(6, this.q);
            }
            if ((this.h & 64) == 64) {
                codedOutputStream.a(7, this.r);
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
                i3 = 0 + CodedOutputStream.m(1, this.i);
            }
            if ((this.h & 2) == 2) {
                i3 += CodedOutputStream.g(2, this.j);
            }
            if ((this.h & 4) == 4) {
                i3 += CodedOutputStream.m(3, this.k);
            }
            if ((this.h & 8) == 8) {
                i3 += CodedOutputStream.f(4, this.l);
            }
            if ((this.h & 16) == 16) {
                i3 += CodedOutputStream.b(5, j());
            }
            if ((this.h & 32) == 32) {
                i3 += CodedOutputStream.c(6, this.q);
            }
            if ((this.h & 64) == 64) {
                i3 += CodedOutputStream.f(7, this.r);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static RTSMessage a(ByteString byteString) throws InvalidProtocolBufferException {
            return (RTSMessage) GeneratedMessageLite.a(s, byteString);
        }

        public static RTSMessage a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RTSMessage) GeneratedMessageLite.a(s, byteString, extensionRegistryLite);
        }

        public static RTSMessage a(byte[] bArr) throws InvalidProtocolBufferException {
            return (RTSMessage) GeneratedMessageLite.a(s, bArr);
        }

        public static RTSMessage a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RTSMessage) GeneratedMessageLite.a(s, bArr, extensionRegistryLite);
        }

        public static RTSMessage a(InputStream inputStream) throws IOException {
            return (RTSMessage) GeneratedMessageLite.a(s, inputStream);
        }

        public static RTSMessage a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RTSMessage) GeneratedMessageLite.a(s, inputStream, extensionRegistryLite);
        }

        public static RTSMessage b(InputStream inputStream) throws IOException {
            return (RTSMessage) b(s, inputStream);
        }

        public static RTSMessage b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RTSMessage) b(s, inputStream, extensionRegistryLite);
        }

        public static RTSMessage a(CodedInputStream codedInputStream) throws IOException {
            return (RTSMessage) GeneratedMessageLite.b(s, codedInputStream);
        }

        public static RTSMessage a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RTSMessage) GeneratedMessageLite.b(s, codedInputStream, extensionRegistryLite);
        }

        public static Builder q() {
            return (Builder) s.Y();
        }

        public static Builder a(RTSMessage rTSMessage) {
            return (Builder) ((Builder) s.Y()).b(rTSMessage);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<RTSMessage, Builder> implements RTSMessageOrBuilder {
            private Builder() {
                super(RTSMessage.s);
            }

            public boolean a() {
                return ((RTSMessage) this.f11310a).a();
            }

            public RTSMessageType b() {
                return ((RTSMessage) this.f11310a).b();
            }

            public Builder a(RTSMessageType rTSMessageType) {
                S();
                ((RTSMessage) this.f11310a).a(rTSMessageType);
                return this;
            }

            public Builder k() {
                S();
                ((RTSMessage) this.f11310a).u();
                return this;
            }

            public boolean c() {
                return ((RTSMessage) this.f11310a).c();
            }

            public long d() {
                return ((RTSMessage) this.f11310a).d();
            }

            public Builder a(long j) {
                S();
                ((RTSMessage) this.f11310a).a(j);
                return this;
            }

            public Builder q() {
                S();
                ((RTSMessage) this.f11310a).v();
                return this;
            }

            public boolean e() {
                return ((RTSMessage) this.f11310a).e();
            }

            public CallType f() {
                return ((RTSMessage) this.f11310a).f();
            }

            public Builder a(CallType callType) {
                S();
                ((RTSMessage) this.f11310a).a(callType);
                return this;
            }

            public Builder r() {
                S();
                ((RTSMessage) this.f11310a).w();
                return this;
            }

            public boolean g() {
                return ((RTSMessage) this.f11310a).g();
            }

            public long h() {
                return ((RTSMessage) this.f11310a).h();
            }

            public Builder b(long j) {
                S();
                ((RTSMessage) this.f11310a).b(j);
                return this;
            }

            public Builder s() {
                S();
                ((RTSMessage) this.f11310a).x();
                return this;
            }

            public boolean i() {
                return ((RTSMessage) this.f11310a).i();
            }

            public String j() {
                return ((RTSMessage) this.f11310a).j();
            }

            public ByteString l() {
                return ((RTSMessage) this.f11310a).l();
            }

            public Builder a(String str) {
                S();
                ((RTSMessage) this.f11310a).a(str);
                return this;
            }

            public Builder t() {
                S();
                ((RTSMessage) this.f11310a).y();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((RTSMessage) this.f11310a).c(byteString);
                return this;
            }

            public boolean m() {
                return ((RTSMessage) this.f11310a).m();
            }

            public ByteString n() {
                return ((RTSMessage) this.f11310a).n();
            }

            public Builder b(ByteString byteString) {
                S();
                ((RTSMessage) this.f11310a).d(byteString);
                return this;
            }

            public Builder u() {
                S();
                ((RTSMessage) this.f11310a).z();
                return this;
            }

            public boolean o() {
                return ((RTSMessage) this.f11310a).o();
            }

            public long p() {
                return ((RTSMessage) this.f11310a).p();
            }

            public Builder c(long j) {
                S();
                ((RTSMessage) this.f11310a).c(j);
                return this;
            }

            public Builder v() {
                S();
                ((RTSMessage) this.f11310a).A();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new RTSMessage();
                case IS_INITIALIZED:
                    return s;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    RTSMessage rTSMessage = (RTSMessage) obj2;
                    this.i = visitor.a(a(), this.i, rTSMessage.a(), rTSMessage.i);
                    this.j = visitor.a(c(), this.j, rTSMessage.c(), rTSMessage.j);
                    this.k = visitor.a(e(), this.k, rTSMessage.e(), rTSMessage.k);
                    this.l = visitor.a(g(), this.l, rTSMessage.g(), rTSMessage.l);
                    this.p = visitor.a(i(), this.p, rTSMessage.i(), rTSMessage.p);
                    this.q = visitor.a(m(), this.q, rTSMessage.m(), rTSMessage.q);
                    this.r = visitor.a(o(), this.r, rTSMessage.o(), rTSMessage.r);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.h |= rTSMessage.h;
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
                                    int r2 = codedInputStream.r();
                                    if (RTSMessageType.forNumber(r2) == null) {
                                        super.a(1, r2);
                                    } else {
                                        this.h = 1 | this.h;
                                        this.i = r2;
                                    }
                                } else if (a2 == 16) {
                                    this.h |= 2;
                                    this.j = codedInputStream.f();
                                } else if (a2 == 24) {
                                    int r3 = codedInputStream.r();
                                    if (CallType.forNumber(r3) == null) {
                                        super.a(3, r3);
                                    } else {
                                        this.h |= 4;
                                        this.k = r3;
                                    }
                                } else if (a2 == 32) {
                                    this.h |= 8;
                                    this.l = codedInputStream.g();
                                } else if (a2 == 42) {
                                    String l2 = codedInputStream.l();
                                    this.h |= 16;
                                    this.p = l2;
                                } else if (a2 == 50) {
                                    this.h |= 32;
                                    this.q = codedInputStream.n();
                                } else if (a2 == 56) {
                                    this.h |= 64;
                                    this.r = codedInputStream.g();
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
                        synchronized (RTSMessage.class) {
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

        public static RTSMessage r() {
            return s;
        }

        public static Parser<RTSMessage> s() {
            return s.M();
        }
    }

    public static final class CreateRequest extends GeneratedMessageLite<CreateRequest, Builder> implements CreateRequestOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11275a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        /* access modifiers changed from: private */
        public static final CreateRequest j = new CreateRequest();
        private static volatile Parser<CreateRequest> k;
        private int e;
        private int f = 1;
        private Internal.ProtobufList<UserInfo> g = X();
        private String h = "";
        private ByteString i = ByteString.EMPTY;

        private CreateRequest() {
        }

        public boolean a() {
            return (this.e & 1) == 1;
        }

        public StreamDataType b() {
            StreamDataType forNumber = StreamDataType.forNumber(this.f);
            return forNumber == null ? StreamDataType.A_STREAM : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(StreamDataType streamDataType) {
            if (streamDataType != null) {
                this.e |= 1;
                this.f = streamDataType.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void p() {
            this.e &= -2;
            this.f = 1;
        }

        public List<UserInfo> c() {
            return this.g;
        }

        public List<? extends UserInfoOrBuilder> d() {
            return this.g;
        }

        public int e() {
            return this.g.size();
        }

        public UserInfo a(int i2) {
            return (UserInfo) this.g.get(i2);
        }

        public UserInfoOrBuilder b(int i2) {
            return (UserInfoOrBuilder) this.g.get(i2);
        }

        private void q() {
            if (!this.g.a()) {
                this.g = GeneratedMessageLite.a(this.g);
            }
        }

        /* access modifiers changed from: private */
        public void a(int i2, UserInfo userInfo) {
            if (userInfo != null) {
                q();
                this.g.set(i2, userInfo);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(int i2, UserInfo.Builder builder) {
            q();
            this.g.set(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(UserInfo userInfo) {
            if (userInfo != null) {
                q();
                this.g.add(userInfo);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void b(int i2, UserInfo userInfo) {
            if (userInfo != null) {
                q();
                this.g.add(i2, userInfo);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UserInfo.Builder builder) {
            q();
            this.g.add(builder.Z());
        }

        /* access modifiers changed from: private */
        public void b(int i2, UserInfo.Builder builder) {
            q();
            this.g.add(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(Iterable<? extends UserInfo> iterable) {
            q();
            AbstractMessageLite.a(iterable, this.g);
        }

        /* access modifiers changed from: private */
        public void r() {
            this.g = X();
        }

        /* access modifiers changed from: private */
        public void c(int i2) {
            q();
            this.g.remove(i2);
        }

        public boolean f() {
            return (this.e & 2) == 2;
        }

        public String g() {
            return this.h;
        }

        public ByteString h() {
            return ByteString.copyFromUtf8(this.h);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.e |= 2;
                this.h = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void s() {
            this.e &= -3;
            this.h = m().g();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.e |= 2;
                this.h = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean i() {
            return (this.e & 4) == 4;
        }

        public ByteString j() {
            return this.i;
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.e |= 4;
                this.i = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void t() {
            this.e &= -5;
            this.i = m().j();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.e & 1) == 1) {
                codedOutputStream.g(1, this.f);
            }
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                codedOutputStream.a(2, (MessageLite) this.g.get(i2));
            }
            if ((this.e & 2) == 2) {
                codedOutputStream.a(3, g());
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
            int m = (this.e & 1) == 1 ? CodedOutputStream.m(1, this.f) + 0 : 0;
            for (int i3 = 0; i3 < this.g.size(); i3++) {
                m += CodedOutputStream.c(2, (MessageLite) this.g.get(i3));
            }
            if ((this.e & 2) == 2) {
                m += CodedOutputStream.b(3, g());
            }
            if ((this.e & 4) == 4) {
                m += CodedOutputStream.c(4, this.i);
            }
            int e2 = m + this.n.e();
            this.o = e2;
            return e2;
        }

        public static CreateRequest a(ByteString byteString) throws InvalidProtocolBufferException {
            return (CreateRequest) GeneratedMessageLite.a(j, byteString);
        }

        public static CreateRequest a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CreateRequest) GeneratedMessageLite.a(j, byteString, extensionRegistryLite);
        }

        public static CreateRequest a(byte[] bArr) throws InvalidProtocolBufferException {
            return (CreateRequest) GeneratedMessageLite.a(j, bArr);
        }

        public static CreateRequest a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CreateRequest) GeneratedMessageLite.a(j, bArr, extensionRegistryLite);
        }

        public static CreateRequest a(InputStream inputStream) throws IOException {
            return (CreateRequest) GeneratedMessageLite.a(j, inputStream);
        }

        public static CreateRequest a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CreateRequest) GeneratedMessageLite.a(j, inputStream, extensionRegistryLite);
        }

        public static CreateRequest b(InputStream inputStream) throws IOException {
            return (CreateRequest) b(j, inputStream);
        }

        public static CreateRequest b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CreateRequest) b(j, inputStream, extensionRegistryLite);
        }

        public static CreateRequest a(CodedInputStream codedInputStream) throws IOException {
            return (CreateRequest) GeneratedMessageLite.b(j, codedInputStream);
        }

        public static CreateRequest a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CreateRequest) GeneratedMessageLite.b(j, codedInputStream, extensionRegistryLite);
        }

        public static Builder l() {
            return (Builder) j.Y();
        }

        public static Builder a(CreateRequest createRequest) {
            return (Builder) ((Builder) j.Y()).b(createRequest);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CreateRequest, Builder> implements CreateRequestOrBuilder {
            private Builder() {
                super(CreateRequest.j);
            }

            public boolean a() {
                return ((CreateRequest) this.f11310a).a();
            }

            public StreamDataType b() {
                return ((CreateRequest) this.f11310a).b();
            }

            public Builder a(StreamDataType streamDataType) {
                S();
                ((CreateRequest) this.f11310a).a(streamDataType);
                return this;
            }

            public Builder d() {
                S();
                ((CreateRequest) this.f11310a).p();
                return this;
            }

            public List<UserInfo> c() {
                return Collections.unmodifiableList(((CreateRequest) this.f11310a).c());
            }

            public int e() {
                return ((CreateRequest) this.f11310a).e();
            }

            public UserInfo a(int i) {
                return ((CreateRequest) this.f11310a).a(i);
            }

            public Builder a(int i, UserInfo userInfo) {
                S();
                ((CreateRequest) this.f11310a).a(i, userInfo);
                return this;
            }

            public Builder a(int i, UserInfo.Builder builder) {
                S();
                ((CreateRequest) this.f11310a).a(i, builder);
                return this;
            }

            public Builder a(UserInfo userInfo) {
                S();
                ((CreateRequest) this.f11310a).a(userInfo);
                return this;
            }

            public Builder b(int i, UserInfo userInfo) {
                S();
                ((CreateRequest) this.f11310a).b(i, userInfo);
                return this;
            }

            public Builder a(UserInfo.Builder builder) {
                S();
                ((CreateRequest) this.f11310a).a(builder);
                return this;
            }

            public Builder b(int i, UserInfo.Builder builder) {
                S();
                ((CreateRequest) this.f11310a).b(i, builder);
                return this;
            }

            public Builder a(Iterable<? extends UserInfo> iterable) {
                S();
                ((CreateRequest) this.f11310a).a(iterable);
                return this;
            }

            public Builder k() {
                S();
                ((CreateRequest) this.f11310a).r();
                return this;
            }

            public Builder b(int i) {
                S();
                ((CreateRequest) this.f11310a).c(i);
                return this;
            }

            public boolean f() {
                return ((CreateRequest) this.f11310a).f();
            }

            public String g() {
                return ((CreateRequest) this.f11310a).g();
            }

            public ByteString h() {
                return ((CreateRequest) this.f11310a).h();
            }

            public Builder a(String str) {
                S();
                ((CreateRequest) this.f11310a).a(str);
                return this;
            }

            public Builder l() {
                S();
                ((CreateRequest) this.f11310a).s();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((CreateRequest) this.f11310a).c(byteString);
                return this;
            }

            public boolean i() {
                return ((CreateRequest) this.f11310a).i();
            }

            public ByteString j() {
                return ((CreateRequest) this.f11310a).j();
            }

            public Builder b(ByteString byteString) {
                S();
                ((CreateRequest) this.f11310a).d(byteString);
                return this;
            }

            public Builder m() {
                S();
                ((CreateRequest) this.f11310a).t();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new CreateRequest();
                case IS_INITIALIZED:
                    return j;
                case MAKE_IMMUTABLE:
                    this.g.b();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    CreateRequest createRequest = (CreateRequest) obj2;
                    this.f = visitor.a(a(), this.f, createRequest.a(), createRequest.f);
                    this.g = visitor.a(this.g, createRequest.g);
                    this.h = visitor.a(f(), this.h, createRequest.f(), createRequest.h);
                    this.i = visitor.a(i(), this.i, createRequest.i(), createRequest.i);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.e |= createRequest.e;
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
                                    int r = codedInputStream.r();
                                    if (StreamDataType.forNumber(r) == null) {
                                        super.a(1, r);
                                    } else {
                                        this.e = 1 | this.e;
                                        this.f = r;
                                    }
                                } else if (a2 == 18) {
                                    if (!this.g.a()) {
                                        this.g = GeneratedMessageLite.a(this.g);
                                    }
                                    this.g.add(codedInputStream.a(UserInfo.G(), extensionRegistryLite));
                                } else if (a2 == 26) {
                                    String l = codedInputStream.l();
                                    this.e |= 2;
                                    this.h = l;
                                } else if (a2 == 34) {
                                    this.e |= 4;
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
                        synchronized (CreateRequest.class) {
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

        public static CreateRequest m() {
            return j;
        }

        public static Parser<CreateRequest> n() {
            return j.M();
        }
    }

    public static final class InviteRequest extends GeneratedMessageLite<InviteRequest, Builder> implements InviteRequestOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11277a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        /* access modifiers changed from: private */
        public static final InviteRequest j = new InviteRequest();
        private static volatile Parser<InviteRequest> k;
        private int e;
        private int f = 1;
        private Internal.ProtobufList<UserInfo> g = X();
        private String h = "";
        private ByteString i = ByteString.EMPTY;

        private InviteRequest() {
        }

        public boolean a() {
            return (this.e & 1) == 1;
        }

        public StreamDataType b() {
            StreamDataType forNumber = StreamDataType.forNumber(this.f);
            return forNumber == null ? StreamDataType.A_STREAM : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(StreamDataType streamDataType) {
            if (streamDataType != null) {
                this.e |= 1;
                this.f = streamDataType.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void p() {
            this.e &= -2;
            this.f = 1;
        }

        public List<UserInfo> c() {
            return this.g;
        }

        public List<? extends UserInfoOrBuilder> d() {
            return this.g;
        }

        public int e() {
            return this.g.size();
        }

        public UserInfo a(int i2) {
            return (UserInfo) this.g.get(i2);
        }

        public UserInfoOrBuilder b(int i2) {
            return (UserInfoOrBuilder) this.g.get(i2);
        }

        private void q() {
            if (!this.g.a()) {
                this.g = GeneratedMessageLite.a(this.g);
            }
        }

        /* access modifiers changed from: private */
        public void a(int i2, UserInfo userInfo) {
            if (userInfo != null) {
                q();
                this.g.set(i2, userInfo);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(int i2, UserInfo.Builder builder) {
            q();
            this.g.set(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(UserInfo userInfo) {
            if (userInfo != null) {
                q();
                this.g.add(userInfo);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void b(int i2, UserInfo userInfo) {
            if (userInfo != null) {
                q();
                this.g.add(i2, userInfo);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UserInfo.Builder builder) {
            q();
            this.g.add(builder.Z());
        }

        /* access modifiers changed from: private */
        public void b(int i2, UserInfo.Builder builder) {
            q();
            this.g.add(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(Iterable<? extends UserInfo> iterable) {
            q();
            AbstractMessageLite.a(iterable, this.g);
        }

        /* access modifiers changed from: private */
        public void r() {
            this.g = X();
        }

        /* access modifiers changed from: private */
        public void c(int i2) {
            q();
            this.g.remove(i2);
        }

        public boolean f() {
            return (this.e & 2) == 2;
        }

        public String g() {
            return this.h;
        }

        public ByteString h() {
            return ByteString.copyFromUtf8(this.h);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.e |= 2;
                this.h = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void s() {
            this.e &= -3;
            this.h = m().g();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.e |= 2;
                this.h = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean i() {
            return (this.e & 4) == 4;
        }

        public ByteString j() {
            return this.i;
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.e |= 4;
                this.i = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void t() {
            this.e &= -5;
            this.i = m().j();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.e & 1) == 1) {
                codedOutputStream.g(1, this.f);
            }
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                codedOutputStream.a(2, (MessageLite) this.g.get(i2));
            }
            if ((this.e & 2) == 2) {
                codedOutputStream.a(3, g());
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
            int m = (this.e & 1) == 1 ? CodedOutputStream.m(1, this.f) + 0 : 0;
            for (int i3 = 0; i3 < this.g.size(); i3++) {
                m += CodedOutputStream.c(2, (MessageLite) this.g.get(i3));
            }
            if ((this.e & 2) == 2) {
                m += CodedOutputStream.b(3, g());
            }
            if ((this.e & 4) == 4) {
                m += CodedOutputStream.c(4, this.i);
            }
            int e2 = m + this.n.e();
            this.o = e2;
            return e2;
        }

        public static InviteRequest a(ByteString byteString) throws InvalidProtocolBufferException {
            return (InviteRequest) GeneratedMessageLite.a(j, byteString);
        }

        public static InviteRequest a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InviteRequest) GeneratedMessageLite.a(j, byteString, extensionRegistryLite);
        }

        public static InviteRequest a(byte[] bArr) throws InvalidProtocolBufferException {
            return (InviteRequest) GeneratedMessageLite.a(j, bArr);
        }

        public static InviteRequest a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InviteRequest) GeneratedMessageLite.a(j, bArr, extensionRegistryLite);
        }

        public static InviteRequest a(InputStream inputStream) throws IOException {
            return (InviteRequest) GeneratedMessageLite.a(j, inputStream);
        }

        public static InviteRequest a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InviteRequest) GeneratedMessageLite.a(j, inputStream, extensionRegistryLite);
        }

        public static InviteRequest b(InputStream inputStream) throws IOException {
            return (InviteRequest) b(j, inputStream);
        }

        public static InviteRequest b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InviteRequest) b(j, inputStream, extensionRegistryLite);
        }

        public static InviteRequest a(CodedInputStream codedInputStream) throws IOException {
            return (InviteRequest) GeneratedMessageLite.b(j, codedInputStream);
        }

        public static InviteRequest a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InviteRequest) GeneratedMessageLite.b(j, codedInputStream, extensionRegistryLite);
        }

        public static Builder l() {
            return (Builder) j.Y();
        }

        public static Builder a(InviteRequest inviteRequest) {
            return (Builder) ((Builder) j.Y()).b(inviteRequest);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<InviteRequest, Builder> implements InviteRequestOrBuilder {
            private Builder() {
                super(InviteRequest.j);
            }

            public boolean a() {
                return ((InviteRequest) this.f11310a).a();
            }

            public StreamDataType b() {
                return ((InviteRequest) this.f11310a).b();
            }

            public Builder a(StreamDataType streamDataType) {
                S();
                ((InviteRequest) this.f11310a).a(streamDataType);
                return this;
            }

            public Builder d() {
                S();
                ((InviteRequest) this.f11310a).p();
                return this;
            }

            public List<UserInfo> c() {
                return Collections.unmodifiableList(((InviteRequest) this.f11310a).c());
            }

            public int e() {
                return ((InviteRequest) this.f11310a).e();
            }

            public UserInfo a(int i) {
                return ((InviteRequest) this.f11310a).a(i);
            }

            public Builder a(int i, UserInfo userInfo) {
                S();
                ((InviteRequest) this.f11310a).a(i, userInfo);
                return this;
            }

            public Builder a(int i, UserInfo.Builder builder) {
                S();
                ((InviteRequest) this.f11310a).a(i, builder);
                return this;
            }

            public Builder a(UserInfo userInfo) {
                S();
                ((InviteRequest) this.f11310a).a(userInfo);
                return this;
            }

            public Builder b(int i, UserInfo userInfo) {
                S();
                ((InviteRequest) this.f11310a).b(i, userInfo);
                return this;
            }

            public Builder a(UserInfo.Builder builder) {
                S();
                ((InviteRequest) this.f11310a).a(builder);
                return this;
            }

            public Builder b(int i, UserInfo.Builder builder) {
                S();
                ((InviteRequest) this.f11310a).b(i, builder);
                return this;
            }

            public Builder a(Iterable<? extends UserInfo> iterable) {
                S();
                ((InviteRequest) this.f11310a).a(iterable);
                return this;
            }

            public Builder k() {
                S();
                ((InviteRequest) this.f11310a).r();
                return this;
            }

            public Builder b(int i) {
                S();
                ((InviteRequest) this.f11310a).c(i);
                return this;
            }

            public boolean f() {
                return ((InviteRequest) this.f11310a).f();
            }

            public String g() {
                return ((InviteRequest) this.f11310a).g();
            }

            public ByteString h() {
                return ((InviteRequest) this.f11310a).h();
            }

            public Builder a(String str) {
                S();
                ((InviteRequest) this.f11310a).a(str);
                return this;
            }

            public Builder l() {
                S();
                ((InviteRequest) this.f11310a).s();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((InviteRequest) this.f11310a).c(byteString);
                return this;
            }

            public boolean i() {
                return ((InviteRequest) this.f11310a).i();
            }

            public ByteString j() {
                return ((InviteRequest) this.f11310a).j();
            }

            public Builder b(ByteString byteString) {
                S();
                ((InviteRequest) this.f11310a).d(byteString);
                return this;
            }

            public Builder m() {
                S();
                ((InviteRequest) this.f11310a).t();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new InviteRequest();
                case IS_INITIALIZED:
                    return j;
                case MAKE_IMMUTABLE:
                    this.g.b();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    InviteRequest inviteRequest = (InviteRequest) obj2;
                    this.f = visitor.a(a(), this.f, inviteRequest.a(), inviteRequest.f);
                    this.g = visitor.a(this.g, inviteRequest.g);
                    this.h = visitor.a(f(), this.h, inviteRequest.f(), inviteRequest.h);
                    this.i = visitor.a(i(), this.i, inviteRequest.i(), inviteRequest.i);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.e |= inviteRequest.e;
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
                                    int r = codedInputStream.r();
                                    if (StreamDataType.forNumber(r) == null) {
                                        super.a(1, r);
                                    } else {
                                        this.e = 1 | this.e;
                                        this.f = r;
                                    }
                                } else if (a2 == 18) {
                                    if (!this.g.a()) {
                                        this.g = GeneratedMessageLite.a(this.g);
                                    }
                                    this.g.add(codedInputStream.a(UserInfo.G(), extensionRegistryLite));
                                } else if (a2 == 26) {
                                    String l = codedInputStream.l();
                                    this.e |= 2;
                                    this.h = l;
                                } else if (a2 == 34) {
                                    this.e |= 4;
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
                        synchronized (InviteRequest.class) {
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

        public static InviteRequest m() {
            return j;
        }

        public static Parser<InviteRequest> n() {
            return j.M();
        }
    }

    public static final class InviteResponse extends GeneratedMessageLite<InviteResponse, Builder> implements InviteResponseOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11278a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        /* access modifiers changed from: private */
        public static final InviteResponse j = new InviteResponse();
        private static volatile Parser<InviteResponse> k;
        private int e;
        private int f;
        private String g = "";
        private String h = "";
        private UserInfo i;

        private InviteResponse() {
        }

        public boolean a() {
            return (this.e & 1) == 1;
        }

        public RTSResult b() {
            RTSResult forNumber = RTSResult.forNumber(this.f);
            return forNumber == null ? RTSResult.SUCC : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(RTSResult rTSResult) {
            if (rTSResult != null) {
                this.e |= 1;
                this.f = rTSResult.getNumber();
                return;
            }
            throw new NullPointerException();
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
        public void r() {
            this.e &= -5;
            this.h = m().g();
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

        public UserInfo j() {
            return this.i == null ? UserInfo.F() : this.i;
        }

        /* access modifiers changed from: private */
        public void a(UserInfo userInfo) {
            if (userInfo != null) {
                this.i = userInfo;
                this.e |= 8;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UserInfo.Builder builder) {
            this.i = (UserInfo) builder.Z();
            this.e |= 8;
        }

        /* access modifiers changed from: private */
        public void b(UserInfo userInfo) {
            if (this.i == null || this.i == UserInfo.F()) {
                this.i = userInfo;
            } else {
                this.i = (UserInfo) ((UserInfo.Builder) UserInfo.a(this.i).b(userInfo)).Y();
            }
            this.e |= 8;
        }

        /* access modifiers changed from: private */
        public void s() {
            this.i = null;
            this.e &= -9;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.e & 1) == 1) {
                codedOutputStream.g(1, this.f);
            }
            if ((this.e & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.e & 4) == 4) {
                codedOutputStream.a(3, g());
            }
            if ((this.e & 8) == 8) {
                codedOutputStream.a(4, (MessageLite) j());
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
                i3 += CodedOutputStream.b(2, d());
            }
            if ((this.e & 4) == 4) {
                i3 += CodedOutputStream.b(3, g());
            }
            if ((this.e & 8) == 8) {
                i3 += CodedOutputStream.c(4, (MessageLite) j());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static InviteResponse a(ByteString byteString) throws InvalidProtocolBufferException {
            return (InviteResponse) GeneratedMessageLite.a(j, byteString);
        }

        public static InviteResponse a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InviteResponse) GeneratedMessageLite.a(j, byteString, extensionRegistryLite);
        }

        public static InviteResponse a(byte[] bArr) throws InvalidProtocolBufferException {
            return (InviteResponse) GeneratedMessageLite.a(j, bArr);
        }

        public static InviteResponse a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (InviteResponse) GeneratedMessageLite.a(j, bArr, extensionRegistryLite);
        }

        public static InviteResponse a(InputStream inputStream) throws IOException {
            return (InviteResponse) GeneratedMessageLite.a(j, inputStream);
        }

        public static InviteResponse a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InviteResponse) GeneratedMessageLite.a(j, inputStream, extensionRegistryLite);
        }

        public static InviteResponse b(InputStream inputStream) throws IOException {
            return (InviteResponse) b(j, inputStream);
        }

        public static InviteResponse b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InviteResponse) b(j, inputStream, extensionRegistryLite);
        }

        public static InviteResponse a(CodedInputStream codedInputStream) throws IOException {
            return (InviteResponse) GeneratedMessageLite.b(j, codedInputStream);
        }

        public static InviteResponse a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (InviteResponse) GeneratedMessageLite.b(j, codedInputStream, extensionRegistryLite);
        }

        public static Builder l() {
            return (Builder) j.Y();
        }

        public static Builder a(InviteResponse inviteResponse) {
            return (Builder) ((Builder) j.Y()).b(inviteResponse);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<InviteResponse, Builder> implements InviteResponseOrBuilder {
            private Builder() {
                super(InviteResponse.j);
            }

            public boolean a() {
                return ((InviteResponse) this.f11310a).a();
            }

            public RTSResult b() {
                return ((InviteResponse) this.f11310a).b();
            }

            public Builder a(RTSResult rTSResult) {
                S();
                ((InviteResponse) this.f11310a).a(rTSResult);
                return this;
            }

            public Builder k() {
                S();
                ((InviteResponse) this.f11310a).p();
                return this;
            }

            public boolean c() {
                return ((InviteResponse) this.f11310a).c();
            }

            public String d() {
                return ((InviteResponse) this.f11310a).d();
            }

            public ByteString e() {
                return ((InviteResponse) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((InviteResponse) this.f11310a).a(str);
                return this;
            }

            public Builder l() {
                S();
                ((InviteResponse) this.f11310a).q();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((InviteResponse) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((InviteResponse) this.f11310a).f();
            }

            public String g() {
                return ((InviteResponse) this.f11310a).g();
            }

            public ByteString h() {
                return ((InviteResponse) this.f11310a).h();
            }

            public Builder b(String str) {
                S();
                ((InviteResponse) this.f11310a).b(str);
                return this;
            }

            public Builder m() {
                S();
                ((InviteResponse) this.f11310a).r();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((InviteResponse) this.f11310a).d(byteString);
                return this;
            }

            public boolean i() {
                return ((InviteResponse) this.f11310a).i();
            }

            public UserInfo j() {
                return ((InviteResponse) this.f11310a).j();
            }

            public Builder a(UserInfo userInfo) {
                S();
                ((InviteResponse) this.f11310a).a(userInfo);
                return this;
            }

            public Builder a(UserInfo.Builder builder) {
                S();
                ((InviteResponse) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UserInfo userInfo) {
                S();
                ((InviteResponse) this.f11310a).b(userInfo);
                return this;
            }

            public Builder n() {
                S();
                ((InviteResponse) this.f11310a).s();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new InviteResponse();
                case IS_INITIALIZED:
                    return j;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    InviteResponse inviteResponse = (InviteResponse) obj2;
                    this.f = visitor.a(a(), this.f, inviteResponse.a(), inviteResponse.f);
                    this.g = visitor.a(c(), this.g, inviteResponse.c(), inviteResponse.g);
                    this.h = visitor.a(f(), this.h, inviteResponse.f(), inviteResponse.h);
                    this.i = (UserInfo) visitor.a(this.i, inviteResponse.i);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.e |= inviteResponse.e;
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
                                    int r = codedInputStream.r();
                                    if (RTSResult.forNumber(r) == null) {
                                        super.a(1, r);
                                    } else {
                                        this.e = 1 | this.e;
                                        this.f = r;
                                    }
                                } else if (a2 == 18) {
                                    String l = codedInputStream.l();
                                    this.e |= 2;
                                    this.g = l;
                                } else if (a2 == 26) {
                                    String l2 = codedInputStream.l();
                                    this.e |= 4;
                                    this.h = l2;
                                } else if (a2 == 34) {
                                    UserInfo.Builder builder = (this.e & 8) == 8 ? (UserInfo.Builder) this.i.Y() : null;
                                    this.i = (UserInfo) codedInputStream.a(UserInfo.G(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.i);
                                        this.i = (UserInfo) builder.Y();
                                    }
                                    this.e |= 8;
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
                        synchronized (InviteResponse.class) {
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

        public static InviteResponse m() {
            return j;
        }

        public static Parser<InviteResponse> n() {
            return j.M();
        }
    }

    public static final class CreateResponse extends GeneratedMessageLite<CreateResponse, Builder> implements CreateResponseOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11276a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        /* access modifiers changed from: private */
        public static final CreateResponse j = new CreateResponse();
        private static volatile Parser<CreateResponse> k;
        private int e;
        private int f;
        private String g = "";
        private String h = "";
        private Internal.ProtobufList<UserInfo> i = X();

        private CreateResponse() {
        }

        public boolean a() {
            return (this.e & 1) == 1;
        }

        public RTSResult b() {
            RTSResult forNumber = RTSResult.forNumber(this.f);
            return forNumber == null ? RTSResult.SUCC : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(RTSResult rTSResult) {
            if (rTSResult != null) {
                this.e |= 1;
                this.f = rTSResult.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void q() {
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

        public List<UserInfo> i() {
            return this.i;
        }

        public List<? extends UserInfoOrBuilder> j() {
            return this.i;
        }

        public int l() {
            return this.i.size();
        }

        public UserInfo a(int i2) {
            return (UserInfo) this.i.get(i2);
        }

        public UserInfoOrBuilder b(int i2) {
            return (UserInfoOrBuilder) this.i.get(i2);
        }

        private void t() {
            if (!this.i.a()) {
                this.i = GeneratedMessageLite.a(this.i);
            }
        }

        /* access modifiers changed from: private */
        public void a(int i2, UserInfo userInfo) {
            if (userInfo != null) {
                t();
                this.i.set(i2, userInfo);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(int i2, UserInfo.Builder builder) {
            t();
            this.i.set(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(UserInfo userInfo) {
            if (userInfo != null) {
                t();
                this.i.add(userInfo);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void b(int i2, UserInfo userInfo) {
            if (userInfo != null) {
                t();
                this.i.add(i2, userInfo);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UserInfo.Builder builder) {
            t();
            this.i.add(builder.Z());
        }

        /* access modifiers changed from: private */
        public void b(int i2, UserInfo.Builder builder) {
            t();
            this.i.add(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(Iterable<? extends UserInfo> iterable) {
            t();
            AbstractMessageLite.a(iterable, this.i);
        }

        /* access modifiers changed from: private */
        public void u() {
            this.i = X();
        }

        /* access modifiers changed from: private */
        public void c(int i2) {
            t();
            this.i.remove(i2);
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.e & 1) == 1) {
                codedOutputStream.g(1, this.f);
            }
            if ((this.e & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.e & 4) == 4) {
                codedOutputStream.a(3, g());
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
            int m = (this.e & 1) == 1 ? CodedOutputStream.m(1, this.f) + 0 : 0;
            if ((this.e & 2) == 2) {
                m += CodedOutputStream.b(2, d());
            }
            if ((this.e & 4) == 4) {
                m += CodedOutputStream.b(3, g());
            }
            for (int i3 = 0; i3 < this.i.size(); i3++) {
                m += CodedOutputStream.c(4, (MessageLite) this.i.get(i3));
            }
            int e2 = m + this.n.e();
            this.o = e2;
            return e2;
        }

        public static CreateResponse a(ByteString byteString) throws InvalidProtocolBufferException {
            return (CreateResponse) GeneratedMessageLite.a(j, byteString);
        }

        public static CreateResponse a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CreateResponse) GeneratedMessageLite.a(j, byteString, extensionRegistryLite);
        }

        public static CreateResponse a(byte[] bArr) throws InvalidProtocolBufferException {
            return (CreateResponse) GeneratedMessageLite.a(j, bArr);
        }

        public static CreateResponse a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CreateResponse) GeneratedMessageLite.a(j, bArr, extensionRegistryLite);
        }

        public static CreateResponse a(InputStream inputStream) throws IOException {
            return (CreateResponse) GeneratedMessageLite.a(j, inputStream);
        }

        public static CreateResponse a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CreateResponse) GeneratedMessageLite.a(j, inputStream, extensionRegistryLite);
        }

        public static CreateResponse b(InputStream inputStream) throws IOException {
            return (CreateResponse) b(j, inputStream);
        }

        public static CreateResponse b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CreateResponse) b(j, inputStream, extensionRegistryLite);
        }

        public static CreateResponse a(CodedInputStream codedInputStream) throws IOException {
            return (CreateResponse) GeneratedMessageLite.b(j, codedInputStream);
        }

        public static CreateResponse a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CreateResponse) GeneratedMessageLite.b(j, codedInputStream, extensionRegistryLite);
        }

        public static Builder m() {
            return (Builder) j.Y();
        }

        public static Builder a(CreateResponse createResponse) {
            return (Builder) ((Builder) j.Y()).b(createResponse);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CreateResponse, Builder> implements CreateResponseOrBuilder {
            private Builder() {
                super(CreateResponse.j);
            }

            public boolean a() {
                return ((CreateResponse) this.f11310a).a();
            }

            public RTSResult b() {
                return ((CreateResponse) this.f11310a).b();
            }

            public Builder a(RTSResult rTSResult) {
                S();
                ((CreateResponse) this.f11310a).a(rTSResult);
                return this;
            }

            public Builder j() {
                S();
                ((CreateResponse) this.f11310a).q();
                return this;
            }

            public boolean c() {
                return ((CreateResponse) this.f11310a).c();
            }

            public String d() {
                return ((CreateResponse) this.f11310a).d();
            }

            public ByteString e() {
                return ((CreateResponse) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((CreateResponse) this.f11310a).a(str);
                return this;
            }

            public Builder k() {
                S();
                ((CreateResponse) this.f11310a).r();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((CreateResponse) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((CreateResponse) this.f11310a).f();
            }

            public String g() {
                return ((CreateResponse) this.f11310a).g();
            }

            public ByteString h() {
                return ((CreateResponse) this.f11310a).h();
            }

            public Builder b(String str) {
                S();
                ((CreateResponse) this.f11310a).b(str);
                return this;
            }

            public Builder m() {
                S();
                ((CreateResponse) this.f11310a).s();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((CreateResponse) this.f11310a).d(byteString);
                return this;
            }

            public List<UserInfo> i() {
                return Collections.unmodifiableList(((CreateResponse) this.f11310a).i());
            }

            public int l() {
                return ((CreateResponse) this.f11310a).l();
            }

            public UserInfo a(int i) {
                return ((CreateResponse) this.f11310a).a(i);
            }

            public Builder a(int i, UserInfo userInfo) {
                S();
                ((CreateResponse) this.f11310a).a(i, userInfo);
                return this;
            }

            public Builder a(int i, UserInfo.Builder builder) {
                S();
                ((CreateResponse) this.f11310a).a(i, builder);
                return this;
            }

            public Builder a(UserInfo userInfo) {
                S();
                ((CreateResponse) this.f11310a).a(userInfo);
                return this;
            }

            public Builder b(int i, UserInfo userInfo) {
                S();
                ((CreateResponse) this.f11310a).b(i, userInfo);
                return this;
            }

            public Builder a(UserInfo.Builder builder) {
                S();
                ((CreateResponse) this.f11310a).a(builder);
                return this;
            }

            public Builder b(int i, UserInfo.Builder builder) {
                S();
                ((CreateResponse) this.f11310a).b(i, builder);
                return this;
            }

            public Builder a(Iterable<? extends UserInfo> iterable) {
                S();
                ((CreateResponse) this.f11310a).a(iterable);
                return this;
            }

            public Builder n() {
                S();
                ((CreateResponse) this.f11310a).u();
                return this;
            }

            public Builder b(int i) {
                S();
                ((CreateResponse) this.f11310a).c(i);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new CreateResponse();
                case IS_INITIALIZED:
                    return j;
                case MAKE_IMMUTABLE:
                    this.i.b();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    CreateResponse createResponse = (CreateResponse) obj2;
                    this.f = visitor.a(a(), this.f, createResponse.a(), createResponse.f);
                    this.g = visitor.a(c(), this.g, createResponse.c(), createResponse.g);
                    this.h = visitor.a(f(), this.h, createResponse.f(), createResponse.h);
                    this.i = visitor.a(this.i, createResponse.i);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.e |= createResponse.e;
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
                                    int r = codedInputStream.r();
                                    if (RTSResult.forNumber(r) == null) {
                                        super.a(1, r);
                                    } else {
                                        this.e = 1 | this.e;
                                        this.f = r;
                                    }
                                } else if (a2 == 18) {
                                    String l = codedInputStream.l();
                                    this.e |= 2;
                                    this.g = l;
                                } else if (a2 == 26) {
                                    String l2 = codedInputStream.l();
                                    this.e |= 4;
                                    this.h = l2;
                                } else if (a2 == 34) {
                                    if (!this.i.a()) {
                                        this.i = GeneratedMessageLite.a(this.i);
                                    }
                                    this.i.add(codedInputStream.a(UserInfo.G(), extensionRegistryLite));
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
                        synchronized (CreateResponse.class) {
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

        public static CreateResponse n() {
            return j;
        }

        public static Parser<CreateResponse> o() {
            return j.M();
        }
    }

    public static final class UpdateRequest extends GeneratedMessageLite<UpdateRequest, Builder> implements UpdateRequestOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11287a = 1;
        /* access modifiers changed from: private */
        public static final UpdateRequest d = new UpdateRequest();
        private static volatile Parser<UpdateRequest> e;
        private int b;
        private UserInfo c;

        private UpdateRequest() {
        }

        public boolean a() {
            return (this.b & 1) == 1;
        }

        public UserInfo b() {
            return this.c == null ? UserInfo.F() : this.c;
        }

        /* access modifiers changed from: private */
        public void a(UserInfo userInfo) {
            if (userInfo != null) {
                this.c = userInfo;
                this.b |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UserInfo.Builder builder) {
            this.c = (UserInfo) builder.Z();
            this.b |= 1;
        }

        /* access modifiers changed from: private */
        public void b(UserInfo userInfo) {
            if (this.c == null || this.c == UserInfo.F()) {
                this.c = userInfo;
            } else {
                this.c = (UserInfo) ((UserInfo.Builder) UserInfo.a(this.c).b(userInfo)).Y();
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

        public static UpdateRequest a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UpdateRequest) GeneratedMessageLite.a(d, byteString);
        }

        public static UpdateRequest a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateRequest) GeneratedMessageLite.a(d, byteString, extensionRegistryLite);
        }

        public static UpdateRequest a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UpdateRequest) GeneratedMessageLite.a(d, bArr);
        }

        public static UpdateRequest a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateRequest) GeneratedMessageLite.a(d, bArr, extensionRegistryLite);
        }

        public static UpdateRequest a(InputStream inputStream) throws IOException {
            return (UpdateRequest) GeneratedMessageLite.a(d, inputStream);
        }

        public static UpdateRequest a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateRequest) GeneratedMessageLite.a(d, inputStream, extensionRegistryLite);
        }

        public static UpdateRequest b(InputStream inputStream) throws IOException {
            return (UpdateRequest) b(d, inputStream);
        }

        public static UpdateRequest b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateRequest) b(d, inputStream, extensionRegistryLite);
        }

        public static UpdateRequest a(CodedInputStream codedInputStream) throws IOException {
            return (UpdateRequest) GeneratedMessageLite.b(d, codedInputStream);
        }

        public static UpdateRequest a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateRequest) GeneratedMessageLite.b(d, codedInputStream, extensionRegistryLite);
        }

        public static Builder c() {
            return (Builder) d.Y();
        }

        public static Builder a(UpdateRequest updateRequest) {
            return (Builder) ((Builder) d.Y()).b(updateRequest);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UpdateRequest, Builder> implements UpdateRequestOrBuilder {
            private Builder() {
                super(UpdateRequest.d);
            }

            public boolean a() {
                return ((UpdateRequest) this.f11310a).a();
            }

            public UserInfo b() {
                return ((UpdateRequest) this.f11310a).b();
            }

            public Builder a(UserInfo userInfo) {
                S();
                ((UpdateRequest) this.f11310a).a(userInfo);
                return this;
            }

            public Builder a(UserInfo.Builder builder) {
                S();
                ((UpdateRequest) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UserInfo userInfo) {
                S();
                ((UpdateRequest) this.f11310a).b(userInfo);
                return this;
            }

            public Builder c() {
                S();
                ((UpdateRequest) this.f11310a).g();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UpdateRequest();
                case IS_INITIALIZED:
                    return d;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    UpdateRequest updateRequest = (UpdateRequest) obj2;
                    this.c = (UserInfo) visitor.a(this.c, updateRequest.c);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.b |= updateRequest.b;
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
                                    UserInfo.Builder builder = (this.b & 1) == 1 ? (UserInfo.Builder) this.c.Y() : null;
                                    this.c = (UserInfo) codedInputStream.a(UserInfo.G(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.c);
                                        this.c = (UserInfo) builder.Y();
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
                    if (e == null) {
                        synchronized (UpdateRequest.class) {
                            if (e == null) {
                                e = new GeneratedMessageLite.DefaultInstanceBasedParser(d);
                            }
                        }
                    }
                    return e;
                default:
                    throw new UnsupportedOperationException();
            }
            return d;
        }

        static {
            d.P();
        }

        public static UpdateRequest d() {
            return d;
        }

        public static Parser<UpdateRequest> e() {
            return d.M();
        }
    }

    public static final class UpdateResponse extends GeneratedMessageLite<UpdateResponse, Builder> implements UpdateResponseOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11288a = 1;
        /* access modifiers changed from: private */
        public static final UpdateResponse d = new UpdateResponse();
        private static volatile Parser<UpdateResponse> e;
        private int b;
        private int c;

        private UpdateResponse() {
        }

        public boolean a() {
            return (this.b & 1) == 1;
        }

        public RTSResult b() {
            RTSResult forNumber = RTSResult.forNumber(this.c);
            return forNumber == null ? RTSResult.SUCC : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(RTSResult rTSResult) {
            if (rTSResult != null) {
                this.b |= 1;
                this.c = rTSResult.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void g() {
            this.b &= -2;
            this.c = 0;
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

        public static UpdateResponse a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UpdateResponse) GeneratedMessageLite.a(d, byteString);
        }

        public static UpdateResponse a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateResponse) GeneratedMessageLite.a(d, byteString, extensionRegistryLite);
        }

        public static UpdateResponse a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UpdateResponse) GeneratedMessageLite.a(d, bArr);
        }

        public static UpdateResponse a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateResponse) GeneratedMessageLite.a(d, bArr, extensionRegistryLite);
        }

        public static UpdateResponse a(InputStream inputStream) throws IOException {
            return (UpdateResponse) GeneratedMessageLite.a(d, inputStream);
        }

        public static UpdateResponse a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateResponse) GeneratedMessageLite.a(d, inputStream, extensionRegistryLite);
        }

        public static UpdateResponse b(InputStream inputStream) throws IOException {
            return (UpdateResponse) b(d, inputStream);
        }

        public static UpdateResponse b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateResponse) b(d, inputStream, extensionRegistryLite);
        }

        public static UpdateResponse a(CodedInputStream codedInputStream) throws IOException {
            return (UpdateResponse) GeneratedMessageLite.b(d, codedInputStream);
        }

        public static UpdateResponse a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateResponse) GeneratedMessageLite.b(d, codedInputStream, extensionRegistryLite);
        }

        public static Builder c() {
            return (Builder) d.Y();
        }

        public static Builder a(UpdateResponse updateResponse) {
            return (Builder) ((Builder) d.Y()).b(updateResponse);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UpdateResponse, Builder> implements UpdateResponseOrBuilder {
            private Builder() {
                super(UpdateResponse.d);
            }

            public boolean a() {
                return ((UpdateResponse) this.f11310a).a();
            }

            public RTSResult b() {
                return ((UpdateResponse) this.f11310a).b();
            }

            public Builder a(RTSResult rTSResult) {
                S();
                ((UpdateResponse) this.f11310a).a(rTSResult);
                return this;
            }

            public Builder c() {
                S();
                ((UpdateResponse) this.f11310a).g();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UpdateResponse();
                case IS_INITIALIZED:
                    return d;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    UpdateResponse updateResponse = (UpdateResponse) obj2;
                    this.c = visitor.a(a(), this.c, updateResponse.a(), updateResponse.c);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.b |= updateResponse.b;
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
                                    int r = codedInputStream.r();
                                    if (RTSResult.forNumber(r) == null) {
                                        super.a(1, r);
                                    } else {
                                        this.b = 1 | this.b;
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
                    if (e == null) {
                        synchronized (UpdateResponse.class) {
                            if (e == null) {
                                e = new GeneratedMessageLite.DefaultInstanceBasedParser(d);
                            }
                        }
                    }
                    return e;
                default:
                    throw new UnsupportedOperationException();
            }
            return d;
        }

        static {
            d.P();
        }

        public static UpdateResponse d() {
            return d;
        }

        public static Parser<UpdateResponse> e() {
            return d.M();
        }
    }

    public static final class PingRequest extends GeneratedMessageLite<PingRequest, Builder> implements PingRequestOrBuilder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final PingRequest f11283a = new PingRequest();
        private static volatile Parser<PingRequest> b;

        private PingRequest() {
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i = this.o;
            if (i != -1) {
                return i;
            }
            int e = this.n.e() + 0;
            this.o = e;
            return e;
        }

        public static PingRequest a(ByteString byteString) throws InvalidProtocolBufferException {
            return (PingRequest) GeneratedMessageLite.a(f11283a, byteString);
        }

        public static PingRequest a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PingRequest) GeneratedMessageLite.a(f11283a, byteString, extensionRegistryLite);
        }

        public static PingRequest a(byte[] bArr) throws InvalidProtocolBufferException {
            return (PingRequest) GeneratedMessageLite.a(f11283a, bArr);
        }

        public static PingRequest a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PingRequest) GeneratedMessageLite.a(f11283a, bArr, extensionRegistryLite);
        }

        public static PingRequest a(InputStream inputStream) throws IOException {
            return (PingRequest) GeneratedMessageLite.a(f11283a, inputStream);
        }

        public static PingRequest a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PingRequest) GeneratedMessageLite.a(f11283a, inputStream, extensionRegistryLite);
        }

        public static PingRequest b(InputStream inputStream) throws IOException {
            return (PingRequest) b(f11283a, inputStream);
        }

        public static PingRequest b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PingRequest) b(f11283a, inputStream, extensionRegistryLite);
        }

        public static PingRequest a(CodedInputStream codedInputStream) throws IOException {
            return (PingRequest) GeneratedMessageLite.b(f11283a, codedInputStream);
        }

        public static PingRequest a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PingRequest) GeneratedMessageLite.b(f11283a, codedInputStream, extensionRegistryLite);
        }

        public static Builder a() {
            return (Builder) f11283a.Y();
        }

        public static Builder a(PingRequest pingRequest) {
            return (Builder) ((Builder) f11283a.Y()).b(pingRequest);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PingRequest, Builder> implements PingRequestOrBuilder {
            private Builder() {
                super(PingRequest.f11283a);
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new PingRequest();
                case IS_INITIALIZED:
                    return f11283a;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    PingRequest pingRequest = (PingRequest) obj2;
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.f11318a;
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 == 0 || !a(a2, codedInputStream)) {
                                z = true;
                            }
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
                    if (b == null) {
                        synchronized (PingRequest.class) {
                            if (b == null) {
                                b = new GeneratedMessageLite.DefaultInstanceBasedParser(f11283a);
                            }
                        }
                    }
                    return b;
                default:
                    throw new UnsupportedOperationException();
            }
            return f11283a;
        }

        static {
            f11283a.P();
        }

        public static PingRequest b() {
            return f11283a;
        }

        public static Parser<PingRequest> c() {
            return f11283a.M();
        }
    }

    public static final class PingResponse extends GeneratedMessageLite<PingResponse, Builder> implements PingResponseOrBuilder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final PingResponse f11284a = new PingResponse();
        private static volatile Parser<PingResponse> b;

        private PingResponse() {
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i = this.o;
            if (i != -1) {
                return i;
            }
            int e = this.n.e() + 0;
            this.o = e;
            return e;
        }

        public static PingResponse a(ByteString byteString) throws InvalidProtocolBufferException {
            return (PingResponse) GeneratedMessageLite.a(f11284a, byteString);
        }

        public static PingResponse a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PingResponse) GeneratedMessageLite.a(f11284a, byteString, extensionRegistryLite);
        }

        public static PingResponse a(byte[] bArr) throws InvalidProtocolBufferException {
            return (PingResponse) GeneratedMessageLite.a(f11284a, bArr);
        }

        public static PingResponse a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PingResponse) GeneratedMessageLite.a(f11284a, bArr, extensionRegistryLite);
        }

        public static PingResponse a(InputStream inputStream) throws IOException {
            return (PingResponse) GeneratedMessageLite.a(f11284a, inputStream);
        }

        public static PingResponse a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PingResponse) GeneratedMessageLite.a(f11284a, inputStream, extensionRegistryLite);
        }

        public static PingResponse b(InputStream inputStream) throws IOException {
            return (PingResponse) b(f11284a, inputStream);
        }

        public static PingResponse b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PingResponse) b(f11284a, inputStream, extensionRegistryLite);
        }

        public static PingResponse a(CodedInputStream codedInputStream) throws IOException {
            return (PingResponse) GeneratedMessageLite.b(f11284a, codedInputStream);
        }

        public static PingResponse a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PingResponse) GeneratedMessageLite.b(f11284a, codedInputStream, extensionRegistryLite);
        }

        public static Builder a() {
            return (Builder) f11284a.Y();
        }

        public static Builder a(PingResponse pingResponse) {
            return (Builder) ((Builder) f11284a.Y()).b(pingResponse);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PingResponse, Builder> implements PingResponseOrBuilder {
            private Builder() {
                super(PingResponse.f11284a);
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new PingResponse();
                case IS_INITIALIZED:
                    return f11284a;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    PingResponse pingResponse = (PingResponse) obj2;
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.f11318a;
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int a2 = codedInputStream.a();
                            if (a2 == 0 || !a(a2, codedInputStream)) {
                                z = true;
                            }
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
                    if (b == null) {
                        synchronized (PingResponse.class) {
                            if (b == null) {
                                b = new GeneratedMessageLite.DefaultInstanceBasedParser(f11284a);
                            }
                        }
                    }
                    return b;
                default:
                    throw new UnsupportedOperationException();
            }
            return f11284a;
        }

        static {
            f11284a.P();
        }

        public static PingResponse b() {
            return f11284a;
        }

        public static Parser<PingResponse> c() {
            return f11284a.M();
        }
    }

    public static final class ByeRequest extends GeneratedMessageLite<ByeRequest, Builder> implements ByeRequestOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11270a = 1;
        /* access modifiers changed from: private */
        public static final ByeRequest d = new ByeRequest();
        private static volatile Parser<ByeRequest> e;
        private int b;
        private String c = "";

        private ByeRequest() {
        }

        public boolean a() {
            return (this.b & 1) == 1;
        }

        public String b() {
            return this.c;
        }

        public ByteString c() {
            return ByteString.copyFromUtf8(this.c);
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            if (str != null) {
                this.b |= 1;
                this.c = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void h() {
            this.b &= -2;
            this.c = e().b();
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.b |= 1;
                this.c = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.b & 1) == 1) {
                codedOutputStream.a(1, b());
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
                i2 = 0 + CodedOutputStream.b(1, b());
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static ByeRequest a(ByteString byteString) throws InvalidProtocolBufferException {
            return (ByeRequest) GeneratedMessageLite.a(d, byteString);
        }

        public static ByeRequest a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ByeRequest) GeneratedMessageLite.a(d, byteString, extensionRegistryLite);
        }

        public static ByeRequest a(byte[] bArr) throws InvalidProtocolBufferException {
            return (ByeRequest) GeneratedMessageLite.a(d, bArr);
        }

        public static ByeRequest a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ByeRequest) GeneratedMessageLite.a(d, bArr, extensionRegistryLite);
        }

        public static ByeRequest a(InputStream inputStream) throws IOException {
            return (ByeRequest) GeneratedMessageLite.a(d, inputStream);
        }

        public static ByeRequest a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ByeRequest) GeneratedMessageLite.a(d, inputStream, extensionRegistryLite);
        }

        public static ByeRequest b(InputStream inputStream) throws IOException {
            return (ByeRequest) b(d, inputStream);
        }

        public static ByeRequest b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ByeRequest) b(d, inputStream, extensionRegistryLite);
        }

        public static ByeRequest a(CodedInputStream codedInputStream) throws IOException {
            return (ByeRequest) GeneratedMessageLite.b(d, codedInputStream);
        }

        public static ByeRequest a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ByeRequest) GeneratedMessageLite.b(d, codedInputStream, extensionRegistryLite);
        }

        public static Builder d() {
            return (Builder) d.Y();
        }

        public static Builder a(ByeRequest byeRequest) {
            return (Builder) ((Builder) d.Y()).b(byeRequest);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ByeRequest, Builder> implements ByeRequestOrBuilder {
            private Builder() {
                super(ByeRequest.d);
            }

            public boolean a() {
                return ((ByeRequest) this.f11310a).a();
            }

            public String b() {
                return ((ByeRequest) this.f11310a).b();
            }

            public ByteString c() {
                return ((ByeRequest) this.f11310a).c();
            }

            public Builder a(String str) {
                S();
                ((ByeRequest) this.f11310a).a(str);
                return this;
            }

            public Builder d() {
                S();
                ((ByeRequest) this.f11310a).h();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((ByeRequest) this.f11310a).c(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new ByeRequest();
                case IS_INITIALIZED:
                    return d;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ByeRequest byeRequest = (ByeRequest) obj2;
                    this.c = visitor.a(a(), this.c, byeRequest.a(), byeRequest.c);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.b |= byeRequest.b;
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
                                    this.b = 1 | this.b;
                                    this.c = l;
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
                    if (e == null) {
                        synchronized (ByeRequest.class) {
                            if (e == null) {
                                e = new GeneratedMessageLite.DefaultInstanceBasedParser(d);
                            }
                        }
                    }
                    return e;
                default:
                    throw new UnsupportedOperationException();
            }
            return d;
        }

        static {
            d.P();
        }

        public static ByeRequest e() {
            return d;
        }

        public static Parser<ByeRequest> f() {
            return d.M();
        }
    }

    public static final class ByeResponse extends GeneratedMessageLite<ByeResponse, Builder> implements ByeResponseOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11271a = 1;
        public static final int b = 2;
        /* access modifiers changed from: private */
        public static final ByeResponse f = new ByeResponse();
        private static volatile Parser<ByeResponse> g;
        private int c;
        private int d;
        private String e = "";

        private ByeResponse() {
        }

        public boolean a() {
            return (this.c & 1) == 1;
        }

        public RTSResult b() {
            RTSResult forNumber = RTSResult.forNumber(this.d);
            return forNumber == null ? RTSResult.SUCC : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(RTSResult rTSResult) {
            if (rTSResult != null) {
                this.c |= 1;
                this.d = rTSResult.getNumber();
                return;
            }
            throw new NullPointerException();
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
                codedOutputStream.g(1, this.d);
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
                i2 = 0 + CodedOutputStream.m(1, this.d);
            }
            if ((this.c & 2) == 2) {
                i2 += CodedOutputStream.b(2, d());
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static ByeResponse a(ByteString byteString) throws InvalidProtocolBufferException {
            return (ByeResponse) GeneratedMessageLite.a(f, byteString);
        }

        public static ByeResponse a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ByeResponse) GeneratedMessageLite.a(f, byteString, extensionRegistryLite);
        }

        public static ByeResponse a(byte[] bArr) throws InvalidProtocolBufferException {
            return (ByeResponse) GeneratedMessageLite.a(f, bArr);
        }

        public static ByeResponse a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ByeResponse) GeneratedMessageLite.a(f, bArr, extensionRegistryLite);
        }

        public static ByeResponse a(InputStream inputStream) throws IOException {
            return (ByeResponse) GeneratedMessageLite.a(f, inputStream);
        }

        public static ByeResponse a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ByeResponse) GeneratedMessageLite.a(f, inputStream, extensionRegistryLite);
        }

        public static ByeResponse b(InputStream inputStream) throws IOException {
            return (ByeResponse) b(f, inputStream);
        }

        public static ByeResponse b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ByeResponse) b(f, inputStream, extensionRegistryLite);
        }

        public static ByeResponse a(CodedInputStream codedInputStream) throws IOException {
            return (ByeResponse) GeneratedMessageLite.b(f, codedInputStream);
        }

        public static ByeResponse a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ByeResponse) GeneratedMessageLite.b(f, codedInputStream, extensionRegistryLite);
        }

        public static Builder f() {
            return (Builder) f.Y();
        }

        public static Builder a(ByeResponse byeResponse) {
            return (Builder) ((Builder) f.Y()).b(byeResponse);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ByeResponse, Builder> implements ByeResponseOrBuilder {
            private Builder() {
                super(ByeResponse.f);
            }

            public boolean a() {
                return ((ByeResponse) this.f11310a).a();
            }

            public RTSResult b() {
                return ((ByeResponse) this.f11310a).b();
            }

            public Builder a(RTSResult rTSResult) {
                S();
                ((ByeResponse) this.f11310a).a(rTSResult);
                return this;
            }

            public Builder f() {
                S();
                ((ByeResponse) this.f11310a).j();
                return this;
            }

            public boolean c() {
                return ((ByeResponse) this.f11310a).c();
            }

            public String d() {
                return ((ByeResponse) this.f11310a).d();
            }

            public ByteString e() {
                return ((ByeResponse) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((ByeResponse) this.f11310a).a(str);
                return this;
            }

            public Builder g() {
                S();
                ((ByeResponse) this.f11310a).l();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((ByeResponse) this.f11310a).c(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new ByeResponse();
                case IS_INITIALIZED:
                    return f;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ByeResponse byeResponse = (ByeResponse) obj2;
                    this.d = visitor.a(a(), this.d, byeResponse.a(), byeResponse.d);
                    this.e = visitor.a(c(), this.e, byeResponse.c(), byeResponse.e);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.c |= byeResponse.c;
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
                                    int r = codedInputStream.r();
                                    if (RTSResult.forNumber(r) == null) {
                                        super.a(1, r);
                                    } else {
                                        this.c = 1 | this.c;
                                        this.d = r;
                                    }
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
                        synchronized (ByeResponse.class) {
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

        public static ByeResponse g() {
            return f;
        }

        public static Parser<ByeResponse> h() {
            return f.M();
        }
    }

    public static final class CreateChannelRequest extends GeneratedMessageLite<CreateChannelRequest, Builder> implements CreateChannelRequestOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11273a = 1;
        public static final int b = 2;
        public static final int c = 3;
        /* access modifiers changed from: private */
        public static final CreateChannelRequest h = new CreateChannelRequest();
        private static volatile Parser<CreateChannelRequest> i;
        private int d;
        private UserInfo e;
        private long f;
        private ByteString g = ByteString.EMPTY;

        private CreateChannelRequest() {
        }

        public boolean a() {
            return (this.d & 1) == 1;
        }

        public UserInfo b() {
            return this.e == null ? UserInfo.F() : this.e;
        }

        /* access modifiers changed from: private */
        public void a(UserInfo userInfo) {
            if (userInfo != null) {
                this.e = userInfo;
                this.d |= 1;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UserInfo.Builder builder) {
            this.e = (UserInfo) builder.Z();
            this.d |= 1;
        }

        /* access modifiers changed from: private */
        public void b(UserInfo userInfo) {
            if (this.e == null || this.e == UserInfo.F()) {
                this.e = userInfo;
            } else {
                this.e = (UserInfo) ((UserInfo.Builder) UserInfo.a(this.e).b(userInfo)).Y();
            }
            this.d |= 1;
        }

        /* access modifiers changed from: private */
        public void l() {
            this.e = null;
            this.d &= -2;
        }

        public boolean c() {
            return (this.d & 2) == 2;
        }

        public long d() {
            return this.f;
        }

        /* access modifiers changed from: private */
        public void a(long j) {
            this.d |= 2;
            this.f = j;
        }

        /* access modifiers changed from: private */
        public void m() {
            this.d &= -3;
            this.f = 0;
        }

        public boolean e() {
            return (this.d & 4) == 4;
        }

        public ByteString f() {
            return this.g;
        }

        /* access modifiers changed from: private */
        public void c(ByteString byteString) {
            if (byteString != null) {
                this.d |= 4;
                this.g = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void n() {
            this.d &= -5;
            this.g = h().f();
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.d & 1) == 1) {
                codedOutputStream.a(1, (MessageLite) b());
            }
            if ((this.d & 2) == 2) {
                codedOutputStream.b(2, this.f);
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
                i3 = 0 + CodedOutputStream.c(1, (MessageLite) b());
            }
            if ((this.d & 2) == 2) {
                i3 += CodedOutputStream.g(2, this.f);
            }
            if ((this.d & 4) == 4) {
                i3 += CodedOutputStream.c(3, this.g);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static CreateChannelRequest a(ByteString byteString) throws InvalidProtocolBufferException {
            return (CreateChannelRequest) GeneratedMessageLite.a(h, byteString);
        }

        public static CreateChannelRequest a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CreateChannelRequest) GeneratedMessageLite.a(h, byteString, extensionRegistryLite);
        }

        public static CreateChannelRequest a(byte[] bArr) throws InvalidProtocolBufferException {
            return (CreateChannelRequest) GeneratedMessageLite.a(h, bArr);
        }

        public static CreateChannelRequest a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CreateChannelRequest) GeneratedMessageLite.a(h, bArr, extensionRegistryLite);
        }

        public static CreateChannelRequest a(InputStream inputStream) throws IOException {
            return (CreateChannelRequest) GeneratedMessageLite.a(h, inputStream);
        }

        public static CreateChannelRequest a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CreateChannelRequest) GeneratedMessageLite.a(h, inputStream, extensionRegistryLite);
        }

        public static CreateChannelRequest b(InputStream inputStream) throws IOException {
            return (CreateChannelRequest) b(h, inputStream);
        }

        public static CreateChannelRequest b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CreateChannelRequest) b(h, inputStream, extensionRegistryLite);
        }

        public static CreateChannelRequest a(CodedInputStream codedInputStream) throws IOException {
            return (CreateChannelRequest) GeneratedMessageLite.b(h, codedInputStream);
        }

        public static CreateChannelRequest a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CreateChannelRequest) GeneratedMessageLite.b(h, codedInputStream, extensionRegistryLite);
        }

        public static Builder g() {
            return (Builder) h.Y();
        }

        public static Builder a(CreateChannelRequest createChannelRequest) {
            return (Builder) ((Builder) h.Y()).b(createChannelRequest);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CreateChannelRequest, Builder> implements CreateChannelRequestOrBuilder {
            private Builder() {
                super(CreateChannelRequest.h);
            }

            public boolean a() {
                return ((CreateChannelRequest) this.f11310a).a();
            }

            public UserInfo b() {
                return ((CreateChannelRequest) this.f11310a).b();
            }

            public Builder a(UserInfo userInfo) {
                S();
                ((CreateChannelRequest) this.f11310a).a(userInfo);
                return this;
            }

            public Builder a(UserInfo.Builder builder) {
                S();
                ((CreateChannelRequest) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UserInfo userInfo) {
                S();
                ((CreateChannelRequest) this.f11310a).b(userInfo);
                return this;
            }

            public Builder g() {
                S();
                ((CreateChannelRequest) this.f11310a).l();
                return this;
            }

            public boolean c() {
                return ((CreateChannelRequest) this.f11310a).c();
            }

            public long d() {
                return ((CreateChannelRequest) this.f11310a).d();
            }

            public Builder a(long j) {
                S();
                ((CreateChannelRequest) this.f11310a).a(j);
                return this;
            }

            public Builder h() {
                S();
                ((CreateChannelRequest) this.f11310a).m();
                return this;
            }

            public boolean e() {
                return ((CreateChannelRequest) this.f11310a).e();
            }

            public ByteString f() {
                return ((CreateChannelRequest) this.f11310a).f();
            }

            public Builder a(ByteString byteString) {
                S();
                ((CreateChannelRequest) this.f11310a).c(byteString);
                return this;
            }

            public Builder i() {
                S();
                ((CreateChannelRequest) this.f11310a).n();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new CreateChannelRequest();
                case IS_INITIALIZED:
                    return h;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    CreateChannelRequest createChannelRequest = (CreateChannelRequest) obj2;
                    this.e = (UserInfo) visitor.a(this.e, createChannelRequest.e);
                    this.f = visitor.a(c(), this.f, createChannelRequest.c(), createChannelRequest.f);
                    this.g = visitor.a(e(), this.g, createChannelRequest.e(), createChannelRequest.g);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.d |= createChannelRequest.d;
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
                                    UserInfo.Builder builder = (this.d & 1) == 1 ? (UserInfo.Builder) this.e.Y() : null;
                                    this.e = (UserInfo) codedInputStream.a(UserInfo.G(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.e);
                                        this.e = (UserInfo) builder.Y();
                                    }
                                    this.d |= 1;
                                } else if (a2 == 16) {
                                    this.d |= 2;
                                    this.f = codedInputStream.f();
                                } else if (a2 == 26) {
                                    this.d |= 4;
                                    this.g = codedInputStream.n();
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
                        synchronized (CreateChannelRequest.class) {
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

        public static CreateChannelRequest h() {
            return h;
        }

        public static Parser<CreateChannelRequest> i() {
            return h.M();
        }
    }

    public static final class CreateChannelResponse extends GeneratedMessageLite<CreateChannelResponse, Builder> implements CreateChannelResponseOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11274a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        /* access modifiers changed from: private */
        public static final CreateChannelResponse s = new CreateChannelResponse();
        private static volatile Parser<CreateChannelResponse> t;
        private int h;
        private int i;
        private String j = "";
        private long k;
        private String l = "";
        private long p;
        private ByteString q = ByteString.EMPTY;
        private UserInfo r;

        private CreateChannelResponse() {
        }

        public boolean a() {
            return (this.h & 1) == 1;
        }

        public RTSResult b() {
            RTSResult forNumber = RTSResult.forNumber(this.i);
            return forNumber == null ? RTSResult.SUCC : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(RTSResult rTSResult) {
            if (rTSResult != null) {
                this.h |= 1;
                this.i = rTSResult.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void v() {
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
        public void w() {
            this.h &= -3;
            this.j = s().d();
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

        public long g() {
            return this.k;
        }

        /* access modifiers changed from: private */
        public void a(long j2) {
            this.h |= 4;
            this.k = j2;
        }

        /* access modifiers changed from: private */
        public void x() {
            this.h &= -5;
            this.k = 0;
        }

        public boolean h() {
            return (this.h & 8) == 8;
        }

        public String i() {
            return this.l;
        }

        public ByteString j() {
            return ByteString.copyFromUtf8(this.l);
        }

        /* access modifiers changed from: private */
        public void b(String str) {
            if (str != null) {
                this.h |= 8;
                this.l = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void y() {
            this.h &= -9;
            this.l = s().i();
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.h |= 8;
                this.l = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean l() {
            return (this.h & 16) == 16;
        }

        public long m() {
            return this.p;
        }

        /* access modifiers changed from: private */
        public void b(long j2) {
            this.h |= 16;
            this.p = j2;
        }

        /* access modifiers changed from: private */
        public void z() {
            this.h &= -17;
            this.p = 0;
        }

        public boolean n() {
            return (this.h & 32) == 32;
        }

        public ByteString o() {
            return this.q;
        }

        /* access modifiers changed from: private */
        public void e(ByteString byteString) {
            if (byteString != null) {
                this.h |= 32;
                this.q = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void A() {
            this.h &= -33;
            this.q = s().o();
        }

        public boolean p() {
            return (this.h & 64) == 64;
        }

        public UserInfo q() {
            return this.r == null ? UserInfo.F() : this.r;
        }

        /* access modifiers changed from: private */
        public void a(UserInfo userInfo) {
            if (userInfo != null) {
                this.r = userInfo;
                this.h |= 64;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UserInfo.Builder builder) {
            this.r = (UserInfo) builder.Z();
            this.h |= 64;
        }

        /* access modifiers changed from: private */
        public void b(UserInfo userInfo) {
            if (this.r == null || this.r == UserInfo.F()) {
                this.r = userInfo;
            } else {
                this.r = (UserInfo) ((UserInfo.Builder) UserInfo.a(this.r).b(userInfo)).Y();
            }
            this.h |= 64;
        }

        /* access modifiers changed from: private */
        public void B() {
            this.r = null;
            this.h &= -65;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.h & 1) == 1) {
                codedOutputStream.g(1, this.i);
            }
            if ((this.h & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.h & 4) == 4) {
                codedOutputStream.b(3, this.k);
            }
            if ((this.h & 8) == 8) {
                codedOutputStream.a(4, i());
            }
            if ((this.h & 16) == 16) {
                codedOutputStream.b(5, this.p);
            }
            if ((this.h & 32) == 32) {
                codedOutputStream.a(6, this.q);
            }
            if ((this.h & 64) == 64) {
                codedOutputStream.a(7, (MessageLite) q());
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
                i3 = 0 + CodedOutputStream.m(1, this.i);
            }
            if ((this.h & 2) == 2) {
                i3 += CodedOutputStream.b(2, d());
            }
            if ((this.h & 4) == 4) {
                i3 += CodedOutputStream.g(3, this.k);
            }
            if ((this.h & 8) == 8) {
                i3 += CodedOutputStream.b(4, i());
            }
            if ((this.h & 16) == 16) {
                i3 += CodedOutputStream.g(5, this.p);
            }
            if ((this.h & 32) == 32) {
                i3 += CodedOutputStream.c(6, this.q);
            }
            if ((this.h & 64) == 64) {
                i3 += CodedOutputStream.c(7, (MessageLite) q());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static CreateChannelResponse a(ByteString byteString) throws InvalidProtocolBufferException {
            return (CreateChannelResponse) GeneratedMessageLite.a(s, byteString);
        }

        public static CreateChannelResponse a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CreateChannelResponse) GeneratedMessageLite.a(s, byteString, extensionRegistryLite);
        }

        public static CreateChannelResponse a(byte[] bArr) throws InvalidProtocolBufferException {
            return (CreateChannelResponse) GeneratedMessageLite.a(s, bArr);
        }

        public static CreateChannelResponse a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CreateChannelResponse) GeneratedMessageLite.a(s, bArr, extensionRegistryLite);
        }

        public static CreateChannelResponse a(InputStream inputStream) throws IOException {
            return (CreateChannelResponse) GeneratedMessageLite.a(s, inputStream);
        }

        public static CreateChannelResponse a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CreateChannelResponse) GeneratedMessageLite.a(s, inputStream, extensionRegistryLite);
        }

        public static CreateChannelResponse b(InputStream inputStream) throws IOException {
            return (CreateChannelResponse) b(s, inputStream);
        }

        public static CreateChannelResponse b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CreateChannelResponse) b(s, inputStream, extensionRegistryLite);
        }

        public static CreateChannelResponse a(CodedInputStream codedInputStream) throws IOException {
            return (CreateChannelResponse) GeneratedMessageLite.b(s, codedInputStream);
        }

        public static CreateChannelResponse a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CreateChannelResponse) GeneratedMessageLite.b(s, codedInputStream, extensionRegistryLite);
        }

        public static Builder r() {
            return (Builder) s.Y();
        }

        public static Builder a(CreateChannelResponse createChannelResponse) {
            return (Builder) ((Builder) s.Y()).b(createChannelResponse);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<CreateChannelResponse, Builder> implements CreateChannelResponseOrBuilder {
            private Builder() {
                super(CreateChannelResponse.s);
            }

            public boolean a() {
                return ((CreateChannelResponse) this.f11310a).a();
            }

            public RTSResult b() {
                return ((CreateChannelResponse) this.f11310a).b();
            }

            public Builder a(RTSResult rTSResult) {
                S();
                ((CreateChannelResponse) this.f11310a).a(rTSResult);
                return this;
            }

            public Builder k() {
                S();
                ((CreateChannelResponse) this.f11310a).v();
                return this;
            }

            public boolean c() {
                return ((CreateChannelResponse) this.f11310a).c();
            }

            public String d() {
                return ((CreateChannelResponse) this.f11310a).d();
            }

            public ByteString e() {
                return ((CreateChannelResponse) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((CreateChannelResponse) this.f11310a).a(str);
                return this;
            }

            public Builder r() {
                S();
                ((CreateChannelResponse) this.f11310a).w();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((CreateChannelResponse) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((CreateChannelResponse) this.f11310a).f();
            }

            public long g() {
                return ((CreateChannelResponse) this.f11310a).g();
            }

            public Builder a(long j) {
                S();
                ((CreateChannelResponse) this.f11310a).a(j);
                return this;
            }

            public Builder s() {
                S();
                ((CreateChannelResponse) this.f11310a).x();
                return this;
            }

            public boolean h() {
                return ((CreateChannelResponse) this.f11310a).h();
            }

            public String i() {
                return ((CreateChannelResponse) this.f11310a).i();
            }

            public ByteString j() {
                return ((CreateChannelResponse) this.f11310a).j();
            }

            public Builder b(String str) {
                S();
                ((CreateChannelResponse) this.f11310a).b(str);
                return this;
            }

            public Builder t() {
                S();
                ((CreateChannelResponse) this.f11310a).y();
                return this;
            }

            public Builder b(ByteString byteString) {
                S();
                ((CreateChannelResponse) this.f11310a).d(byteString);
                return this;
            }

            public boolean l() {
                return ((CreateChannelResponse) this.f11310a).l();
            }

            public long m() {
                return ((CreateChannelResponse) this.f11310a).m();
            }

            public Builder b(long j) {
                S();
                ((CreateChannelResponse) this.f11310a).b(j);
                return this;
            }

            public Builder u() {
                S();
                ((CreateChannelResponse) this.f11310a).z();
                return this;
            }

            public boolean n() {
                return ((CreateChannelResponse) this.f11310a).n();
            }

            public ByteString o() {
                return ((CreateChannelResponse) this.f11310a).o();
            }

            public Builder c(ByteString byteString) {
                S();
                ((CreateChannelResponse) this.f11310a).e(byteString);
                return this;
            }

            public Builder v() {
                S();
                ((CreateChannelResponse) this.f11310a).A();
                return this;
            }

            public boolean p() {
                return ((CreateChannelResponse) this.f11310a).p();
            }

            public UserInfo q() {
                return ((CreateChannelResponse) this.f11310a).q();
            }

            public Builder a(UserInfo userInfo) {
                S();
                ((CreateChannelResponse) this.f11310a).a(userInfo);
                return this;
            }

            public Builder a(UserInfo.Builder builder) {
                S();
                ((CreateChannelResponse) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UserInfo userInfo) {
                S();
                ((CreateChannelResponse) this.f11310a).b(userInfo);
                return this;
            }

            public Builder w() {
                S();
                ((CreateChannelResponse) this.f11310a).B();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new CreateChannelResponse();
                case IS_INITIALIZED:
                    return s;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    CreateChannelResponse createChannelResponse = (CreateChannelResponse) obj2;
                    this.i = visitor.a(a(), this.i, createChannelResponse.a(), createChannelResponse.i);
                    this.j = visitor.a(c(), this.j, createChannelResponse.c(), createChannelResponse.j);
                    this.k = visitor.a(f(), this.k, createChannelResponse.f(), createChannelResponse.k);
                    this.l = visitor.a(h(), this.l, createChannelResponse.h(), createChannelResponse.l);
                    this.p = visitor.a(l(), this.p, createChannelResponse.l(), createChannelResponse.p);
                    this.q = visitor.a(n(), this.q, createChannelResponse.n(), createChannelResponse.q);
                    this.r = (UserInfo) visitor.a(this.r, createChannelResponse.r);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.h |= createChannelResponse.h;
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
                                    int r2 = codedInputStream.r();
                                    if (RTSResult.forNumber(r2) == null) {
                                        super.a(1, r2);
                                    } else {
                                        this.h = 1 | this.h;
                                        this.i = r2;
                                    }
                                } else if (a2 == 18) {
                                    String l2 = codedInputStream.l();
                                    this.h |= 2;
                                    this.j = l2;
                                } else if (a2 == 24) {
                                    this.h |= 4;
                                    this.k = codedInputStream.f();
                                } else if (a2 == 34) {
                                    String l3 = codedInputStream.l();
                                    this.h |= 8;
                                    this.l = l3;
                                } else if (a2 == 40) {
                                    this.h |= 16;
                                    this.p = codedInputStream.f();
                                } else if (a2 == 50) {
                                    this.h |= 32;
                                    this.q = codedInputStream.n();
                                } else if (a2 == 58) {
                                    UserInfo.Builder builder = (this.h & 64) == 64 ? (UserInfo.Builder) this.r.Y() : null;
                                    this.r = (UserInfo) codedInputStream.a(UserInfo.G(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.r);
                                        this.r = (UserInfo) builder.Y();
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
                    if (t == null) {
                        synchronized (CreateChannelResponse.class) {
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

        public static CreateChannelResponse s() {
            return s;
        }

        public static Parser<CreateChannelResponse> t() {
            return s.M();
        }
    }

    public static final class JoinChannelRequest extends GeneratedMessageLite<JoinChannelRequest, Builder> implements JoinChannelRequestOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11279a = 1;
        public static final int b = 2;
        public static final int c = 3;
        /* access modifiers changed from: private */
        public static final JoinChannelRequest h = new JoinChannelRequest();
        private static volatile Parser<JoinChannelRequest> i;
        private int d;
        private long e;
        private String f = "";
        private UserInfo g;

        private JoinChannelRequest() {
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

        public UserInfo g() {
            return this.g == null ? UserInfo.F() : this.g;
        }

        /* access modifiers changed from: private */
        public void a(UserInfo userInfo) {
            if (userInfo != null) {
                this.g = userInfo;
                this.d |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UserInfo.Builder builder) {
            this.g = (UserInfo) builder.Z();
            this.d |= 4;
        }

        /* access modifiers changed from: private */
        public void b(UserInfo userInfo) {
            if (this.g == null || this.g == UserInfo.F()) {
                this.g = userInfo;
            } else {
                this.g = (UserInfo) ((UserInfo.Builder) UserInfo.a(this.g).b(userInfo)).Y();
            }
            this.d |= 4;
        }

        /* access modifiers changed from: private */
        public void o() {
            this.g = null;
            this.d &= -5;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.d & 1) == 1) {
                codedOutputStream.b(1, this.e);
            }
            if ((this.d & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.d & 4) == 4) {
                codedOutputStream.a(3, (MessageLite) g());
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
                i3 += CodedOutputStream.c(3, (MessageLite) g());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static JoinChannelRequest a(ByteString byteString) throws InvalidProtocolBufferException {
            return (JoinChannelRequest) GeneratedMessageLite.a(h, byteString);
        }

        public static JoinChannelRequest a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (JoinChannelRequest) GeneratedMessageLite.a(h, byteString, extensionRegistryLite);
        }

        public static JoinChannelRequest a(byte[] bArr) throws InvalidProtocolBufferException {
            return (JoinChannelRequest) GeneratedMessageLite.a(h, bArr);
        }

        public static JoinChannelRequest a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (JoinChannelRequest) GeneratedMessageLite.a(h, bArr, extensionRegistryLite);
        }

        public static JoinChannelRequest a(InputStream inputStream) throws IOException {
            return (JoinChannelRequest) GeneratedMessageLite.a(h, inputStream);
        }

        public static JoinChannelRequest a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (JoinChannelRequest) GeneratedMessageLite.a(h, inputStream, extensionRegistryLite);
        }

        public static JoinChannelRequest b(InputStream inputStream) throws IOException {
            return (JoinChannelRequest) b(h, inputStream);
        }

        public static JoinChannelRequest b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (JoinChannelRequest) b(h, inputStream, extensionRegistryLite);
        }

        public static JoinChannelRequest a(CodedInputStream codedInputStream) throws IOException {
            return (JoinChannelRequest) GeneratedMessageLite.b(h, codedInputStream);
        }

        public static JoinChannelRequest a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (JoinChannelRequest) GeneratedMessageLite.b(h, codedInputStream, extensionRegistryLite);
        }

        public static Builder h() {
            return (Builder) h.Y();
        }

        public static Builder a(JoinChannelRequest joinChannelRequest) {
            return (Builder) ((Builder) h.Y()).b(joinChannelRequest);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<JoinChannelRequest, Builder> implements JoinChannelRequestOrBuilder {
            private Builder() {
                super(JoinChannelRequest.h);
            }

            public boolean a() {
                return ((JoinChannelRequest) this.f11310a).a();
            }

            public long b() {
                return ((JoinChannelRequest) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((JoinChannelRequest) this.f11310a).a(j);
                return this;
            }

            public Builder h() {
                S();
                ((JoinChannelRequest) this.f11310a).m();
                return this;
            }

            public boolean c() {
                return ((JoinChannelRequest) this.f11310a).c();
            }

            public String d() {
                return ((JoinChannelRequest) this.f11310a).d();
            }

            public ByteString e() {
                return ((JoinChannelRequest) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((JoinChannelRequest) this.f11310a).a(str);
                return this;
            }

            public Builder i() {
                S();
                ((JoinChannelRequest) this.f11310a).n();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((JoinChannelRequest) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((JoinChannelRequest) this.f11310a).f();
            }

            public UserInfo g() {
                return ((JoinChannelRequest) this.f11310a).g();
            }

            public Builder a(UserInfo userInfo) {
                S();
                ((JoinChannelRequest) this.f11310a).a(userInfo);
                return this;
            }

            public Builder a(UserInfo.Builder builder) {
                S();
                ((JoinChannelRequest) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UserInfo userInfo) {
                S();
                ((JoinChannelRequest) this.f11310a).b(userInfo);
                return this;
            }

            public Builder j() {
                S();
                ((JoinChannelRequest) this.f11310a).o();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new JoinChannelRequest();
                case IS_INITIALIZED:
                    return h;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    JoinChannelRequest joinChannelRequest = (JoinChannelRequest) obj2;
                    this.e = visitor.a(a(), this.e, joinChannelRequest.a(), joinChannelRequest.e);
                    this.f = visitor.a(c(), this.f, joinChannelRequest.c(), joinChannelRequest.f);
                    this.g = (UserInfo) visitor.a(this.g, joinChannelRequest.g);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.d |= joinChannelRequest.d;
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
                                    this.e = codedInputStream.f();
                                } else if (a2 == 18) {
                                    String l = codedInputStream.l();
                                    this.d |= 2;
                                    this.f = l;
                                } else if (a2 == 26) {
                                    UserInfo.Builder builder = (this.d & 4) == 4 ? (UserInfo.Builder) this.g.Y() : null;
                                    this.g = (UserInfo) codedInputStream.a(UserInfo.G(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.g);
                                        this.g = (UserInfo) builder.Y();
                                    }
                                    this.d |= 4;
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
                        synchronized (JoinChannelRequest.class) {
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

        public static JoinChannelRequest i() {
            return h;
        }

        public static Parser<JoinChannelRequest> j() {
            return h.M();
        }
    }

    public static final class JoinChannelResponse extends GeneratedMessageLite<JoinChannelResponse, Builder> implements JoinChannelResponseOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11280a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        /* access modifiers changed from: private */
        public static final JoinChannelResponse l = new JoinChannelResponse();
        private static volatile Parser<JoinChannelResponse> p;
        private int f;
        private int g;
        private String h = "";
        private long i;
        private ByteString j = ByteString.EMPTY;
        private Internal.ProtobufList<UserInfo> k = X();

        private JoinChannelResponse() {
        }

        public boolean a() {
            return (this.f & 1) == 1;
        }

        public RTSResult b() {
            RTSResult forNumber = RTSResult.forNumber(this.g);
            return forNumber == null ? RTSResult.SUCC : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(RTSResult rTSResult) {
            if (rTSResult != null) {
                this.f |= 1;
                this.g = rTSResult.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void r() {
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
        public void s() {
            this.f &= -3;
            this.h = o().d();
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
        public void a(long j2) {
            this.f |= 4;
            this.i = j2;
        }

        /* access modifiers changed from: private */
        public void t() {
            this.f &= -5;
            this.i = 0;
        }

        public boolean h() {
            return (this.f & 8) == 8;
        }

        public ByteString i() {
            return this.j;
        }

        /* access modifiers changed from: private */
        public void d(ByteString byteString) {
            if (byteString != null) {
                this.f |= 8;
                this.j = byteString;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void u() {
            this.f &= -9;
            this.j = o().i();
        }

        public List<UserInfo> j() {
            return this.k;
        }

        public List<? extends UserInfoOrBuilder> l() {
            return this.k;
        }

        public int m() {
            return this.k.size();
        }

        public UserInfo a(int i2) {
            return (UserInfo) this.k.get(i2);
        }

        public UserInfoOrBuilder b(int i2) {
            return (UserInfoOrBuilder) this.k.get(i2);
        }

        private void v() {
            if (!this.k.a()) {
                this.k = GeneratedMessageLite.a(this.k);
            }
        }

        /* access modifiers changed from: private */
        public void a(int i2, UserInfo userInfo) {
            if (userInfo != null) {
                v();
                this.k.set(i2, userInfo);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(int i2, UserInfo.Builder builder) {
            v();
            this.k.set(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(UserInfo userInfo) {
            if (userInfo != null) {
                v();
                this.k.add(userInfo);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void b(int i2, UserInfo userInfo) {
            if (userInfo != null) {
                v();
                this.k.add(i2, userInfo);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UserInfo.Builder builder) {
            v();
            this.k.add(builder.Z());
        }

        /* access modifiers changed from: private */
        public void b(int i2, UserInfo.Builder builder) {
            v();
            this.k.add(i2, builder.Z());
        }

        /* access modifiers changed from: private */
        public void a(Iterable<? extends UserInfo> iterable) {
            v();
            AbstractMessageLite.a(iterable, this.k);
        }

        /* access modifiers changed from: private */
        public void w() {
            this.k = X();
        }

        /* access modifiers changed from: private */
        public void c(int i2) {
            v();
            this.k.remove(i2);
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.f & 1) == 1) {
                codedOutputStream.g(1, this.g);
            }
            if ((this.f & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.f & 4) == 4) {
                codedOutputStream.b(3, this.i);
            }
            if ((this.f & 8) == 8) {
                codedOutputStream.a(4, this.j);
            }
            for (int i2 = 0; i2 < this.k.size(); i2++) {
                codedOutputStream.a(5, (MessageLite) this.k.get(i2));
            }
            this.n.a(codedOutputStream);
        }

        public int k() {
            int i2 = this.o;
            if (i2 != -1) {
                return i2;
            }
            int m = (this.f & 1) == 1 ? CodedOutputStream.m(1, this.g) + 0 : 0;
            if ((this.f & 2) == 2) {
                m += CodedOutputStream.b(2, d());
            }
            if ((this.f & 4) == 4) {
                m += CodedOutputStream.g(3, this.i);
            }
            if ((this.f & 8) == 8) {
                m += CodedOutputStream.c(4, this.j);
            }
            for (int i3 = 0; i3 < this.k.size(); i3++) {
                m += CodedOutputStream.c(5, (MessageLite) this.k.get(i3));
            }
            int e2 = m + this.n.e();
            this.o = e2;
            return e2;
        }

        public static JoinChannelResponse a(ByteString byteString) throws InvalidProtocolBufferException {
            return (JoinChannelResponse) GeneratedMessageLite.a(l, byteString);
        }

        public static JoinChannelResponse a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (JoinChannelResponse) GeneratedMessageLite.a(l, byteString, extensionRegistryLite);
        }

        public static JoinChannelResponse a(byte[] bArr) throws InvalidProtocolBufferException {
            return (JoinChannelResponse) GeneratedMessageLite.a(l, bArr);
        }

        public static JoinChannelResponse a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (JoinChannelResponse) GeneratedMessageLite.a(l, bArr, extensionRegistryLite);
        }

        public static JoinChannelResponse a(InputStream inputStream) throws IOException {
            return (JoinChannelResponse) GeneratedMessageLite.a(l, inputStream);
        }

        public static JoinChannelResponse a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (JoinChannelResponse) GeneratedMessageLite.a(l, inputStream, extensionRegistryLite);
        }

        public static JoinChannelResponse b(InputStream inputStream) throws IOException {
            return (JoinChannelResponse) b(l, inputStream);
        }

        public static JoinChannelResponse b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (JoinChannelResponse) b(l, inputStream, extensionRegistryLite);
        }

        public static JoinChannelResponse a(CodedInputStream codedInputStream) throws IOException {
            return (JoinChannelResponse) GeneratedMessageLite.b(l, codedInputStream);
        }

        public static JoinChannelResponse a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (JoinChannelResponse) GeneratedMessageLite.b(l, codedInputStream, extensionRegistryLite);
        }

        public static Builder n() {
            return (Builder) l.Y();
        }

        public static Builder a(JoinChannelResponse joinChannelResponse) {
            return (Builder) ((Builder) l.Y()).b(joinChannelResponse);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<JoinChannelResponse, Builder> implements JoinChannelResponseOrBuilder {
            private Builder() {
                super(JoinChannelResponse.l);
            }

            public boolean a() {
                return ((JoinChannelResponse) this.f11310a).a();
            }

            public RTSResult b() {
                return ((JoinChannelResponse) this.f11310a).b();
            }

            public Builder a(RTSResult rTSResult) {
                S();
                ((JoinChannelResponse) this.f11310a).a(rTSResult);
                return this;
            }

            public Builder k() {
                S();
                ((JoinChannelResponse) this.f11310a).r();
                return this;
            }

            public boolean c() {
                return ((JoinChannelResponse) this.f11310a).c();
            }

            public String d() {
                return ((JoinChannelResponse) this.f11310a).d();
            }

            public ByteString e() {
                return ((JoinChannelResponse) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((JoinChannelResponse) this.f11310a).a(str);
                return this;
            }

            public Builder l() {
                S();
                ((JoinChannelResponse) this.f11310a).s();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((JoinChannelResponse) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((JoinChannelResponse) this.f11310a).f();
            }

            public long g() {
                return ((JoinChannelResponse) this.f11310a).g();
            }

            public Builder a(long j) {
                S();
                ((JoinChannelResponse) this.f11310a).a(j);
                return this;
            }

            public Builder n() {
                S();
                ((JoinChannelResponse) this.f11310a).t();
                return this;
            }

            public boolean h() {
                return ((JoinChannelResponse) this.f11310a).h();
            }

            public ByteString i() {
                return ((JoinChannelResponse) this.f11310a).i();
            }

            public Builder b(ByteString byteString) {
                S();
                ((JoinChannelResponse) this.f11310a).d(byteString);
                return this;
            }

            public Builder o() {
                S();
                ((JoinChannelResponse) this.f11310a).u();
                return this;
            }

            public List<UserInfo> j() {
                return Collections.unmodifiableList(((JoinChannelResponse) this.f11310a).j());
            }

            public int m() {
                return ((JoinChannelResponse) this.f11310a).m();
            }

            public UserInfo a(int i) {
                return ((JoinChannelResponse) this.f11310a).a(i);
            }

            public Builder a(int i, UserInfo userInfo) {
                S();
                ((JoinChannelResponse) this.f11310a).a(i, userInfo);
                return this;
            }

            public Builder a(int i, UserInfo.Builder builder) {
                S();
                ((JoinChannelResponse) this.f11310a).a(i, builder);
                return this;
            }

            public Builder a(UserInfo userInfo) {
                S();
                ((JoinChannelResponse) this.f11310a).a(userInfo);
                return this;
            }

            public Builder b(int i, UserInfo userInfo) {
                S();
                ((JoinChannelResponse) this.f11310a).b(i, userInfo);
                return this;
            }

            public Builder a(UserInfo.Builder builder) {
                S();
                ((JoinChannelResponse) this.f11310a).a(builder);
                return this;
            }

            public Builder b(int i, UserInfo.Builder builder) {
                S();
                ((JoinChannelResponse) this.f11310a).b(i, builder);
                return this;
            }

            public Builder a(Iterable<? extends UserInfo> iterable) {
                S();
                ((JoinChannelResponse) this.f11310a).a(iterable);
                return this;
            }

            public Builder p() {
                S();
                ((JoinChannelResponse) this.f11310a).w();
                return this;
            }

            public Builder b(int i) {
                S();
                ((JoinChannelResponse) this.f11310a).c(i);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new JoinChannelResponse();
                case IS_INITIALIZED:
                    return l;
                case MAKE_IMMUTABLE:
                    this.k.b();
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    JoinChannelResponse joinChannelResponse = (JoinChannelResponse) obj2;
                    this.g = visitor.a(a(), this.g, joinChannelResponse.a(), joinChannelResponse.g);
                    this.h = visitor.a(c(), this.h, joinChannelResponse.c(), joinChannelResponse.h);
                    this.i = visitor.a(f(), this.i, joinChannelResponse.f(), joinChannelResponse.i);
                    this.j = visitor.a(h(), this.j, joinChannelResponse.h(), joinChannelResponse.j);
                    this.k = visitor.a(this.k, joinChannelResponse.k);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.f |= joinChannelResponse.f;
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
                                    int r = codedInputStream.r();
                                    if (RTSResult.forNumber(r) == null) {
                                        super.a(1, r);
                                    } else {
                                        this.f = 1 | this.f;
                                        this.g = r;
                                    }
                                } else if (a2 == 18) {
                                    String l2 = codedInputStream.l();
                                    this.f |= 2;
                                    this.h = l2;
                                } else if (a2 == 24) {
                                    this.f |= 4;
                                    this.i = codedInputStream.f();
                                } else if (a2 == 34) {
                                    this.f |= 8;
                                    this.j = codedInputStream.n();
                                } else if (a2 == 42) {
                                    if (!this.k.a()) {
                                        this.k = GeneratedMessageLite.a(this.k);
                                    }
                                    this.k.add(codedInputStream.a(UserInfo.G(), extensionRegistryLite));
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
                        synchronized (JoinChannelResponse.class) {
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

        public static JoinChannelResponse o() {
            return l;
        }

        public static Parser<JoinChannelResponse> p() {
            return l.M();
        }
    }

    public static final class LeaveChannelRequest extends GeneratedMessageLite<LeaveChannelRequest, Builder> implements LeaveChannelRequestOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11281a = 1;
        public static final int b = 2;
        public static final int c = 3;
        /* access modifiers changed from: private */
        public static final LeaveChannelRequest h = new LeaveChannelRequest();
        private static volatile Parser<LeaveChannelRequest> i;
        private int d;
        private long e;
        private String f = "";
        private UserInfo g;

        private LeaveChannelRequest() {
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

        public UserInfo g() {
            return this.g == null ? UserInfo.F() : this.g;
        }

        /* access modifiers changed from: private */
        public void a(UserInfo userInfo) {
            if (userInfo != null) {
                this.g = userInfo;
                this.d |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UserInfo.Builder builder) {
            this.g = (UserInfo) builder.Z();
            this.d |= 4;
        }

        /* access modifiers changed from: private */
        public void b(UserInfo userInfo) {
            if (this.g == null || this.g == UserInfo.F()) {
                this.g = userInfo;
            } else {
                this.g = (UserInfo) ((UserInfo.Builder) UserInfo.a(this.g).b(userInfo)).Y();
            }
            this.d |= 4;
        }

        /* access modifiers changed from: private */
        public void o() {
            this.g = null;
            this.d &= -5;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.d & 1) == 1) {
                codedOutputStream.b(1, this.e);
            }
            if ((this.d & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.d & 4) == 4) {
                codedOutputStream.a(3, (MessageLite) g());
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
                i3 += CodedOutputStream.c(3, (MessageLite) g());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static LeaveChannelRequest a(ByteString byteString) throws InvalidProtocolBufferException {
            return (LeaveChannelRequest) GeneratedMessageLite.a(h, byteString);
        }

        public static LeaveChannelRequest a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LeaveChannelRequest) GeneratedMessageLite.a(h, byteString, extensionRegistryLite);
        }

        public static LeaveChannelRequest a(byte[] bArr) throws InvalidProtocolBufferException {
            return (LeaveChannelRequest) GeneratedMessageLite.a(h, bArr);
        }

        public static LeaveChannelRequest a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LeaveChannelRequest) GeneratedMessageLite.a(h, bArr, extensionRegistryLite);
        }

        public static LeaveChannelRequest a(InputStream inputStream) throws IOException {
            return (LeaveChannelRequest) GeneratedMessageLite.a(h, inputStream);
        }

        public static LeaveChannelRequest a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LeaveChannelRequest) GeneratedMessageLite.a(h, inputStream, extensionRegistryLite);
        }

        public static LeaveChannelRequest b(InputStream inputStream) throws IOException {
            return (LeaveChannelRequest) b(h, inputStream);
        }

        public static LeaveChannelRequest b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LeaveChannelRequest) b(h, inputStream, extensionRegistryLite);
        }

        public static LeaveChannelRequest a(CodedInputStream codedInputStream) throws IOException {
            return (LeaveChannelRequest) GeneratedMessageLite.b(h, codedInputStream);
        }

        public static LeaveChannelRequest a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LeaveChannelRequest) GeneratedMessageLite.b(h, codedInputStream, extensionRegistryLite);
        }

        public static Builder h() {
            return (Builder) h.Y();
        }

        public static Builder a(LeaveChannelRequest leaveChannelRequest) {
            return (Builder) ((Builder) h.Y()).b(leaveChannelRequest);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<LeaveChannelRequest, Builder> implements LeaveChannelRequestOrBuilder {
            private Builder() {
                super(LeaveChannelRequest.h);
            }

            public boolean a() {
                return ((LeaveChannelRequest) this.f11310a).a();
            }

            public long b() {
                return ((LeaveChannelRequest) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((LeaveChannelRequest) this.f11310a).a(j);
                return this;
            }

            public Builder h() {
                S();
                ((LeaveChannelRequest) this.f11310a).m();
                return this;
            }

            public boolean c() {
                return ((LeaveChannelRequest) this.f11310a).c();
            }

            public String d() {
                return ((LeaveChannelRequest) this.f11310a).d();
            }

            public ByteString e() {
                return ((LeaveChannelRequest) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((LeaveChannelRequest) this.f11310a).a(str);
                return this;
            }

            public Builder i() {
                S();
                ((LeaveChannelRequest) this.f11310a).n();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((LeaveChannelRequest) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((LeaveChannelRequest) this.f11310a).f();
            }

            public UserInfo g() {
                return ((LeaveChannelRequest) this.f11310a).g();
            }

            public Builder a(UserInfo userInfo) {
                S();
                ((LeaveChannelRequest) this.f11310a).a(userInfo);
                return this;
            }

            public Builder a(UserInfo.Builder builder) {
                S();
                ((LeaveChannelRequest) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UserInfo userInfo) {
                S();
                ((LeaveChannelRequest) this.f11310a).b(userInfo);
                return this;
            }

            public Builder j() {
                S();
                ((LeaveChannelRequest) this.f11310a).o();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new LeaveChannelRequest();
                case IS_INITIALIZED:
                    return h;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    LeaveChannelRequest leaveChannelRequest = (LeaveChannelRequest) obj2;
                    this.e = visitor.a(a(), this.e, leaveChannelRequest.a(), leaveChannelRequest.e);
                    this.f = visitor.a(c(), this.f, leaveChannelRequest.c(), leaveChannelRequest.f);
                    this.g = (UserInfo) visitor.a(this.g, leaveChannelRequest.g);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.d |= leaveChannelRequest.d;
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
                                    this.e = codedInputStream.f();
                                } else if (a2 == 18) {
                                    String l = codedInputStream.l();
                                    this.d |= 2;
                                    this.f = l;
                                } else if (a2 == 26) {
                                    UserInfo.Builder builder = (this.d & 4) == 4 ? (UserInfo.Builder) this.g.Y() : null;
                                    this.g = (UserInfo) codedInputStream.a(UserInfo.G(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.g);
                                        this.g = (UserInfo) builder.Y();
                                    }
                                    this.d |= 4;
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
                        synchronized (LeaveChannelRequest.class) {
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

        public static LeaveChannelRequest i() {
            return h;
        }

        public static Parser<LeaveChannelRequest> j() {
            return h.M();
        }
    }

    public static final class LeaveChannelResponse extends GeneratedMessageLite<LeaveChannelResponse, Builder> implements LeaveChannelResponseOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11282a = 1;
        public static final int b = 2;
        public static final int c = 3;
        /* access modifiers changed from: private */
        public static final LeaveChannelResponse h = new LeaveChannelResponse();
        private static volatile Parser<LeaveChannelResponse> i;
        private int d;
        private int e;
        private String f = "";
        private long g;

        private LeaveChannelResponse() {
        }

        public boolean a() {
            return (this.d & 1) == 1;
        }

        public RTSResult b() {
            RTSResult forNumber = RTSResult.forNumber(this.e);
            return forNumber == null ? RTSResult.SUCC : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(RTSResult rTSResult) {
            if (rTSResult != null) {
                this.d |= 1;
                this.e = rTSResult.getNumber();
                return;
            }
            throw new NullPointerException();
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
        public void a(long j) {
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
                codedOutputStream.g(1, this.e);
            }
            if ((this.d & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.d & 4) == 4) {
                codedOutputStream.b(3, this.g);
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
                i3 = 0 + CodedOutputStream.m(1, this.e);
            }
            if ((this.d & 2) == 2) {
                i3 += CodedOutputStream.b(2, d());
            }
            if ((this.d & 4) == 4) {
                i3 += CodedOutputStream.g(3, this.g);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static LeaveChannelResponse a(ByteString byteString) throws InvalidProtocolBufferException {
            return (LeaveChannelResponse) GeneratedMessageLite.a(h, byteString);
        }

        public static LeaveChannelResponse a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LeaveChannelResponse) GeneratedMessageLite.a(h, byteString, extensionRegistryLite);
        }

        public static LeaveChannelResponse a(byte[] bArr) throws InvalidProtocolBufferException {
            return (LeaveChannelResponse) GeneratedMessageLite.a(h, bArr);
        }

        public static LeaveChannelResponse a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LeaveChannelResponse) GeneratedMessageLite.a(h, bArr, extensionRegistryLite);
        }

        public static LeaveChannelResponse a(InputStream inputStream) throws IOException {
            return (LeaveChannelResponse) GeneratedMessageLite.a(h, inputStream);
        }

        public static LeaveChannelResponse a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LeaveChannelResponse) GeneratedMessageLite.a(h, inputStream, extensionRegistryLite);
        }

        public static LeaveChannelResponse b(InputStream inputStream) throws IOException {
            return (LeaveChannelResponse) b(h, inputStream);
        }

        public static LeaveChannelResponse b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LeaveChannelResponse) b(h, inputStream, extensionRegistryLite);
        }

        public static LeaveChannelResponse a(CodedInputStream codedInputStream) throws IOException {
            return (LeaveChannelResponse) GeneratedMessageLite.b(h, codedInputStream);
        }

        public static LeaveChannelResponse a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LeaveChannelResponse) GeneratedMessageLite.b(h, codedInputStream, extensionRegistryLite);
        }

        public static Builder h() {
            return (Builder) h.Y();
        }

        public static Builder a(LeaveChannelResponse leaveChannelResponse) {
            return (Builder) ((Builder) h.Y()).b(leaveChannelResponse);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<LeaveChannelResponse, Builder> implements LeaveChannelResponseOrBuilder {
            private Builder() {
                super(LeaveChannelResponse.h);
            }

            public boolean a() {
                return ((LeaveChannelResponse) this.f11310a).a();
            }

            public RTSResult b() {
                return ((LeaveChannelResponse) this.f11310a).b();
            }

            public Builder a(RTSResult rTSResult) {
                S();
                ((LeaveChannelResponse) this.f11310a).a(rTSResult);
                return this;
            }

            public Builder h() {
                S();
                ((LeaveChannelResponse) this.f11310a).m();
                return this;
            }

            public boolean c() {
                return ((LeaveChannelResponse) this.f11310a).c();
            }

            public String d() {
                return ((LeaveChannelResponse) this.f11310a).d();
            }

            public ByteString e() {
                return ((LeaveChannelResponse) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((LeaveChannelResponse) this.f11310a).a(str);
                return this;
            }

            public Builder i() {
                S();
                ((LeaveChannelResponse) this.f11310a).n();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((LeaveChannelResponse) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((LeaveChannelResponse) this.f11310a).f();
            }

            public long g() {
                return ((LeaveChannelResponse) this.f11310a).g();
            }

            public Builder a(long j) {
                S();
                ((LeaveChannelResponse) this.f11310a).a(j);
                return this;
            }

            public Builder j() {
                S();
                ((LeaveChannelResponse) this.f11310a).o();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new LeaveChannelResponse();
                case IS_INITIALIZED:
                    return h;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    LeaveChannelResponse leaveChannelResponse = (LeaveChannelResponse) obj2;
                    this.e = visitor.a(a(), this.e, leaveChannelResponse.a(), leaveChannelResponse.e);
                    this.f = visitor.a(c(), this.f, leaveChannelResponse.c(), leaveChannelResponse.f);
                    this.g = visitor.a(f(), this.g, leaveChannelResponse.f(), leaveChannelResponse.g);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.d |= leaveChannelResponse.d;
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
                                    int r = codedInputStream.r();
                                    if (RTSResult.forNumber(r) == null) {
                                        super.a(1, r);
                                    } else {
                                        this.d = 1 | this.d;
                                        this.e = r;
                                    }
                                } else if (a2 == 18) {
                                    String l = codedInputStream.l();
                                    this.d |= 2;
                                    this.f = l;
                                } else if (a2 == 24) {
                                    this.d |= 4;
                                    this.g = codedInputStream.f();
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
                        synchronized (LeaveChannelResponse.class) {
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

        public static LeaveChannelResponse i() {
            return h;
        }

        public static Parser<LeaveChannelResponse> j() {
            return h.M();
        }
    }

    public static final class UserJoinNotification extends GeneratedMessageLite<UserJoinNotification, Builder> implements UserJoinNotificationOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11290a = 1;
        public static final int b = 2;
        public static final int c = 3;
        /* access modifiers changed from: private */
        public static final UserJoinNotification h = new UserJoinNotification();
        private static volatile Parser<UserJoinNotification> i;
        private int d;
        private long e;
        private String f = "";
        private UserInfo g;

        private UserJoinNotification() {
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

        public UserInfo g() {
            return this.g == null ? UserInfo.F() : this.g;
        }

        /* access modifiers changed from: private */
        public void a(UserInfo userInfo) {
            if (userInfo != null) {
                this.g = userInfo;
                this.d |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UserInfo.Builder builder) {
            this.g = (UserInfo) builder.Z();
            this.d |= 4;
        }

        /* access modifiers changed from: private */
        public void b(UserInfo userInfo) {
            if (this.g == null || this.g == UserInfo.F()) {
                this.g = userInfo;
            } else {
                this.g = (UserInfo) ((UserInfo.Builder) UserInfo.a(this.g).b(userInfo)).Y();
            }
            this.d |= 4;
        }

        /* access modifiers changed from: private */
        public void o() {
            this.g = null;
            this.d &= -5;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.d & 1) == 1) {
                codedOutputStream.b(1, this.e);
            }
            if ((this.d & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.d & 4) == 4) {
                codedOutputStream.a(3, (MessageLite) g());
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
                i3 += CodedOutputStream.c(3, (MessageLite) g());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UserJoinNotification a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UserJoinNotification) GeneratedMessageLite.a(h, byteString);
        }

        public static UserJoinNotification a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UserJoinNotification) GeneratedMessageLite.a(h, byteString, extensionRegistryLite);
        }

        public static UserJoinNotification a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UserJoinNotification) GeneratedMessageLite.a(h, bArr);
        }

        public static UserJoinNotification a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UserJoinNotification) GeneratedMessageLite.a(h, bArr, extensionRegistryLite);
        }

        public static UserJoinNotification a(InputStream inputStream) throws IOException {
            return (UserJoinNotification) GeneratedMessageLite.a(h, inputStream);
        }

        public static UserJoinNotification a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UserJoinNotification) GeneratedMessageLite.a(h, inputStream, extensionRegistryLite);
        }

        public static UserJoinNotification b(InputStream inputStream) throws IOException {
            return (UserJoinNotification) b(h, inputStream);
        }

        public static UserJoinNotification b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UserJoinNotification) b(h, inputStream, extensionRegistryLite);
        }

        public static UserJoinNotification a(CodedInputStream codedInputStream) throws IOException {
            return (UserJoinNotification) GeneratedMessageLite.b(h, codedInputStream);
        }

        public static UserJoinNotification a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UserJoinNotification) GeneratedMessageLite.b(h, codedInputStream, extensionRegistryLite);
        }

        public static Builder h() {
            return (Builder) h.Y();
        }

        public static Builder a(UserJoinNotification userJoinNotification) {
            return (Builder) ((Builder) h.Y()).b(userJoinNotification);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UserJoinNotification, Builder> implements UserJoinNotificationOrBuilder {
            private Builder() {
                super(UserJoinNotification.h);
            }

            public boolean a() {
                return ((UserJoinNotification) this.f11310a).a();
            }

            public long b() {
                return ((UserJoinNotification) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((UserJoinNotification) this.f11310a).a(j);
                return this;
            }

            public Builder h() {
                S();
                ((UserJoinNotification) this.f11310a).m();
                return this;
            }

            public boolean c() {
                return ((UserJoinNotification) this.f11310a).c();
            }

            public String d() {
                return ((UserJoinNotification) this.f11310a).d();
            }

            public ByteString e() {
                return ((UserJoinNotification) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((UserJoinNotification) this.f11310a).a(str);
                return this;
            }

            public Builder i() {
                S();
                ((UserJoinNotification) this.f11310a).n();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((UserJoinNotification) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((UserJoinNotification) this.f11310a).f();
            }

            public UserInfo g() {
                return ((UserJoinNotification) this.f11310a).g();
            }

            public Builder a(UserInfo userInfo) {
                S();
                ((UserJoinNotification) this.f11310a).a(userInfo);
                return this;
            }

            public Builder a(UserInfo.Builder builder) {
                S();
                ((UserJoinNotification) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UserInfo userInfo) {
                S();
                ((UserJoinNotification) this.f11310a).b(userInfo);
                return this;
            }

            public Builder j() {
                S();
                ((UserJoinNotification) this.f11310a).o();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UserJoinNotification();
                case IS_INITIALIZED:
                    return h;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    UserJoinNotification userJoinNotification = (UserJoinNotification) obj2;
                    this.e = visitor.a(a(), this.e, userJoinNotification.a(), userJoinNotification.e);
                    this.f = visitor.a(c(), this.f, userJoinNotification.c(), userJoinNotification.f);
                    this.g = (UserInfo) visitor.a(this.g, userJoinNotification.g);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.d |= userJoinNotification.d;
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
                                    this.e = codedInputStream.f();
                                } else if (a2 == 18) {
                                    String l = codedInputStream.l();
                                    this.d |= 2;
                                    this.f = l;
                                } else if (a2 == 26) {
                                    UserInfo.Builder builder = (this.d & 4) == 4 ? (UserInfo.Builder) this.g.Y() : null;
                                    this.g = (UserInfo) codedInputStream.a(UserInfo.G(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.g);
                                        this.g = (UserInfo) builder.Y();
                                    }
                                    this.d |= 4;
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
                        synchronized (UserJoinNotification.class) {
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

        public static UserJoinNotification i() {
            return h;
        }

        public static Parser<UserJoinNotification> j() {
            return h.M();
        }
    }

    public static final class UserLeaveNotification extends GeneratedMessageLite<UserLeaveNotification, Builder> implements UserLeaveNotificationOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11291a = 1;
        public static final int b = 2;
        public static final int c = 3;
        /* access modifiers changed from: private */
        public static final UserLeaveNotification h = new UserLeaveNotification();
        private static volatile Parser<UserLeaveNotification> i;
        private int d;
        private long e;
        private String f = "";
        private UserInfo g;

        private UserLeaveNotification() {
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

        public UserInfo g() {
            return this.g == null ? UserInfo.F() : this.g;
        }

        /* access modifiers changed from: private */
        public void a(UserInfo userInfo) {
            if (userInfo != null) {
                this.g = userInfo;
                this.d |= 4;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void a(UserInfo.Builder builder) {
            this.g = (UserInfo) builder.Z();
            this.d |= 4;
        }

        /* access modifiers changed from: private */
        public void b(UserInfo userInfo) {
            if (this.g == null || this.g == UserInfo.F()) {
                this.g = userInfo;
            } else {
                this.g = (UserInfo) ((UserInfo.Builder) UserInfo.a(this.g).b(userInfo)).Y();
            }
            this.d |= 4;
        }

        /* access modifiers changed from: private */
        public void o() {
            this.g = null;
            this.d &= -5;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.d & 1) == 1) {
                codedOutputStream.b(1, this.e);
            }
            if ((this.d & 2) == 2) {
                codedOutputStream.a(2, d());
            }
            if ((this.d & 4) == 4) {
                codedOutputStream.a(3, (MessageLite) g());
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
                i3 += CodedOutputStream.c(3, (MessageLite) g());
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UserLeaveNotification a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UserLeaveNotification) GeneratedMessageLite.a(h, byteString);
        }

        public static UserLeaveNotification a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UserLeaveNotification) GeneratedMessageLite.a(h, byteString, extensionRegistryLite);
        }

        public static UserLeaveNotification a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UserLeaveNotification) GeneratedMessageLite.a(h, bArr);
        }

        public static UserLeaveNotification a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UserLeaveNotification) GeneratedMessageLite.a(h, bArr, extensionRegistryLite);
        }

        public static UserLeaveNotification a(InputStream inputStream) throws IOException {
            return (UserLeaveNotification) GeneratedMessageLite.a(h, inputStream);
        }

        public static UserLeaveNotification a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UserLeaveNotification) GeneratedMessageLite.a(h, inputStream, extensionRegistryLite);
        }

        public static UserLeaveNotification b(InputStream inputStream) throws IOException {
            return (UserLeaveNotification) b(h, inputStream);
        }

        public static UserLeaveNotification b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UserLeaveNotification) b(h, inputStream, extensionRegistryLite);
        }

        public static UserLeaveNotification a(CodedInputStream codedInputStream) throws IOException {
            return (UserLeaveNotification) GeneratedMessageLite.b(h, codedInputStream);
        }

        public static UserLeaveNotification a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UserLeaveNotification) GeneratedMessageLite.b(h, codedInputStream, extensionRegistryLite);
        }

        public static Builder h() {
            return (Builder) h.Y();
        }

        public static Builder a(UserLeaveNotification userLeaveNotification) {
            return (Builder) ((Builder) h.Y()).b(userLeaveNotification);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UserLeaveNotification, Builder> implements UserLeaveNotificationOrBuilder {
            private Builder() {
                super(UserLeaveNotification.h);
            }

            public boolean a() {
                return ((UserLeaveNotification) this.f11310a).a();
            }

            public long b() {
                return ((UserLeaveNotification) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((UserLeaveNotification) this.f11310a).a(j);
                return this;
            }

            public Builder h() {
                S();
                ((UserLeaveNotification) this.f11310a).m();
                return this;
            }

            public boolean c() {
                return ((UserLeaveNotification) this.f11310a).c();
            }

            public String d() {
                return ((UserLeaveNotification) this.f11310a).d();
            }

            public ByteString e() {
                return ((UserLeaveNotification) this.f11310a).e();
            }

            public Builder a(String str) {
                S();
                ((UserLeaveNotification) this.f11310a).a(str);
                return this;
            }

            public Builder i() {
                S();
                ((UserLeaveNotification) this.f11310a).n();
                return this;
            }

            public Builder a(ByteString byteString) {
                S();
                ((UserLeaveNotification) this.f11310a).c(byteString);
                return this;
            }

            public boolean f() {
                return ((UserLeaveNotification) this.f11310a).f();
            }

            public UserInfo g() {
                return ((UserLeaveNotification) this.f11310a).g();
            }

            public Builder a(UserInfo userInfo) {
                S();
                ((UserLeaveNotification) this.f11310a).a(userInfo);
                return this;
            }

            public Builder a(UserInfo.Builder builder) {
                S();
                ((UserLeaveNotification) this.f11310a).a(builder);
                return this;
            }

            public Builder b(UserInfo userInfo) {
                S();
                ((UserLeaveNotification) this.f11310a).b(userInfo);
                return this;
            }

            public Builder j() {
                S();
                ((UserLeaveNotification) this.f11310a).o();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UserLeaveNotification();
                case IS_INITIALIZED:
                    return h;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    UserLeaveNotification userLeaveNotification = (UserLeaveNotification) obj2;
                    this.e = visitor.a(a(), this.e, userLeaveNotification.a(), userLeaveNotification.e);
                    this.f = visitor.a(c(), this.f, userLeaveNotification.c(), userLeaveNotification.f);
                    this.g = (UserInfo) visitor.a(this.g, userLeaveNotification.g);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.d |= userLeaveNotification.d;
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
                                    this.e = codedInputStream.f();
                                } else if (a2 == 18) {
                                    String l = codedInputStream.l();
                                    this.d |= 2;
                                    this.f = l;
                                } else if (a2 == 26) {
                                    UserInfo.Builder builder = (this.d & 4) == 4 ? (UserInfo.Builder) this.g.Y() : null;
                                    this.g = (UserInfo) codedInputStream.a(UserInfo.G(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.b(this.g);
                                        this.g = (UserInfo) builder.Y();
                                    }
                                    this.d |= 4;
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
                        synchronized (UserLeaveNotification.class) {
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

        public static UserLeaveNotification i() {
            return h;
        }

        public static Parser<UserLeaveNotification> j() {
            return h.M();
        }
    }

    public static final class UpdateCallInfo extends GeneratedMessageLite<UpdateCallInfo, Builder> implements UpdateCallInfoOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11286a = 1;
        public static final int b = 2;
        /* access modifiers changed from: private */
        public static final UpdateCallInfo f = new UpdateCallInfo();
        private static volatile Parser<UpdateCallInfo> g;
        private int c;
        private long d;
        private int e = 1;

        private UpdateCallInfo() {
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

        public UpdateType d() {
            UpdateType forNumber = UpdateType.forNumber(this.e);
            return forNumber == null ? UpdateType.UPDATE : forNumber;
        }

        /* access modifiers changed from: private */
        public void a(UpdateType updateType) {
            if (updateType != null) {
                this.c |= 2;
                this.e = updateType.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void j() {
            this.c &= -3;
            this.e = 1;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.c & 1) == 1) {
                codedOutputStream.b(1, this.d);
            }
            if ((this.c & 2) == 2) {
                codedOutputStream.g(2, this.e);
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
                i2 += CodedOutputStream.m(2, this.e);
            }
            int e2 = i2 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static UpdateCallInfo a(ByteString byteString) throws InvalidProtocolBufferException {
            return (UpdateCallInfo) GeneratedMessageLite.a(f, byteString);
        }

        public static UpdateCallInfo a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateCallInfo) GeneratedMessageLite.a(f, byteString, extensionRegistryLite);
        }

        public static UpdateCallInfo a(byte[] bArr) throws InvalidProtocolBufferException {
            return (UpdateCallInfo) GeneratedMessageLite.a(f, bArr);
        }

        public static UpdateCallInfo a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdateCallInfo) GeneratedMessageLite.a(f, bArr, extensionRegistryLite);
        }

        public static UpdateCallInfo a(InputStream inputStream) throws IOException {
            return (UpdateCallInfo) GeneratedMessageLite.a(f, inputStream);
        }

        public static UpdateCallInfo a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateCallInfo) GeneratedMessageLite.a(f, inputStream, extensionRegistryLite);
        }

        public static UpdateCallInfo b(InputStream inputStream) throws IOException {
            return (UpdateCallInfo) b(f, inputStream);
        }

        public static UpdateCallInfo b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateCallInfo) b(f, inputStream, extensionRegistryLite);
        }

        public static UpdateCallInfo a(CodedInputStream codedInputStream) throws IOException {
            return (UpdateCallInfo) GeneratedMessageLite.b(f, codedInputStream);
        }

        public static UpdateCallInfo a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdateCallInfo) GeneratedMessageLite.b(f, codedInputStream, extensionRegistryLite);
        }

        public static Builder e() {
            return (Builder) f.Y();
        }

        public static Builder a(UpdateCallInfo updateCallInfo) {
            return (Builder) ((Builder) f.Y()).b(updateCallInfo);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<UpdateCallInfo, Builder> implements UpdateCallInfoOrBuilder {
            private Builder() {
                super(UpdateCallInfo.f);
            }

            public boolean a() {
                return ((UpdateCallInfo) this.f11310a).a();
            }

            public long b() {
                return ((UpdateCallInfo) this.f11310a).b();
            }

            public Builder a(long j) {
                S();
                ((UpdateCallInfo) this.f11310a).a(j);
                return this;
            }

            public Builder e() {
                S();
                ((UpdateCallInfo) this.f11310a).i();
                return this;
            }

            public boolean c() {
                return ((UpdateCallInfo) this.f11310a).c();
            }

            public UpdateType d() {
                return ((UpdateCallInfo) this.f11310a).d();
            }

            public Builder a(UpdateType updateType) {
                S();
                ((UpdateCallInfo) this.f11310a).a(updateType);
                return this;
            }

            public Builder f() {
                S();
                ((UpdateCallInfo) this.f11310a).j();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UpdateCallInfo();
                case IS_INITIALIZED:
                    return f;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    UpdateCallInfo updateCallInfo = (UpdateCallInfo) obj2;
                    this.d = visitor.a(a(), this.d, updateCallInfo.a(), updateCallInfo.d);
                    this.e = visitor.a(c(), this.e, updateCallInfo.c(), updateCallInfo.e);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.c |= updateCallInfo.c;
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
                                    this.d = codedInputStream.f();
                                } else if (a2 == 16) {
                                    int r = codedInputStream.r();
                                    if (UpdateType.forNumber(r) == null) {
                                        super.a(2, r);
                                    } else {
                                        this.c |= 2;
                                        this.e = r;
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
                    if (g == null) {
                        synchronized (UpdateCallInfo.class) {
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

        public static UpdateCallInfo f() {
            return f;
        }

        public static Parser<UpdateCallInfo> g() {
            return f.M();
        }
    }
}
