package com.xiaomi.passport.ui.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.data.IdentityAuthReason;
import com.xiaomi.accountsdk.account.data.PassportInfo;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.data.XMPassportInfo;
import java.io.IOException;

public class GetIdentityAuthUrlTask extends AsyncTask<Void, Void, TaskResult> {
    private final String TAG = "GetIdentityAuthUrlTask";
    private String mAuthST;
    private GetIdentityAuthUrlCallback mCallback;
    @SuppressLint({"StaticFieldLeak"})
    private Context mContext;
    private IdentityAuthReason mIdentityAuthType;

    public interface GetIdentityAuthUrlCallback {
        void onFail(int i);

        void onNeedNotification(String str);

        void onSuccess();
    }

    public GetIdentityAuthUrlTask(Context context, String str, IdentityAuthReason identityAuthReason, GetIdentityAuthUrlCallback getIdentityAuthUrlCallback) {
        this.mContext = context != null ? context.getApplicationContext() : null;
        this.mAuthST = str;
        this.mIdentityAuthType = identityAuthReason;
        this.mCallback = getIdentityAuthUrlCallback;
    }

    /* access modifiers changed from: protected */
    public TaskResult doInBackground(Void... voidArr) {
        if (this.mCallback == null || this.mContext == null) {
            AccountLog.w("GetIdentityAuthUrlTask", "null callback");
            return null;
        }
        XMPassportInfo build = XMPassportInfo.build(this.mContext, "passportapi");
        if (build == null) {
            AccountLog.w("GetIdentityAuthUrlTask", "null passportInfo");
            return null;
        }
        int i = 0;
        int i2 = 5;
        while (i < 2) {
            try {
                return new TaskResult(XMPassport.getIdentityAuthUrl((PassportInfo) build, this.mAuthST, this.mIdentityAuthType), 0);
            } catch (IOException e) {
                AccountLog.e("GetIdentityAuthUrlTask", "IOException", e);
                i2 = 2;
            } catch (AuthenticationFailureException e2) {
                AccountLog.e("GetIdentityAuthUrlTask", "AuthenticationFailureException", e2);
                build.refreshAuthToken(this.mContext);
                i++;
                i2 = 1;
            } catch (AccessDeniedException e3) {
                AccountLog.e("GetIdentityAuthUrlTask", "AccessDeniedException", e3);
                i2 = 4;
            } catch (InvalidResponseException e4) {
                AccountLog.e("GetIdentityAuthUrlTask", "InvalidResponseException", e4);
                i2 = 3;
            } catch (CipherException e5) {
                AccountLog.e("GetIdentityAuthUrlTask", "CipherException", e5);
                i2 = 3;
            }
        }
        return new TaskResult((String) null, i2);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(TaskResult taskResult) {
        super.onPostExecute(taskResult);
        if (taskResult == null) {
            AccountLog.w("GetIdentityAuthUrlTask", "null result");
        } else if (taskResult.hasException()) {
            this.mCallback.onFail(taskResult.getExceptionReason());
        } else if (TextUtils.isEmpty(taskResult.getNotificationUrl())) {
            this.mCallback.onSuccess();
        } else {
            this.mCallback.onNeedNotification(taskResult.getNotificationUrl());
        }
    }

    public class TaskResult {
        private AsyncTaskResult asyncTaskResult;
        private String notificationUrl;

        public TaskResult(String str, int i) {
            this.asyncTaskResult = new AsyncTaskResult(i);
            this.notificationUrl = str;
        }

        public boolean hasException() {
            return this.asyncTaskResult != null && this.asyncTaskResult.hasException();
        }

        public String getNotificationUrl() {
            return this.notificationUrl;
        }

        public int getExceptionReason() {
            return this.asyncTaskResult.getExceptionReason();
        }
    }
}
