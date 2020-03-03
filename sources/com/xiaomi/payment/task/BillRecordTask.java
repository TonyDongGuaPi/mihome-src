package com.xiaomi.payment.task;

import android.content.Context;
import android.text.TextUtils;
import com.mibi.common.base.BasePaymentTask;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.data.Utils;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.xiaomi.payment.data.MibiConstants;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BillRecordTask extends BasePaymentTask<Void, Result> {
    protected final int c = 20;
    private int d = 0;

    public static class Result extends BasePaymentTask.Result {
        public ArrayList<BillRecordItem> mBillItemsList = new ArrayList<>();
        public long mTotalNum;

        public static class BillRecordItem implements Serializable {
            public String mBenefitID;
            public long mBillFee;
            public String mBillId;
            public String mBillRecordDesc;
            public String mBillRecordType;
            public long mBillTime;
            public String mChargeType;
            public String mConsumeDetail;
            public String mPartnerGiftCardName;
            public String mPayUserID;
        }
    }

    public BillRecordTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    public void a(int i) {
        this.d = i;
    }

    public void c() {
        if (this.d > 0) {
            this.d--;
        }
    }

    public int d() {
        return this.d;
    }

    public boolean e() {
        return this.d == 0;
    }

    public void q() {
        this.d = 0;
    }

    public void r() {
        this.d += 20;
    }

    /* access modifiers changed from: protected */
    public void b(SortedParameter sortedParameter) {
        sortedParameter.a(MibiConstants.dK, (Object) Integer.valueOf(this.d));
        sortedParameter.a("pageSize", (Object) 20);
    }

    /* access modifiers changed from: protected */
    public Connection a(SortedParameter sortedParameter) {
        int e = sortedParameter.e(MibiConstants.dK);
        int e2 = sortedParameter.e("pageSize");
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bS), this.f7453a);
        SortedParameter d2 = a2.d();
        d2.a(MibiConstants.dK, (Object) Integer.valueOf(e));
        d2.a("pageSize", (Object) Integer.valueOf(e2));
        return a2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        try {
            result.mTotalNum = jSONObject.getLong(MibiConstants.dL);
            JSONArray jSONArray = jSONObject.getJSONArray(MibiConstants.dM);
            for (int i = 0; i < jSONArray.length(); i++) {
                Result.BillRecordItem billRecordItem = new Result.BillRecordItem();
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                billRecordItem.mBillRecordType = jSONObject2.getString(MibiConstants.dH);
                billRecordItem.mPayUserID = jSONObject2.getString(MibiConstants.fL);
                if (TextUtils.equals(billRecordItem.mBillRecordType, MibiConstants.dn)) {
                    billRecordItem.mBillId = jSONObject2.getString(MibiConstants.dr);
                    billRecordItem.mChargeType = jSONObject2.getString(MibiConstants.dG);
                    billRecordItem.mBillFee = jSONObject2.getLong(MibiConstants.dq);
                    billRecordItem.mBillRecordDesc = jSONObject2.getString(MibiConstants.dJ);
                    billRecordItem.mBillTime = jSONObject2.optLong(MibiConstants.dN);
                    billRecordItem.mBenefitID = jSONObject2.getString(MibiConstants.fK);
                    if (!Utils.a(billRecordItem.mBillId, billRecordItem.mChargeType, billRecordItem.mBillRecordDesc, billRecordItem.mPayUserID, billRecordItem.mBenefitID)) {
                        throw new ResultException("result has error");
                    }
                } else if (TextUtils.equals(billRecordItem.mBillRecordType, "trade")) {
                    billRecordItem.mBillId = jSONObject2.getString("tradeId");
                    billRecordItem.mBillTime = jSONObject2.optLong("payTime");
                    billRecordItem.mBillRecordDesc = jSONObject2.getString(MibiConstants.dR);
                    billRecordItem.mBillFee = jSONObject2.getLong(MibiConstants.dQ);
                    billRecordItem.mPartnerGiftCardName = jSONObject2.optString(MibiConstants.eI);
                    billRecordItem.mConsumeDetail = jSONObject2.optString(MibiConstants.fN);
                    billRecordItem.mBenefitID = jSONObject2.getString(MibiConstants.fM);
                    if (!Utils.a(billRecordItem.mBillId, billRecordItem.mBillRecordDesc, billRecordItem.mPayUserID, billRecordItem.mBenefitID)) {
                        throw new ResultException("result has error");
                    }
                } else {
                    throw new ResultException("result has error");
                }
                result.mBillItemsList.add(billRecordItem);
            }
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }
}
