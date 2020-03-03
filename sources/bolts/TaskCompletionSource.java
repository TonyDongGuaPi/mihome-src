package bolts;

public class TaskCompletionSource<TResult> {

    /* renamed from: a  reason: collision with root package name */
    private final Task<TResult> f524a = new Task<>();

    public Task<TResult> a() {
        return this.f524a;
    }

    public boolean b() {
        return this.f524a.l();
    }

    public boolean a(TResult tresult) {
        return this.f524a.b(tresult);
    }

    public boolean a(Exception exc) {
        return this.f524a.b(exc);
    }

    public void c() {
        if (!b()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    public void b(TResult tresult) {
        if (!a(tresult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }

    public void b(Exception exc) {
        if (!a(exc)) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }
}
