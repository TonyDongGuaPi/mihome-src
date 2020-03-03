package com.miui.tsmclient.model;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.common.mvp.OnModelChangedListener;
import com.miui.tsmclient.database.CardDataUtil;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.util.LogUtils;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class TransitCardModel extends BaseTransitCardModel {

    public interface OnGotUnfinishedOrderListener {
        void onError();

        void onGotUnfinishedOrder(Map<String, List<OrderInfo>> map);
    }

    public static TransitCardModel create(Context context) {
        TransitCardModel transitCardModel = new TransitCardModel();
        transitCardModel.init(context, (OnModelChangedListener) null);
        return transitCardModel;
    }

    public Observable<CardInfo> deferGetCardsFromDB() {
        LogUtils.d("deferGetCardsFromDB");
        return Observable.defer(new Func0<Observable<CardInfo>>() {
            public Observable<CardInfo> call() {
                try {
                    return Observable.from(TransitCardModel.this.getCardsFromDB());
                } catch (Exception e) {
                    LogUtils.e("deferGetCardsFromDB failed", e);
                    return Observable.error(e);
                }
            }
        });
    }

    public Observable<CardInfo> deferGetCardsFromNetwork() {
        LogUtils.d("deferGetCardsFromNetwork");
        return Observable.defer(new Func0<Observable<CardInfo>>() {
            public Observable<CardInfo> call() {
                try {
                    return Observable.from(TransitCardModel.this.getCardsFromNetwork((CardInfo) null));
                } catch (Exception e) {
                    LogUtils.e("deferGetCardsFromNetwork failed", e);
                    return Observable.error(e);
                }
            }
        });
    }

    public List<CardInfo> getCardsFromDB() {
        List<CardInfo> loadCardList = CardDataUtil.loadCardList(getContext(), (String) null);
        if (loadCardList != null) {
            Iterator<CardInfo> it = loadCardList.iterator();
            while (it.hasNext()) {
                CardInfo next = it.next();
                if (!next.isTransCard()) {
                    it.remove();
                } else {
                    next.mDataSource = CardInfo.DataSource.DB;
                }
            }
        }
        return loadCardList == null ? Collections.emptyList() : loadCardList;
    }

    public BaseResponse updateFromSE(CardInfo cardInfo) {
        BaseResponse queryCardInfo = CardOperationFactory.createCardOperation(cardInfo.mCardType).queryCardInfo(getContext(), cardInfo, (Bundle) null);
        cardInfo.mDataSource = CardInfo.DataSource.SE;
        LogUtils.d("after read ese, got card " + cardInfo.mCardName + ", aid:" + cardInfo.mAid + ", status:" + cardInfo.mStatus + ", isReadSECorrectly:" + cardInfo.mIsReadSECorrectly + ", pid:" + Thread.currentThread().getId() + ", response:" + queryCardInfo.mResultCode);
        return queryCardInfo;
    }

    public BaseResponse processOrderAndUpdateFromSE(CardInfo cardInfo) {
        BaseResponse updateCardInfo = CardOperationFactory.createCardOperation(cardInfo.mCardType).updateCardInfo(getContext(), cardInfo);
        cardInfo.mDataSource = CardInfo.DataSource.SE;
        return updateCardInfo;
    }

    public Subscription queryByUserIdAsync(final OnGotUnfinishedOrderListener onGotUnfinishedOrderListener) {
        if (onGotUnfinishedOrderListener == null) {
            return null;
        }
        return Observable.fromCallable(new Callable<Map<String, List<OrderInfo>>>() {
            public Map<String, List<OrderInfo>> call() throws Exception {
                return TransitCardModel.this.queryByUserId();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Map<String, List<OrderInfo>>>() {
            public void onCompleted() {
            }

            public void onError(Throwable th) {
                onGotUnfinishedOrderListener.onError();
            }

            public void onNext(Map<String, List<OrderInfo>> map) {
                onGotUnfinishedOrderListener.onGotUnfinishedOrder(map);
            }
        });
    }
}
