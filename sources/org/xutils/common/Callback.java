package org.xutils.common;

import java.lang.reflect.Type;

public interface Callback {

    public interface CacheCallback<ResultType> extends CommonCallback<ResultType> {
        boolean a(ResultType resulttype);
    }

    public interface Callable<ResultType> {
        void a(ResultType resulttype);
    }

    public interface Cancelable {
        void a();

        boolean b();
    }

    public interface CommonCallback<ResultType> extends Callback {
        void a(Throwable th, boolean z);

        void a(CancelledException cancelledException);

        void b(ResultType resulttype);

        void c();
    }

    public interface GroupCallback<ItemType> extends Callback {
        void a();

        void a(ItemType itemtype);

        void a(ItemType itemtype, Throwable th, boolean z);

        void a(ItemType itemtype, CancelledException cancelledException);

        void b(ItemType itemtype);
    }

    public interface PrepareCallback<PrepareType, ResultType> extends CommonCallback<ResultType> {
        ResultType c(PrepareType preparetype);
    }

    public interface ProgressCallback<ResultType> extends CommonCallback<ResultType> {
        void a(long j, long j2, boolean z);

        void d();

        void e();
    }

    public interface ProxyCacheCallback<ResultType> extends CacheCallback<ResultType> {
        boolean a();
    }

    public interface TypedCallback<ResultType> extends CommonCallback<ResultType> {
        Type f();
    }

    public static class CancelledException extends RuntimeException {
        public CancelledException(String str) {
            super(str);
        }
    }
}
