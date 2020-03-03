package com.miui.tsmclient.net.request;

import com.miui.tsmclient.common.net.ResponseListener;
import com.miui.tsmclient.common.net.request.SecureRequest;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.RechargeOrderResponseInfo;
import com.miui.tsmclient.net.TSMAuthContants;
import java.io.IOException;

public class RechargeOrderListRequest extends SecureRequest<RechargeOrderResponseInfo> {
    private CardInfo mCardInfo;

    public RechargeOrderListRequest(CardInfo cardInfo, ResponseListener<RechargeOrderResponseInfo> responseListener) {
        super(1, TSMAuthContants.URL_QUERY_RECHARGE_ORDER_LIST, RechargeOrderResponseInfo.class, responseListener);
        this.mCardInfo = cardInfo;
        addParams("cardName", this.mCardInfo.mCardType);
    }

    public void addExtraParams() throws IOException {
        super.addExtraParams();
        try {
            addParams(TSMAuthContants.PARAM_CPLC, this.mCardInfo.getTerminal().getCPLC());
        } catch (IOException | InterruptedException e) {
            throw new IOException("RechargeOrderListRequest getExtraParams failed", e);
        }
    }
}
