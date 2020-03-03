package com.xiaomi.smarthome.framework.update;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.page.DevelopSharePreManager;
import com.xiaomi.smarthome.framework.plugin.FileUtils;
import com.xiaomi.smarthome.framework.update.api.UpdateApi;
import com.xiaomi.smarthome.framework.update.api.entity.AppGrayUpdateResult;
import com.xiaomi.smarthome.framework.update.api.entity.AppInnerUpdateResult;
import com.xiaomi.smarthome.framework.update.api.entity.AppReleaseUpdateResult;
import com.xiaomi.smarthome.framework.update.api.entity.FirmwareUpdateBatchResult;
import com.xiaomi.smarthome.framework.update.api.entity.FirmwareUpdateResult;
import com.xiaomi.smarthome.library.common.imagecache.ImageCacheUtils;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.http.ClientUtil;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.HttpRangeUtil;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.library.http.async.FileAsyncHandler;
import com.xiaomi.smarthome.library.http.async.HttpAsyncHandle;
import com.xiaomi.smarthome.miio.Miio;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class UpdateManager {
    private static final int d = 60;
    private static final long e = 2000;
    private static final int f = 1;
    private static final int g = 2;
    private static final int h = 3;
    private static final String i = "UpdateManager";
    private static UpdateManager j;

    /* renamed from: a  reason: collision with root package name */
    Handler f17729a = new Handler(Looper.getMainLooper());
    MessageHandlerThread b = new MessageHandlerThread("UpdateWorker");
    WorkerHandler c;
    /* access modifiers changed from: private */
    public Context k = SHApplication.getAppContext();
    private OkHttpClient l;
    /* access modifiers changed from: private */
    public volatile FileAsyncHandler m = null;
    /* access modifiers changed from: private */
    public volatile WeakReference<DownloadAppCallback> n = null;

    public interface CheckAllFirmwareUpdateCallback {
        void a();

        void a(List<FirmwareUpdateInfo> list);

        void b();
    }

    private interface CheckAppGrayUpdateCallback {
        void a();

        void a(AppGrayUpdateInfo appGrayUpdateInfo);

        void b();
    }

    private interface CheckAppInnerUpdateCallback {
        void a();

        void a(AppInnerUpdateInfo appInnerUpdateInfo);

        void b();
    }

    private interface CheckAppReleaseUpdateCallback {
        void a();

        void a(AppReleaseUpdateInfo appReleaseUpdateInfo);

        void b();

        void b(AppReleaseUpdateInfo appReleaseUpdateInfo);
    }

    public interface CheckAppUpdateCallback {
        void a();

        void a(AppGrayUpdateInfo appGrayUpdateInfo);

        void a(AppInnerUpdateInfo appInnerUpdateInfo);

        void a(AppReleaseUpdateInfo appReleaseUpdateInfo);

        void b();

        void b(AppReleaseUpdateInfo appReleaseUpdateInfo);
    }

    public interface CheckFirmwareUpdateCallback {
        void a();

        void a(FirmwareUpdateInfo firmwareUpdateInfo);

        void b(FirmwareUpdateInfo firmwareUpdateInfo);
    }

    public interface DownloadAppCallback {
        void a();

        void a(float f);

        void a(HttpAsyncHandle httpAsyncHandle);

        void a(String str);

        void b();

        void b(HttpAsyncHandle httpAsyncHandle);
    }

    public interface FirmwareUpdateCallback {
        void a();

        void b();
    }

    private interface FirmwareUpdateCallbackInternal {
        void a();

        void b();
    }

    public interface FirmwareUpdatePollCallback {
        void a();

        void a(float f);

        void b();
    }

    private interface FirmwareUpdatePollCallbackInternal {
        void a();

        void a(float f);

        void b();
    }

    private UpdateManager() {
        this.b.start();
        this.c = new WorkerHandler(this.b.getLooper());
    }

    public static UpdateManager a() {
        if (j == null) {
            j = new UpdateManager();
        }
        return j;
    }

    private OkHttpClient c() {
        return ClientUtil.a();
    }

    public void a(final String str, int i2, final CheckFirmwareUpdateCallback checkFirmwareUpdateCallback) {
        if (!TextUtils.isEmpty(str)) {
            UpdateApi.a().c(this.k, str, i2, new AsyncCallback<FirmwareUpdateResult, Error>() {
                /* renamed from: a */
                public void onSuccess(FirmwareUpdateResult firmwareUpdateResult) {
                    if (firmwareUpdateResult != null) {
                        FirmwareUpdateInfo firmwareUpdateInfo = new FirmwareUpdateInfo();
                        firmwareUpdateInfo.f17724a = str;
                        firmwareUpdateInfo.e = firmwareUpdateResult.d;
                        firmwareUpdateInfo.b = firmwareUpdateResult.f17779a;
                        firmwareUpdateInfo.c = firmwareUpdateResult.b;
                        firmwareUpdateInfo.d = firmwareUpdateResult.c;
                        firmwareUpdateInfo.f = firmwareUpdateResult.e;
                        firmwareUpdateInfo.h = firmwareUpdateResult.g;
                        firmwareUpdateInfo.g = firmwareUpdateResult.f;
                        firmwareUpdateInfo.i = firmwareUpdateResult.h;
                        if ("installing".equals(firmwareUpdateInfo.h)) {
                            firmwareUpdateInfo.b = true;
                        }
                        if (firmwareUpdateInfo.e) {
                            if (checkFirmwareUpdateCallback != null) {
                                checkFirmwareUpdateCallback.b(firmwareUpdateInfo);
                            }
                        } else if (checkFirmwareUpdateCallback != null) {
                            checkFirmwareUpdateCallback.a(firmwareUpdateInfo);
                        }
                    } else if (checkFirmwareUpdateCallback != null) {
                        checkFirmwareUpdateCallback.a();
                    }
                }

                public void onFailure(Error error) {
                    if (checkFirmwareUpdateCallback != null) {
                        checkFirmwareUpdateCallback.a();
                    }
                }
            });
        } else if (checkFirmwareUpdateCallback != null) {
            checkFirmwareUpdateCallback.a();
        }
    }

    public void a(List<KeyValuePair> list, final CheckAllFirmwareUpdateCallback checkAllFirmwareUpdateCallback) {
        UpdateApi.a().b(this.k, list, new AsyncCallback<FirmwareUpdateBatchResult, Error>() {
            /* renamed from: a */
            public void onSuccess(FirmwareUpdateBatchResult firmwareUpdateBatchResult) {
                if (firmwareUpdateBatchResult == null) {
                    if (checkAllFirmwareUpdateCallback != null) {
                        checkAllFirmwareUpdateCallback.b();
                    }
                } else if (firmwareUpdateBatchResult.f17777a != null && firmwareUpdateBatchResult.f17777a.size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    int size = firmwareUpdateBatchResult.f17777a.size();
                    for (int i = 0; i < size; i++) {
                        FirmwareUpdateBatchResult.UpdateResultItem updateResultItem = firmwareUpdateBatchResult.f17777a.get(i);
                        if (!updateResultItem.e) {
                            FirmwareUpdateInfo firmwareUpdateInfo = new FirmwareUpdateInfo();
                            firmwareUpdateInfo.f17724a = updateResultItem.f17778a;
                            firmwareUpdateInfo.e = updateResultItem.e;
                            firmwareUpdateInfo.b = updateResultItem.b;
                            firmwareUpdateInfo.c = updateResultItem.c;
                            firmwareUpdateInfo.d = updateResultItem.d;
                            firmwareUpdateInfo.f = updateResultItem.f;
                            firmwareUpdateInfo.h = updateResultItem.h;
                            firmwareUpdateInfo.g = updateResultItem.g;
                            firmwareUpdateInfo.i = updateResultItem.i;
                            firmwareUpdateInfo.j = updateResultItem.j;
                            if ("installing".equals(firmwareUpdateInfo.h)) {
                                firmwareUpdateInfo.b = true;
                            }
                            arrayList.add(firmwareUpdateInfo);
                        }
                    }
                    if (arrayList.size() <= 0) {
                        if (checkAllFirmwareUpdateCallback != null) {
                            checkAllFirmwareUpdateCallback.a();
                        }
                    } else if (checkAllFirmwareUpdateCallback != null) {
                        checkAllFirmwareUpdateCallback.a(arrayList);
                    }
                } else if (checkAllFirmwareUpdateCallback != null) {
                    checkAllFirmwareUpdateCallback.b();
                }
            }

            public void onFailure(Error error) {
                if (checkAllFirmwareUpdateCallback != null) {
                    checkAllFirmwareUpdateCallback.b();
                }
            }
        });
    }

    public void a(final CheckAppUpdateCallback checkAppUpdateCallback) {
        a((CheckAppGrayUpdateCallback) new CheckAppGrayUpdateCallback() {
            public void a(AppGrayUpdateInfo appGrayUpdateInfo) {
                if (checkAppUpdateCallback != null) {
                    checkAppUpdateCallback.a(appGrayUpdateInfo);
                }
            }

            public void a() {
                UpdateManager.this.a((CheckAppReleaseUpdateCallback) new CheckAppReleaseUpdateCallback() {
                    public void a(AppReleaseUpdateInfo appReleaseUpdateInfo) {
                        if (checkAppUpdateCallback != null) {
                            checkAppUpdateCallback.a(appReleaseUpdateInfo);
                        }
                    }

                    public void b(AppReleaseUpdateInfo appReleaseUpdateInfo) {
                        if (checkAppUpdateCallback != null) {
                            checkAppUpdateCallback.b(appReleaseUpdateInfo);
                        }
                    }

                    public void a() {
                        if (checkAppUpdateCallback != null) {
                            checkAppUpdateCallback.a();
                        }
                    }

                    public void b() {
                        if (checkAppUpdateCallback != null) {
                            checkAppUpdateCallback.b();
                        }
                    }
                });
            }

            public void b() {
                UpdateManager.this.a((CheckAppReleaseUpdateCallback) new CheckAppReleaseUpdateCallback() {
                    public void a(AppReleaseUpdateInfo appReleaseUpdateInfo) {
                        if (checkAppUpdateCallback != null) {
                            checkAppUpdateCallback.a(appReleaseUpdateInfo);
                        }
                    }

                    public void b(AppReleaseUpdateInfo appReleaseUpdateInfo) {
                        if (checkAppUpdateCallback != null) {
                            checkAppUpdateCallback.b(appReleaseUpdateInfo);
                        }
                    }

                    public void a() {
                        if (checkAppUpdateCallback != null) {
                            checkAppUpdateCallback.a();
                        }
                    }

                    public void b() {
                        if (checkAppUpdateCallback != null) {
                            checkAppUpdateCallback.b();
                        }
                    }
                });
            }
        });
    }

    private void a(final CheckAppGrayUpdateCallback checkAppGrayUpdateCallback) {
        UpdateApi.a().a(this.k, SHApplication.getAppContext().getPackageName() + ".gray", SystemApi.a().f(this.k), DevelopSharePreManager.a().c() ? 1000 : SystemApi.a().e(this.k), new AsyncCallback<AppGrayUpdateResult, Error>() {
            /* renamed from: a */
            public void onSuccess(AppGrayUpdateResult appGrayUpdateResult) {
                if (appGrayUpdateResult != null) {
                    AppGrayUpdateInfo appGrayUpdateInfo = new AppGrayUpdateInfo();
                    appGrayUpdateInfo.f17688a = appGrayUpdateResult.f17774a >= 0;
                    appGrayUpdateInfo.b = appGrayUpdateResult.b;
                    appGrayUpdateInfo.c = appGrayUpdateResult.c;
                    appGrayUpdateInfo.d = appGrayUpdateResult.d;
                    appGrayUpdateInfo.e = appGrayUpdateResult.e;
                    Miio.b(UpdateManager.i, "checkAppGrayUpdate---" + appGrayUpdateInfo.toString());
                    if (appGrayUpdateInfo.f17688a) {
                        if (checkAppGrayUpdateCallback != null) {
                            checkAppGrayUpdateCallback.a(appGrayUpdateInfo);
                        }
                    } else if (checkAppGrayUpdateCallback != null) {
                        checkAppGrayUpdateCallback.a();
                    }
                } else if (checkAppGrayUpdateCallback != null) {
                    checkAppGrayUpdateCallback.b();
                }
            }

            public void onFailure(Error error) {
                if (checkAppGrayUpdateCallback != null) {
                    checkAppGrayUpdateCallback.b();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final CheckAppReleaseUpdateCallback checkAppReleaseUpdateCallback) {
        int e2 = SystemApi.a().e(this.k);
        UpdateApi.a().d(SHApplication.getAppContext(), SystemApi.a().f(this.k), e2, new AsyncCallback<AppReleaseUpdateResult, Error>() {
            /* renamed from: a */
            public void onSuccess(AppReleaseUpdateResult appReleaseUpdateResult) {
                if (appReleaseUpdateResult != null) {
                    AppReleaseUpdateInfo appReleaseUpdateInfo = new AppReleaseUpdateInfo();
                    appReleaseUpdateInfo.f17690a = appReleaseUpdateResult.f17776a >= 0;
                    appReleaseUpdateInfo.b = false;
                    appReleaseUpdateInfo.c = appReleaseUpdateResult.c;
                    appReleaseUpdateInfo.d = appReleaseUpdateResult.d;
                    appReleaseUpdateInfo.e = appReleaseUpdateResult.e;
                    appReleaseUpdateInfo.f = appReleaseUpdateResult.f;
                    Miio.b(UpdateManager.i, "checkAppReleaseUpdate---" + appReleaseUpdateInfo.toString());
                    if (appReleaseUpdateInfo.f17690a) {
                        if (appReleaseUpdateInfo.b) {
                            if (checkAppReleaseUpdateCallback != null) {
                                checkAppReleaseUpdateCallback.a(appReleaseUpdateInfo);
                            }
                        } else if (checkAppReleaseUpdateCallback != null) {
                            checkAppReleaseUpdateCallback.b(appReleaseUpdateInfo);
                        }
                    } else if (checkAppReleaseUpdateCallback != null) {
                        checkAppReleaseUpdateCallback.a();
                    }
                } else if (checkAppReleaseUpdateCallback != null) {
                    checkAppReleaseUpdateCallback.b();
                }
            }

            public void onFailure(Error error) {
                if (checkAppReleaseUpdateCallback != null) {
                    checkAppReleaseUpdateCallback.b();
                }
            }
        });
    }

    private void a(final CheckAppInnerUpdateCallback checkAppInnerUpdateCallback) {
        String packageName = SHApplication.getAppContext().getPackageName();
        int e2 = SystemApi.a().e(this.k);
        UpdateApi.a().b(SHApplication.getAppContext(), packageName, SystemApi.a().f(this.k), e2, new AsyncCallback<AppInnerUpdateResult, Error>() {
            /* renamed from: a */
            public void onSuccess(AppInnerUpdateResult appInnerUpdateResult) {
                if (appInnerUpdateResult == null) {
                    if (checkAppInnerUpdateCallback != null) {
                        checkAppInnerUpdateCallback.b();
                    }
                } else if (!TextUtils.isEmpty(appInnerUpdateResult.f17775a) && !TextUtils.isEmpty(appInnerUpdateResult.b) && !TextUtils.isEmpty(appInnerUpdateResult.c) && !TextUtils.isEmpty(appInnerUpdateResult.d) && !TextUtils.isEmpty(appInnerUpdateResult.e) && !TextUtils.isEmpty(appInnerUpdateResult.f)) {
                    String str = appInnerUpdateResult.e;
                    String str2 = appInnerUpdateResult.f;
                    String[] split = str.split("-");
                    int parseInt = Integer.parseInt(split[2]);
                    String str3 = split[3];
                    int parseInt2 = Integer.parseInt(split[4]);
                    int e = SystemApi.a().e(UpdateManager.this.k);
                    if (e < parseInt || (e == parseInt && 1360 < parseInt2)) {
                        AppInnerUpdateInfo appInnerUpdateInfo = new AppInnerUpdateInfo();
                        appInnerUpdateInfo.f17689a = true;
                        appInnerUpdateInfo.b = parseInt;
                        appInnerUpdateInfo.c = str3 + "-" + parseInt2 + "-DB";
                        appInnerUpdateInfo.d = str2;
                        appInnerUpdateInfo.e = "";
                        if (checkAppInnerUpdateCallback != null) {
                            checkAppInnerUpdateCallback.a(appInnerUpdateInfo);
                        }
                    } else if (checkAppInnerUpdateCallback != null) {
                        checkAppInnerUpdateCallback.a();
                    }
                } else if (checkAppInnerUpdateCallback != null) {
                    checkAppInnerUpdateCallback.b();
                }
            }

            public void onFailure(Error error) {
                if (checkAppInnerUpdateCallback != null) {
                    checkAppInnerUpdateCallback.b();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str, int i2, String str2, final FirmwareUpdateCallbackInternal firmwareUpdateCallbackInternal) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            UpdateApi.a().a(this.k, str, i2, new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    if (firmwareUpdateCallbackInternal != null) {
                        firmwareUpdateCallbackInternal.a();
                    }
                }

                public void onFailure(Error error) {
                    if (firmwareUpdateCallbackInternal != null) {
                        firmwareUpdateCallbackInternal.b();
                    }
                }
            });
        } else if (firmwareUpdateCallbackInternal != null) {
            firmwareUpdateCallbackInternal.b();
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, int i2, String str2, FirmwareUpdatePollCallbackInternal firmwareUpdatePollCallbackInternal) {
        FirmwareUpdatePollTask firmwareUpdatePollTask = new FirmwareUpdatePollTask();
        firmwareUpdatePollTask.f17751a = str;
        firmwareUpdatePollTask.b = i2;
        firmwareUpdatePollTask.c = str2;
        firmwareUpdatePollTask.d = 0;
        firmwareUpdatePollTask.e = true;
        firmwareUpdatePollTask.g = firmwareUpdatePollCallbackInternal;
        this.c.obtainMessage(3, firmwareUpdatePollTask).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void a(final FirmwareUpdatePollTask firmwareUpdatePollTask) {
        if (firmwareUpdatePollTask.e) {
            firmwareUpdatePollTask.d++;
            if (firmwareUpdatePollTask.d > firmwareUpdatePollTask.f) {
                this.c.removeMessages(3, firmwareUpdatePollTask);
                firmwareUpdatePollTask.e = false;
                c(firmwareUpdatePollTask);
                return;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            a(firmwareUpdatePollTask.f17751a, firmwareUpdatePollTask.b, (CheckFirmwareUpdateCallback) new CheckFirmwareUpdateCallback() {
                public void a(FirmwareUpdateInfo firmwareUpdateInfo) {
                    String str;
                    String str2;
                    UpdateManager.this.a(currentTimeMillis, firmwareUpdatePollTask);
                    if (!TextUtils.isEmpty(firmwareUpdateInfo.c) && firmwareUpdatePollTask.e) {
                        if (firmwareUpdateInfo.i > 0) {
                            firmwareUpdatePollTask.f = firmwareUpdateInfo.i;
                        }
                        if (firmwareUpdateInfo.c.startsWith(JSMethod.NOT_SET)) {
                            str = firmwareUpdateInfo.c.substring(1);
                        } else {
                            str = firmwareUpdateInfo.c;
                        }
                        if (firmwareUpdatePollTask.c.startsWith(JSMethod.NOT_SET)) {
                            str2 = firmwareUpdatePollTask.c.substring(1);
                        } else {
                            str2 = firmwareUpdatePollTask.c;
                        }
                        if (str.equalsIgnoreCase(str2)) {
                            UpdateManager.this.c.removeMessages(3, firmwareUpdatePollTask);
                            firmwareUpdatePollTask.e = false;
                            UpdateManager.this.b(firmwareUpdatePollTask);
                            return;
                        }
                        UpdateManager.this.a(firmwareUpdatePollTask, ((float) firmwareUpdateInfo.g) / 100.0f);
                    }
                }

                public void b(FirmwareUpdateInfo firmwareUpdateInfo) {
                    String str;
                    String str2;
                    UpdateManager.this.a(currentTimeMillis, firmwareUpdatePollTask);
                    if (!TextUtils.isEmpty(firmwareUpdateInfo.c) && firmwareUpdatePollTask.e) {
                        if (firmwareUpdateInfo.c.startsWith(JSMethod.NOT_SET)) {
                            str = firmwareUpdateInfo.c.substring(1);
                        } else {
                            str = firmwareUpdateInfo.c;
                        }
                        if (firmwareUpdatePollTask.c.startsWith(JSMethod.NOT_SET)) {
                            str2 = firmwareUpdatePollTask.c.substring(1);
                        } else {
                            str2 = firmwareUpdatePollTask.c;
                        }
                        if (str.equalsIgnoreCase(str2)) {
                            UpdateManager.this.c.removeMessages(3, firmwareUpdatePollTask);
                            firmwareUpdatePollTask.e = false;
                            UpdateManager.this.b(firmwareUpdatePollTask);
                        }
                    }
                }

                public void a() {
                    UpdateManager.this.a(currentTimeMillis, firmwareUpdatePollTask);
                    UpdateManager.this.c.removeMessages(3, firmwareUpdatePollTask);
                    firmwareUpdatePollTask.e = false;
                    UpdateManager.this.c(firmwareUpdatePollTask);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(long j2, FirmwareUpdatePollTask firmwareUpdatePollTask) {
        long currentTimeMillis = 2000 - (System.currentTimeMillis() - j2);
        if (currentTimeMillis < 0) {
            currentTimeMillis = 0;
        }
        this.c.sendMessageDelayed(this.c.obtainMessage(3, firmwareUpdatePollTask), currentTimeMillis);
    }

    /* access modifiers changed from: private */
    public void a(FirmwareUpdatePollTask firmwareUpdatePollTask, float f2) {
        if (firmwareUpdatePollTask != null && firmwareUpdatePollTask.g != null) {
            firmwareUpdatePollTask.g.a(f2);
        }
    }

    /* access modifiers changed from: private */
    public void b(FirmwareUpdatePollTask firmwareUpdatePollTask) {
        if (firmwareUpdatePollTask != null && firmwareUpdatePollTask.g != null) {
            firmwareUpdatePollTask.g.a();
        }
    }

    /* access modifiers changed from: private */
    public void c(FirmwareUpdatePollTask firmwareUpdatePollTask) {
        if (firmwareUpdatePollTask != null && firmwareUpdatePollTask.g != null) {
            firmwareUpdatePollTask.g.b();
        }
    }

    public void a(String str, int i2, String str2, final FirmwareUpdateCallback firmwareUpdateCallback) {
        FirmwareUpdateOpInternal firmwareUpdateOpInternal = new FirmwareUpdateOpInternal();
        firmwareUpdateOpInternal.f17749a = str;
        firmwareUpdateOpInternal.b = i2;
        firmwareUpdateOpInternal.c = str2;
        firmwareUpdateOpInternal.d = new FirmwareUpdateCallbackInternal() {
            public void a() {
                if (firmwareUpdateCallback != null) {
                    UpdateManager.this.f17729a.post(new Runnable() {
                        public void run() {
                            firmwareUpdateCallback.a();
                        }
                    });
                }
            }

            public void b() {
                if (firmwareUpdateCallback != null) {
                    UpdateManager.this.f17729a.post(new Runnable() {
                        public void run() {
                            firmwareUpdateCallback.b();
                        }
                    });
                }
            }
        };
        this.c.obtainMessage(1, firmwareUpdateOpInternal).sendToTarget();
    }

    public void a(String str, int i2, String str2, final FirmwareUpdatePollCallback firmwareUpdatePollCallback) {
        FirmwareUpdatePollOpInternal firmwareUpdatePollOpInternal = new FirmwareUpdatePollOpInternal();
        firmwareUpdatePollOpInternal.f17750a = str;
        firmwareUpdatePollOpInternal.b = i2;
        firmwareUpdatePollOpInternal.c = str2;
        firmwareUpdatePollOpInternal.d = new FirmwareUpdatePollCallbackInternal() {
            public void a(final float f) {
                if (firmwareUpdatePollCallback != null) {
                    UpdateManager.this.f17729a.post(new Runnable() {
                        public void run() {
                            firmwareUpdatePollCallback.a(f);
                        }
                    });
                }
            }

            public void a() {
                if (firmwareUpdatePollCallback != null) {
                    UpdateManager.this.f17729a.post(new Runnable() {
                        public void run() {
                            firmwareUpdatePollCallback.a();
                        }
                    });
                }
            }

            public void b() {
                if (firmwareUpdatePollCallback != null) {
                    UpdateManager.this.f17729a.post(new Runnable() {
                        public void run() {
                            firmwareUpdatePollCallback.b();
                        }
                    });
                }
            }
        };
        this.c.obtainMessage(2, firmwareUpdatePollOpInternal).sendToTarget();
    }

    private String d() {
        if ("mounted".equals(Environment.getExternalStorageState()) || !ImageCacheUtils.b()) {
            return ImageCacheUtils.a(SHApplication.getAppContext()).getPath();
        }
        return SHApplication.getAppContext().getCacheDir().getPath() + File.separator + "app";
    }

    private String a(int i2) {
        return "SmartHome_" + i2 + ".apk";
    }

    public void b() {
        if (this.m != null) {
            this.m.a(true);
            this.m = null;
        }
    }

    public void a(DownloadAppCallback downloadAppCallback) {
        this.n = new WeakReference<>(downloadAppCallback);
    }

    public void a(String str, int i2, long j2) {
        DownloadAppCallback downloadAppCallback = this.n == null ? null : (DownloadAppCallback) this.n.get();
        if (!TextUtils.isEmpty(str)) {
            String d2 = d();
            if (TextUtils.isEmpty(d2)) {
                if (downloadAppCallback != null) {
                    downloadAppCallback.a();
                }
                MyLog.f("UpdateManager.startDownloadApp localDir:" + d2);
                return;
            }
            this.n = new WeakReference<>(downloadAppCallback);
            if (this.m == null) {
                final String str2 = d2 + File.separator + a(i2);
                long j3 = 0;
                File file = new File(str2);
                if (file.exists()) {
                    j3 = file.length();
                }
                File h2 = FileUtils.h(str2);
                if (h2 == null) {
                    FileUtils.d(str2);
                    if (downloadAppCallback != null) {
                        downloadAppCallback.a();
                        return;
                    }
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("Range", HttpRangeUtil.a(j3, -1));
                Request a2 = new Request.Builder().a("GET").a((Map<String, String>) hashMap).b(str).a();
                final long j4 = j2;
                this.m = new FileAsyncHandler(h2, false, true) {
                    public void onProgress(long j, long j2) {
                        DownloadAppCallback downloadAppCallback = (DownloadAppCallback) UpdateManager.this.n.get();
                        if (downloadAppCallback != null) {
                            float f = j4 > 0 ? ((float) j) / ((float) j4) : j2 > 0 ? ((float) j) / ((float) j2) : 0.0f;
                            if (f < 0.0f) {
                                f = 0.0f;
                            } else if (1.0f < f) {
                                f = 1.0f;
                            }
                            downloadAppCallback.a(f);
                        }
                    }

                    /* renamed from: a */
                    public void onSuccess(File file, Response response) {
                        DownloadAppCallback downloadAppCallback = (DownloadAppCallback) UpdateManager.this.n.get();
                        if (downloadAppCallback != null) {
                            downloadAppCallback.a(str2);
                        }
                        FileAsyncHandler unused = UpdateManager.this.m = null;
                    }

                    public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                        FileUtils.d(str2);
                        DownloadAppCallback downloadAppCallback = (DownloadAppCallback) UpdateManager.this.n.get();
                        if (downloadAppCallback != null) {
                            downloadAppCallback.a();
                        }
                        FileAsyncHandler unused = UpdateManager.this.m = null;
                    }
                };
                HttpAsyncHandle a3 = HttpApi.a(c(), a2, (AsyncHandler) this.m);
                if (downloadAppCallback != null) {
                    downloadAppCallback.a(a3);
                }
            } else if (downloadAppCallback != null) {
                downloadAppCallback.a((HttpAsyncHandle) null);
            }
        } else if (downloadAppCallback != null) {
            downloadAppCallback.a();
        }
    }

    class WorkerHandler extends Handler {
        WorkerHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (message.obj instanceof FirmwareUpdateOpInternal) {
                        FirmwareUpdateOpInternal firmwareUpdateOpInternal = (FirmwareUpdateOpInternal) message.obj;
                        UpdateManager.this.a(firmwareUpdateOpInternal.f17749a, firmwareUpdateOpInternal.b, firmwareUpdateOpInternal.c, firmwareUpdateOpInternal.d);
                        return;
                    }
                    return;
                case 2:
                    if (message.obj instanceof FirmwareUpdatePollOpInternal) {
                        FirmwareUpdatePollOpInternal firmwareUpdatePollOpInternal = (FirmwareUpdatePollOpInternal) message.obj;
                        UpdateManager.this.a(firmwareUpdatePollOpInternal.f17750a, firmwareUpdatePollOpInternal.b, firmwareUpdatePollOpInternal.c, firmwareUpdatePollOpInternal.d);
                        return;
                    }
                    return;
                case 3:
                    if (message.obj instanceof FirmwareUpdatePollTask) {
                        UpdateManager.this.a((FirmwareUpdatePollTask) message.obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private class FirmwareUpdateOpInternal {

        /* renamed from: a  reason: collision with root package name */
        String f17749a;
        int b;
        String c;
        FirmwareUpdateCallbackInternal d;

        private FirmwareUpdateOpInternal() {
        }
    }

    private class FirmwareUpdatePollOpInternal {

        /* renamed from: a  reason: collision with root package name */
        String f17750a;
        int b;
        String c;
        FirmwareUpdatePollCallbackInternal d;

        private FirmwareUpdatePollOpInternal() {
        }
    }

    private class FirmwareUpdatePollTask {

        /* renamed from: a  reason: collision with root package name */
        String f17751a;
        int b;
        String c;
        int d;
        boolean e;
        int f;
        FirmwareUpdatePollCallbackInternal g;

        private FirmwareUpdatePollTask() {
            this.f = 60;
        }
    }
}
