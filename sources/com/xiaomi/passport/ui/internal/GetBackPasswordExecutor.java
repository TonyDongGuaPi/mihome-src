package com.xiaomi.passport.ui.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;
import com.google.android.exoplayer2.C;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.accountsdk.utils.XMPassportUtil;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.XiaomiPassportExecutor;
import java.util.Locale;

public class GetBackPasswordExecutor {
    private static final String TAG = "GetBackPasswordExecutor";
    /* access modifiers changed from: private */
    public static GetBackPasswordTask sGetBackPasswordTask;

    public static void execute(Activity activity) {
        if (activity != null) {
            if (sGetBackPasswordTask == null || AsyncTask.Status.FINISHED == sGetBackPasswordTask.getStatus()) {
                sGetBackPasswordTask = new GetBackPasswordTask(activity);
                sGetBackPasswordTask.executeOnExecutor(XiaomiPassportExecutor.getSingleton(), new Void[0]);
            }
        }
    }

    public static void execute(Activity activity, Runnable runnable) {
        if (activity != null) {
            if (sGetBackPasswordTask == null || AsyncTask.Status.FINISHED == sGetBackPasswordTask.getStatus()) {
                sGetBackPasswordTask = new GetBackPasswordTask(activity, runnable);
                sGetBackPasswordTask.executeOnExecutor(XiaomiPassportExecutor.getSingleton(), new Void[0]);
            }
        }
    }

    public static void stopIfNeeded() {
        if (sGetBackPasswordTask != null) {
            sGetBackPasswordTask.cancel(true);
            sGetBackPasswordTask = null;
        }
    }

    private static class GetBackPasswordTask extends AsyncTask<Void, Void, Intent> {
        private Activity mActivity;
        private Runnable mPostRunnable;

        public GetBackPasswordTask(Activity activity) {
            this.mActivity = activity;
        }

        public GetBackPasswordTask(Activity activity, Runnable runnable) {
            this(activity);
            this.mPostRunnable = runnable;
        }

        /* access modifiers changed from: protected */
        public Intent doInBackground(Void... voidArr) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(getLocalizedPasswordRecoveryUri(this.mActivity.getResources().getConfiguration().locale, AccountHelper.getHashedDeviceId()));
            intent.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            return intent;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Intent intent) {
            if (this.mActivity != null && !this.mActivity.isFinishing()) {
                try {
                    this.mActivity.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    AccountLog.e(GetBackPasswordExecutor.TAG, "cannot find browser");
                    Toast.makeText(this.mActivity, "Cannot find the Browser App", 1).show();
                }
            }
            this.mActivity = null;
            GetBackPasswordTask unused2 = GetBackPasswordExecutor.sGetBackPasswordTask = null;
            if (this.mPostRunnable != null) {
                this.mPostRunnable.run();
            }
        }

        private static Uri getLocalizedPasswordRecoveryUri(Locale locale, String str) {
            Uri.Builder buildUpon = Uri.parse(com.xiaomi.passport.ui.internal.util.Constants.PASSWORD_RECOVERY_URL).buildUpon();
            if (str != null) {
                buildUpon.appendQueryParameter("hint", str);
            }
            String iSOLocaleString = XMPassportUtil.getISOLocaleString(locale);
            if (iSOLocaleString != null) {
                buildUpon.appendQueryParameter("_locale", iSOLocaleString);
            }
            return buildUpon.build();
        }
    }
}
