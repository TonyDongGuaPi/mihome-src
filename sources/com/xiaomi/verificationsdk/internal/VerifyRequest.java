package com.xiaomi.verificationsdk.internal;

import android.util.Log;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.SimpleRequest;
import com.xiaomi.accountsdk.request.SimpleRequestForAccount;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.accountsdk.utils.EasyMap;
import com.xiaomi.verificationsdk.BuildConfig;
import com.xiaomi.verificationsdk.internal.ErrorInfo;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Map;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

public class VerifyRequest {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23133a = "VerifyRequest";

    public static RegisterInfo a(String str) throws VerificationException {
        try {
            String property = System.getProperty("http.agent");
            SimpleRequest.StringContent asString = SimpleRequestForAccount.getAsString(str, (Map<String, String>) null, new EasyMap().easyPut("User-Agent", property + " AndroidVerifySDK/" + BuildConfig.f), (Map<String, String>) null, true);
            if (asString != null) {
                JSONObject jSONObject = new JSONObject(asString.getBody());
                int optInt = jSONObject.optInt("code");
                if (optInt == 0) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    return new RegisterInfo(jSONObject2.optString("eventId"), jSONObject2.optString("clientId"));
                }
                Log.e(f23133a, "getRegisterInfo :" + ("code: " + optInt + ", msg: " + jSONObject.optString("msg")));
                throw new VerificationException(optInt, "getRegisterInfo:" + jSONObject.optString("msg"), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_SERVER));
            }
            Log.e(f23133a, "getRegisterInfo:stringContent is null");
            throw new VerificationException(ErrorInfo.ErrorCode.ERROR_NETWORK_EXCEPTION.getCode(), "getRegisterInfo:stringContent is null", ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_NETWORK_EXCEPTION));
        } catch (JSONException e) {
            AccountLog.e(f23133a, "fail to parse JSONObject", e);
            throw new VerificationException(ErrorInfo.ErrorCode.ERROR_JSON_EXCEPTION.getCode(), "getRegisterInfo:" + e.toString(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_JSON_EXCEPTION));
        } catch (IOException e2) {
            e2.printStackTrace();
            if (e2 instanceof ConnectException) {
                throw new VerificationException(ErrorInfo.ErrorCode.ERROR_CONNECT_UNREACHABLE_EXCEPTION.getCode(), "getRegisterInfo:" + e2.toString(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_CONNECT_UNREACHABLE_EXCEPTION));
            } else if (e2 instanceof SocketTimeoutException) {
                throw new VerificationException(ErrorInfo.ErrorCode.ERROR_SOCKET_TIMEOUT_EXCEPTION.getCode(), "getRegisterInfo:" + e2.toString(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_SOCKET_TIMEOUT_EXCEPTION));
            } else if (e2 instanceof ConnectTimeoutException) {
                throw new VerificationException(ErrorInfo.ErrorCode.ERROR_CONNECT_TIMEOUT_EXCEPTION.getCode(), "getRegisterInfo:" + e2.toString(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_CONNECT_TIMEOUT_EXCEPTION));
            } else {
                throw new VerificationException(ErrorInfo.ErrorCode.ERROR_IO_EXCEPTION.getCode(), "getRegisterInfo:" + e2.toString(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_IO_EXCEPTION));
            }
        } catch (AccessDeniedException e3) {
            e3.printStackTrace();
            throw new VerificationException(ErrorInfo.ErrorCode.ERROR_ACCESSDENIED_EXCEPTION.getCode(), "getRegisterInfo:" + e3.toString(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_ACCESSDENIED_EXCEPTION));
        } catch (AuthenticationFailureException e4) {
            e4.printStackTrace();
            throw new VerificationException(ErrorInfo.ErrorCode.ERROR_AUTHENTICATIONFAILURE_EXCEPTION.getCode(), "getRegisterInfo:" + e4.toString(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_AUTHENTICATIONFAILURE_EXCEPTION));
        } catch (VerificationException e5) {
            e5.printStackTrace();
            throw new VerificationException(e5.getCode(), "getRegisterInfo:" + e5.getMessage(), e5.getDialogTipMsg());
        }
    }

    public static Config b(String str) {
        try {
            SimpleRequest.StringContent asString = SimpleRequestForAccount.getAsString(str, new EasyMap().easyPut("type", String.valueOf(2)), (Map<String, String>) null, (Map<String, String>) null, true);
            if (asString == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(asString.getBody());
            int optInt = jSONObject.optInt("maxDuration");
            int optInt2 = jSONObject.optInt(Constants.Q);
            Config config = new Config();
            config.a(optInt);
            config.b(optInt2);
            return config;
        } catch (JSONException e) {
            AccountLog.w(f23133a, "fail to parse JSONObject", e);
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (AccessDeniedException e3) {
            e3.printStackTrace();
            return null;
        } catch (AuthenticationFailureException e4) {
            e4.printStackTrace();
            return null;
        }
    }
}
