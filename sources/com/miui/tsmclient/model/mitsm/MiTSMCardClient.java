package com.miui.tsmclient.model.mitsm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.protobuf.ByteString;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.entity.TransferOutOrderInfo;
import com.miui.tsmclient.model.BaseCardClient;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.mitsm.TSMSessionManager;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.seitsm.SeiTsmAuthManager;
import com.miui.tsmclient.seitsm.TsmRpcModels;
import com.miui.tsmclient.util.Constants;
import com.miui.tsmclient.util.LogUtils;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.terminal.IScTerminal;
import com.tsmclient.smartcard.terminal.response.ScResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class MiTSMCardClient extends BaseCardClient {
    public static final String EXTRAS_KEY_BUSINESS_TYPE = "extras_key_business_type";
    public static final String EXTRAS_KEY_SESSION_NOT_FINISH = "extras_key_session_not_finish";
    public static final String EXTRAS_KEY_SE_OPERATION = "extras_key_se_operation";
    private static final String EXTRA_CARD_TYPE = "card_type";
    protected static AtomicBoolean sNeedSync = new AtomicBoolean(true);
    protected SeiTsmAuthManager mSeiTsmAuthManager = new SeiTsmAuthManager();

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005e, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005f, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0062, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0063, code lost:
        r1 = 11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r11.setExtra(r0.getMessage());
        com.miui.tsmclient.util.LogUtils.e("preTransferOut is interrupted.", r0);
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0078, code lost:
        r11.setErrorCode(11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007f, code lost:
        if (r11.needUpload() != false) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0081, code lost:
        r11.setObjectName(r10.mCardType);
        r11.setCoreOperation("preTransferOut");
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r9).uploadUserExceptionLog(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0093, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0094, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00b2, code lost:
        r11.setObjectName(r10.mCardType);
        r11.setCoreOperation("preTransferOut");
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r9).uploadUserExceptionLog(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c9, code lost:
        r1 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r11.setExtra(r0.getMessage());
        com.miui.tsmclient.util.LogUtils.e("preTransferOut failed with an io exception.", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d6, code lost:
        r11.setErrorCode(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00dd, code lost:
        if (r11.needUpload() != false) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e7, code lost:
        return new com.miui.tsmclient.model.BaseResponse(r1, r6, new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f2, code lost:
        r11.setObjectName(r10.mCardType);
        r11.setCoreOperation("preTransferOut");
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r9).uploadUserExceptionLog(r11);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0062 A[ExcHandler: InterruptedException (r0v10 'e' java.lang.InterruptedException A[CUSTOM_DECLARE]), Splitter:B:4:0x0016] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c8 A[ExcHandler: IOException (r0v4 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:4:0x0016] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00f2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.miui.tsmclient.model.BaseResponse preTransferOut(android.content.Context r9, com.miui.tsmclient.entity.CardInfo r10, android.os.Bundle r11) {
        /*
            r8 = this;
            java.lang.String r0 = "preTransferOut called."
            com.miui.tsmclient.util.LogUtils.d(r0)
            if (r11 != 0) goto L_0x000c
            android.os.Bundle r11 = new android.os.Bundle
            r11.<init>()
        L_0x000c:
            r5 = r11
            com.miui.tsmclient.entity.UserExceptionLogInfo r11 = new com.miui.tsmclient.entity.UserExceptionLogInfo
            r11.<init>()
            java.lang.String r6 = ""
            r0 = 0
            r7 = 0
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmSessionInfo r3 = r8.getSession(r9, r10, r0)     // Catch:{ IOException -> 0x00c8, SeiTSMApiException -> 0x0093, InterruptedException -> 0x0062, all -> 0x005e }
            if (r3 == 0) goto L_0x0021
            java.lang.String r0 = r3.getSessionId()     // Catch:{ IOException -> 0x00c8, SeiTSMApiException -> 0x0093, InterruptedException -> 0x0062, all -> 0x005e }
            goto L_0x0023
        L_0x0021:
            java.lang.String r0 = ""
        L_0x0023:
            r11.setSessionId(r0)     // Catch:{ IOException -> 0x00c8, SeiTSMApiException -> 0x0093, InterruptedException -> 0x0062, all -> 0x005e }
            com.miui.tsmclient.seitsm.TsmRpcModels$SeOperationType r4 = com.miui.tsmclient.seitsm.TsmRpcModels.SeOperationType.CHECK_SHIFT_OUT     // Catch:{ IOException -> 0x00c8, SeiTSMApiException -> 0x0093, InterruptedException -> 0x0062, all -> 0x005e }
            r0 = r8
            r1 = r9
            r2 = r10
            com.miui.tsmclient.model.BaseResponse r0 = r0.startSeOperation(r1, r2, r3, r4, r5)     // Catch:{ IOException -> 0x00c8, SeiTSMApiException -> 0x0093, InterruptedException -> 0x0062, all -> 0x005e }
            boolean r1 = r0.isSuccess()     // Catch:{ IOException -> 0x00c8, SeiTSMApiException -> 0x0093, InterruptedException -> 0x0062, all -> 0x005e }
            if (r1 != 0) goto L_0x0042
            int r1 = r0.mResultCode     // Catch:{ IOException -> 0x00c8, SeiTSMApiException -> 0x0093, InterruptedException -> 0x0062, all -> 0x005e }
            java.lang.String r2 = r0.mMsg     // Catch:{ IOException -> 0x00c8, SeiTSMApiException -> 0x0040, InterruptedException -> 0x0062 }
            r11.setErrorDesc(r2)     // Catch:{ IOException -> 0x00c8, SeiTSMApiException -> 0x0040, InterruptedException -> 0x0062 }
            r11.setErrorCode(r1)     // Catch:{ IOException -> 0x00c8, SeiTSMApiException -> 0x0040, InterruptedException -> 0x0062 }
            goto L_0x0043
        L_0x0040:
            r0 = move-exception
            goto L_0x0095
        L_0x0042:
            r1 = 0
        L_0x0043:
            r11.setErrorCode(r1)
            boolean r1 = r11.needUpload()
            if (r1 == 0) goto L_0x005d
            java.lang.String r10 = r10.mCardType
            r11.setObjectName(r10)
            java.lang.String r10 = "preTransferOut"
            r11.setCoreOperation(r10)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r9 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r9)
            r9.uploadUserExceptionLog(r11)
        L_0x005d:
            return r0
        L_0x005e:
            r0 = move-exception
            r1 = 0
            goto L_0x00e9
        L_0x0062:
            r0 = move-exception
            r1 = 11
            java.lang.String r2 = r0.getMessage()     // Catch:{ all -> 0x00e8 }
            r11.setExtra(r2)     // Catch:{ all -> 0x00e8 }
            java.lang.String r2 = "preTransferOut is interrupted."
            com.miui.tsmclient.util.LogUtils.e(r2, r0)     // Catch:{ all -> 0x00e8 }
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x00e8 }
            r0.interrupt()     // Catch:{ all -> 0x00e8 }
            r11.setErrorCode(r1)
            boolean r0 = r11.needUpload()
            if (r0 == 0) goto L_0x00e0
        L_0x0081:
            java.lang.String r10 = r10.mCardType
            r11.setObjectName(r10)
            java.lang.String r10 = "preTransferOut"
            r11.setCoreOperation(r10)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r9 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r9)
            r9.uploadUserExceptionLog(r11)
            goto L_0x00e0
        L_0x0093:
            r0 = move-exception
            r1 = 0
        L_0x0095:
            int r2 = r0.getErrorCode()     // Catch:{ all -> 0x00e8 }
            java.lang.String r6 = r0.getMessage()     // Catch:{ all -> 0x00c5 }
            java.lang.String r1 = r0.getMessage()     // Catch:{ all -> 0x00c5 }
            r11.setExtra(r1)     // Catch:{ all -> 0x00c5 }
            java.lang.String r1 = "preTransferOut failed with an SeiTSMApiException."
            com.miui.tsmclient.util.LogUtils.e(r1, r0)     // Catch:{ all -> 0x00c5 }
            r11.setErrorCode(r2)
            boolean r0 = r11.needUpload()
            if (r0 == 0) goto L_0x00c3
            java.lang.String r10 = r10.mCardType
            r11.setObjectName(r10)
            java.lang.String r10 = "preTransferOut"
            r11.setCoreOperation(r10)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r9 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r9)
            r9.uploadUserExceptionLog(r11)
        L_0x00c3:
            r1 = r2
            goto L_0x00e0
        L_0x00c5:
            r0 = move-exception
            r1 = r2
            goto L_0x00e9
        L_0x00c8:
            r0 = move-exception
            r1 = 2
            java.lang.String r2 = r0.getMessage()     // Catch:{ all -> 0x00e8 }
            r11.setExtra(r2)     // Catch:{ all -> 0x00e8 }
            java.lang.String r2 = "preTransferOut failed with an io exception."
            com.miui.tsmclient.util.LogUtils.e(r2, r0)     // Catch:{ all -> 0x00e8 }
            r11.setErrorCode(r1)
            boolean r0 = r11.needUpload()
            if (r0 == 0) goto L_0x00e0
            goto L_0x0081
        L_0x00e0:
            com.miui.tsmclient.model.BaseResponse r9 = new com.miui.tsmclient.model.BaseResponse
            java.lang.Object[] r10 = new java.lang.Object[r7]
            r9.<init>(r1, r6, r10)
            return r9
        L_0x00e8:
            r0 = move-exception
        L_0x00e9:
            r11.setErrorCode(r1)
            boolean r1 = r11.needUpload()
            if (r1 == 0) goto L_0x0103
            java.lang.String r10 = r10.mCardType
            r11.setObjectName(r10)
            java.lang.String r10 = "preTransferOut"
            r11.setCoreOperation(r10)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r9 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r9)
            r9.uploadUserExceptionLog(r11)
        L_0x0103:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.model.mitsm.MiTSMCardClient.preTransferOut(android.content.Context, com.miui.tsmclient.entity.CardInfo, android.os.Bundle):com.miui.tsmclient.model.BaseResponse");
    }

    public BaseResponse transferOut(Context context, CardInfo cardInfo, Bundle bundle) {
        TransferOutOrderInfo unfinishTransferOutInfo;
        LogUtils.d("transferOut called.");
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(EXTRAS_KEY_SE_OPERATION, 11);
        if ((cardInfo instanceof PayableCardInfo) && (unfinishTransferOutInfo = ((PayableCardInfo) cardInfo).getUnfinishTransferOutInfo()) != null) {
            String transferOutToken = unfinishTransferOutInfo.getTransferOutToken();
            if (!TextUtils.isEmpty(transferOutToken)) {
                bundle.putString("authentication_code", transferOutToken);
            }
        }
        bundle.putSerializable(EXTRAS_KEY_BUSINESS_TYPE, TSMSessionManager.BusinessType.TRANSFER_OUT);
        return delete(context, cardInfo, bundle);
    }

    public BaseResponse returnCard(Context context, CardInfo cardInfo, Bundle bundle) {
        LogUtils.d("returnCard called.");
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(EXTRAS_KEY_SE_OPERATION, 23);
        bundle.putSerializable(EXTRAS_KEY_BUSINESS_TYPE, TSMSessionManager.BusinessType.OUT_RETURN);
        return delete(context, cardInfo, bundle);
    }

    public BaseResponse transferIn(Context context, CardInfo cardInfo, Bundle bundle) {
        LogUtils.d("transferIn called.");
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(EXTRAS_KEY_SE_OPERATION, 12);
        bundle.putSerializable(EXTRAS_KEY_BUSINESS_TYPE, TSMSessionManager.BusinessType.TRANSFER_IN);
        return issue(context, cardInfo, bundle);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x022c, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0256, code lost:
        com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance().removeSession(r9, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0266, code lost:
        r13.setObjectName(r9.mCardType);
        r13.setCoreOperation("issue");
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r19).uploadUserExceptionLog(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0293, code lost:
        com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance().removeSession(r9, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02a3, code lost:
        r13.setObjectName(r9.mCardType);
        r13.setCoreOperation("issue");
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r19).uploadUserExceptionLog(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0149, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x014c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x014d, code lost:
        r1 = 11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        r13.setExtra(r0.getMessage());
        com.miui.tsmclient.util.LogUtils.e("issue is interrupted.", r0);
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0168, code lost:
        if (r10.getBoolean(EXTRAS_KEY_SESSION_NOT_FINISH) == false) goto L_0x016a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x016a, code lost:
        com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance().removeSession(r9, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0171, code lost:
        r13.setErrorCode(11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0178, code lost:
        if (r13.needUpload() != false) goto L_0x017a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x017a, code lost:
        r13.setObjectName(r9.mCardType);
        r13.setCoreOperation("issue");
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r19).uploadUserExceptionLog(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x018b, code lost:
        r0 = new java.lang.StringBuilder();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0197, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0198, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01b1, code lost:
        com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance().removeSession(r9, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01c1, code lost:
        r13.setObjectName(r9.mCardType);
        r13.setCoreOperation("issue");
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r19).uploadUserExceptionLog(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01dc, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01dd, code lost:
        r1 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:?, code lost:
        r13.setExtra(r0.getMessage());
        com.miui.tsmclient.util.LogUtils.e("issue failed with an io exception.", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01f0, code lost:
        if (r10.getBoolean(EXTRAS_KEY_SESSION_NOT_FINISH) == false) goto L_0x01f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01f2, code lost:
        com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance().removeSession(r9, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01f9, code lost:
        r13.setErrorCode(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0200, code lost:
        if (r13.needUpload() != false) goto L_0x0202;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0202, code lost:
        r13.setObjectName(r9.mCardType);
        r13.setCoreOperation("issue");
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r19).uploadUserExceptionLog(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0213, code lost:
        r0 = new java.lang.StringBuilder();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0218, code lost:
        r0.append("issue finished. resultCode: ");
        r0.append(r1);
        com.miui.tsmclient.util.LogUtils.d(r0.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x022b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0256  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0266  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0293  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x02a3  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a6 A[Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x014c A[ExcHandler: InterruptedException (r0v39 'e' java.lang.InterruptedException A[CUSTOM_DECLARE]), Splitter:B:10:0x0033] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01b1  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01dc A[ExcHandler: IOException (r0v18 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:10:0x0033] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:72:0x0199=Splitter:B:72:0x0199, B:101:0x022d=Splitter:B:101:0x022d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.miui.tsmclient.model.BaseResponse issue(android.content.Context r19, com.miui.tsmclient.entity.CardInfo r20, android.os.Bundle r21) {
        /*
            r18 = this;
            r7 = r18
            r8 = r19
            r9 = r20
            r0 = r21
            if (r0 != 0) goto L_0x0011
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            r10 = r1
            goto L_0x0012
        L_0x0011:
            r10 = r0
        L_0x0012:
            java.lang.String r1 = "out_operation"
            boolean r1 = r10.getBoolean(r1)
            java.lang.String r2 = "extras_key_business_type"
            java.io.Serializable r2 = r10.getSerializable(r2)
            com.miui.tsmclient.model.mitsm.TSMSessionManager$BusinessType r2 = (com.miui.tsmclient.model.mitsm.TSMSessionManager.BusinessType) r2
            if (r2 != 0) goto L_0x0029
            if (r1 == 0) goto L_0x0027
            com.miui.tsmclient.model.mitsm.TSMSessionManager$BusinessType r2 = com.miui.tsmclient.model.mitsm.TSMSessionManager.BusinessType.OUT_INSTALL
            goto L_0x0029
        L_0x0027:
            com.miui.tsmclient.model.mitsm.TSMSessionManager$BusinessType r2 = com.miui.tsmclient.model.mitsm.TSMSessionManager.BusinessType.INSTALL
        L_0x0029:
            r11 = r2
            java.lang.String r12 = ""
            com.miui.tsmclient.entity.UserExceptionLogInfo r13 = new com.miui.tsmclient.entity.UserExceptionLogInfo
            r13.<init>()
            r2 = 1
            r14 = 0
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmSessionInfo r4 = r7.getSession(r8, r9, r11, r2)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            if (r4 == 0) goto L_0x003e
            java.lang.String r3 = r4.getSessionId()     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            goto L_0x0040
        L_0x003e:
            java.lang.String r3 = ""
        L_0x0040:
            r13.setSessionId(r3)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            java.lang.String r3 = "pre_load"
            boolean r3 = r10.getBoolean(r3)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            java.lang.String r5 = "extras_key_se_operation"
            if (r1 == 0) goto L_0x0050
            r1 = 21
            goto L_0x0051
        L_0x0050:
            r1 = 3
        L_0x0051:
            int r1 = r10.getInt(r5, r1)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            com.miui.tsmclient.seitsm.TsmRpcModels$SeOperationType r1 = com.miui.tsmclient.seitsm.TsmRpcModels.SeOperationType.valueOf((int) r1)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            if (r3 == 0) goto L_0x005d
            com.miui.tsmclient.seitsm.TsmRpcModels$SeOperationType r1 = com.miui.tsmclient.seitsm.TsmRpcModels.SeOperationType.LOAD     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
        L_0x005d:
            r5 = r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            r1.<init>()     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            java.lang.String r3 = "issue called. SeOperationType: "
            r1.append(r3)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            r1.append(r5)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            java.lang.String r1 = r1.toString()     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            com.miui.tsmclient.util.LogUtils.d(r1)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            java.lang.String r1 = "version_control_id"
            r2 = 0
            long r2 = r10.getLong(r1, r2)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            boolean r1 = r7.isIssueOrTransferIn(r5)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            if (r1 == 0) goto L_0x0094
            java.lang.String r1 = r9.mCardType     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            java.lang.String r1 = com.miui.tsmclient.util.VersionControlHelper.uploadPhoneNumber(r8, r1, r2)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            boolean r6 = android.text.TextUtils.isEmpty(r1)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            if (r6 != 0) goto L_0x0094
            java.lang.String r6 = "need_phone_number"
            r10.putString(r6, r1)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            r15 = 1
            goto L_0x0095
        L_0x0094:
            r15 = 0
        L_0x0095:
            r1 = r18
            r16 = r2
            r2 = r19
            r3 = r20
            r6 = r10
            com.miui.tsmclient.model.BaseResponse r1 = r1.startSeOperation(r2, r3, r4, r5, r6)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            int r2 = r1.mResultCode     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            if (r2 == 0) goto L_0x00f6
            int r2 = r1.mResultCode     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            java.lang.String r0 = r1.mMsg     // Catch:{ NfcEeIOException -> 0x00f3, IOException -> 0x01dc, SeiTSMApiException -> 0x00f0, InterruptedException -> 0x014c }
            r13.setErrorDesc(r0)     // Catch:{ NfcEeIOException -> 0x00f3, IOException -> 0x01dc, SeiTSMApiException -> 0x00f0, InterruptedException -> 0x014c }
            r13.setErrorCode(r2)     // Catch:{ NfcEeIOException -> 0x00f3, IOException -> 0x01dc, SeiTSMApiException -> 0x00f0, InterruptedException -> 0x014c }
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x00bf
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r9, r11)
        L_0x00bf:
            r13.setErrorCode(r2)
            boolean r0 = r13.needUpload()
            if (r0 == 0) goto L_0x00d9
            java.lang.String r0 = r9.mCardType
            r13.setObjectName(r0)
            java.lang.String r0 = "issue"
            r13.setCoreOperation(r0)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r0 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r19)
            r0.uploadUserExceptionLog(r13)
        L_0x00d9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "issue finished. resultCode: "
            r0.append(r1)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.miui.tsmclient.util.LogUtils.d(r0)
            r1 = r2
            goto L_0x027d
        L_0x00f0:
            r0 = move-exception
            goto L_0x0199
        L_0x00f3:
            r0 = move-exception
            goto L_0x022d
        L_0x00f6:
            if (r15 == 0) goto L_0x010b
            java.lang.String r2 = "extras_key_session_not_finish"
            boolean r0 = r0.getBoolean(r2)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            if (r0 != 0) goto L_0x010b
            com.miui.tsmclient.util.VersionControlHelper r0 = com.miui.tsmclient.util.VersionControlHelper.getInstance()     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            java.lang.String r2 = r9.mCardType     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
            r3 = r16
            r0.confirmUploadPhoneNumber(r8, r2, r3)     // Catch:{ NfcEeIOException -> 0x022b, IOException -> 0x01dc, SeiTSMApiException -> 0x0197, InterruptedException -> 0x014c, all -> 0x0149 }
        L_0x010b:
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x011a
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r9, r11)
        L_0x011a:
            r13.setErrorCode(r14)
            boolean r0 = r13.needUpload()
            if (r0 == 0) goto L_0x0134
            java.lang.String r0 = r9.mCardType
            r13.setObjectName(r0)
            java.lang.String r0 = "issue"
            r13.setCoreOperation(r0)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r0 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r19)
            r0.uploadUserExceptionLog(r13)
        L_0x0134:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "issue finished. resultCode: "
            r0.append(r2)
            r0.append(r14)
            java.lang.String r0 = r0.toString()
            com.miui.tsmclient.util.LogUtils.d(r0)
            return r1
        L_0x0149:
            r0 = move-exception
            goto L_0x028b
        L_0x014c:
            r0 = move-exception
            r1 = 11
            java.lang.String r2 = r0.getMessage()     // Catch:{ all -> 0x0192 }
            r13.setExtra(r2)     // Catch:{ all -> 0x0192 }
            java.lang.String r2 = "issue is interrupted."
            com.miui.tsmclient.util.LogUtils.e(r2, r0)     // Catch:{ all -> 0x0192 }
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0192 }
            r0.interrupt()     // Catch:{ all -> 0x0192 }
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x0171
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r9, r11)
        L_0x0171:
            r13.setErrorCode(r1)
            boolean r0 = r13.needUpload()
            if (r0 == 0) goto L_0x018b
            java.lang.String r0 = r9.mCardType
            r13.setObjectName(r0)
            java.lang.String r0 = "issue"
            r13.setCoreOperation(r0)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r0 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r19)
            r0.uploadUserExceptionLog(r13)
        L_0x018b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            goto L_0x0218
        L_0x0192:
            r0 = move-exception
            r14 = 11
            goto L_0x028b
        L_0x0197:
            r0 = move-exception
            r2 = 0
        L_0x0199:
            int r1 = r0.getErrorCode()     // Catch:{ all -> 0x0289 }
            java.lang.String r12 = r0.getMessage()     // Catch:{ all -> 0x01d8 }
            r13.setExtra(r12)     // Catch:{ all -> 0x01d8 }
            java.lang.String r2 = "issue failed with an tsmapi exception."
            com.miui.tsmclient.util.LogUtils.e(r2, r0)     // Catch:{ all -> 0x01d8 }
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x01b8
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r9, r11)
        L_0x01b8:
            r13.setErrorCode(r1)
            boolean r0 = r13.needUpload()
            if (r0 == 0) goto L_0x01d2
            java.lang.String r0 = r9.mCardType
            r13.setObjectName(r0)
            java.lang.String r0 = "issue"
            r13.setCoreOperation(r0)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r0 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r19)
            r0.uploadUserExceptionLog(r13)
        L_0x01d2:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            goto L_0x0218
        L_0x01d8:
            r0 = move-exception
            r14 = r1
            goto L_0x028b
        L_0x01dc:
            r0 = move-exception
            r1 = 2
            java.lang.String r2 = r0.getMessage()     // Catch:{ all -> 0x0228 }
            r13.setExtra(r2)     // Catch:{ all -> 0x0228 }
            java.lang.String r2 = "issue failed with an io exception."
            com.miui.tsmclient.util.LogUtils.e(r2, r0)     // Catch:{ all -> 0x0228 }
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x01f9
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r9, r11)
        L_0x01f9:
            r13.setErrorCode(r1)
            boolean r0 = r13.needUpload()
            if (r0 == 0) goto L_0x0213
            java.lang.String r0 = r9.mCardType
            r13.setObjectName(r0)
            java.lang.String r0 = "issue"
            r13.setCoreOperation(r0)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r0 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r19)
            r0.uploadUserExceptionLog(r13)
        L_0x0213:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L_0x0218:
            java.lang.String r2 = "issue finished. resultCode: "
            r0.append(r2)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.miui.tsmclient.util.LogUtils.d(r0)
            goto L_0x027d
        L_0x0228:
            r0 = move-exception
            r14 = 2
            goto L_0x028b
        L_0x022b:
            r0 = move-exception
            r2 = 0
        L_0x022d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0289 }
            r1.<init>()     // Catch:{ all -> 0x0289 }
            java.lang.String r3 = "issue failed with an nfc exception. errorCode:"
            r1.append(r3)     // Catch:{ all -> 0x0289 }
            int r3 = r0.getErrorCode()     // Catch:{ all -> 0x0289 }
            r1.append(r3)     // Catch:{ all -> 0x0289 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0289 }
            com.miui.tsmclient.util.LogUtils.e(r1, r0)     // Catch:{ all -> 0x0289 }
            r1 = 10
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0285 }
            r13.setExtra(r0)     // Catch:{ all -> 0x0285 }
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r0 = r10.getBoolean(r0)
            if (r0 != 0) goto L_0x025d
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r9, r11)
        L_0x025d:
            r13.setErrorCode(r1)
            boolean r0 = r13.needUpload()
            if (r0 == 0) goto L_0x0277
            java.lang.String r0 = r9.mCardType
            r13.setObjectName(r0)
            java.lang.String r0 = "issue"
            r13.setCoreOperation(r0)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r0 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r19)
            r0.uploadUserExceptionLog(r13)
        L_0x0277:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            goto L_0x0218
        L_0x027d:
            com.miui.tsmclient.model.BaseResponse r0 = new com.miui.tsmclient.model.BaseResponse
            java.lang.Object[] r2 = new java.lang.Object[r14]
            r0.<init>(r1, r12, r2)
            return r0
        L_0x0285:
            r0 = move-exception
            r14 = 10
            goto L_0x028b
        L_0x0289:
            r0 = move-exception
            r14 = r2
        L_0x028b:
            java.lang.String r1 = "extras_key_session_not_finish"
            boolean r1 = r10.getBoolean(r1)
            if (r1 != 0) goto L_0x029a
            com.miui.tsmclient.model.mitsm.TSMSessionManager r1 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r1.removeSession(r9, r11)
        L_0x029a:
            r13.setErrorCode(r14)
            boolean r1 = r13.needUpload()
            if (r1 == 0) goto L_0x02b4
            java.lang.String r1 = r9.mCardType
            r13.setObjectName(r1)
            java.lang.String r1 = "issue"
            r13.setCoreOperation(r1)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r1 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r19)
            r1.uploadUserExceptionLog(r13)
        L_0x02b4:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "issue finished. resultCode: "
            r1.append(r2)
            r1.append(r14)
            java.lang.String r1 = r1.toString()
            com.miui.tsmclient.util.LogUtils.d(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.model.mitsm.MiTSMCardClient.issue(android.content.Context, com.miui.tsmclient.entity.CardInfo, android.os.Bundle):com.miui.tsmclient.model.BaseResponse");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00bf, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c4, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c5, code lost:
        r1 = 11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r7.setExtra(r0.getMessage());
        com.miui.tsmclient.util.LogUtils.e("delete is interrupted.", r0);
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00e0, code lost:
        if (r13.getBoolean(EXTRAS_KEY_SESSION_NOT_FINISH) == false) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e2, code lost:
        com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance().removeSession(r12, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e9, code lost:
        r7.setErrorCode(11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00f0, code lost:
        if (r7.needUpload() != false) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00f2, code lost:
        r7.setObjectName(r12.mCardType);
        r7.setCoreOperation("delete");
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r11).uploadUserExceptionLog(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0105, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0106, code lost:
        r9 = 11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0114, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0116, code lost:
        r2 = r0.getErrorCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x012e, code lost:
        com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance().removeSession(r12, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x013e, code lost:
        r7.setObjectName(r12.mCardType);
        r7.setCoreOperation("delete");
        com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r11).uploadUserExceptionLog(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0157, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0158, code lost:
        r1 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r7.setExtra(r0.getMessage());
        com.miui.tsmclient.util.LogUtils.e("delete failed with an io exception.", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x016b, code lost:
        if (r13.getBoolean(EXTRAS_KEY_SESSION_NOT_FINISH) == false) goto L_0x016d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x016d, code lost:
        com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance().removeSession(r12, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0174, code lost:
        r7.setErrorCode(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x017b, code lost:
        if (r7.needUpload() != false) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0187, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0188, code lost:
        r9 = 2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c4 A[ExcHandler: InterruptedException (r0v20 'e' java.lang.InterruptedException A[CUSTOM_DECLARE]), Splitter:B:7:0x0052] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0114 A[Catch:{ all -> 0x0154 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0116 A[Catch:{ all -> 0x0154 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0157 A[ExcHandler: IOException (r0v12 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:7:0x0052] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0191  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.miui.tsmclient.model.BaseResponse delete(android.content.Context r11, com.miui.tsmclient.entity.CardInfo r12, android.os.Bundle r13) {
        /*
            r10 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Delete aid is : "
            r0.append(r1)
            java.lang.String r1 = r12.mAid
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.miui.tsmclient.util.LogUtils.d(r0)
            if (r13 != 0) goto L_0x001d
            android.os.Bundle r13 = new android.os.Bundle
            r13.<init>()
        L_0x001d:
            java.lang.String r0 = "extras_key_business_type"
            java.io.Serializable r0 = r13.getSerializable(r0)
            com.miui.tsmclient.model.mitsm.TSMSessionManager$BusinessType r0 = (com.miui.tsmclient.model.mitsm.TSMSessionManager.BusinessType) r0
            if (r0 != 0) goto L_0x0029
            com.miui.tsmclient.model.mitsm.TSMSessionManager$BusinessType r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.BusinessType.DELETE
        L_0x0029:
            r6 = r0
            com.miui.tsmclient.entity.UserExceptionLogInfo r7 = new com.miui.tsmclient.entity.UserExceptionLogInfo
            r7.<init>()
            java.lang.String r0 = "extras_key_se_operation"
            r1 = 4
            int r0 = r13.getInt(r0, r1)
            com.miui.tsmclient.seitsm.TsmRpcModels$SeOperationType r4 = com.miui.tsmclient.seitsm.TsmRpcModels.SeOperationType.valueOf((int) r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "delete called. SeOperationType: "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            com.miui.tsmclient.util.LogUtils.d(r0)
            java.lang.String r8 = ""
            r0 = 1
            r9 = 0
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmSessionInfo r3 = r10.getSession(r11, r12, r6, r0)     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x010a, InterruptedException -> 0x00c4, all -> 0x00c1 }
            if (r3 == 0) goto L_0x005d
            java.lang.String r0 = r3.getSessionId()     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x010a, InterruptedException -> 0x00c4, all -> 0x00c1 }
            goto L_0x005f
        L_0x005d:
            java.lang.String r0 = ""
        L_0x005f:
            r7.setSessionId(r0)     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x010a, InterruptedException -> 0x00c4, all -> 0x00c1 }
            r0 = r10
            r1 = r11
            r2 = r12
            r5 = r13
            com.miui.tsmclient.model.BaseResponse r0 = r0.startSeOperation(r1, r2, r3, r4, r5)     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x010a, InterruptedException -> 0x00c4, all -> 0x00c1 }
            boolean r1 = r0.isSuccess()     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x010a, InterruptedException -> 0x00c4, all -> 0x00c1 }
            if (r1 == 0) goto L_0x008b
            com.miui.tsmclient.util.SysUtils.clearCardCache(r11, r12)     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x010a, InterruptedException -> 0x00c4, all -> 0x00c1 }
            android.content.Intent r1 = new android.content.Intent     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x010a, InterruptedException -> 0x00c4, all -> 0x00c1 }
            java.lang.String r2 = "com.miui.tsmclient.action.QUERY_CARDS"
            r1.<init>(r2)     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x010a, InterruptedException -> 0x00c4, all -> 0x00c1 }
            java.lang.String r2 = "com.miui.nextpay"
            r1.setPackage(r2)     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x010a, InterruptedException -> 0x00c4, all -> 0x00c1 }
            java.lang.String r2 = "card_type"
            java.lang.String r3 = r12.mCardType     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x010a, InterruptedException -> 0x00c4, all -> 0x00c1 }
            r1.putExtra(r2, r3)     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x010a, InterruptedException -> 0x00c4, all -> 0x00c1 }
            com.miui.tsmclient.util.ServiceUtils.startService(r11, r1)     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x010a, InterruptedException -> 0x00c4, all -> 0x00c1 }
            r1 = 0
            goto L_0x0095
        L_0x008b:
            int r1 = r0.mResultCode     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x010a, InterruptedException -> 0x00c4, all -> 0x00c1 }
            java.lang.String r2 = r0.mMsg     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x00bf, InterruptedException -> 0x00c4 }
            r7.setErrorDesc(r2)     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x00bf, InterruptedException -> 0x00c4 }
            r7.setErrorCode(r1)     // Catch:{ IOException -> 0x0157, SeiTSMApiException -> 0x00bf, InterruptedException -> 0x00c4 }
        L_0x0095:
            java.lang.String r2 = "extras_key_session_not_finish"
            boolean r13 = r13.getBoolean(r2)
            if (r13 != 0) goto L_0x00a4
            com.miui.tsmclient.model.mitsm.TSMSessionManager r13 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r13.removeSession(r12, r6)
        L_0x00a4:
            r7.setErrorCode(r1)
            boolean r13 = r7.needUpload()
            if (r13 == 0) goto L_0x00be
            java.lang.String r12 = r12.mCardType
            r7.setObjectName(r12)
            java.lang.String r12 = "delete"
            r7.setCoreOperation(r12)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r11 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r11)
            r11.uploadUserExceptionLog(r7)
        L_0x00be:
            return r0
        L_0x00bf:
            r0 = move-exception
            goto L_0x010c
        L_0x00c1:
            r0 = move-exception
            goto L_0x0189
        L_0x00c4:
            r0 = move-exception
            r1 = 11
            java.lang.String r2 = r0.getMessage()     // Catch:{ all -> 0x0105 }
            r7.setExtra(r2)     // Catch:{ all -> 0x0105 }
            java.lang.String r2 = "delete is interrupted."
            com.miui.tsmclient.util.LogUtils.e(r2, r0)     // Catch:{ all -> 0x0105 }
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0105 }
            r0.interrupt()     // Catch:{ all -> 0x0105 }
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r13 = r13.getBoolean(r0)
            if (r13 != 0) goto L_0x00e9
            com.miui.tsmclient.model.mitsm.TSMSessionManager r13 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r13.removeSession(r12, r6)
        L_0x00e9:
            r7.setErrorCode(r1)
            boolean r13 = r7.needUpload()
            if (r13 == 0) goto L_0x017f
        L_0x00f2:
            java.lang.String r12 = r12.mCardType
            r7.setObjectName(r12)
            java.lang.String r12 = "delete"
            r7.setCoreOperation(r12)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r11 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r11)
            r11.uploadUserExceptionLog(r7)
            goto L_0x017f
        L_0x0105:
            r0 = move-exception
            r9 = 11
            goto L_0x0189
        L_0x010a:
            r0 = move-exception
            r1 = 0
        L_0x010c:
            int r2 = r0.getErrorCode()     // Catch:{ all -> 0x0154 }
            r3 = 10021(0x2725, float:1.4042E-41)
            if (r2 != r3) goto L_0x0116
            r2 = 0
            goto L_0x011a
        L_0x0116:
            int r2 = r0.getErrorCode()     // Catch:{ all -> 0x0154 }
        L_0x011a:
            java.lang.String r8 = r0.getMessage()     // Catch:{ all -> 0x0151 }
            r7.setExtra(r8)     // Catch:{ all -> 0x0151 }
            java.lang.String r1 = "delete failed with an tsmapi exception."
            com.miui.tsmclient.util.LogUtils.e(r1, r0)     // Catch:{ all -> 0x0151 }
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r13 = r13.getBoolean(r0)
            if (r13 != 0) goto L_0x0135
            com.miui.tsmclient.model.mitsm.TSMSessionManager r13 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r13.removeSession(r12, r6)
        L_0x0135:
            r7.setErrorCode(r2)
            boolean r13 = r7.needUpload()
            if (r13 == 0) goto L_0x014f
            java.lang.String r12 = r12.mCardType
            r7.setObjectName(r12)
            java.lang.String r12 = "delete"
            r7.setCoreOperation(r12)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r11 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r11)
            r11.uploadUserExceptionLog(r7)
        L_0x014f:
            r1 = r2
            goto L_0x017f
        L_0x0151:
            r0 = move-exception
            r9 = r2
            goto L_0x0189
        L_0x0154:
            r0 = move-exception
            r9 = r1
            goto L_0x0189
        L_0x0157:
            r0 = move-exception
            r1 = 2
            java.lang.String r2 = r0.getMessage()     // Catch:{ all -> 0x0187 }
            r7.setExtra(r2)     // Catch:{ all -> 0x0187 }
            java.lang.String r2 = "delete failed with an io exception."
            com.miui.tsmclient.util.LogUtils.e(r2, r0)     // Catch:{ all -> 0x0187 }
            java.lang.String r0 = "extras_key_session_not_finish"
            boolean r13 = r13.getBoolean(r0)
            if (r13 != 0) goto L_0x0174
            com.miui.tsmclient.model.mitsm.TSMSessionManager r13 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r13.removeSession(r12, r6)
        L_0x0174:
            r7.setErrorCode(r1)
            boolean r13 = r7.needUpload()
            if (r13 == 0) goto L_0x017f
            goto L_0x00f2
        L_0x017f:
            com.miui.tsmclient.model.BaseResponse r11 = new com.miui.tsmclient.model.BaseResponse
            java.lang.Object[] r12 = new java.lang.Object[r9]
            r11.<init>(r1, r8, r12)
            return r11
        L_0x0187:
            r0 = move-exception
            r9 = 2
        L_0x0189:
            java.lang.String r1 = "extras_key_session_not_finish"
            boolean r13 = r13.getBoolean(r1)
            if (r13 != 0) goto L_0x0198
            com.miui.tsmclient.model.mitsm.TSMSessionManager r13 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r13.removeSession(r12, r6)
        L_0x0198:
            r7.setErrorCode(r9)
            boolean r13 = r7.needUpload()
            if (r13 == 0) goto L_0x01b2
            java.lang.String r12 = r12.mCardType
            r7.setObjectName(r12)
            java.lang.String r12 = "delete"
            r7.setCoreOperation(r12)
            com.miui.tsmclient.model.UploadUserExceptionLogModel r11 = com.miui.tsmclient.model.UploadUserExceptionLogModel.create(r11)
            r11.uploadUserExceptionLog(r7)
        L_0x01b2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.model.mitsm.MiTSMCardClient.delete(android.content.Context, com.miui.tsmclient.entity.CardInfo, android.os.Bundle):com.miui.tsmclient.model.BaseResponse");
    }

    /* access modifiers changed from: protected */
    public BaseResponse uploadTransferOutResult(Context context, CardInfo cardInfo, Bundle bundle) {
        if (bundle == null || !bundle.getBoolean(Constants.EXTRAS_KEY_WITHDRAW, false)) {
            return new BaseResponse(-2, new Object[0]);
        }
        return this.mTSMAuthManager.uploadTransferOutResult(context, cardInfo);
    }

    public BaseResponse lock(Context context, CardInfo cardInfo, Bundle bundle) {
        int i;
        TSMSessionManager.BusinessType businessType = TSMSessionManager.BusinessType.LOCK;
        String str = "";
        try {
            BaseResponse startSeOperation = startSeOperation(context, cardInfo, getSession(context, cardInfo, businessType, true), TsmRpcModels.SeOperationType.LOCK, bundle);
            TSMSessionManager.getInstance().removeSession(cardInfo, businessType);
            return startSeOperation;
        } catch (IOException e) {
            i = 2;
            LogUtils.e("lock failed with an io exception.", e);
        } catch (SeiTSMApiException e2) {
            i = e2.getErrorCode();
            str = e2.getMessage();
            LogUtils.e("lock failed with an tsmapi exception.", e2);
        } catch (InterruptedException e3) {
            i = 11;
            LogUtils.e("lock is interrupted.", e3);
            Thread.currentThread().interrupt();
        } catch (Throwable th) {
            TSMSessionManager.getInstance().removeSession(cardInfo, businessType);
            throw th;
        }
        TSMSessionManager.getInstance().removeSession(cardInfo, businessType);
        return new BaseResponse(i, str, new Object[0]);
    }

    public TsmRpcModels.TsmSessionInfo getSession(Context context, CardInfo cardInfo, TSMSessionManager.BusinessType businessType, boolean z) throws SeiTSMApiException {
        TsmRpcModels.TsmSessionInfo tsmSessionInfo;
        TSMSessionManager instance = TSMSessionManager.getInstance();
        if (businessType == null) {
            tsmSessionInfo = instance.getSession(context, cardInfo);
        } else {
            tsmSessionInfo = instance.getSession(context, cardInfo, businessType, z);
        }
        if (tsmSessionInfo.getResult() == 0) {
            LogUtils.d("sessionInfo : " + tsmSessionInfo.getSessionId());
            return tsmSessionInfo;
        }
        throw new SeiTSMApiException(MiTsmErrorCode.format(tsmSessionInfo.getResult()), tsmSessionInfo.getErrorDesc());
    }

    public TsmRpcModels.TsmSessionInfo getSession(Context context, CardInfo cardInfo, TSMSessionManager.BusinessType businessType) throws SeiTSMApiException {
        return getSession(context, cardInfo, businessType, false);
    }

    public BaseResponse syncEse(Context context, CardInfo cardInfo, TsmRpcModels.TsmSessionInfo tsmSessionInfo, Bundle bundle) throws IOException, SeiTSMApiException, InterruptedException {
        TsmRpcModels.TsmAPDUCommand startSeOperation = this.mSeiTsmAuthManager.startSeOperation(context, tsmSessionInfo, TsmRpcModels.SeOperationType.SYNC, cardInfo, bundle);
        if (startSeOperation != null) {
            int format = MiTsmErrorCode.format(startSeOperation.getResult());
            if (format == 0) {
                IScTerminal terminal = cardInfo.getTerminal();
                try {
                    terminal.open();
                    BaseResponse executeCapdu = executeCapdu(context, terminal, tsmSessionInfo, startSeOperation);
                    LogUtils.d("sync ese finished, result:" + executeCapdu.mResultCode);
                    sNeedSync.set(false);
                    return executeCapdu;
                } finally {
                    terminal.close();
                }
            } else {
                throw new SeiTSMApiException(format, startSeOperation.getErrorDesc());
            }
        } else {
            LogUtils.e("syncEse failed, no apdu commands");
            throw new SeiTSMApiException(8, "");
        }
    }

    public BaseResponse unrestrictEse(Context context, Bundle bundle) throws IOException, SeiTSMApiException, InterruptedException {
        CardInfo cardInfo = new CardInfo(CardInfo.CARD_TYPE_DUMMY);
        TsmRpcModels.TsmSessionInfo session = getSession(context, cardInfo, (TSMSessionManager.BusinessType) null);
        TsmRpcModels.TsmAPDUCommand startSeOperation = this.mSeiTsmAuthManager.startSeOperation(context, session, TsmRpcModels.SeOperationType.UNRESTRICT, (CardInfo) null, bundle);
        if (startSeOperation != null) {
            int format = MiTsmErrorCode.format(startSeOperation.getResult());
            if (format == 0) {
                IScTerminal terminal = cardInfo.getTerminal();
                try {
                    terminal.open();
                    return executeCapdu(context, terminal, session, startSeOperation);
                } finally {
                    terminal.close();
                }
            } else {
                throw new SeiTSMApiException(format, startSeOperation.getErrorDesc());
            }
        } else {
            LogUtils.e("unRestrict ese failed, no apdu commands");
            throw new SeiTSMApiException(8, "");
        }
    }

    /* access modifiers changed from: protected */
    public BaseResponse executeCapdu(Context context, IScTerminal iScTerminal, TsmRpcModels.TsmSessionInfo tsmSessionInfo, TsmRpcModels.TsmAPDUCommand tsmAPDUCommand) throws IOException, SeiTSMApiException, InterruptedException {
        return executeCapdu(context, iScTerminal, tsmSessionInfo, tsmAPDUCommand, (TsmRpcModels.SeOperationType) null, true);
    }

    /* access modifiers changed from: protected */
    public BaseResponse executeCapdu(Context context, IScTerminal iScTerminal, TsmRpcModels.TsmSessionInfo tsmSessionInfo, TsmRpcModels.TsmAPDUCommand tsmAPDUCommand, TsmRpcModels.SeOperationType seOperationType, boolean z) throws IOException, SeiTSMApiException, InterruptedException {
        boolean z2;
        String str;
        List<TsmRpcModels.TsmCAPDU> apdusList = tsmAPDUCommand.getApdusList();
        if (apdusList == null || apdusList.isEmpty()) {
            throw new SeiTSMApiException(MiTsmErrorCode.format(tsmAPDUCommand.getResult()), tsmAPDUCommand.getErrorDesc());
        }
        ArrayList arrayList = new ArrayList();
        Iterator<TsmRpcModels.TsmCAPDU> it = apdusList.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            TsmRpcModels.TsmCAPDU next = it.next();
            ScResponse transmit = iScTerminal.transmit(next.getApdu().toByteArray());
            i++;
            arrayList.add(TsmRpcModels.SeAPDUResponseItem.newBuilder().setIndex(i).setResponseData(ByteString.copyFrom(transmit.getData().toBytes())).setResponseSw(ByteString.copyFrom(transmit.getStatus().toBytes())).build());
            if (!TextUtils.isEmpty(next.getExpectSwRegex())) {
                if (!Pattern.compile(next.getExpectSwRegex()).matcher(Coder.bytesToHexString(transmit.getStatus().toBytes())).matches()) {
                    z2 = false;
                    break;
                }
            } else {
                LogUtils.d("no expected sw.");
                break;
            }
        }
        z2 = true;
        if (z) {
            TsmRpcModels.TsmAPDUCommand processSeResponse = this.mSeiTsmAuthManager.processSeResponse(context, tsmSessionInfo, arrayList);
            if (processSeResponse == null) {
                LogUtils.d("can not get nextApducommand, processSeResponse failed.");
                return new BaseResponse(16, new Object[0]);
            }
            int format = MiTsmErrorCode.format(processSeResponse.getResult());
            if (format != 0) {
                throw new SeiTSMApiException(format, processSeResponse.getErrorDesc());
            } else if (processSeResponse.getApdusList() != null && !processSeResponse.getApdusList().isEmpty()) {
                return executeCapdu(context, iScTerminal, tsmSessionInfo, processSeResponse);
            } else {
                LogUtils.d("no more apdu, execute finished!");
                return new BaseResponse(0, new Object[0]);
            }
        } else {
            TsmRpcModels.CommonResponse processFinishNotify = this.mSeiTsmAuthManager.processFinishNotify(context, tsmSessionInfo, z2, seOperationType, arrayList);
            if (processFinishNotify == null || processFinishNotify.getResult() != 0) {
                int i2 = -2;
                if (processFinishNotify != null) {
                    i2 = processFinishNotify.getResult();
                    str = processFinishNotify.getErrorDesc();
                } else {
                    str = "";
                }
                throw new SeiTSMApiException(i2, str);
            }
            return new BaseResponse(z2 ? 0 : 8, new Object[0]);
        }
    }

    /* access modifiers changed from: protected */
    public BaseResponse executeCapdu(Context context, CardInfo cardInfo, TsmRpcModels.TsmSessionInfo tsmSessionInfo, TsmRpcModels.TsmAPDUCommand tsmAPDUCommand) throws IOException, InterruptedException, SeiTSMApiException {
        IScTerminal terminal = cardInfo.getTerminal();
        try {
            terminal.open();
            return executeCapdu(context, terminal, tsmSessionInfo, tsmAPDUCommand);
        } finally {
            terminal.close();
        }
    }

    /* access modifiers changed from: protected */
    public BaseResponse startSeOperation(Context context, CardInfo cardInfo, TsmRpcModels.TsmSessionInfo tsmSessionInfo, TsmRpcModels.SeOperationType seOperationType, Bundle bundle) throws IOException, SeiTSMApiException, InterruptedException {
        BaseResponse syncEse = sNeedSync.get() ? syncEse(context, cardInfo, tsmSessionInfo, bundle) : null;
        if (sNeedSync.get()) {
            return syncEse;
        }
        TsmRpcModels.TsmAPDUCommand startSeOperation = this.mSeiTsmAuthManager.startSeOperation(context, tsmSessionInfo, seOperationType, cardInfo, bundle);
        if (startSeOperation == null) {
            sNeedSync.set(true);
            return new BaseResponse(8, new Object[0]);
        }
        int format = MiTsmErrorCode.format(startSeOperation.getResult());
        if (format != 0) {
            throw new SeiTSMApiException(format, startSeOperation.getErrorDesc());
        } else if (startSeOperation.getApdusList() == null || startSeOperation.getApdusList().isEmpty()) {
            return new BaseResponse(0, new Object[0]);
        } else {
            IScTerminal terminal = cardInfo.getTerminal();
            try {
                terminal.open();
                return executeCapdu(context, terminal, tsmSessionInfo, startSeOperation);
            } finally {
                terminal.close();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00fb, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r7 = r11.getErrorCode();
        r8 = r11.getMessage();
        com.miui.tsmclient.util.LogUtils.e("saveAppKey failed with an tsmapi exception.", r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x010a, code lost:
        r11 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x010b, code lost:
        r8 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x011b, code lost:
        r11 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x011c, code lost:
        r8 = "";
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00fb A[ExcHandler: SeiTSMApiException (r11v12 'e' com.miui.tsmclient.seitsm.Exception.SeiTSMApiException A[CUSTOM_DECLARE]), Splitter:B:9:0x0069] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.miui.tsmclient.model.BaseResponse saveAppKey(android.content.Context r11) {
        /*
            r10 = this;
            java.lang.String r0 = com.miui.tsmclient.util.PrefUtils.PREF_KEY_SPI_PK_STATE
            r1 = 0
            boolean r0 = com.miui.tsmclient.util.PrefUtils.getBoolean(r11, r0, r1)
            if (r0 == 0) goto L_0x0016
            java.lang.String r11 = "don't need to save app key again"
            com.miui.tsmclient.util.LogUtils.d(r11)
            com.miui.tsmclient.model.BaseResponse r11 = new com.miui.tsmclient.model.BaseResponse
            java.lang.Object[] r0 = new java.lang.Object[r1]
            r11.<init>(r1, r0)
            return r11
        L_0x0016:
            java.lang.String r0 = "-------------- start saveAppKey---------------"
            com.miui.tsmclient.util.LogUtils.v(r0)
            com.miui.tsmclient.analytics.AnalyticManager r0 = com.miui.tsmclient.analytics.AnalyticManager.getInstance()
            java.lang.String r2 = "nfc"
            java.lang.String r3 = "operation_%s_launch"
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]
            java.lang.String r6 = "saveAppKey"
            r5[r1] = r6
            java.lang.String r3 = java.lang.String.format(r3, r5)
            r0.recordEvent(r2, r3)
            android.os.Bundle r0 = com.miui.tsmclient.util.SysUtils.getSignedSpiPK(r11)
            if (r0 != 0) goto L_0x0060
            java.lang.String r11 = "signedPK is null"
            com.miui.tsmclient.util.LogUtils.w(r11)
            com.miui.tsmclient.analytics.AnalyticManager r11 = com.miui.tsmclient.analytics.AnalyticManager.getInstance()
            java.lang.String r0 = "nfc"
            java.lang.String r2 = "operation_%s_failed"
            java.lang.Object[] r3 = new java.lang.Object[r4]
            java.lang.String r4 = "saveAppKey"
            r3[r1] = r4
            java.lang.String r2 = java.lang.String.format(r2, r3)
            r3 = 10
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            r11.recordEvent((java.lang.String) r0, (java.lang.String) r2, (java.lang.Object) r4)
            com.miui.tsmclient.model.BaseResponse r11 = new com.miui.tsmclient.model.BaseResponse
            java.lang.Object[] r0 = new java.lang.Object[r1]
            r11.<init>(r3, r0)
            return r11
        L_0x0060:
            com.miui.tsmclient.model.mitsm.TSMSessionManager$BusinessType r2 = com.miui.tsmclient.model.mitsm.TSMSessionManager.BusinessType.SAVEKEY
            java.lang.String r3 = ""
            com.miui.tsmclient.entity.BankCardInfo r5 = new com.miui.tsmclient.entity.BankCardInfo
            r5.<init>()
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmSessionInfo r6 = r10.getSession(r11, r5, r2, r4)     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            com.miui.tsmclient.seitsm.SeiTsmAuthManager r7 = r10.mSeiTsmAuthManager     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmAPDUCommand r0 = r7.saveAppKey(r11, r0, r6)     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            if (r0 != 0) goto L_0x00a4
            java.lang.String r11 = "can not get apdu Command, saveAppKey failed."
            com.miui.tsmclient.util.LogUtils.d(r11)     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            com.miui.tsmclient.analytics.AnalyticManager r11 = com.miui.tsmclient.analytics.AnalyticManager.getInstance()     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            java.lang.String r0 = "nfc"
            java.lang.String r6 = "operation_%s_failed"
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            java.lang.String r8 = "saveAppKey"
            r7[r1] = r8     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            java.lang.String r6 = java.lang.String.format(r6, r7)     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            r7 = 8
            java.lang.Integer r8 = java.lang.Integer.valueOf(r7)     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            r11.recordEvent((java.lang.String) r0, (java.lang.String) r6, (java.lang.Object) r8)     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            com.miui.tsmclient.model.BaseResponse r11 = new com.miui.tsmclient.model.BaseResponse     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            r11.<init>(r7, r0)     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r5, r2)
            return r11
        L_0x00a4:
            int r7 = r0.getResult()     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            int r7 = com.miui.tsmclient.model.mitsm.MiTsmErrorCode.format(r7)     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            java.lang.String r8 = r0.getErrorDesc()     // Catch:{ IOException -> 0x011b, InterruptedException -> 0x010a, SeiTSMApiException -> 0x00fb }
            if (r7 != 0) goto L_0x0123
            com.miui.tsmclient.model.BaseResponse r3 = new com.miui.tsmclient.model.BaseResponse     // Catch:{ IOException -> 0x00f7, InterruptedException -> 0x00f5, SeiTSMApiException -> 0x00fb }
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch:{ IOException -> 0x00f7, InterruptedException -> 0x00f5, SeiTSMApiException -> 0x00fb }
            r3.<init>(r1, r7)     // Catch:{ IOException -> 0x00f7, InterruptedException -> 0x00f5, SeiTSMApiException -> 0x00fb }
            java.util.List r7 = r0.getApdusList()     // Catch:{ IOException -> 0x00f7, InterruptedException -> 0x00f5, SeiTSMApiException -> 0x00fb }
            if (r7 == 0) goto L_0x00cd
            java.util.List r7 = r0.getApdusList()     // Catch:{ IOException -> 0x00f7, InterruptedException -> 0x00f5, SeiTSMApiException -> 0x00fb }
            boolean r7 = r7.isEmpty()     // Catch:{ IOException -> 0x00f7, InterruptedException -> 0x00f5, SeiTSMApiException -> 0x00fb }
            if (r7 != 0) goto L_0x00cd
            com.miui.tsmclient.model.BaseResponse r3 = r10.executeCapdu((android.content.Context) r11, (com.miui.tsmclient.entity.CardInfo) r5, (com.miui.tsmclient.seitsm.TsmRpcModels.TsmSessionInfo) r6, (com.miui.tsmclient.seitsm.TsmRpcModels.TsmAPDUCommand) r0)     // Catch:{ IOException -> 0x00f7, InterruptedException -> 0x00f5, SeiTSMApiException -> 0x00fb }
        L_0x00cd:
            if (r3 == 0) goto L_0x00ed
            int r0 = r3.mResultCode     // Catch:{ IOException -> 0x00f7, InterruptedException -> 0x00f5, SeiTSMApiException -> 0x00fb }
            if (r0 != 0) goto L_0x00ed
            java.lang.String r0 = com.miui.tsmclient.util.PrefUtils.PREF_KEY_SPI_PK_STATE     // Catch:{ IOException -> 0x00f7, InterruptedException -> 0x00f5, SeiTSMApiException -> 0x00fb }
            com.miui.tsmclient.util.PrefUtils.putBoolean(r11, r0, r4)     // Catch:{ IOException -> 0x00f7, InterruptedException -> 0x00f5, SeiTSMApiException -> 0x00fb }
            com.miui.tsmclient.analytics.AnalyticManager r11 = com.miui.tsmclient.analytics.AnalyticManager.getInstance()     // Catch:{ IOException -> 0x00f7, InterruptedException -> 0x00f5, SeiTSMApiException -> 0x00fb }
            java.lang.String r0 = "nfc"
            java.lang.String r6 = "operation_%s_success"
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x00f7, InterruptedException -> 0x00f5, SeiTSMApiException -> 0x00fb }
            java.lang.String r9 = "saveAppKey"
            r7[r1] = r9     // Catch:{ IOException -> 0x00f7, InterruptedException -> 0x00f5, SeiTSMApiException -> 0x00fb }
            java.lang.String r6 = java.lang.String.format(r6, r7)     // Catch:{ IOException -> 0x00f7, InterruptedException -> 0x00f5, SeiTSMApiException -> 0x00fb }
            r11.recordEvent(r0, r6)     // Catch:{ IOException -> 0x00f7, InterruptedException -> 0x00f5, SeiTSMApiException -> 0x00fb }
        L_0x00ed:
            com.miui.tsmclient.model.mitsm.TSMSessionManager r11 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r11.removeSession(r5, r2)
            return r3
        L_0x00f5:
            r11 = move-exception
            goto L_0x010c
        L_0x00f7:
            r11 = move-exception
            goto L_0x011d
        L_0x00f9:
            r11 = move-exception
            goto L_0x0164
        L_0x00fb:
            r11 = move-exception
            int r7 = r11.getErrorCode()     // Catch:{ all -> 0x00f9 }
            java.lang.String r8 = r11.getMessage()     // Catch:{ all -> 0x00f9 }
            java.lang.String r0 = "saveAppKey failed with an tsmapi exception."
            com.miui.tsmclient.util.LogUtils.e(r0, r11)     // Catch:{ all -> 0x00f9 }
            goto L_0x0123
        L_0x010a:
            r11 = move-exception
            r8 = r3
        L_0x010c:
            r7 = 11
            java.lang.String r0 = "saveAppKey is interrupted."
            com.miui.tsmclient.util.LogUtils.e(r0, r11)     // Catch:{ all -> 0x00f9 }
            java.lang.Thread r11 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x00f9 }
            r11.interrupt()     // Catch:{ all -> 0x00f9 }
            goto L_0x0123
        L_0x011b:
            r11 = move-exception
            r8 = r3
        L_0x011d:
            r7 = 2
            java.lang.String r0 = "saveAppKey failed with an io exception."
            com.miui.tsmclient.util.LogUtils.e(r0, r11)     // Catch:{ all -> 0x00f9 }
        L_0x0123:
            com.miui.tsmclient.model.mitsm.TSMSessionManager r11 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r11.removeSession(r5, r2)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "save app key result code: "
            r11.append(r0)
            r11.append(r7)
            java.lang.String r11 = r11.toString()
            com.miui.tsmclient.util.LogUtils.w(r11)
            java.lang.String r11 = "-------------- saveAppKey end ---------------"
            com.miui.tsmclient.util.LogUtils.v(r11)
            com.miui.tsmclient.analytics.AnalyticManager r11 = com.miui.tsmclient.analytics.AnalyticManager.getInstance()
            java.lang.String r0 = "nfc"
            java.lang.String r2 = "operation_%s_failed"
            java.lang.Object[] r3 = new java.lang.Object[r4]
            java.lang.String r4 = "saveAppKey"
            r3[r1] = r4
            java.lang.String r2 = java.lang.String.format(r2, r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            r11.recordEvent((java.lang.String) r0, (java.lang.String) r2, (java.lang.Object) r3)
            com.miui.tsmclient.model.BaseResponse r11 = new com.miui.tsmclient.model.BaseResponse
            java.lang.Object[] r0 = new java.lang.Object[r1]
            r11.<init>(r7, r8, r0)
            return r11
        L_0x0164:
            com.miui.tsmclient.model.mitsm.TSMSessionManager r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.getInstance()
            r0.removeSession(r5, r2)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.model.mitsm.MiTSMCardClient.saveAppKey(android.content.Context):com.miui.tsmclient.model.BaseResponse");
    }

    public BaseResponse isServiceAvailable(Context context, CardInfo cardInfo, Bundle bundle) {
        return new BaseResponse(0, new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void notifyCardInfoChanged(Context context, CardInfo cardInfo) {
        Intent intent = new Intent(Constants.ACTION_UPDATE_CARD_INFO);
        intent.putExtra("card_info", cardInfo);
        context.sendBroadcast(intent, Constants.Permission.TSM_GROUP);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0059, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005a, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005d, code lost:
        throw r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0060, code lost:
        r10 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0062, code lost:
        r10 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0064, code lost:
        r10 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0077, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0078, code lost:
        r1 = r10.getErrorCode();
        r2 = r10.getMessage();
        com.miui.tsmclient.util.LogUtils.e("failed to upgrade applet", r10);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0077 A[ExcHandler: SeiTSMApiException (r10v8 'e' com.miui.tsmclient.seitsm.Exception.SeiTSMApiException A[CUSTOM_DECLARE]), Splitter:B:1:0x0015] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.miui.tsmclient.model.BaseResponse upgradeApplet(android.content.Context r10, int r11) {
        /*
            r9 = this;
            com.miui.tsmclient.entity.CardInfo r6 = new com.miui.tsmclient.entity.CardInfo
            java.lang.String r0 = "DUMMY"
            r6.<init>((java.lang.String) r0)
            java.lang.String r7 = ""
            android.os.Bundle r5 = new android.os.Bundle
            r5.<init>()
            java.lang.String r0 = "serviceType"
            r5.putInt(r0, r11)
            r11 = 0
            r8 = 0
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmSessionInfo r11 = r9.getSession(r10, r6, r11)     // Catch:{ NfcEeIOException -> 0x008f, IOException -> 0x0086, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0066 }
            com.miui.tsmclient.seitsm.SeiTsmAuthManager r0 = r9.mSeiTsmAuthManager     // Catch:{ NfcEeIOException -> 0x008f, IOException -> 0x0086, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0066 }
            com.miui.tsmclient.seitsm.TsmRpcModels$SeOperationType r3 = com.miui.tsmclient.seitsm.TsmRpcModels.SeOperationType.UPGRADE_SE     // Catch:{ NfcEeIOException -> 0x008f, IOException -> 0x0086, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0066 }
            r1 = r10
            r2 = r11
            r4 = r6
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmAPDUCommand r0 = r0.startSeOperation(r1, r2, r3, r4, r5)     // Catch:{ NfcEeIOException -> 0x008f, IOException -> 0x0086, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0066 }
            if (r0 != 0) goto L_0x0030
            com.miui.tsmclient.model.BaseResponse r10 = new com.miui.tsmclient.model.BaseResponse     // Catch:{ NfcEeIOException -> 0x008f, IOException -> 0x0086, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0066 }
            r11 = 8
            java.lang.Object[] r0 = new java.lang.Object[r8]     // Catch:{ NfcEeIOException -> 0x008f, IOException -> 0x0086, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0066 }
            r10.<init>(r11, r0)     // Catch:{ NfcEeIOException -> 0x008f, IOException -> 0x0086, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0066 }
            return r10
        L_0x0030:
            int r1 = r0.getResult()     // Catch:{ NfcEeIOException -> 0x008f, IOException -> 0x0086, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0066 }
            java.lang.String r2 = r0.getErrorDesc()     // Catch:{ NfcEeIOException -> 0x008f, IOException -> 0x0086, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0066 }
            if (r1 != 0) goto L_0x0098
            java.util.List r1 = r0.getApdusList()     // Catch:{ NfcEeIOException -> 0x0064, IOException -> 0x0062, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0060 }
            if (r1 == 0) goto L_0x005e
            java.util.List r1 = r0.getApdusList()     // Catch:{ NfcEeIOException -> 0x0064, IOException -> 0x0062, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0060 }
            boolean r1 = r1.isEmpty()     // Catch:{ NfcEeIOException -> 0x0064, IOException -> 0x0062, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0060 }
            if (r1 != 0) goto L_0x005e
            com.tsmclient.smartcard.terminal.IScTerminal r1 = r6.getTerminal()     // Catch:{ NfcEeIOException -> 0x0064, IOException -> 0x0062, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0060 }
            r1.open()     // Catch:{ all -> 0x0059 }
            com.miui.tsmclient.model.BaseResponse r10 = r9.executeCapdu((android.content.Context) r10, (com.tsmclient.smartcard.terminal.IScTerminal) r1, (com.miui.tsmclient.seitsm.TsmRpcModels.TsmSessionInfo) r11, (com.miui.tsmclient.seitsm.TsmRpcModels.TsmAPDUCommand) r0)     // Catch:{ all -> 0x0059 }
            r1.close()     // Catch:{ NfcEeIOException -> 0x0064, IOException -> 0x0062, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0060 }
            return r10
        L_0x0059:
            r10 = move-exception
            r1.close()     // Catch:{ NfcEeIOException -> 0x0064, IOException -> 0x0062, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0060 }
            throw r10     // Catch:{ NfcEeIOException -> 0x0064, IOException -> 0x0062, SeiTSMApiException -> 0x0077, InterruptedException -> 0x0060 }
        L_0x005e:
            r1 = 0
            goto L_0x0098
        L_0x0060:
            r10 = move-exception
            goto L_0x0068
        L_0x0062:
            r10 = move-exception
            goto L_0x0088
        L_0x0064:
            r10 = move-exception
            goto L_0x0091
        L_0x0066:
            r10 = move-exception
            r2 = r7
        L_0x0068:
            java.lang.Thread r11 = java.lang.Thread.currentThread()
            r11.interrupt()
            r1 = 11
            java.lang.String r11 = "failed to upgrade applet"
            com.miui.tsmclient.util.LogUtils.e(r11, r10)
            goto L_0x0098
        L_0x0077:
            r10 = move-exception
            int r1 = r10.getErrorCode()
            java.lang.String r2 = r10.getMessage()
            java.lang.String r11 = "failed to upgrade applet"
            com.miui.tsmclient.util.LogUtils.e(r11, r10)
            goto L_0x0098
        L_0x0086:
            r10 = move-exception
            r2 = r7
        L_0x0088:
            r1 = 2
            java.lang.String r11 = "failed to upgrade applet"
            com.miui.tsmclient.util.LogUtils.e(r11, r10)
            goto L_0x0098
        L_0x008f:
            r10 = move-exception
            r2 = r7
        L_0x0091:
            r1 = 10
            java.lang.String r11 = "failed to upgrade applet"
            com.miui.tsmclient.util.LogUtils.e(r11, r10)
        L_0x0098:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "upgrade resultCode = "
            r10.append(r11)
            r10.append(r1)
            java.lang.String r11 = ", msg = "
            r10.append(r11)
            r10.append(r2)
            java.lang.String r10 = r10.toString()
            com.miui.tsmclient.util.LogUtils.d(r10)
            com.miui.tsmclient.model.BaseResponse r10 = new com.miui.tsmclient.model.BaseResponse
            java.lang.Object[] r11 = new java.lang.Object[r8]
            r10.<init>(r1, r2, r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.model.mitsm.MiTSMCardClient.upgradeApplet(android.content.Context, int):com.miui.tsmclient.model.BaseResponse");
    }

    public BaseResponse checkSeUpgrade(Context context, int i) {
        int i2;
        TsmRpcModels.CheckSeUpgradeResponse checkSeUpgradeResponse;
        String str = "";
        try {
            checkSeUpgradeResponse = this.mSeiTsmAuthManager.checkSeUpgrade(context, TsmRpcModels.ServiceType.valueOf(i));
            if (checkSeUpgradeResponse != null) {
                try {
                    i2 = checkSeUpgradeResponse.getResult();
                    str = checkSeUpgradeResponse.getErrorDesc();
                } catch (SeiTSMApiException e) {
                    e = e;
                    LogUtils.e("failed to check service available, businessType = " + i, e);
                    i2 = e.getErrorCode();
                    str = e.getMessage();
                    return new BaseResponse(i2, str, checkSeUpgradeResponse);
                }
            } else {
                i2 = -2;
            }
        } catch (SeiTSMApiException e2) {
            e = e2;
            checkSeUpgradeResponse = null;
            LogUtils.e("failed to check service available, businessType = " + i, e);
            i2 = e.getErrorCode();
            str = e.getMessage();
            return new BaseResponse(i2, str, checkSeUpgradeResponse);
        }
        return new BaseResponse(i2, str, checkSeUpgradeResponse);
    }

    private boolean isIssueOrTransferIn(TsmRpcModels.SeOperationType seOperationType) {
        return seOperationType == TsmRpcModels.SeOperationType.INSTALL || seOperationType == TsmRpcModels.SeOperationType.SHIFT_IN;
    }
}
