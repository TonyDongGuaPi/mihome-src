package com.tsmclient.smartcard.handler;

import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.text.TextUtils;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.apdu.ReadRecordCommand;
import com.tsmclient.smartcard.exception.CardStatusException;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.model.TradeLog;
import com.tsmclient.smartcard.terminal.IScTerminal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTransCardHandler implements ISmartCardHandler<IsoDep> {
    private Map<String, String> mCardInfoMap = new HashMap();
    protected boolean mInternalRead;
    protected IsoDep mTech;
    protected IScTerminal mTerminal;

    /* access modifiers changed from: protected */
    public int getAtc() throws IOException, UnProcessableCardException {
        return -999;
    }

    /* access modifiers changed from: protected */
    public abstract Map<String, String> getCardNumAndValidDate() throws IOException, UnProcessableCardException;

    /* access modifiers changed from: protected */
    public abstract String getCardType();

    public int getTechType() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public void otherVerify() throws IOException, UnProcessableCardException {
    }

    /* access modifiers changed from: protected */
    public void readCardStatus(Map<String, String> map) throws IOException, UnProcessableCardException, CardStatusException {
    }

    /* access modifiers changed from: protected */
    public abstract void selectVerify() throws IOException, UnProcessableCardException;

    public Bundle onHandleCard(IsoDep isoDep) throws IOException, UnProcessableCardException {
        this.mTech = isoDep;
        this.mInternalRead = false;
        return doHandleCard(new Bundle());
    }

    public Bundle onHandleCard(IScTerminal iScTerminal, Bundle bundle) throws IOException, UnProcessableCardException {
        this.mTerminal = iScTerminal;
        this.mInternalRead = true;
        if (bundle == null) {
            bundle = new Bundle();
        }
        return doHandleCard(bundle);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0172  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0193  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Bundle doHandleCard(android.os.Bundle r8) throws java.io.IOException, com.tsmclient.smartcard.exception.UnProcessableCardException {
        /*
            r7 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            r7.selectVerify()
            r2 = -999(0xfffffffffffffc19, float:NaN)
            java.util.Map<java.lang.String, java.lang.String> r3 = r7.mCardInfoMap     // Catch:{ CardStatusException -> 0x0035 }
            r7.readCardStatus(r3)     // Catch:{ CardStatusException -> 0x0035 }
            java.util.Map r3 = r7.getCardNumAndValidDate()     // Catch:{ CardStatusException -> 0x0035 }
            java.util.Map<java.lang.String, java.lang.String> r4 = r7.mCardInfoMap     // Catch:{ CardStatusException -> 0x0035 }
            r4.putAll(r3)     // Catch:{ CardStatusException -> 0x0035 }
            int r3 = r7.getAtc()     // Catch:{ CardStatusException -> 0x0035 }
            r7.otherVerify()     // Catch:{ CardStatusException -> 0x0037 }
            int r4 = r7.getBalance()     // Catch:{ CardStatusException -> 0x0037 }
            java.lang.String r5 = "KEY_READ_CARD_OPTION_SKIP_RECORD"
            r6 = 0
            boolean r8 = r8.getBoolean(r5, r6)     // Catch:{ CardStatusException -> 0x0039 }
            if (r8 != 0) goto L_0x0039
            r7.readRecord(r0, r6)     // Catch:{ CardStatusException -> 0x0039 }
            goto L_0x0039
        L_0x0035:
            r3 = -999(0xfffffffffffffc19, float:NaN)
        L_0x0037:
            r4 = -999(0xfffffffffffffc19, float:NaN)
        L_0x0039:
            java.lang.String r8 = "success"
            r5 = 1
            r1.putBoolean(r8, r5)
            java.lang.String r8 = "card_type"
            r5 = 2
            r1.putInt(r8, r5)
            java.lang.String r8 = "card_id"
            java.lang.String r5 = r7.getCardType()
            r1.putString(r8, r5)
            java.util.Map<java.lang.String, java.lang.String> r8 = r7.mCardInfoMap
            java.lang.String r5 = "account_num"
            java.lang.Object r8 = r8.get(r5)
            if (r8 == 0) goto L_0x0067
            java.lang.String r8 = "account_num"
            java.util.Map<java.lang.String, java.lang.String> r5 = r7.mCardInfoMap
            java.lang.String r6 = "account_num"
            java.lang.Object r5 = r5.get(r6)
            java.lang.String r5 = (java.lang.String) r5
            r1.putString(r8, r5)
        L_0x0067:
            java.util.Map<java.lang.String, java.lang.String> r8 = r7.mCardInfoMap
            java.lang.String r5 = "account_real_num"
            java.lang.Object r8 = r8.get(r5)
            if (r8 == 0) goto L_0x0080
            java.lang.String r8 = "account_real_num"
            java.util.Map<java.lang.String, java.lang.String> r5 = r7.mCardInfoMap
            java.lang.String r6 = "account_real_num"
            java.lang.Object r5 = r5.get(r6)
            java.lang.String r5 = (java.lang.String) r5
            r1.putString(r8, r5)
        L_0x0080:
            java.util.Map<java.lang.String, java.lang.String> r8 = r7.mCardInfoMap
            java.lang.String r5 = "valid_start"
            java.lang.Object r8 = r8.get(r5)
            if (r8 == 0) goto L_0x0099
            java.lang.String r8 = "valid_start"
            java.util.Map<java.lang.String, java.lang.String> r5 = r7.mCardInfoMap
            java.lang.String r6 = "valid_start"
            java.lang.Object r5 = r5.get(r6)
            java.lang.String r5 = (java.lang.String) r5
            r1.putString(r8, r5)
        L_0x0099:
            java.util.Map<java.lang.String, java.lang.String> r8 = r7.mCardInfoMap
            java.lang.String r5 = "valid_end"
            java.lang.Object r8 = r8.get(r5)
            if (r8 == 0) goto L_0x00b2
            java.lang.String r8 = "valid_end"
            java.util.Map<java.lang.String, java.lang.String> r5 = r7.mCardInfoMap
            java.lang.String r6 = "valid_end"
            java.lang.Object r5 = r5.get(r6)
            java.lang.String r5 = (java.lang.String) r5
            r1.putString(r8, r5)
        L_0x00b2:
            if (r3 == r2) goto L_0x00b9
            java.lang.String r8 = "atc"
            r1.putInt(r8, r3)
        L_0x00b9:
            java.lang.String r8 = "e_balance"
            r1.putInt(r8, r4)
            java.lang.String r8 = "trade_log"
            r1.putParcelableArrayList(r8, r0)
            java.util.Map<java.lang.String, java.lang.String> r8 = r7.mCardInfoMap
            java.lang.String r0 = "status_negative"
            boolean r8 = r8.containsKey(r0)
            if (r8 == 0) goto L_0x00e4
            java.lang.String r8 = "status_negative"
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.mCardInfoMap
            java.lang.String r2 = "status_negative"
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            int r0 = r0.intValue()
            r1.putInt(r8, r0)
        L_0x00e4:
            java.util.Map<java.lang.String, java.lang.String> r8 = r7.mCardInfoMap
            java.lang.String r0 = "in_black_list"
            boolean r8 = r8.containsKey(r0)
            if (r8 == 0) goto L_0x0105
            java.lang.String r8 = "in_black_list"
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.mCardInfoMap
            java.lang.String r2 = "in_black_list"
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            int r0 = r0.intValue()
            r1.putInt(r8, r0)
        L_0x0105:
            java.util.Map<java.lang.String, java.lang.String> r8 = r7.mCardInfoMap
            java.lang.String r0 = "card_locked"
            boolean r8 = r8.containsKey(r0)
            if (r8 == 0) goto L_0x0126
            java.lang.String r8 = "card_locked"
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.mCardInfoMap
            java.lang.String r2 = "card_locked"
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            int r0 = r0.intValue()
            r1.putInt(r8, r0)
        L_0x0126:
            java.util.Map<java.lang.String, java.lang.String> r8 = r7.mCardInfoMap
            java.lang.String r0 = "card_exception"
            boolean r8 = r8.containsKey(r0)
            if (r8 == 0) goto L_0x0147
            java.lang.String r8 = "card_exception"
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.mCardInfoMap
            java.lang.String r2 = "card_exception"
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            int r0 = r0.intValue()
            r1.putInt(r8, r0)
        L_0x0147:
            java.util.Map<java.lang.String, java.lang.String> r8 = r7.mCardInfoMap
            java.lang.String r0 = "overdrawn"
            boolean r8 = r8.containsKey(r0)
            if (r8 == 0) goto L_0x0168
            java.lang.String r8 = "overdrawn"
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.mCardInfoMap
            java.lang.String r2 = "overdrawn"
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            int r0 = r0.intValue()
            r1.putInt(r8, r0)
        L_0x0168:
            java.util.Map<java.lang.String, java.lang.String> r8 = r7.mCardInfoMap
            java.lang.String r0 = "is_valid_start_date"
            boolean r8 = r8.containsKey(r0)
            if (r8 == 0) goto L_0x0189
            java.lang.String r8 = "is_valid_start_date"
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.mCardInfoMap
            java.lang.String r2 = "is_valid_start_date"
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            boolean r0 = r0.booleanValue()
            r1.putBoolean(r8, r0)
        L_0x0189:
            java.util.Map<java.lang.String, java.lang.String> r8 = r7.mCardInfoMap
            java.lang.String r0 = "is_valid_end_date"
            boolean r8 = r8.containsKey(r0)
            if (r8 == 0) goto L_0x01aa
            java.lang.String r8 = "is_valid_end_date"
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.mCardInfoMap
            java.lang.String r2 = "is_valid_end_date"
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            boolean r0 = r0.booleanValue()
            r1.putBoolean(r8, r0)
        L_0x01aa:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tsmclient.smartcard.handler.BaseTransCardHandler.doHandleCard(android.os.Bundle):android.os.Bundle");
    }

    /* access modifiers changed from: protected */
    public int getBalance() throws IOException, UnProcessableCardException {
        byte[] transceive = transceive(GET_BALANCE_CMD.toBytes());
        if (transceive == null || transceive.length < 2) {
            throw new IOException("failed to get balance");
        } else if (!ByteArray.equals(STATUS_OK, ByteArray.wrap(transceive, transceive.length - 2, 2))) {
            return -999;
        } else {
            return Coder.bytesToInt(transceive, 0, 4);
        }
    }

    /* access modifiers changed from: protected */
    public void readRecord(ArrayList<TradeLog> arrayList, boolean z, byte b, byte b2, boolean z2) throws IOException {
        ReadRecordCommand readRecordCommand = new ReadRecordCommand();
        int i = 1;
        readRecordCommand.setP1((byte) 1);
        readRecordCommand.setP2(Constants.TagName.USER_PLATFORM_ID);
        byte[] transceive = transceive(readRecordCommand.toRawAPDU().toBytes());
        if (transceive == null) {
            return;
        }
        if (transceive.length < 2) {
            throw new IOException("failed to get record");
        } else if (ByteArray.equals(STATUS_ERROR_PARAM, ByteArray.wrap(transceive, transceive.length - 2, 2)) || z) {
            readRecordCommand.setP2(b);
            readRecordCommand.setSfi(b2);
            readRecordCommand.setP1(Coder.toBytesLow(1));
            byte[] transceive2 = transceive(readRecordCommand.toRawAPDU().toBytes());
            if (transceive2 != null && ByteArray.wrap(transceive2).length() != 2) {
                while (!ByteArray.equals(STATUS_RECORD_END, ByteArray.wrap(transceive2)) && i < 11 && !ByteArray.equals(EMPTY_RECORD, ByteArray.wrap(transceive2, 0, transceive2.length - 2)) && !ByteArray.equals(EMPTY_RECORD_TWO, ByteArray.wrap(transceive2, 0, transceive2.length - 2))) {
                    processPerLog(transceive2, arrayList, z2);
                    i++;
                    readRecordCommand.setP1(Coder.toBytesLow(i));
                    transceive2 = transceive(readRecordCommand.toRawAPDU().toBytes());
                    if (transceive2 == null) {
                        return;
                    }
                }
            }
        } else {
            processAllLog(ByteArray.wrap(transceive, 0, transceive.length - 2), arrayList, z2);
        }
    }

    /* access modifiers changed from: protected */
    public void readRecord(ArrayList<TradeLog> arrayList, boolean z) throws IOException {
        readRecord(arrayList, z, Constants.TagName.USER_PLATFORM_TYPE, (byte) 0, false);
    }

    private void processAllLog(ByteArray byteArray, ArrayList<TradeLog> arrayList, boolean z) {
        int length = byteArray.length() / 23;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            byte[] bytes = byteArray.duplicate(i2, 23).toBytes();
            if (!ByteArray.equals(ByteArray.wrap(bytes), EMPTY_RECORD)) {
                processPerLog(bytes, arrayList, z);
                i2 += 23;
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void processPerLog(byte[] bArr, ArrayList<TradeLog> arrayList, boolean z) {
        int bytesToInt = Coder.bytesToInt(bArr, 5, 4);
        if (bytesToInt != 0 && bytesToInt != Integer.MIN_VALUE) {
            TradeLog tradeLog = new TradeLog();
            int i = 0;
            int bytesToInt2 = Coder.bytesToInt(bArr, 0, 2);
            if (bytesToInt2 > 0) {
                tradeLog.setTradeID(bytesToInt2);
            }
            if (getConsumeTag().contains(bArr[9])) {
                tradeLog.setTradeType(1);
            } else {
                tradeLog.setTradeType(2);
            }
            tradeLog.setAuAmount((float) bytesToInt);
            String bytesToHexString = Coder.bytesToHexString(ByteArray.wrap(bArr, 10, 6).toBytes());
            if (!TextUtils.isEmpty(bytesToHexString)) {
                tradeLog.setTerminalNo(bytesToHexString);
            }
            if (z) {
                i = 2;
            }
            tradeLog.setTradeDate(Coder.bytesToHexString(ByteArray.wrap(bArr, i + 16, 4 - i).toBytes()));
            tradeLog.setTradeTime(Coder.bytesToHexString(ByteArray.wrap(bArr, 20, 3).toBytes()));
            arrayList.add(tradeLog);
        }
    }

    /* access modifiers changed from: protected */
    public ByteArray getConsumeTag() {
        return ByteArray.wrap(new byte[]{10, 17, 9, 6, 5});
    }

    /* access modifiers changed from: protected */
    public byte[] transceive(byte[] bArr) throws IOException {
        if (!this.mInternalRead) {
            return this.mTech.transceive(bArr);
        }
        try {
            return this.mTerminal.transmit(bArr).toBytes();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new IOException("transceive is interrupted");
        }
    }

    /* access modifiers changed from: protected */
    public void assertResponse(byte[] bArr, ByteArray byteArray, String str) throws IOException, UnProcessableCardException {
        if (bArr == null || bArr.length < 2) {
            throw new IOException(str);
        } else if (!ByteArray.equals(byteArray, ByteArray.wrap(bArr, bArr.length - 2, 2))) {
            throw new UnProcessableCardException(getClass().getSimpleName() + ": unsupported card type");
        }
    }

    /* access modifiers changed from: protected */
    public void assertResponse(byte[] bArr, String str) throws IOException, UnProcessableCardException {
        assertResponse(bArr, STATUS_OK, str);
    }

    /* access modifiers changed from: protected */
    public void updateCardInfo(String str, String str2) {
        this.mCardInfoMap.put(str, str2);
    }
}
