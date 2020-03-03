package com.xiaomi.push;

import java.net.UnknownHostException;

final class he {

    static class a {

        /* renamed from: a  reason: collision with root package name */
        fj f12769a;
        String b;

        a() {
        }
    }

    static a a(Exception exc) {
        e(exc);
        boolean z = exc instanceof gf;
        Throwable th = exc;
        if (z) {
            gf gfVar = (gf) exc;
            th = exc;
            if (gfVar.a() != null) {
                th = gfVar.a();
            }
        }
        a aVar = new a();
        String message = th.getMessage();
        if (th.getCause() != null) {
            message = th.getCause().getMessage();
        }
        String str = th.getClass().getSimpleName() + ":" + message;
        int a2 = fw.a(th);
        if (a2 != 0) {
            aVar.f12769a = fj.a(fj.GSLB_REQUEST_SUCCESS.a() + a2);
        }
        if (aVar.f12769a == null) {
            aVar.f12769a = fj.GSLB_TCP_ERR_OTHER;
        }
        if (aVar.f12769a == fj.GSLB_TCP_ERR_OTHER) {
            aVar.b = str;
        }
        return aVar;
    }

    static a b(Exception exc) {
        fj fjVar;
        Throwable cause;
        e(exc);
        boolean z = exc instanceof gf;
        Throwable th = exc;
        if (z) {
            gf gfVar = (gf) exc;
            th = exc;
            if (gfVar.a() != null) {
                th = gfVar.a();
            }
        }
        a aVar = new a();
        String message = th.getMessage();
        if (th.getCause() != null) {
            message = th.getCause().getMessage();
        }
        int a2 = fw.a(th);
        String str = th.getClass().getSimpleName() + ":" + message;
        if (a2 != 0) {
            aVar.f12769a = fj.a(fj.CONN_SUCCESS.a() + a2);
            if (aVar.f12769a == fj.CONN_BOSH_ERR && (cause = th.getCause()) != null && (cause instanceof UnknownHostException)) {
                fjVar = fj.CONN_BOSH_UNKNOWNHOST;
            }
            if (aVar.f12769a == fj.CONN_TCP_ERR_OTHER || aVar.f12769a == fj.CONN_XMPP_ERR || aVar.f12769a == fj.CONN_BOSH_ERR) {
                aVar.b = str;
            }
            return aVar;
        }
        fjVar = fj.CONN_XMPP_ERR;
        aVar.f12769a = fjVar;
        aVar.b = str;
        return aVar;
    }

    static a c(Exception exc) {
        fj fjVar;
        e(exc);
        boolean z = exc instanceof gf;
        Throwable th = exc;
        if (z) {
            gf gfVar = (gf) exc;
            th = exc;
            if (gfVar.a() != null) {
                th = gfVar.a();
            }
        }
        a aVar = new a();
        String message = th.getMessage();
        if (th.getCause() != null) {
            message = th.getCause().getMessage();
        }
        int a2 = fw.a(th);
        String str = th.getClass().getSimpleName() + ":" + message;
        if (a2 == 105) {
            fjVar = fj.BIND_TCP_READ_TIMEOUT;
        } else if (a2 == 199) {
            fjVar = fj.BIND_TCP_ERR;
        } else if (a2 != 499) {
            switch (a2) {
                case 109:
                    fjVar = fj.BIND_TCP_CONNRESET;
                    break;
                case 110:
                    fjVar = fj.BIND_TCP_BROKEN_PIPE;
                    break;
                default:
                    fjVar = fj.BIND_XMPP_ERR;
                    break;
            }
        } else {
            aVar.f12769a = fj.BIND_BOSH_ERR;
            if (message.startsWith("Terminal binding condition encountered: item-not-found")) {
                fjVar = fj.BIND_BOSH_ITEM_NOT_FOUND;
            }
            if (aVar.f12769a == fj.BIND_TCP_ERR || aVar.f12769a == fj.BIND_XMPP_ERR || aVar.f12769a == fj.BIND_BOSH_ERR) {
                aVar.b = str;
            }
            return aVar;
        }
        aVar.f12769a = fjVar;
        aVar.b = str;
        return aVar;
    }

    static a d(Exception exc) {
        fj fjVar;
        e(exc);
        boolean z = exc instanceof gf;
        Throwable th = exc;
        if (z) {
            gf gfVar = (gf) exc;
            th = exc;
            if (gfVar.a() != null) {
                th = gfVar.a();
            }
        }
        a aVar = new a();
        String message = th.getMessage();
        int a2 = fw.a(th);
        String str = th.getClass().getSimpleName() + ":" + message;
        if (a2 == 105) {
            fjVar = fj.CHANNEL_TCP_READTIMEOUT;
        } else if (a2 == 199) {
            fjVar = fj.CHANNEL_TCP_ERR;
        } else if (a2 != 499) {
            switch (a2) {
                case 109:
                    fjVar = fj.CHANNEL_TCP_CONNRESET;
                    break;
                case 110:
                    fjVar = fj.CHANNEL_TCP_BROKEN_PIPE;
                    break;
                default:
                    fjVar = fj.CHANNEL_XMPPEXCEPTION;
                    break;
            }
        } else {
            aVar.f12769a = fj.CHANNEL_BOSH_EXCEPTION;
            if (message.startsWith("Terminal binding condition encountered: item-not-found")) {
                fjVar = fj.CHANNEL_BOSH_ITEMNOTFIND;
            }
            if (aVar.f12769a == fj.CHANNEL_TCP_ERR || aVar.f12769a == fj.CHANNEL_XMPPEXCEPTION || aVar.f12769a == fj.CHANNEL_BOSH_EXCEPTION) {
                aVar.b = str;
            }
            return aVar;
        }
        aVar.f12769a = fjVar;
        aVar.b = str;
        return aVar;
    }

    private static void e(Exception exc) {
        if (exc == null) {
            throw new NullPointerException();
        }
    }
}
