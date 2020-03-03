package com.mi.global.shop.buy;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import com.alipay.sdk.cons.b;
import com.google.android.apps.nbu.paisa.inapp.client.api.PaymentsClient;
import com.google.android.apps.nbu.paisa.inapp.client.api.Wallet;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mi.global.shop.model.SyncModel;
import com.payu.sdk.Constants;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleTezPayWrapper {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6832a = 258;
    private static final String c = "com.google.android.apps.nbu.paisa.user";
    /* access modifiers changed from: private */
    public static final String d = "GoogleTezPayWrapper";
    private PaymentsClient b = Wallet.getPaymentsClient();
    private Activity e;

    public interface IsReadyListener {
        void a();

        void b();
    }

    public void a(Activity activity, String str, final IsReadyListener isReadyListener) {
        this.e = activity;
        if (isReadyListener != null) {
            if (SyncModel.data == null || SyncModel.data.switchInfo == null || SyncModel.data.switchInfo.assembleTEZ) {
                try {
                    if (!c()) {
                        isReadyListener.a();
                    } else {
                        this.b.isReadyToPay(this.e, str).addOnCompleteListener(new OnCompleteListener<Boolean>() {
                            public void onComplete(Task<Boolean> task) {
                                try {
                                    if (task.getResult(ApiException.class).booleanValue()) {
                                        isReadyListener.b();
                                        Log.d(GoogleTezPayWrapper.d, "TezIsReadyToPay Successful");
                                        return;
                                    }
                                    Log.d(GoogleTezPayWrapper.d, "TezIsReadyToPay Failed");
                                    isReadyListener.a();
                                } catch (ApiException unused) {
                                    isReadyListener.a();
                                }
                            }
                        });
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    isReadyListener.a();
                }
            } else {
                isReadyListener.a();
            }
        }
    }

    public void a(Activity activity, String str) {
        this.e = activity;
        try {
            Log.d(d, str);
            this.b.loadPaymentData(this.e, str, 258);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            Log.e(d, "Unable to check Tez signature.", e2);
            a("An error has occurred within the Tez SDK. Please check the log for more details.");
        }
    }

    public static String a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) throws JSONException {
        return PaymentsUtil.a(PaymentsUtil.a(str2, ""), (List<JSONObject>) Arrays.asList(new JSONObject[]{PaymentsUtil.a(str5, str6, str, str4, "", str10)})).toString();
    }

    private void b() {
        try {
            Uri build = new Uri.Builder().scheme(Constants.PAYTYPE_UPI).authority("pay").appendQueryParameter(b.k, "vpaText").appendQueryParameter("pn", "nameText").appendQueryParameter("mc", "mccText").appendQueryParameter(BaseInfo.KEY_TIME_RECORD, "transactionReferenceIdText").appendQueryParameter(BaseInfo.KEY_THREAD_NAME, "transactionNoteText").appendQueryParameter("am", "amountText").appendQueryParameter("cu", Constant.KEY_CURRENCYTYPE_INR).appendQueryParameter("url", "referenceUrlText").build();
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(build);
            intent.setPackage(c);
            this.e.startActivityForResult(intent, 258);
        } catch (Exception e2) {
            Log.e(d, "intent payment failed", e2);
            e2.printStackTrace();
        }
    }

    private boolean c() throws PackageManager.NameNotFoundException {
        try {
            return Wallet.getPaymentsClient().isGooglePayInstalled(this.e, 2);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private boolean d() throws PackageManager.NameNotFoundException {
        try {
            return Wallet.getPaymentsClient().isGooglePayInstalled(this.e, 1);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private void a(int i) {
        if (i != 409) {
            a("Error starting a payment in Tez. Please check the log for more details.");
        } else {
            a("Account payment error. Please retry with a different payment method.");
        }
    }

    private void a(String str) {
        Toast.makeText(this.e, str, 0).show();
    }
}
