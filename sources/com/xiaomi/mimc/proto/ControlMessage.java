package com.xiaomi.mimc.proto;

import com.xiaomi.mimc.protobuf.ByteString;
import com.xiaomi.mimc.protobuf.CodedInputStream;
import com.xiaomi.mimc.protobuf.CodedOutputStream;
import com.xiaomi.mimc.protobuf.ExtensionRegistryLite;
import com.xiaomi.mimc.protobuf.GeneratedMessageLite;
import com.xiaomi.mimc.protobuf.InvalidProtocolBufferException;
import com.xiaomi.mimc.protobuf.MessageLiteOrBuilder;
import com.xiaomi.mimc.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class ControlMessage {

    public interface PushServiceConfigMsgOrBuilder extends MessageLiteOrBuilder {
        boolean a();

        boolean b();

        boolean c();

        boolean d();

        boolean e();

        int f();

        boolean g();

        int h();

        boolean i();

        int j();
    }

    public static void a(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ControlMessage() {
    }

    public static final class PushServiceConfigMsg extends GeneratedMessageLite<PushServiceConfigMsg, Builder> implements PushServiceConfigMsgOrBuilder {

        /* renamed from: a  reason: collision with root package name */
        public static final int f11213a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        /* access modifiers changed from: private */
        public static final PushServiceConfigMsg l = new PushServiceConfigMsg();
        private static volatile Parser<PushServiceConfigMsg> p;
        private int f;
        private boolean g;
        private boolean h;
        private int i;
        private int j;
        private int k;

        private PushServiceConfigMsg() {
        }

        public boolean a() {
            return (this.f & 1) == 1;
        }

        public boolean b() {
            return this.g;
        }

        /* access modifiers changed from: private */
        public void a(boolean z) {
            this.f |= 1;
            this.g = z;
        }

        /* access modifiers changed from: private */
        public void p() {
            this.f &= -2;
            this.g = false;
        }

        public boolean c() {
            return (this.f & 2) == 2;
        }

        public boolean d() {
            return this.h;
        }

        /* access modifiers changed from: private */
        public void b(boolean z) {
            this.f |= 2;
            this.h = z;
        }

        /* access modifiers changed from: private */
        public void q() {
            this.f &= -3;
            this.h = false;
        }

        public boolean e() {
            return (this.f & 4) == 4;
        }

        public int f() {
            return this.i;
        }

        /* access modifiers changed from: private */
        public void a(int i2) {
            this.f |= 4;
            this.i = i2;
        }

        /* access modifiers changed from: private */
        public void r() {
            this.f &= -5;
            this.i = 0;
        }

        public boolean g() {
            return (this.f & 8) == 8;
        }

        public int h() {
            return this.j;
        }

        /* access modifiers changed from: private */
        public void b(int i2) {
            this.f |= 8;
            this.j = i2;
        }

        /* access modifiers changed from: private */
        public void s() {
            this.f &= -9;
            this.j = 0;
        }

        public boolean i() {
            return (this.f & 16) == 16;
        }

        public int j() {
            return this.k;
        }

        /* access modifiers changed from: private */
        public void c(int i2) {
            this.f |= 16;
            this.k = i2;
        }

        /* access modifiers changed from: private */
        public void t() {
            this.f &= -17;
            this.k = 0;
        }

        public void a(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.f & 1) == 1) {
                codedOutputStream.a(1, this.g);
            }
            if ((this.f & 2) == 2) {
                codedOutputStream.a(2, this.h);
            }
            if ((this.f & 4) == 4) {
                codedOutputStream.b(3, this.i);
            }
            if ((this.f & 8) == 8) {
                codedOutputStream.b(4, this.j);
            }
            if ((this.f & 16) == 16) {
                codedOutputStream.b(5, this.k);
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
                i3 = 0 + CodedOutputStream.b(1, this.g);
            }
            if ((this.f & 2) == 2) {
                i3 += CodedOutputStream.b(2, this.h);
            }
            if ((this.f & 4) == 4) {
                i3 += CodedOutputStream.h(3, this.i);
            }
            if ((this.f & 8) == 8) {
                i3 += CodedOutputStream.h(4, this.j);
            }
            if ((this.f & 16) == 16) {
                i3 += CodedOutputStream.h(5, this.k);
            }
            int e2 = i3 + this.n.e();
            this.o = e2;
            return e2;
        }

        public static PushServiceConfigMsg a(ByteString byteString) throws InvalidProtocolBufferException {
            return (PushServiceConfigMsg) GeneratedMessageLite.a(l, byteString);
        }

        public static PushServiceConfigMsg a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PushServiceConfigMsg) GeneratedMessageLite.a(l, byteString, extensionRegistryLite);
        }

        public static PushServiceConfigMsg a(byte[] bArr) throws InvalidProtocolBufferException {
            return (PushServiceConfigMsg) GeneratedMessageLite.a(l, bArr);
        }

        public static PushServiceConfigMsg a(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PushServiceConfigMsg) GeneratedMessageLite.a(l, bArr, extensionRegistryLite);
        }

        public static PushServiceConfigMsg a(InputStream inputStream) throws IOException {
            return (PushServiceConfigMsg) GeneratedMessageLite.a(l, inputStream);
        }

        public static PushServiceConfigMsg a(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PushServiceConfigMsg) GeneratedMessageLite.a(l, inputStream, extensionRegistryLite);
        }

        public static PushServiceConfigMsg b(InputStream inputStream) throws IOException {
            return (PushServiceConfigMsg) b(l, inputStream);
        }

        public static PushServiceConfigMsg b(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PushServiceConfigMsg) b(l, inputStream, extensionRegistryLite);
        }

        public static PushServiceConfigMsg a(CodedInputStream codedInputStream) throws IOException {
            return (PushServiceConfigMsg) GeneratedMessageLite.b(l, codedInputStream);
        }

        public static PushServiceConfigMsg a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PushServiceConfigMsg) GeneratedMessageLite.b(l, codedInputStream, extensionRegistryLite);
        }

        public static Builder l() {
            return (Builder) l.Y();
        }

        public static Builder a(PushServiceConfigMsg pushServiceConfigMsg) {
            return (Builder) ((Builder) l.Y()).b(pushServiceConfigMsg);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<PushServiceConfigMsg, Builder> implements PushServiceConfigMsgOrBuilder {
            private Builder() {
                super(PushServiceConfigMsg.l);
            }

            public boolean a() {
                return ((PushServiceConfigMsg) this.f11310a).a();
            }

            public boolean b() {
                return ((PushServiceConfigMsg) this.f11310a).b();
            }

            public Builder a(boolean z) {
                S();
                ((PushServiceConfigMsg) this.f11310a).a(z);
                return this;
            }

            public Builder k() {
                S();
                ((PushServiceConfigMsg) this.f11310a).p();
                return this;
            }

            public boolean c() {
                return ((PushServiceConfigMsg) this.f11310a).c();
            }

            public boolean d() {
                return ((PushServiceConfigMsg) this.f11310a).d();
            }

            public Builder b(boolean z) {
                S();
                ((PushServiceConfigMsg) this.f11310a).b(z);
                return this;
            }

            public Builder l() {
                S();
                ((PushServiceConfigMsg) this.f11310a).q();
                return this;
            }

            public boolean e() {
                return ((PushServiceConfigMsg) this.f11310a).e();
            }

            public int f() {
                return ((PushServiceConfigMsg) this.f11310a).f();
            }

            public Builder a(int i) {
                S();
                ((PushServiceConfigMsg) this.f11310a).a(i);
                return this;
            }

            public Builder m() {
                S();
                ((PushServiceConfigMsg) this.f11310a).r();
                return this;
            }

            public boolean g() {
                return ((PushServiceConfigMsg) this.f11310a).g();
            }

            public int h() {
                return ((PushServiceConfigMsg) this.f11310a).h();
            }

            public Builder b(int i) {
                S();
                ((PushServiceConfigMsg) this.f11310a).b(i);
                return this;
            }

            public Builder n() {
                S();
                ((PushServiceConfigMsg) this.f11310a).s();
                return this;
            }

            public boolean i() {
                return ((PushServiceConfigMsg) this.f11310a).i();
            }

            public int j() {
                return ((PushServiceConfigMsg) this.f11310a).j();
            }

            public Builder c(int i) {
                S();
                ((PushServiceConfigMsg) this.f11310a).c(i);
                return this;
            }

            public Builder o() {
                S();
                ((PushServiceConfigMsg) this.f11310a).t();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object a(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new PushServiceConfigMsg();
                case IS_INITIALIZED:
                    return l;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    PushServiceConfigMsg pushServiceConfigMsg = (PushServiceConfigMsg) obj2;
                    this.g = visitor.a(a(), this.g, pushServiceConfigMsg.a(), pushServiceConfigMsg.g);
                    this.h = visitor.a(c(), this.h, pushServiceConfigMsg.c(), pushServiceConfigMsg.h);
                    this.i = visitor.a(e(), this.i, pushServiceConfigMsg.e(), pushServiceConfigMsg.i);
                    this.j = visitor.a(g(), this.j, pushServiceConfigMsg.g(), pushServiceConfigMsg.j);
                    this.k = visitor.a(i(), this.k, pushServiceConfigMsg.i(), pushServiceConfigMsg.k);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.f11318a) {
                        this.f |= pushServiceConfigMsg.f;
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
                                    this.f |= 1;
                                    this.g = codedInputStream.k();
                                } else if (a2 == 16) {
                                    this.f |= 2;
                                    this.h = codedInputStream.k();
                                } else if (a2 == 24) {
                                    this.f |= 4;
                                    this.i = codedInputStream.h();
                                } else if (a2 == 32) {
                                    this.f |= 8;
                                    this.j = codedInputStream.h();
                                } else if (a2 == 40) {
                                    this.f |= 16;
                                    this.k = codedInputStream.h();
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
                        synchronized (PushServiceConfigMsg.class) {
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

        public static PushServiceConfigMsg m() {
            return l;
        }

        public static Parser<PushServiceConfigMsg> n() {
            return l.M();
        }
    }
}
