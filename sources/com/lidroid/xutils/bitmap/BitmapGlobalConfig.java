package com.lidroid.xutils.bitmap;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import com.lidroid.xutils.bitmap.core.BitmapCache;
import com.lidroid.xutils.bitmap.download.DefaultDownloader;
import com.lidroid.xutils.bitmap.download.Downloader;
import com.lidroid.xutils.cache.FileNameGenerator;
import com.lidroid.xutils.task.Priority;
import com.lidroid.xutils.task.PriorityAsyncTask;
import com.lidroid.xutils.task.PriorityExecutor;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.util.OtherUtils;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoListActivity;
import java.util.HashMap;

public class BitmapGlobalConfig {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6292a = 2097152;
    public static final int b = 10485760;
    private static final int j = 5;
    private static final PriorityExecutor k = new PriorityExecutor(5);
    private static final PriorityExecutor l = new PriorityExecutor(2);
    private static final HashMap<String, BitmapGlobalConfig> s = new HashMap<>(1);
    private String c;
    private int d = 4194304;
    private int e = 52428800;
    private boolean f = true;
    private boolean g = true;
    private Downloader h;
    private BitmapCache i;
    private long m = CloudVideoListActivity.THIRTY_DAYS_MILLIS;
    private int n = 15000;
    private int o = 15000;
    private FileNameGenerator p;
    /* access modifiers changed from: private */
    public BitmapCacheListener q;
    private Context r;

    private BitmapGlobalConfig(Context context, String str) {
        if (context != null) {
            this.r = context;
            this.c = str;
            u();
            return;
        }
        throw new IllegalArgumentException("context may not be null");
    }

    public static synchronized BitmapGlobalConfig a(Context context, String str) {
        synchronized (BitmapGlobalConfig.class) {
            if (TextUtils.isEmpty(str)) {
                str = OtherUtils.a(context, "xBitmapCache");
            }
            if (s.containsKey(str)) {
                BitmapGlobalConfig bitmapGlobalConfig = s.get(str);
                return bitmapGlobalConfig;
            }
            BitmapGlobalConfig bitmapGlobalConfig2 = new BitmapGlobalConfig(context, str);
            s.put(str, bitmapGlobalConfig2);
            return bitmapGlobalConfig2;
        }
    }

    private void u() {
        new BitmapCacheManagementTask(this, (BitmapCacheManagementTask) null).e((Params[]) new Object[]{0});
        new BitmapCacheManagementTask(this, (BitmapCacheManagementTask) null).e((Params[]) new Object[]{1});
    }

    public String a() {
        return this.c;
    }

    public Downloader b() {
        if (this.h == null) {
            this.h = new DefaultDownloader();
        }
        this.h.a(this.r);
        this.h.a(c());
        this.h.a(d());
        this.h.b(e());
        return this.h;
    }

    public void a(Downloader downloader) {
        this.h = downloader;
    }

    public long c() {
        return this.m;
    }

    public void a(long j2) {
        this.m = j2;
    }

    public int d() {
        return this.n;
    }

    public void a(int i2) {
        this.n = i2;
    }

    public int e() {
        return this.o;
    }

    public void b(int i2) {
        this.o = i2;
    }

    public BitmapCache f() {
        if (this.i == null) {
            this.i = new BitmapCache(this);
        }
        return this.i;
    }

    public int g() {
        return this.d;
    }

    public void c(int i2) {
        if (i2 >= 2097152) {
            this.d = i2;
            if (this.i != null) {
                this.i.a(this.d);
                return;
            }
            return;
        }
        a(0.3f);
    }

    public void a(float f2) {
        if (f2 < 0.05f || f2 > 0.8f) {
            throw new IllegalArgumentException("percent must be between 0.05 and 0.8 (inclusive)");
        }
        this.d = Math.round(f2 * ((float) v()) * 1024.0f * 1024.0f);
        if (this.i != null) {
            this.i.a(this.d);
        }
    }

    public int h() {
        return this.e;
    }

    public void d(int i2) {
        if (i2 >= 10485760) {
            this.e = i2;
            if (this.i != null) {
                this.i.b(this.e);
            }
        }
    }

    public int i() {
        return k.a();
    }

    public void e(int i2) {
        k.a(i2);
    }

    public PriorityExecutor j() {
        return k;
    }

    public PriorityExecutor k() {
        return l;
    }

    public boolean l() {
        return this.f;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public boolean m() {
        return this.g;
    }

    public void b(boolean z) {
        this.g = z;
    }

    public FileNameGenerator n() {
        return this.p;
    }

    public void a(FileNameGenerator fileNameGenerator) {
        this.p = fileNameGenerator;
        if (this.i != null) {
            this.i.a(fileNameGenerator);
        }
    }

    public BitmapCacheListener o() {
        return this.q;
    }

    public void a(BitmapCacheListener bitmapCacheListener) {
        this.q = bitmapCacheListener;
    }

    private int v() {
        return ((ActivityManager) this.r.getSystemService("activity")).getMemoryClass();
    }

    private class BitmapCacheManagementTask extends PriorityAsyncTask<Object, Void, Object[]> {

        /* renamed from: a  reason: collision with root package name */
        public static final int f6293a = 0;
        public static final int b = 1;
        public static final int c = 2;
        public static final int d = 3;
        public static final int e = 4;
        public static final int f = 5;
        public static final int g = 6;
        public static final int h = 7;
        public static final int i = 8;
        public static final int j = 9;

        private BitmapCacheManagementTask() {
            a(Priority.UI_TOP);
        }

        /* synthetic */ BitmapCacheManagementTask(BitmapGlobalConfig bitmapGlobalConfig, BitmapCacheManagementTask bitmapCacheManagementTask) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Object[] c(Object... objArr) {
            BitmapCache f2;
            if (objArr == null || objArr.length == 0 || (f2 = BitmapGlobalConfig.this.f()) == null) {
                return objArr;
            }
            try {
                switch (objArr[0].intValue()) {
                    case 0:
                        f2.a();
                        break;
                    case 1:
                        f2.b();
                        break;
                    case 2:
                        f2.f();
                        break;
                    case 3:
                        f2.d();
                        f2.g();
                        break;
                    case 4:
                        f2.c();
                        break;
                    case 5:
                        f2.d();
                        break;
                    case 6:
                        f2.e();
                        break;
                    case 7:
                        if (objArr.length == 2) {
                            f2.b(String.valueOf(objArr[1]));
                            break;
                        } else {
                            return objArr;
                        }
                    case 8:
                        if (objArr.length == 2) {
                            f2.c(String.valueOf(objArr[1]));
                            break;
                        } else {
                            return objArr;
                        }
                    case 9:
                        if (objArr.length == 2) {
                            f2.d(String.valueOf(objArr[1]));
                            break;
                        } else {
                            return objArr;
                        }
                }
            } catch (Throwable th) {
                LogUtils.b(th.getMessage(), th);
            }
            return objArr;
        }

        /* access modifiers changed from: protected */
        /* renamed from: d */
        public void a(Object[] objArr) {
            if (BitmapGlobalConfig.this.q != null && objArr != null && objArr.length != 0) {
                try {
                    switch (objArr[0].intValue()) {
                        case 0:
                            BitmapGlobalConfig.this.q.a();
                            return;
                        case 1:
                            BitmapGlobalConfig.this.q.b();
                            return;
                        case 2:
                            BitmapGlobalConfig.this.q.f();
                            return;
                        case 3:
                            BitmapGlobalConfig.this.q.g();
                            return;
                        case 4:
                            BitmapGlobalConfig.this.q.c();
                            return;
                        case 5:
                            BitmapGlobalConfig.this.q.d();
                            return;
                        case 6:
                            BitmapGlobalConfig.this.q.e();
                            return;
                        case 7:
                            if (objArr.length == 2) {
                                BitmapGlobalConfig.this.q.a(String.valueOf(objArr[1]));
                                return;
                            }
                            return;
                        case 8:
                            if (objArr.length == 2) {
                                BitmapGlobalConfig.this.q.b(String.valueOf(objArr[1]));
                                return;
                            }
                            return;
                        case 9:
                            if (objArr.length == 2) {
                                BitmapGlobalConfig.this.q.c(String.valueOf(objArr[1]));
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                } catch (Throwable th) {
                    LogUtils.b(th.getMessage(), th);
                }
            }
        }
    }

    public void p() {
        new BitmapCacheManagementTask(this, (BitmapCacheManagementTask) null).e((Params[]) new Object[]{4});
    }

    public void q() {
        new BitmapCacheManagementTask(this, (BitmapCacheManagementTask) null).e((Params[]) new Object[]{5});
    }

    public void r() {
        new BitmapCacheManagementTask(this, (BitmapCacheManagementTask) null).e((Params[]) new Object[]{6});
    }

    public void a(String str) {
        new BitmapCacheManagementTask(this, (BitmapCacheManagementTask) null).e((Params[]) new Object[]{7, str});
    }

    public void b(String str) {
        new BitmapCacheManagementTask(this, (BitmapCacheManagementTask) null).e((Params[]) new Object[]{8, str});
    }

    public void c(String str) {
        new BitmapCacheManagementTask(this, (BitmapCacheManagementTask) null).e((Params[]) new Object[]{9, str});
    }

    public void s() {
        new BitmapCacheManagementTask(this, (BitmapCacheManagementTask) null).e((Params[]) new Object[]{2});
    }

    public void t() {
        new BitmapCacheManagementTask(this, (BitmapCacheManagementTask) null).e((Params[]) new Object[]{3});
    }
}
