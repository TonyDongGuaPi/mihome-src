package com.tsmclient.smartcard.handler;

import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.alipay.sdk.sys.a;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.CardConstants;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.apdu.GetDataCommand;
import com.tsmclient.smartcard.apdu.ReadRecordCommand;
import com.tsmclient.smartcard.apdu.SelectCommand;
import com.tsmclient.smartcard.exception.InvalidTLVException;
import com.tsmclient.smartcard.exception.TagNotFoundException;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.model.TradeLog;
import com.tsmclient.smartcard.terminal.APDUConstants;
import com.tsmclient.smartcard.terminal.CommandApdu;
import com.tsmclient.smartcard.terminal.IScTerminal;
import com.tsmclient.smartcard.terminal.response.ScResponse;
import com.tsmclient.smartcard.tlv.ITLVObject;
import com.tsmclient.smartcard.tlv.TLVParser;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.youpin.network.annotation.Encoding;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class BankCardHandler implements ISmartCardHandler<IsoDep> {
    private static final ByteArray AU_AMOUNT = ByteArray.wrap(new byte[]{0, 0, 0, 0, 0, 0});
    private static final ByteArray AU_AMOUNT_OTHER = ByteArray.wrap(new byte[]{0, 0, 0, 0, 0, 0});
    private static final ByteArray CUR_CODE = ByteArray.wrap(new byte[]{1, Constants.TagName.QUERY_DATA_SORT_TYPE});
    public static final String EXTRAS_KEY_PAN_LIST = "extras_key_pan_list";
    private static final ByteArray NOT_EXISTS = ByteArray.wrap(new byte[]{Constants.TagName.PAY_ORDER_ID, -126});
    private static final ByteArray RANDOM_NUMBER = ByteArray.wrap(new byte[]{1, 2, 3, 4});
    private static final String TAG = "BankCardHandler";
    private static final ByteArray TAG_AEF_ENTRANCE = ByteArray.wrap(new byte[]{97});
    private static final ByteArray TAG_AID = ByteArray.wrap(new byte[]{Constants.TagName.CP_NO});
    private static final ByteArray TAG_APP = ByteArray.wrap(new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST});
    private static final ByteArray TAG_BANK_CUSTOM_DATA = ByteArray.wrap(new byte[]{Constants.TagName.STATION_ID, 12});
    private static final ByteArray TAG_CARD_NUM = ByteArray.wrap(new byte[]{87});
    private static final ByteArray TAG_FCI_DATA_TEMPLATE = ByteArray.wrap(new byte[]{-91});
    private static final ByteArray TAG_PDOL = ByteArray.wrap(new byte[]{-97, ScriptToolsConst.TagName.TagSerial});
    private static final ByteArray TER_TRADE_TYPE = ByteArray.wrap(new byte[]{Constants.TagName.ELECTRONIC_OUT_SERIAL, 0, 0, 0});
    private static final ByteArray TRADE_TYPE = ByteArray.wrap(new byte[]{48});
    private static final ByteArray TVR = ByteArray.wrap(new byte[]{0, 0, 0, 0, 0});
    public static final ByteArray UNION_PAY_AID = ByteArray.wrap(new byte[]{ScriptToolsConst.TagName.CommandSingle, 0, 0, 3, 51});
    public static final ByteArray VISA_AID = ByteArray.wrap(new byte[]{ScriptToolsConst.TagName.CommandSingle, 0, 0, 0, 3});
    public static final ByteArray VISA_CREDIT = ByteArray.wrap(new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_INFO_LIST});
    public static final ByteArray VISA_DEBIT = ByteArray.wrap(new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.INVOICE_TOKEN, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_INFO_LIST});
    private int mCardScheme;

    public int getTechType() {
        return 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0160  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x021f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Bundle onHandleCard(android.nfc.tech.IsoDep r12) throws java.io.IOException, com.tsmclient.smartcard.exception.UnProcessableCardException {
        /*
            r11 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            r2 = 5
            byte[] r2 = new byte[r2]
            r2 = {0, -78, 1, 12, 0} // fill-array
            com.tsmclient.smartcard.ByteArray r2 = com.tsmclient.smartcard.ByteArray.wrap((byte[]) r2)
            byte[] r2 = r2.toBytes()
            r12.transceive(r2)
            com.tsmclient.smartcard.apdu.SelectCommand r2 = new com.tsmclient.smartcard.apdu.SelectCommand
            r2.<init>()
            r3 = 4
            r2.setP1(r3)
            com.tsmclient.smartcard.ByteArray r3 = AID_PPSE
            r2.setData(r3)
            com.tsmclient.smartcard.ByteArray r3 = r2.toRawAPDU()
            byte[] r3 = r3.toBytes()
            byte[] r3 = r12.transceive(r3)
            if (r3 == 0) goto L_0x023f
            int r4 = r3.length
            r5 = 2
            if (r4 < r5) goto L_0x023f
            com.tsmclient.smartcard.ByteArray r4 = NOT_EXISTS
            com.tsmclient.smartcard.ByteArray r6 = com.tsmclient.smartcard.ByteArray.wrap((byte[]) r3)
            boolean r4 = com.tsmclient.smartcard.ByteArray.equals(r4, r6)
            if (r4 != 0) goto L_0x0237
            java.lang.String r4 = "BankCardHandler"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "ppse = "
            r6.append(r7)
            java.lang.String r7 = com.tsmclient.smartcard.Coder.bytesToHexString((byte[]) r3)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            android.util.Log.d(r4, r6)
            r4 = 0
            r6 = 0
            int r7 = r3.length     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            int r7 = r7 - r5
            com.tsmclient.smartcard.ByteArray r7 = com.tsmclient.smartcard.ByteArray.wrap(r3, r4, r7)     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            com.tsmclient.smartcard.tlv.ITLVObject r7 = com.tsmclient.smartcard.tlv.TLVParser.parse(r7)     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            com.tsmclient.smartcard.tlv.ITLVValue r7 = r7.getValue()     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            com.tsmclient.smartcard.ByteArray r8 = TAG_FCI_DATA_TEMPLATE     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            com.tsmclient.smartcard.tlv.ITLVObject r7 = r7.findTLV(r8)     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            com.tsmclient.smartcard.tlv.ITLVValue r7 = r7.getValue()     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            com.tsmclient.smartcard.ByteArray r8 = TAG_BANK_CUSTOM_DATA     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            com.tsmclient.smartcard.tlv.ITLVObject r7 = r7.findTLV(r8)     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            com.tsmclient.smartcard.tlv.ITLVValue r7 = r7.getValue()     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            com.tsmclient.smartcard.ByteArray r8 = TAG_AEF_ENTRANCE     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            com.tsmclient.smartcard.tlv.ITLVObject r7 = r7.findTLV(r8)     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            com.tsmclient.smartcard.tlv.ITLVValue r7 = r7.getValue()     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            com.tsmclient.smartcard.ByteArray r8 = TAG_AID     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            com.tsmclient.smartcard.tlv.ITLVObject r7 = r7.findTLV(r8)     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            com.tsmclient.smartcard.tlv.ITLVValue r7 = r7.getValue()     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            com.tsmclient.smartcard.ByteArray r7 = r7.toBytes()     // Catch:{ InvalidTLVException -> 0x00ae, TagNotFoundException -> 0x009e }
            goto L_0x00c9
        L_0x009e:
            r12 = move-exception
            java.lang.String r0 = "BankCardHandler"
            java.lang.String r1 = "error when parse tlv"
            android.util.Log.e(r0, r1, r12)
            com.tsmclient.smartcard.exception.UnProcessableCardException r12 = new com.tsmclient.smartcard.exception.UnProcessableCardException
            java.lang.String r0 = "BankCardHandler: unsupported card type"
            r12.<init>(r0)
            throw r12
        L_0x00ae:
            java.lang.String r7 = "BankCardHandler"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "invalid res: "
            r8.append(r9)
            java.lang.String r3 = com.tsmclient.smartcard.Coder.bytesToHexString((byte[]) r3)
            r8.append(r3)
            java.lang.String r3 = r8.toString()
            android.util.Log.e(r7, r3)
            r7 = r6
        L_0x00c9:
            if (r7 == 0) goto L_0x022f
            r2.setData(r7)
            com.tsmclient.smartcard.ByteArray r2 = r2.toRawAPDU()
            byte[] r2 = r2.toBytes()
            byte[] r2 = r12.transceive(r2)
            if (r2 == 0) goto L_0x0227
            int r3 = r2.length
            if (r3 < r5) goto L_0x0227
            java.lang.String r3 = "BankCardHandler"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Qppse = "
            r8.append(r9)
            java.lang.String r9 = com.tsmclient.smartcard.Coder.bytesToHexString((byte[]) r2)
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            android.util.Log.d(r3, r8)
            byte[] r3 = r11.getEntranceOfTrade(r2)
            int r8 = r2.length     // Catch:{ InvalidTLVException -> 0x0142, TagNotFoundException -> 0x0138 }
            int r8 = r8 - r5
            com.tsmclient.smartcard.ByteArray r4 = com.tsmclient.smartcard.ByteArray.wrap(r2, r4, r8)     // Catch:{ InvalidTLVException -> 0x0142, TagNotFoundException -> 0x0138 }
            com.tsmclient.smartcard.tlv.ITLVObject r4 = com.tsmclient.smartcard.tlv.TLVParser.parse(r4)     // Catch:{ InvalidTLVException -> 0x0142, TagNotFoundException -> 0x0138 }
            com.tsmclient.smartcard.tlv.ITLVValue r4 = r4.getValue()     // Catch:{ InvalidTLVException -> 0x0142, TagNotFoundException -> 0x0138 }
            com.tsmclient.smartcard.ByteArray r8 = TAG_FCI_DATA_TEMPLATE     // Catch:{ InvalidTLVException -> 0x0142, TagNotFoundException -> 0x0138 }
            com.tsmclient.smartcard.tlv.ITLVObject r4 = r4.findTLV(r8)     // Catch:{ InvalidTLVException -> 0x0142, TagNotFoundException -> 0x0138 }
            com.tsmclient.smartcard.tlv.ITLVValue r8 = r4.getValue()     // Catch:{ InvalidTLVException -> 0x0142, TagNotFoundException -> 0x0138 }
            com.tsmclient.smartcard.ByteArray r9 = TAG_APP     // Catch:{ InvalidTLVException -> 0x0142, TagNotFoundException -> 0x0138 }
            com.tsmclient.smartcard.tlv.ITLVObject r8 = r8.findTLV(r9)     // Catch:{ InvalidTLVException -> 0x0142, TagNotFoundException -> 0x0138 }
            com.tsmclient.smartcard.tlv.ITLVValue r8 = r8.getValue()     // Catch:{ InvalidTLVException -> 0x0142, TagNotFoundException -> 0x0138 }
            com.tsmclient.smartcard.ByteArray r8 = r8.toBytes()     // Catch:{ InvalidTLVException -> 0x0142, TagNotFoundException -> 0x0138 }
            com.tsmclient.smartcard.tlv.ITLVValue r4 = r4.getValue()     // Catch:{ InvalidTLVException -> 0x0143, TagNotFoundException -> 0x0136 }
            com.tsmclient.smartcard.ByteArray r9 = TAG_PDOL     // Catch:{ InvalidTLVException -> 0x0143, TagNotFoundException -> 0x0136 }
            com.tsmclient.smartcard.tlv.ITLVObject r4 = r4.findTLV(r9)     // Catch:{ InvalidTLVException -> 0x0143, TagNotFoundException -> 0x0136 }
            com.tsmclient.smartcard.tlv.ITLVValue r4 = r4.getValue()     // Catch:{ InvalidTLVException -> 0x0143, TagNotFoundException -> 0x0136 }
            com.tsmclient.smartcard.ByteArray r4 = r4.toBytes()     // Catch:{ InvalidTLVException -> 0x0143, TagNotFoundException -> 0x0136 }
            goto L_0x015e
        L_0x0136:
            r2 = move-exception
            goto L_0x013a
        L_0x0138:
            r2 = move-exception
            r8 = r6
        L_0x013a:
            java.lang.String r4 = "BankCardHandler"
            java.lang.String r9 = "Error when parse tlv"
            android.util.Log.e(r4, r9, r2)
            goto L_0x015d
        L_0x0142:
            r8 = r6
        L_0x0143:
            java.lang.String r4 = "BankCardHandler"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Invalid res: "
            r9.append(r10)
            java.lang.String r2 = com.tsmclient.smartcard.Coder.bytesToHexString((byte[]) r2)
            r9.append(r2)
            java.lang.String r2 = r9.toString()
            android.util.Log.e(r4, r2)
        L_0x015d:
            r4 = r6
        L_0x015e:
            if (r4 == 0) goto L_0x021f
            int r2 = r11.getCardType(r7, r8)
            com.tsmclient.smartcard.ByteArray r4 = r11.getGPOInputData(r4)
            com.tsmclient.smartcard.apdu.GPOCommand r7 = new com.tsmclient.smartcard.apdu.GPOCommand
            r7.<init>()
            r7.setData(r4)
            com.tsmclient.smartcard.ByteArray r4 = r7.toRawAPDU()
            byte[] r4 = r4.toBytes()
            byte[] r4 = r12.transceive(r4)
            if (r4 == 0) goto L_0x0217
            int r4 = r4.length
            if (r4 < r5) goto L_0x0217
            com.tsmclient.smartcard.apdu.ReadRecordCommand r4 = new com.tsmclient.smartcard.apdu.ReadRecordCommand
            r4.<init>()
            r7 = 1
            r8 = r6
            r6 = 1
        L_0x0189:
            r9 = 10
            if (r6 > r9) goto L_0x01bd
            r4.setP1(r6)
            r8 = 12
            r4.setP2(r8)
            com.tsmclient.smartcard.ByteArray r8 = r4.toRawAPDU()
            byte[] r8 = r8.toBytes()
            byte[] r8 = r12.transceive(r8)
            if (r8 == 0) goto L_0x01b5
            int r9 = r8.length
            if (r9 < r5) goto L_0x01b5
            java.lang.String r8 = r11.getCardNumString(r8)
            boolean r9 = android.text.TextUtils.isEmpty(r8)
            if (r9 != 0) goto L_0x01b1
            goto L_0x01bd
        L_0x01b1:
            int r6 = r6 + 1
            byte r6 = (byte) r6
            goto L_0x0189
        L_0x01b5:
            java.io.IOException r12 = new java.io.IOException
            java.lang.String r0 = "failed to read card number"
            r12.<init>(r0)
            throw r12
        L_0x01bd:
            boolean r4 = android.text.TextUtils.isEmpty(r8)
            if (r4 != 0) goto L_0x020f
            byte[] r4 = r11.getTradeLogFormat(r12)
            r11.getTradeLog(r12, r3, r4, r0)
            int r3 = r11.getATC(r12)
            float r4 = r11.getEBalance(r12)
            float r5 = r11.getEPerLimit(r12)
            float r12 = r11.getEBanlanceLimit(r12)
            java.lang.String r6 = "success"
            r1.putBoolean(r6, r7)
            java.lang.String r6 = "card_type"
            r1.putInt(r6, r7)
            java.lang.String r6 = "card_scheme"
            int r7 = r11.mCardScheme
            r1.putInt(r6, r7)
            java.lang.String r6 = "bank_card_type"
            r1.putInt(r6, r2)
            java.lang.String r2 = "account_num"
            r1.putString(r2, r8)
            java.lang.String r2 = "atc"
            r1.putInt(r2, r3)
            java.lang.String r2 = "e_balance"
            r1.putFloat(r2, r4)
            java.lang.String r2 = "per_limit"
            r1.putFloat(r2, r5)
            java.lang.String r2 = "e_balance_limit"
            r1.putFloat(r2, r12)
            java.lang.String r12 = "trade_log"
            r1.putParcelableArrayList(r12, r0)
            return r1
        L_0x020f:
            java.io.IOException r12 = new java.io.IOException
            java.lang.String r0 = "failed to get cardNum string"
            r12.<init>(r0)
            throw r12
        L_0x0217:
            java.io.IOException r12 = new java.io.IOException
            java.lang.String r0 = "failed to get AFL"
            r12.<init>(r0)
            throw r12
        L_0x021f:
            com.tsmclient.smartcard.exception.UnProcessableCardException r12 = new com.tsmclient.smartcard.exception.UnProcessableCardException
            java.lang.String r0 = "failed to get pdol"
            r12.<init>(r0)
            throw r12
        L_0x0227:
            java.io.IOException r12 = new java.io.IOException
            java.lang.String r0 = "failed to select qPBOC"
            r12.<init>(r0)
            throw r12
        L_0x022f:
            java.io.IOException r12 = new java.io.IOException
            java.lang.String r0 = "failed to get aid or appBytes"
            r12.<init>(r0)
            throw r12
        L_0x0237:
            com.tsmclient.smartcard.exception.UnProcessableCardException r12 = new com.tsmclient.smartcard.exception.UnProcessableCardException
            java.lang.String r0 = "BankCardHandler: unsupported card type"
            r12.<init>(r0)
            throw r12
        L_0x023f:
            java.io.IOException r12 = new java.io.IOException
            java.lang.String r0 = "failed to select PPSE"
            r12.<init>(r0)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tsmclient.smartcard.handler.BankCardHandler.onHandleCard(android.nfc.tech.IsoDep):android.os.Bundle");
    }

    public Bundle onHandleCard(IScTerminal iScTerminal, Bundle bundle) throws IOException, UnProcessableCardException {
        Bundle bundle2 = new Bundle();
        ArrayList arrayList = new ArrayList();
        try {
            if (ByteArray.equals(iScTerminal.transmit(APDUConstants.SELECT_CRS).getStatus(), ScResponse.STATUS_OK)) {
                byte[] bArr = APDUConstants.PBOC_CARD_AID_PREFFIX;
                byte[] bArr2 = new byte[(bArr.length + 2 + 5)];
                bArr2[0] = Constants.TagName.CP_NO;
                bArr2[1] = (byte) (bArr.length & 255);
                System.arraycopy(bArr, 0, bArr2, 2, bArr.length);
                System.arraycopy(APDUConstants.COMM_TAG_GET_STATUS.toBytes(), 0, bArr2, bArr.length + 2, APDUConstants.COMM_TAG_GET_STATUS.toBytes().length);
                CommandApdu clone = APDUConstants.COMM_PREFIX_GET_STATUS.clone();
                clone.setData(bArr2);
                ScResponse transmit = iScTerminal.transmit(clone.toBytes());
                if (ByteArray.equals(transmit.getStatus(), ScResponse.STATUS_REFERENCE_NOT_FOUND)) {
                    return null;
                }
                while (true) {
                    if (!ByteArray.equals(transmit.getStatus(), ScResponse.STATUS_MORE_DATA_AVAILABLE) && !ByteArray.equals(transmit.getStatus(), ScResponse.STATUS_OK)) {
                        break;
                    }
                    if (clone.getP2() != 1) {
                        clone.setP2(1);
                    }
                    List<ITLVObject> findTLVList = TLVParser.parseTLVValue(transmit.getData()).findTLVList(TAG_AEF_ENTRANCE);
                    SelectCommand selectCommand = new SelectCommand();
                    selectCommand.setP1((byte) 4);
                    for (ITLVObject value : findTLVList) {
                        ByteArray bytes = value.getValue().findTLV(TAG_AID).getValue().toBytes();
                        selectCommand.setData(bytes);
                        ScResponse transmit2 = iScTerminal.transmit(selectCommand.toRawAPDU().toBytes());
                        if (ByteArray.equals(transmit2.getStatus(), ScResponse.STATUS_OK)) {
                            ReadRecordCommand readRecordCommand = new ReadRecordCommand();
                            readRecordCommand.setP1((byte) 1);
                            readRecordCommand.setP2((byte) 12);
                            transmit2 = iScTerminal.transmit(readRecordCommand.toRawAPDU().toBytes());
                            String cardNumString = getCardNumString(transmit2.toBytes());
                            if (!TextUtils.isEmpty(cardNumString)) {
                                arrayList.add(bytes.toString() + a.b + cardNumString);
                            }
                        }
                        transmit = transmit2;
                    }
                }
                bundle2.putStringArrayList(EXTRAS_KEY_PAN_LIST, arrayList);
                return bundle2;
            }
            throw new IOException("failed to select CRS");
        } catch (InvalidTLVException e) {
            Log.e(TAG, "onHandleCard return error when parse tlv", e);
        } catch (TagNotFoundException e2) {
            Log.e(TAG, "onHandleCard return error when parse tlv", e2);
        } catch (InterruptedException unused) {
            throw new IOException("onHandleCard is interrupted");
        }
    }

    private int getCardType(ByteArray byteArray, ByteArray byteArray2) throws UnProcessableCardException {
        if (byteArray == null || byteArray2 == null) {
            throw new UnProcessableCardException("BankCardHandler: unsupported card type");
        } else if (ByteArray.equals(byteArray.duplicate(0, 5), UNION_PAY_AID)) {
            this.mCardScheme = 1;
            byte b = byteArray.get(7);
            if (b == 6) {
                return 4;
            }
            switch (b) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                default:
                    return 1;
            }
        } else if (ByteArray.equals(byteArray.duplicate(0, 5), VISA_AID)) {
            this.mCardScheme = 2;
            return byteArray2.toString().contains(VISA_DEBIT.toString()) ? 1 : 2;
        } else {
            throw new UnProcessableCardException("BankCardHandler: unsupported card type");
        }
    }

    private void getTradeLog(IsoDep isoDep, byte[] bArr, byte[] bArr2, ArrayList<TradeLog> arrayList) throws IOException {
        byte bytesLow = Coder.toBytesLow((bArr[0] << 3) + 4);
        ReadRecordCommand readRecordCommand = new ReadRecordCommand();
        readRecordCommand.setP2(bytesLow);
        int i = 0;
        while (true) {
            if (i < Coder.bytesToInt(bArr[1])) {
                i++;
                readRecordCommand.setP1(Coder.toBytesLow(i));
                byte[] bArr3 = null;
                try {
                    bArr3 = isoDep.transceive(readRecordCommand.toRawAPDU().toBytes());
                } catch (IOException e) {
                    Log.e(TAG, "failed to get per log", e);
                }
                if (bArr3 == null) {
                    throw new IOException("failed to get per trade log");
                } else if (bArr3[0] != 106) {
                    arrayList.add(translateLog(bArr3, bArr2));
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private TradeLog translateLog(byte[] bArr, byte[] bArr2) {
        int i;
        String str;
        TradeLog tradeLog = new TradeLog();
        String str2 = null;
        int i2 = 3;
        int i3 = 0;
        while (i2 < bArr2.length) {
            if (bArr2[i2] == -102) {
                i2 += 2;
                StringBuilder sb = new StringBuilder();
                sb.append(UserConfig.g);
                int i4 = i3 + 1;
                sb.append(Coder.bytesToHexString(bArr[i3]));
                int i5 = i4 + 1;
                sb.append(Coder.bytesToHexString(bArr[i4]));
                i3 = i5 + 1;
                sb.append(Coder.bytesToHexString(bArr[i5]));
                tradeLog.setTradeDate(sb.toString());
            } else {
                if (bArr2[i2] == -97) {
                    int i6 = i2 + 1;
                    if (bArr2[i6] == 33) {
                        i2 += 3;
                        StringBuilder sb2 = new StringBuilder();
                        int i7 = i3 + 1;
                        sb2.append(Coder.bytesToHexString(bArr[i3]));
                        int i8 = i7 + 1;
                        sb2.append(Coder.bytesToHexString(bArr[i7]));
                        i3 = i8 + 1;
                        sb2.append(Coder.bytesToHexString(bArr[i8]));
                        tradeLog.setTradeTime(sb2.toString());
                    } else if (bArr2[i6] == 2) {
                        i2 += 3;
                        tradeLog.setAuAmount(getRealMoney(bArr, i3, 5, i3 + 5, 1));
                        i3 += 6;
                    } else if (bArr2[i6] == 3) {
                        i2 += 3;
                        i3 += 6;
                    } else if (bArr2[i6] == 26) {
                        i2 += 3;
                        tradeLog.setCountryCode(" ");
                        i3 += 2;
                    } else if (bArr2[i6] == 78) {
                        i2 += 3;
                        if (!ByteArray.wrap(bArr).duplicate(i3, 20).toString().matches("0*")) {
                            try {
                                str = new String(ByteArray.wrap(bArr).duplicate(i3, 20).toBytes(), Encoding.GBK);
                            } catch (UnsupportedEncodingException e) {
                                Log.e(TAG, "failed to get shop name", e);
                                str = str2;
                            }
                            tradeLog.setBusinessName(str);
                            str2 = str;
                        } else {
                            tradeLog.setBusinessName((String) null);
                        }
                        i3 += 20;
                    } else if (bArr2[i6] == 54) {
                        i2 += 3;
                        i3 += 2;
                    } else if (bArr2[i6] == 39) {
                        i2 += 3;
                        i3++;
                    }
                }
                if (bArr2[i2] == 95 && bArr2[i2 + 1] == 42) {
                    i2 += 3;
                    ByteArray duplicate = ByteArray.wrap(bArr).duplicate(i3, 2);
                    if (CardConstants.sCurrencyCodeMap.containsKey(duplicate.toString())) {
                        tradeLog.setCurCode(CardConstants.sCurrencyCodeMap.get(duplicate.toString()).intValue());
                    }
                    i3 += 2;
                } else if (bArr2[i2] != -100) {
                    return tradeLog;
                } else {
                    i2 += 2;
                    byte b = bArr[i3];
                    if (b != 33) {
                        if (b != 48) {
                            if (b == 96) {
                                i = 5;
                            } else if (b != 99) {
                                switch (b) {
                                    case 0:
                                        i = 1;
                                        break;
                                    case 1:
                                        i = 6;
                                        break;
                                }
                            } else {
                                i = 2;
                            }
                        }
                        i = 4;
                    } else {
                        i = 3;
                    }
                    tradeLog.setTradeType(i);
                    i3++;
                }
            }
        }
        return tradeLog;
    }

    private byte[] getTradeLogFormat(IsoDep isoDep) throws IOException {
        byte[] bArr;
        GetDataCommand getDataCommand = new GetDataCommand();
        getDataCommand.setP1((byte) -97);
        getDataCommand.setP2(Constants.TagName.CP_NO);
        try {
            bArr = isoDep.transceive(getDataCommand.toRawAPDU().toBytes());
        } catch (IOException e) {
            Log.e(TAG, "failed to get trade log format", e);
            bArr = null;
        }
        if (bArr != null) {
            return bArr;
        }
        throw new IOException("failed to get TradeLogFormat");
    }

    private byte[] getEntranceOfTrade(byte[] bArr) {
        int i = 0;
        while (true) {
            if (i < bArr.length - 1) {
                if (bArr[i] == -97 && bArr[i + 1] == 77) {
                    i += 3;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        if (i == bArr.length - 1) {
            return new byte[]{11, 10};
        }
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.put(bArr[i]).put(bArr[i + 1]);
        return allocate.array();
    }

    private float getEBanlanceLimit(IsoDep isoDep) throws IOException {
        byte[] bArr;
        GetDataCommand getDataCommand = new GetDataCommand();
        getDataCommand.setP1((byte) -97);
        getDataCommand.setP2((byte) 119);
        try {
            bArr = isoDep.transceive(getDataCommand.toRawAPDU().toBytes());
        } catch (IOException e) {
            Log.e(TAG, "failed to get balance limit", e);
            bArr = null;
        }
        byte[] bArr2 = bArr;
        if (bArr2 != null) {
            return getRealMoney(bArr2, 3, 5, 8, 1);
        }
        throw new IOException("failed to get EBanlanceLimit");
    }

    private float getEPerLimit(IsoDep isoDep) throws IOException {
        byte[] bArr;
        GetDataCommand getDataCommand = new GetDataCommand();
        getDataCommand.setP1((byte) -97);
        getDataCommand.setP2((byte) 120);
        try {
            bArr = isoDep.transceive(getDataCommand.toRawAPDU().toBytes());
        } catch (IOException e) {
            Log.e(TAG, "failed to get per limit", e);
            bArr = null;
        }
        byte[] bArr2 = bArr;
        if (bArr2 != null) {
            return getRealMoney(bArr2, 3, 5, 8, 1);
        }
        throw new IOException("failed to get EPerLimit");
    }

    private float getEBalance(IsoDep isoDep) throws IOException {
        byte[] bArr;
        GetDataCommand getDataCommand = new GetDataCommand();
        getDataCommand.setP1((byte) -97);
        getDataCommand.setP2(Constants.TagName.ELECTRONIC_STATE);
        try {
            bArr = isoDep.transceive(getDataCommand.toRawAPDU().toBytes());
        } catch (IOException e) {
            Log.e(TAG, "failed to get balance", e);
            bArr = null;
        }
        byte[] bArr2 = bArr;
        if (bArr2 != null) {
            return getRealMoney(bArr2, 3, 5, 8, 1);
        }
        throw new IOException("failed to get balance");
    }

    private int getATC(IsoDep isoDep) throws IOException {
        byte[] bArr;
        GetDataCommand getDataCommand = new GetDataCommand();
        getDataCommand.setP1((byte) -97);
        getDataCommand.setP2((byte) 54);
        try {
            bArr = isoDep.transceive(getDataCommand.toRawAPDU().toBytes());
        } catch (IOException e) {
            Log.e(TAG, "failed to get atc", e);
            bArr = null;
        }
        if (bArr != null) {
            return Coder.bytesToInt(bArr, 3, 2);
        }
        throw new IOException("failed to get ATC");
    }

    private String getCardNumString(byte[] bArr) {
        String str;
        Boolean bool = false;
        String str2 = null;
        try {
            ByteArray bytes = TLVParser.parse(ByteArray.wrap(bArr, 0, bArr.length - 2)).getValue().findTLV(TAG_CARD_NUM).getValue().toBytes();
            int i = 0;
            while (true) {
                if (((bytes.get(i) & 240) ^ 208) == 0) {
                    break;
                }
                i++;
                if (((bytes.get(i) & 15) ^ 13) == 0) {
                    i++;
                    bool = true;
                    break;
                }
            }
            String byteArray = ByteArray.wrap(bytes.toBytes(), 0, i).toString();
            try {
                return bool.booleanValue() ? byteArray.substring(0, byteArray.length() - 1) : byteArray;
            } catch (InvalidTLVException e) {
                e = e;
                str2 = byteArray;
                Log.e(TAG, "Invalid res: " + Coder.bytesToHexString(bArr), e);
                return str2;
            } catch (TagNotFoundException e2) {
                e = e2;
                str = byteArray;
                Log.e(TAG, "Error when parse tlv", e);
                return str;
            }
        } catch (InvalidTLVException e3) {
            e = e3;
            Log.e(TAG, "Invalid res: " + Coder.bytesToHexString(bArr), e);
            return str2;
        } catch (TagNotFoundException e4) {
            e = e4;
            str = null;
            Log.e(TAG, "Error when parse tlv", e);
            return str;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.tsmclient.smartcard.ByteArray getGPOInputData(com.tsmclient.smartcard.ByteArray r17) {
        /*
            r16 = this;
            r0 = r17
            r1 = 0
            r2 = 0
            r3 = 0
        L_0x0005:
            int r4 = r17.length()
            r5 = 156(0x9c, float:2.19E-43)
            r6 = 154(0x9a, float:2.16E-43)
            r7 = 149(0x95, float:2.09E-43)
            r8 = 102(0x66, float:1.43E-43)
            r9 = 55
            r10 = 42
            r11 = 33
            r12 = 26
            r13 = 31
            if (r2 >= r4) goto L_0x0079
            byte r4 = r0.get(r2)
            r4 = r4 & r13
            if (r4 != r13) goto L_0x005d
            int r4 = r2 + 1
            byte r4 = r0.get(r4)
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r4 == r12) goto L_0x0058
            if (r4 == r11) goto L_0x0053
            if (r4 == r10) goto L_0x004e
            if (r4 == r9) goto L_0x0049
            if (r4 == r8) goto L_0x0044
            switch(r4) {
                case 2: goto L_0x003f;
                case 3: goto L_0x003a;
                default: goto L_0x0039;
            }
        L_0x0039:
            goto L_0x005d
        L_0x003a:
            int r3 = r3 + 6
            int r2 = r2 + 3
            goto L_0x0005
        L_0x003f:
            int r3 = r3 + 6
            int r2 = r2 + 3
            goto L_0x0005
        L_0x0044:
            int r3 = r3 + 4
            int r2 = r2 + 3
            goto L_0x0005
        L_0x0049:
            int r3 = r3 + 4
            int r2 = r2 + 3
            goto L_0x0005
        L_0x004e:
            int r3 = r3 + 2
            int r2 = r2 + 3
            goto L_0x0005
        L_0x0053:
            int r3 = r3 + 3
            int r2 = r2 + 3
            goto L_0x0005
        L_0x0058:
            int r3 = r3 + 2
            int r2 = r2 + 3
            goto L_0x0005
        L_0x005d:
            byte r4 = r0.get(r2)
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r4 == r7) goto L_0x0074
            if (r4 == r6) goto L_0x006f
            if (r4 == r5) goto L_0x006a
            goto L_0x0079
        L_0x006a:
            int r3 = r3 + 1
            int r2 = r2 + 2
            goto L_0x0005
        L_0x006f:
            int r3 = r3 + 3
            int r2 = r2 + 2
            goto L_0x0005
        L_0x0074:
            int r3 = r3 + 5
            int r2 = r2 + 2
            goto L_0x0005
        L_0x0079:
            int r2 = r3 + 2
            java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r2)
            r4 = -125(0xffffffffffffff83, float:NaN)
            java.nio.ByteBuffer r4 = r2.put(r4)
            byte r3 = com.tsmclient.smartcard.Coder.toBytesLow(r3)
            r4.put(r3)
        L_0x008c:
            int r3 = r17.length()
            if (r1 >= r3) goto L_0x015c
            byte r3 = r0.get(r1)
            r3 = r3 & r13
            if (r3 != r13) goto L_0x0116
            int r3 = r1 + 1
            byte r3 = r0.get(r3)
            r3 = r3 & 255(0xff, float:3.57E-43)
            if (r3 == r12) goto L_0x0109
            if (r3 == r11) goto L_0x00eb
            if (r3 == r10) goto L_0x00df
            if (r3 == r9) goto L_0x00d3
            if (r3 == r8) goto L_0x00c7
            switch(r3) {
                case 2: goto L_0x00bb;
                case 3: goto L_0x00af;
                default: goto L_0x00ae;
            }
        L_0x00ae:
            goto L_0x0116
        L_0x00af:
            com.tsmclient.smartcard.ByteArray r3 = AU_AMOUNT_OTHER
            byte[] r3 = r3.toBytes()
            r2.put(r3)
            int r1 = r1 + 3
            goto L_0x008c
        L_0x00bb:
            com.tsmclient.smartcard.ByteArray r3 = AU_AMOUNT
            byte[] r3 = r3.toBytes()
            r2.put(r3)
            int r1 = r1 + 3
            goto L_0x008c
        L_0x00c7:
            com.tsmclient.smartcard.ByteArray r3 = TER_TRADE_TYPE
            byte[] r3 = r3.toBytes()
            r2.put(r3)
            int r1 = r1 + 3
            goto L_0x008c
        L_0x00d3:
            com.tsmclient.smartcard.ByteArray r3 = RANDOM_NUMBER
            byte[] r3 = r3.toBytes()
            r2.put(r3)
            int r1 = r1 + 3
            goto L_0x008c
        L_0x00df:
            com.tsmclient.smartcard.ByteArray r3 = CUR_CODE
            byte[] r3 = r3.toBytes()
            r2.put(r3)
            int r1 = r1 + 3
            goto L_0x008c
        L_0x00eb:
            java.util.Date r3 = new java.util.Date
            r3.<init>()
            java.text.SimpleDateFormat r4 = new java.text.SimpleDateFormat
            java.lang.String r14 = "HHmmss"
            java.util.Locale r15 = java.util.Locale.getDefault()
            r4.<init>(r14, r15)
            java.lang.String r3 = r4.format(r3)
            byte[] r3 = com.tsmclient.smartcard.Coder.str2Bcd(r3)
            r2.put(r3)
            int r1 = r1 + 3
            goto L_0x008c
        L_0x0109:
            com.tsmclient.smartcard.ByteArray r3 = CUR_CODE
            byte[] r3 = r3.toBytes()
            r2.put(r3)
            int r1 = r1 + 3
            goto L_0x008c
        L_0x0116:
            byte r3 = r0.get(r1)
            r3 = r3 & 255(0xff, float:3.57E-43)
            if (r3 == r7) goto L_0x014f
            if (r3 == r6) goto L_0x0130
            if (r3 == r5) goto L_0x0123
            goto L_0x015c
        L_0x0123:
            com.tsmclient.smartcard.ByteArray r3 = TRADE_TYPE
            byte[] r3 = r3.toBytes()
            r2.put(r3)
            int r1 = r1 + 2
            goto L_0x008c
        L_0x0130:
            java.util.Date r3 = new java.util.Date
            r3.<init>()
            java.text.SimpleDateFormat r4 = new java.text.SimpleDateFormat
            java.lang.String r14 = "yyMMdd"
            java.util.Locale r15 = java.util.Locale.getDefault()
            r4.<init>(r14, r15)
            java.lang.String r3 = r4.format(r3)
            byte[] r3 = com.tsmclient.smartcard.Coder.str2Bcd(r3)
            r2.put(r3)
            int r1 = r1 + 2
            goto L_0x008c
        L_0x014f:
            com.tsmclient.smartcard.ByteArray r3 = TVR
            byte[] r3 = r3.toBytes()
            r2.put(r3)
            int r1 = r1 + 2
            goto L_0x008c
        L_0x015c:
            byte[] r0 = r2.array()
            com.tsmclient.smartcard.ByteArray r0 = com.tsmclient.smartcard.ByteArray.wrap((byte[]) r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tsmclient.smartcard.handler.BankCardHandler.getGPOInputData(com.tsmclient.smartcard.ByteArray):com.tsmclient.smartcard.ByteArray");
    }

    private float getRealMoney(byte[] bArr, int i, int i2, int i3, int i4) {
        return ((float) Integer.parseInt(Coder.bytesToHexString(ByteArray.wrap(bArr).duplicate(i, i2).toBytes()))) + (Float.parseFloat(Coder.bytesToHexString(ByteArray.wrap(bArr).duplicate(i3, i4).toBytes())) / 100.0f);
    }
}
