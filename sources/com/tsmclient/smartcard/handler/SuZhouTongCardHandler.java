package com.tsmclient.smartcard.handler;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.CardConstants;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.apdu.ReadBinaryCommand;
import com.tsmclient.smartcard.apdu.SelectCommand;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.model.TradeLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SuZhouTongCardHandler extends BaseTransCardHandler {
    private static final ByteArray GET_BALANCE_CMD = ByteArray.wrap(new byte[]{Byte.MIN_VALUE, Constants.TagName.ORDER_TRADE_STATUSES, 0, 1, 4});
    private static final ByteArray PIN_AUTH_CMD = ByteArray.wrap(new byte[]{0, 32, 0, 0, 3, 18, 52, Constants.TagName.QUERY_DATA_SORT_TYPE});
    private static final ByteArray SUZHOUTONG_AID = ByteArray.wrap(new byte[]{83, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 88, Constants.TagName.ORDER_BRIEF_INFO, 78, Constants.TagName.SIM_SEID, 77, Constants.TagName.TERMINAL_BASEBAND_VERSION});

    /* access modifiers changed from: protected */
    public String getCardType() {
        return CardConstants.SUZHOUTONG;
    }

    /* access modifiers changed from: protected */
    public void selectVerify() throws IOException, UnProcessableCardException {
        SelectCommand selectCommand = new SelectCommand();
        selectCommand.setP1((byte) 4);
        selectCommand.setData(SUZHOUTONG_AID);
        assertResponse(transceive(selectCommand.toRawAPDU().toBytes()), "failed to select SUZHOUTONG AID");
        selectCommand.setP1((byte) 0);
        selectCommand.setData(ByteArray.wrap(new byte[]{-33, 1}));
        assertResponse(transceive(selectCommand.toRawAPDU().toBytes()), "failed to select DF 01");
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getCardNumAndValidDate() throws IOException, UnProcessableCardException {
        HashMap hashMap = new HashMap();
        ReadBinaryCommand readBinaryCommand = new ReadBinaryCommand();
        readBinaryCommand.setP1(Constants.TagName.PREDEPOSIT_TYPE);
        byte[] transceive = transceive(readBinaryCommand.toRawAPDU().toBytes());
        assertResponse(transceive, "failed to get card num");
        String bytesToHexString = Coder.bytesToHexString(ByteArray.wrap(transceive, 0, 8).toBytes());
        String bytesToHexString2 = Coder.bytesToHexString(ByteArray.wrap(transceive, 20, 4).toBytes());
        String bytesToHexString3 = Coder.bytesToHexString(ByteArray.wrap(transceive, 24, 4).toBytes());
        hashMap.put(CardConstants.KEY_ACCOUNT_NUM, bytesToHexString);
        hashMap.put(CardConstants.VALID_START, bytesToHexString2);
        hashMap.put(CardConstants.VALID_END, bytesToHexString3);
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public int getBalance() throws IOException, UnProcessableCardException {
        assertResponse(transceive(PIN_AUTH_CMD.toBytes()), "failed to auth pin");
        byte[] transceive = transceive(GET_BALANCE_CMD.toBytes());
        assertResponse(transceive, "failed to get balance");
        return Coder.bytesToInt(ByteArray.wrap(transceive, 0, 4).toBytes());
    }

    /* access modifiers changed from: protected */
    public void readRecord(ArrayList<TradeLog> arrayList, boolean z) throws IOException {
        try {
            assertResponse(transceive(PIN_AUTH_CMD.toBytes()), "failed to auth pin");
            super.readRecord(arrayList, z);
        } catch (UnProcessableCardException unused) {
        }
    }
}
