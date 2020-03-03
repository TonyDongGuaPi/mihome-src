package com.xiaomi.market.sdk;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

class DownloadInstallResultNotifier {

    /* renamed from: a  reason: collision with root package name */
    public static final int f11106a = -1;
    public static final int b = -2;

    DownloadInstallResultNotifier() {
    }

    public static void a(int i) {
        switch (i) {
            case -2:
                b(R.string.xiaomi_update_sdk_connect_download_manager_fail);
                return;
            case -1:
                b(R.string.xiaomi_update_sdk_external_storage_unavailable);
                return;
            default:
                return;
        }
    }

    private static void b(int i) {
        new Handler(Looper.getMainLooper()).post(new Runnable(i) {
            private final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                Toast.makeText(AppGlobal.a(), this.f$0, 0).show();
            }
        });
    }
}
