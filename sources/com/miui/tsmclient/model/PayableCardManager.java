package com.miui.tsmclient.model;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclientsdk.MiTsmCallback;

public class PayableCardManager extends CardManager {
    public PayableCardManager(Context context) {
        super(context);
    }

    public void recharge(Context context, PayableCardInfo payableCardInfo, OrderInfo orderInfo, Tag tag, MiTsmCallback miTsmCallback) {
        if (payableCardInfo == null || orderInfo == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(CardManager.class.getSimpleName());
            sb.append("recharge() param is invalid! info == null:");
            sb.append(payableCardInfo == null);
            LogUtils.d(sb.toString());
            if (miTsmCallback != null) {
                miTsmCallback.onFail(1, "", new Object[0]);
                return;
            }
            return;
        }
        final PayableCardInfo payableCardInfo2 = payableCardInfo;
        final Context context2 = context;
        final OrderInfo orderInfo2 = orderInfo;
        final Tag tag2 = tag;
        final MiTsmCallback miTsmCallback2 = miTsmCallback;
        CardExecutor.getInstance().execute(new Runnable() {
            public void run() {
                CardExecutor.getInstance().notifyResult(((IPayableCardOperation) CardExecutor.getInstance().createCardOperation(PayableCardManager.this.mTag, payableCardInfo2.mCardType)).recharge(context2, payableCardInfo2, orderInfo2, tag2, (Bundle) null), miTsmCallback2);
            }
        });
    }

    public BaseResponse recharge(Context context, CardInfo cardInfo) {
        BaseResponse recharge;
        if (!(cardInfo instanceof PayableCardInfo)) {
            return new BaseResponse(1, new Object[0]);
        }
        PayableCardInfo payableCardInfo = (PayableCardInfo) cardInfo;
        OrderInfo rechargeOrder = payableCardInfo.getRechargeOrder();
        if (rechargeOrder == null) {
            return new BaseResponse(0, new Object[0]);
        }
        ICardOperation createCardOperation = CardOperationFactory.createCardOperation(cardInfo.mCardType);
        if (!(createCardOperation instanceof IPayableCardOperation) || (recharge = ((IPayableCardOperation) createCardOperation).recharge(context, payableCardInfo, rechargeOrder, (Tag) null, (Bundle) null)) == null) {
            return new BaseResponse(-2, new Object[0]);
        }
        return recharge;
    }
}
