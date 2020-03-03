package com.mipay.ucashier.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.mipay.common.base.StepActivity;
import com.mipay.common.base.l;
import com.mipay.common.base.s;
import com.mipay.common.data.h;
import com.mipay.ucashier.R;
import com.mipay.ucashier.data.UCashierConstants;
import com.mipay.ucashier.task.BaseUCashierTaskAdapter;
import com.mipay.ucashier.task.CreateTradeTask;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateTradeFragment extends BaseUCashierFragment {
    /* access modifiers changed from: private */
    public String g;
    /* access modifiers changed from: private */
    public String h;
    private a i;
    /* access modifiers changed from: private */
    public int j = 2;
    /* access modifiers changed from: private */
    public String k = null;
    private String l = null;

    private class a extends BaseUCashierTaskAdapter<CreateTradeTask, Void, CreateTradeTask.Result> {
        public a(Context context, s sVar) {
            super(context, sVar, new CreateTradeTask(context));
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void c(CreateTradeTask.Result result) {
            if (result.mLastPayType != null || result.mPayTypes.size() <= 1) {
                CreateTradeFragment.this.a(result);
            } else {
                CreateTradeFragment.this.b(result);
            }
        }

        /* access modifiers changed from: protected */
        public void a(String str, int i, CreateTradeTask.Result result) {
            int unused = CreateTradeFragment.this.j = 1;
            String unused2 = CreateTradeFragment.this.k = str;
            CreateTradeFragment.this.finish();
        }

        /* access modifiers changed from: protected */
        public h c() {
            h hVar = new h();
            hVar.a("order", (Object) CreateTradeFragment.this.g);
            hVar.a("userId", (Object) CreateTradeFragment.this.h);
            return hVar;
        }

        /* access modifiers changed from: protected */
        public void d() {
            super.d();
            CreateTradeFragment.this.a(R.string.ucashier_handle_loading);
        }

        /* access modifiers changed from: protected */
        public boolean e() {
            CreateTradeFragment.this.e();
            return super.e();
        }
    }

    private Intent a(int i2, String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra("code", i2);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("message", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("result", str2);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", i2);
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("message", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("result", str2);
            }
        } catch (JSONException unused) {
        }
        intent.putExtra("fullResult", jSONObject.toString());
        return intent;
    }

    /* access modifiers changed from: private */
    public void a(CreateTradeTask.Result result) {
        Bundle bundle = new Bundle();
        bundle.putString("tradeId", result.mTradeId);
        bundle.putString(UCashierConstants.KEY_PRODUCT_NAME, result.mProductName);
        bundle.putLong(UCashierConstants.KEY_TOTAL_FEE, result.mTotalFee.longValue());
        bundle.putString("deviceId", result.mDeviceId);
        bundle.putSerializable(UCashierConstants.KEY_PAY_TYPES, result.mPayTypes);
        bundle.putSerializable(UCashierConstants.KEY_LAST_PAY_TYPE, result.mLastPayType);
        a((Class<? extends l>) CheckTradeInfoFragment.class, bundle, (String) null, (Class<? extends StepActivity>) CounterActivity.class);
    }

    private void b(int i2, String str) {
        getActivity().setResult(0, a(i2, str, (String) null));
    }

    /* access modifiers changed from: private */
    public void b(CreateTradeTask.Result result) {
        Bundle bundle = new Bundle();
        bundle.putString("tradeId", result.mTradeId);
        bundle.putString("deviceId", result.mDeviceId);
        bundle.putSerializable(UCashierConstants.KEY_PAY_TYPES, result.mPayTypes);
        a((Class<? extends l>) ChoosePayTypeFragment.class, bundle, (String) null, (Class<? extends StepActivity>) CounterActivity.class);
    }

    private void d(String str) {
        new Bundle().putString("result", str);
        getActivity().setResult(-1, a(0, "success", str));
    }

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
        super.a(bundle);
        this.g = bundle.getString("order");
        this.h = bundle.getString("userId");
    }

    public void doActivityCreated(Bundle bundle) {
        super.doActivityCreated(bundle);
        a("trade");
        this.i = new a(getActivity(), getTaskManager());
        this.i.start();
    }

    public void doFragmentResult(int i2, int i3, Bundle bundle) {
        String str;
        super.doFragmentResult(i2, i3, bundle);
        if (i3 == RESULT_ERROR) {
            this.j = bundle.getInt("errcode", 2);
            str = bundle.getString("errDesc", "");
        } else {
            if (i3 == RESULT_CANCELED) {
                this.j = 2;
                str = "user cancelled";
            }
            finish();
        }
        this.k = str;
        finish();
    }

    public void doJumpBackResult(int i2, Bundle bundle) {
        String str;
        super.doJumpBackResult(i2, bundle);
        if (i2 == RESULT_OK) {
            this.j = 0;
            this.l = bundle.getString("result");
        } else {
            if (i2 == RESULT_ERROR) {
                this.j = bundle.getInt("errcode", 2);
                str = bundle.getString("errDesc", "");
            } else if (i2 == RESULT_CANCELED) {
                this.j = 2;
                str = "user cancelled";
            }
            this.k = str;
        }
        finish();
    }

    public void finish() {
        if (this.j == 0) {
            d(this.l);
        } else {
            b(this.j, this.k);
        }
        getActivity().finish();
    }
}
