package bolts;

import bolts.Task;

class UnobservedErrorNotifier {

    /* renamed from: a  reason: collision with root package name */
    private Task<?> f525a;

    public UnobservedErrorNotifier(Task<?> task) {
        this.f525a = task;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        Task.UnobservedExceptionHandler a2;
        try {
            Task<?> task = this.f525a;
            if (!(task == null || (a2 = Task.a()) == null)) {
                a2.a(task, new UnobservedTaskException(task.g()));
            }
        } finally {
            super.finalize();
        }
    }

    public void a() {
        this.f525a = null;
    }
}
