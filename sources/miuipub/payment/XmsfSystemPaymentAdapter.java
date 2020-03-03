package miuipub.payment;

import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.mibi.sdk.IMibiAccountProvider;
import com.mibi.sdk.MibiFactory;
import miui.payment.PaymentManager;

class XmsfSystemPaymentAdapter implements IXmsfPayment {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3005a = "payment_bundle";
    private static final String b = "PaymentAdapter";
    private AccountManager c;
    private PaymentManager d;

    public XmsfSystemPaymentAdapter(Context context) {
        this.c = AccountManager.get(context);
        this.d = PaymentManager.get(context);
    }

    public AuthenticatorDescription[] a() {
        return this.c.getAuthenticatorTypes();
    }

    public void a(Activity activity, String str, String str2, Bundle bundle, IXmsfPaymentListener iXmsfPaymentListener) {
        MibiFactory.a(activity, true).b(activity, str2, (IMibiAccountProvider) null, bundle);
    }

    public void a(Activity activity, String str, String str2, String str3, boolean z, boolean z2, boolean z3, Bundle bundle, IXmsfPaymentListener iXmsfPaymentListener) {
        throw new IllegalStateException("这个函数不应该调用系统米币,可能会出现安全问题");
    }

    public void a(Activity activity, String str, String str2, String str3) {
        this.d.recharge(activity, str, str2, str3);
    }

    public void a(Activity activity) {
        MibiFactory.a(activity, true).a(activity, (IMibiAccountProvider) null);
    }

    public void a(Activity activity, String str, String str2, String str3, IXmsfPaymentListener iXmsfPaymentListener) {
        this.d.getMiliBalance(activity, str, str2, str3, new PaymentListenerWrapper(iXmsfPaymentListener));
    }

    public void a(Activity activity, String str, String str2, Bundle bundle) {
        Toast.makeText(activity.getApplicationContext(), "should not call system deduct", 0).show();
        Log.e(b, "should not call system deduct");
    }

    public void b(Activity activity, String str, String str2, Bundle bundle) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setPackage("com.xiaomi.payment");
        intent.setAction(MibiFactory.J);
        if (intent.resolveActivity(activity.getPackageManager()) == null) {
            Log.e(b, "sign deduct not support in this version");
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        boolean z = bundle.getBoolean("payment_skip_view", true);
        intent.putExtra("payment_bundle", bundle);
        intent.putExtra("deductSignOrder", str);
        intent.putExtra("payment_skip_view", z);
        intent.putExtra("sign_deduct_channel", str2);
        activity.startActivityForResult(intent, MibiFactory.e);
    }

    private class PaymentListenerWrapper implements PaymentManager.PaymentListener {
        private IXmsfPaymentListener b;

        public PaymentListenerWrapper(IXmsfPaymentListener iXmsfPaymentListener) {
            this.b = iXmsfPaymentListener;
        }

        public void onSuccess(String str, Bundle bundle) {
            this.b.a(str, bundle);
        }

        public void onFailed(String str, int i, String str2, Bundle bundle) {
            this.b.a(str, i, str2, bundle);
        }
    }
}
