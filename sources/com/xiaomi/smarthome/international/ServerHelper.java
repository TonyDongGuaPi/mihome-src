package com.xiaomi.smarthome.international;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.library.common.util.MijiaWrapper;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;

public class ServerHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f18415a = "com.xiaomi.smarthome.serverconfig";
    public static final String b = "action_select_server_local_broadcast_complete";
    public static final String c = "action_select_server_local_broadcast_complete_final";
    public static final String d = "param_key";
    public static final int e = 1;
    public static final int f = 2;
    private static final String g = "pref_key_ignore";
    private static SharedPreferences h;

    public interface InternationalCallback {
        void a();

        void b();
    }

    private static void b() {
        if (h == null) {
            h = SharePrefsManager.a(SHApplication.getAppContext(), f18415a);
        }
    }

    private static void a(String str, boolean z) {
        b();
        SharePrefsManager.a(h, str, z);
    }

    private static boolean b(String str, boolean z) {
        b();
        return SharePrefsManager.b(h, str, z);
    }

    public static boolean a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AccountManager.b, 0);
        if (!TextUtils.isEmpty(SharePrefsManager.b(sharedPreferences, AccountManager.c, "")) || !TextUtils.isEmpty(SharePrefsManager.b(sharedPreferences, AccountManager.d, ""))) {
            return true;
        }
        return false;
    }

    public static void a(Context context, int i, InternationalCallback internationalCallback, String str) {
        final MijiaWrapper mijiaWrapper = new MijiaWrapper(internationalCallback);
        final LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context);
        AnonymousClass1 r1 = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                instance.unregisterReceiver(this);
                int intExtra = intent.getIntExtra("param_key", 0);
                InternationalCallback internationalCallback = (InternationalCallback) mijiaWrapper.f18690a;
                mijiaWrapper.f18690a = null;
                if (intExtra == 1) {
                    if (internationalCallback != null) {
                        internationalCallback.a();
                    }
                } else if (intExtra == 2 && internationalCallback != null) {
                    internationalCallback.b();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(b);
        intentFilter.addAction(c);
        instance.registerReceiver(r1, intentFilter);
        Intent intent = new Intent(context, SelectServerActivity.class);
        intent.putExtra(SelectServerActivity.DISPLAY_MODE, i);
        intent.putExtra(SelectServerActivity.FROM_WHERE, str);
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        context.startActivity(intent);
    }

    public static void a(Context context, ServerBean serverBean, InternationalCallback internationalCallback) {
        if (serverBean != null) {
            final MijiaWrapper mijiaWrapper = new MijiaWrapper(internationalCallback);
            final LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context);
            AnonymousClass2 r1 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    instance.unregisterReceiver(this);
                    InternationalCallback internationalCallback = (InternationalCallback) mijiaWrapper.f18690a;
                    mijiaWrapper.f18690a = null;
                    int intExtra = intent.getIntExtra("param_key", 0);
                    if (intExtra == 1) {
                        if (internationalCallback != null) {
                            internationalCallback.a();
                        }
                    } else if (intExtra == 2 && internationalCallback != null) {
                        internationalCallback.b();
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter(b);
            intentFilter.addAction(c);
            instance.registerReceiver(r1, intentFilter);
            Intent intent = new Intent(context, ServerLocationIncompatibleActivity.class);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            intent.putExtra("server", serverBean);
            context.startActivity(intent);
        } else if (internationalCallback != null) {
            internationalCallback.b();
        }
    }

    public static boolean a() {
        return b(g, false);
    }

    public static void a(boolean z) {
        a(g, z);
    }
}
