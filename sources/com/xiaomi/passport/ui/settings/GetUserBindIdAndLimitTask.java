package com.xiaomi.passport.ui.settings;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import com.xiaomi.accountsdk.account.exception.InvalidPhoneNumException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.data.XMPassportInfo;
import java.io.IOException;

public class GetUserBindIdAndLimitTask extends AsyncTask<Void, Void, Pair<Integer, UserBindIdAndLimitResult>> {
    private final String TAG = "GetUserBindIdAndLimitTask";
    private GetUserBindIdAndLimitCallBack mCallBack;
    private Context mContext;
    private String mInputPhone;

    public interface GetUserBindIdAndLimitCallBack {
        void onFail(int i);

        void onSuccess(UserBindIdAndLimitResult userBindIdAndLimitResult);
    }

    public GetUserBindIdAndLimitTask(Context context, String str, GetUserBindIdAndLimitCallBack getUserBindIdAndLimitCallBack) {
        this.mContext = context != null ? context.getApplicationContext() : null;
        this.mInputPhone = str;
        this.mCallBack = getUserBindIdAndLimitCallBack;
    }

    /* access modifiers changed from: protected */
    public Pair<Integer, UserBindIdAndLimitResult> doInBackground(Void... voidArr) {
        if (this.mCallBack == null || this.mContext == null) {
            AccountLog.w("GetUserBindIdAndLimitTask", "null callback");
            return null;
        }
        XMPassportInfo build = XMPassportInfo.build(this.mContext, "passportapi");
        if (build == null) {
            AccountLog.w("GetUserBindIdAndLimitTask", "null passportInfo");
            return Pair.create(1, (Object) null);
        }
        int i = 0;
        int i2 = 5;
        while (i < 2) {
            i2 = 3;
            try {
                return Pair.create(0, CloudHelper.getUserBindIdAndLimit(build, this.mInputPhone));
            } catch (IOException e) {
                AccountLog.e("GetUserBindIdAndLimitTask", "IOException", e);
                i2 = 2;
                i++;
            } catch (AuthenticationFailureException e2) {
                AccountLog.e("GetUserBindIdAndLimitTask", "AuthenticationFailureException", e2);
                build.refreshAuthToken(this.mContext);
                i2 = 1;
                i++;
            } catch (AccessDeniedException e3) {
                AccountLog.e("GetUserBindIdAndLimitTask", "AccessDeniedException", e3);
                i2 = 4;
                i++;
            } catch (InvalidResponseException e4) {
                AccountLog.e("GetUserBindIdAndLimitTask", "InvalidResponseException", e4);
                i++;
            } catch (CipherException e5) {
                AccountLog.e("GetUserBindIdAndLimitTask", "CipherException", e5);
                i++;
            } catch (InvalidPhoneNumException e6) {
                AccountLog.e("GetUserBindIdAndLimitTask", "InvalidPhoneNumException", e6);
                i2 = 9;
                i++;
            }
        }
        return Pair.create(Integer.valueOf(i2), (Object) null);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Pair<Integer, UserBindIdAndLimitResult> pair) {
        AsyncTaskResult asyncTaskResult = new AsyncTaskResult(((Integer) pair.first).intValue());
        if (asyncTaskResult.hasException()) {
            this.mCallBack.onFail(asyncTaskResult.getExceptionReason());
        } else {
            this.mCallBack.onSuccess((UserBindIdAndLimitResult) pair.second);
        }
    }

    public static class UserBindIdAndLimitResult {
        private int times;
        private long ts;
        private String userId;

        public UserBindIdAndLimitResult(String str, long j, int i) {
            this.userId = str;
            this.ts = j;
            this.times = i;
        }

        public String getUserId() {
            return this.userId;
        }

        public long getTime() {
            return this.ts;
        }

        public int getTimes() {
            return this.times;
        }
    }
}
