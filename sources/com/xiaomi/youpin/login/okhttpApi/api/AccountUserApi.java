package com.xiaomi.youpin.login.okhttpApi.api;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Pair;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.data.PassportInfo;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.youpin.login.api.DefaultRefreshServiceTokenCallback;
import com.xiaomi.youpin.login.api.callback.BasePassportCallback;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiAccountInfo;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.other.common.AsyncTaskUtils;
import com.xiaomi.youpin.login.other.log.LoginLogUtilsGrey;

public class AccountUserApi {
    @SuppressLint({"StaticFieldLeak"})
    public static void a(LoginMiAccount loginMiAccount, final String str, final BasePassportCallback<Void> basePassportCallback) {
        new DefaultRefreshServiceTokenCallback<Void>(loginMiAccount, basePassportCallback) {
            public void a(final LoginMiAccount loginMiAccount, final MiServiceTokenInfo miServiceTokenInfo, final boolean z) {
                AsyncTaskUtils.a(new AsyncTask<Void, Void, Exception>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public Exception doInBackground(Void... voidArr) {
                        try {
                            XMPassport.uploadXiaomiUserName(loginMiAccount.a(), miServiceTokenInfo.b, "passportapi", miServiceTokenInfo.c, miServiceTokenInfo.d, str);
                            return null;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return e;
                        }
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(Exception exc) {
                        if (exc == null) {
                            basePassportCallback.a(null);
                        } else if (!z || !(exc instanceof AuthenticationFailureException)) {
                            basePassportCallback.a(-1, "");
                        } else {
                            AnonymousClass1.this.b();
                        }
                    }
                }, new Void[0]);
            }
        }.a();
    }

    @SuppressLint({"StaticFieldLeak"})
    public static void b(LoginMiAccount loginMiAccount, final String str, final BasePassportCallback<Pair<Bitmap, String>> basePassportCallback) {
        new DefaultRefreshServiceTokenCallback<Pair<Bitmap, String>>(loginMiAccount, basePassportCallback) {
            public void a(final LoginMiAccount loginMiAccount, final MiServiceTokenInfo miServiceTokenInfo, final boolean z) {
                AsyncTaskUtils.a(new AsyncTask<Void, Void, Pair<Pair<Bitmap, String>, Exception>>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public Pair<Pair<Bitmap, String>, Exception> doInBackground(Void... voidArr) {
                        Bitmap decodeFile = BitmapFactory.decodeFile(str);
                        String str = "";
                        try {
                            e = null;
                            str = XMPassport.uploadXiaomiUserIcon(new PassportInfo(loginMiAccount.a(), miServiceTokenInfo.b, "passportapi", miServiceTokenInfo.c, miServiceTokenInfo.d), decodeFile);
                        } catch (Exception e) {
                            e = e;
                            e.printStackTrace();
                        }
                        return new Pair<>(new Pair(decodeFile, str), e);
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(Pair<Pair<Bitmap, String>, Exception> pair) {
                        Exception exc = (Exception) pair.second;
                        if (!TextUtils.isEmpty((String) ((Pair) pair.first).second)) {
                            basePassportCallback.a(pair.first);
                        } else if (!z || exc == null || !(exc instanceof AuthenticationFailureException)) {
                            basePassportCallback.a(-1, "");
                        } else {
                            AnonymousClass2.this.b();
                        }
                    }
                }, new Void[0]);
            }
        }.a();
    }

    @SuppressLint({"StaticFieldLeak"})
    public static void a(LoginMiAccount loginMiAccount, final BasePassportCallback<MiAccountInfo> basePassportCallback) {
        new DefaultRefreshServiceTokenCallback<MiAccountInfo>(loginMiAccount, basePassportCallback) {
            public void a(final LoginMiAccount loginMiAccount, final MiServiceTokenInfo miServiceTokenInfo, final boolean z) {
                AsyncTaskUtils.a(new AsyncTask<Void, Void, Pair<MiAccountInfo, ExceptionError>>() {
                    /* access modifiers changed from: protected */
                    /* JADX WARNING: Removed duplicated region for block: B:47:0x0089  */
                    /* JADX WARNING: Removed duplicated region for block: B:49:0x00b4  */
                    /* renamed from: a */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public android.util.Pair<com.xiaomi.youpin.login.entity.account.MiAccountInfo, com.xiaomi.youpin.login.entity.error.ExceptionError> doInBackground(java.lang.Void... r8) {
                        /*
                            r7 = this;
                            com.xiaomi.accountsdk.account.data.PassportInfo r8 = new com.xiaomi.accountsdk.account.data.PassportInfo
                            com.xiaomi.youpin.login.entity.account.LoginMiAccount r0 = r2
                            java.lang.String r1 = r0.a()
                            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r0 = r3
                            java.lang.String r2 = r0.b
                            java.lang.String r3 = "passportapi"
                            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r0 = r3
                            java.lang.String r4 = r0.c
                            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r0 = r3
                            java.lang.String r5 = r0.d
                            r0 = r8
                            r0.<init>(r1, r2, r3, r4, r5)
                            r0 = 0
                            java.lang.String r1 = "AccountUserApi"
                            java.lang.String r2 = "start get userInfo and profile from XMPassport"
                            com.xiaomi.youpin.login.other.log.LoginLogUtilsGrey.a(r1, r2)     // Catch:{ InvalidResponseException -> 0x0080, CipherException -> 0x0079, IOException -> 0x0072, AuthenticationFailureException -> 0x006b, AccessDeniedException -> 0x0064 }
                            com.xiaomi.accountsdk.account.data.XiaomiUserInfo r1 = com.xiaomi.accountsdk.account.XMPassport.getXiaomiUserInfo(r8)     // Catch:{ InvalidResponseException -> 0x0080, CipherException -> 0x0079, IOException -> 0x0072, AuthenticationFailureException -> 0x006b, AccessDeniedException -> 0x0064 }
                            java.lang.String r2 = "AccountUserApi"
                            java.lang.String r3 = "userInfo got"
                            com.xiaomi.youpin.login.other.log.LoginLogUtilsGrey.a(r2, r3)     // Catch:{ InvalidResponseException -> 0x0061, CipherException -> 0x005e, IOException -> 0x005b, AuthenticationFailureException -> 0x0058, AccessDeniedException -> 0x0055 }
                            com.xiaomi.accountsdk.account.data.XiaomiUserProfile r8 = com.xiaomi.accountsdk.account.XMPassport.getXiaomiUserProfile(r8)     // Catch:{ InvalidResponseException -> 0x0061, CipherException -> 0x005e, IOException -> 0x005b, AuthenticationFailureException -> 0x0058, AccessDeniedException -> 0x0055 }
                            java.lang.String r2 = "AccountUserApi"
                            java.lang.String r3 = "profile got"
                            com.xiaomi.youpin.login.other.log.LoginLogUtilsGrey.a(r2, r3)     // Catch:{ InvalidResponseException -> 0x0050, CipherException -> 0x004b, IOException -> 0x0046, AuthenticationFailureException -> 0x0041, AccessDeniedException -> 0x003c }
                            r2 = r8
                            r8 = r0
                            goto L_0x0086
                        L_0x003c:
                            r2 = move-exception
                            r6 = r2
                            r2 = r8
                            r8 = r6
                            goto L_0x0067
                        L_0x0041:
                            r2 = move-exception
                            r6 = r2
                            r2 = r8
                            r8 = r6
                            goto L_0x006e
                        L_0x0046:
                            r2 = move-exception
                            r6 = r2
                            r2 = r8
                            r8 = r6
                            goto L_0x0075
                        L_0x004b:
                            r2 = move-exception
                            r6 = r2
                            r2 = r8
                            r8 = r6
                            goto L_0x007c
                        L_0x0050:
                            r2 = move-exception
                            r6 = r2
                            r2 = r8
                            r8 = r6
                            goto L_0x0083
                        L_0x0055:
                            r8 = move-exception
                            r2 = r0
                            goto L_0x0067
                        L_0x0058:
                            r8 = move-exception
                            r2 = r0
                            goto L_0x006e
                        L_0x005b:
                            r8 = move-exception
                            r2 = r0
                            goto L_0x0075
                        L_0x005e:
                            r8 = move-exception
                            r2 = r0
                            goto L_0x007c
                        L_0x0061:
                            r8 = move-exception
                            r2 = r0
                            goto L_0x0083
                        L_0x0064:
                            r8 = move-exception
                            r1 = r0
                            r2 = r1
                        L_0x0067:
                            r8.printStackTrace()
                            goto L_0x0086
                        L_0x006b:
                            r8 = move-exception
                            r1 = r0
                            r2 = r1
                        L_0x006e:
                            r8.printStackTrace()
                            goto L_0x0086
                        L_0x0072:
                            r8 = move-exception
                            r1 = r0
                            r2 = r1
                        L_0x0075:
                            r8.printStackTrace()
                            goto L_0x0086
                        L_0x0079:
                            r8 = move-exception
                            r1 = r0
                            r2 = r1
                        L_0x007c:
                            r8.printStackTrace()
                            goto L_0x0086
                        L_0x0080:
                            r8 = move-exception
                            r1 = r0
                            r2 = r1
                        L_0x0083:
                            r8.printStackTrace()
                        L_0x0086:
                            r3 = -1
                            if (r8 == 0) goto L_0x00b4
                            java.lang.String r1 = "AccountUserApi"
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder
                            r2.<init>()
                            java.lang.String r4 = "XMPassport exception:"
                            r2.append(r4)
                            java.lang.String r4 = r8.getMessage()
                            r2.append(r4)
                            java.lang.String r2 = r2.toString()
                            com.xiaomi.youpin.login.other.log.LoginLogUtilsGrey.a(r1, r2)
                            com.xiaomi.youpin.login.entity.error.ExceptionError r1 = new com.xiaomi.youpin.login.entity.error.ExceptionError
                            java.lang.String r2 = r8.getMessage()
                            r1.<init>(r3, r2)
                            r1.f23518a = r8
                            android.util.Pair r8 = new android.util.Pair
                            r8.<init>(r0, r1)
                            return r8
                        L_0x00b4:
                            if (r1 == 0) goto L_0x0111
                            if (r2 != 0) goto L_0x00b9
                            goto L_0x0111
                        L_0x00b9:
                            com.xiaomi.youpin.login.entity.account.MiAccountInfo r8 = new com.xiaomi.youpin.login.entity.account.MiAccountInfo
                            r8.<init>()
                            java.lang.String r3 = r1.getUserId()
                            r8.f23513a = r3
                            java.lang.String r3 = r1.getUserName()
                            r8.b = r3
                            java.lang.String r3 = r1.getAvatarAddress()
                            r8.c = r3
                            java.lang.String r3 = r1.getPhone()
                            r8.d = r3
                            java.util.ArrayList r3 = r1.getPhoneList()
                            r8.e = r3
                            java.lang.String r3 = r1.getEmail()
                            r8.f = r3
                            java.lang.String r3 = r1.getNickName()
                            r8.g = r3
                            android.graphics.Bitmap r1 = r1.getAvatar()
                            r8.h = r1
                            com.xiaomi.accountsdk.account.data.Gender r1 = r2.getGender()
                            if (r1 == 0) goto L_0x0105
                            com.xiaomi.accountsdk.account.data.Gender r3 = com.xiaomi.accountsdk.account.data.Gender.MALE
                            if (r1 != r3) goto L_0x00fd
                            com.xiaomi.youpin.login.entity.account.MiAccountInfo$Gender r1 = com.xiaomi.youpin.login.entity.account.MiAccountInfo.Gender.MALE
                            r8.i = r1
                            goto L_0x0105
                        L_0x00fd:
                            com.xiaomi.accountsdk.account.data.Gender r3 = com.xiaomi.accountsdk.account.data.Gender.FEMALE
                            if (r1 != r3) goto L_0x0105
                            com.xiaomi.youpin.login.entity.account.MiAccountInfo$Gender r1 = com.xiaomi.youpin.login.entity.account.MiAccountInfo.Gender.FEMALE
                            r8.i = r1
                        L_0x0105:
                            java.util.Calendar r1 = r2.getBirthday()
                            r8.j = r1
                            android.util.Pair r1 = new android.util.Pair
                            r1.<init>(r8, r0)
                            return r1
                        L_0x0111:
                            java.lang.String r8 = "AccountUserApi"
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder
                            r4.<init>()
                            java.lang.String r5 = "XMPassport exception:userInfo="
                            r4.append(r5)
                            r4.append(r1)
                            java.lang.String r1 = ",profile="
                            r4.append(r1)
                            r4.append(r2)
                            java.lang.String r1 = r4.toString()
                            com.xiaomi.youpin.login.other.log.LoginLogUtilsGrey.a(r8, r1)
                            com.xiaomi.youpin.login.entity.error.ExceptionError r8 = new com.xiaomi.youpin.login.entity.error.ExceptionError
                            java.lang.String r1 = "data null"
                            r8.<init>(r3, r1)
                            android.util.Pair r1 = new android.util.Pair
                            r1.<init>(r0, r8)
                            return r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.login.okhttpApi.api.AccountUserApi.AnonymousClass3.AnonymousClass1.doInBackground(java.lang.Void[]):android.util.Pair");
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(Pair<MiAccountInfo, ExceptionError> pair) {
                        MiAccountInfo miAccountInfo = (MiAccountInfo) pair.first;
                        ExceptionError exceptionError = (ExceptionError) pair.second;
                        if (exceptionError == null) {
                            LoginLogUtilsGrey.a("AccountUserApi", "XMPassport getMiUserInfoAndProfile onSuccess");
                            basePassportCallback.a(miAccountInfo);
                            return;
                        }
                        Exception exc = exceptionError.f23518a;
                        if (exc != null && (exc instanceof AuthenticationFailureException)) {
                            LoginLogUtilsGrey.a("AccountUserApi", "XMPassport AuthenticationFailureException isNeedRefresh=" + z);
                            if (z) {
                                AnonymousClass3.this.b();
                                return;
                            }
                        }
                        basePassportCallback.a(exceptionError.a(), exceptionError.toString());
                    }
                }, new Void[0]);
            }
        }.a();
    }
}
