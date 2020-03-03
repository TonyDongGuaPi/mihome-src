package com.miui.tsmclient.net.request;

import com.google.gson.reflect.TypeToken;
import com.miui.tsmclient.common.net.request.SecureRequest;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.NfcConfigsResponse;
import com.miui.tsmclient.net.TSMAuthContants;
import com.miui.tsmclient.util.SysUtils;
import java.util.Locale;

public class NfcConfigsRequest extends SecureRequest<NfcConfigsResponse> {
    public NfcConfigsRequest(String str) {
        super(0, TSMAuthContants.URL_FETCH_NFC_CONFIGS, TypeToken.get(NfcConfigsResponse.class));
        addParams("deviceModel", SysUtils.getDeviceModel((CardInfo) null)).addParams(TSMAuthContants.PARAM_LANGUAGE, Locale.getDefault().toString()).addParams(TSMAuthContants.PARAM_MIUI_ROM_TYPE, SysUtils.getMIUIRomType((CardInfo) null)).addParams(TSMAuthContants.PARAM_MIUI_SYSTEM_VERSION, SysUtils.getRomVersion()).addParams(TSMAuthContants.PARAM_CPLC, str);
    }
}
