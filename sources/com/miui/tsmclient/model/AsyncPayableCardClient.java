package com.miui.tsmclient.model;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.model.mitsm.MiTSMCardClient;
import com.miui.tsmclient.model.mitsm.PullDataOperationType;
import com.miui.tsmclient.model.mitsm.TSMSessionManager;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.util.Constants;

public class AsyncPayableCardClient extends PayableCardClient {
    private static final int MAX_RETRY_COUNT = 10;
    private int mRetryCount;

    public AsyncPayableCardClient(MiTSMCardClient miTSMCardClient) {
        super(miTSMCardClient);
    }

    /* access modifiers changed from: protected */
    public BaseResponse recharge(Context context, PayableCardInfo payableCardInfo, OrderInfo orderInfo, Tag tag, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle2 = bundle;
        PullDataOperationType pullDataOperationType = bundle2.getBoolean(Constants.EXTRA_OUT_OPERATION) ? PullDataOperationType.OUT_RECHARGE : PullDataOperationType.RECHARGE;
        bundle2.putBoolean(MiTSMCardClient.EXTRAS_KEY_SESSION_NOT_FINISH, true);
        BaseResponse baseResponse = null;
        if (this.mRetryCount == 0) {
            baseResponse = super.recharge(context, payableCardInfo, orderInfo, tag, bundle2);
        } else if (this.mRetryCount == 10) {
            this.mRetryCount = 0;
        }
        if (baseResponse == null || baseResponse.mResultCode == 0) {
            return pullBusCardTopUpData(context, payableCardInfo, pullDataOperationType);
        }
        TSMSessionManager.getInstance().removeSession(payableCardInfo, pullDataOperationType.getBusinessType());
        return baseResponse;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0171, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0172, code lost:
        r15 = r4;
        r3 = com.miui.tsmclient.model.ErrorCode.DATA_NOT_READY;
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0195, code lost:
        if (3007 == r15) goto L_0x01a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0197, code lost:
        r8.mRetryCount = 0;
        com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance().removeSession(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x01a0, code lost:
        if (r1 == null) goto L_0x01a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x01a2, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x01a5, code lost:
        r13.setErrorCode(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x01ac, code lost:
        if (r13.needUpload() == false) goto L_0x01bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x01ae, code lost:
        r13.setObjectName(r10.mCardType);
        r13.setCoreOperation("pullBusCardTopUpData");
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r21).uploadUserExceptionLog(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x01bf, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x01e4, code lost:
        if (r13.needUpload() != false) goto L_0x01e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x01e6, code lost:
        r13.setObjectName(r10.mCardType);
        r13.setCoreOperation("pullBusCardTopUpData");
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r21).uploadUserExceptionLog(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x01fe, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0202, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0207, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0209, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x021c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x021d, code lost:
        r3 = com.miui.tsmclient.model.ErrorCode.DATA_NOT_READY;
        r14 = 0;
        r2 = -2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0223, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0251, code lost:
        if (r13.needUpload() != false) goto L_0x01e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0254, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0255, code lost:
        r3 = com.miui.tsmclient.model.ErrorCode.DATA_NOT_READY;
        r14 = 0;
        r15 = -2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0280, code lost:
        if (r13.needUpload() == false) goto L_0x02ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0284, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x02aa, code lost:
        if (r13.needUpload() != false) goto L_0x01e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x02b0, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x02b1, code lost:
        r3 = com.miui.tsmclient.model.ErrorCode.DATA_NOT_READY;
        r14 = 0;
        r15 = -2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x02df, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x02eb, code lost:
        if (r13.needUpload() == false) goto L_0x02ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x02f6, code lost:
        return new com.miui.tsmclient.model.BaseResponse(r2, r12, new java.lang.Object[r14]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0067, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0071, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0020, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0021, code lost:
        r2 = -2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0078 */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x01fe A[ExcHandler: InterruptedException (e java.lang.InterruptedException), PHI: r1 r3 r14 
      PHI: (r1v22 com.tsmclient.smartcard.terminal.IScTerminal) = (r1v23 com.tsmclient.smartcard.terminal.IScTerminal), (r1v23 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal) binds: [B:134:0x01c7, B:135:?, B:131:0x01c0, B:132:?, B:117:0x017e] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r3v22 int) = (r3v23 int), (r3v23 int), (r3v27 int), (r3v27 int), (r3v28 int) binds: [B:134:0x01c7, B:135:?, B:131:0x01c0, B:132:?, B:117:0x017e] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r14v20 int) = (r14v21 int), (r14v21 int), (r14v24 int), (r14v24 int), (r14v25 int) binds: [B:134:0x01c7, B:135:?, B:131:0x01c0, B:132:?, B:117:0x017e] A[DONT_GENERATE, DONT_INLINE], Splitter:B:131:0x01c0] */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x0202 A[ExcHandler: IOException (e java.io.IOException), PHI: r1 r3 r14 
      PHI: (r1v21 com.tsmclient.smartcard.terminal.IScTerminal) = (r1v23 com.tsmclient.smartcard.terminal.IScTerminal), (r1v23 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal) binds: [B:134:0x01c7, B:135:?, B:131:0x01c0, B:132:?, B:117:0x017e] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r3v21 int) = (r3v23 int), (r3v23 int), (r3v27 int), (r3v27 int), (r3v28 int) binds: [B:134:0x01c7, B:135:?, B:131:0x01c0, B:132:?, B:117:0x017e] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r14v19 int) = (r14v21 int), (r14v21 int), (r14v24 int), (r14v24 int), (r14v25 int) binds: [B:134:0x01c7, B:135:?, B:131:0x01c0, B:132:?, B:117:0x017e] A[DONT_GENERATE, DONT_INLINE], Splitter:B:117:0x017e] */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x0223 A[ExcHandler: InterruptedException (e java.lang.InterruptedException), PHI: r1 
      PHI: (r1v17 com.tsmclient.smartcard.terminal.IScTerminal) = (r1v0 com.tsmclient.smartcard.terminal.IScTerminal), (r1v0 com.tsmclient.smartcard.terminal.IScTerminal), (r1v0 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v0 com.tsmclient.smartcard.terminal.IScTerminal) binds: [B:1:0x0015, B:10:0x0029, B:11:?, B:13:0x002d, B:32:0x0082, B:38:0x0094, B:29:0x0078, B:30:?, B:4:0x001b] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x0247  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0254 A[ExcHandler: SeiTSMApiException (e com.miui.tsmclient.seitsm.Exception.SeiTSMApiException), Splitter:B:1:0x0015] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x026b  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0276  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x0284 A[ExcHandler: IOException (e java.io.IOException), PHI: r1 
      PHI: (r1v13 com.tsmclient.smartcard.terminal.IScTerminal) = (r1v0 com.tsmclient.smartcard.terminal.IScTerminal), (r1v0 com.tsmclient.smartcard.terminal.IScTerminal), (r1v0 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v18 com.tsmclient.smartcard.terminal.IScTerminal), (r1v0 com.tsmclient.smartcard.terminal.IScTerminal) binds: [B:1:0x0015, B:10:0x0029, B:11:?, B:13:0x002d, B:32:0x0082, B:38:0x0094, B:20:0x005e, B:29:0x0078, B:30:?, B:21:?, B:4:0x001b] A[DONT_GENERATE, DONT_INLINE], Splitter:B:1:0x0015] */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x02a0  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x02b0 A[ExcHandler: NfcEeIOException (e com.tsmclient.smartcard.exception.NfcEeIOException), Splitter:B:1:0x0015] */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x02df  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x02ff  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x030a  */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x0316  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006a A[ExcHandler: SeiTSMApiException (e com.miui.tsmclient.seitsm.Exception.SeiTSMApiException), Splitter:B:20:0x005e] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0071 A[ExcHandler: NfcEeIOException (e com.tsmclient.smartcard.exception.NfcEeIOException), Splitter:B:20:0x005e] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:196:0x02b5=Splitter:B:196:0x02b5, B:173:0x0259=Splitter:B:173:0x0259} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.miui.tsmclient.model.BaseResponse pullBusCardTopUpData(android.content.Context r21, com.miui.tsmclient.entity.CardInfo r22, com.miui.tsmclient.model.mitsm.PullDataOperationType r23) {
        /*
            r20 = this;
            r8 = r20
            r9 = r21
            r10 = r22
            com.miui.tsmclient.model.mitsm.TSMSessionManager$BusinessType r11 = r23.getBusinessType()
            java.lang.String r12 = ""
            com.miui.tsmclient.entity.UserExceptionLogInfo r13 = new com.miui.tsmclient.entity.UserExceptionLogInfo
            r13.<init>()
            r7 = 3007(0xbbf, float:4.214E-42)
            r6 = 0
            r1 = 0
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmSessionInfo r0 = r8.getSession(r9, r10, r11)     // Catch:{ NfcEeIOException -> 0x02b0, IOException -> 0x0284, SeiTSMApiException -> 0x0254, InterruptedException -> 0x0223, all -> 0x021c }
            if (r0 == 0) goto L_0x0027
            java.lang.String r2 = r0.getSessionId()     // Catch:{ NfcEeIOException -> 0x02b0, IOException -> 0x0284, SeiTSMApiException -> 0x0254, InterruptedException -> 0x0223, all -> 0x0020 }
            goto L_0x0029
        L_0x0020:
            r0 = move-exception
            r2 = -2
        L_0x0022:
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            goto L_0x02fd
        L_0x0027:
            java.lang.String r2 = ""
        L_0x0029:
            r13.setSessionId(r2)     // Catch:{ NfcEeIOException -> 0x02b0, IOException -> 0x0284, SeiTSMApiException -> 0x0254, InterruptedException -> 0x0223, all -> 0x021c }
            r2 = -2
        L_0x002d:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NfcEeIOException -> 0x0215, IOException -> 0x0284, SeiTSMApiException -> 0x020f, InterruptedException -> 0x0223, all -> 0x020c }
            r3.<init>()     // Catch:{ NfcEeIOException -> 0x0215, IOException -> 0x0284, SeiTSMApiException -> 0x020f, InterruptedException -> 0x0223, all -> 0x020c }
            java.lang.String r4 = "pullBusCardTopUpData, retryCount:"
            r3.append(r4)     // Catch:{ NfcEeIOException -> 0x0215, IOException -> 0x0284, SeiTSMApiException -> 0x020f, InterruptedException -> 0x0223, all -> 0x020c }
            int r4 = r8.mRetryCount     // Catch:{ NfcEeIOException -> 0x0215, IOException -> 0x0284, SeiTSMApiException -> 0x020f, InterruptedException -> 0x0223, all -> 0x020c }
            r3.append(r4)     // Catch:{ NfcEeIOException -> 0x0215, IOException -> 0x0284, SeiTSMApiException -> 0x020f, InterruptedException -> 0x0223, all -> 0x020c }
            java.lang.String r3 = r3.toString()     // Catch:{ NfcEeIOException -> 0x0215, IOException -> 0x0284, SeiTSMApiException -> 0x020f, InterruptedException -> 0x0223, all -> 0x020c }
            com.miui.tsmclient.util.LogUtils.d(r3)     // Catch:{ NfcEeIOException -> 0x0215, IOException -> 0x0284, SeiTSMApiException -> 0x020f, InterruptedException -> 0x0223, all -> 0x020c }
            com.miui.tsmclient.seitsm.SeiTsmAuthManager r3 = r8.mSeiTsmAuthManager     // Catch:{ NfcEeIOException -> 0x0215, IOException -> 0x0284, SeiTSMApiException -> 0x020f, InterruptedException -> 0x0223, all -> 0x020c }
            r5 = r23
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmAPDUCommand r16 = r3.pullBusCardPersoData(r9, r0, r5)     // Catch:{ NfcEeIOException -> 0x0215, IOException -> 0x0284, SeiTSMApiException -> 0x020f, InterruptedException -> 0x0223, all -> 0x020c }
            if (r16 != 0) goto L_0x0052
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            goto L_0x01c7
        L_0x0052:
            int r3 = r16.getResult()     // Catch:{ NfcEeIOException -> 0x0215, IOException -> 0x0284, SeiTSMApiException -> 0x020f, InterruptedException -> 0x0223, all -> 0x020c }
            int r4 = com.miui.tsmclient.model.mitsm.MiTsmErrorCode.format(r3)     // Catch:{ NfcEeIOException -> 0x0215, IOException -> 0x0284, SeiTSMApiException -> 0x020f, InterruptedException -> 0x0223, all -> 0x020c }
            if (r7 != r4) goto L_0x0080
            r2 = 2000(0x7d0, double:9.88E-321)
            java.lang.Thread.sleep(r2)     // Catch:{ InterruptedException -> 0x0078, NfcEeIOException -> 0x0071, IOException -> 0x0284, SeiTSMApiException -> 0x006a, all -> 0x0067 }
        L_0x0061:
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            goto L_0x01c0
        L_0x0067:
            r0 = move-exception
        L_0x0068:
            r2 = r4
            goto L_0x0022
        L_0x006a:
            r0 = move-exception
        L_0x006b:
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            goto L_0x0259
        L_0x0071:
            r0 = move-exception
        L_0x0072:
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            goto L_0x02b5
        L_0x0078:
            java.lang.Thread r2 = java.lang.Thread.currentThread()     // Catch:{ NfcEeIOException -> 0x0071, IOException -> 0x0284, SeiTSMApiException -> 0x006a, InterruptedException -> 0x0223, all -> 0x0067 }
            r2.interrupt()     // Catch:{ NfcEeIOException -> 0x0071, IOException -> 0x0284, SeiTSMApiException -> 0x006a, InterruptedException -> 0x0223, all -> 0x0067 }
            goto L_0x0061
        L_0x0080:
            if (r4 != 0) goto L_0x0178
            java.util.List r2 = r16.getApdusList()     // Catch:{ NfcEeIOException -> 0x0071, IOException -> 0x0284, SeiTSMApiException -> 0x006a, InterruptedException -> 0x0223, all -> 0x0171 }
            if (r2 == 0) goto L_0x0178
            java.util.List r2 = r16.getApdusList()     // Catch:{ NfcEeIOException -> 0x0071, IOException -> 0x0284, SeiTSMApiException -> 0x006a, InterruptedException -> 0x0223, all -> 0x0171 }
            boolean r2 = r2.isEmpty()     // Catch:{ NfcEeIOException -> 0x0071, IOException -> 0x0284, SeiTSMApiException -> 0x006a, InterruptedException -> 0x0223, all -> 0x0171 }
            if (r2 != 0) goto L_0x0178
            if (r1 != 0) goto L_0x00af
            com.tsmclient.smartcard.terminal.IScTerminal r2 = r22.getTerminal()     // Catch:{ NfcEeIOException -> 0x0071, IOException -> 0x0284, SeiTSMApiException -> 0x006a, InterruptedException -> 0x0223, all -> 0x0067 }
            r2.open()     // Catch:{ NfcEeIOException -> 0x00ac, IOException -> 0x00a8, SeiTSMApiException -> 0x00a5, InterruptedException -> 0x00a1, all -> 0x009e }
            r17 = r2
            goto L_0x00b1
        L_0x009e:
            r0 = move-exception
            r1 = r2
            goto L_0x0068
        L_0x00a1:
            r0 = move-exception
            r1 = r2
            goto L_0x0224
        L_0x00a5:
            r0 = move-exception
            r1 = r2
            goto L_0x006b
        L_0x00a8:
            r0 = move-exception
            r1 = r2
            goto L_0x0285
        L_0x00ac:
            r0 = move-exception
            r1 = r2
            goto L_0x0072
        L_0x00af:
            r17 = r1
        L_0x00b1:
            com.miui.tsmclient.seitsm.TsmRpcModels$SeOperationType r18 = r23.getSeOperationType()     // Catch:{ NfcEeIOException -> 0x0168, IOException -> 0x0160, SeiTSMApiException -> 0x0157, InterruptedException -> 0x014f, all -> 0x0145 }
            r19 = 0
            r1 = r20
            r2 = r21
            r3 = r17
            r15 = r4
            r4 = r0
            r5 = r16
            r14 = 0
            r6 = r18
            r7 = r19
            com.miui.tsmclient.model.BaseResponse r1 = r1.executeCapdu(r2, r3, r4, r5, r6, r7)     // Catch:{ NfcEeIOException -> 0x0141, IOException -> 0x013d, SeiTSMApiException -> 0x0139, InterruptedException -> 0x0135, all -> 0x0131 }
            if (r1 == 0) goto L_0x00fd
            int r2 = r1.mResultCode     // Catch:{ NfcEeIOException -> 0x00f6, IOException -> 0x00ef, SeiTSMApiException -> 0x00e8, InterruptedException -> 0x00e1, all -> 0x00d9 }
            if (r2 != 0) goto L_0x00fd
            r8.mRetryCount = r14     // Catch:{ NfcEeIOException -> 0x00f6, IOException -> 0x00ef, SeiTSMApiException -> 0x00e8, InterruptedException -> 0x00e1, all -> 0x00d9 }
            r2 = r15
            r1 = r17
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x01c7
        L_0x00d9:
            r0 = move-exception
            r2 = r15
            r1 = r17
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x02fd
        L_0x00e1:
            r0 = move-exception
            r1 = r17
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x0227
        L_0x00e8:
            r0 = move-exception
            r1 = r17
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x0259
        L_0x00ef:
            r0 = move-exception
            r1 = r17
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x0288
        L_0x00f6:
            r0 = move-exception
            r1 = r17
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x02b5
        L_0x00fd:
            if (r1 != 0) goto L_0x0103
            r0 = -2
        L_0x0100:
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x0106
        L_0x0103:
            int r0 = r1.mResultCode     // Catch:{ NfcEeIOException -> 0x0141, IOException -> 0x013d, SeiTSMApiException -> 0x0139, InterruptedException -> 0x0135, all -> 0x0131 }
            goto L_0x0100
        L_0x0106:
            if (r3 == r0) goto L_0x0111
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r2 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r2.removeSession(r10, r11)
        L_0x0111:
            if (r17 == 0) goto L_0x0116
            r17.close()
        L_0x0116:
            r13.setErrorCode(r0)
            boolean r0 = r13.needUpload()
            if (r0 == 0) goto L_0x0130
            java.lang.String r0 = r10.mCardType
            r13.setObjectName(r0)
            java.lang.String r0 = "pullBusCardTopUpData"
            r13.setCoreOperation(r0)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r0 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r21)
            r0.uploadUserExceptionLog(r13)
        L_0x0130:
            return r1
        L_0x0131:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x014a
        L_0x0135:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x0153
        L_0x0139:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x015c
        L_0x013d:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x0164
        L_0x0141:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x016d
        L_0x0145:
            r0 = move-exception
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x014a:
            r2 = r15
            r1 = r17
            goto L_0x02fd
        L_0x014f:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x0153:
            r1 = r17
            goto L_0x0227
        L_0x0157:
            r0 = move-exception
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x015c:
            r1 = r17
            goto L_0x0259
        L_0x0160:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x0164:
            r1 = r17
            goto L_0x0288
        L_0x0168:
            r0 = move-exception
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x016d:
            r1 = r17
            goto L_0x02b5
        L_0x0171:
            r0 = move-exception
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            goto L_0x02fc
        L_0x0178:
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            if (r15 != 0) goto L_0x01c0
            java.util.List r2 = r16.getApdusList()     // Catch:{ NfcEeIOException -> 0x0209, IOException -> 0x0202, SeiTSMApiException -> 0x0207, InterruptedException -> 0x01fe }
            if (r2 == 0) goto L_0x018e
            java.util.List r2 = r16.getApdusList()     // Catch:{ NfcEeIOException -> 0x0209, IOException -> 0x0202, SeiTSMApiException -> 0x0207, InterruptedException -> 0x01fe }
            boolean r2 = r2.isEmpty()     // Catch:{ NfcEeIOException -> 0x0209, IOException -> 0x0202, SeiTSMApiException -> 0x0207, InterruptedException -> 0x01fe }
            if (r2 == 0) goto L_0x01c0
        L_0x018e:
            com.miui.tsmclient.model.BaseResponse r0 = new com.miui.tsmclient.model.BaseResponse     // Catch:{ NfcEeIOException -> 0x0209, IOException -> 0x0202, SeiTSMApiException -> 0x0207, InterruptedException -> 0x01fe }
            java.lang.Object[] r2 = new java.lang.Object[r14]     // Catch:{ NfcEeIOException -> 0x0209, IOException -> 0x0202, SeiTSMApiException -> 0x0207, InterruptedException -> 0x01fe }
            r0.<init>(r14, r2)     // Catch:{ NfcEeIOException -> 0x0209, IOException -> 0x0202, SeiTSMApiException -> 0x0207, InterruptedException -> 0x01fe }
            if (r3 == r15) goto L_0x01a0
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r2 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r2.removeSession(r10, r11)
        L_0x01a0:
            if (r1 == 0) goto L_0x01a5
            r1.close()
        L_0x01a5:
            r13.setErrorCode(r15)
            boolean r1 = r13.needUpload()
            if (r1 == 0) goto L_0x01bf
            java.lang.String r1 = r10.mCardType
            r13.setObjectName(r1)
            java.lang.String r1 = "pullBusCardTopUpData"
            r13.setCoreOperation(r1)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r1 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r21)
            r1.uploadUserExceptionLog(r13)
        L_0x01bf:
            return r0
        L_0x01c0:
            int r2 = r8.mRetryCount     // Catch:{ NfcEeIOException -> 0x0209, IOException -> 0x0202, SeiTSMApiException -> 0x0207, InterruptedException -> 0x01fe }
            int r2 = r2 + 1
            r8.mRetryCount = r2     // Catch:{ NfcEeIOException -> 0x0209, IOException -> 0x0202, SeiTSMApiException -> 0x0207, InterruptedException -> 0x01fe }
            r2 = r15
        L_0x01c7:
            int r4 = r8.mRetryCount     // Catch:{ NfcEeIOException -> 0x0205, IOException -> 0x0202, SeiTSMApiException -> 0x0200, InterruptedException -> 0x01fe }
            r5 = 10
            if (r4 < r5) goto L_0x01f9
            if (r3 == r2) goto L_0x01d8
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r10, r11)
        L_0x01d8:
            if (r1 == 0) goto L_0x01dd
            r1.close()
        L_0x01dd:
            r13.setErrorCode(r2)
            boolean r0 = r13.needUpload()
            if (r0 == 0) goto L_0x02ef
        L_0x01e6:
            java.lang.String r0 = r10.mCardType
            r13.setObjectName(r0)
            java.lang.String r0 = "pullBusCardTopUpData"
            r13.setCoreOperation(r0)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r0 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r21)
            r0.uploadUserExceptionLog(r13)
            goto L_0x02ef
        L_0x01f9:
            r6 = 0
            r7 = 3007(0xbbf, float:4.214E-42)
            goto L_0x002d
        L_0x01fe:
            r0 = move-exception
            goto L_0x0227
        L_0x0200:
            r0 = move-exception
            goto L_0x0213
        L_0x0202:
            r0 = move-exception
            goto L_0x0288
        L_0x0205:
            r0 = move-exception
            goto L_0x0219
        L_0x0207:
            r0 = move-exception
            goto L_0x0259
        L_0x0209:
            r0 = move-exception
            goto L_0x02b5
        L_0x020c:
            r0 = move-exception
            goto L_0x0022
        L_0x020f:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x0213:
            r15 = r2
            goto L_0x0259
        L_0x0215:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x0219:
            r15 = r2
            goto L_0x02b5
        L_0x021c:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            r2 = -2
            goto L_0x02fd
        L_0x0223:
            r0 = move-exception
        L_0x0224:
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x0227:
            r2 = 11
            java.lang.String r4 = r0.getMessage()     // Catch:{ all -> 0x02ae }
            r13.setExtra(r4)     // Catch:{ all -> 0x02ae }
            java.lang.String r4 = "pullBusCardTopUpData is interrupted."
            com.miui.tsmclient.util.LogUtils.e(r4, r0)     // Catch:{ all -> 0x02ae }
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x02ae }
            r0.interrupt()     // Catch:{ all -> 0x02ae }
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r10, r11)
            if (r1 == 0) goto L_0x024a
            r1.close()
        L_0x024a:
            r13.setErrorCode(r2)
            boolean r0 = r13.needUpload()
            if (r0 == 0) goto L_0x02ef
            goto L_0x01e6
        L_0x0254:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            r15 = -2
        L_0x0259:
            int r2 = r0.getErrorCode()     // Catch:{ all -> 0x02fb }
            java.lang.String r12 = r0.getMessage()     // Catch:{ all -> 0x02ae }
            r13.setExtra(r12)     // Catch:{ all -> 0x02ae }
            java.lang.String r4 = "pullBusCardTopUpData failed with an tsmapi exception."
            com.miui.tsmclient.util.LogUtils.e(r4, r0)     // Catch:{ all -> 0x02ae }
            if (r3 == r2) goto L_0x0274
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r10, r11)
        L_0x0274:
            if (r1 == 0) goto L_0x0279
            r1.close()
        L_0x0279:
            r13.setErrorCode(r2)
            boolean r0 = r13.needUpload()
            if (r0 == 0) goto L_0x02ef
            goto L_0x01e6
        L_0x0284:
            r0 = move-exception
        L_0x0285:
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x0288:
            r2 = 2
            java.lang.String r4 = r0.getMessage()     // Catch:{ all -> 0x02ae }
            r13.setExtra(r4)     // Catch:{ all -> 0x02ae }
            java.lang.String r4 = "pullBusCardTopUpData failed with an io exception."
            com.miui.tsmclient.util.LogUtils.e(r4, r0)     // Catch:{ all -> 0x02ae }
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r10, r11)
            if (r1 == 0) goto L_0x02a3
            r1.close()
        L_0x02a3:
            r13.setErrorCode(r2)
            boolean r0 = r13.needUpload()
            if (r0 == 0) goto L_0x02ef
            goto L_0x01e6
        L_0x02ae:
            r0 = move-exception
            goto L_0x02fd
        L_0x02b0:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            r15 = -2
        L_0x02b5:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x02fb }
            r2.<init>()     // Catch:{ all -> 0x02fb }
            java.lang.String r4 = "pullBusCardTopUpData failed with an nfc exception. errorCode:"
            r2.append(r4)     // Catch:{ all -> 0x02fb }
            int r4 = r0.getErrorCode()     // Catch:{ all -> 0x02fb }
            r2.append(r4)     // Catch:{ all -> 0x02fb }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x02fb }
            com.miui.tsmclient.util.LogUtils.e(r2, r0)     // Catch:{ all -> 0x02fb }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x02f7 }
            r13.setExtra(r0)     // Catch:{ all -> 0x02f7 }
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r10, r11)
            if (r1 == 0) goto L_0x02e2
            r1.close()
        L_0x02e2:
            r2 = 10
            r13.setErrorCode(r2)
            boolean r0 = r13.needUpload()
            if (r0 == 0) goto L_0x02ef
            goto L_0x01e6
        L_0x02ef:
            com.miui.tsmclient.model.BaseResponse r0 = new com.miui.tsmclient.model.BaseResponse
            java.lang.Object[] r1 = new java.lang.Object[r14]
            r0.<init>(r2, r12, r1)
            return r0
        L_0x02f7:
            r0 = move-exception
            r2 = 10
            goto L_0x02fd
        L_0x02fb:
            r0 = move-exception
        L_0x02fc:
            r2 = r15
        L_0x02fd:
            if (r3 == r2) goto L_0x0308
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r3 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r3.removeSession(r10, r11)
        L_0x0308:
            if (r1 == 0) goto L_0x030d
            r1.close()
        L_0x030d:
            r13.setErrorCode(r2)
            boolean r1 = r13.needUpload()
            if (r1 == 0) goto L_0x0327
            java.lang.String r1 = r10.mCardType
            r13.setObjectName(r1)
            java.lang.String r1 = "pullBusCardTopUpData"
            r13.setCoreOperation(r1)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r1 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r21)
            r1.uploadUserExceptionLog(r13)
        L_0x0327:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.model.AsyncPayableCardClient.pullBusCardTopUpData(android.content.Context, com.miui.tsmclient.entity.CardInfo, com.miui.tsmclient.model.mitsm.PullDataOperationType):com.miui.tsmclient.model.BaseResponse");
    }
}
