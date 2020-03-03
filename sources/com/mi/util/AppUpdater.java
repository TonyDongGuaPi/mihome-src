package com.mi.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coloros.mcssdk.PushManager;
import com.google.android.exoplayer2.C;
import com.mi.global.bbs.utils.Constants;
import com.mi.log.LogUtil;
import com.mi.micomponents.R;
import com.mi.model.UpdaterInfo;
import com.mi.util.Constants;
import com.mi.util.Utils;
import com.mi.widget.BaseAlertDialog;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AppUpdater {
    public static final String TAG = "AppUpdater";
    private static final int WHAT_FAIL_GEN_MD5 = -1;
    private static final int WHAT_FAIL_GET_SOURCE = -3;
    private static final int WHAT_FAIL_PATCH = -2;
    private static final int WHAT_SUCCESS = 1;
    /* access modifiers changed from: private */
    public Context mContext;
    private Handler mHandler = new Handler();
    private String mMd5;
    private String mPatchUrl;
    /* access modifiers changed from: private */
    public String mURL;
    /* access modifiers changed from: private */
    public Timer mUpdateTimer;
    private int mVersionCode;

    public interface DownLoadProgressCallback {
        void a();

        void a(int i);
    }

    public AppUpdater(Context context) {
        this.mContext = context;
    }

    public AppUpdater(Context context, UpdaterInfo updaterInfo) {
        this.mContext = context;
        this.mURL = updaterInfo.b;
        this.mMd5 = updaterInfo.c;
        this.mVersionCode = updaterInfo.f;
        this.mPatchUrl = updaterInfo.d;
    }

    public boolean needCheck() {
        long currentTimeMillis = System.currentTimeMillis();
        long longPref = Utils.Preference.getLongPref(this.mContext, "pref_last_check_update", 0);
        LogUtil.b(TAG, "lastCheck:" + longPref + ", now:" + currentTimeMillis);
        if (currentTimeMillis - longPref < 30000) {
            return false;
        }
        Utils.Preference.setLongPref(this.mContext, "pref_last_check_update", Long.valueOf(currentTimeMillis));
        long longPref2 = Utils.Preference.getLongPref(this.mContext, "pref_last_update_is_ok", 0);
        LogUtil.b(TAG, "lastUpdate:" + longPref2);
        if (currentTimeMillis - longPref2 < 3600000) {
            return false;
        }
        return true;
    }

    public void sendCheckApkUpdateService(boolean z) {
        if (z) {
            sendCheckApkUpdateService();
        } else if (needCheck()) {
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    AppUpdater.this.sendCheckApkUpdateService();
                }
            }, 10000);
        }
    }

    public void sendCheckApkUpdateService() {
        if (this.mContext != null) {
        }
    }

    public void download(String str) {
        download(str, true);
    }

    public void download(String str, boolean z) {
        LogUtil.b(TAG, "download url:" + str);
        if (!TextUtils.isEmpty(str) && str.startsWith("https://play.google.com/")) {
            this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } else if (!Environment.getExternalStorageState().equals("mounted")) {
            MiToast.a(this.mContext, R.string.update_no_sd, 0);
        } else {
            long longPref = Utils.Preference.getLongPref(this.mContext, "pref_download_id", 0);
            int downloadStatusById = getDownloadStatusById(this.mContext, longPref);
            if (downloadStatusById != 2 && downloadStatusById != 1) {
                if (downloadStatusById == 4) {
                    ((DownloadManager) this.mContext.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD)).remove(new long[]{longPref});
                }
                Utils.Preference.setStringPref(this.mContext, Constants.AppUpdate.k, this.mMd5);
                Utils.Preference.setStringPref(this.mContext, Constants.AppUpdate.l, this.mURL);
                Utils.Preference.setIntPref(this.mContext, Constants.AppUpdate.m, this.mVersionCode);
                if (TextUtils.isEmpty(this.mPatchUrl)) {
                    Utils.Preference.setBooleanPref(this.mContext, Constants.AppUpdate.j, false);
                } else {
                    Utils.Preference.setBooleanPref(this.mContext, Constants.AppUpdate.j, true);
                    str = this.mPatchUrl;
                }
                new CheckApkTask(this.mContext, this.mVersionCode, this.mMd5, str, false, z).execute(new String[0]);
            }
        }
    }

    public int getDownloadStatusById(Context context, long j) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(new long[]{j});
        Cursor query2 = ((DownloadManager) context.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD)).query(query);
        int i = -1;
        if (query2 == null) {
            return -1;
        }
        try {
            int columnIndex = query2.getColumnIndex("status");
            if (query2.moveToFirst()) {
                i = query2.getInt(columnIndex);
            }
            return i;
        } finally {
            query2.close();
        }
    }

    public static boolean canDownloadState(Context context) {
        try {
            int applicationEnabledSetting = context.getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");
            return (applicationEnabledSetting == 2 || applicationEnabledSetting == 3 || applicationEnabledSetting == 4) ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void requestDownload(Context context, String str, boolean z, boolean z2) {
        String str2;
        LogUtil.b(TAG, "requestDownload url:" + str);
        if (!z) {
            MiToast.a(context, R.string.start_download, 1);
        }
        try {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
            request.setMimeType("application/vnd.android.package-archive");
            request.allowScanningByMediaScanner();
            request.setVisibleInDownloadsUi(true);
            request.setNotificationVisibility(z2 ? 0 : 2);
            int intPref = Utils.Preference.getIntPref(context, Constants.AppUpdate.m, 0);
            if (Utils.Preference.getBooleanPref(context, Constants.AppUpdate.j, false)) {
                str2 = String.format("%s_%s_patch.apk", new Object[]{Device.q, Integer.valueOf(intPref)});
            } else {
                str2 = String.format("%s_%s.apk", new Object[]{Device.q, Integer.valueOf(intPref)});
            }
            request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, str2);
            request.setTitle(context.getResources().getString(R.string.app_name));
            request.setDescription(context.getResources().getString(R.string.self_confirm_dowloading));
            Utils.Preference.setLongPref(context, "pref_download_id", Long.valueOf(downloadManager.enqueue(request)));
        } catch (Exception unused) {
        }
    }

    public void loadVersionLogAndPopDialog(UpdaterInfo updaterInfo) {
        this.mURL = updaterInfo.b;
        this.mMd5 = updaterInfo.c;
        this.mVersionCode = updaterInfo.f;
        this.mPatchUrl = updaterInfo.d;
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setPadding(0, 0, 0, Coder.a(13.0f));
        linearLayout.setOrientation(1);
        linearLayout.removeAllViews();
        ArrayList<UpdaterInfo.DescType> arrayList = updaterInfo.f7375a;
        for (int i = 0; i < arrayList.size(); i++) {
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.update_list_item, (ViewGroup) null, false);
            TextView textView = (TextView) inflate.findViewById(R.id.update_log_type);
            TextView textView2 = (TextView) inflate.findViewById(R.id.update_log_desc);
            textView.setText(arrayList.get(i).mDescType);
            if (TextUtils.isEmpty(arrayList.get(i).mDescType)) {
                textView.setVisibility(8);
            }
            textView2.setText(arrayList.get(i).mDesc);
            linearLayout.addView(inflate);
        }
        if (updaterInfo.g) {
            LogUtil.b(TAG, "forceUpdate");
            popForceUpdateDialog(linearLayout);
            return;
        }
        popUpdateDialog(linearLayout);
    }

    @TargetApi(16)
    private void popForceUpdateDialog(View view) {
        final BaseAlertDialog baseAlertDialog = new BaseAlertDialog(this.mContext);
        baseAlertDialog.setTitle(R.string.update_log_title);
        baseAlertDialog.a(view);
        baseAlertDialog.setCanceledOnTouchOutside(false);
        baseAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                LogUtil.b(AppUpdater.TAG, "CANCEL DIALOG");
                dialogInterface.cancel();
                dialogInterface.dismiss();
                Utils.Preference.setLongPref(AppUpdater.this.mContext, "pref_last_check_update", Long.valueOf(System.currentTimeMillis() - 30000));
                if (Build.VERSION.SDK_INT >= 16) {
                    ((Activity) AppUpdater.this.mContext).finishAffinity();
                } else {
                    System.exit(0);
                }
            }
        });
        baseAlertDialog.c(R.string.immediately_update, new View.OnClickListener() {
            public void onClick(View view) {
                Utils.Preference.setLongPref(AppUpdater.this.mContext, "pref_last_check_update", Long.valueOf(System.currentTimeMillis() - 30000));
                AppUpdater.this.download(AppUpdater.this.mURL);
            }
        });
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (AppUpdater.this.mContext != null && !((Activity) AppUpdater.this.mContext).isFinishing()) {
                    baseAlertDialog.show();
                }
            }
        }, 1000);
    }

    private void popUpdateDialog(View view) {
        final BaseAlertDialog baseAlertDialog = new BaseAlertDialog(this.mContext);
        baseAlertDialog.setTitle(R.string.update_log_title);
        baseAlertDialog.a(view);
        baseAlertDialog.setCanceledOnTouchOutside(true);
        baseAlertDialog.a(R.string.immediately_update, new View.OnClickListener() {
            public void onClick(View view) {
                AppUpdater.this.download(AppUpdater.this.mURL);
            }
        });
        baseAlertDialog.b(R.string.cancel_update, new View.OnClickListener() {
            public void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis() + 3600000;
                Utils.Preference.setLongPref(AppUpdater.this.mContext, "pref_last_check_update", Long.valueOf(currentTimeMillis));
                LogUtil.b(AppUpdater.TAG, "cancel update, set last_check_update_time::" + currentTimeMillis);
            }
        });
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (AppUpdater.this.mContext != null && !((Activity) AppUpdater.this.mContext).isFinishing()) {
                    baseAlertDialog.show();
                }
            }
        }, 1000);
    }

    protected static void installApk(Context context, Uri uri) {
        Intent intent = new Intent();
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(1);
        }
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static class DownloadCompletedReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.b(AppUpdater.TAG, "receive download broadcast");
            if (com.xiaomi.smarthome.download.DownloadManager.D.equals(action)) {
                long longPref = Utils.Preference.getLongPref(context, "pref_download_id", 0);
                long longExtra = intent.getLongExtra(com.xiaomi.smarthome.download.DownloadManager.G, 0);
                if (longExtra == longPref) {
                    a(context, longExtra);
                }
            }
        }

        private void a(Context context, long j) {
            Uri uri;
            String b = b(context, j);
            if (TextUtils.isEmpty(b)) {
                return;
            }
            if (Utils.Preference.getBooleanPref(context, Constants.AppUpdate.j, false)) {
                new PatchApkTask(context, b, j).execute(new String[0]);
                return;
            }
            if (Build.VERSION.SDK_INT >= 24) {
                try {
                    uri = FileProvider.getUriForFile(context, ResourceUtil.a("file_provider_authorities"), new File(new URI(b)));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    MiToast.a(context, (CharSequence) e.getMessage(), 0);
                    return;
                }
            } else {
                uri = Uri.parse(b);
            }
            AppUpdater.installApk(context, uri);
        }

        private String b(Context context, long j) {
            DownloadManager.Query query = new DownloadManager.Query();
            boolean z = false;
            query.setFilterById(new long[]{j});
            Cursor query2 = ((DownloadManager) context.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD)).query(query);
            String str = null;
            if (query2 == null) {
                return null;
            }
            try {
                int columnIndex = query2.getColumnIndex("status");
                int columnIndex2 = query2.getColumnIndex("local_uri");
                if (query2.moveToFirst()) {
                    while (true) {
                        if (8 != query2.getInt(columnIndex)) {
                            if (16 != query2.getInt(columnIndex)) {
                                if (!query2.moveToNext()) {
                                    break;
                                }
                            } else {
                                z = true;
                                break;
                            }
                        } else {
                            str = query2.getString(columnIndex2);
                            break;
                        }
                    }
                    if (z) {
                        a(context);
                    }
                }
                return str;
            } finally {
                query2.close();
            }
        }

        private void a(Context context) {
            Notification notification;
            Notification.Builder builder;
            String string = context.getString(R.string.download_failed_title);
            String string2 = context.getString(R.string.download_failed_tips);
            Intent intent = new Intent();
            intent.setAction(com.xiaomi.smarthome.download.DownloadManager.F);
            PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 134217728);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
            if (Build.VERSION.SDK_INT >= 16) {
                if (Build.VERSION.SDK_INT >= 26) {
                    builder = new Notification.Builder(context, "message");
                } else {
                    builder = new Notification.Builder(context);
                }
                notification = builder.setContentTitle(string).setContentText(string2).setSmallIcon(R.drawable.app_icon).setAutoCancel(true).setDefaults(1).setContentIntent(activity).build();
            } else {
                notification = new NotificationCompat.Builder(context, "message").setContentTitle(string).setContentText(string2).setSmallIcon(R.drawable.app_icon).setAutoCancel(true).setDefaults(1).setContentIntent(activity).build();
            }
            notificationManager.notify(R.string.download_failed_id, notification);
        }
    }

    private static class PatchApkTask extends AsyncTask<String, Void, Integer> {

        /* renamed from: a  reason: collision with root package name */
        private Context f7408a;
        private String b;
        private long c;
        private int d;
        private String e;

        public PatchApkTask(Context context, String str, long j) {
            this.f7408a = context;
            this.b = str;
            this.c = j;
            this.e = Utils.Preference.getStringPref(context, Constants.AppUpdate.k, (String) null);
            this.d = Utils.Preference.getIntPref(context, Constants.AppUpdate.m, 0);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x006d  */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x00a5  */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Integer doInBackground(java.lang.String... r9) {
            /*
                r8 = this;
                android.content.Context r9 = r8.f7408a
                android.content.Context r0 = r8.f7408a
                java.lang.String r0 = r0.getPackageName()
                java.lang.String r9 = com.mi.util.ApkUtils.c(r9, r0)
                boolean r0 = android.text.TextUtils.isEmpty(r9)
                if (r0 != 0) goto L_0x00ab
                android.content.Context r0 = r8.f7408a
                java.lang.String r1 = android.os.Environment.DIRECTORY_DOWNLOADS
                java.io.File r0 = r0.getExternalFilesDir(r1)
                java.lang.String r1 = "%s/%s_%s.apk"
                r2 = 3
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                r2[r3] = r0
                java.lang.String r0 = com.mi.util.Device.q
                r4 = 1
                r2[r4] = r0
                r0 = 2
                int r5 = r8.d
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r2[r0] = r5
                java.lang.String r0 = java.lang.String.format(r1, r2)
                r1 = 0
                java.io.File r2 = new java.io.File     // Catch:{ URISyntaxException -> 0x0043 }
                java.net.URI r5 = new java.net.URI     // Catch:{ URISyntaxException -> 0x0043 }
                java.lang.String r6 = r8.b     // Catch:{ URISyntaxException -> 0x0043 }
                r5.<init>(r6)     // Catch:{ URISyntaxException -> 0x0043 }
                r2.<init>(r5)     // Catch:{ URISyntaxException -> 0x0043 }
                r1 = r2
                goto L_0x0047
            L_0x0043:
                r2 = move-exception
                r2.printStackTrace()
            L_0x0047:
                r2 = -1
                if (r1 == 0) goto L_0x0057
                java.lang.String r1 = r1.getAbsolutePath()     // Catch:{ UnsatisfiedLinkError -> 0x0053 }
                int r9 = com.cundong.utils.PatchUtils.patch(r9, r0, r1)     // Catch:{ UnsatisfiedLinkError -> 0x0053 }
                goto L_0x0058
            L_0x0053:
                r9 = move-exception
                r9.printStackTrace()
            L_0x0057:
                r9 = -1
            L_0x0058:
                android.content.Context r1 = r8.f7408a
                java.lang.String r5 = "download"
                java.lang.Object r1 = r1.getSystemService(r5)
                android.app.DownloadManager r1 = (android.app.DownloadManager) r1
                long[] r5 = new long[r4]
                long r6 = r8.c
                r5[r3] = r6
                r1.remove(r5)
                if (r9 != 0) goto L_0x00a5
                java.lang.String r9 = r8.e
                boolean r9 = com.mi.util.SignUtils.a((java.lang.String) r0, (java.lang.String) r9)
                if (r9 == 0) goto L_0x00a0
                int r9 = android.os.Build.VERSION.SDK_INT
                r1 = 24
                if (r9 < r1) goto L_0x008d
                android.content.Context r9 = r8.f7408a
                java.lang.String r1 = "file_provider_authorities"
                java.lang.String r1 = com.mi.util.ResourceUtil.a(r1)
                java.io.File r2 = new java.io.File
                r2.<init>(r0)
                android.net.Uri r9 = android.support.v4.content.FileProvider.getUriForFile(r9, r1, r2)
                goto L_0x0096
            L_0x008d:
                java.io.File r9 = new java.io.File
                r9.<init>(r0)
                android.net.Uri r9 = android.net.Uri.fromFile(r9)
            L_0x0096:
                android.content.Context r0 = r8.f7408a
                com.mi.util.AppUpdater.installApk(r0, r9)
                java.lang.Integer r9 = java.lang.Integer.valueOf(r4)
                return r9
            L_0x00a0:
                java.lang.Integer r9 = java.lang.Integer.valueOf(r2)
                return r9
            L_0x00a5:
                r9 = -2
                java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
                return r9
            L_0x00ab:
                r9 = -3
                java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.util.AppUpdater.PatchApkTask.doInBackground(java.lang.String[]):java.lang.Integer");
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Integer num) {
            super.onPostExecute(num);
            if (num.intValue() != 1) {
                Utils.Preference.setBooleanPref(this.f7408a, Constants.AppUpdate.j, false);
                new CheckApkTask(this.f7408a, this.d, this.e, Utils.Preference.getStringPref(this.f7408a, Constants.AppUpdate.l, (String) null), true, true).execute(new String[0]);
            }
        }
    }

    public void updateViews(final DownLoadProgressCallback downLoadProgressCallback) {
        if (this.mUpdateTimer != null) {
            this.mUpdateTimer.cancel();
        }
        this.mUpdateTimer = new Timer();
        final DownloadManager downloadManager = (DownloadManager) this.mContext.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD);
        this.mUpdateTimer.schedule(new TimerTask() {
            public void run() {
                int i;
                long longPref = Utils.Preference.getLongPref(AppUpdater.this.mContext, "pref_download_id", 0);
                int i2 = 0;
                Cursor query = downloadManager.query(new DownloadManager.Query().setFilterById(new long[]{longPref}));
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            i2 = query.getInt(query.getColumnIndex("bytes_so_far"));
                            i = query.getInt(query.getColumnIndex("total_size"));
                            if (16 == query.getInt(query.getColumnIndex("status"))) {
                                AppUpdater.this.updateProgressFailed(downLoadProgressCallback);
                            }
                        } else {
                            i = 0;
                        }
                        query.close();
                        if (i != 0) {
                            int i3 = (i2 * 100) / i;
                            if (i3 > 0 && downLoadProgressCallback != null) {
                                downLoadProgressCallback.a(i3);
                            }
                            if (i3 == 100) {
                                AppUpdater.this.mUpdateTimer.cancel();
                            }
                        }
                    } catch (Exception e) {
                        Log.d("", e.toString());
                    }
                }
            }
        }, 0, 300);
    }

    /* access modifiers changed from: private */
    public void updateProgressFailed(DownLoadProgressCallback downLoadProgressCallback) {
        if (this.mUpdateTimer != null) {
            this.mUpdateTimer.cancel();
        }
        if (downLoadProgressCallback != null) {
            downLoadProgressCallback.a();
        }
    }

    private static class CheckApkTask extends AsyncTask<String, Void, Boolean> {

        /* renamed from: a  reason: collision with root package name */
        private Context f7407a;
        private int b;
        private String c;
        private String d;
        private boolean e;
        private boolean f;

        public CheckApkTask(Context context, int i, String str, String str2, boolean z, boolean z2) {
            this.f7407a = context;
            this.b = i;
            this.c = str;
            this.d = str2;
            this.e = z;
            this.f = z2;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Boolean doInBackground(String... strArr) {
            Uri uri;
            String format = String.format("%s/%s_%s.apk", new Object[]{this.f7407a.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), Device.q, Integer.valueOf(this.b)});
            File file = new File(format);
            if (file.exists() && file.isFile()) {
                if (TextUtils.isEmpty(this.c) || !SignUtils.a(format, this.c)) {
                    file.delete();
                } else {
                    if (Build.VERSION.SDK_INT >= 24) {
                        uri = FileProvider.getUriForFile(this.f7407a, ResourceUtil.a("file_provider_authorities"), file);
                    } else {
                        uri = Uri.fromFile(file);
                    }
                    AppUpdater.installApk(this.f7407a, uri);
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (bool.booleanValue()) {
                return;
            }
            if (!AppUpdater.canDownloadState(this.f7407a)) {
                MiToast.a(this.f7407a, R.string.open_download, 1);
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.parse("package:" + "com.android.providers.downloads"));
                this.f7407a.startActivity(intent);
                return;
            }
            AppUpdater.requestDownload(this.f7407a, this.d, this.e, this.f);
        }
    }

    static {
        try {
            System.loadLibrary("ApkPatchLibrary");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }
}
