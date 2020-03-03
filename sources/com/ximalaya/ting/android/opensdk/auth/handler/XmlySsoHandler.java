package com.ximalaya.ting.android.opensdk.auth.handler;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.auth.call.IXmlyAuthListener;
import com.ximalaya.ting.android.opensdk.auth.constants.XmlyAuthErrorNoConstants;
import com.ximalaya.ting.android.opensdk.auth.constants.XmlyConstants;
import com.ximalaya.ting.android.opensdk.auth.exception.XmlyAuthException;
import com.ximalaya.ting.android.opensdk.auth.exception.XmlyException;
import com.ximalaya.ting.android.opensdk.auth.handler.a;
import com.ximalaya.ting.android.opensdk.auth.model.XmlyAuth2AccessToken;
import com.ximalaya.ting.android.opensdk.auth.model.XmlyAuthInfo;
import com.ximalaya.ting.android.opensdk.auth.utils.AccessTokenKeeper;
import com.ximalaya.ting.android.opensdk.auth.utils.Logger;
import com.ximalaya.ting.android.opensdk.auth.utils.b;
import com.ximalaya.ting.android.opensdk.auth.utils.c;
import com.ximalaya.ting.android.opensdk.auth.utils.e;
import com.ximalaya.ting.android.opensdk.auth.utils.g;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.httputil.XimalayaException;
import java.util.HashMap;
import org.json.JSONObject;

public class XmlySsoHandler {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1828a = "code";
    public static final String b = "token";
    private static final String c = "XmlySsoHandler";
    private static final String d = "com.ximalaya.ting.android";
    private static final String e = "com.ximalaya.ting.android.activity.account.LoginActivity";
    private static final int f = 4096;
    private Activity g;
    private XmlyAuthInfo h;
    private IXmlyAuthListener i;
    private b j;
    private a k;

    public XmlySsoHandler(Activity activity, XmlyAuthInfo xmlyAuthInfo) {
        this.g = activity;
        this.h = xmlyAuthInfo;
        this.j = new b(activity, xmlyAuthInfo);
        this.k = new a(activity);
    }

    public void a(IXmlyAuthListener iXmlyAuthListener) {
        if (!c.b(this.g)) {
            g.a(this.g, "网络不给力，稍后再试试");
        }
        this.i = iXmlyAuthListener;
        if (iXmlyAuthListener == null) {
            return;
        }
        if (!a() || !a("token", false)) {
            this.j.a(iXmlyAuthListener);
        }
    }

    public void a(String str, String str2, IXmlyAuthListener iXmlyAuthListener) {
        if (!c.b(this.g)) {
            g.a(this.g, "网络不给力，稍后再试试");
        }
        this.i = iXmlyAuthListener;
        if (iXmlyAuthListener != null) {
            a aVar = this.k;
            HashMap hashMap = new HashMap();
            hashMap.put(XmlyConstants.i, "token_exchange");
            long currentTimeMillis = System.currentTimeMillis();
            hashMap.put("nonce", b.a(String.valueOf(currentTimeMillis)));
            hashMap.put("timestamp", String.valueOf(currentTimeMillis));
            hashMap.put("third_uid", str);
            hashMap.put("third_token", str2);
            try {
                hashMap.put("client_id", CommonRequest.a().f());
            } catch (XimalayaException e2) {
                e2.printStackTrace();
            }
            CommonRequest.b(DTransferConstants.cT, hashMap, new IDataCallBack<a.C0033a>(str, iXmlyAuthListener) {
                public final /* synthetic */ void a(Object obj) {
                    C0033a aVar = (C0033a) obj;
                    if (aVar != null) {
                        XmlyAuth2AccessToken xmlyAuth2AccessToken = new XmlyAuth2AccessToken(aVar.b(), aVar.a());
                        xmlyAuth2AccessToken.b(r6);
                        if (r8 == null) {
                            return;
                        }
                        if (xmlyAuth2AccessToken.a()) {
                            AccessTokenKeeper.b(a.this.c);
                            AccessTokenKeeper.a(a.this.c, xmlyAuth2AccessToken);
                            r8.a(xmlyAuth2AccessToken.b());
                            return;
                        }
                        r8.a((XmlyException) new XmlyAuthException("token 失效", (String) null, "token 失效"));
                        Logger.a(a.f1829a, "Failed to receive access token by Web");
                    } else if (r8 != null) {
                        r8.a((XmlyException) new XmlyAuthException("json 转换失败", "1007", "json 转换失败"));
                    }
                }

                private void a(C0033a aVar) {
                    if (aVar != null) {
                        XmlyAuth2AccessToken xmlyAuth2AccessToken = new XmlyAuth2AccessToken(aVar.b(), aVar.a());
                        xmlyAuth2AccessToken.b(r6);
                        if (r8 == null) {
                            return;
                        }
                        if (xmlyAuth2AccessToken.a()) {
                            AccessTokenKeeper.b(a.this.c);
                            AccessTokenKeeper.a(a.this.c, xmlyAuth2AccessToken);
                            r8.a(xmlyAuth2AccessToken.b());
                            return;
                        }
                        r8.a((XmlyException) new XmlyAuthException("token 失效", (String) null, "token 失效"));
                        Logger.a(a.f1829a, "Failed to receive access token by Web");
                    } else if (r8 != null) {
                        r8.a((XmlyException) new XmlyAuthException("json 转换失败", "1007", "json 转换失败"));
                    }
                }

                public final void a(int i, String str) {
                    IXmlyAuthListener iXmlyAuthListener = r8;
                    iXmlyAuthListener.a((XmlyException) new XmlyAuthException(i + ":" + str, str, str));
                }
            }, new CommonRequest.IRequestCallBack<a.C0033a>(str) {
                private C0033a a(String str) throws Exception {
                    JSONObject jSONObject = new JSONObject(str);
                    return new C0033a(jSONObject.optString("access_token"), jSONObject.optString("expires_in"), r6);
                }

                public final /* synthetic */ Object b(String str) throws Exception {
                    JSONObject jSONObject = new JSONObject(str);
                    return new C0033a(jSONObject.optString("access_token"), jSONObject.optString("expires_in"), r6);
                }
            });
        }
    }

    public void a(IXmlyAuthListener iXmlyAuthListener, String str) {
        if (!c.b(this.g)) {
            g.a(this.g, "网络不给力，稍后再试试");
        }
        this.i = iXmlyAuthListener;
        if (iXmlyAuthListener == null) {
            return;
        }
        if (!a() || !a(str, false)) {
            this.j.a(iXmlyAuthListener);
        }
    }

    public void b(IXmlyAuthListener iXmlyAuthListener) {
        if (!c.b(this.g)) {
            g.a(this.g, "网络不给力，稍后再试试");
        }
        if (iXmlyAuthListener != null) {
            this.j.a(iXmlyAuthListener);
        }
    }

    public void c(IXmlyAuthListener iXmlyAuthListener) {
        if (!c.b(this.g)) {
            g.a(this.g, "网络不给力，稍后再试试");
        }
        this.i = iXmlyAuthListener;
        if (iXmlyAuthListener == null) {
            return;
        }
        if (!a() || !a("token", true)) {
            this.j.b(iXmlyAuthListener);
        }
    }

    public void a(int i2, int i3, Intent intent) {
        String str;
        Logger.a(c, "requestCode: " + i2 + ", resultCode: " + i3 + ", data: " + intent);
        if (i2 != 4096) {
            return;
        }
        if (i3 == -1) {
            Activity activity = this.g;
            String stringExtra = intent != null ? intent.getStringExtra(XmlyConstants.z) : null;
            if (stringExtra != null && e.a((Context) activity, stringExtra)) {
                String stringExtra2 = intent.getStringExtra("error");
                if (stringExtra2 == null) {
                    stringExtra2 = intent.getStringExtra("error_no");
                }
                if (stringExtra2 == null) {
                    Bundle extras = intent.getExtras();
                    XmlyAuth2AccessToken a2 = XmlyAuth2AccessToken.a(extras);
                    if (a2 == null || !a2.a()) {
                        Logger.a(c, "Failed to receive access token by SSO");
                        this.j.a(this.i);
                        return;
                    }
                    Logger.a(c, "Login Success! " + a2.toString());
                    AccessTokenKeeper.b(this.g);
                    AccessTokenKeeper.a(this.g, a2);
                    this.i.a(extras);
                } else if (stringExtra2.equals("access_denied") || stringExtra2.equals("OAuthAccessDeniedException")) {
                    Logger.a(c, "Login canceled by user.");
                    this.i.a();
                } else {
                    String stringExtra3 = intent.getStringExtra("error_desc");
                    if (stringExtra3 != null) {
                        stringExtra2 = stringExtra2 + ":" + stringExtra3;
                    }
                    Logger.a(c, "Login failed: " + stringExtra2);
                    this.i.a((XmlyException) new XmlyAuthException(stringExtra2, (String) null, stringExtra3));
                }
            }
        } else if (i3 != 0) {
        } else {
            if (intent != null) {
                String stringExtra4 = intent.getStringExtra("error");
                if (TextUtils.isEmpty(stringExtra4)) {
                    String stringExtra5 = intent.getStringExtra("error_no");
                    String stringExtra6 = intent.getStringExtra("error_code");
                    String stringExtra7 = intent.getStringExtra("error_desc");
                    if (!TextUtils.isEmpty(intent.getStringExtra("service"))) {
                        str = " error service" + stringExtra7;
                    } else {
                        str = "";
                    }
                    Logger.a(c, "Login failed: error no = " + stringExtra5 + " error code = " + stringExtra6 + " error desc = " + stringExtra7 + str);
                    if (TextUtils.equals(XmlyAuthErrorNoConstants.n, stringExtra5)) {
                        this.j.a(this.i);
                    } else {
                        this.i.a((XmlyException) new XmlyAuthException(stringExtra5, stringExtra6, stringExtra7));
                    }
                } else {
                    Logger.a(c, "Login failed: " + stringExtra4);
                    this.i.a((XmlyException) new XmlyAuthException(stringExtra4, (String) null, (String) null));
                }
            } else {
                Logger.a(c, "Login canceled by user.");
                this.i.a();
            }
        }
    }

    private boolean a(String str, boolean z) {
        Intent intent = new Intent();
        intent.setClassName(d, e);
        intent.putExtra(XmlyConstants.RequestParams.b, true);
        intent.putExtra(XmlyConstants.RequestParams.c, z);
        if (!TextUtils.isEmpty(str)) {
            this.h.b(str);
        } else {
            this.h.b("token");
        }
        this.h.a("2");
        intent.putExtras(this.h.i());
        if (e.a((Context) this.g, intent)) {
            if (e.b((Context) this.g, d) >= 118) {
                try {
                    this.g.startActivityForResult(intent, 4096);
                    return true;
                } catch (ActivityNotFoundException unused) {
                    return false;
                }
            }
        }
        return false;
    }

    private boolean a() {
        return com.ximalaya.ting.android.opensdk.auth.utils.a.b(this.g, d);
    }
}
