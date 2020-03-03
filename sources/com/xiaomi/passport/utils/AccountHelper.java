package com.xiaomi.passport.utils;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.xiaomi.accountsdk.account.AccountIntent;
import com.xiaomi.accountsdk.account.URLs;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.XMPassportSettings;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.accountsdk.account.data.PassportInfo;
import com.xiaomi.accountsdk.account.data.PasswordLoginParams;
import com.xiaomi.accountsdk.account.data.Step2LoginParams;
import com.xiaomi.accountsdk.account.data.XiaomiUserCoreInfo;
import com.xiaomi.accountsdk.account.exception.IllegalDeviceException;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.InvalidUserNameException;
import com.xiaomi.accountsdk.account.exception.NeedCaptchaException;
import com.xiaomi.accountsdk.account.exception.NeedNotificationException;
import com.xiaomi.accountsdk.account.exception.NeedVerificationException;
import com.xiaomi.accountsdk.hasheddeviceidlib.HashedDeviceIdUtil;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.request.SecureRequestForAccount;
import com.xiaomi.accountsdk.request.SimpleRequest;
import com.xiaomi.accountsdk.request.SimpleRequestForAccount;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.accountsdk.utils.EasyMap;
import com.xiaomi.accountsdk.utils.UserSpaceIdUtil;
import com.xiaomi.passport.PassportUserEnvironment;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class AccountHelper {
    private static final Integer INT_0 = 0;
    private static final String PATTERN_URL_MI_ACCOUNT_LOGIN = "((https://.*\\.account\\.xiaomi\\.com)|(http://.*\\.account\\.preview\\.n\\.xiaomi\\.net))/longPolling/login\\?.*";
    private static final String TAG = "AccountHelper";
    private static final String URL_IS_SET_PASSWORD = (URLs.URL_ACCOUNT_API_V2_BASE + "/safe/user/isSetPassword");

    public static AccountInfo getStsUrlByPassword(String str, String str2, String str3, String str4, String str5) throws IOException, InvalidResponseException, NeedVerificationException, AuthenticationFailureException, NeedCaptchaException, InvalidCredentialException, AccessDeniedException, InvalidUserNameException, NeedNotificationException, IllegalDeviceException {
        return XMPassport.getStsUrlByPassword(str, str2, str3, getHashedDeviceId(), str4, str5, getEnvInfoArray());
    }

    public static AccountInfo getServiceTokenByPassword(String str, String str2, String str3, String str4, String str5) throws IOException, NeedCaptchaException, InvalidUserNameException, InvalidResponseException, AccessDeniedException, AuthenticationFailureException, InvalidCredentialException, NeedVerificationException, IllegalDeviceException {
        try {
            return getServiceTokenByPassword(str, str2, str3, str4, str5, false);
        } catch (NeedNotificationException unused) {
            throw new InvalidResponseException("Unexpected NeedNotificationException");
        }
    }

    public static AccountInfo getServiceTokenByPassword(String str, String str2, String str3, String str4, String str5, boolean z) throws IOException, NeedCaptchaException, InvalidUserNameException, InvalidResponseException, AccessDeniedException, AuthenticationFailureException, InvalidCredentialException, NeedVerificationException, NeedNotificationException, IllegalDeviceException {
        return XMPassport.loginByPassword(str, str5, getHashedDeviceId(), str2, str3, str4, (MetaLoginData) null, z, getEnvInfoArray());
    }

    public static AccountInfo loginByPassword(PasswordLoginParams passwordLoginParams) throws IOException, NeedCaptchaException, InvalidUserNameException, InvalidResponseException, AccessDeniedException, AuthenticationFailureException, InvalidCredentialException, NeedVerificationException, NeedNotificationException, IllegalDeviceException {
        if (passwordLoginParams != null) {
            PasswordLoginParams.Builder copyFrom = PasswordLoginParams.copyFrom(passwordLoginParams);
            if (TextUtils.isEmpty(passwordLoginParams.deviceId)) {
                copyFrom.setDeviceId(getHashedDeviceId());
            }
            if (passwordLoginParams.hashedEnvFactors == null) {
                copyFrom.setHashedEnvFactors(getEnvInfoArray());
            }
            return XMPassport.loginByPassword(copyFrom.build());
        }
        throw new IllegalArgumentException("password login params is null");
    }

    public static AccountInfo getServiceTokenByPassToken(String str, String str2, String str3, String str4) throws IOException, InvalidResponseException, InvalidCredentialException, AccessDeniedException, AuthenticationFailureException, InvalidUserNameException, IllegalDeviceException, NeedNotificationException {
        return XMPassport.loginByPassTokenNE(str, str3, getHashedDeviceId(), str2, str4);
    }

    public static AccountInfo getServiceTokenByPassToken(String str, String str2, String str3) throws IOException, InvalidResponseException, InvalidCredentialException, AccessDeniedException, AuthenticationFailureException, InvalidUserNameException, IllegalDeviceException, NeedNotificationException {
        return XMPassport.loginByPassTokenNE(str, str3, getHashedDeviceId(), str2);
    }

    public static AccountInfo getStsUrlByPassToken(String str, String str2, String str3) throws IOException, InvalidResponseException, InvalidCredentialException, AccessDeniedException, AuthenticationFailureException, InvalidUserNameException, IllegalDeviceException, NeedNotificationException {
        return XMPassport.getStsUrlByPassToken(str, str3, getHashedDeviceId(), str2);
    }

    public static AccountInfo loginByStep2(Step2LoginParams step2LoginParams) throws IOException, AccessDeniedException, AuthenticationFailureException, InvalidCredentialException, InvalidResponseException, NeedVerificationException, InvalidUserNameException {
        return XMPassport.loginByStep2(step2LoginParams);
    }

    public static AccountInfo getStsUrlByStep2(String str, String str2, MetaLoginData metaLoginData, boolean z, String str3, String str4) throws IOException, AccessDeniedException, AuthenticationFailureException, InvalidCredentialException, InvalidResponseException, NeedVerificationException, InvalidUserNameException, IllegalDeviceException {
        return XMPassport.getStsUrlByStep2(str, str4, getHashedDeviceId(), str2, metaLoginData, z, str3);
    }

    public static AccountInfo getServiceTokenByStep2(String str, String str2, MetaLoginData metaLoginData, boolean z, String str3, String str4) throws IOException, AccessDeniedException, AuthenticationFailureException, InvalidCredentialException, InvalidResponseException, NeedVerificationException, InvalidUserNameException, IllegalDeviceException {
        return XMPassport.loginByStep2(str, str4, getHashedDeviceId(), str2, metaLoginData, z, str3);
    }

    public static String getHashedDeviceId() {
        return new HashedDeviceIdUtil(XMPassportSettings.getApplicationContext()).getHashedDeviceIdNoThrow();
    }

    @Deprecated
    public static Pair<Bitmap, String> getCaptchaImage(String str) {
        return XMPassport.getCaptchaImage(str);
    }

    @Deprecated
    public static Pair<Bitmap, String> getIckImage(String str) {
        return XMPassport.getCaptchaImageAndIck(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void sendActivateEmail(java.lang.String r2, java.lang.String r3, java.lang.String r4) throws java.io.IOException, com.xiaomi.accountsdk.request.InvalidResponseException {
        /*
            com.xiaomi.accountsdk.utils.EasyMap r0 = new com.xiaomi.accountsdk.utils.EasyMap
            r0.<init>()
            java.lang.String r1 = "userId"
            com.xiaomi.accountsdk.utils.EasyMap r2 = r0.easyPut(r1, r2)
            java.lang.String r0 = "addressType"
            java.lang.String r1 = "EM"
            com.xiaomi.accountsdk.utils.EasyMap r2 = r2.easyPut(r0, r1)
            java.lang.String r0 = "address"
            com.xiaomi.accountsdk.utils.EasyMap r2 = r2.easyPut(r0, r3)
            java.lang.String r3 = "region"
            com.xiaomi.accountsdk.utils.EasyMap r2 = r2.easyPutOpt(r3, r4)
            r3 = 0
            java.lang.String r4 = com.xiaomi.accountsdk.account.URLs.URL_RESEND_EMAIL     // Catch:{ AccessDeniedException -> 0x002d, AuthenticationFailureException -> 0x0028 }
            r0 = 1
            com.xiaomi.accountsdk.request.SimpleRequest$MapContent r2 = com.xiaomi.accountsdk.request.SimpleRequestForAccount.getAsMap(r4, r2, r3, r0)     // Catch:{ AccessDeniedException -> 0x002d, AuthenticationFailureException -> 0x0028 }
            goto L_0x0032
        L_0x0028:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0031
        L_0x002d:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0031:
            r2 = r3
        L_0x0032:
            if (r2 == 0) goto L_0x004b
            java.lang.String r3 = "code"
            java.lang.Object r2 = r2.getFromBody(r3)
            java.lang.Integer r3 = INT_0
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0043
            return
        L_0x0043:
            com.xiaomi.accountsdk.request.InvalidResponseException r2 = new com.xiaomi.accountsdk.request.InvalidResponseException
            java.lang.String r3 = "invalid response, failed to send activate email"
            r2.<init>(r3)
            throw r2
        L_0x004b:
            java.io.IOException r2 = new java.io.IOException
            java.lang.String r3 = "failed to register, no response"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.utils.AccountHelper.sendActivateEmail(java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static Bundle getAccountAuthenticatorResponseResult(int i, String str, String str2) {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("authAccount", str);
            bundle.putString("accountType", "com.xiaomi");
        }
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("authtoken", str2);
        }
        bundle.putBoolean("booleanResult", i == -1);
        if (i == 0) {
            bundle.putInt("errorCode", 4);
        }
        return bundle;
    }

    public static Bundle getAccountAuthenticatorResponseResult(int i, AccountInfo accountInfo, boolean z) {
        Bundle authenticatorResponseBundle = getAuthenticatorResponseBundle(accountInfo, z);
        if (i == 0) {
            authenticatorResponseBundle.putInt("errorCode", 4);
        }
        return authenticatorResponseBundle;
    }

    public static Bundle getAuthenticatorResponseBundle(AccountInfo accountInfo, boolean z) {
        Bundle bundle = new Bundle();
        if (accountInfo == null || accountInfo.userId == null) {
            bundle.putBoolean("booleanResult", false);
            return bundle;
        }
        bundle.putString("authAccount", accountInfo.userId);
        bundle.putString("accountType", "com.xiaomi");
        if (!TextUtils.isEmpty(accountInfo.encryptedUserId)) {
            bundle.putString("encrypted_user_id", accountInfo.encryptedUserId);
        }
        bundle.putBoolean(AccountIntent.EXTRA_HAS_PASSWORD, accountInfo.getHasPwd());
        if (!TextUtils.isEmpty(accountInfo.autoLoginUrl)) {
            bundle.putString(AccountIntent.EXTRA_STS_URL_RESULT, accountInfo.autoLoginUrl);
            bundle.putString("sts_url", accountInfo.autoLoginUrl);
        }
        String serviceId = accountInfo.getServiceId();
        String serviceToken = accountInfo.getServiceToken();
        if (!TextUtils.isEmpty(serviceId) && !TextUtils.isEmpty(serviceToken)) {
            bundle.putString("authtoken", ExtendedAuthToken.build(serviceToken, accountInfo.getSecurity()).toPlain());
        }
        bundle.putBoolean("booleanResult", true);
        bundle.putBoolean("retry", z);
        return bundle;
    }

    public static boolean isWebSsoAuthTokenType(String str) {
        return str != null && str.startsWith("weblogin:");
    }

    public static boolean isTrustedWebSsoUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            URL url = new URL(str);
            URL url2 = new URL(XMPassport.ACCOUNT_DOMAIN);
            String protocol = url2.getProtocol();
            String protocol2 = url.getProtocol();
            String host = url2.getHost();
            String host2 = url.getHost();
            if (!protocol.equalsIgnoreCase(protocol2) || !host.equalsIgnoreCase(host2)) {
                return false;
            }
            return true;
        } catch (MalformedURLException unused) {
            return false;
        }
    }

    public static String[] getEnvInfoArray() {
        return PassportUserEnvironment.Holder.getInstance().getEnvInfoArray(XMPassportSettings.getApplicationContext());
    }

    public static boolean isMiAccountLoginQRCodeScanResult(String str) {
        return !TextUtils.isEmpty(str) && Pattern.matches(PATTERN_URL_MI_ACCOUNT_LOGIN, str);
    }

    public static boolean isSetPassword(PassportInfo passportInfo, String str, String str2, String str3) throws AccessDeniedException, AuthenticationFailureException, InvalidResponseException, CipherException, IOException {
        if (passportInfo != null) {
            SimpleRequest.StringContent asString = SecureRequestForAccount.getAsString(URL_IS_SET_PASSWORD, new EasyMap().easyPut("userId", passportInfo.getUserId()).easyPutOpt("sid", str).easyPut("transId", str3), new EasyMap().easyPut("cUserId", passportInfo.getEncryptedUserId()).easyPut("serviceToken", passportInfo.getServiceToken()).easyPut("deviceId", str2).easyPutOpt(SimpleRequestForAccount.COOKIE_NAME_USER_SPACE_ID, UserSpaceIdUtil.getNullableUserSpaceIdCookie()), true, passportInfo.getSecurity());
            if (asString != null) {
                String removeSafePrefixAndGetRealBody = XMPassport.removeSafePrefixAndGetRealBody(asString);
                try {
                    JSONObject jSONObject = new JSONObject(removeSafePrefixAndGetRealBody);
                    int i = jSONObject.getInt("code");
                    if (i == 0) {
                        return jSONObject.getJSONObject("data").getBoolean("status");
                    }
                    throw new InvalidResponseException("code: " + i + "desc: " + jSONObject.optString("description"));
                } catch (JSONException unused) {
                    throw new InvalidResponseException("json error: " + removeSafePrefixAndGetRealBody);
                }
            } else {
                throw new InvalidResponseException("http response result should not be null");
            }
        } else {
            throw new IllegalArgumentException("passport info should not be null");
        }
    }

    public static XiaomiUserCoreInfo getXiaomiUserCoreInfo(PassportInfo passportInfo, String str, List<XiaomiUserCoreInfo.Flag> list) throws AccessDeniedException, AuthenticationFailureException, InvalidResponseException, CipherException, IOException {
        if (passportInfo != null) {
            return XMPassport.getXiaomiUserCoreInfo(passportInfo, str, list);
        }
        AccountLog.e(TAG, "passportinfo is null");
        return null;
    }
}
