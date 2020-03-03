package com.payu.custombrowser;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.coloros.mcssdk.PushManager;
import com.payu.custombrowser.bean.CustomBrowserConfig;
import com.payu.custombrowser.bean.CustomBrowserData;
import com.payu.custombrowser.util.CBAnalyticsConstant;
import com.payu.custombrowser.util.CBConstant;
import com.payu.custombrowser.util.CBUtil;
import com.payu.magicretry.MagicRetryFragment;
import org.json.JSONException;

public class CBActivity extends FragmentActivity implements MagicRetryFragment.ActivityCallback {
    public static int STATE;
    private AlertDialog backButtonAlertDialog;
    CBUtil cbUtil;
    CustomBrowserConfig customBrowserConfig;
    private Bank payUCustomBrowser;
    private android.support.v7.app.AlertDialog snoozeDialog;

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        STATE = 1;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        STATE = 2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate((Bundle) null);
        setContentView(R.layout.cb_payments);
        this.payUCustomBrowser = new Bank();
        this.cbUtil = new CBUtil();
        this.cbUtil.resetPayuID();
        Bundle bundle2 = new Bundle();
        this.customBrowserConfig = (CustomBrowserConfig) getIntent().getParcelableExtra(CBConstant.CB_CONFIG);
        bundle2.putParcelable(CBConstant.CB_CONFIG, this.customBrowserConfig);
        this.payUCustomBrowser.setArguments(bundle2);
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, (Fragment) this.payUCustomBrowser).commit();
    }

    public void onBackPressed() {
        if (this.customBrowserConfig == null || this.customBrowserConfig.getDisableBackButtonDialog() == 1) {
            finish();
            return;
        }
        this.payUCustomBrowser.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, CBAnalyticsConstant.PAYU_BACK_BUTTON_CLICK.toLowerCase());
        this.payUCustomBrowser.showBackButtonDialog();
    }

    public void showMagicRetry() {
        this.payUCustomBrowser.showMagicRetry();
    }

    public void hideMagicRetry() {
        this.payUCustomBrowser.hideMagicRetry();
    }

    public void onDestroy() {
        if (this.backButtonAlertDialog != null && this.backButtonAlertDialog.isShowing()) {
            this.backButtonAlertDialog.dismiss();
            this.backButtonAlertDialog.cancel();
        }
        if (this.snoozeDialog != null && this.snoozeDialog.isShowing()) {
            this.snoozeDialog.dismiss();
            this.snoozeDialog.cancel();
        }
        STATE = 3;
        if (!(this.payUCustomBrowser == null || this.payUCustomBrowser.getSnoozeLoaderView() == null)) {
            this.payUCustomBrowser.getSnoozeLoaderView().cancelAnimation();
        }
        if (CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback() != null) {
            CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback().onPaymentTerminate();
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        notificationManager.cancel(CBConstant.SNOOZE_NOTIFICATION_ID);
        notificationManager.cancel(63);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null && intent.getStringExtra(CBConstant.SENDER).contentEquals(CBConstant.SNOOZE_SERVICE) && this.payUCustomBrowser != null) {
            this.payUCustomBrowser.killSnoozeService();
            this.payUCustomBrowser.dismissSnoozeWindow();
            this.payUCustomBrowser.snoozeNotificationIntent = null;
            this.payUCustomBrowser.isSnoozeNotificationLaunched = false;
            if (intent.getExtras().getBoolean(CBConstant.VERIFICATION_MSG_RECEIVED)) {
                try {
                    if (this.cbUtil.getValueOfJSONKey(intent.getExtras().getString(CBConstant.PAYU_RESPONSE), getString(R.string.cb_snooze_verify_api_status)).equalsIgnoreCase("1")) {
                        this.payUCustomBrowser.addEventAnalytics(CBAnalyticsConstant.TRANSACTION_VERIFIED_NOTIFICATION_CLICK, "-1");
                    } else {
                        this.payUCustomBrowser.addEventAnalytics(CBAnalyticsConstant.TRANSACTION_NOT_VERIFIED_NOTIFICATION_CLICK, "-1");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.payUCustomBrowser.showTransactionStatusDialog(intent.getExtras().getString(CBConstant.PAYU_RESPONSE), true);
                return;
            }
            this.payUCustomBrowser.addEventAnalytics(CBAnalyticsConstant.INTERNET_RESTORED_NOTIFICATION_CLICK, "-1");
            this.payUCustomBrowser.resumeTransaction(intent);
        }
    }
}
