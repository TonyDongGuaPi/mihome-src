package com.miui.tsmclient.model;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.tsmclient.SEInteractionService;
import com.miui.tsmclient.entity.CardConfigManager;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.model.mitsm.AsyncMiTSMClient;
import com.miui.tsmclient.model.mitsm.MiTSMCardOperation;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.util.Constants;

public class PayableCardOperation<C extends PayableCardInfo> extends MiTSMCardOperation<PayableCardClient, C> implements IPayableCardOperation<C> {
    public PayableCardOperation(String str) {
        if (CardConfigManager.getInstance().getOperationType(str) == CardConfigManager.CardConfig.OperationType.ASYNC) {
            this.mMiTSMCardClient = new AsyncPayableCardClient(new AsyncMiTSMClient(this.mMiTSMCardClient));
        } else {
            this.mMiTSMCardClient = new PayableCardClient(this.mMiTSMCardClient);
        }
    }

    public BaseResponse recharge(Context context, C c, OrderInfo orderInfo, Tag tag, Bundle bundle) {
        if (!SEInteractionService.rechargeTransaction(c)) {
            return new BaseResponse(9, new Object[0]);
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle2 = bundle;
        if (orderInfo != null && !TextUtils.isEmpty(orderInfo.mCityId)) {
            bundle2.putString("extra_city_id", orderInfo.mCityId);
        }
        BaseResponse recharge = ((PayableCardClient) this.mMiTSMCardClient).recharge(context, c, orderInfo, tag, bundle2);
        if (recharge.mResultCode == 0) {
            c.mUnfinishOrderInfos.remove(orderInfo);
        }
        SEInteractionService.endSETransaction();
        return recharge;
    }

    public BaseResponse updateCardInfo(Context context, C c) {
        if (c.getUnfinishTransferOutInfo() != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(Constants.EXTRAS_KEY_WITHDRAW, true);
            if (preTransferOut(context, c, bundle).mResultCode == 0) {
                transferOut(context, c, bundle);
            }
        }
        return super.updateCardInfo(context, c);
    }
}
