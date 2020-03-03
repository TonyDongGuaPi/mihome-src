package com.ximalaya.ting.android.opensdk.auth.handler;

import android.content.Context;
import com.ximalaya.ting.android.opensdk.auth.call.IXmlyAuthListener;
import com.ximalaya.ting.android.opensdk.auth.constants.XmlyConstants;
import com.ximalaya.ting.android.opensdk.auth.exception.XmlyAuthException;
import com.ximalaya.ting.android.opensdk.auth.exception.XmlyException;
import com.ximalaya.ting.android.opensdk.auth.model.XmlyAuth2AccessToken;
import com.ximalaya.ting.android.opensdk.auth.utils.AccessTokenKeeper;
import com.ximalaya.ting.android.opensdk.auth.utils.Logger;
import com.ximalaya.ting.android.opensdk.auth.utils.b;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import com.ximalaya.ting.android.opensdk.httputil.XimalayaException;
import java.util.HashMap;
import org.json.JSONObject;

public final class a {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f1829a = b.class.getSimpleName();
    private static final String b = "http://api.ximalaya.com/oauth2/exchange_access_token";
    /* access modifiers changed from: private */
    public Context c;

    public a(Context context) {
        this.c = context;
    }

    private void a(final String str, String str2, final IXmlyAuthListener iXmlyAuthListener) {
        HashMap hashMap = new HashMap();
        hashMap.put(XmlyConstants.i, "token_exchange");
        long currentTimeMillis = System.currentTimeMillis();
        hashMap.put("nonce", b.a(String.valueOf(currentTimeMillis)));
        hashMap.put("timestamp", String.valueOf(currentTimeMillis));
        hashMap.put("third_uid", str);
        hashMap.put("third_token", str2);
        try {
            hashMap.put("client_id", CommonRequest.a().f());
        } catch (XimalayaException e) {
            e.printStackTrace();
        }
        CommonRequest.b("http://api.ximalaya.com/oauth2/exchange_access_token", hashMap, new IDataCallBack<C0033a>() {
            public final /* synthetic */ void a(Object obj) {
                C0033a aVar = (C0033a) obj;
                if (aVar != null) {
                    XmlyAuth2AccessToken xmlyAuth2AccessToken = new XmlyAuth2AccessToken(aVar.b(), aVar.a());
                    xmlyAuth2AccessToken.b(str);
                    if (iXmlyAuthListener == null) {
                        return;
                    }
                    if (xmlyAuth2AccessToken.a()) {
                        AccessTokenKeeper.b(a.this.c);
                        AccessTokenKeeper.a(a.this.c, xmlyAuth2AccessToken);
                        iXmlyAuthListener.a(xmlyAuth2AccessToken.b());
                        return;
                    }
                    iXmlyAuthListener.a((XmlyException) new XmlyAuthException("token 失效", (String) null, "token 失效"));
                    Logger.a(a.f1829a, "Failed to receive access token by Web");
                } else if (iXmlyAuthListener != null) {
                    iXmlyAuthListener.a((XmlyException) new XmlyAuthException("json 转换失败", "1007", "json 转换失败"));
                }
            }

            private void a(C0033a aVar) {
                if (aVar != null) {
                    XmlyAuth2AccessToken xmlyAuth2AccessToken = new XmlyAuth2AccessToken(aVar.b(), aVar.a());
                    xmlyAuth2AccessToken.b(str);
                    if (iXmlyAuthListener == null) {
                        return;
                    }
                    if (xmlyAuth2AccessToken.a()) {
                        AccessTokenKeeper.b(a.this.c);
                        AccessTokenKeeper.a(a.this.c, xmlyAuth2AccessToken);
                        iXmlyAuthListener.a(xmlyAuth2AccessToken.b());
                        return;
                    }
                    iXmlyAuthListener.a((XmlyException) new XmlyAuthException("token 失效", (String) null, "token 失效"));
                    Logger.a(a.f1829a, "Failed to receive access token by Web");
                } else if (iXmlyAuthListener != null) {
                    iXmlyAuthListener.a((XmlyException) new XmlyAuthException("json 转换失败", "1007", "json 转换失败"));
                }
            }

            public final void a(int i, String str) {
                IXmlyAuthListener iXmlyAuthListener = iXmlyAuthListener;
                iXmlyAuthListener.a((XmlyException) new XmlyAuthException(i + ":" + str, str, str));
            }
        }, new CommonRequest.IRequestCallBack<C0033a>() {
            private C0033a a(String str) throws Exception {
                JSONObject jSONObject = new JSONObject(str);
                return new C0033a(jSONObject.optString("access_token"), jSONObject.optString("expires_in"), str);
            }

            public final /* synthetic */ Object b(String str) throws Exception {
                JSONObject jSONObject = new JSONObject(str);
                return new C0033a(jSONObject.optString("access_token"), jSONObject.optString("expires_in"), str);
            }
        });
    }

    /* renamed from: com.ximalaya.ting.android.opensdk.auth.handler.a$a  reason: collision with other inner class name */
    private static class C0033a extends XimalayaResponse {

        /* renamed from: a  reason: collision with root package name */
        private String f1832a;
        private String b;
        private String c;

        public C0033a(String str, String str2, String str3) {
            this.f1832a = str;
            this.b = str2;
            this.c = str3;
        }

        public final String a() {
            return this.b;
        }

        private void a(String str) {
            this.b = str;
        }

        public final String b() {
            return this.f1832a;
        }

        private void b(String str) {
            this.f1832a = str;
        }

        private String c() {
            return this.c;
        }

        private void c(String str) {
            this.c = str;
        }
    }
}
