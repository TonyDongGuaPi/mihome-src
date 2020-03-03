package com.xiaomi.jr.appbase.accounts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.xiaomi.jr.account.AccountChangeEvent;
import com.xiaomi.jr.account.PostLogoutTasks;
import com.xiaomi.jr.appbase.app.MiFiAppController;
import com.xiaomi.jr.common.utils.MifiLog;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MiFiAccountMonitor {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1391a = "MiFiAccountMonitor";
    private static final String b = "android.accounts.LOGIN_ACCOUNTS_POST_CHANGED";
    private static final String c = "extra_update_type";
    private static MiFiAccountMonitor d = new MiFiAccountMonitor();
    private Context e;
    private BroadcastReceiver f = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (MiFiAccountMonitor.b.equals(action)) {
                int intExtra = intent.getIntExtra("extra_update_type", -1);
                MifiLog.c(MiFiAccountMonitor.f1391a, "action:" + action + ", updateType:" + intExtra);
                EventBus.a().d((Object) new AccountChangeEvent(intExtra));
            }
        }
    };

    private MiFiAccountMonitor() {
    }

    public static MiFiAccountMonitor a() {
        return d;
    }

    public void a(Context context) {
        this.e = context.getApplicationContext();
        this.e.registerReceiver(this.f, new IntentFilter(b));
        EventBus.a().a((Object) this);
    }

    public void b() {
        if (this.e != null && this.f != null) {
            this.e.unregisterReceiver(this.f);
            EventBus.a().c((Object) this);
            this.f = null;
        }
    }

    @Subscribe
    public void onAccountChange(AccountChangeEvent accountChangeEvent) {
        if (2 == accountChangeEvent.a()) {
            MifiLog.e(f1391a, "System Account Login Succeeded. Maybe confirmed credential just now.");
        } else if (1 == accountChangeEvent.a()) {
            MifiLog.e(f1391a, "System Account Logout. Do clean work and exit.");
            PostLogoutTasks.a(this.e);
            MiFiAppController.a().d();
        }
    }
}
