package org.greenrobot.eventbus.util;

import android.content.res.Resources;
import android.util.Log;
import org.greenrobot.eventbus.EventBus;

public class ErrorDialogConfig {

    /* renamed from: a  reason: collision with root package name */
    final Resources f3504a;
    final int b;
    final int c;
    final ExceptionToResourceMapping d;
    EventBus e;
    boolean f = true;
    String g;
    int h;
    Class<?> i;

    public ErrorDialogConfig(Resources resources, int i2, int i3) {
        this.f3504a = resources;
        this.b = i2;
        this.c = i3;
        this.d = new ExceptionToResourceMapping();
    }

    public ErrorDialogConfig a(Class<? extends Throwable> cls, int i2) {
        this.d.a(cls, i2);
        return this;
    }

    public int a(Throwable th) {
        Integer a2 = this.d.a(th);
        if (a2 != null) {
            return a2.intValue();
        }
        String str = EventBus.f3481a;
        Log.d(str, "No specific message ressource ID found for " + th);
        return this.c;
    }

    public void a(int i2) {
        this.h = i2;
    }

    public void a(Class<?> cls) {
        this.i = cls;
    }

    public void a() {
        this.f = false;
    }

    public void a(String str) {
        this.g = str;
    }

    public void a(EventBus eventBus) {
        this.e = eventBus;
    }

    /* access modifiers changed from: package-private */
    public EventBus b() {
        return this.e != null ? this.e : EventBus.a();
    }
}
