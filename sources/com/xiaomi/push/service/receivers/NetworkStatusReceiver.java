package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.mipush.sdk.COSPushHelper;
import com.xiaomi.mipush.sdk.FTOSPushHelper;
import com.xiaomi.mipush.sdk.HWPushHelper;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.am;
import com.xiaomi.mipush.sdk.aw;
import com.xiaomi.mipush.sdk.b;
import com.xiaomi.mipush.sdk.bb;
import com.xiaomi.push.az;
import com.xiaomi.push.gz;
import com.xiaomi.push.service.ay;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NetworkStatusReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private static int f12937a = 1;

    /* renamed from: a  reason: collision with other field name */
    private static BlockingQueue<Runnable> f355a = new LinkedBlockingQueue();

    /* renamed from: a  reason: collision with other field name */
    private static ThreadPoolExecutor f356a = new ThreadPoolExecutor(f12937a, b, (long) c, TimeUnit.SECONDS, f355a);

    /* renamed from: a  reason: collision with other field name */
    private static boolean f357a = false;
    private static int b = 1;
    private static int c = 2;

    /* renamed from: b  reason: collision with other field name */
    private boolean f358b;

    public NetworkStatusReceiver() {
        this.f358b = false;
        this.f358b = true;
    }

    public NetworkStatusReceiver(Object obj) {
        this.f358b = false;
        f357a = true;
    }

    /* access modifiers changed from: private */
    public void a(Context context) {
        if (!aw.a(context).c() && b.a(context).j() && !b.a(context).n()) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(context, "com.xiaomi.push.service.XMPushService"));
                intent.setAction("com.xiaomi.push.network_status_changed");
                ay.a(context).a(intent);
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
            }
        }
        gz.a(context);
        if (az.c(context) && aw.a(context).g()) {
            aw.a(context).d();
        }
        if (az.c(context)) {
            if ("syncing".equals(am.a(context).a(bb.DISABLE_PUSH))) {
                MiPushClient.h(context);
            }
            if ("syncing".equals(am.a(context).a(bb.ENABLE_PUSH))) {
                MiPushClient.i(context);
            }
            if ("syncing".equals(am.a(context).a(bb.UPLOAD_HUAWEI_TOKEN))) {
                MiPushClient.j(context);
            }
            if ("syncing".equals(am.a(context).a(bb.UPLOAD_FCM_TOKEN))) {
                MiPushClient.k(context);
            }
            if ("syncing".equals(am.a(context).a(bb.UPLOAD_COS_TOKEN))) {
                MiPushClient.l(context);
            }
            if ("syncing".equals(am.a(context).a(bb.UPLOAD_FTOS_TOKEN))) {
                MiPushClient.m(context);
            }
            if (HWPushHelper.a() && HWPushHelper.b(context)) {
                HWPushHelper.a(context);
                HWPushHelper.g(context);
            }
            COSPushHelper.c(context);
            FTOSPushHelper.b(context);
        }
    }

    public static boolean a() {
        return f357a;
    }

    public void onReceive(Context context, Intent intent) {
        if (!this.f358b) {
            f356a.execute(new a(this, context));
        }
    }
}
