package com.xiaomi.miot.support.monitor.core.tasks;

public abstract class BaseTask implements ITask {
    public static final String b = "BaseTask";
    protected boolean c = false;
    protected boolean d = false;

    public void g() {
    }

    public boolean e() {
        return this.c;
    }

    public void b() {
        this.c = true;
        this.d = true;
    }

    public boolean f() {
        return this.d && TaskManager.a().f1486a;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void h() {
        this.c = false;
        this.d = false;
    }
}
