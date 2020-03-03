package com.miui.tsmclient.model.bankcard;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoManager;
import com.miui.tsmclient.entity.InAppTransData;
import com.miui.tsmclient.model.CardExecutor;
import com.miui.tsmclient.model.CardManager;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclientsdk.MiTsmCallback;

public class BankCardManager extends CardManager {
    public BankCardManager(Context context) {
        super(context);
    }

    public void updateCardInfo(final Context context, final BankCardInfo bankCardInfo, final MiTsmCallback miTsmCallback) {
        CardExecutor.getInstance().execute(new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(CardInfoManager.getInstance(context).updateCards(bankCardInfo), miTsmCallback);
            }
        });
    }

    public void requestVerificationCode(Context context, CardInfo cardInfo, String str, MiTsmCallback miTsmCallback) {
        if (cardInfo == null) {
            LogUtils.d(CardManager.class.getSimpleName() + "#requestVerificationCode() called,but param info is null!");
            if (miTsmCallback != null) {
                miTsmCallback.onFail(1, "", new Object[0]);
                return;
            }
            return;
        }
        final CardInfo cardInfo2 = cardInfo;
        final Context context2 = context;
        final String str2 = str;
        final MiTsmCallback miTsmCallback2 = miTsmCallback;
        CardExecutor.getInstance().execute(new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(((BankCardOperation) CardExecutor.getInstance().createCardOperation(BankCardManager.this.mTag, cardInfo2.mCardType)).requestVerificationCode(context2, cardInfo2, str2), miTsmCallback2);
            }
        });
    }

    public void verifyVerificationCode(Context context, CardInfo cardInfo, String str, String str2, MiTsmCallback miTsmCallback) {
        if (cardInfo == null) {
            LogUtils.d(CardManager.class.getSimpleName() + "#verifyVerificationCode() called,but param info is null!");
            if (miTsmCallback != null) {
                miTsmCallback.onFail(1, "", new Object[0]);
                return;
            }
            return;
        }
        final CardInfo cardInfo2 = cardInfo;
        final Context context2 = context;
        final String str3 = str;
        final String str4 = str2;
        final MiTsmCallback miTsmCallback2 = miTsmCallback;
        CardExecutor.getInstance().execute(new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(((BankCardOperation) CardExecutor.getInstance().createCardOperation(BankCardManager.this.mTag, cardInfo2.mCardType)).verifyVerificationCode(context2, cardInfo2, str3, str4), miTsmCallback2);
            }
        });
    }

    public void lock(Context context, CardInfo cardInfo, Bundle bundle, MiTsmCallback miTsmCallback) {
        if (cardInfo == null) {
            LogUtils.d(CardManager.class.getSimpleName() + "#lockCard() called,but param info is null!");
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
        CardExecutor.getInstance().execute(new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(((BankCardOperation) CardExecutor.getInstance().createCardOperation(BankCardManager.this.mTag, cardInfo2.mCardType)).lock(context2, cardInfo2, bundle2), miTsmCallback2);
            }
        });
    }

    public int deleteAllBankCard(Context context) {
        return CardInfoManager.getInstance(context).deleteBankCards().mResultCode;
    }

    public void queryPan(Context context, CardInfo cardInfo, Bundle bundle, MiTsmCallback miTsmCallback) {
        if (cardInfo == null) {
            LogUtils.d(CardManager.class.getSimpleName() + "#queryPan() called,but param info is null!");
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
        CardExecutor.getInstance().execute(new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(((BankCardOperation) CardExecutor.getInstance().createCardOperation(BankCardManager.this.mTag, cardInfo2.mCardType)).queryPan(context2, cardInfo2, bundle2), miTsmCallback2);
            }
        });
    }

    public void enrollUPCard(Context context, CardInfo cardInfo, Bundle bundle, MiTsmCallback miTsmCallback) {
        if (cardInfo == null) {
            LogUtils.d(CardManager.class.getSimpleName() + "#enrollUPCard() called,but param info is null!");
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
        CardExecutor.getInstance().execute(new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(((BankCardOperation) CardExecutor.getInstance().createCardOperation(BankCardManager.this.mTag, cardInfo2.mCardType)).enrollUPCard(context2, cardInfo2, bundle2), miTsmCallback2);
            }
        });
    }

    public void preparePayApplet(Context context, CardInfo cardInfo, Bundle bundle, MiTsmCallback miTsmCallback) {
        if (cardInfo == null) {
            LogUtils.d(CardManager.class.getSimpleName() + "#preparePayApplet() called,but param info is null!");
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
                CardExecutor.getInstance().notifyResult(((BankCardOperation) CardExecutor.getInstance().createCardOperation(BankCardManager.this.mTag, cardInfo2.mCardType)).preparePayApplet(context2, cardInfo2, bundle2), miTsmCallback2);
            }
        });
    }

    public void pullPersoData(Context context, CardInfo cardInfo, Bundle bundle, MiTsmCallback miTsmCallback) {
        if (cardInfo == null) {
            LogUtils.d(CardManager.class.getSimpleName() + "#pullPersoData() called,but param info is null!");
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
        CardExecutor.getInstance().execute(new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(((BankCardOperation) CardExecutor.getInstance().createCardOperation(BankCardManager.this.mTag, cardInfo2.mCardType)).pullPersoData(context2, cardInfo2, bundle2), miTsmCallback2);
            }
        });
    }

    public void isBankCardServiceAvailable(final Context context, final MiTsmCallback miTsmCallback) {
        CardExecutor.getInstance().execute(new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(((BankCardOperation) CardExecutor.getInstance().createCardOperation(BankCardManager.this.mTag, new BankCardInfo().mCardType)).isBankCardServiceAvailable(context), miTsmCallback);
            }
        });
    }

    public void requestInappPay(Context context, CardInfo cardInfo, InAppTransData inAppTransData, MiTsmCallback miTsmCallback) {
        final BankCardOperation bankCardOperation = (BankCardOperation) CardExecutor.getInstance().createCardOperation(this.mTag, "BANKCARD");
        final Context context2 = context;
        final CardInfo cardInfo2 = cardInfo;
        final InAppTransData inAppTransData2 = inAppTransData;
        final MiTsmCallback miTsmCallback2 = miTsmCallback;
        CardExecutor.getInstance().execute(new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(bankCardOperation.requestInappPay(context2, cardInfo2, inAppTransData2), miTsmCallback2);
            }
        });
    }

    public void notifyInappPayResult(Context context, int i, InAppTransData inAppTransData, MiTsmCallback miTsmCallback) {
        final BankCardOperation bankCardOperation = (BankCardOperation) CardExecutor.getInstance().createCardOperation(this.mTag, "BANKCARD");
        final Context context2 = context;
        final int i2 = i;
        final InAppTransData inAppTransData2 = inAppTransData;
        final MiTsmCallback miTsmCallback2 = miTsmCallback;
        CardExecutor.getInstance().execute(new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(bankCardOperation.notifyInappPayResult(context2, i2, inAppTransData2), miTsmCallback2);
            }
        });
    }
}
