package com.xiaomi.pluginhost;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.mobikwik.sdk.lib.utils.PaymentOptionsDecoder;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.plugin.IXmPluginMessageReceiver;
import com.xiaomi.plugin.PackageRawInfo;
import com.xiaomi.plugin.XmPluginPackage;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public class PluginRuntimeManager {

    /* renamed from: a  reason: collision with root package name */
    static XmPluginPackage f12599a = null;
    private static final Object b = new Object();
    private static final String c = "PluginRuntimeManager";
    private static PluginRuntimeManager d = null;
    private static final String i = "MiHomeDeveloperId";
    private final ConcurrentHashMap<String, XmPluginPackage> e = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, XmPluginPackage> f = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, XmPluginPackage> g = new ConcurrentHashMap<>();
    private Context h = PluginSettings.f;

    public static void a(XmPluginPackage xmPluginPackage) {
    }

    private PluginRuntimeManager() {
    }

    public static PluginRuntimeManager a() {
        if (d == null) {
            synchronized (b) {
                if (d == null) {
                    d = new PluginRuntimeManager();
                }
            }
        }
        return d;
    }

    /* access modifiers changed from: package-private */
    public boolean a(Intent intent, String str) {
        if (intent != null) {
            if (intent.getBooleanExtra("isSingleTask", false)) {
                return true;
            }
            String stringExtra = intent.getStringExtra("url");
            return !TextUtils.isEmpty(stringExtra) && (stringExtra.contains(UrlConstants.cart) || stringExtra.contains(UrlConstants.orderdetail));
        }
    }

    public Class b(Intent intent, String str) {
        if (a(intent, str)) {
            return PluginHostSingleTaskActivity.class;
        }
        if (intent == null || !intent.getBooleanExtra("enableTransParent", false)) {
            return PluginHostActivityMain.class;
        }
        return PluginHostTransparentActivity.class;
    }

    private static void a(Resources resources, Locale locale) {
        if (resources != null) {
            LogUtils.d("LanguageUtil", "applyLanguage:" + locale.toString());
            Configuration configuration = resources.getConfiguration();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            configuration.locale = locale;
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    public XmPluginPackage a(String str) {
        return this.e.get(str);
    }

    public XmPluginPackage b(String str) {
        return this.f.get(str);
    }

    public void a(Locale locale) {
        for (XmPluginPackage resources : this.f.values()) {
            a(resources.getResources(), locale);
        }
    }

    public boolean a(Context context, String str, PackageRawInfo packageRawInfo, int i2, Intent intent) {
        XmPluginPackage a2 = a(str, packageRawInfo);
        if (a2 == null || a2.getMessageReceiver() == null) {
            return false;
        }
        return a2.getMessageReceiver().handleMessage(context, a2, i2, intent);
    }

    public static PackageRawInfo a(Context context, String str) {
        Bundle bundle;
        PackageRawInfo packageRawInfo = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 128);
        if (packageArchiveInfo != null) {
            packageRawInfo = new PackageRawInfo();
            packageRawInfo.mVersion = packageArchiveInfo.versionCode;
            packageRawInfo.mVersionName = packageArchiveInfo.versionName;
            packageRawInfo.mPackageName = packageArchiveInfo.packageName;
            ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
            if (!(applicationInfo == null || (bundle = applicationInfo.metaData) == null)) {
                packageRawInfo.mUrlList = Arrays.asList(bundle.getString("urls", "").split(PaymentOptionsDecoder.pipeSeparator));
                packageRawInfo.mMinApiLevel = bundle.getInt("minPluginSdkApiVersion", 0);
                packageRawInfo.mDeveloperId = a(bundle);
                packageRawInfo.mPlatform = bundle.getString("MiHomePlatform", "");
                packageRawInfo.mMessageHandleName = bundle.getString("message_handler", "");
            }
        }
        return packageRawInfo;
    }

    private static long a(Bundle bundle) {
        if (bundle == null) {
            return 0;
        }
        try {
            String string = bundle.getString(i, "");
            return Long.parseLong(string.substring("id_".length(), string.length()));
        } catch (Exception unused) {
            return 0;
        }
    }

    public XmPluginPackage a(String str, PackageRawInfo packageRawInfo) {
        IXmPluginMessageReceiver iXmPluginMessageReceiver;
        XmPluginPackage xmPluginPackage = this.f.get(str);
        if (xmPluginPackage != null) {
            return xmPluginPackage;
        }
        if (packageRawInfo == null) {
            packageRawInfo = a(this.h, str);
        }
        if (packageRawInfo == null) {
            return null;
        }
        if (packageRawInfo.mPackageId <= 0) {
            String[] split = str.split(File.separator);
            try {
                packageRawInfo.mPackageId = Integer.valueOf(split[split.length - 1]).intValue();
            } catch (Exception unused) {
            }
        }
        DexClassLoader a2 = a(packageRawInfo, str);
        AssetManager d2 = d(str);
        Resources a3 = a(d2);
        if (!TextUtils.isEmpty(packageRawInfo.mMessageHandleName)) {
            try {
                iXmPluginMessageReceiver = (IXmPluginMessageReceiver) a2.loadClass(packageRawInfo.mMessageHandleName).getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Exception e2) {
                LogUtils.e(c, "load apk", e2);
                return null;
            }
        } else {
            iXmPluginMessageReceiver = null;
        }
        XmPluginPackage xmPluginPackage2 = new XmPluginPackage(str, packageRawInfo, a2, d2, a3, iXmPluginMessageReceiver);
        for (String put : packageRawInfo.mUrlList) {
            this.e.put(put, xmPluginPackage2);
        }
        this.f.put(str, xmPluginPackage2);
        this.g.put(packageRawInfo.mPackageName, xmPluginPackage2);
        return xmPluginPackage2;
    }

    private DexClassLoader a(PackageRawInfo packageRawInfo, String str) {
        String a2 = PluginSettings.a(this.h, packageRawInfo);
        FileUtils.i(a2);
        return new DexClassLoader(str, a2, (String) null, this.h.getClassLoader());
    }

    private AssetManager d(String str) {
        try {
            AssetManager newInstance = AssetManager.class.newInstance();
            newInstance.getClass().getMethod("addAssetPath", new Class[]{String.class}).invoke(newInstance, new Object[]{str});
            return newInstance;
        } catch (Exception unused) {
            return null;
        }
    }

    public XmPluginPackage c(String str) {
        return this.g.get(str);
    }

    public void a(Context context, long j, long j2) {
        if (PluginSettings.b(j2)) {
            File dir = context.getDir(ShareConstants.q, 0);
            new File(dir.getAbsolutePath() + File.separator + "plugin" + File.separator + j + File.separator + j2 + ShareConstants.w).delete();
        }
    }

    private Resources a(AssetManager assetManager) {
        Resources resources = this.h.getResources();
        return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
    }

    public XmPluginPackage a(List<String> list) {
        boolean z;
        if (list == null || list.size() <= 0) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (String next : list) {
                if (!next.startsWith("android") && !next.startsWith("dalvik") && !next.startsWith("java") && !next.startsWith("javax")) {
                    if (!next.startsWith("com.xiaomi.smarthome")) {
                        String[] split = next.split("\\.");
                        if (split.length >= 3) {
                            arrayList.add(split[0] + "." + split[1] + "." + split[2]);
                        }
                        if (split.length >= 4) {
                            arrayList.add(split[0] + "." + split[1] + "." + split[2] + "." + split[3]);
                        }
                        if (split.length >= 5) {
                            arrayList.add(split[0] + "." + split[1] + "." + split[2] + "." + split[3] + "." + split[4]);
                        }
                    }
                }
            }
            if (arrayList.size() <= 0) {
                return null;
            }
            XmPluginPackage xmPluginPackage = null;
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                XmPluginPackage xmPluginPackage2 = this.f.get(Integer.valueOf(i2));
                String str = xmPluginPackage2.packageRawInfo.mPackageName;
                if (!TextUtils.isEmpty(str)) {
                    int size = arrayList.size();
                    int i3 = 0;
                    while (true) {
                        if (i3 >= size) {
                            xmPluginPackage2 = xmPluginPackage;
                            z = false;
                            break;
                        } else if (str.equalsIgnoreCase((String) arrayList.get(i3))) {
                            z = true;
                            break;
                        } else {
                            i3++;
                        }
                    }
                    if (z) {
                        return xmPluginPackage2;
                    }
                    xmPluginPackage = xmPluginPackage2;
                }
            }
            return xmPluginPackage;
        } catch (Exception unused) {
            return null;
        }
    }
}
