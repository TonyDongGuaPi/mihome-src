package com.miui.tsmclient.net.request;

import com.miui.tsmclient.common.net.ResponseListener;
import com.miui.tsmclient.common.net.request.SecureRequest;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.GroupConfigInfo;
import com.miui.tsmclient.net.TSMAuthContants;
import com.miui.tsmclient.util.DeviceUtils;
import java.util.Locale;

public class ConfigListRequest extends SecureRequest<GroupConfigInfo> {
    public ConfigListRequest(CardInfo cardInfo, String str, ResponseListener<GroupConfigInfo> responseListener) {
        super(1, TSMAuthContants.URL_QUERY_GROUPED_CONFIGS, GroupConfigInfo.class, responseListener);
        addParams("deviceModel", DeviceUtils.getDeviceModel(cardInfo)).addParams("cardName", cardInfo == null ? null : cardInfo.mCardType).addParams("key", str).addParams(TSMAuthContants.PARAM_LANGUAGE, Locale.getDefault().toString()).addParams(TSMAuthContants.PARAM_MIUI_ROM_TYPE, DeviceUtils.getMIUIRomType(cardInfo)).addParams(TSMAuthContants.PARAM_MIUI_SYSTEM_VERSION, DeviceUtils.getRomVersion());
    }
}
