package com.ximalaya.ting.android.sdkdownloader.task;

public interface TaskController {
    <T> AbsTask<T> a(AbsTask<T> absTask);

    void a(Runnable runnable);

    void a(Runnable runnable, long j);

    <T> AbsTask<T> b(AbsTask<T> absTask);

    void b(Runnable runnable);

    void b(Runnable runnable, long j);

    void c(Runnable runnable);

    void d(Runnable runnable);

    void e(Runnable runnable);
}
