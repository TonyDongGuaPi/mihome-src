package com.miui.tsmclient.model;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.mitsm.DecoratorMiTSMClient;
import com.miui.tsmclient.model.mitsm.MiTSMCardClient;
import com.miui.tsmclient.model.mitsm.MiTsmErrorCode;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.seitsm.TsmRpcModels;
import com.miui.tsmclient.util.LogUtils;
import com.tsmclient.smartcard.terminal.IScTerminal;
import java.io.IOException;

public class PayableCardClient extends DecoratorMiTSMClient {
    public PayableCardClient(MiTSMCardClient miTSMCardClient) {
        super(miTSMCardClient);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01cc, code lost:
        if (r11.needUpload() == false) goto L_0x018e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01d6, code lost:
        return new com.miui.tsmclient.model.BaseResponse(r1, r16, new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0116, code lost:
        if (r11.needUpload() == false) goto L_0x018e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0150, code lost:
        if (r11.needUpload() == false) goto L_0x018e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x017b, code lost:
        if (r11.needUpload() == false) goto L_0x018e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x017d, code lost:
        r11.setObjectName(r8.mCardType);
        r11.setCoreOperation(com.mi.global.shop.model.Tags.Lottery.NATIVE_TYPE_RECHARGE);
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r18).uploadUserExceptionLog(r11);
     */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x01e2  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x01f2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.miui.tsmclient.model.BaseResponse recharge(android.content.Context r18, com.miui.tsmclient.entity.PayableCardInfo r19, com.miui.tsmclient.pay.OrderInfo r20, android.nfc.Tag r21, android.os.Bundle r22) {
        /*
            r17 = this;
            r7 = r18
            r8 = r19
            r0 = r20
            r1 = 1
            r9 = 0
            if (r8 != 0) goto L_0x0012
            com.miui.tsmclient.model.BaseResponse r0 = new com.miui.tsmclient.model.BaseResponse
            java.lang.Object[] r2 = new java.lang.Object[r9]
            r0.<init>(r1, r2)
            return r0
        L_0x0012:
            if (r22 != 0) goto L_0x001b
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            r10 = r2
            goto L_0x001d
        L_0x001b:
            r10 = r22
        L_0x001d:
            java.lang.String r2 = "out_operation"
            boolean r2 = r10.getBoolean(r2)
            if (r2 != 0) goto L_0x002f
            if (r0 != 0) goto L_0x002f
            com.miui.tsmclient.model.BaseResponse r0 = new com.miui.tsmclient.model.BaseResponse
            java.lang.Object[] r2 = new java.lang.Object[r9]
            r0.<init>(r1, r2)
            return r0
        L_0x002f:
            com.miui.tsmclient.entity.UserExceptionLogInfo r11 = new com.miui.tsmclient.entity.UserExceptionLogInfo
            r11.<init>()
            java.lang.String r3 = "version_control_id"
            r4 = 0
            long r12 = r10.getLong(r3, r4)
            java.lang.String r3 = r8.mCardType
            java.lang.String r3 = com.miui.tsmclient.util.VersionControlHelper.uploadPhoneNumber(r7, r3, r12)
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L_0x004f
            java.lang.String r4 = "need_phone_number"
            r10.putString(r4, r3)
            r14 = 1
            goto L_0x0050
        L_0x004f:
            r14 = 0
        L_0x0050:
            if (r2 == 0) goto L_0x0056
            com.miui.tsmclient.model.mitsm.TSMSessionManager$BusinessType r3 = com.miui.tsmclient.model.mitsm.TSMSessionManager.BusinessType.OUT_RECHARGE
        L_0x0054:
            r15 = r3
            goto L_0x0059
        L_0x0056:
            com.miui.tsmclient.model.mitsm.TSMSessionManager$BusinessType r3 = com.miui.tsmclient.model.mitsm.TSMSessionManager.BusinessType.RECHARGE
            goto L_0x0054
        L_0x0059:
            java.lang.String r16 = ""
            r6 = r17
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmSessionInfo r4 = r6.getSession(r7, r8, r15, r1)     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            if (r4 == 0) goto L_0x0068
            java.lang.String r1 = r4.getSessionId()     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            goto L_0x006a
        L_0x0068:
            java.lang.String r1 = ""
        L_0x006a:
            r11.setSessionId(r1)     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            if (r2 == 0) goto L_0x007d
            com.miui.tsmclient.seitsm.TsmRpcModels$SeOperationType r5 = com.miui.tsmclient.seitsm.TsmRpcModels.SeOperationType.OUT_TOPUP     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            r1 = r17
            r2 = r18
            r3 = r19
            r6 = r10
            com.miui.tsmclient.model.BaseResponse r0 = r1.startSeOperation(r2, r3, r4, r5, r6)     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            goto L_0x00a6
        L_0x007d:
            r1 = 0
            java.util.List<com.miui.tsmclient.entity.ActionToken> r0 = r0.mActionTokens     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
        L_0x0084:
            boolean r2 = r0.hasNext()     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            if (r2 == 0) goto L_0x009a
            java.lang.Object r2 = r0.next()     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            com.miui.tsmclient.entity.ActionToken r2 = (com.miui.tsmclient.entity.ActionToken) r2     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            boolean r3 = r2.isRechargeType()     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            if (r3 == 0) goto L_0x0084
            java.lang.String r0 = r2.mToken     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            r5 = r0
            goto L_0x009b
        L_0x009a:
            r5 = r1
        L_0x009b:
            r1 = r17
            r2 = r18
            r3 = r19
            r6 = r10
            com.miui.tsmclient.model.BaseResponse r0 = r1.startTopupOperation(r2, r3, r4, r5, r6)     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
        L_0x00a6:
            boolean r1 = r0.isSuccess()     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            if (r1 == 0) goto L_0x00b8
            if (r14 == 0) goto L_0x00bd
            com.miui.tsmclient.util.VersionControlHelper r1 = com.miui.tsmclient.util.VersionControlHelper.getInstance()     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            java.lang.String r2 = r8.mCardType     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            r1.confirmUploadPhoneNumber(r7, r2, r12)     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            goto L_0x00bd
        L_0x00b8:
            int r1 = r0.mResultCode     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
            r11.setErrorCode(r1)     // Catch:{ NfcEeIOException -> 0x0194, IOException -> 0x0157, SeiTSMApiException -> 0x011e, InterruptedException -> 0x00ea }
        L_0x00bd:
            java.lang.String r1 = "extras_key_session_not_finish"
            boolean r1 = r10.getBoolean(r1)
            if (r1 != 0) goto L_0x00cc
            com.miui.tsmclient.model.mitsm.TSMSessionManager r1 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r1.removeSession(r8, r15)
        L_0x00cc:
            r11.setErrorCode(r9)
            boolean r1 = r11.needUpload()
            if (r1 == 0) goto L_0x00e6
            java.lang.String r1 = r8.mCardType
            r11.setObjectName(r1)
            java.lang.String r1 = "recharge"
            r11.setCoreOperation(r1)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r1 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r18)
            r1.uploadUserExceptionLog(r11)
        L_0x00e6:
            return r0
        L_0x00e7:
            r0 = move-exception
            goto L_0x01da
        L_0x00ea:
            r0 = move-exception
            java.lang.String r1 = "recharge is interrupted."
            com.miui.tsmclient.util.LogUtils.e(r1, r0)     // Catch:{ all -> 0x00e7 }
            r1 = 11
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0119 }
            r11.setExtra(r0)     // Catch:{ all -> 0x0119 }
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0119 }
            r0.interrupt()     // Catch:{ all -> 0x0119 }
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x010f
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r8, r15)
        L_0x010f:
            r11.setErrorCode(r1)
            boolean r0 = r11.needUpload()
            if (r0 == 0) goto L_0x018e
            goto L_0x017d
        L_0x0119:
            r0 = move-exception
            r9 = 11
            goto L_0x01da
        L_0x011e:
            r0 = move-exception
            java.lang.String r1 = "recharge failed with an tsmapi exception."
            com.miui.tsmclient.util.LogUtils.e(r1, r0)     // Catch:{ all -> 0x00e7 }
            int r1 = r0.getErrorCode()     // Catch:{ all -> 0x00e7 }
            java.lang.String r16 = r0.getMessage()     // Catch:{ all -> 0x0153 }
            r2 = 1000000(0xf4240, float:1.401298E-39)
            if (r1 <= r2) goto L_0x0133
            r1 = 1001(0x3e9, float:1.403E-42)
        L_0x0133:
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0153 }
            r11.setExtra(r0)     // Catch:{ all -> 0x0153 }
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x0149
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r8, r15)
        L_0x0149:
            r11.setErrorCode(r1)
            boolean r0 = r11.needUpload()
            if (r0 == 0) goto L_0x018e
            goto L_0x017d
        L_0x0153:
            r0 = move-exception
            r9 = r1
            goto L_0x01da
        L_0x0157:
            r0 = move-exception
            java.lang.String r1 = "recharge failed with an io exception."
            com.miui.tsmclient.util.LogUtils.e(r1, r0)     // Catch:{ all -> 0x00e7 }
            r1 = 2
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0191 }
            r11.setExtra(r0)     // Catch:{ all -> 0x0191 }
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x0174
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r8, r15)
        L_0x0174:
            r11.setErrorCode(r1)
            boolean r0 = r11.needUpload()
            if (r0 == 0) goto L_0x018e
        L_0x017d:
            java.lang.String r0 = r8.mCardType
            r11.setObjectName(r0)
            java.lang.String r0 = "recharge"
            r11.setCoreOperation(r0)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r0 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r18)
            r0.uploadUserExceptionLog(r11)
        L_0x018e:
            r0 = r16
            goto L_0x01cf
        L_0x0191:
            r0 = move-exception
            r9 = 2
            goto L_0x01da
        L_0x0194:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e7 }
            r1.<init>()     // Catch:{ all -> 0x00e7 }
            java.lang.String r2 = "recharge failed with an nfc exception. errorCode:"
            r1.append(r2)     // Catch:{ all -> 0x00e7 }
            int r2 = r0.getErrorCode()     // Catch:{ all -> 0x00e7 }
            r1.append(r2)     // Catch:{ all -> 0x00e7 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00e7 }
            com.miui.tsmclient.util.LogUtils.e(r1, r0)     // Catch:{ all -> 0x00e7 }
            r1 = 10
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x01d7 }
            r11.setExtra(r0)     // Catch:{ all -> 0x01d7 }
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x01c5
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r8, r15)
        L_0x01c5:
            r11.setErrorCode(r1)
            boolean r0 = r11.needUpload()
            if (r0 == 0) goto L_0x018e
            goto L_0x017d
        L_0x01cf:
            com.miui.tsmclient.model.BaseResponse r2 = new com.miui.tsmclient.model.BaseResponse
            java.lang.Object[] r3 = new java.lang.Object[r9]
            r2.<init>(r1, r0, r3)
            return r2
        L_0x01d7:
            r0 = move-exception
            r9 = 10
        L_0x01da:
            java.lang.String r1 = "extras_key_session_not_finish"
            boolean r1 = r10.getBoolean(r1)
            if (r1 != 0) goto L_0x01e9
            com.miui.tsmclient.model.mitsm.TSMSessionManager r1 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r1.removeSession(r8, r15)
        L_0x01e9:
            r11.setErrorCode(r9)
            boolean r1 = r11.needUpload()
            if (r1 == 0) goto L_0x0203
            java.lang.String r1 = r8.mCardType
            r11.setObjectName(r1)
            java.lang.String r1 = "recharge"
            r11.setCoreOperation(r1)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r1 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r18)
            r1.uploadUserExceptionLog(r11)
        L_0x0203:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.model.PayableCardClient.recharge(android.content.Context, com.miui.tsmclient.entity.PayableCardInfo, com.miui.tsmclient.pay.OrderInfo, android.nfc.Tag, android.os.Bundle):com.miui.tsmclient.model.BaseResponse");
    }

    private BaseResponse startTopupOperation(Context context, CardInfo cardInfo, TsmRpcModels.TsmSessionInfo tsmSessionInfo, String str, Bundle bundle) throws IOException, SeiTSMApiException, InterruptedException {
        BaseResponse syncEse = sNeedSync.get() ? syncEse(context, cardInfo, tsmSessionInfo, bundle) : null;
        if (sNeedSync.get()) {
            return syncEse;
        }
        TsmRpcModels.TsmAPDUCommand startTopupOperation = this.mSeiTsmAuthManager.startTopupOperation(context, tsmSessionInfo, cardInfo, str, bundle);
        if (startTopupOperation == null) {
            LogUtils.d("can not get apduCommand,startTopupOperation failed.");
            return new BaseResponse(16, new Object[0]);
        }
        int format = MiTsmErrorCode.format(startTopupOperation.getResult());
        if (format != 0) {
            throw new SeiTSMApiException(format, startTopupOperation.getErrorDesc());
        } else if (startTopupOperation.getApdusList() == null || startTopupOperation.getApdusList().isEmpty()) {
            return new BaseResponse(0, new Object[0]);
        } else {
            IScTerminal terminal = cardInfo.getTerminal();
            try {
                terminal.open();
                return executeCapdu(context, terminal, tsmSessionInfo, startTopupOperation);
            } finally {
                terminal.close();
            }
        }
    }
}
