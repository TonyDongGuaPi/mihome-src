package com.tsmclient.smartcard.handler;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.CardConstants;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.ReaderUtil;
import com.tsmclient.smartcard.apdu.ReadBinaryCommand;
import com.tsmclient.smartcard.apdu.SelectCommand;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.terminal.APDUConstants;
import java.io.IOException;
import java.util.HashMap;

public class SZTCardHandler extends BaseTransCardHandler {
    private static final ByteArray SZT_CARD_AID = ByteArray.wrap(new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, 89, Constants.TagName.SIM_SEID, 83, Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_BACK_INFO_LIST});
    private static final ByteArray SZT_FID = ByteArray.wrap(new byte[]{16, 1});
    private static final String TAG = "SZTCardHandler";

    /* access modifiers changed from: protected */
    public String getCardType() {
        return "SZT";
    }

    /* access modifiers changed from: protected */
    public void selectVerify() throws IOException, UnProcessableCardException {
        SelectCommand selectCommand = new SelectCommand();
        selectCommand.setP1((byte) 4);
        if (this.mInternalRead) {
            selectCommand.setData(APDUConstants.AID_SZT);
            assertResponse(transceive(selectCommand.toRawAPDU().toBytes()), "failed to select SZT AID");
            selectCommand.setP1((byte) 0);
            selectCommand.setData(SZT_FID);
            assertResponse(transceive(selectCommand.toRawAPDU().toBytes()), "failed to select SZT FID");
            return;
        }
        selectCommand.setData(SZT_CARD_AID);
        assertResponse(transceive(selectCommand.toRawAPDU().toBytes()), "failed to select SZT AID");
    }

    /* access modifiers changed from: protected */
    public HashMap<String, String> getCardNumAndValidDate() throws IOException, UnProcessableCardException {
        HashMap<String, String> hashMap = new HashMap<>();
        ReadBinaryCommand readBinaryCommand = new ReadBinaryCommand();
        readBinaryCommand.setP1(Constants.TagName.PREDEPOSIT_TYPE);
        byte[] transceive = transceive(readBinaryCommand.toRawAPDU().toBytes());
        assertResponse(transceive, "failed to get card num");
        String cardNum = getCardNum(ByteArray.wrap(transceive, 16, 4));
        String bytesToHexString = Coder.bytesToHexString(ByteArray.wrap(transceive, 20, 4).toBytes());
        String bytesToHexString2 = Coder.bytesToHexString(ByteArray.wrap(transceive, 24, 4).toBytes());
        hashMap.put(CardConstants.KEY_ACCOUNT_NUM, cardNum);
        hashMap.put(CardConstants.VALID_START, bytesToHexString);
        hashMap.put(CardConstants.VALID_END, bytesToHexString2);
        return hashMap;
    }

    private String getCardNum(ByteArray byteArray) {
        return Integer.toString(Coder.bytesToInt(Coder.str2Bcd(ReaderUtil.invertString(Coder.bytesToHexString(byteArray.toBytes()), 2))));
    }

    /* access modifiers changed from: protected */
    public int getBalance() throws IOException, UnProcessableCardException {
        byte[] transceive = transceive(GET_BALANCE_CMD.toBytes());
        assertResponse(transceive, "failed to get balance");
        return Coder.bytesToInt(ByteArray.wrap(transceive, 0, 4).toBytes()) - Integer.MIN_VALUE;
    }
}
