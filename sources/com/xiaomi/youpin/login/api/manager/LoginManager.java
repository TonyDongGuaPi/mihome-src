package com.xiaomi.youpin.login.api.manager;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.CallbackManager;
import com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.youpin.login.api.LoginConfig;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.manager.callback.BaseLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.DynamicTokenLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.FacebookLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.MiuiSystemLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.OAuthLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.PhoneLoginBaseCallback;
import com.xiaomi.youpin.login.api.manager.callback.PhonePwdLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.PwdLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.ReLoginAfterSetPwdCallback;
import com.xiaomi.youpin.login.api.manager.callback.RegisterCallback;
import com.xiaomi.youpin.login.api.manager.callback.WxLoginCallback;
import com.xiaomi.youpin.login.api.stat.LoginStatInterface;
import com.xiaomi.youpin.login.api.stat.LoginStatUtil;
import com.xiaomi.youpin.login.api.stat.LoginType;
import com.xiaomi.youpin.login.api.wechat.data.WxTouristLoginData;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.oauth.LoginMiByOAuthResult;
import java.util.Map;

public class LoginManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23423a = "LoginManager";
    protected Context b;
    private MiuiSystemLoginManager c;
    private PwdLoginManager d;
    private DynamicTokenLoginManager e;
    private WxLoginManager f;
    private PhoneRegisterManager g;
    private PhoneTicketLoginManager h;
    private PhonePwdLoginManager i;
    private SetPwdReLoginManager j;
    private FbLoginManager k;
    private OAuthLoginManager l;
    /* access modifiers changed from: private */
    public LoginStatInterface m;

    /* access modifiers changed from: protected */
    public void a() {
    }

    public LoginManager(Context context) {
        this.b = context;
        LoginConfig a2 = MiLoginApi.a();
        if (a2 != null) {
            this.m = a2.m();
        }
    }

    public void a(@Nullable Activity activity, final MiuiSystemLoginCallback miuiSystemLoginCallback) {
        a();
        MiAccountManager.get(this.b).setUseSystem();
        AnonymousClass1 r0 = new MiuiSystemLoginCallback() {
            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                LoginManager.this.a("MIUI", loginMiAccount, (Map<String, String>) null, miuiSystemLoginCallback);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                if (miuiSystemLoginCallback != null) {
                    miuiSystemLoginCallback.onLoginFail(i, str, map);
                }
                Map<String, String> a2 = LoginStatUtil.a(i, str);
                if (map != null) {
                    a2.putAll(map);
                }
                LoginManager.this.a("MIUI", i, str, map, (BaseLoginCallback) miuiSystemLoginCallback);
            }
        };
        if (this.c == null) {
            this.c = new MiuiSystemLoginManager(this.b);
        }
        this.c.a(activity, r0);
    }

    public void a(String str, String str2, String str3, String str4, final PwdLoginCallback pwdLoginCallback) {
        a();
        MiAccountManager.get(this.b).setUseLocal();
        AnonymousClass2 r6 = new PwdLoginCallback() {
            public void a(String str, boolean z) {
                if (pwdLoginCallback != null) {
                    pwdLoginCallback.a(str, z);
                }
            }

            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                LoginManager.this.a(LoginType.b, loginMiAccount, (Map<String, String>) null, pwdLoginCallback);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                if (pwdLoginCallback != null) {
                    pwdLoginCallback.onLoginFail(i, str, map);
                }
                Map<String, String> a2 = LoginStatUtil.a(i, str);
                if (map != null) {
                    a2.putAll(map);
                }
                LoginManager.this.a(LoginType.b, i, str, a2, (BaseLoginCallback) pwdLoginCallback);
            }
        };
        if (this.d == null) {
            this.d = new PwdLoginManager(this.b);
        }
        this.d.a(str, str2, str3, str4, (PwdLoginCallback) r6);
    }

    public void a(String str, String str2, String str3, MetaLoginData metaLoginData, boolean z, final DynamicTokenLoginCallback dynamicTokenLoginCallback) {
        a();
        MiAccountManager.get(this.b).setUseLocal();
        AnonymousClass3 r7 = new DynamicTokenLoginCallback() {
            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                LoginManager.this.a(LoginType.c, loginMiAccount, (Map<String, String>) null, dynamicTokenLoginCallback);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                if (dynamicTokenLoginCallback != null) {
                    dynamicTokenLoginCallback.onLoginFail(i, str, map);
                }
                Map<String, String> a2 = LoginStatUtil.a(i, str);
                if (map != null) {
                    a2.putAll(map);
                }
                LoginManager.this.a(LoginType.c, i, str, a2, (BaseLoginCallback) dynamicTokenLoginCallback);
            }
        };
        if (this.e == null) {
            this.e = new DynamicTokenLoginManager(this.b);
        }
        this.e.a(str, str2, str3, metaLoginData, z, r7);
    }

    public void a(Activity activity, final WxLoginCallback wxLoginCallback) {
        a();
        MiAccountManager.get(this.b).setUseLocal();
        AnonymousClass4 r0 = new WxLoginCallback() {
            public void a(int i) {
                if (wxLoginCallback != null) {
                    wxLoginCallback.a(i);
                }
                LoginManager.this.a(LoginType.d, i, "微信授权失败", LoginStatUtil.a(i, "微信授权失败"), (BaseLoginCallback) wxLoginCallback);
            }

            public void a() {
                LoginManager.this.a();
                if (wxLoginCallback != null) {
                    wxLoginCallback.a();
                }
            }

            public void b() {
                if (wxLoginCallback != null) {
                    wxLoginCallback.b();
                }
                LoginManager.this.a(LoginType.d, (int) LoginErrorCode.ac, "取消微信登录", LoginStatUtil.a(LoginErrorCode.ac, "取消微信登录"), (BaseLoginCallback) wxLoginCallback);
            }

            public void a(WxTouristLoginData wxTouristLoginData) {
                LoginManager.this.a(wxTouristLoginData, wxLoginCallback);
            }

            public void a(boolean z, boolean z2) {
                if (wxLoginCallback != null) {
                    wxLoginCallback.a(z, z2);
                }
            }

            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                LoginManager.this.a(LoginType.d, loginMiAccount, (Map<String, String>) null, wxLoginCallback);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                if (wxLoginCallback != null) {
                    wxLoginCallback.onLoginFail(i, str, map);
                }
                Map<String, String> a2 = LoginStatUtil.a(i, str);
                if (map != null) {
                    a2.putAll(map);
                }
                LoginManager.this.a(LoginType.d, i, str, a2, (BaseLoginCallback) wxLoginCallback);
            }
        };
        if (this.f == null) {
            this.f = new WxLoginManager(this.b);
        }
        this.f.a(activity, (WxLoginCallback) r0);
    }

    public boolean b() {
        return this.f != null && this.f.a();
    }

    public void a(final String str, String str2, final RegisterCallback registerCallback) {
        a();
        MiAccountManager.get(this.b).setUseLocal();
        AnonymousClass5 r0 = new RegisterCallback() {
            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                LoginManager.this.a(LoginType.e, loginMiAccount, LoginStatUtil.a(false), registerCallback);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                if (registerCallback != null) {
                    registerCallback.onLoginFail(i, str, map);
                }
                Map<String, String> a2 = LoginStatUtil.a(i, str, str);
                if (map != null) {
                    a2.putAll(map);
                }
                LoginManager.this.a(LoginType.e, i, str, a2, (BaseLoginCallback) registerCallback);
            }
        };
        if (this.g == null) {
            this.g = new PhoneRegisterManager(this.b);
        }
        this.g.a(str, str2, (RegisterCallback) r0);
    }

    public void a(ActivatorPhoneInfo activatorPhoneInfo, final RegisterCallback registerCallback) {
        a();
        MiAccountManager.get(this.b).setUseLocal();
        AnonymousClass6 r0 = new RegisterCallback() {
            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                LoginManager.this.a(LoginType.e, loginMiAccount, LoginStatUtil.a(true), registerCallback);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                if (registerCallback != null) {
                    registerCallback.onLoginFail(i, str, map);
                }
                Map<String, String> b2 = LoginStatUtil.b(i, str);
                if (map != null) {
                    b2.putAll(map);
                }
                LoginManager.this.a(LoginType.e, i, str, b2, (BaseLoginCallback) registerCallback);
            }
        };
        if (this.g == null) {
            this.g = new PhoneRegisterManager(this.b);
        }
        this.g.a(activatorPhoneInfo, (RegisterCallback) r0);
    }

    public void a(final String str, String str2, final PhoneLoginBaseCallback phoneLoginBaseCallback) {
        a();
        MiAccountManager.get(this.b).setUseLocal();
        AnonymousClass7 r0 = new PhoneLoginBaseCallback() {
            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                LoginManager.this.a(LoginType.f, loginMiAccount, LoginStatUtil.a(false), phoneLoginBaseCallback);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                if (phoneLoginBaseCallback != null) {
                    phoneLoginBaseCallback.onLoginFail(i, str, map);
                }
                Map<String, String> a2 = LoginStatUtil.a(i, str, str);
                if (map != null) {
                    a2.putAll(map);
                }
                LoginManager.this.a(LoginType.f, i, str, a2, (BaseLoginCallback) phoneLoginBaseCallback);
            }
        };
        if (this.h == null) {
            this.h = new PhoneTicketLoginManager(this.b);
        }
        this.h.a(str, str2, (PhoneLoginBaseCallback) r0);
    }

    public void a(ActivatorPhoneInfo activatorPhoneInfo, String str, final PhoneLoginBaseCallback phoneLoginBaseCallback) {
        a();
        MiAccountManager.get(this.b).setUseLocal();
        AnonymousClass8 r0 = new PhoneLoginBaseCallback() {
            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                LoginManager.this.a(LoginType.f, loginMiAccount, LoginStatUtil.a(true), phoneLoginBaseCallback);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                if (phoneLoginBaseCallback != null) {
                    phoneLoginBaseCallback.onLoginFail(i, str, map);
                }
                Map<String, String> b2 = LoginStatUtil.b(i, str);
                if (map != null) {
                    b2.putAll(map);
                }
                LoginManager.this.a(LoginType.f, i, str, b2, (BaseLoginCallback) phoneLoginBaseCallback);
            }
        };
        if (this.h == null) {
            this.h = new PhoneTicketLoginManager(this.b);
        }
        this.h.a(activatorPhoneInfo, str, (PhoneLoginBaseCallback) r0);
    }

    public void a(ActivatorPhoneInfo activatorPhoneInfo, String str, String str2, String str3, final PhonePwdLoginCallback phonePwdLoginCallback) {
        a();
        MiAccountManager.get(this.b).setUseLocal();
        AnonymousClass9 r6 = new PhonePwdLoginCallback() {
            public void a(String str, boolean z) {
                if (phonePwdLoginCallback != null) {
                    phonePwdLoginCallback.a(str, z);
                }
            }

            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                LoginManager.this.a(LoginType.i, loginMiAccount, LoginStatUtil.a(true), phonePwdLoginCallback);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                if (phonePwdLoginCallback != null) {
                    phonePwdLoginCallback.onLoginFail(i, str, map);
                }
                Map<String, String> a2 = LoginStatUtil.a(i, str);
                if (map != null) {
                    a2.putAll(map);
                }
                LoginManager.this.a(LoginType.i, i, str, a2, (BaseLoginCallback) phonePwdLoginCallback);
            }
        };
        if (this.i == null) {
            this.i = new PhonePwdLoginManager(this.b);
        }
        this.i.a(activatorPhoneInfo, str, str2, str3, (PhonePwdLoginCallback) r6);
    }

    public void a(String str, String str2, final ReLoginAfterSetPwdCallback reLoginAfterSetPwdCallback) {
        a();
        MiAccountManager.get(this.b).setUseLocal();
        AnonymousClass10 r0 = new ReLoginAfterSetPwdCallback() {
            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                LoginManager.this.a(LoginType.g, loginMiAccount, (Map<String, String>) null, reLoginAfterSetPwdCallback);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                if (reLoginAfterSetPwdCallback != null) {
                    reLoginAfterSetPwdCallback.onLoginFail(i, str, map);
                }
                Map<String, String> a2 = LoginStatUtil.a(i, str);
                if (map != null) {
                    a2.putAll(map);
                }
                LoginManager.this.a(LoginType.g, i, str, a2, (BaseLoginCallback) reLoginAfterSetPwdCallback);
            }
        };
        if (this.j == null) {
            this.j = new SetPwdReLoginManager(this.b);
        }
        this.j.a(str, str2, r0);
    }

    public void a(Activity activity, CallbackManager callbackManager, final FacebookLoginCallback facebookLoginCallback) {
        a();
        MiAccountManager.get(this.b).setUseLocal();
        AnonymousClass11 r0 = new FacebookLoginCallback() {
            public void a() {
                if (facebookLoginCallback != null) {
                    facebookLoginCallback.a();
                }
                LoginManager.this.a(LoginType.j, (int) LoginErrorCode.aB, "facebook授权取消", LoginStatUtil.a(LoginErrorCode.aB, "facebook授权取消"), (BaseLoginCallback) facebookLoginCallback);
            }

            public void a(int i, String str) {
                if (facebookLoginCallback != null) {
                    facebookLoginCallback.a(i, str);
                }
                LoginManager.this.a(LoginType.j, i, str, LoginStatUtil.a(i, str), (BaseLoginCallback) facebookLoginCallback);
            }

            public void b() {
                LoginManager.this.a();
                if (facebookLoginCallback != null) {
                    facebookLoginCallback.b();
                }
            }

            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                LoginManager.this.a(LoginType.j, loginMiAccount, (Map<String, String>) null, facebookLoginCallback);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                if (facebookLoginCallback != null) {
                    facebookLoginCallback.onLoginFail(i, str, map);
                }
                Map<String, String> a2 = LoginStatUtil.a(i, str);
                if (map != null) {
                    a2.putAll(map);
                }
                LoginManager.this.a(LoginType.j, i, str, a2, (BaseLoginCallback) facebookLoginCallback);
            }
        };
        if (this.k == null) {
            this.k = new FbLoginManager(this.b);
        }
        this.k.a(activity, callbackManager, (FacebookLoginCallback) r0);
    }

    public void a(Activity activity, int[] iArr, String str, String str2, final OAuthLoginCallback oAuthLoginCallback) {
        a();
        MiAccountManager.get(this.b).setUseLocal();
        AnonymousClass12 r6 = new OAuthLoginCallback() {
            public void a(LoginMiByOAuthResult loginMiByOAuthResult) {
                LoginManager.this.a(loginMiByOAuthResult, oAuthLoginCallback);
            }

            public void a(int i, String str) {
                if (LoginManager.this.m != null) {
                    LoginManager.this.m.b(LoginType.k, LoginStatUtil.a(i, str));
                }
                if (oAuthLoginCallback != null) {
                    oAuthLoginCallback.a(i, str);
                }
            }
        };
        if (this.l == null) {
            this.l = new OAuthLoginManager();
        }
        this.l.a(activity, iArr, str, str2, r6);
    }

    /* access modifiers changed from: protected */
    public void a(String str, LoginMiAccount loginMiAccount, @Nullable Map<String, String> map, BaseLoginCallback baseLoginCallback) {
        if (baseLoginCallback != null) {
            baseLoginCallback.onLoginSuccess(loginMiAccount);
        }
        if (this.m != null) {
            this.m.a(str, map);
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, int i2, String str2, @Nullable Map<String, String> map, BaseLoginCallback baseLoginCallback) {
        if (this.m != null) {
            this.m.b(str, map);
        }
    }

    /* access modifiers changed from: protected */
    public void a(WxTouristLoginData wxTouristLoginData, WxLoginCallback wxLoginCallback) {
        if (wxLoginCallback != null) {
            wxLoginCallback.a(wxTouristLoginData);
        }
        if (this.m != null) {
            this.m.a(LoginType.h, (Map<String, String>) null);
        }
    }

    /* access modifiers changed from: protected */
    public void a(LoginMiByOAuthResult loginMiByOAuthResult, OAuthLoginCallback oAuthLoginCallback) {
        if (this.m != null) {
            this.m.a(LoginType.k, (Map<String, String>) null);
        }
        if (oAuthLoginCallback != null) {
            oAuthLoginCallback.a(loginMiByOAuthResult);
        }
    }
}
