package com.miui.tsmclient;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoManager;
import com.miui.tsmclient.entity.ServiceResponseParcelable;
import com.miui.tsmclient.entity.eventbus.DefaultCardEvent;
import com.miui.tsmclient.entity.eventbus.SeActionEvent;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.PayableCardManager;
import com.miui.tsmclient.model.mitsm.MiTSMCardClient;
import com.miui.tsmclient.service.ISEInteractionService;
import com.miui.tsmclient.service.IServiceResponse;
import com.miui.tsmclient.util.Constants;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.SysUtils;
import com.miui.tsmclientsdk.MiTsmConstants;
import de.greenrobot.event.EventBus;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class SEInteractionService extends IntentService {
    public static final String ACTION_DELETE_CARD = "com.miui.action.DELETE_CARD";
    public static final String ACTION_INSTALL_CARD = "com.miui.action.INSTALL_CARD";
    public static final String ACTION_SAVE_APP_KEY = "com.miui.action.SAVE_SPI_PK";
    public static final String EXTRA_ACTION_TYPE = "action_type";
    public static final String EXTRA_CARD_INFO = "card_info";
    public static final String EXTRA_DO_RECHARGE = "do_recharge";
    public static final String EXTRA_PRE_LOAD = "pre_load";
    public static final int PARAM_ACTION_TYPE_ISSUE = 1;
    private static final String TAG = "SEInteractionService";
    private final IBinder mBinder = new LocalBinder();
    private PayableCardManager mCardManager;
    private SEInteractionState mInteractionState;
    /* access modifiers changed from: private */
    public int mProgress;
    private List<WeakReference<Thread>> mTaskList;
    private Timer mTimer;
    private TimerTask mTimerTask;

    private void onTaskStart() {
    }

    static /* synthetic */ int access$008(SEInteractionService sEInteractionService) {
        int i = sEInteractionService.mProgress;
        sEInteractionService.mProgress = i + 1;
        return i;
    }

    public SEInteractionService() {
        super(TAG);
    }

    public IBinder onBind(Intent intent) {
        LogUtils.t("SEInteractionService onBind");
        return this.mBinder;
    }

    public void onCreate() {
        LogUtils.v("SEInteractionService onCreate");
        super.onCreate();
        this.mTaskList = new CopyOnWriteArrayList();
        this.mInteractionState = SEInteractionState.getInstance();
        this.mInteractionState.init();
        this.mCardManager = new PayableCardManager(getApplicationContext());
    }

    public void onDestroy() {
        LogUtils.v("SEInteractionService onDestroy");
        stopTimer();
        if (this.mCardManager != null) {
            this.mCardManager.release();
            this.mCardManager = null;
        }
        releaseTasks();
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (TextUtils.equals(ACTION_INSTALL_CARD, action)) {
                doInstallCard(intent);
            } else if (TextUtils.equals(ACTION_DELETE_CARD, action)) {
                doDeleteCard(intent);
            } else if (TextUtils.equals(ACTION_SAVE_APP_KEY, action)) {
                doSaveSpiPk();
            }
        }
    }

    /* access modifiers changed from: private */
    public void doInstallCard(Intent intent) {
        BaseResponse baseResponse;
        ServiceResponseParcelable serviceResponseParcelable = (ServiceResponseParcelable) intent.getParcelableExtra(Constants.KEY_RESPONSE);
        CardInfo cardInfo = (CardInfo) intent.getParcelableExtra("card_info");
        boolean booleanExtra = intent.getBooleanExtra("pre_load", false);
        boolean booleanExtra2 = intent.getBooleanExtra("do_recharge", false);
        if (cardInfo == null) {
            onTaskFinished(new BaseResponse(-1, new Object[0]), serviceResponseParcelable);
            return;
        }
        startTimer(serviceResponseParcelable);
        if (!beginSETransaction(SEInteractionState.Type.INSTALL_APP, cardInfo.mCardType)) {
            onTaskFinished(new BaseResponse(9, new Object[0]), serviceResponseParcelable);
            stopTimer();
            return;
        }
        this.mInteractionState.setCardType(cardInfo.mCardType);
        onTaskStart();
        LogUtils.d("doInstallCard preLoad:" + booleanExtra + ", cardType:" + cardInfo.mCardType);
        if (cardInfo.hasTransferInOrder()) {
            baseResponse = CardInfoManager.getInstance(getApplicationContext()).transferIn(cardInfo, intent.getExtras());
            LogUtils.d("doInstallCard transferIn called! result: " + baseResponse.mResultCode);
        } else {
            baseResponse = CardInfoManager.getInstance(getApplicationContext()).issue(cardInfo, intent.getExtras());
            LogUtils.d("doInstallCard issue called! result: " + baseResponse.mResultCode);
        }
        int i = baseResponse.mResultCode;
        if (!booleanExtra && i == 0) {
            if (cardInfo.isTransCard() && TextUtils.isEmpty(SysUtils.getDefaultTransCard(getApplicationContext())) && SysUtils.setDefaultTransCard(getApplicationContext(), cardInfo)) {
                EventBus.getDefault().post(new DefaultCardEvent(cardInfo.mAid));
            }
            if (booleanExtra2) {
                this.mInteractionState.setStatus(SEInteractionState.Status.PENDING);
                baseResponse = CardInfoManager.getInstance(getApplicationContext()).recharge(cardInfo);
                LogUtils.i("doInstallCard recharge result: " + baseResponse.mResultCode);
            }
            BaseResponse baseResponse2 = new BaseResponse(baseResponse.mResultCode, cardInfo);
            notifyCardInfoChanges(cardInfo);
            baseResponse = baseResponse2;
        }
        LogUtils.d("doInstallCard finished");
        onTaskFinished(baseResponse, serviceResponseParcelable);
        endSETransaction();
        stopTimer();
    }

    /* access modifiers changed from: private */
    public void doDeleteCard(Intent intent) {
        BaseResponse baseResponse;
        boolean booleanExtra = intent.getBooleanExtra(Constants.EXTRAS_KEY_WITHDRAW, false);
        CardInfo cardInfo = (CardInfo) intent.getParcelableExtra("card_info");
        ServiceResponseParcelable serviceResponseParcelable = (ServiceResponseParcelable) intent.getParcelableExtra(Constants.KEY_RESPONSE);
        startTimer(serviceResponseParcelable);
        if (cardInfo != null) {
            if (!beginSETransaction(SEInteractionState.Type.DELETE_APP, cardInfo.mCardType)) {
                onTaskFinished(new BaseResponse(9, new Object[0]), serviceResponseParcelable);
                stopTimer();
                return;
            }
            onTaskStart();
            if (booleanExtra) {
                baseResponse = CardInfoManager.getInstance(getApplicationContext()).transferOut(cardInfo, intent.getExtras());
            } else {
                baseResponse = CardInfoManager.getInstance(getApplicationContext()).deleteCard(cardInfo, intent.getExtras());
            }
            onTaskFinished(baseResponse, serviceResponseParcelable);
            endSETransaction();
        }
        stopTimer();
    }

    private void doSaveSpiPk() {
        EventBus.getDefault().post(new SeActionEvent(SeActionEvent.Action.SAVE_APP_KEY, new MiTSMCardClient().saveAppKey(getApplicationContext()).mResultCode));
    }

    private void notifyCardInfoChanges(CardInfo cardInfo) {
        Intent intent = new Intent(Constants.ACTION_UPDATE_CARD_INFO);
        intent.putExtra("card_info", cardInfo);
        intent.putExtra("action_type", 1);
        sendBroadcast(intent, Constants.Permission.TSM_GROUP);
    }

    private void onTaskFinished(BaseResponse baseResponse, ServiceResponseParcelable serviceResponseParcelable) {
        LogUtils.t("onTaskFinished result:" + baseResponse.mResultCode);
        if (serviceResponseParcelable != null) {
            int i = baseResponse.mResultCode;
            String str = baseResponse.mMsg;
            if (baseResponse.isSuccess()) {
                Bundle bundle = new Bundle();
                bundle.putInt(MiTsmConstants.KEY_RESULT_CODE, i);
                bundle.getString(MiTsmConstants.KEY_RESULT_MSG, str);
                if (baseResponse.mDatas != null && baseResponse.mDatas.length > 0 && (baseResponse.mDatas[0] instanceof CardInfo)) {
                    bundle.putParcelable(MiTsmConstants.KEY_CARD, (CardInfo) baseResponse.mDatas[0]);
                }
                serviceResponseParcelable.onResult(bundle);
                return;
            }
            serviceResponseParcelable.onError(i, str);
        }
    }

    private void startTimer(final ServiceResponseParcelable serviceResponseParcelable) {
        this.mProgress = 0;
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        if (this.mTimerTask == null) {
            this.mTimerTask = new TimerTask() {
                public void run() {
                    if (SEInteractionService.this.mProgress < 99) {
                        SEInteractionService.access$008(SEInteractionService.this);
                        if (serviceResponseParcelable != null) {
                            serviceResponseParcelable.onProgress(SEInteractionService.this.mProgress);
                        }
                    }
                }
            };
        }
        this.mTimer.schedule(this.mTimerTask, 0, 1000);
    }

    private void stopTimer() {
        this.mProgress = 0;
        if (this.mTimerTask != null) {
            this.mTimerTask.cancel();
            this.mTimerTask = null;
        }
        if (this.mTimer != null) {
            this.mTimer.cancel();
            this.mTimer = null;
        }
    }

    public int getProgress() {
        return this.mProgress;
    }

    public class LocalBinder extends ISEInteractionService.Stub {
        public LocalBinder() {
        }

        public void issueCard(IServiceResponse iServiceResponse, Map map) {
            SEInteractionService.this.addTask();
            SEInteractionService.this.doInstallCard(getIntent(iServiceResponse, map, SEInteractionService.ACTION_INSTALL_CARD));
        }

        public void deleteCard(IServiceResponse iServiceResponse, Map map) {
            SEInteractionService.this.addTask();
            SEInteractionService.this.doDeleteCard(getIntent(iServiceResponse, map, SEInteractionService.ACTION_DELETE_CARD));
        }

        private Intent getIntent(IServiceResponse iServiceResponse, Map map, String str) {
            CardInfo cardInfo = (CardInfo) map.get(MiTsmConstants.KEY_CARD);
            Bundle bundle = (Bundle) map.get(MiTsmConstants.KEY_DATA);
            Intent intent = new Intent(str);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            intent.putExtra("card_info", cardInfo);
            intent.putExtra(Constants.KEY_RESPONSE, new ServiceResponseParcelable(iServiceResponse));
            return intent;
        }
    }

    public static void installCard(Context context, CardInfo cardInfo, Bundle bundle) {
        installCard(context, cardInfo, bundle, false);
    }

    public static void loadCard(Context context, CardInfo cardInfo, Bundle bundle) {
        installCard(context, cardInfo, bundle, true);
    }

    public static void deleteCard(Context context, CardInfo cardInfo, Bundle bundle) {
        if (context != null) {
            Intent intent = new Intent(ACTION_DELETE_CARD);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            intent.putExtra("card_info", cardInfo);
            intent.setPackage(context.getPackageName());
            context.startService(intent);
        }
    }

    public static void saveSPIPk(Context context, Bundle bundle) {
        if (context != null) {
            Intent intent = new Intent(ACTION_SAVE_APP_KEY);
            if (bundle != null) {
                intent.putExtras(intent);
            }
            intent.setPackage(context.getPackageName());
            context.startService(intent);
        }
    }

    private static void installCard(Context context, CardInfo cardInfo, Bundle bundle, boolean z) {
        if (context != null) {
            Intent intent = new Intent(ACTION_INSTALL_CARD);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            intent.putExtra("card_info", cardInfo);
            intent.putExtra("pre_load", z);
            intent.setPackage(context.getPackageName());
            context.startService(intent);
        }
    }

    public static boolean rechargeTransaction(CardInfo cardInfo) {
        return beginSETransaction(SEInteractionState.Type.RECHARGE, cardInfo.mCardType);
    }

    public static void endSETransaction() {
        SEInteractionState.INSTANCE.endSETransaction();
    }

    public static boolean isBusy() {
        return SEInteractionState.INSTANCE.isBusy();
    }

    public static SEInteractionState.Type getType() {
        return SEInteractionState.INSTANCE.getOperationType();
    }

    public static boolean isInstalling() {
        return SEInteractionState.INSTANCE.isInstalling();
    }

    public static boolean isInstalling(String str) {
        return SEInteractionState.INSTANCE.isInstalling(str);
    }

    private void releaseTasks() {
        Thread thread;
        if (this.mTaskList != null) {
            for (WeakReference next : this.mTaskList) {
                if (!(next == null || (thread = (Thread) next.get()) == null)) {
                    thread.interrupt();
                }
            }
            this.mTaskList = null;
        }
    }

    /* access modifiers changed from: private */
    public void addTask() {
        if (this.mTaskList != null) {
            Thread.interrupted();
            this.mTaskList.add(new WeakReference(Thread.currentThread()));
        }
    }

    static boolean beginSETransaction(SEInteractionState.Type type, String str) {
        synchronized (SEInteractionState.INSTANCE) {
            if (SEInteractionState.INSTANCE.isBusy()) {
                LogUtils.d("cardType:" + str + " on " + type + " is busy.");
                return false;
            }
            SEInteractionState.INSTANCE.beginSETransaction(SEInteractionState.Status.RUNNING, type, str);
            return true;
        }
    }

    static class SEInteractionState {
        /* access modifiers changed from: private */
        public static final SEInteractionState INSTANCE = new SEInteractionState();
        private static String mCardType;
        private Status mStatus;
        private Type mType;

        enum Status {
            PENDING,
            RUNNING,
            FINISHED
        }

        enum Type {
            INSTALL_APP,
            DELETE_APP,
            LOCK_APP,
            RECHARGE
        }

        private SEInteractionState() {
            init();
        }

        static SEInteractionState getInstance() {
            return INSTANCE;
        }

        /* access modifiers changed from: package-private */
        public synchronized void init() {
            this.mStatus = Status.PENDING;
            this.mType = null;
        }

        /* access modifiers changed from: package-private */
        public synchronized void setStatus(Status status) {
            this.mStatus = status;
        }

        /* access modifiers changed from: package-private */
        public synchronized Status getStatus() {
            return this.mStatus;
        }

        /* access modifiers changed from: package-private */
        public synchronized void setOperationType(Type type) {
            this.mType = type;
        }

        /* access modifiers changed from: package-private */
        public synchronized Type getOperationType() {
            return this.mType;
        }

        /* access modifiers changed from: package-private */
        public synchronized void setCardType(String str) {
            mCardType = str;
        }

        /* access modifiers changed from: package-private */
        public synchronized String getCardType() {
            return mCardType;
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean isInstalling() {
            return this.mStatus == Status.RUNNING && this.mType == Type.INSTALL_APP;
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean isInstalling(String str) {
            return isInstalling() && TextUtils.equals(mCardType, str);
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean isBusy() {
            return this.mStatus == Status.RUNNING;
        }

        /* access modifiers changed from: package-private */
        public synchronized void beginSETransaction(Status status, Type type, String str) {
            LogUtils.t("SEInteractionService beginSETransaction mType:" + this.mType + ", mCardType:" + mCardType);
            this.mStatus = status;
            this.mType = type;
            mCardType = str;
        }

        /* access modifiers changed from: package-private */
        public synchronized void endSETransaction() {
            this.mStatus = Status.FINISHED;
            this.mType = null;
            mCardType = null;
        }
    }
}
