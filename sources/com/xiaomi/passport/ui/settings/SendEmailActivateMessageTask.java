package com.xiaomi.passport.ui.settings;

import android.content.Context;
import android.os.AsyncTask;
import com.xiaomi.accountsdk.account.exception.InvalidBindAddressException;
import com.xiaomi.accountsdk.account.exception.NeedCaptchaException;
import com.xiaomi.accountsdk.account.exception.ReachLimitException;
import com.xiaomi.accountsdk.account.exception.UsedEmailAddressException;
import com.xiaomi.accountsdk.hasheddeviceidlib.HashedDeviceIdUtil;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.data.XMPassportInfo;
import java.io.IOException;

public class SendEmailActivateMessageTask extends AsyncTask<Void, Void, EmailTaskResult> {
    private static final String TAG = "SendEmailActivateMessag";
    private String mAuthST;
    private Context mContext;
    private String mEmailAddress;
    private String sCaptCode;
    private String sCaptIck;

    SendEmailActivateMessageTask(Context context, String str, String str2, String str3, String str4) {
        this.mContext = context.getApplicationContext();
        this.mEmailAddress = str;
        this.mAuthST = str2;
        this.sCaptCode = str3;
        this.sCaptIck = str4;
    }

    /* access modifiers changed from: protected */
    public EmailTaskResult doInBackground(Void... voidArr) {
        XMPassportInfo build = XMPassportInfo.build(this.mContext, "passportapi");
        String str = null;
        int i = 5;
        if (build == null) {
            AccountLog.w(TAG, "null passportInfo");
            return new EmailTaskResult(5, (String) null);
        }
        String hashedDeviceIdNoThrow = new HashedDeviceIdUtil(this.mContext).getHashedDeviceIdNoThrow();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= 2) {
                i2 = i;
                break;
            }
            try {
                MyXMPassport.sendEmailActivateMessage(build, this.mEmailAddress, this.mAuthST, hashedDeviceIdNoThrow, this.sCaptCode, this.sCaptIck);
                break;
            } catch (IOException e) {
                AccountLog.e(TAG, "IOException", e);
                i2 = 2;
            } catch (AuthenticationFailureException e2) {
                AccountLog.e(TAG, "AuthenticationFailureException", e2);
                build.refreshAuthToken(this.mContext);
                i3++;
                i = 1;
            } catch (AccessDeniedException e3) {
                AccountLog.e(TAG, "AccessDeniedException", e3);
                i2 = 4;
            } catch (InvalidResponseException e4) {
                AccountLog.e(TAG, "InvalidResponseException", e4);
                i2 = 3;
            } catch (CipherException e5) {
                AccountLog.e(TAG, "CipherException", e5);
                i2 = 3;
            } catch (InvalidBindAddressException e6) {
                AccountLog.e(TAG, "InvalidBindAddressException", e6);
                i2 = 9;
            } catch (NeedCaptchaException e7) {
                AccountLog.e(TAG, "NeedCaptchaException", e7);
                str = e7.getCaptchaUrl();
                i2 = 12;
            } catch (UsedEmailAddressException e8) {
                AccountLog.e(TAG, "UsedEmailAddressException", e8);
                i2 = 8;
            } catch (ReachLimitException e9) {
                AccountLog.e(TAG, "ReachLimitException", e9);
                i2 = 13;
            }
        }
        return new EmailTaskResult(i2, str);
    }

    class EmailTaskResult {
        String captchaPath;
        int exceptionType;

        EmailTaskResult(int i, String str) {
            this.exceptionType = i;
            this.captchaPath = str;
        }
    }
}
