package com.mi.global.shop.buy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.Request;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.PayResultWebActivity;
import com.mi.global.shop.buy.OrderdetailFragment;
import com.mi.global.shop.buy.adapter.WalletAdapter;
import com.mi.global.shop.buy.model.BuyOrderInfo;
import com.mi.global.shop.buy.model.MobikwikModel;
import com.mi.global.shop.buy.payu.PayU;
import com.mi.global.shop.model.buy.PayWalletBody;
import com.mi.global.shop.model.buy.PayWalletData;
import com.mi.global.shop.newmodel.pay.payparam.NewPayGoResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.widget.CommonButton;
import com.mi.global.shop.widget.RecycleViewDivider;
import com.mi.log.LogUtil;
import com.mi.multimonitor.CrashReport;
import com.mi.util.Coder;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.mobikwik.sdk.MobikwikSDK;
import com.mobikwik.sdk.lib.Transaction;
import com.mobikwik.sdk.lib.TransactionConfiguration;
import com.mobikwik.sdk.lib.User;
import com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType;
import com.paytm.pgsdk.PaytmClientCertificate;
import com.paytm.pgsdk.PaytmConstants;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.payu.sdk.Constants;
import com.payu.sdk.Params;
import com.payu.sdk.Payment;
import com.payu.sdk.ProcessPaymentActivity;
import com.payu.sdk.exceptions.HashException;
import com.payu.sdk.exceptions.MissingParameterException;
import com.squareup.wire.Wire;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class Walletfragment extends Fragment implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6859a = "wallet";
    public static final String b = "wallet";
    private static final String f = "Walletfragment";
    private static final String i = "payu_money";
    private static final String j = "mobikwik";
    private static final String k = "paytm";
    private HashMap<String, String> c;
    private double d = -1.0d;
    private View e;
    private CommonButton g;
    /* access modifiers changed from: private */
    public MobikwikModel h;
    private RecyclerView l;
    private WalletAdapter m;

    public void onCreate(Bundle bundle) {
        LogUtil.b(f, "onCreate");
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.b(f, "onCreateView");
        if (this.e == null) {
            this.e = layoutInflater.inflate(R.layout.shop_buy_confirm_payment_wallet, viewGroup, false);
        } else {
            ViewGroup viewGroup2 = (ViewGroup) this.e.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.e);
                LogUtil.b(f, "onCreateView remove from parent");
            }
        }
        this.l = (RecyclerView) this.e.findViewById(R.id.wallet_recycleview);
        this.l.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.m = new WalletAdapter(getActivity());
        this.l.addItemDecoration(new RecycleViewDivider(getActivity(), 0, Coder.a(0.0f), getResources().getColor(R.color.divider_color)));
        this.l.setAdapter(this.m);
        if (PayU.ae != null && PayU.ae.size() > 0) {
            Iterator<OrderdetailFragment.PaymentMethod> it = PayU.ae.iterator();
            while (it.hasNext()) {
                OrderdetailFragment.PaymentMethod next = it.next();
                if (next.f.equals("wallet")) {
                    a(next);
                    this.m.a(next.j);
                }
            }
        }
        this.g = (CommonButton) this.e.findViewById(R.id.buy_confirm_wallet_payorder);
        this.g.setOnClickListener(this);
        return this.e;
    }

    public void a(OrderdetailFragment.PaymentMethod paymentMethod) {
        if (paymentMethod.j != null && paymentMethod.j.size() > 0) {
            for (int i2 = 0; i2 < paymentMethod.j.size(); i2++) {
                if (i2 == 0) {
                    paymentMethod.j.get(i2).h = true;
                } else {
                    paymentMethod.j.get(i2).h = false;
                }
            }
        }
    }

    public String a() {
        if (PayU.ae == null || PayU.ae.size() <= 0) {
            return "";
        }
        Iterator<OrderdetailFragment.PaymentMethod> it = PayU.ae.iterator();
        while (it.hasNext()) {
            OrderdetailFragment.PaymentMethod next = it.next();
            if (next.f.equals("wallet")) {
                Iterator<OrderdetailFragment.PaymentMethod> it2 = next.j.iterator();
                while (it2.hasNext()) {
                    OrderdetailFragment.PaymentMethod next2 = it2.next();
                    if (next2.h) {
                        return next2.f;
                    }
                }
                continue;
            }
        }
        return "";
    }

    public void a(String str, final String str2) {
        Request request;
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aY()).buildUpon();
        buildUpon.appendQueryParameter("id", str);
        buildUpon.appendQueryParameter("bank", str2);
        buildUpon.appendQueryParameter("type", "wallet");
        AnonymousClass1 r3 = new SimpleCallback<NewPayGoResult>() {
            public void a(NewPayGoResult newPayGoResult) {
                ((ConfirmActivity) Walletfragment.this.getActivity()).hideLoading();
                if (newPayGoResult.data != null) {
                    String str = newPayGoResult.data.params;
                    try {
                        Walletfragment.this.a(new JSONObject(str));
                        if (str2.equals(Constants.PAY_BANK_PAYU)) {
                            Walletfragment.this.b();
                        } else if (str2.equals("mobikwik")) {
                            MobikwikModel unused = Walletfragment.this.h = Walletfragment.this.a(str);
                            Walletfragment.this.c();
                        } else if (str2.equals("paytm")) {
                            Walletfragment.this.c(str, newPayGoResult.data.ext);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        CrashReport.postCrash(Thread.currentThread(), (Throwable) e);
                        Walletfragment.this.b("");
                    }
                }
            }

            public void a(String str) {
                super.a(str);
                Walletfragment.this.b(str);
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(buildUpon.toString(), NewPayGoResult.class, r3);
        } else {
            request = new SimpleJsonRequest(buildUpon.toString(), NewPayGoResult.class, r3);
        }
        request.setTag(f);
        RequestQueueUtil.a().add(request);
        ((ConfirmActivity) getActivity()).showLoading();
    }

    public void b(String str, String str2) {
        Request request;
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aY()).buildUpon();
        buildUpon.appendQueryParameter("id", str);
        buildUpon.appendQueryParameter("bank", str2);
        buildUpon.appendQueryParameter("type", "wallet");
        buildUpon.appendQueryParameter("redirect", "1");
        AnonymousClass2 r3 = new SimpleCallback<NewPayGoResult>() {
            public void a(NewPayGoResult newPayGoResult) {
                ((ConfirmActivity) Walletfragment.this.getActivity()).hideLoading();
                if (newPayGoResult.data != null) {
                    String str = newPayGoResult.data.html;
                    if (TextUtils.isEmpty(str)) {
                        Walletfragment.this.b("");
                    }
                    Intent intent = new Intent(Walletfragment.this.getActivity(), PayResultWebActivity.class);
                    intent.putExtra("htmlString", str);
                    Walletfragment.this.getActivity().startActivityForResult(intent, 101);
                }
            }

            public void a(String str) {
                super.a(str);
                Walletfragment.this.b(str);
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(buildUpon.toString(), NewPayGoResult.class, r3);
        } else {
            request = new SimpleJsonRequest(buildUpon.toString(), NewPayGoResult.class, r3);
        }
        request.setTag(f);
        RequestQueueUtil.a().add(request);
        ((ConfirmActivity) getActivity()).showLoading();
    }

    /* access modifiers changed from: protected */
    public void a(JSONObject jSONObject) {
        Iterator<String> keys = jSONObject.keys();
        HashMap<String, String> hashMap = new HashMap<>();
        PayU.ak = null;
        while (keys.hasNext()) {
            String obj = keys.next().toString();
            try {
                if (obj.equals("hash")) {
                    PayU.ak = jSONObject.getString("hash");
                } else if (obj.equals("amount")) {
                    this.d = Double.parseDouble(jSONObject.getString("amount"));
                } else if (obj.equals("supportStoreCards")) {
                    PayU.av = Boolean.parseBoolean(jSONObject.getString("supportStoreCards"));
                } else if (obj.equals("key")) {
                    PayU.f6891a = jSONObject.getString("key");
                }
                hashMap.put(obj, jSONObject.getString(obj));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        this.c = hashMap;
    }

    /* access modifiers changed from: private */
    public void b() throws MissingParameterException, HashException {
        Payment payment = new Payment();
        payment.getClass();
        Payment.Builder builder = new Payment.Builder();
        Params params = new Params();
        HashMap<String, String> hashMap = this.c;
        String[] strArr = {"txnid", PayU.d, "amount", PayU.i, "email", "phone", "surl", "furl", "key", "hash"};
        for (int i2 = 0; i2 < strArr.length; i2++) {
            builder.set(strArr[i2], hashMap.get(strArr[i2]));
            params.put(strArr[i2], hashMap.get(strArr[i2]));
        }
        builder.set("mode", PayU.PaymentMode.PAYU_MONEY.toString());
        String a2 = PayU.a(getActivity()).a(builder.create(), params);
        Intent intent = new Intent(getActivity(), ProcessPaymentActivity.class);
        intent.putExtra("postData", a2);
        intent.addFlags(com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE);
        ((ConfirmActivity) getActivity()).startActivityForResult(intent, 100);
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.h == null) {
            b("");
        }
        TransactionConfiguration transactionConfiguration = new TransactionConfiguration();
        transactionConfiguration.setDebitWallet(true);
        transactionConfiguration.setPgResponseUrl(this.h.f);
        transactionConfiguration.setChecksumUrl(this.h.e);
        transactionConfiguration.setMerchantName(this.h.g);
        transactionConfiguration.setMbkId(this.h.d);
        if (!TextUtils.isEmpty(this.h.h)) {
            ((ConfirmActivity) getActivity()).setAfterPaySuccessUrl(Utils.b(this.h.h));
        }
        LogUtil.b(f, "MbkModel.pg_response_url:" + this.h.f);
        LogUtil.b(f, "MbkModel.checksum_url:" + this.h.e);
        LogUtil.b(f, "MbkModel.merchant_name:" + this.h.g);
        LogUtil.b(f, "MbkModel.mid:" + this.h.d);
        LogUtil.b(f, "MbkModel.order_id:" + this.h.f6886a);
        LogUtil.b(f, "MbkModel.amount:" + this.h.b);
        if (ShopApp.j()) {
            transactionConfiguration.setMode("0");
        } else {
            transactionConfiguration.setMode("1");
        }
        Transaction newTransaction = Transaction.Factory.newTransaction(new User("", ""), this.h.f6886a, this.h.b, PaymentInstrumentType.MK_WALLET);
        Intent intent = new Intent(getActivity(), MobikwikSDK.class);
        intent.putExtra(MobikwikSDK.EXTRA_TRANSACTION_CONFIG, transactionConfiguration);
        intent.putExtra(MobikwikSDK.EXTRA_TRANSACTION, newTransaction);
        getActivity().startActivityForResult(intent, 101);
    }

    private MobikwikModel a(PayWalletBody payWalletBody) {
        MobikwikModel mobikwikModel = new MobikwikModel();
        PayWalletData payWalletData = payWalletBody.Data;
        if (payWalletData != null) {
            mobikwikModel.b = (String) Wire.get(payWalletData.amount, "");
            mobikwikModel.e = (String) Wire.get(payWalletData.checksum_url, "");
            mobikwikModel.g = (String) Wire.get(payWalletData.merchant_name, "");
            mobikwikModel.d = (String) Wire.get(payWalletData.mid, "");
            mobikwikModel.f6886a = (String) Wire.get(payWalletData.order_id, "");
            mobikwikModel.c = (String) Wire.get(payWalletData.payment_type, "");
            mobikwikModel.f = (String) Wire.get(payWalletData.pg_response_url, "");
        }
        return mobikwikModel;
    }

    /* access modifiers changed from: private */
    public MobikwikModel a(String str) {
        MobikwikModel mobikwikModel = new MobikwikModel();
        try {
            JSONObject jSONObject = new JSONObject(str);
            mobikwikModel.b = jSONObject.optString("amount");
            mobikwikModel.e = jSONObject.optString("checksum_url");
            mobikwikModel.g = jSONObject.optString("merchant_name");
            mobikwikModel.d = jSONObject.optString(com.mobikwik.sdk.lib.Constants.MID);
            mobikwikModel.f6886a = jSONObject.optString("order_id");
            mobikwikModel.c = jSONObject.optString("payment_type");
            mobikwikModel.f = jSONObject.optString("pg_response_url");
            mobikwikModel.h = jSONObject.optString("afterPaySuccessUrl");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return mobikwikModel;
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            MiToast.a((Context) ShopApp.g(), R.string.buy_confirm_order_payfailed, 1);
        } else {
            MiToast.a((Context) ShopApp.g(), (CharSequence) str, 1);
        }
        ((ConfirmActivity) getActivity()).hideLoading();
    }

    public void onClick(View view) {
        if (view == this.g) {
            try {
                BuyOrderInfo buyOrderInfo = ((ConfirmActivity) getActivity()).getconfirmOrder();
                if (a().equals(i)) {
                    a(buyOrderInfo.f6881a, Constants.PAY_BANK_PAYU);
                }
                if (a().equals("mobikwik")) {
                    b(buyOrderInfo.f6881a, "mobikwik");
                }
                if (a().equals("paytm")) {
                    a(buyOrderInfo.f6881a, "paytm");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void c(String str, String str2) {
        PaytmPGService paytmPGService;
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        if (ShopApp.j()) {
            paytmPGService = PaytmPGService.b();
        } else {
            paytmPGService = PaytmPGService.c();
        }
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("afterPaySuccessUrl");
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (str2.indexOf("##" + next + "##") == -1) {
                    hashMap.put(next, jSONObject.getString(next));
                }
            }
            final String b2 = Utils.b(string);
            paytmPGService.a(new PaytmOrder(hashMap), (PaytmClientCertificate) null);
            paytmPGService.a(getActivity(), true, true, new PaytmPaymentTransactionCallback() {
                public void a(String str) {
                    Log.d("Payment Transaction", "Payment Transaction : " + str);
                }

                public void a(Bundle bundle) {
                    Log.d("Payment Transaction", "Payment Transaction : " + bundle.toString());
                    try {
                        String string = bundle.getString(PaytmConstants.e);
                        String string2 = bundle.getString(PaytmConstants.f);
                        if (string.equals("01")) {
                            ((ConfirmActivity) Walletfragment.this.getActivity()).onPaytmTransactionSuccess(b2);
                        } else {
                            MiToast.a((Context) Walletfragment.this.getActivity(), (CharSequence) string2, 0);
                        }
                    } catch (Exception unused) {
                        MiToast.a((Context) Walletfragment.this.getActivity(), (CharSequence) "Payment Failed", 0);
                    }
                }

                public void a() {
                    Log.d("Payment Transaction", "Payment Transaction networkNotAvailable");
                }

                public void b(String str) {
                    Log.d("Payment Transaction", "Payment Transaction " + str);
                }

                public void a(int i, String str, String str2) {
                    Log.d("Payment Transaction", "Payment Transaction Failed " + str);
                }

                public void b() {
                    Log.d("Payment Transaction", "Payment Transaction Failed onBackPressedCancelTransaction");
                }

                public void a(String str, Bundle bundle) {
                    Log.d("Payment Transaction", "Payment Transaction Failed " + str);
                }
            });
        } catch (Exception unused) {
            MiToast.a((Context) getActivity(), (CharSequence) "params error", 0);
        }
    }
}
