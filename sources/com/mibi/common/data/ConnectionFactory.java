package com.mibi.common.data;

import android.content.Context;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.data.Client;
import com.mibi.common.mock.IConnectionFactoryMock;

public class ConnectionFactory {

    /* renamed from: a  reason: collision with root package name */
    private static IConnectionFactoryMock f7514a;

    public static final void a(IConnectionFactoryMock iConnectionFactoryMock) {
        f7514a = iConnectionFactoryMock;
    }

    public static final Connection a(Context context, Session session, String str) {
        if (session.e() instanceof FakeAccountLoader) {
            return a(context, str);
        }
        return a(str, session);
    }

    public static final Connection a(Context context, String str) {
        return a(context, str, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000e, code lost:
        r1 = f7514a.a(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.mibi.common.data.Connection a(android.content.Context r1, java.lang.String r2, boolean r3) {
        /*
            com.mibi.common.data.ConnectionDefault r0 = new com.mibi.common.data.ConnectionDefault
            r0.<init>(r2)
            if (r3 == 0) goto L_0x000a
            a((android.content.Context) r1, (com.mibi.common.data.Connection) r0)
        L_0x000a:
            com.mibi.common.mock.IConnectionFactoryMock r1 = f7514a
            if (r1 == 0) goto L_0x0017
            com.mibi.common.mock.IConnectionFactoryMock r1 = f7514a
            com.mibi.common.data.Connection r1 = r1.a(r0)
            if (r1 == 0) goto L_0x0017
            return r1
        L_0x0017:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.ConnectionFactory.a(android.content.Context, java.lang.String, boolean):com.mibi.common.data.Connection");
    }

    public static final Connection a(String str, Session session) {
        return a(str, session, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0046, code lost:
        r4 = f7514a.a(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.mibi.common.data.Connection a(java.lang.String r4, com.mibi.common.data.Session r5, boolean r6) {
        /*
            com.mibi.common.account.AccountLoader r0 = r5.e()
            com.mibi.common.account.AccountToken r0 = r0.b()
            com.mibi.common.data.ConnectionAccount r1 = new com.mibi.common.data.ConnectionAccount
            java.lang.String r2 = r0.c()
            java.lang.String r3 = r0.b()
            java.lang.String r0 = r0.a()
            r1.<init>(r4, r2, r3, r0)
            if (r6 == 0) goto L_0x0022
            android.content.Context r4 = r5.i()
            a((android.content.Context) r4, (com.mibi.common.data.Connection) r1)
        L_0x0022:
            com.mibi.common.data.SortedParameter r4 = r1.d()
            java.lang.String r6 = "userId"
            java.lang.String r0 = r5.f()
            r4.a((java.lang.String) r6, (java.lang.Object) r0)
            java.lang.String r6 = "session"
            java.lang.String r0 = r5.g()
            r4.a((java.lang.String) r6, (java.lang.Object) r0)
            java.lang.String r6 = "deviceId"
            java.lang.String r5 = com.mibi.common.data.DeviceManager.b(r5)
            r4.a((java.lang.String) r6, (java.lang.Object) r5)
            com.mibi.common.mock.IConnectionFactoryMock r4 = f7514a
            if (r4 == 0) goto L_0x004f
            com.mibi.common.mock.IConnectionFactoryMock r4 = f7514a
            com.mibi.common.data.Connection r4 = r4.a(r1)
            if (r4 == 0) goto L_0x004f
            return r4
        L_0x004f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.ConnectionFactory.a(java.lang.String, com.mibi.common.data.Session, boolean):com.mibi.common.data.Connection");
    }

    public static final Connection b(Context context, String str) {
        return b(context, str, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000e, code lost:
        r1 = f7514a.a(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.mibi.common.data.Connection b(android.content.Context r1, java.lang.String r2, boolean r3) {
        /*
            com.mibi.common.data.ConnectionSaltSignature r0 = new com.mibi.common.data.ConnectionSaltSignature
            r0.<init>(r2)
            if (r3 == 0) goto L_0x000a
            a((android.content.Context) r1, (com.mibi.common.data.Connection) r0)
        L_0x000a:
            com.mibi.common.mock.IConnectionFactoryMock r1 = f7514a
            if (r1 == 0) goto L_0x0017
            com.mibi.common.mock.IConnectionFactoryMock r1 = f7514a
            com.mibi.common.data.Connection r1 = r1.a(r0)
            if (r1 == 0) goto L_0x0017
            return r1
        L_0x0017:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.ConnectionFactory.b(android.content.Context, java.lang.String, boolean):com.mibi.common.data.Connection");
    }

    public static final Connection a(Connection connection, Session session) {
        return new ConnectionCacheReader(connection, session);
    }

    public static final Connection b(Connection connection, Session session) {
        return new ConnectionCacheWriter(connection, session);
    }

    private static final void a(Context context, Connection connection) {
        SortedParameter d = connection.d();
        d.a("la", (Object) Client.a());
        d.a("co", (Object) Client.b());
        d.a("uuid", (Object) Client.A().j());
        Client.AppInfo F = Client.F();
        d.a("package", (Object) F.e());
        d.a("apkSign", (Object) F.f());
        d.a("version", (Object) F.a());
        d.a("versionCode", (Object) Integer.valueOf(F.b()));
        d.a("versionName", (Object) F.c());
        d.a("networkType", (Object) Integer.valueOf(Client.c(context)));
        d.a("networkMeter", (Object) Boolean.valueOf(Client.b(context)));
    }
}
