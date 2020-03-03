package miuipub.payment;

import android.app.Activity;
import android.os.Bundle;

public interface IXmsfPayment {
    void a(Activity activity);

    void a(Activity activity, String str, String str2, Bundle bundle);

    void a(Activity activity, String str, String str2, Bundle bundle, IXmsfPaymentListener iXmsfPaymentListener);

    void a(Activity activity, String str, String str2, String str3);

    void a(Activity activity, String str, String str2, String str3, IXmsfPaymentListener iXmsfPaymentListener);

    void a(Activity activity, String str, String str2, String str3, boolean z, boolean z2, boolean z3, Bundle bundle, IXmsfPaymentListener iXmsfPaymentListener);

    void b(Activity activity, String str, String str2, Bundle bundle);
}
