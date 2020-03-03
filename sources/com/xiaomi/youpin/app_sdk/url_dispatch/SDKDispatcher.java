package com.xiaomi.youpin.app_sdk.url_dispatch;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import java.util.ArrayList;
import java.util.List;

public class SDKDispatcher {
    public Fragment a(Context context, String str, String str2) {
        return null;
    }

    public void a(int i) {
    }

    public void a(List<String> list) {
    }

    public boolean a(Activity activity, String str, int i) {
        return false;
    }

    public boolean a(Activity activity, String str, String str2, int i) {
        return false;
    }

    public boolean a(String str) {
        return true;
    }

    public String[] a() {
        return null;
    }

    public boolean b(String str) {
        return false;
    }

    public String[] b() {
        return null;
    }

    public Fragment c(String str) {
        return null;
    }

    public int d() {
        return 0;
    }

    public void d(String str) {
    }

    public boolean e() {
        return true;
    }

    public boolean e(String str) {
        return false;
    }

    public void f() {
    }

    public void f(String str) {
    }

    public void g() {
    }

    public boolean g(String str) {
        return false;
    }

    public void h() {
    }

    public List<String> c() {
        return new ArrayList();
    }

    public final Fragment a(String str, boolean z) {
        return WXAppStoreApiManager.b().a(str, z);
    }

    public final Fragment b(String str, boolean z) {
        return MiotStoreApi.a().newMiotStoreFragment(str, z);
    }
}
