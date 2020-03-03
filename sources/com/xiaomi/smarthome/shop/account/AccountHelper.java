package com.xiaomi.smarthome.shop.account;

import android.text.TextUtils;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.InvalidUserNameException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.utils.CloudCoder;
import com.xiaomi.youpin.common.AppIdManager;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AccountHelper {
    private static String a() {
        return CloudCoder.hashDeviceInfo(AppIdManager.a().c());
    }

    public static AccountInfo a(String str, String str2, String str3, String str4) throws IOException, InvalidResponseException, InvalidCredentialException, AccessDeniedException, AuthenticationFailureException, InvalidUserNameException {
        return XMPassport.loginByPassToken(str, str3, a(), str2, str4);
    }

    public static AccountInfo a(String str, String str2, String str3) throws IOException, InvalidResponseException, InvalidCredentialException, AccessDeniedException, AuthenticationFailureException, InvalidUserNameException {
        return XMPassport.loginByPassToken(str, str3, a(), str2);
    }

    public static boolean a(String str) {
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
}
