package com.miui.tsmclient.analytics.upload;

import android.content.Context;
import com.miui.tsmclient.account.AccountInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.net.AuthApiException;
import com.miui.tsmclient.net.AuthRequest;
import com.miui.tsmclient.net.BaseAuthManager;
import com.miui.tsmclient.net.TSMAuthContants;
import com.miui.tsmclient.util.DeviceUtils;
import com.miui.tsmclient.util.LogUtils;
import com.tsmclient.smartcard.Coder;
import java.io.IOException;
import java.util.Locale;

public class TSMDataStatAuthManager extends BaseAuthManager {
    public BaseResponse uploadDataStatInfo(Context context, String str) {
        int i;
        String str2 = "";
        try {
            AccountInfo accountInfo = getAccountInfo(context);
            sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_UPLOAD_DATA_STAT_INFO, AuthRequest.RespContentType.json).addParams("message", str).addParams(TSMAuthContants.PARAM_LANGUAGE, Locale.getDefault().toString()).addParams(TSMAuthContants.PARAM_MIUI_ROM_TYPE, DeviceUtils.getMIUIRomType((CardInfo) null)).addParams(TSMAuthContants.PARAM_MIUI_SYSTEM_VERSION, DeviceUtils.getRomVersion()).addParams("deviceId", Coder.hashDeviceInfo(DeviceUtils.getImei(context, (CardInfo) null))).addParams("deviceModel", DeviceUtils.getDeviceModel((CardInfo) null)).addParams(TSMAuthContants.PARAM_CPLC, getCplc(new CardInfo(CardInfo.CARD_TYPE_DUMMY))).create());
            LogUtils.d("upload data stat info success");
            i = 0;
        } catch (AuthApiException e) {
            LogUtils.e("upload data stat info failed with an exception", e);
            i = e.mErrorCode;
            str2 = e.mErrorMsg;
        }
        return new BaseResponse(i, str2, new Object[0]);
    }

    private String getCplc(CardInfo cardInfo) throws AuthApiException {
        try {
            return cardInfo.getTerminal().getCPLC();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LogUtils.e("failed to get cplc", e);
            throw new AuthApiException(11);
        } catch (IOException e2) {
            LogUtils.e("failed to get cplc", e2);
            throw new AuthApiException(13);
        }
    }

    private AccountInfo getAccountInfo(Context context) throws AuthApiException {
        AccountInfo loadAccountInfo = this.mAccountManager.loadAccountInfo(context);
        if (loadAccountInfo != null) {
            return loadAccountInfo;
        }
        throw new AuthApiException(14);
    }
}
