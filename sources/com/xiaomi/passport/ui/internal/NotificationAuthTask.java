package com.xiaomi.passport.ui.internal;

import android.os.AsyncTask;
import com.xiaomi.accountsdk.account.data.NotificationAuthResult;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.SimpleRequest;
import com.xiaomi.accountsdk.request.SimpleRequestForAccount;
import com.xiaomi.accountsdk.utils.AccountLog;
import java.io.IOException;
import java.util.Map;

public class NotificationAuthTask extends AsyncTask<Void, Void, NotificationAuthResult> {
    private static final String TAG = "NotificationAuthTask";
    private final NotificationAuthUICallback mCallback;
    private final String mStsUrl;

    public interface NotificationAuthUICallback {
        void call(NotificationAuthResult notificationAuthResult);
    }

    NotificationAuthTask(String str, NotificationAuthUICallback notificationAuthUICallback) {
        this.mStsUrl = str;
        this.mCallback = notificationAuthUICallback;
    }

    /* access modifiers changed from: protected */
    public NotificationAuthResult doInBackground(Void... voidArr) {
        return doRequest();
    }

    private NotificationAuthResult doRequest() {
        Map<String, String> headers;
        try {
            SimpleRequest.StringContent asString = SimpleRequestForAccount.getAsString(this.mStsUrl, (Map<String, String>) null, (Map<String, String>) null, false);
            if (!(asString == null || (headers = asString.getHeaders()) == null)) {
                return new NotificationAuthResult.Builder().setUserId(headers.get("userId")).setServiceToken(headers.get("serviceToken")).setPSecurity_ph(headers.get("passportsecurity_ph")).setPSecurity_slh(headers.get("passportsecurity_slh")).build();
            }
        } catch (RuntimeException e) {
            letCrashAsync(e);
            AccountLog.e(TAG, "runtime exception", e);
            return null;
        } catch (IOException e2) {
            AccountLog.e(TAG, "network error", e2);
        } catch (AccessDeniedException e3) {
            AccountLog.e(TAG, "access denied", e3);
        } catch (AuthenticationFailureException e4) {
            AccountLog.e(TAG, "auth error", e4);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(NotificationAuthResult notificationAuthResult) {
        super.onPostExecute(notificationAuthResult);
        this.mCallback.call(notificationAuthResult);
    }

    private void letCrashAsync(final RuntimeException runtimeException) {
        new Thread() {
            public void run() {
                throw runtimeException;
            }
        }.start();
    }
}
