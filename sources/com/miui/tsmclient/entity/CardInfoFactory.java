package com.miui.tsmclient.entity;

import android.text.TextUtils;
import org.json.JSONObject;

public class CardInfoFactory {
    public static CardInfo makeCardInfo(String str, JSONObject jSONObject) {
        ObjectParser objectParser;
        if (str == null) {
            return null;
        }
        if (CardInfo.SPTC_TYPE_SET.contains(str)) {
            objectParser = new SptcCardInfo(str);
        } else if (TextUtils.equals(str, "BANKCARD")) {
            objectParser = new BankCardInfo();
        } else if (TextUtils.equals(str, CardInfo.CARD_TYPE_MIFARE)) {
            objectParser = new MifareCardInfo();
        } else {
            objectParser = new PayableCardInfo(str);
        }
        return (CardInfo) objectParser.parse(jSONObject);
    }
}
