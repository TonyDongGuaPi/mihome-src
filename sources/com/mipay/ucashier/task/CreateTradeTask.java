package com.mipay.ucashier.task;

import android.content.Context;
import android.text.TextUtils;
import com.mipay.common.data.e;
import com.mipay.common.data.h;
import com.mipay.common.exception.f;
import com.mipay.common.exception.g;
import com.mipay.ucashier.data.PayType;
import com.mipay.ucashier.data.UCashierConstants;
import com.mipay.ucashier.task.BaseUCashierTask;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateTradeTask extends BaseUCashierTask<Void, Result> {

    public static class Result extends BaseUCashierTask.Result {
        public String mDeviceId;
        public PayType mLastPayType;
        public ArrayList<PayType> mPayTypes = new ArrayList<>();
        public String mProductName;
        public Long mTotalFee;
        public String mTradeId;
    }

    public CreateTradeTask(Context context) {
        super(context, Result.class);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws f {
        try {
            result.mTradeId = jSONObject.getString("tradeId");
            result.mProductName = jSONObject.getString(UCashierConstants.KEY_PRODUCT_NAME);
            result.mTotalFee = Long.valueOf(jSONObject.getLong(UCashierConstants.KEY_TOTAL_FEE));
            JSONObject optJSONObject = jSONObject.optJSONObject(UCashierConstants.KEY_LAST_PAY_TYPE);
            if (optJSONObject != null) {
                result.mLastPayType = PayType.fromJson(optJSONObject);
            }
            JSONArray jSONArray = jSONObject.getJSONArray(UCashierConstants.KEY_PAY_TYPES);
            for (int i = 0; i < jSONArray.length(); i++) {
                result.mPayTypes.add(PayType.fromJson(jSONArray.getJSONObject(i)));
            }
            result.mDeviceId = jSONObject.getString("deviceId");
        } catch (JSONException e) {
            throw new g((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    public e b(h hVar) {
        String f = hVar.f("order");
        String f2 = hVar.f("userId");
        e a2 = com.mipay.common.data.f.a(this.f8187a, UCashierConstants.getUrl(UCashierConstants.URL_CREATE_TRADE), true);
        e.b d = a2.d();
        d.a("order", (Object) f);
        if (!TextUtils.isEmpty(f2)) {
            d.a("userId", (Object) f2);
        }
        return a2;
    }
}
