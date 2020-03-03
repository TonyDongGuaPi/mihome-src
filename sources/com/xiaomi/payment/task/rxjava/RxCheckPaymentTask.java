package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.data.Utils;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.rxjava.RxBasePaymentTask;
import com.xiaomi.payment.data.MibiConstants;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RxCheckPaymentTask extends RxBasePaymentTask<Result> {
    public RxCheckPaymentTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        String str;
        String f = sortedParameter.f("processId");
        Boolean valueOf = Boolean.valueOf(sortedParameter.a(MibiConstants.f12225dk, false));
        String f2 = sortedParameter.f("order");
        String f3 = sortedParameter.f("url");
        if (valueOf.booleanValue()) {
            str = MibiConstants.a(MibiConstants.bC);
        } else {
            str = MibiConstants.a(MibiConstants.bB);
        }
        Connection a2 = ConnectionFactory.a(str, this.f7587a);
        SortedParameter d = a2.d();
        d.a("processId", (Object) f);
        d.a(CommonConstants.aX, (Object) Boolean.valueOf(Utils.b()));
        if (valueOf.booleanValue()) {
            d.a("url", (Object) f3);
        } else {
            d.a("order", (Object) f2);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean b(JSONObject jSONObject, Result result) throws PaymentException {
        JSONObject optJSONObject = jSONObject.optJSONObject("result");
        if (optJSONObject == null) {
            return false;
        }
        result.mResult = optJSONObject.toString();
        try {
            result.mResultErrorCode = jSONObject.getInt("errcode");
            result.mResultErrorDesc = jSONObject.optString("errDesc");
            return true;
        } catch (JSONException e) {
            throw new ResultException("error code not exists", e);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        JSONObject jSONObject2 = jSONObject;
        Result result2 = result;
        try {
            long j = jSONObject2.getLong("balance");
            long j2 = jSONObject2.getLong("giftcardValue");
            long j3 = jSONObject2.getLong(MibiConstants.eJ);
            boolean optBoolean = jSONObject2.optBoolean(MibiConstants.eE, true);
            boolean optBoolean2 = jSONObject2.optBoolean(MibiConstants.eG, true);
            String string = jSONObject2.getString(MibiConstants.eI);
            String string2 = jSONObject2.getString("title");
            long j4 = jSONObject2.getLong("price");
            String string3 = jSONObject2.getString(MibiConstants.dP);
            String string4 = jSONObject2.getString("tradeId");
            long j5 = j4;
            int optInt = jSONObject2.optInt(MibiConstants.gC, 0);
            String optString = jSONObject2.optString("merchantName");
            JSONArray optJSONArray = jSONObject2.optJSONArray(MibiConstants.gN);
            int i = optInt;
            int i2 = 0;
            int optInt2 = jSONObject2.optInt(MibiConstants.gR, 0);
            if (Utils.a(string2, string3, string4)) {
                result2.mBalance = j;
                result2.mGiftcardValue = j2;
                result2.mPartnerGiftcardValue = j3;
                result2.mUseGiftcard = optBoolean;
                result2.mUsePartnerGiftcard = optBoolean2;
                result2.mPartnerGiftcardName = string;
                result2.mOrderTitle = string2;
                result2.mOrderPrice = j5;
                result2.mServerMarketType = string3;
                result2.mTradeId = string4;
                result2.mPayMode = i;
                result2.mMerchantName = optString;
                result2.mOrderConsumedDiscountGiftcardId = optInt2;
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    result2.mDiscountGiftCards = new ArrayList<>();
                    while (i2 < length) {
                        Result.DiscountGiftCard discountGiftCard = new Result.DiscountGiftCard();
                        JSONArray jSONArray = optJSONArray;
                        try {
                            JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                            discountGiftCard.mGiftCardId = jSONObject3.getLong(MibiConstants.gO);
                            discountGiftCard.mGiftCardValue = jSONObject3.getLong("giftcardValue");
                            discountGiftCard.mOrderFeeRequiredValue = jSONObject3.getLong(MibiConstants.gQ);
                            result2.mDiscountGiftCards.add(discountGiftCard);
                            i2++;
                            optJSONArray = jSONArray;
                        } catch (JSONException e) {
                            throw new ResultException((Throwable) e);
                        }
                    }
                    return;
                }
                return;
            }
            throw new ResultException("result has error");
        } catch (JSONException e2) {
            throw new ResultException((Throwable) e2);
        }
    }

    public static class Result implements Serializable {
        public static final int COMMON_PAY = 0;
        public static final int DEDUCT_PAY = 1;
        public long mBalance;
        public ArrayList<DiscountGiftCard> mDiscountGiftCards;
        public long mGiftcardValue;
        public String mMerchantName;
        public int mOrderConsumedDiscountGiftcardId;
        public long mOrderPrice;
        public String mOrderTitle;
        public String mPartnerGiftcardName;
        public long mPartnerGiftcardValue;
        public int mPayMode;
        public String mResult;
        public int mResultErrorCode;
        public String mResultErrorDesc;
        public String mServerMarketType;
        public String mTradeId;
        public boolean mUseGiftcard;
        public boolean mUsePartnerGiftcard;

        public static class DiscountGiftCard implements Serializable {
            public long mGiftCardId;
            public long mGiftCardValue;
            public long mOrderFeeRequiredValue;
        }

        public long getTotalBalance() {
            long j = this.mBalance;
            if (this.mUseGiftcard) {
                j += this.mGiftcardValue;
            }
            return this.mUsePartnerGiftcard ? j + this.mPartnerGiftcardValue : j;
        }
    }
}
