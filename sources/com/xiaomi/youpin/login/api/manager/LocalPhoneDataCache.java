package com.xiaomi.youpin.login.api.manager;

import com.xiaomi.youpin.login.api.phone.LocalPhoneDetailInfo;
import java.util.ArrayList;
import java.util.List;

public class LocalPhoneDataCache {

    /* renamed from: a  reason: collision with root package name */
    private List<LocalPhoneDetailInfo> f23421a;

    private LocalPhoneDataCache() {
        this.f23421a = new ArrayList();
    }

    public static LocalPhoneDataCache a() {
        return Holder.f23422a;
    }

    public List<LocalPhoneDetailInfo> b() {
        return this.f23421a;
    }

    public void a(List<LocalPhoneDetailInfo> list) {
        this.f23421a.clear();
        this.f23421a.addAll(list);
    }

    public void c() {
        this.f23421a.clear();
    }

    private static class Holder {

        /* renamed from: a  reason: collision with root package name */
        public static final LocalPhoneDataCache f23422a = new LocalPhoneDataCache();

        private Holder() {
        }
    }
}
