package com.xiaomi.jr.appbase.accounts;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.jr.account.MiFiAccountUtils;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.appbase.accounts.XiaomiCUserIdHelper;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.ThreadUtils;
import com.xiaomi.jr.common.utils.Utils;

public class XiaomiCUserIdHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10315a = "XiaomiCUserIdHelper";

    public interface XiaomiCUserIdObserver {
        void a(String str);
    }

    public static void a(Context context, XiaomiCUserIdObserver xiaomiCUserIdObserver) {
        Utils.a();
        if (xiaomiCUserIdObserver != null) {
            if (!XiaomiAccountManager.a().d()) {
                xiaomiCUserIdObserver.a((String) null);
            }
            String g = XiaomiAccountManager.g();
            if (!TextUtils.isEmpty(g)) {
                xiaomiCUserIdObserver.a(g);
            } else {
                ThreadUtils.b(new Runnable(context, g, xiaomiCUserIdObserver) {
                    private final /* synthetic */ Context f$0;
                    private final /* synthetic */ String f$1;
                    private final /* synthetic */ XiaomiCUserIdHelper.XiaomiCUserIdObserver f$2;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        XiaomiCUserIdHelper.a(this.f$0, this.f$1, this.f$2);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Context context, String str, XiaomiCUserIdObserver xiaomiCUserIdObserver) {
        String b = MiFiAccountUtils.b(context);
        MifiLog.c(f10315a, "fetch cUserId = " + b);
        XiaomiAccountManager.a(str);
        xiaomiCUserIdObserver.a(str);
    }

    public static String a() {
        Utils.a();
        if (!XiaomiAccountManager.a().d()) {
            return null;
        }
        String g = XiaomiAccountManager.g();
        if (TextUtils.isEmpty(g)) {
            MifiLog.e(f10315a, "Error! CUserId is invalid!");
        }
        return g;
    }
}
