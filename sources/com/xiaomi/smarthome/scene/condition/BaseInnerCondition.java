package com.xiaomi.smarthome.scene.condition;

import android.app.Activity;
import android.content.Intent;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity;
import com.xiaomi.smarthome.scene.api.SceneApi;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseInnerCondition {

    /* renamed from: a  reason: collision with root package name */
    protected int[] f21535a;
    protected String[] b;
    protected Device c;
    private List<Integer> d = new ArrayList();

    public int a(int i, Activity activity, SceneApi.Condition condition) {
        return -1;
    }

    public abstract int a(SceneApi.Condition condition);

    public abstract SceneApi.Condition a(int i, Intent intent);

    public void a(SimpleDraweeView simpleDraweeView) {
    }

    public abstract int c(int i);

    public boolean g() {
        return true;
    }

    public static BaseInnerCondition a(Device device) {
        if (SceneManager.x().a(device.model, device.did) == null || SceneManager.x().a(device.model, device.did).e.size() <= 0) {
            return null;
        }
        return new InnerConditionCommon(device, (SmartHomeSceneCreateEditActivity.DefaultSceneItemSet) null);
    }

    public static BaseInnerCondition a(Device device, SmartHomeSceneCreateEditActivity.DefaultSceneItemSet defaultSceneItemSet) {
        if (SceneManager.x().a(device.model, device.did) == null || SceneManager.x().a(device.model, device.did).e.size() <= 0) {
            return null;
        }
        return new InnerConditionCommon(device, defaultSceneItemSet);
    }

    public BaseInnerCondition(Device device) {
        this.c = device;
    }

    public void a(int i) {
        int i2 = 0;
        while (i2 < this.d.size()) {
            if (this.d.get(i2).intValue() != i) {
                i2++;
            } else {
                return;
            }
        }
        this.d.add(Integer.valueOf(i));
    }

    public List<Integer> b() {
        return this.d;
    }

    public boolean c() {
        if (this.f21535a == null) {
            return this.d.isEmpty();
        }
        if (this.d.size() == this.f21535a.length) {
            return true;
        }
        return false;
    }

    public Device d() {
        return this.c;
    }

    public String e() {
        return this.c.name;
    }

    public int[] f() {
        return this.f21535a;
    }

    public String b(int i) {
        if (this.f21535a == null) {
            return null;
        }
        for (int i2 = 0; i2 < this.f21535a.length; i2++) {
            if (this.f21535a[i2] == i) {
                return this.b[i2];
            }
        }
        return null;
    }
}
