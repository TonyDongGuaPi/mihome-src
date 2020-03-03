package com.xiaomi.smarthome.frame.login;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebView;
import com.facebook.CallbackManager;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.callback.BasePassportCallback;
import com.xiaomi.youpin.login.api.manager.LoginManager;
import com.xiaomi.youpin.login.api.third_part.ThirdPartAccountBindUtil;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil;

public class LoginApiNew {

    /* renamed from: a  reason: collision with root package name */
    private static LoginApiNew f16146a;
    private LoginManager b;

    public static LoginApiNew a() {
        if (f16146a == null) {
            f16146a = new LoginApiNew();
        }
        return f16146a;
    }

    public void a(String str, AsyncCallback<MiServiceTokenInfo, ExceptionError> asyncCallback) {
        if (!CoreApi.a().q()) {
            if (asyncCallback != null) {
                asyncCallback.b(new ExceptionError(ErrorCode.INVALID.getCode(), "not loggedin"));
            }
        } else if (CoreApi.a().v()) {
            MiLoginApi.a(str, CoreApi.a().a(str), true, asyncCallback);
        } else {
            MiLoginApi.a(str, CoreApi.a().s(), CoreApi.a().w(), true, asyncCallback);
        }
    }

    public void a(final com.xiaomi.smarthome.frame.AsyncCallback<Boolean, Error> asyncCallback) {
        ThirdPartAccountBindUtil.b(CoreApi.a().y(), new BasePassportCallback<Boolean>() {
            public void a(Boolean bool) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(bool);
                }
            }

            public void a(int i, String str) {
                if (asyncCallback != null) {
                    asyncCallback.sendFailureMessage(new Error(i, str));
                }
            }

            public void a(MiServiceTokenInfo miServiceTokenInfo) {
                LoginMiAccount y = CoreApi.a().y();
                if (y != null) {
                    y.a(miServiceTokenInfo);
                    CoreApi.a().a(y, (com.xiaomi.smarthome.frame.AsyncCallback<Void, Error>) new com.xiaomi.smarthome.frame.AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                        }

                        public void onFailure(Error error) {
                        }
                    });
                }
            }
        });
    }

    public void a(Context context, final com.xiaomi.smarthome.frame.AsyncCallback<Void, Error> asyncCallback) {
        String s = CoreApi.a().s();
        String u = CoreApi.a().u();
        String b2 = AccountManagerUtil.b(context);
        ThirdPartAccountBindUtil.a(s, u, (CoreApi.a().v() && !TextUtils.isEmpty(b2) && b2.equalsIgnoreCase(s)) || !CoreApi.a().v(), CoreApi.a().w(), new AsyncCallback<Void, com.xiaomi.youpin.login.entity.Error>() {
            public void a(Void voidR) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(voidR);
                }
            }

            public void a(com.xiaomi.youpin.login.entity.Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(error.a(), error.b()));
                }
            }
        });
    }

    public void b(final com.xiaomi.smarthome.frame.AsyncCallback<Void, Error> asyncCallback) {
        ThirdPartAccountBindUtil.d(CoreApi.a().y(), new BasePassportCallback<Void>() {
            public void a(Void voidR) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(voidR);
                }
            }

            public void a(int i, String str) {
                if (asyncCallback != null) {
                    asyncCallback.sendFailureMessage(new Error(i, str));
                }
            }

            public void a(MiServiceTokenInfo miServiceTokenInfo) {
                LoginMiAccount y = CoreApi.a().y();
                if (y != null) {
                    y.a(miServiceTokenInfo);
                    CoreApi.a().a(y, (com.xiaomi.smarthome.frame.AsyncCallback<Void, Error>) new com.xiaomi.smarthome.frame.AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                        }

                        public void onFailure(Error error) {
                        }
                    });
                }
            }
        });
    }

    public void c(final com.xiaomi.smarthome.frame.AsyncCallback<Boolean, Error> asyncCallback) {
        ThirdPartAccountBindUtil.a(CoreApi.a().y(), new BasePassportCallback<Boolean>() {
            public void a(Boolean bool) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(bool);
                }
            }

            public void a(int i, String str) {
                if (asyncCallback != null) {
                    asyncCallback.sendFailureMessage(new Error(i, str));
                }
            }

            public void a(MiServiceTokenInfo miServiceTokenInfo) {
                LoginMiAccount y = CoreApi.a().y();
                if (y != null) {
                    y.a(miServiceTokenInfo);
                    CoreApi.a().a(y, (com.xiaomi.smarthome.frame.AsyncCallback<Void, Error>) new com.xiaomi.smarthome.frame.AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                        }

                        public void onFailure(Error error) {
                        }
                    });
                }
            }
        });
    }

    public void a(Activity activity, WebView webView, CallbackManager callbackManager, final com.xiaomi.smarthome.frame.AsyncCallback<Void, Error> asyncCallback) {
        String s = CoreApi.a().s();
        String u = CoreApi.a().u();
        String b2 = AccountManagerUtil.b(activity);
        ThirdPartAccountBindUtil.a(activity, webView, callbackManager, s, u, (CoreApi.a().v() && !TextUtils.isEmpty(b2) && b2.equalsIgnoreCase(s)) || !CoreApi.a().v(), CoreApi.a().w(), new AsyncCallback<Void, com.xiaomi.youpin.login.entity.Error>() {
            public void a(Void voidR) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(voidR);
                }
            }

            public void a(com.xiaomi.youpin.login.entity.Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(error.a(), error.b()));
                }
            }
        });
    }

    public void d(final com.xiaomi.smarthome.frame.AsyncCallback<Void, Error> asyncCallback) {
        ThirdPartAccountBindUtil.c(CoreApi.a().y(), new BasePassportCallback<Void>() {
            public void a(Void voidR) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(voidR);
                }
            }

            public void a(int i, String str) {
                if (asyncCallback != null) {
                    asyncCallback.sendFailureMessage(new Error(i, str));
                }
            }

            public void a(MiServiceTokenInfo miServiceTokenInfo) {
                LoginMiAccount y = CoreApi.a().y();
                if (y != null) {
                    y.a(miServiceTokenInfo);
                    CoreApi.a().a(y, (com.xiaomi.smarthome.frame.AsyncCallback<Void, Error>) new com.xiaomi.smarthome.frame.AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                        }

                        public void onFailure(Error error) {
                        }
                    });
                }
            }
        });
    }
}
