package com.xiaomi.jr.appbase.accounts;

import android.content.Context;
import com.xiaomi.jr.account.AccountNotifier;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.agreement.AgreementUpdateManager;
import com.xiaomi.jr.appbase.CustomizedSnippets;
import com.xiaomi.jr.common.utils.Constants;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.stats.SensorsDataManager;
import com.xiaomi.jr.stats.StatUtils;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import java.util.HashMap;
import java.util.Map;

public class MiFiAccountNotifierImpl extends AccountNotifier {

    /* renamed from: a  reason: collision with root package name */
    protected static final int f1392a = -1;
    private static final String b = "MiFiAccountNotifier";
    private static final int c = -2;
    private static final int d = 4;

    public void a(Context context, int i) {
        if (!(i == -1)) {
            MifiLog.e(b, "mLoginCallBack - failure. errorCode = " + i);
            HashMap hashMap = new HashMap();
            hashMap.put("errorCode", String.valueOf(i));
            StatUtils.a(SensorsDataManager.o, "LoginFailed", (Map<String, String>) hashMap);
            if (i == 4) {
                MifiLog.a(b, "login cancelled...");
            } else {
                CustomizedSnippets.a(CustomizedSnippets.e, false);
            }
        } else {
            MifiLog.b(b, "mLoginCallBack - success. errorCode = " + i);
            if (Constants.f1410a) {
                AgreementUpdateManager.a().f();
            }
            CustomizedSnippets.a(CustomizedSnippets.e, true);
        }
        StatUtils.a(XiaomiAccountManager.h());
        super.a(context, i);
    }

    public void a(Context context) {
        StatUtils.a((String) null);
        AuthenticatorUtil.clearAllXiaomiAccountCookies(context);
        super.a(context);
    }
}
