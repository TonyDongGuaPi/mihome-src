package com.miui.tsmclient.model;

import android.location.Location;
import android.text.TextUtils;
import com.miui.tsmclient.entity.CardGroupType;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoFactory;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.entity.TransferOutOrderInfo;
import com.miui.tsmclient.net.AuthApiException;
import com.miui.tsmclient.net.TSMAuthManager;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.TSMLocationService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BaseTransitCardModel extends BaseModel {
    protected TSMAuthManager mTSMAuthManager;

    /* access modifiers changed from: protected */
    public void onInit() {
        this.mTSMAuthManager = new TSMAuthManager();
    }

    public List<CardInfo> getCardsFromNetwork(CardInfo cardInfo) throws AuthApiException {
        Location lastLocation = TSMLocationService.getInstance(getContext()).getLastLocation();
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray queryCardProduct = this.mTSMAuthManager.queryCardProduct(getContext(), CardGroupType.TRANSCARD, cardInfo == null ? null : cardInfo.mCardType, lastLocation);
            if (queryCardProduct == null) {
                return arrayList;
            }
            HashMap hashMap = new HashMap();
            boolean z = false;
            for (int i = 0; i < queryCardProduct.length(); i++) {
                JSONObject jSONObject = queryCardProduct.getJSONObject(i);
                CardInfo makeCardInfo = CardInfoFactory.makeCardInfo(jSONObject.optString("cardName"), (JSONObject) null);
                makeCardInfo.mDataSource = CardInfo.DataSource.Network;
                makeCardInfo.parse(jSONObject);
                arrayList.add(makeCardInfo);
                if (makeCardInfo instanceof PayableCardInfo) {
                    if (!z) {
                        queryByUserId(hashMap);
                        z = true;
                    }
                    PayableCardInfo payableCardInfo = (PayableCardInfo) makeCardInfo;
                    if (hashMap.containsKey(makeCardInfo.mCardType)) {
                        payableCardInfo.mUnfinishOrderInfos = (List) hashMap.get(makeCardInfo.mCardType);
                    }
                }
            }
            queryWithdrawCardOrder(arrayList);
            return arrayList;
        } catch (JSONException e) {
            LogUtils.e("failed to get cards on network!", e);
            throw new AuthApiException(16);
        }
    }

    public List<OrderInfo> getCardOrderList(CardInfo cardInfo) {
        if (cardInfo != null) {
            HashMap hashMap = new HashMap();
            try {
                JSONArray queryByUserId = queryByUserId(hashMap);
                List<OrderInfo> list = (List) hashMap.get(cardInfo.mCardType);
                if (queryByUserId == null) {
                    return null;
                }
                return list == null ? new ArrayList() : list;
            } catch (JSONException e) {
                LogUtils.e("failed to getCardOrderList!", e);
            }
        }
        return null;
    }

    public Map<String, List<OrderInfo>> queryByUserId() throws JSONException {
        HashMap hashMap = new HashMap();
        queryByUserId(hashMap);
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public JSONArray queryByUserId(Map<String, List<OrderInfo>> map) throws JSONException {
        JSONArray jSONArray;
        try {
            jSONArray = this.mTSMAuthManager.queryByUserId(getContext());
        } catch (AuthApiException e) {
            LogUtils.e("queryByUserId failed. errorCode: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
            jSONArray = null;
        }
        if (jSONArray == null) {
            return jSONArray;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.parse(jSONArray.getJSONObject(i));
            List list = map.get(orderInfo.mCardType);
            if (list == null) {
                list = new CopyOnWriteArrayList();
                map.put(orderInfo.mCardType, list);
            }
            list.add(orderInfo);
        }
        return jSONArray;
    }

    private void queryWithdrawCardOrder(List<CardInfo> list) throws JSONException, AuthApiException {
        List<TransferOutOrderInfo> queryWithdrawCardOrder;
        if (list != null && !list.isEmpty() && (queryWithdrawCardOrder = this.mTSMAuthManager.queryWithdrawCardOrder(getContext())) != null) {
            for (TransferOutOrderInfo next : queryWithdrawCardOrder) {
                Iterator<CardInfo> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    PayableCardInfo payableCardInfo = (PayableCardInfo) it.next();
                    if (TextUtils.equals(next.getCardType(), payableCardInfo.mCardType)) {
                        payableCardInfo.setUnfinishTransferOutInfo(next);
                        break;
                    }
                }
            }
        }
    }
}
