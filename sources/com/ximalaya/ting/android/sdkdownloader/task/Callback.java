package com.ximalaya.ting.android.sdkdownloader.task;

public interface Callback {

    public interface Cancelable {
        void d();

        boolean f();

        boolean g();

        void h();

        void i();
    }

    public interface CommonCallback<ResultType> extends Callback {
        void a(CancelledException cancelledException);

        void a(RemovedException removedException);

        void a(ResultType resulttype);

        void a(Throwable th, boolean z);

        void c();
    }

    public interface ProgressCallback<ResultType> extends CommonCallback<ResultType> {
        void a();

        void a(long j, long j2, boolean z);

        void b();
    }

    public static class CancelledException extends RuntimeException {
        public CancelledException(String str) {
            super(str);
        }
    }

    public static class RemovedException extends RuntimeException {
        public RemovedException(String str) {
            super(str);
        }
    }
}
