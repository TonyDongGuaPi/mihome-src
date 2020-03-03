package com.xiaomi.youpin.login.api.phone;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import com.xiaomi.accountsdk.account.XMPassportSettings;
import com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo;
import com.xiaomi.accountsdk.account.data.PassportInfo;
import com.xiaomi.accountsdk.account.data.PasswordLoginParams;
import com.xiaomi.accountsdk.account.data.PhoneTicketLoginParams;
import com.xiaomi.accountsdk.account.data.PhoneTokenRegisterParams;
import com.xiaomi.accountsdk.account.data.QueryPhoneInfoParams;
import com.xiaomi.accountsdk.account.data.RegisterUserInfo;
import com.xiaomi.accountsdk.account.data.SendPhoneTicketParams;
import com.xiaomi.accountsdk.account.data.SetPasswordParams;
import com.xiaomi.accountsdk.hasheddeviceidlib.HashedDeviceIdUtil;
import com.xiaomi.passport.PassportUserEnvironment;
import com.xiaomi.passport.uicontroller.PhoneLoginController;
import com.xiaomi.passport.v2.utils.ActivatorPhoneController;
import com.xiaomi.youpin.login.api.manager.LocalPhoneDataCache;
import com.xiaomi.youpin.login.entity.account.AccountParam;
import java.util.ArrayList;
import java.util.List;

public class PhoneQueryManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23478a = "PhoneQueryManager";
    private Context b;
    private String c;
    private PhoneLoginController d;
    /* access modifiers changed from: private */
    public boolean e = false;
    /* access modifiers changed from: private */
    public boolean f = false;

    private interface QueryLocalPhoneUserInfoCallback {
        void a(LocalPhoneDetailInfo localPhoneDetailInfo);
    }

    public interface QueryLocalPhoneUserInfosCallback {
        void a();

        void a(List<LocalPhoneDetailInfo> list);
    }

    public PhoneQueryManager(Context context) {
        this.b = context;
        this.c = new HashedDeviceIdUtil(context).getHashedDeviceIdNoThrow();
        this.d = new PhoneLoginController();
    }

    public void a(Activity activity, final QueryLocalPhoneUserInfosCallback queryLocalPhoneUserInfosCallback, boolean z, long j) {
        List<LocalPhoneDetailInfo> b2;
        this.e = false;
        this.f = false;
        if (j > 0) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (!PhoneQueryManager.this.f) {
                        queryLocalPhoneUserInfosCallback.a();
                        boolean unused = PhoneQueryManager.this.e = true;
                    }
                }
            }, j);
        }
        if (!z || (b2 = LocalPhoneDataCache.a().b()) == null || b2.isEmpty()) {
            final ArrayList arrayList = new ArrayList();
            new ActivatorPhoneController(activity).getLocalActivatorPhone(new ActivatorPhoneController.PhoneNumCallback() {
                public void onDualSIM(ActivatorPhoneInfo activatorPhoneInfo, final ActivatorPhoneInfo activatorPhoneInfo2) {
                    PhoneQueryManager.this.a(activatorPhoneInfo, (QueryLocalPhoneUserInfoCallback) new QueryLocalPhoneUserInfoCallback() {
                        public void a(LocalPhoneDetailInfo localPhoneDetailInfo) {
                            if (localPhoneDetailInfo != null) {
                                arrayList.add(localPhoneDetailInfo);
                            }
                            PhoneQueryManager.this.a(activatorPhoneInfo2, (QueryLocalPhoneUserInfoCallback) new QueryLocalPhoneUserInfoCallback() {
                                public void a(LocalPhoneDetailInfo localPhoneDetailInfo) {
                                    boolean unused = PhoneQueryManager.this.f = true;
                                    if (localPhoneDetailInfo != null) {
                                        arrayList.add(localPhoneDetailInfo);
                                    }
                                    LocalPhoneDataCache.a().a(arrayList);
                                    if (queryLocalPhoneUserInfosCallback != null) {
                                        queryLocalPhoneUserInfosCallback.a(arrayList);
                                    }
                                }
                            });
                        }
                    });
                }

                public void onSingleSIM(ActivatorPhoneInfo activatorPhoneInfo) {
                    PhoneQueryManager.this.a(activatorPhoneInfo, (QueryLocalPhoneUserInfoCallback) new QueryLocalPhoneUserInfoCallback() {
                        public void a(LocalPhoneDetailInfo localPhoneDetailInfo) {
                            boolean unused = PhoneQueryManager.this.f = true;
                            if (localPhoneDetailInfo != null) {
                                arrayList.add(localPhoneDetailInfo);
                            }
                            LocalPhoneDataCache.a().a(arrayList);
                            if (queryLocalPhoneUserInfosCallback != null) {
                                queryLocalPhoneUserInfosCallback.a(arrayList);
                            }
                        }
                    });
                }

                public void onNone() {
                    boolean unused = PhoneQueryManager.this.f = true;
                    LocalPhoneDataCache.a().a(arrayList);
                    if (queryLocalPhoneUserInfosCallback != null) {
                        queryLocalPhoneUserInfosCallback.a(arrayList);
                    }
                }
            }, false);
            return;
        }
        this.f = true;
        queryLocalPhoneUserInfosCallback.a(b2);
    }

    /* access modifiers changed from: private */
    public void a(final ActivatorPhoneInfo activatorPhoneInfo, final QueryLocalPhoneUserInfoCallback queryLocalPhoneUserInfoCallback) {
        if (activatorPhoneInfo != null) {
            this.d.queryPhoneUserInfo(new QueryPhoneInfoParams.Builder().phoneHashActivatorToken(activatorPhoneInfo).deviceId(this.c).build(), new PhoneLoginController.PhoneUserInfoCallback() {
                public void onRecycledOrNotRegisteredPhone(RegisterUserInfo registerUserInfo) {
                    if (queryLocalPhoneUserInfoCallback != null) {
                        queryLocalPhoneUserInfoCallback.a(new LocalPhoneDetailInfo(activatorPhoneInfo, registerUserInfo, 1));
                    }
                }

                public void onNotRecycledRegisteredPhone(RegisterUserInfo registerUserInfo) {
                    if (queryLocalPhoneUserInfoCallback != null) {
                        queryLocalPhoneUserInfoCallback.a(new LocalPhoneDetailInfo(activatorPhoneInfo, registerUserInfo, 2));
                    }
                }

                public void onProbablyRecycleRegisteredPhone(RegisterUserInfo registerUserInfo) {
                    if (queryLocalPhoneUserInfoCallback != null) {
                        queryLocalPhoneUserInfoCallback.a(new LocalPhoneDetailInfo(activatorPhoneInfo, registerUserInfo, 3));
                    }
                }

                public void onPhoneNumInvalid() {
                    if (queryLocalPhoneUserInfoCallback != null) {
                        queryLocalPhoneUserInfoCallback.a((LocalPhoneDetailInfo) null);
                    }
                }

                public void onTicketOrTokenInvalid() {
                    if (queryLocalPhoneUserInfoCallback != null) {
                        queryLocalPhoneUserInfoCallback.a((LocalPhoneDetailInfo) null);
                    }
                }

                public void onQueryFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                    if (queryLocalPhoneUserInfoCallback != null) {
                        queryLocalPhoneUserInfoCallback.a((LocalPhoneDetailInfo) null);
                    }
                }
            });
        } else if (queryLocalPhoneUserInfoCallback != null) {
            queryLocalPhoneUserInfoCallback.a((LocalPhoneDetailInfo) null);
        }
    }

    public void a(String str, String str2, String str3, PhoneLoginController.SendPhoneTicketCallback sendPhoneTicketCallback) {
        this.d.sendPhoneTicket(new SendPhoneTicketParams.Builder().phone(str).serviceId("passportapi").captchaCode(str2, str3).build(), sendPhoneTicketCallback);
    }

    public void a(ActivatorPhoneInfo activatorPhoneInfo, PhoneLoginController.SendPhoneTicketCallback sendPhoneTicketCallback) {
        this.d.sendPhoneTicket(new SendPhoneTicketParams.Builder().phoneHashActivatorToken(activatorPhoneInfo).serviceId("passportapi").build(), sendPhoneTicketCallback);
    }

    public void a(String str, String str2, PhoneLoginController.PhoneUserInfoCallback phoneUserInfoCallback) {
        this.d.queryPhoneUserInfo(new QueryPhoneInfoParams.Builder().phoneTicket(str, str2).deviceId(this.c).build(), phoneUserInfoCallback);
    }

    public void a(String str, String str2, String str3, PhoneLoginController.TicketLoginCallback ticketLoginCallback) {
        this.d.ticketLogin(new PhoneTicketLoginParams.Builder().phoneTicketToken(str2, str3).serviceId(str).deviceId(this.c).build(), ticketLoginCallback);
    }

    public void a(String str, ActivatorPhoneInfo activatorPhoneInfo, String str2, PhoneLoginController.TicketLoginCallback ticketLoginCallback) {
        this.d.ticketLogin(new PhoneTicketLoginParams.Builder().activatorPhoneTicket(activatorPhoneInfo, str2).serviceId(str).deviceId(this.c).build(), ticketLoginCallback);
    }

    public void a(String str, ActivatorPhoneInfo activatorPhoneInfo, String str2, String str3, String str4, PhoneLoginController.PasswordLoginCallback passwordLoginCallback) {
        this.d.passwordLogin(new PasswordLoginParams.Builder().setActivatorPhone(activatorPhoneInfo).setPassword(str2).setDeviceId(this.c).setServiceId(str).setHashedEnvFactors(PassportUserEnvironment.Holder.getInstance().getEnvInfoArray(XMPassportSettings.getApplicationContext())).setCaptCode(str3).setCaptIck(str4).build(), passwordLoginCallback);
    }

    public void a(String str, String str2, String str3, String str4, String str5, PhoneLoginController.PasswordLoginCallback passwordLoginCallback) {
        this.d.passwordLogin(new PasswordLoginParams.Builder().setUserId(str2).setPassword(str3).setDeviceId(this.c).setServiceId(str).setHashedEnvFactors(PassportUserEnvironment.Holder.getInstance().getEnvInfoArray(XMPassportSettings.getApplicationContext())).setCaptCode(str4).setCaptIck(str5).build(), passwordLoginCallback);
    }

    public void a(String str, String str2, PhoneLoginController.PhoneRegisterCallback phoneRegisterCallback) {
        this.d.register(new PhoneTokenRegisterParams.Builder().phoneTicketToken(str, str2).build(), phoneRegisterCallback);
    }

    public void a(ActivatorPhoneInfo activatorPhoneInfo, PhoneLoginController.PhoneRegisterCallback phoneRegisterCallback) {
        this.d.register(new PhoneTokenRegisterParams.Builder().phoneHashActivatorToken(activatorPhoneInfo).build(), phoneRegisterCallback);
    }

    public void a(AccountParam accountParam, String str, String str2, String str3, PhoneLoginController.SetPasswordCallback setPasswordCallback) {
        this.d.setPassword(new SetPasswordParams.Builder(accountParam.f23511a).passportApiInfo(new PassportInfo(accountParam.f23511a, accountParam.b, "passportapi", accountParam.c, accountParam.d)).password(str2).passToken(str).ticket(str3).serviceId("passportapi").deviceId(this.c).build(), setPasswordCallback);
    }

    public void a(AccountParam accountParam, PhoneLoginController.SendSetPwdTicketCallback sendSetPwdTicketCallback) {
        this.d.sendSetPasswordTicket(new PassportInfo(accountParam.f23511a, accountParam.b, "passportapi", accountParam.c, accountParam.d), "passportapi", sendSetPwdTicketCallback);
    }
}
