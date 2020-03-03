package com.xiaomi.smarthome.frame.login.util;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import com.mi.global.bbs.utils.Constants;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileLoadManager {

    /* renamed from: a  reason: collision with root package name */
    public static volatile FileLoadManager f16343a;
    /* access modifiers changed from: private */
    public Context b;
    private DownloadManager c;
    /* access modifiers changed from: private */
    public CompleteReceiver d;
    /* access modifiers changed from: private */
    public long e = -1;
    /* access modifiers changed from: private */
    public AtomicBoolean f = new AtomicBoolean(false);

    private FileLoadManager(Context context) {
        this.b = context.getApplicationContext();
        this.c = (DownloadManager) this.b.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD);
    }

    public static FileLoadManager a(Context context) {
        if (f16343a == null) {
            synchronized (FileLoadManager.class) {
                if (f16343a == null) {
                    f16343a = new FileLoadManager(context);
                }
            }
        }
        return f16343a;
    }

    /* access modifiers changed from: private */
    public long a(Uri uri, String str, String str2) {
        if (uri == null || uri.equals(Uri.EMPTY) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || !a()) {
            return -1;
        }
        try {
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setDestinationInExternalFilesDir(this.b, str, str2);
            request.setNotificationVisibility(2);
            request.setAllowedNetworkTypes(2);
            return this.c.enqueue(request);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    public void a(final String str, final String str2, final String str3) {
        if (!this.f.getAndSet(true)) {
            AsyncTaskUtils.a(new AsyncTask<Object, Object, Object>() {
                /* access modifiers changed from: protected */
                public Object doInBackground(Object... objArr) {
                    CompleteReceiver unused = FileLoadManager.this.d = new CompleteReceiver(str, str2);
                    FileLoadManager.this.b.registerReceiver(FileLoadManager.this.d, new IntentFilter(com.xiaomi.smarthome.download.DownloadManager.D));
                    long unused2 = FileLoadManager.this.e = FileLoadManager.this.a(Uri.parse(str3), Environment.DIRECTORY_MOVIES, str);
                    return null;
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(Object obj) {
                    super.onPostExecute(obj);
                }
            }, new Object[0]);
        }
    }

    private boolean a() {
        try {
            int applicationEnabledSetting = this.b.getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");
            return (applicationEnabledSetting == 2 || applicationEnabledSetting == 3 || applicationEnabledSetting == 4) ? false : true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    class CompleteReceiver extends BroadcastReceiver {
        private String b;
        private String c;

        public CompleteReceiver(String str, String str2) {
            this.b = str;
            this.c = str2;
        }

        public void onReceive(Context context, Intent intent) {
            FileLoadManager.this.f.set(false);
            if (FileLoadManager.this.e == intent.getLongExtra(com.xiaomi.smarthome.download.DownloadManager.G, -1) && FileLoadManager.this.e != -1) {
                File b2 = FileUtils.b(FileLoadManager.this.b, this.b);
                File b3 = FileUtils.b(FileLoadManager.this.b, this.c);
                if (b2.exists() && !b3.exists() && b2.renameTo(b3)) {
                    FileLoadManager.this.b.unregisterReceiver(FileLoadManager.this.d);
                }
            }
        }
    }
}
