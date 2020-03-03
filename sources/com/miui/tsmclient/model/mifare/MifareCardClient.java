package com.miui.tsmclient.model.mifare;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.MifareCardInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.mitsm.MiTSMCardClient;
import com.miui.tsmclient.model.mitsm.MiTsmErrorCode;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.seitsm.TsmRpcModels;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.ResUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MifareCardClient extends MiTSMCardClient {
    public static final String EXTRA_MIFARE_CPU_CARD_APPLY_CHANNEL = "extra_mifare_cpu_card_apply_channel";
    public static final String EXTRA_MIFARE_DOOR_CARD_ISSUER_TOKEN = "extra_mifare_door_card_issuer_token";

    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01d5, code lost:
        if (r10.getBoolean(com.miui.tsmclient.model.mitsm.MiTSMCardClient.EXTRAS_KEY_SESSION_NOT_FINISH) != false) goto L_0x01ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x016f, code lost:
        if (r10.getBoolean(com.miui.tsmclient.model.mitsm.MiTSMCardClient.EXTRAS_KEY_SESSION_NOT_FINISH) != false) goto L_0x01ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x018c, code lost:
        if (r10.getBoolean(com.miui.tsmclient.model.mitsm.MiTSMCardClient.EXTRAS_KEY_SESSION_NOT_FINISH) != false) goto L_0x01ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01a2, code lost:
        if (r10.getBoolean(com.miui.tsmclient.model.mitsm.MiTSMCardClient.EXTRAS_KEY_SESSION_NOT_FINISH) != false) goto L_0x01ab;
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x01cf  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x019c  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:103:0x01b3=Splitter:B:103:0x01b3, B:86:0x0177=Splitter:B:86:0x0177} */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:51:0x00f6=Splitter:B:51:0x00f6, B:42:0x00dc=Splitter:B:42:0x00dc} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.miui.tsmclient.model.BaseResponse issue(android.content.Context r20, com.miui.tsmclient.entity.CardInfo r21, android.os.Bundle r22) {
        /*
            r19 = this;
            r7 = r19
            r0 = r20
            r6 = r21
            r5 = r22
            com.miui.tsmclient.model.mitsm.TSMSessionManager$BusinessType r4 = com.miui.tsmclient.model.mitsm.TSMSessionManager.BusinessType.INSTALL
            java.lang.String r16 = ""
            java.lang.String r1 = ""
            java.lang.String r2 = ""
            java.lang.String r3 = ""
            r8 = r6
            com.miui.tsmclient.entity.MifareCardInfo r8 = (com.miui.tsmclient.entity.MifareCardInfo) r8
            int r17 = r7.getMifareCardType(r8)
            r9 = 0
            if (r5 == 0) goto L_0x003b
            java.lang.String r1 = "extra_mifare_tag"
            android.os.Parcelable r1 = r5.getParcelable(r1)
            com.miui.tsmclient.entity.MifareTag r1 = (com.miui.tsmclient.entity.MifareTag) r1
            java.lang.String r2 = "extra_door_card_product_id"
            java.lang.String r2 = r5.getString(r2)
            java.lang.String r3 = "extra_mifare_door_card_issuer_token"
            java.lang.String r3 = r5.getString(r3)
            java.lang.String r10 = "extra_mifare_cpu_card_apply_channel"
            java.lang.String r10 = r5.getString(r10)
            r12 = r1
            r1 = r2
            r14 = r3
            r15 = r10
            goto L_0x003e
        L_0x003b:
            r14 = r2
            r15 = r3
            r12 = r9
        L_0x003e:
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L_0x0048
            java.lang.String r1 = r8.getProductId()
        L_0x0048:
            r18 = r1
            r3 = 0
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmSessionInfo r1 = r7.getSession(r0, r6, r4)     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            java.util.concurrent.atomic.AtomicBoolean r2 = sNeedSync     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            boolean r2 = r2.get()     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            if (r2 == 0) goto L_0x005b
            com.miui.tsmclient.model.BaseResponse r9 = r7.syncEse(r0, r6, r1, r5)     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
        L_0x005b:
            java.util.concurrent.atomic.AtomicBoolean r2 = sNeedSync     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            boolean r2 = r2.get()     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            if (r2 != 0) goto L_0x0140
            com.miui.tsmclient.seitsm.SeiTsmAuthManager r8 = r7.mSeiTsmAuthManager     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            r9 = r20
            r10 = r1
            r11 = r18
            r13 = r17
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmAPDUCommand r2 = r8.installDoorCard(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            if (r2 != 0) goto L_0x0093
            java.util.concurrent.atomic.AtomicBoolean r0 = sNeedSync     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            r1 = 1
            r0.set(r1)     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            com.miui.tsmclient.model.BaseResponse r0 = new com.miui.tsmclient.model.BaseResponse     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            r1 = 16
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            r0.<init>(r1, r2)     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            if (r5 == 0) goto L_0x008b
            java.lang.String r1 = "extras_key_session_not_finish"
            boolean r1 = r5.getBoolean(r1)
            if (r1 != 0) goto L_0x0092
        L_0x008b:
            com.miui.tsmclient.model.mitsm.TSMSessionManager r1 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r1.removeSession(r6, r4)
        L_0x0092:
            return r0
        L_0x0093:
            int r8 = r2.getResult()     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            int r8 = com.miui.tsmclient.model.mitsm.MiTsmErrorCode.format(r8)     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            if (r8 != 0) goto L_0x0128
            java.util.List r8 = r2.getApdusList()     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            if (r8 == 0) goto L_0x00fa
            java.util.List r8 = r2.getApdusList()     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            boolean r8 = r8.isEmpty()     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            if (r8 == 0) goto L_0x00ae
            goto L_0x00fa
        L_0x00ae:
            com.tsmclient.smartcard.terminal.IScTerminal r8 = r21.getTerminal()     // Catch:{ NfcEeIOException -> 0x01ae, IOException -> 0x018f, SeiTSMApiException -> 0x0172, InterruptedException -> 0x015b, all -> 0x0155 }
            r8.open()     // Catch:{ all -> 0x00f1 }
            com.miui.tsmclient.model.BaseResponse r9 = r7.executeCapdu((android.content.Context) r0, (com.tsmclient.smartcard.terminal.IScTerminal) r8, (com.miui.tsmclient.seitsm.TsmRpcModels.TsmSessionInfo) r1, (com.miui.tsmclient.seitsm.TsmRpcModels.TsmAPDUCommand) r2)     // Catch:{ all -> 0x00f1 }
            if (r9 == 0) goto L_0x00d8
            int r1 = r9.mResultCode     // Catch:{ all -> 0x00f1 }
            if (r1 != 0) goto L_0x00d8
            java.lang.String r10 = r2.getAid()     // Catch:{ all -> 0x00f1 }
            r1 = r19
            r2 = r20
            r11 = 0
            r3 = r21
            r12 = r4
            r4 = r10
            r10 = r5
            r5 = r17
            r13 = r6
            r6 = r18
            r1.updateCardInfoFromServer(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x00d6 }
            goto L_0x00dc
        L_0x00d6:
            r0 = move-exception
            goto L_0x00f6
        L_0x00d8:
            r12 = r4
            r10 = r5
            r13 = r6
            r11 = 0
        L_0x00dc:
            r8.close()     // Catch:{ NfcEeIOException -> 0x013d, IOException -> 0x013a, SeiTSMApiException -> 0x0138, InterruptedException -> 0x0136 }
            if (r10 == 0) goto L_0x00e9
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x00f0
        L_0x00e9:
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r13, r12)
        L_0x00f0:
            return r9
        L_0x00f1:
            r0 = move-exception
            r12 = r4
            r10 = r5
            r13 = r6
            r11 = 0
        L_0x00f6:
            r8.close()     // Catch:{ NfcEeIOException -> 0x013d, IOException -> 0x013a, SeiTSMApiException -> 0x0138, InterruptedException -> 0x0136 }
            throw r0     // Catch:{ NfcEeIOException -> 0x013d, IOException -> 0x013a, SeiTSMApiException -> 0x0138, InterruptedException -> 0x0136 }
        L_0x00fa:
            r12 = r4
            r10 = r5
            r13 = r6
            r11 = 0
            java.lang.String r4 = r2.getAid()     // Catch:{ NfcEeIOException -> 0x013d, IOException -> 0x013a, SeiTSMApiException -> 0x0138, InterruptedException -> 0x0136 }
            r1 = r19
            r2 = r20
            r3 = r21
            r5 = r17
            r6 = r18
            r1.updateCardInfoFromServer(r2, r3, r4, r5, r6)     // Catch:{ NfcEeIOException -> 0x013d, IOException -> 0x013a, SeiTSMApiException -> 0x0138, InterruptedException -> 0x0136 }
            com.miui.tsmclient.model.BaseResponse r0 = new com.miui.tsmclient.model.BaseResponse     // Catch:{ NfcEeIOException -> 0x013d, IOException -> 0x013a, SeiTSMApiException -> 0x0138, InterruptedException -> 0x0136 }
            java.lang.Object[] r1 = new java.lang.Object[r11]     // Catch:{ NfcEeIOException -> 0x013d, IOException -> 0x013a, SeiTSMApiException -> 0x0138, InterruptedException -> 0x0136 }
            r0.<init>(r11, r1)     // Catch:{ NfcEeIOException -> 0x013d, IOException -> 0x013a, SeiTSMApiException -> 0x0138, InterruptedException -> 0x0136 }
            if (r10 == 0) goto L_0x0120
            java.lang.String r1 = "extras_key_session_not_finish"
            boolean r1 = r10.getBoolean(r1)
            if (r1 != 0) goto L_0x0127
        L_0x0120:
            com.miui.tsmclient.model.mitsm.TSMSessionManager r1 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r1.removeSession(r13, r12)
        L_0x0127:
            return r0
        L_0x0128:
            r12 = r4
            r10 = r5
            r13 = r6
            r11 = 0
            com.miui.tsmclient.seitsm.Exception.SeiTSMApiException r0 = new com.miui.tsmclient.seitsm.Exception.SeiTSMApiException     // Catch:{ NfcEeIOException -> 0x013d, IOException -> 0x013a, SeiTSMApiException -> 0x0138, InterruptedException -> 0x0136 }
            java.lang.String r1 = r2.getErrorDesc()     // Catch:{ NfcEeIOException -> 0x013d, IOException -> 0x013a, SeiTSMApiException -> 0x0138, InterruptedException -> 0x0136 }
            r0.<init>(r8, r1)     // Catch:{ NfcEeIOException -> 0x013d, IOException -> 0x013a, SeiTSMApiException -> 0x0138, InterruptedException -> 0x0136 }
            throw r0     // Catch:{ NfcEeIOException -> 0x013d, IOException -> 0x013a, SeiTSMApiException -> 0x0138, InterruptedException -> 0x0136 }
        L_0x0136:
            r0 = move-exception
            goto L_0x0160
        L_0x0138:
            r0 = move-exception
            goto L_0x0177
        L_0x013a:
            r0 = move-exception
            goto L_0x0194
        L_0x013d:
            r0 = move-exception
            goto L_0x01b3
        L_0x0140:
            r12 = r4
            r10 = r5
            r13 = r6
            if (r10 == 0) goto L_0x014d
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x0154
        L_0x014d:
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r13, r12)
        L_0x0154:
            return r9
        L_0x0155:
            r0 = move-exception
            r12 = r4
            r10 = r5
            r13 = r6
            goto L_0x01e1
        L_0x015b:
            r0 = move-exception
            r12 = r4
            r10 = r5
            r13 = r6
            r11 = 0
        L_0x0160:
            r1 = 11
            java.lang.String r2 = "issue is interrupted."
            com.miui.tsmclient.util.LogUtils.e(r2, r0)     // Catch:{ all -> 0x01e0 }
            if (r10 == 0) goto L_0x01a4
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x01ab
            goto L_0x01a4
        L_0x0172:
            r0 = move-exception
            r12 = r4
            r10 = r5
            r13 = r6
            r11 = 0
        L_0x0177:
            int r1 = r0.getErrorCode()     // Catch:{ all -> 0x01e0 }
            java.lang.String r16 = r0.getMessage()     // Catch:{ all -> 0x01e0 }
            java.lang.String r2 = "issue failed with an tsmapi exception."
            com.miui.tsmclient.util.LogUtils.e(r2, r0)     // Catch:{ all -> 0x01e0 }
            if (r10 == 0) goto L_0x01a4
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x01ab
            goto L_0x01a4
        L_0x018f:
            r0 = move-exception
            r12 = r4
            r10 = r5
            r13 = r6
            r11 = 0
        L_0x0194:
            r1 = 2
            java.lang.String r2 = "issue failed with an io exception."
            com.miui.tsmclient.util.LogUtils.e(r2, r0)     // Catch:{ all -> 0x01e0 }
            if (r10 == 0) goto L_0x01a4
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x01ab
        L_0x01a4:
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r13, r12)
        L_0x01ab:
            r0 = r16
            goto L_0x01d8
        L_0x01ae:
            r0 = move-exception
            r12 = r4
            r10 = r5
            r13 = r6
            r11 = 0
        L_0x01b3:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x01e0 }
            r1.<init>()     // Catch:{ all -> 0x01e0 }
            java.lang.String r2 = "issue failed with an nfc exception. errorCode:"
            r1.append(r2)     // Catch:{ all -> 0x01e0 }
            int r2 = r0.getErrorCode()     // Catch:{ all -> 0x01e0 }
            r1.append(r2)     // Catch:{ all -> 0x01e0 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x01e0 }
            com.miui.tsmclient.util.LogUtils.e(r1, r0)     // Catch:{ all -> 0x01e0 }
            r1 = 10
            if (r10 == 0) goto L_0x01a4
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x01ab
            goto L_0x01a4
        L_0x01d8:
            com.miui.tsmclient.model.BaseResponse r2 = new com.miui.tsmclient.model.BaseResponse
            java.lang.Object[] r3 = new java.lang.Object[r11]
            r2.<init>(r1, r0, r3)
            return r2
        L_0x01e0:
            r0 = move-exception
        L_0x01e1:
            if (r10 == 0) goto L_0x01eb
            java.lang.String r1 = "extras_key_session_not_finish"
            boolean r1 = r10.getBoolean(r1)
            if (r1 != 0) goto L_0x01f2
        L_0x01eb:
            com.miui.tsmclient.model.mitsm.TSMSessionManager r1 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r1.removeSession(r13, r12)
        L_0x01f2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.model.mifare.MifareCardClient.issue(android.content.Context, com.miui.tsmclient.entity.CardInfo, android.os.Bundle):com.miui.tsmclient.model.BaseResponse");
    }

    public BaseResponse queryMifareCardInfo(Context context) {
        int i;
        String str = "";
        try {
            TsmRpcModels.QueryDoorCardInfoResponse queryMifareCardList = this.mSeiTsmAuthManager.queryMifareCardList(context);
            if (queryMifareCardList == null) {
                i = -1;
            } else {
                int format = MiTsmErrorCode.format(queryMifareCardList.getResult());
                String errorDesc = queryMifareCardList.getErrorDesc();
                LogUtils.d("queryMifareCardInfo result: " + format);
                if (format == 0) {
                    return new BaseResponse(0, queryMifareCardList);
                }
                i = format;
                str = errorDesc;
            }
        } catch (SeiTSMApiException e) {
            LogUtils.e("failed to query mifare card info", e);
            int errorCode = e.getErrorCode();
            str = e.getMessage();
            i = errorCode;
        }
        return new BaseResponse(i, str, new Object[0]);
    }

    public BaseResponse updateMifareCardInfo(Context context, MifareCardInfo... mifareCardInfoArr) {
        int i;
        int i2;
        String str = "";
        try {
            TsmRpcModels.CommonResponse updateMifareCard = this.mSeiTsmAuthManager.updateMifareCard(context, mifareCardInfoArr);
            if (updateMifareCard == null) {
                i = -1;
                return new BaseResponse(i, str, new Object[0]);
            }
            i2 = MiTsmErrorCode.format(updateMifareCard.getResult());
            str = updateMifareCard.getErrorDesc();
            LogUtils.d("updateMifareCardInfo result: " + i2);
            i = i2;
            return new BaseResponse(i, str, new Object[0]);
        } catch (SeiTSMApiException e) {
            LogUtils.e("failed to update mifare card info", e);
            i2 = e.getErrorCode();
            str = e.getMessage();
        }
    }

    public BaseResponse isServiceAvailable(Context context, CardInfo cardInfo, Bundle bundle) {
        TsmRpcModels.ServiceType serviceType;
        int i = -1;
        if (!(cardInfo instanceof MifareCardInfo)) {
            return new BaseResponse(-1, new Object[0]);
        }
        MifareCardInfo mifareCardInfo = (MifareCardInfo) cardInfo;
        String str = "";
        try {
            if (getMifareCardType((MifareCardInfo) cardInfo) != 1) {
                serviceType = TsmRpcModels.ServiceType.MIFARE_CARD;
            } else {
                serviceType = TsmRpcModels.ServiceType.DOOR_CARD;
            }
            TsmRpcModels.CommonResponse isServiceAvailable = this.mSeiTsmAuthManager.isServiceAvailable(context, serviceType, mifareCardInfo.getProductId());
            if (isServiceAvailable != null) {
                i = isServiceAvailable.getResult();
                str = isServiceAvailable.getErrorDesc();
            }
        } catch (SeiTSMApiException e) {
            i = e.getErrorCode();
            str = e.getMessage();
            LogUtils.e("failed to check service availability", e);
        }
        LogUtils.d("isServiceAvailable result: " + i + ", msg:" + str);
        return new BaseResponse(i, str, new Object[0]);
    }

    public static List<MifareCardInfo> fillFromTsm(Context context, List<TsmRpcModels.DoorCardInfo> list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (TsmRpcModels.DoorCardInfo next : list) {
            MifareCardInfo mifareCardInfo = new MifareCardInfo();
            mifareCardInfo.mAid = next.getAid();
            mifareCardInfo.mMifareCardType = next.getCardType().getNumber();
            mifareCardInfo.mCardName = TextUtils.isEmpty(next.getName()) ? ResUtils.getString(context, "entrance_card_name_home") : next.getName();
            mifareCardInfo.mCardArt = next.getCardArt();
            mifareCardInfo.mCardFace = i % 2 == 0 ? 1 : 2;
            mifareCardInfo.mFingerAuthFlag = next.getFingerFlag();
            mifareCardInfo.setProductId(next.getProductId());
            arrayList.add(mifareCardInfo);
            i++;
        }
        return arrayList;
    }

    public static void updateInfoFromServer(Context context, MifareCardInfo mifareCardInfo, TsmRpcModels.DoorCardInfo doorCardInfo) {
        if (doorCardInfo != null) {
            mifareCardInfo.mAid = doorCardInfo.getAid();
            mifareCardInfo.mCardArt = doorCardInfo.getCardArt();
            mifareCardInfo.mCardName = TextUtils.isEmpty(doorCardInfo.getName()) ? ResUtils.getString(context, "entrance_card_name_home") : doorCardInfo.getName();
            mifareCardInfo.mFingerAuthFlag = doorCardInfo.getFingerFlag();
            mifareCardInfo.setProductId(doorCardInfo.getProductId());
        }
    }

    private void updateCardInfoFromServer(Context context, CardInfo cardInfo, String str, int i, String str2) throws IOException, SeiTSMApiException {
        TsmRpcModels.DoorCardInfo queryDoorCardInfoByAid = this.mSeiTsmAuthManager.queryDoorCardInfoByAid(context, str, i, str2);
        if (CardInfo.CARD_TYPE_MIFARE.equals(cardInfo.mCardType)) {
            updateInfoFromServer(context, (MifareCardInfo) cardInfo, queryDoorCardInfoByAid);
            notifyCardInfoChanged(context, cardInfo);
        }
    }

    private int getMifareCardType(MifareCardInfo mifareCardInfo) {
        if (mifareCardInfo.mMifareCardType > 0) {
            return 1;
        }
        return mifareCardInfo.mMifareCardType;
    }
}
