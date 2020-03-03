package com.xiaomi.jr.appbase.configuration;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.xiaomi.jr.appbase.AppManager;
import com.xiaomi.jr.common.utils.AppUtils;
import com.xiaomi.jr.common.utils.Client;
import com.xiaomi.jr.common.utils.FileUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.http.model.MiFiResponse;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.regex.Pattern;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfigurationManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1403a = "ConfigurationManager";
    private static final String b = "default_config.json";
    private static final String c = "configuration_debug.json";
    /* access modifiers changed from: private */
    public static String d;
    private static final Pattern e = Pattern.compile("^cached_configuration_\\d+\\.json$");
    private static boolean f;
    private static volatile ConfigurationManager j;
    /* access modifiers changed from: private */
    public Configuration g;
    /* access modifiers changed from: private */
    public Handler h = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public Context i;
    /* access modifiers changed from: private */
    public Call<MiFiResponse<Configuration>> k;
    /* access modifiers changed from: private */
    public WeakReference<RequestConfigurationListener> l = new WeakReference<>((Object) null);
    /* access modifiers changed from: private */
    public Runnable m = new Runnable() {
        public void run() {
            MifiLog.b(ConfigurationManager.f1403a, "requestConfigurationTask timeout!");
            if (ConfigurationManager.this.k != null && !ConfigurationManager.this.k.isCanceled()) {
                ConfigurationManager.this.k.cancel();
            }
            if (ConfigurationManager.this.i != null) {
                Configuration unused = ConfigurationManager.this.g = ConfigurationManager.this.f(ConfigurationManager.this.i);
            }
            if (ConfigurationManager.this.l != null && ConfigurationManager.this.l.get() != null) {
                ((RequestConfigurationListener) ConfigurationManager.this.l.get()).c();
            }
        }
    };

    public interface RequestConfigurationListener {
        void a();

        void b();

        void c();
    }

    private ConfigurationManager(Context context) {
        this.i = context.getApplicationContext();
        boolean z = true;
        d = String.format(Locale.getDefault(), "cached_configuration_%d.json", new Object[]{Integer.valueOf(AppUtils.f(context))});
        f = (!MifiLog.f1417a || !new File(context.getFilesDir(), c).exists()) ? false : z;
    }

    public static ConfigurationManager a(Context context) {
        if (j == null) {
            synchronized (ConfigurationManager.class) {
                if (j == null) {
                    j = new ConfigurationManager(context);
                }
            }
        }
        return j;
    }

    public Configuration a() {
        if (this.g == null && this.i != null) {
            c(this.i);
        }
        return this.g;
    }

    public void a(Configuration configuration) {
        this.g = configuration;
    }

    public void a(final Context context, RequestConfigurationListener requestConfigurationListener, long j2) {
        this.l = new WeakReference<>(requestConfigurationListener);
        if (!f) {
            String g2 = AppUtils.g(context);
            if (g2 == null || !g2.endsWith("_update")) {
                if (this.k != null && !this.k.isCanceled()) {
                    this.k.cancel();
                }
                this.k = AppManager.a().a(context.getPackageName(), Client.c(context));
                this.k.enqueue(new Callback<MiFiResponse<Configuration>>() {
                    public void onResponse(Call<MiFiResponse<Configuration>> call, Response<MiFiResponse<Configuration>> response) {
                        Configuration configuration;
                        ConfigurationManager.this.h.removeCallbacks(ConfigurationManager.this.m);
                        if (response == null || !response.isSuccessful() || response.body() == null || (configuration = (Configuration) response.body().d()) == null || configuration.a() == null) {
                            Configuration unused = ConfigurationManager.this.g = ConfigurationManager.this.f(context);
                            if (ConfigurationManager.this.l != null && ConfigurationManager.this.l.get() != null) {
                                ((RequestConfigurationListener) ConfigurationManager.this.l.get()).b();
                                return;
                            }
                            return;
                        }
                        Configuration unused2 = ConfigurationManager.this.g = configuration;
                        if (!(ConfigurationManager.this.l == null || ConfigurationManager.this.l.get() == null)) {
                            ((RequestConfigurationListener) ConfigurationManager.this.l.get()).a();
                        }
                        boolean unused3 = ConfigurationManager.b(context, ConfigurationManager.d, configuration.a());
                    }

                    public void onFailure(Call<MiFiResponse<Configuration>> call, Throwable th) {
                        MifiLog.b(ConfigurationManager.f1403a, "fetch cloud configuration onFail: " + th.getMessage() + ", fallback to cached configuration.");
                        ConfigurationManager.this.h.removeCallbacks(ConfigurationManager.this.m);
                        Configuration unused = ConfigurationManager.this.g = ConfigurationManager.this.f(context);
                        if (ConfigurationManager.this.l != null && ConfigurationManager.this.l.get() != null) {
                            ((RequestConfigurationListener) ConfigurationManager.this.l.get()).b();
                        }
                    }
                });
                if (j2 > 0) {
                    this.h.postDelayed(this.m, j2);
                }
            } else if (requestConfigurationListener != null) {
                this.g = f(context);
                requestConfigurationListener.a();
            }
        } else if (requestConfigurationListener != null) {
            this.g = b(context);
            requestConfigurationListener.a();
        }
    }

    private Configuration a(Context context, String str) {
        return (Configuration) Configuration.d.fromJson(c(context, str), Configuration.class);
    }

    private Configuration b(Context context) {
        MifiLog.b(f1403a, "load debug configuration.");
        return a(context, c);
    }

    private void c(Context context) {
        if (this.g == null) {
            if (f) {
                this.g = b(context);
                return;
            }
            Configuration d2 = d(context);
            if (d2 == null) {
                d2 = f(context);
            }
            this.g = d2;
        }
    }

    private Configuration d(Context context) {
        String a2 = FileUtils.a(context, d);
        if (a2 == null || !new File(a2).exists()) {
            e(context);
            return null;
        }
        return (Configuration) Configuration.d.fromJson(c(context, d), Configuration.class);
    }

    private void e(Context context) {
        File[] listFiles = context.getFilesDir().listFiles($$Lambda$ConfigurationManager$AkH3fOHpaEPr9O0D7Hh23Qz3yk.INSTANCE);
        if (listFiles != null) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean a(File file, String str) {
        return !TextUtils.equals(str, d) && e.matcher(str).find();
    }

    /* access modifiers changed from: private */
    public Configuration f(Context context) {
        return b(context, b);
    }

    private Configuration b(Context context, String str) {
        return (Configuration) Configuration.d.fromJson(FileUtils.b(context, str), Configuration.class);
    }

    /* access modifiers changed from: private */
    public static boolean b(Context context, String str, String str2) {
        String a2 = FileUtils.a(context);
        if (a2 == null) {
            return false;
        }
        return FileUtils.b(a2 + File.separator + str, str2);
    }

    private static String c(Context context, String str) {
        String a2 = FileUtils.a(context);
        if (a2 == null) {
            return null;
        }
        return FileUtils.c(a2 + File.separator + str);
    }
}
