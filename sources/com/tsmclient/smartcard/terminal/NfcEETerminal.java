package com.tsmclient.smartcard.terminal;

import android.content.Context;
import android.util.Log;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.PrefUtils;
import com.tsmclient.smartcard.exception.InvalidTLVException;
import com.tsmclient.smartcard.exception.NfcEeIOException;
import com.tsmclient.smartcard.exception.TagNotFoundException;
import com.tsmclient.smartcard.terminal.response.ScResponse;
import com.tsmclient.smartcard.tlv.ITLVObject;
import com.tsmclient.smartcard.tlv.TLVParser;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class NfcEETerminal extends BaseTerminal {
    protected static final String SE_RESTRICTED = "SERESTRICTED";
    protected static final String TAG = "NfcEETerminal";
    protected static String sCIN;
    protected static String sCPLC;
    protected static String sSeid;
    protected Context mContext;
    protected boolean mNfcChannelOpen;

    /* access modifiers changed from: protected */
    public abstract void internalClose();

    /* access modifiers changed from: protected */
    public abstract void internalConnect() throws IOException, InterruptedException;

    /* access modifiers changed from: protected */
    public abstract byte[] internalTransmit(byte[] bArr) throws IOException, InterruptedException;

    public NfcEETerminal(Context context) {
        this.mContext = context.getApplicationContext();
        this.mTerminalCategory = "com.miui.tsmclient";
    }

    public void open() throws IOException, InterruptedException {
        acquireLock();
        Log.d(TAG, "try to connect terminal");
        internalConnect();
        this.mNfcChannelOpen = true;
        Log.d(TAG, "terminal opened");
    }

    public synchronized ScResponse transmit(byte[] bArr) throws IOException, InterruptedException {
        ByteArray wrap;
        Log.d(TAG, "send: " + Coder.bytesToHexString(bArr));
        byte[] internalTransmit = internalTransmit(bArr);
        Log.d(TAG, "resp: " + Coder.bytesToHexString(internalTransmit));
        if (internalTransmit == null || internalTransmit.length < 2) {
            throw new IOException("response too small");
        }
        byte b = internalTransmit[internalTransmit.length - 2] & 255;
        if (b == 108) {
            bArr[bArr.length - 1] = internalTransmit[internalTransmit.length - 1];
            internalTransmit = internalTransmit(bArr);
        } else if (b == 97) {
            byte[] bArr2 = {bArr[0], Constants.TagName.STATION_ENAME, 0, 0, 0};
            byte[] bArr3 = new byte[(internalTransmit.length - 2)];
            System.arraycopy(internalTransmit, 0, bArr3, 0, internalTransmit.length - 2);
            while (true) {
                bArr2[4] = internalTransmit[internalTransmit.length - 1];
                internalTransmit = internalTransmit(bArr2);
                if (internalTransmit.length < 2 || internalTransmit[internalTransmit.length - 2] != 97) {
                    internalTransmit = appendResponse(bArr3, internalTransmit, internalTransmit.length);
                } else {
                    bArr3 = appendResponse(bArr3, internalTransmit, internalTransmit.length - 2);
                }
            }
            internalTransmit = appendResponse(bArr3, internalTransmit, internalTransmit.length);
        }
        wrap = ByteArray.wrap(internalTransmit);
        return new ScResponseImpl(wrap.duplicate(0, wrap.length() - 2), wrap.duplicate(wrap.length() - 2, 2));
    }

    public synchronized void close() {
        String str;
        String str2;
        try {
            internalClose();
            this.mNfcChannelOpen = false;
            releaseLock();
            str = TAG;
            str2 = "terminal closed";
        } catch (Throwable th) {
            try {
                Log.e(TAG, "closing terminal failed", th);
                releaseLock();
                str = TAG;
                str2 = "terminal closed";
            } catch (Throwable th2) {
                releaseLock();
                Log.d(TAG, "terminal closed");
                throw th2;
            }
        }
        Log.d(str, str2);
        return;
    }

    public String getSeid() throws IOException, InterruptedException {
        Context context = this.mContext;
        sSeid = PrefUtils.getString(context, PrefUtils.PREF_KEY_SEID + getTerminalCategory(), (String) null);
        if (sSeid == null) {
            getSEInfo();
        }
        return sSeid;
    }

    public String getCPLC() throws IOException, InterruptedException {
        Context context = this.mContext;
        sCPLC = PrefUtils.getString(context, PrefUtils.PREF_KEY_CPLC + getTerminalCategory(), (String) null);
        if (sCPLC == null) {
            getSEInfo();
        }
        return sCPLC;
    }

    public String getCIN() throws IOException, InterruptedException {
        Context context = this.mContext;
        sCIN = PrefUtils.getString(context, PrefUtils.PREF_KEY_CIN + getTerminalCategory(), (String) null);
        if (sCIN == null) {
            getSEInfo();
        }
        return sCIN;
    }

    public void getSEInfo() throws IOException, InterruptedException {
        try {
            open();
            readSEInfo();
        } finally {
            close();
        }
    }

    public boolean isAppletExist(String str) throws IOException, InterruptedException {
        CommandApdu commandApdu = new CommandApdu(0, 164, 4, 0);
        try {
            open();
            commandApdu.setData(Coder.hexStringToBytes(str));
            return ByteArray.equals(transmit(commandApdu.toBytes()).getStatus(), ScResponse.STATUS_OK);
        } finally {
            close();
        }
    }

    public Map<String, ByteArray> getCardActivationState(String str) throws InterruptedException {
        HashMap hashMap = new HashMap();
        try {
            open();
            ScResponse transmit = transmit(APDUConstants.SELECT_CRS);
            if (!ByteArray.equals(transmit.getStatus(), ScResponse.STATUS_OK)) {
                Log.e(TAG, "failed to select CRS, status: " + Coder.bytesToHexString(transmit.getStatus().toBytes()));
                close();
                return null;
            }
            byte[] hexStringToBytes = Coder.hexStringToBytes(str);
            byte[] bArr = new byte[(hexStringToBytes.length + 2 + 5)];
            bArr[0] = Constants.TagName.CP_NO;
            bArr[1] = (byte) (hexStringToBytes.length & 255);
            System.arraycopy(hexStringToBytes, 0, bArr, 2, hexStringToBytes.length);
            System.arraycopy(APDUConstants.COMM_TAG_GET_STATUS.toBytes(), 0, bArr, hexStringToBytes.length + 2, APDUConstants.COMM_TAG_GET_STATUS.toBytes().length);
            CommandApdu clone = APDUConstants.COMM_PREFIX_GET_STATUS.clone();
            clone.setData(bArr);
            ScResponse transmit2 = transmit(clone.toBytes());
            if (ByteArray.equals(transmit2.getStatus(), ScResponse.STATUS_REFERENCE_NOT_FOUND)) {
                close();
                return hashMap;
            }
            while (true) {
                if (!ByteArray.equals(transmit2.getStatus(), ScResponse.STATUS_MORE_DATA_AVAILABLE) && !ByteArray.equals(transmit2.getStatus(), ScResponse.STATUS_OK)) {
                    break;
                }
                if (clone.getP2() != 1) {
                    clone.setP2(1);
                }
                for (ITLVObject next : TLVParser.parseTLVValue(transmit2.getData()).findTLVList(APDUConstants.TAG_AEF_ENTRANCE)) {
                    hashMap.put(Coder.bytesToHexString(next.getValue().findTLV(APDUConstants.TAG_AID).getValue().toBytes().toBytes()), next.getValue().findTLV(APDUConstants.TAG_LIFESTYLE_STATE).getValue().toBytes());
                }
                if (ByteArray.equals(transmit2.getStatus(), ScResponse.STATUS_OK)) {
                    close();
                    return hashMap;
                }
                transmit2 = transmit(clone.toBytes());
            }
            close();
            return hashMap;
        } catch (InvalidTLVException e) {
            Log.e(TAG, "getCardActivationState InvalidTLVException occurred.", e);
        } catch (TagNotFoundException e2) {
            Log.e(TAG, "getCardActivationState TagNotFoundException occurred.", e2);
        } catch (IOException e3) {
            Log.e(TAG, "getCardActivationState IOException occurred.", e3);
        } catch (Throwable th) {
            close();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0151, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        android.util.Log.e(TAG, "failed to active card", r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x015a, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x015b, code lost:
        android.util.Log.e(TAG, "failed to active card, resolveActivationConflict TagNotFoundException occurred.", r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0166, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        android.util.Log.e(TAG, "active card is interrupted. aid: " + r11, r1);
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0185, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0186, code lost:
        android.util.Log.e(TAG, "failed to active card, aid = " + r11, r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0151 A[ExcHandler: InvalidTLVException (r11v5 'e' com.tsmclient.smartcard.exception.InvalidTLVException A[CUSTOM_DECLARE]), Splitter:B:1:0x0017] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0166 A[ExcHandler: InterruptedException (r1v3 'e' java.lang.InterruptedException A[CUSTOM_DECLARE]), Splitter:B:1:0x0017] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0185 A[Catch:{ TagNotFoundException -> 0x0098, IOException -> 0x0185, InterruptedException -> 0x0166, InvalidTLVException -> 0x0151, all -> 0x014f }, ExcHandler: IOException (r1v2 'e' java.io.IOException A[CUSTOM_DECLARE, Catch:{  }]), Splitter:B:1:0x0017] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean activateCard(java.lang.String r11) {
        /*
            r10 = this;
            java.lang.String r0 = "NfcEETerminal"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "activateCard appAid:"
            r1.append(r2)
            r1.append(r11)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
            r0 = 0
            r10.open()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            byte[] r1 = com.tsmclient.smartcard.terminal.APDUConstants.SELECT_CRS     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.terminal.response.ScResponse r1 = r10.transmit(r1)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.ByteArray r1 = r1.getStatus()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.ByteArray r2 = com.tsmclient.smartcard.terminal.response.ScResponse.STATUS_OK     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            boolean r1 = com.tsmclient.smartcard.ByteArray.equals(r1, r2)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            if (r1 != 0) goto L_0x0030
            r10.close()
            return r0
        L_0x0030:
            r1 = 1
            com.tsmclient.smartcard.terminal.CommandApdu r2 = com.tsmclient.smartcard.terminal.TerminalUtils.buildSetStatusApdu(r11, r1)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            byte[] r2 = r2.toBytes()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.terminal.response.ScResponse r2 = r10.transmit(r2)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.ByteArray r3 = r2.getStatus()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.ByteArray r4 = com.tsmclient.smartcard.terminal.response.ScResponse.STATUS_OK     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            boolean r3 = com.tsmclient.smartcard.ByteArray.equals(r3, r4)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            if (r3 != 0) goto L_0x0130
            com.tsmclient.smartcard.ByteArray r3 = r2.getStatus()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.ByteArray r4 = com.tsmclient.smartcard.terminal.response.ScResponse.STATUS_OPERATION_FAILED     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            boolean r3 = com.tsmclient.smartcard.ByteArray.equals(r3, r4)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            if (r3 == 0) goto L_0x0057
            goto L_0x0130
        L_0x0057:
            com.tsmclient.smartcard.ByteArray r3 = r2.getStatus()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.ByteArray r4 = com.tsmclient.smartcard.terminal.response.ScResponse.STATUS_ACTIVATION_CONFLICT     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            boolean r3 = com.tsmclient.smartcard.ByteArray.equals(r3, r4)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            if (r3 == 0) goto L_0x0162
            com.tsmclient.smartcard.ByteArray r2 = r2.getData()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            if (r2 != 0) goto L_0x006d
            r10.close()
            return r0
        L_0x006d:
            com.tsmclient.smartcard.tlv.ITLVValue r2 = com.tsmclient.smartcard.tlv.TLVParser.parseTLVValue(r2)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.ByteArray r3 = com.tsmclient.smartcard.terminal.APDUConstants.TAG_AEF_ENTRANCE     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.tlv.ITLVObject r2 = r2.findTLV(r3)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.tlv.ITLVValue r2 = r2.getValue()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            if (r2 != 0) goto L_0x0081
            r10.close()
            return r0
        L_0x0081:
            r3 = 0
            r4 = 2
            byte[] r4 = new byte[r4]     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            r4 = {-96, -94} // fill-array     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            int r5 = r4.length     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            r6 = 0
        L_0x008a:
            if (r6 >= r5) goto L_0x00a3
            byte r7 = r4[r6]     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.ByteArray r7 = com.tsmclient.smartcard.ByteArray.wrap((byte) r7)     // Catch:{ TagNotFoundException -> 0x0098, IOException -> 0x0185, InterruptedException -> 0x0166, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.tlv.ITLVObject r7 = r2.findTLV(r7)     // Catch:{ TagNotFoundException -> 0x0098, IOException -> 0x0185, InterruptedException -> 0x0166, InvalidTLVException -> 0x0151 }
            r3 = r7
            goto L_0x00a3
        L_0x0098:
            r7 = move-exception
            java.lang.String r8 = "NfcEETerminal"
            java.lang.String r9 = "resolveActivationConflict TagNotFoundException occurred."
            android.util.Log.e(r8, r9, r7)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            int r6 = r6 + 1
            goto L_0x008a
        L_0x00a3:
            if (r3 == 0) goto L_0x012c
            com.tsmclient.smartcard.tlv.ITLVValue r2 = r3.getValue()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            if (r2 == 0) goto L_0x012c
            com.tsmclient.smartcard.tlv.ITLVValue r2 = r3.getValue()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.ByteArray r3 = com.tsmclient.smartcard.terminal.APDUConstants.TAG_AID     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.tlv.ITLVObject r2 = r2.findTLV(r3)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.tlv.ITLVValue r2 = r2.getValue()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.ByteArray r2 = r2.toBytes()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            byte[] r2 = r2.toBytes()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            java.lang.String r2 = com.tsmclient.smartcard.Coder.bytesToHexString((byte[]) r2)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.terminal.CommandApdu r3 = com.tsmclient.smartcard.terminal.TerminalUtils.buildSetStatusApdu(r2, r0)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            byte[] r3 = r3.toBytes()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.terminal.response.ScResponse r3 = r10.transmit(r3)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.ByteArray r4 = r3.getStatus()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.ByteArray r5 = com.tsmclient.smartcard.terminal.response.ScResponse.STATUS_OK     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            boolean r4 = com.tsmclient.smartcard.ByteArray.equals(r4, r5)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            if (r4 != 0) goto L_0x0110
            com.tsmclient.smartcard.ByteArray r1 = r3.getStatus()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            if (r1 != 0) goto L_0x00e6
            java.lang.String r1 = "null"
            goto L_0x00ee
        L_0x00e6:
            com.tsmclient.smartcard.ByteArray r1 = r3.getStatus()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
        L_0x00ee:
            java.lang.String r3 = "NfcEETerminal"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            r4.<init>()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            java.lang.String r5 = "resolveActivationConflict deactivate: "
            r4.append(r5)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            r4.append(r2)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            java.lang.String r2 = ", response: "
            r4.append(r2)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            r4.append(r1)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            java.lang.String r1 = r4.toString()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            android.util.Log.d(r3, r1)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            r10.close()
            return r0
        L_0x0110:
            com.tsmclient.smartcard.terminal.CommandApdu r2 = com.tsmclient.smartcard.terminal.TerminalUtils.buildSetStatusApdu(r11, r1)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            byte[] r2 = r2.toBytes()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.terminal.response.ScResponse r2 = r10.transmit(r2)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.ByteArray r2 = r2.getStatus()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            com.tsmclient.smartcard.ByteArray r3 = com.tsmclient.smartcard.terminal.response.ScResponse.STATUS_OK     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            boolean r2 = com.tsmclient.smartcard.ByteArray.equals(r2, r3)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            if (r2 == 0) goto L_0x012c
            r10.close()
            return r1
        L_0x012c:
            r10.close()
            return r0
        L_0x0130:
            java.lang.String r2 = "NfcEETerminal"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            r3.<init>()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            java.lang.String r4 = "activateCard appAid:"
            r3.append(r4)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            r3.append(r11)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            java.lang.String r4 = " success"
            r3.append(r4)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            android.util.Log.d(r2, r3)     // Catch:{ IOException -> 0x0185, InterruptedException -> 0x0166, TagNotFoundException -> 0x015a, InvalidTLVException -> 0x0151 }
            r10.close()
            return r1
        L_0x014f:
            r11 = move-exception
            goto L_0x019e
        L_0x0151:
            r11 = move-exception
            java.lang.String r1 = "NfcEETerminal"
            java.lang.String r2 = "failed to active card"
            android.util.Log.e(r1, r2, r11)     // Catch:{ all -> 0x014f }
            goto L_0x0162
        L_0x015a:
            r11 = move-exception
            java.lang.String r1 = "NfcEETerminal"
            java.lang.String r2 = "failed to active card, resolveActivationConflict TagNotFoundException occurred."
            android.util.Log.e(r1, r2, r11)     // Catch:{ all -> 0x014f }
        L_0x0162:
            r10.close()
            goto L_0x019d
        L_0x0166:
            r1 = move-exception
            java.lang.String r2 = "NfcEETerminal"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x014f }
            r3.<init>()     // Catch:{ all -> 0x014f }
            java.lang.String r4 = "active card is interrupted. aid: "
            r3.append(r4)     // Catch:{ all -> 0x014f }
            r3.append(r11)     // Catch:{ all -> 0x014f }
            java.lang.String r11 = r3.toString()     // Catch:{ all -> 0x014f }
            android.util.Log.e(r2, r11, r1)     // Catch:{ all -> 0x014f }
            java.lang.Thread r11 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x014f }
            r11.interrupt()     // Catch:{ all -> 0x014f }
            goto L_0x0162
        L_0x0185:
            r1 = move-exception
            java.lang.String r2 = "NfcEETerminal"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x014f }
            r3.<init>()     // Catch:{ all -> 0x014f }
            java.lang.String r4 = "failed to active card, aid = "
            r3.append(r4)     // Catch:{ all -> 0x014f }
            r3.append(r11)     // Catch:{ all -> 0x014f }
            java.lang.String r11 = r3.toString()     // Catch:{ all -> 0x014f }
            android.util.Log.e(r2, r11, r1)     // Catch:{ all -> 0x014f }
            goto L_0x0162
        L_0x019d:
            return r0
        L_0x019e:
            r10.close()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tsmclient.smartcard.terminal.NfcEETerminal.activateCard(java.lang.String):boolean");
    }

    public boolean deactivateCard(String str) {
        try {
            open();
            if (!ByteArray.equals(transmit(APDUConstants.SELECT_CRS).getStatus(), ScResponse.STATUS_OK)) {
                close();
                return false;
            }
            ScResponse transmit = transmit(TerminalUtils.buildSetStatusApdu(str, false).toBytes());
            if (ByteArray.equals(transmit.getStatus(), ScResponse.STATUS_OK) || ByteArray.equals(transmit.getStatus(), ScResponse.STATUS_OPERATION_FAILED)) {
                Log.d(TAG, "deactivateCard appAid:" + str + " success");
                close();
                return true;
            }
            close();
            return false;
        } catch (IOException e) {
            Log.e(TAG, "failed to deactivate card, aid = " + str, e);
        } catch (InterruptedException e2) {
            Log.e(TAG, "deactivate card is interrupted, aid: " + str, e2);
            Thread.currentThread().interrupt();
        } catch (Throwable th) {
            close();
            throw th;
        }
    }

    public boolean isNfcChannelOpen() {
        return this.mNfcChannelOpen;
    }

    /* access modifiers changed from: protected */
    public void readSEInfo() throws IOException, InterruptedException {
        if (ByteArray.equals(transmit(APDUConstants.SELECT_ISD).getStatus(), ScResponse.STATUS_OK)) {
            ScResponse transmit = transmit(APDUConstants.GET_SEID);
            if (ByteArray.equals(transmit.getStatus(), ScResponse.STATUS_OK)) {
                byte[] bytes = transmit.getData().toBytes();
                if (bytes == null || bytes.length < 45) {
                    throw new NfcEeIOException("data too small returned from se when getting cplc");
                }
                int i = bytes[2] & 255;
                byte[] bArr = new byte[i];
                System.arraycopy(bytes, 3, bArr, 0, i);
                sCPLC = Coder.bytesToHexString(bArr);
                Context context = this.mContext;
                PrefUtils.putString(context, PrefUtils.PREF_KEY_CPLC + getTerminalCategory(), sCPLC);
                sSeid = Coder.encodeMD5(bArr) + sCPLC.substring(24, 36);
                Context context2 = this.mContext;
                PrefUtils.putString(context2, PrefUtils.PREF_KEY_SEID + getTerminalCategory(), sSeid);
                byte[] bArr2 = new byte[10];
                System.arraycopy(bArr, i - 4, bArr2, 0, 4);
                System.arraycopy(bytes, 15, bArr2, 4, 6);
                sCIN = Coder.bytesToHexString(bArr2);
                Context context3 = this.mContext;
                PrefUtils.putString(context3, PrefUtils.PREF_KEY_CIN + getTerminalCategory(), sCIN);
                return;
            }
        }
        throw new NfcEeIOException("failed to read se info");
    }

    private static byte[] appendResponse(byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3 = new byte[(bArr.length + i)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, i);
        return bArr3;
    }

    static final class ScResponseImpl implements ScResponse {
        private ByteArray mData;
        private ByteArray mStatus;

        public ScResponseImpl() {
            this.mData = ByteArray.EMPTY;
            this.mStatus = STATUS_OK;
        }

        public ScResponseImpl(ByteArray byteArray, ByteArray byteArray2) {
            this.mData = byteArray;
            this.mStatus = byteArray2;
        }

        public ByteArray getData() {
            return this.mData;
        }

        public ByteArray getStatus() {
            return this.mStatus;
        }

        public String toString() {
            return Coder.bytesToHexString(getStatus().toBytes());
        }

        public byte[] toBytes() {
            return this.mData.append(this.mStatus).toBytes();
        }
    }
}
