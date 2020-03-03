package com.miui.tsmclient.model.bankcard;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.InAppTransData;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.mitsm.MiTSMCardOperation;
import com.miui.tsmclient.seitsm.TsmRpcModels;

public class BankCardOperation extends MiTSMCardOperation<BankCardClient, BankCardInfo> {
    /* access modifiers changed from: protected */
    public BankCardClient createCardClient() {
        return new BankCardClient();
    }

    public BaseResponse issue(Context context, BankCardInfo bankCardInfo, Bundle bundle) {
        return ((BankCardClient) this.mMiTSMCardClient).issue(context, bankCardInfo, bundle);
    }

    public BaseResponse requestVerificationCode(Context context, CardInfo cardInfo, String str) {
        return ((BankCardClient) this.mMiTSMCardClient).requestVerificationCode(context, cardInfo, str);
    }

    public BaseResponse verifyVerificationCode(Context context, CardInfo cardInfo, String str, String str2) {
        return ((BankCardClient) this.mMiTSMCardClient).verifyVerificationCode(context, cardInfo, str, str2);
    }

    public BaseResponse lock(Context context, CardInfo cardInfo, Bundle bundle) {
        return ((BankCardClient) this.mMiTSMCardClient).lock(context, cardInfo, bundle);
    }

    public BaseResponse deleteCards(Context context) {
        return ((BankCardClient) this.mMiTSMCardClient).deleteCards(context);
    }

    public BaseResponse queryPan(Context context, CardInfo cardInfo, Bundle bundle) {
        return ((BankCardClient) this.mMiTSMCardClient).queryPan(context, cardInfo, bundle);
    }

    public BaseResponse preparePayApplet(Context context, CardInfo cardInfo, Bundle bundle) {
        return ((BankCardClient) this.mMiTSMCardClient).preparePayApplet(context, cardInfo, bundle);
    }

    public BaseResponse enrollUPCard(Context context, CardInfo cardInfo, Bundle bundle) {
        return ((BankCardClient) this.mMiTSMCardClient).enrollUPCard(context, cardInfo, bundle);
    }

    public BaseResponse pullPersoData(Context context, CardInfo cardInfo, Bundle bundle) {
        return ((BankCardClient) this.mMiTSMCardClient).pullPersonData(context, cardInfo, bundle);
    }

    public BaseResponse isBankCardServiceAvailable(Context context) {
        return ((BankCardClient) this.mMiTSMCardClient).isBankCardServiceAvailable(context);
    }

    public BaseResponse requestInappPay(Context context, CardInfo cardInfo, InAppTransData inAppTransData) {
        return ((BankCardClient) this.mMiTSMCardClient).requestInAppPay(context, cardInfo, inAppTransData);
    }

    public BaseResponse notifyInappPayResult(Context context, int i, InAppTransData inAppTransData) {
        return ((BankCardClient) this.mMiTSMCardClient).notifyInappPayResult(context, i, inAppTransData);
    }

    public BaseResponse updateCardInfo(Context context, BankCardInfo bankCardInfo) {
        BaseResponse queryBankCardList = ((BankCardClient) this.mMiTSMCardClient).queryBankCardList(context, bankCardInfo == null ? null : bankCardInfo.mAid);
        if (queryBankCardList.mResultCode == 0) {
            queryBankCardList.mDatas[0] = BankCardClient.fillFromTsm(((TsmRpcModels.QueryBankCardInfoResponse) queryBankCardList.mDatas[0]).getBankCardInfoListList());
        }
        return queryBankCardList;
    }
}
