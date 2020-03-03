package com.mi.global.shop.util;

import android.content.Context;
import android.util.Log;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.util.Constants;
import com.mi.global.shop.util.Utils;
import java.io.File;

public class WebResourceManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7121a = "com.mi.global.shop.util.WebResourceManager";
    private static WebResourceManager b;

    private WebResourceManager() {
    }

    public static synchronized WebResourceManager a() {
        WebResourceManager webResourceManager;
        synchronized (WebResourceManager.class) {
            if (b == null) {
                b = new WebResourceManager();
            }
            webResourceManager = b;
        }
        return webResourceManager;
    }

    public void a(Context context) {
        File file = null;
        try {
            Log.d(f7121a, "Start to update web view resources");
            File file2 = new File(Constants.WebViewRes.b(context));
            try {
                if (!a(file2)) {
                    if (!Utils.Files.b(file2) && ShopApp.f1346a) {
                        Log.e(f7121a, "Delete temp web view resources fails");
                    }
                } else if (!a(context, false)) {
                    if (!Utils.Files.b(file2) && ShopApp.f1346a) {
                        Log.e(f7121a, "Delete temp web view resources fails");
                    }
                } else if (!c(context)) {
                    if (ShopApp.f1346a) {
                        Log.e(f7121a, "Copy zip file failed");
                    }
                    if (!Utils.Files.b(file2) && ShopApp.f1346a) {
                        Log.e(f7121a, "Delete temp web view resources fails");
                    }
                } else if (!d(context)) {
                    if (ShopApp.f1346a) {
                        Log.e(f7121a, "Extract package files fails");
                    }
                    if (!Utils.Files.b(file2) && ShopApp.f1346a) {
                        Log.e(f7121a, "Delete temp web view resources fails");
                    }
                } else {
                    b(context);
                    if (Utils.Files.b(file2) || !ShopApp.f1346a) {
                        return;
                    }
                    Log.e(f7121a, "Delete temp web view resources fails");
                }
            } catch (Exception e) {
                e = e;
                file = file2;
                try {
                    e.printStackTrace();
                } catch (Throwable th) {
                    th = th;
                    file2 = file;
                    if (!Utils.Files.b(file2) && ShopApp.f1346a) {
                        Log.e(f7121a, "Delete temp web view resources fails");
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                Log.e(f7121a, "Delete temp web view resources fails");
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            if (Utils.Files.b(file) || !ShopApp.f1346a) {
                return;
            }
            Log.e(f7121a, "Delete temp web view resources fails");
        }
    }

    public void b(Context context) {
        if (ShopApp.f1346a) {
            Log.d(f7121a, "start cleanup process");
        }
        b(new File(Constants.WebViewRes.b(context)));
        b(new File(Constants.WebViewRes.d(context)));
    }

    private boolean c(Context context) {
        return Utils.Files.a(Constants.WebViewRes.c(context), Constants.WebViewRes.d(context));
    }

    private boolean a(File file) {
        if (file.exists() || file.mkdirs()) {
            return true;
        }
        if (!ShopApp.f1346a) {
            return false;
        }
        String str = f7121a;
        Log.e(str, "create " + file.getName() + " failed");
        return false;
    }

    private boolean a(Context context, boolean z) {
        return Utils.Files.a(context, ConnectionHelper.aA(), Constants.WebViewRes.c(context), z);
    }

    private boolean d(Context context) {
        return Utils.Files.b(Constants.WebViewRes.d(context), Constants.WebViewRes.a(context));
    }

    private void b(File file) {
        if (file.isFile()) {
            if (ShopApp.f1346a) {
                Log.d(f7121a, file + " is useless, delete");
            }
            file.delete();
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File b2 : listFiles) {
                    b(b2);
                }
            }
            if (file.list().length == 0) {
                if (ShopApp.f1346a) {
                    Log.d(f7121a, file + " is empty, delete");
                }
                file.delete();
            }
        }
    }
}
