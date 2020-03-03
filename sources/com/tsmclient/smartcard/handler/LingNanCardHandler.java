package com.tsmclient.smartcard.handler;

import android.text.TextUtils;
import cn.com.fmsh.script.constants.ScriptToolsConst;
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
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class LingNanCardHandler extends BaseTransCardHandler {
    private static final ByteArray LNT_AID = ByteArray.wrap(new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, 89, Constants.TagName.SIM_SEID, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.ORDER_BRIEF_INFO_LIST, 89});
    private static final ByteArray LNT_INTERNAL_AID = ByteArray.wrap(new byte[]{89, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.SIM_SEID, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 83, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG});
    private static final ByteArray LNT_INTERNAL_WALLET_AID = ByteArray.wrap(new byte[]{-35, -15});
    private static final ByteArray LNT_MOT_INTERNAL_AID = ByteArray.wrap(new byte[]{ScriptToolsConst.TagName.CommandSingle, 0, 0, 6, 50, 1, 1, 5, 88, 0, 2, 32, 88, 16, 0, 0});
    private static final String TAG = "LingNanCardHandler";
    private final ByteArray READ_BALANCE_CMD = ByteArray.wrap(new byte[]{Byte.MIN_VALUE, Constants.TagName.ORDER_TRADE_STATUSES, 0, 2, 4});

    /* access modifiers changed from: protected */
    public String getCardType() {
        return "LNT";
    }

    /* access modifiers changed from: protected */
    public void selectVerify() throws IOException, UnProcessableCardException {
        SelectCommand selectCommand = new SelectCommand();
        selectCommand.setP1((byte) 4);
        selectCommand.setData(LNT_MOT_INTERNAL_AID);
        assertResponse(transceive(selectCommand.toRawAPDU().toBytes()), STATUS_APP_NOT_FOUND, "LingNanTong MOT should not be selected");
        selectCommand.setData(LNT_INTERNAL_AID);
        assertResponse(transceive(selectCommand.toRawAPDU().toBytes()), "failed to select LingNanTong AID");
        selectCommand.setP1((byte) 0);
        selectCommand.setData(LNT_INTERNAL_WALLET_AID);
        assertResponse(transceive(selectCommand.toRawAPDU().toBytes()), "failed to select LingNanTong AID");
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getCardNumAndValidDate() throws IOException, UnProcessableCardException {
        HashMap hashMap = new HashMap();
        ReadBinaryCommand readBinaryCommand = new ReadBinaryCommand();
        readBinaryCommand.setP1(Constants.TagName.PREDEPOSIT_TYPE);
        byte[] transceive = transceive(readBinaryCommand.toRawAPDU().toBytes());
        assertResponse(transceive, "failed to get card num");
        String bytesToHexString = Coder.bytesToHexString(ByteArray.wrap(transceive, 53, 5).toBytes());
        Pattern compile = Pattern.compile("^0*$");
        if (bytesToHexString == null || compile.matcher(bytesToHexString).matches()) {
            throw new UnProcessableCardException(getClass().getSimpleName() + " illegal card number");
        }
        String bytesToHexString2 = Coder.bytesToHexString(ByteArray.wrap(transceive, 23, 4).toBytes());
        String bytesToHexString3 = Coder.bytesToHexString(ByteArray.wrap(transceive, 27, 4).toBytes());
        hashMap.put(CardConstants.KEY_ACCOUNT_NUM, bytesToHexString);
        hashMap.put(CardConstants.VALID_START, bytesToHexString2);
        hashMap.put(CardConstants.VALID_END, bytesToHexString3);
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public int getBalance() throws IOException {
        SelectCommand selectCommand = new SelectCommand();
        selectCommand.setP1((byte) 0);
        selectCommand.setData(ByteArray.wrap(new byte[]{-83, -13}));
        byte[] transceive = transceive(selectCommand.toRawAPDU().toBytes());
        if (!ByteArray.equals(STATUS_OK, ByteArray.wrap(transceive, transceive.length - 2, 2))) {
            return -999;
        }
        return Coder.bytesToInt(transceive(this.READ_BALANCE_CMD.toBytes()), 0, 4);
    }

    /* access modifiers changed from: protected */
    public void readRecord(ArrayList<TradeLog> arrayList, boolean z) throws IOException {
        SelectCommand selectCommand = new SelectCommand();
        selectCommand.setP1((byte) 0);
        selectCommand.setData(ByteArray.wrap(new byte[]{0, 24}));
        byte[] transceive = transceive(selectCommand.toRawAPDU().toBytes());
        if (ByteArray.equals(STATUS_OK, ByteArray.wrap(transceive, transceive.length - 2, 2))) {
            super.readRecord(arrayList, true);
            Iterator<TradeLog> it = arrayList.iterator();
            while (it.hasNext()) {
                TradeLog next = it.next();
                String tradeDate = next.getTradeDate();
                if (!TextUtils.isEmpty(tradeDate) && tradeDate.length() > 4) {
                    next.setTradeDate(tradeDate.substring(4, tradeDate.length()));
                }
            }
            return;
        }
        throw new IOException("failed to get record");
    }

    /* access modifiers changed from: protected */
    public ByteArray getConsumeTag() {
        return ByteArray.wrap(new byte[]{9, 6});
    }
}
