package com.miui.tsmclient.model;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.IOperation;
import com.miui.tsmclient.entity.CardInfo;

public interface ICardOperation<T extends CardInfo> extends IOperation {
    BaseResponse deleteCard(Context context, T t, Bundle bundle);

    BaseResponse isServiceAvailable(Context context, T t, Bundle bundle);

    BaseResponse issue(Context context, T t, Bundle bundle);

    BaseResponse preTransferOut(Context context, T t, Bundle bundle);

    BaseResponse queryCardInfo(Context context, T t, Bundle bundle);

    BaseResponse queryPurchaseRecord(Context context, T t);

    BaseResponse returnCard(Context context, T t, Bundle bundle);

    BaseResponse transferIn(Context context, T t, Bundle bundle);

    BaseResponse transferOut(Context context, T t, Bundle bundle);

    BaseResponse updateCardInfo(Context context, T t);
}
