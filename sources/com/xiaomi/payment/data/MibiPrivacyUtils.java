package com.xiaomi.payment.data;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.MibiLicenseActivity;

public class MibiPrivacyUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12227a = "105";
    public static final String b = "HOMEPAGE";
    public static final String c = "PAYMENTPAGE";
    public static final String d = "DEDUCTPAGE";
    public static final String e = "THIRDPARTY";

    public interface PrivacyCallBack {
        void a();
    }

    public static void a(final Context context, final PrivacyCallBack privacyCallBack) {
        new AlertDialog.Builder(context).setTitle(R.string.mibi_privacy_tip_title).setMessage(R.string.mibi_privacy_tip_message).setPositiveButton(R.string.mibi_order_pay_uncertain_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Utils.b(context, MibiLicenseActivity.KEY_USER_CONFIRM, false);
                Utils.b(context, MibiLicenseActivity.KEY_APP_ENABLE, false);
                privacyCallBack.a();
            }
        }).show().setCancelable(false);
    }
}
