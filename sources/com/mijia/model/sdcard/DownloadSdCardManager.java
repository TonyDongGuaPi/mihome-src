package com.mijia.model.sdcard;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.Utils.FileUtil;
import com.Utils.Util;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.mijia.app.AppConfig;
import com.tutk.IOTC.Packet;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.camera.P2pResponse;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DownloadSdCardManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8070a = "DownloadSdCardManager";
    /* access modifiers changed from: private */
    public CameraDevice b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public WeakReference<OnDownloadListener> d;
    /* access modifiers changed from: private */
    public ArrayList<DownloadItem> e = new ArrayList<>();
    /* access modifiers changed from: private */
    public DownloadItem f;
    /* access modifiers changed from: private */
    public ArrayList<DownloadItem> g = new ArrayList<>();
    private Handler h = new Handler(Looper.getMainLooper());

    public interface OnDownloadListener {
        void onDownloadFailed(DownloadItem downloadItem, String str, int i, String str2);

        void onDownloadSuccess(DownloadItem downloadItem, String str);

        void onDownloading(DownloadItem downloadItem, String str, int i);
    }

    public static class DownloadItem {

        /* renamed from: a  reason: collision with root package name */
        public TimeItem f8073a;
        public int b;
        public int c;
        public int d = 1;
        public boolean e = true;

        public DownloadItem(TimeItem timeItem, boolean z) {
            this.f8073a = timeItem;
            this.e = z;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof DownloadItem)) {
                return false;
            }
            DownloadItem downloadItem = (DownloadItem) obj;
            if (this.e != downloadItem.e || !this.f8073a.equals(downloadItem.f8073a)) {
                return false;
            }
            return true;
        }
    }

    public DownloadSdCardManager(CameraDevice cameraDevice) {
        this.b = cameraDevice;
    }

    public boolean a() {
        return this.c;
    }

    public DownloadItem a(TimeItem timeItem) {
        if (this.c && this.f.f8073a.equals(timeItem)) {
            return this.f;
        }
        try {
            if (!this.e.isEmpty()) {
                Iterator<DownloadItem> it = this.e.iterator();
                while (it.hasNext()) {
                    DownloadItem next = it.next();
                    if (next.f8073a.equals(timeItem)) {
                        return next;
                    }
                }
            }
            if (!this.g.isEmpty()) {
                Iterator<DownloadItem> it2 = this.g.iterator();
                while (it2.hasNext()) {
                    DownloadItem next2 = it2.next();
                    if (next2.f8073a.equals(timeItem)) {
                        return next2;
                    }
                }
            }
            return null;
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }

    public void a(List<TimeItem> list) {
        if (!this.e.isEmpty()) {
            try {
                for (TimeItem next : list) {
                    Iterator<DownloadItem> it = this.e.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DownloadItem next2 = it.next();
                        if (next2.f8073a.f8098a == next.f8098a) {
                            this.e.remove(next2);
                            break;
                        }
                    }
                }
            } catch (IndexOutOfBoundsException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(OnDownloadListener onDownloadListener) {
        if (onDownloadListener == null) {
            this.d = null;
        } else {
            this.d = new WeakReference<>(onDownloadListener);
        }
    }

    public synchronized void a(TimeItem timeItem, boolean z) {
        this.e.add(new DownloadItem(timeItem, z));
        b();
    }

    public synchronized void b(TimeItem timeItem) {
        a(timeItem, true);
    }

    public synchronized void b() {
        if (this.e.size() != 0) {
            this.h.post(new Runnable() {
                public void run() {
                    if (DownloadSdCardManager.this.e.size() != 0 && !DownloadSdCardManager.this.c) {
                        DownloadSdCardManager.this.b((DownloadItem) DownloadSdCardManager.this.e.remove(0));
                    }
                }
            });
        }
    }

    public String a(DownloadItem downloadItem) {
        return FileUtil.a(downloadItem.f8073a.f8098a, true, this.b.getDid());
    }

    public void b(final DownloadItem downloadItem) {
        final String a2 = a(downloadItem);
        if (new File(a2).exists()) {
            downloadItem.d = 4;
            if (!(this.d == null || this.d.get() == null)) {
                ((OnDownloadListener) this.d.get()).onDownloadSuccess(downloadItem, a2);
            }
            b();
            return;
        }
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(a2 + DefaultDiskStorage.FileType.TEMP, false);
            this.f = downloadItem;
            this.g.remove(downloadItem);
            this.c = true;
            downloadItem.d = 2;
            if (!(this.d == null || this.d.get() == null)) {
                ((OnDownloadListener) this.d.get()).onDownloading(downloadItem, a2, 0);
            }
            CameraDevice cameraDevice = this.b;
            this.b.u().sendMsg(1, 1, Packet.intToByteArray((int) (downloadItem.f8073a.f8098a / 1000), AppConfig.b()), new P2pResponse() {
                public void onResponse(int i, byte[] bArr) {
                    Log.d(DownloadSdCardManager.f8070a, "downloadFile:" + i);
                    if (downloadItem.b <= 0) {
                        downloadItem.b = i;
                    }
                    if (!(DownloadSdCardManager.this.d == null || DownloadSdCardManager.this.d.get() == null)) {
                        int i2 = downloadItem.b > 0 ? ((downloadItem.b - i) * 100) / downloadItem.b : 0;
                        downloadItem.d = 2;
                        downloadItem.c = i2;
                        ((OnDownloadListener) DownloadSdCardManager.this.d.get()).onDownloading(downloadItem, a2, i2);
                    }
                    try {
                        fileOutputStream.write(bArr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (i <= 0) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        File file = new File(a2 + DefaultDiskStorage.FileType.TEMP);
                        File file2 = new File(a2 + ".g711");
                        if (!Util.a(a2, file, file2) || !file2.exists()) {
                            file.renameTo(new File(a2));
                        } else {
                            file.delete();
                            file2.renameTo(new File(a2));
                        }
                        boolean unused = DownloadSdCardManager.this.c = false;
                        DownloadItem unused2 = DownloadSdCardManager.this.f = null;
                        downloadItem.d = 4;
                        DownloadSdCardManager.this.b.b().a(a2);
                        XmPluginHostApi.instance().context().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(a2))));
                        if (!(DownloadSdCardManager.this.d == null || DownloadSdCardManager.this.d.get() == null)) {
                            ((OnDownloadListener) DownloadSdCardManager.this.d.get()).onDownloadSuccess(downloadItem, a2);
                        }
                        DownloadSdCardManager.this.b();
                    }
                }

                public void onError(int i) {
                    Log.d(DownloadSdCardManager.f8070a, "downloadFile failled:" + i);
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    new File(a2 + DefaultDiskStorage.FileType.TEMP).delete();
                    boolean unused = DownloadSdCardManager.this.c = false;
                    DownloadItem unused2 = DownloadSdCardManager.this.f = null;
                    downloadItem.d = 3;
                    DownloadSdCardManager.this.g.add(downloadItem);
                    if (!(DownloadSdCardManager.this.d == null || DownloadSdCardManager.this.d.get() == null)) {
                        ((OnDownloadListener) DownloadSdCardManager.this.d.get()).onDownloadFailed(downloadItem, a2, i, "");
                    }
                    DownloadSdCardManager.this.b();
                }
            });
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            this.c = false;
            this.f = null;
            downloadItem.d = 3;
            this.g.add(downloadItem);
            if (!(this.d == null || this.d.get() == null)) {
                ((OnDownloadListener) this.d.get()).onDownloadFailed(downloadItem, a2, -1, "");
            }
            b();
        }
    }
}
