package com.ximalaya.ting.android.opensdk.auth.component;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.ximalaya.ting.android.opensdk.auth.call.IXmlyAuthListener;
import com.ximalaya.ting.android.opensdk.auth.exception.XmlyAuthException;
import com.ximalaya.ting.android.opensdk.auth.exception.XmlyException;
import com.ximalaya.ting.android.opensdk.auth.model.XmlyAuth2AccessToken;
import com.ximalaya.ting.android.opensdk.auth.model.XmlyErrorInfo;
import com.ximalaya.ting.android.opensdk.auth.utils.AccessTokenKeeper;
import com.ximalaya.ting.android.opensdk.auth.utils.Logger;
import com.ximalaya.ting.android.opensdk.auth.utils.h;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public final class a extends f {
    /* access modifiers changed from: private */
    public Activity b;
    private c c;
    /* access modifiers changed from: private */
    public IXmlyAuthListener d;
    private boolean e = false;

    public a(Activity activity, c cVar) {
        this.b = activity;
        this.c = cVar;
        this.d = cVar.b();
    }

    public final void onPageStarted(WebView webView, final String str, Bitmap bitmap) {
        if (this.f1822a != null) {
            this.f1822a.onPageStartedCallBack(webView, str, bitmap);
        }
        if (!str.startsWith(this.c.a().b()) || this.e) {
            super.onPageStarted(webView, str, bitmap);
            return;
        }
        this.e = true;
        Bundle a2 = h.a(str);
        String string = a2.getString("error_no");
        String string2 = a2.getString("error_code");
        String string3 = a2.getString("error_desc");
        if (string == null && string2 == null) {
            Logger.a("ContentValues", "requestAccessTokenInfo, request: method = get, url = " + str);
            new OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS).followRedirects(false).build().newCall(new Request.Builder().url(str).build()).enqueue(new Callback() {
                public final void onFailure(Call call, IOException iOException) {
                    if (a.this.d != null) {
                        a.this.d.a((XmlyException) new XmlyAuthException(iOException.getMessage(), (String) null, (String) null));
                    }
                    iOException.printStackTrace();
                    Logger.a("ContentValues", "requestAccessTokenInfo, request failed, error message = " + iOException.getMessage());
                }

                public final void onResponse(Call call, Response response) throws IOException {
                    int code = response.code();
                    String string = response.body().string();
                    if (response.isSuccessful()) {
                        if (!TextUtils.isEmpty(string)) {
                            XmlyAuth2AccessToken a2 = XmlyAuth2AccessToken.a(string);
                            try {
                                int optInt = new JSONObject(string).optInt("expires_in", 0);
                                if (a.this.d != null) {
                                    if (a2 == null || !a2.a()) {
                                        a.this.d.a((XmlyException) new XmlyAuthException(String.valueOf(code), (String) null, (String) null));
                                        Logger.a("ContentValues", "Failed to receive access token by Web");
                                    } else {
                                        Logger.a("ContentValues", "Login Success! " + a2.toString());
                                        AccessTokenKeeper.b(a.this.b);
                                        AccessTokenKeeper.a(a.this.b, a2);
                                        a2.a((long) optInt);
                                        a.this.d.a(a2.b());
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Logger.a("ContentValues", "redirectRequestAccessTokenInfo, request success, status code = " + code + ", body = " + string + ", redirectUrl" + str);
                        }
                        a.this.d.a((XmlyException) new XmlyAuthException(String.valueOf(code), (String) null, (String) null));
                    } else if (!TextUtils.isEmpty(string)) {
                        XmlyErrorInfo d = XmlyErrorInfo.d(string);
                        if (d != null) {
                            a.this.d.a((XmlyException) new XmlyAuthException(d.a(), d.b(), d.c()));
                        } else {
                            a.this.d.a((XmlyException) new XmlyAuthException(String.valueOf(code), (String) null, (String) null));
                        }
                    } else {
                        a.this.d.a((XmlyException) new XmlyAuthException(String.valueOf(code), (String) null, (String) null));
                    }
                    Logger.a("ContentValues", "Failed to receive access token by Web");
                    Logger.a("ContentValues", "redirectRequestAccessTokenInfo, request success, status code = " + code + ", body = " + string + ", redirectUrl" + str);
                }
            });
        } else if (this.d != null) {
            this.d.a((XmlyException) new XmlyAuthException(string2, string, string3));
        }
        webView.stopLoading();
        XmlyBrowserComponent.closeBrowser(this.b, this.c.c());
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.f1822a != null) {
            this.f1822a.shouldOverrideUrlLoadingCallBack(webView, str);
        }
        if (!str.startsWith(XmlyBrowserComponent.BROWSER_CLOSE_SCHEME)) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        if (this.d != null) {
            this.d.a();
        }
        XmlyBrowserComponent.closeBrowser(this.b, this.c.c());
        return true;
    }

    public final void onPageFinished(WebView webView, String str) {
        if (this.f1822a != null) {
            this.f1822a.onPageFinishedCallBack(webView, str);
        }
        super.onPageFinished(webView, str);
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        if (this.f1822a != null) {
            this.f1822a.onReceivedErrorCallBack(webView, i, str, str2);
        }
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (this.f1822a != null) {
            this.f1822a.onReceivedSslErrorCallBack(webView, sslErrorHandler, sslError);
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    private void a(final String str) {
        Bundle a2 = h.a(str);
        String string = a2.getString("error_no");
        String string2 = a2.getString("error_code");
        String string3 = a2.getString("error_desc");
        if (string == null && string2 == null) {
            Logger.a("ContentValues", "requestAccessTokenInfo, request: method = get, url = " + str);
            new OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS).followRedirects(false).build().newCall(new Request.Builder().url(str).build()).enqueue(new Callback() {
                public final void onFailure(Call call, IOException iOException) {
                    if (a.this.d != null) {
                        a.this.d.a((XmlyException) new XmlyAuthException(iOException.getMessage(), (String) null, (String) null));
                    }
                    iOException.printStackTrace();
                    Logger.a("ContentValues", "requestAccessTokenInfo, request failed, error message = " + iOException.getMessage());
                }

                public final void onResponse(Call call, Response response) throws IOException {
                    int code = response.code();
                    String string = response.body().string();
                    if (response.isSuccessful()) {
                        if (!TextUtils.isEmpty(string)) {
                            XmlyAuth2AccessToken a2 = XmlyAuth2AccessToken.a(string);
                            try {
                                int optInt = new JSONObject(string).optInt("expires_in", 0);
                                if (a.this.d != null) {
                                    if (a2 == null || !a2.a()) {
                                        a.this.d.a((XmlyException) new XmlyAuthException(String.valueOf(code), (String) null, (String) null));
                                        Logger.a("ContentValues", "Failed to receive access token by Web");
                                    } else {
                                        Logger.a("ContentValues", "Login Success! " + a2.toString());
                                        AccessTokenKeeper.b(a.this.b);
                                        AccessTokenKeeper.a(a.this.b, a2);
                                        a2.a((long) optInt);
                                        a.this.d.a(a2.b());
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Logger.a("ContentValues", "redirectRequestAccessTokenInfo, request success, status code = " + code + ", body = " + string + ", redirectUrl" + str);
                        }
                        a.this.d.a((XmlyException) new XmlyAuthException(String.valueOf(code), (String) null, (String) null));
                    } else if (!TextUtils.isEmpty(string)) {
                        XmlyErrorInfo d = XmlyErrorInfo.d(string);
                        if (d != null) {
                            a.this.d.a((XmlyException) new XmlyAuthException(d.a(), d.b(), d.c()));
                        } else {
                            a.this.d.a((XmlyException) new XmlyAuthException(String.valueOf(code), (String) null, (String) null));
                        }
                    } else {
                        a.this.d.a((XmlyException) new XmlyAuthException(String.valueOf(code), (String) null, (String) null));
                    }
                    Logger.a("ContentValues", "Failed to receive access token by Web");
                    Logger.a("ContentValues", "redirectRequestAccessTokenInfo, request success, status code = " + code + ", body = " + string + ", redirectUrl" + str);
                }
            });
        } else if (this.d != null) {
            this.d.a((XmlyException) new XmlyAuthException(string2, string, string3));
        }
    }

    private void b(final String str) {
        Logger.a("ContentValues", "requestAccessTokenInfo, request: method = get, url = " + str);
        new OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS).followRedirects(false).build().newCall(new Request.Builder().url(str).build()).enqueue(new Callback() {
            public final void onFailure(Call call, IOException iOException) {
                if (a.this.d != null) {
                    a.this.d.a((XmlyException) new XmlyAuthException(iOException.getMessage(), (String) null, (String) null));
                }
                iOException.printStackTrace();
                Logger.a("ContentValues", "requestAccessTokenInfo, request failed, error message = " + iOException.getMessage());
            }

            public final void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                String string = response.body().string();
                if (response.isSuccessful()) {
                    if (!TextUtils.isEmpty(string)) {
                        XmlyAuth2AccessToken a2 = XmlyAuth2AccessToken.a(string);
                        try {
                            int optInt = new JSONObject(string).optInt("expires_in", 0);
                            if (a.this.d != null) {
                                if (a2 == null || !a2.a()) {
                                    a.this.d.a((XmlyException) new XmlyAuthException(String.valueOf(code), (String) null, (String) null));
                                    Logger.a("ContentValues", "Failed to receive access token by Web");
                                } else {
                                    Logger.a("ContentValues", "Login Success! " + a2.toString());
                                    AccessTokenKeeper.b(a.this.b);
                                    AccessTokenKeeper.a(a.this.b, a2);
                                    a2.a((long) optInt);
                                    a.this.d.a(a2.b());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Logger.a("ContentValues", "redirectRequestAccessTokenInfo, request success, status code = " + code + ", body = " + string + ", redirectUrl" + str);
                    }
                    a.this.d.a((XmlyException) new XmlyAuthException(String.valueOf(code), (String) null, (String) null));
                } else if (!TextUtils.isEmpty(string)) {
                    XmlyErrorInfo d = XmlyErrorInfo.d(string);
                    if (d != null) {
                        a.this.d.a((XmlyException) new XmlyAuthException(d.a(), d.b(), d.c()));
                    } else {
                        a.this.d.a((XmlyException) new XmlyAuthException(String.valueOf(code), (String) null, (String) null));
                    }
                } else {
                    a.this.d.a((XmlyException) new XmlyAuthException(String.valueOf(code), (String) null, (String) null));
                }
                Logger.a("ContentValues", "Failed to receive access token by Web");
                Logger.a("ContentValues", "redirectRequestAccessTokenInfo, request success, status code = " + code + ", body = " + string + ", redirectUrl" + str);
            }
        });
    }
}
