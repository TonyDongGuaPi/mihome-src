package com.mijia.model.alarm;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.Utils.FileUtil;
import com.mijia.debug.SDKLog;
import com.mijia.player.FileNamer;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import java.io.File;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class FDSFileManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8035a = "FDSFileManager";
    private CameraDevice b;
    /* access modifiers changed from: private */
    public boolean c;
    private OnDownloadListener d;
    /* access modifiers changed from: private */
    public ArrayList<DownloadItem> e = new ArrayList<>();
    private DownloadItem f;
    private ArrayList<DownloadItem> g = new ArrayList<>();
    private Handler h = new Handler(Looper.getMainLooper());

    public interface OnDownloadListener {
        void onDownloadFailed(DownloadItem downloadItem, String str, int i, String str2);

        void onDownloadSuccess(DownloadItem downloadItem, String str);

        void onDownloading(DownloadItem downloadItem, String str, int i);
    }

    public static class DownloadItem {

        /* renamed from: a  reason: collision with root package name */
        public AlarmItem f8040a;
        public int b;
        public int c;
        public int d;
        public int e = 1;

        public DownloadItem(AlarmItem alarmItem) {
            this.f8040a = alarmItem;
        }
    }

    public FDSFileManager(CameraDevice cameraDevice) {
        this.b = cameraDevice;
    }

    public boolean a() {
        return this.c;
    }

    public boolean a(AlarmItem alarmItem) {
        if (this.c) {
            for (int i = 0; i < this.e.size(); i++) {
                if (this.e.get(i).f8040a.equals(alarmItem)) {
                    return true;
                }
            }
        }
        return false;
    }

    public DownloadItem b(AlarmItem alarmItem) {
        if (this.c) {
            if (this.f.f8040a.equals(alarmItem)) {
                return this.f;
            }
            for (int i = 0; i < this.e.size(); i++) {
                if (this.e.get(i).f8040a.equals(alarmItem)) {
                    return this.e.get(i);
                }
            }
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            if (this.g.get(i2).f8040a.equals(alarmItem)) {
                return this.e.get(i2);
            }
        }
        return null;
    }

    public void a(OnDownloadListener onDownloadListener) {
        if (onDownloadListener == null) {
            this.d = null;
        } else {
            this.d = onDownloadListener;
        }
    }

    public void b(OnDownloadListener onDownloadListener) {
        if (this.d != null && this.d.equals(onDownloadListener)) {
            this.d = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void c(com.mijia.model.alarm.AlarmItem r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 == 0) goto L_0x001e
            java.lang.String r0 = r3.f     // Catch:{ all -> 0x001b }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x000c
            goto L_0x001e
        L_0x000c:
            java.util.ArrayList<com.mijia.model.alarm.FDSFileManager$DownloadItem> r0 = r2.e     // Catch:{ all -> 0x001b }
            com.mijia.model.alarm.FDSFileManager$DownloadItem r1 = new com.mijia.model.alarm.FDSFileManager$DownloadItem     // Catch:{ all -> 0x001b }
            r1.<init>(r3)     // Catch:{ all -> 0x001b }
            r0.add(r1)     // Catch:{ all -> 0x001b }
            r2.b()     // Catch:{ all -> 0x001b }
            monitor-exit(r2)
            return
        L_0x001b:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        L_0x001e:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mijia.model.alarm.FDSFileManager.c(com.mijia.model.alarm.AlarmItem):void");
    }

    private synchronized void b() {
        if (this.e.size() != 0) {
            this.h.post(new Runnable() {
                public void run() {
                    if (FDSFileManager.this.e.size() != 0 && !FDSFileManager.this.c) {
                        FDSFileManager.this.a((DownloadItem) FDSFileManager.this.e.remove(0));
                    }
                }
            });
        }
    }

    private String d(AlarmItem alarmItem) {
        String c2 = FileUtil.c(this.b.getDid());
        if (TextUtils.isEmpty(c2)) {
            return c2;
        }
        File file = new File(c2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return c2 + File.separator + FileNamer.a().a(alarmItem.b, true) + ".mp4";
    }

    private synchronized void a(DownloadItem downloadItem, String str) {
        this.f = downloadItem;
        this.g.remove(downloadItem);
        this.c = true;
        downloadItem.e = 2;
        if (this.d != null) {
            this.d.onDownloading(downloadItem, str, 0);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void a(DownloadItem downloadItem, String str, int i, String str2) {
        this.c = false;
        this.f = null;
        downloadItem.e = 3;
        this.g.add(downloadItem);
        if (this.d != null) {
            this.d.onDownloadFailed(downloadItem, str, i, str2);
        }
        SDKLog.e("alarm", "down fds fail " + i);
        b();
    }

    private synchronized void b(DownloadItem downloadItem, String str) {
        this.c = false;
        this.f = null;
        downloadItem.e = 4;
        if (this.d != null) {
            this.d.onDownloadSuccess(downloadItem, str);
        }
        b();
    }

    /* access modifiers changed from: private */
    public void a(final DownloadItem downloadItem) {
        final String d2 = d(downloadItem.f8040a);
        if (TextUtils.isEmpty(d2)) {
            a(downloadItem, "", -2, "");
        } else if (new File(d2).exists()) {
            b(downloadItem, d2);
        } else {
            a(downloadItem, d2);
            a(downloadItem.f8040a.f, (Callback<String>) new Callback<String>() {
                /* renamed from: a */
                public void onSuccess(final String str) {
                    new Thread() {
                        public void run() {
                            FDSFileManager.this.a(downloadItem, str, d2);
                        }
                    }.start();
                }

                public void onFailure(int i, String str) {
                    FDSFileManager.this.a(downloadItem, d2, i, str);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00c5, code lost:
        if (r9.b <= 0) goto L_0x00cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c7, code lost:
        r4 = (r9.c * 100) / r9.b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00cf, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x017e A[SYNTHETIC, Splitter:B:50:0x017e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.mijia.model.alarm.FDSFileManager.DownloadItem r9, java.lang.String r10, java.lang.String r11) {
        /*
            r8 = this;
            r0 = -1
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x01a5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x01a5 }
            r2.<init>()     // Catch:{ FileNotFoundException -> 0x01a5 }
            r2.append(r11)     // Catch:{ FileNotFoundException -> 0x01a5 }
            java.lang.String r3 = ".tmp"
            r2.append(r3)     // Catch:{ FileNotFoundException -> 0x01a5 }
            java.lang.String r2 = r2.toString()     // Catch:{ FileNotFoundException -> 0x01a5 }
            r3 = 0
            r1.<init>(r2, r3)     // Catch:{ FileNotFoundException -> 0x01a5 }
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x0178 }
            r2.<init>(r10)     // Catch:{ IOException -> 0x0178 }
            com.xiaomi.smarthome.device.api.XmPluginHostApi r10 = com.xiaomi.smarthome.device.api.XmPluginHostApi.instance()     // Catch:{ IOException -> 0x0178 }
            android.content.Context r10 = r10.context()     // Catch:{ IOException -> 0x0178 }
            boolean r10 = com.Utils.Network.c(r10)     // Catch:{ IOException -> 0x0178 }
            if (r10 == 0) goto L_0x0084
            java.net.HttpURLConnection.setFollowRedirects(r3)     // Catch:{ IOException -> 0x0178 }
            java.lang.String r10 = com.Utils.Network.a((java.net.URL) r2)     // Catch:{ IOException -> 0x0178 }
            java.lang.String r2 = r2.getHost()     // Catch:{ IOException -> 0x0178 }
            java.net.URL r4 = new java.net.URL     // Catch:{ IOException -> 0x0178 }
            r4.<init>(r10)     // Catch:{ IOException -> 0x0178 }
            java.net.URLConnection r10 = r4.openConnection()     // Catch:{ IOException -> 0x0178 }
            java.net.HttpURLConnection r10 = (java.net.HttpURLConnection) r10     // Catch:{ IOException -> 0x0178 }
            java.lang.String r4 = "X-Online-Host"
            r10.setRequestProperty(r4, r2)     // Catch:{ IOException -> 0x0178 }
            int r2 = r10.getResponseCode()     // Catch:{ IOException -> 0x0178 }
        L_0x004a:
            r4 = 300(0x12c, float:4.2E-43)
            if (r2 < r4) goto L_0x008e
            r4 = 400(0x190, float:5.6E-43)
            if (r2 >= r4) goto L_0x008e
            java.lang.String r2 = "location"
            java.lang.String r2 = r10.getHeaderField(r2)     // Catch:{ IOException -> 0x0178 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ IOException -> 0x0178 }
            if (r4 == 0) goto L_0x005f
            goto L_0x008e
        L_0x005f:
            java.net.URL r10 = new java.net.URL     // Catch:{ IOException -> 0x0178 }
            r10.<init>(r2)     // Catch:{ IOException -> 0x0178 }
            java.lang.String r2 = com.Utils.Network.a((java.net.URL) r10)     // Catch:{ IOException -> 0x0178 }
            java.lang.String r10 = r10.getHost()     // Catch:{ IOException -> 0x0178 }
            java.net.URL r4 = new java.net.URL     // Catch:{ IOException -> 0x0178 }
            r4.<init>(r2)     // Catch:{ IOException -> 0x0178 }
            java.net.URLConnection r2 = r4.openConnection()     // Catch:{ IOException -> 0x0178 }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ IOException -> 0x0178 }
            java.lang.String r4 = "X-Online-Host"
            r2.setRequestProperty(r4, r10)     // Catch:{ IOException -> 0x0178 }
            int r10 = r2.getResponseCode()     // Catch:{ IOException -> 0x0178 }
            r7 = r2
            r2 = r10
            r10 = r7
            goto L_0x004a
        L_0x0084:
            java.net.URLConnection r10 = r2.openConnection()     // Catch:{ IOException -> 0x0178 }
            java.net.HttpURLConnection r10 = (java.net.HttpURLConnection) r10     // Catch:{ IOException -> 0x0178 }
            r2 = 1
            java.net.HttpURLConnection.setFollowRedirects(r2)     // Catch:{ IOException -> 0x0178 }
        L_0x008e:
            r2 = 10000(0x2710, float:1.4013E-41)
            r10.setConnectTimeout(r2)     // Catch:{ IOException -> 0x0178 }
            r2 = 15000(0x3a98, float:2.102E-41)
            r10.setReadTimeout(r2)     // Catch:{ IOException -> 0x0178 }
            r10.connect()     // Catch:{ IOException -> 0x0178 }
            java.io.InputStream r2 = r10.getInputStream()     // Catch:{ IOException -> 0x0178 }
            int r10 = r10.getContentLength()     // Catch:{ IOException -> 0x0178 }
            r9.b = r10     // Catch:{ IOException -> 0x0178 }
            r9.c = r3     // Catch:{ IOException -> 0x0178 }
            r10 = 1024(0x400, float:1.435E-42)
            byte[] r10 = new byte[r10]     // Catch:{ IOException -> 0x0178 }
        L_0x00ab:
            r4 = 0
        L_0x00ac:
            int r5 = r2.read(r10)     // Catch:{ IOException -> 0x0178 }
            if (r5 <= 0) goto L_0x00db
            r1.write(r10, r3, r5)     // Catch:{ IOException -> 0x0178 }
            int r6 = r9.c     // Catch:{ IOException -> 0x0178 }
            int r6 = r6 + r5
            r9.c = r6     // Catch:{ IOException -> 0x0178 }
            int r4 = r4 + r5
            com.mijia.model.alarm.FDSFileManager$OnDownloadListener r5 = r8.d     // Catch:{ IOException -> 0x0178 }
            if (r5 == 0) goto L_0x00ac
            r5 = 10240(0x2800, float:1.4349E-41)
            if (r4 <= r5) goto L_0x00ac
            int r4 = r9.b     // Catch:{ IOException -> 0x0178 }
            if (r4 <= 0) goto L_0x00cf
            int r4 = r9.c     // Catch:{ IOException -> 0x0178 }
            int r4 = r4 * 100
            int r5 = r9.b     // Catch:{ IOException -> 0x0178 }
            int r4 = r4 / r5
            goto L_0x00d0
        L_0x00cf:
            r4 = 0
        L_0x00d0:
            r5 = 2
            r9.e = r5     // Catch:{ IOException -> 0x0178 }
            r9.d = r4     // Catch:{ IOException -> 0x0178 }
            com.mijia.model.alarm.FDSFileManager$OnDownloadListener r5 = r8.d     // Catch:{ IOException -> 0x0178 }
            r5.onDownloading(r9, r11, r4)     // Catch:{ IOException -> 0x0178 }
            goto L_0x00ab
        L_0x00db:
            r2.close()     // Catch:{ IOException -> 0x0178 }
            r1.close()     // Catch:{ IOException -> 0x0178 }
            r1 = 0
            java.io.File r10 = new java.io.File     // Catch:{ IOException -> 0x0178 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0178 }
            r2.<init>()     // Catch:{ IOException -> 0x0178 }
            r2.append(r11)     // Catch:{ IOException -> 0x0178 }
            java.lang.String r3 = ".tmp"
            r2.append(r3)     // Catch:{ IOException -> 0x0178 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0178 }
            r10.<init>(r2)     // Catch:{ IOException -> 0x0178 }
            java.io.File r2 = new java.io.File     // Catch:{ IOException -> 0x0178 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0178 }
            r3.<init>()     // Catch:{ IOException -> 0x0178 }
            r3.append(r11)     // Catch:{ IOException -> 0x0178 }
            java.lang.String r4 = ".g711"
            r3.append(r4)     // Catch:{ IOException -> 0x0178 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0178 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0178 }
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x0178 }
            r3.<init>(r11)     // Catch:{ IOException -> 0x0178 }
            com.mijia.model.alarm.AlarmItem r4 = r9.f8040a     // Catch:{ IOException -> 0x0178 }
            java.lang.String r4 = r4.g     // Catch:{ IOException -> 0x0178 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ IOException -> 0x0178 }
            if (r4 == 0) goto L_0x0134
            boolean r4 = com.Utils.Util.a(r11, r10, r2)     // Catch:{ IOException -> 0x0178 }
            if (r4 == 0) goto L_0x0130
            boolean r4 = r2.exists()     // Catch:{ IOException -> 0x0178 }
            if (r4 == 0) goto L_0x0130
            r10.delete()     // Catch:{ IOException -> 0x0178 }
            r2.renameTo(r3)     // Catch:{ IOException -> 0x0178 }
            goto L_0x016e
        L_0x0130:
            r10.renameTo(r3)     // Catch:{ IOException -> 0x0178 }
            goto L_0x016e
        L_0x0134:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0178 }
            r4.<init>()     // Catch:{ IOException -> 0x0178 }
            r4.append(r11)     // Catch:{ IOException -> 0x0178 }
            java.lang.String r5 = ".dec"
            r4.append(r5)     // Catch:{ IOException -> 0x0178 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x0178 }
            java.lang.String r5 = r10.getAbsolutePath()     // Catch:{ IOException -> 0x0178 }
            com.mijia.model.alarm.AlarmItem r6 = r9.f8040a     // Catch:{ IOException -> 0x0178 }
            java.lang.String r6 = r6.g     // Catch:{ IOException -> 0x0178 }
            com.Utils.VideoDecryption.a(r5, r6, r4)     // Catch:{ IOException -> 0x0178 }
            r10.delete()     // Catch:{ IOException -> 0x0178 }
            java.io.File r10 = new java.io.File     // Catch:{ IOException -> 0x0178 }
            r10.<init>(r4)     // Catch:{ IOException -> 0x0178 }
            boolean r4 = com.Utils.Util.a(r11, r10, r2)     // Catch:{ IOException -> 0x0178 }
            if (r4 == 0) goto L_0x016b
            boolean r4 = r2.exists()     // Catch:{ IOException -> 0x0178 }
            if (r4 == 0) goto L_0x016b
            r10.delete()     // Catch:{ IOException -> 0x0178 }
            r2.renameTo(r3)     // Catch:{ IOException -> 0x0178 }
            goto L_0x016e
        L_0x016b:
            r10.renameTo(r3)     // Catch:{ IOException -> 0x0178 }
        L_0x016e:
            boolean r10 = r3.exists()     // Catch:{ IOException -> 0x0178 }
            if (r10 == 0) goto L_0x017c
            r8.b(r9, r11)     // Catch:{ IOException -> 0x0178 }
            return
        L_0x0178:
            r10 = move-exception
            r10.printStackTrace()
        L_0x017c:
            if (r1 == 0) goto L_0x0186
            r1.close()     // Catch:{ IOException -> 0x0182 }
            goto L_0x0186
        L_0x0182:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0186:
            java.io.File r10 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r11)
            java.lang.String r2 = ".tmp"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r10.<init>(r1)
            r10.delete()
            java.lang.String r10 = ""
            r8.a((com.mijia.model.alarm.FDSFileManager.DownloadItem) r9, (java.lang.String) r11, (int) r0, (java.lang.String) r10)
            return
        L_0x01a5:
            java.lang.String r10 = ""
            r8.a((com.mijia.model.alarm.FDSFileManager.DownloadItem) r9, (java.lang.String) r11, (int) r0, (java.lang.String) r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mijia.model.alarm.FDSFileManager.a(com.mijia.model.alarm.FDSFileManager$DownloadItem, java.lang.String, java.lang.String):void");
    }

    public void a(String str, Callback<String> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("obj_name", str);
        } catch (JSONException unused) {
            callback.onFailure(-10000, (String) null);
        }
        XmPluginHostApi.instance().callSmartHomeApi(this.b.getModel(), "/home/getfileurl", jSONObject, callback, new Parser<String>() {
            /* renamed from: a */
            public String parse(String str) throws JSONException {
                return new JSONObject(str).optString("url");
            }
        });
    }
}
