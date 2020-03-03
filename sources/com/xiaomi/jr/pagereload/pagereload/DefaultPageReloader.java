package com.xiaomi.jr.pagereload.pagereload;

import com.xiaomi.jr.pagereload.pagereload.IPageReloader;

public class DefaultPageReloader implements IPageReloader {

    /* renamed from: a  reason: collision with root package name */
    private int f10991a;
    private String b;
    private IPageReloader.ReloadOnResumeType c;

    public boolean a() {
        return this.c == IPageReloader.ReloadOnResumeType.RELOAD;
    }

    public void a(IPageReloader.ReloadOnResumeType reloadOnResumeType) {
        this.c = reloadOnResumeType;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public void c() {
        if (this.c == IPageReloader.ReloadOnResumeType.DELAY_RELOAD) {
            this.c = IPageReloader.ReloadOnResumeType.RELOAD;
        }
    }

    public void a(boolean z) {
        PageReloadUtils.a(this, z, (String) null, -1);
    }

    public void a(boolean z, String str, int i) {
        PageReloadUtils.a(this, z, str, i);
    }

    public void a(int i) {
        this.f10991a = i;
    }

    public int d() {
        return this.f10991a;
    }
}
