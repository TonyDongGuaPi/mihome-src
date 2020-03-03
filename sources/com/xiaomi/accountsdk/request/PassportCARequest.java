package com.xiaomi.accountsdk.request;

import android.text.TextUtils;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.account.data.PassportCAToken;
import com.xiaomi.account.exception.PassportCAException;
import com.xiaomi.accountsdk.account.PassportCATokenManager;
import com.xiaomi.accountsdk.request.SimpleRequest;
import com.xiaomi.accountsdk.utils.AESWithIVCoder;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.accountsdk.utils.CloudCoder;
import com.xiaomi.accountsdk.utils.CryptCoder;
import com.xiaomi.accountsdk.utils.EasyMap;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.json.JSONException;

public class PassportCARequest extends PassportRequest {
    private static final String KEY_PASSPORT_CA_TOKEN = "passport_ca_token";
    private static final String TAG = "com.xiaomi.accountsdk.request.PassportCARequest";
    private final PassportCATokenManager caTokenManager;
    private boolean handled401Already = false;
    private final PassportSimpleRequest originalRequest;

    private interface EncryptRule {
        boolean shouldEncrypt(String str);
    }

    public PassportCARequest(PassportSimpleRequest passportSimpleRequest, PassportCATokenManager passportCATokenManager) {
        this.originalRequest = passportSimpleRequest;
        this.caTokenManager = passportCATokenManager;
    }

    public SimpleRequest.StringContent execute() throws IOException, PassportRequestException {
        if (getCaTokenManager() == null || !getCaTokenManager().isReady()) {
            throw new PassportRequestException(new PassportCAException("null CA Manager"));
        }
        try {
            return executeInternal();
        } catch (PassportRequestException e) {
            if (!(e.getCause() instanceof AuthenticationFailureException) || this.handled401Already) {
                throw e;
            }
            this.handled401Already = true;
            return handle401Error((AuthenticationFailureException) e.getCause());
        } catch (AuthenticationFailureException e2) {
            if (!this.handled401Already) {
                this.handled401Already = true;
                return handle401Error(e2);
            }
            throw new PassportRequestException(e2);
        } catch (AccessDeniedException e3) {
            throw new PassportRequestException(e3);
        } catch (PassportCAException e4) {
            throw new PassportRequestException(e4);
        } catch (InvalidResponseException e5) {
            throw new PassportRequestException(e5);
        }
    }

    /* access modifiers changed from: package-private */
    public PassportCATokenManager getCaTokenManager() {
        return this.caTokenManager;
    }

    private static class ParamEncryptRule implements EncryptRule {
        private ParamEncryptRule() {
        }

        public boolean shouldEncrypt(String str) {
            return str != null && !str.startsWith(JSMethod.NOT_SET);
        }
    }

    private static class CookiesEncryptRule implements EncryptRule {
        private CookiesEncryptRule() {
        }

        public boolean shouldEncrypt(String str) {
            return str != null && !str.startsWith("__") && !str.equals(PassportCARequest.KEY_PASSPORT_CA_TOKEN);
        }
    }

    /* access modifiers changed from: package-private */
    public SimpleRequest.StringContent executeInternal() throws PassportCAException, IOException, InvalidResponseException, AccessDeniedException, AuthenticationFailureException, PassportRequestException {
        if (!this.originalRequest.isHTTPS()) {
            PassportSimpleRequest copy = this.originalRequest.copy();
            PassportRequestArguments passportRequestArguments = copy.arguments;
            PassportCAToken passportCAToken = getPassportCAToken(passportRequestArguments.url, getCaTokenManager());
            if (passportCAToken == null || !passportCAToken.isLegal()) {
                throw new PassportCAException("null CA token");
            }
            passportRequestArguments.params.put("_nonce", CloudCoder.generateNonce());
            passportRequestArguments.cookies.put(KEY_PASSPORT_CA_TOKEN, passportCAToken.token);
            passportRequestArguments.headers.put("caTag", "noSafe");
            AESWithIVCoder aESWithIVCoder = new AESWithIVCoder(passportCAToken.security);
            encrypt(passportRequestArguments.params, aESWithIVCoder, new ParamEncryptRule());
            encrypt(passportRequestArguments.cookies, aESWithIVCoder, new CookiesEncryptRule());
            passportRequestArguments.params.put("_caSign", CloudCoder.generateSignature(copy.getMethod(), passportRequestArguments.url, passportRequestArguments.params, passportCAToken.security));
            SimpleRequest.StringContent execute = new RequestWithIP(copy, new IPStrategy(), new CALoginStatHelper()).execute();
            if (execute != null) {
                return parseResponse(aESWithIVCoder, execute);
            }
            throw new IOException("no response from server");
        }
        throw new IllegalStateException("https request should not use PassportCA");
    }

    private static SimpleRequest.StringContent parseResponse(CryptCoder cryptCoder, SimpleRequest.StringContent stringContent) throws InvalidResponseException {
        String body = stringContent.getBody();
        boolean z = false;
        boolean z2 = stringContent.getHttpCode() == 302;
        if (!TextUtils.isEmpty(body)) {
            try {
                SimpleRequest.StringContent stringContent2 = new SimpleRequest.StringContent(cryptCoder.decrypt(body));
                stringContent2.setHttpCode(stringContent.getHttpCode());
                Map<String, String> headers = stringContent.getHeaders();
                for (String next : stringContent.getCookieKeys()) {
                    try {
                        headers.put(next, cryptCoder.decrypt(stringContent.getHeader(next)));
                    } catch (CipherException unused) {
                    }
                }
                stringContent2.putHeaders(headers);
                return stringContent2;
            } catch (CipherException e) {
                boolean isHtml = isHtml(body);
                if (z2 || isHtml) {
                    z = true;
                }
                throw new InvalidResponseException("failed to decrypt response", e, z);
            }
        } else {
            throw new InvalidResponseException("invalid response from server", (Throwable) null, z2);
        }
    }

    static boolean isHtml(String str) {
        return str != null && (str.contains("html") || str.contains("http"));
    }

    private static PassportCAToken getPassportCAToken(String str, PassportCATokenManager passportCATokenManager) throws PassportCAException, AuthenticationFailureException {
        try {
            return passportCATokenManager.getPassportCAToken(str);
        } catch (CertificateException e) {
            throw new PassportCAException((Throwable) e);
        } catch (NoSuchAlgorithmException e2) {
            throw new PassportCAException((Throwable) e2);
        } catch (NoSuchPaddingException e3) {
            throw new PassportCAException((Throwable) e3);
        } catch (JSONException e4) {
            throw new PassportCAException((Throwable) e4);
        } catch (BadPaddingException e5) {
            throw new PassportCAException((Throwable) e5);
        } catch (IllegalBlockSizeException e6) {
            throw new PassportCAException((Throwable) e6);
        } catch (InvalidKeyException e7) {
            throw new PassportCAException((Throwable) e7);
        } catch (AccessDeniedException e8) {
            throw new PassportCAException((Throwable) e8);
        } catch (InvalidResponseException e9) {
            throw new PassportCAException((Throwable) e9);
        } catch (CipherException e10) {
            throw new PassportCAException((Throwable) e10);
        } catch (IOException e11) {
            throw new PassportCAException((Throwable) e11);
        }
    }

    private static void encrypt(EasyMap<String, String> easyMap, CryptCoder cryptCoder, EncryptRule encryptRule) throws PassportCAException {
        try {
            for (Map.Entry entry : easyMap.entrySet()) {
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                if (!(str == null || str2 == null || !encryptRule.shouldEncrypt(str))) {
                    easyMap.put(str, cryptCoder.encrypt(str2));
                }
            }
        } catch (CipherException e) {
            throw new PassportCAException((Throwable) e);
        }
    }

    /* access modifiers changed from: package-private */
    public SimpleRequest.StringContent handle401Error(AuthenticationFailureException authenticationFailureException) throws IOException, PassportRequestException {
        Long l;
        String wwwAuthenticateHeader = authenticationFailureException.getWwwAuthenticateHeader();
        if ("passportCA".equals(wwwAuthenticateHeader)) {
            getCaTokenManager().invalidateAllCAToken();
            return execute();
        } else if ("passportCA-Disabled".equals(wwwAuthenticateHeader)) {
            try {
                l = Long.valueOf(authenticationFailureException.getCaDisableSecondsHeader());
            } catch (NumberFormatException e) {
                AccountLog.w(TAG, (Throwable) e);
                l = null;
            }
            getCaTokenManager().onCADisabledForSeconds(l);
            throw new PassportRequestException(new PassportCAException("PassportCA Disabled"));
        } else {
            throw new PassportRequestException(authenticationFailureException);
        }
    }
}
