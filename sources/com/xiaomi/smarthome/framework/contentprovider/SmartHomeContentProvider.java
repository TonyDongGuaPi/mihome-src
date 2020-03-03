package com.xiaomi.smarthome.framework.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.miio.device.PhoneIRDevice;
import java.util.List;

public class SmartHomeContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.xiaomi.smarthome.ext_cp";
    public static final String BUNDLE_KEY_COUNT = "count";
    public static final String METHOD_KEY_DEVICES_COUNT_ONLINE = "online_devices_count";
    public static final String PATH_ONLINE_DEVICE_COUNT = "online_devices_count";

    /* renamed from: a  reason: collision with root package name */
    private static final String f16478a = "SmartHomeContentProvider";
    private static final int b = 1;
    private static final UriMatcher c = new UriMatcher(-1);
    private static final String d = "com.xiaomi.smarthome.ext_cp_sp";
    private static Runnable e = new Runnable() {
        public void run() {
            SHApplication.getAppContext().getContentResolver().notifyChange(Uri.parse("content://com.xiaomi.smarthome.ext_cp/online_devices_count"), (ContentObserver) null);
        }
    };

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Nullable
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return false;
    }

    @Nullable
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    static {
        c.addURI(AUTHORITY, "online_devices_count", 1);
    }

    @Nullable
    public Bundle call(String str, String str2, Bundle bundle) {
        if (!TextUtils.equals("online_devices_count", str)) {
            return null;
        }
        if (!CoreApi.a().l()) {
            Log.d(f16478a, "core not ready");
            return null;
        } else if (!CoreApi.a().q()) {
            Log.d(f16478a, "not login");
            return null;
        } else {
            int a2 = a();
            if (a2 == 0 && (a2 = SHApplication.getAppContext().getSharedPreferences(d, 0).getInt("online_devices_count", 0)) > 0) {
                String str3 = f16478a;
                Log.d(str3, "read from cache " + a2);
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString("count", a(a2));
            return bundle2;
        }
    }

    private int a() {
        List<Device> d2;
        if (!CoreApi.a().q() || (d2 = SmartHomeDeviceManager.a().d()) == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < d2.size(); i2++) {
            Device device = d2.get(i2);
            if (device.pid != Device.PID_VIRTUAL_DEVICE && ((!device.isSubDevice() || !device.isShowMainList()) && !(device instanceof PhoneIRDevice) && device.isOnline)) {
                i++;
            }
        }
        List<Device> k = SmartHomeDeviceManager.a().k();
        if (k == null) {
            return i;
        }
        for (int i3 = 0; i3 < k.size(); i3++) {
            if (k.get(i3).isOnline) {
                i++;
            }
        }
        return i;
    }

    private static String a(int i) {
        if (i <= 0) {
            return "";
        }
        Context appContext = SHApplication.getAppContext();
        if (CoreApi.a().I() == null) {
            return appContext.getResources().getQuantityString(R.plurals.miui_online_device_count, i, new Object[]{Integer.valueOf(i)});
        }
        appContext.getResources();
        Configuration configuration = Resources.getSystem().getConfiguration();
        return new Resources(appContext.getAssets(), new DisplayMetrics(), configuration).getQuantityString(R.plurals.miui_online_device_count, i, new Object[]{Integer.valueOf(i)});
    }

    public static void notifyChange(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            b();
            if (c.match(Uri.parse("content://com.xiaomi.smarthome.ext_cp/" + str)) == 1) {
                SHApplication.getGlobalWorkerHandler().removeCallbacks(e);
                SHApplication.getGlobalWorkerHandler().postDelayed(e, 2000);
            }
        }
    }

    private static void b() {
        int i;
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        List<Device> k = SmartHomeDeviceManager.a().k();
        if (d2 == null) {
            i = 0;
        } else {
            i = d2.size();
        }
        SHApplication.getAppContext().getSharedPreferences(d, 0).edit().putInt("online_devices_count", i + (k == null ? 0 : k.size())).apply();
    }
}
