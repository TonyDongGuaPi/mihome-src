package com.miui.tsmclient.model;

import android.text.TextUtils;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.bankcard.BankCardOperation;
import com.miui.tsmclient.model.fmsh.FmshCardOperation;
import com.miui.tsmclient.model.mifare.MifareCardOperation;
import com.miui.tsmclient.model.mitsm.AsyncMiTSMCardOperation;

public class CardOperationFactory {
    public static ICardOperation createCardOperation(String str) {
        if (CardInfo.SPTC_TYPE_SET.contains(str)) {
            return new FmshCardOperation(str);
        }
        if (TextUtils.equals(str, "BANKCARD")) {
            return new BankCardOperation();
        }
        if (TextUtils.equals(str, CardInfo.CARD_TYPE_VSIM)) {
            return new AsyncMiTSMCardOperation();
        }
        if (TextUtils.equals(str, CardInfo.CARD_TYPE_MIFARE)) {
            return new MifareCardOperation();
        }
        return new PayableCardOperation(str);
    }
}
