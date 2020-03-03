package com.xiaomi.smarthome.miio.areainfo;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.api.model.AreaPropInfo;
import com.xiaomi.smarthome.framework.location.SHLocationManager;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.util.List;

public class AreaInfo {
    public AreaPropInfo A() {
        return null;
    }

    public int a(int i) {
        return 0;
    }

    public void a(Context context) {
    }

    /* access modifiers changed from: protected */
    public void a(Context context, Address address, Location location, boolean z, boolean z2) {
    }

    public void a(Context context, boolean z) {
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
    }

    public int b() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void b(Context context, boolean z, boolean z2, Location location) {
    }

    /* access modifiers changed from: protected */
    public void b(boolean z) {
    }

    public boolean b(int i) {
        return false;
    }

    public int c(boolean z) {
        return -1;
    }

    public String c() {
        return null;
    }

    public boolean c(int i) {
        return false;
    }

    public String d() {
        return "0";
    }

    public String e() {
        return "0";
    }

    public List<Integer> f() {
        return null;
    }

    public boolean g() {
        return false;
    }

    public String h() {
        return "-";
    }

    public String i() {
        return "-";
    }

    public String j() {
        return "";
    }

    public String k() {
        return "";
    }

    public String l() {
        return "";
    }

    public String m() {
        return "-";
    }

    public String n() {
        return "-";
    }

    public String o() {
        return "-";
    }

    public String p() {
        return "-";
    }

    public String q() {
        return "-";
    }

    public String r() {
        return "-";
    }

    public String s() {
        return "-";
    }

    public String t() {
        return "-";
    }

    public String u() {
        return "-";
    }

    public String v() {
        return "-";
    }

    public String w() {
        return "-";
    }

    public String x() {
        return "-";
    }

    public String y() {
        return "-";
    }

    public int z() {
        return -1;
    }

    /* access modifiers changed from: protected */
    public void a(final Context context, final boolean z, final boolean z2) {
        AsyncTaskUtils.a(new AsyncTask<Object, Object, Object>() {
            /* access modifiers changed from: protected */
            public Object doInBackground(Object... objArr) {
                SHLocationManager.a().a((SHLocationManager.LocationCallback) new SHLocationManager.LocationCallback() {
                    public void onSucceed(String str, Location location) {
                        AreaInfo.this.a(context, z, z2, location);
                    }

                    public void onGetLatLngSucceed(String str, Location location) {
                        AreaInfo.this.b(context, z, z2, location);
                    }

                    public void onFailure(String str) {
                        AreaInfo.this.a(z);
                    }

                    public void onTimeout(String str) {
                        AreaInfo.this.b(z);
                    }
                });
                return null;
            }
        }, new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void a(Context context, boolean z, boolean z2, Location location) {
        if (location == null) {
            if (!z2) {
                a();
            }
            a(z);
            return;
        }
        a(context, a(context, location), location, z, z2);
    }

    /* access modifiers changed from: protected */
    public Address a(Context context, Location location) {
        Bundle extras = location.getExtras();
        if (extras == null) {
            return null;
        }
        return (Address) extras.getParcelable("address");
    }

    /* access modifiers changed from: protected */
    public void a() {
        ToastUtil.a((int) R.string.area_auto_locate_failed);
    }
}
