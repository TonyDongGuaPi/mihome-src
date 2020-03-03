package com.xiaomi.smarthome.download;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.lang.ref.WeakReference;

public class MiWFDownloadManager extends DownloadManager {
    public static final String H = "local_wifi_connected";
    public static final String I = "local_wifi_disconnected";
    private WeakReference<Context> J = null;
    private DownloadReceiver K = new DownloadReceiver();

    public MiWFDownloadManager(Context context, ContentResolver contentResolver, String str) {
        super(contentResolver, str);
        this.J = new WeakReference<>(context);
    }

    public void b(boolean z) {
        Intent intent = new Intent();
        if (z) {
            intent.setAction(H);
        } else {
            intent.setAction(I);
        }
        if (this.J != null && this.J.get() != null) {
            ((Context) this.J.get()).sendBroadcast(intent);
        }
    }

    public void c() {
        if (this.J != null && this.J.get() != null) {
            ((Context) this.J.get()).registerReceiver(this.K, new IntentFilter(H));
        }
    }

    public void d() {
        if (this.J != null && this.J.get() != null) {
            ((Context) this.J.get()).unregisterReceiver(this.K);
        }
    }
}
