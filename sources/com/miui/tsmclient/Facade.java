package com.miui.tsmclient;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.miui.tsmclient.common.data.CommonResponseInfo;
import com.miui.tsmclient.common.mvp.OnModelChangedListener;
import com.miui.tsmclient.common.net.HttpClient;
import com.miui.tsmclient.common.net.ResponseListener;
import com.miui.tsmclient.common.net.request.SecureRequest;
import com.miui.tsmclient.database.CardDataUtil;
import com.miui.tsmclient.entity.ActionToken;
import com.miui.tsmclient.entity.CardConfigManager;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoManager;
import com.miui.tsmclient.entity.ConfigInfo;
import com.miui.tsmclient.entity.FeeInfo;
import com.miui.tsmclient.entity.GroupConfigInfo;
import com.miui.tsmclient.entity.MifareCardInfo;
import com.miui.tsmclient.entity.OrderDetailInfo;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.entity.RechargeOrderInfo;
import com.miui.tsmclient.entity.RechargeOrderResponseInfo;
import com.miui.tsmclient.entity.RefundInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.PayableCardManager;
import com.miui.tsmclient.model.TransitCardModel;
import com.miui.tsmclient.model.mitsm.MiTSMCardClient;
import com.miui.tsmclient.model.mitsm.TSMSessionManager;
import com.miui.tsmclient.net.AuthApiException;
import com.miui.tsmclient.net.TSMAuthContants;
import com.miui.tsmclient.net.TSMAuthManager;
import com.miui.tsmclient.net.request.ConfigListRequest;
import com.miui.tsmclient.net.request.RechargeOrderListRequest;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.pay.PayToolFactory;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.util.Constants;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.NetworkUtil;
import com.miui.tsmclient.util.SysUtils;
import com.miui.tsmclient.util.TSMLocationService;
import com.tsmclient.smartcard.PrefUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Facade {
    private PayableCardManager mCardManager;
    /* access modifiers changed from: private */
    public Context mContext;
    private CompositeSubscription mSubscriptions;
    /* access modifiers changed from: private */
    public TSMAuthManager mTSMAuthManager;
    /* access modifiers changed from: private */
    public TransitCardModel mTransitCardModel;

    public interface CardListListener extends UpdateCardListener {
        void onCardListUpdated(List<CardInfo> list);

        void onNetworkUnavailable();

        void onServerUnavailable();

        void onUpdateCardListCompleted();
    }

    public static class CityResponseInfo extends CommonResponseInfo {
        /* access modifiers changed from: private */
        @SerializedName("data")
        public CitiesInfo mCitiesInfo;

        public static class CitiesInfo {
            @SerializedName("availableCityInfo")
            public CityInfo mAvailableCityInfo;
            @SerializedName("locationCityInfo")
            public CityInfo mLocationCityInfo;
        }

        public static class CityInfo {
            @SerializedName("cityId")
            public String mCityId;
            @SerializedName("cityName")
            public String mCityName;
        }
    }

    public interface CreateOrderListener {
        void onCreateOrderFailed(int i, String str);

        void onCreateOrderSuccess(OrderInfo orderInfo);
    }

    public interface GetCitiesInfoListener {
        void onGetCitiesInfoFailed(int i, String str);

        void onGetCitiesInfoListSuccess(CityResponseInfo.CitiesInfo citiesInfo);
    }

    public interface GetDefaultCardListener {
        void onGetDefaultCard(String str);
    }

    public interface GetIssueCardNoticeListener {
        void onGetIssueCardNoticeFailed(int i, String str);

        void onGetIssueCardNoticeSuccess(String str);
    }

    public interface GetRechargeOrderListListener {
        void onGetRechargeOrderListFailed(int i, String str);

        void onGetRechargeOrderListSuccess(List<RechargeOrderInfo> list);
    }

    public interface UpdateCardListener {
        void onCardUpdated(CardInfo cardInfo);
    }

    public interface UpdateOrderListener {
        void onOrderUpdate(CardInfo cardInfo);
    }

    private Facade() {
    }

    public static Facade getFacade(Context context) {
        Facade facade = new Facade();
        facade.onInit(context);
        return facade;
    }

    public void release() {
        this.mSubscriptions.unsubscribe();
        this.mCardManager.release();
    }

    private void onInit(Context context) {
        this.mContext = context.getApplicationContext();
        this.mTransitCardModel = new TransitCardModel();
        this.mTransitCardModel.init(this.mContext, (OnModelChangedListener) null);
        this.mTSMAuthManager = new TSMAuthManager();
        this.mSubscriptions = new CompositeSubscription();
        this.mCardManager = new PayableCardManager(this.mContext);
    }

    public void getCardList(CardListListener cardListListener, boolean z) {
        if (NetworkUtil.isConnected(this.mContext)) {
            final CardListListener cardListListener2 = cardListListener;
            final boolean z2 = z;
            this.mSubscriptions.add(Observable.fromCallable(new Callable<List<CardInfo>>() {
                public List<CardInfo> call() throws Exception {
                    List<CardInfo> cardsFromNetwork = Facade.this.mTransitCardModel.getCardsFromNetwork((CardInfo) null);
                    if (cardsFromNetwork == null) {
                        return Collections.emptyList();
                    }
                    CardConfigManager.getInstance().getSupportedTransCardMap(true);
                    return cardsFromNetwork;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleSubscriber<List<CardInfo>>("getCardsFromNetwork complete", "getCardsFromNetwork error occurred") {
                public void onNext(List<CardInfo> list) {
                    if (cardListListener2 == null) {
                        return;
                    }
                    if (list.isEmpty()) {
                        cardListListener2.onServerUnavailable();
                        return;
                    }
                    ArrayList arrayList = new ArrayList(list);
                    cardListListener2.onCardListUpdated(list);
                    if (z2) {
                        Facade.this.updateFromLocal(arrayList, cardListListener2);
                    }
                }
            }));
        } else if (cardListListener != null) {
            cardListListener.onNetworkUnavailable();
        }
    }

    public boolean setDefaultCard(CardInfo cardInfo) {
        return SysUtils.setDefaultTransCard(this.mContext, cardInfo);
    }

    public String getDefaultCard() {
        return SysUtils.getDefaultTransCard(this.mContext);
    }

    public void getDefaultCard(final GetDefaultCardListener getDefaultCardListener) {
        this.mSubscriptions.add(Observable.fromCallable(new Callable<String>() {
            public String call() throws Exception {
                return Facade.this.getDefaultCard();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleSubscriber<String>("getDefaultCard error occurred") {
            public void onNext(String str) {
                if (getDefaultCardListener != null) {
                    getDefaultCardListener.onGetDefaultCard(str);
                }
            }
        }));
    }

    public List<CardInfo> fetchCards(CardInfo cardInfo) {
        BaseResponse updateCards = CardInfoManager.getInstance(this.mContext).updateCards(cardInfo);
        if (updateCards.isSuccess()) {
            if (updateCards.mDatas[0] instanceof List) {
                ArrayList arrayList = new ArrayList();
                List list = (List) updateCards.mDatas[0];
                for (int i = 0; i < list.size(); i++) {
                    arrayList.add((CardInfo) list.get(i));
                }
                return arrayList;
            }
        }
        return null;
    }

    public BaseResponse updateCard(CardInfo cardInfo) {
        return CardInfoManager.getInstance(this.mContext).updateCards(cardInfo);
    }

    public BaseResponse deleteCard(CardInfo cardInfo) {
        return CardInfoManager.getInstance(this.mContext).deleteCard(cardInfo, (Bundle) null);
    }

    @Deprecated
    public void updateFromSE(CardInfo cardInfo) {
        CardInfoManager.getInstance(this.mContext).updateCards(cardInfo);
    }

    @Deprecated
    public void updateFromSE(final CardInfo cardInfo, final UpdateCardListener updateCardListener) {
        this.mSubscriptions.add(Observable.fromCallable(new Callable<CardInfo>() {
            public CardInfo call() throws Exception {
                Facade.this.updateFromSE(cardInfo);
                return cardInfo;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleSubscriber<CardInfo>("updateTransCard is completed", "updateTransCard error occurred") {
            public void onNext(CardInfo cardInfo) {
                if (updateCardListener != null) {
                    updateCardListener.onCardUpdated(cardInfo);
                }
            }
        }));
    }

    public void updateCardOrder(CardInfo cardInfo) {
        List<OrderInfo> cardOrderList;
        if ((cardInfo instanceof PayableCardInfo) && (cardOrderList = this.mTransitCardModel.getCardOrderList(cardInfo)) != null) {
            ((PayableCardInfo) cardInfo).mUnfinishOrderInfos = cardOrderList;
        }
    }

    public void updateCardOrder(final CardInfo cardInfo, final UpdateOrderListener updateOrderListener) {
        this.mSubscriptions.add(Observable.fromCallable(new Callable<CardInfo>() {
            public CardInfo call() throws Exception {
                Facade.this.updateCardOrder(cardInfo);
                return cardInfo;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleSubscriber<CardInfo>("updateCardOrder is completed", "updateCardOrder error occurred") {
            public void onNext(CardInfo cardInfo) {
                if (updateOrderListener != null) {
                    updateOrderListener.onOrderUpdate(cardInfo);
                }
            }
        }));
    }

    public BaseResponse issue(CardInfo cardInfo, Bundle bundle) {
        if (cardInfo == null) {
            return new BaseResponse(1, new Object[0]);
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (cardInfo instanceof PayableCardInfo) {
            PayableCardInfo payableCardInfo = (PayableCardInfo) cardInfo;
            bundle.putParcelable("card_info", payableCardInfo);
            Iterator<OrderInfo> it = payableCardInfo.mUnfinishOrderInfos.iterator();
            while (true) {
                if (it.hasNext()) {
                    OrderInfo next = it.next();
                    ActionToken issueOrWithdrawOrderToken = next.getIssueOrWithdrawOrderToken();
                    if (issueOrWithdrawOrderToken != null) {
                        bundle.putString("order_id", next.mOrderId);
                        bundle.putString("authentication_code", issueOrWithdrawOrderToken.mToken);
                        break;
                    }
                } else {
                    break;
                }
            }
        } else if (!(cardInfo instanceof MifareCardInfo)) {
            return new BaseResponse(1, new Object[0]);
        } else {
            if (!bundle.containsKey(Constants.EXTRA_DOOR_CARD_PRODUCT_ID)) {
                bundle.putString(Constants.EXTRA_DOOR_CARD_PRODUCT_ID, "99999-00001");
            }
        }
        return CardInfoManager.getInstance(this.mContext).issue(cardInfo, bundle);
    }

    public BaseResponse recharge(CardInfo cardInfo) {
        return CardInfoManager.getInstance(this.mContext).recharge(cardInfo);
    }

    public int pay(Activity activity, CardInfo cardInfo, OrderInfo orderInfo) {
        return PayToolFactory.getPayTool(cardInfo.mCardType).pay(activity, orderInfo.mPayExtra, (Bundle) null);
    }

    public BaseResponse syncEse(CardInfo cardInfo) {
        int i;
        MiTSMCardClient miTSMCardClient = new MiTSMCardClient();
        String str = "";
        try {
            return miTSMCardClient.syncEse(this.mContext, cardInfo, miTSMCardClient.getSession(this.mContext, cardInfo, (TSMSessionManager.BusinessType) null), (Bundle) null);
        } catch (IOException e) {
            i = 2;
            LogUtils.e("sync Ese failed with an io exception.", e);
            return new BaseResponse(i, str, new Object[0]);
        } catch (SeiTSMApiException e2) {
            i = e2.getErrorCode();
            str = e2.getMessage();
            LogUtils.e("sync Ese failed with an tsmapi exception.", e2);
            return new BaseResponse(i, str, new Object[0]);
        } catch (InterruptedException e3) {
            i = 11;
            LogUtils.e("sync Ese is interrupted.", e3);
            return new BaseResponse(i, str, new Object[0]);
        }
    }

    public int parsePayResult(CardInfo cardInfo, Bundle bundle) {
        return PayToolFactory.getPayTool(cardInfo.mCardType).parsePayResult(this.mContext, cardInfo, bundle);
    }

    public void createOrder(FeeInfo feeInfo, CardInfo cardInfo, Bundle bundle, final CreateOrderListener createOrderListener) {
        if (cardInfo != null && feeInfo != null && createOrderListener != null) {
            final FeeInfo feeInfo2 = feeInfo;
            final CardInfo cardInfo2 = cardInfo;
            final Bundle bundle2 = bundle;
            final CreateOrderListener createOrderListener2 = createOrderListener;
            this.mSubscriptions.add(Observable.fromCallable(new Callable<OrderInfo>() {
                public OrderInfo call() throws Exception {
                    try {
                        JSONObject createOrder = Facade.this.mTSMAuthManager.createOrder(Facade.this.mContext, feeInfo2.mId, cardInfo2, bundle2);
                        if (createOrder == null) {
                            return null;
                        }
                        OrderInfo orderInfo = new OrderInfo();
                        orderInfo.parse(createOrder);
                        LogUtils.d("order create success, orderId:" + orderInfo.mOrderId);
                        return orderInfo;
                    } catch (AuthApiException e) {
                        LogUtils.e("create order from mipay failed. errorcode: " + e.mErrorCode + ", msg:" + e.mErrorMsg, e);
                        createOrderListener2.onCreateOrderFailed(e.mErrorCode, e.mErrorMsg);
                        return null;
                    }
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleSubscriber<OrderInfo>("createOrder is completed", "createOrder error occurred") {
                public void onNext(OrderInfo orderInfo) {
                    if (orderInfo != null) {
                        createOrderListener.onCreateOrderSuccess(orderInfo);
                    }
                }
            }));
        }
    }

    public void clearData() {
        CardInfoManager.getInstance(this.mContext).clear();
        PrefUtils.clear(this.mContext);
        com.miui.tsmclient.util.PrefUtils.clear(this.mContext);
        CardDataUtil.deleteAllCards(this.mContext);
    }

    public void getRechargeOrderList(CardInfo cardInfo, final GetRechargeOrderListListener getRechargeOrderListListener) {
        if (cardInfo != null && getRechargeOrderListListener != null) {
            HttpClient.getInstance(this.mContext).enqueue(new RechargeOrderListRequest(cardInfo, new ResponseListener<RechargeOrderResponseInfo>() {
                public void onSuccess(RechargeOrderResponseInfo rechargeOrderResponseInfo) {
                    LogUtils.d("RechargeOrderListRequest onSuccess called!");
                    getRechargeOrderListListener.onGetRechargeOrderListSuccess(rechargeOrderResponseInfo.getRechargeOrderInfoList());
                }

                public void onFailed(int i, String str, RechargeOrderResponseInfo rechargeOrderResponseInfo) {
                    LogUtils.e("RechargeOrderListRequest onFail called! errorCode:" + i + ", errorMsg:" + str);
                    getRechargeOrderListListener.onGetRechargeOrderListFailed(i, str);
                }
            }));
        }
    }

    public RefundInfo refund(String str) {
        try {
            return this.mTSMAuthManager.refund(this.mContext, str);
        } catch (Exception e) {
            LogUtils.e("refund failed", e);
            return null;
        }
    }

    public BaseResponse retryOrder(CardInfo cardInfo, String str) {
        OrderDetailInfo orderDetailInfo;
        BaseResponse issue;
        BaseResponse baseResponse = new BaseResponse(-1, new Object[0]);
        try {
            orderDetailInfo = this.mTSMAuthManager.getOrderDetailById(this.mContext, cardInfo, str);
        } catch (Exception e) {
            LogUtils.e("retryOrder failed, orderId:" + str, e);
            orderDetailInfo = null;
        }
        if (orderDetailInfo == null || !orderDetailInfo.canRetry()) {
            return baseResponse;
        }
        boolean z = true;
        if (orderDetailInfo.isIssueOrWithdrawOrder()) {
            Bundle bundle = new Bundle();
            ActionToken issueOrWithdrawOrderToken = orderDetailInfo.getUnfinishedOrder().getIssueOrWithdrawOrderToken();
            if (issueOrWithdrawOrderToken != null) {
                bundle.putString("authentication_code", issueOrWithdrawOrderToken.mToken);
                if (cardInfo.hasTransferInOrder()) {
                    issue = CardInfoManager.getInstance(this.mContext).transferIn(cardInfo, bundle);
                    LogUtils.d("retryOrder transferIn called! resultCode: " + issue.mResultCode + ", responseDesc:" + issue.mMsg);
                } else {
                    issue = CardInfoManager.getInstance(this.mContext).issue(cardInfo, bundle);
                    LogUtils.d("retryOrder issue called! resultCode: " + issue.mResultCode + ", responseDesc:" + issue.mMsg);
                }
                baseResponse = issue;
                if (baseResponse.mResultCode != 0) {
                    z = false;
                }
            }
        }
        if (!z || !orderDetailInfo.isRechargeOrder() || !(cardInfo instanceof PayableCardInfo)) {
            return baseResponse;
        }
        ((PayableCardInfo) cardInfo).updateOrderInfo(orderDetailInfo.getUnfinishedOrder());
        return CardInfoManager.getInstance(this.mContext).recharge(cardInfo);
    }

    public void getCitiesInfo(CardInfo cardInfo, final GetCitiesInfoListener getCitiesInfoListener) {
        if (cardInfo != null && getCitiesInfoListener != null) {
            final CardInfo cardInfo2 = cardInfo;
            HttpClient.getInstance(this.mContext).enqueue(new SecureRequest<CityResponseInfo>(1, "api/%s/sporder/queryCityInfo", CityResponseInfo.class, new ResponseListener<CityResponseInfo>() {
                public void onSuccess(CityResponseInfo cityResponseInfo) {
                    LogUtils.d("getCitiesInfo onSuccess!");
                    getCitiesInfoListener.onGetCitiesInfoListSuccess(cityResponseInfo.mCitiesInfo);
                }

                public void onFailed(int i, String str, CityResponseInfo cityResponseInfo) {
                    LogUtils.e("getCitiesInfo onFail! errorCode:" + i + ", errorMsg:" + str);
                    getCitiesInfoListener.onGetCitiesInfoFailed(i, str);
                }
            }) {
                public void addExtraParams() throws IOException {
                    super.addExtraParams();
                    try {
                        Location lastLocation = TSMLocationService.getInstance(Facade.this.mContext).getLastLocation();
                        addParams("la", String.valueOf(lastLocation.getLatitude()));
                        addParams("lo", String.valueOf(lastLocation.getLongitude()));
                        addParams(TSMAuthContants.PARAM_CPLC, cardInfo2.getTerminal().getCPLC());
                        addParams("cardName", cardInfo2.mCardType);
                    } catch (IOException | InterruptedException e) {
                        throw new IOException("RechargeOrderListRequest getExtraParams failed", e);
                    }
                }
            });
        }
    }

    public void getIssueCardNotice(final CardInfo cardInfo, final GetIssueCardNoticeListener getIssueCardNoticeListener) {
        if (cardInfo != null && getIssueCardNoticeListener != null) {
            HttpClient.getInstance(this.mContext).enqueue(new ConfigListRequest(cardInfo, ConfigInfo.ISSUE_CARD_NOTICE, new ResponseListener<GroupConfigInfo>() {
                public void onSuccess(GroupConfigInfo groupConfigInfo) {
                    LogUtils.d("getIssueCardNotice onSuccess!");
                    List<String> contentList = groupConfigInfo.getContentList(cardInfo.mCardType, ConfigInfo.ISSUE_CARD_NOTICE);
                    getIssueCardNoticeListener.onGetIssueCardNoticeSuccess(!contentList.isEmpty() ? contentList.get(0) : null);
                }

                public void onFailed(int i, String str, GroupConfigInfo groupConfigInfo) {
                    LogUtils.e("getIssueCardNotice onFail! errorCode:" + i + ", errorMsg:" + str);
                    getIssueCardNoticeListener.onGetIssueCardNoticeFailed(i, str);
                }
            }));
        }
    }

    /* access modifiers changed from: private */
    public void updateFromLocal(List<CardInfo> list, final CardListListener cardListListener) {
        if (list != null) {
            this.mSubscriptions.add(Observable.from(list).map(new Func1<CardInfo, CardInfo>() {
                public CardInfo call(CardInfo cardInfo) {
                    CardInfo transCard = CardInfoManager.getInstance(Facade.this.mContext).getTransCard((CardInfoManager.CacheLauncher) null, cardInfo);
                    if (transCard == null || !transCard.mIsReadSECorrectly) {
                        CardInfoManager.getInstance(Facade.this.mContext).updateCards(cardInfo);
                    } else {
                        cardInfo.updateInfo(transCard);
                        cardInfo.mDataSource = CardInfo.DataSource.DB;
                    }
                    return cardInfo;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleSubscriber<CardInfo>("updateFromLocal error occurred") {
                public void onCompleted() {
                    LogUtils.d("updateFromLocal complete");
                    if (cardListListener != null) {
                        cardListListener.onUpdateCardListCompleted();
                    }
                }

                public void onNext(CardInfo cardInfo) {
                    if (cardListListener != null) {
                        cardListListener.onCardUpdated(cardInfo);
                    }
                }
            }));
        }
    }

    public static class SimpleSubscriber<T> extends Subscriber<T> {
        private String mCompleteLog;
        private String mErrorLog;

        public void onNext(T t) {
        }

        public SimpleSubscriber() {
            this("error occurred");
        }

        public SimpleSubscriber(String str) {
            this((String) null, str);
        }

        public SimpleSubscriber(String str, String str2) {
            this.mCompleteLog = str;
            this.mErrorLog = str2;
        }

        public void onCompleted() {
            if (!TextUtils.isEmpty(this.mCompleteLog)) {
                LogUtils.d(this.mCompleteLog);
            }
        }

        public void onError(Throwable th) {
            if (!TextUtils.isEmpty(this.mErrorLog)) {
                LogUtils.e(this.mErrorLog, th);
            }
        }
    }
}
