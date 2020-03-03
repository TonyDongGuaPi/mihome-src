package com.xiaomi.infra.galaxy.fds.android.auth;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.infra.galaxy.fds.android.util.Args;
import kotlin.text.Typography;
import org.apache.http.client.methods.HttpRequestBase;

public class SSOCredential implements GalaxyFDSCredential {

    /* renamed from: a  reason: collision with root package name */
    private final String f10135a = "SSO";
    private final String b = "serviceToken";
    private final String c = "APP_ID";
    private final String d;
    private final String e;

    @Deprecated
    public SSOCredential(String str) {
        Args.a(str, "Service token");
        Args.a(str, "Service token");
        this.d = str;
        this.e = null;
    }

    public SSOCredential(String str, String str2) {
        Args.a(str, "Service token");
        Args.a(str, "Service token");
        Args.a(str2, "App id");
        Args.a(str2, "App id");
        this.d = str;
        this.e = str2;
    }

    public void a(HttpRequestBase httpRequestBase) {
        httpRequestBase.addHeader("Authorization", "SSO");
    }

    public String a(String str) {
        StringBuilder sb = new StringBuilder(str);
        if (str.indexOf(63) == -1) {
            sb.append(Operators.CONDITION_IF);
        } else {
            sb.append(Typography.c);
        }
        sb.append("serviceToken");
        sb.append('=');
        sb.append(this.d);
        if (this.e != null) {
            sb.append(Typography.c);
            sb.append("APP_ID");
            sb.append('=');
            sb.append(this.e);
        }
        return sb.toString();
    }
}
