package com.miui.tsmclient.model;

import android.os.Bundle;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoManager;
import com.miui.tsmclient.entity.MifareCardInfo;
import com.miui.tsmclient.model.mitsm.MiTSMCardClient;
import com.miui.tsmclient.model.mitsm.TSMSessionManager;
import com.miui.tsmclient.net.AuthApiException;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.PrefUtils;
import com.miui.tsmclient.util.ResUtils;
import com.miui.tsmclient.util.SysUtils;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CacheModel extends BaseModel {
    private volatile boolean isCancelled;
    protected Subscription mSubscription;
    private TransitCardModel mTransitCardModel;

    public interface CacheListener {
        void onLoadFailed(int i, String str);

        void onLoadSuccess();
    }

    /* access modifiers changed from: protected */
    public void onInit() {
        this.isCancelled = false;
        this.mTransitCardModel = (TransitCardModel) BaseModel.create(getContext(), TransitCardModel.class);
    }

    public void release() {
        this.isCancelled = true;
        if (this.mSubscription != null) {
            this.mSubscription.unsubscribe();
        }
    }

    public boolean isCacheSanity() {
        return isTransCached() && isMifareCached() && isBankCached();
    }

    public boolean isTransCached() {
        return PrefUtils.getBoolean(getContext(), PrefUtils.PREF_KEY_TRANS_CACHED, false);
    }

    public boolean isMifareCached() {
        return PrefUtils.getBoolean(getContext(), PrefUtils.PREF_KEY_MIFARE_CACHED, false);
    }

    public boolean isBankCached() {
        return PrefUtils.getBoolean(getContext(), PrefUtils.PREF_KEY_BANK_CACHED, false);
    }

    public void setTransCached(boolean z) {
        PrefUtils.putBoolean(getContext(), PrefUtils.PREF_KEY_TRANS_CACHED, z);
    }

    public void setMifareCached(boolean z) {
        PrefUtils.putBoolean(getContext(), PrefUtils.PREF_KEY_MIFARE_CACHED, z);
    }

    public void setBankCached(boolean z) {
        PrefUtils.putBoolean(getContext(), PrefUtils.PREF_KEY_BANK_CACHED, z);
    }

    public void buildCache(final CacheListener cacheListener) {
        this.mSubscription = Observable.fromCallable(new Callable<BaseResponse>() {
            public BaseResponse call() throws Exception {
                BaseResponse access$000 = CacheModel.this.syncEse();
                LogUtils.d("syncEse result:" + access$000.mResultCode + ", " + access$000.mMsg);
                if (!access$000.isSuccess()) {
                    return access$000;
                }
                if (!CacheModel.this.isTransCached()) {
                    BaseResponse access$100 = CacheModel.this.loadTransCards();
                    if (!access$100.isSuccess()) {
                        return access$100;
                    }
                    SysUtils.getDefaultTransCard(CacheModel.this.getContext());
                    CacheModel.this.setTransCached(true);
                }
                if (!CacheModel.this.isMifareCached()) {
                    BaseResponse access$200 = CacheModel.this.loadMiFareCards();
                    if (!access$200.isSuccess()) {
                        return access$200;
                    }
                    CacheModel.this.setMifareCached(true);
                }
                if (!CacheModel.this.isBankCached()) {
                    BaseResponse access$300 = CacheModel.this.loadBankCards();
                    if (!access$300.isSuccess()) {
                        return access$300;
                    }
                    CacheModel.this.setBankCached(true);
                }
                return new BaseResponse(0, new Object[0]);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<BaseResponse>() {
            public void onCompleted() {
            }

            public void onNext(BaseResponse baseResponse) {
                if (baseResponse.isSuccess()) {
                    cacheListener.onLoadSuccess();
                    LogUtils.d("buildCache onLoadSuccess");
                    return;
                }
                String errorText = ErrorCode.getErrorText(CacheModel.this.getContext(), baseResponse.mResultCode, baseResponse.mMsg);
                cacheListener.onLoadFailed(baseResponse.mResultCode, errorText);
                LogUtils.d("buildCache onLoadFailed:" + errorText);
            }

            public void onError(Throwable th) {
                if (th instanceof AuthApiException) {
                    AuthApiException authApiException = (AuthApiException) th;
                    cacheListener.onLoadFailed(authApiException.mErrorCode, authApiException.mErrorMsg);
                } else {
                    cacheListener.onLoadFailed(-2, ResUtils.getString(CacheModel.this.getContext(), "error_common"));
                }
                LogUtils.e("buildCache onError", th);
            }
        });
    }

    /* access modifiers changed from: private */
    public BaseResponse loadTransCards() throws InterruptedException {
        checkCancel("loadTransCards");
        try {
            List<CardInfo> cardsFromNetwork = this.mTransitCardModel.getCardsFromNetwork((CardInfo) null);
            if (cardsFromNetwork == null || cardsFromNetwork.isEmpty()) {
                return new BaseResponse(3, ResUtils.getString(getContext(), "service_unavailable"), new Object[0]);
            }
            for (CardInfo next : cardsFromNetwork) {
                checkCancel("loadTransCards");
                CardInfoManager.getInstance(getContext()).updateCards(next);
                if (!next.mIsReadSECorrectly && next.mServiceStatus == CardInfo.ServiceStatus.active) {
                    return new BaseResponse(-2, new Object[0]);
                }
            }
            return new BaseResponse(0, new Object[0]);
        } catch (AuthApiException e) {
            LogUtils.e("failed to get cards from network, code = " + e.mErrorCode + ", msg = " + e.mErrorMsg, e);
            return new BaseResponse(e.mErrorCode, e.mErrorMsg, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public BaseResponse loadMiFareCards() throws InterruptedException {
        checkCancel("loadMiFareCards");
        return CardInfoManager.getInstance(getContext()).updateCards(new MifareCardInfo());
    }

    /* access modifiers changed from: private */
    public BaseResponse loadBankCards() throws InterruptedException {
        checkCancel("loadBankCards");
        return CardInfoManager.getInstance(getContext()).updateCards(new BankCardInfo());
    }

    /* access modifiers changed from: private */
    public BaseResponse syncEse() throws InterruptedException {
        String str;
        int i;
        MiTSMCardClient miTSMCardClient = new MiTSMCardClient();
        try {
            CardInfo cardInfo = new CardInfo(CardInfo.CARD_TYPE_DUMMY);
            checkCancel("syncEse");
            return miTSMCardClient.syncEse(getContext(), cardInfo, miTSMCardClient.getSession(getContext(), cardInfo, (TSMSessionManager.BusinessType) null), (Bundle) null);
        } catch (IOException e) {
            LogUtils.e("sync Ese failed with an io exception.", e);
            str = "";
            i = 2;
            return new BaseResponse(i, str, new Object[0]);
        } catch (SeiTSMApiException e2) {
            i = e2.getErrorCode();
            str = e2.getMessage();
            LogUtils.e("sync Ese failed with an tsm api exception.", e2);
            return new BaseResponse(i, str, new Object[0]);
        }
    }

    private void checkCancel(String str) throws InterruptedException {
        if (this.isCancelled) {
            LogUtils.d("" + str + " throw InterruptedException");
            throw new InterruptedException(str);
        }
    }
}
