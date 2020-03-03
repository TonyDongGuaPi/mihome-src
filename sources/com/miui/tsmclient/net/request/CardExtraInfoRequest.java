package com.miui.tsmclient.net.request;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.miui.tsmclient.common.net.request.SecureRequest;
import com.miui.tsmclient.entity.CardExtraInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.net.TSMAuthContants;
import com.miui.tsmclient.util.SysUtils;
import java.io.IOException;
import java.util.Locale;

public class CardExtraInfoRequest extends SecureRequest<CardExtraInfo> {
    private CardInfo mCardInfo;
    private String mCplc;

    public CardExtraInfoRequest(CardInfo cardInfo, String str) {
        super(0, TSMAuthContants.URL_TRANSIT_CARDS_EXTRA_INFO, TypeToken.get(CardExtraInfo.class));
        this.mCardInfo = cardInfo;
        this.mCplc = str;
        addParams("deviceModel", SysUtils.getDeviceModel(this.mCardInfo)).addParams("type", cardInfo.mCardType).addParams(TSMAuthContants.PARAM_CARDNO, cardInfo.mCardNo).addParams(TSMAuthContants.PARAM_REAL_CARDNO, cardInfo.mRealCardNo).addParams(TSMAuthContants.PARAM_LANGUAGE, Locale.getDefault().toString()).addParams(TSMAuthContants.PARAM_MIUI_ROM_TYPE, SysUtils.getMIUIRomType(this.mCardInfo)).addParams(TSMAuthContants.PARAM_MIUI_SYSTEM_VERSION, SysUtils.getRomVersion());
    }

    public void addExtraParams() throws IOException {
        super.addExtraParams();
        if (TextUtils.isEmpty(this.mCardInfo.mAid) || TextUtils.isEmpty(this.mCplc)) {
            throw new IOException("params can't be null");
        }
        addParams("aid", this.mCardInfo.mAid);
        addParams(TSMAuthContants.PARAM_CPLC, this.mCplc);
    }
}
