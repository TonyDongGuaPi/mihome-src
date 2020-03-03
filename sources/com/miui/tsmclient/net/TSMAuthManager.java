package com.miui.tsmclient.net;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.miui.tsmclient.account.AccountInfo;
import com.miui.tsmclient.common.data.CommonResponseInfo;
import com.miui.tsmclient.entity.ActionToken;
import com.miui.tsmclient.entity.CardGroupType;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoFactory;
import com.miui.tsmclient.entity.DeductInfo;
import com.miui.tsmclient.entity.FmshUserInfo;
import com.miui.tsmclient.entity.OrderDetailInfo;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.entity.RefundInfo;
import com.miui.tsmclient.entity.TransferOutOrderInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.net.AuthRequest;
import com.miui.tsmclient.net.TSMAuthContants;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.util.DeviceUtils;
import com.miui.tsmclient.util.LogUtils;
import com.tsmclient.smartcard.Coder;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TSMAuthManager extends BaseAuthManager {
    public FmshUserInfo getFmshUserInfo(Context context, String str, String str2) throws AuthApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        AuthRequest.AuthRequestBuilder addParams = AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_NEW_BIZ_PASS, AuthRequest.RespContentType.json).addParams(TSMAuthContants.PARAM_CPLC, getCplc(new CardInfo(CardInfo.CARD_TYPE_DUMMY))).addParams("deviceModel", DeviceUtils.getDeviceModel((CardInfo) null)).addParams("type", str);
        if (!TextUtils.isEmpty(str2)) {
            addParams.addParams(TSMAuthContants.PARAM_APP_NO, str2);
        }
        return (FmshUserInfo) new Gson().fromJson(((JSONObject) sendRequest(context, accountInfo, addParams.create())).toString(), FmshUserInfo.class);
    }

    public JSONObject createOrder(Context context, int i, CardInfo cardInfo, Bundle bundle) throws AuthApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        AuthRequest create = AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_CREATE_ORDER, AuthRequest.RespContentType.json).addParams(TSMAuthContants.PARAM_FEEID, String.valueOf(i)).addParams("seId", getSEId(cardInfo)).addParams("deviceModel", DeviceUtils.getDeviceModel(cardInfo)).addParams(TSMAuthContants.PARAM_CARDNO, cardInfo.mCardNo).addParams("balance", String.valueOf(cardInfo.mCardBalance)).addParams("deviceId", Coder.hashDeviceInfo(DeviceUtils.getImei(context, cardInfo))).addParams(TSMAuthContants.PARAM_CPLC, getCplc(cardInfo)).addParams(TSMAuthContants.PARAM_MIUI_ROM_TYPE, DeviceUtils.getMIUIRomType(cardInfo)).addParams(TSMAuthContants.PARAM_MIUI_SYSTEM_VERSION, DeviceUtils.getRomVersion()).create();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TSMAuthContants.PARAM_SUPPORT_COUPON, true);
            if (bundle != null) {
                if (bundle.containsKey("cityId")) {
                    create.addParams("cityId", String.valueOf(bundle.getInt("cityId", 5200)));
                }
                if (bundle.containsKey(TSMAuthContants.PARAM_TRANSFER_ORDERID)) {
                    jSONObject.put(TSMAuthContants.PARAM_TRANSFER_ORDERID, bundle.getString(TSMAuthContants.PARAM_TRANSFER_ORDERID));
                }
                if (bundle.containsKey("couponId")) {
                    jSONObject.put("couponId", bundle.getLong("couponId"));
                }
                if (bundle.containsKey(TSMAuthContants.PARAM_PAY_CHANNEL)) {
                    jSONObject.put(TSMAuthContants.PARAM_PAY_CHANNEL, bundle.getString(TSMAuthContants.PARAM_PAY_CHANNEL));
                }
            }
        } catch (JSONException e) {
            LogUtils.e("createOrder called! parse  error.", e);
        }
        if (jSONObject.length() > 0) {
            create.addParams("extra", jSONObject.toString());
        }
        JSONObject jSONObject2 = (JSONObject) sendRequest(context, accountInfo, create);
        String jSONObject3 = jSONObject2.toString();
        LogUtils.d("createOrder api response: " + jSONObject3);
        return jSONObject2;
    }

    public JSONArray queryByUserId(Context context) throws AuthApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        CardInfo cardInfo = new CardInfo(CardInfo.CARD_TYPE_DUMMY);
        JSONArray jSONArray = (JSONArray) sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_QUERY_BY_USERID, AuthRequest.RespContentType.json).addParams("seId", getSEId(cardInfo)).addParams("deviceModel", DeviceUtils.getDeviceModel(cardInfo)).addParams(TSMAuthContants.PARAM_SUPPORT_CARD_TRANSFER, "true").create());
        LogUtils.d("queryByUserId api response: " + jSONArray.toString());
        return jSONArray;
    }

    public JSONObject acquireToken(Context context, CardInfo cardInfo) throws AuthApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        JSONObject jSONObject = (JSONObject) sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_ACQUIRE_TOKEN, AuthRequest.RespContentType.json).addParams("seId", getSEId(cardInfo)).addParams("deviceModel", DeviceUtils.getDeviceModel(cardInfo)).addParams("cardName", cardInfo.mCardType).addParams("deviceId", Coder.hashDeviceInfo(DeviceUtils.getImei(context, cardInfo))).addParams(TSMAuthContants.PARAM_MIUI_ROM_TYPE, DeviceUtils.getMIUIRomType(cardInfo)).addParams(TSMAuthContants.PARAM_MIUI_SYSTEM_VERSION, DeviceUtils.getRomVersion()).create());
        LogUtils.d("acquireToken api response: " + jSONObject.toString());
        return jSONObject;
    }

    public JSONArray queryCardProduct(Context context, String str) throws IOException, AuthApiException {
        return queryCardProduct(context, CardGroupType.TRANSCARD, str, (Location) null);
    }

    public JSONArray queryCardProduct(Context context, CardGroupType cardGroupType) throws IOException, AuthApiException {
        return queryCardProduct(context, cardGroupType, (String) null, (Location) null);
    }

    public JSONArray queryCardProduct(Context context, CardGroupType cardGroupType, String str, Location location) throws AuthApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        CardInfo makeCardInfo = CardInfoFactory.makeCardInfo(TextUtils.isEmpty(str) ? CardInfo.CARD_TYPE_DUMMY : str, (JSONObject) null);
        AuthRequest.AuthRequestBuilder addParams = AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_QUERY_CARD_PRODUCT, AuthRequest.RespContentType.json).addParams(TSMAuthContants.PARAM_CPLC, getCplc(makeCardInfo)).addParams("type", String.valueOf(cardGroupType.getId())).addParams("deviceModel", DeviceUtils.getDeviceModel(makeCardInfo)).addParams(TSMAuthContants.PARAM_LANGUAGE, Locale.getDefault().toString()).addParams(TSMAuthContants.PARAM_MIUI_ROM_TYPE, DeviceUtils.getMIUIRomType(makeCardInfo)).addParams(TSMAuthContants.PARAM_MIUI_SYSTEM_VERSION, DeviceUtils.getRomVersion()).addParams(TSMAuthContants.PARAM_SUPPORT_CARD_TRANSFER, "true");
        if (!TextUtils.isEmpty(str)) {
            addParams.addParams("cardName", str);
        }
        if (location != null) {
            addParams.addParams("la", String.valueOf(location.getLatitude())).addParams("lo", String.valueOf(location.getLongitude()));
        }
        JSONArray jSONArray = (JSONArray) sendRequest(context, accountInfo, addParams.create());
        LogUtils.d("queryCardProduct api response: " + jSONArray.toString());
        return jSONArray;
    }

    public JSONObject queryByOrderId(Context context, String str) throws AuthApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        JSONObject jSONObject = (JSONObject) sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_QUERY_BY_ORDERID, AuthRequest.RespContentType.json).addParams("orderId", str).create());
        LogUtils.d("queryByOrderId api response: " + jSONObject.toString());
        return jSONObject;
    }

    public JSONObject refundOrderById(Context context, String str) throws AuthApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        JSONObject jSONObject = (JSONObject) sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_REFUND_ORDER_BY_ID, AuthRequest.RespContentType.json).addParams("orderId", str).create());
        LogUtils.d("refundOrderById api response: " + jSONObject.toString());
        return jSONObject;
    }

    public JSONObject queryAllServiceProtocol(Context context, String str, TSMAuthContants.ActionType actionType) throws AuthApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        return (JSONObject) sendGetRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_QUERY_ALL_SERVICE_PROTOCOL, AuthRequest.RespContentType.json).addParams("seId", getSEId(CardInfoFactory.makeCardInfo(str, (JSONObject) null))).addParams("deviceModel", DeviceUtils.getDeviceModel((CardInfo) null)).addParams(TSMAuthContants.PARAM_MIUI_ROM_TYPE, DeviceUtils.getMIUIRomType((CardInfo) null)).addParams(TSMAuthContants.PARAM_MIUI_SYSTEM_VERSION, DeviceUtils.getRomVersion()).addParams("cardName", str).addParams(TSMAuthContants.PARAM_ACTION_TYPE, actionType.toString()).create());
    }

    public JSONObject confirmProtocolVersion(Context context, long j) throws AuthApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        return (JSONObject) sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_CONFIRM, AuthRequest.RespContentType.json).addParams("id", String.valueOf(j)).addParams("seId", getSEId(new CardInfo(CardInfo.CARD_TYPE_DUMMY))).create());
    }

    public List<TransferOutOrderInfo> queryWithdrawCardOrder(Context context) throws AuthApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        JSONArray jSONArray = (JSONArray) sendGetRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_QUERY_UNCOMPLETED_TRANSFERS, AuthRequest.RespContentType.json).addParams(TSMAuthContants.PARAM_CPLC, getCplc(new CardInfo(CardInfo.CARD_TYPE_DUMMY))).create());
        LogUtils.d("queryUncompletedTransfers api response:" + jSONArray.toString());
        return (List) new Gson().fromJson(jSONArray.toString(), new TypeToken<List<TransferOutOrderInfo>>() {
        }.getType());
    }

    public BaseResponse uploadTransferOutResult(Context context, CardInfo cardInfo) {
        int i;
        PayableCardInfo payableCardInfo = cardInfo instanceof PayableCardInfo ? (PayableCardInfo) cardInfo : null;
        if (payableCardInfo == null || payableCardInfo.getUnfinishTransferOutInfo() == null) {
            return new BaseResponse(1, new Object[0]);
        }
        String str = "";
        try {
            AccountInfo loadAccountInfo = this.mAccountManager.loadAccountInfo(context);
            String str2 = (String) sendRequest(context, loadAccountInfo, AuthRequest.AuthRequestBuilder.newBuilder(loadAccountInfo, TSMAuthContants.URL_CONFIRM_TRANSFER_OUT, (AuthRequest.RespContentType) null).addParams("orderId", payableCardInfo.getUnfinishTransferOutInfo().getOrderId()).addParams(TSMAuthContants.PARAM_CPLC, getCplc(payableCardInfo)).create());
            LogUtils.d("uploadTransferOutResult api response: " + str2);
            CommonResponseInfo commonResponseInfo = (CommonResponseInfo) new Gson().fromJson(str2, CommonResponseInfo.class);
            if (commonResponseInfo == null) {
                i = 16;
            } else {
                str = commonResponseInfo.getErrorDesc();
                if (commonResponseInfo.getErrorCode() == 200) {
                    i = 0;
                } else {
                    i = commonResponseInfo.getErrorCode();
                }
            }
        } catch (AuthApiException e) {
            LogUtils.e("confirmTransferOut failed with an apiException, code:" + e.mErrorCode + ", msg:" + e.mErrorMsg, e);
            int i2 = e.mErrorCode;
            str = e.mErrorMsg;
            i = i2;
        }
        return new BaseResponse(i, str, new Object[0]);
    }

    public JSONArray queryAccountTransitCards(Context context) throws AuthApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        return (JSONArray) sendGetRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_ACCOUNT_QUERY_ALL_TRANSIT_CARDS, AuthRequest.RespContentType.json).addParams(TSMAuthContants.PARAM_CPLC, getCplc(new CardInfo(CardInfo.CARD_TYPE_DUMMY))).create());
    }

    public DeductInfo queryDeductContract(Context context, CardInfo cardInfo) throws AuthApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        JSONObject jSONObject = (JSONObject) sendGetRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_QUERY_DEDUCT_CONTRACT, AuthRequest.RespContentType.json).addParams(TSMAuthContants.PARAM_CPLC, getCplc(cardInfo)).addParams("cardName", cardInfo.mCardType).create());
        LogUtils.d("queryDeductContract api response:" + jSONObject.toString());
        return (DeductInfo) new Gson().fromJson(jSONObject.toString(), DeductInfo.class);
    }

    public BaseResponse createDeductOrder(Context context, CardInfo cardInfo, DeductInfo deductInfo) {
        try {
            AccountInfo accountInfo = getAccountInfo(context);
            JSONObject jSONObject = (JSONObject) sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_CREATE_DEDUCT_ORDER, AuthRequest.RespContentType.json).addParams(TSMAuthContants.PARAM_FEEID, String.valueOf(deductInfo.getFeeId())).addParams("seId", getSEId(cardInfo)).addParams("deviceModel", DeviceUtils.getDeviceModel(cardInfo)).addParams(TSMAuthContants.PARAM_CARDNO, cardInfo.mCardNo).addParams("balance", String.valueOf(cardInfo.mCardBalance)).addParams("deviceId", Coder.hashDeviceInfo(DeviceUtils.getImei(context, cardInfo))).addParams(TSMAuthContants.PARAM_CPLC, getCplc(cardInfo)).addParams(TSMAuthContants.PARAM_DEDUCT_ID, deductInfo.getDeductId()).create());
            LogUtils.d("createDeductOrder api response: " + jSONObject.toString());
            return new BaseResponse(0, "", jSONObject.optString("orderId"));
        } catch (AuthApiException e) {
            LogUtils.e("createDeductOrder failed with an apiException, code:" + e.mErrorCode + ", msg:" + e.mErrorMsg, e);
            return new BaseResponse(e.mErrorCode, e.mErrorMsg, new Object[0]);
        }
    }

    public OrderInfo queryOrderInfoByOrderId(Context context, String str) throws AuthApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        JSONObject jSONObject = (JSONObject) sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_QUERY_BY_ORDERID, AuthRequest.RespContentType.json).addParams("orderId", str).create());
        LogUtils.d("queryByOrderId api response: " + jSONObject.toString());
        return (OrderInfo) new GsonBuilder().registerTypeAdapter(OrderInfo.OrderStatus.class, new OrderInfo.OrderStatusDeserializer()).registerTypeAdapter(ActionToken.TokenType.class, new ActionToken.TokenTypeDeserializer()).create().fromJson(jSONObject.toString(), OrderInfo.class);
    }

    public BaseResponse uploadSwipeCardHciTradeInfo(Context context, String str) {
        int i;
        String str2 = "";
        try {
            AccountInfo accountInfo = getAccountInfo(context);
            CardInfo cardInfo = new CardInfo(CardInfo.CARD_TYPE_DUMMY);
            sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_UPLOAD_SWIPE_CARD_HCI_TRADE_INFO, AuthRequest.RespContentType.json).addParams("deviceModel", DeviceUtils.getDeviceModel(cardInfo)).addParams(TSMAuthContants.PARAM_CPLC, getCplc(cardInfo)).addParams(TSMAuthContants.PARAM_MIUI_ROM_TYPE, DeviceUtils.getMIUIRomType((CardInfo) null)).addParams(TSMAuthContants.PARAM_MIUI_SYSTEM_VERSION, DeviceUtils.getRomVersion()).addParams("message", str).create());
            LogUtils.d("uploadSwipeCardHciTradeInfo success");
            i = 0;
        } catch (AuthApiException e) {
            LogUtils.e("uploadSwipeCardHciTradeInfo failed with an exception", e);
            i = e.mErrorCode;
            str2 = e.mErrorMsg;
        }
        return new BaseResponse(i, str2, new Object[0]);
    }

    public BaseResponse uploadSwipeCardErrorInfo(Context context, String str) {
        int i;
        String str2 = "";
        try {
            AccountInfo accountInfo = getAccountInfo(context);
            sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_UPLOAD_SWIPE_CARD_ERROR_INFO, AuthRequest.RespContentType.json).addParams("message", str).create());
            LogUtils.d("uploadSwipeCardErrorInfo success");
            i = 0;
        } catch (AuthApiException e) {
            LogUtils.e("uploadSwipeCardErrorInfo failed with an exception", e);
            i = e.mErrorCode;
            str2 = e.mErrorMsg;
        }
        return new BaseResponse(i, str2, new Object[0]);
    }

    public BaseResponse uploadDefaultTransCardTradeLogs(Context context, String str) {
        int i;
        String str2 = "";
        try {
            AccountInfo accountInfo = getAccountInfo(context);
            sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_UPLOAD_DEFAULT_TRANSIT_CARD_TRADE_LOG, AuthRequest.RespContentType.json).addParams(TSMAuthContants.PARAM_CPLC, getCplc(new CardInfo(CardInfo.CARD_TYPE_DUMMY))).addParams("cardInfo", str).create());
            LogUtils.d("uploadDefaultTransCardTradeLogs success");
            i = 0;
        } catch (AuthApiException e) {
            LogUtils.e("uploadSwipeCardHciTradeInfo failed with an exception", e);
            i = e.mErrorCode;
            str2 = e.mErrorMsg;
        }
        return new BaseResponse(i, str2, new Object[0]);
    }

    public OrderDetailInfo getOrderDetailById(Context context, CardInfo cardInfo, String str) throws AuthApiException {
        if (cardInfo == null || TextUtils.isEmpty(str)) {
            LogUtils.e("getOrderDetailById failed. params is invalid");
            throw new AuthApiException(1);
        }
        AccountInfo accountInfo = getAccountInfo(context);
        String jSONObject = ((JSONObject) sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_QUERY_RECHARGE_ORDER_DETAIL, AuthRequest.RespContentType.json).addParams("orderId", str).addParams("deviceModel", DeviceUtils.getDeviceModel(cardInfo)).addParams("aid", cardInfo.mAid).addParams("cardName", cardInfo.mCardType).addParams(TSMAuthContants.PARAM_CPLC, getCplc(cardInfo)).create())).toString();
        LogUtils.d("getOrderDetailById api response: " + jSONObject);
        return (OrderDetailInfo) new GsonBuilder().registerTypeAdapter(ActionToken.TokenType.class, new ActionToken.TokenTypeDeserializer()).registerTypeAdapter(OrderInfo.OrderStatus.class, new OrderInfo.OrderStatusDeserializer()).create().fromJson(jSONObject, OrderDetailInfo.class);
    }

    public JSONObject updateCardBaseInfo(Context context, String str) throws AuthApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        return (JSONObject) sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_UPDATE_CARD_BASE_INFO, AuthRequest.RespContentType.json).addParams(TSMAuthContants.PARAM_CPLC, getCplc(new CardInfo(CardInfo.CARD_TYPE_DUMMY))).addParams("message", str).create());
    }

    public RefundInfo refund(Context context, String str) throws AuthApiException {
        if (!TextUtils.isEmpty(str)) {
            AccountInfo accountInfo = getAccountInfo(context);
            String jSONObject = ((JSONObject) sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_REFUND_ORDER_BY_ID, AuthRequest.RespContentType.json).addParams("orderId", str).create())).toString();
            LogUtils.d("refund api response: " + jSONObject);
            return (RefundInfo) new Gson().fromJson(jSONObject, RefundInfo.class);
        }
        throw new AuthApiException(1);
    }

    /* access modifiers changed from: protected */
    public Object sendRequest(Context context, AccountInfo accountInfo, AuthRequest authRequest) throws AuthApiException {
        return super.sendRequest(context, accountInfo, authRequest);
    }

    /* access modifiers changed from: protected */
    public Object sendGetRequest(Context context, AccountInfo accountInfo, AuthRequest authRequest) throws AuthApiException {
        return super.sendGetRequest(context, accountInfo, authRequest);
    }

    /* access modifiers changed from: protected */
    public AccountInfo getAccountInfo(Context context) throws AuthApiException {
        AccountInfo loadAccountInfo = this.mAccountManager.loadAccountInfo(context);
        if (loadAccountInfo != null) {
            return loadAccountInfo;
        }
        throw new AuthApiException(14);
    }

    /* access modifiers changed from: protected */
    public String getCplc(CardInfo cardInfo) throws AuthApiException {
        try {
            return cardInfo.getTerminal().getCPLC();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LogUtils.e("failed to get cplc", e);
            throw new AuthApiException(11);
        } catch (IOException e2) {
            LogUtils.e("failed to get cplc", e2);
            throw new AuthApiException(10);
        }
    }

    /* access modifiers changed from: protected */
    public String getSEId(CardInfo cardInfo) throws AuthApiException {
        try {
            String seid = cardInfo.getTerminal().getSeid();
            if (!TextUtils.isEmpty(seid)) {
                return seid;
            }
            throw new AuthApiException(13);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LogUtils.e("failed to get seId", e);
            throw new AuthApiException(11);
        } catch (IOException e2) {
            LogUtils.e("failed to get seId", e2);
            throw new AuthApiException(10);
        }
    }
}
