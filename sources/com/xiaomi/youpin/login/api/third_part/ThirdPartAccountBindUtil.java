package com.xiaomi.youpin.login.api.third_part;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Pair;
import android.webkit.WebView;
import com.facebook.CallbackManager;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.SecureRequest;
import com.xiaomi.accountsdk.request.SimpleRequest;
import com.xiaomi.accountsdk.utils.EasyMap;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.DefaultRefreshServiceTokenCallback;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.callback.BasePassportCallback;
import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.other.common.AsyncTaskUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ThirdPartAccountBindUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23490a = "http://api.account.xiaomi.com/pass/v2/safe/user";
    private static final String b = "http://api.account.xiaomi.com/pass/v2/safe/user/coreInfo";
    private static final String c = "http://api.account.xiaomi.com/pass/v2/safe/user/accessToken/full/delete";
    private static final int d = 12;
    private static final int e = 23;
    private static final String f = "FB";
    private static final String g = "WEIXIN";

    public static void a(LoginMiAccount loginMiAccount, BasePassportCallback<Boolean> basePassportCallback) {
        a(12, loginMiAccount, basePassportCallback);
    }

    public static void b(LoginMiAccount loginMiAccount, BasePassportCallback<Boolean> basePassportCallback) {
        a(23, loginMiAccount, basePassportCallback);
    }

    private static void a(final int i, LoginMiAccount loginMiAccount, final BasePassportCallback<Boolean> basePassportCallback) {
        new DefaultRefreshServiceTokenCallback<Boolean>(loginMiAccount, basePassportCallback) {
            @SuppressLint({"StaticFieldLeak"})
            public void a(LoginMiAccount loginMiAccount, MiServiceTokenInfo miServiceTokenInfo, boolean z) {
                final AnonymousClass1 r5 = new AsyncCallback<SimpleRequest.MapContent, ExceptionError>() {
                    public void a(SimpleRequest.MapContent mapContent) {
                        if (mapContent != null) {
                            String str = "";
                            try {
                                Integer num = (Integer) mapContent.getFromBody("code");
                                if (num != null) {
                                    if (num.intValue() == 0) {
                                        HashMap hashMap = (HashMap) mapContent.getFromBody("data");
                                        if (hashMap != null) {
                                            ArrayList arrayList = (ArrayList) hashMap.get("userAddresses");
                                            if (arrayList != null) {
                                                boolean z = false;
                                                Iterator it = arrayList.iterator();
                                                while (true) {
                                                    if (it.hasNext()) {
                                                        if (((Integer) ((HashMap) it.next()).get("addressType")).intValue() == i) {
                                                            z = true;
                                                            break;
                                                        }
                                                    } else {
                                                        break;
                                                    }
                                                }
                                                if (basePassportCallback != null) {
                                                    basePassportCallback.a(Boolean.valueOf(z));
                                                }
                                                if (basePassportCallback != null) {
                                                    basePassportCallback.a(LoginErrorCode.b, str);
                                                    return;
                                                }
                                                return;
                                            } else if (basePassportCallback != null) {
                                                basePassportCallback.a(-1, "userAddresses is null");
                                                return;
                                            } else {
                                                return;
                                            }
                                        } else if (basePassportCallback != null) {
                                            basePassportCallback.a(-1, "data is null");
                                            return;
                                        } else {
                                            return;
                                        }
                                    }
                                }
                                if (basePassportCallback != null) {
                                    BasePassportCallback basePassportCallback = basePassportCallback;
                                    basePassportCallback.a(-1, "code error " + num);
                                }
                            } catch (Exception e) {
                                str = e.getMessage();
                            }
                        } else if (basePassportCallback != null) {
                            basePassportCallback.a(-1, "mapContent is null");
                        }
                    }

                    public void a(ExceptionError exceptionError) {
                        String str;
                        if (basePassportCallback != null) {
                            BasePassportCallback basePassportCallback = basePassportCallback;
                            if ((exceptionError.b() + "" + exceptionError.f23518a) == null) {
                                str = "";
                            } else {
                                str = exceptionError.f23518a.getMessage();
                            }
                            basePassportCallback.a(-1, str);
                        }
                    }
                };
                final LoginMiAccount loginMiAccount2 = loginMiAccount;
                final MiServiceTokenInfo miServiceTokenInfo2 = miServiceTokenInfo;
                final boolean z2 = z;
                AsyncTaskUtils.a(new AsyncTask<Object, Object, Pair<SimpleRequest.MapContent, Exception>>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public Pair<SimpleRequest.MapContent, Exception> doInBackground(Object... objArr) {
                        SimpleRequest.MapContent mapContent;
                        Exception exc = null;
                        try {
                            mapContent = SecureRequest.getAsMap(ThirdPartAccountBindUtil.b, new EasyMap().easyPut("userId", loginMiAccount2.a()).easyPut("flag", "2"), new EasyMap().easyPut("cUserId", miServiceTokenInfo2.b).easyPut("serviceToken", miServiceTokenInfo2.c), true, miServiceTokenInfo2.d);
                        } catch (Exception e2) {
                            exc = e2;
                            mapContent = null;
                        }
                        return new Pair<>(mapContent, exc);
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(Pair<SimpleRequest.MapContent, Exception> pair) {
                        SimpleRequest.MapContent mapContent = (SimpleRequest.MapContent) pair.first;
                        Exception exc = (Exception) pair.second;
                        if (exc == null) {
                            r5.b(mapContent);
                        } else if (!z2 || !(exc instanceof AuthenticationFailureException)) {
                            ExceptionError exceptionError = new ExceptionError(LoginErrorCode.b, "");
                            exceptionError.f23518a = exc;
                            r5.b(exceptionError);
                        } else {
                            AnonymousClass1.this.b();
                        }
                    }
                }, new Object[0]);
            }
        }.a();
    }

    public static void c(LoginMiAccount loginMiAccount, BasePassportCallback<Void> basePassportCallback) {
        a("FB", loginMiAccount, basePassportCallback);
    }

    public static void d(LoginMiAccount loginMiAccount, BasePassportCallback<Void> basePassportCallback) {
        a("WEIXIN", loginMiAccount, basePassportCallback);
    }

    private static void a(final String str, LoginMiAccount loginMiAccount, final BasePassportCallback<Void> basePassportCallback) {
        new DefaultRefreshServiceTokenCallback<Void>(loginMiAccount, basePassportCallback) {
            @SuppressLint({"StaticFieldLeak"})
            public void a(LoginMiAccount loginMiAccount, MiServiceTokenInfo miServiceTokenInfo, boolean z) {
                final AnonymousClass1 r5 = new AsyncCallback<SimpleRequest.MapContent, Error>() {
                    public void a(SimpleRequest.MapContent mapContent) {
                        boolean z = false;
                        if (mapContent != null) {
                            try {
                                Integer num = (Integer) mapContent.getFromBody("code");
                                if (num != null) {
                                    if (num.intValue() == 0) {
                                        z = true;
                                    }
                                }
                            } catch (Exception e) {
                                if (basePassportCallback != null) {
                                    basePassportCallback.a(LoginErrorCode.b, e.getMessage());
                                    return;
                                }
                                return;
                            }
                        }
                        if (basePassportCallback == null) {
                            return;
                        }
                        if (z) {
                            basePassportCallback.a(null);
                        } else {
                            basePassportCallback.a(-999, "解绑失败");
                        }
                    }

                    public void a(Error error) {
                        if (basePassportCallback != null) {
                            basePassportCallback.a(error.a(), error.b());
                        }
                    }
                };
                final LoginMiAccount loginMiAccount2 = loginMiAccount;
                final MiServiceTokenInfo miServiceTokenInfo2 = miServiceTokenInfo;
                final boolean z2 = z;
                AsyncTaskUtils.a(new AsyncTask<Object, Object, Pair<SimpleRequest.MapContent, Exception>>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public Pair<SimpleRequest.MapContent, Exception> doInBackground(Object... objArr) {
                        SimpleRequest.MapContent mapContent;
                        Exception exc = null;
                        try {
                            mapContent = SecureRequest.postAsMap(ThirdPartAccountBindUtil.c, new EasyMap().easyPut("snsType", str).easyPut("userId", loginMiAccount2.a()), new EasyMap().easyPut("cUserId", miServiceTokenInfo2.b).easyPut("serviceToken", miServiceTokenInfo2.c), true, miServiceTokenInfo2.d);
                        } catch (Exception e2) {
                            exc = e2;
                            mapContent = null;
                        }
                        return new Pair<>(mapContent, exc);
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(Pair<SimpleRequest.MapContent, Exception> pair) {
                        SimpleRequest.MapContent mapContent = (SimpleRequest.MapContent) pair.first;
                        Exception exc = (Exception) pair.second;
                        if (exc == null) {
                            r5.b(mapContent);
                        } else if (!z2 || !(exc instanceof AuthenticationFailureException)) {
                            ExceptionError exceptionError = new ExceptionError(LoginErrorCode.b, "");
                            exceptionError.f23518a = exc;
                            r5.b(exceptionError);
                        } else {
                            AnonymousClass2.this.b();
                        }
                    }
                }, new Object[0]);
            }
        }.a();
    }

    public static void a(String str, String str2, boolean z, String str3, AsyncCallback<Void, Error> asyncCallback) {
        WeChatBindManager weChatBindManager = new WeChatBindManager();
        if (z) {
            weChatBindManager.a(str, str2, asyncCallback);
        } else {
            weChatBindManager.a(str, str2, str3, asyncCallback);
        }
    }

    public static void a(Activity activity, WebView webView, CallbackManager callbackManager, String str, String str2, boolean z, String str3, AsyncCallback<Void, Error> asyncCallback) {
        FbBindManager fbBindManager = new FbBindManager(activity, callbackManager);
        if (z) {
            fbBindManager.a(str, str2, asyncCallback);
        } else {
            fbBindManager.a(str, str2, str3, asyncCallback);
        }
    }
}
