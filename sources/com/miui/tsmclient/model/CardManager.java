package com.miui.tsmclient.model;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoManager;
import com.miui.tsmclient.model.mitsm.MiTSMCardClient;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import java.io.IOException;

public class CardManager {
    protected Context mContext;
    protected String mTag;

    public CardManager(String str, Context context) {
        this.mTag = str;
        this.mContext = context;
    }

    public CardManager(Context context) {
        this.mContext = context;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace != null) {
            int i = 0;
            while (true) {
                if (i < stackTrace.length) {
                    if (TextUtils.equals(getClass().getName(), stackTrace[i].getClassName()) && i < stackTrace.length - 1) {
                        this.mTag = stackTrace[i + 1].getClassName();
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
        }
        if (this.mTag == null) {
            this.mTag = toString();
        }
    }

    public void queryCardInfo(Context context, CardInfo cardInfo, Bundle bundle, MiTsmCallback miTsmCallback) {
        if (cardInfo == null) {
            LogUtils.d(CardManager.class.getSimpleName() + "#queryCardInfo() called, but param cardInfo is null!");
            if (miTsmCallback != null) {
                miTsmCallback.onFail(1, "", new Object[0]);
                return;
            }
            return;
        }
        final CardInfo cardInfo2 = cardInfo;
        final Context context2 = context;
        final Bundle bundle2 = bundle;
        final MiTsmCallback miTsmCallback2 = miTsmCallback;
        CardExecutor.getInstance().submit(this.mTag, new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(CardExecutor.getInstance().createCardOperation(CardManager.this.mTag, cardInfo2.mCardType).queryCardInfo(context2, cardInfo2, bundle2), miTsmCallback2);
            }
        });
    }

    public void deleteCard(Context context, CardInfo cardInfo, Bundle bundle, MiTsmCallback miTsmCallback) {
        final Context context2 = context;
        final CardInfo cardInfo2 = cardInfo;
        final Bundle bundle2 = bundle;
        final MiTsmCallback miTsmCallback2 = miTsmCallback;
        CardExecutor.getInstance().submit(this.mTag, new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(CardInfoManager.getInstance(context2).deleteCard(cardInfo2, bundle2), miTsmCallback2);
            }
        });
    }

    public void queryPurchaseRecord(final Context context, final CardInfo cardInfo, final MiTsmCallback miTsmCallback) {
        if (cardInfo == null) {
            LogUtils.d(CardManager.class.getSimpleName() + "#queryPurchaseRecord() called, but param info is null!");
            if (miTsmCallback != null) {
                miTsmCallback.onFail(1, "", new Object[0]);
                return;
            }
            return;
        }
        CardExecutor.getInstance().submit(this.mTag, new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(CardExecutor.getInstance().createCardOperation(CardManager.this.mTag, cardInfo.mCardType).queryPurchaseRecord(context, cardInfo), miTsmCallback);
            }
        });
    }

    public void unrestrictESE(final Context context, final MiTsmCallback miTsmCallback) {
        CardExecutor.getInstance().execute(new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(CardManager.this.unrestrictESE(context), miTsmCallback);
            }
        });
    }

    public BaseResponse unrestrictESE(Context context) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            return new MiTSMCardClient().unrestrictEse(context, (Bundle) null);
        } catch (IOException e) {
            baseResponse.mResultCode = 2;
            LogUtils.e("unrestrictESE failed with an io exception.", e);
            return baseResponse;
        } catch (SeiTSMApiException e2) {
            baseResponse.mResultCode = e2.getErrorCode();
            baseResponse.mMsg = e2.getMessage();
            LogUtils.e("unrestrictESE failed with an tsmapi exception.", e2);
            return baseResponse;
        } catch (InterruptedException e3) {
            baseResponse.mResultCode = 11;
            LogUtils.e("unrestrictESE is interrupted.", e3);
            return baseResponse;
        }
    }

    public BaseResponse preTransferOut(Context context, CardInfo cardInfo, Bundle bundle) {
        if (cardInfo == null) {
            return new BaseResponse(1, new Object[0]);
        }
        BaseResponse preTransferOut = CardExecutor.getInstance().createCardOperation(this.mTag, cardInfo.mCardType).preTransferOut(context, cardInfo, bundle);
        return preTransferOut == null ? new BaseResponse(-2, new Object[0]) : preTransferOut;
    }

    public void upgradeApplet(final Context context, final int i, final MiTsmCallback miTsmCallback) {
        CardExecutor.getInstance().execute(new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(new MiTSMCardClient().upgradeApplet(context, i), miTsmCallback);
            }
        });
    }

    public void checkSeUpgrade(final Context context, final int i, final MiTsmCallback miTsmCallback) {
        CardExecutor.getInstance().execute(new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(new MiTSMCardClient().checkSeUpgrade(context, i), miTsmCallback);
            }
        });
    }

    public void release() {
        CardExecutor.getInstance().release(this.mTag);
    }
}
