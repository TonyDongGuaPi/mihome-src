package com.xiaomi.youpin.hawkeye.storage;

import com.xiaomi.youpin.hawkeye.HawkEye;
import com.xiaomi.youpin.hawkeye.entity.BaseInfo;
import java.util.List;

public class DBSaveImpl implements ISaveData {

    /* renamed from: a  reason: collision with root package name */
    private DBHelper f23369a;

    public BaseInfo a(int i) {
        return null;
    }

    public boolean a(BaseInfo baseInfo) {
        if (b()) {
            return this.f23369a.a(baseInfo);
        }
        return false;
    }

    public List<BaseInfo> a() {
        if (b()) {
            return this.f23369a.b();
        }
        return null;
    }

    public boolean b(int i) {
        if (b()) {
            return this.f23369a.c(i);
        }
        return false;
    }

    private boolean b() {
        if (this.f23369a != null) {
            return true;
        }
        if (!HawkEye.a()) {
            return false;
        }
        this.f23369a = new DBHelper(HawkEye.d());
        return true;
    }
}
