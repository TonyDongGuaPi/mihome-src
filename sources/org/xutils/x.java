package org.xutils;

import android.app.Application;
import android.content.Context;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.xutils.DbManager;
import org.xutils.common.TaskController;
import org.xutils.common.task.TaskControllerImpl;
import org.xutils.db.DbManagerImpl;
import org.xutils.http.HttpManagerImpl;
import org.xutils.image.ImageManagerImpl;
import org.xutils.view.ViewInjectorImpl;

public final class x {
    private x() {
    }

    public static boolean a() {
        return Ext.f4251a;
    }

    public static Application b() {
        if (Ext.b == null) {
            try {
                Application unused = Ext.b = new MockApplication((Context) Class.forName("com.android.layoutlib.bridge.impl.RenderAction").getDeclaredMethod("getCurrentContext", new Class[0]).invoke((Object) null, new Object[0]));
            } catch (Throwable unused2) {
                throw new RuntimeException("please invoke x.Ext.init(app) on Application#onCreate() and register your Application in manifest.");
            }
        }
        return Ext.b;
    }

    public static TaskController c() {
        return Ext.c;
    }

    public static HttpManager d() {
        if (Ext.d == null) {
            HttpManagerImpl.a();
        }
        return Ext.d;
    }

    public static ImageManager e() {
        if (Ext.e == null) {
            ImageManagerImpl.c();
        }
        return Ext.e;
    }

    public static ViewInjector f() {
        if (Ext.f == null) {
            ViewInjectorImpl.a();
        }
        return Ext.f;
    }

    public static DbManager a(DbManager.DaoConfig daoConfig) {
        return DbManagerImpl.a(daoConfig);
    }

    public static class Ext {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static boolean f4251a;
        /* access modifiers changed from: private */
        public static Application b;
        /* access modifiers changed from: private */
        public static TaskController c;
        /* access modifiers changed from: private */
        public static HttpManager d;
        /* access modifiers changed from: private */
        public static ImageManager e;
        /* access modifiers changed from: private */
        public static ViewInjector f;

        private Ext() {
        }

        static {
            TaskControllerImpl.a();
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            });
        }

        public static void a(Application application) {
            if (b == null) {
                b = application;
            }
        }

        public static void a(boolean z) {
            f4251a = z;
        }

        public static void a(TaskController taskController) {
            if (c == null) {
                c = taskController;
            }
        }

        public static void a(HttpManager httpManager) {
            d = httpManager;
        }

        public static void a(ImageManager imageManager) {
            e = imageManager;
        }

        public static void a(ViewInjector viewInjector) {
            f = viewInjector;
        }
    }

    private static class MockApplication extends Application {
        public MockApplication(Context context) {
            attachBaseContext(context);
        }
    }
}
