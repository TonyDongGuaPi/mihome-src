package com.ximalaya.ting.android.opensdk.auth.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.ximalaya.ting.android.opensdk.auth.call.IXmlyAuthListener;
import com.ximalaya.ting.android.opensdk.auth.component.XmlyBrowserComponent;
import com.ximalaya.ting.android.opensdk.auth.model.XmlyAuthInfo;
import com.ximalaya.ting.android.opensdk.auth.model.XmlyParameters;
import com.ximalaya.ting.android.opensdk.auth.utils.Logger;
import com.ximalaya.ting.android.opensdk.auth.utils.c;
import com.ximalaya.ting.android.opensdk.auth.utils.g;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1833a = "b";
    private static final String b = "无法连接到网络，请检查网络配置";
    private static final String c = "https://m.ximalaya.com/login?";
    private static final String d = "https://m.ximalaya.com/xiaoya/login?";
    private static final String e = "https://api.ximalaya.com/oauth2/v2/authorize?";
    private static final String f = "UTF-8";
    private Context g;
    private XmlyAuthInfo h;

    public b(Context context, XmlyAuthInfo xmlyAuthInfo) {
        this.g = context;
        this.h = xmlyAuthInfo;
    }

    private XmlyAuthInfo a() {
        return this.h;
    }

    private void c(IXmlyAuthListener iXmlyAuthListener) {
        String str;
        if (iXmlyAuthListener != null) {
            XmlyParameters xmlyParameters = new XmlyParameters(this.h.a());
            xmlyParameters.a("client_id", this.h.a());
            xmlyParameters.a("response_type", "code");
            xmlyParameters.a("redirect_uri", this.h.b());
            xmlyParameters.a("device_id", this.h.c());
            xmlyParameters.a("client_os_type", "2");
            xmlyParameters.a("pack_id", this.h.d());
            try {
                str = URLEncoder.encode(e, "UTF-8") + a(xmlyParameters);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                str = "";
            }
            String str2 = "https://m.ximalaya.com/login?fromUri=" + str;
            if (!c.a(this.g)) {
                g.a(this.g, "Error", "Application requires permission to access the Internet");
                return;
            }
            com.ximalaya.ting.android.opensdk.auth.component.c cVar = new com.ximalaya.ting.android.opensdk.auth.component.c(this.g);
            cVar.a(this.h);
            cVar.a(iXmlyAuthListener);
            cVar.a(str2);
            cVar.b("喜马拉雅登录");
            Bundle d2 = cVar.d();
            Intent intent = new Intent(this.g, XmlyBrowserComponent.class);
            intent.putExtras(d2);
            this.g.startActivity(intent);
        }
    }

    private void d(IXmlyAuthListener iXmlyAuthListener) {
        String str;
        if (iXmlyAuthListener != null) {
            XmlyParameters xmlyParameters = new XmlyParameters(this.h.a());
            xmlyParameters.a("client_id", this.h.a());
            xmlyParameters.a("response_type", "code");
            xmlyParameters.a("redirect_uri", this.h.b());
            xmlyParameters.a("device_id", this.h.c());
            xmlyParameters.a("client_os_type", "2");
            xmlyParameters.a("pack_id", this.h.d());
            try {
                str = URLEncoder.encode(e, "UTF-8") + a(xmlyParameters);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                str = "";
            }
            String str2 = "https://m.ximalaya.com/xiaoya/login?fromUri=" + str;
            if (!c.a(this.g)) {
                g.a(this.g, "Error", "Application requires permission to access the Internet");
                return;
            }
            com.ximalaya.ting.android.opensdk.auth.component.c cVar = new com.ximalaya.ting.android.opensdk.auth.component.c(this.g);
            cVar.a(this.h);
            cVar.a(iXmlyAuthListener);
            cVar.a(str2);
            cVar.b("手机号注册");
            Bundle d2 = cVar.d();
            Intent intent = new Intent(this.g, XmlyBrowserComponent.class);
            intent.putExtras(d2);
            this.g.startActivity(intent);
        }
    }

    private static String a(XmlyParameters xmlyParameters) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String next : xmlyParameters.c()) {
            if (z) {
                z = false;
            } else {
                try {
                    sb.append(URLEncoder.encode(a.b, "UTF-8"));
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
            Object a2 = xmlyParameters.a(next);
            if (a2 instanceof String) {
                String str = (String) a2;
                if (!TextUtils.isEmpty(str)) {
                    try {
                        sb.append(URLEncoder.encode(next, "UTF-8") + URLEncoder.encode("=", "UTF-8") + URLEncoder.encode(str, "UTF-8"));
                    } catch (UnsupportedEncodingException e3) {
                        e3.printStackTrace();
                    }
                }
                Logger.b("encodeUrl", sb.toString());
            }
        }
        return sb.toString();
    }

    public final void a(IXmlyAuthListener iXmlyAuthListener) {
        String str;
        if (iXmlyAuthListener != null) {
            XmlyParameters xmlyParameters = new XmlyParameters(this.h.a());
            xmlyParameters.a("client_id", this.h.a());
            xmlyParameters.a("response_type", "code");
            xmlyParameters.a("redirect_uri", this.h.b());
            xmlyParameters.a("device_id", this.h.c());
            xmlyParameters.a("client_os_type", "2");
            xmlyParameters.a("pack_id", this.h.d());
            try {
                str = URLEncoder.encode(e, "UTF-8") + a(xmlyParameters);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                str = "";
            }
            String str2 = "https://m.ximalaya.com/login?fromUri=" + str;
            if (!c.a(this.g)) {
                g.a(this.g, "Error", "Application requires permission to access the Internet");
                return;
            }
            com.ximalaya.ting.android.opensdk.auth.component.c cVar = new com.ximalaya.ting.android.opensdk.auth.component.c(this.g);
            cVar.a(this.h);
            cVar.a(iXmlyAuthListener);
            cVar.a(str2);
            cVar.b("喜马拉雅登录");
            Bundle d2 = cVar.d();
            Intent intent = new Intent(this.g, XmlyBrowserComponent.class);
            intent.putExtras(d2);
            this.g.startActivity(intent);
        }
    }

    public final void b(IXmlyAuthListener iXmlyAuthListener) {
        String str;
        if (iXmlyAuthListener != null) {
            XmlyParameters xmlyParameters = new XmlyParameters(this.h.a());
            xmlyParameters.a("client_id", this.h.a());
            xmlyParameters.a("response_type", "code");
            xmlyParameters.a("redirect_uri", this.h.b());
            xmlyParameters.a("device_id", this.h.c());
            xmlyParameters.a("client_os_type", "2");
            xmlyParameters.a("pack_id", this.h.d());
            try {
                str = URLEncoder.encode(e, "UTF-8") + a(xmlyParameters);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                str = "";
            }
            String str2 = "https://m.ximalaya.com/xiaoya/login?fromUri=" + str;
            if (!c.a(this.g)) {
                g.a(this.g, "Error", "Application requires permission to access the Internet");
                return;
            }
            com.ximalaya.ting.android.opensdk.auth.component.c cVar = new com.ximalaya.ting.android.opensdk.auth.component.c(this.g);
            cVar.a(this.h);
            cVar.a(iXmlyAuthListener);
            cVar.a(str2);
            cVar.b("手机号注册");
            Bundle d2 = cVar.d();
            Intent intent = new Intent(this.g, XmlyBrowserComponent.class);
            intent.putExtras(d2);
            this.g.startActivity(intent);
        }
    }
}
