package com.xiaomi.smarthome.library.common.util;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.ServiceApplication;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.library.file.FileUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParcelStoreHelper<P extends Parcelable> {
    private static Map<String, List<?>> b = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    private final String f18692a = "ParcelStoreHelper";
    private String c = null;
    private String d;
    private Class<P> e;

    public void a(String str, List<P> list) {
        File file = new File(c(str));
        try {
            if (file.exists()) {
                file.delete();
            }
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileUtil.a(ServiceApplication.getAppContext(), Uri.fromFile(file), a(list));
            b.put(str, list);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(String str) {
        new File(c(str)).delete();
    }

    public List<P> b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (b.containsKey(str)) {
            return b.get(str);
        }
        File file = new File(c(str));
        if (!file.exists()) {
            return null;
        }
        List<P> a2 = a(file);
        b.put(str, a2);
        return a2;
    }

    private byte[] a(List<P> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        Parcel obtain = Parcel.obtain();
        obtain.writeInt(list.size());
        for (int i = 0; i < list.size(); i++) {
            Parcelable parcelable = (Parcelable) list.get(i);
            if (parcelable != null) {
                obtain.writeParcelable(parcelable, 0);
            }
        }
        return obtain.marshall();
    }

    private List<P> a(Parcel parcel) {
        if (parcel == null) {
            LogUtilGrey.a("ParcelStoreHelper", "parseParcel parcel is null");
            return new ArrayList();
        }
        int readInt = parcel.readInt();
        ArrayList arrayList = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            Parcelable readParcelable = parcel.readParcelable(this.e.getClassLoader());
            if (readParcelable != null) {
                arrayList.add(readParcelable);
            }
        }
        LogUtilGrey.a("ParcelStoreHelper", "parseParcel " + arrayList.size());
        return arrayList;
    }

    private List<P> a(File file) {
        try {
            byte[] d2 = FileUtil.d(ServiceApplication.getAppContext(), Uri.fromFile(file));
            if (d2 != null) {
                return a(FileUtils.a(d2));
            }
            LogUtilGrey.a("ParcelStoreHelper", "getLocalStoredDevicesFromParcelFile data is null");
            return new ArrayList();
        } catch (Exception e2) {
            LogUtilGrey.a("ParcelStoreHelper", "getLocalStoredDevicesFromParcelFile exception " + e2.getMessage());
            e2.printStackTrace();
            return new ArrayList();
        }
    }

    public ParcelStoreHelper(String str, String str2, Class<P> cls) {
        this.c = str2;
        this.d = str;
        this.e = cls;
    }

    private String c(String str) {
        return ServiceApplication.getAppContext().getFilesDir() + File.separator + this.d + File.separator + this.c + MD5.a(str);
    }
}
