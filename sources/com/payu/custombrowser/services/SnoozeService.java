package com.payu.custombrowser.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Binder;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import com.coloros.mcssdk.PushManager;
import com.payu.custombrowser.Bank;
import com.payu.custombrowser.CBActivity;
import com.payu.custombrowser.R;
import com.payu.custombrowser.bean.CustomBrowserConfig;
import com.payu.custombrowser.util.CBAnalyticsConstant;
import com.payu.custombrowser.util.CBConstant;
import com.payu.custombrowser.util.CBUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Random;
import javax.net.ssl.SSLException;
import net.qiujuer.genius.ui.Ui;
import org.json.JSONException;
import org.json.JSONObject;

public class SnoozeService extends Service {
    /* access modifiers changed from: private */
    public static int IMAGE_DOWNLOAD_TIME_OUT;
    private final String CURRENT_URL = CBConstant.CURRENT_URL;
    /* access modifiers changed from: private */
    public int EXPONENTIAL_BACKOFF_TIME_THRESHOLD = 60000;
    String MERCHANT_CHECKOUT_ACTIVITY = CBConstant.MERCHANT_CHECKOUT_ACTIVITY;
    private final String SNOOZE_BROAD_CAST_MESSAGE = "snooze_broad_cast_message";
    private final String SNOOZE_GET_WEBVIEW_STATUS_INTENT_ACTION = "webview_status_action";
    /* access modifiers changed from: private */
    public int SNOOZE_SERVICE_TTL = 1800000;
    private final int TRACK_WEB_VIEW_TIME_INTERVAL = 500;
    /* access modifiers changed from: private */
    public String broadCastingMessage = "";
    /* access modifiers changed from: private */
    public CBUtil cbUtil;
    private String currentUrl = "";
    private CustomBrowserConfig customBrowserConfig;
    /* access modifiers changed from: private */
    public long endTime;
    /* access modifiers changed from: private */
    public int exponentialBackOffTime = 1000;
    /* access modifiers changed from: private */
    public Handler handler;
    /* access modifiers changed from: private */
    public long imageDownloadTime;
    /* access modifiers changed from: private */
    public boolean isImageDownloadTimedOut;
    /* access modifiers changed from: private */
    public boolean isNotificationIntentPrepared;
    /* access modifiers changed from: private */
    public boolean isServiceAlive = true;
    /* access modifiers changed from: private */
    public boolean isWebViewAlive;
    /* access modifiers changed from: private */
    public boolean isWebViewIntentPrepared;
    /* access modifiers changed from: private */
    public CountDownTimer killSnoozeServiceCounter;
    private ServiceHandler mServiceHandler;
    private Looper mServiceLooper;
    private String merchantCheckoutActivity;
    /* access modifiers changed from: private */
    public String payuResponse;
    /* access modifiers changed from: private */
    public String postData = "";
    private HashMap<String, String> postParamsMap;
    private String postURL = "";
    /* access modifiers changed from: private */
    public String receivedMessage = "";
    /* access modifiers changed from: private */
    public Runnable runnable;
    private final IBinder snoozeBinder = new SnoozeBinder();
    private HandlerThread snoozeHandlerThread;
    /* access modifiers changed from: private */
    public long startTime;
    /* access modifiers changed from: private */
    public long timeToNotify;
    /* access modifiers changed from: private */
    public Handler trackWebViewStatusHandler;
    /* access modifiers changed from: private */
    public String verifyParam;
    /* access modifiers changed from: private */
    public boolean verifyPaymentCheck;
    /* access modifiers changed from: private */
    public Runnable verifyPaymentRunnable = new Runnable() {
        public void run() {
            StringBuffer stringBufferFromInputStream;
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(SnoozeService.this.verifyURL).openConnection();
                String str = "command=verifyTxnStatus&var1=" + SnoozeService.this.cbUtil.getDataFromPostData(SnoozeService.this.postData, "txnid") + "&key=" + SnoozeService.this.cbUtil.getDataFromPostData(SnoozeService.this.postData, "key") + "&priorityParam=" + SnoozeService.this.verifyParam;
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setConnectTimeout(120000);
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(str.length()));
                httpURLConnection.setRequestProperty("Cookie", "PHPSESSID=" + SnoozeService.this.cbUtil.getCookie(CBConstant.PHPSESSID, SnoozeService.this.getApplicationContext()) + "; PAYUID=" + SnoozeService.this.cbUtil.getCookie(CBConstant.PAYUID, SnoozeService.this.getApplicationContext()));
                httpURLConnection.setDoOutput(true);
                httpURLConnection.getOutputStream().write(str.getBytes());
                byte[] bArr = new byte[1024];
                if (httpURLConnection.getResponseCode() != 200) {
                    SnoozeService.this.onTransactionStatusReceived("{\"api_status\":\"0\",\"message\":\"Some error occurred\"}");
                } else if (httpURLConnection.getInputStream() != null && (stringBufferFromInputStream = CBUtil.getStringBufferFromInputStream(httpURLConnection.getInputStream())) != null) {
                    new JSONObject(stringBufferFromInputStream.toString());
                    String unused = SnoozeService.this.payuResponse = stringBufferFromInputStream.toString();
                    SnoozeService.this.onTransactionStatusReceived(stringBufferFromInputStream.toString());
                }
            } catch (Exception e) {
                SnoozeService.this.onTransactionStatusReceived("{\"api_status\":\"0\",\"message\":\"Some exception occurred\"}");
                e.printStackTrace();
            }
        }
    };
    /* access modifiers changed from: private */
    public String verifyURL = "https://info.payu.in/merchant/postservice?form=2";

    /* access modifiers changed from: private */
    public void onTransactionStatusReceived(String str) {
        try {
            String valueOfJSONKey = this.cbUtil.getValueOfJSONKey(str, getString(R.string.cb_snooze_verify_api_status));
            if (CBActivity.STATE == 2) {
                if (valueOfJSONKey.contentEquals("1")) {
                    broadcastAnalyticsEvent(CBAnalyticsConstant.TRANSACTION_VERIFIED_NOTIFICATION, "-1");
                } else {
                    broadcastAnalyticsEvent(CBAnalyticsConstant.TRANSACTION_NOT_VERIFIED_NOTIFICATION, "-1");
                }
                launchNotificationTransactionUpdate(str);
                return;
            }
            if (valueOfJSONKey.contentEquals("1")) {
                broadcastAnalyticsEvent(CBAnalyticsConstant.TRANSACTION_VERIFIED_DIALOG_FOREGROUND, "-1");
            } else {
                broadcastAnalyticsEvent(CBAnalyticsConstant.TRANSACTION_NOT_VERIFIED_DIALOG_FOREGROUND, "-1");
            }
            broadcastEvent(CBConstant.BACKWARD_JOURNEY_STATUS, str, false);
            killSnoozeService();
        } catch (JSONException e) {
            e.printStackTrace();
            if (CBActivity.STATE == 2) {
                broadcastAnalyticsEvent(CBAnalyticsConstant.TRANSACTION_NOT_VERIFIED_NOTIFICATION, "-1");
                launchNotificationTransactionUpdate(str);
                return;
            }
            broadcastAnalyticsEvent(CBAnalyticsConstant.TRANSACTION_NOT_VERIFIED_DIALOG_FOREGROUND, "-1");
            broadcastEvent(CBConstant.BACKWARD_JOURNEY_STATUS, str, false);
            killSnoozeService();
        }
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        this.isWebViewAlive = true;
        return this.snoozeBinder;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this.cbUtil = new CBUtil();
        this.merchantCheckoutActivity = intent.getStringExtra(this.MERCHANT_CHECKOUT_ACTIVITY);
        this.customBrowserConfig = (CustomBrowserConfig) intent.getParcelableExtra(CBConstant.CB_CONFIG);
        this.SNOOZE_SERVICE_TTL = this.customBrowserConfig.getSurePayBackgroundTTL();
        this.postParamsMap = this.cbUtil.getDataFromPostData(this.customBrowserConfig.getPayuPostData());
        IMAGE_DOWNLOAD_TIME_OUT = Bank.snoozeImageDownloadTimeout > 0 ? Bank.snoozeImageDownloadTimeout : 10000;
        if (!intent.getExtras().containsKey(CBConstant.VERIFICATION_MSG_RECEIVED) || !intent.getExtras().getBoolean(CBConstant.VERIFICATION_MSG_RECEIVED)) {
            this.verifyPaymentCheck = false;
            this.currentUrl = intent.getStringExtra(CBConstant.CURRENT_URL);
        } else {
            this.verifyPaymentCheck = true;
            if (intent.getExtras().containsKey(CBConstant.VERIFY_ADDON_PARAMS)) {
                this.verifyParam = intent.getExtras().getString(CBConstant.VERIFY_ADDON_PARAMS);
            }
            this.postData = this.customBrowserConfig.getPayuPostData();
            this.postURL = this.customBrowserConfig.getPostURL();
        }
        Message obtainMessage = this.mServiceHandler.obtainMessage();
        obtainMessage.arg1 = i2;
        this.mServiceHandler.sendMessage(obtainMessage);
        return 3;
    }

    public void onCreate() {
        this.snoozeHandlerThread = new HandlerThread("SnoozeServiceHandlerThread", 10);
        this.snoozeHandlerThread.start();
        this.mServiceLooper = this.snoozeHandlerThread.getLooper();
        this.mServiceHandler = new ServiceHandler(this.mServiceLooper);
    }

    public void killSnoozeService() {
        this.isServiceAlive = false;
        if (this.killSnoozeServiceCounter != null) {
            this.killSnoozeServiceCounter.cancel();
        }
        this.snoozeHandlerThread.interrupt();
        stopSelf();
    }

    public void updateWebviewStatus(String str) {
        this.receivedMessage = str;
    }

    /* access modifiers changed from: private */
    public void calculateInternetSpeed() {
        this.handler = new Handler(this.mServiceLooper);
        this.runnable = new Runnable() {
            public void run() {
                if (SnoozeService.this.isServiceAlive) {
                    SnoozeService.this.downloadImage();
                }
            }
        };
        this.handler.postDelayed(this.runnable, (long) Math.min(this.exponentialBackOffTime, this.EXPONENTIAL_BACKOFF_TIME_THRESHOLD));
    }

    /* access modifiers changed from: private */
    public void downloadImage() {
        this.isImageDownloadTimedOut = false;
        final String str = CBConstant.SNOOZE_IMAGE_DOWNLOAD_END_POINT + CBConstant.SNOOZE_IMAGE_COLLECTIONS[new Random().nextInt(2)];
        final AnonymousClass3 r1 = new CountDownTimer((long) IMAGE_DOWNLOAD_TIME_OUT, 1000) {
            public void onTick(long j) {
            }

            public void onFinish() {
                cancel();
                boolean unused = SnoozeService.this.isImageDownloadTimedOut = true;
            }
        };
        r1.start();
        new Thread(new Runnable() {
            public void run() {
                try {
                    CBUtil unused = SnoozeService.this.cbUtil;
                    if (CBUtil.isNetworkAvailable(SnoozeService.this.getApplicationContext())) {
                        long unused2 = SnoozeService.this.startTime = System.currentTimeMillis();
                        URLConnection openConnection = new URL(str).openConnection();
                        openConnection.setUseCaches(false);
                        openConnection.connect();
                        openConnection.getContentLength();
                        InputStream inputStream = openConnection.getInputStream();
                        byte[] bArr = new byte[1024];
                        while (!SnoozeService.this.isImageDownloadTimedOut && inputStream.read(bArr) != -1) {
                        }
                        if (SnoozeService.this.isImageDownloadTimedOut) {
                            r1.cancel();
                            inputStream.close();
                            long unused3 = SnoozeService.this.imageDownloadTime = (long) (SnoozeService.IMAGE_DOWNLOAD_TIME_OUT + 1);
                        } else {
                            r1.cancel();
                            long unused4 = SnoozeService.this.endTime = System.currentTimeMillis();
                            inputStream.close();
                            long unused5 = SnoozeService.this.imageDownloadTime = SnoozeService.this.endTime - SnoozeService.this.startTime;
                        }
                        if (SnoozeService.this.imageDownloadTime > ((long) SnoozeService.IMAGE_DOWNLOAD_TIME_OUT)) {
                            int unused6 = SnoozeService.this.exponentialBackOffTime = SnoozeService.this.exponentialBackOffTime + SnoozeService.this.exponentialBackOffTime;
                            SnoozeService.this.handler.postDelayed(SnoozeService.this.runnable, (long) Math.min(SnoozeService.this.exponentialBackOffTime, SnoozeService.this.EXPONENTIAL_BACKOFF_TIME_THRESHOLD));
                        } else if (!SnoozeService.this.isServiceAlive) {
                        } else {
                            if (SnoozeService.this.verifyPaymentCheck) {
                                SnoozeService.this.broadcastAnalyticsEvent(CBAnalyticsConstant.SNOOZE_VERIFY_API_STATUS, CBAnalyticsConstant.SNOOZE_VERIFY_API_CALLED);
                                new Thread(SnoozeService.this.verifyPaymentRunnable).start();
                            } else if (CBActivity.STATE == 1) {
                                SnoozeService.this.broadcastEvent(SnoozeService.this.getString(R.string.internet_restored), SnoozeService.this.getString(R.string.resuming_your_transaction), true);
                                SnoozeService.this.broadcastAnalyticsEvent(CBAnalyticsConstant.INTERNET_RESTORED_DIALOG_FOREGROUND, "-1");
                                SnoozeService.this.killSnoozeService();
                            } else {
                                SnoozeService.this.launchNotification(SnoozeService.this.isWebViewAlive);
                                SnoozeService.this.broadcastAnalyticsEvent(CBAnalyticsConstant.INTERNET_RESTORED_NOTIFICATION, "-1");
                            }
                        }
                    } else {
                        SnoozeService.this.handler.postDelayed(SnoozeService.this.runnable, (long) Math.min(SnoozeService.this.exponentialBackOffTime, SnoozeService.this.EXPONENTIAL_BACKOFF_TIME_THRESHOLD));
                    }
                } catch (MalformedURLException e) {
                    long unused7 = SnoozeService.this.imageDownloadTime = -1;
                    r1.cancel();
                    e.printStackTrace();
                } catch (SSLException e2) {
                    SnoozeService.this.handler.postDelayed(SnoozeService.this.runnable, (long) Math.min(SnoozeService.this.exponentialBackOffTime, SnoozeService.this.EXPONENTIAL_BACKOFF_TIME_THRESHOLD));
                    e2.printStackTrace();
                } catch (IOException e3) {
                    long unused8 = SnoozeService.this.imageDownloadTime = -1;
                    r1.cancel();
                    e3.printStackTrace();
                } catch (Exception unused9) {
                    long unused10 = SnoozeService.this.imageDownloadTime = -1;
                    r1.cancel();
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public void launchNotification(boolean z) {
        boolean z2;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        NotificationCompat.Builder defaults = builder.setContentTitle(this.customBrowserConfig.getSurePayNotificationGoodNetworkTitle()).setContentText(this.customBrowserConfig.getSurePayNotificationGoodNetWorkHeader()).setSmallIcon(this.customBrowserConfig.getSurePayNotificationIcon()).setAutoCancel(true).setPriority(1).setDefaults(2);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        defaults.setStyle(bigTextStyle.bigText(this.customBrowserConfig.getSurePayNotificationGoodNetWorkHeader() + "\n\n" + this.customBrowserConfig.getSurePayNotificationGoodNetWorkBody()));
        if (Build.VERSION.SDK_INT >= 23) {
            builder.setColor(getResources().getColor(R.color.cb_blue_button, (Resources.Theme) null));
        } else {
            builder.setColor(getResources().getColor(R.color.cb_blue_button));
        }
        this.isNotificationIntentPrepared = true;
        Intent intent = new Intent();
        intent.putExtra(CBConstant.CURRENT_URL, this.currentUrl);
        intent.putExtra(CBConstant.SENDER, CBConstant.SNOOZE_SERVICE);
        if (z) {
            this.isWebViewIntentPrepared = true;
            intent.setFlags(536870912);
            intent.putExtra(CBConstant.CURRENT_URL, this.currentUrl);
            intent.putExtra(CBConstant.CB_CONFIG, this.customBrowserConfig);
            intent.setClass(getApplicationContext(), CBActivity.class);
            z2 = true;
        } else {
            Intent intent2 = new Intent();
            intent2.setClassName(getApplicationContext(), this.merchantCheckoutActivity == null ? "" : this.merchantCheckoutActivity);
            if (intent2.resolveActivityInfo(getPackageManager(), 0) != null) {
                intent.setClassName(getApplicationContext(), this.merchantCheckoutActivity);
                intent.putExtra(CBConstant.POST_TYPE, "sure_pay_payment_data");
                intent.putExtra("postData", this.customBrowserConfig.getPayuPostData());
                z2 = true;
            } else {
                z2 = false;
            }
            broadcastAnalyticsEvent(CBAnalyticsConstant.SNOOZE_NOTIFICATION_EXPECTED_ACTION, CBAnalyticsConstant.MERCHANT_CHECKOUT_PAGE);
            this.isWebViewIntentPrepared = false;
            killSnoozeService();
        }
        if (z2) {
            try {
                builder.setContentIntent(PendingIntent.getActivity(getApplicationContext(), 0, intent, 134217728));
                ((NotificationManager) getSystemService(PushManager.MESSAGE_TYPE_NOTI)).notify(CBConstant.SNOOZE_NOTIFICATION_ID, builder.build());
                broadcastEvent(CBConstant.GOOD_NETWORK_NOTIFICATION_LAUNCHED, "true", true);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            throw new ActivityNotFoundException("The Activity " + this.merchantCheckoutActivity + " is not found, Please set valid activity ");
        }
    }

    /* access modifiers changed from: private */
    public void broadcastAnalyticsEvent(String str, String str2) {
        Intent intent = new Intent("webview_status_action");
        intent.putExtra(CBAnalyticsConstant.BROAD_CAST_FROM_SNOOZE_SERVICE, true);
        intent.putExtra(CBAnalyticsConstant.KEY, str);
        intent.putExtra(CBAnalyticsConstant.VALUE, str2);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    public void launchNotificationTransactionUpdate(String str) {
        StringBuilder sb;
        try {
            String valueOfJSONKey = this.cbUtil.getValueOfJSONKey(str, getString(R.string.cb_snooze_verify_api_status));
            broadcastAnalyticsEvent(CBAnalyticsConstant.SNOOZE_VERIFY_API_RESPONSE_RECEIVED, valueOfJSONKey + "");
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            if (valueOfJSONKey.contentEquals("1")) {
                sb = new StringBuilder();
                sb.append(this.customBrowserConfig.getSurePayNotificationTransactionVerifiedHeader());
                sb.append("\n\n");
                sb.append(this.customBrowserConfig.getSurePayNotificationTransactionVerifiedBody());
            } else {
                sb = new StringBuilder();
                sb.append(this.customBrowserConfig.getSurePayNotificationTransactionNotVerifiedHeader());
                sb.append("\n\n");
                sb.append(this.customBrowserConfig.getSurePayNotificationTransactionNotVerifiedBody());
            }
            boolean z = true;
            builder.setContentTitle(valueOfJSONKey.contentEquals("1") ? this.customBrowserConfig.getSurePayNotificationTransactionVerifiedTitle() : this.customBrowserConfig.getSurePayNotificationTransactionNotVerifiedTitle()).setContentText(valueOfJSONKey.contentEquals("1") ? this.customBrowserConfig.getSurePayNotificationTransactionVerifiedHeader() : this.customBrowserConfig.getSurePayNotificationTransactionNotVerifiedHeader()).setSmallIcon(this.customBrowserConfig.getSurePayNotificationIcon()).setAutoCancel(true).setPriority(1).setDefaults(2).setStyle(new NotificationCompat.BigTextStyle().bigText(sb.toString()));
            Intent intent = new Intent();
            intent.putExtra(CBConstant.CB_CONFIG, this.customBrowserConfig);
            this.isNotificationIntentPrepared = true;
            intent.putExtra(CBConstant.PAYU_RESPONSE, str);
            if (this.isWebViewAlive) {
                intent.setFlags(Ui.b);
                this.isWebViewIntentPrepared = true;
                intent.putExtra(CBConstant.SENDER, CBConstant.SNOOZE_SERVICE);
                intent.putExtra(CBConstant.VERIFICATION_MSG_RECEIVED, true);
                intent.setClass(getApplicationContext(), CBActivity.class);
            } else {
                Intent intent2 = new Intent();
                intent2.setClassName(getApplicationContext(), this.merchantCheckoutActivity == null ? "" : this.merchantCheckoutActivity);
                if (intent2.resolveActivityInfo(getPackageManager(), 0) != null) {
                    intent.putExtra("postData", str);
                    intent.setClassName(getApplicationContext(), this.merchantCheckoutActivity);
                    intent.putExtra(CBConstant.POST_TYPE, "verify_response_post_data");
                } else {
                    z = false;
                }
                broadcastAnalyticsEvent(CBAnalyticsConstant.SNOOZE_NOTIFICATION_EXPECTED_ACTION, CBAnalyticsConstant.MERCHANT_CHECKOUT_PAGE);
                this.isWebViewIntentPrepared = false;
                killSnoozeService();
            }
            if (z) {
                try {
                    builder.setContentIntent(PendingIntent.getActivity(this, 0, intent, 134217728));
                    ((NotificationManager) getSystemService(PushManager.MESSAGE_TYPE_NOTI)).notify(CBConstant.TRANSACTION_STATUS_NOTIFICATION_ID, builder.build());
                    broadcastEvent(CBConstant.GOOD_NETWORK_NOTIFICATION_LAUNCHED, str, false);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                throw new ActivityNotFoundException("The Activity " + this.merchantCheckoutActivity + " is not found, Please set valid activity ");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void launchNoInternetNotification() {
        broadcastAnalyticsEvent(CBAnalyticsConstant.SNOOZE_NOTIFICATION_EXPECTED_ACTION, CBAnalyticsConstant.MERCHANT_CHECKOUT_PAGE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        NotificationCompat.Builder defaults = builder.setContentTitle(this.customBrowserConfig.getSurePayNotificationPoorNetWorkTitle()).setContentText(this.customBrowserConfig.getSurePayNotificationPoorNetWorkHeader()).setSmallIcon(this.customBrowserConfig.getSurePayNotificationIcon()).setAutoCancel(true).setPriority(1).setDefaults(2);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        defaults.setStyle(bigTextStyle.bigText(this.customBrowserConfig.getSurePayNotificationPoorNetWorkHeader() + this.customBrowserConfig.getSurePayNotificationPoorNetWorkBody()));
        if (Build.VERSION.SDK_INT >= 23) {
            builder.setColor(getResources().getColor(R.color.cb_blue_button, (Resources.Theme) null));
        } else {
            builder.setColor(getResources().getColor(R.color.cb_blue_button));
        }
        Intent intent = new Intent();
        intent.setClassName(getApplicationContext(), this.merchantCheckoutActivity == null ? "" : this.merchantCheckoutActivity);
        if (intent.resolveActivityInfo(getPackageManager(), 0) != null) {
            Intent intent2 = new Intent();
            intent2.setClassName(getApplicationContext(), this.merchantCheckoutActivity);
            intent2.putExtra(CBConstant.POST_TYPE, "sure_pay_payment_data");
            intent2.putExtra("postData", this.customBrowserConfig.getPayuPostData());
            builder.setContentIntent(PendingIntent.getActivity(this, 0, intent2, 134217728));
            ((NotificationManager) getSystemService(PushManager.MESSAGE_TYPE_NOTI)).notify(CBConstant.SNOOZE_NOTIFICATION_ID, builder.build());
            return;
        }
        try {
            throw new ActivityNotFoundException("The Activity " + this.merchantCheckoutActivity + " is not found, Please set valid activity ");
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            boolean unused = SnoozeService.this.isServiceAlive = true;
            CountDownTimer unused2 = SnoozeService.this.killSnoozeServiceCounter = new CountDownTimer((long) SnoozeService.this.SNOOZE_SERVICE_TTL, 5000) {
                public void onTick(long j) {
                    long unused = SnoozeService.this.timeToNotify = (((long) SnoozeService.this.SNOOZE_SERVICE_TTL) - j) / 1000;
                }

                public void onFinish() {
                    if (!SnoozeService.this.isNotificationIntentPrepared && CBActivity.STATE == 2) {
                        SnoozeService.this.launchNoInternetNotification();
                        SnoozeService.this.broadcastAnalyticsEvent(CBAnalyticsConstant.INTERNET_NOT_RESTORED_NOTIFICATION, "-1");
                    } else if (!SnoozeService.this.isNotificationIntentPrepared && CBActivity.STATE == 1) {
                        SnoozeService.this.broadcastAnalyticsEvent(CBAnalyticsConstant.INTERNET_NOT_RESTORED_DIALOG_FOREGROUND, "-1");
                    }
                    if (SnoozeService.this.isWebViewAlive && !SnoozeService.this.isNotificationIntentPrepared) {
                        Intent intent = new Intent("webview_status_action");
                        intent.putExtra(CBConstant.SNOOZE_SERVICE_STATUS, CBConstant.SNOOZE_SERVICE_DEAD);
                        LocalBroadcastManager.getInstance(SnoozeService.this).sendBroadcast(intent);
                    }
                    SnoozeService.this.killSnoozeService();
                }
            };
            SnoozeService.this.killSnoozeServiceCounter.start();
            Handler unused3 = SnoozeService.this.trackWebViewStatusHandler = new Handler();
            SnoozeService.this.trackWebViewStatusHandler.postDelayed(new Runnable() {
                public void run() {
                    if (SnoozeService.this.isServiceAlive) {
                        if (SnoozeService.this.broadCastingMessage.contentEquals(SnoozeService.this.receivedMessage)) {
                            boolean unused = SnoozeService.this.isWebViewAlive = true;
                        } else {
                            boolean unused2 = SnoozeService.this.isWebViewAlive = false;
                            if (SnoozeService.this.isServiceAlive && SnoozeService.this.verifyPaymentCheck && SnoozeService.this.isNotificationIntentPrepared && SnoozeService.this.isWebViewIntentPrepared) {
                                SnoozeService.this.launchNotificationTransactionUpdate(SnoozeService.this.payuResponse);
                            } else if (SnoozeService.this.isServiceAlive && SnoozeService.this.isNotificationIntentPrepared && SnoozeService.this.isWebViewIntentPrepared) {
                                SnoozeService.this.launchNotification(SnoozeService.this.isWebViewAlive);
                            }
                        }
                        SnoozeService.this.trackWebViewStatusHandler.postDelayed(this, 500);
                        Intent intent = new Intent("webview_status_action");
                        SnoozeService snoozeService = SnoozeService.this;
                        String unused3 = snoozeService.broadCastingMessage = "" + System.currentTimeMillis();
                        intent.putExtra("snooze_broad_cast_message", SnoozeService.this.broadCastingMessage);
                        LocalBroadcastManager.getInstance(SnoozeService.this).sendBroadcast(intent);
                    }
                }
            }, 500);
            SnoozeService.this.calculateInternetSpeed();
        }
    }

    public class SnoozeBinder extends Binder {
        public SnoozeBinder() {
        }

        public SnoozeService getSnoozeService() {
            return SnoozeService.this;
        }
    }

    /* access modifiers changed from: private */
    public void broadcastEvent(String str, String str2, boolean z) {
        Intent intent = new Intent("webview_status_action");
        intent.putExtra(CBConstant.BROADCAST_FROM_SERVICE_UPDATE_UI, true);
        intent.putExtra("key", str);
        intent.putExtra("value", str2);
        intent.putExtra(CBConstant.CURRENT_URL, this.currentUrl);
        intent.putExtra(CBConstant.CB_CONFIG, this.customBrowserConfig);
        intent.putExtra(CBConstant.IS_FORWARD_JOURNEY, z);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
