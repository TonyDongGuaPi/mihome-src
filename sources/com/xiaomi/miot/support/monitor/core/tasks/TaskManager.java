package com.xiaomi.miot.support.monitor.core.tasks;

import android.os.Looper;
import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.Env;
import com.xiaomi.miot.support.monitor.MiotMonitorManager;
import com.xiaomi.miot.support.monitor.core.activity.ActivityTask;
import com.xiaomi.miot.support.monitor.core.activity.InstrumentationHooker;
import com.xiaomi.miot.support.monitor.core.appstart.AppStartTask;
import com.xiaomi.miot.support.monitor.core.block.BlockTask;
import com.xiaomi.miot.support.monitor.core.fps.FpsTask;
import com.xiaomi.miot.support.monitor.core.memory.MemoryTask;
import com.xiaomi.miot.support.monitor.core.net.NetTask;
import com.xiaomi.miot.support.monitor.utils.LogX;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {

    /* renamed from: a  reason: collision with root package name */
    public boolean f1486a;
    private final String b;
    private Map<String, ITask> c;
    private boolean d;

    public static TaskManager a() {
        return Holder.f11476a;
    }

    static final class Holder {

        /* renamed from: a  reason: collision with root package name */
        static TaskManager f11476a = new TaskManager();

        Holder() {
        }
    }

    private TaskManager() {
        this.b = "TaskManager";
        this.f1486a = true;
        this.d = false;
        this.c = new HashMap();
    }

    public void b() {
        if (this.c != null) {
            this.c.clear();
            this.c = null;
        }
    }

    public List<ITask> c() {
        ArrayList arrayList = new ArrayList();
        if (this.c == null) {
            return arrayList;
        }
        for (Map.Entry<String, ITask> value : this.c.entrySet()) {
            arrayList.add(value.getValue());
        }
        return arrayList;
    }

    public ITask a(String str) {
        if (!TextUtils.isEmpty(str) && this.c != null) {
            return this.c.get(str);
        }
        return null;
    }

    public boolean b(String str) {
        synchronized (this) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (this.c.get(str) == null) {
                return false;
            }
            boolean f = this.c.get(str).f();
            return f;
        }
    }

    public boolean d() {
        if (this.c == null) {
            LogX.a(Env.c, "TaskManager", "taskMap is null ");
            return false;
        }
        for (ITask f : this.c.values()) {
            if (f.f()) {
                return true;
            }
        }
        return false;
    }

    public void e() {
        if (this.c == null) {
            LogX.a(Env.c, "TaskManager", "taskMap is null ");
            return;
        }
        f();
        for (ITask next : c()) {
            if (next.f()) {
                next.b();
            }
        }
    }

    public void f() {
        if (this.c.containsKey("activity") && this.c.get("activity").f() && !InstrumentationHooker.b()) {
            InstrumentationHooker.a();
        }
    }

    public void g() {
        this.f1486a = false;
    }

    public void h() {
        ITask iTask;
        if (this.c != null && (iTask = this.c.get("fps")) != null && iTask.f()) {
            iTask.b();
        }
    }

    public void i() {
        if (this.c != null) {
            this.c.clear();
        }
    }

    public void j() {
        if (Looper.myLooper() == Looper.getMainLooper() && !this.d) {
            if (MiotMonitorManager.a().c().i) {
                BlockTask blockTask = new BlockTask();
                blockTask.a(MiotMonitorManager.a().c().c.switchFlag);
                this.c.put(MiotApmTask.j, blockTask);
                AppStartTask appStartTask = new AppStartTask();
                appStartTask.a(MiotMonitorManager.a().c().f.switchFlag);
                this.c.put(MiotApmTask.f, appStartTask);
            }
            ActivityTask activityTask = new ActivityTask();
            activityTask.a(MiotMonitorManager.a().c().d.switchFlag);
            this.c.put("activity", activityTask);
            FpsTask fpsTask = new FpsTask();
            fpsTask.a(MiotMonitorManager.a().c().e.switchFlag);
            this.c.put("fps", fpsTask);
            MemoryTask memoryTask = new MemoryTask();
            memoryTask.a(MiotMonitorManager.a().c().g.switchFlag);
            this.c.put(MiotApmTask.d, memoryTask);
            NetTask netTask = new NetTask();
            netTask.a(MiotMonitorManager.a().c().h.switchFlag);
            this.c.put("net", netTask);
            this.d = true;
        }
    }
}
