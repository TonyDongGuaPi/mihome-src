package com.xiaomi.smarthome.core.server.internal.device;

import android.net.Uri;
import android.os.Parcel;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.ServiceApplication;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.frame.log.CoreLogUtilGrey;
import com.xiaomi.smarthome.library.common.util.MD5;
import com.xiaomi.smarthome.library.file.FileUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DeviceManagerStoreHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14525a = "DeviceManagerStoreHelper";
    private static final String b = "device_list_";

    public static void a(String str, List<Device> list) {
        File file = new File(d(str));
        try {
            if (file.exists()) {
                file.delete();
            }
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileUtil.a(ServiceApplication.getAppContext(), Uri.fromFile(file), a(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(String str) {
        new File(d(str)).delete();
    }

    public static List<Device> b(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        File file = new File(d(str));
        if (!file.exists()) {
            return c(str);
        }
        return a(file);
    }

    private static byte[] a(List<Device> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        Parcel obtain = Parcel.obtain();
        obtain.writeInt(list.size());
        for (int i = 0; i < list.size(); i++) {
            Device device = list.get(i);
            if (device != null) {
                obtain.writeParcelable(device, 0);
            }
        }
        return obtain.marshall();
    }

    private static List<Device> a(Parcel parcel) {
        if (parcel == null) {
            CoreLogUtilGrey.a(f14525a, "parseParcel parcel is null");
            return new ArrayList();
        }
        int readInt = parcel.readInt();
        ArrayList arrayList = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            Device device = (Device) parcel.readParcelable(Device.class.getClassLoader());
            if (device != null) {
                arrayList.add(device);
            }
        }
        CoreLogUtilGrey.a(f14525a, "parseParcel " + arrayList.size());
        return arrayList;
    }

    private static List<Device> a(File file) {
        try {
            byte[] d = FileUtil.d(ServiceApplication.getAppContext(), Uri.fromFile(file));
            if (d != null) {
                return a(FileUtils.a(d));
            }
            CoreLogUtilGrey.a(f14525a, "getLocalStoredDevicesFromParcelFile data is null");
            return new ArrayList();
        } catch (Exception e) {
            CoreLogUtilGrey.a(f14525a, "getLocalStoredDevicesFromParcelFile exception " + e.getMessage());
            e.printStackTrace();
            return new ArrayList();
        }
    }

    private static List<Device> c(String str) {
        List<DeviceRecord> queryAllByUserId = DeviceRecord.queryAllByUserId(AccountManager.a().m());
        ArrayList arrayList = new ArrayList(queryAllByUserId.size());
        for (DeviceRecord a2 : queryAllByUserId) {
            Device a3 = DeviceFactory.a(a2);
            if (a3 != null) {
                arrayList.add(a3);
            }
        }
        CoreLogUtilGrey.a(f14525a, "getLocalStoredDevicesFromDB " + arrayList.size());
        return arrayList;
    }

    private static String d(String str) {
        return ServiceApplication.getAppContext().getFilesDir() + File.separator + "device" + File.separator + "cache" + File.separator + b + MD5.a(str);
    }
}
