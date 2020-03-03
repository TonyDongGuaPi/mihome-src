package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.text.TextUtils;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.data.Utils;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.rxjava.RxPageableTask;
import com.xiaomi.payment.data.MibiConstants;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RxGiftcardTask extends RxPageableTask<Result> {
    private String c = "froze";

    public static class Result {

        /* renamed from: a  reason: collision with root package name */
        public ArrayList<GiftcardItem> f12420a = new ArrayList<>();

        public static class GiftcardItem implements Serializable {
            public String mActivityDesc;
            public String mAppTitle;
            public long mExpiredTime;
            public long mGiftcardAvailableBalance;
            public String mGiftcardId;
            public long mGiftcardTotalBalance;
            public boolean mIsExpired;
            public boolean mIsFrozen;
            public String mMarketGiftcardName;
            public int mOrderFeeRequired;
            public String mPackageName;
        }
    }

    public RxGiftcardTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    public void a(int i) {
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a(CommonConstants.aU, (Object) Integer.valueOf(i));
        super.a(sortedParameter);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        int e = sortedParameter.e(CommonConstants.aS);
        sortedParameter.e("pageSize");
        int e2 = sortedParameter.e(CommonConstants.aU);
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bV), this.f7587a);
        SortedParameter d = a2.d();
        d.a(CommonConstants.aS, (Object) Integer.valueOf(e));
        d.a(CommonConstants.aU, (Object) Integer.valueOf(e2));
        a2.a(true);
        return a2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(MibiConstants.eC);
            int i = 0;
            while (i < jSONArray.length()) {
                Result.GiftcardItem giftcardItem = new Result.GiftcardItem();
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                giftcardItem.mGiftcardId = jSONObject2.getString("id");
                giftcardItem.mGiftcardTotalBalance = jSONObject2.getLong(MibiConstants.eN);
                giftcardItem.mGiftcardAvailableBalance = jSONObject2.getLong(MibiConstants.eO);
                giftcardItem.mActivityDesc = jSONObject2.getString(MibiConstants.eM);
                giftcardItem.mExpiredTime = jSONObject2.getLong(MibiConstants.eP);
                giftcardItem.mIsFrozen = TextUtils.equals(this.c, jSONObject2.getString("status"));
                giftcardItem.mAppTitle = jSONObject2.optString("appName");
                giftcardItem.mPackageName = jSONObject2.optString("package");
                giftcardItem.mMarketGiftcardName = jSONObject2.optString(MibiConstants.eS);
                giftcardItem.mIsExpired = System.currentTimeMillis() > giftcardItem.mExpiredTime;
                giftcardItem.mOrderFeeRequired = jSONObject2.optInt(MibiConstants.gQ);
                if (Utils.a(giftcardItem.mGiftcardId, giftcardItem.mActivityDesc)) {
                    result.f12420a.add(giftcardItem);
                    i++;
                } else {
                    throw new ResultException("result has error");
                }
            }
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }
}
