package com.miui.tsmclient.model.mitsm;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.mitsm.TSMSessionManager;
import com.miui.tsmclient.util.Constants;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.VersionControlHelper;

public class AsyncMiTSMClient extends DecoratorMiTSMClient {
    private static final int MAX_RETRY_COUNT = 10;
    private int mRetryCount;

    public AsyncMiTSMClient(MiTSMCardClient miTSMCardClient) {
        super(miTSMCardClient);
    }

    public BaseResponse issue(Context context, CardInfo cardInfo, Bundle bundle) {
        Bundle bundle2 = getBundle(bundle);
        PullDataOperationType pullDataOperationType = bundle2.getBoolean(Constants.EXTRA_OUT_OPERATION) ? PullDataOperationType.OUT_ISSUE : PullDataOperationType.ISSUE;
        BaseResponse baseResponse = null;
        if (this.mRetryCount == 0) {
            baseResponse = super.issue(context, cardInfo, bundle2);
        } else if (this.mRetryCount == 10) {
            this.mRetryCount = 0;
        }
        if (bundle2.getBoolean("pre_load") || !(baseResponse == null || baseResponse.mResultCode == 0)) {
            TSMSessionManager.getInstance().removeSession(cardInfo, pullDataOperationType.getBusinessType());
            return baseResponse;
        }
        BaseResponse pullPersoData = pullPersoData(context, cardInfo, pullDataOperationType);
        if (pullPersoData.isSuccess()) {
            uploadPhoneNumber(context, cardInfo, bundle2);
        }
        return pullPersoData;
    }

    public BaseResponse transferIn(Context context, CardInfo cardInfo, Bundle bundle) {
        BaseResponse baseResponse;
        LogUtils.d("AsyncMiTSMClient transferIn called.");
        Bundle bundle2 = getBundle(bundle);
        if (this.mRetryCount == 0) {
            baseResponse = super.transferIn(context, cardInfo, bundle2);
        } else {
            if (this.mRetryCount == 10) {
                this.mRetryCount = 0;
            }
            baseResponse = null;
        }
        if (baseResponse == null || baseResponse.isSuccess()) {
            BaseResponse pullPersoData = pullPersoData(context, cardInfo, PullDataOperationType.TRANSFER_IN);
            if (pullPersoData.isSuccess()) {
                uploadPhoneNumber(context, cardInfo, bundle2);
            }
            return pullPersoData;
        }
        TSMSessionManager.getInstance().removeSession(cardInfo, TSMSessionManager.BusinessType.TRANSFER_IN);
        return baseResponse;
    }

    public BaseResponse transferOut(Context context, CardInfo cardInfo, Bundle bundle) {
        BaseResponse baseResponse;
        LogUtils.d("AsyncMiTSMClient transferOut called.");
        Bundle bundle2 = getBundle(bundle);
        if (this.mRetryCount == 0) {
            baseResponse = super.transferOut(context, cardInfo, bundle2);
        } else {
            if (this.mRetryCount == 10) {
                this.mRetryCount = 0;
            }
            baseResponse = null;
        }
        if (baseResponse == null || baseResponse.isSuccess()) {
            return pullPersoData(context, cardInfo, PullDataOperationType.TRANSFER_OUT);
        }
        TSMSessionManager.getInstance().removeSession(cardInfo, TSMSessionManager.BusinessType.TRANSFER_OUT);
        return baseResponse;
    }

    public BaseResponse returnCard(Context context, CardInfo cardInfo, Bundle bundle) {
        BaseResponse baseResponse;
        LogUtils.d("AsyncMiTSMClient returnCard called.");
        Bundle bundle2 = getBundle(bundle);
        PullDataOperationType pullDataOperationType = PullDataOperationType.OUT_RETURN;
        if (this.mRetryCount == 0) {
            baseResponse = super.returnCard(context, cardInfo, bundle2);
        } else {
            if (this.mRetryCount == 10) {
                this.mRetryCount = 0;
            }
            baseResponse = null;
        }
        if (baseResponse == null || baseResponse.isSuccess()) {
            return pullPersoData(context, cardInfo, pullDataOperationType);
        }
        TSMSessionManager.getInstance().removeSession(cardInfo, pullDataOperationType.getBusinessType());
        return baseResponse;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v0, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v1, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v2, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v4, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v5, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v6, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v7, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v8, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v9, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v10, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v11, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v12, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v13, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v14, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v15, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v16, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v17, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v24, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v26, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v18, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v31, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v33, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v35, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v37, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v32, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v41, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v42, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v43, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v44, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v35, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v36, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v45, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v45, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v46, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v47, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v48, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v37, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v49, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v50, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v51, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v52, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v38, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v54, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v55, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v56, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v57, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v58, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v59, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v40, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v41, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v60, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v61, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v62, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v63, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v64, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v65, resolved type: com.tsmclient.smartcard.terminal.IScTerminal} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x014b, code lost:
        if (r1 != null) goto L_0x0151;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x014d, code lost:
        r0 = -2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:?, code lost:
        r0 = r1.mResultCode;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0154, code lost:
        if (3007 == r0) goto L_0x015f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0156, code lost:
        r8.mRetryCount = 0;
        com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance().removeSession(r10, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x015f, code lost:
        if (r18 == null) goto L_0x0164;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0161, code lost:
        r18.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0164, code lost:
        r11.setErrorCode(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x016b, code lost:
        if (r11.needUpload() == false) goto L_0x0180;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x016d, code lost:
        r11.setObjectName(r10.mCardType);
        r11.setCoreOperation(r24.getDescription());
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r22).uploadUserExceptionLog(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0180, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x01c3, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x01c4, code lost:
        r15 = r4;
        r3 = com.miui.tsmclient.model.ErrorCode.DATA_NOT_READY;
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x01c8, code lost:
        r2 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x01cb, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x01cc, code lost:
        r15 = r4;
        r3 = com.miui.tsmclient.model.ErrorCode.DATA_NOT_READY;
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x01d0, code lost:
        r2 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x01d3, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x01d4, code lost:
        r15 = r4;
        r3 = com.miui.tsmclient.model.ErrorCode.DATA_NOT_READY;
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x01d8, code lost:
        r2 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:?, code lost:
        r0 = new com.miui.tsmclient.model.BaseResponse(0, new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x01e8, code lost:
        if (3007 == r15) goto L_0x01f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x01ea, code lost:
        r8.mRetryCount = 0;
        com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance().removeSession(r10, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x01f3, code lost:
        if (r1 == null) goto L_0x01f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x01f5, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x01f8, code lost:
        r11.setErrorCode(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x01ff, code lost:
        if (r11.needUpload() == false) goto L_0x0214;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0201, code lost:
        r11.setObjectName(r10.mCardType);
        r11.setCoreOperation(r24.getDescription());
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r22).uploadUserExceptionLog(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0214, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0215, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x0217, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0219, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0260, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0264, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x0268, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x026b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x026c, code lost:
        r3 = com.miui.tsmclient.model.ErrorCode.DATA_NOT_READY;
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x026f, code lost:
        r15 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0277, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0278, code lost:
        r3 = com.miui.tsmclient.model.ErrorCode.DATA_NOT_READY;
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x027b, code lost:
        r15 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x02b5, code lost:
        if (r11.needUpload() == false) goto L_0x0387;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x02b7, code lost:
        r11.setObjectName(r10.mCardType);
        r11.setCoreOperation(r24.getDescription());
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r22).uploadUserExceptionLog(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x02ff, code lost:
        if (r11.needUpload() != false) goto L_0x02b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x032d, code lost:
        if (r11.needUpload() == false) goto L_0x0387;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0080, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0081, code lost:
        r15 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0083, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0084, code lost:
        r15 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0086, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0087, code lost:
        r15 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0089, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008a, code lost:
        r15 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00be, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00c2, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00c3, code lost:
        r15 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00c7, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00c8, code lost:
        r15 = r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00b6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x0260 A[ExcHandler: InterruptedException (e java.lang.InterruptedException), PHI: r1 r3 r14 
      PHI: (r1v25 com.tsmclient.smartcard.terminal.IScTerminal) = (r1v26 com.tsmclient.smartcard.terminal.IScTerminal), (r1v26 com.tsmclient.smartcard.terminal.IScTerminal), (r1v19 com.tsmclient.smartcard.terminal.IScTerminal), (r1v19 com.tsmclient.smartcard.terminal.IScTerminal), (r1v19 com.tsmclient.smartcard.terminal.IScTerminal), (r1v19 com.tsmclient.smartcard.terminal.IScTerminal) binds: [B:170:0x0222, B:171:?, B:167:0x021b, B:168:?, B:154:0x01e1, B:155:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r3v29 int) = (r3v30 int), (r3v30 int), (r3v32 int), (r3v32 int), (r3v39 int), (r3v39 int) binds: [B:170:0x0222, B:171:?, B:167:0x021b, B:168:?, B:154:0x01e1, B:155:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r14v23 int) = (r14v24 int), (r14v24 int), (r14v25 int), (r14v25 int), (r14v32 int), (r14v32 int) binds: [B:170:0x0222, B:171:?, B:167:0x021b, B:168:?, B:154:0x01e1, B:155:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:170:0x0222] */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x0264 A[ExcHandler: IOException (e java.io.IOException), PHI: r1 r3 r14 
      PHI: (r1v24 com.tsmclient.smartcard.terminal.IScTerminal) = (r1v26 com.tsmclient.smartcard.terminal.IScTerminal), (r1v26 com.tsmclient.smartcard.terminal.IScTerminal), (r1v19 com.tsmclient.smartcard.terminal.IScTerminal), (r1v19 com.tsmclient.smartcard.terminal.IScTerminal), (r1v19 com.tsmclient.smartcard.terminal.IScTerminal), (r1v19 com.tsmclient.smartcard.terminal.IScTerminal) binds: [B:170:0x0222, B:171:?, B:167:0x021b, B:168:?, B:154:0x01e1, B:155:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r3v28 int) = (r3v30 int), (r3v30 int), (r3v32 int), (r3v32 int), (r3v39 int), (r3v39 int) binds: [B:170:0x0222, B:171:?, B:167:0x021b, B:168:?, B:154:0x01e1, B:155:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r14v22 int) = (r14v24 int), (r14v24 int), (r14v25 int), (r14v25 int), (r14v32 int), (r14v32 int) binds: [B:170:0x0222, B:171:?, B:167:0x021b, B:168:?, B:154:0x01e1, B:155:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:154:0x01e1] */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x0268 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:23:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x026b A[ExcHandler: InterruptedException (e java.lang.InterruptedException), Splitter:B:23:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x0277 A[ExcHandler: IOException (e java.io.IOException), Splitter:B:23:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x02ab  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x02ea  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x0323  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0364  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x0372  */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x0397  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x03a2  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x03ae  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0080 A[ExcHandler: InterruptedException (e java.lang.InterruptedException), Splitter:B:26:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0086 A[ExcHandler: IOException (e java.io.IOException), Splitter:B:26:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00be A[ExcHandler: all (th java.lang.Throwable), Splitter:B:45:0x00a8] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00c2 A[ExcHandler: SeiTSMApiException (e com.miui.tsmclient.seitsm.Exception.SeiTSMApiException), Splitter:B:45:0x00a8] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00c7 A[ExcHandler: NfcEeIOException (e com.tsmclient.smartcard.exception.NfcEeIOException), Splitter:B:45:0x00a8] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:218:0x02d8=Splitter:B:218:0x02d8, B:244:0x033a=Splitter:B:244:0x033a} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.miui.tsmclient.model.BaseResponse pullPersoData(android.content.Context r22, com.miui.tsmclient.entity.CardInfo r23, com.miui.tsmclient.model.mitsm.PullDataOperationType r24) {
        /*
            r21 = this;
            r8 = r21
            r9 = r22
            r10 = r23
            com.miui.tsmclient.entity.UserExceptionLogInfo r11 = new com.miui.tsmclient.entity.UserExceptionLogInfo
            r11.<init>()
            com.miui.tsmclient.model.mitsm.TSMSessionManager$BusinessType r12 = r24.getBusinessType()
            java.lang.String r13 = ""
            r15 = 0
            r16 = -2
            r7 = 3007(0xbbf, float:4.214E-42)
            r6 = 0
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmSessionInfo r0 = r8.getSession(r9, r10, r12)     // Catch:{ NfcEeIOException -> 0x0334, IOException -> 0x0306, SeiTSMApiException -> 0x02d2, InterruptedException -> 0x028d, all -> 0x0285 }
            if (r0 == 0) goto L_0x0044
            java.lang.String r1 = r0.getSessionId()     // Catch:{ NfcEeIOException -> 0x003d, IOException -> 0x0037, SeiTSMApiException -> 0x0030, InterruptedException -> 0x002a, all -> 0x0022 }
            goto L_0x0046
        L_0x0022:
            r0 = move-exception
            r1 = r15
            r2 = -2
        L_0x0025:
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            goto L_0x0395
        L_0x002a:
            r0 = move-exception
        L_0x002b:
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            goto L_0x0292
        L_0x0030:
            r0 = move-exception
            r2 = -2
        L_0x0032:
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            goto L_0x02d8
        L_0x0037:
            r0 = move-exception
        L_0x0038:
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            goto L_0x030b
        L_0x003d:
            r0 = move-exception
            r2 = -2
        L_0x003f:
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            goto L_0x033a
        L_0x0044:
            java.lang.String r1 = ""
        L_0x0046:
            r11.setSessionId(r1)     // Catch:{ NfcEeIOException -> 0x0334, IOException -> 0x0306, SeiTSMApiException -> 0x02d2, InterruptedException -> 0x028d, all -> 0x0285 }
            r1 = r15
            r2 = -2
        L_0x004b:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            r3.<init>()     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            java.lang.String r4 = "AsyncMiTSMClient: pullPersoData, retryCount: "
            r3.append(r4)     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            int r4 = r8.mRetryCount     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            r3.append(r4)     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            java.lang.String r4 = ", cardType: "
            r3.append(r4)     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            java.lang.String r4 = r10.mCardType     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            r3.append(r4)     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            java.lang.String r3 = r3.toString()     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            com.miui.tsmclient.util.LogUtils.d(r3)     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            java.lang.String r3 = "VSIM"
            java.lang.String r4 = r10.mCardType     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            boolean r3 = r3.equals(r4)     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            if (r3 == 0) goto L_0x008c
            com.miui.tsmclient.seitsm.SeiTsmAuthManager r3 = r8.mSeiTsmAuthManager     // Catch:{ NfcEeIOException -> 0x0089, IOException -> 0x0086, SeiTSMApiException -> 0x0083, InterruptedException -> 0x0080, all -> 0x0268 }
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmAPDUCommand r3 = r3.pullPersonData(r9, r0, r10, r15)     // Catch:{ NfcEeIOException -> 0x0089, IOException -> 0x0086, SeiTSMApiException -> 0x0083, InterruptedException -> 0x0080, all -> 0x0268 }
            r5 = r24
        L_0x007d:
            r17 = r3
            goto L_0x0095
        L_0x0080:
            r0 = move-exception
            r15 = r1
            goto L_0x002b
        L_0x0083:
            r0 = move-exception
            r15 = r1
            goto L_0x0032
        L_0x0086:
            r0 = move-exception
            r15 = r1
            goto L_0x0038
        L_0x0089:
            r0 = move-exception
            r15 = r1
            goto L_0x003f
        L_0x008c:
            com.miui.tsmclient.seitsm.SeiTsmAuthManager r3 = r8.mSeiTsmAuthManager     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            r5 = r24
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmAPDUCommand r3 = r3.pullBusCardPersoData(r9, r0, r5)     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            goto L_0x007d
        L_0x0095:
            if (r17 != 0) goto L_0x009c
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            goto L_0x0222
        L_0x009c:
            int r3 = r17.getResult()     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            int r4 = com.miui.tsmclient.model.mitsm.MiTsmErrorCode.format(r3)     // Catch:{ NfcEeIOException -> 0x027e, IOException -> 0x0277, SeiTSMApiException -> 0x0271, InterruptedException -> 0x026b, all -> 0x0268 }
            if (r7 != r4) goto L_0x00cc
            java.lang.String r2 = "pullCardPersoData, but data not ready."
            com.miui.tsmclient.util.LogUtils.d(r2)     // Catch:{ NfcEeIOException -> 0x00c7, IOException -> 0x0086, SeiTSMApiException -> 0x00c2, InterruptedException -> 0x0080, all -> 0x00be }
            r2 = 2000(0x7d0, double:9.88E-321)
            java.lang.Thread.sleep(r2)     // Catch:{ InterruptedException -> 0x00b6, NfcEeIOException -> 0x00c7, IOException -> 0x0086, SeiTSMApiException -> 0x00c2, all -> 0x00be }
        L_0x00b0:
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            goto L_0x021b
        L_0x00b6:
            java.lang.Thread r2 = java.lang.Thread.currentThread()     // Catch:{ NfcEeIOException -> 0x00c7, IOException -> 0x0086, SeiTSMApiException -> 0x00c2, InterruptedException -> 0x0080, all -> 0x00be }
            r2.interrupt()     // Catch:{ NfcEeIOException -> 0x00c7, IOException -> 0x0086, SeiTSMApiException -> 0x00c2, InterruptedException -> 0x0080, all -> 0x00be }
            goto L_0x00b0
        L_0x00be:
            r0 = move-exception
        L_0x00bf:
            r2 = r4
            goto L_0x0025
        L_0x00c2:
            r0 = move-exception
            r15 = r1
        L_0x00c4:
            r2 = r4
            goto L_0x0032
        L_0x00c7:
            r0 = move-exception
            r15 = r1
        L_0x00c9:
            r2 = r4
            goto L_0x003f
        L_0x00cc:
            if (r4 != 0) goto L_0x01db
            java.util.List r2 = r17.getApdusList()     // Catch:{ NfcEeIOException -> 0x01d3, IOException -> 0x0277, SeiTSMApiException -> 0x01cb, InterruptedException -> 0x026b, all -> 0x01c3 }
            if (r2 == 0) goto L_0x01db
            java.util.List r2 = r17.getApdusList()     // Catch:{ NfcEeIOException -> 0x01d3, IOException -> 0x0277, SeiTSMApiException -> 0x01cb, InterruptedException -> 0x026b, all -> 0x01c3 }
            boolean r2 = r2.isEmpty()     // Catch:{ NfcEeIOException -> 0x01d3, IOException -> 0x0277, SeiTSMApiException -> 0x01cb, InterruptedException -> 0x026b, all -> 0x01c3 }
            if (r2 != 0) goto L_0x01db
            if (r1 != 0) goto L_0x00fb
            com.tsmclient.smartcard.terminal.IScTerminal r2 = r23.getTerminal()     // Catch:{ NfcEeIOException -> 0x00c7, IOException -> 0x0086, SeiTSMApiException -> 0x00c2, InterruptedException -> 0x0080, all -> 0x00be }
            r2.open()     // Catch:{ NfcEeIOException -> 0x00f8, IOException -> 0x00f4, SeiTSMApiException -> 0x00f1, InterruptedException -> 0x00ed, all -> 0x00ea }
            r18 = r2
            goto L_0x00fd
        L_0x00ea:
            r0 = move-exception
            r1 = r2
            goto L_0x00bf
        L_0x00ed:
            r0 = move-exception
            r15 = r2
            goto L_0x002b
        L_0x00f1:
            r0 = move-exception
            r15 = r2
            goto L_0x00c4
        L_0x00f4:
            r0 = move-exception
            r15 = r2
            goto L_0x0038
        L_0x00f8:
            r0 = move-exception
            r15 = r2
            goto L_0x00c9
        L_0x00fb:
            r18 = r1
        L_0x00fd:
            com.miui.tsmclient.seitsm.TsmRpcModels$SeOperationType r19 = r24.getSeOperationType()     // Catch:{ NfcEeIOException -> 0x01b9, IOException -> 0x01b1, SeiTSMApiException -> 0x01a7, InterruptedException -> 0x019f, all -> 0x0195 }
            r20 = 0
            r1 = r21
            r2 = r22
            r3 = r18
            r15 = r4
            r4 = r0
            r5 = r17
            r14 = 0
            r6 = r19
            r7 = r20
            com.miui.tsmclient.model.BaseResponse r1 = r1.executeCapdu(r2, r3, r4, r5, r6, r7)     // Catch:{ NfcEeIOException -> 0x0191, IOException -> 0x018d, SeiTSMApiException -> 0x0189, InterruptedException -> 0x0185, all -> 0x0181 }
            if (r1 == 0) goto L_0x014b
            int r2 = r1.mResultCode     // Catch:{ NfcEeIOException -> 0x0143, IOException -> 0x013c, SeiTSMApiException -> 0x0134, InterruptedException -> 0x012d, all -> 0x0125 }
            if (r2 != 0) goto L_0x014b
            r8.mRetryCount = r14     // Catch:{ NfcEeIOException -> 0x0143, IOException -> 0x013c, SeiTSMApiException -> 0x0134, InterruptedException -> 0x012d, all -> 0x0125 }
            r2 = r15
            r1 = r18
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x0222
        L_0x0125:
            r0 = move-exception
            r2 = r15
            r1 = r18
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x0395
        L_0x012d:
            r0 = move-exception
            r15 = r18
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x0292
        L_0x0134:
            r0 = move-exception
            r2 = r15
            r15 = r18
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x02d8
        L_0x013c:
            r0 = move-exception
            r15 = r18
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x030b
        L_0x0143:
            r0 = move-exception
            r2 = r15
            r15 = r18
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x033a
        L_0x014b:
            if (r1 != 0) goto L_0x0151
            r0 = -2
        L_0x014e:
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x0154
        L_0x0151:
            int r0 = r1.mResultCode     // Catch:{ NfcEeIOException -> 0x0191, IOException -> 0x018d, SeiTSMApiException -> 0x0189, InterruptedException -> 0x0185, all -> 0x0181 }
            goto L_0x014e
        L_0x0154:
            if (r3 == r0) goto L_0x015f
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r2 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r2.removeSession(r10, r12)
        L_0x015f:
            if (r18 == 0) goto L_0x0164
            r18.close()
        L_0x0164:
            r11.setErrorCode(r0)
            boolean r0 = r11.needUpload()
            if (r0 == 0) goto L_0x0180
            java.lang.String r0 = r10.mCardType
            r11.setObjectName(r0)
            java.lang.String r0 = r24.getDescription()
            r11.setCoreOperation(r0)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r0 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r22)
            r0.uploadUserExceptionLog(r11)
        L_0x0180:
            return r1
        L_0x0181:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x019a
        L_0x0185:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x01a3
        L_0x0189:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x01ac
        L_0x018d:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x01b5
        L_0x0191:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            goto L_0x01be
        L_0x0195:
            r0 = move-exception
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x019a:
            r2 = r15
            r1 = r18
            goto L_0x0395
        L_0x019f:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x01a3:
            r15 = r18
            goto L_0x0292
        L_0x01a7:
            r0 = move-exception
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x01ac:
            r2 = r15
            r15 = r18
            goto L_0x02d8
        L_0x01b1:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x01b5:
            r15 = r18
            goto L_0x030b
        L_0x01b9:
            r0 = move-exception
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x01be:
            r2 = r15
            r15 = r18
            goto L_0x033a
        L_0x01c3:
            r0 = move-exception
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x01c8:
            r2 = r15
            goto L_0x0395
        L_0x01cb:
            r0 = move-exception
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x01d0:
            r2 = r15
            goto L_0x0275
        L_0x01d3:
            r0 = move-exception
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x01d8:
            r2 = r15
            goto L_0x0282
        L_0x01db:
            r15 = r4
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            if (r15 != 0) goto L_0x021b
            com.miui.tsmclient.model.BaseResponse r0 = new com.miui.tsmclient.model.BaseResponse     // Catch:{ NfcEeIOException -> 0x0219, IOException -> 0x0264, SeiTSMApiException -> 0x0217, InterruptedException -> 0x0260, all -> 0x0215 }
            java.lang.Object[] r2 = new java.lang.Object[r14]     // Catch:{ NfcEeIOException -> 0x0219, IOException -> 0x0264, SeiTSMApiException -> 0x0217, InterruptedException -> 0x0260, all -> 0x0215 }
            r0.<init>(r14, r2)     // Catch:{ NfcEeIOException -> 0x0219, IOException -> 0x0264, SeiTSMApiException -> 0x0217, InterruptedException -> 0x0260, all -> 0x0215 }
            if (r3 == r15) goto L_0x01f3
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r2 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r2.removeSession(r10, r12)
        L_0x01f3:
            if (r1 == 0) goto L_0x01f8
            r1.close()
        L_0x01f8:
            r11.setErrorCode(r15)
            boolean r1 = r11.needUpload()
            if (r1 == 0) goto L_0x0214
            java.lang.String r1 = r10.mCardType
            r11.setObjectName(r1)
            java.lang.String r1 = r24.getDescription()
            r11.setCoreOperation(r1)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r1 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r22)
            r1.uploadUserExceptionLog(r11)
        L_0x0214:
            return r0
        L_0x0215:
            r0 = move-exception
            goto L_0x01c8
        L_0x0217:
            r0 = move-exception
            goto L_0x01d0
        L_0x0219:
            r0 = move-exception
            goto L_0x01d8
        L_0x021b:
            int r2 = r8.mRetryCount     // Catch:{ NfcEeIOException -> 0x0219, IOException -> 0x0264, SeiTSMApiException -> 0x0217, InterruptedException -> 0x0260, all -> 0x0215 }
            int r2 = r2 + 1
            r8.mRetryCount = r2     // Catch:{ NfcEeIOException -> 0x0219, IOException -> 0x0264, SeiTSMApiException -> 0x0217, InterruptedException -> 0x0260, all -> 0x0215 }
            r2 = r15
        L_0x0222:
            int r4 = r8.mRetryCount     // Catch:{ NfcEeIOException -> 0x0266, IOException -> 0x0264, SeiTSMApiException -> 0x0262, InterruptedException -> 0x0260, all -> 0x025d }
            r5 = 10
            if (r4 < r5) goto L_0x0257
            if (r3 == r2) goto L_0x0233
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r10, r12)
        L_0x0233:
            if (r1 == 0) goto L_0x0238
            r1.close()
        L_0x0238:
            r11.setErrorCode(r2)
            boolean r0 = r11.needUpload()
            if (r0 == 0) goto L_0x0254
            java.lang.String r0 = r10.mCardType
            r11.setObjectName(r0)
            java.lang.String r0 = r24.getDescription()
            r11.setCoreOperation(r0)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r0 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r22)
            r0.uploadUserExceptionLog(r11)
        L_0x0254:
            r1 = r2
            goto L_0x0387
        L_0x0257:
            r6 = 0
            r7 = 3007(0xbbf, float:4.214E-42)
            r15 = 0
            goto L_0x004b
        L_0x025d:
            r0 = move-exception
            goto L_0x0395
        L_0x0260:
            r0 = move-exception
            goto L_0x026f
        L_0x0262:
            r0 = move-exception
            goto L_0x0275
        L_0x0264:
            r0 = move-exception
            goto L_0x027b
        L_0x0266:
            r0 = move-exception
            goto L_0x0282
        L_0x0268:
            r0 = move-exception
            goto L_0x0025
        L_0x026b:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x026f:
            r15 = r1
            goto L_0x0292
        L_0x0271:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x0275:
            r15 = r1
            goto L_0x02d8
        L_0x0277:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x027b:
            r15 = r1
            goto L_0x030b
        L_0x027e:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
        L_0x0282:
            r15 = r1
            goto L_0x033a
        L_0x0285:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            r1 = 0
            r2 = -2
            goto L_0x0395
        L_0x028d:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            r15 = 0
        L_0x0292:
            r1 = 11
            java.lang.String r2 = r0.getMessage()     // Catch:{ all -> 0x02cc }
            r11.setExtra(r2)     // Catch:{ all -> 0x02cc }
            java.lang.String r2 = "AsyncMiTSMClient: pullPersoData is interrupted."
            com.miui.tsmclient.util.LogUtils.e(r2, r0)     // Catch:{ all -> 0x02cc }
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r10, r12)
            if (r15 == 0) goto L_0x02ae
            r15.close()
        L_0x02ae:
            r11.setErrorCode(r1)
            boolean r0 = r11.needUpload()
            if (r0 == 0) goto L_0x0387
        L_0x02b7:
            java.lang.String r0 = r10.mCardType
            r11.setObjectName(r0)
            java.lang.String r0 = r24.getDescription()
            r11.setCoreOperation(r0)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r0 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r22)
            r0.uploadUserExceptionLog(r11)
            goto L_0x0387
        L_0x02cc:
            r0 = move-exception
            r1 = r15
            r2 = 11
            goto L_0x0395
        L_0x02d2:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            r2 = -2
            r15 = 0
        L_0x02d8:
            int r1 = r0.getErrorCode()     // Catch:{ all -> 0x0393 }
            java.lang.String r13 = r0.getMessage()     // Catch:{ all -> 0x0302 }
            r11.setExtra(r13)     // Catch:{ all -> 0x0302 }
            java.lang.String r2 = "AsyncMiTSMClient: pullPersoData failed with an tsm api exception."
            com.miui.tsmclient.util.LogUtils.e(r2, r0)     // Catch:{ all -> 0x0302 }
            if (r3 == r1) goto L_0x02f3
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r10, r12)
        L_0x02f3:
            if (r15 == 0) goto L_0x02f8
            r15.close()
        L_0x02f8:
            r11.setErrorCode(r1)
            boolean r0 = r11.needUpload()
            if (r0 == 0) goto L_0x0387
            goto L_0x02b7
        L_0x0302:
            r0 = move-exception
            r2 = r1
            goto L_0x0394
        L_0x0306:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            r15 = 0
        L_0x030b:
            r1 = 2
            java.lang.String r2 = r0.getMessage()     // Catch:{ all -> 0x0330 }
            r11.setExtra(r2)     // Catch:{ all -> 0x0330 }
            java.lang.String r2 = "AsyncMiTSMClient: pullPersoData failed with an io exception."
            com.miui.tsmclient.util.LogUtils.e(r2, r0)     // Catch:{ all -> 0x0330 }
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r10, r12)
            if (r15 == 0) goto L_0x0326
            r15.close()
        L_0x0326:
            r11.setErrorCode(r1)
            boolean r0 = r11.needUpload()
            if (r0 == 0) goto L_0x0387
            goto L_0x02b7
        L_0x0330:
            r0 = move-exception
            r1 = r15
            r2 = 2
            goto L_0x0395
        L_0x0334:
            r0 = move-exception
            r3 = 3007(0xbbf, float:4.214E-42)
            r14 = 0
            r2 = -2
            r15 = 0
        L_0x033a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0393 }
            r1.<init>()     // Catch:{ all -> 0x0393 }
            java.lang.String r4 = "AsyncMiTSMClient: pullPersoData failed with an nfc exception. errorCode:"
            r1.append(r4)     // Catch:{ all -> 0x0393 }
            int r4 = r0.getErrorCode()     // Catch:{ all -> 0x0393 }
            r1.append(r4)     // Catch:{ all -> 0x0393 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0393 }
            com.miui.tsmclient.util.LogUtils.e(r1, r0)     // Catch:{ all -> 0x0393 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x038f }
            r11.setExtra(r0)     // Catch:{ all -> 0x038f }
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r10, r12)
            if (r15 == 0) goto L_0x0367
            r15.close()
        L_0x0367:
            r2 = 10
            r11.setErrorCode(r2)
            boolean r0 = r11.needUpload()
            if (r0 == 0) goto L_0x0385
            java.lang.String r0 = r10.mCardType
            r11.setObjectName(r0)
            java.lang.String r0 = r24.getDescription()
            r11.setCoreOperation(r0)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r0 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r22)
            r0.uploadUserExceptionLog(r11)
        L_0x0385:
            r1 = 10
        L_0x0387:
            com.miui.tsmclient.model.BaseResponse r0 = new com.miui.tsmclient.model.BaseResponse
            java.lang.Object[] r2 = new java.lang.Object[r14]
            r0.<init>(r1, r13, r2)
            return r0
        L_0x038f:
            r0 = move-exception
            r2 = 10
            goto L_0x0394
        L_0x0393:
            r0 = move-exception
        L_0x0394:
            r1 = r15
        L_0x0395:
            if (r3 == r2) goto L_0x03a0
            r8.mRetryCount = r14
            com.miui.tsmclient.model.mitsm.TSMSessionManager r3 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r3.removeSession(r10, r12)
        L_0x03a0:
            if (r1 == 0) goto L_0x03a5
            r1.close()
        L_0x03a5:
            r11.setErrorCode(r2)
            boolean r1 = r11.needUpload()
            if (r1 == 0) goto L_0x03c1
            java.lang.String r1 = r10.mCardType
            r11.setObjectName(r1)
            java.lang.String r1 = r24.getDescription()
            r11.setCoreOperation(r1)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r1 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r22)
            r1.uploadUserExceptionLog(r11)
        L_0x03c1:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.model.mitsm.AsyncMiTSMClient.pullPersoData(android.content.Context, com.miui.tsmclient.entity.CardInfo, com.miui.tsmclient.model.mitsm.PullDataOperationType):com.miui.tsmclient.model.BaseResponse");
    }

    private Bundle getBundle(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean(MiTSMCardClient.EXTRAS_KEY_SESSION_NOT_FINISH, true);
        return bundle;
    }

    private void uploadPhoneNumber(Context context, CardInfo cardInfo, Bundle bundle) {
        long j = bundle.getLong(Constants.EXTRA_VERSION_CONTROL_ID, 0);
        if (!TextUtils.isEmpty(VersionControlHelper.uploadPhoneNumber(context, cardInfo.mCardType, j))) {
            VersionControlHelper.getInstance().confirmUploadPhoneNumber(context, cardInfo.mCardType, j);
        }
    }
}
