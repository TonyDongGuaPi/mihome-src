package com.xiaomi.jr.agreement;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.jr.common.utils.UrlUtils;
import java.util.ArrayList;

public class AgreementUpdateUtils {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f1383a;

    public static void a(boolean z) {
        f1383a = z;
    }

    public static void a(Context context, AgreementUpdateInfo agreementUpdateInfo) {
        String str;
        if (agreementUpdateInfo == null) {
            return;
        }
        if ((agreementUpdateInfo.a() || agreementUpdateInfo.b()) && agreementUpdateInfo.c() != null && !agreementUpdateInfo.c().isEmpty() && !TextUtils.isEmpty(agreementUpdateInfo.d())) {
            ArrayList<AgreementInfo> c = agreementUpdateInfo.c();
            boolean z = false;
            String str2 = "";
            String d = agreementUpdateInfo.d();
            int i = 0;
            while (i < c.size()) {
                AgreementInfo agreementInfo = c.get(i);
                String a2 = UrlUtils.a(agreementInfo.d(), "from", "local");
                StringBuilder sb = new StringBuilder();
                sb.append(Operators.BLOCK_START_STR);
                int i2 = i + 1;
                sb.append(i2);
                sb.append("}");
                d = d.replace(sb.toString(), String.format("<txt href='%s'>《%s》</txt>", new Object[]{a2, agreementInfo.b()}));
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str2);
                if (i == 0) {
                    str = agreementInfo.a();
                } else {
                    str = JSMethod.NOT_SET + agreementInfo.a();
                }
                sb2.append(str);
                str2 = sb2.toString();
                i = i2;
            }
            if (agreementUpdateInfo.a() && !f1383a) {
                z = true;
            }
            AgreementUpdateManager.a().c().e().a(context, context.getString(z ? R.string.agreement : R.string.agreement_update), d, false, context.getString(z ? R.string.dialog_confirm_text_confirm_and_continue : R.string.dialog_confirm_text_i_know), z ? context.getString(R.string.dialog_negative_text_logout) : null, new DialogInterface.OnClickListener(context, str2) {
                private final /* synthetic */ Context f$0;
                private final /* synthetic */ String f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    AgreementUpdateUtils.a(this.f$0, this.f$1, dialogInterface, i);
                }
            }, $$Lambda$AgreementUpdateUtils$ZXMgKb1VoLk8Yl2I2Tw8l0uHUs.INSTANCE, "agreement");
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Context context, String str, DialogInterface dialogInterface, int i) {
        AgreementUpdateManager.a().a(context, str);
        if (AgreementUpdateManager.a().b() != null) {
            AgreementUpdateManager.a().b().a();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(DialogInterface dialogInterface, int i) {
        if (AgreementUpdateManager.a().b() != null) {
            AgreementUpdateManager.a().b().b();
        }
    }
}
