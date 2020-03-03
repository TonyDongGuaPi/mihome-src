package miuipub.payment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.mibi.sdk.MibiFactory;
import java.security.InvalidParameterException;
import miuipub.payment.PaymentManager;

class XmsfLocalPaymentAdapter implements IXmsfPayment {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3002a = "XmsfLocalPaymentAdapter";
    private static final String b = "payment_bundle";
    private PaymentManager c;

    public XmsfLocalPaymentAdapter(Context context) {
        this.c = PaymentManager.a(context);
    }

    public void a(Activity activity, String str, String str2, Bundle bundle, IXmsfPaymentListener iXmsfPaymentListener) {
        if (activity != null) {
            a(activity, str2, bundle);
            return;
        }
        throw new InvalidParameterException("activity cannot be null");
    }

    public void a(Activity activity, String str, String str2, String str3, boolean z, boolean z2, boolean z3, Bundle bundle, IXmsfPaymentListener iXmsfPaymentListener) {
        this.c.a(activity, str, str2, str3, z, z2, z3, bundle, new PaymentListenerWrapper(iXmsfPaymentListener));
    }

    public void a(Activity activity, String str, String str2, String str3) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setPackage(activity.getPackageName());
        intent.setData(Uri.parse("https://publish.app.mibi.xiaomi.com?id=mibi.recharge"));
        activity.startActivity(intent);
    }

    public void a(Activity activity) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setPackage(activity.getPackageName());
        intent.setData(Uri.parse("https://publish.app.mibi.xiaomi.com?id=mibi.milicenter"));
        activity.startActivity(intent);
    }

    public void a(Activity activity, String str, String str2, String str3, IXmsfPaymentListener iXmsfPaymentListener) {
        this.c.a(activity, str, str2, str3, (PaymentManager.PaymentListener) new PaymentListenerWrapper(iXmsfPaymentListener));
    }

    public void a(Activity activity, String str, String str2, Bundle bundle) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setPackage(activity.getPackageName());
        intent.setAction("com.xiaomi.action.DEDUCT");
        if (bundle == null) {
            bundle = new Bundle();
        }
        boolean z = bundle.getBoolean("payment_skip_view", true);
        intent.putExtra("payment_bundle", bundle);
        intent.putExtra("deductSignOrder", str);
        intent.putExtra("payment_skip_view", z);
        intent.putExtra("deduct_channel", str2);
        activity.startActivityForResult(intent, MibiFactory.d);
    }

    public void b(Activity activity, String str, String str2, Bundle bundle) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setPackage(activity.getPackageName());
        intent.setAction(MibiFactory.J);
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

        public void a(String str, Bundle bundle) {
            this.b.a(str, bundle);
        }

        public void a(String str, int i, String str2, Bundle bundle) {
            this.b.a(str, i, str2, bundle);
        }
    }

    private void a(Activity activity, String str, Bundle bundle) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setPackage(activity.getPackageName());
        intent.setData(Uri.parse("https://publish.app.mibi.xiaomi.com?id=mibi.pay"));
        Bundle bundle2 = new Bundle();
        if (bundle == null) {
            bundle = bundle2;
        }
        bundle.putString("order", str);
        intent.putExtra("payment_fragment_arguments", bundle);
        activity.startActivityForResult(intent, 424);
    }
}
