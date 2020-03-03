package com.miui.tsmclient.model.fmsh;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import cn.com.fmsh.nfcos.client.service.xm.CardAppInfo;
import cn.com.fmsh.nfcos.client.service.xm.CardAppRecord;
import cn.com.fmsh.nfcos.client.service.xm.NfcosBusinessOrder;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import cn.com.fmsh.tsm.business.enums.EnumRechargeMode;
import com.mi.global.shop.model.Tags;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.miui.tsmclient.entity.ActionToken;
import com.miui.tsmclient.entity.FmshCardInfo;
import com.miui.tsmclient.entity.UserExceptionLogInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.PayableCardClient;
import com.miui.tsmclient.model.PayableCardOperation;
import com.miui.tsmclient.model.UploadUserExceptionLogModel;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.pay.PayType;
import com.miui.tsmclient.util.Constants;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.NetworkUtil;
import com.miui.tsmclient.util.ResUtils;
import com.miui.tsmclient.util.SysUtils;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.model.TradeLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FmshCardOperation extends PayableCardOperation<FmshCardInfo> {
    public static final String TAG = "FmshCardOperation";
    private FmshCardClient mFmshCardClient = new FmshCardClient();

    private static int getTradeType(int i) {
        return (i == 6 || i == 12) ? 2 : 1;
    }

    public FmshCardOperation(String str) {
        super(str);
    }

    public BaseResponse updateCardInfo(Context context, FmshCardInfo fmshCardInfo) {
        return updateCardInfo(context, fmshCardInfo, false);
    }

    private BaseResponse updateCardInfo(Context context, FmshCardInfo fmshCardInfo, boolean z) {
        boolean z2;
        Context context2 = context;
        FmshCardInfo fmshCardInfo2 = fmshCardInfo;
        boolean z3 = true;
        if (fmshCardInfo.getUnfinishTransferOutInfo() != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(Constants.EXTRAS_KEY_WITHDRAW, true);
            if (preTransferOut(context2, fmshCardInfo2, bundle).isSuccess()) {
                transferOut(context2, fmshCardInfo2, bundle);
            }
        }
        List list = null;
        BaseResponse queryCardInfo = queryCardInfo(context2, fmshCardInfo2, (Bundle) null);
        if (!z || queryCardInfo.mResultCode != 0 || !fmshCardInfo2.mHasIssue || !fmshCardInfo.hasUnfinishedOrder()) {
            LogUtils.d(" unfinishedOrders size:" + fmshCardInfo2.mUnfinishOrderInfos.size() + ", resultCode:" + queryCardInfo.mResultCode);
            return queryCardInfo;
        }
        int cardAppType = FmshCardClient.getCardAppType(fmshCardInfo2.mCardType);
        BaseResponse queryUnsolvedOrder = this.mFmshCardClient.queryUnsolvedOrder(context2, cardAppType, (Tag) null);
        if (queryUnsolvedOrder.mResultCode == 0 && queryUnsolvedOrder.mDatas != null && (queryUnsolvedOrder.mDatas[0] instanceof List)) {
            list = (List) queryUnsolvedOrder.mDatas[0];
        }
        List list2 = list;
        if (list2 == null || list2.isEmpty()) {
            z2 = true;
            for (OrderInfo orderInfo : fmshCardInfo2.mUnfinishOrderInfos) {
                Iterator<ActionToken> it = orderInfo.mActionTokens.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ActionToken next = it.next();
                    if (next.mType == ActionToken.TokenType.recharge) {
                        BaseResponse recharge = this.mFmshCardClient.recharge(context, cardAppType, next, fmshCardInfo2.mAppNo, getRechargeType(orderInfo), (Tag) null);
                        if (recharge.mResultCode != 0) {
                            LogUtils.d("handle unfinished order failed.error:" + recharge.mResultCode);
                            recharge.mResultCode = 1003;
                            queryCardInfo = recharge;
                            z2 = false;
                            continue;
                            break;
                        }
                        queryCardInfo = recharge;
                    }
                }
                if (!z2) {
                    break;
                }
            }
        } else {
            LogUtils.d("unsolvedBusinessOrders size:" + list2.size() + ", unfinishedOrders:" + fmshCardInfo2.mUnfinishOrderInfos.size());
            Iterator it2 = list2.iterator();
            boolean z4 = false;
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                NfcosBusinessOrder nfcosBusinessOrder = (NfcosBusinessOrder) it2.next();
                if (nfcosBusinessOrder.tradeState == EnumOrderStatus.dubious.getId()) {
                    z4 = true;
                } else {
                    queryCardInfo = this.mFmshCardClient.handleUnsolvedOrder(context, cardAppType, fmshCardInfo, (Tag) null, nfcosBusinessOrder);
                    if (queryCardInfo.mResultCode != 0) {
                        LogUtils.d("handleUnsolvedOrder failed.error:" + queryCardInfo.mResultCode);
                        queryCardInfo.mResultCode = 1003;
                        z3 = false;
                        break;
                    }
                }
            }
            if (z3 && list2.size() != fmshCardInfo2.mUnfinishOrderInfos.size()) {
                return new BaseResponse(1010, new Object[0]);
            }
            if (z3 && z4) {
                queryCardInfo = new BaseResponse(1004, "has confirm doubt order which orderstate is 12", new Object[0]);
            }
            z2 = z3;
        }
        if (z2) {
            return updateCardInfo(context2, fmshCardInfo2, false);
        }
        updateCardInfo(context2, fmshCardInfo2, false);
        return queryCardInfo;
    }

    public BaseResponse deleteCard(Context context, FmshCardInfo fmshCardInfo, Bundle bundle) {
        if (context == null) {
            LogUtils.e("fmsh deleteCard called! param is invalid, context = null");
            return new BaseResponse(1, new Object[0]);
        } else if (!NetworkUtil.isConnected(context)) {
            return new BaseResponse(2, new Object[0]);
        } else {
            if (fmshCardInfo == null || fmshCardInfo.getUnfinishTransferOutInfo() == null || TextUtils.isEmpty(fmshCardInfo.getUnfinishTransferOutInfo().getTransferOutToken())) {
                return new BaseResponse(1, ResUtils.getString(context, "service_unavailable"), new Object[0]);
            }
            UserExceptionLogInfo userExceptionLogInfo = new UserExceptionLogInfo();
            userExceptionLogInfo.setObjectName(fmshCardInfo.mCardType);
            String transferOutToken = fmshCardInfo.getUnfinishTransferOutInfo().getTransferOutToken();
            LogUtils.d("fmsh deleteCard called! transferOutToken:" + transferOutToken);
            byte[] hexStringToBytes = Coder.hexStringToBytes(transferOutToken);
            try {
                byte[] seid = this.mFmshCardClient.getSeid(context);
                Context context2 = context;
                BaseResponse moveApp = this.mFmshCardClient.moveApp(context2, hexStringToBytes, FmshCardClient.getCardAppType(fmshCardInfo.mCardType), seid, SysUtils.getDeviceModel(fmshCardInfo), fmshCardInfo, bundle);
                if (!moveApp.isSuccess()) {
                    userExceptionLogInfo.setCoreOperation("moveApp");
                    userExceptionLogInfo.setErrorCode(moveApp.mResultCode);
                    userExceptionLogInfo.setErrorDesc(moveApp.mMsg);
                    UploadUserExceptionLogModel.create(context).uploadUserExceptionLog(userExceptionLogInfo);
                }
                return moveApp;
            } catch (IOException e) {
                LogUtils.e("get seid failed!", e);
                userExceptionLogInfo.setCoreOperation("getSeid");
                userExceptionLogInfo.setExtra(e.getMessage());
                UploadUserExceptionLogModel.create(context).uploadUserExceptionLog(userExceptionLogInfo);
                return new BaseResponse(1, ResUtils.getString(context, "service_unavailable"), new Object[0]);
            }
        }
    }

    public BaseResponse recharge(Context context, FmshCardInfo fmshCardInfo, OrderInfo orderInfo, Tag tag, Bundle bundle) {
        ActionToken actionToken;
        BaseResponse baseResponse;
        Context context2 = context;
        FmshCardInfo fmshCardInfo2 = fmshCardInfo;
        OrderInfo orderInfo2 = orderInfo;
        if (!NetworkUtil.isConnected(context)) {
            return new BaseResponse(2, new Object[0]);
        }
        UserExceptionLogInfo userExceptionLogInfo = new UserExceptionLogInfo();
        userExceptionLogInfo.setObjectName(fmshCardInfo2.mCardType);
        long currentTimeMillis = System.currentTimeMillis();
        int cardAppType = FmshCardClient.getCardAppType(fmshCardInfo2.mCardType);
        Iterator<ActionToken> it = orderInfo2.mActionTokens.iterator();
        while (true) {
            if (!it.hasNext()) {
                actionToken = null;
                break;
            }
            ActionToken next = it.next();
            if (fmshCardInfo2.mHasIssue && next.mType == ActionToken.TokenType.recharge) {
                actionToken = next;
                break;
            }
        }
        BaseResponse queryUnsolvedOrder = this.mFmshCardClient.queryUnsolvedOrder(context2, cardAppType, tag);
        if (!queryUnsolvedOrder.isSuccess()) {
            userExceptionLogInfo.setCoreOperation("queryUnsolvedOrder");
            userExceptionLogInfo.setErrorCode(queryUnsolvedOrder.mResultCode);
            userExceptionLogInfo.setErrorDesc(queryUnsolvedOrder.mMsg);
            UploadUserExceptionLogModel.create(context).uploadUserExceptionLog(userExceptionLogInfo);
            LogUtils.d("fmsh queryUnsolvedOrder called! resultCode:" + queryUnsolvedOrder.mResultCode);
        } else if (queryUnsolvedOrder.mDatas != null && !((List) queryUnsolvedOrder.mDatas[0]).isEmpty()) {
            for (NfcosBusinessOrder nfcosBusinessOrder : (List) queryUnsolvedOrder.mDatas[0]) {
                BaseResponse baseResponse2 = queryUnsolvedOrder;
                BaseResponse handleUnsolvedOrder = this.mFmshCardClient.handleUnsolvedOrder(context, cardAppType, fmshCardInfo, tag, nfcosBusinessOrder);
                if (!handleUnsolvedOrder.isSuccess()) {
                    LogUtils.d("fmsh handleUnsolvedOrder called! resultCode:" + handleUnsolvedOrder.mResultCode);
                    if (handleUnsolvedOrder.mResultCode == 2002 && handleUnsolvedOrder.mDatas != null) {
                        handleUnsolvedOrder.mDatas = new Object[]{nfcosBusinessOrder.faceNo};
                    }
                    AnalyticManager.getInstance().bugReport(context2, AnalyticManager.CATEGORY_FMSH, currentTimeMillis);
                    userExceptionLogInfo.setCoreOperation("handleUnsolvedOrder");
                    baseResponse = baseResponse2;
                    userExceptionLogInfo.setErrorCode(baseResponse.mResultCode);
                    userExceptionLogInfo.setErrorDesc(baseResponse.mMsg);
                    UploadUserExceptionLogModel.create(context).uploadUserExceptionLog(userExceptionLogInfo);
                } else {
                    baseResponse = baseResponse2;
                }
                queryUnsolvedOrder = baseResponse;
                Tag tag2 = tag;
            }
        }
        BaseResponse baseResponse3 = queryUnsolvedOrder;
        FmshCardClient fmshCardClient = this.mFmshCardClient;
        byte[] bArr = fmshCardInfo2.mAppNo;
        int rechargeType = getRechargeType(orderInfo2);
        BaseResponse baseResponse4 = baseResponse3;
        BaseResponse recharge = fmshCardClient.recharge(context, cardAppType, actionToken, bArr, rechargeType, tag);
        if (!recharge.isSuccess()) {
            AnalyticManager.getInstance().bugReport(context2, AnalyticManager.CATEGORY_FMSH, currentTimeMillis);
            userExceptionLogInfo.setCoreOperation(Tags.Lottery.NATIVE_TYPE_RECHARGE);
            userExceptionLogInfo.setExtra(actionToken != null ? actionToken.mToken : "");
            userExceptionLogInfo.setErrorCode(baseResponse4.mResultCode);
            userExceptionLogInfo.setErrorDesc(baseResponse4.mMsg);
            UploadUserExceptionLogModel.create(context).uploadUserExceptionLog(userExceptionLogInfo);
        }
        return recharge;
    }

    public BaseResponse issue(Context context, FmshCardInfo fmshCardInfo, Bundle bundle) {
        byte b;
        byte[] bArr;
        Context context2 = context;
        FmshCardInfo fmshCardInfo2 = fmshCardInfo;
        if (!NetworkUtil.isConnected(context)) {
            return new BaseResponse(2, new Object[0]);
        }
        UserExceptionLogInfo userExceptionLogInfo = new UserExceptionLogInfo();
        userExceptionLogInfo.setObjectName(fmshCardInfo2.mCardType);
        long currentTimeMillis = System.currentTimeMillis();
        int cardAppType = FmshCardClient.getCardAppType(fmshCardInfo2.mCardType);
        if (EnumCardAppType.CARD_APP_TYPE_SH.getId() == cardAppType) {
            b = 1;
        } else {
            b = EnumCardAppType.CARD_APP_TYPE_LNT.getId() == cardAppType ? (byte) 3 : 0;
        }
        try {
            bArr = this.mFmshCardClient.getSeid(context2);
        } catch (IOException e) {
            LogUtils.e("get seid failed!", e);
            bArr = null;
        }
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        boolean z = !bundle2.getBoolean("pre_load");
        bundle2.putBoolean("pre_load", true);
        BaseResponse issue = ((PayableCardClient) this.mMiTSMCardClient).issue(context2, fmshCardInfo2, bundle2);
        LogUtils.d("install fmsh sd result:" + issue.mResultCode);
        if (issue.mResultCode != 0) {
            return issue;
        }
        if (!z) {
            BaseResponse downloadApplet = this.mFmshCardClient.downloadApplet(context2, cardAppType, bArr, SysUtils.getDeviceModel(fmshCardInfo));
            if (!downloadApplet.isSuccess()) {
                userExceptionLogInfo.setCoreOperation("downloadApplet");
                userExceptionLogInfo.setErrorCode(downloadApplet.mResultCode);
                userExceptionLogInfo.setErrorDesc(downloadApplet.mMsg);
                UploadUserExceptionLogModel.create(context).uploadUserExceptionLog(userExceptionLogInfo);
            }
            return downloadApplet;
        } else if (fmshCardInfo2.mUnfinishOrderInfos == null) {
            return new BaseResponse(1, new Object[0]);
        } else {
            BaseResponse queryUnsolvedOrder = this.mFmshCardClient.queryUnsolvedOrder(context2, cardAppType, (Tag) null);
            if (queryUnsolvedOrder.mResultCode == 0 && queryUnsolvedOrder.mDatas != null && !((List) queryUnsolvedOrder.mDatas[0]).isEmpty()) {
                for (NfcosBusinessOrder nfcosBusinessOrder : (List) queryUnsolvedOrder.mDatas[0]) {
                    if (nfcosBusinessOrder.businessOrderType == EnumBusinessOrderType.ORDER_TYPE_ISSUE.getId()) {
                        BaseResponse doIssue = this.mFmshCardClient.doIssue(context, cardAppType, nfcosBusinessOrder.order, b, bArr, (byte[]) null, (Bundle) null);
                        LogUtils.d("fmsh doIssue result:" + doIssue.mResultCode);
                        if (!doIssue.isSuccess()) {
                            AnalyticManager.getInstance().bugReport(context2, AnalyticManager.CATEGORY_FMSH, currentTimeMillis);
                            userExceptionLogInfo.setCoreOperation("doIssue");
                            userExceptionLogInfo.setErrorCode(doIssue.mResultCode);
                            userExceptionLogInfo.setErrorDesc(doIssue.mMsg);
                            UploadUserExceptionLogModel.create(context).uploadUserExceptionLog(userExceptionLogInfo);
                        }
                        return doIssue;
                    }
                }
            }
            int id = EnumRechargeMode.MIPAY_MI.getId();
            int i = id;
            ActionToken actionToken = null;
            for (OrderInfo orderInfo : fmshCardInfo2.mUnfinishOrderInfos) {
                Iterator<ActionToken> it = orderInfo.mActionTokens.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ActionToken next = it.next();
                    if (next.shouldAutoIssueOrWithdraw()) {
                        i = getRechargeType(orderInfo);
                        actionToken = next;
                        break;
                    }
                }
            }
            BaseResponse doIssueEx = this.mFmshCardClient.doIssueEx(context, cardAppType, i, SysUtils.getDeviceModel(fmshCardInfo), bArr, actionToken, fmshCardInfo2.mIssueFee, bundle2);
            LogUtils.d("fmsh doIssueEx result:" + doIssueEx.mResultCode);
            if (!doIssueEx.isSuccess()) {
                AnalyticManager.getInstance().bugReport(context2, AnalyticManager.CATEGORY_FMSH, currentTimeMillis);
                userExceptionLogInfo.setCoreOperation("doIssueEx");
                userExceptionLogInfo.setExtra(actionToken != null ? actionToken.mToken : "");
                userExceptionLogInfo.setErrorCode(doIssueEx.mResultCode);
                userExceptionLogInfo.setErrorDesc(doIssueEx.mMsg);
                UploadUserExceptionLogModel.create(context).uploadUserExceptionLog(userExceptionLogInfo);
            }
            return doIssueEx;
        }
    }

    public BaseResponse transferIn(Context context, FmshCardInfo fmshCardInfo, Bundle bundle) {
        return issue(context, fmshCardInfo, bundle);
    }

    public BaseResponse isServiceAvailable(Context context, FmshCardInfo fmshCardInfo, Bundle bundle) {
        return new BaseResponse(0, new Object[0]);
    }

    public BaseResponse queryPurchaseRecord(Context context, FmshCardInfo fmshCardInfo) {
        BaseResponse info = this.mFmshCardClient.getInfo(context, FmshCardClient.getCardAppType(fmshCardInfo.mCardType), 4);
        if (info.mResultCode != 0) {
            return info;
        }
        CardAppInfo cardAppInfo = (CardAppInfo) info.mDatas[0];
        ArrayList arrayList = new ArrayList();
        if (cardAppInfo.records != null) {
            for (CardAppRecord convertToTradeLog : cardAppInfo.records) {
                arrayList.add(convertToTradeLog(convertToTradeLog));
            }
        }
        fmshCardInfo.mTradeLogs = arrayList;
        return new BaseResponse(info.mResultCode, fmshCardInfo);
    }

    public void terminate() {
        this.mFmshCardClient.shutDown();
    }

    /* renamed from: com.miui.tsmclient.model.fmsh.FmshCardOperation$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$tsmclient$pay$PayType = new int[PayType.values().length];

        static {
            try {
                $SwitchMap$com$miui$tsmclient$pay$PayType[PayType.Mipay.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private int getRechargeType(OrderInfo orderInfo) {
        if (AnonymousClass1.$SwitchMap$com$miui$tsmclient$pay$PayType[orderInfo.mPayType.ordinal()] != 1) {
            return EnumRechargeMode.MIPAY_MI.getId();
        }
        return EnumRechargeMode.MIPAY_MI.getId();
    }

    private TradeLog convertToTradeLog(CardAppRecord cardAppRecord) {
        if (cardAppRecord == null) {
            return null;
        }
        TradeLog tradeLog = new TradeLog();
        tradeLog.setAuAmount((float) cardAppRecord.amount);
        tradeLog.setTradeType(getTradeType(cardAppRecord.tradeType));
        tradeLog.setTradeTime(cardAppRecord.tradeTime);
        tradeLog.setTradeDate(cardAppRecord.tradeDate);
        return tradeLog;
    }
}
