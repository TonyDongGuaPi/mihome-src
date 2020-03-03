package com.miui.tsmclientsdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.miui.tsmclientsdk.IMiTsmService;
import com.miui.tsmclientsdk.MiTsmConstants;
import com.miui.tsmclientsdk.internal.ServiceTask;
import java.util.List;

public class MiTsmManager {
    private static final String ACTION_MI_TSM_SERVICE = "com.miui.tsmclientsdk.action.MI_TSM_SERVICE";
    private static final MiTsmManager INSTANCE = new MiTsmManager();
    private static final int MIN_VERSION_NFC_BANK_CARD_INTRO_PAGE_ACCESSIBLE = 22;
    private static final int MIN_VERSION_OPEN_NFC_BANK_CARD = 18;
    private static final int MIN_VERSION_OPEN_NFC_TRAFFIC_CARD = 11;
    private static final int MIN_VERSION_OPEN_TSM_SETTINGS = 23;
    private static final String PACKAGE_MIPAY_WALLET = "com.mipay.wallet";
    private static final String PACKAGE_NAME_TSMCLIENT = "com.miui.tsmclient";
    private static final String TAG = "MiTsmManager";
    private static final String URI_APP_BANK_CARD = "mipay://walletapp?id=mipay.bankCardList";
    private static final String URI_PARAM_KEY_NFC_WITH_CONNECTOR = "&isNfcBankServiceAvailable=";
    private static final String URI_PARAM_KEY_REF_WITH_CONNECTOR = "&miref=";

    private MiTsmManager() {
    }

    public static MiTsmManager getInstance() {
        return INSTANCE;
    }

    public MiTsmFuture<Bundle> addMiPayShortcut(Context context, final String str) throws UnSupportedException {
        checkVersion(context, 19);
        return new TSMTask(context) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).addMiPayShortcut((IMiTsmResponse) this.mResponse, str);
            }
        }.start();
    }

    public int isTSMServiceAvailable(Context context) {
        int i = 1;
        if (getClientVersionCode(context) >= 1) {
            i = 0;
        }
        NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(context);
        if (defaultAdapter == null) {
            i += 2;
        }
        return (defaultAdapter == null || defaultAdapter.isEnabled()) ? i : i + 4;
    }

    public boolean canOpenBankCardIntroPage(Context context) {
        return getClientVersionCode(context) >= 22;
    }

    public static boolean canOpenNfcBankCard(Context context) {
        return getClientVersionCode(context) >= 18;
    }

    public static boolean canOpenNfcTrafficCard(Context context) {
        return getClientVersionCode(context) >= 11;
    }

    public static boolean canOpenTSMSettings(Context context) {
        return getClientVersionCode(context) >= 23;
    }

    public MiTsmFuture<Bundle> getCPLC(Context context) throws UnSupportedException {
        checkVersion(context, 2);
        return new TSMTask(context) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).getCPLC((IMiTsmResponse) this.mResponse);
            }
        }.start();
    }

    public MiTsmFuture<Bundle> getTransCardState(Context context) throws UnSupportedException {
        checkVersion(context, 16);
        return new TSMTask(context) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).getTransCardState((IMiTsmResponse) this.mResponse);
            }
        }.start();
    }

    public MiTsmFuture<Bundle> getCardsState(Context context) throws UnSupportedException {
        checkVersion(context, 36);
        return new TSMTask(context) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).getCardsState((IMiTsmResponse) this.mResponse);
            }
        }.start();
    }

    public MiTsmFuture<Bundle> getCardsQuantity(Context context, final MiTsmConstants.CardType cardType) throws UnSupportedException {
        Log.i(TAG, "start to getCardsQuantity");
        checkVersion(context, 8);
        return new TSMTask(context, true) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).getCardsQuantity((IMiTsmResponse) this.mResponse, cardType.toString());
            }
        }.start();
    }

    public MiTsmFuture<Bundle> createSSD(Context context, final int i) throws UnSupportedException {
        checkVersion(context, 3);
        return new TSMTask(context) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).createSSD((IMiTsmResponse) this.mResponse, i);
            }
        }.start();
    }

    @Deprecated
    public MiTsmFuture<Bundle> getDefaultCard(Context context, MiTsmConstants.CardType cardType) throws UnSupportedException {
        throw new UnSupportedException("This method has been deprecated");
    }

    @Deprecated
    public MiTsmFuture<Bundle> setDefaultCard(Context context, MiTsmConstants.CardType cardType, Bundle bundle) throws UnSupportedException {
        throw new UnSupportedException("This method has been deprecated");
    }

    public MiTsmFuture<Bundle> getCardInfo(Context context, final List<String> list) throws UnSupportedException {
        checkVersion(context, 9);
        return new TSMTask(context) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).getCardInfo((IMiTsmResponse) this.mResponse, list);
            }
        }.start();
    }

    public MiTsmFuture<Bundle> getActiveCards(Context context, final MiTsmConstants.CardType cardType) throws UnSupportedException {
        checkVersion(context, 9);
        return new TSMTask(context) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).getActiveCards((IMiTsmResponse) this.mResponse, cardType.toString());
            }
        }.start();
    }

    public MiTsmFuture<Bundle> getSeBankCards(Context context) throws UnSupportedException {
        checkVersion(context, 11);
        return new TSMTask(context) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).getSeBankCards((IMiTsmResponse) this.mResponse);
            }
        }.start();
    }

    public MiTsmFuture<Bundle> deleteBankCard(Context context, String str) throws UnSupportedException {
        throw new UnSupportedException("This method has been deprecated");
    }

    public void startOpenCard(Activity activity, int i, Bundle bundle) throws UnSupportedException {
        checkVersion(activity, 11);
        Intent intent = new Intent();
        intent.setPackage("com.miui.tsmclient");
        intent.setData(Uri.parse("https://tsmclient.miui.com?action=issue&type=BANKCARD"));
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, i);
    }

    public MiTsmFuture<Bundle> manageBankCard(Activity activity, final String str, final MiTsmConstants.OperationType operationType) throws UnSupportedException {
        checkVersion(activity, 14);
        return new TSMTask(activity) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).manageBankCard((IMiTsmResponse) this.mResponse, str, operationType.ordinal());
            }
        }.start();
    }

    public MiTsmFuture<Bundle> deleteBankCards(Context context) throws UnSupportedException {
        checkVersion(context, 13);
        return new TSMTask(context) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).deleteAllBankCard((IMiTsmResponse) this.mResponse);
            }
        }.start();
    }

    public MiTsmFuture<Bundle> isBankCardAvailable(Context context) throws UnSupportedException {
        checkVersion(context, 15);
        return new TSMTask(context) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).isBankCardAvailable((IMiTsmResponse) this.mResponse);
            }
        }.start();
    }

    public MiTsmFuture<Bundle> syncBankCardStatus(Context context) throws UnSupportedException {
        checkVersion(context, 17);
        return new TSMTask(context) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).syncBankCardStatus((IMiTsmResponse) this.mResponse);
            }
        }.start();
    }

    public void showBankcardList(Activity activity, String str) throws UnSupportedException {
        if (TextUtils.isEmpty(str) || activity == null) {
            throw new UnSupportedException("Channel ref must not be empty");
        }
        checkVersion(activity, 18);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("mipay://walletapp?id=mipay.bankCardList&miref=" + str + URI_PARAM_KEY_NFC_WITH_CONNECTOR + "true"));
        intent.setPackage("com.mipay.wallet");
        activity.startActivityForResult(intent, 10000);
    }

    public MiTsmFuture<Bundle> manageVirtualSimCard(Context context, final String str, final MiTsmConstants.OperationType operationType) throws UnSupportedException {
        if (operationType == MiTsmConstants.OperationType.INSTALL) {
            checkVersion(context, 21);
        } else if (operationType == MiTsmConstants.OperationType.DELETE) {
            checkVersion(context, 24);
        } else {
            throw new UnSupportedException("operation is not supported");
        }
        return new TSMTask(context) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).manageVirtualSimCard((IMiTsmResponse) this.mResponse, str, operationType.ordinal());
            }
        }.start();
    }

    public MiTsmFuture<Bundle> getMiPayStatus(Context context) throws UnSupportedException {
        checkVersion(context, 29);
        return new TSMTask(context) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).getMiPayStatus((IMiTsmResponse) this.mResponse);
            }
        }.start();
    }

    public MiTsmFuture<Bundle> requestInAppTransaction(Context context, Bundle bundle, MiTsmConstants.Channel channel) throws UnSupportedException {
        checkVersion(context, 32);
        final Bundle bundle2 = bundle;
        final MiTsmConstants.Channel channel2 = channel;
        return new TSMTask(context, true) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).requestInappTransaction((IMiTsmResponse) this.mResponse, bundle2, channel2.ordinal());
            }
        }.start();
    }

    public MiTsmFuture<Bundle> requestPin(Context context, final MiTsmConstants.Channel channel) throws UnSupportedException {
        checkVersion(context, 32);
        return new TSMTask(context, true) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).requestPin((IMiTsmResponse) this.mResponse, channel.ordinal());
            }
        }.start();
    }

    public MiTsmFuture<Bundle> notifyPayResult(Context context, final Bundle bundle) throws UnSupportedException {
        checkVersion(context, 32);
        return new TSMTask(context, true) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).notifyPayResult((IMiTsmResponse) this.mResponse, bundle);
            }
        }.start();
    }

    public MiTsmFuture<Bundle> addMipayCard(Context context, String str, int i, String str2, OnProgressUpdateListener onProgressUpdateListener) throws UnSupportedException {
        checkVersion(context, 35);
        final String str3 = str;
        final int i2 = i;
        final String str4 = str2;
        return new TSMTask(context, false, onProgressUpdateListener) {
            /* access modifiers changed from: protected */
            public void doWork() throws RemoteException {
                ((IMiTsmService) this.mService).addMipayCard((IMiTsmResponse) this.mResponse, str3, i2, str4);
            }
        }.start();
    }

    private void checkVersion(Context context, int i) throws UnSupportedException {
        int clientVersionCode = getClientVersionCode(context);
        if (clientVersionCode < i) {
            throw new UnSupportedException("requires minVersionCode is " + i + ", current versionCode is " + clientVersionCode);
        }
    }

    private static int getClientVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.miui.tsmclient", 0).versionCode;
        } catch (Exception e) {
            Log.d(TAG, "getTsmVersionCode:" + 0, e);
            return 0;
        }
    }

    private static abstract class TSMTask extends ServiceTask<IMiTsmService> {
        TSMTask(Context context) {
            super(context);
        }

        TSMTask(Context context, boolean z) {
            super(context, z);
        }

        TSMTask(Context context, boolean z, OnProgressUpdateListener onProgressUpdateListener) {
            super(context, z, onProgressUpdateListener);
        }

        /* access modifiers changed from: protected */
        public Intent getServiceIntent() {
            Intent intent = new Intent(MiTsmManager.ACTION_MI_TSM_SERVICE);
            intent.setPackage("com.miui.tsmclient");
            return intent;
        }

        /* access modifiers changed from: protected */
        public IMiTsmService asInterface(IBinder iBinder) {
            return IMiTsmService.Stub.asInterface(iBinder);
        }
    }
}
