package com.miui.tsmclient.model.mifare;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.tsmclient.database.CardDataUtil;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.MifareCardInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.mitsm.MiTSMCardOperation;
import com.miui.tsmclient.seitsm.TsmRpcModels;
import com.miui.tsmclient.util.PrefUtils;
import java.util.List;

public class MifareCardOperation extends MiTSMCardOperation<MifareCardClient, MifareCardInfo> {
    /* access modifiers changed from: protected */
    public MifareCardClient createCardClient() {
        return new MifareCardClient();
    }

    public BaseResponse issue(Context context, MifareCardInfo mifareCardInfo, Bundle bundle) {
        return ((MifareCardClient) this.mMiTSMCardClient).issue(context, mifareCardInfo, bundle);
    }

    public BaseResponse updateCardInfo(Context context, MifareCardInfo mifareCardInfo) {
        BaseResponse updateMifareCardInfo;
        if (mifareCardInfo == null || TextUtils.isEmpty(mifareCardInfo.mAid)) {
            if (PrefUtils.getBoolean(context, PrefUtils.PREF_KEY_NOTIFY_SERVER_UPDATE_CARD, false)) {
                List<CardInfo> loadCardList = CardDataUtil.loadCardList(context, CardInfo.CARD_TYPE_MIFARE);
                if (loadCardList != null && ((updateMifareCardInfo = ((MifareCardClient) this.mMiTSMCardClient).updateMifareCardInfo(context, (MifareCardInfo[]) loadCardList.toArray(new MifareCardInfo[loadCardList.size()]))) == null || updateMifareCardInfo.mResultCode != 0)) {
                    return updateMifareCardInfo;
                }
                PrefUtils.putBoolean(context, PrefUtils.PREF_KEY_NOTIFY_SERVER_UPDATE_CARD, false);
            }
            BaseResponse queryMifareCardInfo = ((MifareCardClient) this.mMiTSMCardClient).queryMifareCardInfo(context);
            if (queryMifareCardInfo.mResultCode == 0) {
                queryMifareCardInfo.mDatas[0] = MifareCardClient.fillFromTsm(context, ((TsmRpcModels.QueryDoorCardInfoResponse) queryMifareCardInfo.mDatas[0]).getCardInfoListList());
            }
            return queryMifareCardInfo;
        }
        return ((MifareCardClient) this.mMiTSMCardClient).updateMifareCardInfo(context, mifareCardInfo);
    }

    public BaseResponse queryCardInfo(Context context, MifareCardInfo mifareCardInfo, Bundle bundle) {
        return new BaseResponse(0, mifareCardInfo);
    }
}
