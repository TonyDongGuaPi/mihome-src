package org.xutils.common;

import org.xutils.common.Callback;
import org.xutils.common.task.AbsTask;

public interface TaskController {
    <T extends AbsTask<?>> Callback.Cancelable a(Callback.GroupCallback<T> groupCallback, T... tArr);

    <T> AbsTask<T> a(AbsTask<T> absTask);

    void a(Runnable runnable);

    void a(Runnable runnable, long j);

    <T> T b(AbsTask<T> absTask) throws Throwable;

    void b(Runnable runnable);

    void c(Runnable runnable);

    void d(Runnable runnable);
}
