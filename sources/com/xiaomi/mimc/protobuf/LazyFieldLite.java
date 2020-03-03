package com.xiaomi.mimc.protobuf;

import java.io.IOException;

public class LazyFieldLite {
    private static final ExtensionRegistryLite b = ExtensionRegistryLite.d();

    /* renamed from: a  reason: collision with root package name */
    protected volatile MessageLite f11328a;
    private ByteString c;
    private ExtensionRegistryLite d;
    private volatile ByteString e;

    public int hashCode() {
        return 1;
    }

    public LazyFieldLite(ExtensionRegistryLite extensionRegistryLite, ByteString byteString) {
        a(extensionRegistryLite, byteString);
        this.d = extensionRegistryLite;
        this.c = byteString;
    }

    public LazyFieldLite() {
    }

    public static LazyFieldLite a(MessageLite messageLite) {
        LazyFieldLite lazyFieldLite = new LazyFieldLite();
        lazyFieldLite.c(messageLite);
        return lazyFieldLite;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LazyFieldLite)) {
            return false;
        }
        LazyFieldLite lazyFieldLite = (LazyFieldLite) obj;
        MessageLite messageLite = this.f11328a;
        MessageLite messageLite2 = lazyFieldLite.f11328a;
        if (messageLite == null && messageLite2 == null) {
            return e().equals(lazyFieldLite.e());
        }
        if (messageLite != null && messageLite2 != null) {
            return messageLite.equals(messageLite2);
        }
        if (messageLite != null) {
            return messageLite.equals(lazyFieldLite.b(messageLite.aa()));
        }
        return b(messageLite2.aa()).equals(messageLite2);
    }

    public boolean a() {
        return this.e == ByteString.EMPTY || (this.f11328a == null && (this.c == null || this.c == ByteString.EMPTY));
    }

    public void c() {
        this.c = null;
        this.f11328a = null;
        this.e = null;
    }

    public void a(LazyFieldLite lazyFieldLite) {
        this.c = lazyFieldLite.c;
        this.f11328a = lazyFieldLite.f11328a;
        this.e = lazyFieldLite.e;
        if (lazyFieldLite.d != null) {
            this.d = lazyFieldLite.d;
        }
    }

    public MessageLite b(MessageLite messageLite) {
        d(messageLite);
        return this.f11328a;
    }

    public MessageLite c(MessageLite messageLite) {
        MessageLite messageLite2 = this.f11328a;
        this.c = null;
        this.e = null;
        this.f11328a = messageLite;
        return messageLite2;
    }

    public void b(LazyFieldLite lazyFieldLite) {
        if (!lazyFieldLite.a()) {
            if (a()) {
                a(lazyFieldLite);
                return;
            }
            if (this.d == null) {
                this.d = lazyFieldLite.d;
            }
            if (this.c != null && lazyFieldLite.c != null) {
                this.c = this.c.concat(lazyFieldLite.c);
            } else if (this.f11328a == null && lazyFieldLite.f11328a != null) {
                c(a(lazyFieldLite.f11328a, this.c, this.d));
            } else if (this.f11328a != null && lazyFieldLite.f11328a == null) {
                c(a(this.f11328a, lazyFieldLite.c, lazyFieldLite.d));
            } else if (lazyFieldLite.d != null) {
                c(a(this.f11328a, lazyFieldLite.e(), lazyFieldLite.d));
            } else if (this.d != null) {
                c(a(lazyFieldLite.f11328a, e(), this.d));
            } else {
                c(a(this.f11328a, lazyFieldLite.e(), b));
            }
        }
    }

    public void a(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        if (a()) {
            a(codedInputStream.n(), extensionRegistryLite);
            return;
        }
        if (this.d == null) {
            this.d = extensionRegistryLite;
        }
        if (this.c != null) {
            a(this.c.concat(codedInputStream.n()), this.d);
            return;
        }
        try {
            c(this.f11328a.Y().b(codedInputStream, extensionRegistryLite).Z());
        } catch (InvalidProtocolBufferException unused) {
        }
    }

    private static MessageLite a(MessageLite messageLite, ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        try {
            return messageLite.Y().b(byteString, extensionRegistryLite).Z();
        } catch (InvalidProtocolBufferException unused) {
            return messageLite;
        }
    }

    public void a(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        a(extensionRegistryLite, byteString);
        this.c = byteString;
        this.d = extensionRegistryLite;
        this.f11328a = null;
        this.e = null;
    }

    public int d() {
        if (this.e != null) {
            return this.e.size();
        }
        if (this.c != null) {
            return this.c.size();
        }
        if (this.f11328a != null) {
            return this.f11328a.k();
        }
        return 0;
    }

    public ByteString e() {
        if (this.e != null) {
            return this.e;
        }
        if (this.c != null) {
            return this.c;
        }
        synchronized (this) {
            if (this.e != null) {
                ByteString byteString = this.e;
                return byteString;
            }
            if (this.f11328a == null) {
                this.e = ByteString.EMPTY;
            } else {
                this.e = this.f11328a.J();
            }
            ByteString byteString2 = this.e;
            return byteString2;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r3.f11328a = r4;
        r3.e = com.xiaomi.mimc.protobuf.ByteString.EMPTY;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x002c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d(com.xiaomi.mimc.protobuf.MessageLite r4) {
        /*
            r3 = this;
            com.xiaomi.mimc.protobuf.MessageLite r0 = r3.f11328a
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            monitor-enter(r3)
            com.xiaomi.mimc.protobuf.MessageLite r0 = r3.f11328a     // Catch:{ all -> 0x0034 }
            if (r0 == 0) goto L_0x000c
            monitor-exit(r3)     // Catch:{ all -> 0x0034 }
            return
        L_0x000c:
            com.xiaomi.mimc.protobuf.ByteString r0 = r3.c     // Catch:{ InvalidProtocolBufferException -> 0x002c }
            if (r0 == 0) goto L_0x0025
            com.xiaomi.mimc.protobuf.Parser r0 = r4.M()     // Catch:{ InvalidProtocolBufferException -> 0x002c }
            com.xiaomi.mimc.protobuf.ByteString r1 = r3.c     // Catch:{ InvalidProtocolBufferException -> 0x002c }
            com.xiaomi.mimc.protobuf.ExtensionRegistryLite r2 = r3.d     // Catch:{ InvalidProtocolBufferException -> 0x002c }
            java.lang.Object r0 = r0.d((com.xiaomi.mimc.protobuf.ByteString) r1, (com.xiaomi.mimc.protobuf.ExtensionRegistryLite) r2)     // Catch:{ InvalidProtocolBufferException -> 0x002c }
            com.xiaomi.mimc.protobuf.MessageLite r0 = (com.xiaomi.mimc.protobuf.MessageLite) r0     // Catch:{ InvalidProtocolBufferException -> 0x002c }
            r3.f11328a = r0     // Catch:{ InvalidProtocolBufferException -> 0x002c }
            com.xiaomi.mimc.protobuf.ByteString r0 = r3.c     // Catch:{ InvalidProtocolBufferException -> 0x002c }
            r3.e = r0     // Catch:{ InvalidProtocolBufferException -> 0x002c }
            goto L_0x0032
        L_0x0025:
            r3.f11328a = r4     // Catch:{ InvalidProtocolBufferException -> 0x002c }
            com.xiaomi.mimc.protobuf.ByteString r0 = com.xiaomi.mimc.protobuf.ByteString.EMPTY     // Catch:{ InvalidProtocolBufferException -> 0x002c }
            r3.e = r0     // Catch:{ InvalidProtocolBufferException -> 0x002c }
            goto L_0x0032
        L_0x002c:
            r3.f11328a = r4     // Catch:{ all -> 0x0034 }
            com.xiaomi.mimc.protobuf.ByteString r4 = com.xiaomi.mimc.protobuf.ByteString.EMPTY     // Catch:{ all -> 0x0034 }
            r3.e = r4     // Catch:{ all -> 0x0034 }
        L_0x0032:
            monitor-exit(r3)     // Catch:{ all -> 0x0034 }
            return
        L_0x0034:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0034 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.protobuf.LazyFieldLite.d(com.xiaomi.mimc.protobuf.MessageLite):void");
    }

    private static void a(ExtensionRegistryLite extensionRegistryLite, ByteString byteString) {
        if (extensionRegistryLite == null) {
            throw new NullPointerException("found null ExtensionRegistry");
        } else if (byteString == null) {
            throw new NullPointerException("found null ByteString");
        }
    }
}
