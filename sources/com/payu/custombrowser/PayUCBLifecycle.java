package com.payu.custombrowser;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsMessage;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.alipay.sdk.sys.a;
import com.mibi.common.data.CommonConstants;
import com.payu.custombrowser.CustomBrowserMain;
import com.payu.custombrowser.bean.CustomBrowserConfig;
import com.payu.custombrowser.bean.CustomBrowserData;
import com.payu.custombrowser.cbinterface.MagicRetryCallbacks;
import com.payu.custombrowser.custombar.CustomProgressBar;
import com.payu.custombrowser.services.SnoozeService;
import com.payu.custombrowser.util.CBAnalyticsConstant;
import com.payu.custombrowser.util.CBConstant;
import com.payu.custombrowser.util.CBUtil;
import com.payu.custombrowser.util.L;
import com.payu.custombrowser.util.SharedPreferenceUtil;
import com.payu.custombrowser.util.SnoozeConfigMap;
import com.payu.custombrowser.widgets.SnoozeLoaderView;
import com.payu.custombrowser.widgets.SurePayCancelAsyncTaskHelper;
import com.taobao.weex.ui.module.WXModalUIModule;
import com.xiaomi.payment.data.MibiConstants;
import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import net.qiujuer.genius.ui.Ui;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public abstract class PayUCBLifecycle extends CustomBrowserMain implements MagicRetryCallbacks {
    private static boolean isFromSnoozeService;
    public static int snoozeImageDownloadTimeout;
    protected static List<String> whiteListedUrls = new ArrayList();
    boolean SMSOTPClicked = false;
    /* access modifiers changed from: private */
    public String SNOOZE_BROAD_CAST_MESSAGE = "snooze_broad_cast_message";
    protected String SNOOZE_GET_WEBVIEW_STATUS_INTENT_ACTION = "webview_status_action";
    Boolean approve_flag = false;
    View cbBlankOverlay;
    boolean checkLoading;
    boolean checkPermissionVisibility;
    boolean checkedPermission = false;
    int chooseActionHeight;
    CustomProgressBar customProgressBar;
    String enterOtpParams;
    protected boolean isSnoozeBroadCastReceiverRegistered = false;
    protected boolean isSnoozeEnabled = true;
    boolean isSnoozeNotificationLaunched;
    protected boolean isSnoozeServiceBounded = false;
    int mInternetRestoredWindowTTL = 0;
    boolean mPageReady = false;
    String mPassword;
    boolean permissionGranted = true;
    boolean pin_selected_flag;
    boolean showCB = true;
    protected CountDownTimer slowUserCountDownTimer;
    protected AlertDialog slowUserWarningDialog;
    protected BroadcastReceiver snoozeBroadCastReceiver;
    SnoozeConfigMap snoozeConfigMap;
    protected int snoozeCount = 0;
    protected int snoozeCountBackwardJourney = 0;
    int[] snoozeLoadPercentageAndTimeOut;
    Intent snoozeNotificationIntent;
    protected SnoozeService snoozeService;
    protected ServiceConnection snoozeServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PayUCBLifecycle.this.snoozeService = ((SnoozeService.SnoozeBinder) iBinder).getSnoozeService();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            PayUCBLifecycle.this.snoozeService = null;
        }
    };
    protected int snoozeUrlLoadingPercentage;
    protected int snoozeUrlLoadingTimeout;
    protected int snoozeVisibleCountBackwdJourney;
    protected int snoozeVisibleCountFwdJourney;
    int surePayDisableStatus = 0;
    Timer waitingOTPTimer;

    /* access modifiers changed from: package-private */
    public abstract void dismissSlowUserWarningTimer();

    /* access modifiers changed from: package-private */
    public abstract void enter_otp(String str);

    /* access modifiers changed from: package-private */
    public abstract void onPageStarted();

    /* access modifiers changed from: package-private */
    public abstract void reloadWebView();

    /* access modifiers changed from: package-private */
    public abstract void reloadWebView(String str);

    /* access modifiers changed from: package-private */
    public abstract void reloadWebView(String str, String str2);

    /* access modifiers changed from: package-private */
    public abstract void startSlowUserWarningTimer();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.activity = getActivity();
        this.cbUtil.resetPayuID();
        this.isSnoozeEnabled = this.cbUtil.getBooleanSharedPreferenceDefaultTrue(CBConstant.SNOOZE_ENABLED, getActivity().getApplicationContext());
        isFromSnoozeService = false;
        this.snoozeConfigMap = this.cbUtil.convertToSnoozeConfigMap(SharedPreferenceUtil.getSharedPrefMap(this.activity, CBConstant.SNOOZE_SHARED_PREF));
        this.snoozeLoadPercentageAndTimeOut = this.snoozeConfigMap.getPercentageAndTimeout("*");
        this.snoozeUrlLoadingPercentage = this.snoozeLoadPercentageAndTimeOut[0];
        this.snoozeUrlLoadingTimeout = this.snoozeLoadPercentageAndTimeOut[1];
        this.surePayDisableStatus = this.cbUtil.getSurePayDisableStatus(this.snoozeConfigMap, "*");
        whiteListedUrls = CBUtil.processAndAddWhiteListedUrls(SharedPreferenceUtil.getStringFromSharedPreference(this.activity, CBConstant.SP_RETRY_FILE_NAME, CBConstant.SP_RETRY_WHITELISTED_URLS, ""));
        snoozeImageDownloadTimeout = SharedPreferenceUtil.getIntFromSharedPreference(this.activity.getApplicationContext(), CBUtil.CB_PREFERENCE, CBConstant.SNOOZE_IMAGE_DOWNLOAD_TIME_OUT, 0);
        if (this.snoozeService != null) {
            this.snoozeService.killSnoozeService();
        }
        if (this.activity.getIntent().getStringExtra(CBConstant.SENDER) != null && this.activity.getIntent().getStringExtra(CBConstant.SENDER).contentEquals(CBConstant.SNOOZE_SERVICE)) {
            isFromSnoozeService = true;
        }
        this.snoozeBroadCastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent.hasExtra("broadcaststatus")) {
                    Intent intent2 = new Intent(PayUCBLifecycle.this.activity, CBActivity.class);
                    intent2.putExtra(CBConstant.SENDER, CBConstant.SNOOZE_SERVICE);
                    intent2.putExtra(CBConstant.VERIFICATION_MSG_RECEIVED, true);
                    intent2.putExtra(CBConstant.PAYU_RESPONSE, intent.getExtras().getString(CBConstant.PAYU_RESPONSE));
                    intent2.setFlags(Ui.b);
                    PayUCBLifecycle.this.startActivity(intent2);
                }
                if (intent.hasExtra(PayUCBLifecycle.this.SNOOZE_BROAD_CAST_MESSAGE) && PayUCBLifecycle.this.snoozeService != null) {
                    PayUCBLifecycle.this.snoozeService.updateWebviewStatus(intent.getStringExtra(PayUCBLifecycle.this.SNOOZE_BROAD_CAST_MESSAGE));
                }
                if (intent.getBooleanExtra(CBAnalyticsConstant.BROAD_CAST_FROM_SNOOZE_SERVICE, false)) {
                    PayUCBLifecycle.this.addEventAnalytics(intent.getStringExtra(CBAnalyticsConstant.KEY), intent.getStringExtra(CBAnalyticsConstant.VALUE));
                }
                if (intent.hasExtra(CBConstant.SNOOZE_SERVICE_STATUS)) {
                    PayUCBLifecycle.this.isSnoozeNotificationLaunched = true;
                    if (CBActivity.STATE != 2) {
                        int i = CBActivity.STATE;
                    }
                    PayUCBLifecycle.this.updateSnoozeDialogWithSnoozeServiceStatus();
                }
                if (intent.getBooleanExtra(CBConstant.BROADCAST_FROM_SERVICE_UPDATE_UI, false) && intent.hasExtra(CBConstant.IS_FORWARD_JOURNEY)) {
                    if (intent.getStringExtra("key").contentEquals(CBConstant.GOOD_NETWORK_NOTIFICATION_LAUNCHED)) {
                        PayUCBLifecycle.this.isSnoozeNotificationLaunched = true;
                        PayUCBLifecycle.this.snoozeNotificationIntent = intent;
                        return;
                    }
                    PayUCBLifecycle.this.isSnoozeNotificationLaunched = false;
                    PayUCBLifecycle.this.killSnoozeWindowAndContinuePayment(intent);
                }
            }
        };
        if (!this.activity.getClass().getSimpleName().equalsIgnoreCase("CBActivity")) {
            this.cbOldFlow = true;
            cbOldOnCreate();
        } else {
            cbOnCreate();
        }
        initAnalytics(Bank.keyAnalytics);
        this.pin_selected_flag = false;
        if (this.activity != null) {
            this.cbUtil.clearCookie();
        }
        if (this.customBrowserConfig != null) {
            addEventAnalytics(CBAnalyticsConstant.SNOOZE_ENABLE_COUNT, "" + this.customBrowserConfig.getEnableSurePay());
            addEventAnalytics(CBAnalyticsConstant.SNOOZE_MODE_SET_MERCHANT, this.customBrowserConfig.getSurePayMode() == 1 ? "WARN" : CommonConstants.Mgc.ag);
        }
    }

    /* access modifiers changed from: private */
    public void updateSnoozeDialogWithSnoozeServiceStatus() {
        if (this.snoozeDialog != null && this.snoozeDialog.isShowing()) {
            this.snoozeDialog.cancel();
            this.snoozeDialog.dismiss();
        }
        View inflate = this.activity.getLayoutInflater().inflate(R.layout.cb_layout_snooze, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.snooze_header_txt)).setText(getString(R.string.cb_snooze_network_error));
        inflate.findViewById(R.id.text_view_cancel_snooze_window).setVisibility(8);
        ((TextView) inflate.findViewById(R.id.text_view_snooze_message)).setText(getString(R.string.cb_snooze_network_down_message));
        inflate.findViewById(R.id.snooze_loader_view).setVisibility(8);
        inflate.findViewById(R.id.button_snooze_transaction).setVisibility(8);
        inflate.findViewById(R.id.text_view_retry_message_detail).setVisibility(8);
        inflate.findViewById(R.id.button_retry_transaction).setVisibility(8);
        inflate.findViewById(R.id.button_cancel_transaction).setVisibility(8);
        inflate.findViewById(R.id.t_confirm).setVisibility(8);
        inflate.findViewById(R.id.t_nconfirm).setVisibility(8);
        Button button = (Button) inflate.findViewById(R.id.button_go_back_snooze);
        button.setVisibility(0);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PayUCBLifecycle.this.activity.finish();
            }
        });
        this.snoozeDialog = new AlertDialog.Builder(this.activity).create();
        this.snoozeDialog.setView(inflate);
        this.snoozeDialog.setCanceledOnTouchOutside(false);
        this.snoozeDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                PayUCBLifecycle.this.activity.finish();
            }
        });
        this.snoozeDialog.show();
    }

    public void cbOldOnCreate() {
        this.bundle = getArguments();
        this.autoApprove = this.bundle.getBoolean("auto_approve", false);
        this.autoSelectOtp = this.bundle.getBoolean(CBConstant.AUTO_SELECT_OTP, false);
        this.storeOneClickHash = this.bundle.getInt(CBConstant.STORE_ONE_CLICK_HASH, 0);
        this.merchantSMSPermission = this.bundle.getBoolean(CBConstant.MERCHANT_SMS_PERMISSION, false);
        if (Bank.sdkVersion == null || Bank.sdkVersion.equalsIgnoreCase("")) {
            Bank.sdkVersion = getArguments().getString(CBConstant.SDK_DETAILS);
        }
        if (Bank.transactionID == null || Bank.transactionID.equalsIgnoreCase("")) {
            Bank.transactionID = getArguments().getString("txnid");
        }
        if (Bank.keyAnalytics == null || Bank.keyAnalytics.equalsIgnoreCase("")) {
            Bank.keyAnalytics = getArguments().getString("merchantid");
        }
    }

    public void cbOnCreate() {
        if (getArguments() != null && getArguments().containsKey(CBConstant.CB_CONFIG)) {
            this.customBrowserConfig = (CustomBrowserConfig) getArguments().getParcelable(CBConstant.CB_CONFIG);
            int i = 0;
            boolean z = true;
            this.merchantSMSPermission = this.customBrowserConfig != null && this.customBrowserConfig.getMerchantSMSPermission() == 1;
            this.autoApprove = this.customBrowserConfig != null && this.customBrowserConfig.getAutoApprove() == 1;
            if (this.customBrowserConfig == null || this.customBrowserConfig.getAutoSelectOTP() != 1) {
                z = false;
            }
            this.autoSelectOtp = z;
            if (this.customBrowserConfig != null) {
                i = this.customBrowserConfig.getStoreOneClickHash();
            }
            this.storeOneClickHash = i;
            if (this.customBrowserConfig != null) {
                this.customBrowserConfig.getPostURL();
            }
            if (this.customBrowserConfig != null) {
                if (Bank.keyAnalytics == null || Bank.keyAnalytics.trim().equals("")) {
                    if (this.customBrowserConfig.getMerchantKey() != null || !this.customBrowserConfig.getMerchantKey().trim().equals("")) {
                        Bank.keyAnalytics = this.customBrowserConfig.getMerchantKey();
                    } else {
                        Bank.keyAnalytics = "";
                    }
                }
                if (Bank.transactionID == null || Bank.transactionID.trim().equals("")) {
                    if (this.customBrowserConfig.getTransactionID() == null || this.customBrowserConfig.getTransactionID().trim().equals("")) {
                        Bank.transactionID = "123";
                    } else {
                        Bank.transactionID = this.customBrowserConfig.getTransactionID();
                    }
                }
                if (Bank.sdkVersion != null && !Bank.sdkVersion.trim().equals("")) {
                    return;
                }
                if (this.customBrowserConfig.getSdkVersionName() == null || this.customBrowserConfig.getSdkVersionName().trim().equals("")) {
                    Bank.sdkVersion = "";
                } else {
                    Bank.sdkVersion = this.customBrowserConfig.getSdkVersionName();
                }
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view;
        View view2;
        super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.cbOldFlow) {
            view2 = layoutInflater.inflate(R.layout.bankold, viewGroup, false);
            view2.bringToFront();
            cbOldFlowOnCreateView();
            view = view2;
        } else {
            view2 = layoutInflater.inflate(R.layout.bank, viewGroup, false);
            this.cbTransparentView = view2.findViewById(R.id.trans_overlay);
            this.cbWebView = (WebView) view2.findViewById(R.id.webview);
            this.cbBlankOverlay = view2.findViewById(R.id.cb_blank_overlay);
            view = view2.findViewById(R.id.parent);
            cbOnCreateView();
        }
        CBUtil.setVariableReflection(CBConstant.MAGIC_RETRY_PAKAGE, BuildConfig.VERSION_NAME, CBConstant.CB_VERSION);
        this.cbBaseView = (FrameLayout) view2.findViewById(R.id.help_view);
        this.cbSlideBarView = view2.findViewById(R.id.view);
        this.cbWebPageProgressBar = (ProgressBar) view2.findViewById(R.id.cb_progressbar);
        initCBSettings();
        this.viewOnClickListener = new CustomBrowserMain.ViewOnClickListener();
        getInitializeJS();
        view.setOnTouchListener(new PayUCBLifeCycleTouchListener());
        return view2;
    }

    private void initCBSettings() {
        this.cbWebView.getSettings().setJavaScriptEnabled(true);
        this.cbWebView.addJavascriptInterface(this, "PayU");
        this.cbWebView.getSettings().setSupportMultipleWindows(true);
        this.cbWebView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                PayUCBLifecycle.this.dismissSlowUserWarningTimerOnTouch();
                if (PayUCBLifecycle.this.cbTransparentView != null) {
                    PayUCBLifecycle.this.cbTransparentView.setVisibility(8);
                }
                if (PayUCBLifecycle.this.frameState != 2) {
                    return false;
                }
                PayUCBLifecycle.this.minimizeWebviewHeight();
                return false;
            }
        });
        this.cbWebView.getSettings().setDomStorageEnabled(true);
        this.cbWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        this.cbWebView.getSettings().setCacheMode(2);
        this.cbWebView.getSettings().setAppCacheEnabled(false);
    }

    public void cbOldFlowOnCreateView() {
        this.cbWebView = (WebView) this.activity.findViewById(getArguments().getInt(CBConstant.WEBVIEW));
        if (Bank.paymentMode != null && Bank.paymentMode.equalsIgnoreCase(CBConstant.NB)) {
            this.cbWebView.getSettings().setUseWideViewPort(true);
        } else if (this.customBrowserConfig != null && this.customBrowserConfig.getViewPortWideEnable() == 1) {
            this.cbWebView.getSettings().setUseWideViewPort(true);
        }
        this.cbWebView.setFocusable(true);
        if (getArguments().getBoolean("backButton", true)) {
            this.cbWebView.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == 1 && i == 4) {
                        if (PayUCBLifecycle.this.getArguments().getBoolean("backButton", true)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(PayUCBLifecycle.this.activity);
                            builder.setCancelable(false);
                            builder.setMessage("Do you really want to cancel the transaction ?");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    PayUCBLifecycle.this.postToPaytxn();
                                    PayUCBLifecycle.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, CBAnalyticsConstant.BACK_BUTTON_OK_CLICK);
                                    dialogInterface.dismiss();
                                    PayUCBLifecycle.this.onBackApproved();
                                    PayUCBLifecycle.this.activity.finish();
                                }
                            });
                            builder.setNegativeButton(WXModalUIModule.CANCEL, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    PayUCBLifecycle.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, CBAnalyticsConstant.BACK_BUTTON_CANCEL_CLICK);
                                    PayUCBLifecycle.this.onBackCancelled();
                                    dialogInterface.dismiss();
                                }
                            });
                            PayUCBLifecycle.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, CBAnalyticsConstant.PAYU_BACK_BUTTON_CLICK);
                            PayUCBLifecycle.this.onBackPressed(builder);
                            builder.show();
                            return true;
                        }
                        PayUCBLifecycle.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, CBAnalyticsConstant.MERCHANT_BACK_BUTTON_CLICK);
                        PayUCBLifecycle.this.activity.onBackPressed();
                    }
                    return false;
                }
            });
        }
        if (Bank.paymentMode != null && Bank.paymentMode.equalsIgnoreCase(CBConstant.NB)) {
            this.cbWebView.getSettings().setUseWideViewPort(true);
        } else if (this.bundle.getBoolean(CBConstant.VIEWPORTWIDE, false)) {
            this.cbWebView.getSettings().setUseWideViewPort(true);
        }
    }

    public void cbOnCreateView() {
        if (Bank.paymentMode != null && Bank.paymentMode.equalsIgnoreCase(CBConstant.NB)) {
            this.cbWebView.getSettings().setUseWideViewPort(true);
        } else if (this.customBrowserConfig != null && this.customBrowserConfig.getViewPortWideEnable() == 1) {
            this.cbWebView.getSettings().setUseWideViewPort(true);
        }
        Bank bank = (Bank) this;
        this.cbWebView.setWebChromeClient(new PayUWebChromeClient(bank));
        if (this.customBrowserConfig.getEnableSurePay() > 0) {
            this.cbWebView.setWebViewClient(new PayUSurePayWebViewClient(bank, Bank.keyAnalytics));
        } else {
            this.cbWebView.setWebViewClient(new PayUWebViewClient(bank, Bank.keyAnalytics));
        }
        if (!(this.customBrowserConfig == null || this.customBrowserConfig.getPostURL() == null || this.customBrowserConfig.getPayuPostData() == null)) {
            this.cbWebView.postUrl(this.customBrowserConfig.getPostURL(), this.customBrowserConfig.getPayuPostData().getBytes());
        }
        if (CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback() != null) {
            CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback().setCBProperties(this.cbWebView, bank);
        }
        if (this.customBrowserConfig != null && this.customBrowserConfig.getMagicretry() == 1) {
            if (this.customBrowserConfig.getEnableSurePay() == 0) {
                initMagicRetry();
            }
            if (CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback() != null) {
                CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback().initializeMagicRetry(bank, this.cbWebView, this.magicRetryFragment);
            }
        }
    }

    public void logOnTerminate() {
        try {
            addEventAnalytics(CBAnalyticsConstant.LAST_URL, CBUtil.updateLastUrl(this.cbUtil.getStringSharedPreference(this.activity.getApplicationContext(), CBAnalyticsConstant.LAST_URL)));
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.cbUtil.deleteSharedPrefKey(this.activity.getApplicationContext(), CBAnalyticsConstant.LAST_URL);
            throw th;
        }
        this.cbUtil.deleteSharedPrefKey(this.activity.getApplicationContext(), CBAnalyticsConstant.LAST_URL);
        if (!this.eventArray.contains(CBAnalyticsConstant.CUSTOM_BROWSER)) {
            this.eventRecorded = CBAnalyticsConstant.NON_CUSTOM_BROWSER;
            addEventAnalytics(CBAnalyticsConstant.CB_STATUS, this.eventRecorded);
        }
        this.eventRecorded = CBAnalyticsConstant.TERMINATE_TRANSACTION;
        addEventAnalytics(CBAnalyticsConstant.USER_INPUT, this.eventRecorded);
        if (this.progressDialog != null && !this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
        if (this.mBroadcastReceiver != null) {
            unregisterBroadcast(this.mBroadcastReceiver);
            this.mBroadcastReceiver = null;
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.cbUtil.cancelTimer(this.timerProgress);
        if (this.snoozeDialog != null && this.snoozeDialog.isShowing()) {
            this.snoozeDialog.dismiss();
        }
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacks(this.mResetCounter);
        this.cbUtil.cancelTimer(this.timerProgress);
        this.cbUtil.cancelTimer(this.waitingOTPTimer);
        if (this.slowUserCountDownTimer != null) {
            this.slowUserCountDownTimer.cancel();
        }
        addEventAnalytics(CBAnalyticsConstant.SNOOZE_COUNT, "" + (this.snoozeVisibleCountBackwdJourney + this.snoozeVisibleCountFwdJourney));
        CustomBrowserData.SINGLETON.setPayuCustomBrowserCallback((PayUCustomBrowserCallback) null);
        if (this.snoozeDialog != null && this.snoozeDialog.isShowing()) {
            this.snoozeDialog.dismiss();
        }
        if (this.snoozeBroadCastReceiver != null && this.isSnoozeBroadCastReceiverRegistered && !isFromSnoozeService) {
            LocalBroadcastManager.getInstance(this.activity.getApplicationContext()).unregisterReceiver(this.snoozeBroadCastReceiver);
        }
        if (this.snoozeServiceConnection != null && this.isSnoozeServiceBounded) {
            this.activity.unbindService(this.snoozeServiceConnection);
        }
        if (this.snoozeService != null && isFromSnoozeService) {
            this.snoozeService.killSnoozeService();
        }
        if (this.loadingLayout != null) {
            this.customProgressBar.removeProgressDialog(this.loadingLayout.findViewById(R.id.progress));
        }
        if (this.enterOTPView != null) {
            this.customProgressBar.removeProgressDialog(this.enterOTPView.findViewById(R.id.progress));
        }
        if (this.payuDeviceAnalytics != null) {
            this.cbUtil.cancelTimer(this.payuDeviceAnalytics.getmTimer());
        }
        if (this.mAnalytics != null) {
            this.cbUtil.cancelTimer(this.mAnalytics.getmTimer());
        }
        this.cbUtil.cancelTimer(this.waitingOTPTimer);
        if (this.countDownTimer != null) {
            this.countDownTimer.cancel();
        }
        logOnTerminate();
        Bank.sdkVersion = null;
        Bank.keyAnalytics = null;
        Bank.transactionID = null;
        Bank.paymentMode = null;
        this.cbUtil.resetPayuID();
    }

    public void onResume() {
        super.onResume();
        if (this.isSnoozeNotificationLaunched) {
            this.isSnoozeNotificationLaunched = false;
            cancelTransactionNotification();
            if (this.snoozeNotificationIntent != null) {
                if (this.backwardJourneyStarted) {
                    try {
                        if (Integer.parseInt(new JSONObject(this.snoozeNotificationIntent.getStringExtra("value")).get(getString(R.string.cb_snooze_verify_api_status)).toString()) == 1) {
                            addEventAnalytics(CBAnalyticsConstant.TRANSACTION_VERIFIED_DIALOG_RECENT_APP, "-1");
                        } else {
                            addEventAnalytics(CBAnalyticsConstant.TRANSACTION_NOT_VERIFIED_DIALOG_RECENT_APP, "-1");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        addEventAnalytics(CBAnalyticsConstant.TRANSACTION_NOT_VERIFIED_DIALOG_RECENT_APP, "-1");
                    }
                } else {
                    addEventAnalytics(CBAnalyticsConstant.INTERNET_RESTORED_DIALOG_RECENT_APP, "-1");
                }
                killSnoozeWindowAndContinuePayment(this.snoozeNotificationIntent);
                return;
            }
            addEventAnalytics(CBAnalyticsConstant.INTERNET_NOT_RESTORED_DIALOG_RECENT_APP, "-1");
        }
    }

    /* access modifiers changed from: package-private */
    public void prepareSmsListener() {
        if (this.mBroadcastReceiver == null) {
            this.mBroadcastReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    Bundle extras;
                    String str;
                    try {
                        if (PayUCBLifecycle.this.mBankJS != null) {
                            Bundle extras2 = intent.getExtras();
                            if (PayUCBLifecycle.this.getActivity() != null && (extras = intent.getExtras()) != null) {
                                Object[] objArr = (Object[]) extras.get("pdus");
                                String str2 = null;
                                if (objArr != null) {
                                    SmsMessage[] smsMessageArr = new SmsMessage[objArr.length];
                                    str = null;
                                    for (int i = 0; i < smsMessageArr.length; i++) {
                                        if (Build.VERSION.SDK_INT >= 23) {
                                            smsMessageArr[i] = SmsMessage.createFromPdu((byte[]) objArr[i], extras2.getString(IjkMediaMeta.IJKM_KEY_FORMAT));
                                        } else {
                                            smsMessageArr[i] = SmsMessage.createFromPdu((byte[]) objArr[i]);
                                        }
                                        str2 = str2 + smsMessageArr[i].getMessageBody();
                                        str = smsMessageArr[i].getDisplayOriginatingAddress();
                                    }
                                } else {
                                    str = null;
                                }
                                PayUCBLifecycle.this.mPassword = CBUtil.filterSMS(PayUCBLifecycle.this.mBankJS, str2, PayUCBLifecycle.this.activity.getApplicationContext());
                                if (PayUCBLifecycle.this.mPassword != null) {
                                    PayUCBLifecycle.this.fillOTP(this);
                                    return;
                                }
                                if (PayUCBLifecycle.this.payuPG) {
                                    PayUCBLifecycle.this.verificationMsgReceived = PayUCBLifecycle.this.checkConfirmMessage(str, str2);
                                }
                                if (PayUCBLifecycle.this.verificationMsgReceived) {
                                    PayUCBLifecycle.this.addEventAnalytics(CBAnalyticsConstant.SNOOZE_BACKWARD_WINDOW_SMS_RECEIVED, CBAnalyticsConstant.RECEIVED);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.setPriority(9999999);
            intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
            registerBroadcast(this.mBroadcastReceiver, intentFilter);
        }
    }

    public void registerSMSBroadcast() {
        if (this.mBroadcastReceiver == null) {
            prepareSmsListener();
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(9999999);
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerBroadcast(this.mBroadcastReceiver, intentFilter);
    }

    /* access modifiers changed from: package-private */
    public String getValueFromPostData(String str) {
        for (String split : this.customBrowserConfig.getPayuPostData().split(a.b)) {
            String[] split2 = split.split("=");
            if (split2.length >= 2 && split2[0].equalsIgnoreCase(str)) {
                return split2[1];
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public boolean checkConfirmMessage(String str, String str2) {
        String lowerCase = str2.toLowerCase();
        boolean z = false;
        int i = str.contains(this.bankName) ? 1 : 0;
        if (lowerCase.toLowerCase().contains(getValueFromPostData("amount").replace(",", ""))) {
            i++;
        }
        boolean z2 = i == 2;
        if (i == 0) {
            z2 = false;
        }
        if (i == 0) {
            return false;
        }
        if (lowerCase.contains("made") && lowerCase.contains("purchase")) {
            return true;
        }
        if (lowerCase.contains("account") && lowerCase.contains("debited")) {
            return true;
        }
        if (lowerCase.contains("ac") && lowerCase.contains("debited")) {
            return true;
        }
        if (lowerCase.contains("tranx") && lowerCase.contains("made")) {
            return true;
        }
        if ((lowerCase.contains("transaction") && lowerCase.contains("made")) || lowerCase.contains("spent") || lowerCase.contains("Thank you using card for")) {
            return true;
        }
        if (!lowerCase.contains(MibiConstants.dn) || !lowerCase.contains("initiated")) {
            return z2;
        }
        if (lowerCase.contains(MibiConstants.dn) && lowerCase.contains("initiated")) {
            z = true;
        }
        return z;
    }

    public void fillOTP(BroadcastReceiver broadcastReceiver) {
        if (getActivity().findViewById(R.id.otp_sms) != null) {
            final TextView textView = (TextView) getActivity().findViewById(R.id.otp_sms);
            if (this.showCB && this.mPassword != null && textView.getVisibility() != 0) {
                this.cbUtil.cancelTimer(this.waitingOTPTimer);
                String str = this.eventRecorded;
                char c = 65535;
                int hashCode = str.hashCode();
                if (hashCode != -557081102) {
                    if (hashCode != 674270068) {
                        if (hashCode == 2084916017 && str.equals(CBAnalyticsConstant.REGENERATE)) {
                            c = 2;
                        }
                    } else if (str.equals(CBAnalyticsConstant.OTP)) {
                        c = 1;
                    }
                } else if (str.equals(CBAnalyticsConstant.PAYMENT_INITIATED)) {
                    c = 0;
                }
                switch (c) {
                    case 0:
                        this.eventRecorded = CBAnalyticsConstant.RECEIVED_OTP_DIRECT;
                        break;
                    case 1:
                        this.eventRecorded = CBAnalyticsConstant.RECEIVED_OTP_SELECTED;
                        break;
                    case 2:
                        this.eventRecorded = CBAnalyticsConstant.RECEIVED_OTP_REGENERATE;
                        break;
                    default:
                        this.eventRecorded = CBAnalyticsConstant.OTP_WEB;
                        break;
                }
                addEventAnalytics(CBAnalyticsConstant.OTP_RECIEVED, this.eventRecorded);
                textView.setText(this.mPassword);
                this.mPassword = null;
                this.customProgressBar.removeDialog(getActivity().findViewById(R.id.progress));
                Button button = (Button) getActivity().findViewById(R.id.approve);
                button.setClickable(true);
                CBUtil.setAlpha(1.0f, button);
                button.setVisibility(0);
                this.activity.findViewById(R.id.timer).setVisibility(8);
                this.activity.findViewById(R.id.retry_text).setVisibility(8);
                this.activity.findViewById(R.id.regenerate_layout).setVisibility(8);
                this.activity.findViewById(R.id.waiting).setVisibility(8);
                this.activity.findViewById(R.id.otp_recieved).setVisibility(0);
                textView.setVisibility(0);
                if (this.autoApprove) {
                    button.performClick();
                    this.eventRecorded = "auto_approve";
                    addEventAnalytics(CBAnalyticsConstant.USER_INPUT, this.eventRecorded);
                }
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        try {
                            PayUCBLifecycle.this.mPassword = null;
                            PayUCBLifecycle.this.eventRecorded = CBAnalyticsConstant.APPROVED_OTP;
                            PayUCBLifecycle.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, PayUCBLifecycle.this.eventRecorded);
                            PayUCBLifecycle.this.addEventAnalytics(CBAnalyticsConstant.APPROVE_BTN_CLICK_TIME, "-1");
                            PayUCBLifecycle.this.prepareSmsListener();
                            PayUCBLifecycle.this.checkLoading = false;
                            PayUCBLifecycle.this.approve_flag = true;
                            PayUCBLifecycle.this.onHelpUnavailable();
                            PayUCBLifecycle.this.maximiseWebviewHeight();
                            PayUCBLifecycle.this.frameState = 1;
                            WebView webView = PayUCBLifecycle.this.cbWebView;
                            webView.loadUrl("javascript:" + PayUCBLifecycle.this.mJS.getString(PayUCBLifecycle.this.getString(R.string.cb_process_otp)) + "(\"" + textView.getText().toString() + "\")");
                            textView.setText("");
                            PayUCBLifecycle.this.hideSoftKeyboard();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                });
                if (this.mBroadcastReceiver != null) {
                    broadcastReceiver.abortBroadcast();
                    unregisterBroadcast(this.mBroadcastReceiver);
                    this.mBroadcastReceiver = null;
                }
            }
        }
    }

    private void getInitializeJS() {
        prepareSmsListener();
        this.eventRecorded = CBAnalyticsConstant.PAYMENT_INITIATED;
        addEventAnalytics(CBAnalyticsConstant.USER_INPUT, this.eventRecorded);
        this.serialExecutor.execute(new Runnable() {
            public void run() {
                final String str;
                final String str2;
                final String str3;
                final String str4;
                final String str5;
                final String str6;
                HttpURLConnection httpsConn = CBUtil.getHttpsConn(CBConstant.PRODUCTION_URL + "initialize" + ".js");
                if (httpsConn != null) {
                    try {
                        if (httpsConn.getResponseCode() == 200) {
                            PayUCBLifecycle.this.cbUtil.writeFileOutputStream(httpsConn.getInputStream(), PayUCBLifecycle.this.activity, "initialize", 0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            if (PayUCBLifecycle.this.activity != null) {
                                PayUCBLifecycle.this.mBankJS = new JSONObject(CBUtil.decodeContents(PayUCBLifecycle.this.activity.openFileInput("initialize")));
                                PayUCBLifecycle.this.setUrlString();
                                PayUCBLifecycle.this.checkVisibilityCB("", true);
                                if (PayUCBLifecycle.this.mBankJS.has("snooze_config")) {
                                    str3 = PayUCBLifecycle.this.mBankJS.get("snooze_config") + "('" + Bank.keyAnalytics + "')";
                                } else {
                                    str3 = "";
                                }
                                PayUCBLifecycle.snoozeImageDownloadTimeout = Integer.parseInt(PayUCBLifecycle.this.mBankJS.has("snooze_image_download_time") ? PayUCBLifecycle.this.mBankJS.get("snooze_image_download_time").toString() : "0");
                                SharedPreferenceUtil.addIntToSharedPreference(PayUCBLifecycle.this.activity.getApplicationContext(), CBUtil.CB_PREFERENCE, CBConstant.SNOOZE_IMAGE_DOWNLOAD_TIME_OUT, PayUCBLifecycle.snoozeImageDownloadTimeout);
                                if (PayUCBLifecycle.this.mBankJS.has(PayUCBLifecycle.this.getString(R.string.sp_internet_restored_ttl))) {
                                    str4 = PayUCBLifecycle.this.mBankJS.get(PayUCBLifecycle.this.getString(R.string.sp_internet_restored_ttl)) + "('" + Bank.keyAnalytics + "')";
                                } else {
                                    str4 = "";
                                }
                                PayUCBLifecycle.this.activity.runOnUiThread(new Runnable() {
                                    public void run() {
                                        WebView webView = PayUCBLifecycle.this.cbWebView;
                                        webView.loadUrl("javascript:" + str);
                                    }
                                });
                                PayUCBLifecycle.this.activity.runOnUiThread(new Runnable() {
                                    public void run() {
                                        WebView webView = PayUCBLifecycle.this.cbWebView;
                                        webView.loadUrl("javascript:" + str2);
                                    }
                                });
                                if (PayUCBLifecycle.this.mPageReady && PayUCBLifecycle.this.activity != null) {
                                    PayUCBLifecycle.this.activity.runOnUiThread(new Runnable() {
                                        public void run() {
                                            PayUCBLifecycle.this.onPageStarted();
                                        }
                                    });
                                    return;
                                }
                                return;
                            }
                            return;
                        } catch (FileNotFoundException | JSONException e2) {
                            PayUCBLifecycle.this.communicationError();
                            e2.printStackTrace();
                            return;
                        } catch (Exception e3) {
                            PayUCBLifecycle.this.communicationError();
                            e3.printStackTrace();
                            return;
                        }
                    } catch (Throwable th) {
                        try {
                            if (PayUCBLifecycle.this.activity != null) {
                                PayUCBLifecycle.this.mBankJS = new JSONObject(CBUtil.decodeContents(PayUCBLifecycle.this.activity.openFileInput("initialize")));
                                PayUCBLifecycle.this.setUrlString();
                                PayUCBLifecycle.this.checkVisibilityCB("", true);
                                if (PayUCBLifecycle.this.mBankJS.has("snooze_config")) {
                                    str5 = PayUCBLifecycle.this.mBankJS.get("snooze_config") + "('" + Bank.keyAnalytics + "')";
                                } else {
                                    str5 = "";
                                }
                                PayUCBLifecycle.snoozeImageDownloadTimeout = Integer.parseInt(PayUCBLifecycle.this.mBankJS.has("snooze_image_download_time") ? PayUCBLifecycle.this.mBankJS.get("snooze_image_download_time").toString() : "0");
                                SharedPreferenceUtil.addIntToSharedPreference(PayUCBLifecycle.this.activity.getApplicationContext(), CBUtil.CB_PREFERENCE, CBConstant.SNOOZE_IMAGE_DOWNLOAD_TIME_OUT, PayUCBLifecycle.snoozeImageDownloadTimeout);
                                if (PayUCBLifecycle.this.mBankJS.has(PayUCBLifecycle.this.getString(R.string.sp_internet_restored_ttl))) {
                                    str6 = PayUCBLifecycle.this.mBankJS.get(PayUCBLifecycle.this.getString(R.string.sp_internet_restored_ttl)) + "('" + Bank.keyAnalytics + "')";
                                } else {
                                    str6 = "";
                                }
                                PayUCBLifecycle.this.activity.runOnUiThread(new Runnable() {
                                    public void run() {
                                        WebView webView = PayUCBLifecycle.this.cbWebView;
                                        webView.loadUrl("javascript:" + str);
                                    }
                                });
                                PayUCBLifecycle.this.activity.runOnUiThread(new Runnable() {
                                    public void run() {
                                        WebView webView = PayUCBLifecycle.this.cbWebView;
                                        webView.loadUrl("javascript:" + str2);
                                    }
                                });
                                if (PayUCBLifecycle.this.mPageReady && PayUCBLifecycle.this.activity != null) {
                                    PayUCBLifecycle.this.activity.runOnUiThread(new Runnable() {
                                        public void run() {
                                            PayUCBLifecycle.this.onPageStarted();
                                        }
                                    });
                                }
                            }
                        } catch (FileNotFoundException | JSONException e4) {
                            PayUCBLifecycle.this.communicationError();
                            e4.printStackTrace();
                        } catch (Exception e5) {
                            PayUCBLifecycle.this.communicationError();
                            e5.printStackTrace();
                        }
                        throw th;
                    }
                }
                if (PayUCBLifecycle.this.activity != null) {
                    PayUCBLifecycle.this.mBankJS = new JSONObject(CBUtil.decodeContents(PayUCBLifecycle.this.activity.openFileInput("initialize")));
                    PayUCBLifecycle.this.setUrlString();
                    PayUCBLifecycle.this.checkVisibilityCB("", true);
                    if (PayUCBLifecycle.this.mBankJS.has("snooze_config")) {
                        str = PayUCBLifecycle.this.mBankJS.get("snooze_config") + "('" + Bank.keyAnalytics + "')";
                    } else {
                        str = "";
                    }
                    PayUCBLifecycle.snoozeImageDownloadTimeout = Integer.parseInt(PayUCBLifecycle.this.mBankJS.has("snooze_image_download_time") ? PayUCBLifecycle.this.mBankJS.get("snooze_image_download_time").toString() : "0");
                    SharedPreferenceUtil.addIntToSharedPreference(PayUCBLifecycle.this.activity.getApplicationContext(), CBUtil.CB_PREFERENCE, CBConstant.SNOOZE_IMAGE_DOWNLOAD_TIME_OUT, PayUCBLifecycle.snoozeImageDownloadTimeout);
                    if (PayUCBLifecycle.this.mBankJS.has(PayUCBLifecycle.this.getString(R.string.sp_internet_restored_ttl))) {
                        str2 = PayUCBLifecycle.this.mBankJS.get(PayUCBLifecycle.this.getString(R.string.sp_internet_restored_ttl)) + "('" + Bank.keyAnalytics + "')";
                    } else {
                        str2 = "";
                    }
                    PayUCBLifecycle.this.activity.runOnUiThread(new Runnable() {
                        public void run() {
                            WebView webView = PayUCBLifecycle.this.cbWebView;
                            webView.loadUrl("javascript:" + str);
                        }
                    });
                    PayUCBLifecycle.this.activity.runOnUiThread(new Runnable() {
                        public void run() {
                            WebView webView = PayUCBLifecycle.this.cbWebView;
                            webView.loadUrl("javascript:" + str2);
                        }
                    });
                    if (PayUCBLifecycle.this.mPageReady && PayUCBLifecycle.this.activity != null) {
                        PayUCBLifecycle.this.activity.runOnUiThread(new Runnable() {
                            public void run() {
                                PayUCBLifecycle.this.onPageStarted();
                            }
                        });
                    }
                }
            }
        });
    }

    public void updateHeight(View view) {
        if (this.maxWebview == 0) {
            calculateMaximumWebViewHeight();
            maximiseWebviewHeight();
        }
        calculateCBHeight(view);
    }

    public void updateLoaderHeight() {
        if (this.chooseActionHeight == 0) {
            this.cbWebView.measure(-1, -1);
            double measuredHeight = (double) this.cbWebView.getMeasuredHeight();
            Double.isNaN(measuredHeight);
            this.chooseActionHeight = (int) (measuredHeight * 0.35d);
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i == 1) {
            this.checkPermissionVisibility = false;
            if (this.SMSOTPClicked) {
                try {
                    WebView webView = this.cbWebView;
                    webView.loadUrl("javascript:" + this.mJS.getString(getString(R.string.cb_otp)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (ContextCompat.checkSelfPermission(this.activity, "android.permission.RECEIVE_SMS") == 0) {
                this.permissionGranted = true;
                this.mPassword = null;
                prepareSmsListener();
                enter_otp(this.enterOtpParams);
                return;
            }
            this.permissionGranted = false;
            enter_otp(this.enterOtpParams);
        }
    }

    /* access modifiers changed from: protected */
    public void showSlowUserWarning() {
        if (this.activity != null && !this.activity.isFinishing() && !this.isSnoozeWindowVisible) {
            View inflate = this.activity.getLayoutInflater().inflate(R.layout.cb_layout_snooze_slow_user, (ViewGroup) null);
            ((TextView) inflate.findViewById(R.id.snooze_header_txt)).setText(R.string.cb_snooze_slow_user_warning_header);
            TextView textView = (TextView) inflate.findViewById(R.id.text_view_cancel_snooze_window);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.snooze_status_icon);
            imageView.setVisibility(0);
            imageView.setImageDrawable(getCbDrawable(this.activity.getApplicationContext(), R.drawable.hourglass));
            if (this.slowUserWarningDialog == null) {
                this.slowUserWarningDialog = new AlertDialog.Builder(this.activity).create();
                this.slowUserWarningDialog.setView(inflate);
                this.slowUserWarningDialog.setCanceledOnTouchOutside(true);
                this.slowUserWarningDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                });
                this.slowUserWarningDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        if (i != 4 || keyEvent.getAction() != 0) {
                            return true;
                        }
                        PayUCBLifecycle.this.slowUserWarningDialog.dismiss();
                        return true;
                    }
                });
                textView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        PayUCBLifecycle.this.slowUserWarningDialog.dismiss();
                    }
                });
            }
            this.slowUserWarningDialog.show();
            CBActivity cBActivity = (CBActivity) this.activity;
            if (CBActivity.STATE == 1) {
                showSlowUserWarningNotification();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dismissSlowUserWarning() {
        if (this.slowUserWarningDialog != null) {
            this.slowUserWarningDialog.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void dismissSlowUserWarningTimerOnTouch() {
        if (this.forwardJourneyForChromeLoaderIsComplete) {
            this.firstTouch = true;
            dismissSlowUserWarningTimer();
        }
    }

    /* access modifiers changed from: protected */
    public void showSlowUserWarningNotification() {
        if (this.activity != null && !this.activity.isFinishing()) {
            new Intent();
        }
    }

    public class PayUCBLifeCycleTouchListener extends CustomBrowserMain.CBMainViewOnTouchListener {
        public PayUCBLifeCycleTouchListener() {
            super();
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            L.v("sTag", "onTouch of PayUCBLifeCycleCalled");
            PayUCBLifecycle.this.dismissSlowUserWarningTimerOnTouch();
            return super.onTouch(view, motionEvent);
        }
    }

    /* access modifiers changed from: protected */
    public void showCbBlankOverlay(int i) {
        if (this.cbBlankOverlay != null) {
            this.cbBlankOverlay.setVisibility(i);
        }
    }

    /* access modifiers changed from: protected */
    public void updateSnoozeDialogWithMessage(String str, String str2) {
        if (this.snoozeDialog != null && this.snoozeDialog.isShowing()) {
            this.snoozeDialog.cancel();
            this.snoozeDialog.dismiss();
        }
        View inflate = this.activity.getLayoutInflater().inflate(R.layout.cb_layout_snooze, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.snooze_header_txt)).setText(str);
        inflate.findViewById(R.id.text_view_cancel_snooze_window).setVisibility(8);
        ((TextView) inflate.findViewById(R.id.text_view_snooze_message)).setText(str2);
        SnoozeLoaderView snoozeLoaderView = (SnoozeLoaderView) inflate.findViewById(R.id.snooze_loader_view);
        snoozeLoaderView.setVisibility(0);
        snoozeLoaderView.startAnimation();
        inflate.findViewById(R.id.button_snooze_transaction).setVisibility(8);
        inflate.findViewById(R.id.text_view_retry_message_detail).setVisibility(8);
        inflate.findViewById(R.id.button_retry_transaction).setVisibility(8);
        inflate.findViewById(R.id.button_cancel_transaction).setVisibility(8);
        inflate.findViewById(R.id.t_confirm).setVisibility(8);
        inflate.findViewById(R.id.t_nconfirm).setVisibility(8);
        inflate.findViewById(R.id.button_go_back_snooze).setVisibility(8);
        this.snoozeDialog = new AlertDialog.Builder(this.activity).create();
        this.snoozeDialog.setView(inflate);
        this.snoozeDialog.setCancelable(false);
        this.snoozeDialog.setCanceledOnTouchOutside(false);
        this.snoozeDialog.show();
    }

    /* access modifiers changed from: private */
    public void killSnoozeWindowAndContinuePayment(final Intent intent) {
        int internetRestoredWindowTTL = this.customBrowserConfig != null ? this.customBrowserConfig.getInternetRestoredWindowTTL() : 5000;
        if (this.mInternetRestoredWindowTTL != 0) {
            internetRestoredWindowTTL = this.mInternetRestoredWindowTTL;
        }
        if (this.backwardJourneyStarted) {
            try {
                if (this.cbUtil.getValueOfJSONKey(intent.getStringExtra("value"), getString(R.string.cb_snooze_verify_api_status)).contentEquals("1")) {
                    updateSnoozeDialogWithMessage(getString(R.string.cb_transaction_verified), getString(R.string.redirect_back_to_merchant));
                } else {
                    updateSnoozeDialogWithMessage(getString(R.string.cb_transaction_state_unknown), getString(R.string.status_unknown_redirect_to_merchant));
                }
            } catch (Exception e) {
                e.printStackTrace();
                updateSnoozeDialogWithMessage(getString(R.string.cb_transaction_state_unknown), getString(R.string.status_unknown_redirect_to_merchant));
            }
        } else {
            updateSnoozeDialogWithMessage(getString(R.string.internet_restored), getString(R.string.resuming_your_transaction));
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (PayUCBLifecycle.this.snoozeDialog != null) {
                    PayUCBLifecycle.this.snoozeDialog.dismiss();
                }
                if (PayUCBLifecycle.this.backwardJourneyStarted) {
                    if (PayUCBLifecycle.this.snoozeService != null) {
                        PayUCBLifecycle.this.snoozeService.killSnoozeService();
                    }
                    PayUCBLifecycle.this.showTransactionStatusDialog(intent.getStringExtra("value"), false);
                    return;
                }
                PayUCBLifecycle.this.resumeTransaction(intent);
            }
        }, (long) internetRestoredWindowTTL);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0099 A[Catch:{ Exception -> 0x01f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x012b A[Catch:{ Exception -> 0x01f5 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void showTransactionStatusDialog(java.lang.String r10, boolean r11) {
        /*
            r9 = this;
            r0 = 1
            r9.setTransactionStatusReceived(r0)     // Catch:{ Exception -> 0x01f5 }
            com.payu.custombrowser.util.CBUtil r0 = r9.cbUtil     // Catch:{ Exception -> 0x01f5 }
            int r1 = com.payu.custombrowser.R.string.cb_snooze_verify_api_status     // Catch:{ Exception -> 0x01f5 }
            java.lang.String r1 = r9.getString(r1)     // Catch:{ Exception -> 0x01f5 }
            java.lang.String r0 = r0.getValueOfJSONKey(r10, r1)     // Catch:{ Exception -> 0x01f5 }
            android.app.Activity r1 = r9.activity     // Catch:{ Exception -> 0x01f5 }
            android.view.LayoutInflater r1 = r1.getLayoutInflater()     // Catch:{ Exception -> 0x01f5 }
            int r2 = com.payu.custombrowser.R.layout.cb_layout_snooze     // Catch:{ Exception -> 0x01f5 }
            r3 = 0
            android.view.View r1 = r1.inflate(r2, r3)     // Catch:{ Exception -> 0x01f5 }
            android.support.v7.app.AlertDialog$Builder r2 = new android.support.v7.app.AlertDialog$Builder     // Catch:{ Exception -> 0x01f5 }
            android.app.Activity r3 = r9.activity     // Catch:{ Exception -> 0x01f5 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x01f5 }
            r2.setView((android.view.View) r1)     // Catch:{ Exception -> 0x01f5 }
            android.support.v7.app.AlertDialog r2 = r2.create()     // Catch:{ Exception -> 0x01f5 }
            r9.snoozeDialog = r2     // Catch:{ Exception -> 0x01f5 }
            java.lang.String r2 = "1"
            boolean r0 = r0.contentEquals(r2)     // Catch:{ Exception -> 0x01f5 }
            r2 = 5000(0x1388, double:2.4703E-320)
            r4 = 0
            r5 = 8
            if (r0 == 0) goto L_0x0149
            android.webkit.WebView r0 = r9.cbWebView     // Catch:{ Exception -> 0x01f5 }
            java.lang.String r0 = r0.getUrl()     // Catch:{ Exception -> 0x01f5 }
            if (r0 == 0) goto L_0x0049
            android.webkit.WebView r0 = r9.cbWebView     // Catch:{ Exception -> 0x01f5 }
            java.lang.String r0 = r0.getUrl()     // Catch:{ Exception -> 0x01f5 }
            goto L_0x004b
        L_0x0049:
            java.lang.String r0 = ""
        L_0x004b:
            boolean r0 = com.payu.custombrowser.Bank.isUrlWhiteListed(r0)     // Catch:{ Exception -> 0x01f5 }
            if (r0 == 0) goto L_0x0068
            r0 = 19
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x01f5 }
            if (r0 == r6) goto L_0x0068
            java.lang.String r10 = "snooze_transaction_status_update"
            java.lang.String r11 = "data_repost"
            r9.addEventAnalytics(r10, r11)     // Catch:{ Exception -> 0x01f5 }
            java.lang.String r10 = ""
            r9.progressBarVisibilityPayuChrome(r5, r10)     // Catch:{ Exception -> 0x01f5 }
            r9.reloadWebView()     // Catch:{ Exception -> 0x01f5 }
            goto L_0x01f9
        L_0x0068:
            java.lang.String r0 = "snooze_transaction_status_update"
            java.lang.String r6 = "post_to_surl"
            r9.addEventAnalytics(r0, r6)     // Catch:{ Exception -> 0x01f5 }
            java.lang.String r0 = ""
            com.payu.custombrowser.util.CBUtil r6 = r9.cbUtil     // Catch:{ JSONException -> 0x0090 }
            java.lang.String r7 = "response"
            java.lang.String r10 = r6.getValueOfJSONKey(r10, r7)     // Catch:{ JSONException -> 0x0090 }
            com.payu.custombrowser.util.CBUtil r0 = new com.payu.custombrowser.util.CBUtil     // Catch:{ JSONException -> 0x008e }
            r0.<init>()     // Catch:{ JSONException -> 0x008e }
            com.payu.custombrowser.bean.CustomBrowserConfig r6 = r9.customBrowserConfig     // Catch:{ JSONException -> 0x008e }
            java.lang.String r6 = r6.getPayuPostData()     // Catch:{ JSONException -> 0x008e }
            java.lang.String r7 = "surl"
            java.lang.String r0 = r0.getDataFromPostData(r6, r7)     // Catch:{ JSONException -> 0x008e }
            r9.postDataToSurl(r10, r0)     // Catch:{ JSONException -> 0x008e }
            goto L_0x0097
        L_0x008e:
            r0 = move-exception
            goto L_0x0094
        L_0x0090:
            r10 = move-exception
            r8 = r0
            r0 = r10
            r10 = r8
        L_0x0094:
            r0.printStackTrace()     // Catch:{ Exception -> 0x01f5 }
        L_0x0097:
            if (r11 == 0) goto L_0x012b
            int r11 = com.payu.custombrowser.R.id.snooze_status_icon     // Catch:{ Exception -> 0x01f5 }
            android.view.View r11 = r1.findViewById(r11)     // Catch:{ Exception -> 0x01f5 }
            r11.setVisibility(r4)     // Catch:{ Exception -> 0x01f5 }
            int r11 = com.payu.custombrowser.R.id.snooze_header_txt     // Catch:{ Exception -> 0x01f5 }
            android.view.View r11 = r1.findViewById(r11)     // Catch:{ Exception -> 0x01f5 }
            android.widget.TextView r11 = (android.widget.TextView) r11     // Catch:{ Exception -> 0x01f5 }
            int r0 = com.payu.custombrowser.R.string.cb_transaction_sucess     // Catch:{ Exception -> 0x01f5 }
            r11.setText(r0)     // Catch:{ Exception -> 0x01f5 }
            int r11 = com.payu.custombrowser.R.id.text_view_cancel_snooze_window     // Catch:{ Exception -> 0x01f5 }
            android.view.View r11 = r1.findViewById(r11)     // Catch:{ Exception -> 0x01f5 }
            r11.setVisibility(r5)     // Catch:{ Exception -> 0x01f5 }
            int r11 = com.payu.custombrowser.R.id.text_view_snooze_message     // Catch:{ Exception -> 0x01f5 }
            android.view.View r11 = r1.findViewById(r11)     // Catch:{ Exception -> 0x01f5 }
            android.widget.TextView r11 = (android.widget.TextView) r11     // Catch:{ Exception -> 0x01f5 }
            int r0 = com.payu.custombrowser.R.string.cb_transaction_success_msg     // Catch:{ Exception -> 0x01f5 }
            java.lang.String r0 = r9.getString(r0)     // Catch:{ Exception -> 0x01f5 }
            r11.setText(r0)     // Catch:{ Exception -> 0x01f5 }
            int r11 = com.payu.custombrowser.R.id.snooze_loader_view     // Catch:{ Exception -> 0x01f5 }
            android.view.View r11 = r1.findViewById(r11)     // Catch:{ Exception -> 0x01f5 }
            r11.setVisibility(r5)     // Catch:{ Exception -> 0x01f5 }
            int r11 = com.payu.custombrowser.R.id.button_snooze_transaction     // Catch:{ Exception -> 0x01f5 }
            android.view.View r11 = r1.findViewById(r11)     // Catch:{ Exception -> 0x01f5 }
            r11.setVisibility(r5)     // Catch:{ Exception -> 0x01f5 }
            int r11 = com.payu.custombrowser.R.id.text_view_retry_message_detail     // Catch:{ Exception -> 0x01f5 }
            android.view.View r11 = r1.findViewById(r11)     // Catch:{ Exception -> 0x01f5 }
            r11.setVisibility(r5)     // Catch:{ Exception -> 0x01f5 }
            int r11 = com.payu.custombrowser.R.id.button_retry_transaction     // Catch:{ Exception -> 0x01f5 }
            android.view.View r11 = r1.findViewById(r11)     // Catch:{ Exception -> 0x01f5 }
            r11.setVisibility(r5)     // Catch:{ Exception -> 0x01f5 }
            int r11 = com.payu.custombrowser.R.id.button_cancel_transaction     // Catch:{ Exception -> 0x01f5 }
            android.view.View r11 = r1.findViewById(r11)     // Catch:{ Exception -> 0x01f5 }
            r11.setVisibility(r5)     // Catch:{ Exception -> 0x01f5 }
            int r11 = com.payu.custombrowser.R.id.t_confirm     // Catch:{ Exception -> 0x01f5 }
            android.view.View r11 = r1.findViewById(r11)     // Catch:{ Exception -> 0x01f5 }
            r11.setVisibility(r5)     // Catch:{ Exception -> 0x01f5 }
            int r11 = com.payu.custombrowser.R.id.t_nconfirm     // Catch:{ Exception -> 0x01f5 }
            android.view.View r11 = r1.findViewById(r11)     // Catch:{ Exception -> 0x01f5 }
            r11.setVisibility(r5)     // Catch:{ Exception -> 0x01f5 }
            android.support.v7.app.AlertDialog r11 = r9.snoozeDialog     // Catch:{ Exception -> 0x01f5 }
            com.payu.custombrowser.PayUCBLifecycle$14 r0 = new com.payu.custombrowser.PayUCBLifecycle$14     // Catch:{ Exception -> 0x01f5 }
            r0.<init>(r10)     // Catch:{ Exception -> 0x01f5 }
            r11.setOnDismissListener(r0)     // Catch:{ Exception -> 0x01f5 }
            android.support.v7.app.AlertDialog r10 = r9.snoozeDialog     // Catch:{ Exception -> 0x01f5 }
            r10.setCanceledOnTouchOutside(r4)     // Catch:{ Exception -> 0x01f5 }
            android.support.v7.app.AlertDialog r10 = r9.snoozeDialog     // Catch:{ Exception -> 0x01f5 }
            r10.show()     // Catch:{ Exception -> 0x01f5 }
            android.os.Handler r10 = new android.os.Handler     // Catch:{ Exception -> 0x01f5 }
            r10.<init>()     // Catch:{ Exception -> 0x01f5 }
            com.payu.custombrowser.PayUCBLifecycle$15 r11 = new com.payu.custombrowser.PayUCBLifecycle$15     // Catch:{ Exception -> 0x01f5 }
            r11.<init>()     // Catch:{ Exception -> 0x01f5 }
            r10.postDelayed(r11, r2)     // Catch:{ Exception -> 0x01f5 }
            goto L_0x01f9
        L_0x012b:
            com.payu.custombrowser.bean.CustomBrowserData r11 = com.payu.custombrowser.bean.CustomBrowserData.SINGLETON     // Catch:{ Exception -> 0x01f5 }
            if (r11 == 0) goto L_0x0142
            com.payu.custombrowser.bean.CustomBrowserData r11 = com.payu.custombrowser.bean.CustomBrowserData.SINGLETON     // Catch:{ Exception -> 0x01f5 }
            com.payu.custombrowser.PayUCustomBrowserCallback r11 = r11.getPayuCustomBrowserCallback()     // Catch:{ Exception -> 0x01f5 }
            if (r11 == 0) goto L_0x0142
            com.payu.custombrowser.bean.CustomBrowserData r11 = com.payu.custombrowser.bean.CustomBrowserData.SINGLETON     // Catch:{ Exception -> 0x01f5 }
            com.payu.custombrowser.PayUCustomBrowserCallback r11 = r11.getPayuCustomBrowserCallback()     // Catch:{ Exception -> 0x01f5 }
            java.lang.String r0 = ""
            r11.onPaymentSuccess(r10, r0)     // Catch:{ Exception -> 0x01f5 }
        L_0x0142:
            android.app.Activity r10 = r9.activity     // Catch:{ Exception -> 0x01f5 }
            r10.finish()     // Catch:{ Exception -> 0x01f5 }
            goto L_0x01f9
        L_0x0149:
            if (r11 == 0) goto L_0x01ef
            int r10 = com.payu.custombrowser.R.id.button_snooze_transaction     // Catch:{ Exception -> 0x01f5 }
            android.view.View r10 = r1.findViewById(r10)     // Catch:{ Exception -> 0x01f5 }
            r10.setVisibility(r4)     // Catch:{ Exception -> 0x01f5 }
            int r10 = com.payu.custombrowser.R.id.snooze_status_icon     // Catch:{ Exception -> 0x01f5 }
            android.view.View r10 = r1.findViewById(r10)     // Catch:{ Exception -> 0x01f5 }
            r10.setVisibility(r4)     // Catch:{ Exception -> 0x01f5 }
            int r10 = com.payu.custombrowser.R.id.text_view_cancel_snooze_window     // Catch:{ Exception -> 0x01f5 }
            android.view.View r10 = r1.findViewById(r10)     // Catch:{ Exception -> 0x01f5 }
            r10.setVisibility(r5)     // Catch:{ Exception -> 0x01f5 }
            int r10 = com.payu.custombrowser.R.id.button_snooze_transaction     // Catch:{ Exception -> 0x01f5 }
            android.view.View r10 = r1.findViewById(r10)     // Catch:{ Exception -> 0x01f5 }
            r10.setVisibility(r5)     // Catch:{ Exception -> 0x01f5 }
            int r10 = com.payu.custombrowser.R.id.snooze_header_txt     // Catch:{ Exception -> 0x01f5 }
            android.view.View r10 = r1.findViewById(r10)     // Catch:{ Exception -> 0x01f5 }
            android.widget.TextView r10 = (android.widget.TextView) r10     // Catch:{ Exception -> 0x01f5 }
            int r11 = com.payu.custombrowser.R.string.cb_transaction_failed_title     // Catch:{ Exception -> 0x01f5 }
            r10.setText(r11)     // Catch:{ Exception -> 0x01f5 }
            int r10 = com.payu.custombrowser.R.id.text_view_snooze_message     // Catch:{ Exception -> 0x01f5 }
            android.view.View r10 = r1.findViewById(r10)     // Catch:{ Exception -> 0x01f5 }
            android.widget.TextView r10 = (android.widget.TextView) r10     // Catch:{ Exception -> 0x01f5 }
            int r11 = com.payu.custombrowser.R.string.cb_transaction_failed     // Catch:{ Exception -> 0x01f5 }
            r10.setText(r11)     // Catch:{ Exception -> 0x01f5 }
            int r10 = com.payu.custombrowser.R.id.button_retry_transaction     // Catch:{ Exception -> 0x01f5 }
            android.view.View r10 = r1.findViewById(r10)     // Catch:{ Exception -> 0x01f5 }
            r10.setVisibility(r5)     // Catch:{ Exception -> 0x01f5 }
            int r10 = com.payu.custombrowser.R.id.button_cancel_transaction     // Catch:{ Exception -> 0x01f5 }
            android.view.View r10 = r1.findViewById(r10)     // Catch:{ Exception -> 0x01f5 }
            r10.setVisibility(r4)     // Catch:{ Exception -> 0x01f5 }
            int r10 = com.payu.custombrowser.R.id.button_snooze_transaction     // Catch:{ Exception -> 0x01f5 }
            android.view.View r10 = r1.findViewById(r10)     // Catch:{ Exception -> 0x01f5 }
            r10.setVisibility(r5)     // Catch:{ Exception -> 0x01f5 }
            int r10 = com.payu.custombrowser.R.id.text_view_retry_message_detail     // Catch:{ Exception -> 0x01f5 }
            android.view.View r10 = r1.findViewById(r10)     // Catch:{ Exception -> 0x01f5 }
            r10.setVisibility(r5)     // Catch:{ Exception -> 0x01f5 }
            int r10 = com.payu.custombrowser.R.id.text_view_transaction_snoozed_message1     // Catch:{ Exception -> 0x01f5 }
            android.view.View r10 = r1.findViewById(r10)     // Catch:{ Exception -> 0x01f5 }
            r10.setVisibility(r5)     // Catch:{ Exception -> 0x01f5 }
            int r10 = com.payu.custombrowser.R.id.text_view_ac_debited_twice     // Catch:{ Exception -> 0x01f5 }
            android.view.View r10 = r1.findViewById(r10)     // Catch:{ Exception -> 0x01f5 }
            r10.setVisibility(r5)     // Catch:{ Exception -> 0x01f5 }
            int r10 = com.payu.custombrowser.R.id.button_cancel_transaction     // Catch:{ Exception -> 0x01f5 }
            android.view.View r10 = r1.findViewById(r10)     // Catch:{ Exception -> 0x01f5 }
            com.payu.custombrowser.PayUCBLifecycle$16 r11 = new com.payu.custombrowser.PayUCBLifecycle$16     // Catch:{ Exception -> 0x01f5 }
            r11.<init>()     // Catch:{ Exception -> 0x01f5 }
            r10.setOnClickListener(r11)     // Catch:{ Exception -> 0x01f5 }
            android.support.v7.app.AlertDialog r10 = r9.snoozeDialog     // Catch:{ Exception -> 0x01f5 }
            com.payu.custombrowser.PayUCBLifecycle$17 r11 = new com.payu.custombrowser.PayUCBLifecycle$17     // Catch:{ Exception -> 0x01f5 }
            r11.<init>()     // Catch:{ Exception -> 0x01f5 }
            r10.setOnDismissListener(r11)     // Catch:{ Exception -> 0x01f5 }
            android.support.v7.app.AlertDialog r10 = r9.snoozeDialog     // Catch:{ Exception -> 0x01f5 }
            r10.setCanceledOnTouchOutside(r4)     // Catch:{ Exception -> 0x01f5 }
            android.support.v7.app.AlertDialog r10 = r9.snoozeDialog     // Catch:{ Exception -> 0x01f5 }
            r10.show()     // Catch:{ Exception -> 0x01f5 }
            android.os.Handler r10 = new android.os.Handler     // Catch:{ Exception -> 0x01f5 }
            r10.<init>()     // Catch:{ Exception -> 0x01f5 }
            com.payu.custombrowser.PayUCBLifecycle$18 r11 = new com.payu.custombrowser.PayUCBLifecycle$18     // Catch:{ Exception -> 0x01f5 }
            r11.<init>()     // Catch:{ Exception -> 0x01f5 }
            r10.postDelayed(r11, r2)     // Catch:{ Exception -> 0x01f5 }
            goto L_0x01f9
        L_0x01ef:
            android.app.Activity r10 = r9.activity     // Catch:{ Exception -> 0x01f5 }
            r10.finish()     // Catch:{ Exception -> 0x01f5 }
            goto L_0x01f9
        L_0x01f5:
            r10 = move-exception
            r10.printStackTrace()
        L_0x01f9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.payu.custombrowser.PayUCBLifecycle.showTransactionStatusDialog(java.lang.String, boolean):void");
    }

    public void postDataToSurl(final String str, final String str2) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(URLDecoder.decode(str2, "UTF-8")).openConnection();
                    String str = str;
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    httpURLConnection.setRequestProperty("Content-Length", String.valueOf(str.length()));
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.getOutputStream().write(str.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void resumeTransaction(Intent intent) {
        this.customBrowserConfig = (CustomBrowserConfig) intent.getExtras().getParcelable(CBConstant.CB_CONFIG);
        if (intent.getStringExtra(CBConstant.CURRENT_URL) == null) {
            reloadWebView(this.customBrowserConfig.getPostURL(), this.customBrowserConfig.getPayuPostData());
        } else if (intent.getStringExtra(CBConstant.CURRENT_URL).equalsIgnoreCase(this.customBrowserConfig.getPostURL())) {
            if (this.customBrowserConfig.getPostURL().contentEquals("https://secure.payu.in/_payment") || this.customBrowserConfig.getPostURL().contentEquals("https://mobiletest.payu.in/_payment")) {
                markPreviousTxnAsUserCanceled(this.cbUtil.getLogMessage(this.activity.getApplicationContext(), CBAnalyticsConstant.SURE_PAY_CANCELLED, this.customBrowserConfig.getTransactionID(), "", Bank.keyAnalytics, this.customBrowserConfig.getTransactionID(), ""));
            }
            reloadWebView(this.customBrowserConfig.getPostURL(), this.customBrowserConfig.getPayuPostData());
        } else if (Bank.isUrlWhiteListed(intent.getStringExtra(CBConstant.CURRENT_URL))) {
            reloadWebView(intent.getStringExtra(CBConstant.CURRENT_URL));
        } else {
            if (this.customBrowserConfig.getPostURL().contentEquals("https://secure.payu.in/_payment") || this.customBrowserConfig.getPostURL().contentEquals("https://mobiletest.payu.in/_payment")) {
                markPreviousTxnAsUserCanceled(this.cbUtil.getLogMessage(this.activity.getApplicationContext(), CBAnalyticsConstant.SURE_PAY_CANCELLED, this.customBrowserConfig.getTransactionID(), "", Bank.keyAnalytics, this.customBrowserConfig.getTransactionID(), ""));
            }
            reloadWebView(this.customBrowserConfig.getPostURL(), this.customBrowserConfig.getPayuPostData());
        }
    }

    public void markPreviousTxnAsUserCanceled(String str) {
        new SurePayCancelAsyncTaskHelper(str).execute();
    }
}
