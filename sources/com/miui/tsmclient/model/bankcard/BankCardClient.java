package com.miui.tsmclient.model.bankcard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.protobuf.ByteString;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.InAppTransData;
import com.miui.tsmclient.entity.NfcConfigsResponse;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.mitsm.MiTSMCardClient;
import com.miui.tsmclient.model.mitsm.MiTsmErrorCode;
import com.miui.tsmclient.model.mitsm.TSMSessionManager;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.seitsm.TsmRpcModels;
import com.miui.tsmclient.util.Constants;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.ServiceUtils;
import com.miui.tsmclient.util.StringUtils;
import com.miui.tsmclient.util.SysUtils;
import com.miui.tsmclientsdk.MiTsmConstants;
import com.miui.tsmclientsdk.OnProgressUpdateListener;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.terminal.APDUConstants;
import com.tsmclient.smartcard.terminal.IScTerminal;
import com.tsmclient.smartcard.terminal.response.ScResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class BankCardClient extends MiTSMCardClient {
    private static final int MAX_RETRY_COUNT = 10;

    public BaseResponse issue(Context context, CardInfo cardInfo, Bundle bundle) {
        return issue(context, cardInfo, bundle);
    }

    public BaseResponse issue(Context context, CardInfo cardInfo, Bundle bundle, OnProgressUpdateListener onProgressUpdateListener) {
        if (onProgressUpdateListener != null) {
            onProgressUpdateListener.onProgress(1);
        }
        if (cardInfo == null || !cardInfo.isBankCard()) {
            return new BaseResponse(1, new Object[0]);
        }
        BankCardInfo bankCardInfo = (BankCardInfo) cardInfo;
        BaseResponse preparePayApplet = preparePayApplet(context, cardInfo, bundle);
        if (preparePayApplet == null || preparePayApplet.mResultCode != 0) {
            TSMSessionManager.getInstance().removeSession(cardInfo, TSMSessionManager.BusinessType.INSTALL);
            return preparePayApplet;
        }
        if (onProgressUpdateListener != null) {
            onProgressUpdateListener.onProgress(40);
        }
        BaseResponse enrollUPCard = enrollUPCard(context, cardInfo, bundle);
        if (enrollUPCard == null || enrollUPCard.mResultCode != 0) {
            TSMSessionManager.getInstance().removeSession(cardInfo, TSMSessionManager.BusinessType.INSTALL);
            return enrollUPCard;
        }
        updateCardInfoFromServer(bankCardInfo, (TsmRpcModels.VirtualCardInfoResponse) enrollUPCard.mDatas[0]);
        if (onProgressUpdateListener != null) {
            onProgressUpdateListener.onProgress(60);
        }
        BaseResponse pullPersonData = pullPersonData(context, cardInfo, bundle);
        if (pullPersonData != null && pullPersonData.mResultCode == 0) {
            if (onProgressUpdateListener != null) {
                onProgressUpdateListener.onProgress(100);
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString(MiTsmConstants.KEY_CARD_INFO, getSimpleCardInfo(bankCardInfo));
            pullPersonData.mDatas = new Object[1];
            pullPersonData.mDatas[0] = bundle2;
            updateLocalBankCards(context, cardInfo);
        }
        TSMSessionManager.getInstance().removeSession(cardInfo, TSMSessionManager.BusinessType.INSTALL);
        return pullPersonData;
    }

    public String getSimpleCardInfo(BankCardInfo bankCardInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("aid", bankCardInfo.mAid);
            jSONObject.put("bank_card_type", String.valueOf(bankCardInfo.mBankCardType));
            jSONObject.put(BankCardInfo.CARD_INFO_FIELD_BANK_CARD_VC_NUM, StringUtils.tail(bankCardInfo.mVCardNumber, 4));
            jSONObject.put(BankCardInfo.CARD_INFO_FIELD_BANK_CARD_PAN_LAST_DIGITS, bankCardInfo.mPanLastDigits);
            jSONObject.put("card_vc_status", String.valueOf(bankCardInfo.mVCStatus));
            jSONObject.put("deviceModel", SysUtils.getDeviceModel(bankCardInfo));
        } catch (JSONException e) {
            LogUtils.e("serialize bankcard info to json object failed!", e);
        }
        return jSONObject.toString();
    }

    public BaseResponse requestVerificationCode(Context context, CardInfo cardInfo, String str) {
        int i;
        String str2 = "";
        try {
            TsmRpcModels.CommonResponse requestVerificationCode = this.mSeiTsmAuthManager.requestVerificationCode(context, getSession(context, cardInfo, TSMSessionManager.BusinessType.INSTALL), str);
            if (requestVerificationCode == null) {
                i = -1;
            } else {
                i = MiTsmErrorCode.format(requestVerificationCode.getResult());
                str2 = requestVerificationCode.getErrorDesc();
            }
            LogUtils.d("requestVerificationCode result: " + i);
            if (i == 0) {
                return new BaseResponse(i, requestVerificationCode);
            }
        } catch (SeiTSMApiException e) {
            i = e.getErrorCode();
            str2 = e.getMessage();
            LogUtils.e("requestVerificationCode failed with an tsmapi exception.", e);
        }
        return new BaseResponse(i, str2, new Object[0]);
    }

    public BaseResponse verifyVerificationCode(Context context, CardInfo cardInfo, String str, String str2) {
        int i;
        TSMSessionManager.BusinessType businessType = TSMSessionManager.BusinessType.INSTALL;
        String str3 = "";
        int i2 = -1;
        try {
            TsmRpcModels.CommonResponse verifyVerificationCode = this.mSeiTsmAuthManager.verifyVerificationCode(context, getSession(context, cardInfo, businessType), str, str2);
            if (verifyVerificationCode != null) {
                int format = MiTsmErrorCode.format(verifyVerificationCode.getResult());
                try {
                    str3 = verifyVerificationCode.getErrorDesc();
                    i2 = format;
                } catch (SeiTSMApiException e) {
                    e = e;
                    i2 = format;
                    try {
                        i = e.getErrorCode();
                        try {
                            str3 = e.getMessage();
                            LogUtils.e("verifyVerificationCode failed with an tsmapi exception.", e);
                            if (i == 0 || i == 3011) {
                                TSMSessionManager.getInstance().removeSession(cardInfo, businessType);
                            }
                            return new BaseResponse(i, str3, new Object[0]);
                        } catch (Throwable th) {
                            th = th;
                            i2 = i;
                            if (i2 == 0 || i2 == 3011) {
                                TSMSessionManager.getInstance().removeSession(cardInfo, businessType);
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        TSMSessionManager.getInstance().removeSession(cardInfo, businessType);
                        throw th;
                    }
                }
            }
            LogUtils.d("verifyVerificationCode result: " + i2);
            if (i2 == 0) {
                BaseResponse baseResponse = new BaseResponse(i2, verifyVerificationCode);
                if (i2 == 0 || i2 == 3011) {
                    TSMSessionManager.getInstance().removeSession(cardInfo, businessType);
                }
                return baseResponse;
            }
            if (i2 == 0 || i2 == 3011) {
                TSMSessionManager.getInstance().removeSession(cardInfo, businessType);
            }
            i = i2;
            return new BaseResponse(i, str3, new Object[0]);
        } catch (SeiTSMApiException e2) {
            e = e2;
            i = e.getErrorCode();
            str3 = e.getMessage();
            LogUtils.e("verifyVerificationCode failed with an tsmapi exception.", e);
            TSMSessionManager.getInstance().removeSession(cardInfo, businessType);
            return new BaseResponse(i, str3, new Object[0]);
        }
    }

    public BaseResponse queryBankCardList(Context context, String str) {
        int i;
        String str2 = "";
        try {
            TsmRpcModels.QueryBankCardInfoResponse queryBankCardInfo = this.mSeiTsmAuthManager.queryBankCardInfo(context, str);
            if (queryBankCardInfo == null) {
                i = -1;
            } else {
                i = MiTsmErrorCode.format(queryBankCardInfo.getResult());
                str2 = queryBankCardInfo.getErrorDesc();
            }
            LogUtils.d("queryBankCardList result: " + i);
            if (i == 0) {
                return new BaseResponse(0, queryBankCardInfo);
            }
        } catch (SeiTSMApiException e) {
            i = e.getErrorCode();
            str2 = e.getMessage();
            LogUtils.e("queryBankCardList failed with an tsmapi exception.", e);
        }
        return new BaseResponse(i, str2, new Object[0]);
    }

    /* JADX INFO: finally extract failed */
    public BaseResponse lock(Context context, CardInfo cardInfo, Bundle bundle) {
        int i;
        String str;
        TSMSessionManager.BusinessType businessType = TSMSessionManager.BusinessType.LOCK;
        try {
            Context context2 = context;
            TsmRpcModels.TsmAPDUCommand startSeOperation = this.mSeiTsmAuthManager.startSeOperation(context2, getSession(context, cardInfo, businessType), TsmRpcModels.SeOperationType.LOCK, cardInfo, bundle);
            if (startSeOperation == null) {
                LogUtils.d("can not get apduCommand, startSeOperation failed.");
                BaseResponse baseResponse = new BaseResponse(8, new Object[0]);
                TSMSessionManager.getInstance().removeSession(cardInfo, businessType);
                return baseResponse;
            }
            i = MiTsmErrorCode.format(startSeOperation.getResult());
            str = startSeOperation.getErrorDesc();
            TSMSessionManager.getInstance().removeSession(cardInfo, businessType);
            return new BaseResponse(i, str, new Object[0]);
        } catch (SeiTSMApiException e) {
            i = e.getErrorCode();
            String message = e.getMessage();
            LogUtils.e("lock failed with an tsmapi exception.", e);
            TSMSessionManager.getInstance().removeSession(cardInfo, businessType);
            str = message;
        } catch (Throwable th) {
            TSMSessionManager.getInstance().removeSession(cardInfo, businessType);
            throw th;
        }
    }

    public BaseResponse queryPan(Context context, CardInfo cardInfo, Bundle bundle) {
        int i;
        TSMSessionManager.BusinessType businessType = TSMSessionManager.BusinessType.INSTALL;
        String str = "";
        try {
            TSMSessionManager.getInstance().removeSession(cardInfo, businessType);
            TsmRpcModels.QueryPanResponse queryPan = this.mSeiTsmAuthManager.queryPan(context, getSession(context, cardInfo, businessType), bundle);
            if (queryPan == null) {
                i = -1;
            } else {
                i = MiTsmErrorCode.format(queryPan.getResult());
                str = queryPan.getErrorDesc();
            }
            LogUtils.i("queryPan resultCode: " + i);
            if (i == 0) {
                return new BaseResponse(0, queryPan);
            }
        } catch (SeiTSMApiException e) {
            i = e.getErrorCode();
            str = e.getMessage();
            LogUtils.e("queryPan failed with an tsmapi exception.", e);
        }
        return new BaseResponse(i, str, new Object[0]);
    }

    public BaseResponse enrollUPCard(Context context, CardInfo cardInfo, Bundle bundle) {
        int i;
        String str = "";
        try {
            TsmRpcModels.VirtualCardInfoResponse enrollUPCard = this.mSeiTsmAuthManager.enrollUPCard(context, getSession(context, cardInfo, TSMSessionManager.BusinessType.INSTALL), cardInfo, bundle);
            if (enrollUPCard == null) {
                i = -1;
            } else {
                i = MiTsmErrorCode.format(enrollUPCard.getResult());
                str = enrollUPCard.getErrorDesc();
            }
            LogUtils.i("enrollUPCard resultCode: " + i);
            if (i == 0) {
                return new BaseResponse(i, enrollUPCard);
            }
        } catch (SeiTSMApiException e) {
            i = e.getErrorCode();
            str = e.getMessage();
            LogUtils.e("enrollUPCard failed with an tsmapi exception.", e);
        }
        return new BaseResponse(i, str, new Object[0]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009c, code lost:
        r3 = r7.getErrorCode();
        r4 = r7.getMessage();
        com.miui.tsmclient.util.LogUtils.e("preparePayApplet failed with an tsmapi exception.", r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00aa, code lost:
        r7 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ab, code lost:
        r4 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b4, code lost:
        r7 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b5, code lost:
        r4 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00bd, code lost:
        r7 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00be, code lost:
        r4 = "";
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009b A[ExcHandler: SeiTSMApiException (r7v10 'e' com.miui.tsmclient.seitsm.Exception.SeiTSMApiException A[CUSTOM_DECLARE]), Splitter:B:4:0x000e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.miui.tsmclient.model.BaseResponse preparePayApplet(android.content.Context r7, com.miui.tsmclient.entity.CardInfo r8, android.os.Bundle r9) {
        /*
            r6 = this;
            com.miui.tsmclient.model.BaseResponse r0 = r6.saveAppKey(r7)
            int r1 = r0.mResultCode
            if (r1 == 0) goto L_0x0009
            return r0
        L_0x0009:
            com.miui.tsmclient.model.mitsm.TSMSessionManager$BusinessType r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.BusinessType.INSTALL
            java.lang.String r1 = ""
            r2 = 0
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmSessionInfo r0 = r6.getSession(r7, r8, r0)     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            r3 = 0
            java.util.concurrent.atomic.AtomicBoolean r4 = sNeedSync     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            boolean r4 = r4.get()     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            if (r4 == 0) goto L_0x001f
            com.miui.tsmclient.model.BaseResponse r3 = r6.syncEse(r7, r8, r0, r9)     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
        L_0x001f:
            java.util.concurrent.atomic.AtomicBoolean r4 = sNeedSync     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            boolean r4 = r4.get()     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            if (r4 != 0) goto L_0x009a
            boolean r3 = r8.isBankCard()     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            if (r3 == 0) goto L_0x004c
            if (r9 != 0) goto L_0x0034
            android.os.Bundle r9 = new android.os.Bundle     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            r9.<init>()     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
        L_0x0034:
            r3 = r8
            com.miui.tsmclient.entity.BankCardInfo r3 = (com.miui.tsmclient.entity.BankCardInfo) r3     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            java.lang.String r4 = "bankcard_type"
            int r5 = r3.mBankCardType     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            r9.putInt(r4, r5)     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            java.lang.String r4 = "issuer_channel"
            int r5 = r3.mIssuerChannel     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            r9.putInt(r4, r5)     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            java.lang.String r4 = "issuer_id"
            java.lang.String r3 = r3.mIssuerId     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            r9.putString(r4, r3)     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
        L_0x004c:
            com.miui.tsmclient.seitsm.SeiTsmAuthManager r3 = r6.mSeiTsmAuthManager     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmAPDUCommand r9 = r3.preparePayApplet(r7, r0, r9)     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            if (r9 != 0) goto L_0x0068
            java.util.concurrent.atomic.AtomicBoolean r7 = sNeedSync     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            r8 = 1
            r7.set(r8)     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            java.lang.String r7 = "cannnot get apduCommand, preparePayApplet failed."
            com.miui.tsmclient.util.LogUtils.d(r7)     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            com.miui.tsmclient.model.BaseResponse r7 = new com.miui.tsmclient.model.BaseResponse     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            r8 = -1
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            r7.<init>(r8, r9)     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            return r7
        L_0x0068:
            int r3 = r9.getResult()     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            int r3 = com.miui.tsmclient.model.mitsm.MiTsmErrorCode.format(r3)     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            java.lang.String r4 = r9.getErrorDesc()     // Catch:{ NfcEeIOException -> 0x00bd, IOException -> 0x00b4, InterruptedException -> 0x00aa, SeiTSMApiException -> 0x009b }
            if (r3 != 0) goto L_0x00c6
            java.util.List r1 = r9.getApdusList()     // Catch:{ NfcEeIOException -> 0x0098, IOException -> 0x0096, InterruptedException -> 0x0094, SeiTSMApiException -> 0x009b }
            if (r1 == 0) goto L_0x008c
            java.util.List r1 = r9.getApdusList()     // Catch:{ NfcEeIOException -> 0x0098, IOException -> 0x0096, InterruptedException -> 0x0094, SeiTSMApiException -> 0x009b }
            boolean r1 = r1.isEmpty()     // Catch:{ NfcEeIOException -> 0x0098, IOException -> 0x0096, InterruptedException -> 0x0094, SeiTSMApiException -> 0x009b }
            if (r1 == 0) goto L_0x0087
            goto L_0x008c
        L_0x0087:
            com.miui.tsmclient.model.BaseResponse r7 = r6.executeCapdu((android.content.Context) r7, (com.miui.tsmclient.entity.CardInfo) r8, (com.miui.tsmclient.seitsm.TsmRpcModels.TsmSessionInfo) r0, (com.miui.tsmclient.seitsm.TsmRpcModels.TsmAPDUCommand) r9)     // Catch:{ NfcEeIOException -> 0x0098, IOException -> 0x0096, InterruptedException -> 0x0094, SeiTSMApiException -> 0x009b }
            return r7
        L_0x008c:
            com.miui.tsmclient.model.BaseResponse r7 = new com.miui.tsmclient.model.BaseResponse     // Catch:{ NfcEeIOException -> 0x0098, IOException -> 0x0096, InterruptedException -> 0x0094, SeiTSMApiException -> 0x009b }
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch:{ NfcEeIOException -> 0x0098, IOException -> 0x0096, InterruptedException -> 0x0094, SeiTSMApiException -> 0x009b }
            r7.<init>(r2, r8)     // Catch:{ NfcEeIOException -> 0x0098, IOException -> 0x0096, InterruptedException -> 0x0094, SeiTSMApiException -> 0x009b }
            return r7
        L_0x0094:
            r7 = move-exception
            goto L_0x00ac
        L_0x0096:
            r7 = move-exception
            goto L_0x00b6
        L_0x0098:
            r7 = move-exception
            goto L_0x00bf
        L_0x009a:
            return r3
        L_0x009b:
            r7 = move-exception
            int r3 = r7.getErrorCode()
            java.lang.String r4 = r7.getMessage()
            java.lang.String r8 = "preparePayApplet failed with an tsmapi exception."
            com.miui.tsmclient.util.LogUtils.e(r8, r7)
            goto L_0x00c6
        L_0x00aa:
            r7 = move-exception
            r4 = r1
        L_0x00ac:
            r3 = 11
            java.lang.String r8 = "preparePayApplet is interrupted."
            com.miui.tsmclient.util.LogUtils.e(r8, r7)
            goto L_0x00c6
        L_0x00b4:
            r7 = move-exception
            r4 = r1
        L_0x00b6:
            r3 = 2
            java.lang.String r8 = "preparePayApplet failed with an io exception."
            com.miui.tsmclient.util.LogUtils.e(r8, r7)
            goto L_0x00c6
        L_0x00bd:
            r7 = move-exception
            r4 = r1
        L_0x00bf:
            r3 = 10
            java.lang.String r8 = "preparePayApplet failed with an io exception."
            com.miui.tsmclient.util.LogUtils.e(r8, r7)
        L_0x00c6:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "preparePayApplet resultCode = "
            r7.append(r8)
            r7.append(r3)
            java.lang.String r8 = ", resultMsg = "
            r7.append(r8)
            r7.append(r4)
            java.lang.String r7 = r7.toString()
            com.miui.tsmclient.util.LogUtils.e(r7)
            com.miui.tsmclient.model.BaseResponse r7 = new com.miui.tsmclient.model.BaseResponse
            java.lang.Object[] r8 = new java.lang.Object[r2]
            r7.<init>(r3, r4, r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.model.bankcard.BankCardClient.preparePayApplet(android.content.Context, com.miui.tsmclient.entity.CardInfo, android.os.Bundle):com.miui.tsmclient.model.BaseResponse");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0118, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x011b, code lost:
        r13 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x011e, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x011f, code lost:
        r11 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0122, code lost:
        r13 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0123, code lost:
        r2 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0125, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0126, code lost:
        r11 = -2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0129, code lost:
        r13 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x012a, code lost:
        r2 = -2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0161, code lost:
        r13 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x017a, code lost:
        r13 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x018a, code lost:
        r13 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0161 A[ExcHandler: InterruptedException (e java.lang.InterruptedException), PHI: r1 
      PHI: (r1v9 java.lang.String) = (r1v0 java.lang.String), (r1v13 java.lang.String), (r1v13 java.lang.String) binds: [B:1:0x0008, B:10:0x003f, B:15:0x0056] A[DONT_GENERATE, DONT_INLINE], Splitter:B:1:0x0008] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x017a A[ExcHandler: IOException (e java.io.IOException), PHI: r1 
      PHI: (r1v7 java.lang.String) = (r1v0 java.lang.String), (r1v13 java.lang.String), (r1v13 java.lang.String) binds: [B:1:0x0008, B:10:0x003f, B:15:0x0056] A[DONT_GENERATE, DONT_INLINE], Splitter:B:1:0x0008] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x018a A[ExcHandler: NfcEeIOException (e com.tsmclient.smartcard.exception.NfcEeIOException), PHI: r1 
      PHI: (r1v5 java.lang.String) = (r1v0 java.lang.String), (r1v13 java.lang.String), (r1v13 java.lang.String) binds: [B:1:0x0008, B:10:0x003f, B:15:0x0056] A[DONT_GENERATE, DONT_INLINE], Splitter:B:1:0x0008] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.miui.tsmclient.model.BaseResponse pullPersonData(android.content.Context r13, com.miui.tsmclient.entity.CardInfo r14, android.os.Bundle r15) {
        /*
            r12 = this;
            com.miui.tsmclient.model.mitsm.TSMSessionManager$BusinessType r0 = com.miui.tsmclient.model.mitsm.TSMSessionManager.BusinessType.INSTALL
            java.lang.String r1 = ""
            r2 = 10
            r3 = 0
            r4 = -2
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmSessionInfo r9 = r12.getSession(r13, r14, r0)     // Catch:{ NfcEeIOException -> 0x018a, IOException -> 0x017a, InterruptedException -> 0x0161, SeiTSMApiException -> 0x0129, all -> 0x0125 }
            r0 = 0
            java.util.concurrent.atomic.AtomicBoolean r5 = sNeedSync     // Catch:{ NfcEeIOException -> 0x018a, IOException -> 0x017a, InterruptedException -> 0x0161, SeiTSMApiException -> 0x0129, all -> 0x0125 }
            boolean r5 = r5.get()     // Catch:{ NfcEeIOException -> 0x018a, IOException -> 0x017a, InterruptedException -> 0x0161, SeiTSMApiException -> 0x0129, all -> 0x0125 }
            if (r5 == 0) goto L_0x0019
            com.miui.tsmclient.model.BaseResponse r0 = r12.syncEse(r13, r14, r9, r15)     // Catch:{ NfcEeIOException -> 0x018a, IOException -> 0x017a, InterruptedException -> 0x0161, SeiTSMApiException -> 0x0129, all -> 0x0125 }
        L_0x0019:
            java.util.concurrent.atomic.AtomicBoolean r5 = sNeedSync     // Catch:{ NfcEeIOException -> 0x018a, IOException -> 0x017a, InterruptedException -> 0x0161, SeiTSMApiException -> 0x0129, all -> 0x0125 }
            boolean r5 = r5.get()     // Catch:{ NfcEeIOException -> 0x018a, IOException -> 0x017a, InterruptedException -> 0x0161, SeiTSMApiException -> 0x0129, all -> 0x0125 }
            if (r5 == 0) goto L_0x003e
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "pullPersoData end, code = "
            r13.append(r14)
            r13.append(r4)
            java.lang.String r14 = ", errorMsg = "
            r13.append(r14)
            r13.append(r1)
            java.lang.String r13 = r13.toString()
            com.miui.tsmclient.util.LogUtils.d(r13)
            return r0
        L_0x003e:
            r0 = 0
        L_0x003f:
            com.miui.tsmclient.seitsm.SeiTsmAuthManager r5 = r12.mSeiTsmAuthManager     // Catch:{ NfcEeIOException -> 0x018a, IOException -> 0x017a, InterruptedException -> 0x0161, SeiTSMApiException -> 0x0122, all -> 0x011e }
            com.miui.tsmclient.seitsm.TsmRpcModels$TsmAPDUCommand r10 = r5.pullPersonData(r13, r9, r14, r15)     // Catch:{ NfcEeIOException -> 0x018a, IOException -> 0x017a, InterruptedException -> 0x0161, SeiTSMApiException -> 0x0122, all -> 0x011e }
            if (r10 != 0) goto L_0x004e
            java.lang.String r5 = "can not get apdu command, pullPersoData failed."
            com.miui.tsmclient.util.LogUtils.d(r5)     // Catch:{ NfcEeIOException -> 0x018a, IOException -> 0x017a, InterruptedException -> 0x0161, SeiTSMApiException -> 0x0122, all -> 0x011e }
            goto L_0x00f7
        L_0x004e:
            int r5 = r10.getResult()     // Catch:{ NfcEeIOException -> 0x018a, IOException -> 0x017a, InterruptedException -> 0x0161, SeiTSMApiException -> 0x0122, all -> 0x011e }
            int r11 = com.miui.tsmclient.model.mitsm.MiTsmErrorCode.format(r5)     // Catch:{ NfcEeIOException -> 0x018a, IOException -> 0x017a, InterruptedException -> 0x0161, SeiTSMApiException -> 0x0122, all -> 0x011e }
            java.lang.String r4 = r10.getErrorDesc()     // Catch:{ NfcEeIOException -> 0x018a, IOException -> 0x017a, InterruptedException -> 0x0161, SeiTSMApiException -> 0x011b, all -> 0x0118 }
            r1 = 3007(0xbbf, float:4.214E-42)
            if (r11 != r1) goto L_0x007e
            java.lang.String r1 = "pullPersoData, but data not ready"
            com.miui.tsmclient.util.LogUtils.d(r1)     // Catch:{ NfcEeIOException -> 0x007a, IOException -> 0x0076, InterruptedException -> 0x0072, SeiTSMApiException -> 0x006e, all -> 0x006a }
            r5 = 4000(0xfa0, double:1.9763E-320)
            java.lang.Thread.sleep(r5)     // Catch:{ NfcEeIOException -> 0x007a, IOException -> 0x0076, InterruptedException -> 0x0072, SeiTSMApiException -> 0x006e, all -> 0x006a }
            goto L_0x00f3
        L_0x006a:
            r13 = move-exception
            r1 = r4
            goto L_0x01b7
        L_0x006e:
            r13 = move-exception
            r1 = r4
            goto L_0x011c
        L_0x0072:
            r13 = move-exception
            r1 = r4
            goto L_0x0162
        L_0x0076:
            r13 = move-exception
            r1 = r4
            goto L_0x017b
        L_0x007a:
            r13 = move-exception
            r1 = r4
            goto L_0x018b
        L_0x007e:
            if (r11 != 0) goto L_0x00f3
            java.util.List r15 = r10.getApdusList()     // Catch:{ NfcEeIOException -> 0x007a, IOException -> 0x0076, InterruptedException -> 0x0072, SeiTSMApiException -> 0x006e, all -> 0x006a }
            if (r15 == 0) goto L_0x00cf
            java.util.List r15 = r10.getApdusList()     // Catch:{ NfcEeIOException -> 0x007a, IOException -> 0x0076, InterruptedException -> 0x0072, SeiTSMApiException -> 0x006e, all -> 0x006a }
            boolean r15 = r15.isEmpty()     // Catch:{ NfcEeIOException -> 0x007a, IOException -> 0x0076, InterruptedException -> 0x0072, SeiTSMApiException -> 0x006e, all -> 0x006a }
            if (r15 != 0) goto L_0x00cf
            com.tsmclient.smartcard.terminal.IScTerminal r15 = r14.getTerminal()     // Catch:{ NfcEeIOException -> 0x007a, IOException -> 0x0076, InterruptedException -> 0x0072, SeiTSMApiException -> 0x006e, all -> 0x006a }
            r15.open()     // Catch:{ all -> 0x00ca }
            r5 = r12
            r6 = r13
            r7 = r14
            r8 = r15
            com.miui.tsmclient.model.BaseResponse r13 = r5.processUPCmd(r6, r7, r8, r9, r10)     // Catch:{ all -> 0x00ca }
            int r14 = r13.mResultCode     // Catch:{ all -> 0x00ca }
            java.lang.String r1 = r13.mMsg     // Catch:{ all -> 0x00c7 }
            r15.close()     // Catch:{ NfcEeIOException -> 0x018a, IOException -> 0x017a, InterruptedException -> 0x0161, SeiTSMApiException -> 0x00c3 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r0 = "pullPersoData end, code = "
            r15.append(r0)
            r15.append(r14)
            java.lang.String r14 = ", errorMsg = "
            r15.append(r14)
            r15.append(r1)
            java.lang.String r14 = r15.toString()
            com.miui.tsmclient.util.LogUtils.d(r14)
            return r13
        L_0x00c3:
            r13 = move-exception
            r2 = r14
            goto L_0x012b
        L_0x00c7:
            r13 = move-exception
            r11 = r14
            goto L_0x00cb
        L_0x00ca:
            r13 = move-exception
        L_0x00cb:
            r15.close()     // Catch:{ NfcEeIOException -> 0x007a, IOException -> 0x0076, InterruptedException -> 0x0072, SeiTSMApiException -> 0x006e, all -> 0x006a }
            throw r13     // Catch:{ NfcEeIOException -> 0x007a, IOException -> 0x0076, InterruptedException -> 0x0072, SeiTSMApiException -> 0x006e, all -> 0x006a }
        L_0x00cf:
            com.miui.tsmclient.model.BaseResponse r13 = new com.miui.tsmclient.model.BaseResponse     // Catch:{ NfcEeIOException -> 0x007a, IOException -> 0x0076, InterruptedException -> 0x0072, SeiTSMApiException -> 0x006e, all -> 0x006a }
            java.lang.Object[] r14 = new java.lang.Object[r3]     // Catch:{ NfcEeIOException -> 0x007a, IOException -> 0x0076, InterruptedException -> 0x0072, SeiTSMApiException -> 0x006e, all -> 0x006a }
            r13.<init>(r11, r14)     // Catch:{ NfcEeIOException -> 0x007a, IOException -> 0x0076, InterruptedException -> 0x0072, SeiTSMApiException -> 0x006e, all -> 0x006a }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "pullPersoData end, code = "
            r14.append(r15)
            r14.append(r11)
            java.lang.String r15 = ", errorMsg = "
            r14.append(r15)
            r14.append(r4)
            java.lang.String r14 = r14.toString()
            com.miui.tsmclient.util.LogUtils.d(r14)
            return r13
        L_0x00f3:
            int r0 = r0 + 1
            r1 = r4
            r4 = r11
        L_0x00f7:
            if (r0 < r2) goto L_0x003f
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "pullPersoData end, code = "
            r13.append(r14)
            r13.append(r4)
            java.lang.String r14 = ", errorMsg = "
            r13.append(r14)
            r13.append(r1)
            java.lang.String r13 = r13.toString()
            com.miui.tsmclient.util.LogUtils.d(r13)
            r2 = r4
            goto L_0x01ac
        L_0x0118:
            r13 = move-exception
            goto L_0x01b7
        L_0x011b:
            r13 = move-exception
        L_0x011c:
            r2 = r11
            goto L_0x012b
        L_0x011e:
            r13 = move-exception
            r11 = r4
            goto L_0x01b7
        L_0x0122:
            r13 = move-exception
            r2 = r4
            goto L_0x012b
        L_0x0125:
            r13 = move-exception
            r11 = -2
            goto L_0x01b7
        L_0x0129:
            r13 = move-exception
            r2 = -2
        L_0x012b:
            int r14 = r13.getErrorCode()     // Catch:{ all -> 0x015e }
            java.lang.String r15 = r13.getMessage()     // Catch:{ all -> 0x015b }
            java.lang.String r0 = "pullPersoData failed with an tsmapi exception."
            com.miui.tsmclient.util.LogUtils.e(r0, r13)     // Catch:{ all -> 0x0157 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r0 = "pullPersoData end, code = "
            r13.append(r0)
            r13.append(r14)
            java.lang.String r0 = ", errorMsg = "
            r13.append(r0)
            r13.append(r15)
            java.lang.String r13 = r13.toString()
            com.miui.tsmclient.util.LogUtils.d(r13)
            r2 = r14
            r1 = r15
            goto L_0x01ac
        L_0x0157:
            r13 = move-exception
            r11 = r14
            r1 = r15
            goto L_0x01b7
        L_0x015b:
            r13 = move-exception
            r11 = r14
            goto L_0x01b7
        L_0x015e:
            r13 = move-exception
            r11 = r2
            goto L_0x01b7
        L_0x0161:
            r13 = move-exception
        L_0x0162:
            r2 = 11
            java.lang.Thread r14 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0176 }
            r14.interrupt()     // Catch:{ all -> 0x0176 }
            java.lang.String r14 = "pullPersoData is interrupted."
            com.miui.tsmclient.util.LogUtils.e(r14, r13)     // Catch:{ all -> 0x0176 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            goto L_0x0195
        L_0x0176:
            r13 = move-exception
            r11 = 11
            goto L_0x01b7
        L_0x017a:
            r13 = move-exception
        L_0x017b:
            r2 = 2
            java.lang.String r14 = "pullPersoData failed with an io exception."
            com.miui.tsmclient.util.LogUtils.e(r14, r13)     // Catch:{ all -> 0x0187 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            goto L_0x0195
        L_0x0187:
            r13 = move-exception
            r11 = 2
            goto L_0x01b7
        L_0x018a:
            r13 = move-exception
        L_0x018b:
            java.lang.String r14 = "preparePayApplet failed with an io exception."
            com.miui.tsmclient.util.LogUtils.e(r14, r13)     // Catch:{ all -> 0x01b4 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
        L_0x0195:
            java.lang.String r14 = "pullPersoData end, code = "
            r13.append(r14)
            r13.append(r2)
            java.lang.String r14 = ", errorMsg = "
            r13.append(r14)
            r13.append(r1)
            java.lang.String r13 = r13.toString()
            com.miui.tsmclient.util.LogUtils.d(r13)
        L_0x01ac:
            com.miui.tsmclient.model.BaseResponse r13 = new com.miui.tsmclient.model.BaseResponse
            java.lang.Object[] r14 = new java.lang.Object[r3]
            r13.<init>(r2, r1, r14)
            return r13
        L_0x01b4:
            r13 = move-exception
            r11 = 10
        L_0x01b7:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "pullPersoData end, code = "
            r14.append(r15)
            r14.append(r11)
            java.lang.String r15 = ", errorMsg = "
            r14.append(r15)
            r14.append(r1)
            java.lang.String r14 = r14.toString()
            com.miui.tsmclient.util.LogUtils.d(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.model.bankcard.BankCardClient.pullPersonData(android.content.Context, com.miui.tsmclient.entity.CardInfo, android.os.Bundle):com.miui.tsmclient.model.BaseResponse");
    }

    public static void updateCardInfoFromServer(BankCardInfo bankCardInfo, TsmRpcModels.VirtualCardInfoResponse virtualCardInfoResponse) {
        if (virtualCardInfoResponse != null) {
            bankCardInfo.mVCReferenceId = virtualCardInfoResponse.getVirtualCardReferenceId();
            bankCardInfo.mVCardNumber = virtualCardInfoResponse.getVirtualCardNumber();
            bankCardInfo.mPanLastDigits = virtualCardInfoResponse.getLastDigits();
            bankCardInfo.mAid = virtualCardInfoResponse.getAid();
            bankCardInfo.mPhoneLastDigits = virtualCardInfoResponse.getPhoneNumLastDigits();
            bankCardInfo.mCardFrontColor = virtualCardInfoResponse.getFrontColor();
            bankCardInfo.mCardArt = virtualCardInfoResponse.getCardArt();
        }
    }

    public static List<BankCardInfo> fillFromTsm(List<TsmRpcModels.BankCardInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (TsmRpcModels.BankCardInfo next : list) {
            BankCardInfo bankCardInfo = new BankCardInfo();
            bankCardInfo.mAid = next.getAid();
            bankCardInfo.mVCReferenceId = next.getVirtualCardReferenceId();
            bankCardInfo.mVCardNumber = next.getVirtualCardNumber();
            bankCardInfo.mPanLastDigits = next.getLastDigits();
            bankCardInfo.mVCStatus = next.getVcStatus();
            bankCardInfo.mCardProductTypeId = next.getProductId();
            bankCardInfo.mCardProductName = next.getProductName();
            bankCardInfo.mUserTerms = next.getUserTerms();
            bankCardInfo.mCardArt = next.getCardArt();
            bankCardInfo.mBankCardType = next.getCardType();
            TsmRpcModels.CardIssuerInfo issuerInfo = next.getIssuerInfo();
            bankCardInfo.mBankName = issuerInfo.getBankName();
            bankCardInfo.mIssuerId = issuerInfo.getIssuerId();
            bankCardInfo.mIssuerChannel = issuerInfo.getCardIssueChannel().getNumber();
            bankCardInfo.mBankLogoUrl = issuerInfo.getLogoUrl();
            bankCardInfo.mBankLogoWithNameUrl = issuerInfo.getLogoWithBankNameUrl();
            bankCardInfo.mBankContactNum = issuerInfo.getContactNumber();
            bankCardInfo.mCardFrontColor = next.getFrontColor();
            arrayList.add(bankCardInfo);
        }
        return arrayList;
    }

    private BaseResponse processUPCmd(Context context, CardInfo cardInfo, IScTerminal iScTerminal, TsmRpcModels.TsmSessionInfo tsmSessionInfo, TsmRpcModels.TsmAPDUCommand tsmAPDUCommand) throws IOException, SeiTSMApiException, InterruptedException {
        boolean z;
        List<TsmRpcModels.TsmCAPDU> apdusList = tsmAPDUCommand.getApdusList();
        if (apdusList == null || apdusList.isEmpty()) {
            LogUtils.e("can not get apduCommand, processUPCmd failed. errorCode:" + tsmAPDUCommand.getResult() + ", errorMsg:" + tsmAPDUCommand.getErrorDesc());
            throw new SeiTSMApiException(tsmAPDUCommand.getResult(), tsmAPDUCommand.getErrorDesc());
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (TsmRpcModels.TsmCAPDU next : apdusList) {
            ScResponse transmit = iScTerminal.transmit(next.getApdu().toByteArray());
            i++;
            arrayList.add(TsmRpcModels.SeAPDUResponseItem.newBuilder().setIndex(i).setResponseData(ByteString.copyFrom(transmit.getData().toBytes())).setResponseSw(ByteString.copyFrom(transmit.getStatus().toBytes())).build());
            if (TextUtils.isEmpty(next.getExpectSwRegex())) {
                LogUtils.d("processUPCmd: no expected sw.");
            } else {
                Pattern compile = Pattern.compile(next.getExpectSwRegex());
                String bytesToHexString = Coder.bytesToHexString(transmit.getStatus().toBytes());
                if (!compile.matcher(bytesToHexString).matches()) {
                    LogUtils.d("processUPCmd failed, expected:" + next.getExpectSwRegex() + " but:" + bytesToHexString);
                }
            }
            z = false;
        }
        z = true;
        TsmRpcModels.CommonResponse persoFinishNotify = this.mSeiTsmAuthManager.persoFinishNotify(context, cardInfo, z, arrayList, tsmSessionInfo);
        if (!z) {
            return new BaseResponse(8, new Object[0]);
        }
        if (persoFinishNotify == null) {
            LogUtils.d("processUPCmd ： commonResponse is null.");
            return new BaseResponse(16, new Object[0]);
        } else if (persoFinishNotify.getResult() == 0) {
            return new BaseResponse(0, new Object[0]);
        } else {
            LogUtils.e("persoFinishNotify errorCode:" + persoFinishNotify.getResult() + ", errorMsg:" + persoFinishNotify.getErrorDesc());
            return new BaseResponse(MiTsmErrorCode.format(persoFinishNotify.getResult()), persoFinishNotify.getErrorDesc(), new Object[0]);
        }
    }

    public BaseResponse deleteCards(Context context) {
        int i;
        String str = "";
        try {
            TsmRpcModels.CommonResponse deleteAllBankCards = this.mSeiTsmAuthManager.deleteAllBankCards(context);
            if (deleteAllBankCards != null) {
                int format = MiTsmErrorCode.format(deleteAllBankCards.getResult());
                str = deleteAllBankCards.getErrorDesc();
                i = format;
            } else {
                i = -2;
            }
            LogUtils.d("deleteCards result: " + i);
        } catch (SeiTSMApiException e) {
            int errorCode = e.getErrorCode();
            String message = e.getMessage();
            LogUtils.e("failed to delete all bank cards", e);
            i = errorCode;
            str = message;
        }
        return new BaseResponse(i, str, new Object[0]);
    }

    public BaseResponse isServiceAvailable(Context context, CardInfo cardInfo, Bundle bundle) {
        return isBankCardServiceAvailable(context);
    }

    public BaseResponse isBankCardServiceAvailable(Context context) {
        int i;
        String str = "";
        try {
            TsmRpcModels.CommonResponse isBankCardServiceAvailable = this.mSeiTsmAuthManager.isBankCardServiceAvailable(context);
            if (isBankCardServiceAvailable == null) {
                i = -1;
            } else {
                i = MiTsmErrorCode.format(isBankCardServiceAvailable.getResult());
                str = isBankCardServiceAvailable.getErrorDesc();
            }
            LogUtils.d("isBankCardServiceAvailable result: " + i + ", msg:" + str);
            if (i == 0) {
                return new BaseResponse(i, isBankCardServiceAvailable);
            }
        } catch (SeiTSMApiException e) {
            i = e.getErrorCode();
            str = e.getMessage();
            LogUtils.e("failed to check bank card service.", e);
        }
        return new BaseResponse(i, str, new Object[0]);
    }

    public BaseResponse requestInAppPay(Context context, CardInfo cardInfo, InAppTransData inAppTransData) {
        int i;
        String str = "";
        try {
            BaseResponse saveAppKey = saveAppKey(context);
            if (saveAppKey.mResultCode != 0) {
                LogUtils.w("save app key occurred error, so failed to request inapp pay");
                return saveAppKey;
            }
            TsmRpcModels.InAppTransCommand requestInappTransCommand = this.mSeiTsmAuthManager.requestInappTransCommand(context, inAppTransData);
            if (requestInappTransCommand != null) {
                int format = MiTsmErrorCode.format(requestInappTransCommand.getResult());
                String errorDesc = requestInappTransCommand.getErrorDesc();
                if (format == 0) {
                    inAppTransData.setMiTsmSignedData(requestInappTransCommand.getSign());
                    inAppTransData.setMiTsmSignKeyIndex(requestInappTransCommand.getKeyIndex());
                    return transmitInAppCommand(cardInfo, inAppTransData, requestInappTransCommand.getApdus());
                }
                i = format;
                str = errorDesc;
            } else {
                i = -1;
            }
            LogUtils.d("requestInAppPay result: " + i);
            return new BaseResponse(i, str, new Object[0]);
        } catch (SeiTSMApiException e) {
            int errorCode = e.getErrorCode();
            str = e.getMessage();
            LogUtils.e("failed to request inapp pay.", e);
            i = errorCode;
        }
    }

    public int getMipayStatus(Context context) {
        NfcConfigsResponse.NfcConfigs createConfigFromFile = NfcConfigsResponse.NfcConfigs.createConfigFromFile(context);
        if (createConfigFromFile == null || createConfigFromFile.isInAppConfigExpired(context)) {
            LogUtils.d("nfc configs got from cache is null or expired");
            createConfigFromFile = NfcConfigsResponse.NfcConfigs.fetchNfcConfigFromServer(context);
        }
        int i = 0;
        if (createConfigFromFile == null || createConfigFromFile.getInAppConfig() == null) {
            LogUtils.d("nfc configs got from server is null");
            return 0;
        }
        if (createConfigFromFile.getInAppConfig().isSupportInappPay()) {
            i = 2;
        }
        return createConfigFromFile.getInAppConfig().isSupportInappIssue() ? i + 4 : i;
    }

    public BaseResponse notifyInappPayResult(Context context, int i, InAppTransData inAppTransData) {
        int i2;
        String str = "";
        try {
            TsmRpcModels.CommonResponse notifyResult = this.mSeiTsmAuthManager.notifyResult(context, i, inAppTransData);
            if (notifyResult != null) {
                i2 = MiTsmErrorCode.format(notifyResult.getResult());
                str = notifyResult.getErrorDesc();
            } else {
                i2 = -1;
            }
        } catch (SeiTSMApiException e) {
            i2 = e.getErrorCode();
            str = e.getMessage();
            LogUtils.e("failed to notify inapp pay result", e);
        }
        LogUtils.d("notifyInappPayResult result: " + i2);
        return new BaseResponse(i2, str, new Object[0]);
    }

    private BaseResponse transmitInAppCommand(CardInfo cardInfo, InAppTransData inAppTransData, TsmRpcModels.TsmCAPDU tsmCAPDU) {
        int i;
        IScTerminal terminal = cardInfo.getTerminal();
        try {
            terminal.open();
            if (ByteArray.equals(ScResponse.STATUS_OK, terminal.transmit(APDUConstants.SELECT_CRS).getStatus())) {
                ScResponse transmit = terminal.transmit(tsmCAPDU.getApdu().toByteArray());
                if (ByteArray.equals(ScResponse.STATUS_OK, transmit.getStatus()) && transmit.getData() != null) {
                    inAppTransData.fillTransResponseData(transmit.getData());
                    BaseResponse baseResponse = new BaseResponse(0, inAppTransData);
                    terminal.close();
                    return baseResponse;
                }
            }
            terminal.close();
            i = -1;
        } catch (IOException e) {
            i = 10;
            LogUtils.e("transmitInAppCommand failed with an IOException.", e);
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            i = 11;
            LogUtils.e("transmitInAppCommand is interrupted.", e2);
        } catch (Throwable th) {
            terminal.close();
            throw th;
        }
        return new BaseResponse(i, new Object[0]);
        terminal.close();
        return new BaseResponse(i, new Object[0]);
    }

    private void updateLocalBankCards(Context context, CardInfo cardInfo) {
        Intent intent = new Intent(Constants.ACTION_QUERY_CARDS);
        intent.setPackage(Constants.PACKAGE_NAME_NEXTPAY);
        intent.putExtra("card_type", cardInfo.mCardType);
        ServiceUtils.startService(context, intent);
    }
}
