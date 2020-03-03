package com.xiaomi.smarthome.scene.action;

import android.content.Context;
import android.content.Intent;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.scene.SmartHomeSceneActionChooseActivity;
import com.xiaomi.smarthome.scene.api.SceneApi;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseInnerAction {
    public String[] b = null;
    public int[] c = null;
    protected int[] d;
    protected List<Integer> e = new ArrayList();

    public abstract int a(int i);

    public int a(SmartHomeSceneActionChooseActivity.InnerValueCallback innerValueCallback, Context context, String str, int i, Object obj, Object obj2) {
        return -1;
    }

    public abstract int a(SceneApi.Action action, Object obj);

    public abstract int a(String str, Object obj, Device device);

    public abstract SceneApi.Action a(String str, int i, Object obj, Intent intent);

    public abstract String a(Object obj);

    public String[] b() {
        return this.b;
    }

    public int[] c() {
        return this.c;
    }

    public List<Integer> d() {
        return this.e;
    }

    public void b(int i) {
        int i2 = 0;
        while (i2 < this.e.size()) {
            if (this.e.get(i2).intValue() != i) {
                i2++;
            } else {
                return;
            }
        }
        this.e.add(Integer.valueOf(i));
    }

    public boolean e() {
        return this.e.size() == this.c.length;
    }

    public String c(int i) {
        for (int i2 = 0; i2 < this.c.length; i2++) {
            if (this.c[i2] == i) {
                return this.b[i2];
            }
        }
        return null;
    }
}
