package com.mics.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.mics.core.business.ChatExecutors;
import com.mics.core.business.ChatManager;
import com.mics.core.data.business.ChatParams;
import com.mics.core.ui.page.ChatActivity;
import com.mics.exception.InitializationException;
import com.mics.network.GoHttp;
import com.mics.util.AppUtils;
import com.mics.util.GsonUtil;
import com.mics.util.Logger;
import com.mics.util.Preferences;
import com.mics.widget.reminder.MessageReminder;
import com.mics.widget.reminder.ReminderResource;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;

public class MiCS {

    /* renamed from: a  reason: collision with root package name */
    private static volatile MiCS f7624a;
    private static boolean b;
    private WeakReference<Context> c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private int l;
    private boolean m;
    private long[] n;
    private OnMessageReadListener o;
    private List<UrlDispatchInterceptor> p;
    private GalleryDispatchInterceptor q;

    public interface GalleryDispatchInterceptor {
        void a(Activity activity, int i);

        void a(List<String> list, int i, int i2, int i3, int i4, int i5);

        String[] a(Intent intent);
    }

    public interface OnMessageReadListener {
        void onMessageRead(Object... objArr);
    }

    public interface UrlDispatchInterceptor {
        void openUrl(String str);
    }

    private MiCS() {
    }

    public static MiCS a() {
        if (f7624a == null) {
            synchronized (MiCS.class) {
                if (f7624a == null) {
                    f7624a = new MiCS();
                }
            }
        }
        return f7624a;
    }

    public static boolean b() {
        return f7624a != null;
    }

    public static synchronized void a(Context context, Config config) {
        synchronized (MiCS.class) {
            b = config.j;
            Logger.a(b);
            Logger.a(b ? 3 : 5);
            r();
            MiCS a2 = a();
            a2.c = new WeakReference<>(context);
            a2.d = config.f;
            a2.e = config.g;
            a2.f = config.h != null ? config.h : AppUtils.a(context);
            a2.g = config.i != null ? config.i : AppUtils.b(context);
            a2.h = config.e != null ? config.e : AppUtils.d(context);
            a2.i = config.f7626a;
            a2.j = config.b;
            a2.k = config.c;
            a2.l = config.d;
            GoHttp.b();
            ReminderResource.a(context);
            MessageReminder.b();
        }
    }

    public static void a(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            GoHttp.b();
        } else {
            GoHttp.a(okHttpClient);
        }
    }

    public void a(String str, String str2, String str3, int i2) {
        this.i = str;
        this.j = str2;
        this.k = str3;
        this.l = i2;
    }

    public void a(boolean z) {
        this.m = z;
        Preferences.b("mics-vibrate-enable", z);
    }

    public void a(long[] jArr) {
        this.n = jArr;
    }

    public boolean c() {
        this.m = Preferences.d("mics-vibrate-enable");
        return this.m;
    }

    public long[] d() {
        return this.n;
    }

    public static boolean e() {
        return b;
    }

    public static void a(final Context context, final ChatParams chatParams) {
        if (MessageReminder.a().b(context)) {
            ChatExecutors.d().a(new Runnable() {
                public void run() {
                    MiCS.c(context, chatParams);
                }
            }, 400);
        } else {
            c(context, chatParams);
        }
    }

    /* access modifiers changed from: private */
    public static void c(Context context, ChatParams chatParams) {
        if (a(chatParams)) {
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra(UrlConstants.customerService, GsonUtil.a(chatParams));
            context.startActivity(intent);
            Logger.b("【%s】启动 ChatActivity", context.getClass().getName());
        }
    }

    private static boolean a(ChatParams chatParams) {
        String merchantId = chatParams.getMerchantId();
        if (MessageReminder.a().a(merchantId)) {
            Logger.c("isTargetActivityTop", new Object[0]);
            return false;
        } else if (ChatManager.a().c(merchantId)) {
            Logger.c("launchedChat", new Object[0]);
            return false;
        } else {
            ChatManager.a().b(merchantId);
            Logger.a("launch Add", new Object[0]);
            ChatManager.a().a(merchantId);
            return true;
        }
    }

    public synchronized void a(UrlDispatchInterceptor urlDispatchInterceptor) {
        if (this.p == null) {
            this.p = new ArrayList();
        }
        this.p.add(urlDispatchInterceptor);
    }

    public void a(GalleryDispatchInterceptor galleryDispatchInterceptor) {
        this.q = galleryDispatchInterceptor;
    }

    public GalleryDispatchInterceptor f() {
        return this.q;
    }

    public void a(OnMessageReadListener onMessageReadListener) {
        this.o = onMessageReadListener;
    }

    public OnMessageReadListener g() {
        return this.o;
    }

    public void a(String str) {
        if (this.p != null) {
            for (UrlDispatchInterceptor openUrl : this.p) {
                openUrl.openUrl(str);
            }
        }
    }

    private static void r() {
        if (f7624a == null) {
            return;
        }
        if (!b) {
            Logger.d("MiCS 在init之前执行了其他方法，或者是多次执行了init方法！！！", new Object[0]);
            return;
        }
        throw new InitializationException("MiCS 在init之前执行了其他方法，或者是多次执行了init方法！！！");
    }

    public Context h() {
        if (this.c == null) {
            return null;
        }
        return (Context) this.c.get();
    }

    public String i() {
        return this.d;
    }

    public String j() {
        return this.e;
    }

    public String k() {
        return this.f;
    }

    public String l() {
        return this.g;
    }

    public String m() {
        return this.h;
    }

    public String n() {
        return this.i;
    }

    public String o() {
        return this.j;
    }

    public String p() {
        return this.k;
    }

    public int q() {
        return this.l;
    }

    public static class Config {

        /* renamed from: a  reason: collision with root package name */
        String f7626a;
        String b;
        String c;
        int d;
        String e;
        String f;
        String g;
        String h;
        String i;
        boolean j;

        public static class Builder extends Config {
            public Builder a(String str) {
                this.e = str;
                return this;
            }

            public Builder b(String str) {
                this.f7626a = str;
                return this;
            }

            public Builder c(String str) {
                this.b = str;
                return this;
            }

            public Builder d(String str) {
                this.c = str;
                return this;
            }

            public Builder a(int i) {
                this.d = i;
                return this;
            }

            public Builder e(String str) {
                this.f = str;
                return this;
            }

            public Builder f(String str) {
                this.g = str;
                return this;
            }

            public Builder g(String str) {
                this.h = str;
                return this;
            }

            public Builder h(String str) {
                this.i = str;
                return this;
            }

            public Builder a(boolean z) {
                this.j = z;
                return this;
            }

            public Config a() {
                Config config = new Config();
                config.f7626a = this.f7626a;
                config.b = this.b;
                config.c = this.c;
                config.d = this.d;
                config.e = this.e;
                config.f = this.f;
                config.g = this.g;
                config.h = this.h;
                config.i = this.i;
                config.j = this.j;
                return config;
            }
        }
    }
}
