package com.xiaomi.smarthome.miio.update;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.coloros.mcssdk.PushManager;
import com.hannto.printservice.hanntoprintservice.entity.PrinterParmater;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.plugin.NotificationChannelCreator;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.update.AppInnerUpdateInfo;
import com.xiaomi.smarthome.framework.update.api.UpdateApi;
import com.xiaomi.smarthome.framework.update.api.entity.AppInnerUpdateResult;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.imagecache.ImageCacheUtils;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.file.FileUtil;
import com.xiaomi.smarthome.messagecenter.MessageCenter;
import java.io.File;
import java.lang.reflect.Field;
import org.json.JSONException;
import org.json.JSONObject;

public class AppUpdateManger {

    /* renamed from: a  reason: collision with root package name */
    static final String f19954a = "app_ignore_version_code";
    static final String b = "com.xiaomi.smarthome.miio.update.AppUpdateManger";
    public static final String c = "action_update_progress";
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 0;
    static AppUpdateManger g = null;
    public static boolean u = false;
    private static final int x = 257;
    NotificationManager h;
    JSONObject i;
    int j;
    String k;
    String l;
    String m;
    boolean n = false;
    int o = 0;
    MLAlertDialog p = null;
    Context q = SHApplication.getAppContext();
    boolean r = false;
    String s = null;
    int t = 0;
    DownloadTask v = null;
    final BroadcastReceiver w = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("status");
            if (TextUtils.isEmpty(stringExtra) || AppUpdateManger.this.p == null) {
                return;
            }
            if (stringExtra.equals(NotificationCompat.CATEGORY_PROGRESS)) {
                int intExtra = intent.getIntExtra(NotificationCompat.CATEGORY_PROGRESS, 0);
                if (intExtra > 0 && intExtra < 100) {
                    Button button = AppUpdateManger.this.p.getButton(-1);
                    button.setText(AppUpdateManger.this.q.getResources().getQuantityString(R.plurals.upgrade_pkg_downloaded_is_downloading, intExtra, new Object[]{Integer.valueOf(intExtra)}) + Operators.MOD);
                }
            } else if (stringExtra.equals("success")) {
                if (AppUpdateManger.a().j() == 1) {
                    AppUpdateManger.this.p.getButton(-1).setText(R.string.update_install);
                    AppUpdateManger.this.p.getButton(-1).setEnabled(true);
                    AppUpdateManger.this.p.getButton(-1).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            AppUpdateManger.a(AppUpdateManger.this.q);
                        }
                    });
                } else if (AppUpdateManger.a().j() == 2) {
                    AppUpdateManger.this.b((Dialog) AppUpdateManger.this.p);
                }
                AppUpdateManger.a(AppUpdateManger.this.q);
            } else {
                AppUpdateManger.this.b((Dialog) AppUpdateManger.this.p);
                Toast.makeText(AppUpdateManger.this.q, R.string.app_upgrade_failed_smarthome, 0).show();
            }
        }
    };

    public interface DownloadProgressListener {
        void a();

        void a(int i);

        void b();

        void c();
    }

    public interface UpdateEventListener {
        void a();

        void b();
    }

    public static void a(Context context) {
    }

    public static AppUpdateManger a() {
        if (g == null) {
            g = new AppUpdateManger();
        }
        return g;
    }

    public static boolean b() {
        if (d() && PreferenceUtils.c(SHApplication.getAppContext(), f19954a, 0) != g()) {
            return false;
        }
        return true;
    }

    public static synchronized void c() {
        synchronized (AppUpdateManger.class) {
            PreferenceUtils.a(SHApplication.getAppContext(), f19954a, g());
        }
    }

    public static boolean d() {
        return a().n;
    }

    public static boolean e() {
        return a().o == 1;
    }

    public static String f() {
        return a().k;
    }

    public static int g() {
        return a().j;
    }

    public static String h() {
        return a().l;
    }

    public static String i() {
        return a().m;
    }

    public int j() {
        return this.o;
    }

    public void a(UpdateEventListener updateEventListener) {
        if (updateEventListener != null) {
            updateEventListener.a();
        }
    }

    public void b(UpdateEventListener updateEventListener) {
        if (updateEventListener != null) {
            updateEventListener.b();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(com.xiaomi.smarthome.miio.update.AppUpdateManger.UpdateEventListener r6) {
        /*
            r5 = this;
            android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            java.lang.String r1 = r0.getPackageName()
            r2 = 0
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ Exception -> 0x0016 }
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r1, r2)     // Catch:{ Exception -> 0x0016 }
            int r1 = r0.versionCode     // Catch:{ Exception -> 0x0016 }
            java.lang.String r0 = r0.versionName     // Catch:{ Exception -> 0x0017 }
            goto L_0x0018
        L_0x0016:
            r1 = 0
        L_0x0017:
            r0 = 0
        L_0x0018:
            boolean r3 = com.xiaomi.smarthome.globalsetting.GlobalSetting.w
            r4 = 1
            if (r3 != 0) goto L_0x0021
            boolean r3 = com.xiaomi.smarthome.globalsetting.GlobalSetting.q
            if (r3 == 0) goto L_0x0031
        L_0x0021:
            com.xiaomi.smarthome.framework.page.DevelopSharePreManager r3 = com.xiaomi.smarthome.framework.page.DevelopSharePreManager.a()
            boolean r3 = r3.c()
            if (r3 == 0) goto L_0x0030
            r1 = 1000(0x3e8, float:1.401E-42)
            java.lang.String r0 = "1.1.1"
            goto L_0x0031
        L_0x0030:
            r4 = 0
        L_0x0031:
            if (r4 == 0) goto L_0x003b
            com.xiaomi.smarthome.miio.update.AppUpdateManger r2 = a()
            r2.a(r0, r1, r6)
            goto L_0x0042
        L_0x003b:
            com.xiaomi.smarthome.miio.update.AppUpdateManger r2 = a()
            r2.b(r0, r1, r6)
        L_0x0042:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.update.AppUpdateManger.c(com.xiaomi.smarthome.miio.update.AppUpdateManger$UpdateEventListener):void");
    }

    public void a(final String str, final int i2, final UpdateEventListener updateEventListener) {
        String packageName = SHApplication.getAppContext().getPackageName();
        UpdateApi a2 = UpdateApi.a();
        Context appContext = SHApplication.getAppContext();
        a2.c(appContext, packageName + ".gray", str, i2, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    String str = AppUpdateManger.b;
                    Log.d(str, "getGrayUpdateInfo  ---onsuccess:" + jSONObject);
                    if (jSONObject.optInt("new_version") >= 0) {
                        AppUpdateManger.this.n = true;
                        AppUpdateManger.this.a(jSONObject);
                        AppUpdateManger.this.a(updateEventListener);
                        return;
                    }
                    AppUpdateManger.this.a(SHApplication.getAppContext(), str, i2, updateEventListener);
                }
            }

            public void onFailure(Error error) {
                Log.d(AppUpdateManger.b, "getGrayUpdateInfo:--onFailure");
                AppUpdateManger.this.a(SHApplication.getAppContext(), str, i2, updateEventListener);
            }
        });
    }

    public void b(String str, int i2, final UpdateEventListener updateEventListener) {
        String packageName = SHApplication.getAppContext().getPackageName();
        int e2 = SystemApi.a().e(SHApplication.getAppContext());
        UpdateApi.a().b(SHApplication.getAppContext(), packageName, SystemApi.a().f(SHApplication.getAppContext()), e2, new AsyncCallback<AppInnerUpdateResult, Error>() {
            /* renamed from: a */
            public void onSuccess(AppInnerUpdateResult appInnerUpdateResult) {
                if (appInnerUpdateResult == null) {
                    AppUpdateManger.this.b(updateEventListener);
                } else if (TextUtils.isEmpty(appInnerUpdateResult.f17775a) || TextUtils.isEmpty(appInnerUpdateResult.b) || TextUtils.isEmpty(appInnerUpdateResult.c) || TextUtils.isEmpty(appInnerUpdateResult.d) || TextUtils.isEmpty(appInnerUpdateResult.e) || TextUtils.isEmpty(appInnerUpdateResult.f)) {
                    AppUpdateManger.this.b(updateEventListener);
                } else {
                    String str = appInnerUpdateResult.e;
                    String str2 = appInnerUpdateResult.f;
                    String[] split = str.split("-");
                    int parseInt = Integer.parseInt(split[2]);
                    String str3 = split[3];
                    int parseInt2 = Integer.parseInt(split[4]);
                    int e = SystemApi.a().e(SHApplication.getAppContext());
                    if (e < parseInt || (e == parseInt && 1360 < parseInt2)) {
                        AppInnerUpdateInfo appInnerUpdateInfo = new AppInnerUpdateInfo();
                        appInnerUpdateInfo.f17689a = true;
                        appInnerUpdateInfo.b = parseInt;
                        appInnerUpdateInfo.c = str3 + "-" + parseInt2 + "-DB";
                        appInnerUpdateInfo.d = str2;
                        appInnerUpdateInfo.e = "";
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("download_url", appInnerUpdateInfo.d);
                            jSONObject.put("change_log", "DB");
                            jSONObject.put("new_version_name", appInnerUpdateInfo.c);
                            jSONObject.put("new_version_code", appInnerUpdateInfo.b);
                            AppUpdateManger.this.n = true;
                            AppUpdateManger.this.a(jSONObject);
                            AppUpdateManger.this.a(updateEventListener);
                        } catch (JSONException unused) {
                            AppUpdateManger.this.b(updateEventListener);
                        }
                    } else {
                        AppUpdateManger.this.b(updateEventListener);
                    }
                }
            }

            public void onFailure(Error error) {
                AppUpdateManger.this.b(updateEventListener);
            }
        });
    }

    public void a(Context context, String str, int i2, final UpdateEventListener updateEventListener) {
        UpdateApi.a().h(context, str, i2, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    String str = AppUpdateManger.b;
                    Log.d(str, "getNormalUpdateInfo:" + jSONObject);
                    if (jSONObject.optInt("new_version") >= 0) {
                        AppUpdateManger.this.n = true;
                        AppUpdateManger.this.a(jSONObject);
                        AppUpdateManger.this.a(updateEventListener);
                        return;
                    }
                    AppUpdateManger.this.n = false;
                    AppUpdateManger.this.a(jSONObject);
                    AppUpdateManger.this.a(updateEventListener);
                }
            }

            public void onFailure(Error error) {
                AppUpdateManger.this.b(updateEventListener);
            }
        });
    }

    public static class DownloadTask extends AsyncTask<Object, Long, NetworkUtils.DownloadResponse> {

        /* renamed from: a  reason: collision with root package name */
        static final int f19968a = 1;
        AsyncResponseCallback<Void> b;
        Bitmap c;
        String d;
        String e;
        NotificationManager f;
        DownloadProgressListener g;
        int h = 0;
        Context i;
        boolean j = false;

        public void a(boolean z) {
            this.j = z;
        }

        DownloadTask(Context context, boolean z, String str, Bitmap bitmap, String str2, NotificationManager notificationManager, DownloadProgressListener downloadProgressListener) {
            this.i = context;
            this.d = str;
            this.c = bitmap;
            this.e = str2;
            this.f = notificationManager;
            this.g = downloadProgressListener;
            this.j = z;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public NetworkUtils.DownloadResponse doInBackground(Object... objArr) {
            if (isCancelled()) {
                return null;
            }
            this.b = objArr[0];
            FileUtil.b(new File(this.e));
            return NetworkUtils.a(this.i, this.d, new File(this.e), new NetworkUtils.OnDownloadProgress() {
                private int b = 0;

                public void a() {
                }

                public void a(String str) {
                }

                public void b() {
                }

                public void a(long j, long j2) {
                    Notification notification;
                    int i = (int) ((100 * j) / j2);
                    if (i / 4 != this.b / 4) {
                        this.b = i;
                        Log.d(AppUpdateManger.b, String.format("before percent:%d  downloaded:%d  progress:%d", new Object[]{Integer.valueOf(i), Long.valueOf(j), Integer.valueOf(DownloadTask.this.h)}));
                        if (DownloadTask.this.h != i) {
                            int abs = Math.abs(DownloadTask.this.h - i);
                            DownloadTask.this.h = i;
                            if (abs >= 1) {
                                DownloadTask.this.publishProgress(new Long[]{Long.valueOf((long) DownloadTask.this.h)});
                            }
                        }
                        Log.d(AppUpdateManger.b, String.format("after percent:%d  downloaded:%d  progress:%d", new Object[]{Integer.valueOf(i), Long.valueOf(j), Integer.valueOf(DownloadTask.this.h)}));
                        if (!DownloadTask.this.j) {
                            return;
                        }
                        if (ApiHelper.d) {
                            DownloadTask.this.a(j, j2);
                        } else if (DownloadTask.this.f != null) {
                            if (Build.VERSION.SDK_INT >= 26) {
                                notification = new Notification.Builder(DownloadTask.this.i, NotificationChannelCreator.b(DownloadTask.this.f)).build();
                            } else {
                                notification = new Notification();
                            }
                            notification.icon = R.drawable.notify_icon;
                            notification.tickerText = DownloadTask.this.i.getString(R.string.upgrade_pkg_downloaded_title_smarthome);
                            notification.flags &= -17;
                            DownloadTask.this.f.notify(R.drawable.ic_launcher, notification);
                        }
                    }
                }
            }, false, false);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onProgressUpdate(Long... lArr) {
            if (this.j && this.g != null) {
                this.g.a(this.h);
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(NetworkUtils.DownloadResponse downloadResponse) {
            if (this.j) {
                AppUpdateManger.a().l();
            }
            if (downloadResponse.b != 3) {
                if (this.g != null) {
                    this.g.b();
                }
                if (this.j) {
                    Toast.makeText(this.i, R.string.app_upgrade_failed_smarthome, 0).show();
                }
            } else if (this.g != null) {
                this.g.a();
            }
        }

        /* access modifiers changed from: private */
        public void a(long j2, long j3) {
            Notification notification;
            Log.d(AppUpdateManger.b, "sendDownloadNotification");
            if (this.f != null) {
                if (Build.VERSION.SDK_INT >= 26) {
                    notification = new Notification.Builder(this.i, NotificationChannelCreator.b(this.f)).build();
                } else {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this.i);
                    builder.setAutoCancel(true);
                    builder.setShowWhen(false);
                    builder.setContentTitle(this.i.getString(R.string.upgrade_pkg_downloaded_title_smarthome) + this.h + Operators.MOD);
                    builder.setContentText(this.i.getString(R.string.upgrade_pkg_downloaded_title_smarthome) + this.h + Operators.MOD);
                    builder.setLargeIcon(this.c);
                    builder.setSmallIcon(R.drawable.notify_icon);
                    notification = builder.build();
                }
                if (this.f != null) {
                    this.f.notify(257, notification);
                }
            }
        }
    }

    public void k() {
        if (this.v != null && !this.v.isCancelled()) {
            Log.d(b, "cancelDownloadTask");
            this.v.cancel(true);
        }
    }

    public void l() {
        String str = b;
        StringBuilder sb = new StringBuilder();
        sb.append("cancelUpdateNotification");
        sb.append(this.h == null);
        Log.d(str, sb.toString());
        if (this.h != null) {
            this.h.cancel(257);
            return;
        }
        NotificationManager notificationManager = (NotificationManager) SHApplication.getAppContext().getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        if (notificationManager != null) {
            notificationManager.cancel(257);
        }
    }

    public void a(boolean z) {
        if (this.v != null) {
            this.v.a(z);
        }
    }

    public boolean a(Context context, int i2) {
        String str = b(context) + ".apk";
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return false;
        }
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 128);
            if (packageArchiveInfo != null && packageArchiveInfo.versionCode >= i2) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String b(Context context) {
        String path = ("mounted".equals(Environment.getExternalStorageState()) || !ImageCacheUtils.b()) ? ImageCacheUtils.a(context).getPath() : null;
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        return path + "/new_version";
    }

    public void a(final Context context, boolean z, String str, Bitmap bitmap) {
        if (!this.r) {
            this.s = b(context);
            if (!TextUtils.isEmpty(this.s)) {
                AnonymousClass4 r8 = new DownloadProgressListener() {
                    public void a() {
                        AppUpdateManger.this.r = false;
                        File file = new File(AppUpdateManger.b(context));
                        if (file.exists()) {
                            File file2 = new File(file.getAbsolutePath() + ".apk");
                            if (file2.exists()) {
                                FileUtil.b(file2);
                            }
                            file.renameTo(file2);
                            Intent intent = new Intent(AppUpdateManger.c);
                            intent.putExtra("status", "success");
                            context.sendBroadcast(intent);
                            return;
                        }
                        Intent intent2 = new Intent(AppUpdateManger.c);
                        intent2.putExtra("status", "failed");
                        context.sendBroadcast(intent2);
                    }

                    public void b() {
                        AppUpdateManger.this.r = false;
                        Intent intent = new Intent(AppUpdateManger.c);
                        intent.putExtra("status", "failed");
                        context.sendBroadcast(intent);
                    }

                    public void c() {
                        AppUpdateManger.this.r = false;
                        Intent intent = new Intent(AppUpdateManger.c);
                        intent.putExtra("status", PrinterParmater.q);
                        context.sendBroadcast(intent);
                    }

                    public void a(int i) {
                        AppUpdateManger.this.t = i;
                        Intent intent = new Intent(AppUpdateManger.c);
                        intent.putExtra("status", NotificationCompat.CATEGORY_PROGRESS);
                        intent.putExtra(NotificationCompat.CATEGORY_PROGRESS, i);
                        context.sendBroadcast(intent);
                    }
                };
                this.h = (NotificationManager) context.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
                this.v = new DownloadTask(context, z, str, bitmap, this.s, this.h, r8);
                AsyncTaskUtils.a(this.v, new AsyncResponseCallback<Void>() {
                    public void a(int i) {
                    }

                    public void a(int i, Object obj) {
                    }

                    public void a(Void voidR) {
                    }
                });
                this.r = true;
                if (z) {
                    Toast.makeText(context, R.string.upgrade_pkg_downloading_title2_smarthome, 0).show();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(JSONObject jSONObject) {
        this.i = jSONObject;
        if (jSONObject != null) {
            int optInt = jSONObject.optInt("new_version", -1);
            if (optInt == 0) {
                this.o = 0;
            } else if (optInt == 1) {
                this.o = 2;
            } else if (optInt == 2) {
                this.o = 1;
            }
            this.m = jSONObject.optString("download_url");
            this.l = jSONObject.optString("change_log");
            this.k = jSONObject.optString("new_version_name");
            this.j = jSONObject.optInt("new_version_code");
        }
    }

    public void a(final Activity activity) {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity);
        builder.a((int) R.string.dialog_title_apk_downloaded);
        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (activity != null && !activity.isFinishing()) {
                    AppUpdateManger.a((Context) activity);
                }
            }
        });
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (AppUpdateManger.a().j() == 1) {
                    AppUpdateManger.a().k();
                    AppUpdateManger.a().l();
                    System.exit(0);
                } else if (AppUpdateManger.a().j() == 0) {
                    AppUpdateManger.c();
                } else {
                    AppUpdateManger.a().j();
                }
                dialogInterface.dismiss();
            }
        });
        builder.b().show();
    }

    public void b(final Activity activity) {
        Log.d(MessageCenter.a().getClass().getSimpleName(), "强制升级");
        if (this.p == null) {
            MLAlertDialog.Builder b2 = new MLAlertDialog.Builder(activity).a((int) R.string.dialog_right_update, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (activity != null && !activity.isFinishing()) {
                        AppUpdateManger.this.a((Dialog) AppUpdateManger.this.p);
                        if (AppUpdateManger.a().a((Context) activity, AppUpdateManger.g())) {
                            AppUpdateManger.a((Context) activity);
                        } else {
                            AppUpdateManger.this.o();
                        }
                    }
                }
            }).a(false).b((CharSequence) this.q.getString(R.string.dialog_title_force_update));
            b2.b((int) R.string.exit, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    AppUpdateManger.a().l();
                    AppUpdateManger.a().k();
                    System.exit(0);
                }
            });
            this.p = b2.b();
            if (!activity.isFinishing()) {
                this.p.show();
            }
        }
    }

    public void c(final Activity activity) {
        Log.d(MessageCenter.a().getClass().getSimpleName(), "半强制升级");
        if (this.p == null) {
            MLAlertDialog.Builder b2 = new MLAlertDialog.Builder(activity).a((int) R.string.dialog_right_update, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (activity != null && !activity.isFinishing()) {
                        AppUpdateManger.this.a((Dialog) AppUpdateManger.this.p);
                        if (AppUpdateManger.a().a((Context) activity, AppUpdateManger.g())) {
                            AppUpdateManger.a((Context) activity);
                        } else {
                            AppUpdateManger.this.o();
                        }
                    }
                }
            }).a(false).b((CharSequence) String.format(this.q.getResources().getString(R.string.dialog_title_half_force_update), new Object[]{this.k}));
            b2.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    AppUpdateManger.a().k();
                    AppUpdateManger.a().l();
                    AppUpdateManger.this.b((Dialog) AppUpdateManger.this.p);
                }
            });
            this.p = b2.b();
            if (!activity.isFinishing()) {
                this.p.show();
            }
        }
    }

    /* access modifiers changed from: private */
    public void o() {
        a().a(this.q, true, i(), BitmapFactory.decodeResource(this.q.getResources(), R.drawable.ic_launcher));
        Button button = this.p.getButton(-1);
        button.setText(this.q.getResources().getQuantityString(R.plurals.upgrade_pkg_downloaded_is_downloading, 1, new Object[]{1}) + Operators.MOD);
        this.p.getButton(-1).setEnabled(false);
    }

    /* access modifiers changed from: private */
    public void a(Dialog dialog) {
        if (dialog != null) {
            try {
                Field declaredField = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                declaredField.setAccessible(true);
                declaredField.set(dialog, false);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(Dialog dialog) {
        if (dialog != null) {
            try {
                Log.d(MessageCenter.a().getClass().getSimpleName(), "closeDialog");
                Field declaredField = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                declaredField.setAccessible(true);
                declaredField.set(dialog, true);
                dialog.dismiss();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void m() {
        if (this.q != null && this.w != null) {
            try {
                this.q.unregisterReceiver(this.w);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void n() {
        if (this.q != null && this.w != null) {
            this.q.registerReceiver(this.w, new IntentFilter(c));
        }
    }
}
