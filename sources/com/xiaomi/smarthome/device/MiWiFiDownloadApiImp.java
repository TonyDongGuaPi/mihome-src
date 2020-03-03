package com.xiaomi.smarthome.device;

import android.database.Cursor;
import android.net.Uri;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.download.DownloadManager;
import com.xiaomi.smarthome.download.MiWFDownloadManager;
import com.xiaomi.smarthome.upnp.MiWiFiDownloadApi;

public class MiWiFiDownloadApiImp extends MiWiFiDownloadApi {

    /* renamed from: a  reason: collision with root package name */
    private static final Byte[] f14896a = new Byte[0];
    private static MiWFDownloadManager b = null;

    public static void a() {
        if (_instance == null) {
            synchronized (f14896a) {
                if (_instance == null) {
                    _instance = new MiWiFiDownloadApiImp();
                }
            }
        }
    }

    public long startDownload(Uri uri, String str, String str2, String str3) {
        DownloadManager.Request request = new DownloadManager.Request(uri, str);
        request.a(SHApplication.getAppContext(), str2, str3);
        MiWFDownloadManager b2 = b();
        if (b2 != null) {
            return b2.a(request);
        }
        return 0;
    }

    private MiWFDownloadManager b() {
        if (b == null) {
            synchronized (f14896a) {
                if (b == null) {
                    b = new MiWFDownloadManager(SHApplication.getAppContext(), SHApplication.getAppContext().getContentResolver(), SHApplication.getAppContext().getPackageName());
                }
            }
        }
        return b;
    }

    public void pauseDownload(long... jArr) {
        MiWFDownloadManager b2 = b();
        if (b2 != null) {
            b2.c(jArr);
        }
    }

    public void resumeDownload(long... jArr) {
        MiWFDownloadManager b2 = b();
        if (b2 != null) {
            b2.d(jArr);
        }
    }

    public void restartDownload(long... jArr) {
        MiWFDownloadManager b2 = b();
        if (b2 != null) {
            b2.e(jArr);
        }
    }

    public void removeDownload(long... jArr) {
        MiWFDownloadManager b2 = b();
        if (b2 != null) {
            b2.b(jArr);
        }
    }

    public Cursor queryDownload(boolean z, long... jArr) {
        MiWFDownloadManager b2 = b();
        if (b2 == null) {
            return null;
        }
        DownloadManager.Query query = new DownloadManager.Query();
        query.a(jArr).a(z);
        return b2.a(query);
    }

    public void notifyLocalWifiConnect(boolean z) {
        MiWFDownloadManager b2 = b();
        if (b2 != null) {
            b2.b(z);
        }
    }
}
