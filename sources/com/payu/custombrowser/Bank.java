package com.payu.custombrowser;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.method.TransformationMethod;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.coloros.mcssdk.PushManager;
import com.google.common.net.HttpHeaders;
import com.payu.custombrowser.bean.CustomBrowserData;
import com.payu.custombrowser.custombar.CustomProgressBar;
import com.payu.custombrowser.services.SnoozeService;
import com.payu.custombrowser.util.CBAnalyticsConstant;
import com.payu.custombrowser.util.CBConstant;
import com.payu.custombrowser.util.CBUtil;
import com.payu.custombrowser.util.L;
import com.payu.custombrowser.util.MissingParamException;
import com.payu.custombrowser.widgets.SnoozeLoaderView;
import com.payu.magicretry.MagicRetryFragment;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.module.WXModalUIModule;
import com.unionpay.tsmservice.data.Constant;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class Bank extends PayUCBLifecycle {
    public static String Version = BuildConfig.VERSION_NAME;
    public static String keyAnalytics;
    static String paymentMode;
    static String sdkVersion;
    static String transactionID;
    private static List<String> whiteListedUrls = new ArrayList();
    /* access modifiers changed from: private */
    public ButtonOnclickListener buttonClickListener = new ButtonOnclickListener();
    private int currentLoadingProgress;
    Runnable enterOtpRunnable;
    private boolean firstFinish = true;
    private boolean isFirstURLLoaded;
    private boolean isMRDataSet = false;
    /* access modifiers changed from: private */
    public boolean isPageStoppedForcefully = false;
    /* access modifiers changed from: private */
    public boolean isSnoozeTimerRunning = false;
    private boolean isSurePayResumed = false;
    private CountDownTimer mCountDownTimer = null;
    /* access modifiers changed from: private */
    public boolean mLoadingJS = false;
    /* access modifiers changed from: private */
    public boolean pageStarted = false;
    private CountDownTimer payUChromeLoaderDisableTimer;
    private CountDownTimer payUChromeLoaderEnableTimer;
    /* access modifiers changed from: private */
    public boolean saveUserIDCheck = true;
    /* access modifiers changed from: private */
    public boolean showSnoozeWindow = true;
    /* access modifiers changed from: private */
    public boolean showToggleCheck = false;
    public long snoozeClickedTime;
    private View snoozeLayout;
    /* access modifiers changed from: private */
    public SnoozeLoaderView snoozeLoaderView;
    private boolean stopOnlyOnce;
    private boolean visibilitychecked;
    private boolean webpageNotFoundError = false;

    public Bank() {
        this.customProgressBar = new CustomProgressBar();
        this.backwardJourneyUrls = new HashSet();
        this.cbUtil = new CBUtil();
        this.serialExecutor = Executors.newCachedThreadPool();
        this.retryUrls = new HashSet();
    }

    public static boolean isUrlWhiteListed(String str) {
        if ((str.contains("https://secure.payu.in") || str.contains(CBConstant.PAYU_DOMAIN_TEST)) && str.contains(CBConstant.RESPONSE_BACKWARD)) {
            return true;
        }
        for (String next : whiteListedUrls) {
            if (str != null && str.contains(next)) {
                return true;
            }
        }
        return false;
    }

    public SnoozeLoaderView getSnoozeLoaderView() {
        return this.snoozeLoaderView;
    }

    public String getPageType() {
        return this.pageType;
    }

    public void reloadWebView(String str, String str2) {
        this.forwardJourneyForChromeLoaderIsComplete = false;
        this.backwardJourneyStarted = false;
        this.isWebviewReloading = true;
        registerSMSBroadcast();
        this.backwardJourneyStarted = false;
        if (this.snoozeService != null) {
            this.snoozeService.killSnoozeService();
        }
        if (this.isSnoozeWindowVisible) {
            dismissSnoozeWindow();
        }
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
        }
        this.progressDialog = null;
        if (Build.VERSION.SDK_INT == 16 || Build.VERSION.SDK_INT == 17 || Build.VERSION.SDK_INT == 18) {
            this.cbWebView.loadUrl("about:blank");
        }
        setSurePayResumeStatus(true);
        resetAutoSelectOTP();
        this.cbUtil.resetPayuID();
        this.cbWebView.postUrl(str, str2.getBytes());
    }

    public void killSnoozeService() {
        if (this.snoozeService != null) {
            this.snoozeService.killSnoozeService();
        }
    }

    public void reloadWebView() {
        if (this.snoozeService != null) {
            this.snoozeService.killSnoozeService();
        }
        if (this.isSnoozeWindowVisible) {
            dismissSnoozeWindow();
        }
        registerSMSBroadcast();
        this.isWebviewReloading = true;
        if (this.isSnoozeEnabled) {
            initializeSnoozeTimer();
        }
        if (this.cbWebView.getUrl() != null) {
            setSurePayResumeStatus(true);
            if (19 == Build.VERSION.SDK_INT) {
                this.cbWebView.reload();
            } else {
                reloadWVUsingJS();
            }
        }
    }

    public void reloadWebView(String str) {
        if (this.snoozeService != null) {
            this.snoozeService.killSnoozeService();
        }
        if (this.isSnoozeWindowVisible) {
            dismissSnoozeWindow();
        }
        registerSMSBroadcast();
        this.isWebviewReloading = true;
        if (this.isSnoozeEnabled) {
            initializeSnoozeTimer();
        }
        if (this.cbWebView.getUrl() != null) {
            setSurePayResumeStatus(true);
            if (19 == Build.VERSION.SDK_INT) {
                this.cbWebView.reload();
            } else {
                reloadWVUsingJS();
            }
        } else {
            reloadWebView(this.customBrowserConfig.getPostURL(), this.customBrowserConfig.getPayuPostData());
        }
    }

    public String getBankName() {
        if (this.bankName == null) {
            return "";
        }
        return this.bankName;
    }

    /* access modifiers changed from: private */
    public void checkPermission() {
        boolean z = false;
        if (this.checkedPermission || Build.VERSION.SDK_INT < 23 || !this.merchantSMSPermission) {
            onHelpAvailable();
            if (ContextCompat.checkSelfPermission(this.activity, "android.permission.RECEIVE_SMS") == 0) {
                z = true;
            }
            this.permissionGranted = z;
            if (this.SMSOTPClicked) {
                try {
                    WebView webView = this.cbWebView;
                    webView.loadUrl("javascript:" + this.mJS.getString(getString(R.string.cb_otp)));
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else {
            this.checkedPermission = true;
            if (ContextCompat.checkSelfPermission(this.activity, "android.permission.RECEIVE_SMS") != 0) {
                requestPermissions(new String[]{"android.permission.RECEIVE_SMS"}, 1);
                this.checkPermissionVisibility = true;
                return;
            }
            this.permissionGranted = true;
            if (this.SMSOTPClicked) {
                try {
                    WebView webView2 = this.cbWebView;
                    webView2.loadUrl("javascript:" + this.mJS.getString(getString(R.string.cb_otp)));
                } catch (JSONException e3) {
                    e3.printStackTrace();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
    }

    @JavascriptInterface
    public void showCustomBrowser(final boolean z) {
        this.showCB = z;
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (!z) {
                        Bank.this.maximiseWebviewHeight();
                        Bank.this.frameState = 1;
                        try {
                            if (Bank.this.cbSlideBarView != null) {
                                Bank.this.cbSlideBarView.setVisibility(8);
                            }
                            Bank.this.onHelpUnavailable();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void setMRData(String str) {
        if (!this.isMRDataSet) {
            MagicRetryFragment.setMRData(str, getActivity().getApplicationContext());
            updateWhitelistedRetryUrls(CBUtil.updateRetryData(str, getActivity().getApplicationContext()));
            this.isMRDataSet = true;
        }
    }

    public void onOverrideURL(String str) {
        if (this.cbWebPageProgressBar != null) {
            this.cbWebPageProgressBar.setProgress(10);
        }
    }

    private void snoozeOnReceivedError() {
        setIsPageStoppedForcefully(true);
        if (this.snoozeConfigMap != null) {
            stopSnoozeCountDownTimer();
            this.surePayDisableStatus = this.cbUtil.getSurePayDisableStatus(this.snoozeConfigMap, this.webviewUrl);
            launchSnoozeWindow(2);
        }
    }

    public void onReceivedErrorWebClient(int i, String str) {
        this.webpageNotFoundError = true;
        try {
            if (getActivity() != null && !getActivity().isFinishing() && CustomBrowserData.SINGLETON != null && CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback() != null) {
                if (!this.backwardJourneyStarted) {
                    snoozeOnReceivedError();
                } else if (this.backwardJourneyStarted && this.isTxnNBType && this.snoozeCountBackwardJourney < this.customBrowserConfig.getEnableSurePay()) {
                    dismissSnoozeWindow();
                    snoozeOnReceivedError();
                }
                onHelpUnavailable();
                this.cbBaseView.removeAllViews();
                if (this.cbWebPageProgressBar != null) {
                    this.cbWebPageProgressBar.setVisibility(8);
                }
                if (this.maxWebview != 0) {
                    maximiseWebviewHeight();
                    this.frameState = 1;
                }
                hideCB();
                if (!this.cbOldFlow) {
                    CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback().onCBErrorReceived(i, str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMagicRetryCB() {
        try {
            WebView webView = this.cbWebView;
            webView.loadUrl("javascript:" + this.mBankJS.getString("getMagicRetryUrls") + "('" + keyAnalytics + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onProgressChanged(int i) {
        if (this.activity != null && !this.activity.isFinishing() && !isRemoving() && isAdded() && this.cbWebPageProgressBar != null) {
            this.cbWebPageProgressBar.setVisibility(0);
            if (i != 100) {
                startAnimation(i);
            } else if (this.cbWebPageProgressBar != null) {
                this.cbWebPageProgressBar.setProgress(100);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (Bank.this.activity != null && !Bank.this.activity.isFinishing() && !Bank.this.isRemoving() && Bank.this.isAdded()) {
                            Bank.this.cbWebPageProgressBar.setVisibility(8);
                            Bank.this.lastProgress = 0;
                        }
                    }
                }, 100);
            }
        }
    }

    @JavascriptInterface
    public void onMerchantHashReceived(final String str) {
        if (getActivity() != null && !getActivity().isFinishing() && !isRemoving() && isAdded()) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    switch (Bank.this.storeOneClickHash) {
                        case 2:
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                Bank.this.cbUtil.storeInSharedPreferences(Bank.this.getActivity().getApplicationContext(), jSONObject.getString(CBAnalyticsConstant.CARD_TOKEN), jSONObject.getString(CBAnalyticsConstant.MERCHANT_HASH));
                                return;
                            } catch (JSONException e) {
                                e.printStackTrace();
                                return;
                            }
                        default:
                            return;
                    }
                }
            });
        }
    }

    private void initializeSnoozeTimer() {
        if (CustomBrowserData.SINGLETON != null && CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback() != null && this.customBrowserConfig != null && this.cbUtil.getBooleanSharedPreferenceDefaultTrue(CBConstant.SNOOZE_ENABLED, this.activity.getApplicationContext()) && this.customBrowserConfig.getEnableSurePay() > this.snoozeCount) {
            if (this.isSnoozeTimerRunning) {
                stopSnoozeCountDownTimer();
            }
            startSnoozeCountDownTimer();
        }
    }

    public void onPageStartedWebclient(String str) {
        this.pageStarted = true;
        this.isPageStoppedForcefully = false;
        if ((Build.VERSION.SDK_INT == 16 || Build.VERSION.SDK_INT == 17 || Build.VERSION.SDK_INT == 18) && this.webpageNotFoundError) {
            jellyBeanOnReceivedError();
        }
        this.webpageNotFoundError = false;
        dismissSlowUserWarning();
        if (!this.payuPG && str != null && (str.equalsIgnoreCase("https://secure.payu.in/_payment") || str.equalsIgnoreCase(CBConstant.PRODUCTION_PAYMENT_URL_SEAMLESS))) {
            this.payuPG = true;
        }
        if (!this.isFirstURLLoaded) {
            if (this.customBrowserConfig != null && this.customBrowserConfig.getPayuPostData() == null && this.customBrowserConfig.getPostURL() == null) {
                if (CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback().getPostData() == null || CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback().getPostURL() == null) {
                    throw new MissingParamException("POST Data or POST URL Missing or wrong POST URL");
                }
                this.customBrowserConfig.setPayuPostData(CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback().getPostData());
                this.customBrowserConfig.setPostURL(CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback().getPostURL());
                CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback().setPostURL((String) null);
                CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback().setPostData((String) null);
            }
            if (this.customBrowserConfig != null) {
                this.isTxnNBType = checkIfTransactionNBType(this.customBrowserConfig.getPayuPostData());
            }
            this.isFirstURLLoaded = true;
        }
        this.showSnoozeWindow = true;
        if (this.pageType != null && !this.pageType.equalsIgnoreCase("")) {
            addEventAnalytics(CBAnalyticsConstant.DEPARTURE, "-1");
            this.pageType = "";
        }
        if (this.activity != null && !this.activity.isFinishing() && !isRemoving() && isAdded()) {
            CBUtil cBUtil = this.cbUtil;
            Context applicationContext = this.activity.getApplicationContext();
            cBUtil.setStringSharedPreferenceLastURL(applicationContext, CBAnalyticsConstant.LAST_URL, "s:" + str);
            if (this.cbWebPageProgressBar != null) {
                this.cbWebPageProgressBar.setVisibility(0);
            }
            if (this.payUChromeLoaderDisableTimer != null) {
                this.payUChromeLoaderDisableTimer.cancel();
            }
            if (this.cbWebPageProgressBar != null) {
                this.cbWebPageProgressBar.setVisibility(0);
                this.cbWebPageProgressBar.setProgress(10);
            }
            this.backwardJourneyStarted = isInBackWardJourney(str);
            if (!this.forwardJourneyForChromeLoaderIsComplete || this.backwardJourneyStarted) {
                progressBarVisibilityPayuChrome(0, str);
            }
            this.webviewUrl = (this.cbWebView.getUrl() == null || this.cbWebView.getUrl().equalsIgnoreCase("")) ? str : this.cbWebView.getUrl();
            if (CustomBrowserData.SINGLETON != null && CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback() != null) {
                if (this.backwardJourneyStarted) {
                    if (this.isTxnNBType) {
                        this.isSnoozeWindowVisible = false;
                    } else {
                        dismissSnoozeWindow();
                    }
                }
                if (str.contains(CBConstant.PAYMENT_OPTION_URL_PROD)) {
                    this.mJS = null;
                    this.drawable = null;
                }
                try {
                    if ((!this.cbUtil.getDataFromPostData(this.customBrowserConfig.getPayuPostData(), "surl").equals("") && str.contains(URLDecoder.decode(this.cbUtil.getDataFromPostData(this.customBrowserConfig.getPayuPostData(), "surl"), "UTF-8"))) || ((!this.cbUtil.getDataFromPostData(this.customBrowserConfig.getPayuPostData(), "furl").equals("") && str.contains(URLDecoder.decode(this.cbUtil.getDataFromPostData(this.customBrowserConfig.getPayuPostData(), "furl"), "UTF-8"))) || isRetryURL(str))) {
                        this.showSnoozeWindow = false;
                        dismissSnoozeWindow();
                        hideCB();
                        if (isRetryURL(str)) {
                            resetAutoSelectOTP();
                            this.backwardJourneyStarted = false;
                        }
                        stopSnoozeCountDownTimer();
                        if (this.snoozeService != null) {
                            this.snoozeService.killSnoozeService();
                        }
                    } else if (this.isSnoozeEnabled && this.customBrowserConfig.getSurePayMode() == 1 && !this.backwardJourneyStarted) {
                        this.snoozeLoadPercentageAndTimeOut = this.snoozeConfigMap.getPercentageAndTimeout(str);
                        this.snoozeUrlLoadingPercentage = this.snoozeLoadPercentageAndTimeOut[0];
                        this.snoozeUrlLoadingTimeout = this.snoozeLoadPercentageAndTimeOut[1];
                        this.surePayDisableStatus = this.cbUtil.getSurePayDisableStatus(this.snoozeConfigMap, str);
                        initializeSnoozeTimer();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void jellyBeanOnReceivedError() {
        setIsPageStoppedForcefully(true);
        if (this.snoozeConfigMap != null && !this.cbOldFlow) {
            stopSnoozeCountDownTimer();
            this.surePayDisableStatus = this.cbUtil.getSurePayDisableStatus(this.snoozeConfigMap, this.webviewUrl);
            launchSnoozeWindow(2);
        }
    }

    public boolean isInBackWardJourney(String str) {
        try {
            if (!this.backwardJourneyStarted) {
                if ((str.contains("https://secure.payu.in") || str.contains(CBConstant.PAYU_DOMAIN_TEST)) && str.contains(CBConstant.RESPONSE_BACKWARD)) {
                    return true;
                }
                if (this.backwardJourneyUrls != null) {
                    for (String contains : this.backwardJourneyUrls) {
                        if (str.contains(contains)) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            return this.backwardJourneyStarted;
        } catch (Exception e) {
            e.printStackTrace();
            return this.backwardJourneyStarted;
        }
    }

    public void onLoadResourse(WebView webView, String str) {
        if (this.activity != null && !this.activity.isFinishing() && !isRemoving() && isAdded() && !str.equalsIgnoreCase(CBConstant.rupeeURL) && !str.contains(CBConstant.rupeeURL1)) {
            str.contains(CBConstant.rupeeURL2);
        }
    }

    public void onPageFinishWebclient(String str) {
        this.pageStarted = false;
        if (this.activity != null && !this.activity.isFinishing() && !isRemoving() && isAdded()) {
            if (this.isSurePayResumed) {
                addEventAnalytics(CBAnalyticsConstant.SNOOZE_RESUME_URL, str);
                setSurePayResumeStatus(false);
            }
            CBUtil cBUtil = this.cbUtil;
            Context applicationContext = this.activity.getApplicationContext();
            cBUtil.setStringSharedPreferenceLastURL(applicationContext, CBAnalyticsConstant.LAST_URL, "f:" + str);
            startPayUChromeLoaderDisbaleTimer();
            if (!(!this.firstFinish || getArguments() == null || getArguments().getInt(CBConstant.MAIN_LAYOUT, -1) == -1)) {
                try {
                    final View findViewById = this.activity.findViewById(getArguments().getInt(CBConstant.MAIN_LAYOUT));
                    findViewById.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        private final int DefaultKeyboardDP = 100;
                        private final int EstimatedKeyboardDP;
                        private final Rect r;

                        {
                            this.EstimatedKeyboardDP = (Build.VERSION.SDK_INT >= 21 ? 48 : 0) + 100;
                            this.r = new Rect();
                        }

                        public void onGlobalLayout() {
                            if (Bank.this.activity != null && !Bank.this.activity.isFinishing() && !Bank.this.isRemoving() && Bank.this.isAdded()) {
                                int applyDimension = (int) TypedValue.applyDimension(1, (float) this.EstimatedKeyboardDP, findViewById.getResources().getDisplayMetrics());
                                findViewById.getWindowVisibleDisplayFrame(this.r);
                                if ((findViewById.getRootView().getHeight() - (this.r.bottom - this.r.top) >= applyDimension) && Bank.this.checkForInput == 0) {
                                    ((InputMethodManager) Bank.this.activity.getSystemService("input_method")).toggleSoftInput(3, 0);
                                    Bank.this.checkForInput = 1;
                                }
                            }
                        }
                    });
                    this.firstFinish = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (!this.isPageStoppedForcefully) {
            stopSnoozeCountDownTimer();
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (!Bank.this.isPageStoppedForcefully && !Bank.this.pageStarted && Bank.this.isSnoozeWindowVisible && !Bank.this.backwardJourneyStarted) {
                    if (Bank.this.isSnoozeWindowVisible) {
                        Bank.this.addEventAnalytics(CBAnalyticsConstant.SNOOZE_WINDOW_AUTOMATICALLY_DISAPPEAR_TIME, "-1");
                    }
                    Bank.this.dismissSnoozeWindow();
                }
            }
        }, 1000);
    }

    @JavascriptInterface
    public void setSnoozeEnabled(boolean z) {
        if (!z) {
            this.customBrowserConfig.setEnableSurePay(0);
        }
        this.cbUtil.setBooleanSharedPreference(CBConstant.SNOOZE_ENABLED, z, this.activity.getApplicationContext());
    }

    private void startPayUChromeLoaderDisbaleTimer() {
        if (this.payUChromeLoaderDisableTimer != null) {
            this.payUChromeLoaderDisableTimer.cancel();
        }
        if (this.payUChromeLoaderEnableTimer != null) {
            this.payUChromeLoaderEnableTimer.cancel();
        }
        this.payUChromeLoaderDisableTimer = new CountDownTimer(2000, 1000) {
            public void onTick(long j) {
            }

            public void onFinish() {
                Bank.this.dismissPayULoader();
            }
        }.start();
    }

    private void startPayUChromeLoaderEnableTimer() {
        if (this.payUChromeLoaderEnableTimer != null) {
            this.payUChromeLoaderEnableTimer.cancel();
        }
        this.payUChromeLoaderEnableTimer = new CountDownTimer(1000, 1000) {
            public void onTick(long j) {
            }

            public void onFinish() {
                Bank.this.progressBarVisibilityPayuChrome(0, "");
                Bank.this.forwardJourneyForChromeLoaderIsComplete = true;
            }
        }.start();
    }

    @JavascriptInterface
    public void getUserId() {
        if (this.activity != null && !this.activity.isFinishing()) {
            this.activity.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        if (Bank.this.cbUtil.getStringSharedPreference(Bank.this.activity.getApplicationContext(), Bank.this.bankName) != null && !Bank.this.cbUtil.getStringSharedPreference(Bank.this.activity.getApplicationContext(), Bank.this.bankName).equals("")) {
                            WebView webView = Bank.this.cbWebView;
                            webView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.string.cb_populate_user_id)) + "(\"" + Bank.this.cbUtil.getStringSharedPreference(Bank.this.activity.getApplicationContext(), Bank.this.bankName) + "\")");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void setUserId(String str) {
        if (this.saveUserIDCheck) {
            if (this.activity != null && !this.activity.isFinishing()) {
                this.cbUtil.storeInSharedPreferences(this.activity.getApplicationContext(), this.bankName, str);
            }
        } else if (!this.cbUtil.getStringSharedPreference(this.activity.getApplicationContext(), this.bankName).equals("")) {
            this.cbUtil.removeFromSharedPreferences(this.activity.getApplicationContext(), this.bankName);
        }
    }

    @JavascriptInterface
    public void nativeHelperForNB(final String str, final String str2) {
        if (this.activity != null && !this.activity.isFinishing()) {
            this.activity.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        if (Bank.this.isSnoozeWindowVisible) {
                            Bank.this.dismissSnoozeWindow();
                            Bank.this.addEventAnalytics(CBAnalyticsConstant.SNOOZE_WINDOW_ACTION, CBAnalyticsConstant.SNOOZE_WINDOW_DISMISSED_BY_CB);
                            Bank.this.addEventAnalytics(CBAnalyticsConstant.SNOOZE_WINDOW_AUTOMATICALLY_DISAPPEAR_TIME, "-1");
                        }
                        Bank.this.pageType = CBAnalyticsConstant.NBLOGIN_PAGE;
                        Bank.this.addEventAnalytics(CBAnalyticsConstant.ARRIVAL, "-1");
                        Bank.this.onHelpAvailable();
                        Bank.this.addEventAnalytics(CBAnalyticsConstant.CB_STATUS, CBAnalyticsConstant.NB_CUSTOM_BROWSER);
                        if (str != null && Bank.this.activity != null) {
                            Bank.this.dismissSnoozeWindow();
                            View inflate = Bank.this.activity.getLayoutInflater().inflate(R.layout.nb_layout, (ViewGroup) null);
                            final Button button = (Button) inflate.findViewById(R.id.b_continue);
                            final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.checkbox);
                            JSONObject jSONObject = new JSONObject(str2);
                            String string = Bank.this.getString(R.string.cb_btn_text);
                            if (!jSONObject.has(string) || jSONObject.getString(string) == null || jSONObject.getString(string).equalsIgnoreCase("")) {
                                Bank.this.onHelpUnavailable();
                                Bank.this.cbBaseView.removeAllViews();
                            } else if (str.equals(Bank.this.getString(R.string.cb_button))) {
                                if (!jSONObject.has(Bank.this.getString(R.string.cb_checkbox))) {
                                    checkBox.setVisibility(8);
                                } else if (jSONObject.getBoolean(Bank.this.getString(R.string.cb_checkbox))) {
                                    if (Bank.this.saveUserIDCheck) {
                                        Bank.this.addEventAnalytics(CBAnalyticsConstant.INITIAL_USER_NAME_CHECKBOX_STATUS, Constants.Name.Y);
                                        checkBox.setChecked(true);
                                    } else {
                                        Bank.this.addEventAnalytics(CBAnalyticsConstant.INITIAL_USER_NAME_CHECKBOX_STATUS, "n");
                                        checkBox.setChecked(false);
                                    }
                                    checkBox.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            boolean unused = Bank.this.saveUserIDCheck = checkBox.isChecked();
                                            if (Bank.this.saveUserIDCheck) {
                                                Bank bank = Bank.this;
                                                bank.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, CBAnalyticsConstant.USER_NAME_CHECKBOX_STATUS + Constants.Name.Y);
                                                return;
                                            }
                                            Bank bank2 = Bank.this;
                                            bank2.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, CBAnalyticsConstant.USER_NAME_CHECKBOX_STATUS + "n");
                                        }
                                    });
                                    checkBox.setVisibility(0);
                                } else {
                                    checkBox.setVisibility(8);
                                }
                                button.setText(jSONObject.getString(string));
                                button.setTransformationMethod((TransformationMethod) null);
                                button.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        try {
                                            Bank bank = Bank.this;
                                            bank.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, CBAnalyticsConstant.NB_BUTTON_CLICK + button.getText());
                                            WebView webView = Bank.this.cbWebView;
                                            webView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.string.cb_btn_action)));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                Bank.this.cbBaseView.removeAllViews();
                                Bank.this.cbBaseView.addView(inflate);
                                Bank.this.nbhelpVisible = true;
                            } else if (str.equals(Bank.this.getString(R.string.cb_pwd_btn))) {
                                button.setText(jSONObject.getString(string));
                                if (Bank.this.showToggleCheck) {
                                    checkBox.setChecked(true);
                                } else {
                                    checkBox.setChecked(false);
                                }
                                if (checkBox.isChecked()) {
                                    try {
                                        WebView webView = Bank.this.cbWebView;
                                        webView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.string.cb_toggle_field)) + "(\"" + true + "\")");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                checkBox.setText(Bank.this.getString(R.string.cb_show_password));
                                checkBox.setVisibility(0);
                                checkBox.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        boolean unused = Bank.this.showToggleCheck = checkBox.isChecked();
                                        if (checkBox.isChecked()) {
                                            try {
                                                WebView webView = Bank.this.cbWebView;
                                                webView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.string.cb_toggle_field)) + "(\"" + true + "\")");
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            try {
                                                WebView webView2 = Bank.this.cbWebView;
                                                webView2.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.string.cb_toggle_field)) + "(\"" + false + "\")");
                                            } catch (Exception e2) {
                                                e2.printStackTrace();
                                            }
                                        }
                                    }
                                });
                                button.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        try {
                                            WebView webView = Bank.this.cbWebView;
                                            webView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.string.cb_btn_action)));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                Bank.this.nbhelpVisible = true;
                                Bank.this.cbBaseView.removeAllViews();
                                Bank.this.cbBaseView.addView(inflate);
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
        }
        this.activity.runOnUiThread(new Runnable() {
            public void run() {
                Bank.this.dismissPayULoader();
            }
        });
    }

    @JavascriptInterface
    public void reInit() {
        if (this.activity != null && !this.activity.isFinishing()) {
            this.activity.runOnUiThread(new Runnable() {
                public void run() {
                    Bank.this.onPageFinished();
                }
            });
        }
    }

    @JavascriptInterface
    public void bankFound(final String str) {
        if (!this.visibilitychecked) {
            checkVisibilityCB(str);
            this.visibilitychecked = true;
        }
        cbSetBankDrawable(str);
        CBUtil.setVariableReflection(CBConstant.MAGIC_RETRY_PAKAGE, str, CBConstant.BANKNAME);
        if (this.activity != null && !this.activity.isFinishing()) {
            this.activity.runOnUiThread(new Runnable() {
                public void run() {
                    Bank.this.calculateMaximumWebViewHeight();
                }
            });
        }
        this.bankName = str;
        if (!this.mPageReady) {
            try {
                if (this.loadingLayout != null) {
                    this.activity.runOnUiThread(new Runnable() {
                        public void run() {
                            Bank.this.customProgressBar.removeProgressDialog(Bank.this.loadingLayout.findViewById(R.id.progress));
                        }
                    });
                }
                if (!this.isPageStoppedForcefully) {
                    if (this.loadingLayout == null) {
                        convertToNative("loading", "{}");
                    } else if (!(this.activity == null || this.loadingLayout == ((ViewGroup) this.activity.findViewById(R.id.help_view)).getChildAt(0))) {
                        convertToNative("loading", "{}");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!this.mLoadingJS && this.mJS == null) {
            this.serialExecutor.execute(new Runnable() {
                public void run() {
                    boolean unused = Bank.this.mLoadingJS = true;
                    try {
                        if (Bank.this.activity != null) {
                            String string = Bank.this.mBankJS.getString(str);
                            if (!new File(Bank.this.activity.getFilesDir(), string).exists()) {
                                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(CBConstant.PRODUCTION_URL + string + ".js").openConnection();
                                httpURLConnection.setRequestMethod("GET");
                                httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_CHARSET, "UTF-8");
                                if (httpURLConnection.getResponseCode() == 200) {
                                    Bank.this.cbUtil.writeFileOutputStream(httpURLConnection.getInputStream(), Bank.this.activity, string, 0);
                                }
                            }
                        }
                        try {
                            if (Bank.this.activity != null) {
                                String string2 = Bank.this.mBankJS.getString(str);
                                Bank.this.mJS = new JSONObject(CBUtil.decodeContents(Bank.this.activity.openFileInput(string2)));
                                if (Bank.this.mPageReady && Bank.this.activity != null) {
                                    Bank.this.activity.runOnUiThread(new Runnable() {
                                        public void run() {
                                            Bank.this.onPageFinished();
                                        }
                                    });
                                }
                                boolean unused2 = Bank.this.mLoadingJS = false;
                            }
                        } catch (FileNotFoundException | JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        if (Bank.this.activity != null) {
                            String string3 = Bank.this.mBankJS.getString(str);
                            Bank.this.mJS = new JSONObject(CBUtil.decodeContents(Bank.this.activity.openFileInput(string3)));
                            if (Bank.this.mPageReady && Bank.this.activity != null) {
                                Bank.this.activity.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Bank.this.onPageFinished();
                                    }
                                });
                            }
                            boolean unused3 = Bank.this.mLoadingJS = false;
                        }
                    } catch (Throwable th) {
                        try {
                            if (Bank.this.activity != null) {
                                String string4 = Bank.this.mBankJS.getString(str);
                                Bank.this.mJS = new JSONObject(CBUtil.decodeContents(Bank.this.activity.openFileInput(string4)));
                                if (Bank.this.mPageReady && Bank.this.activity != null) {
                                    Bank.this.activity.runOnUiThread(new Runnable() {
                                        public void run() {
                                            Bank.this.onPageFinished();
                                        }
                                    });
                                }
                                boolean unused4 = Bank.this.mLoadingJS = false;
                            }
                        } catch (FileNotFoundException | JSONException e4) {
                            e4.printStackTrace();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                        throw th;
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void convertToNative(final String str, final String str2) {
        if (this.isSnoozeWindowVisible) {
            dismissSnoozeWindow();
            killSnoozeService();
            cancelTransactionNotification();
            addEventAnalytics(CBAnalyticsConstant.SNOOZE_WINDOW_ACTION, CBAnalyticsConstant.SNOOZE_WINDOW_DISMISSED_BY_CB);
            addEventAnalytics(CBAnalyticsConstant.SNOOZE_WINDOW_AUTOMATICALLY_DISAPPEAR_TIME, "-1");
        }
        this.activity.runOnUiThread(new Runnable() {
            public void run() {
                Bank.this.dismissPayULoader();
            }
        });
        if (this.pageType != null && !this.pageType.equalsIgnoreCase("")) {
            addEventAnalytics(CBAnalyticsConstant.DEPARTURE, "-1");
            this.pageType = "";
        }
        if (this.activity != null && this.showCB) {
            this.activity.runOnUiThread(new Runnable() {
                public void run() {
                    if (Bank.this.loadingLayout != null) {
                        Bank.this.customProgressBar.removeProgressDialog(Bank.this.loadingLayout.findViewById(R.id.progress));
                    }
                    if (Bank.this.enterOTPView != null) {
                        Bank.this.customProgressBar.removeProgressDialog(Bank.this.enterOTPView.findViewById(R.id.progress));
                    }
                    try {
                        if (!(Bank.this.waitingOTPTimer == null || Bank.this.enterOtpRunnable == null)) {
                            Bank.this.cbUtil.cancelTimer(Bank.this.waitingOTPTimer);
                        }
                        if (str.equals(Bank.this.getString(R.string.cb_error))) {
                            Bank.this.onBankError();
                        } else if (str.equals("parse error")) {
                            Bank.this.onBankError();
                        } else if (!str.contentEquals("loading") || Bank.this.pin_selected_flag || !Bank.this.checkLoading) {
                            boolean z = true;
                            if (str.equals(Bank.this.getString(R.string.cb_choose))) {
                                Bank.this.addCustomBrowserEventAnalytics();
                                Bank.this.frameState = 2;
                                Bank.this.checkLoading = true;
                                if (Bank.this.cbTransparentView != null) {
                                    Bank.this.cbTransparentView.setVisibility(0);
                                }
                                View inflate = Bank.this.activity.getLayoutInflater().inflate(R.layout.choose_action, (ViewGroup) null);
                                if (Bank.this.maxWebview == 0) {
                                    Bank.this.calculateMaximumWebViewHeight();
                                    Bank.this.maximiseWebviewHeight();
                                }
                                Bank.this.cbBaseView.setVisibility(0);
                                if (Bank.this.cbSlideBarView != null) {
                                    Bank.this.cbSlideBarView.setVisibility(8);
                                }
                                Bank.this.calculateCBHeight(inflate);
                                Bank.this.onHelpAvailable();
                                inflate.measure(-2, -2);
                                Bank.this.chooseActionHeight = inflate.getMeasuredHeight();
                                ImageView imageView = (ImageView) inflate.findViewById(R.id.bank_logo);
                                imageView.setOnClickListener(Bank.this.viewOnClickListener);
                                if (Bank.this.drawable != null) {
                                    imageView.setImageDrawable(Bank.this.drawable);
                                }
                                Bank.this.cbBaseView.removeAllViews();
                                Bank.this.cbBaseView.addView(inflate);
                                if (Bank.this.cbBaseView.isShown()) {
                                    Bank.this.frameState = 2;
                                }
                                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("Select an option for Faster payment");
                                spannableStringBuilder.setSpan(new StyleSpan(1), 21, 27, 33);
                                ((TextView) inflate.findViewById(R.id.choose_text)).setText(spannableStringBuilder);
                                try {
                                    final JSONObject jSONObject = new JSONObject(str2);
                                    if ((!jSONObject.has(Bank.this.getString(R.string.cb_otp)) || !jSONObject.getBoolean(Bank.this.getString(R.string.cb_otp))) && (!jSONObject.has(Bank.this.getString(R.string.cb_pin)) || !jSONObject.getBoolean(Bank.this.getString(R.string.cb_pin)))) {
                                        Bank.this.pageType = "";
                                    } else {
                                        Bank.this.pageType = CBAnalyticsConstant.CHOOSE_SCREEN;
                                    }
                                    if (!jSONObject.has(Bank.this.getString(R.string.cb_otp)) || jSONObject.getBoolean(Bank.this.getString(R.string.cb_otp))) {
                                        inflate.findViewById(R.id.otp).setOnClickListener(Bank.this.buttonClickListener);
                                        if (Bank.this.autoSelectOtp) {
                                            Bank.this.eventRecorded = CBAnalyticsConstant.CB_AUTO_OTP_SELECT;
                                            Bank.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, Bank.this.eventRecorded);
                                            inflate.findViewById(R.id.otp).performClick();
                                            Bank.this.autoSelectOtp = false;
                                        }
                                    } else {
                                        inflate.findViewById(R.id.otp).setVisibility(8);
                                        inflate.findViewById(R.id.view).setVisibility(8);
                                    }
                                    inflate.findViewById(R.id.otp).setOnClickListener(Bank.this.buttonClickListener);
                                    if (!jSONObject.has(Bank.this.getString(R.string.cb_pin)) || jSONObject.getBoolean(Bank.this.getString(R.string.cb_pin))) {
                                        inflate.findViewById(R.id.pin).setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View view) {
                                                Bank.this.pin_selected_flag = true;
                                                Bank.this.approve_flag = true;
                                                Bank.this.maximiseWebviewHeight();
                                                Bank.this.frameState = 1;
                                                if (Bank.this.cbTransparentView != null) {
                                                    Bank.this.cbTransparentView.setVisibility(8);
                                                }
                                                try {
                                                    if (!jSONObject.has(Bank.this.getString(R.string.cb_register)) || !jSONObject.getBoolean(Bank.this.getString(R.string.cb_register))) {
                                                        Bank.this.eventRecorded = CBAnalyticsConstant.PASSWORD;
                                                        Bank.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, Bank.this.eventRecorded);
                                                        Bank.this.onHelpUnavailable();
                                                        WebView webView = Bank.this.cbWebView;
                                                        webView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.string.cb_pin)));
                                                        Bank.this.updateHeight(view);
                                                    }
                                                    View inflate = Bank.this.activity.getLayoutInflater().inflate(R.layout.register_pin, (ViewGroup) null);
                                                    try {
                                                        Bank.this.cbBaseView.removeAllViews();
                                                        Bank.this.cbBaseView.addView(inflate);
                                                        if (Bank.this.cbBaseView.isShown()) {
                                                            Bank.this.frameState = 2;
                                                        }
                                                        inflate.findViewById(R.id.pin).setOnClickListener(new View.OnClickListener() {
                                                            public void onClick(View view) {
                                                                try {
                                                                    Bank.this.eventRecorded = CBAnalyticsConstant.PASSWORD;
                                                                    Bank.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, Bank.this.eventRecorded);
                                                                    WebView webView = Bank.this.cbWebView;
                                                                    webView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.string.cb_pin)));
                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
                                                        });
                                                        if (jSONObject.has(Bank.this.getString(R.string.cb_otp)) && !jSONObject.getBoolean(Bank.this.getString(R.string.cb_otp))) {
                                                            inflate.findViewById(R.id.otp).setVisibility(8);
                                                        }
                                                        inflate.findViewById(R.id.otp).setOnClickListener(Bank.this.buttonClickListener);
                                                        view = inflate;
                                                    } catch (JSONException e) {
                                                        View view2 = inflate;
                                                        e = e;
                                                        view = view2;
                                                        e.printStackTrace();
                                                        Bank.this.updateHeight(view);
                                                    }
                                                    Bank.this.updateHeight(view);
                                                } catch (JSONException e2) {
                                                    e = e2;
                                                }
                                            }
                                        });
                                    } else {
                                        inflate.findViewById(R.id.pin).setVisibility(8);
                                        inflate.findViewById(R.id.view).setVisibility(8);
                                    }
                                    if (jSONObject.has(Bank.this.getString(R.string.cb_error))) {
                                        inflate.findViewById(R.id.error_message).setVisibility(0);
                                        ((TextView) inflate.findViewById(R.id.error_message)).setText(jSONObject.getString("error"));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else if (str.equals(Bank.this.getString(R.string.cb_incorrect_OTP_2))) {
                                Bank.this.pageType = CBAnalyticsConstant.OTP_PAGE;
                                Bank.this.addCustomBrowserEventAnalytics();
                                Bank.this.checkLoading = true;
                                Bank.this.onHelpAvailable();
                                View inflate2 = Bank.this.activity.getLayoutInflater().inflate(R.layout.retry_otp, (ViewGroup) null);
                                ImageView imageView2 = (ImageView) inflate2.findViewById(R.id.bank_logo);
                                imageView2.setOnClickListener(Bank.this.viewOnClickListener);
                                if (Bank.this.drawable != null) {
                                    imageView2.setImageDrawable(Bank.this.drawable);
                                }
                                Bank.this.cbBaseView.removeAllViews();
                                Bank.this.cbBaseView.addView(inflate2);
                                if (Bank.this.cbBaseView.isShown()) {
                                    Bank.this.frameState = 2;
                                } else {
                                    if (Bank.this.cbSlideBarView != null) {
                                        Bank.this.cbSlideBarView.setVisibility(0);
                                    }
                                    Bank.this.maximiseWebviewHeight();
                                }
                                if (Bank.this.mPassword == null) {
                                    inflate2.findViewById(R.id.regenerate_layout).setVisibility(0);
                                    inflate2.findViewById(R.id.Regenerate_layout_gone).setVisibility(8);
                                    try {
                                        JSONObject jSONObject2 = new JSONObject(str2);
                                        if (!jSONObject2.has(Bank.this.getString(R.string.cb_pin)) || !jSONObject2.getBoolean(Bank.this.getString(R.string.cb_pin))) {
                                            z = false;
                                        }
                                        inflate2.findViewById(R.id.enter_manually).setOnClickListener(Bank.this.buttonClickListener);
                                        if (z) {
                                            inflate2.findViewById(R.id.pin_layout_gone).setVisibility(0);
                                        } else {
                                            inflate2.findViewById(R.id.pin_layout_gone).setVisibility(8);
                                        }
                                        inflate2.findViewById(R.id.pin).setOnClickListener(Bank.this.buttonClickListener);
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                Bank.this.updateHeight(inflate2);
                            } else if (str.equals(Bank.this.getString(R.string.cb_retry_otp))) {
                                Bank.this.pageType = CBAnalyticsConstant.OTP_PAGE;
                                Bank.this.addCustomBrowserEventAnalytics();
                                Bank.this.checkLoading = true;
                                Bank.this.onHelpAvailable();
                                Bank.this.hideSoftKeyboard();
                                if (Bank.this.cbTransparentView != null) {
                                    Bank.this.cbTransparentView.setVisibility(0);
                                }
                                View inflate3 = Bank.this.activity.getLayoutInflater().inflate(R.layout.retry_otp, (ViewGroup) null);
                                ImageView imageView3 = (ImageView) inflate3.findViewById(R.id.bank_logo);
                                imageView3.setOnClickListener(Bank.this.viewOnClickListener);
                                if (Bank.this.drawable != null) {
                                    imageView3.setImageDrawable(Bank.this.drawable);
                                }
                                Bank.this.cbBaseView.removeAllViews();
                                Bank.this.cbBaseView.addView(inflate3);
                                if (Bank.this.cbBaseView.isShown()) {
                                    Bank.this.frameState = 2;
                                } else {
                                    if (Bank.this.cbSlideBarView != null) {
                                        Bank.this.cbSlideBarView.setVisibility(0);
                                    }
                                    Bank.this.maximiseWebviewHeight();
                                }
                                try {
                                    if (Bank.this.mPassword == null) {
                                        JSONObject jSONObject3 = new JSONObject(str2);
                                        boolean z2 = jSONObject3.has(Bank.this.getString(R.string.cb_regenerate)) && jSONObject3.getBoolean(Bank.this.getString(R.string.cb_regenerate));
                                        if (!jSONObject3.has(Bank.this.getString(R.string.cb_pin)) || !jSONObject3.getBoolean(Bank.this.getString(R.string.cb_pin))) {
                                            z = false;
                                        }
                                        inflate3.findViewById(R.id.regenerate_layout).setVisibility(0);
                                        if (z2) {
                                            inflate3.findViewById(R.id.Regenerate_layout_gone).setVisibility(0);
                                            if (z) {
                                                inflate3.findViewById(R.id.Enter_manually_gone).setVisibility(8);
                                                inflate3.findViewById(R.id.pin_layout_gone).setVisibility(0);
                                            } else {
                                                inflate3.findViewById(R.id.Enter_manually_gone).setVisibility(0);
                                                inflate3.findViewById(R.id.pin_layout_gone).setVisibility(8);
                                            }
                                        } else {
                                            if (z) {
                                                inflate3.findViewById(R.id.pin_layout_gone).setVisibility(0);
                                            } else {
                                                inflate3.findViewById(R.id.pin_layout_gone).setVisibility(8);
                                            }
                                            inflate3.findViewById(R.id.Regenerate_layout_gone).setVisibility(8);
                                            inflate3.findViewById(R.id.Enter_manually_gone).setVisibility(0);
                                        }
                                    }
                                    inflate3.findViewById(R.id.pin).setOnClickListener(Bank.this.buttonClickListener);
                                    inflate3.findViewById(R.id.enter_manually).setOnClickListener(Bank.this.buttonClickListener);
                                    inflate3.findViewById(R.id.retry).setOnClickListener(Bank.this.buttonClickListener);
                                    Bank.this.buttonClickListener.setView(inflate3);
                                    inflate3.findViewById(R.id.approve).setOnClickListener(Bank.this.buttonClickListener);
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                                Bank.this.updateHeight(inflate3);
                            } else if (str.equals(Bank.this.getString(R.string.cb_enter_pin))) {
                                Bank.this.pageType = CBAnalyticsConstant.PIN_PAGE;
                                Bank.this.addCustomBrowserEventAnalytics();
                                if (Bank.this.cbSlideBarView != null) {
                                    Bank.this.cbSlideBarView.setVisibility(8);
                                }
                                Bank.this.onHelpUnavailable();
                                Bank.this.pin_selected_flag = true;
                                Bank.this.approve_flag = true;
                                Bank.this.maximiseWebviewHeight();
                                Bank.this.frameState = 1;
                                if (Bank.this.cbTransparentView != null) {
                                    Bank.this.cbTransparentView.setVisibility(8);
                                }
                                Bank.this.maximiseWebviewHeight();
                                Bank.this.cbBaseView.removeAllViews();
                            } else if (str.equals(Bank.this.getString(R.string.cb_enter_otp))) {
                                Bank.this.pageType = CBAnalyticsConstant.OTP_PAGE;
                                Bank.this.SMSOTPClicked = false;
                                Bank.this.checkPermission();
                                Bank.this.enterOtpParams = str2;
                                if (!Bank.this.checkPermissionVisibility) {
                                    Bank.this.addCustomBrowserEventAnalytics();
                                    Bank.this.enter_otp(str2);
                                }
                            } else if (str.equals(Bank.this.getString(R.string.cb_incorrect_pin))) {
                                Bank.this.pageType = CBAnalyticsConstant.CHOOSE_SCREEN;
                                Bank.this.addCustomBrowserEventAnalytics();
                                try {
                                    JSONObject jSONObject4 = new JSONObject(str2);
                                    if (jSONObject4.has(Bank.this.getString(R.string.cb_otp)) && jSONObject4.getBoolean(Bank.this.getString(R.string.cb_otp))) {
                                        Bank.this.checkLoading = true;
                                        Bank.this.onHelpAvailable();
                                        View inflate4 = Bank.this.activity.getLayoutInflater().inflate(R.layout.choose_action, (ViewGroup) null);
                                        ImageView imageView4 = (ImageView) inflate4.findViewById(R.id.bank_logo);
                                        imageView4.setOnClickListener(Bank.this.viewOnClickListener);
                                        if (Bank.this.drawable != null) {
                                            imageView4.setImageDrawable(Bank.this.drawable);
                                        }
                                        TextView textView = (TextView) inflate4.findViewById(R.id.error_message);
                                        textView.setVisibility(0);
                                        textView.setText(Bank.this.activity.getResources().getString(R.string.cb_incorrect_password));
                                        TextView textView2 = (TextView) inflate4.findViewById(R.id.choose_text);
                                        textView2.setVisibility(0);
                                        textView2.setText(Bank.this.activity.getResources().getString(R.string.cb_retry));
                                        Bank.this.cbBaseView.removeAllViews();
                                        Bank.this.cbBaseView.addView(inflate4);
                                        inflate4.findViewById(R.id.otp).setOnClickListener(Bank.this.buttonClickListener);
                                        inflate4.findViewById(R.id.pin).setOnClickListener(Bank.this.buttonClickListener);
                                        Bank.this.updateHeight(inflate4);
                                        if (Bank.this.cbBaseView.isShown()) {
                                            Bank.this.frameState = 2;
                                        } else {
                                            Bank.this.maximiseWebviewHeight();
                                        }
                                    }
                                } catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                            } else if (str.equals(Bank.this.getString(R.string.cb_register_option))) {
                                Bank.this.pageType = CBAnalyticsConstant.REGISTER_PAGE;
                                Bank.this.addCustomBrowserEventAnalytics();
                                Bank.this.onHelpAvailable();
                                View inflate5 = Bank.this.activity.getLayoutInflater().inflate(R.layout.register, (ViewGroup) null);
                                Bank.this.cbBaseView.removeAllViews();
                                Bank.this.cbBaseView.addView(inflate5);
                                ImageView imageView5 = (ImageView) inflate5.findViewById(R.id.bank_logo);
                                imageView5.setOnClickListener(Bank.this.viewOnClickListener);
                                if (Bank.this.drawable != null) {
                                    imageView5.setImageDrawable(Bank.this.drawable);
                                }
                                Bank.this.updateHeight(inflate5);
                                if (Bank.this.cbBaseView.isShown()) {
                                    Bank.this.frameState = 2;
                                } else {
                                    Bank.this.maximiseWebviewHeight();
                                }
                            } else {
                                Bank.this.maximiseWebviewHeight();
                                Bank.this.frameState = 1;
                                if (Bank.this.cbSlideBarView != null) {
                                    Bank.this.cbSlideBarView.setVisibility(8);
                                }
                                Bank.this.onHelpUnavailable();
                            }
                        } else {
                            Bank.this.onHelpAvailable();
                            if (Bank.this.cbTransparentView != null) {
                                Bank.this.cbTransparentView.setVisibility(0);
                            }
                            if (Bank.this.loadingLayout == null) {
                                Bank.this.loadingLayout = Bank.this.activity.getLayoutInflater().inflate(R.layout.loading, (ViewGroup) null);
                            }
                            ImageView imageView6 = (ImageView) Bank.this.loadingLayout.findViewById(R.id.bank_logo);
                            imageView6.setOnClickListener(Bank.this.viewOnClickListener);
                            if (Bank.this.drawable != null) {
                                imageView6.setImageDrawable(Bank.this.drawable);
                            }
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, Bank.this.chooseActionHeight);
                            View findViewById = Bank.this.loadingLayout.findViewById(R.id.loading);
                            findViewById.setLayoutParams(layoutParams);
                            findViewById.requestLayout();
                            findViewById.invalidate();
                            Bank.this.customProgressBar.showDialog(Bank.this.loadingLayout.findViewById(R.id.progress));
                            Bank.this.cbBaseView.removeAllViews();
                            Bank.this.cbBaseView.addView(Bank.this.loadingLayout);
                            if (Bank.this.cbBaseView.isShown()) {
                                Bank.this.frameState = 2;
                            } else {
                                Bank.this.maximiseWebviewHeight();
                            }
                            Bank.this.updateHeight(Bank.this.loadingLayout);
                        }
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                    if (Bank.this.pageType != null && !Bank.this.pageType.equalsIgnoreCase("")) {
                        Bank.this.addEventAnalytics(CBAnalyticsConstant.ARRIVAL, "-1");
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void addCustomBrowserEventAnalytics() {
        if (!this.eventArray.contains(CBAnalyticsConstant.CUSTOM_BROWSER)) {
            this.eventRecorded = CBAnalyticsConstant.CUSTOM_BROWSER;
            this.eventArray.add(CBAnalyticsConstant.CUSTOM_BROWSER);
            addEventAnalytics(CBAnalyticsConstant.CB_STATUS, this.eventRecorded);
        }
    }

    public void onPageFinished() {
        if (isAdded() && !isRemoving() && this.activity != null) {
            this.mPageReady = true;
            if (this.approve_flag.booleanValue()) {
                onHelpUnavailable();
                this.approve_flag = false;
            }
            if (this.loadingLayout != null && this.loadingLayout.isShown()) {
                this.frameState = 1;
                maximiseWebviewHeight();
                onHelpUnavailable();
            }
            this.activity.getWindow().setSoftInputMode(3);
            if (this.mJS != null && this.showCB && !this.isPageStoppedForcefully) {
                try {
                    WebView webView = this.cbWebView;
                    webView.loadUrl("javascript:" + this.mJS.getString(getString(R.string.cb_init)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (this.mBankJS != null && this.mJS == null && this.cbTransparentView != null) {
                this.cbTransparentView.setVisibility(8);
            }
        }
    }

    public void onPageStarted() {
        if (this.activity != null && !this.activity.isFinishing() && !isRemoving() && isAdded()) {
            if (this.nbhelpVisible) {
                onHelpUnavailable();
                this.nbhelpVisible = false;
            }
            if (isAdded() && !isRemoving()) {
                this.mPageReady = false;
                if (this.mBankJS != null) {
                    try {
                        if (this.showCB) {
                            WebView webView = this.cbWebView;
                            webView.loadUrl("javascript:" + this.mBankJS.getString(getString(R.string.cb_detect_bank)));
                            showMagicRetryCB();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (this.cbTransparentView != null) {
                    this.cbTransparentView.setVisibility(8);
                }
            }
        }
    }

    @JavascriptInterface
    public void onFailure(String str) {
        this.merchantResponse = str;
    }

    @JavascriptInterface
    public void onPayuFailure(String str) {
        if (this.snoozeService != null) {
            this.snoozeService.killSnoozeService();
        }
        if (this.activity != null) {
            this.eventRecorded = CBAnalyticsConstant.FAILURE_TRANSACTION;
            addEventAnalytics(CBAnalyticsConstant.TRNX_STATUS, this.eventRecorded);
            this.isSuccessTransaction = false;
            this.payuReponse = str;
        }
        cancelTransactionNotification();
        callTimer();
    }

    @JavascriptInterface
    public void onSuccess() {
        onSuccess("");
    }

    @JavascriptInterface
    public void onPayuSuccess(String str) {
        if (this.snoozeService != null) {
            this.snoozeService.killSnoozeService();
        }
        this.isSuccessTransaction = true;
        this.eventRecorded = CBAnalyticsConstant.SUCCESS_TRANSACTION;
        addEventAnalytics(CBAnalyticsConstant.TRNX_STATUS, this.eventRecorded);
        this.payuReponse = str;
        if (this.storeOneClickHash == 2) {
            try {
                JSONObject jSONObject = new JSONObject(this.payuReponse);
                this.cbUtil.storeInSharedPreferences(this.activity.getApplicationContext(), jSONObject.getString(CBAnalyticsConstant.CARD_TOKEN), jSONObject.getString(CBAnalyticsConstant.MERCHANT_HASH));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cancelTransactionNotification();
        callTimer();
    }

    @JavascriptInterface
    public void onSuccess(String str) {
        this.merchantResponse = str;
    }

    @JavascriptInterface
    public void onCancel() {
        onCancel("");
    }

    @JavascriptInterface
    public void onCancel(final String str) {
        this.activity.runOnUiThread(new Runnable() {
            public void run() {
                if (Bank.this.activity != null && !Bank.this.activity.isFinishing() && Bank.this.isAdded()) {
                    Intent intent = new Intent();
                    intent.putExtra(Bank.this.getString(R.string.cb_result), str);
                    Bank.this.activity.setResult(0, intent);
                    Bank.this.activity.finish();
                }
            }
        });
    }

    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x02d0: MOVE  (r2v9 android.widget.EditText) = (r33v0 android.widget.EditText)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:122)
        	at jadx.core.dex.visitors.regions.TernaryMod.visitRegion(TernaryMod.java:34)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:73)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:31)
        */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x028e  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0291  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0325  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0373  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0377  */
    void enter_otp(java.lang.String r39) {
        /*
            r38 = this;
            r15 = r38
            boolean r0 = r15.webpageNotFoundError
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            r38.prepareSmsListener()
            java.lang.String r0 = r15.eventRecorded
            java.lang.String r1 = "payment_initiated"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0026
            java.lang.String r0 = "CUSTOM_BROWSER"
            r15.eventRecorded = r0
            java.util.ArrayList r0 = r15.eventArray
            java.lang.String r1 = "CUSTOM_BROWSER"
            r0.add(r1)
            java.lang.String r0 = "cb_status"
            java.lang.String r1 = r15.eventRecorded
            r15.addEventAnalytics(r0, r1)
        L_0x0026:
            android.view.View r0 = r15.enterOTPView
            if (r0 == 0) goto L_0x0037
            com.payu.custombrowser.custombar.CustomProgressBar r0 = r15.customProgressBar
            android.view.View r1 = r15.enterOTPView
            int r2 = com.payu.custombrowser.R.id.progress
            android.view.View r1 = r1.findViewById(r2)
            r0.removeProgressDialog(r1)
        L_0x0037:
            r14 = 1
            r15.checkLoading = r14
            r38.onHelpAvailable()
            android.view.View r0 = r15.cbTransparentView
            r13 = 0
            if (r0 == 0) goto L_0x0047
            android.view.View r0 = r15.cbTransparentView
            r0.setVisibility(r13)
        L_0x0047:
            android.view.View r0 = r15.enterOTPView
            if (r0 != 0) goto L_0x005a
            android.app.Activity r0 = r15.activity
            android.view.LayoutInflater r0 = r0.getLayoutInflater()
            int r1 = com.payu.custombrowser.R.layout.wait_for_otp
            r2 = 0
            android.view.View r0 = r0.inflate(r1, r2)
            r15.enterOTPView = r0
        L_0x005a:
            android.view.View r0 = r15.enterOTPView
            int r1 = com.payu.custombrowser.R.id.approve
            android.view.View r0 = r0.findViewById(r1)
            r12 = r0
            android.widget.Button r12 = (android.widget.Button) r12
            android.view.View r0 = r15.enterOTPView
            int r1 = com.payu.custombrowser.R.id.Regenerate_layout_gone
            android.view.View r6 = r0.findViewById(r1)
            android.view.View r0 = r15.enterOTPView
            int r1 = com.payu.custombrowser.R.id.Enter_manually_gone
            android.view.View r8 = r0.findViewById(r1)
            android.view.View r0 = r15.enterOTPView
            int r1 = com.payu.custombrowser.R.id.pin_layout_gone
            android.view.View r7 = r0.findViewById(r1)
            android.view.View r0 = r15.enterOTPView
            int r1 = com.payu.custombrowser.R.id.regenerate_layout
            android.view.View r11 = r0.findViewById(r1)
            android.view.View r0 = r15.enterOTPView
            int r1 = com.payu.custombrowser.R.id.bank_logo
            android.view.View r0 = r0.findViewById(r1)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            android.view.View r1 = r15.enterOTPView
            int r2 = com.payu.custombrowser.R.id.timer
            android.view.View r1 = r1.findViewById(r2)
            r4 = r1
            android.widget.TextView r4 = (android.widget.TextView) r4
            android.view.View r1 = r15.enterOTPView
            int r2 = com.payu.custombrowser.R.id.otp_sms
            android.view.View r1 = r1.findViewById(r2)
            r10 = r1
            android.widget.EditText r10 = (android.widget.EditText) r10
            android.view.View r1 = r15.enterOTPView
            int r2 = com.payu.custombrowser.R.id.waiting
            android.view.View r9 = r1.findViewById(r2)
            android.view.View r1 = r15.enterOTPView
            int r2 = com.payu.custombrowser.R.id.pin
            android.view.View r5 = r1.findViewById(r2)
            android.view.View r1 = r15.enterOTPView
            int r2 = com.payu.custombrowser.R.id.retry
            android.view.View r3 = r1.findViewById(r2)
            android.view.View r1 = r15.enterOTPView
            int r2 = com.payu.custombrowser.R.id.enter_manually
            android.view.View r2 = r1.findViewById(r2)
            android.view.View r1 = r15.enterOTPView
            int r14 = com.payu.custombrowser.R.id.retry_text
            android.view.View r14 = r1.findViewById(r14)
            android.view.View r1 = r15.enterOTPView
            int r13 = com.payu.custombrowser.R.id.progress
            android.view.View r13 = r1.findViewById(r13)
            android.view.View r1 = r15.enterOTPView
            r19 = r0
            int r0 = com.payu.custombrowser.R.id.otp_recieved
            android.view.View r1 = r1.findViewById(r0)
            r15 = 0
            r12.setVisibility(r15)
            r0 = 1050253722(0x3e99999a, float:0.3)
            com.payu.custombrowser.util.CBUtil.setAlpha(r0, r12)
            r20 = r12
            r12 = 8
            r6.setVisibility(r12)
            r8.setVisibility(r15)
            r7.setVisibility(r12)
            r11.setVisibility(r12)
            r4.setVisibility(r15)
            r10.setVisibility(r12)
            r9.setVisibility(r15)
            r5.setVisibility(r15)
            r3.setVisibility(r15)
            r2.setVisibility(r15)
            r14.setVisibility(r12)
            r13.setVisibility(r15)
            r1.setVisibility(r12)
            r15 = r38
            android.graphics.drawable.Drawable r0 = r15.drawable
            if (r0 == 0) goto L_0x0121
            android.graphics.drawable.Drawable r0 = r15.drawable
            r12 = r19
            r12.setImageDrawable(r0)
        L_0x0121:
            com.payu.custombrowser.Bank$18 r0 = new com.payu.custombrowser.Bank$18
            r12 = r20
            r0.<init>(r10, r12)
            r10.addTextChangedListener(r0)
            com.payu.custombrowser.custombar.CustomProgressBar r0 = r15.customProgressBar
            r0.showDialog(r13)
            android.widget.FrameLayout r0 = r15.cbBaseView
            r0.removeAllViews()
            android.widget.FrameLayout r0 = r15.cbBaseView
            r22 = r1
            android.view.View r1 = r15.enterOTPView
            r0.addView(r1)
            android.widget.FrameLayout r0 = r15.cbBaseView
            boolean r0 = r0.isShown()
            r1 = 2
            if (r0 == 0) goto L_0x014a
            r15.frameState = r1
            goto L_0x014d
        L_0x014a:
            r38.maximiseWebviewHeight()
        L_0x014d:
            int r0 = r15.frameState
            r23 = r4
            r4 = 1
            if (r0 != r4) goto L_0x015e
            android.view.View r0 = r15.cbSlideBarView
            if (r0 == 0) goto L_0x015e
            android.view.View r0 = r15.cbSlideBarView
            r1 = 0
            r0.setVisibility(r1)
        L_0x015e:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x027c }
            r1 = r39
            r0.<init>(r1)     // Catch:{ Exception -> 0x027c }
            int r4 = com.payu.custombrowser.R.string.cb_regenerate     // Catch:{ Exception -> 0x027c }
            java.lang.String r4 = r15.getString(r4)     // Catch:{ Exception -> 0x027c }
            boolean r4 = r0.has(r4)     // Catch:{ Exception -> 0x027c }
            if (r4 == 0) goto L_0x017f
            int r4 = com.payu.custombrowser.R.string.cb_regenerate     // Catch:{ Exception -> 0x027c }
            java.lang.String r4 = r15.getString(r4)     // Catch:{ Exception -> 0x027c }
            boolean r4 = r0.getBoolean(r4)     // Catch:{ Exception -> 0x027c }
            if (r4 == 0) goto L_0x017f
            r4 = 1
            goto L_0x0180
        L_0x017f:
            r4 = 0
        L_0x0180:
            int r1 = com.payu.custombrowser.R.string.cb_skip_screen     // Catch:{ Exception -> 0x027c }
            java.lang.String r1 = r15.getString(r1)     // Catch:{ Exception -> 0x027c }
            boolean r1 = r0.has(r1)     // Catch:{ Exception -> 0x027c }
            if (r1 == 0) goto L_0x019c
            int r1 = com.payu.custombrowser.R.string.cb_skip_screen     // Catch:{ Exception -> 0x027c }
            java.lang.String r1 = r15.getString(r1)     // Catch:{ Exception -> 0x027c }
            boolean r1 = r0.getBoolean(r1)     // Catch:{ Exception -> 0x027c }
            if (r1 == 0) goto L_0x019c
            r24 = r13
            r1 = 1
            goto L_0x019f
        L_0x019c:
            r24 = r13
            r1 = 0
        L_0x019f:
            int r13 = com.payu.custombrowser.R.string.cb_pin     // Catch:{ Exception -> 0x0278 }
            java.lang.String r13 = r15.getString(r13)     // Catch:{ Exception -> 0x0278 }
            boolean r13 = r0.has(r13)     // Catch:{ Exception -> 0x0278 }
            if (r13 == 0) goto L_0x01b9
            int r13 = com.payu.custombrowser.R.string.cb_pin     // Catch:{ Exception -> 0x0278 }
            java.lang.String r13 = r15.getString(r13)     // Catch:{ Exception -> 0x0278 }
            boolean r0 = r0.getBoolean(r13)     // Catch:{ Exception -> 0x0278 }
            if (r0 == 0) goto L_0x01b9
            r0 = 1
            goto L_0x01ba
        L_0x01b9:
            r0 = 0
        L_0x01ba:
            if (r1 == 0) goto L_0x0212
            android.app.Activity r1 = r15.activity     // Catch:{ Exception -> 0x0278 }
            if (r1 == 0) goto L_0x022b
            if (r10 == 0) goto L_0x022b
            int r1 = r10.getVisibility()     // Catch:{ Exception -> 0x0278 }
            if (r1 == 0) goto L_0x022b
            r1 = 0
            r14.setVisibility(r1)     // Catch:{ Exception -> 0x0278 }
            if (r4 == 0) goto L_0x01e1
            r6.setVisibility(r1)     // Catch:{ Exception -> 0x0278 }
            r4 = 8
            r7.setVisibility(r4)     // Catch:{ Exception -> 0x01dc }
            r8.setVisibility(r1)     // Catch:{ Exception -> 0x0278 }
            r4 = 8
            goto L_0x01f4
        L_0x01dc:
            r0 = move-exception
            r1 = r24
            goto L_0x0280
        L_0x01e1:
            if (r0 == 0) goto L_0x01e9
            r7.setVisibility(r1)     // Catch:{ Exception -> 0x0278 }
            r4 = 8
            goto L_0x01ee
        L_0x01e9:
            r4 = 8
            r7.setVisibility(r4)     // Catch:{ Exception -> 0x01dc }
        L_0x01ee:
            r6.setVisibility(r4)     // Catch:{ Exception -> 0x01dc }
            r8.setVisibility(r1)     // Catch:{ Exception -> 0x01dc }
        L_0x01f4:
            r11.setVisibility(r1)     // Catch:{ Exception -> 0x01dc }
            r12.setVisibility(r4)     // Catch:{ Exception -> 0x01dc }
            r9.setVisibility(r4)     // Catch:{ Exception -> 0x01dc }
            com.payu.custombrowser.Bank$ButtonOnclickListener r0 = r15.buttonClickListener     // Catch:{ Exception -> 0x0278 }
            r5.setOnClickListener(r0)     // Catch:{ Exception -> 0x0278 }
            com.payu.custombrowser.Bank$ButtonOnclickListener r0 = r15.buttonClickListener     // Catch:{ Exception -> 0x0278 }
            r3.setOnClickListener(r0)     // Catch:{ Exception -> 0x0278 }
            com.payu.custombrowser.Bank$ButtonOnclickListener r0 = r15.buttonClickListener     // Catch:{ Exception -> 0x0278 }
            r2.setOnClickListener(r0)     // Catch:{ Exception -> 0x0278 }
            android.view.View r0 = r15.enterOTPView     // Catch:{ Exception -> 0x0278 }
            r15.updateHeight(r0)     // Catch:{ Exception -> 0x0278 }
            goto L_0x022b
        L_0x0212:
            java.util.Timer r0 = new java.util.Timer     // Catch:{ Exception -> 0x0278 }
            r0.<init>()     // Catch:{ Exception -> 0x0278 }
            r15.waitingOTPTimer = r0     // Catch:{ Exception -> 0x0278 }
            java.util.Timer r0 = r15.waitingOTPTimer     // Catch:{ Exception -> 0x0278 }
            com.payu.custombrowser.Bank$19 r1 = new com.payu.custombrowser.Bank$19     // Catch:{ Exception -> 0x0278 }
            r1.<init>()     // Catch:{ Exception -> 0x0278 }
            r27 = 0
            r29 = 1000(0x3e8, double:4.94E-321)
            r25 = r0
            r26 = r1
            r25.scheduleAtFixedRate(r26, r27, r29)     // Catch:{ Exception -> 0x0278 }
        L_0x022b:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0278 }
            r1 = 23
            if (r0 < r1) goto L_0x0272
            boolean r0 = r15.permissionGranted     // Catch:{ Exception -> 0x0278 }
            if (r0 != 0) goto L_0x0272
            r1 = 0
            r12.setClickable(r1)     // Catch:{ Exception -> 0x0278 }
            android.view.View r0 = r15.enterOTPView     // Catch:{ Exception -> 0x0278 }
            int r1 = com.payu.custombrowser.R.id.linear_layout_waiting_for_otp     // Catch:{ Exception -> 0x0278 }
            android.view.View r0 = r0.findViewById(r1)     // Catch:{ Exception -> 0x0278 }
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0     // Catch:{ Exception -> 0x0278 }
            r15.showSoftKeyboard(r10)     // Catch:{ Exception -> 0x0278 }
            r13 = 0
            r10.setVisibility(r13)     // Catch:{ Exception -> 0x026c }
            r12.setVisibility(r13)     // Catch:{ Exception -> 0x026c }
            r4 = 8
            r9.setVisibility(r4)     // Catch:{ Exception -> 0x0268 }
            r1 = r24
            r1.setVisibility(r4)     // Catch:{ Exception -> 0x0266 }
            r11.setVisibility(r13)     // Catch:{ Exception -> 0x0266 }
            r8.setVisibility(r4)     // Catch:{ Exception -> 0x0266 }
            com.payu.custombrowser.Bank$20 r0 = new com.payu.custombrowser.Bank$20     // Catch:{ Exception -> 0x0266 }
            r0.<init>(r10, r12)     // Catch:{ Exception -> 0x0266 }
            r10.addTextChangedListener(r0)     // Catch:{ Exception -> 0x0266 }
            goto L_0x0284
        L_0x0266:
            r0 = move-exception
            goto L_0x0281
        L_0x0268:
            r0 = move-exception
            r1 = r24
            goto L_0x0281
        L_0x026c:
            r0 = move-exception
            r1 = r24
            r4 = 8
            goto L_0x0281
        L_0x0272:
            r1 = r24
            r4 = 8
            r13 = 0
            goto L_0x0284
        L_0x0278:
            r0 = move-exception
            r1 = r24
            goto L_0x027e
        L_0x027c:
            r0 = move-exception
            r1 = r13
        L_0x027e:
            r4 = 8
        L_0x0280:
            r13 = 0
        L_0x0281:
            r0.printStackTrace()
        L_0x0284:
            int r0 = android.os.Build.VERSION.SDK_INT
            r4 = 23
            if (r0 < r4) goto L_0x0291
            boolean r0 = r15.permissionGranted
            if (r0 != 0) goto L_0x0291
            r0 = 45
            goto L_0x0293
        L_0x0291:
            r0 = 30
        L_0x0293:
            com.payu.custombrowser.Bank$21 r4 = new com.payu.custombrowser.Bank$21
            r16 = r1
            r31 = r22
            r1 = r4
            r17 = r2
            r2 = r38
            r18 = r3
            r3 = r0
            r0 = r4
            r19 = 8
            r20 = 1
            r4 = r23
            r21 = r5
            r5 = r39
            r32 = r9
            r9 = r14
            r33 = r10
            r10 = r11
            r34 = r11
            r11 = r12
            r35 = r12
            r12 = r32
            r36 = r16
            r13 = r33
            r37 = r14
            r14 = r21
            r15 = r18
            r16 = r17
            r1.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            r1 = r38
            r1.enterOtpRunnable = r0
            java.lang.String r0 = r1.mPassword
            if (r0 == 0) goto L_0x0366
            r2 = r33
            if (r2 == 0) goto L_0x0366
            int r0 = r2.getVisibility()
            if (r0 == 0) goto L_0x0366
            com.payu.custombrowser.util.CBUtil r0 = r1.cbUtil
            java.util.Timer r3 = r1.waitingOTPTimer
            r0.cancelTimer(r3)
            android.app.Activity r0 = r1.activity
            int r3 = com.payu.custombrowser.R.id.timer
            android.view.View r0 = r0.findViewById(r3)
            r3 = 8
            r0.setVisibility(r3)
            java.lang.String r0 = r1.eventRecorded
            java.lang.String r4 = "payment_initiated"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x0302
            java.lang.String r0 = r1.eventRecorded
            java.lang.String r4 = "CUSTOM_BROWSER"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x030d
        L_0x0302:
            java.lang.String r0 = "received_otp_direct"
            r1.eventRecorded = r0
            java.lang.String r0 = "otp_received"
            java.lang.String r4 = r1.eventRecorded
            r1.addEventAnalytics(r0, r4)
        L_0x030d:
            java.lang.String r0 = r1.mPassword
            r2.setText(r0)
            int r0 = com.payu.custombrowser.R.string.cb_approve_otp
            java.lang.String r0 = r1.getString(r0)
            r4 = r35
            r4.setText(r0)
            r5 = 1
            r4.setClickable(r5)
            boolean r0 = r1.autoApprove
            if (r0 == 0) goto L_0x0333
            r4.performClick()
            java.lang.String r0 = "auto_approve"
            r1.eventRecorded = r0
            java.lang.String r0 = "user_input"
            java.lang.String r5 = r1.eventRecorded
            r1.addEventAnalytics(r0, r5)
        L_0x0333:
            r0 = 1065353216(0x3f800000, float:1.0)
            com.payu.custombrowser.util.CBUtil.setAlpha(r0, r4)
            r5 = r31
            r6 = 0
            r5.setVisibility(r6)
            com.payu.custombrowser.custombar.CustomProgressBar r0 = r1.customProgressBar
            r5 = r36
            r0.removeDialog(r5)
            r5 = r37
            r5.setVisibility(r3)
            r5 = r34
            r5.setVisibility(r3)
            r4.setVisibility(r6)
            r5 = r32
            r5.setVisibility(r3)
            r2.setVisibility(r6)
            com.payu.custombrowser.Bank$ButtonOnclickListener r0 = r1.buttonClickListener
            android.view.View r2 = r1.enterOTPView
            r0.setView(r2)
            com.payu.custombrowser.Bank$ButtonOnclickListener r0 = r1.buttonClickListener
            r4.setOnClickListener(r0)
        L_0x0366:
            android.view.View r0 = r1.enterOTPView
            r1.updateHeight(r0)
            android.widget.FrameLayout r0 = r1.cbBaseView
            boolean r0 = r0.isShown()
            if (r0 == 0) goto L_0x0377
            r2 = 2
            r1.frameState = r2
            goto L_0x037a
        L_0x0377:
            r38.maximiseWebviewHeight()
        L_0x037a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.payu.custombrowser.Bank.enter_otp(java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    public int getCode(String str) {
        if (str.equalsIgnoreCase(Constant.KEY_PIN)) {
            return 3;
        }
        if (str.equalsIgnoreCase("password")) {
            return 1;
        }
        if (str.equalsIgnoreCase("enter manually")) {
            return 4;
        }
        if (str.equalsIgnoreCase("approve")) {
            return 5;
        }
        if (str.equalsIgnoreCase("otp") || str.equalsIgnoreCase("use sms otp")) {
            return 6;
        }
        if (str.equalsIgnoreCase("sms otp")) {
            return 7;
        }
        return str.equalsIgnoreCase("regenerate otp") ? 2 : 0;
    }

    private void startSnoozeCountDownTimer() {
        this.mCountDownTimer = new CountDownTimer((long) this.snoozeUrlLoadingTimeout, 500) {
            public void onTick(long j) {
                boolean unused = Bank.this.isSnoozeTimerRunning = true;
            }

            public void onFinish() {
                boolean unused = Bank.this.isSnoozeTimerRunning = false;
                if (Bank.this.cbWebView.getProgress() < Bank.this.snoozeUrlLoadingPercentage && !Bank.this.isSnoozeWindowVisible && Bank.this.showSnoozeWindow && !Bank.this.getTransactionStatusReceived()) {
                    Bank.this.launchSnoozeWindow();
                }
                Bank.this.stopSnoozeCountDownTimer();
            }
        };
        this.mCountDownTimer.start();
    }

    /* access modifiers changed from: private */
    public void stopSnoozeCountDownTimer() {
        if (this.mCountDownTimer != null) {
            this.isSnoozeTimerRunning = false;
            this.mCountDownTimer.cancel();
            this.mCountDownTimer = null;
        }
    }

    public void launchSnoozeWindow() {
        launchSnoozeWindow(1);
    }

    public void launchSnoozeWindow(int i) {
        int i2 = i;
        if (this.surePayDisableStatus != 3) {
            if (i2 != 2 || this.surePayDisableStatus != 2) {
                if (i2 != 1 || this.surePayDisableStatus != 1) {
                    showCbBlankOverlay(8);
                    if (this.backwardJourneyStarted) {
                        if (this.snoozeCountBackwardJourney >= this.customBrowserConfig.getEnableSurePay()) {
                            return;
                        }
                    } else if (this.snoozeCount >= this.customBrowserConfig.getEnableSurePay()) {
                        return;
                    }
                    this.snoozeMode = i2;
                    if (this.activity != null && !this.activity.isFinishing()) {
                        dismissSlowUserWarning();
                        progressBarVisibilityPayuChrome(8, "");
                        this.isSnoozeWindowVisible = true;
                        addEventAnalytics(CBAnalyticsConstant.SNOOZE_WINDOW_STATUS, CBAnalyticsConstant.SNOOZE_VISIBLE);
                        addEventAnalytics(CBAnalyticsConstant.SNOOZE_APPEAR_URL, this.webviewUrl);
                        addEventAnalytics(CBAnalyticsConstant.SNOOZE_WINDOW_LAUNCH_MODE, i2 == 1 ? CBConstant.STR_SNOOZE_MODE_WARN : CBConstant.STR_SNOOZE_MODE_FAIL);
                        addEventAnalytics(CBAnalyticsConstant.SNOOZE_APPEAR_TIME, "-1");
                        this.snoozeLayout = this.activity.getLayoutInflater().inflate(R.layout.cb_layout_snooze, (ViewGroup) null);
                        TextView textView = (TextView) this.snoozeLayout.findViewById(R.id.text_view_snooze_message);
                        final TextView textView2 = (TextView) this.snoozeLayout.findViewById(R.id.text_view_transaction_snoozed_message1);
                        TextView textView3 = (TextView) this.snoozeLayout.findViewById(R.id.button_cancel_transaction);
                        Button button = (Button) this.snoozeLayout.findViewById(R.id.button_snooze_transaction);
                        Button button2 = (Button) this.snoozeLayout.findViewById(R.id.button_retry_transaction);
                        TextView textView4 = (TextView) this.snoozeLayout.findViewById(R.id.text_view_cancel_snooze_window);
                        TextView textView5 = (TextView) this.snoozeLayout.findViewById(R.id.t_confirm);
                        final TextView textView6 = (TextView) this.snoozeLayout.findViewById(R.id.t_nconfirm);
                        TextView textView7 = (TextView) this.snoozeLayout.findViewById(R.id.snooze_header_txt);
                        TextView textView8 = (TextView) this.snoozeLayout.findViewById(R.id.text_view_retry_message_detail);
                        Button button3 = (Button) this.snoozeLayout.findViewById(R.id.button_retry_anyway);
                        this.snoozeLoaderView = (SnoozeLoaderView) this.snoozeLayout.findViewById(R.id.snooze_loader_view);
                        this.snoozeLoaderView.setVisibility(8);
                        textView4.setVisibility(0);
                        textView3.setVisibility(0);
                        button.setVisibility(0);
                        button2.setVisibility(0);
                        textView.setVisibility(0);
                        textView2.setVisibility(8);
                        textView8.setVisibility(0);
                        textView5.setVisibility(8);
                        textView6.setVisibility(8);
                        button3.setVisibility(8);
                        textView.setText(this.activity.getString(R.string.cb_slownetwork_status));
                        textView7.setText(this.activity.getString(R.string.cb_try_later));
                        textView8.setText(this.activity.getString(R.string.cb_retry_restart));
                        if (!this.backwardJourneyStarted || !this.payuPG) {
                            this.snoozeVisibleCountFwdJourney++;
                        } else {
                            textView.setText(this.activity.getResources().getString(R.string.cb_slow_internet_confirmation));
                            textView2.setText(this.activity.getResources().getString(R.string.cb_receive_sms));
                            textView7.setText(this.activity.getResources().getString(R.string.cb_confirm_transaction));
                            button.setVisibility(8);
                            textView8.setVisibility(8);
                            button2.setVisibility(8);
                            textView3.setVisibility(8);
                            textView.setVisibility(0);
                            textView2.setVisibility(0);
                            textView5.setVisibility(0);
                            textView6.setVisibility(0);
                            button3.setVisibility(8);
                            this.snoozeVisibleCountBackwdJourney++;
                            addEventAnalytics(CBAnalyticsConstant.SNOOZE_BACKWARD_VISIBLE, "Y");
                        }
                        TextView textView9 = textView3;
                        AnonymousClass23 r12 = r0;
                        final TextView textView10 = textView7;
                        final TextView textView11 = textView;
                        Button button4 = button3;
                        final Button button5 = button;
                        TextView textView12 = textView8;
                        final TextView textView13 = textView2;
                        TextView textView14 = textView7;
                        final TextView textView15 = textView5;
                        AnonymousClass23 r0 = new View.OnClickListener() {
                            public void onClick(View view) {
                                Bank.this.addEventAnalytics(CBAnalyticsConstant.SNOOZE_BACKWARD_WINDOW_ACTION, CBAnalyticsConstant.SNOOZE_CONFIRM_DEDUCTION_Y);
                                if (Bank.this.waitingOTPTimer != null) {
                                    Bank.this.waitingOTPTimer.cancel();
                                    Bank.this.waitingOTPTimer.purge();
                                }
                                Bank.this.snoozeCountBackwardJourney++;
                                Bank.this.snoozeDialog.setCanceledOnTouchOutside(false);
                                textView10.setText(Bank.this.activity.getResources().getString(R.string.cb_confirm_transaction));
                                textView11.setText(Bank.this.activity.getString(R.string.cb_transaction_status));
                                Bank.this.snoozeLoaderView.setVisibility(0);
                                Bank.this.snoozeLoaderView.startAnimation();
                                button5.setVisibility(8);
                                textView13.setVisibility(8);
                                textView15.setVisibility(8);
                                textView6.setVisibility(8);
                                if (Bank.this.verificationMsgReceived) {
                                    Bank.this.startSnoozeServiceVerifyPayment(Bank.this.activity.getResources().getString(R.string.cb_verify_message_received));
                                } else {
                                    Bank.this.startSnoozeServiceVerifyPayment(Bank.this.activity.getResources().getString(R.string.cb_user_input_confirm_transaction));
                                }
                            }
                        };
                        textView5.setOnClickListener(r12);
                        textView6.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Bank.this.snoozeCountBackwardJourney++;
                                Bank.this.dismissSnoozeWindow();
                                Bank.this.addEventAnalytics(CBAnalyticsConstant.SNOOZE_BACKWARD_WINDOW_ACTION, CBAnalyticsConstant.SNOOZE_CONFIRM_DEDUCTION_N);
                            }
                        });
                        textView4.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (Bank.this.backwardJourneyStarted) {
                                    Bank.this.snoozeCountBackwardJourney++;
                                } else {
                                    Bank.this.snoozeCount++;
                                }
                                Bank.this.addEventAnalytics(CBAnalyticsConstant.SNOOZE_INTERACTION_TIME, "-1");
                                if (!Bank.this.backwardJourneyStarted) {
                                    Bank.this.addEventAnalytics(CBAnalyticsConstant.SNOOZE_WINDOW_ACTION, CBAnalyticsConstant.SNOOZE_CANCEL_WINDOW_CLICK);
                                }
                                Bank.this.dismissSnoozeWindow();
                            }
                        });
                        button2.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Bank.this.hideCB();
                                Bank.this.retryPayment(view);
                            }
                        });
                        final TextView textView16 = textView4;
                        final TextView textView17 = textView9;
                        final Button button6 = button2;
                        final TextView textView18 = textView;
                        final TextView textView19 = textView12;
                        final TextView textView20 = textView14;
                        final Button button7 = button4;
                        button.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Bank.this.snoozeCount++;
                                Bank.this.addEventAnalytics(CBAnalyticsConstant.SNOOZE_INTERACTION_TIME, "-1");
                                Bank.this.maximiseWebviewHeight();
                                Bank.this.frameState = 1;
                                if (Bank.this.cbSlideBarView != null) {
                                    Bank.this.cbSlideBarView.setVisibility(8);
                                }
                                Bank.this.onHelpUnavailable();
                                Bank.this.snoozeClickedTime = System.currentTimeMillis();
                                Bank.this.isSnoozeBroadCastReceiverRegistered = true;
                                boolean unused = Bank.this.isPageStoppedForcefully = true;
                                Bank.this.cbWebView.stopLoading();
                                if (!(CustomBrowserData.SINGLETON == null || CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback() == null)) {
                                    Bank.this.bindService();
                                }
                                Bank.this.mPassword = null;
                                Bank.this.unregisterBroadcast(Bank.this.mBroadcastReceiver);
                                textView16.setVisibility(8);
                                textView17.setVisibility(8);
                                button5.setVisibility(8);
                                button6.setVisibility(8);
                                textView18.setText("We have paused your transaction because the network was unable to process it now. We will notify you when the network improves.");
                                textView19.setVisibility(8);
                                textView20.setText(Bank.this.activity.getResources().getString(R.string.cb_transaction_paused));
                                textView2.setVisibility(0);
                                button7.setVisibility(0);
                                Bank.this.progressBarVisibilityPayuChrome(8, "");
                                Bank.this.addEventAnalytics(CBAnalyticsConstant.SNOOZE_WINDOW_ACTION, CBAnalyticsConstant.SNOOZE_CLICK);
                                Bank.this.addEventAnalytics(CBAnalyticsConstant.SNOOZE_LOAD_URL, Bank.this.cbWebView.getUrl() == null ? Bank.this.webviewUrl : Bank.this.cbWebView.getUrl());
                                Bank.this.slowUserCountDownTimer = null;
                                Bank.this.showCbBlankOverlay(0);
                            }
                        });
                        textView9.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (Bank.this.backwardJourneyStarted) {
                                    Bank.this.snoozeCountBackwardJourney++;
                                } else {
                                    Bank.this.snoozeCount++;
                                }
                                Bank.this.addEventAnalytics(CBAnalyticsConstant.SNOOZE_INTERACTION_TIME, "-1");
                                Bank.this.addEventAnalytics(CBAnalyticsConstant.SNOOZE_WINDOW_ACTION, CBAnalyticsConstant.SNOOZE_CANCEL_TRANSACTION_CLICK);
                                Bank.this.showBackButtonDialog();
                            }
                        });
                        button4.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Bank.this.hideCB();
                                Bank.this.retryPayment(view);
                            }
                        });
                        if (this.snoozeDialog == null || !this.snoozeDialog.isShowing()) {
                            this.snoozeDialog = new AlertDialog.Builder(this.activity).create();
                            this.snoozeDialog.setView(this.snoozeLayout);
                            this.snoozeDialog.setCanceledOnTouchOutside(false);
                            this.snoozeDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                public void onDismiss(DialogInterface dialogInterface) {
                                    Bank.this.showCbBlankOverlay(8);
                                }
                            });
                            this.snoozeDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                                    if (i != 4 || keyEvent.getAction() != 0) {
                                        return true;
                                    }
                                    Bank.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, CBAnalyticsConstant.PAYU_BACK_BUTTON_CLICK.toLowerCase());
                                    Bank.this.showBackButtonDialog();
                                    return true;
                                }
                            });
                        }
                        this.snoozeDialog.show();
                    }
                }
            }
        }
    }

    public void bindService() {
        LocalBroadcastManager.getInstance(this.activity).unregisterReceiver(this.snoozeBroadCastReceiver);
        LocalBroadcastManager.getInstance(this.activity.getApplicationContext()).registerReceiver(this.snoozeBroadCastReceiver, new IntentFilter(this.SNOOZE_GET_WEBVIEW_STATUS_INTENT_ACTION));
        Intent intent = new Intent(this.activity, SnoozeService.class);
        intent.putExtra(CBConstant.CB_CONFIG, this.customBrowserConfig);
        intent.putExtra(CBConstant.CURRENT_URL, this.webviewUrl);
        intent.putExtra(CBConstant.MERCHANT_CHECKOUT_ACTIVITY, this.customBrowserConfig.getMerchantCheckoutActivityPath());
        this.isSnoozeServiceBounded = true;
        this.activity.bindService(intent, this.snoozeServiceConnection, 1);
        this.activity.startService(intent);
    }

    public void startSnoozeServiceVerifyPayment(String str) {
        LocalBroadcastManager.getInstance(this.activity).unregisterReceiver(this.snoozeBroadCastReceiver);
        LocalBroadcastManager.getInstance(this.activity.getApplicationContext()).registerReceiver(this.snoozeBroadCastReceiver, new IntentFilter(this.SNOOZE_GET_WEBVIEW_STATUS_INTENT_ACTION));
        Intent intent = new Intent(this.activity, SnoozeService.class);
        intent.putExtra(CBConstant.CB_CONFIG, this.customBrowserConfig);
        intent.putExtra(CBConstant.VERIFICATION_MSG_RECEIVED, true);
        intent.putExtra(CBConstant.MERCHANT_CHECKOUT_ACTIVITY, this.customBrowserConfig.getMerchantCheckoutActivityPath());
        intent.putExtra(CBConstant.VERIFY_ADDON_PARAMS, str);
        this.isSnoozeServiceBounded = true;
        this.activity.bindService(intent, this.snoozeServiceConnection, 1);
        this.isSnoozeBroadCastReceiverRegistered = true;
        this.activity.startService(intent);
    }

    public void dismissSnoozeWindow() {
        this.isSnoozeWindowVisible = false;
        if (this.snoozeDialog != null) {
            this.snoozeDialog.dismiss();
            this.snoozeDialog.cancel();
            showCbBlankOverlay(8);
        }
    }

    public void setMagicRetry(Map<String, String> map) {
        if (this.magicRetryFragment != null) {
            this.magicRetryFragment.setUrlListWithPostData(map);
        }
    }

    public void initMagicRetry() {
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        this.magicRetryFragment = new MagicRetryFragment();
        Bundle bundle = new Bundle();
        if (!(CustomBrowserData.SINGLETON == null || CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback() == null)) {
            bundle.putString("transaction_id", this.customBrowserConfig.getTransactionID());
        }
        this.magicRetryFragment.setArguments(bundle);
        supportFragmentManager.beginTransaction().add(R.id.magic_retry_container, this.magicRetryFragment, "magicRetry").commit();
        toggleFragmentVisibility(0);
        this.magicRetryFragment.isWhiteListingEnabled(true);
        this.magicRetryFragment.setWebView(this.cbWebView);
        this.magicRetryFragment.initMRSettingsFromSharedPreference(this.activity);
        if (this.customBrowserConfig.getEnableSurePay() > 0) {
            this.cbWebView.setWebViewClient(new PayUSurePayWebViewClient(this, keyAnalytics));
        } else {
            this.cbWebView.setWebViewClient(new PayUWebViewClient(this, this.magicRetryFragment, keyAnalytics));
        }
    }

    public void toggleFragmentVisibility(int i) {
        if (getActivity() != null && !getActivity().isFinishing() && isAdded() && !isRemoving()) {
            FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            if (this.magicRetryFragment != null && i == 1) {
                beginTransaction.show(this.magicRetryFragment).commitAllowingStateLoss();
            } else if (this.magicRetryFragment != null && i == 0) {
                beginTransaction.hide(this.magicRetryFragment).commitAllowingStateLoss();
            }
        }
    }

    public void showMagicRetry() {
        dismissSnoozeWindow();
        toggleFragmentVisibility(1);
    }

    public void hideMagicRetry() {
        toggleFragmentVisibility(0);
    }

    public void showBackButtonDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setCancelable(false);
        builder.setMessage("Do you really want to cancel the transaction ?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Bank.this.postToPaytxn();
                if (Bank.this.snoozeDialog != null && Bank.this.snoozeDialog.isShowing()) {
                    Bank.this.snoozeDialog.cancel();
                }
                Bank.this.killSnoozeService();
                Bank.this.cancelTransactionNotification();
                Bank.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, CBAnalyticsConstant.BACK_BUTTON_OK_CLICK);
                Bank.this.dismissSnoozeWindow();
                if (!(CustomBrowserData.SINGLETON == null || CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback() == null)) {
                    CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback().onBackApprove();
                }
                Bank.this.activity.finish();
            }
        });
        builder.setNegativeButton(WXModalUIModule.CANCEL, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Bank.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, CBAnalyticsConstant.BACK_BUTTON_CANCEL_CLICK);
                dialogInterface.dismiss();
                if (CustomBrowserData.SINGLETON != null && CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback() != null) {
                    CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback().onBackDismiss();
                }
            }
        });
        if (!(CustomBrowserData.SINGLETON == null || CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback() == null)) {
            CustomBrowserData.SINGLETON.getPayuCustomBrowserCallback().onBackButton(builder);
        }
        builder.create().getWindow().getAttributes().type = 2003;
        builder.show();
    }

    public void setIsPageStoppedForcefully(boolean z) {
        this.isPageStoppedForcefully = z;
    }

    private void updateWhitelistedRetryUrls(List<String> list) {
        whiteListedUrls.clear();
        L.v("#### PAYU", "MR Cleared whitelisted urls, length: " + whiteListedUrls.size());
        whiteListedUrls.addAll(list);
        L.v("#### PAYU", "MR Updated whitelisted urls, length: " + whiteListedUrls.size());
    }

    @JavascriptInterface
    public void setSnoozeConfig(String str) {
        this.snoozeConfigMap = this.cbUtil.storeSnoozeConfigInSharedPref(this.activity.getApplicationContext(), str);
    }

    @JavascriptInterface
    public void dismissPayULoader() {
        if (this.activity != null && !this.activity.isFinishing() && this.progressDialog != null) {
            this.progressDialog.dismiss();
            this.progressDialog.cancel();
            if (!this.webpageNotFoundError) {
                this.forwardJourneyForChromeLoaderIsComplete = true;
                L.v("stag", "Setting forwardJourneyForChromeLoaderIsComplete = " + this.forwardJourneyForChromeLoaderIsComplete);
                startSlowUserWarningTimer();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void startSlowUserWarningTimer() {
        L.v("sTag", "Attempting to start slowUserCountDownTimer");
        if (this.slowUserCountDownTimer == null) {
            L.v("sTag", "Starting slowUserCountDownTimer");
        }
    }

    /* access modifiers changed from: protected */
    public void dismissSlowUserWarningTimer() {
        if (this.slowUserCountDownTimer != null) {
            L.v("sTag", "Shutting down slowUserCountDownTimer");
            this.slowUserCountDownTimer.cancel();
        }
    }

    public class ButtonOnclickListener implements View.OnClickListener {
        private View view_edit;

        public ButtonOnclickListener() {
        }

        public void setView(View view) {
            this.view_edit = view;
        }

        public void onClick(View view) {
            String str = "";
            if (view instanceof Button) {
                str = ((Button) view).getText().toString();
            } else if (view instanceof TextView) {
                str = ((TextView) view).getText().toString();
            }
            switch (Bank.this.getCode(str.toLowerCase())) {
                case 1:
                case 3:
                    Bank.this.pin_selected_flag = true;
                    Bank.this.approve_flag = true;
                    Bank.this.maximiseWebviewHeight();
                    Bank.this.frameState = 1;
                    Bank.this.onHelpUnavailable();
                    if (Bank.this.cbSlideBarView != null) {
                        Bank.this.cbSlideBarView.setVisibility(8);
                    }
                    if (Bank.this.cbTransparentView != null) {
                        Bank.this.cbTransparentView.setVisibility(8);
                    }
                    try {
                        WebView webView = Bank.this.cbWebView;
                        webView.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.string.cb_pin)));
                        Bank.this.eventRecorded = CBAnalyticsConstant.PASSWORD;
                        Bank.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, Bank.this.eventRecorded);
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return;
                    }
                case 2:
                    try {
                        Bank.this.eventRecorded = CBAnalyticsConstant.REGENERATE;
                        Bank.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, Bank.this.eventRecorded);
                        Bank.this.mPassword = null;
                        WebView webView2 = Bank.this.cbWebView;
                        webView2.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.string.cb_regen_otp)));
                        Bank.this.prepareSmsListener();
                        return;
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        return;
                    }
                case 4:
                    final View inflate = Bank.this.activity.getLayoutInflater().inflate(R.layout.wait_for_otp, (ViewGroup) null);
                    Bank.this.eventRecorded = CBAnalyticsConstant.ENTER_MANUALLY;
                    Bank.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, Bank.this.eventRecorded);
                    if (Bank.this.chooseActionHeight == 0) {
                        inflate.measure(-2, -2);
                        Bank.this.chooseActionHeight = inflate.getMeasuredHeight();
                    }
                    Bank.this.cbBaseView.removeAllViews();
                    Bank.this.cbBaseView.addView(inflate);
                    if (Bank.this.cbBaseView.isShown()) {
                        Bank.this.frameState = 2;
                    } else {
                        Bank.this.maximiseWebviewHeight();
                    }
                    ImageView imageView = (ImageView) inflate.findViewById(R.id.bank_logo);
                    imageView.setOnClickListener(Bank.this.viewOnClickListener);
                    if (Bank.this.drawable != null) {
                        imageView.setImageDrawable(Bank.this.drawable);
                    }
                    inflate.findViewById(R.id.waiting).setVisibility(8);
                    final Button button = (Button) inflate.findViewById(R.id.approve);
                    button.setClickable(false);
                    EditText editText = (EditText) inflate.findViewById(R.id.otp_sms);
                    Bank.this.showSoftKeyboard(editText);
                    CBUtil.setAlpha(0.3f, button);
                    button.setVisibility(0);
                    editText.setVisibility(0);
                    inflate.findViewById(R.id.regenerate_layout).setVisibility(8);
                    inflate.findViewById(R.id.progress).setVisibility(4);
                    Bank.this.showSoftKeyboard(editText);
                    editText.addTextChangedListener(new TextWatcher() {
                        public void afterTextChanged(Editable editable) {
                        }

                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                            if (((EditText) inflate.findViewById(R.id.otp_sms)).getText().toString().length() > 5) {
                                Bank.this.buttonClickListener.setView(inflate);
                                button.setOnClickListener(Bank.this.buttonClickListener);
                                button.setClickable(true);
                                CBUtil.setAlpha(1.0f, button);
                                return;
                            }
                            button.setClickable(false);
                            CBUtil.setAlpha(0.3f, button);
                            button.setOnClickListener((View.OnClickListener) null);
                        }
                    });
                    Bank.this.updateHeight(inflate);
                    return;
                case 5:
                    try {
                        Bank.this.hideKeyboardForcefully();
                        Bank.this.mPassword = null;
                        Bank.this.checkLoading = false;
                        Bank.this.approve_flag = true;
                        Bank.this.onHelpUnavailable();
                        Bank.this.maximiseWebviewHeight();
                        Bank.this.frameState = 1;
                        Bank.this.prepareSmsListener();
                        if (((EditText) this.view_edit.findViewById(R.id.otp_sms)).getText().toString().length() > 5) {
                            Bank.this.eventRecorded = CBAnalyticsConstant.APPROVED_OTP;
                            Bank.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, Bank.this.eventRecorded);
                            Bank.this.addEventAnalytics(CBAnalyticsConstant.APPROVE_BTN_CLICK_TIME, "-1");
                            WebView webView3 = Bank.this.cbWebView;
                            webView3.loadUrl("javascript:" + Bank.this.mJS.getString(Bank.this.getString(R.string.cb_process_otp)) + "(\"" + ((TextView) Bank.this.activity.findViewById(R.id.otp_sms)).getText().toString() + "\")");
                            ((EditText) this.view_edit.findViewById(R.id.otp_sms)).setText("");
                            return;
                        }
                        return;
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                        return;
                    }
                case 6:
                case 7:
                    Bank.this.SMSOTPClicked = true;
                    Bank.this.checkPermission();
                    Bank.this.eventRecorded = CBAnalyticsConstant.OTP;
                    Bank.this.addEventAnalytics(CBAnalyticsConstant.USER_INPUT, Bank.this.eventRecorded);
                    if (Build.VERSION.SDK_INT < 23) {
                        Bank.this.mPassword = null;
                        Bank.this.prepareSmsListener();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void reloadWVUsingJS() {
        this.cbWebView.loadUrl("javascript:window.location.reload(true)");
    }

    public void reloadWVNative() {
        this.cbWebView.reload();
    }

    public void reloadWVUsingJSFromCache() {
        this.cbWebView.loadUrl("javascript:window.location.reload()");
    }

    /* access modifiers changed from: private */
    public void retryPayment(View view) {
        if (view.getId() == R.id.button_retry_transaction) {
            this.snoozeCount++;
            addEventAnalytics(CBAnalyticsConstant.SNOOZE_INTERACTION_TIME, "-1");
            addEventAnalytics(CBAnalyticsConstant.SNOOZE_WINDOW_ACTION, CBAnalyticsConstant.SNOOZE_RETRY_CLICK);
        } else if (view.getId() == R.id.button_retry_anyway) {
            addEventAnalytics(CBAnalyticsConstant.SNOOZE_TXN_PAUSED_USER_INTERACTION_TIME, "-1");
            addEventAnalytics(CBAnalyticsConstant.SNOOZE_TXN_PAUSED_WINDOW_ACTION, CBAnalyticsConstant.RETRY_ANYWAY_CLICK);
        }
        setTransactionStatusReceived(false);
        if (CBUtil.isNetworkAvailable(this.activity.getApplicationContext())) {
            if (this.cbWebView.getUrl() == null || this.cbWebView.getUrl().contentEquals("https://secure.payu.in/_payment") || this.cbWebView.getUrl().contentEquals(CBConstant.PRODUCTION_PAYMENT_URL_SEAMLESS) || !isUrlWhiteListed(this.cbWebView.getUrl())) {
                this.cbUtil.clearCookie();
                if (this.customBrowserConfig.getPostURL().contentEquals("https://secure.payu.in/_payment") || this.customBrowserConfig.getPostURL().contentEquals("https://mobiletest.payu.in/_payment")) {
                    markPreviousTxnAsUserCanceled(this.cbUtil.getLogMessage(this.activity.getApplicationContext(), CBAnalyticsConstant.SURE_PAY_CANCELLED, this.customBrowserConfig.getTransactionID(), "", keyAnalytics, this.customBrowserConfig.getTransactionID(), ""));
                }
                reloadWebView(this.customBrowserConfig.getPostURL(), this.customBrowserConfig.getPayuPostData());
            } else {
                reloadWebView();
            }
            dismissSnoozeWindow();
            this.slowUserCountDownTimer = null;
            if (view.getId() == R.id.button_retry_anyway) {
                killSnoozeService();
                ((NotificationManager) this.activity.getSystemService(PushManager.MESSAGE_TYPE_NOTI)).cancel(CBConstant.SNOOZE_NOTIFICATION_ID);
                return;
            }
            return;
        }
        Toast.makeText(this.activity.getApplicationContext(), CBConstant.MSG_NO_INTERNET, 0).show();
    }

    @JavascriptInterface
    public void spResumedWindowTTL(String str) {
        try {
            this.mInternetRestoredWindowTTL = Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSurePayResumeStatus(boolean z) {
        this.isSurePayResumed = z;
    }
}
