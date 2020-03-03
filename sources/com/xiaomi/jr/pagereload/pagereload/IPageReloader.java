package com.xiaomi.jr.pagereload.pagereload;

public interface IPageReloader {

    public enum ReloadOnResumeType {
        RELOAD,
        NOT_RELOAD,
        DELAY_RELOAD
    }

    void a(int i);

    void a(ReloadOnResumeType reloadOnResumeType);

    void a(String str);

    void a(boolean z);

    void a(boolean z, String str, int i);

    boolean a();

    String b();

    void c();

    int d();
}
