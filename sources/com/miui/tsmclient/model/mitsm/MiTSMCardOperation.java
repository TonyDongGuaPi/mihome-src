package com.miui.tsmclient.model.mitsm;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.entity.CardConfigManager;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.ICardOperation;
import com.miui.tsmclient.model.mitsm.MiTSMCardClient;
import com.miui.tsmclient.util.Constants;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.SysUtils;
import com.tsmclient.smartcard.CardConstants;
import com.tsmclient.smartcard.handler.SmartCardReader;

public class MiTSMCardOperation<T extends MiTSMCardClient, C extends CardInfo> implements ICardOperation<C> {
    protected T mMiTSMCardClient = createCardClient();

    public BaseResponse issue(Context context, C c, Bundle bundle) {
        return this.mMiTSMCardClient.issue(context, c, bundle);
    }

    public BaseResponse updateCardInfo(Context context, C c) {
        return queryCardInfo(context, c, (Bundle) null);
    }

    public BaseResponse queryCardInfo(Context context, C c, Bundle bundle) {
        String str = c.mCardType;
        Bundle bundle2 = new Bundle();
        CardConfigManager.getInstance().parseCardRulesToBundle(str, bundle2);
        Bundle readCard = SmartCardReader.readCard(c.getTerminal(), str, context, bundle2);
        int i = -2;
        if (readCard == null) {
            return new BaseResponse(-2, c);
        }
        LogUtils.i("queryCardInfo:" + str + ", " + readCard);
        if (!readCard.getBoolean("success")) {
            if (readCard.getInt("error") == 2) {
                i = 2003;
                c.mHasIssue = false;
                c.mCardBalance = 0;
                c.mCardNo = null;
                c.mRealCardNo = null;
                c.mIsReadSECorrectly = true;
            }
            return new BaseResponse(i, c);
        }
        c.mHasIssue = true;
        c.mCardNo = readCard.getString(CardConstants.KEY_ACCOUNT_NUM);
        c.mRealCardNo = readCard.getString(CardConstants.KEY_ACCOUNT_REAL_NUM);
        c.mIsReadSECorrectly = true;
        c.mCardBalance = readCard.getInt(CardConstants.E_BALANCE);
        c.mTradeLogs = readCard.getParcelableArrayList(CardConstants.TRADE_LOG);
        c.mStartDate = readCard.getString(CardConstants.VALID_START);
        c.mEndDate = readCard.getString(CardConstants.VALID_END);
        c.mStatus = CardInfo.Status.ACTIVE;
        if (readCard.getInt(CardConstants.STATUS_NEGATIVE) != 0) {
            c.mStatus = CardInfo.Status.NEGATIVE;
        }
        if (readCard.getInt(CardConstants.STATUS_CARD_EXCEPTION) != 0) {
            c.mStatus = CardInfo.Status.INVALID;
        }
        if (readCard.getInt(CardConstants.STATUS_IN_BLACK_LIST) == 1) {
            c.mStatus = CardInfo.Status.IN_BLACKLIST;
        }
        int i2 = readCard.getInt(CardConstants.OVER_DRAWN);
        CardConfigManager.CardConfig cardConfigByType = CardConfigManager.getInstance().getCardConfigByType(str);
        if (i2 < 0 || (cardConfigByType != null && cardConfigByType.isJudgeOverdrawIllegal() && i2 > 0 && c.mCardBalance + i2 > 0)) {
            c.mStatus = CardInfo.Status.DATA_ILLEGAL;
        }
        boolean z = readCard.getBoolean(CardConstants.STATUS_VALID_START_DATE, true);
        boolean z2 = readCard.getBoolean(CardConstants.STATUS_VALID_END_DATE, true);
        if (!z) {
            c.mStatus = CardInfo.Status.START_DATE_INVALID;
        }
        if (!z2) {
            c.mStatus = CardInfo.Status.END_DATE_INVALID;
        }
        if (readCard.getInt(CardConstants.STATUS_LOCKED) == 1) {
            c.mStatus = CardInfo.Status.LOCKED;
        }
        c.updateExtraInfo(context);
        return new BaseResponse(0, c);
    }

    public BaseResponse deleteCard(Context context, C c, Bundle bundle) {
        return this.mMiTSMCardClient.delete(context, c, bundle);
    }

    public BaseResponse queryPurchaseRecord(Context context, C c) {
        return queryCardInfo(context, c, (Bundle) null);
    }

    public BaseResponse preTransferOut(Context context, C c, Bundle bundle) {
        return this.mMiTSMCardClient.preTransferOut(context, c, bundle);
    }

    public BaseResponse transferOut(Context context, C c, Bundle bundle) {
        BaseResponse transferOut = this.mMiTSMCardClient.transferOut(context, c, bundle);
        if (bundle == null || !bundle.getBoolean(Constants.EXTRAS_KEY_WITHDRAW, false) || !transferOut.isSuccess()) {
            return transferOut;
        }
        SysUtils.clearCardCache(context, c);
        return this.mMiTSMCardClient.uploadTransferOutResult(context, c, bundle);
    }

    public BaseResponse returnCard(Context context, C c, Bundle bundle) {
        return this.mMiTSMCardClient.returnCard(context, c, bundle);
    }

    public BaseResponse transferIn(Context context, C c, Bundle bundle) {
        return this.mMiTSMCardClient.transferIn(context, c, bundle);
    }

    public void terminate() {
        this.mMiTSMCardClient.shutDown();
    }

    public BaseResponse isServiceAvailable(Context context, C c, Bundle bundle) {
        return this.mMiTSMCardClient.isServiceAvailable(context, c, bundle);
    }

    /* access modifiers changed from: protected */
    public T createCardClient() {
        return new MiTSMCardClient();
    }
}
