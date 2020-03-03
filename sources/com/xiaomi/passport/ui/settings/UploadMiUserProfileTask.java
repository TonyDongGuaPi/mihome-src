package com.xiaomi.passport.ui.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.data.Gender;
import com.xiaomi.accountsdk.account.data.XiaomiUserProfile;
import com.xiaomi.accountsdk.account.exception.InvalidParameterException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.data.XMPassportInfo;
import com.xiaomi.passport.ui.R;
import java.io.IOException;
import java.util.Calendar;

public class UploadMiUserProfileTask extends AsyncTask<Void, Void, Integer> {
    private static final String TAG = "UploadMiUserProfileTask";
    @SuppressLint({"StaticFieldLeak"})
    private Context context;
    private Gender gender;
    private IUploadUserProfile uploadUserProfile;
    private String userName;

    public interface IUploadUserProfile {
        void onFinishUploading(String str, Gender gender);
    }

    UploadMiUserProfileTask(Context context2, String str, Gender gender2, IUploadUserProfile iUploadUserProfile) {
        this.context = context2 != null ? context2.getApplicationContext() : null;
        this.userName = str;
        this.gender = gender2;
        this.uploadUserProfile = iUploadUserProfile;
    }

    /* access modifiers changed from: protected */
    public Integer doInBackground(Void... voidArr) {
        if (this.context == null) {
            AccountLog.w(TAG, "context is null");
            return 5;
        }
        XMPassportInfo build = XMPassportInfo.build(this.context, "passportapi");
        if (build == null) {
            AccountLog.w(TAG, "null passportInfo");
            return 5;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 5;
        while (true) {
            if (i2 >= 2) {
                i = i3;
                break;
            }
            try {
                XMPassport.uploadXiaomiUserProfile(build, new XiaomiUserProfile(build.getUserId(), this.userName, (Calendar) null, this.gender));
                break;
            } catch (IOException e) {
                AccountLog.e(TAG, "UploadUserInfoTask error", e);
                i = 2;
            } catch (AuthenticationFailureException e2) {
                AccountLog.e(TAG, "UploadUserInfoTask error", e2);
                build.refreshAuthToken(this.context);
                i2++;
                i3 = 1;
            } catch (AccessDeniedException e3) {
                AccountLog.e(TAG, "UploadUserInfoTask error", e3);
                i = 4;
            } catch (InvalidResponseException e4) {
                AccountLog.e(TAG, "UploadUserInfoTask error", e4);
                i = 3;
            } catch (CipherException e5) {
                AccountLog.e(TAG, "UploadUserInfoTask error", e5);
                i = 3;
            } catch (InvalidParameterException e6) {
                AccountLog.e(TAG, "UploadUserInfoTask error", e6);
                i = 16;
            }
        }
        return Integer.valueOf(i);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Integer num) {
        int i;
        super.onPostExecute(num);
        AsyncTaskResult asyncTaskResult = new AsyncTaskResult(num.intValue());
        if (asyncTaskResult.hasException()) {
            if (num.intValue() != 16 || TextUtils.isEmpty(this.userName)) {
                i = asyncTaskResult.getExceptionReason();
            } else {
                i = R.string.account_error_user_name;
            }
            Toast.makeText(this.context, i, 0).show();
            return;
        }
        this.uploadUserProfile.onFinishUploading(this.userName, this.gender);
    }
}
