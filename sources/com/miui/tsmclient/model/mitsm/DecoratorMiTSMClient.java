package com.miui.tsmclient.model.mitsm;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.mitsm.TSMSessionManager;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.seitsm.TsmRpcModels;
import java.io.IOException;

public class DecoratorMiTSMClient extends MiTSMCardClient {
    private MiTSMCardClient mMiTSMCardClient;

    public DecoratorMiTSMClient(MiTSMCardClient miTSMCardClient) {
        this.mMiTSMCardClient = miTSMCardClient;
    }

    public BaseResponse issue(Context context, CardInfo cardInfo, Bundle bundle) {
        return this.mMiTSMCardClient.issue(context, cardInfo, bundle);
    }

    public BaseResponse delete(Context context, CardInfo cardInfo, Bundle bundle) {
        return this.mMiTSMCardClient.delete(context, cardInfo, bundle);
    }

    public BaseResponse transferIn(Context context, CardInfo cardInfo, Bundle bundle) {
        return this.mMiTSMCardClient.transferIn(context, cardInfo, bundle);
    }

    public BaseResponse transferOut(Context context, CardInfo cardInfo, Bundle bundle) {
        return this.mMiTSMCardClient.transferOut(context, cardInfo, bundle);
    }

    public BaseResponse returnCard(Context context, CardInfo cardInfo, Bundle bundle) {
        return this.mMiTSMCardClient.returnCard(context, cardInfo, bundle);
    }

    public BaseResponse lock(Context context, CardInfo cardInfo, Bundle bundle) {
        return this.mMiTSMCardClient.lock(context, cardInfo, bundle);
    }

    public TsmRpcModels.TsmSessionInfo getSession(Context context, CardInfo cardInfo, TSMSessionManager.BusinessType businessType) throws SeiTSMApiException {
        return this.mMiTSMCardClient.getSession(context, cardInfo, businessType);
    }

    public TsmRpcModels.TsmSessionInfo getSession(Context context, CardInfo cardInfo, TSMSessionManager.BusinessType businessType, boolean z) throws SeiTSMApiException {
        return this.mMiTSMCardClient.getSession(context, cardInfo, businessType, z);
    }

    public BaseResponse syncEse(Context context, CardInfo cardInfo, TsmRpcModels.TsmSessionInfo tsmSessionInfo, Bundle bundle) throws IOException, SeiTSMApiException, InterruptedException {
        return this.mMiTSMCardClient.syncEse(context, cardInfo, tsmSessionInfo, bundle);
    }
}
